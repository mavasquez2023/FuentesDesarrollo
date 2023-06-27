package cl.araucana.spl.action.roles;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

public class SecureRequestProcessor extends org.apache.struts.tiles.TilesRequestProcessor
{

    /** Logger de traza aplicativa */
	private static Logger logger = Logger.getLogger(SecureRequestProcessor.class);

    
    public SecureRequestProcessor()
    {
    }

    public boolean processPreprocess(HttpServletRequest req, HttpServletResponse resp)
    {
        HttpSession session = req.getSession(true);
        HttpServletRequest request = req;
        HttpServletResponse response = resp;
        logger.debug("Iniciando ...");
        Principal user = request.getUserPrincipal();
        logger.debug("Username => " + (user != null ? user.getName() : null));
        String remoteUser = request.getRemoteUser();
        logger.debug("Remote Username => " + remoteUser);
         String finalUser = user != null ? user.getName() : remoteUser;
        if(finalUser != null)
        {
            finalUser = finalUser.trim().toUpperCase();
        }
        
        session.setAttribute("application.username", finalUser);
        
        String url = ((HttpServletRequest)request).getRequestURL().toString();
        if(finalUser == null && url.indexOf("/admin/") > 0)
        {
            try
            {
                logger.debug("Error de Seguridad: Usuario no existe");
                logger.debug("Recuperando Path de Error de Seguridad: /SecurityPageError");
                ActionMapping secureErrorActionMapping = processMapping(req, resp, "/SecurityPageError");
                logger.debug("Destino Error: " + secureErrorActionMapping.getParameter());
                doForward(secureErrorActionMapping.getParameter(), request, response);
            }
            catch(Exception e)
            {
                logger.debug("Error de Request Processor: " + e.getMessage());
                return false;
            }
            return false;
        }else{
        	
        }
        String bean = "application.userinformation";
        if(session.getAttribute(bean) == null)
        {
            logger.debug("Configuraci\363n => Genera Acceso e Info de Usuario en memoria con nombre " + bean);
            if(finalUser != null)
            	session.setAttribute(bean, new cl.araucana.spl.action.roles.UserInformation(finalUser));
        }
        logger.debug("Finaliza");
        return true;
    }

    static 
    {
        logger = Logger.getLogger(cl.araucana.common.ui.struts.SecureRequestProcessor.class);
    }
}
