package cl.laaraucana.satelites.certificados.perfilar.actions;

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
import cl.laaraucana.satelites.Utils.PerfilarSingleton;
import cl.laaraucana.satelites.dao.BitacoraDAO;
import cl.laaraucana.satelites.dao.VO.BitacoraVO;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

/**
 * @version 	1.0
 * @author
 */
public class PerfilarMenuAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	//ActionMessages errors = new ActionMessages();
	ActionForward forward = new ActionForward(); // return value

	try {
				
		HttpSession sesion = request.getSession();
		String opcion= request.getParameter("opcion");
		String url_ldap= ServiciosConst.URL_INTEG_LDAP;
		request.setAttribute("url_ldap", url_ldap);
		if(opcion==null){
			forward = mapping.findForward("success");
			request.setAttribute("opcion", PerfilarSingleton.getInstance().getPerfilamientoActivo());
			return (forward);
		}
		PerfilarSingleton.getInstance().setPerfilamientoActivo(opcion);
		request.setAttribute("opcion", opcion);
		request.setAttribute("resultado", "Grabado exitosamente! <br>Debe cerrar sesión.");
		forward = mapping.findForward("success");

	} catch (Exception e) {
	}

	return (forward);

    }
}
