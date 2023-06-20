package cl.laaraucana.test.controller;

import java.io.File;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.laaraucana.test.services.FTPService;
import cl.laaraucana.test.services.MailService;
import cl.laaraucana.test.util.Configuraciones;
import cl.laaraucana.test.vo.FtpVO;
import cl.laaraucana.test.vo.MailVO;




@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);
	
	
	@Autowired
	private FTPService ftpService;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String initFTP(ModelMap model, HttpServletRequest request) {
		String server= (String)request.getSession().getAttribute("server");
		Integer puerto=(Integer)request.getSession().getAttribute("puerto");
		String usuario=(String)request.getSession().getAttribute("usuario");
		String clave=(String)request.getSession().getAttribute("clave");
		String carpeta=(String)request.getSession().getAttribute("carpeta");
		Integer timeout=(Integer)request.getSession().getAttribute("timeout");
		if(server==null){
			server= Configuraciones.getConfig("mandato.ftp.server");
			puerto= Integer.parseInt(Configuraciones.getConfig("mandato.ftp.puerto"));
			usuario= Configuraciones.getConfig("mandato.ftp.usuario");
			clave= Configuraciones.getConfig("mandato.ftp.clave");
			carpeta= Configuraciones.getConfig("mandato.ftp.carpeta");
			timeout= Integer.parseInt(Configuraciones.getConfig("mandato.ftp.timeout"));
			
			request.getSession().setAttribute("server", server);
			request.getSession().setAttribute("puerto", puerto);
			request.getSession().setAttribute("usuario", usuario);
			request.getSession().setAttribute("clave", clave);
			request.getSession().setAttribute("carpeta", carpeta);
			request.getSession().setAttribute("timeout", timeout);
		}
		
		return "main";

	}
	
	@RequestMapping(value = { "/ftp.do" }, method = RequestMethod.POST)
	public String ftp(ModelMap model, @ModelAttribute FtpVO form, HttpServletRequest request, HttpServletResponse response) {
		String retorno=null;
		String salida="";
		List<FtpVO> resultados=null;
		FtpVO ftpVO=null;
		try {
			
			String server= form.getServer();
			int puerto= form.getPuerto();
			String usuario= form.getUsuario();
			String clave= form.getClave();
			String carpeta= form.getCarpeta();
			Integer timeout= form.getTimeout();
			String nombreArchivo= form.getArchivo().getOriginalFilename();
			
			resultados= (List<FtpVO>)request.getSession().getAttribute("resultados");
			if(resultados==null){
				resultados= new ArrayList<FtpVO>();
			}
			ftpVO= new FtpVO();
			ftpVO.setServer(server);
			ftpVO.setPuerto(puerto);
			ftpVO.setUsuario(usuario);
			ftpVO.setClave(clave);
			ftpVO.setCarpeta(carpeta);
			ftpVO.setTimeout(timeout);
			ftpVO.setNombreArchivo(nombreArchivo);
			
			
			
			request.getSession().setAttribute("server", server);
			request.getSession().setAttribute("puerto", puerto);
			request.getSession().setAttribute("usuario", usuario);
			request.getSession().setAttribute("clave", clave);
			request.getSession().setAttribute("carpeta", carpeta);
			request.getSession().setAttribute("timeout", timeout);
			
			String carpeta_temporal= Configuraciones.getConfig("mandato.carpeta.temp");
			File file_to_send= new File(carpeta_temporal + nombreArchivo);
			FileUtils.copyInputStreamToFile(form.getArchivo().getInputStream(), file_to_send );
			
			logger.info("Conectando a ftp");
			ftpService.connectToFTP(ftpVO);
			logger.info("Enviando archivo a ftp");
			ftpService.putFileToFTP(file_to_send, ftpVO.getCarpeta(), ftpVO.getNombreArchivo());
			logger.info("Desconectando a ftp");
			ftpService.disconnectFTP();
			salida="OK;Subido";

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error Ftp, mensaje ", e);
			salida= " Error;" +  e.getMessage();
		}
		String[] estobs= salida.split(";");
		ftpVO.setEstado(estobs[0]);
		ftpVO.setObservaciones(estobs[1]);
		ftpVO.setHora(getFecha());
		resultados.add(ftpVO);
		request.getSession().setAttribute("resultados", resultados);
		return "redirect:/init.do";
	}
	
	public static String getFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String date = sdf.format(new Date());
		return date;
	}
	
	@RequestMapping(value = { "/initMail.do" }, method = RequestMethod.GET)
	public String initMail(ModelMap model, HttpServletRequest request) {
		String mailsession= (String)request.getSession().getAttribute("mailsession");
		String subject= (String)request.getSession().getAttribute("subject");
		String body= (String)request.getSession().getAttribute("body");
		
		if(mailsession==null){
			mailsession= Configuraciones.getConfig("mail.session");
			subject= Configuraciones.getConfig("mail.subject");
			body= Configuraciones.getConfig("mail.body");
			
			request.getSession().setAttribute("mailsession", mailsession);
			request.getSession().setAttribute("subject", subject);
			request.getSession().setAttribute("body", body);
		}
		
		return "mail";

	}
	
	@RequestMapping(value = { "/mail.do" }, method = RequestMethod.POST)
	public String mail(ModelMap model, @ModelAttribute MailVO form, HttpServletRequest request, HttpServletResponse response) {
		String retorno=null;
		String salida="";

		try {
			String mailsessionProp= Configuraciones.getConfig("mail.session");;
			
			String mailsession= form.getMailsession();
			String subject=form.getSubject();
			String body=form.getBody();
			String email=form.getEmail();
			
			request.getSession().setAttribute("email", email);
			
			if(!mailsessionProp.trim().equals(mailsession.trim())){
				mailService.setSession(mailsession);
			}
			
			mailService.sendEmail(email, subject, body);
			logger.info("Mail enviado");
			salida= "Mail enviado";
			

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error Ftp, mensaje ", e);
			salida= " Error;" +  e.getMessage();
		}
		
		request.getSession().setAttribute("resultados", salida);
		return "redirect:/initMail.do";
	}
	
	}
