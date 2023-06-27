package cl.laaraucana.integracion.impl;

import javax.xml.parsers.*;
import org.w3c.dom.*;

import java.io.File;
import java.io.IOException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class XMLParser {
	
	
	//	 Constantes para validacion de Schemas   
	final static String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";  
	final static String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";  
	final static String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";  
	final static String MY_SCHEMA = "C:/xml/validateXML2.xsd";  
	final static String MY_XML= "C:/xml/validateXML.xml";  
	
	/*
	public static void main(String[] args) throws Exception{
		
		parsearDocumento();
	}*/
	
    public static String parsearDocumento(String entrada)
    {
	                          
		//	 Creando la factoria e indicando que hay validacion  
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();  
			documentBuilderFactory.setNamespaceAware(true);  
			documentBuilderFactory.setValidating(true);  
			          
			try {  
			    
			  //Configurando el Schema de validacion          
			  documentBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);  
			  documentBuilderFactory.setAttribute(JAXP_SCHEMA_SOURCE, new File(MY_SCHEMA));  
			            
			  // Parseando el documento  
			  DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();  
			  documentBuilder.setErrorHandler(new XMLErrorHandler());  
			    
			  //Document document = documentBuilder.parse(new File(MY_XML));  
			  
			  
			  InputStream is = new ByteArrayInputStream(entrada.getBytes());
			  
			  Document document = documentBuilder.parse(is); 
			  
			  
			  
			  NodeList sections = document.getElementsByTagName("peticionservicio");
			    int numSections = sections.getLength();
			    
			    System.out.print("num: "+numSections);
			    
			    for (int i = 0; i < numSections; i++) {
			    	
			      Element section = (Element) sections.item(i); 

			      
			      System.out.print("TIPO: "+section.getAttribute("tipo"));
			      
			      /*
			      Node title = section.getFirstChild();
			      while (title != null && title.getNodeType() != Node.ELEMENT_NODE)
			        title = title.getNextSibling();

			      if (title != null)
			        System.out.println(title.getFirstChild().getNodeValue());
			      */
			      
			    }
			  
			    

			//  System.out.print("OK");
			    
			  } catch (SAXException ex) { 
				  
                System.out.print("sax exception: "+ex.getMessage());  
				  
				return  ""; // XMLResponse.respuestaErrorXml().toString();
				  

			  }catch(ParserConfigurationException ex1)
			  {
				  System.out.print("ParserConfigurationException: "+ex1.getMessage());
				  
			  }catch(IOException ex2)
			  {
				  System.out.print("IOException"+ex2.getMessage());
				  
			  }
			  
			  
			  return "";
	
	  
    }

}
