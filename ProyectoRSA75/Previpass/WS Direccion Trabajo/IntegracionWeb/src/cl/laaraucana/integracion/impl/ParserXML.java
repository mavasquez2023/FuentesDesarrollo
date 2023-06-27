package cl.laaraucana.integracion.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import cl.laaraucana.integracion.util.Constantes;
import cl.laaraucana.integracion.util.Util;
import cl.laaraucana.integracion.util.UtilXML;
import cl.laaraucana.integracion.vo.DatoEntradaVO;
import cl.laaraucana.integracion.vo.CotizacionVO;
import cl.laaraucana.integracion.dao.*;





public class ParserXML {
	private static Logger log = Logger.getLogger(ParserXML.class);
	public static boolean flag=true;
	private static ArrayList listaLog= new ArrayList();
	
	public static String parsearDocumento(String xml)
    {
		log.info("XML entrada= " + xml);
		listaLog.clear();
		flag=true;
		Calendar ahora1 = Calendar.getInstance();
		long tiempo1 = ahora1.getTimeInMillis();
		
		// se eliminar los saltos de línea
		xml = xml.replaceAll("\n", "");
		
		DocumentBuilder builder = null;
		Document tmpX= null;
		
		String respuestaAut[]= new String[2];
		String respuestaDirTrabajo[]= new String[2];
		String ambiente = "Producción";
		String respuesta ="";

			try {
				//si se obtuvo xml vacío se genera respuesta inmediata
				if(xml.trim().equals("")){
					log.warn("XML de entrada vacío");
                	String salida= generarCabeceraXml() +  generaXMLError() + generarFooterXml();
					log.warn("Respuesta error= " + salida);
					return salida;
				}
				builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				tmpX=builder.parse(new ByteArrayInputStream(xml.getBytes()));
				int k=0;
				Node nodo = tmpX.getFirstChild();
				String nombre = "";
				String password="";
				String respuestaPeticiones="";
				
				
				respuesta = generarCabeceraXml();
				
				
				// Nodo 0 siempre debe ser el de autenticacion 
				//NodeList aut = nodo.getChildNodes().item(0).getChildNodes();
				log.info("Número de peticiones en consulta:" + nodo.getChildNodes().getLength());
			
				for (k=0;k < nodo.getChildNodes().getLength();k++)
				{
					
					NodeList nodos = nodo.getChildNodes().item(k).getChildNodes();
					
					
					// validamos el nodo 0 que contiene la informacion de autenticacion
					
					String tipo = nodo.getChildNodes().item(k).getAttributes().getNamedItem("tipo").getNodeValue();
					
					log.debug("Tipo petición: "+tipo);
					
					if(k==0)
					{
						
                      //se rescatan los valores y atributos de <peticionservicio tipo="AUT">
						
						// paramAut1 debe tener como valor "usuario"
						String paramAut1 = nodos.item(0).getAttributes().getNamedItem("nombre").getNodeValue();
						
						// paramAut2 debe tener como valor "password"
						String paramAut2 = nodos.item(1).getAttributes().getNamedItem("nombre").getNodeValue();
						
						log.debug("paraaut 1 nombre: "+paramAut1);
						log.debug("paraaut 2 nombre: "+paramAut2);
						
						nombre = nodos.item(0).getAttributes().getNamedItem("valor").getNodeValue();
						password = nodos.item(1).getAttributes().getNamedItem("valor").getNodeValue();
						
						log.debug("paraaut 1 valor: "+nombre);
						log.debug("paraaut 2 valor: "+password);
						if(nombre.equals("70016160-9") || password.equals("12345")){
							ambiente="Testing";
						}
						 
						if ( ! Util.validaAtributosAut(paramAut1, paramAut2)){
						
							log.warn("Error en valida atributos aut"); 
							String salida= generarCabeceraXml() +  generaXMLError() + generarFooterXml();
							log.info("Respuesta error= " + salida);
							return salida;
						
						}
						
						respuestaAut = Util.validaUsuario(nombre, password, tipo);
						
						
						// si se obtuvo error se genera respuesta inmediata
						if(respuestaAut[0].equals("0"))
						{	
							log.warn("error en valida valores atributos aut"); 
							respuesta = respuesta + UtilXML.generarTag2("control","codigo=\"9010\"") + respuestaAut[1]+ generarFooterXml();
							log.info("Respuesta error= " + respuesta);
							return respuesta;
						}
						log.info("Validación AUT OK");
					}else{
						
						NodeList dirTrabajo = nodo.getChildNodes().item(k).getChildNodes();
						
						// se rescatan los valores y atributos de <peticionservicio tipo="SUBCONTR">
						
		                //	 paramDir1 debe tener como valor "rut_empleador"
						String paramDir1="";
						try {
							paramDir1 = dirTrabajo.item(0).getAttributes().getNamedItem("nombre").getNodeValue();
						} catch (Exception e) {
							log.error("Falta parámetro rut_empresa dentro de petición");
							listaLog.add("Falta parámetro rut_empresa dentro de petición");
						}
						
						// paramDir2 debe tener como valor "rut_trabajador"
						String paramDir2="";
						try {
							paramDir2 = dirTrabajo.item(1).getAttributes().getNamedItem("nombre").getNodeValue();
						} catch (Exception e) {
							log.error("Falta parámetro rut_trabajador dentro de petición");
							listaLog.add("Falta parámetro rut_trabajador dentro de petición");
						}
						
		               //	paramDir3 debe tener como valor "periodo"
						String paramDir3="";
						try {
							paramDir3= dirTrabajo.item(2).getAttributes().getNamedItem("nombre").getNodeValue();
						} catch (Exception e) {
							log.error("Falta parámetro periodo dentro de petición");
							listaLog.add("Falta parámetro periodo dentro de petición");
						}
						
						log.debug("param_dt 1 nombre: "+paramDir1);
						log.debug("param_dt 2 nombre: "+paramDir2);
						log.debug("param_dt 3 nombre: "+paramDir3);
						
		                if ( ! Util.validaAtributosDirTrabajo(paramDir1, paramDir2,paramDir3)){
						
		                	log.warn("error en valida atributos dir trabajo");
		                	String salida= generarCabeceraXml() +  generaXMLError() + generarFooterXml();
							log.info("Respuesta error= " + salida);
							return salida;
		                }
		                
		                
		                // si pasa la validacion Ok se obtienen los valores
		                
		                String rutEmpleador = dirTrabajo.item(0).getAttributes().getNamedItem("valor").getNodeValue();
						String rutTrabajador = dirTrabajo.item(1).getAttributes().getNamedItem("valor").getNodeValue();
						String periodo = dirTrabajo.item(2).getAttributes().getNamedItem("valor").getNodeValue();
						
						log.debug("param_dt 1 valor: "+rutEmpleador);
						log.debug("param_dt 2 valor: "+rutTrabajador);
						log.debug("param_dt 3 valor: "+periodo);
						
						DatoEntradaVO data = new DatoEntradaVO(nombre,password,rutEmpleador,rutTrabajador,periodo);
						
						

						respuestaDirTrabajo = Util.validaDirTrabajo(data.getRutEmpleador(),data.getRutTrabajador(), 
								 tipo, data.getMes(), data.getAgno());
											
						
						// esto indica que parametro subContr esta OK (= 1)
						
						/*
						if(respuestaDirTrabajo[0].equals("1")){
							
							
						
							respuestaPeticiones = respuestaPeticiones +  UtilXML.generarTag2("control","codigo=\"9000\"") + respuestaAut[1] + generarXMLCotizaciones(data) + generarFooterXml();
						}else
						 return	respuestaPeticiones = respuestaPeticiones + UtilXML.generarTag2("control","codigo=\"9010\"") + respuestaAut[1] + respuestaDirTrabajo[1] +  generarFooterXml();*/
						
						
						if(respuestaDirTrabajo[0].equals("1")){
							log.info("Validación SUBCONTR OK");
							
							data.setRutEmpl(Util.agregarEspaciosIzquierda(data.getRutEmpleador().split("-")[0],9));
							data.setRutTrab(Util.agregarEspaciosIzquierda(data.getRutTrabajador().split("-")[0],9));
							
							respuestaPeticiones = respuestaPeticiones +  generarXMLCotizaciones(data) ;
							log.debug("Respuesta petición:" +  respuestaPeticiones);
						}else{
						    flag = false;
						    log.warn("Validación SUBCONTR con Error");
							respuestaPeticiones = respuestaPeticiones +  respuestaDirTrabajo[1] ;
							log.info("Respuesta petición:" +  respuestaPeticiones);
						}
		
					}// cierre else
						
		
					
				}// cierre for.
				
				
				
			if(flag){
			   respuesta = respuesta + UtilXML.generarTag2("control","codigo=\"9000\"")+ respuestaAut[1] +  respuestaPeticiones + generarFooterXml();
			   log.info("Todas las peticiones se ejecutaron sin error.");
			}else{	
				 respuesta = respuesta + UtilXML.generarTag2("control","codigo=\"9010\"") + respuestaAut[1] +  respuestaPeticiones + generarFooterXml();
				 log.warn("Alguna petición tuvo un error.");
			}			
				
				
			} catch (ParserConfigurationException e) {
				
				
		
				respuesta = generarCabeceraXml() +  generaXMLError() + generarFooterXml();

				log.error("error ParserConfigurationException: "+e.getMessage());
				
				listaLog.add("Error ParserConfigurationException: "+e.getMessage());
				
				//return respuesta; 
				
			} catch (SAXException e) {

				respuesta = generarCabeceraXml() +  generaXMLError() + generarFooterXml();
				SAXParseException spe = new SAXParseException(e.getMessage(), null, e);
				log.error("---> Parsing error at line = " + spe.getLineNumber() + " and column=" + spe.getColumnNumber());
				log.error("error SAXException: "+e.getMessage());
				
				listaLog.add("Error SAXException: "+e.getMessage());
				
				//return respuesta; 
			} catch (IOException e) {

				respuesta = generarCabeceraXml() +  generaXMLError() + generarFooterXml();
               
				log.error("error IOException: "+e.getMessage());
				
				listaLog.add("Error IOException: "+e.getMessage());
				
				//return respuesta; 
			} catch (Exception e)
			{
				System.out.print("Error no determinado: "+e.getMessage());

				respuesta = generarCabeceraXml() +  generaXMLError() + generarFooterXml();
				
				log.error("error Exception: "+e.getMessage());
				
				listaLog.add("Error no determinado: "+e.getMessage());
				

				//return respuesta; 
				
			}
			finally{
				if(listaLog.size()>0){
					log.info("enviando email por errores encontrados...");
					String maildefault= Constantes.getInstancia().maildefault;
					String listacorreo= PrevipassDAO.obtenerDestinatariosCorreo();
					if(listacorreo.equals("")){
						listacorreo= maildefault;
						listaLog.add(PrevipassDAO.getMensajeError());
					}
					EnviadorEmail.enviar(listaLog,Constantes.getInstancia().jndimail,listacorreo, ambiente);
				}
			}
			Calendar ahora2 = Calendar.getInstance();
			long tiempo2 = ahora2.getTimeInMillis();
			long dif= tiempo2-tiempo1;
			log.info("Tiempo respuesta: " + (double)dif/1000  + " seg.");
			log.info("Retorna respuesta: "+respuesta);
		   return respuesta;
		
	}
	
public static String generaXMLError()
{
	StringBuffer textoArchivo = new StringBuffer();
	
	textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9020\""));
	//textoArchivo.append("\n");
	
	textoArchivo.append(UtilXML.generarTag2("respuestaservicio","tipo=\"AUT\""));
	//textoArchivo.append("\n");
	
	textoArchivo.append(UtilXML.generarTag2("respuestaservicio","tipo=\"SUBCONTR\""));
	//textoArchivo.append("\n");

	return textoArchivo.toString();
	


}
	




