package cl.ccaf.previpass.test;

import cl.ccaf.previpass.ldap.ProxyLDAP;

public class TestRut {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("ACA");
		for(int i=10000000;i<10000100;i++){
			System.out.println(i+"-"+ProxyLDAP.generaDV(i));
		}
	}

}
