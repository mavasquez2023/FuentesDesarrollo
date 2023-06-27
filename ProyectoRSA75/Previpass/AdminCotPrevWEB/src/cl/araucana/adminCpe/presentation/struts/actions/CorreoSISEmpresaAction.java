package cl.araucana.adminCpe.presentation.struts.actions;

import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.CotizanteMgr;
import cl.araucana.adminCpe.presentation.struts.forms.AfpForm;
import cl.araucana.validadorSis.bpro.ValidadorSisBusiness;
import cl.araucana.validadorSis.model.bo.dvo.AfpDVO;

import com.bh.talon.User;


public class CorreoSISEmpresaAction extends AppAction
{
	private static Logger logger = Logger.getLogger(CorreoSISEmpresaAction.class);
	public static final String FORWARD = "exito";
	
	public CorreoSISEmpresaAction() {
		super();
	}

	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
//		 *** Recupera y define variables de control ***
    	HttpSession session = request.getSession(true);
    	
    	Session sessionCPE  = HibernateUtil.getSession();
    	ValidadorSisBusiness validadorSisDelegate = new ValidadorSisBusiness();
    	    	
    	String accion = request.getParameter("_cmd");
    	request.removeAttribute("mensajeOK");
    	    	
    	logger.info("--> Comando Recibido --> " + accion);
    	logger.info("--> Iniciamos Correo SIS Empresa");

    	// Control del despacho
    	String target = ""; // default
    	target = "inicio"; // default
    	if (accion == null){

    		session.setAttribute("listadoAfp", validadorSisDelegate.getListaAfp());
    	}
    	else if (accion.equalsIgnoreCase("EnviarMail")){
    		logger.info("--> Entramos a EnviarMail");
    		AfpForm afpForm = (AfpForm)actionForm;

    		String codigo = afpForm.getCodigo();
    		String periodo = afpForm.getPeriodo();
    		
    		logger.info("--> Valores seleccionados codigo : "+codigo +" periodo: "+periodo);
    		//String periodoConsulta = periodo.substring(4, 6) + periodo.substring(0, 4);  		
    		AfpDVO[] listado = (AfpDVO[])session.getAttribute("listadoAfp");
    		String afpName = "";
    		
    		for (int i =0 ; i < listado.length ; i++){
    			AfpDVO afpDVO = listado[i];
    			
    			if (afpDVO.getCodigo().equals(afpForm.getCodigo()))
    				afpName = afpDVO.getDescripcion();
    			
    		}
    		CotizanteMgr cotizanteMgr = new CotizanteMgr(sessionCPE);
    		
    		byte[] bs = cotizanteMgr.getInconsistenciasSIS(periodo, codigo, true,sessionCPE,afpName);
    		
    		target= "inicio";
    		
    		if(bs != null){
    			request.setAttribute("mensajeOK", "ok");
    		}else{
				request.setAttribute("mensajeNO", "ok");
			}

			//*** Despachando ***
	    	logger.debug("Despachando a --> " + target);
	    	return mapping.findForward(target);
    	
    	}else if (accion.equalsIgnoreCase("GeneraArchivo")){
    		AfpForm afpForm = (AfpForm)actionForm;

    		String codigo = afpForm.getCodigo();
    		String periodo = afpForm.getPeriodo();
    		
    		//String periodoConsulta = periodo.substring(4, 6) + periodo.substring(0, 4);  		
    		
    		AfpDVO[] listado = (AfpDVO[])session.getAttribute("listadoAfp");
    		String afpName = "";
    		
    		for (int i =0 ; i < listado.length ; i++){
    			AfpDVO afpDVO = listado[i];
    			
    			if (afpDVO.getCodigo().equals(afpForm.getCodigo()))
    				afpName = afpDVO.getDescripcion();
    			
    		}
    		
    		CotizanteMgr cotizanteMgr = new CotizanteMgr(sessionCPE);
    		
    		byte[] bs= cotizanteMgr.getInconsistenciasSIS(periodo, codigo, false,sessionCPE,"");
    		
    		if(bs != null){ 
	    		session.setAttribute(Globals.LOCALE_KEY, new Locale("es", "ES"));
	    		ServletOutputStream out = response.getOutputStream();
	            response.setHeader("Expires", "0");
	        	response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	        	response.setHeader("Pragma", "public");		    	
	        	// setting the content type
	        	response.setContentType("application/csv");
	        	response.addHeader("Content-Disposition", "attachment; filename=" + "reporte_"+afpName.trim() +"_"+periodo+".csv");
	        	// the contentlength is needed for MSIE!!!
	        	response.setContentLength(bs.length);
	        	// write ByteArrayOutputStream to the ServletOutputStream
	        	//baos.write(datos);
	        	//baos.writeTo(out);
	        	out.write(bs); //funciona con error
	        	out.flush();
	
	        	return null;
    		}else{
    			target= "inicio";
    			request.setAttribute("mensajeNO", "ok");
    			//*** Despachando ***
    	    	logger.debug("Despachando a --> " + target);
    	    	return mapping.findForward(target);
    		}
    	}
    	
    	// *** Despachando ***
    	logger.debug("Despachando a --> " + target);
    	return mapping.findForward(target);
    }
}