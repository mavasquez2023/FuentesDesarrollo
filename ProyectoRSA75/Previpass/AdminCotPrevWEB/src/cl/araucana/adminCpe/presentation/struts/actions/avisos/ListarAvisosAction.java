package cl.araucana.adminCpe.presentation.struts.actions.avisos;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.AvisosMgr;
import cl.araucana.adminCpe.presentation.struts.forms.avisos.ListarAvisosActionForm;
import cl.araucana.cp.distribuidor.hibernate.beans.AvisosVO;

import com.bh.talon.User;
/*
* @(#) ListarAvisosAction.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author vagurto
 * @author malvarez
 * 
 * @version 1.2
 */
public class ListarAvisosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListarAvisosAction.class);

	public ListarAvisosAction() {
		super();
	}
	/**
	 * listar avisos
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ListarAvisosActionForm actForm = (ListarAvisosActionForm) form;
		 
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
						
			AvisosMgr avisosMgr = new AvisosMgr(session);
			List avisos = new ArrayList();
			AvisosVO avisoVO;
			
			avisos = avisosMgr.getAvisos();
			if (avisos.size()>0) 
				avisoVO = (AvisosVO)avisos.get(0);
			else 
				avisoVO = new AvisosVO();
			
			actForm.setIdAvisos(avisoVO.getIdAvisos());
			actForm.setTitulo(avisoVO.getTitulo()!=null?avisoVO.getTitulo().trim():"");
			actForm.setEstado(String.valueOf(avisoVO.getEstado()));
			actForm.setContenido(avisoVO.getContenido()!=null?avisoVO.getContenido().trim():"");
			actForm.setLink(avisoVO.getLink()!=null?avisoVO.getLink().trim():"");
			actForm.setAncho(avisoVO.getAncho());
			actForm.setAlto(avisoVO.getAlto());
				
			tx.commit();
			return mapping.findForward("Listar");
		} catch (Exception ex) {
			logger.error("Se produjo una excepcion en ListarAviso.doTask()", ex);
			if (tx != null) {
				tx.rollback();
			}
		return mapping.findForward("error");
		}
	}
}
