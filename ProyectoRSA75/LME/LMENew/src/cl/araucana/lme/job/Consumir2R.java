/**
 * 
 */
package cl.araucana.lme.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import cl.araucana.lme.helper.Helper;
import cl.araucana.lme.ibatis.domain.Ilfe000VO;
import cl.araucana.lme.ibatis.domain.Ilfe002VO;
import cl.araucana.lme.ibatis.domain.IlfeOpeVO;
import cl.araucana.lme.ibatis.domain.UrlBorderVO;
import cl.araucana.lme.svc.IAS400Svc_LME;
import cl.araucana.lme.svc.SvcFactory_LME;
import cl.araucana.lme.svc.exception.SvcException;
import cl.araucana.lme.util.BdConstants;
import cl.araucana.lme.util.Configuraciones;
import cl.araucana.lme.util.EndPointUtil;
import cl.araucana.lme.util.Util;
import conector.lme.ws.cliente.operador.LicenciaType;
import conector.lme.ws.cliente.operador.ServiciosMultiOperador;
import conector.vo.SalidaLMEEvenLcc;

/**
 * @author usist199
 *
 */
public class Consumir2R {
	private Logger log = Logger.getLogger(this.getClass());
	private final String TIPO_INSTITUCION = "C";
	private StringBuffer logLcc = new StringBuffer();
	private IAS400Svc_LME svc_a = null;
	//private Date dateLcc = null;
	private List lmeList = null;
	
	/**
	 * @param vo
	 */
	public List consumirEstadoLME(IlfeOpeVO vo) {
		

		//boolean dateParams = Boolean.valueOf(Configuraciones.getConfig("dateParams")).booleanValue();
		//String fechaConsulta= BdConstants.getInstance().FECHA_CONSUMO_DEV_VAL;
		//int date = !fechaConsulta.equals("") ? Integer.parseInt(fechaConsulta) : Integer.parseInt(Helper.getSdf());
		
		Date rangofecha= Util.sumDays(new Date(), -7);
		int date = Integer.parseInt(Util.getFechaAAAAMMDD(rangofecha));
		
		log.info("Parametro de fecha a consultar:" + date);

		try {
			svc_a = SvcFactory_LME.getAS400Svc_LME();
			String nombreHash = "CONSULTA";
			Map param= new HashMap();
			param.put("fechaest", new Integer(date));
			param.put("ideope", vo.getIdeOpe());
			
			//DEVOLUCIÓN
			List respuesta = svc_a.getIlfe002R_Consumo(param);
			return respuesta;
			
		} catch (Exception e) {
			e.printStackTrace();
			logError(e);
			return null;
		} catch (SvcException e) {
			logError(e);
			e.printStackTrace();
			return null;
		}

	}
	
	private void logError(Throwable e) {
		log.error(e.getClass() + "; " + e.getMessage());
		// Date now = new Date();
		// svc.insertLog(new LmeLogVO("ERROR", proceso, "",sdf.format(now),
		// shf.format(now), dataTruncation(e.getClass() + "; "+
		// e.getMessage(),500)));
	}
	
}
