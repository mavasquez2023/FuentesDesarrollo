package cl.liv.export.comun.util;

import java.util.ArrayList;
import java.util.HashMap;

import cl.liv.export.comun.dao.ExportDAO;

public class ReadXMLUtil {
	public static HashMap procesarInputs( HashMap<String, Object> sesion){
		HashMap datoInput = new HashMap();
		
		HashMap<String, Object> conf = (HashMap<String, Object>) sesion.get("config");
		HashMap<String, String> parametros = (HashMap<String, String>) sesion.get("parametros");
		
		
		ArrayList<HashMap<String, String>> inputs = (ArrayList<HashMap<String, String>>)conf.get("inputs");
		
		for(int i=0; i< inputs.size();i++){
			HashMap<String, String> item = (HashMap<String, String>)inputs.get(i);
			if("string".equals(item.get("tipo").toLowerCase())){
				if(! "true".equals(item.get("es_datasource"))){
					 Object valor = null;
					 if(item.get("origen").equals("constante")){
						 valor = item.get("dato");
						 datoInput.put(item.get("field_name"), valor);
					 }
					 else if(item.get("origen")!= null && item.get("origen").equals("parametro")){
						 valor = parametros.get( item.get("dato") );
						 datoInput.put(item.get("field_name"), valor );
					 }
					 else if(item.get("origen").equals("properties")){
						 valor = SessionUtil.getProperty(item.get("file"), item.get("dato"));
						 datoInput.put(item.get("field_name"), valor );
					 }
					 else if(item.get("origen").equals("java_reflexion")){
							Mensajes.info("Executando java por reflexion...");
							UtilReflection ref = new UtilReflection();

							ref.loadClass(item.get("clase"));

							ref.loadMethod(item.get("metodo") ,HashMap.class);
							Object result = ref.executeMethod(sesion);
							valor = (String) result;
							 datoInput.put(item.get("field_name"), valor );
						}
					 
					//Graba la data en la sesion temporal
					(( HashMap<String, Object>) sesion.get("inputs") ).put(item.get("field_name"), valor);
					
				}
				
				
				
			}
			if("int".equals(item.get("tipo").toLowerCase())){
				if(! "true".equals(item.get("es_datasource"))){
					 Integer valor = null;
					 if(item.get("origen").equals("constante")){
						 valor = Integer.parseInt( item.get("dato") );
						 datoInput.put(item.get("field_name"),  valor);
					 }
					 else if(item.get("origen").equals("parametro")){
						 valor = Integer.parseInt( parametros.get( item.get("dato") ).toString());
						 datoInput.put(item.get("field_name"), valor );
					 }
					 else if(item.get("origen").equals("properties")){
						 valor = Integer.parseInt( SessionUtil.getProperty(item.get("file"), item.get("dato")));
						 datoInput.put(item.get("field_name"), valor );
					 }
					 
					 
					//Graba la data en la sesion temporal
					(( HashMap<String, Object>) sesion.get("inputs") ).put(item.get("field_name"),valor);

				}
				
				
				
			}
			else if("map".equals(item.get("tipo").toLowerCase())){
				HashMap<String, Object> res = null;
				if(item.get("origen").equals("query")){
				
					Mensajes.info("Executando query...");
					String[] dataQuery = Funciones.getQuery(item.get("ref_query"), (ArrayList<HashMap<String, String>>)conf.get("querys"));
					if(dataQuery == null){
						return null;
					}
					dataQuery[0] = Funciones.agregarParametrosAQuery(dataQuery[0], parametros);

					if(!dataQuery[1].contains("/"))
						dataQuery[1] = "/comun/datasources/"+dataQuery[1];
				
					ArrayList<HashMap<String, Object>> data =ExportDAO.ejecutarSelect(dataQuery[0],dataQuery[1]);
					if(data != null && data.size() > 0)
					res = data.get(0);	
				}
				else if(item.get("origen").equals("java_reflexion")){
					Mensajes.info("Executando java por reflexion...");
					UtilReflection ref = new UtilReflection();

					ref.loadClass(item.get("clase"));

					ref.loadMethod(item.get("metodo") ,HashMap.class);
					
					
					Object result = ref.executeMethod(sesion);
					res = (HashMap<String, Object>) result;
				}
				
				(( HashMap<String, Object>) sesion.get("inputs") ).put(item.get("field_name"), res);
				if(! "true".equals(item.get("es_datasource"))){
					datoInput.put(item.get("field_name"), res);
				}
				else{
					ArrayList<HashMap> dataSubReport = new ArrayList<HashMap>();
					dataSubReport.add(res);
					datoInput.put(item.get("field_name"), dataSubReport);
					
				}
			}
			else if("list".equals(item.get("tipo").toLowerCase())){
			
				ArrayList<HashMap<String, Object>> res = null;
				if(item.get("origen").equals("query")){
				
					Mensajes.info("Executando query...");
					String[] dataQuery = Funciones.getQuery(item.get("ref_query"), (ArrayList<HashMap<String, String>>)conf.get("querys"));
					if(dataQuery == null){
						return null;
					}
					dataQuery[0] = Funciones.agregarParametrosAQuery(dataQuery[0], parametros);

					if(!dataQuery[1].contains("/"))
						dataQuery[1] = "/comun/datasources/"+dataQuery[1];
				
					res =ExportDAO.ejecutarSelect(dataQuery[0],dataQuery[1]); 
						
				}
				else if(item.get("origen").equals("java_reflexion")){
					Mensajes.info("Executando java por reflexion...");
					UtilReflection ref = new UtilReflection();

					ref.loadClass(item.get("clase"));

					ref.loadMethod(item.get("metodo") ,HashMap.class);
					
					
					Object result = ref.executeMethod(sesion);
					res = (ArrayList<HashMap<String, Object>>) result;
				}
				
				(( HashMap<String, Object>) sesion.get("inputs") ).put(item.get("field_name"), res);
				if(! "true".equals(item.get("es_datasource"))){
					datoInput.put(item.get("field_name"), res);
				}
				else{
					datoInput.put(item.get("field_name"), new HashMap<String, Object>());					
				}
			}
			
		}
		
		return datoInput;
	}
}
