package ztest;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import cl.jfactory.planillas.service.helper.WorkFlowHelper;
import cl.jfactory.planillas.service.util.UtilesWorkflow;
import cl.liv.comun.utiles.logs.UtilLogErrores;
import cl.liv.core.request.exception.RequestUtilException;
import cl.liv.core.request.impl.RequestImpl;
import cl.liv.core.request.tipos.TiposEntrada;
import cl.liv.core.request.tipos.TiposMetodoHttp;
import cl.liv.core.request.tipos.TiposSalida;

public class Prueba {
	public static void main(String[] args) {
		
		//ejecutarProcesoManualmentePorUsuario("luisibacache@gmail.com", "123456");
		
		/*
		String folder = "/home/clillo/planillas/workflow/201804/";
		if(new File(folder).exists()){
			System.out.println("ya hay estructura");
			if(new File(folder+"blocked.txt").exists()){
				System.out.println("proceso bloqueado...");
			}
			else if(new File(folder+"0.txt").exists() && !new File(folder+"1.txt").exists()){
				System.out.println("ejecuto la validacion...");
				JSONObject json = validaArchivoSAPCargado();
				System.out.println("salida: "+ json);
			}
			else if(new File(folder+"1.txt").exists() && !new File(folder+"2.txt").exists()){
				System.out.println("cargando proceso..");
				JSONObject json = cargarDataSAP();
				System.out.println("salida: "+ json);
			}
			else{
				System.out.println("proceso terminado");
			}
		}
		else{
			System.out.println("creando estructura");
			JSONObject json = generarEstructuraWorkFlow();
			System.out.println("salida: "+ json);
		}
		*/
		
		//obtenerEstadoWF();
		//cargarProcesos();
		buscarEntidad();
		
	}
	
