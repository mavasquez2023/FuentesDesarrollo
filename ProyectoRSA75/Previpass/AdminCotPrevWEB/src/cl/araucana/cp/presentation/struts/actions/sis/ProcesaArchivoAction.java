package cl.araucana.cp.presentation.struts.actions.sis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.validadorSis.bpro.ValidadorSisBusiness;
import cl.araucana.validadorSis.model.bo.dvo.ArchivoContingenciaDVO;

import com.bh.talon.User;

/**
 * Inicia carga de archivo de Contingencia<p>
 *
 * Registro de Versiones:<ul>
 * <li>03/11/2011 [jlandero - schema ltda.]: Versión Inicial</li>
 * </ul><p>
 * 
 * <b>Todos los derechos reservados - La Araucana C.C.A.F.</b><p>
 *
 */
public class ProcesaArchivoAction extends AppAction {
    
	static Logger logger = Logger.getLogger( ProcesaArchivoAction.class );

    public ActionForward doTask(User user, ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	// *** Recupera y define variables de control ***
    	HttpSession session = request.getSession(true);    	
    	ValidadorSisBusiness validadorSisDelegate = new ValidadorSisBusiness();
    	String cmd = request.getParameter("_cmd");
    	logger.debug("Comando Recibido --> " + cmd);

    	// Control del despacho
    	String target = "inicio"; // default
    	ArchivoContingenciaDVO archivoContingenciaDVO = (ArchivoContingenciaDVO)session.getAttribute("archivoContingencia");
    	
    	try{
    		validadorSisDelegate.grabaDatosArchivo(archivoContingenciaDVO);
    		request.setAttribute("mensaje", "ok");
    	}catch (Exception e){
    		System.out.println("Error al grabar archivo "+e);
    		request.setAttribute("error", "ok");
    	}
		
    	// *** Despachando ***
    	logger.debug("Despachando a --> " + target);
    	return mapping.findForward(target);
    }
    
 
}
