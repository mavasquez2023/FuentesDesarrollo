package cl.araucana.adminCpe.presentation.struts.actions.banco;

import java.util.ArrayList;
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
import cl.araucana.adminCpe.presentation.mgr.BancoMgr;
import cl.araucana.adminCpe.presentation.struts.forms.banco.EdicionEstadoBancoActionForm;
import cl.araucana.cp.distribuidor.hibernate.beans.BancoVO;

import com.bh.talon.User;
/*
* @(#) EdicionEstadoAction.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * @author malvarez
 * 
 * @version 1.2
 */
public class EdicionEstadoBancoAction extends AppAction
{
	private static Logger logger = Logger.getLogger(EdicionEstadoBancoAction.class);

	private BancoMgr bancoMgr;
	
	public EdicionEstadoBancoAction() {
		super();
	}
	/**
	 * edicion estado banco
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EdicionEstadoBancoActionForm actForm = (EdicionEstadoBancoActionForm) form;
		
		actForm.setNuevoTipo(false);
		
		boolean procesar = false;
		boolean bGuardar = false;
		
		ActionMessages am = new ActionMessages(); 
		Session session = null;
		Transaction tx = null;
		
		try 
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			this.bancoMgr = new BancoMgr(session);
			
			ActionRedirect redirect = new ActionRedirect();
			
			if(request.getParameter("accionInterna") != null)
			{					
				if (request.getParameter("accionInterna").equals("CANCELAR")) 
				{					
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					return redirect;
				} else if (request.getParameter("accionInterna").equals("GUARDAR")) 
				{						
					bGuardar = true;		
					actForm.setLista(new ArrayList());		
					int totalFila = 0;
					if(request.getParameter("totalFila") != null)
					{
						totalFila = Integer.parseInt(request.getParameter("totalFila"));
						int idBanco=0;
						int estado=0;
						BancoVO bancoVO;
						for (int a = 0; a <= totalFila; a++)
						{
							idBanco=Integer.parseInt(request.getParameter("idBanco" + a));
							estado=Integer.parseInt(request.getParameter("estado" + a));
							List tmp = this.bancoMgr.getBanco(idBanco);
							bancoVO = (BancoVO)tmp.get(0);
							bancoVO.setEstado(estado);
							this.bancoMgr.update(bancoVO);	
						}
						if (bGuardar) 
						{
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Estado ", ""));
							this.saveMessages(request.getSession(), am);
						}
						redirect = new ActionRedirect(mapping.findForward("cancelar"));
						tx.commit();
						return redirect;
						}
					procesar = true;						
				}
			}
			
			boolean actualiza=false;
			if(request.getParameter("tipoEdicion") != null && request.getParameter("tipoEdicion").equals("ACTUALIZA"))
				actualiza=true;	

			if(actualiza || procesar)
			{
				List lista = this.bancoMgr.getBancos(false);
				actForm.setLista(lista);
			}
			if(procesar == true)
			{
				redirect = new ActionRedirect(mapping.findForward("refresh"));
				if(request.getParameter("tipoEdicion") != null && !request.getParameter("tipoEdicion").equals(""))
					redirect.addParameter("tipoEdicion", request.getParameter("tipoEdicion"));
				return redirect;
			} 
			return mapping.findForward("exito");
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en EdicionEstadoBancoAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}	
}
