package cl.araucana.wslme.integration.quartz.jobs;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.araucana.wslme.business.service.EstadisticaService;
import cl.araucana.wslme.business.service.MailService;
import cl.araucana.wslme.business.service.TiempoRespuestaService;
import cl.araucana.wslme.business.service.impl.EstadisticaServiceImpl;
import cl.araucana.wslme.business.service.impl.MailServiceImpl;
import cl.araucana.wslme.business.service.impl.TiempoRespuestaServiceImpl;
import cl.araucana.wslme.business.to.MailTO;
import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.common.util.ConfigUtil;

public class GeneraReportesJob implements Job {

	private final Logger log = Logger.getLogger(GeneraReportesJob.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Date fechaHoy = new Date();
		Integer anoMes = new Integer(sdf.format(fechaHoy));
		
		EstadisticaService es = new EstadisticaServiceImpl();
		TiempoRespuestaService tr = new TiempoRespuestaServiceImpl();
		
		try {
			File respEst = es.generaReporteEstadisticas(anoMes);
			File respTiemp = tr.generaReporteTiemposRespuesta(anoMes);
			
			log.debug("Enviado los reportes...");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			MailService conMail = new MailServiceImpl();
			MailTO mail = new MailTO();
			mail.setDe(ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.reportes.mail.de"));
			mail.setPara(ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.reportes.mail.para").split(","));
			mail.setConCopia(ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.reportes.mail.concopia").split(","));
			mail.setAsunto(ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.reportes.mail.asunto") + sdf.format(new Date()));
			mail.setMensaje(ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.reportes.mail.mensaje"));

			File [] file = {respEst, respTiemp};
			mail.setAdjuntos(file);
			conMail.enviaMail(mail);
		} catch (WSLMEException e2) {
			e2.printStackTrace();
		}
	}

	public static void main(String [] args){
		String a = "";
		for(int i = 0; i < a.split(",").length; i++){
			System.out.println(a.split(",")[i]);
		}
	}
}
