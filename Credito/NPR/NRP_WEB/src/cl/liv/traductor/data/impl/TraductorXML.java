package cl.liv.traductor.data.impl;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import cl.sbs.modelo.dto.DefaultDTO;
import cl.sbs.util.reflection.impl.UtilReflectionImpl;

public class TraductorXML {

	String retorno = "";
	
	public String generarXML(Object input, String key){

		retorno =retorno+ "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><root>";
		if(key == null)
			key = "data";
		
		getDataObject(input,key);

		retorno =retorno+ "</root>";
		return retorno;
	}
	public void getDataObject(Object data, String key){
		
		if(data instanceof ArrayList){

			retorno =retorno+ getTagAbierto(key);
			for (int i=0; i<((ArrayList) data).size();i++) {
				Object item = ((ArrayList) data).get(i);				
				getDataObject(item, key);
			}

			retorno =retorno+ getTagCerrado(key);
		}
		else if (data instanceof DefaultDTO){
			System.out.println("dto-> "+ data.getClass().toString());

			String tag = data.getClass().toString().split("\\.")[data.getClass().toString().split("\\.").length - 1].replace("DTO", "").toLowerCase();
			retorno =retorno+ getTagAbierto(tag);
			System.out.println("Es un objeto de negocio "+ data.getClass());
			Method[] metodos = data.getClass().getMethods();
			for(int i=0; i<metodos.length; i++){
				Method method = metodos[i];
				if(method.getName().indexOf("get") == 0 && !method.getName().equals("getClass") ){
					//System.out.println(method.getName() +" "+ ejecutarReflectionSinParametros(data, method));
					Object valor = UtilReflectionImpl.ejecutarReflectionSinParametros(data, method);

					getDataObject(valor, method.getName().substring(3).toLowerCase());
				}
			}
			retorno =retorno+ getTagCerrado(tag);
		}
		else if(data instanceof String){

			retorno =retorno+ getTagConCData(key, (String)data);
			
		}
		else if(data instanceof Integer){

			retorno =retorno+ getTagConData(key, String.valueOf((Integer)data) );
			
		}
		else if(data instanceof Date){
			retorno =retorno+ getTagConData(key, String.valueOf(((Date)data).getTime()) );
			
		}
		else if(data instanceof Boolean){
			retorno =retorno+ getTagConData(key, String.valueOf(((Boolean)data)) );
			
		}		
		else if(data instanceof BigInteger){

			retorno =retorno+ getTagConData(key, String.valueOf(((BigInteger)data)) );
		}		
		else if(data instanceof Long){

			retorno =retorno+ getTagConData(key, String.valueOf(((Long)data)) );
		}
		else if(data instanceof HashMap){


			retorno =retorno+ getTagAbierto("item");
			
			
			ArrayList keys = getKeysOrdenadasPorNombre((HashMap) data);
			System.out.println("keys: "+ keys);
			for (int i=0; i<keys.size(); i++) {
				getDataObject( ((HashMap) data).get(keys.get(i)),keys.get(i).toString());
			}

			retorno =retorno+ getTagCerrado("item");
		}
		else {
			System.out.println("\n\n*****************   WARNING *********************");
			System.out.println("clase no procesada en xml -> "+ data);
			System.out.println("*****************   WARNING *********************");
		}
	}

	public ArrayList getKeysOrdenadasPorNombre(HashMap data ){
		ArrayList entries = new ArrayList();
		System.out.println("data "+ data);
		
		
		while(data.entrySet().iterator().hasNext()){
			Entry entry = (Entry)data.entrySet().iterator().next();
			entries.add(entry);
		}
		/*
		entries.sort(new Comparator() {
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return o1.toString().compareTo(o2.toString());
			}
	
		});
		*/
		return entries;
	}
	
	public String getTagAbierto(String tag){
		return "<"+tag+">";
	}
	public String getTagCerrado(String tag){
		return "</"+tag+">";
	}
	public String getTagConData(String tag, String data){
		return getTagAbierto(tag) +data+getTagCerrado(tag);
	}
	
	public String getTagConCData(String tag, String data){
		return getTagAbierto(tag)+"<![CDATA[" +data+"]]>" +getTagCerrado(tag);
	}
}
