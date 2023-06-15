package cl.laaraucana.satelites.certificados.utils;

import java.util.ResourceBundle;

import cl.laaraucana.satelites.Utils.Configuraciones;

public class CertificadoConst {

	public static final ResourceBundle RES_CERTIFICADOS = ResourceBundle.getBundle("cl.laaraucana.satelites.certificados.confCertificados");
	public static final int TIPO_CERT_CRED_VIGENTE = 3;
	public static final int TIPO_CERT_CRED_CANCELADO= 4;
	public static final int TIPO_CERT_AFILIACION = 5;
	public static final int TIPO_CERT_FINIQUITO = 6;
	public static final int TIPO_CERT_PREPAGO= 7;
	public static final int TIPO_CERT_CRED_VIGENTE_CUOTAS=8;
	public static final int TIPO_CERT_CRED_CANCELADO_CUOTAS= 9;
	public static final int TIPO_CERT_LIQ_DEUDA= 10;
	public static final String PERFILAMIENTO_MENU = RES_CERTIFICADOS.getString("certificado.perfilamiento.menu");
}
