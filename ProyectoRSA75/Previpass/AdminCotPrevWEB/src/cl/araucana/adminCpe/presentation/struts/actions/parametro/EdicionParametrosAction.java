package cl.araucana.adminCpe.presentation.struts.actions.parametro;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.NodosProcesamientoMgr;
import cl.araucana.adminCpe.presentation.mgr.ParametroMgr;
import cl.araucana.adminCpe.presentation.struts.forms.parametro.EdicionParametrosActionForm;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;

import com.bh.talon.User;

/*
 * @(#) EdicionParametrosAction.java 1.4 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.4
 */
public class EdicionParametrosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(EdicionParametrosAction.class);

	public EdicionParametrosAction()
	{
		super();
	}

	/**
	 * edicion parametros
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EdicionParametrosActionForm actForm = (EdicionParametrosActionForm) form;
		Session session = null;
		Transaction tx = null;

		try
		{
			session = HibernateUtil.getSession();
			ParametroMgr parametroMgr = new ParametroMgr(session);

			if (request.getParameter("accionInterna") != null)
			{
				if (request.getParameter("accionInterna").equals("CANCELAR"))
					return new ActionRedirect(mapping.findForward("cancelar"));
				else if (request.getParameter("accionInterna").equals("GUARDAR"))
				{
					ParametroVO parametroVO;
					tx = session.beginTransaction();

					int largoDatos = 0;
					if (request.getParameter("largoDatos") != null)
						largoDatos = Integer.parseInt(request.getParameter("largoDatos"));

					for (int a = 0; a <= largoDatos; a++)
					{
						boolean modificar = false;
						if (request.getParameter("modificar" + a) != null)
							modificar = String.valueOf(request.getParameter("modificar" + a)).equals("1") ? true : false;
						if (modificar)
						{
							parametroVO = new ParametroVO();
							if (request.getParameter("id" + a) != null)
							{
								parametroVO = parametroMgr.getParametro(Integer.parseInt(request.getParameter("id" + a)));
								if (request.getParameter("descripcion" + a) != null)
									parametroVO.setDescripcion(String.valueOf(request.getParameter("descripcion" + a)));
								else
									parametroVO.setDescripcion("");

								if (request.getParameter("valor" + a) != null)
								{
									if (request.getParameter("tipoValor" + a).equals("decimal"))
										parametroVO.setValor(String.valueOf(request.getParameter("valor" + a).replace(',', '.')));
									else
										parametroVO.setValor(String.valueOf(request.getParameter("valor" + a)));
								} else
									parametroVO.setValor("");
								parametroMgr.update(parametroVO);
							}
						}
					}
					tx.commit();
					logger.info("actualizando parametros");
					NodosProcesamientoMgr npMgr = new NodosProcesamientoMgr(session, usuario.getLogin());
					List result = npMgr.actualizaNodos();
					logger.info("result:" + result.size() + "::");
					boolean actWEB = parametroMgr.cargaConstantes();
					logger.info("actualizacion constantes:" + actWEB + "::");
					if (!actWEB)
						result.add("Constantes parametricas no han podido actualizarse.");
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("cancelar"));
					if (result.size() == 0)
					{// Parametros_guardados correctamente
						redirect.addParameter("resultOK", "ok");
					} else
					{
						for (Iterator it = result.iterator(); it.hasNext();)
							redirect.addParameter("result", it.next());
					}
					return redirect;
				}
			}
			if (request.getParameter("tipoParametro") != null)
			{
				if (request.getParameter("tipoParametro").equals("NEGOCIO"))
				{
					List listaParametros = parametroMgr.getParametrosNegocio();
					if (listaParametros.size() > 0)
						actForm.setListaParametros(listaParametros);
				}
				if (request.getParameter("tipoParametro").equals("SISTEMA"))
				{
					List listaParametros = parametroMgr.getParametrosSistema();
					if (listaParametros.size() > 0)
						actForm.setListaParametros(listaParametros);
				}
			}

			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en EdicionParametroAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}
}
