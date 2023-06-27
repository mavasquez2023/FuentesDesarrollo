

/*
 * @(#) LoggingTester.java    1.0 03-02-2006
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
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
		//M�todo 2
		SaxParser.parsearDocumento(xml);
	}

}
