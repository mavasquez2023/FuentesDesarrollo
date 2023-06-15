package cl.jfactory.planillas.service.helper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.jfactory.planillas.service.util.EstadosWorkflow;
import cl.jfactory.planillas.service.util.ITerminadorHebra;
import cl.jfactory.planillas.service.util.LectorConfiguracionTags;
import cl.jfactory.planillas.service.util.PoolHebras;
import cl.jfactory.planillas.service.util.UtilLogProcesos;
import cl.jfactory.planillas.service.util.UtilLogThread;
import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.jfactory.planillas.service.util.Utiles;
import cl.jfactory.planillas.service.util.UtilesWorkflow;
import cl.liv.archivos.comun.ArchivosUtiles;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.comun.utiles.logs.UtilLogErrores;
import cl.liv.comun.utiles.logs.UtilLogGeneracion;
import cl.liv.export.comun.util.SessionUtil;
import cl.liv.export.txt.util.PropertiesTXTUtil;
import cl.liv.export.txt.util.xml.LectorTXTConfig;
import cl.liv.export.txt.util.xml.UtilXML;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class ConfiguradorHelper {

	

	public static Object sessionDisponibilizacion = null;
	public static Object parametrosDisponibilizacion = null;
	
	public HashMap obtenerConfiguracionFormato(Object session, Object parameters) throws JSONException {
		HashMap salida = new HashMap();
				
		//Disponible
		((HashMap)parameters).put("uri_reporte", ((HashMap)parameters).get("formato_completo"));
		((HashMap)parameters).put("id_atributo", "mapeo_header");
		ArrayList arrayDisponiblesHeader =  obtenerColumnasReporte(session, parameters);
		((HashMap)parameters).put("id_atributo", "mapeo_footer");
		ArrayList arrayDisponiblesFooter =  obtenerColumnasReporte(session, parameters);
		((HashMap)parameters).put("id_atributo", "mapeo_detalle");
		ArrayList arrayDisponiblesDetalle =  obtenerColumnasReporte(session, parameters);
		
		HashMap headerDisponible = new HashMap();
		HashMap columnasDisponible = new HashMap();
		HashMap footerDisponible = new HashMap();
		
		
		//Asociados
		Document docAsociados = LectorTXTConfig.getXMLConfiguracion(((HashMap)parameters).get("uri_configuracion").toString());
		((HashMap)parameters).put("uri_reporte", ((HashMap)parameters).get("uri_configuracion"));
		((HashMap)parameters).put("id_atributo", "mapeo_header");
		ArrayList arrayAsociadosHeader =  obtenerColumnasReporte(session, parameters);
		((HashMap)parameters).put("id_atributo", "mapeo_footer");
		ArrayList arrayAsociadosFooter =  obtenerColumnasReporte(session, parameters);
		((HashMap)parameters).put("id_atributo", "mapeo_detalle");
		ArrayList arrayAsociadosDetalle =  obtenerColumnasReporte(session, parameters);
		
		//Seteo el disponible, dependiendo de lo asociado
		headerDisponible.put("id", "header");
		headerDisponible.put("data", arrayDisponiblesHeader);
		headerDisponible.put("ejecutar", buscarAtributoTag(docAsociados, "query", "query_header", "ejecutar"));
		headerDisponible.put("configurable", buscarAtributoTagUnico(docAsociados, "doc", "configurable"));
		
		
		
		columnasDisponible.put("id", "detalle");
		columnasDisponible.put("data", arrayDisponiblesDetalle);
		columnasDisponible.put("ejecutar", buscarAtributoTag(docAsociados, "query", "query_detalle", "ejecutar"));
		
		footerDisponible.put("id", "footer");
		footerDisponible.put("data", arrayDisponiblesFooter);
		footerDisponible.put("ejecutar", buscarAtributoTag(docAsociados, "query", "query_footer", "ejecutar"));
		
		
		ArrayList arrayDisponibles  = new ArrayList();
		arrayDisponibles.add(headerDisponible);
		arrayDisponibles.add(columnasDisponible);
		arrayDisponibles.add(footerDisponible);

		
		for(int i=0; i< arrayDisponiblesHeader.size(); i++){
			HashMap jsonColumnaDisponible = (HashMap)arrayDisponiblesHeader.get(i);
			jsonColumnaDisponible.put("registrada", Boolean.TRUE);
			jsonColumnaDisponible.put("configurada", Boolean.FALSE);
			for(int j=0; j<arrayAsociadosHeader.size(); j++){
				HashMap jsonConfigurado = (HashMap) arrayAsociadosHeader.get(j);		
				if( jsonConfigurado.get("nombre").equals(jsonColumnaDisponible.get("nombre")) ){
					jsonColumnaDisponible.put("configurada", Boolean.TRUE);
					jsonColumnaDisponible.put("posicion", jsonConfigurado.get("posicion"));
					jsonColumnaDisponible.put("largo", jsonConfigurado.get("largo"));
					jsonColumnaDisponible.put("relleno", jsonConfigurado.get("relleno"));
					jsonColumnaDisponible.put("alineamiento", jsonConfigurado.get("alineamiento"));
					jsonColumnaDisponible.put("no_incluir", jsonConfigurado.get("no_incluir"));
					jsonColumnaDisponible.put("metodo", jsonConfigurado.get("metodo"));
					jsonColumnaDisponible.put("data", jsonConfigurado.get("data"));
					j= arrayAsociadosHeader.size();
				}
			}
		}
		

		for(int i=0; i< arrayDisponiblesDetalle.size(); i++){
			HashMap jsonColumnaDisponible = (HashMap)arrayDisponiblesDetalle.get(i);
			jsonColumnaDisponible.put("registrada", Boolean.TRUE);
			jsonColumnaDisponible.put("configurada", Boolean.FALSE);
			for(int j=0; j<arrayAsociadosDetalle.size(); j++){
				HashMap jsonConfigurado = (HashMap) arrayAsociadosDetalle.get(j);		
				if( jsonConfigurado.get("nombre").equals(jsonColumnaDisponible.get("nombre")) ){
					jsonColumnaDisponible.put("configurada", Boolean.TRUE);
					jsonColumnaDisponible.put("posicion", jsonConfigurado.get("posicion"));
					jsonColumnaDisponible.put("largo", jsonConfigurado.get("largo"));
					jsonColumnaDisponible.put("relleno", jsonConfigurado.get("relleno"));
					jsonColumnaDisponible.put("alineamiento", jsonConfigurado.get("alineamiento"));
					jsonColumnaDisponible.put("no_incluir", jsonConfigurado.get("no_incluir"));
					jsonColumnaDisponible.put("metodo", jsonConfigurado.get("metodo"));
					jsonColumnaDisponible.put("data", jsonConfigurado.get("data"));
					j=arrayAsociadosDetalle.size();
				}
			}
		}
		

		for(int i=0; i< arrayDisponiblesFooter.size(); i++){
			HashMap jsonColumnaDisponible = (HashMap)arrayDisponiblesFooter.get(i);
			jsonColumnaDisponible.put("registrada", Boolean.TRUE);
			jsonColumnaDisponible.put("configurada", Boolean.FALSE);
			for(int j=0; j<arrayAsociadosFooter.size(); j++){
				HashMap jsonConfigurado = (HashMap) arrayAsociadosFooter.get(j);		
				if( jsonConfigurado.get("nombre").equals(jsonColumnaDisponible.get("nombre")) ){
					jsonColumnaDisponible.put("configurada", Boolean.TRUE);
					jsonColumnaDisponible.put("posicion", jsonConfigurado.get("posicion"));
					jsonColumnaDisponible.put("largo", jsonConfigurado.get("largo"));
					jsonColumnaDisponible.put("relleno", jsonConfigurado.get("relleno"));
					jsonColumnaDisponible.put("alineamiento", jsonConfigurado.get("alineamiento"));
					jsonColumnaDisponible.put("no_incluir", jsonConfigurado.get("no_incluir"));
					jsonColumnaDisponible.put("metodo", jsonConfigurado.get("metodo"));
					jsonColumnaDisponible.put("data", jsonConfigurado.get("data"));
					j= arrayAsociadosFooter.size();
				}
			}
		}
		
		
		
		//Validar columnas data_constante
		
		for(int i=0; i< arrayAsociadosDetalle.size(); i++){
			HashMap jsonColumnaAsociada = (HashMap)arrayAsociadosDetalle.get(i);
			if(jsonColumnaAsociada != null && jsonColumnaAsociada.get("nombre").toString().startsWith("data_constante")){
				jsonColumnaAsociada.put("configurada", Boolean.TRUE);
				arrayDisponiblesDetalle.add (jsonColumnaAsociada);
			}
			else if(jsonColumnaAsociada != null && jsonColumnaAsociada.get("nombre").toString().startsWith("sys.")){
				jsonColumnaAsociada.put("configurada", Boolean.TRUE);
				arrayDisponiblesDetalle.add (jsonColumnaAsociada);
			}
		}
		

		for(int i=0; i< arrayAsociadosHeader.size(); i++){
			HashMap jsonColumnaAsociada = (HashMap)arrayAsociadosHeader.get(i);
			if(jsonColumnaAsociada != null && jsonColumnaAsociada.get("nombre").toString().startsWith("data_constante")){
				jsonColumnaAsociada.put("configurada", Boolean.TRUE);
				arrayDisponiblesHeader.add (jsonColumnaAsociada);
			}
			else if(jsonColumnaAsociada != null && jsonColumnaAsociada.get("nombre").toString().startsWith("sys.")){
				jsonColumnaAsociada.put("configurada", Boolean.TRUE);
				arrayDisponiblesHeader.add (jsonColumnaAsociada);
			}
		}
		
		for(int i=0; i< arrayAsociadosFooter.size(); i++){
			HashMap jsonColumnaAsociada = (HashMap)arrayAsociadosFooter.get(i);
			if(jsonColumnaAsociada != null && jsonColumnaAsociada.get("nombre").toString().startsWith("data_constante")){
				jsonColumnaAsociada.put("configurada", Boolean.TRUE);
				arrayDisponiblesFooter.add (jsonColumnaAsociada);
			}
			else if(jsonColumnaAsociada != null && jsonColumnaAsociada.get("nombre").toString().startsWith("sys.")){
				jsonColumnaAsociada.put("configurada", Boolean.TRUE);
				arrayDisponiblesFooter.add (jsonColumnaAsociada);
			}
		}
		salida.put("tags", obtenerTagsDisponibles());
		salida.put("columnas", arrayDisponibles);

		return salida;
	}
	
	
	public static HashMap obtenerTagsDisponibles() {
		HashMap salida = new HashMap();
				
		String tagsHeader 	= "";
		String tagsDetalle 	= "todos:*;fecha:FECHA_COLOCACION,FECHA_OTORGAMIENTO;nombres:APELLIDO_MATERNO,APELLIDO_PATERNO";
		String tagsFooter 	= "";
		salida.put("header", LectorConfiguracionTags.getConfiguracionPorSeccion("header"));
		salida.put("detalle", LectorConfiguracionTags.getConfiguracionPorSeccion("detalle"));
		salida.put("footer", LectorConfiguracionTags.getConfiguracionPorSeccion("footer"));

		return salida;
	}
	private String buscarAtributoTag(Node nodo, String tag, String id, String atributoBuscado){
		if(nodo != null){
			if(nodo.getNodeName().toLowerCase().equals(tag.toLowerCase())){
				if(nodo.hasAttributes()){
					boolean encontreTag = false;
					for(int j=0; j<nodo.getAttributes().getLength(); j++){
						Node subNodo = nodo.getAttributes().item(j);
						if(subNodo.getNodeName().equals("id") && subNodo.getNodeValue().equals(id)){
							encontreTag = true;
							j=nodo.getAttributes().getLength();
						}
					}
					
					if(encontreTag){
						for(int j=0; j<nodo.getAttributes().getLength(); j++){
							Node subNodo = nodo.getAttributes().item(j);
							if(subNodo.getNodeName().equals(atributoBuscado)){
								return subNodo.getNodeValue();
							}
						}
					}
				}
			}
			else{
				if(nodo.hasChildNodes()){
					for(int i=0; i< nodo.getChildNodes().getLength(); i++){
						String resultado = buscarAtributoTag(nodo.getChildNodes().item(i), tag, id, atributoBuscado);
						if(resultado != null){
							return resultado;
						}
					}
				}
			}
		}
		return null;
	}
	
	private String buscarAtributoTagUnico(Node nodo, String tag, String atributoBuscado){
		if(nodo != null){
			if(nodo.getNodeName().toLowerCase().equals(tag.toLowerCase())){
				if(nodo.hasAttributes()){
					for(int j=0; j<nodo.getAttributes().getLength(); j++){
						Node subNodo = nodo.getAttributes().item(j);
						if(subNodo.getNodeName().equals(atributoBuscado)){
							return subNodo.getNodeValue();
						}
					}
				
				}
			}
			else{
				if(nodo.hasChildNodes()){
					for(int i=0; i< nodo.getChildNodes().getLength(); i++){
						String resultado = buscarAtributoTagUnico(nodo.getChildNodes().item(i), tag, atributoBuscado);
						if(resultado != null){
							return resultado;
						}
					}
				}
			}
		}
		return null;
	}
	
	public ArrayList obtenerColumnasReporte(Object session, Object parameters) throws JSONException{
		String idAtributo = ((HashMap)parameters).get("id_atributo").toString();
		ArrayList columnasArray = new ArrayList();
		if(((HashMap)parameters).get("uri_reporte") != null){
			if((HashMap)SessionUtil.txts.get( ((HashMap)parameters).get("uri_reporte") ) == null){
				LectorTXTConfig.getDataXML(((HashMap)parameters).get("uri_reporte").toString());
			}
			HashMap configuracion = (HashMap)SessionUtil.txts.get( ((HashMap)parameters).get("uri_reporte") );
			
			if(configuracion != null){
				if((HashMap) configuracion.get("mapeos") != null && ((HashMap) configuracion.get("mapeos")).get(idAtributo) != null){
					ArrayList columnas = (ArrayList)((HashMap)((HashMap) configuracion.get("mapeos")).get(idAtributo)).get("columnas");
					int contador =0;
					for(int i=0; i< columnas.size(); i++){
						HashMap columna = (HashMap)columnas.get(i);
						if(columna.get("no_incluir") == null || (columna.get("no_incluir")!= null && columna.get("no_incluir").toString().trim().equalsIgnoreCase("false"))) {
							contador++;
						}
						else {
							contador++;
						}
						HashMap hash = new HashMap();
						hash.put("nombre", columna.get("nombre"));
						hash.put("header", columna.get("header"));
						hash.put("alineamiento", columna.get("alineamiento"));
						hash.put("relleno", columna.get("relleno"));
						hash.put("largo", columna.get("largo"));
						hash.put("posicion", new Integer((contador )));
						hash.put("data_ej", columna.get("data_ej"));
						hash.put("valor_default", columna.get("valor_default"));
						hash.put("no_incluir", columna.get("no_incluir"));
						hash.put("metodo", columna.get("metodo"));
						hash.put("data", columna.get("data"));
						columnasArray.add(hash);
					}
				}
				
			}
			UtilLogWorkflow.debug(configuracion.toString());
		}
		return columnasArray;
	}
	
	public HashMap guardarCambiosNomina(Object session, Object parameters) throws IOException  {
		UtilLogWorkflow.debug("en el metodo... guardar cambios nomina...\n\n\n");
		
		HashMap salida = new HashMap();
		UtilLogWorkflow.debug("data ->"+ ((HashMap) parameters).get("data_cfg") );
		
		JSONObject json;
		
		try {
			json = new JSONObject(((HashMap) parameters).get("data_cfg").toString());

			UtilLogWorkflow.debug("json: "+ json);
			JSONArray secciones = json.getJSONArray("secciones");
			
			Document doc = LectorTXTConfig.getXMLConfiguracion(json.getString("formato"));
			
			for(int i=0; i<secciones.length(); i++){
				JSONObject jsonSeccion = secciones.getJSONObject(i);
				if(jsonSeccion.get("habilitado").equals("habilitado")){
					UtilXML.reemplazarAtributo(doc, doc.getChildNodes().item(0), "query", "id", "query_"+jsonSeccion.getString("seccion"), "ejecutar", "true");
				}
				else{
					UtilXML.reemplazarAtributo(doc, doc.getChildNodes().item(0), "query", "id", "query_"+jsonSeccion.getString("seccion"), "ejecutar", "false");
				}
				UtilXML.reemplazarContenido(doc, doc.getChildNodes().item(0),"id", "mapeo_"+jsonSeccion.getString("seccion"), "columna", jsonSeccion.getJSONArray("data"));
			}
			
			String contenidoArchivo = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+ UtilXML.generarXML(doc);
			
			String contenido = new String(contenidoArchivo.getBytes(), "UTF-8");
			
			
			String ruta = LectorTXTConfig.getXMLURLConfiguracion(json.getString("formato"));
			
			PrintWriter pw = ManejoArchivoTXT.getOpenedFileToWrite(ruta);
			ManejoArchivoTXT.putLineFromFileOpened(pw, contenido);
			ManejoArchivoTXT.closeFileToWrite(pw);
			
			SessionUtil.txts.put(json.getString("formato"), null);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	public HashMap limpiarSesionArchivos(Object session, Object parameters) throws IOException  {
		HashMap salida = new HashMap();
		SessionUtil.txts.clear();
		return salida;
	}
	
	


	public HashMap buscarEntidades(Object session, Object parameters) throws IOException  {
		UtilLogWorkflow.debug("en el metodo... guardar cambios nomina...\n\n\n");
		
		HashMap salida = new HashMap();
		
		MiHashMap pars = new MiHashMap();
		pars.put("codigo_entidad", ((HashMap) parameters).get("codigo_entidad").toString());
		pars.put("tipo_entidad", ((HashMap) parameters).get("tipo_entidad").toString());
		pars.put("like", "'%"+((HashMap) parameters).get("codigo_entidad").toString()+ "%'" );
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			ArrayList entidades = (ArrayList) sqlMap.queryForList("carga_SAP.buscarEntidades", pars);
			
			salida.put("entidades", entidades);
			UtilLogWorkflow.debug("entidades: "+ entidades);		
		} catch (Exception e) {
			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			
			e.printStackTrace();
			
		}

		

		return salida;
	}
	

	public HashMap validarNombre(Object session, Object parameters) throws IOException  {
		UtilLogWorkflow.debug("en el metodo... guardar cambios nomina...\n\n\n");
		String sql = "";
		HashMap salida = new HashMap();
		MiHashMap pars = new MiHashMap();
		pars.put("nombre", ((HashMap) parameters).get("nombre").toString());
		if(((HashMap) parameters).get("tipo").toString().equals("formato")){
			sql = "validarNombreFormato";
		}
		else if(((HashMap) parameters).get("tipo").toString().equals("nomina")){
			sql = "validarNombreNomina";
		} 
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			UtilLogWorkflow.debug(sql );
			UtilLogWorkflow.debug(pars.toString() );
			Boolean existe = (Boolean) sqlMap.queryForObject("carga_SAP."+sql, pars);
			UtilLogWorkflow.debug("resultado: "+ existe);
			if(existe != null){
				salida.put("valido", Boolean.FALSE );
			}
			else{
				salida.put("valido", Boolean.TRUE );
			}
		} catch (Exception e) {
			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			e.printStackTrace();
			
		}

		

		return salida;
	}
	

	public HashMap buscarEmpresasAsociadas(Object session, Object parameters) throws IOException  {
		UtilLogWorkflow.debug("en el metodo... guardar cambios nomina...\n\n\n");
		
		HashMap salida = new HashMap();
		MiHashMap pars = new MiHashMap();
		pars.put("codigo_entidad", ((HashMap) parameters).get("codigo_entidad").toString());
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			ArrayList entidades = (ArrayList) sqlMap.queryForList("carga_SAP.buscarEmpresasAsociadas", pars);
			
			salida.put("entidades", entidades);
			UtilLogWorkflow.debug("entidades: "+ entidades);		
		} catch (Exception e) {
			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			e.printStackTrace();
			
		}

		

		return salida;
	}
	

	public HashMap buscarEmpresaHolding(Object session, Object parameters) throws IOException  {
		UtilLogWorkflow.debug("en el metodo... guardar cambios nomina...\n\n\n");
		
		HashMap salida = new HashMap();
		MiHashMap pars = new MiHashMap();
		pars.put("codigo", ((HashMap) parameters).get("codigo_a_buscar").toString());
		
		
		if(!validarCodigoEntidad(((HashMap) parameters).get("codigo_a_buscar").toString())){
			salida.put("error", "C&oacute;digo de entidad ['"+((HashMap) parameters).get("codigo_a_buscar").toString()+"'] no vÃ¡lido ");
			return salida;
		}
		
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			ArrayList formatos = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerFormatos", pars);
			UtilLogWorkflow.debug("formatos: "+ formatos.size());
			if(formatos.size() == 2){
				UtilLogWorkflow.debug(" solo los default ");
				for(int i=formatos.size() - 1;i>=0; i--){
					UtilLogWorkflow.debug("->"+((MiHashMap)formatos.get(i)).get("nombre_nomina").toString().toLowerCase());
					if( ((MiHashMap)formatos.get(i)).get("nombre_nomina").toString().toLowerCase().contains("trabajadores") && GeneradorNominasHelper.esPensionado(((HashMap) parameters), ((HashMap) parameters).get("codigo_a_buscar").toString())){
						formatos.remove(i);
					} 
					if( ((MiHashMap)formatos.get(i)).get("nombre_nomina").toString().toLowerCase().contains("pensionados") && !GeneradorNominasHelper.esPensionado(((HashMap) parameters), ((HashMap) parameters).get("codigo_a_buscar").toString())){
						formatos.remove(i);
					} 
							
				}
			}
			if(formatos.size()>1){
				for(int i=formatos.size() - 1;i>0; i--){
					if( ((MiHashMap)formatos.get(i)).get("rut_empresa") == null &&
							((MiHashMap)formatos.get(i)).get("codigo_holding") == null ){
						formatos.remove(i);
					} 
							
				}
			}
		
			salida.put("formatos", formatos);
			
			ArrayList configuraciones = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerConfiguracionesNominas", pars);
			UtilLogWorkflow.debug("formatos: "+ formatos);
			if(configuraciones.size() == 2){
				UtilLogWorkflow.debug(" solo los default ");
				for(int i=configuraciones.size() - 1;i>=0; i--){
					if( ((MiHashMap)configuraciones.get(i)).get("formato_nomina").toString().toLowerCase().contains("trabajadores") && GeneradorNominasHelper.esPensionado(((HashMap) parameters), ((HashMap) parameters).get("codigo_a_buscar").toString())){
						configuraciones.remove(i);
					} 
					if( ((MiHashMap)configuraciones.get(i)).get("formato_nomina").toString().toLowerCase().contains("pensionados") && !GeneradorNominasHelper.esPensionado(((HashMap) parameters), ((HashMap) parameters).get("codigo_a_buscar").toString())){
						configuraciones.remove(i);
					} 
							
				}
			}
			if(configuraciones.size()>1){
				for(int i=configuraciones.size() - 1;i>0; i--){
					if( ((MiHashMap)configuraciones.get(i)).get("rut_empresa") == null &&
							((MiHashMap)configuraciones.get(i)).get("codigo_holding") == null ){
						configuraciones.remove(i);
					} 
							
				}
			}
			
			salida.put("configuraciones", configuraciones);
			
			ArrayList envio = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerConfiguracionesEnvio", pars);
			
			salida.put("envio", envio);
			if(((HashMap) parameters).get("codigo_a_buscar").toString().toLowerCase().startsWith("h")){
				salida.put("holding", "" );
			}
			else{
				salida.put("holding", obtenerHoldingConfigurado(((HashMap) parameters).get("codigo_a_buscar").toString()) );
			}
			UtilLogWorkflow.debug("salida: "+ salida);
		
		} catch (Exception e) {
			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			e.printStackTrace();
			
		}

		

		return salida;
	}

	
	public HashMap agregarFormato(Object session, Object parameters) throws IOException, JSONException  {
		UtilLogWorkflow.debug("en el metodo... agregar formato...\n\n\n");
		
		HashMap salida = new HashMap();
		salida.put("estado", Boolean.FALSE);
		MiHashMap pars = new MiHashMap();
		
		UtilLogWorkflow.debug("json:"+((HashMap) parameters).get("input_json"));
		JSONObject  json = new JSONObject(((HashMap) parameters).get("input_json").toString());
		UtilLogWorkflow.debug( "formato -> "+ json.getString("nombre_nomina"));
		String formatoACopiar = null;
		
		if(json.has("formato_copia")){
			formatoACopiar = json.getString("formato_copia");
		}
		
		
		if(!generarArchivoConfiguracion(json.getString("nombre_nomina").toString(), formatoACopiar)){
			salida.put("estado", Boolean.FALSE);
			return salida;
		}
			
		UtilLogWorkflow.debug("insertando...");
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			pars.put("rut_empresa", json.get("rut_empresa"));
			pars.put("codigo_holding", json.get("codigo_holding"));
			pars.put("nombre_nomina", json.get("nombre_nomina"));
			pars.put("descripcion", json.get("descripcion"));
			UtilLogWorkflow.debug("pars: "+ pars);
			sqlMap.insert("carga_SAP.agregarFormato", pars);
			salida.put("estado", Boolean.TRUE);
			
		} catch (Exception e) {
			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			e.printStackTrace();
			
		}

		return salida;
	}
	
	public HashMap eliminaFormato(Object session, Object parameters) throws IOException, JSONException  {
		UtilLogWorkflow.debug("en el metodo... guardar cambios nomina...\n\n\n");
		
		HashMap salida = new HashMap();
		
		
		salida.put("estado", Boolean.FALSE);
		MiHashMap pars = new MiHashMap();
		JSONObject  json = new JSONObject(((HashMap) parameters).get("input_json").toString());
		pars.put("codigo", json.getString("codigo_entidad"));
		pars.put("nombre_nomina", json.getString("nombre_nomina"));
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			int result = sqlMap.delete("carga_SAP.eliminarFormato", pars);
			UtilLogWorkflow.debug("eliminando formato ["+pars+"] -> "+result);
			if(result > 0 && !eliminarArchivoConfiguracion(json.getString("nombre_nomina") )){
				
				
				salida.put("estado", Boolean.FALSE);
				return salida;
			}
			
			result = sqlMap.delete("carga_SAP.eliminarConfiguracionesAsociadasAlFormato", pars);

			UtilLogWorkflow.debug("eliminando configs ["+pars+"] -> "+result);
			

			SessionUtil.txts.put(json.getString("nombre_nomina"), null);
			salida.put("estado", Boolean.TRUE);
			
		} catch (Exception e) {
			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			e.printStackTrace();
			
		}

		return salida;
	}

	public static boolean eliminarCMDFilesPorEntidad(String entidad) {
		UtilLogWorkflow.debug("en el metodo... guardar cambios nomina...\n\n\n");
		
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		HashMap salida = new HashMap();
		
		salida.put("estado", Boolean.FALSE);
		MiHashMap pars = new MiHashMap();
		pars.put("codigo", entidad );
		pars.put("nombre_nomina", periodo );
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			int result = sqlMap.delete("carga_SAP.eliminarFormato", pars);
			if(result > 0){
				return true;
			}
		} catch (Exception e) {
			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			e.printStackTrace();
			
		}

		return false;
	}
	
	public HashMap agregarNomina(Object session, Object parameters) throws IOException, JSONException  {
		UtilLogWorkflow.debug("en el metodo... guardar cambios nomina...\n\n\n");
		
		HashMap salida = new HashMap();
		salida.put("estado", Boolean.FALSE);
		MiHashMap pars = new MiHashMap();
		JSONObject  json = new JSONObject(((HashMap) parameters).get("input_json").toString());
		
		 
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			pars.put("rut_empresa", json.get("rut_empresa"));
			pars.put("codigo_holding", json.get("codigo_holding"));
			pars.put("tipo", json.get("tipo"));
			pars.put("data_adicional", json.get("data_adicional"));
			pars.put("formato_salida", json.get("formato_salida"));
			pars.put("nombre_salida", json.get("nombre_salida"));
			pars.put("formato_nomina", json.get("formato_nomina"));
			pars.put("nombre_config", json.get("nombre_config"));
			if(json.has("par_generacion") && json.getString("par_generacion").trim().length()>0)
				pars.put("par_generacion", PropertiesUtil.workflowProperties.getString( json.get("par_generacion").toString() ));
			
			sqlMap.insert("carga_SAP.agregarConfiguracion", pars);
			salida.put("estado", Boolean.TRUE);
			
		} catch (Exception e) {
			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			e.printStackTrace();
			
		}

		return salida;
	}
	
	public HashMap eliminaNomina(Object session, Object parameters) throws IOException, JSONException  {
		UtilLogWorkflow.debug("en el metodo... guardar cambios nomina...\n\n\n");
		
		HashMap salida = new HashMap();
		salida.put("estado", Boolean.FALSE);
		MiHashMap pars = new MiHashMap();
		
		JSONObject  json = new JSONObject(((HashMap) parameters).get("input_json").toString());
		pars.put("codigo_entidad", json.getString("codigo_entidad"));
		pars.put("nombre_config", json.getString("nombre_config"));
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			sqlMap.delete("carga_SAP.eliminarConfiguracion", pars);
			salida.put("estado", Boolean.TRUE);
			
		} catch (Exception e) {
			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			e.printStackTrace();
			
		}

		return salida;
	}
	

	
	
	public HashMap agregarEnvio(Object session, Object parameters) throws IOException, JSONException  {
		UtilLogWorkflow.debug("en el metodo... guardar cambios nomina...\n\n\n");
		
		HashMap salida = new HashMap();
		salida.put("estado", Boolean.FALSE);
		MiHashMap pars = new MiHashMap();
		JSONObject  json = new JSONObject(((HashMap) parameters).get("input_json").toString());
		UtilLogWorkflow.debug("json: "+ json);
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			pars.put("rut_empresa", json.get("rut_empresa"));
			pars.put("codigo_holding", json.get("codigo_holding"));
			pars.put("tipo", json.get("tipo"));
			pars.put("data_adicional", json.get("data_adicional"));
			
			sqlMap.delete("carga_SAP.agregarEnvio", pars);
			salida.put("estado", Boolean.TRUE);
			
		} catch (Exception e) {
			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			e.printStackTrace();
			
		}
		UtilLogWorkflow.debug("salida:" +salida);
		return salida;
	}
	
	public HashMap eliminaEnvio(Object session, Object parameters) throws IOException  {
		UtilLogWorkflow.debug("en el metodo... guardar cambios nomina...\n\n\n");
		
		HashMap salida = new HashMap();
		salida.put("estado", Boolean.FALSE);
		MiHashMap pars = new MiHashMap();
		pars.put("codigo", ((HashMap) parameters).get("codigo_entidad").toString());
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			sqlMap.insert("carga_SAP.eliminarEnvio", pars);
			salida.put("estado", Boolean.TRUE);
			
		} catch (Exception e) {
			UtilLogErrores.error(e);
			e.printStackTrace();
			
		}

		return salida;
	}
	
	
	
	public HashMap obtenerVariablesSistema(Object session, Object parameters) throws IOException  {
		
		HashMap salida = new HashMap();
		String variables = PropertiesUtil.propertiesVariables.getString("config.variables.sistema");
		ArrayList data = new ArrayList();
		String partes[] = variables.split(";");
		
		for(int i=0; i< partes.length; i++){
			if(partes[i].length()>0){
				UtilLogWorkflow.debug("partes["+i+"]:"+ partes[i]);
				HashMap item = new HashMap();
				item.put("texto", partes[i].split(":")[0]);
				item.put("valor", partes[i].split(":")[1] + ":"+ UtilesComunes.getVariable(partes[i].split(":")[1]));
				data.add(item);
			}
		}
		salida.put("data", data);
		return salida;
	}
	
	public HashMap obtenerFormatosDisponibles(Object session, Object parameters) throws IOException  {
		
		HashMap salida = new HashMap();
		String variables = PropertiesUtil.propertiesVariables.getString("config.variables.sistema");
		ArrayList data = new ArrayList();
		String partes[] = variables.split(";");
		
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			MiHashMap pars = new MiHashMap();
			ArrayList formatos = (ArrayList) sqlMap.queryForList("carga_SAP.formatosDisponibles", pars);
			
			if(formatos != null && formatos.size()>0){
				for(int i=0; i< formatos.size(); i++){

					String fileConf = PropertiesTXTUtil.getProperty("export.path.resources.txt")+"/txts/"+((HashMap)formatos.get(i)).get("valor")+"/conf.xml";
					if(new File(fileConf).exists()){
						HashMap item = new HashMap();
						item.put("texto", ((HashMap)formatos.get(i)).get("texto"));
						item.put("valor", ((HashMap)formatos.get(i)).get("valor"));
						data.add(item);
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		salida.put("data", data);
		return salida;
	}
	public HashMap obtenerColumnasBDDisponibles(Object session, Object parameters) throws IOException  {
		
		HashMap salida = new HashMap();
		String variables = PropertiesUtil.propertiesVariables.getString("config.campos.bd.disponibles");
		ArrayList data = new ArrayList();
		String partes[] = variables.split(";");
		
		for(int i=0; i< partes.length; i++){
			if(partes[i].length()>0){
				UtilLogWorkflow.debug("partes["+i+"]:"+ partes[i]);
				HashMap item = new HashMap();
				item.put("texto", partes[i].split(":")[0]);
				item.put("valor", partes[i].split(":")[1] );
				data.add(item);
			}
		}
		salida.put("data", data);
		return salida;
	}
	
	
	
	public boolean generarArchivoConfiguracion(String nombreFormato, String formato){
		String formatoACopiar = "formato_estandar";
		if(formato != null){
			formatoACopiar = formato;
		}
		Document docAsociados = LectorTXTConfig.getXMLConfiguracion(formatoACopiar);
		UtilLogWorkflow.debug("generando archivo -> "+ nombreFormato);
		try {
			String key = LectorTXTConfig.getKey(nombreFormato);
			UtilLogWorkflow.debug("key: "+ key);
			//Document doc, Node nodo, String nombreTag, String atributoABuscar, String textoComparacion,String atributoAReemplazar, String nuevoValor
			UtilXML.reemplazarAtributo(docAsociados, docAsociados.getChildNodes().item(0), "doc", "nombre", formatoACopiar, "key", key);
			UtilXML.reemplazarAtributo(docAsociados, docAsociados.getChildNodes().item(0), "doc", "nombre", formatoACopiar, "nombre", nombreFormato);
			
			String contenido = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+ UtilXML.generarXML(docAsociados);
				
			return LectorTXTConfig.createXMLreporte(nombreFormato, contenido);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public boolean eliminarArchivoConfiguracion(String nombreFormato){
		return LectorTXTConfig.deleteReporte(nombreFormato);
	}
	
	
	
	
	public HashMap obtenerNominasPorPeriodoEntidad(Object session, Object parameters) throws JSONException {
		HashMap salida = new HashMap();
		ArrayList nominas = new ArrayList();
		String periodo = ((HashMap)parameters).get("periodo").toString();
		String codigoEntidad = ((HashMap)parameters).get("codigo_entidad").toString();
		
		String path = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/"+codigoEntidad+"/";
		SimpleDateFormat format = new SimpleDateFormat("yyyyy/MM/dd hh:mm:ss");
		File directorio = new File(path);
		if(directorio != null){
			File[] archivo = directorio.listFiles();
			if(archivo != null ){
				for(int i=0; i< archivo.length; i++){
					HashMap nomina = new HashMap();
					nomina.put("archivo", archivo[i].getName());
					nomina.put("length", archivo[i].length()+"");
					nomina.put("fecha",   format.format(new Date(archivo[i].lastModified())));
					nominas.add( nomina );
				}
			}
		}
		salida.put("data", nominas);
		return salida;
	}

	
	public HashMap generarNominas(final Object session, final Object parameters) throws IOException, JSONException, SQLException  {

		//UtilesComunes.variablesEstaticas.put("sys.YearMonth", "201901");
		UtilLogProcesos.debug(" Iniciando Generación de nóminas ["+((HashMap)session).get("data_session")+"]...");
		UtilLogGeneracion.debug(" Iniciando Generación de nóminas ["+((HashMap)session).get("data_session")+"] ["+UtilesComunes.reemplazarVariables("sys.YearMonth")+"]...");
		PoolHebras.eliminarHebras(null);
		
		final String key = UtilesComunes.reemplazarVariables("sys.YearMonth");
		
		final String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+key+"/";

		UtilLogWorkflow.debug("->"+pathCompleto+"blocked.txt");
		final String rutaFileBlocked = pathCompleto+"blocked.txt";
		

		HashMap salida = new HashMap();
		
		if(ArchivosUtiles.existeArchivoPorRutaAsBoolean(rutaFileBlocked)){
			salida.put("status", Boolean.FALSE);
			salida.put("descripcion", "Proceso Bloqueado. No es posible generar n&oacute;minas en este momento.");
			return salida;
		}
			
			
			
			
		if(!ArchivosUtiles.existeArchivoPorRutaAsBoolean(rutaFileBlocked)){
			JSONObject dataFile = new JSONObject();
			try {
				dataFile.put("fecha_creacion", WorkFlowHelper.formatoFecha.format(new Date()));
				dataFile.put("autor", ((HashMap)parameters).get("email"));
				dataFile.put("status_blocked", "3");
				dataFile.put("descripcion", "Comienza el proceso de generaci&oacute;n...");
				dataFile.put("reintentar_en", ConstantesUtiles.TIEMPO_ESTANDAR_REFRESCO_BLOQUEO);
				UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, dataFile);
			}
			catch(Exception e){
				
			}
		}
			
		salida.put("status", Boolean.TRUE);
		salida.put("descripcion", "Proceso de Generaci&oacute;n iniciado.");
		

		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
					
					HashMap salida = new HashMap();
					salida.put("estado", Boolean.FALSE);
					String codigoEntidad = ((HashMap)parameters).get("codigo_entidad").toString();
					if(codigoEntidad.length()==0){
						UtilLogGeneracion.debug(" <inicializando>");
						GeneradorNominasHelper.inicializar();
						UtilLogGeneracion.debug(" </inicializando>");
						UtilLogGeneracion.debug(" <inicializandoEstadisticas>");
						EstadisticasHelper.inicializar(true);
						UtilLogGeneracion.debug(" </inicializandoEstadisticas>");
						UtilLogGeneracion.debug(" <initGeneracion>");
						GeneradorNominasHelper.limpiarArchivosGeneradosPorEmpresa();
						GeneradorNominasHelper.ejecutarProcesoGeneracion();

						HashMap resultados = new HashMap();
						resultados.put("status", Boolean.TRUE);
						((HashMap)session).put("resultados", resultados);
						UtilesWorkflow.crearArchivoConDataJson(session, parameters, pathCompleto+"/3.txt");
						
					}
					else{
						GeneradorNominasHelper.inicializar();
						MiHashMap parametersQuery = new MiHashMap();
						parametersQuery.put("ENTIDAD", codigoEntidad);
						parametersQuery.put("PERIODO", key);
						SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
						try {
							sqlMap.delete("carga_SAP.eliminarRegistroTablaFiles", parametersQuery);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						eliminarCMDFilesPorEntidad(codigoEntidad);
						GeneradorNominasHelper.generarTodas("codigo_entidad:"+codigoEntidad);
						new PoolHebras(1, "cl.jfactory.planillas.service.helper.GeneradorNominasHelper", "ejecutarHiloGeneracion", new Class[0], new Object[0], new ITerminadorHebra() {
							
							public void notificarCierre(int tipo) {
								// TODO Auto-generated method stub

								UtilLogWorkflow.debug("notificar el cierre Generacion");
								UtilLogThread.debug("notificar cierre Generacion");
							}
						});
						cerrarProcesoBloqueado("3", "Fin proceso generaci&oacute;n");
						if(codigoEntidad != null && codigoEntidad.length()>0){
							AlertasHelper.procesarAlertaCambioEstado(EstadosWorkflow.ANTES_GENERACION_NOMINA, EstadosWorkflow.DESPUES_GENERACION_NOMINA);
						}
						UtilLogProcesos.debug(" Terminando Generación de nóminas ["+((HashMap)session).get("data_session")+"]...");
					}
					
					
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		
		return salida;
	}
	
	public static void callURLResultadosGeneracion(String proceso){
		String url= ResourceBundle.getBundle("etc/workflow_configuraciones").getString("config.url.termino."+proceso);
		UtilLogProcesos.info("Invocando URL de Resultado NRP para generar las estad?sticas, url: " + url);
		Utiles.callURL(url);
	}
	
	public static HashMap<String, Boolean> nominasEnviadas = null;
	
	public HashMap enviarNominas(final Object session, final Object parameters) throws IOException, JSONException  {
		UtilLogProcesos.debug(" Iniciando Envio de nóminas ["+((HashMap)session).get("data_session")+"]...");
		PoolHebras.eliminarHebras(null);
		final String codigoEntidad = ((HashMap)parameters).get("codigo_entidad").toString();
		
		nominasEnviadas = new MiHashMap();
		
		String key = UtilesComunes.reemplazarVariables("sys.YearMonth");
		final String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+key+"/";
		
		if(codigoEntidad != null && codigoEntidad.length()==0){
			UtilLogWorkflow.debug("->"+pathCompleto+"blocked.txt");
			final String rutaFileBlocked = pathCompleto+"blocked.txt";
			if(!ArchivosUtiles.existeArchivoPorRutaAsBoolean(rutaFileBlocked)){
				JSONObject dataFile = new JSONObject();
				try {
					dataFile.put("fecha_creacion", WorkFlowHelper.formatoFecha.format(new Date()));
					dataFile.put("autor", ((HashMap)parameters).get("email"));
					dataFile.put("status_blocked", "4");
					dataFile.put("descripcion", "Comienza el proceso de Envio");
					dataFile.put("reintentar_en", ConstantesUtiles.TIEMPO_ESTANDAR_REFRESCO_BLOQUEO);
					UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, dataFile);
				}
				catch(Exception e){
					
				}
			}
		}
		
		HashMap salida = new HashMap();
		
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				try {
					
					UtilLogWorkflow.debug("esperando para ejecutar");
					Thread.sleep(5000);
					
					boolean generarComprobante = Boolean.parseBoolean( PropertiesUtil.workflowProperties.getString("config.llamar.generador.comprobantes"));
					UtilLogWorkflow.debug("generarComprobante->"+ generarComprobante);
					if( generarComprobante &&  ((HashMap)parameters).get("codigo_entidad") != null  &&  ((HashMap)parameters).get("codigo_entidad").toString().length() == 0){
						UtilLogProcesos.debug(" Iniciando Generación de comprobantes ["+((HashMap)session).get("data_session")+"]...");
						try {
							new WorkFlowHelper().generarComprobantes(session, parameters);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						UtilLogProcesos.debug(" Terminando Generación de comprobantes ["+((HashMap)session).get("data_session")+"]...");
					}
					UtilLogWorkflow.debug("despues de generar...");
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				
				if(codigoEntidad.length()==0){
					UtilLogProcesos.debug(" Comenzando proceso de envio masivo");
					sessionDisponibilizacion = session;
					parametrosDisponibilizacion = parameters;
					GeneradorNominasHelper.ejecutarProcesoEnvio();
				}
				else{
					UtilLogProcesos.debug(" Comenzando proceso de envio unitario");
					GeneradorNominasHelper.enviarTodas("codigo_entidad:"+codigoEntidad);
				}
			}
		}).start();
				
		return salida;
	}
	
	

	public HashMap eliminarNominas(Object session, Object parameters) throws IOException, JSONException  {
		UtilLogWorkflow.debug("en el metodo... guardar cambios nomina...\n\n\n");
		
		HashMap salida = new HashMap();
		salida.put("estado", Boolean.FALSE);
		String codigoEntidad = ((HashMap)parameters).get("codigo_entidad").toString();
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		if(codigoEntidad.length()==0){
			ArchivosUtiles.eliminarDirectorioRecursivo(PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/");
		}
		else{
			ArchivosUtiles.eliminarDirectorioRecursivo(PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/"+codigoEntidad+"/");
		}
		
		return salida;
	}
	
	


	public boolean validarCodigoEntidad(String codigoEntidad) {
		boolean retorno = false;
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			UtilLogWorkflow.debug("validando: "+ codigoEntidad);
			MiHashMap pars = new MiHashMap();
			pars.put("codigo_entidad", codigoEntidad);
			Object data = null;
			if(codigoEntidad.toLowerCase().startsWith("h")){
				data =  sqlMap.queryForObject("carga_SAP.validarCodigoEntidadHolding", pars);
			}
			else{
				codigoEntidad = codigoEntidad.trim();
				data =  sqlMap.queryForObject("carga_SAP.validarCodigoEntidadEmpresaPublica", pars);
				
				if(data == null) data =  sqlMap.queryForObject("carga_SAP.validarCodigoEntidadEmpresaPrivada", pars);
				
				if(data == null) data =  sqlMap.queryForObject("carga_SAP.validarCodigoEntidadPagadora", pars);
				
			}
			UtilLogWorkflow.debug("resultado: "+data);
			if(data != null)
				retorno = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return retorno;
	}
	
	
	public static void cerrarProcesoBloqueado(String status, String descripcion){
	String key = UtilesComunes.reemplazarVariables("sys.YearMonth");
		
		final String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+key+"/";
		final String rutaFileBlocked = pathCompleto+"blocked.txt";
		
		JSONObject dataFile = new JSONObject();
		try {
			dataFile.put("fecha_creacion", WorkFlowHelper.formatoFecha.format(new Date()));
			dataFile.put("autor", "");
			dataFile.put("status_blocked", status);
			dataFile.put("descripcion", descripcion);
			dataFile.put("reintentar_en", new Integer(-1));
			UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, dataFile);
		}
		catch(Exception e){
			
		}
		
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(5000);
					ArchivosUtiles.eliminarArchivoPorRuta(rutaFileBlocked);
				} catch (InterruptedException e) {
					UtilLogErrores.error(e);
					new AlertasHelper().procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
					e.printStackTrace();
				}
			}
		}).start();;
		
	}
	
	
	public static String obtenerHoldingConfigurado(String codigoEntidad){
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			UtilLogWorkflow.debug("validando: "+ codigoEntidad);
			MiHashMap pars = new MiHashMap();
			pars.put("codigo_entidad", codigoEntidad);
			String data = (String) sqlMap.queryForObject("carga_SAP.validarHoldingConNominasEspeciales", pars);
			UtilLogWorkflow.debug("resultado: "+data);
			return data;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
