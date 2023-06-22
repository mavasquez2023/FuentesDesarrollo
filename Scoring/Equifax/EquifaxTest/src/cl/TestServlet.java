package cl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumImplProxy;
import cl.equifax.wse.gru01.platinum.PLATINUMOutput;

public class TestServlet extends HttpServlet {
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.setProperty("javax.net.ssl.keyStore", "C:/truststore/keys.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "araucana");
		System.setProperty("user.timezone", "AGT");
		System.setProperty("javax.net.ssl.trustStore", "C:/truststore/trust.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "metricarts");
		
		  System.setProperty("javax.net.ssl.keyStoreType" ,"jks");
		  //System.setProperty("javax.net.ssl.keyStoreAlias" ,"");
		  System.setProperty("javax.net.ssl.trustStoreType" ,"jks");
		  //System.setProperty("javax.net.ssl.trustStoreAlias" ,"atidenet");
		  System.setProperty("javax.net.debug","true");
		  System.setProperty("http.nonProxyHosts" ,"<local>");
		  System.setProperty("https.nonProxyHosts" ,"<local>");
		  //System.setProperty("user.dir" ,"C:\\j2sdk1.4.2_08\\jre\\javaws");
		  System.setProperty("java.protocol.handler.pkgs", "javax.net.ssl"); 

		
		PlatinumImplProxy myPlatinumImplProxy = new PlatinumImplProxy();
		myPlatinumImplProxy.setEndpoint("https://www.dicom.cl/gru.wse01.solinforut/services/PlatinumService");
		
		PLATINUMOutput salida = myPlatinumImplProxy.getInforme("0104239609", "", "N", "A", "0", "0", "LA ARAUCANA-MATRIZ", "ARAUCA");
		
		System.out.println(salida.getIdentificacionPersona().getNombre());
		System.out.println(salida.getIdentificacionPersona().getNacionalidad());
	}
}