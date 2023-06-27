// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 30-01-2020 18:49:49
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   BeneficiosCreadosAction.java

package cl.araucana.gestorBecas.actions.solicitudBecasIncentivos;

import cl.araucana.gestorBecas.ejb.GestorBecas;
import cl.araucana.gestorBecas.model.bo.BecaBO;
import cl.araucana.gestorBecas.model.bo.SolicitudBO;
import cl.araucana.gestorBecas.model.bo.vo.AlternativaVO;
import cl.araucana.gestorBecas.ui.WebUtils;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

public class BeneficiosCreadosAction extends Action
{

    public BeneficiosCreadosAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        HttpSession session = request.getSession(true);
        String cmd = request.getParameter("_cmd");
        logger.debug("Comando Recibido --> " + cmd);
        ResourceBundle mainCfg = ResourceBundle.getBundle("application_es");
		String mesCorreo = mainCfg.getString("label.mes.correo");
		logger.info("Mes resultado en correo --> " + mesCorreo);
		
        GestorBecas gestorBecasDelegate = WebUtils.getGestorBecasDelegate(request);
        SolicitudBO solicitudRecibidaBO = (SolicitudBO)session.getAttribute("solicitudBO");
        String target = "";
        target = "resultado";
        if(cmd.equalsIgnoreCase("grabar"))
        {
            String index = request.getParameter("indice");
            logger.debug("Indice : " + index);
            solicitudRecibidaBO.getAlternativas()[Integer.parseInt(index)].setSeleccionada(true);
            SolicitudBO solicitudGuardadaBO = gestorBecasDelegate.registrarSolicitud(solicitudRecibidaBO);
            AlternativaVO alternativaVO = solicitudGuardadaBO.getAlternativaSeleccionada();
            session.setAttribute("solicitudBO", solicitudGuardadaBO);
            session.setAttribute("alternativaVO", alternativaVO);
            try
            {
                long idBeneficiario = alternativaVO.getBecas()[0].getIdBeneficiario();
                byte byteArray[] = gestorBecasDelegate.generarPDFGanadoresBecas(idBeneficiario);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                String to = solicitudRecibidaBO.getEmail();
                String from = "portal@laaraucana.cl";
                String s = "portal";
                String s1 = "portal08";
                String host = "owa.laaraucana.cl";
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", "580");
                Session session_ = Session.getInstance(props, new Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("portal", "portal08");
                    }

                }
);
                Message message = new MimeMessage(session_);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject("Postulación Programa de Reconocimiento Educacional");
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                StringBuffer body= new StringBuffer();
                body.append("¡Ya estás postulando al Programa de Reconocimiento Educacional! Se adjunta comprobante respectivo.");
                body.append("<br><br>");
                body.append("Recuerda revisar que tu postulación contenga la información correcta a tu nivel.");
                body.append("<br><br>");
                body.append("Informaremos los resultados del programa a través de nuestro sitio web <a href=\"www.laaraucana.cl\">www.laaraucana.cl</a> a fines del mes de " + mesCorreo +".");
                body.append("<br><br>");
                body.append("Ante cualquier duda puedes contactarte con nuestro servicio de atención telefónica al 600 4228 100.");
                body.append("<br><br>");
                body.append("La Araucana.");

                //String body = "Nos es grato informarle que adjuntamos en el presente correo el Comprobante de Postulaci\363n a Becas La Araucana. <br><br>Informaremos a trav\351s de nuestro sitio web <a href=\"www.laaraucana.cl\">www.laaraucana.cl</a> los resultados de su postulaci\363n. <br><br>Ante cualquier duda puede contactarse con nuestro servicio de atenci\363n telef\363nica al 600 4228 100. <br><br>Con La Araucana apostamos juntos por la Educaci\363n. <br><br>Atentamente, <br><br>La Araucana CCAF";
                messageBodyPart.setContent(body.toString(), "text/html");
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                messageBodyPart = new MimeBodyPart();
                PdfReader reader = new PdfReader(byteArray);
                reader.close();
                reader.selectPages("1");
                PdfStamper stamper = new PdfStamper(reader, baos);
                stamper.close();
                reader.close();
                byte bytes[] = baos.toByteArray();
                ByteArrayDataSource source = new ByteArrayDataSource(bytes, "application/pdf");
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName("Comprobante Becas La Araucana.pdf");
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
                Transport.send(message);
                System.out.println("ok");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        } else
        if(cmd.equalsIgnoreCase("volver"))
            target = "volver";
        logger.debug("Despachando a --> " + target);
        return mapping.findForward(target);
    }

    static Logger logger;

    static 
    {
        logger = Logger.getLogger(cl.araucana.gestorBecas.actions.solicitudBecasIncentivos.BeneficiosCreadosAction.class);
    }
}
