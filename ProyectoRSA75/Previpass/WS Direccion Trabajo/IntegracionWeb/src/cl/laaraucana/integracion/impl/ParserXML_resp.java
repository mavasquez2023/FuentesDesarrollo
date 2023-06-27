package cl.laaraucana.integracion.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xerces.parsers.XMLDocumentParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cl.laaraucana.integracion.util.Constantes;
import cl.laaraucana.integracion.util.Util;
import cl.laaraucana.integracion.util.UtilXML;
import cl.laaraucana.integracion.vo.DatoEntradaVO;
import cl.laaraucana.integracion.vo.CotizacionVO;
import cl.laaraucana.integracion.dao.*;





public class ParserXML_resp {

	public static String parsearDocumento(String xml)
    {
		
		// formato de String de entrada
		
		/*String xml = "<peticion llave=\"\">"+
			"<peticionservicio tipo=\"AUT\">"+
			"<parametro nombre=\"usuario\" valor=\"99999999-9\" />"+
			"<parametro nombre=\"password\" valor=\"xxx\" />"+
			"</peticionservicio>"+
			"<peticionservicio tipo=\"DIRTRABAJO\">"+
			"<parametro nombre=\"rut_empleador\" valor=\"99999999-9\" />"+
			"<parametro nombre=\"rut_trabajador\" valor=\"99999999-9\" />"+
			"<parametro nombre=\"periodo\" valor=\"200901\" />"+
			"</peticionservicio>"+
			"</peticion>";*/
		
		
		// se eliminar los saltos de línea
		xml = xml.replaceAll("\n", "");
		
		DocumentBuilder builder = null;
		Document tmpX= null;
		HashMap hashDirTrabajo;
		HashMap hashAutenticacion;
		String respuestaAut[]= new String[2];
		String respuestaDirTrabajo[]= new String[2];
		
		String respuesta ="";

			try {
				
				builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				tmpX=builder.parse(new ByteArrayInputStream(xml.getBytes()));
				
				Node nodo = tmpX.getFirstChild();
				
				
				
				NodeList aut = nodo.getChildNodes().item(0).getChildNodes();
				
			
				
				NodeList dirTrabajo = nodo.getChildNodes().item(1).getChildNodes();
				
				System.out.print("largo nodos: "+nodo.getChildNodes().getLength());
				
				
				
				System.out.print("dir trabajo: "+dirTrabajo);
				
				// se rescatan los valores y atributos de <peticionservicio tipo="AUT">
				
				// paramAut1 debe tener como valor "usuario"
				String paramAut1 = aut.item(0).getAttributes().getNamedItem("nombre").getNodeValue();
				
				// paramAut2 debe tener como valor "password"
				String paramAut2 = aut.item(1).getAttributes().getNamedItem("nombre").getNodeValue();
				
				System.out.println("paraaut 1 usuario: "+paramAut1);
				System.out.println("paraaut 2 password: "+paramAut2);
				
				if ( ! Util.validaAtributosAut(paramAut1, paramAut2)){
				
					System.out.println("error en valida atributos aut"); 
					return generarCabeceraXml() +  generaXMLError() + generarFooterXml();
				
				}
				
				
				String nombre = aut.item(0).getAttributes().getNamedItem("valor").getNodeValue();
				String password = aut.item(1).getAttributes().getNamedItem("valor").getNodeValue();
				
				
				
				
				// se rescatan los valores y atributos de <peticionservicio tipo="DIRTRABAJO">
				
                //	 paramDir1 debe tener como valor "rut_empleador"
				String paramDir1 = dirTrabajo.item(0).getAttributes().getNamedItem("nombre").getNodeValue();
				
				// paramDir2 debe tener como valor "rut_trabajador"
				String paramDir2 = dirTrabajo.item(1).getAttributes().getNamedItem("nombre").getNodeValue();
				
               //	paramDir3 debe tener como valor "periodo"
				String paramDir3 = dirTrabajo.item(2).getAttributes().getNamedItem("nombre").getNodeValue();
				
				
				System.out.println("paramDir1  rut_empleador: "+paramDir1);
				System.out.println("paramDir2  rut_trabajador: "+paramDir2);
				System.out.println("paramDir3  periodo: "+paramDir3);
				
				
                if ( ! Util.validaAtributosDirTrabajo(paramDir1, paramDir2,paramDir3)){
				
                	System.out.println("error en valida atributos dir trabajo");
					return generarCabeceraXml() +  generaXMLError() + generarFooterXml();
                }
				
				
				String rutEmpleador = dirTrabajo.item(0).getAttributes().getNamedItem("valor").getNodeValue();
				String rutTrabajador = dirTrabajo.item(1).getAttributes().getNamedItem("valor").getNodeValue();
				String periodo = dirTrabajo.item(2).getAttributes().getNamedItem("valor").getNodeValue();
		
				DatoEntradaVO data = new DatoEntradaVO(nombre,password,rutEmpleador,rutTrabajador,periodo);
				
				Object[] datosEntrada = new Object[nodo.getChildNodes().getLength()];
				String nom =null;
				String val =null;
				for(int i=0;i<nodo.getChildNodes().getLength();i++){
					NodeList subNodo = nodo.getChildNodes().item(i).getChildNodes();
					
					HashMap item = new HashMap();
					
					item.put("TIPO", nodo.getChildNodes().item(i).getAttributes().getNamedItem("tipo").getNodeValue());
					nom =null;
					val =null;
					for(int j= 0; j<subNodo.getLength();j++){
						nom = subNodo.item(j).getAttributes().getNamedItem("nombre").getNodeValue();
						val = subNodo.item(j).getAttributes().getNamedItem("valor").getNodeValue();
						item.put(nom, val);
					}
					datosEntrada[i] = item;
					
				
					
				}	
                //				 tiene los datos de DIRTRABAJO
				hashDirTrabajo = (HashMap) datosEntrada[1];
				
				// tiene los datos de AUT
				hashAutenticacion = (HashMap) datosEntrada[0];
				
				respuesta = generarCabeceraXml();
				
				// validaciones.
				
				// se valida usuario y password y que tipo sea AUT
				String tipoAut = hashAutenticacion.get("TIPO").toString();
				String tipoDirTrabajo = hashDirTrabajo.get("TIPO").toString();
				
	
				respuestaAut = Util.validaUsuario(data.getUsuario(), data.getPassword(), tipoAut);

                //	esto indica que se autenticó exitosamente, pasó las validaciones de TIPO AUT, además del password y usuario no sean vacíos.
				if(respuestaAut[0].equals("1"))
				{
					
					/*respuestaDirTrabajo = Util.validaDirTrabajo(data.getRutEmpl(),data.getDvEmpl(), data.getRutTrab(),data.getDvTrab(), 
							 tipoDirTrabajo, data.getMes(), data.getAgno());*/
					
					
					data.setRutEmpl(Util.agregarEspaciosIzquierda(data.getRutEmpl(),9));
					data.setRutTrab(Util.agregarEspaciosIzquierda(data.getRutTrab(),9));
					
					
					// esto indica que parametro dir trabajo esta OK (= 1)
					if(respuestaDirTrabajo[0].equals("1"))
						respuesta = respuesta +  UtilXML.generarTag2("control","codigo=\"9000\"") + respuestaAut[1] + generarXMLCotizaciones(data) + generarFooterXml();
					else
						respuesta = respuesta + UtilXML.generarTag2("control","codigo=\"9010\"") + respuestaAut[1] + respuestaDirTrabajo[1] +  generarFooterXml();
					
					
					 return respuesta;
					
				}else
				  respuesta = respuesta + UtilXML.generarTag2("control","codigo=\"9010\"") + respuestaAut[1]+ generarFooterXml();
				
				
				
			} catch (ParserConfigurationException e) {
				
				
		
				respuesta = generarCabeceraXml() +  generaXMLError() + generarFooterXml();

				System.out.print("error parserConfigurationException: "+e.getMessage());
				
				//return respuesta; 
				
			} catch (SAXException e) {

				respuesta = generarCabeceraXml() +  generaXMLError() + generarFooterXml();

				System.out.print("error SAXException: "+e.getMessage());
				
				//return respuesta; 
			} catch (IOException e) {

				

				respuesta = generarCabeceraXml() +  generaXMLError() + generarFooterXml();
               
				System.out.print("error IOException: "+e.getMessage());
				
				//return respuesta; 
			} catch (Exception e)
			{
				System.out.print("Error no determinado: "+e.getMessage());

				respuesta = generarCabeceraXml() +  generaXMLError() + generarFooterXml();
				
				System.out.print("error IOException: "+e.getMessage());

				//return respuesta; 
				
			}

	   
       System.out.println("retorna respuesta: "+respuesta);
		   return respuesta;
		
	}
	
public static String generaXMLError()
{
	StringBuffer textoArchivo = new StringBuffer();
	
	textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9020\""));
	textoArchivo.append("\n");
	
	textoArchivo.append(UtilXML.generarTag2("respuestaservicio","tipo=\"AUT\""));
	textoArchivo.append("\n");
	
	textoArchivo.append(UtilXML.generarTag2("respuestaservicio","tipo=\"DIRTRABAJO\""));
	textoArchivo.append("\n");

	return textoArchivo.toString();
	


}
	




