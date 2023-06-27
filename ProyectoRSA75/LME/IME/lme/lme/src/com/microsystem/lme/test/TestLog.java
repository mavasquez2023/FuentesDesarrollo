package com.microsystem.lme.test;

import org.apache.log4j.Logger;

public class TestLog {

	private Logger log = Logger.getLogger(this.getClass());

	public static void main(String[] args) {
		//		TestLog test = new TestLog();
		//		test.imprimir();
		String texto = "asdasd SQL0s803 asd";
		
		System.out.println(texto.indexOf("SQL0803"));
		if(texto.indexOf("SQL0803") != -1){
			System.out.println("lo encontro");
		}else{
			System.out.println("no lo encontro");
		}
	}

	public void imprimir() {
		System.out.println("sistem");
		log.debug("asdsdasd");
		log.info("asdsdasd");
	}

}
