package cl.laaraucana.benef.jcrontab;


import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.laaraucana.benef.services.BeneficioService;
import cl.laaraucana.benef.services.BeneficioServiceImpl;
import cl.laaraucana.benef.services.MailService;
import cl.laaraucana.benef.services.MailServiceImpl;
import cl.laaraucana.benef.utils.Configuraciones;
import cl.laaraucana.benef.utils.FormatoCorreo;
import cl.laaraucana.benef.vo.BeneficioVO;



public class CronBeneficioAniversario implements Job{
	private static Logger log = Logger.getLogger(CronBeneficioAniversario.class);
	public static void main(String[] args) throws Exception {
		
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
try {
			
			log.info("iniciando cronta Envio Comprobantes Beneficio Aniversario");
			try {
				//Se prepara envío correo 
				MailService mailService= new MailServiceImpl();
				
				//Se obtiene pendientes
				BeneficioService pendientes= new BeneficioServiceImpl();
				List<BeneficioVO> lista_pendientes= pendientes.obtenerPendientes();
				for (Iterator iterator = lista_pendientes.iterator(); iterator
						.hasNext();) {
					BeneficioVO beneficioVO = (BeneficioVO) iterator.next();
					//Se obtiene asunto correo
					String asunto= Configuraciones.getConfig("beneficio.asunto.correo");
					asunto= asunto.replaceAll("#codigo#", beneficioVO.getCodigoBeneficio());
					mailService.sendEmailClie(beneficioVO.getEmail(), asunto,
							FormatoCorreo.bodyClient(beneficioVO));
					
					boolean exito= pendientes.actualizaPendiente(beneficioVO.getCodigoBeneficio());
					
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			log.error("Problemas en ejecución proceso cronta Envio Comprobantes Beneficio Aniversario, mensaje:", e);
			
		}
		
	}
}