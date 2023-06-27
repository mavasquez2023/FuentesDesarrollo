package cl.araucana.pruebas;

import cl.laaraucana.utils.GetValueXML;

public class TestGetXml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GetValueXML getValue = new GetValueXML();
		System.out.println("main " + getValue.getValue("creditos/consulta/where-afiliado"));

	}

}
