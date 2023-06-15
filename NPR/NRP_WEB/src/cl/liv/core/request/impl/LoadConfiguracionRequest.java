package cl.liv.core.request.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import cl.liv.archivos.comun.xml.lector.dto.AtributoDTO;
import cl.liv.archivos.comun.xml.lector.dto.NodoDTO;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.core.request.dto.PeticionDTO;
import cl.liv.core.request.dto.ProcesoDTO;
import cl.sbs.util.reflection.impl.UtilReflectionImpl;

public class LoadConfiguracionRequest {

	static Logger log = Logger.getLogger(LoadConfiguracionRequest.class);
	
	
	private static ArrayList peticionesConfiguradas = new ArrayList();

	private static boolean configuracionesCargadas = false;

	private static void init() {
		peticionesConfiguradas = new ArrayList();
		configuracionesCargadas = true;
		String parametro = PropertiesUtil.propertiesCoreRequest.getString("request.parametro.configuracion");
		log.info("parametro xml : " + parametro);
		String claseImplementacion = PropertiesUtil.propertiesCoreRequest.getString("request.clase.implementacion.configuracion");
		String metodoImplementacion = PropertiesUtil.propertiesCoreRequest.getString("request.metodo.implementacion.configuracion");
		
		NodoDTO configuracion = null;
		try {
			
			log.debug("Buscando configuracion en ["+claseImplementacion+"."+metodoImplementacion+"]:["+parametro+"] ");
			
			Class[] paramTypes = new Class[1];
			paramTypes[0] = String.class;
			
			Object[] parametros = new Object[1];
			parametros[0] = parametro;
			
			configuracion = (NodoDTO) UtilReflectionImpl.executeReflection(claseImplementacion, metodoImplementacion, paramTypes, parametros);
			
			//configuracion = ProcesarXML.init(rutaArchivo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		if (configuracion != null && configuracion.getNodosHijos() != null
				&& configuracion.getNodosHijos().get(0) != null) {

			NodoDTO peticiones = (NodoDTO)configuracion.getNodosHijos().get(0);
			for (int i=0; i< peticiones.getNodosHijos().size(); i++) {
				NodoDTO peticion = (NodoDTO)peticiones.getNodosHijos().get(i);
				PeticionDTO p = new PeticionDTO();
				for (int i1=0; i1<peticion.getAtributos().size(); i1++) {
					AtributoDTO atributo = (AtributoDTO)peticion.getAtributos().get(i1);
					if (atributo.getKey().equalsIgnoreCase("id")) {
						p.setIdPeticion(atributo.getValue());
					} else if (atributo.getKey().equalsIgnoreCase("metodos_aceptados")) {
						p.setMetodosAceptados(atributo.getValue());
					} else if (atributo.getKey().equalsIgnoreCase("formato_salida")) {
						p.setFormatoSalida(atributo.getValue());
					} else if (atributo.getKey().equalsIgnoreCase("array_list")) {
						p.setArrayList(Boolean.parseBoolean(atributo.getValue() + ""));
					} else if (atributo.getKey().equalsIgnoreCase("dto_principal")) {
						p.setDtoPrincipal(atributo.getValue());
					} else if (atributo.getKey().equalsIgnoreCase("sesion")) {
						p.setSesion(atributo.getValue());
					}else if (atributo.getKey().equalsIgnoreCase("usar_transaccion")) {
						p.setUsarTransaccion(Boolean.parseBoolean(atributo.getValue()));
					}
				}

				NodoDTO procesos = (NodoDTO)peticion.getNodosHijos().get(0);
				for (int i2=0; i2<procesos.getNodosHijos().size(); i2++) {
					NodoDTO proceso = (NodoDTO)procesos.getNodosHijos().get(i2);
					ProcesoDTO p1 = new ProcesoDTO();
					p.getProcesos().add(p1);
					for (int i3=0; i3<proceso.getAtributos().size(); i3++) {
						AtributoDTO atributo = (AtributoDTO)proceso.getAtributos().get(i3);
						if (atributo.getKey().equalsIgnoreCase("id")) {
							p1.setIdProceso(atributo.getValue());
						} else if (atributo.getKey().equalsIgnoreCase("type")) {
							p1.setTipo(atributo.getValue());
						} else if (atributo.getKey().equalsIgnoreCase("data")) {
							p1.setData(atributo.getValue());
						} else if (atributo.getKey().equalsIgnoreCase("data_adicional")) {
							p1.setDataAdicional(atributo.getValue());
						} else if (atributo.getKey().equalsIgnoreCase("array_list")) {
							p1.setArrayList(Boolean.parseBoolean(atributo.getValue() + ""));
						} else if (atributo.getKey().equalsIgnoreCase("dto_principal")) {
							p1.setDtoPrincipal(atributo.getValue());
						} else if (atributo.getKey().equalsIgnoreCase("salida")) {
							p1.setSalida(Boolean.parseBoolean(atributo.getValue() + ""));
						} else if (atributo.getKey().equalsIgnoreCase("key")) {
							p1.setKey(atributo.getValue());
						} else if (atributo.getKey().equalsIgnoreCase("condicion")) {
							p1.setCondicion(atributo.getValue());
						} else if (atributo.getKey().equalsIgnoreCase("validar_resultado")) {
							p1.setValidarResultado(atributo.getValue());
						}else if (atributo.getKey().equalsIgnoreCase("asincrono")) {
							p1.setAsincrono(Boolean.parseBoolean(atributo.getValue() + ""));
						}

					}
				}
				peticionesConfiguradas.add(p);
			}
		}
	}

	public static PeticionDTO getDataPeticion(String idPeticion) {

		log.info("configuracionesCargadas ? " + configuracionesCargadas);
		if (!configuracionesCargadas)
			init();
		if (peticionesConfiguradas != null && peticionesConfiguradas.size() > 0) {
			for (int i=0;i< peticionesConfiguradas.size(); i++) {
				PeticionDTO peticion = (PeticionDTO)peticionesConfiguradas.get(i);
				if (peticion != null && peticion.getIdPeticion().equalsIgnoreCase(idPeticion))
					return peticion;
			}
		}

		return null;
	}

}
