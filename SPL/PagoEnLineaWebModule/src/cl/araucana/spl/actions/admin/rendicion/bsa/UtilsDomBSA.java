package cl.araucana.spl.actions.admin.rendicion.bsa;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import cl.araucana.spl.beans.xmlbsa.ArchivoRendicion;
import cl.araucana.spl.beans.xmlbsa.DetallePagos;
import cl.araucana.spl.beans.xmlbsa.TotalizadorPagos;
import cl.araucana.spl.exceptions.RendicionException;

public class UtilsDomBSA {
	private static Logger logger = Logger.getLogger(UtilsDomBSA.class);

	/**
	 * Transforma un string xml valido a Document.
	 * @param xml
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document getDocument(String xml) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xml.toString())));
		return doc;
	}
	
	/**
	 * Convierte el objeto Node a objeto ArchivoRendicion.
	 * @param root
	 * @return
	 * @throws RendicionException
	 */
	public ArchivoRendicion umArchivoRendicion(Node root) throws RendicionException  {
		ArchivoRendicion archivoRend = new ArchivoRendicion();		
		
		Node n;
		NodeList nodes = root.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			n = nodes.item(i);

			//Validar que sea nodo
		    if (n.getNodeType() == Node.ELEMENT_NODE) {	
		    	String nodeName = n.getNodeName(); 
		    	if ("totalizadorPagos".equals(nodeName)) {
				    archivoRend.setTotalizador(umTotalizador(n));
				}else if ("detallePagos".equals(nodeName)) {
				    archivoRend.addDetalle(umDetalles(n));
				}else{
				    //unexpected element in ArchivoRendicion
					logger.info("Elemento de ArchivoRendicion (nivel 1) no permitido: " + nodeName);
					throw new RendicionException("rendicion.archivo.xml.elementoNoPermitido");
				}
			}
		}
		return archivoRend;
	}
	
	
	/**
	 * Convierte el Node en un objeto TotalizadorPagos
	 * @param nodo
	 * @return
	 * @throws RendicionException
	 */
	private TotalizadorPagos umTotalizador(Node nodo) throws RendicionException {
		TotalizadorPagos totalizador = new TotalizadorPagos();
		
		Node n;
		NodeList nodes = nodo.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			n = nodes.item(i);
			
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				String nodeName = n.getNodeName(); 
				if ("numeroPagos".equals(nodeName)) {
					totalizador.setNumeroPagos(umString(n));
				} else if ("montoTotal".equals(nodeName)) {
					totalizador.setMontoTotal(umString(n));
				} else {
					logger.info("Elemento de TotalizadorPagos (nivel 2) no permitido: " + nodeName);
					throw new RendicionException("rendicion.archivo.xml.elementoNoPermitido");
				}
				
			} else {
				logger.info("Inesperado tipo nodo en TotalizadorPagos");		
				throw new RendicionException("rendicion.archivo.xml.tipoNodoInesperado");
			}
		}
		return totalizador;
	}
	
	/**
	 * Convierte un Node en el objeto DetallePagos
	 * @param nodo
	 * @return
	 * @throws RendicionException
	 */
	private DetallePagos umDetalles(Node nodo) throws RendicionException {
		DetallePagos detalle = new DetallePagos();
		
		Node n;
		NodeList nodes = nodo.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			n = nodes.item(i);			
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				String nodeName = n.getNodeName();
				if ("idCarro".equals(nodeName)) {
					detalle.setIdCarro(umString(n));
				} else if ("idConvenio".equals(nodeName)) {
					detalle.setIdConvenio(umString(n));
				} else if ("numeroProducto".equals(nodeName)) {
					detalle.setNumeroProducto(umString(n));
				} else if ("numeroCliente".equals(nodeName)) {
					detalle.setNumeroCliente(umString(n));
				} else if ("expiracionProducto".equals(nodeName)) {
					detalle.setExpiracionProducto(umString(n));
				} else if ("descProducto".equals(nodeName)) {
					detalle.setDescProducto(umString(n));
				} else if ("montoProducto".equals(nodeName)) {
					detalle.setMontoProducto(umString(n));
				} else if ("fechahoraOperacion".equals(nodeName)) {
					detalle.setFechaHoraOperacion(umString(n));
				} else if ("idAtributo".equals(nodeName)) { //No utilizado por banco
					detalle.setIdAtributo(umString(n));
				} else if ("numeroOperacion".equals(nodeName)) { //No utilizado por banco
					detalle.setNumeroOperacion(umString(n));
					
				} else {
					logger.info("Elemento de DetallePagos (nivel 2) no permitido: " + nodeName);
					throw new RendicionException("rendicion.archivo.xml.elementoNoPermitido");
				}
				
			} else {
		    	logger.info("Inesperado tipo nodo en DetallePagos");		    	
		    	throw new RendicionException("rendicion.archivo.xml.tipoNodoInesperado");		    	
			}
		}
		return detalle;
	}
	
	
	/**
	 * Convierte un Node en el objeto String.
	 * @param nodo
	 * @return
	 * @throws RendicionException
	 */
	private String umString(Node nodo) throws RendicionException {
		String cadena = new String("");
		
		Node n;
		NodeList nodes = nodo.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			n = nodes.item(i);
			
			if (n.getNodeType() == Node.TEXT_NODE) {
				cadena = n.getNodeValue();
			} else {
				//Expected a text-only node
				logger.info("Solo se permite texto en el valor del nodo");		    	
		    	throw new RendicionException("");
			}
		}
		return cadena;
	}
	
	/**
	 * Entrega un string normalizado (quita tabs, enters, etc) 
	 * @param str
	 * @return
	 */
    public String normalizarString(String str) {
        if (str == null)
            return "";

        char[] c = str.toCharArray();
        char[] n = new char[c.length];
        boolean white = true;
        int pos = 0;
        for (int i = 0; i < c.length; i++) {
            if ("\t\n\r".indexOf(c[i]) != -1) {
                if (!white) {
                    //n[pos++] = ' ';
                    white = true;
                }
            }
            else {
                n[pos++] = c[i];
                white = false;
            }
        }
        /*if (white && pos > 0) {
            pos--;
        }*/
        return new String (n, 0, pos);
    }	

}
