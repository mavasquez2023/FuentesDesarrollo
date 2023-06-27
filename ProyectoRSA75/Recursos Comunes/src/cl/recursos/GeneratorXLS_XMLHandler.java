/*
 * Creado el 01-06-2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.recursos;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.Vector;

public class GeneratorXLS_XMLHandler extends DefaultHandler 
{

  //vector de instancias
  private Vector instancias;
//objeto AFP que se esta procesando
  private GeneradorXLS_ContenedorXML objeto;
  //valor contenido entre las etiquetas de un elemento
  private String valor;

  protected GeneratorXLS_XMLHandler (Vector v)
  {
    instancias = v;
  }

  /*
      localName: contiene el nombre de la etiqueta.
      att: de la clase "org.xml.sax.Attributes", es una tabla que contiene
           los atributos contenidos en la etiqueta.
  */
  public void startDocument () throws SAXException {
  	System.out.println("Comenzando a cargar XML");
  }

  public void startElement( String namespaceURI, String localName, String qName,
                                           Attributes attr ) throws SAXException 
  {
    //comprobamos si empezamos un elemento "respuestaservicio"
  	if (localName.equals("formato")){
//  	creamos un nuevo objeto y lo agregamos al vector
        objeto = new GeneradorXLS_ContenedorXML ();
        instancias.addElement (objeto);
     }
  	
  	//se lee el tag rut
  	if (localName.equals("campo")){
        //Se rescatan los atributos del registro
        objeto.setNombre(attr.getValue("nombre"));
        objeto.setPosIni(attr.getValue("posini"));
        objeto.setLargo(attr.getValue("largo"));
        objeto.setAlign(attr.getValue("align"));
     }
  }
  
  public void endElement (String namespaceURI, String localName, String rawName)
  throws SAXException
  {
  	
  }

  /*
    Los parametros que recibe es la localizacion de los carateres del elemento.
  */
  public void characters (char[] ch, int start, int end) throws SAXException
  {
    //creamos un String con los caracteres del elemento y le quitamos 
    //los espacios en blanco que pueda tener en los extremos.
    valor = new String (ch, start, end);
    valor = valor.trim();
  }
  public void error(SAXParseException e)
  throws SAXParseException
  {
      throw e;
  }

}

