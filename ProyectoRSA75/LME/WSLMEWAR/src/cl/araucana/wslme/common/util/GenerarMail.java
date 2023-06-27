/**
 * 
 */
package cl.araucana.wslme.common.util;

import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import cl.araucana.wslme.business.service.MailService;
import cl.araucana.wslme.business.service.impl.MailServiceImpl;
import cl.araucana.wslme.business.to.MailTO;
import cl.araucana.wslme.common.exception.WSLMEException;

/**
 * @author usist199
 *
 */
public class GenerarMail {

	private final Logger log = Logger.getLogger(GenerarMail.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	
	public void generarMail(String detalle) {
				
		try {

			log.debug("Enviado mail por error...");
			MailService conMail = new MailServiceImpl();
			MailTO mail = new MailTO();
			mail.setDe(ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.error.mail.de"));
			mail.setPara(ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.error.mail.para").split(";"));
			mail.setConCopia(ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.error.mail.concopia").split(";"));
			mail.setAsunto(ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.error.mail.asunto"));
			String mensaje= ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.error.mail.mensaje");
			mensaje= mensaje.replaceAll("#mensaje#", detalle);
			mensaje= mensaje.replaceAll("<BR>", "\n");
			mail.setMensaje(mensaje);

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
