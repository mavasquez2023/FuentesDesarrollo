package cl.liv.archivos.comun.xml.lector.impl;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cl.liv.archivos.comun.xml.lector.dto.AtributoDTO;
import cl.liv.archivos.comun.xml.lector.dto.NodoDTO;


public class ProcesarXML {

	
	public static NodoDTO init(String rutaArchivo) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		NodoDTO configuracion = new NodoDTO("");
		DocumentBuilder docBuilder;
		
			docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (rutaArchivo);

			doc.getDocumentElement ().normalize ();
			
			getDataNode(((Node)doc.getDocumentElement ()), configuracion);
			
			
		return configuracion;
	}

	public static void getDataNode(Node node, NodoDTO configuracion){
		
		if(node != null){
			configuracion.setName(node.getNodeName());
			loadAtributtes(node, configuracion);
			
			NodeList nodosHijos = node.getChildNodes();
			if(nodosHijos != null && nodosHijos.getLength()>0){
				for(int i=0; i< nodosHijos.getLength(); i++){
					Node nodoHijo = nodosHijos.item(i);
					if( !nodoHijo.getNodeName().equals("#text") && nodoHijo.getNodeName().length()>0){
						NodoDTO n1 = new NodoDTO(nodoHijo.getNodeName());
						configuracion.getNodosHijos().add(n1);
						getDataNode(nodoHijo, n1);
					}
					
				}
			}
		}
	
	}

	public static void loadAtributtes(Node node, NodoDTO configuracion){
		if(node != null && node.getAttributes() != null){
			int cantidadAtributos = node.getAttributes().getLength();
			for(int i=0; i<cantidadAtributos; i++){
				Attr attribute = (Attr)node.getAttributes().item(i);
				configuracion.getAtributos().add(new AtributoDTO(attribute.getName(), attribute.getValue()));
			}
		}
		
	}
	
	public static void main(String[] args) {
		String rutaArchivo = "/home/desarrollo/workspace/presupuestaria/CargasMasiva/src/cl/sbs/utiles/carga/masiva/etc/cargas_masivas.xml";
		try {
			NodoDTO config =  init(rutaArchivo) ;
			mostrarNodo(config);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void mostrarNodo(NodoDTO nodo){
		mostrarAtributos(nodo);
		if(nodo != null && nodo.getNodosHijos().size()>0){
			for(int i=0; i< nodo.getNodosHijos().size() ; i++){
				NodoDTO n1 = (NodoDTO) nodo.getNodosHijos().get(i);
				mostrarNodo(n1);
			}
		}
	}
	
	public static void mostrarAtributos(NodoDTO nodo){

		if(nodo != null && nodo.getAtributos().size()>0){
			for(int i=0; i<nodo.getAtributos().size(); i++){
				AtributoDTO at = (AtributoDTO)nodo.getAtributos().get(i);
			}
		}
		
	}
	
}
