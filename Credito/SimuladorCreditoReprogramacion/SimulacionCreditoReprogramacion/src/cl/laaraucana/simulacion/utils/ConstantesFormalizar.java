package cl.laaraucana.simulacion.utils;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.OficinaGastosSingleton;
import cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.VO.OficinaGastosNotarialSalidaVO;
import cl.laaraucana.simulacion.xml.Comuna;
import cl.laaraucana.simulacion.xml.DatosComunas;
import cl.laaraucana.simulacion.xml.Region;
import cl.laaraucana.simulacion.xml.Regiones;

public class ConstantesFormalizar {
	private static ResourceBundle resource = ResourceBundle.getBundle("simulacionWeb");

	public ConstantesFormalizar() {
	}
	//public static final ResourceBundle RES_FORMALIZAR = ResourceBundle.getBundle("cl.laaraucana.satelites.formalizar.formalizar");
	public static final String WS_UNAVAILABLE = "Web Service no disponible";
	public static final String CONTENT_TYPE_PDF = "application/pdf";
	public static final String CONTENT_TYPE_JSON = "text/json";
	public static final String NOM_AFILIADO = resource.getString("nombre.afiliado");
	public static final String NOM_PENSIONADO = resource.getString("nombre.pensionado");
	public static final String NOM_INDEPENDIENTE = resource.getString("nombre.independiente");
	public static final String NOM_DEUDOR = resource.getString("nombre.deudor");
	public static final String COD_AFILIADO = resource.getString("codigo.afiliado");
	public static final String COD_PENSIONADO = resource.getString("codigo.pensionado");
	public static final String COD_INDEPENDIENTE = resource.getString("codigo.independiente");
	public static final String COD_DEUDOR = resource.getString("codigo.deudor");
	public static final String PRODUCTOS_AFILIADO = resource.getString("productos.trabajador");
	public static final String PRODUCTOS_PENSIONADO = resource.getString("productos.pensionado");
	public static final String PRODUCTOS_DEUDOR_DIRECTO = resource.getString("productos.deudordirecto");
	public static final String PRODUCTOS_INSOLVENCIA = resource.getString("productos.insolvencia");
	public static final String PRODUCTOS_ACUERDOS_PAGO = resource.getString("productos.acuerdos.pago");
	public static final String TIPO_MONEDA_PESOS = resource.getString("tipo.moneda.pesos");
	public static final String TIPO_MONEDA_UF = resource.getString("tipo.moneda.uf");
	public static final String EDAD_MINIMA_REPRO = resource.getString("repro.edad.minima");
	public static final String EDAD_MAXIMA_REPRO = resource.getString("repro.edad.maxima");
	public static final String MENSAJE_EDAD_MINIMA_REPRO = resource.getString("repro.edad.minima.mensaje");
	public static final String MENSAJE_EDAD_MAXIMA_REPRO = resource.getString("repro.edad.maxima.mensaje");
	public static final String MENSAJE_SIN_FECHA_NAC_REPRO = resource.getString("repro.sin.fecha.nac.mensaje");
	public static final String PDF_SIMULACION_FIRMA = resource.getString("certificado.simulacion.firma");
	public static final String PDF_SIMULACION_PATH_FIRMA = resource.getString("certificado.simulacion.imgPath");
	public static final String PDF_ACUERDO_TITULO = resource.getString("certificado.acuerdo.titulo");
	public static final String PDF_ACUERDO_NOMBRE_SALIDA = resource.getString("certificado.acuerdo.nombre.salida");
	public static final String PDF_ACUERDO_JASPER= resource.getString("certificado.acuerdo.jasper");
	public static final String PDF_REPRO_TITULO = resource.getString("certificado.reprogramacion.titulo");
	public static final String PDF_REPRO_NOMBRE_SALIDA = resource.getString("certificado.reprogramacion.nombre.salida");
	public static final String PDF_REPRO_JASPER= resource.getString("certificado.reprogramacion.jasper");
	public static final String FECHA_NACIMIENTO_DEFAULT= resource.getString("fecha.nacimiento.default");
	
	//Parámetros que se cargan al iniciar la aplicación
	public static HashMap<String, String> REGIONES = null;
	public static HashMap<String, DatosComunas> COMUNAS = null;
	

	public static void main(String[] args) throws Exception {
		//getRegionesComunas();
		ImprimeUtil.imprimirHash(ConstantesFormalizar.REGIONES);
		ImprimeUtil.imprimirHash(ConstantesFormalizar.COMUNAS);
	}

}
