package cl.araucana.adminCpe.presentation.struts.actions.parametro;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ParametroMgr;
import cl.araucana.adminCpe.presentation.struts.forms.parametro.ListaParametrosActionForm;

import com.bh.talon.User;

/*
 * @(#) ListarParametrosAction.java 1.3 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.3
 */
public class ListaParametrosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListaParametrosAction.class);

	public ListaParametrosAction()
	{
		super();
	}

	/**
	 * listar parametros
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ListaParametrosActionForm actForm = (ListaParametrosActionForm) form;
		ActionMessages am = new ActionMessages();

		Session session = null;
		try
		{
			session = HibernateUtil.getSession();
			ParametroMgr parametroMgr = new ParametroMgr(session);

			List listaNegocio = parametroMgr.getParametrosNegocio();
			if (listaNegocio.size() > 0)
				actForm.setListaNegocio(listaNegocio);

			List listaSistema = parametroMgr.getParametrosSistema();
			if (listaSistema.size() > 0)
				actForm.setListaSistema(listaSistema);

			if (request.getParameter("resultOK") != null)
			{
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardarParams"));
				this.saveMessages(request.getSession(), am);
			} else if (request.getParameterValues("result") != null)
			{
				ActionErrors ae = new ActionErrors();
				ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.guardarParams"));
				String[] lista = request.getParameterValues("result");
				for (int i = 0; i < lista.length; i++)
				{
					ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.dinamico", lista[i].toString()));
				}
				this.saveErrors(request.getSession(), ae);
			}
			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListaParametrosAction.doTask()", ex);
			return mapping.findForward("error");
		}
	}
}
