package cl.araucana.wsafiliado.util;

import java.util.ArrayList;
import java.util.Properties;

import cl.araucana.tupla2.exception.FatalException;
import cl.araucana.tupla2.exception.Tupla2Exception;
import cl.araucana.wsafiliado.util.XMLSiagf;
import cl.paperless.siagf.ws.AutenticacionPortTypeProxy;
import cl.paperless.siagf.ws.ConsultaCausantePortTypeProxy;

public class ClienteSIAGF {
	private final int MAX_INTENTOS = 3;
	private static String currentToken;
	private static Properties properties;

	static {
		try {
			properties = new LoadPropertiesFile().load("etc/wssiagf.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList consultaCausante(String rut) throws Tupla2Exception, FatalException {
		XmlParse xml = new XmlParse();
		String respuestaConsultaXml;
		ArrayList responseList = null;
		boolean exito = false;

		for (int i = 0; i < MAX_INTENTOS && !exito; i++) {
			if (currentToken == null) {
				AutenticacionPortTypeProxy autenticador = new AutenticacionPortTypeProxy();
				autenticador.setEndpoint(properties.getProperty("webService.Autenticacion"));
				String token;
				//Intenta MAX_INTENTOS la autenticacion; si no; envia error
				int intentosAutenticacion = 0;
				while (currentToken == null) {
					intentosAutenticacion++;
					try {
						token = autenticador.login(new Integer(properties.getProperty("credential.id").trim()), properties.getProperty("credential.userName"), properties.getProperty("credential.password"));
						currentToken = xml.parsearXMLautentica(token);
					} catch (Exception e) {
						System.err.println(Thread.currentThread().getName() + "|Error Intentando Autenticar" + e.getMessage() + " . Intento Nro." + intentosAutenticacion);
						if (intentosAutenticacion > MAX_INTENTOS)
							throw new Tupla2Exception("Error al autenticar. Es Reprocesable" + e.getMessage());
						else
							try {
								Thread.sleep(30000);
							} catch (InterruptedException e1) {
								e.printStackTrace();
							}
					}
				}
			}

			ConsultaCausantePortTypeProxy consulta = new ConsultaCausantePortTypeProxy();
			consulta.setEndpoint(properties.getProperty("webService.ConsultaCausante"));
			try {
				respuestaConsultaXml = consulta.consultaCausante(currentToken, rut);
				
				responseList = xml.parseaXmlTupla(respuestaConsultaXml);
				exito = true;
			} catch (Tupla2Exception e) {
				//e.printStackTrace();
				//Si hay error en autenticacion, regenerar token
				if ("-6".equals(e.getAppCode())) {
					currentToken = null;
				}
				if ("-15".equals(e.getAppCode()) || "-14".equals(e.getAppCode()) || "-9".equals(e.getAppCode()) || "-2".equals(e.getAppCode())) {
					throw new FatalException(e.getMessage() + " RUT:" + rut, e.getAppCode());
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Tupla2Exception("Error al consultar. Es Reprocesable" + e.getMessage());
			}
		}
		return responseList;
	}
}
