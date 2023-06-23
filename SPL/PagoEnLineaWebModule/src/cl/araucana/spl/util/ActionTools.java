package cl.araucana.spl.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;

public class ActionTools {
	
	public static void addMessage(HttpServletRequest request, ActionMessage amessage) {
		ActionMessages messages = getMessages(request);
		messages.add(ActionMessages.GLOBAL_MESSAGE, amessage);
		saveMessages(request.getSession(), messages);
	}
	public static void addMessage(HttpServletRequest request, String property, ActionMessage amessage) {
		ActionMessages messages = getMessages(request);
		messages.add(property, amessage);
		saveMessages(request.getSession(), messages);
	}
	public static void addError(HttpServletRequest request, ActionMessage error) {
		ActionMessages errors = getErrors(request);
		errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		saveErrors(request.getSession(), errors);
	}
	public static void addError(HttpServletRequest request, String property, ActionMessage error) {
		ActionMessages errors = getErrors(request);
		errors.add(property, error);
		saveErrors(request.getSession(), errors);
	}
	public static void addErrors(HttpServletRequest request, ActionMessages errors) {
		ActionMessages all = getErrors(request);
		all.add(errors);
		saveErrors(request.getSession(), all);
	}
	public static void addMessages(HttpServletRequest request, ActionMessages messages) {
		ActionMessages all = getMessages(request);
		all.add(messages);
		saveMessages(request.getSession(), all);
	}
	
	/*
    public static Object getBean(HttpServlet servlet, String name) {
    	ServletContext c = servlet.getServletContext();
        ApplicationContext ctx = 
            WebApplicationContextUtils.getRequiredWebApplicationContext(c);
        Object bean = ctx.getBean(name);
        return bean;
    }*/

    public static ActionMessages getMessages(HttpServletRequest request) {
		return getFromKey(request, Globals.MESSAGE_KEY);
	}

    public static ActionMessages getErrors(HttpServletRequest request) {
		return getFromKey(request, Globals.ERROR_KEY);
	}


    private static ActionMessages getFromKey(HttpServletRequest request, String key) {
		ActionMessages messages = null;
		HttpSession session = request.getSession();

		if (request.getAttribute(key) != null) {
			messages = (ActionMessages) request.getAttribute(key);
		} else if (session.getAttribute(key) != null) {
			messages = (ActionMessages) session.getAttribute(key);
		} else {
			messages = new ActionMessages();
		}

		return messages;
	}


	/**
	 * Permite pasar parametros para los &lt;forward&gt; descritos en struts-config.xml que usan el atributo <em>redirect="true"</em>
	 * 
	 * @param mapping  El objecto ActionMapping asociado a la accion.
	 * @param forwardName  El nombre del actionforward definido en struts-config
	 * @param param El nombre del parametro que se requiere pasar
	 * @param value El valor del parametro que se requiere pasar
	 * @return  Un objeto ActionForward que debe ser retornado por la Accion, y que tiene agregado el parametro al request.
	 */
	public static ActionForward redirect(ActionMapping mapping, String forwardName, String param, Object value) {
		return redirect(mapping, forwardName, new String[] { param }, new Object[] { value });
	}


	
	/**
	 * Permite pasar parametros para los &lt;forward&gt; descritos en struts-config.xml que usan el atributo <em>redirect="true"</em>
	 * 
	 * @param mapping  El objecto ActionMapping asociado a la accion.
	 * @param forwardName  El nombre del actionforward definido en struts-config
	 * @param params Los nombres de los parametros que se requiere pasar
	 * @param values Los valores de los parametros que se requiere pasar
	 * @return  Un objeto ActionForward que debe ser retornado por la Accion, y que tiene agregado el parametro al request.
	 */
	public static ActionForward redirect(ActionMapping mapping, String forwardName, String[] params, Object[] values) {
		ActionRedirect redirect = new ActionRedirect(mapping.findForward(forwardName));
		for (int i = 0; params != null && i < params.length; i++) {
			redirect.addParameter(params[i], values[i]);	
		}
		return redirect;
	}
	
	
	/**
	 * Copia feliz de las fuentes de struts...
	 *  
	 * @see org.apache.struts.action.Action#saveErrors(javax.servlet.http.HttpSession, org.apache.struts.action.ActionMessages)
	 */
	private static void saveErrors(HttpSession session, ActionMessages errors) {
		// Remove the error attribute if none are required
		if ((errors == null) || errors.isEmpty()) {
			session.removeAttribute(Globals.ERROR_KEY);
			return;
		}
		// Save the errors we need
		session.setAttribute(Globals.ERROR_KEY, errors);
	}
	
    /**
	 * Copia feliz de las fuentes de struts...
     * 
     * @see org.apache.struts.action.Action#saveMessages(javax.servlet.http.HttpSession, org.apache.struts.action.ActionMessages)
     */
    private static void saveMessages(HttpSession session, ActionMessages messages) {
    	// Remove any messages attribute if none are required
    	if ((messages == null) || messages.isEmpty()) {
        	session.removeAttribute(Globals.MESSAGE_KEY);
            return;
        }
        // Save the messages we need
        session.setAttribute(Globals.MESSAGE_KEY, messages);
    }
}