public static String generarXMLCotizaciones(DatoEntradaVO data){
	

	// genera xml de tipo DIRTRABAJO una vez que se validen todos los datos.
	
	List cotizaciones = new ArrayList();
	
	System.out.println("rut emple: "+data.getRutEmpleador());
	System.out.println("rut trab: "+data.getRutTrabajador());
	System.out.println("periodo: "+data.getPeriodo());
	
	cotizaciones =  CotizacionDAO.obtenerCotizaciones(data);
	
	System.out.println("Numero de Cotizaciones : "+cotizaciones.size());
	
	StringBuffer textoArchivo = new StringBuffer();
	

		textoArchivo.append(UtilXML.abrirTag("respuestaservicio","tipo=\"DIRTRABAJO\""));
		textoArchivo.append("\n");
		
		
		
		if(cotizaciones.size() == 0){
			textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9740\""));
			textoArchivo.append("\n");
			
		}else{
		
		textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9050\""));
		textoArchivo.append("\n");
		
		}
		//--------- se lee arraylist de cotizaciones y se arma XML
		
		Iterator itr = cotizaciones.iterator(); 
		while(itr.hasNext()) {

			CotizacionVO element = new CotizacionVO();
			
		    element = (CotizacionVO)itr.next(); 
		   
		    System.out.println("Contenido CotizacionVO: "+element.toString());
		  
			textoArchivo.append("\n");
				textoArchivo.append(UtilXML.generarTag2(
											"cotizacion", 
											"rut_empresa=\""+((element.getRutEmpresa() == null) ? "": element.getRutEmpresa().trim())+"\" "+
											"rut_trabajador=\""+ ((element.getRutTrabajador() == null) ? "":element.getRutTrabajador().trim())+"\" "+
											"nombre_trabajador=\" "+((element.getNombreTrabajador() == null) ? "":element.getNombreTrabajador().trim())+"\" "+
											"periodo=\""+ ((element.getPeriodo()==null)?"":element.getPeriodo().trim() )+"\" "+
											"tipo_pago=\""+ ((element.getTipoPago()==null)?"":element.getTipoPago().trim() )+"\" "+
											"renta_imponible=\""+ ((element.getRentaImponible()== null)?"":element.getRentaImponible().trim())+"\" "+
											"monto_pagado=\""+((element.getMontoPagado()==null)?"":element.getMontoPagado().trim() )+"\" "+
											"folio=\""+ ((element.getFolio()==null)?"":element.getFolio().trim())+"\" "+
											"cod_mov_personal=\""+ ((element.getCodMovPersonal()==null)?"":element.getCodMovPersonal().trim() )+"\" "+
											"fecha_desde=\""+ ((element.getFechaDesde()==null)?"":element.getFechaDesde().trim() )+"\" "+
											"fecha_hasta=\""+ ((element.getFechaHasta()==null)?"":element.getFechaHasta().trim() )+"\" "+
											"dias_trabajados=\""+((element.getDiasTrabajados()==null) ?"":element.getDiasTrabajados().trim() )+"\" "));
			
			/*textoArchivo.append(UtilXML.generarTag2(
					"cotizacion", 
					"rut_empresa=\""+element.getRutEmpresa()+"\" "+
					"rut_trabajador=\""+element.getRutTrabajador()+"\" "+
					"nombre_trabajador=\" "+element.getNombreTrabajador()+"\" "+
					"periodo=\""+element.getPeriodo()+"\" "+
					"tipo_pago=\""+element.getTipoPago()+"\" "+
					"renta_imponible=\""+element.getRentaImponible()+"\" "+
					"monto_pagado=\""+element.getMontoPagado()+"\" "+
					"folio=\""+element.getFolio()+"\" "+
					"cod_mov_personal=\""+element.getCodMovPersonal()+"\" "+
					"fecha_desde=\""+element.getFechaDesde()+"\" "+
					"fecha_hasta=\""+element.getFechaHasta()+"\" "));*/
			
			  textoArchivo.append("\n");
			
		    
		    

		} 
		
		
		textoArchivo.append(UtilXML.cerrarTag("respuestaservicio"));
		textoArchivo.append("\n");
		
		//-------------------------------------------------------------------------------------------------	
			
	
	return textoArchivo.toString();
}

