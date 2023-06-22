package cse.external.client.dao.impl;

import javax.xml.namespace.QName;
import javax.xml.rpc.soap.SOAPFaultException;

import org.w3c.dom.Element;

import com.ibm.ws.webservices.engine.WebServicesFault;

public class ErrorHelper {

	public static String getErrorMessage(Exception exception) {
		// Primero determinamos si tenemos un SOAPFault como causa
		// o tenemos un RemoteException no mas
		if (exception.getCause() instanceof SOAPFaultException) {
			SOAPFaultException fault = (SOAPFaultException) exception.getCause();
			if (null != fault) { // Error devuelto como SOAPFaultException
				String code = fault.getFaultCode().getLocalPart();
				String messg = fault.getMessage();

				if (code.equals("8006") || code.equals("92") || code.equals("93"))
					return "Error en capa principal de protocolo Gene (" + code + ")";
				else if (code.equals("0") && messg.equals("ERROR AL SOLICITAR CONEXION"))
					return "Error por problemas en las comunicaciones (" + code + ")";
				else if (code.equals("05"))
					return "Problemas en privilegios de usuario para consultar Web Service ("
							+ code + ")";
				else if (code.equals("11"))
					return "Falta especificar RUT en consulta (" + code + ")";
				else if (code.equals("12"))
					return "RUT viene en formato no numérico (" + code + ")";
				else if (code.equals("13"))
					return "DV invalido (" + code + ")";
				else if (code.equals("48"))
					return "Problemas en password de usuario. Se debe cambiar(" + code + ")";
				else if (code.equals("53"))
					return "Error en password de usuario (" + code + ")";
				else if (code.equals("52")) // &&
											// messg.equals("Consulta no operativa en estos momentos. Por favor reintente mas tarde."))
					return "Código Erróneo a nivel aplicación del protocolo GENE."
							+ " Usuario bloqueado, suspendido o no habilitado (" + code + ")";
				else if (code.equals("503"))
					return "Error por operación con Servidor Webservice. Se aconseja reintentar ("
							+ code + ")";
				else if (code.equals("72"))
					return "Usuario  impedido de realizar consultas desde cualquier IP (" + code
							+ ")";
				else if (code.equals("73"))
					return "Usuario con IP invalida para realizar consulta (" + code + ")";
				else if (code.equals("74"))
					return "Usuario con IP valida pero fuera de horario (" + code + ")";
				else if (code.equals("77") || code.equals("85") || code.equals("86")
						|| code.equals("88") || code.equals("89") || code.equals("90")
						|| code.equals("98"))
					return "Error interno Equifax de BD (" + code + ")";
				else if (code.equals("99"))
					return "Error interno de aplicación Equifax (" + code + ")";
				else
					return "Error desconocido para acceso al Web Service Platinum (" + code + ")";
			}
		} else { // Error devuelto como WebServicesFault
			if (exception instanceof com.ibm.ws.webservices.engine.WebServicesFault){
				WebServicesFault wsf = (WebServicesFault)exception;
				String faultString = wsf.getFaultString();
				//LOS DOS CASOS CONOCIDOS HASTA AHORA SON 
				//1. FALTA EL ARCHIVO TRUST
				//2. PROBLEMAS DEL CERTIFICADO EN SI MISMO
				if (faultString.lastIndexOf("ChannelException")!=-1){
					return "Problemas con el archivo trust.jks. " + wsf.getFaultString();
				}
				
				if (faultString.lastIndexOf("SSLException")!=-1){
					return "Problemas con el certificado SSL. " + wsf.getFaultString();
				}
				return "Error severo de comunicación con el Web Service. " + wsf.getFaultString();
			}
				
		}
		return "Error desconocido para acceso al Web Service Platinum: " + exception.getMessage();
	}	
}
