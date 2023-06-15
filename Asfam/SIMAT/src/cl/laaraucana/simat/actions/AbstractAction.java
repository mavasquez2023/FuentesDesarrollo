package cl.laaraucana.simat.actions;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class AbstractAction extends DispatchAction {
	public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		return super.execute(arg0, arg1, arg2, arg3);
	}

	protected Method getMethod(String arg0) throws NoSuchMethodException {
		return super.getMethod(arg0);
	}
}
