package cl.liv.mail.impl;

import java.util.ArrayList;
import java.util.HashMap;

import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.liv.comun.utiles.UtilesComunes;

public class MailImpl {

	public static void enviarMail(final String idConfiguracion, final String from, final String to, final String asunto, final String descripcion){
		
		final String asuntoAux = UtilesComunes.reemplazarVariables(asunto);
		final String descripcionAux = UtilesComunes.reemplazarVariables(descripcion);
		
		new Thread(
				new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub

						try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						UtilLogWorkflow.debug("\n\n************************************");
						UtilLogWorkflow.debug("**************MAIL*******************");
						UtilLogWorkflow.debug("Sending mail...["+idConfiguracion+"]");
						UtilLogWorkflow.debug("from ["+from+"]");
						UtilLogWorkflow.debug("to ["+to+"]");
						UtilLogWorkflow.debug("asunto ["+asuntoAux+"]");
						UtilLogWorkflow.debug("descripcion ["+descripcionAux+"]");
						
						UtilLogWorkflow.debug("Enviando...");
						UtilLogWorkflow.debug("**************FIN MAIL***************");
						UtilLogWorkflow.debug("*************************************\n\n");
						
						ArrayList destinatarios = new ArrayList();
						
						String[] destinatariosArr = to.split(";");
						
						for(int i=0; i<destinatariosArr.length; i++){
							if(destinatariosArr[i] != null && destinatariosArr[i].length()>3){
								HashMap mail = new HashMap();
								mail.put("email",destinatariosArr[i]);
								UtilLogWorkflow.debug("agregando mail..."+destinatariosArr[i]);
								destinatarios.add(mail);
							}
						}
						
						
						ArrayList attachments = new ArrayList();
						ArrayList imagenes = new ArrayList();
						HashMap att = new HashMap();
						UtilLogWorkflow.debug("send..");
						EnviarEmail.enviar(destinatarios, asuntoAux, descripcionAux, attachments,imagenes,"",false);
						UtilLogWorkflow.debug("ok...");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			).start();
		
		
	}

	
	public static void enviarMail(final String idConfiguracion, final String from, final String to, final String asunto, final String descripcion, final ArrayList attachments, final ArrayList imagenes, final boolean eliminarAttachments){
		 
		final String asuntoAux = UtilesComunes.reemplazarVariables(asunto);
		final String descripcionAux = UtilesComunes.reemplazarVariables(descripcion);
		
		new Thread(
				new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub

						try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						UtilLogWorkflow.debug("\n\n************************************");
						UtilLogWorkflow.debug("**************MAIL*******************");
						UtilLogWorkflow.debug("Sending mail...["+idConfiguracion+"]");
						UtilLogWorkflow.debug("from ["+from+"]");
						UtilLogWorkflow.debug("to ["+to+"]");
						UtilLogWorkflow.debug("asunto ["+asuntoAux+"]");
						UtilLogWorkflow.debug("descripcion ["+descripcionAux+"]");
						
						UtilLogWorkflow.debug("Enviando...");
						UtilLogWorkflow.debug("**************FIN MAIL***************");
						UtilLogWorkflow.debug("*************************************\n\n");
						
						ArrayList destinatarios = new ArrayList();
						
						String[] destinatariosArr = to.split(";");
						
						for(int i=0; i<destinatariosArr.length; i++){
							if(destinatariosArr[i] != null && destinatariosArr[i].length()>3){
								HashMap mail = new HashMap();
								mail.put("email",destinatariosArr[i]);
								UtilLogWorkflow.debug("agregando mail..."+destinatariosArr[i]);
								destinatarios.add(mail);
							}
						}
						
						UtilLogWorkflow.debug("send..");
						EnviarEmail.enviar(destinatarios, asuntoAux, descripcionAux, attachments,imagenes,"", eliminarAttachments);
						UtilLogWorkflow.debug("ok...");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
				}
			).start();
		
		
	}


}