	/*private static void ejecutarProcesoCarga(){
		String ID_PETICION = "generarEstructuraWorkflow";
		HashMap pars = new HashMap();
		pars.put("token", "041ec6d4d967a2b97489b4c5cdaee7a9");
	try {
			if(json != null && json.getJSONObject("data") != null){
				if(json.getJSONObject("data").getString("codigo").equals( "OK" ) || json.getJSONObject("data").getString("codigo").equals( "W001" )){
					System.out.println("estructura ok...");
					JSONObject json2 = validaArchivoSAPCargado();
					System.out.println("json obtenido -> "+ json2);
					System.out.println("Esperando 5segs...");
					try {
						Thread.sleep(5000);
						ejecutarProcesoCarga();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/
	private static JSONObject generarEstructuraWorkFlow(){
		
		
		String ID_PETICION = "generarEstructuraWorkflow";
		HashMap pars = new HashMap();
		pars.put("token", "041ec6d4d967a2b97489b4c5cdaee7a9");
	

		try {
			int tipoSalida = TiposSalida.SALIDA_JSON;
			Object salida = RequestImpl.procesarPeticion(pars, ID_PETICION, tipoSalida, TiposEntrada.ENTRADA_JSON, TiposMetodoHttp.POST);

			if (tipoSalida == TiposSalida.SALIDA_JSON) {
				((JSONObject) salida).toString();
				return ((JSONObject) salida);
			} else if (tipoSalida == TiposSalida.SALIDA_XML) {
				((String) salida).toString();
			} else if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {
				((String) salida).toString();
			}

		} catch (RequestUtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	private static JSONObject buscarEntidad(){
		
		
		String ID_PETICION = "buscarEmpresaHolding";
		HashMap pars = new HashMap();
		pars.put("codigo_a_buscar", "HO0001");
	

		try {
			int tipoSalida = TiposSalida.SALIDA_JSON;
			Object salida = RequestImpl.procesarPeticion(pars, ID_PETICION, tipoSalida, TiposEntrada.ENTRADA_JSON, TiposMetodoHttp.POST);

			if (tipoSalida == TiposSalida.SALIDA_JSON) {
				((JSONObject) salida).toString();
				return ((JSONObject) salida);
			} else if (tipoSalida == TiposSalida.SALIDA_XML) {
				((String) salida).toString();
			} else if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {
				((String) salida).toString();
			}

		} catch (RequestUtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	private static JSONObject validaArchivoSAPCargado(){
		
		
		String ID_PETICION = "validaArchivoSAPCargado";
		HashMap pars = new HashMap();
		pars.put("email", "luisibacache@gmail.com");
		pars.put("token", "041ec6d4d967a2b97489b4c5cdaee7a9");
		

		try {
			int tipoSalida = TiposSalida.SALIDA_JSON;
			Object salida = RequestImpl.procesarPeticion(pars, ID_PETICION, tipoSalida, TiposEntrada.ENTRADA_JSON, TiposMetodoHttp.POST);

			if (tipoSalida == TiposSalida.SALIDA_JSON) {
				((JSONObject) salida).toString();
				return ((JSONObject) salida);
			} else if (tipoSalida == TiposSalida.SALIDA_XML) {
				((String) salida).toString();
			} else if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {
				((String) salida).toString();
			}

		} catch (RequestUtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	private static JSONObject cargarDataSAP(){
		
		
		String ID_PETICION = "cargarDataSAP";
		HashMap pars = new HashMap();
		pars.put("email", "luisibacache@gmail.com");

		pars.put("token", "041ec6d4d967a2b97489b4c5cdaee7a9");

		try {
			int tipoSalida = TiposSalida.SALIDA_JSON;
			Object salida = RequestImpl.procesarPeticion(pars, ID_PETICION, tipoSalida, TiposEntrada.ENTRADA_JSON, TiposMetodoHttp.POST);

			if (tipoSalida == TiposSalida.SALIDA_JSON) {
				((JSONObject) salida).toString();
				return ((JSONObject) salida);
			} else if (tipoSalida == TiposSalida.SALIDA_XML) {
				((String) salida).toString();
			} else if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {
				((String) salida).toString();
			}

		} catch (RequestUtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	
	private static void validaArchivoCargados(){
		
		
		HashMap pars = new HashMap();
		pars.put("email", "luisibacache@gmail.com");
	
		new WorkFlowHelper().validaArchivosCargados(new HashMap(), pars);
	}
	
	
	private static void cargarProcesos(){
		
		
		HashMap pars = new HashMap();
		pars.put("email", "luisibacache@gmail.com");
		pars.put("periodo", "201805");
	
		try {
			new WorkFlowHelper().ejecutarProcesoConsolidacion(new HashMap(), pars);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			UtilLogErrores.error(e.getMessage());
		}
		//new WorkFlowHelper().validaArchivosCargados(new HashMap(), pars);
	}
	
	
	private static void obtenerEstadoWF(){
		
		
		HashMap pars = new HashMap();
		pars.put("email", "luisibacache@gmail.com");
		pars.put("periodo", "201804");
	
		HashMap salida = new WorkFlowHelper().obtenerEstadoWorklow(new HashMap(), pars);
		System.out.println("salida: "+ salida);
	}
	
	
	public static void ejecutarProcesoManualmentePorUsuario(String email, String password){
		JSONObject dataAutenticacion = autenticarUsuario(email,password);
		
		try {
			if(dataAutenticacion != null && dataAutenticacion.getJSONObject("data").getString("token") != null){
				try {
					System.out.println("data Ejecucion->"+ ejecutarCargaSAPManual(dataAutenticacion) ) ;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static JSONObject autenticarUsuario(String email, String password){
		String ID_PETICION = "autenticarUsuario";
		HashMap pars = new HashMap();
		pars.put("email", email);
		pars.put("_password", UtilesWorkflow.getMD5( password ) );
	

		try {
			int tipoSalida = TiposSalida.SALIDA_JSON;
			Object salida = RequestImpl.procesarPeticion(pars, ID_PETICION, tipoSalida, TiposEntrada.ENTRADA_JSON, TiposMetodoHttp.POST);
			System.out.println("salida servicio->"+ salida);
			if (tipoSalida == TiposSalida.SALIDA_JSON) {
				((JSONObject) salida).toString();
				return ((JSONObject) salida);
			} else if (tipoSalida == TiposSalida.SALIDA_XML) {
				((String) salida).toString();
			} else if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {
				((String) salida).toString();
			}

		} catch (RequestUtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * String salida = null; try { salida = (String) procesarPeticion(pars,
		 * ID_PETICION, TiposSalida.SALIDA_JSON); } catch (RequestUtilException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); return;
		 * }
		 */
		return null;
	}

	public static void validarToken(){
		String ID_PETICION = "validarToken";
		HashMap pars = new HashMap();
		pars.put("token", "luisibacache@gmail.com");
	

		try {
			int tipoSalida = TiposSalida.SALIDA_JSON;
			Object salida = RequestImpl.procesarPeticion(pars, ID_PETICION, tipoSalida, TiposEntrada.ENTRADA_JSON, TiposMetodoHttp.POST);

			if (tipoSalida == TiposSalida.SALIDA_JSON) {
				((JSONObject) salida).toString();
			} else if (tipoSalida == TiposSalida.SALIDA_XML) {
				((String) salida).toString();
			} else if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {
				((String) salida).toString();
			}

		} catch (RequestUtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * String salida = null; try { salida = (String) procesarPeticion(pars,
		 * ID_PETICION, TiposSalida.SALIDA_JSON); } catch (RequestUtilException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); return;
		 * }
		 */
	}

	
	public static void validarServer(){
		String ID_PETICION = "validarServer";
		HashMap pars = new HashMap();
		pars.put("id_preventa", "1536");
		pars.put("token", "eb83b8c0c0a0f599c65de19abccad20f");
	

		try {
			int tipoSalida = TiposSalida.SALIDA_JSON;
			Object salida = RequestImpl.procesarPeticion(pars, ID_PETICION, tipoSalida, TiposEntrada.ENTRADA_JSON, TiposMetodoHttp.POST);

			if (tipoSalida == TiposSalida.SALIDA_JSON) {
				((JSONObject) salida).toString();
			} else if (tipoSalida == TiposSalida.SALIDA_XML) {
				((String) salida).toString();
			} else if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {
				((String) salida).toString();
			}
			System.out.println("La salida->"+salida);
		} catch (RequestUtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * String salida = null; try { salida = (String) procesarPeticion(pars,
		 * ID_PETICION, TiposSalida.SALIDA_JSON); } catch (RequestUtilException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); return;
		 * }
		 */
	}
	
	
	public static JSONObject ejecutarCargaSAPManual(JSONObject autenticacion) throws JSONException{
		System.out.println("data->"+autenticacion.getJSONObject("data").getString("token"));
		String ID_PETICION = "ejecutarProcesoCargaSAPManual";
		HashMap pars = new HashMap();
		pars.put("token", autenticacion.getJSONObject("data").getString("token"));
	

		try {
			int tipoSalida = TiposSalida.SALIDA_JSON;
			Object salida = RequestImpl.procesarPeticion(pars, ID_PETICION, tipoSalida, TiposEntrada.ENTRADA_JSON, TiposMetodoHttp.POST);

			if (tipoSalida == TiposSalida.SALIDA_JSON) {
				((JSONObject) salida).toString();
				return ((JSONObject) salida);
			} else if (tipoSalida == TiposSalida.SALIDA_XML) {
				((String) salida).toString();
			} else if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {
				((String) salida).toString();
			}
			System.out.println("La salida->"+salida);
		} catch (RequestUtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * String salida = null; try { salida = (String) procesarPeticion(pars,
		 * ID_PETICION, TiposSalida.SALIDA_JSON); } catch (RequestUtilException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); return;
		 * }
		 */
		return null;
	}
	
	
	
	
	
}
