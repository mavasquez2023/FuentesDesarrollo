package cl.liv.core.request.impl;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import cl.liv.core.request.dto.PeticionDTO;
import cl.liv.core.request.dto.ProcesoDTO;
import cl.liv.core.request.exception.RequestUtilException;
import cl.liv.core.request.tipos.TiposEntrada;
import cl.liv.core.request.tipos.TiposMetodoHttp;
import cl.liv.core.request.tipos.TiposProceso;
import cl.liv.core.request.tipos.TiposSalida;
import cl.liv.persistencia.ibatis.exception.IbatisException;
import cl.liv.persistencia.ibatis.impl.IbatisUtilDAO;
import cl.liv.traductor.data.impl.TraductorJSON;
import cl.liv.traductor.data.impl.TraductorSoloTexto;
import cl.liv.traductor.data.impl.TraductorXML;
import cl.sbs.util.reflection.impl.UtilReflectionImpl;

public class RequestImpl {
	static Logger log = Logger.getLogger(RequestImpl.class);

	public static Object procesarPeticion(Object parameters, String idPeticion, int tipoSalida, int tipoEntrada, int metodoEntradaHTTP)
			throws RequestUtilException {
		parameters = convertirParametros(parameters, tipoEntrada);
		log.debug("parametros: " + parameters);
		
		if(parameters instanceof HashMap){
			
			Iterator it = ((HashMap) parameters).keySet().iterator();
			
			while(it.hasNext()){
				String entry = (String)it.next();
				log.debug("- Parametro: "+ entry);
				log.debug("  obj: "+ ((HashMap)parameters).get(entry));
				log.debug("  class: "+ ((HashMap)parameters).get(entry).getClass());
				
			}
			
			
		}
		
		log.debug(getValueKeyFromParameter(parameters, "id_req"));

		PeticionDTO peticionAux = LoadConfiguracionRequest.getDataPeticion(idPeticion);
		
		final PeticionDTO peticion = PeticionDTO.clone(peticionAux);

		if (peticion != null) {

			log.info("metodos aceptados: "+ peticion.getMetodosAceptados());
			
			if(peticion.getMetodosAceptados()!= null && peticion.getMetodosAceptados().equalsIgnoreCase("post") && metodoEntradaHTTP == TiposMetodoHttp.GET){
				JSONObject json = new JSONObject();
				try {
					json.put("error", "Metodo GET No permitido para la peticion");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
				return json;
			}
			
			log.info("antes de session: ");
			
			if (peticion.getSesion() != null && peticion.getSesion().length() > 0) {
				
				String[] peticionesIniciales = peticion.getSesion().split(";");
				
				log.info("Se debe ejecutar peticiones relacionada antes de ejecutar: "+ peticionesIniciales);

				for (int i=0; i<peticionesIniciales.length; i++) {
					String peticionInicial = peticionesIniciales[i];
					if(peticionInicial != null){
						log.info("Ejecutando peticion inicial : "+ peticionInicial );
						PeticionDTO peticionSesion = LoadConfiguracionRequest.getDataPeticion(peticionInicial);
	
						for (int i1=0;i1<peticionSesion.getProcesos().size(); i1++) {
							ProcesoDTO proceso = (ProcesoDTO)peticionSesion.getProcesos().get(i1);
							if (proceso.getTipo().equals(TiposProceso.REFLECTION)) {
								log.info("a validar sesión" + peticion.getSesion());
								String clase = proceso.getData();
								String metodo = proceso.getDataAdicional();
								Class[] paramTypes = new Class[2];
								paramTypes[0] = Object.class;
								paramTypes[1] = Object.class;
	
								Object[] parametersInput = new Object[2];
								parametersInput[0] = peticion.getResultados();
								parametersInput[1] = parameters;
	
								Boolean valor = (Boolean) UtilReflectionImpl.executeReflection(clase, metodo, paramTypes,
										parametersInput);
								if (valor == null || valor.equals(Boolean.FALSE)) {
									throw new RequestUtilException("S001: Error en el proceso  "+ peticionInicial);
								}
							} else if (proceso.getTipo().equals(TiposProceso.PERSISTENCIA_IBATIS)) {
	
								Object resultado = null;
								String[] dataConfiguracion = proceso.getData().split("\\.");
								IbatisUtilDAO ibatisDao = new IbatisUtilDAO(dataConfiguracion[0]);
								
								if(peticion.isUsarTransaccion() && peticion.getObjetoTransaccion() == null){
									peticion.setObjetoTransaccion(ibatisDao.getSqlMapTransaction());
								}
								
								if (dataConfiguracion.length > 1 && dataConfiguracion[1] != null) {
	
									HashMap parametrosInput = (HashMap) parameters;
									if (dataConfiguracion[1].equalsIgnoreCase("getObject")) {
										try {
											resultado = ibatisDao.getObject(proceso.getDataAdicional(), parameters, peticion.getObjetoTransaccion());
											if (!(resultado != null && ((Boolean) resultado).equals( Boolean.TRUE)   )) {
												throw new RequestUtilException("S001: Error en el proceso  "+ peticionInicial);
											}
										} catch (IbatisException e) {
											// TODO Auto-generated catch block
											log.error(e);
										}
	
									}
									else if (dataConfiguracion[1].equalsIgnoreCase("update")) {
										try {
											resultado = ibatisDao.update(proceso.getDataAdicional(), parameters, peticion.getObjetoTransaccion());
											if (!(resultado != null &&  ((Boolean) resultado).equals( Boolean.TRUE)) ) {
												throw new RequestUtilException("S001: Error en el proceso  "+ peticionInicial);
											}
										} catch (IbatisException e) {
											// TODO Auto-generated catch block
											log.error(e);
										}
	
									}
	
								}
							}
							
						}

					}

				}
				
			}
			
			log.debug("procesando proceso...["+peticion.getProcesos().size()+"]");
			for (int i=0; i<peticion.getProcesos().size(); i++) {
				log.debug("ejecutando proceso...");
				final ProcesoDTO proceso = (ProcesoDTO) peticion.getProcesos().get(i);
				boolean condicion = true;
					if (proceso.getCondicion() != null) {
					String[] condiciones = proceso.getCondicion().split(";");
					log.info("condiciones encontradas:["+condiciones.length+"] "+ proceso.getCondicion());
					_condiciones: 						
					for (int i1=0; i1<condiciones.length; i1++) {
						String cond = condiciones[i1];
						log.info("condicion: "+ cond);
						if (!validarCondicion(cond, peticion)) {
							condicion = false;
							break _condiciones;
						}
						else{
							log.info("condicion ["+cond+"] = true");
						}
					}
				}
				Object salida = null;
				if (!condicion) {
					log.debug(
							"Proceso " + proceso.getIdProceso() + ", no cumple condicion " + proceso.getCondicion());
				} else {

					log.debug("a ejecutar proceso...");
					if(!proceso.isAsincrono()){
						log.debug("ejecutar proceso...");
						salida = ejecutarProceso(peticion, proceso, parameters);
						log.debug("proceso ejecutado");
					}
					else{
						
						final Object parametersAux = parameters;
						new Thread(new Runnable() {
							
							public void run() {
								// TODO Auto-generated method stub
								ejecutarProceso(peticion, proceso, parametersAux);
							}
						}).start();
					}
					peticion.getResultados().put(proceso.getIdProceso(), salida);
					if (proceso.getValidarResultado() != null && proceso.getValidarResultado().length() > 0) {
						String[] validaciones = proceso.getValidarResultado().split(";");
						for (int i2=0; i2<validaciones.length; i2++) {
							String validacion = validaciones[i2];
							if (!validarCondicion(validacion, peticion)) {
								if (tipoSalida == TiposSalida.SALIDA_JSON) {
									JSONObject obj = new JSONObject();
									try {
										obj.put("error", validacion.split(":")[3]);
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										log.error(e);
									}
									salida = obj;
								} else if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {
									salida = validacion.split(":")[3];
								} else if (tipoSalida == TiposSalida.SALIDA_XML) {
									salida = "<root><error>" + validacion.split(":")[3] + "</error></root>";
								}
								proceso.setSalida(true);

							}
						}
					}
					
					Object objetoSalida = registrarSalida(tipoSalida, salida, proceso);
					if (objetoSalida != null && proceso.isSalida())
						return objetoSalida;

					log.debug("salida del proceso : " + salida);
				}
			}
			if(peticion.isUsarTransaccion() && peticion.getObjetoTransaccion() != null){
				IbatisUtilDAO.executeTransaction(peticion.getObjetoTransaccion(), peticion.isEstadoTransaccion());
			}

			log.debug("Salida del proceso: " + peticion.getResultados());
		} else {
			log.debug("Configuracion de peticion [" + idPeticion + "] no encontrada.");
		}
		return null;
	}
	
	public static Object ejecutarProceso(PeticionDTO peticion, ProcesoDTO proceso, Object parameters){
		log.debug("Ejecutando proceso...:"+ proceso.getIdProceso());
		Object salida = "";
		if (proceso.getTipo().equals(TiposProceso.REFLECTION)) {
			String clase = proceso.getData();
			String metodo = proceso.getDataAdicional();

			Class[] paramTypes = new Class[2];
			paramTypes[0] = Object.class;
			paramTypes[1] = Object.class;

			Object[] parametersInput = new Object[2];
			parametersInput[0] = peticion.getResultados();
			parametersInput[1] = parameters;
			log.debug("Ejecutando por reflection: ["+clase+", "+metodo+"]");
			salida = UtilReflectionImpl.executeReflection(clase, metodo, paramTypes, parametersInput);
			log.debug("Metodo ejecutado...");
			peticion.getResultados().put(proceso.getIdProceso(), salida);
		} else if (proceso.getTipo().equals(TiposProceso.PERSISTENCIA_IBATIS)) {

			Object resultado = null;
			String[] dataConfiguracion = proceso.getData().split("\\.");
			IbatisUtilDAO ibatisDao = new IbatisUtilDAO(dataConfiguracion[0]);

			if(peticion.isUsarTransaccion() && peticion.getObjetoTransaccion() == null){
				peticion.setObjetoTransaccion(ibatisDao.getSqlMapTransaction());
			}
			
			
			if (dataConfiguracion.length > 1 && dataConfiguracion[1] != null) {

				HashMap parametrosInput = (HashMap) parameters;
				if (dataConfiguracion[1].equalsIgnoreCase("getObject")) {
					try {
						resultado = ibatisDao.getObject(proceso.getDataAdicional(), parameters, peticion.getObjetoTransaccion());
						
						Object res = UtilReflectionImpl.getInstance(proceso.getDtoPrincipal(), resultado);

					} catch (IbatisException e) {
						// TODO Auto-generated catch block
						log.error(e);
					}

				} else if (dataConfiguracion[1].equalsIgnoreCase("select")) {
					try {
						resultado = ibatisDao.select(proceso.getDataAdicional(), parametrosInput, peticion.getObjetoTransaccion());

						ArrayList items = (ArrayList) resultado;
						int contador = 0;
						for (int i=0; i< items.size(); i++) {
							Object item = items.get(i);
							if (contador == 0) {
								Object res = UtilReflectionImpl.getInstance(proceso.getDtoPrincipal(),
										item);
								if (res == null) {
									resultado = null;
								}
								contador++;
							}
						}
					
					} catch (IbatisException e) {
						// TODO Auto-generated catch block
						log.error(e);
					}

				} else if (dataConfiguracion[1].equalsIgnoreCase("update")) {
					try {
						resultado = ibatisDao.update(proceso.getDataAdicional(), parametrosInput, peticion.getObjetoTransaccion());

						
					} catch (IbatisException e) {
						// TODO Auto-generated catch block
						log.error(e);
						resultado =  Boolean.FALSE;
					}
					if(peticion.isUsarTransaccion() &&  ((Boolean) resultado).equals( Boolean.FALSE) ){
						peticion.setEstadoTransaccion(false);
					}
					
					

				} else if (dataConfiguracion[1].equalsIgnoreCase("insert")) {
					try {
						resultado = ibatisDao.insert(proceso.getDataAdicional(), parametrosInput, peticion.getObjetoTransaccion());

					} catch (IbatisException e) {
						// TODO Auto-generated catch block
						log.error(e);
						resultado =  Boolean.FALSE;
					}
					if(peticion.isUsarTransaccion() && (Boolean)resultado ==  Boolean.FALSE){
						peticion.setEstadoTransaccion(false);
					}
					
				}
				else if (dataConfiguracion[1].equalsIgnoreCase("delete")) {
					try {
						resultado = ibatisDao.delete(proceso.getDataAdicional(), parametrosInput, peticion.getObjetoTransaccion());

					} catch (IbatisException e) {
						// TODO Auto-generated catch block
						log.error(e);
						resultado =  Boolean.FALSE;
					}
					if(peticion.isUsarTransaccion() &&  ((Boolean) resultado).equals( Boolean.FALSE)  ){
						peticion.setEstadoTransaccion(false);
					}
					
				}
			}
			salida = resultado;
		}
		else if (proceso.getTipo().equals(TiposProceso.GET_DATA_SESSION)) {
			salida = peticion.getResultados().get(proceso.getData());
		}
		
		return salida;

	}

	public static Object registrarSalida(int tipoSalida, Object resultado, ProcesoDTO proceso) {
		if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {

			TraductorSoloTexto traductor = new TraductorSoloTexto();
			String hash = traductor.generarHash(resultado);
			if (proceso.isSalida()) {
				if (hash.substring(hash.length() - 2).indexOf(TraductorSoloTexto.DELIMITADOR_DATA) == 0)
					hash = hash.substring(0, hash.length() - 2);
				log.debug("\n\n DATA : " + hash);
				return hash;
			}
		} else if (tipoSalida == TiposSalida.SALIDA_JSON) {

			TraductorJSON traductor = new TraductorJSON();
			JSONObject json = traductor.generarJSON(resultado, proceso.getKey());

			log.debug("\n\n JSON : " + json);
			if (proceso.isSalida()) {
				return json;
			}
		} else if (tipoSalida == TiposSalida.SALIDA_XML) {

			TraductorXML traductor = new TraductorXML();
			String xml = traductor.generarXML(resultado, proceso.getKey());

			log.debug("\n\n XML : " + xml);
			if (proceso.isSalida()) {
				return xml;
			}
		}

		return null;
	}

	public static boolean validarCondicion(String condicion, PeticionDTO peticion) {
		String[] partes = condicion.split(":");
		try {
			log.info("validando condicion " + condicion + " , " + peticion.getResultados().get(partes[0]));
			if (partes.length > 2 && partes[1].equalsIgnoreCase("equals_boolean") && partes[2].length() > 0) {
				if (!new Boolean(partes[2]).equals( (Boolean) (peticion.getResultados().get(partes[0])))) {
					log.debug("no cumple condicion: " + condicion);
					return false;
				}
			}
			if (partes.length > 2 && partes[1].equalsIgnoreCase("not_equals_boolean") && partes[2].length() > 0) {
				if ( !new Boolean(partes[2]).equals( (Boolean) (peticion.getResultados().get(partes[0])))) {
					log.debug("no cumple condicion: " + condicion);
					return false;
				}
			}

			if (partes.length > 1 && partes[1].equalsIgnoreCase("is_null")) {
				if ((peticion.getResultados().get(partes[0])) != null) {
					log.debug("no cumple condicion: " + condicion);
					return false;
				}
			}
			if (partes.length > 1 && partes[1].equalsIgnoreCase("not_null")) {
				if ((peticion.getResultados().get(partes[0])) == null) {
					log.debug("no cumple condicion: " + condicion);
					return false;
				}
			}
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}

	public static Object getValueKeyFromParameter(Object parameters, String key) {
		Object retorno = null;
		try {
			if (parameters instanceof HashMap) {
				return ((HashMap) parameters).get(key);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return retorno;
	}

	public static HashMap convertirParametros(Object parameters, int tipoEntrada) {
		log.info("\n\n************************** PARAMETROS *********************");

		HashMap pars = new HashMap();
		if (parameters instanceof HttpServletRequest) {

			HttpServletRequest parsReq = (HttpServletRequest) parameters;

			Enumeration attrs = parsReq.getParameterNames();
			while (attrs.hasMoreElements()) {
				String attr = (String) attrs.nextElement();
				pars.put(attr, ((HttpServletRequest) parameters).getParameter(attr));
			}

			if (tipoEntrada == TiposEntrada.ENTRADA_JSON) {
				StringBuffer jb = new StringBuffer();
				String line = null;
				try {
					BufferedReader reader = ((HttpServletRequest) parameters).getReader();
					while ((line = reader.readLine()) != null)
						jb.append(line);
				} catch (Exception e) {
					log.error(e);
				}

				try {
					pars.put("jsonInput", new JSONObject(jb.toString()));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
			} else if (tipoEntrada == TiposEntrada.ENTRADA_STRING) {
				StringBuffer jb = new StringBuffer();
				String line = null;
				try {
					BufferedReader reader = ((HttpServletRequest) parameters).getReader();
					while ((line = reader.readLine()) != null)
						jb.append(line);
				} catch (Exception e) {
					log.error(e);
				}

				pars.put("stringInput", jb.toString());
			}

		} else if (parameters instanceof HashMap) {
			pars = (HashMap) parameters;
		}

		log.info("************************** PARAMETROS *********************\n\n");
		return pars;
	}

	public static Object getDataToCast(String idPeticion, String hash) {
		PeticionDTO peticion = LoadConfiguracionRequest.getDataPeticion(idPeticion);
		if (peticion != null) {
			TraductorSoloTexto traductor = new TraductorSoloTexto();
			traductor.getObjectFromHash(hash, peticion.getDtoPrincipal());
			return traductor.retornoObj;
		}
		return null;
	}
	
	
	
	
}
