package cl.laaraucana.satelites.certificados.bitacora.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.dao.BitacoraDAO;
import cl.laaraucana.satelites.dao.VO.BitacoraVO;

/**
 * @version 	1.0
 * @author
 */
public class ConsultaBitacoraAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	//ActionMessages errors = new ActionMessages();
	ActionForward forward = new ActionForward(); // return value

	try {
				
		HttpSession sesion = request.getSession();
		UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("datosUsuario");
		if(usuario == null){
			request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
			request.setAttribute("message", Constants.SESION_EXPIRED);
			return mapping.findForward("customError");
		}
		String rutConsulta = usuario.getRut()+"-"+usuario.getDv();
		BitacoraDAO bitaDAO= new BitacoraDAO();
		List<BitacoraVO> resultado= bitaDAO.consultaBitacora(rutConsulta);
		request.setAttribute("consultaBitacora", resultado);
		forward = mapping.findForward("success");

	} catch (Exception e) {
	}

	return (forward);

    }
}
