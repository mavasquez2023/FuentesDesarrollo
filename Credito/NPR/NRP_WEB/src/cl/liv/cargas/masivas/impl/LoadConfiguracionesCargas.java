package cl.liv.cargas.masivas.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import cl.liv.archivos.comun.xml.lector.dto.AtributoDTO;
import cl.liv.archivos.comun.xml.lector.dto.NodoDTO;
import cl.liv.archivos.comun.xml.lector.impl.ProcesarXML;
import cl.liv.cargas.masivas.dto.CargaDTO;
import cl.liv.cargas.masivas.dto.ParametroDTO;
import cl.liv.cargas.masivas.util.TiposEntrada;
import cl.liv.cargas.masivas.util.UtilLogCargas;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.comun.utiles.logs.UtilLogErrores;

public class LoadConfiguracionesCargas {
	
	private static ArrayList cargasConfiguradas = new ArrayList();
	static HashMap properties = new HashMap();
	private static HashMap configuracionesCargadas = new HashMap();
	private static void init(String key){
		configuracionesCargadas.put(key, Boolean.TRUE);
		String rutaArchivo = getPropertiesByKey(key).getString("carga.masiva.path.archivo.configuracion");
		UtilLogCargas.info ("Cargando xml : " + rutaArchivo );
		NodoDTO configuracion = null;
		try {
			configuracion = ProcesarXML.init(  rutaArchivo );
		} catch (ParserConfigurationException e) {
			UtilLogErrores.error(e);
			e.printStackTrace();
		} catch (SAXException e) {
			UtilLogErrores.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			UtilLogErrores.error(e);
			e.printStackTrace();
		}
		if(configuracion != null && configuracion.getNodosHijos() != null && configuracion.getNodosHijos().get(0) != null){
			for (int i=0; i< ((NodoDTO)configuracion.getNodosHijos().get(0)).getNodosHijos().size();i++) {
				NodoDTO c = (NodoDTO)((NodoDTO)configuracion.getNodosHijos().get(0)).getNodosHijos().get(i);
				if(c != null){
					CargaDTO carga = new CargaDTO();
					cargasConfiguradas.add(carga);
					for (int i1=0; i1<c.getAtributos().size(); i1++) {
						AtributoDTO a = (AtributoDTO) c.getAtributos().get(i1);
						if(a!=null){
							if(a.getKey().equalsIgnoreCase("id")){
								carga.setId(key+"."+a.getValue());
							}
							else if(a.getKey().equalsIgnoreCase("action")){
								carga.setAction(a.getValue());
							}
							else if(a.getKey().equalsIgnoreCase("separador")){
								carga.setSeparador(a.getValue());
							}
							else if(a.getKey().equalsIgnoreCase("extension_salida")){
								carga.setExtensionSalida(a.getValue());
							}
							else if(a.getKey().equalsIgnoreCase("on_load")){
								carga.setOnLoad(a.getValue());
							}
							else if(a.getKey().equalsIgnoreCase("hash")){
								carga.setHash(a.getValue());
							}
							else if(a.getKey().equalsIgnoreCase("eliminar_archivo_original")){
								carga.setEliminarArchivoOriginal( Boolean.parseBoolean(a.getValue()));
							}
							else if(a.getKey().equalsIgnoreCase("generar_archivo_resultado")){
								carga.setGenerarArchivoResultado( Boolean.parseBoolean(a.getValue()+""));
							}
							else if(a.getKey().equalsIgnoreCase("action_finish")){
								carga.setActionFinish(a.getValue());
							}
							else if(a.getKey().equalsIgnoreCase("fila_inicial")){
								try{
									carga.setFilaInicial( new Integer(a.getValue().toString()).intValue() );
								}catch(Exception e){
									
								}
							}
							
							
							else if(a.getKey().equalsIgnoreCase("tipo_entrada")){
								//carga.setTipoEntrada(a.getValue());
								if(a.getValue().toString().equalsIgnoreCase("por_separador")){
									carga.setTipoEntrada(TiposEntrada.POR_SEPARADOR);
								}
								if(a.getValue().toString().equalsIgnoreCase("por_indice")){
									carga.setTipoEntrada(TiposEntrada.POR_INDICE);
								}
							}
							else if(a.getKey().equalsIgnoreCase("encoding")){
								carga.setEncoding(a.getValue());
							}
		
							
						}
					}
					if(((NodoDTO)c).getNodosHijos().size() > 0){
						for (int i2=0; i2<((NodoDTO)c.getNodosHijos().get(0)).getNodosHijos().size(); i2++) {
							NodoDTO hijo = (NodoDTO)((NodoDTO)c.getNodosHijos().get(0)).getNodosHijos().get(i2);
							ParametroDTO par = new ParametroDTO();
							for (int k=0; k<hijo.getAtributos().size(); k++) {
								AtributoDTO a = (AtributoDTO)hijo.getAtributos().get(k);
								if(a!=null){
									if(a.getKey().equalsIgnoreCase("key")){
										par.setKey(a.getValue());
									}
									else if(a.getKey().equalsIgnoreCase("posicion")){
										par.setPosicion(Integer.parseInt( a.getValue() ));
									}
	
									else if(a.getKey().equalsIgnoreCase("validacion")){
										par.setValidacion(a.getValue());
									}
	
									else if(a.getKey().equalsIgnoreCase("unique")){
										par.setUnique( Boolean.parseBoolean( a.getValue()  ) );
									}
									else if(a.getKey().equalsIgnoreCase("valor_default_null")){
										par.setValorDefaultNull(  a.getValue()   );
									}
									else if(a.getKey().equalsIgnoreCase("largo")){
										par.setLargo( Integer.parseInt( a.getValue())   );
									}
								}
							}
							
							carga.getParametros().add(par);
						}
					}
				}
			}
		}
	}
	
	public static CargaDTO getDataCarga(String id, String key){
		UtilLogCargas.info ("configuracionesCargadas ? "+ configuracionesCargadas);
		if( configuracionesCargadas.get(id) == null)
			init(key);
		int cont=0;
		if(cargasConfiguradas != null && cargasConfiguradas.size()> 0){
			for (int i=0; i<cargasConfiguradas.size();i++) {
				CargaDTO cargaDTO = (CargaDTO)cargasConfiguradas.get(i);
				if(cargaDTO != null && cargaDTO.getId().equalsIgnoreCase(key+"."+id)){
					if( !UtilesComunes.getMD5("liv.cargas_masivas."+key+"."+id).equals(cargaDTO.getHash()) ){
						cont = 1;
						UtilLogCargas.info (new String("E")+new String("R")+":"+cont);
						return null;
					}
					return cargaDTO;
				}
				else{
					cont = 0;
					UtilLogCargas.info (new String("E")+new String("R")+":"+cont);
				}
			}
		}
		
		return null;
	}
	
	public static ResourceBundle getPropertiesByKey(String key){

		if(properties.get(key)!= null){
			return (ResourceBundle)properties.get(key); 
		}
		else{
			if(key != null && key.equals("planillas")){
				
				if(PropertiesUtil.propertiesPlanillas != null){
					properties.put(key, PropertiesUtil.propertiesPlanillas);
					return (ResourceBundle)properties.get(key);
				}
			}
		}
		return null;
	}
	
	
}
