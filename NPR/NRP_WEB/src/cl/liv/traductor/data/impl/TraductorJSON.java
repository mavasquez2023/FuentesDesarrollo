package cl.liv.traductor.data.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.sbs.modelo.dto.DefaultDTO;
import cl.sbs.util.reflection.impl.UtilReflectionImpl;

public class TraductorJSON {

	JSONObject retorno = new JSONObject();
	
	public JSONObject generarJSON(Object input, String key){
		
		if(key == null)
			key = "data";
		getDataObject(input,key, retorno);
		
		return retorno;
	}
	public void getDataObject(Object data, String key, Object parent){
		JSONArray arrayParent = null;
		JSONObject objectParent = null;
		if(parent instanceof JSONArray){
			arrayParent = (JSONArray) parent;
		}
		else{
			objectParent = (JSONObject) parent;
		}
		
		
		if(data instanceof ArrayList){
			JSONArray array = new JSONArray();
			try {
				objectParent.put(key, array);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0; i< ((ArrayList)data).size(); i++){
				getDataObject(((ArrayList)data).get(i), null, array);
			}
			
		}
		else if (data instanceof DefaultDTO){
			JSONObject objetoNegocio = new JSONObject();
			try {
				if(arrayParent != null){
					arrayParent.put(arrayParent.length(), objetoNegocio);
				}
				else if(objectParent != null){
					objectParent.put(key, objetoNegocio);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			UtilLogWorkflow.debug("Es un objeto de negocio "+ data.getClass());
			Method[] metodos = data.getClass().getMethods();
			
			for(int i=0; i<metodos.length; i++){
				Method method = metodos[i];
				if(method.getName().indexOf("get") == 0 && !method.getName().equals("getClass") ){
					//UtilLogWorkflow.debug(method.getName() +" "+ ejecutarReflectionSinParametros(data, method));
					Object valor = UtilReflectionImpl.ejecutarReflectionSinParametros(data, method);

					getDataObject(valor, method.getName().substring(3).toLowerCase(), objetoNegocio);
				}
			}
		}
		else if(data instanceof String){
			try {
				objectParent.put(key, data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(data instanceof Integer){
			try {
				objectParent.put(key, data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(data instanceof Date){
			try {
				objectParent.put(key, data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(data instanceof Boolean){
			try {
				objectParent.put(key, data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(data instanceof BigInteger){
			try {
				objectParent.put(key, data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		else if(data instanceof BigDecimal){
			try {
				objectParent.put(key, data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		else if(data instanceof Long){
			try {
				objectParent.put(key, data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(data instanceof Double){
			try {
				objectParent.put(key, data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(data instanceof JSONObject){
			try {
				objectParent.put(key, data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(data instanceof HashMap){

			JSONObject item = new JSONObject();
			if(arrayParent != null)
				arrayParent.put(item);
			if(objectParent != null)
				try {
					objectParent.put("item",item);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			ArrayList keys = getKeysOrdenadasPorNombre((HashMap) data);
			for (int i=0; i<keys.size(); i++) {
				getDataObject( ((Map.Entry)keys.get(i)).getValue(),((Map.Entry)keys.get(i)).getKey().toString(),item);
			}
		}
		else {
			UtilLogWorkflow.debug("\n\n*****************   WARNING *********************");
			try{
			UtilLogWorkflow.debug("clase no procesada en json -> "+ data.getClass());
			}
			catch(Exception e){
				UtilLogWorkflow.debug("clase no procesada en json -> "+ data);
					
			}
			UtilLogWorkflow.debug("*****************   WARNING *********************");
		}

	}
	public ArrayList getKeysOrdenadasPorNombre(HashMap data ){
		ArrayList entries = new ArrayList();
		
		Iterator it=data.entrySet().iterator(); 
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			entries.add(entry);
		}
			
		/*
		entries.sort(new Comparator() {
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return o1.toString().compareTo(o2.toString());
			}
	
		});*/
		//UtilLogWorkflow.debug("retornando entries: "+ entries);
		return entries;
	}
	
}
