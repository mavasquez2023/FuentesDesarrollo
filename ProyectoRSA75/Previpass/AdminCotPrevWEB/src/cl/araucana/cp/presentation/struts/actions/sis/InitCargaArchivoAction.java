package cl.araucana.cp.presentation.struts.actions.sis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.struts.forms.CargaArchivoForm;
import cl.araucana.validadorSis.bpro.ValidadorSisBusiness;
import cl.araucana.validadorSis.model.bo.dvo.ArchivoContingenciaDVO;
import cl.araucana.validadorSis.model.vo.ArchivoCargaVO;

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
public class InitCargaArchivoAction extends AppAction {
    
	static Logger logger = Logger.getLogger( InitCargaArchivoAction.class );

    public ActionForward doTask(User user, ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	// *** Recupera y define variables de control ***
    	HttpSession session = request.getSession(true);    	
    	ValidadorSisBusiness validadorSisDelegate = new ValidadorSisBusiness();
    	String cmd = request.getParameter("_cmd");
    	logger.debug("Comando Recibido --> " + cmd);

    	// Control del despacho
    	String target = "inicio"; // default
    	
    	if (cmd == null){
    		session.setAttribute("listadoAfp", validadorSisDelegate.getListaAfp());
    		session.removeAttribute("archivoContingencia");
    	}
    	else if (cmd.equalsIgnoreCase("resultado")){
    		CargaArchivoForm cargaArchivoForm = (CargaArchivoForm)actionForm;
    		ArchivoCargaVO archivoCargaVO = new ArchivoCargaVO();
    		// analiza si hay archivo ...
			FormFile file = (FormFile)cargaArchivoForm.getFileContingencia();
			String codigo = cargaArchivoForm.getCodigo();
			logger.debug("File asociado == "  + file);
			if ( file != null && !"".equalsIgnoreCase(file.toString()) ) {
				// procesando file				
				archivoCargaVO.setNombreArchivo(file.getFileName());
				archivoCargaVO.setData(file.getFileData());	
				
				ArchivoContingenciaDVO archivoContingenciaDVO = new ArchivoContingenciaDVO();
				
				try{
					archivoContingenciaDVO.setCodigoAfp(codigo);
					archivoContingenciaDVO = validadorSisDelegate.validaInfoArchivo(archivoCargaVO, archivoContingenciaDVO);
					archivoContingenciaDVO.setCodigoAfp(codigo);
					
					if (archivoContingenciaDVO.getDatosIncorrectos() != null || archivoContingenciaDVO.getDatosIncorrectos().length == 0 && !archivoContingenciaDVO.isBorraDatos()){
						request.setAttribute("mensaje", "ok");	
					}else{
						session.setAttribute("archivoContingencia", archivoContingenciaDVO);
						target = "resultadoNOK";											
					}
					
				}catch (Exception e){
					System.out.println(e.getMessage());
					request.setAttribute("error", "ok");
				}
								
			}else{
				request.setAttribute("mensaje.error", "ok");	
			}
				
			
    	}

    	// *** Despachando ***
    	ActionMessages am = new ActionMessages();
    	logger.debug("Despachando a --> " + target);
    	this.saveMessages(request, am);
    	return mapping.findForward(target);
    }
    
 
}
