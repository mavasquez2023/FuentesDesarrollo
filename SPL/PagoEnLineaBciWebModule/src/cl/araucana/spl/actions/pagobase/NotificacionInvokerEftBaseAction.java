package cl.araucana.spl.actions.pagobase;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.util.HttpRequestor;

import com.bh.talon.User;

/**
 * @author malvarez
 *
 */
public abstract class NotificacionInvokerEftBaseAction extends AppAction {
	
	protected abstract String getCodigoMedio();

	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		String url = (String)req.getParameter("url");
		String mensaje = (String)req.getParameter("TX");
		
		HttpRequestor requestor = new HttpRequestor(url);
		String respuesta = null;
		String banco = getCodigoMedio();
		
		if (Constants.MEDIO_CODIGO_BSA.equalsIgnoreCase(banco)) {
			Map params = new HashMap();
			params.put("TX", mensaje);
			respuesta = requestor.doRequestAsForm(params);
		} else {
			respuesta = requestor.doRequest(mensaje);
		}
		
		PrintWriter writer = res.getWriter();
		writer.print(respuesta);
		writer.flush();
		writer.close();
		
		return null;
	}


}
