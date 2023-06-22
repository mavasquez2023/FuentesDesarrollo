package cl.araucana.infcottra.utils;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean;
import cl.araucana.autoconsulta.vo.DatosValidacionVO;
import cl.araucana.autoconsulta.vo.ValorValidableVO;
import cl.araucana.common.BusinessException;


public class CertificadoUtils {
	
	private static Logger logger = Logger.getLogger(CertificadoUtils.class);

	public static String guardarCertificado(String nombre, String rut, Collection<ValorValidableVO> datos, int tipoCredito) throws BusinessException, Exception{

		logger.debug("Ingreso a cuerdarCertificado");
		ServicesAutoconsultaBean bean = new ServicesAutoconsultaBean();
		bean.ejbCreate();
		
		//Buscar folio vacio en el archivo at01f1
		long codValidacion=0;
		do {
			codValidacion = bean.generarIdCertificado();
		} while (bean.getDatosValidacionCertificado(codValidacion)!=null);
		
		DatosValidacionVO datosCertificado = new DatosValidacionVO();
		datosCertificado.setId(codValidacion);
		datosCertificado.setTipo(tipoCredito);
		datosCertificado.setFecha(new Date());
		datosCertificado.setFullNombre(nombre);
		datosCertificado.setFullRut(rut);
		datosCertificado.setRut( Long.valueOf( (rut.split("-")[0]) ) );
		
		datosCertificado.setListaValores(datos);

		logger.debug("Seteo de datos OK");
	
		bean.registrarDatosValidacion(datosCertificado);
		logger.debug("Pasó registro de certificado");
		
		return String.valueOf(codValidacion);
	}
	
	public static String toTituloCase(String texto){
		String palabras[]= texto.split(" ");
		StringBuffer salida= new StringBuffer();
		for (int i = 0; i < palabras.length; i++) {
			String first= palabras[i].trim().substring(0, 1).toUpperCase();
			salida.append(first);
			if(palabras[i].trim().length()>1){
				String second= palabras[i].trim().substring(1);
				salida.append(second);
			}
			salida.append(" ");
		}
		return salida.toString();
	}
}
