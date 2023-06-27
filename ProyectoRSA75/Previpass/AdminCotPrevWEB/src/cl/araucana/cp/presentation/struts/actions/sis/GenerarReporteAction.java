package cl.araucana.cp.presentation.struts.actions.sis;

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

import cl.araucana.adminCpe.presentation.base.AppAction;
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
public class GenerarReporteAction extends AppAction {
    
	static Logger logger = Logger.getLogger( InitCargaArchivoAction.class );

    public ActionForward doTask(User user, ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	// *** Recupera y define variables de control ***
    	HttpSession session = request.getSession(true);    	
    	String cmd = request.getParameter("_cmd");
    	logger.debug("Comando Recibido --> " + cmd);
    	
    	ArchivoVO archivoVO = (ArchivoVO)session.getAttribute("archivoReporte");		
		
		session.setAttribute(Globals.LOCALE_KEY, new Locale("es", "ES"));
		ServletOutputStream out = response.getOutputStream();
        response.setHeader("Expires", "0");
    	response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
    	response.setHeader("Pragma", "public");		    	
    	// setting the content type
    	response.setContentType("application/csv");
    	response.addHeader("Content-Disposition", "attachment; filename=" + "reporte"+archivoVO.getCodigoAfp()+".csv");
    	// the contentlength is needed for MSIE!!!
    	response.setContentLength(archivoVO.getData().length);
    	// write ByteArrayOutputStream to the ServletOutputStream
    	//baos.write(datos);
    	//baos.writeTo(out);
    	out.write(archivoVO.getData()); //funciona con error
    	out.flush();
    	return null;
    }
}
