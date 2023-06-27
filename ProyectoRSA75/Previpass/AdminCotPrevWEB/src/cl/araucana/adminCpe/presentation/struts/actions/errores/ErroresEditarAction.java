package cl.araucana.adminCpe.presentation.struts.actions.errores;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.hibernate.Session;
import org.hibernate.Transaction;
import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.TipoCausaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.errores.ErroresEditarActionForm;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoCausaVO;
import com.bh.talon.User;

/*
 * @(#)ErroresEditarAction.java 1.4 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * 
 * @version 1.4
 */
public class ErroresEditarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ErroresEditarAction.class);

	public ErroresEditarAction()
	{
		super();
	}

	/**
	 * errores editar
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ErroresEditarActionForm actForm = (ErroresEditarActionForm) form;

		actForm.setNuevoTipo(false);

		boolean procesar = false;
		boolean bGuardar = false;

		ActionMessages am = new ActionMessages();
		Session session = null;
		Transaction tx = null;

		try
		{
			session = HibernateUtil.getSession();
			TipoCausaMgr tipoCausaMgr = new TipoCausaMgr(session);
			ActionRedirect redirect = new ActionRedirect();
			String accionInterna = request.getParameter("accionInterna");
			String tipoEdicion = request.getParameter("tipoEdicion");
			String idError = request.getParameter("id");
			String descripcion = request.getParameter("descripcion");
			String errorSeleccionado = request.getParameter("errorSeleccionado");
			String corregibles = request.getParameter("corregibles");

			if (accionInterna != null)
			{
				if (accionInterna.equals("CANCELAR"))
				{
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					return redirect;
				} else if (accionInterna.equals("GUARDAR"))
				{
					tx = session.beginTransaction();
					bGuardar = true;
					TipoCausaVO tipoCausaVO = new TipoCausaVO();
					if (!"-1".equals(idError))
					{
						List tmp = tipoCausaMgr.getErrores(Integer.parseInt(idError));
						tipoCausaVO = (TipoCausaVO) tmp.get(0);
					}
					if (descripcion != null)
						tipoCausaVO.setDescripcion(String.valueOf(descripcion));
					if (errorSeleccionado != null)
						tipoCausaVO.setError(Integer.parseInt(errorSeleccionado));
					if (corregibles != null)
						tipoCausaVO.setCorregible(Integer.parseInt(corregibles));
					if ("-1".equals(idError))
						tipoCausaMgr.save(tipoCausaVO);
					else
						tipoCausaMgr.update(tipoCausaVO);
					if (bGuardar)
					{
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "error", tipoCausaVO.getDescripcion()));
						this.saveMessages(request.getSession(), am);
					}
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					tx.commit();
					return redirect;
				} else if (accionInterna.equals("DEL_ERRORES"))
				{
					tx = session.beginTransaction();
					int idAux = Integer.parseInt(idError);
					TipoCausaVO tipoCausaVO = tipoCausaMgr.getTipoCausa(idAux);

					bGuardar = tipoCausaMgr.delete(tipoCausaVO);
					if (bGuardar)
					{
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrar", "error", tipoCausaVO.getDescripcion()));
						this.saveMessages(request.getSession(), am);
					}
					tx.commit();
					redirect = new ActionRedirect(mapping.findForward("cancelar"));

					return redirect;
				}
			}
			boolean actualiza = false;
			if (tipoEdicion != null)
			{
				if (tipoEdicion.equals("ACTUALIZA"))
				{
					actualiza = true;
				}
				if (tipoEdicion.equals("NUEVO"))
				{
					actForm.setId(-1);
					actForm.setDescripcion("");
					actForm.setError(0);
					actForm.setCorregible(1);
					return mapping.findForward("exito");
				}
			}
			if (actualiza || procesar)
			{
				int idAux = -1;
				if (idError != null)
				{
					idAux = Integer.parseInt(String.valueOf(idError));
				}
				List lista = tipoCausaMgr.getErrores(idAux);
				TipoCausaVO tipoCausaVO;
				for (Iterator it = lista.iterator(); it.hasNext();)
				{
					tipoCausaVO = (TipoCausaVO) it.next();
					if (idAux == tipoCausaVO.getId())
					{
						actForm.setId(idAux);
						actForm.setDescripcion(String.valueOf(tipoCausaVO.getDescripcion().trim()));
						actForm.setError(tipoCausaVO.getError());
						actForm.setCorregible(tipoCausaVO.getCorregible());
					}
				}
			}
			if (procesar == true)
			{
				redirect = new ActionRedirect(mapping.findForward("refresh"));
				if (tipoEdicion != null && !tipoEdicion.equals(""))
					redirect.addParameter("tipoEdicion", tipoEdicion);				
				if (idError != null && !idError.equals(""))
					redirect.addParameter("id", idError);
				return redirect;
			}
			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ErroresEditarAction.doTask()", ex);
			if (tx != null)
			{
				tx.rollback();
			}
			return mapping.findForward("error");
		}
	}
}
