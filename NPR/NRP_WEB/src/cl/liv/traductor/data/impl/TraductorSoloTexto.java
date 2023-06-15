package cl.liv.traductor.data.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import cl.sbs.modelo.dto.DefaultDTO;
import cl.sbs.modelo.dto.PersonaDTO;
import cl.sbs.util.reflection.impl.UtilReflectionImpl;

public class TraductorSoloTexto {
	

	public String retornoStr = "";
	public Object retornoObj = "";
	public String generarHash(Object input){
		System.out.println("generando hash "+ input);
		getDataObject(input,true);
		retornoStr = retornoStr.replace(DELIMITADOR_DATA+DELIMITADOR_FINAL_OBJECT, DELIMITADOR_FINAL_OBJECT);
		System.out.println("retorno -> "+ retornoStr);
		return retornoStr;
	}
	
	public static String DELIMITADOR_INICIAL_ARRAY 	="!{";
	public static String DELIMITADOR_FINAL_ARRAY 		="}!";
	public static String DELIMITADOR_INICIAL_OBJECT 	="![";
	public static String DELIMITADOR_FINAL_OBJECT 		="]!";
    public static String DELIMITADOR_DATA 				="::";
	
	public void getDataObject(Object data, boolean generar){
		
		if(data instanceof ArrayList){
			retornoStr =retornoStr+DELIMITADOR_INICIAL_ARRAY;
			for (int i=0; i<((ArrayList) data).size();i++) {
				Object item = ((ArrayList) data).get(i);
				getDataObject(item,generar);
			}
			retornoStr =retornoStr+DELIMITADOR_FINAL_ARRAY;
		}
		else if (data instanceof DefaultDTO){
			retornoStr =retornoStr+DELIMITADOR_INICIAL_OBJECT;
			System.out.println("Es un objeto de negocio "+ data.getClass());
			Method[] metodos = data.getClass().getMethods();
			metodos = ordenarMetodos(metodos);
			for(int i=0; i<metodos.length; i++){
				Method method = metodos[i];
				if(method.getName().indexOf("get") == 0 && !method.getName().equals("getClass") ){
					System.out.println(method.getName() +" "+ UtilReflectionImpl.ejecutarReflectionSinParametros(data, method));
					Object valor = UtilReflectionImpl.ejecutarReflectionSinParametros(data, method);

					getDataObject(valor,generar);
				}
			}
			retornoStr =retornoStr+DELIMITADOR_FINAL_OBJECT;
			retornoStr =retornoStr+DELIMITADOR_DATA;
		}
		else if(data instanceof String){

			retornoStr =retornoStr+data+DELIMITADOR_DATA;
		}
		else if(data instanceof Integer){

			retornoStr =retornoStr+data+DELIMITADOR_DATA;
		}
		else if(data instanceof Date){

			retornoStr =retornoStr+((Date)data).getTime()+DELIMITADOR_DATA;
		}
		else if(data instanceof Boolean){

			retornoStr =retornoStr+(Boolean) data+DELIMITADOR_DATA;
		}		
		else if(data instanceof BigInteger){

			retornoStr =retornoStr+(BigInteger) data+DELIMITADOR_DATA;
		}		
		else if(data instanceof Long){

			retornoStr =retornoStr+(Long) data+DELIMITADOR_DATA;
		}
		else if(data instanceof HashMap){

			//retornoStr =retornoStr+(HashMap) data+DELIMITADOR_DATA;
			
			
			ArrayList keys = getKeysOrdenadasPorNombre((HashMap) data);
			System.out.println("keys: "+ keys);
			for (int i=0; i<keys.size(); i++) {
				getDataObject( ((HashMap) data).get(keys.get(i)),generar);
			}
		}
		else {
			System.out.println("\n\n*****************   WARNING *********************");
			System.out.println("clase no procesada en txt -> "+ data.getClass());
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
	
		});*/
		return entries;
	}
	
	public static Method[] ordenarMetodos(Method[] metodos){
		ArrayList metodosArray = new ArrayList();
		
		for(int i=0; i<metodosArray.size(); i++){
			metodosArray.add(metodosArray.get(i));
		}
		
		Collections.sort(metodosArray, new Comparator() {

			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return compare((Method)o1, (Method)o2);
			}
			
			public int compare(Method m1, Method m2)
	        {

	            return  m1.getName().compareTo(m2.getName());
	        }
	        
	        
	    });
		
		metodos = (Method[]) metodosArray.toArray(new Method[metodosArray.size()]);
		return metodos;
	}
	
	public static void mostrarMetodos(Method[] metodos){
		for (int i=0; i<metodos.length; i++) {
			System.out.println(metodos[i].getName());
		}
	}
	
	public void getObjectFromHash(String hash, String clase){
		System.out.println("hash: "+ hash);
		//Es array
		if(hash.indexOf(DELIMITADOR_INICIAL_ARRAY) == 0){
			ArrayList retornoAux = new ArrayList();
			System.out.println(hash);
			hash = hash.replace(DELIMITADOR_INICIAL_ARRAY, "");
			hash = hash.replace(DELIMITADOR_FINAL_ARRAY, "");
			System.out.println(hash);
			String[] registro = hash.split("]!!");
			
			for(int i=0; i<registro.length; i++){
				String item = registro[i];
				System.out.println("item " + item);
				retornoAux.add(  getObject(item, clase) );
			}
			
			/*
			String clase = data.getClass()+"";
			Method[] metodos = data.getClass().getMethods();
			for (Method method : metodos) {
				if(method.getName().indexOf("get") == 0 && !method.getName().equals("getClass") ){
					System.out.println(method.getName() + ejecutarReflectionSinParametros(data, method));
					Object valor = ejecutarReflectionSinParametros(data, method);
					getDataObject(valor,generar);
				}
			}*/
			retornoObj = retornoAux;
			
		}
		else if(hash.indexOf(DELIMITADOR_INICIAL_OBJECT) == 0){
			
			retornoObj  = getObject(hash, clase) ;

		}
		
		
		
	}
	
	private static Object getObject(String hash, String clase){
		
		hash = getHashPreparado(hash);
		String[] atributos = getAtributosLimpios( hash.split(DELIMITADOR_DATA) );
		
		int contador =0;
		Object obj = UtilReflectionImpl.getInstance(clase);
		Method[] metodos = obj.getClass().getMethods();
		metodos = ordenarMetodos(metodos);
		for(int i=0; i<metodos.length; i++){
			Method method = metodos[i];
			if(method.getName().indexOf("get") == 0 && !method.getName().equals("getClass") ){
				String atributo = null;
				if(atributos.length > contador)
					atributo = atributos[contador];
				System.out.println("\n----> "+ method.getName()+":"+atributo);
				
				String metodoString = method.getName();
				metodoString = "set"+metodoString.substring(3);
				Class[] paramTypes = new Class[1];
				paramTypes[0] = method.getReturnType();
				String nombreClase = method.getReturnType().toString().replace("class ","");
				
				Object instanciaDelObjeto = UtilReflectionImpl.getInstance(nombreClase);
				if(atributos.length > contador){
					Object valorObtenido = getValueObject(instanciaDelObjeto, atributo);
					
					Object[] parametros = new Object[1];
					parametros[0]= valorObtenido;
					contador++;
					UtilReflectionImpl.executeReflection(obj, metodoString, paramTypes, parametros);
				}
				
			}
		}
		return obj;
	}
	
	
	private static Object getValueObject(Object instancia, String data){

		if(instancia instanceof String){
			return data;
		}
		else if(instancia instanceof Integer){
			return new Integer(data);
		}
		else if(instancia instanceof Date){
			return new Date( Long.parseLong(data) );
		}
		else if(instancia instanceof DefaultDTO){
			System.out.println("\n\nEs un objeto de negocio, a procesar el objeto");
			return getObject(data, instancia.getClass().toString().replace("class ", ""));
		}
		
		return null;
		
	}
	
	private static String[] getAtributosLimpios(String[] atributos){
		String[] atributosProcesados = atributos;
		System.out.println("\n\n\n**************** LIMPIANDO ATRIBUTOS ******************");
		if(atributos != null){
			atributosProcesados = new String[atributos.length];
			for (int i=0; i< atributos.length ; i++) {
				String atributo = atributos[i];
				//Si el texto trae solo ![ al principio, es basura, se limpia
				if(atributo.indexOf(DELIMITADOR_INICIAL_OBJECT) == 0 && atributo.indexOf(DELIMITADOR_FINAL_OBJECT) < 0 ){
					atributo = atributo.substring(2);
				}
				else if(atributo.indexOf(DELIMITADOR_FINAL_OBJECT+DELIMITADOR_FINAL_OBJECT) > 0 ){
					atributo = atributo.substring(0, atributo.indexOf(DELIMITADOR_FINAL_OBJECT+DELIMITADOR_FINAL_OBJECT) + 2);
				}
				else if(atributo.indexOf(DELIMITADOR_INICIAL_OBJECT) < 0 && atributo.indexOf(DELIMITADOR_FINAL_OBJECT) > 0 ){
					atributo = atributo.substring(0, atributo.indexOf(DELIMITADOR_FINAL_OBJECT) );
				}
				atributo = atributo.replaceAll(";;", DELIMITADOR_DATA);
				System.out.println("atributo procesado -> "+ atributo);
				atributosProcesados[i]=atributo;
			}
		}

		System.out.println("**************** FIN LIMPIANDO ATRIBUTOS ******************\n\n\n");
		return atributosProcesados;
	}
	
	private static String getHashPreparado(String hash){
		//Protejo los objetos que van dentro
		/*hash = hash.replace(DELIMITADOR_DATA+DELIMITADOR_INICIAL_OBJECT, ";;;[");
		hash = hash.replace(DELIMITADOR_DATA+DELIMITADOR_FINAL_OBJECT, ";;;[");
		
		hash = hash.replace(DELIMITADOR_INICIAL_OBJECT, "");
		hash = hash.replace(DELIMITADOR_FINAL_OBJECT, "");
		
		if(hash.charAt(0) == '[')
			hash = hash.substring(1);
		
		//Vuelvo a la forma original
		hash = hash.replace(";;;[", DELIMITADOR_DATA+DELIMITADOR_INICIAL_OBJECT);
		*/
		String nuevoHash = reemplazarDelimitadorEnObject(hash);
		System.out.println("hash antes: "+ hash);
		System.out.println("hash despues: "+ nuevoHash);
		
		/*if(indexInicialObjeto == -1 && indexFinalObject == -1){
			indexInicialObjeto = nuevoHash.indexOf(DELIMITADOR_INICIAL_OBJECT);
			indexFinalObject = nuevoHash.indexOf(DELIMITADOR_FINAL_OBJECT);
			if(indexInicialObjeto == 0){
				nuevoHash = nuevoHash.substring(2);

				nuevoHash = nuevoHash.substring(0, nuevoHash.length() -2);
				System.out.println("nuevo hash -> "+ nuevoHash);
			}
		}*/
		
		return nuevoHash;
	}
	
	public static String reemplazarDelimitadorEnObject(String hash){
		/*int indexInicialObjeto = hash.substring(1).indexOf(DELIMITADOR_INICIAL_OBJECT);
		int indexFinalObject = hash.substring(0,hash.length()-2).indexOf(DELIMITADOR_FINAL_OBJECT);
		System.out.println("delimitador inicial object: -> "+ indexInicialObjeto);
		System.out.println("delimitador final object: -> "+ indexFinalObject);
		String nuevoHash = hash;
		if(indexInicialObjeto != -1 && indexFinalObject != -1){
			String reemplazo = hash.substring(indexInicialObjeto,indexFinalObject).replaceAll(DELIMITADOR_DATA, ";;");
		
			nuevoHash = hash.substring(0,indexInicialObjeto)+reemplazo+hash.substring(indexFinalObject);
		}*/
		System.out.println(hash.length());
		
		int indexInicialObjeto = hash.substring(1).indexOf(DELIMITADOR_INICIAL_OBJECT);
		while(indexInicialObjeto != -1){
			System.out.println("apertura->"+ indexInicialObjeto);
			int indexProximoCierreObjeto = indexInicialObjeto + hash.substring(indexInicialObjeto).indexOf(DELIMITADOR_FINAL_OBJECT);
			System.out.println("cierre  ->"+indexProximoCierreObjeto);
			//indexInicialObjeto = hash.substring(indexProximoCierreObjeto).indexOf(DELIMITADOR_INICIAL_OBJECT);
			
			String reemplazo =   hash.substring(indexInicialObjeto,indexProximoCierreObjeto).replaceAll(DELIMITADOR_DATA, ";;");
			System.out.println("reemplazo -> "+ reemplazo);
			hash = hash.substring(0,indexInicialObjeto)+reemplazo+hash.substring(indexProximoCierreObjeto);
			//nuevoHash = hash.substring(0,indexInicialObjeto)+reemplazo+hash.substring(indexFinalObject);
			
			if(hash.substring(indexProximoCierreObjeto).indexOf(DELIMITADOR_INICIAL_OBJECT) > 0){
				indexInicialObjeto = indexProximoCierreObjeto
						+ hash.substring(indexProximoCierreObjeto).indexOf(DELIMITADOR_INICIAL_OBJECT);
				System.out.println("nuevo -> " + hash.substring(indexProximoCierreObjeto));
				System.out.println("indexInicialObjeto: "+ indexInicialObjeto);
			}
			else{
				indexInicialObjeto = -1;
			}
		}
		
		return hash;
	}
	
	
}
