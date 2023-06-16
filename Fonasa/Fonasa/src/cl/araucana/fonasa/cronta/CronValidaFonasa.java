package cl.araucana.fonasa.cronta;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.araucana.fonasa.business.impl.ConsultaBD;



@SuppressWarnings("serial")
public class CronValidaFonasa extends HttpServlet implements Job{
	private static Logger log = Logger.getLogger(CronValidaFonasa.class);
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			
			log.info("iniciando cronta Validación Fonasa");
			ConsultaBD envioValidacionFonasa= new ConsultaBD();
			envioValidacionFonasa.validaBD();

		} catch (Exception e) {
			log.error("Problemas en ejecución proceso Validacion Fonasa, mensaje:", e);
			
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			execute(null);
		} catch (JobExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			execute(null);
		} catch (JobExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}