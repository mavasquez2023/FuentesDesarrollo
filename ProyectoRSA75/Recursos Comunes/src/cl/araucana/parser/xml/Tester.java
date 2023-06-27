

/*
 * @(#) LoggingTester.java    1.0 03-02-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.parser.xml;

import cl.recursos.Archivo;

/*
 * @author Claudio Lillo
 *
 * @version 1.0
 */
public class Tester {
	public static void main(String[] args) {
		System.out.println("leyendo XML");
		Archivo archivo= new Archivo();
		//String xml= archivo.leerArchivo("/tmp/recursos/pgl052015.xml");
		String xml= archivo.leerArchivo("/Proyectos/ParserXML/catalogo.xml");
		//Metodo 1
		//DocumentBuilderParserXML.parsearDocumento(xml);
		//Método 2
		SaxParser.parsearDocumento(xml);
	}

}