public static String generarXMLCotizaciones(DatoEntradaVO data){


	// genera xml de tipo DIRTRABAJO una vez que se validen todos los datos.

	List cotizaciones = new ArrayList();
	StringBuffer textoArchivo = new StringBuffer();

	textoArchivo.append(UtilXML.abrirTag("respuestaservicio","tipo=\"SUBCONTR\""));
	//textoArchivo.append("\n");

	cotizaciones =  CotizacionDAO.obtenerCotizaciones(data);
	if(CotizacionDAO.getMensajeError()!=null){
		textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9760\""));
		listaLog.add(CotizacionDAO.getMensajeError());
	}else{
		log.debug("Numero de Cotizaciones encontradas : "+cotizaciones.size());

		if(cotizaciones.size() == 0){
			log.warn("No se encontraron cotizaciones para los parámetros ingresados");
			textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9750\""));
			//textoArchivo.append("\n");
			flag=false;
		}else{
			textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9050\""));
			//textoArchivo.append("\n");
		}
		//--------- se lee arraylist de cotizaciones y se arma XML

		Iterator itr = cotizaciones.iterator(); 
		while(itr.hasNext()) {

			CotizacionVO element = new CotizacionVO();

			element = (CotizacionVO)itr.next(); 

			log.trace("Contenido CotizacionVO: "+element.toString());

			//textoArchivo.append("\n");
			textoArchivo.append(UtilXML.generarTag2(
					"cotizacion", 
					"rut_empresa=\""+((element.getRutEmpresa() == null) ? "": element.getRutEmpresa().trim())+"\" "+
					"rut_trabajador=\""+ ((element.getRutTrabajador() == null) ? "":element.getRutTrabajador().trim())+"\" "+
					"nombre_trabajador=\""+((element.getNombreTrabajador() == null) ? "":element.getNombreTrabajador().trim())+"\" "+
					"periodo=\""+ ((element.getPeriodo()==null)?"":element.getPeriodo().trim() )+"\" "+
					"tipo_pago=\""+ ((element.getTipoPago()==null)?"":element.getTipoPago().trim() )+"\" "+
					"renta_imponible=\""+ ((element.getRentaImponible()== null)?"":element.getRentaImponible().trim())+"\" "+
					"monto_pagado=\""+((element.getMontoPagado()==null)?"":element.getMontoPagado().trim() )+"\" "+
					"folio=\""+ ((element.getFolio()==null)?"":element.getFolio().trim())+"\" "+
					"cod_mov_personal=\""+ ((element.getCodMovPersonal()==null)?"":element.getCodMovPersonal().trim() )+"\" "+
					"fecha_desde=\""+ ((element.getFechaDesde()==null)?"":element.getFechaDesde().trim() )+"\" "+
					"fecha_hasta=\""+ ((element.getFechaHasta()==null)?"":element.getFechaHasta().trim() )+"\" "+
					"dias_trabajados=\""+((element.getDiasTrabajados()==null) ?"":element.getDiasTrabajados().trim() )+"\" "));

			// textoArchivo.append("\n");

		} 
	}
	textoArchivo.append(UtilXML.cerrarTag("respuestaservicio"));
	//textoArchivo.append("\n");

	return textoArchivo.toString();
}

