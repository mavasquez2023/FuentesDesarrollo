package cl.laaraucana.satelites.main.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cl.laaraucana.satelites.main.VO.UsuarioVO;
import cl.laaraucana.satelites.main.dao.UsuarioDAO;
import cl.laaraucana.satelites.main.forms.LoginForm;

/**
 * @version 1.0
 * @author
 */
public class LoginActionView extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward forward = new ActionForward(); // return value
		LoginForm loginForm = (LoginForm) form;

		try {
			String mobile = (String) ((request.getAttribute("esMobile")== null)?"":request.getAttribute("esMobile"));
			forward = mapping.findForward("success"+mobile);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return (forward);
	}
}
