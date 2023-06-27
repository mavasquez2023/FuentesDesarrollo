package cl.araucana.adminCpe.presentation.struts.actions.avisos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.AvisosMgr;
import cl.araucana.adminCpe.presentation.struts.forms.avisos.EdicionAvisosActionForm;
import cl.araucana.cp.distribuidor.hibernate.beans.AvisosVO;

import com.bh.talon.User;

/*
 * @(#) EdicionAvisosAction.java 1.4 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author vagurto
 * @author malvarez
 * 
 * @version 1.4
 */
public class EdicionAvisosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(EdicionAvisosAction.class);

	public EdicionAvisosAction()
	{
		super();
	}

	/**
	 * edicion avisos
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EdicionAvisosActionForm actForm = (EdicionAvisosActionForm) form;

		ActionMessages am = new ActionMessages();
		Session session = null;
		Transaction tx = null;

		try
		{
			session = HibernateUtil.getSession();

			AvisosMgr avisosMgr = new AvisosMgr(session);
			if (request.getParameter("operacion") != null)
			{
				if ("Editar".equals(request.getParameter("operacion")))
				{
					int idAviso = new Integer(request.getParameter("idAviso")).intValue();
					avisosMgr = new AvisosMgr(session);
					AvisosVO avisoVO;

					avisoVO = avisosMgr.getAviso(idAviso);
					actForm.setIdAvisos(avisoVO.getIdAvisos());
					actForm.setTitulo(avisoVO.getTitulo().trim());
					actForm.setEstado(String.valueOf(avisoVO.getEstado()));
					actForm.setContenido(avisoVO.getContenido().trim());
					actForm.setLink(avisoVO.getLink().trim());
					actForm.setAncho(avisoVO.getAncho());
					actForm.setAlto(avisoVO.getAlto());
					return mapping.findForward("Editar");
				}
				if ("Guardar".equals(request.getParameter("operacion")))
				{
					tx = session.beginTransaction();
					AvisosVO avisoVO = new AvisosVO();
					avisoVO.setIdAvisos(actForm.getIdAvisos());
					avisoVO.setTitulo(actForm.getTitulo());
					avisoVO.setEstado(Integer.parseInt(actForm.getEstado()));
					avisoVO.setContenido(actForm.getContenido());
					avisoVO.setLink(actForm.getLink());
					avisoVO.setAncho(actForm.getAncho());
					avisoVO.setAlto(actForm.getAlto());
					avisosMgr.guardarAviso(avisoVO);
					tx.commit();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "aviso", avisoVO.getTitulo()));
					this.saveMessages(request, am);
					return mapping.findForward("listar");
				}
			}
		} catch (Exception e)
		{
			logger.error("errror", e);
		}
		return mapping.findForward("error");
	}
}
