/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author IBM Software Factory
 *
 */
@Service
public class DescripcionCodigo {
	
	private static Map<String, String> estados= new HashMap<String, String>();
	private static Map<String, String> estadospago= new HashMap<String, String>();
	private static Map<String, String> convenios= new HashMap<String, String>();
	private static Map<String, String> productos= new HashMap<String, String>();
	private static Map<String, String> beneficios= new HashMap<String, String>();
	
	
	/**
	 * @return the beneficio
	 */
	public static String getBeneficio(String codigo) {
		String beneficio=beneficios.get(codigo);
		return beneficio==null?"":beneficio;
	}
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.rendicionpagonomina.services.DescripcionCodigo#getEstado(java.lang.String)
	 */
	public static String getEstado(String codigo) {
		String estado= estados.get(codigo);
		
		return estado==null?"":estado;
	}
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.rendicionpagonomina.services.DescripcionCodigo#getEstadoPago(java.lang.String)
	 */
	public static String getEstadoPago(String codigo) {
		String estadopago= estadospago.get(codigo);
		return estadospago==null?"":estadopago;
	}
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.rendicionpagonomina.services.DescripcionCodigo#getConvenio(java.lang.String)
	 */
	public static String getConvenio(String codigo) {
		String convenio=convenios.get(codigo);
		return convenio==null?"":convenio;
	}

	/* (non-Javadoc)
	 * @see cl.laaraucana.rendicionpagonomina.services.DescripcionCodigo#getProducto(java.lang.String)
	 */
	public static String getProducto(String codigo) {
		// TODO Auto-generated method stub
		String producto= productos.get(codigo);
		return producto==null?"":producto;
	}

	/**
	 * @param estados the estados to set
	 */
	public static void setEstados(Map<String, String> estados) {
		DescripcionCodigo.estados = estados;
	}
	
	/**
	 * @param estados the estadospago to set
	 */
	public static void setEstadosPago(Map<String, String> estadospago) {
		DescripcionCodigo.estadospago = estadospago;
	}
	
	/**
	 * @param convenios the convenios to set
	 */
	public static void setConvenios(Map<String, String> convenios) {
		DescripcionCodigo.convenios = convenios;
	}

	/**
	 * @param productos the productos to set
	 */
	public static void setProductos(Map<String, String> productos) {
		DescripcionCodigo.productos = productos;
	}

	/**
	 * @param beneficios the beneficios to set
	 */
	public static void setBeneficios(Map<String, String> beneficios) {
		DescripcionCodigo.beneficios = beneficios;
	}
	
}