public static String generarFooterXml()
{
	
	StringBuffer textoArchivo = new StringBuffer();
	
	
	textoArchivo.append(UtilXML.cerrarTag("respuesta"));
	//textoArchivo.append("\n");

	textoArchivo.append(UtilXML.cerrarCdata());
	//textoArchivo.append("\n");
	
	return textoArchivo.toString();
	
}

public static String generarFooter2Xml()
{
	
	StringBuffer textoArchivo = new StringBuffer();
	
	
	textoArchivo.append(UtilXML.cerrarTag("respuestaservicio"));
	//textoArchivo.append("\n");
	
	textoArchivo.append(UtilXML.cerrarTag("respuesta"));
	//textoArchivo.append("\n");

	textoArchivo.append(UtilXML.cerrarCdata());
	//textoArchivo.append("\n");
	
	return textoArchivo.toString();
	
}

public static String generarCabeceraXml()
{
	
	StringBuffer textoArchivo = new StringBuffer();
	
    textoArchivo.append(UtilXML.abrirCdata());
    //textoArchivo.append("\n");
	textoArchivo.append(UtilXML.generarCabecera());
	//textoArchivo.append("\n");
	textoArchivo.append(UtilXML.abrirTag("respuesta"));
	//textoArchivo.append("\n");
	

	return textoArchivo.toString();
	
}


	
}	