public static String generarFooterXml()
{
	
	StringBuffer textoArchivo = new StringBuffer();
	
	
	textoArchivo.append(UtilXML.cerrarTag("respuesta"));
	textoArchivo.append("\n");

	textoArchivo.append(UtilXML.cerrarCdata());
	textoArchivo.append("\n");
	
	return textoArchivo.toString();
	
}

public static String generarFooter2Xml()
{
	
	StringBuffer textoArchivo = new StringBuffer();
	
	
	textoArchivo.append(UtilXML.cerrarTag("respuestaservicio"));
	textoArchivo.append("\n");
	
	textoArchivo.append(UtilXML.cerrarTag("respuesta"));
	textoArchivo.append("\n");

	textoArchivo.append(UtilXML.cerrarCdata());
	textoArchivo.append("\n");
	
	return textoArchivo.toString();
	
}

public static String generarCabeceraXml()
{
	
	StringBuffer textoArchivo = new StringBuffer();
	
    textoArchivo.append(UtilXML.abrirCdata());
    textoArchivo.append("\n");
	textoArchivo.append(UtilXML.generarCabecera());
	textoArchivo.append("\n");
	textoArchivo.append(UtilXML.abrirTag("respuesta"));
	textoArchivo.append("\n");
	

	return textoArchivo.toString();
	
}

