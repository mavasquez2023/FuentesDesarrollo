package cl.araucana.infcotrec.utils;

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

}
