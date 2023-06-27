/*
 * Created on 16-10-2011
 *
 */
package cl.araucana.lme.liq.job;

import org.apache.log4j.Logger;

import cl.araucana.lme.liq.jcrontab.CronLiquidacionLME;
import cl.araucana.lme.liq.util.EndPointUtilLiq;

//import com.microsystem.lme.helper.LoggerHelper;

/**
 * @author microsystem
 *
 */
public class LmeInicio {

	//0 no continuar, 1= continuar
	private static int iniciado = 0;
	//	private LoggerHelper logger = new LoggerHelper();
	private static Logger log = Logger.getLogger(LmeInicio.class);

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	
	public static void IniciarProcesoCompleto() {
		//log.info("el valor de iniciado es ================================== "+LmeInicio.iniciado);
		try {
			CronLiquidacionLME.main(null);
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public static int getIniciado() throws Exception {
		boolean estado= EndPointUtilLiq.getInstance().isEstado();
		if(estado){
			return 1;
		}else{
			return 0;
		}
	}

	public static void setIniciado(int iniciado) throws Exception {
		if(iniciado==1){
			EndPointUtilLiq.getInstance().setEstado(true);
		}else{
			EndPointUtilLiq.getInstance().setEstado(false);
		}
	}
}