/*
public static String generarXMLSalida(){
		
		
		StringBuffer textoArchivo = new StringBuffer();
		textoArchivo.append(UtilXML.generarCabecera());
		textoArchivo.append("\n");
		textoArchivo.append(UtilXML.abrirTag("respuesta"));
		textoArchivo.append("\n");
			textoArchivo.append(UtilXML.generarTag("control","codigo=\"9000\"",""));
			textoArchivo.append("\n");
			textoArchivo.append(UtilXML.abrirTag("respuestaservicio","tipo=\"AUT\""));
			textoArchivo.append("\n");

				textoArchivo.append(UtilXML.generarTag("control","codigo=\"90SO\"",""));
				textoArchivo.append("\n");
				textoArchivo.append(UtilXML.abrirTag("respuestaaut"));
				textoArchivo.append("\n");
					textoArchivo.append(UtilXML.generarTag("llave", "", "9000900090009000"));
					textoArchivo.append("\n");
				textoArchivo.append(UtilXML.cerrarTag("respuestaaut"));
				textoArchivo.append("\n");
			textoArchivo.append(UtilXML.cerrarTag("respuestaservicio"));
			textoArchivo.append("\n");

			textoArchivo.append(UtilXML.abrirTag("respuestaservicio","tipo=\"DIRTRABAJO\""));
			textoArchivo.append("\n");
			textoArchivo.append(UtilXML.generarTag("control","codigo=\"90SO\"",""));
			textoArchivo.append("\n");
				textoArchivo.append(UtilXML.abrirTag("cotizacion"));
				textoArchivo.append("\n");
					textoArchivo.append(UtilXML.generarTag(
												"cotizacion", 
												"rut empresa=\"99999999-9\" "+
												"rut trabajador=\"99999999-9\" "+
												"nombre trabajador=\"XXXXXXX XXXXXX XXXX\" "+
												"periodo=\"200901\" "+
												"tipo pago=\"01\" "+
												"renta imponible=\"999999\" "+
												"monto pagado=\"99999\" "+
												"folio=\"9999999999999\" "+
												"cod mov personal=\"01\" "+
												"fecha desde=\"01j01j2009\" "+
												"fecha hasta=\"01j01j2009\" "+
												"monto pagado=\"9999999\" ", 
												""));
					textoArchivo.append("\n");
				textoArchivo.append(UtilXML.cerrarTag("cotizacion"));
				textoArchivo.append("\n");
				
				textoArchivo.append(UtilXML.abrirTag("cotizacion"));
				textoArchivo.append("\n");
				textoArchivo.append(UtilXML.generarTag(
											"cotizacion", 
											"rut empresa=\"99999999-9\" "+
											"rut trabajador=\"99999999-9\" "+
											"nombre trabajador=\"XXXXXXX XXXXXX XXXX\" "+
											"periodo=\"200901\" "+
											"tipo pago=\"02\" "+
											"renta imponible=\"999999\" "+
											"monto pagado=\"99999\" "+
											"folio=\"9999999999999\" "+
											"cod mov personal=\"01\" "+
											"fecha desde=\"01j01j2009\" "+
											"fecha hasta=\"01j01j2009\" "+
											"monto pagado=\"9999999\" ", 
											""));
				textoArchivo.append("\n");
			textoArchivo.append(UtilXML.cerrarTag("cotizacion"));
			textoArchivo.append("\n");
			textoArchivo.append(UtilXML.cerrarTag("respuestaservicio"));
			textoArchivo.append("\n");
			
			textoArchivo.append(UtilXML.cerrarTag("respuesta"));
			textoArchivo.append("\n");
		
		return textoArchivo.toString();
	}*/
	
}	
