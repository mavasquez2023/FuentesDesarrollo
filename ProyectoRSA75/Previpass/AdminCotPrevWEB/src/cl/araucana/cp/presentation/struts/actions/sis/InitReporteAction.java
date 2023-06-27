		package cl.araucana.cp.presentation.struts.actions.sis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.struts.forms.AfpForm;
import cl.araucana.validadorSis.bpro.ValidadorSisBusiness;
import cl.araucana.validadorSis.model.bo.dvo.AfpDVO;
import cl.araucana.validadorSis.model.vo.ArchivoVO;

import com.bh.talon.User;

/**
* Genera reporte para AFP<p>
*
* Registro de Versiones:<ul>
* <li>27/10/2011 [jlandero - schema ltda.]: Versión Inicial</li>
* </ul><p>
* 
* <b>Todos los derechos reservados - La Araucana C.C.A.F.</b><p>
*
*/
public class InitReporteAction extends AppAction {
    
	static Logger logger = Logger.getLogger( InitCargaArchivoAction.class );

    public ActionForward doTask(User user, ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	// *** Recupera y define variables de control ***
    	HttpSession session = request.getSession(true);
    	ValidadorSisBusiness validadorSisDelegate = new ValidadorSisBusiness();
    	    	
    	String cmd = request.getParameter("_cmd");
    	logger.debug("Comando Recibido --> " + cmd);

    	// Control del despacho
    	String target = ""; // default
    	target = "inicio"; // default
    	if (cmd == null){
    		
    		//WebUtils.setMessage(request, "message.generaArchivo");
    		session.setAttribute("listadoAfp", validadorSisDelegate.getListaAfp());
    	}
    	else if (cmd.equalsIgnoreCase("resultado")){
    		
    		AfpForm afpForm = (AfpForm)actionForm;
    		
    		AfpDVO[] listado = (AfpDVO[])session.getAttribute("listadoAfp");
    		String afpName = "";
    		
    		for (int i =0 ; i < listado.length ; i++){
    			AfpDVO afpDVO = listado[i];
    			
    			if (afpDVO.getCodigo().equals(afpForm.getCodigo()))
    				afpName = afpDVO.getDescripcion();
    			
    		}

    		String codigo = afpForm.getCodigo();
    		String periodo = afpForm.getPeriodo();
    		
    		//asepulveda 2013-05-29
    		//Desde la interfaz del usuario se pide por parte de araucana solicitarlo como yyyyMM
    		//Pero internamente el periodo se maneja como MMyyyy
    		
    		String periodoConsulta = periodo.substring(4, 6) + periodo.substring(0, 4);
    		
    		byte[] archivo = validadorSisDelegate.getArchivoReporte(codigo, periodoConsulta);    		
    		
    		if (archivo == null || archivo.length == 0){
    			//WebUtils.setAlert(request, "message.noArchivo");
    			target= "inicio";
				request.setAttribute("mensaje", "ok");
    		}else{
    			target = "resultado"; // default
    			ArchivoVO archivoVO = new ArchivoVO();
        		
        		archivoVO.setData(archivo);
        		archivoVO.setCodigoAfp(afpName.trim());
        		
        		session.setAttribute("archivoReporte", archivoVO);
    		}
    		
    	}

    	// *** Despachando ***
    	logger.debug("Despachando a --> " + target);
    	return mapping.findForward(target);
    }
}
