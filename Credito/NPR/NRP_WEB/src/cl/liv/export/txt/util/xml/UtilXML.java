package cl.liv.export.txt.util.xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class UtilXML {

	public static String generarXML(Document doc){
		String documento = "";
		if(doc != null){
			for(int i=0; i<doc.getChildNodes().getLength(); i++){
				Node nodo = doc.getChildNodes().item(i);
				documento = documento + getDataNodo(nodo) +"\n";
			}
		}
		
		return documento;
		
	}

	public static boolean reemplazarAtributo(Document doc, Node nodo, String nombreTag, String atributoABuscar, String textoComparacion,String atributoAReemplazar, String nuevoValor) throws JSONException{
		boolean resultadoOperacion = false;
		if(nodo != null){
			if(nodo.getNodeName().equals(nombreTag) && nodo.hasAttributes()){
				//Consulto si es el atributo buscado...
				boolean encontrado = false;
				Node atributoMarcado = null;
				for(int i=0; i< nodo.getAttributes().getLength(); i++){
					Node attr = nodo.getAttributes().item(i);
					if(attr.getNodeName().equals(atributoABuscar) && attr.getNodeValue().equals(textoComparacion) ){
						encontrado = true;
					}
					if(attr.getNodeName().equals(atributoAReemplazar) ){
						atributoMarcado = attr;
					}
					
				}
				if(encontrado && atributoMarcado != null){
					atributoMarcado.setNodeValue(nuevoValor);
					return true;
				}
				
			}
			
		
			//Busco en sus hijos
			if(nodo.hasChildNodes()){
				for(int i=0; i< nodo.getChildNodes().getLength(); i++){
					Node subNodo = nodo.getChildNodes().item(i);
					resultadoOperacion = reemplazarAtributo(doc, subNodo, nombreTag, atributoABuscar, textoComparacion, atributoAReemplazar, nuevoValor);
					if(resultadoOperacion){
						return true;
					}
				}
			}
		}
		
		
		
		return resultadoOperacion;
	}
	
	public static boolean reemplazarContenido(Document doc, Node nodo, String atributo, String idTag, String idTagCrear, JSONArray data) throws JSONException{
		boolean resultadoOperacion = false;
		if(nodo != null){
			if(nodo.hasAttributes()){
				//Consulto si es el atributo buscado...
				for(int i=0; i< nodo.getAttributes().getLength(); i++){
					Node attr = nodo.getAttributes().item(i);
					if(attr.getNodeName().equals(atributo) && idTag.equals(attr.getTextContent())){
						while(nodo.hasChildNodes()){
							nodo.removeChild( nodo.getChildNodes().item(0)  );
						}
						
						for(int j=0; j< data.length(); j++){
							JSONObject json = data.getJSONObject(j);
//							if( ( json.has("no_incluir") && Boolean.parseBoolean(  json.getString("no_incluir") ) ) 
//								|| ( json.has("nombre") && json.getString("nombre").trim().length() == 0)	
//									
//									) {
//								System.out.println("no se incluye columna");
//							}
//							else {
							if(true) {
								Node nodoAdd = getNodo(doc, idTagCrear, json);
								nodo.appendChild(nodoAdd);
							}
						}
						
						return true;
					}
				}
				
			}
			
		
			//Busco en sus hijos
			if(nodo.hasChildNodes()){
				for(int i=0; i< nodo.getChildNodes().getLength(); i++){
					Node subNodo = nodo.getChildNodes().item(i);
					resultadoOperacion = reemplazarContenido(doc, subNodo, atributo, idTag,idTagCrear, data);
					if(resultadoOperacion){
						return true;
					}
				}
			}
		}
		
		
		
		return resultadoOperacion;
	}
	
	
	public static Node getNodo(Document doc, String tag, JSONObject json) throws DOMException, JSONException{
		Node nodo = doc.createElementNS("", tag);
		
		
		String[] names = JSONObject.getNames(json);
		
		if(names != null && names.length>0){
			for(int i=0; i<names.length; i++){
				((Element) nodo).setAttribute(names[i], json.getString( names[i]  ));
			}
		}
		

		return nodo;
		
	}
	
	public static String getDataNodo(Node nodo){
		String documento = "";
		if(nodo.getNodeType() != 1 && nodo.getNodeType() != 4  ) return "";
		
		if(nodo.getNodeType() == 1){
			documento = documento +"<"+nodo.getNodeName();
		}
		else if(nodo.getNodeType() == 4){
			documento = documento +"<![CDATA[ \n\n";
		} 
		
		if(nodo.hasAttributes()){
			for(int j=0; j<nodo.getAttributes().getLength(); j++){
				documento = documento +" "+nodo.getAttributes().item(j).getNodeName() +"=\""+ nodo.getAttributes().item(j).getTextContent()+"\"";
			}
			
			
		}
		
		if(nodo.getNodeType() == 1){
			documento = documento +">";
		}
		
		
		
		if(!nodo.hasChildNodes()){
			documento = documento +nodo.getTextContent();
		
		}
		if(nodo.hasChildNodes()){
			for(int j=0; j<nodo.getChildNodes().getLength(); j++){
				Node subNodo = nodo.getChildNodes().item(j);
				documento = documento + getDataNodo(subNodo) +"\n";
			}
		}
		
		if(nodo.getNodeType() == 1){
			documento = documento +"</"+nodo.getNodeName()+">";
		}
		else if(nodo.getNodeType() == 4){
			documento = documento +"]]> \n";
		} 
		return documento;
	}

	

	
}
