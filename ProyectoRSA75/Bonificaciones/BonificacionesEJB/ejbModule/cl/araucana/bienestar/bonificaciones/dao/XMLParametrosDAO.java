package cl.araucana.bienestar.bonificaciones.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import cl.araucana.bienestar.bonificaciones.model.Parametro;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;

import com.schema.util.XmlUtils;
/**
 * @author asepulveda
 *
 */
public class XMLParametrosDAO implements ParametrosDAO {
	Logger logger = Logger.getLogger(XMLParametrosDAO.class);

	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	
	public ArrayList getParametrosSaldoDeudaTotal() throws Exception,BusinessException {

		boolean exists=false;
		ArrayList retorno = new ArrayList(); 
		XmlUtils xu = new XmlUtils(AppConfig.getInstance().settingsFileName);
	
		exists=xu.stepToNode("/application-settings/tesoreria/param/bienestar/saldo-deuda-total/convenio");
	
		 if (exists) {
			 do {
				Parametro parametro = new Parametro();
				logger.debug("Codigo: "+ xu.getNodeValue("codigo"));
				logger.debug("concepto: "+ xu.getNodeValue("concepto"));
				logger.debug("areaNegocio: " + xu.getNodeValue("area-negocio"));
				parametro.setTipo(xu.getNodeValue("codigo"));
				parametro.setCodigo(xu.getNodeValue("concepto"));
				parametro.setDescripcion(xu.getNodeValue("area-negocio"));
				/*if(codigoPadre!=null)
					parametro.setCodigoPadre(codigoPadre);*/
				retorno.add(parametro);

			} while (xu.nextNode());

		 }
		logger.debug("Retornaron: "+ retorno.size());
		return retorno;	
	}
		
	/**
	 * Retorna una lista que contiene:
	 * Tipo, codigo y descripción de un parametro utilizado en la aplicación
	 * @param int tipo
	 * @param String codigoPadre
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getParametros(int tipo, String codigoPadre) throws Exception,BusinessException {

	boolean exists=false;
	ArrayList retorno = new ArrayList(); 
	XmlUtils xu = new XmlUtils(AppConfig.getInstance().settingsFileName);
	
	switch (tipo) {
		case Parametro.TIPO_CUENTA_CONTABLE:
			exists=xu.stepToNode("/application-settings/contabilidad/cuentas/cuenta");
			break;
		case Parametro.TIPO_CONCEPTO_TESORERIA_BIENESTAR_CONVENIOS_INGRESOS:
			exists=xu.stepToNode("/application-settings/tesoreria/param/bienestar/conceptos-convenios/ingresos/concepto");
			break;
		case Parametro.TIPO_CONCEPTO_TESORERIA_BIENESTAR_CONVENIOS_EGRESOS:
			exists=xu.stepToNode("/application-settings/tesoreria/param/bienestar/conceptos-convenios/egresos/concepto");
			break;			
		case Parametro.TIPO_CONCEPTO_TESORERIA_BIENESTAR_SALUD_INGRESOS:
			exists=xu.stepToNode("/application-settings/tesoreria/param/bienestar/conceptos-salud/ingresos/concepto");
			break;
		case Parametro.TIPO_CONCEPTO_TESORERIA_BIENESTAR_SALUD_EGRESOS:
			exists=xu.stepToNode("/application-settings/tesoreria/param/bienestar/conceptos-salud/egresos/concepto");
			break;						
		case Parametro.TIPO_AREA_TESORERIA:
			exists=xu.stepToNode("/application-settings/tesoreria/param/bienestar/areas-negocios/area");
			break;			
		case Parametro.TIPO_SALDO_DEUDA_TOTAL:
			exists=xu.stepToNode("/application-settings/tesoreria/param/bienestar/saldo-deuda-total");
			break;
	}

	 if (exists) {
		 do {
		 	Parametro parametro = new Parametro();
			logger.debug("Tipo: "+ xu.getNodeValue("tipo"));
			logger.debug("Codigo: "+ xu.getNodeValue("codigo"));
			logger.debug("Descripción: " + xu.getNodeValue("descripcion"));
			parametro.setTipo(xu.getNodeValue("tipo"));
			parametro.setCodigo(xu.getNodeValue("codigo"));
			parametro.setDescripcion(xu.getNodeValue("codigo")+" - "+xu.getNodeValue("descripcion"));
			if(codigoPadre!=null)
				parametro.setCodigoPadre(codigoPadre);
			retorno.add(parametro);

	 	} while (xu.nextNode());

	 }
	logger.debug("Retornaron: "+ retorno.size());
	return retorno;
	}
		

}
