package cl.araucana.adminCpe.presentation.struts.actions.conceptoTeso;

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
import cl.araucana.adminCpe.presentation.mgr.TesoreriaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.conceptoTeso.ConceptoTesoEditarActionForm;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoTesoreriaVO;
import com.bh.talon.User;
/*
* @(#) ConceptoTesoEditarAction.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author malvarez
 * 
 * @version 1.2
 */
public class ConceptoTesoEditarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ConceptoTesoEditarAction.class);
	
	public ConceptoTesoEditarAction() 
	{
		super();
	}
	/**
	 * editar concepto tesoreria 
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ConceptoTesoEditarActionForm actForm = (ConceptoTesoEditarActionForm) form;
		
		ActionMessages am = new ActionMessages(); 
		Session session = null;
		Transaction tx = null;
		
		try 
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			TesoreriaMgr tesoreriaMgr = new TesoreriaMgr(session);
			
			ActionRedirect redirect = new ActionRedirect();
								
			actForm.setAccion("");
			if(request.getParameter("accion")!=null){
				actForm.setAccion(request.getParameter("accion"));
			}
			
			int id=0;
			String descripcion="";
			
			if(request.getParameter("idSeleccionado")!=null){
				if(!request.getParameter("idSeleccionado").equals("")){
					id = Integer.parseInt(request.getParameter("idSeleccionado"));
				}
			}
			if(request.getParameter("descripcionSeleccionado")!=null){
				if(!request.getParameter("descripcionSeleccionado").equals("")){
					descripcion = String.valueOf(request.getParameter("descripcionSeleccionado")).trim();
				}
			}
			
			ConceptoTesoreriaVO conceptoTesoreriaVO = new ConceptoTesoreriaVO();
			conceptoTesoreriaVO.setId(id);
			conceptoTesoreriaVO.setDescripcion(descripcion);
			
			actForm.setId(id);
			actForm.setDescripcion(descripcion);
			
			if(request.getParameter("idActual")!=null)
				actForm.setIdActual(Integer.parseInt(request.getParameter("idActual")));
			else
				actForm.setIdActual(id);
			
			if(request.getParameter("descripcionActual")!=null)
				actForm.setDescripcionActual(String.valueOf(request.getParameter("descripcionActual")).trim());
			else
				actForm.setDescripcionActual(descripcion.trim());
			
			
			if(request.getParameter("accionInterna") != null){		
				if (request.getParameter("accionInterna").equals("CANCELAR")) {					
						redirect = new ActionRedirect(mapping.findForward("cancelar"));
						return redirect;
				} else if (request.getParameter("accionInterna").equals("GUARDAR")) {						
						
					if(actForm.getId() == actForm.getIdActual() && actForm.getIdActual() != 0)
					{
						tesoreriaMgr.update(conceptoTesoreriaVO);
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Concepto Tesorer&iacute;a",new Integer(conceptoTesoreriaVO.getId())));
						this.saveMessages(request.getSession(), am);
					} else
					{
						if(!tesoreriaMgr.existeConceptoTesoreria(conceptoTesoreriaVO.getId()))
						{
							tesoreriaMgr.save(conceptoTesoreriaVO);
							tesoreriaMgr.update(conceptoTesoreriaVO.getId(), actForm.getIdActual());
							if(actForm.getIdActual() != 0)
								tesoreriaMgr.delete(new ConceptoTesoreriaVO(actForm.getIdActual(),""));
							am.add(ActionMessages.GLOBAL_MESSAGE,	new ActionMessage("exito.guardar",	"Concepto Tesorer&iacute;a",  new Integer(conceptoTesoreriaVO.getId())));
							this.saveMessages(request.getSession(), am);
						} else 
						{
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe",	"Concepto Tesorer&iacute;a", new Integer(conceptoTesoreriaVO.getId())));
							this.saveMessages(request.getSession(), am);
							return new ActionRedirect(mapping.findForward("exito"));
						}			
					}
					tx.commit();
					return new ActionRedirect(mapping.findForward("cancelar"));
				} 
				if (request.getParameter("accionInterna").equals("DEL_CONCEPTO")) 
				{
					if(tesoreriaMgr.existeAsociacionConceptoTesoreria(conceptoTesoreriaVO.getId()))
					{
						am.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("error.noBorro",
										"Concepto Tesorer&iacute;a", new Integer(conceptoTesoreriaVO.getId())));
						this.saveMessages(request.getSession(), am);
					} else {
						tesoreriaMgr.delete(conceptoTesoreriaVO);
						am.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("exito.borrar",
										"Concepto Tesorer&iacute;a",  new Integer(conceptoTesoreriaVO.getId())));
						this.saveMessages(request.getSession(), am);
					}
					tx.commit();
					return new ActionRedirect(mapping.findForward("cancelar"));
				} 
			}
			return mapping.findForward("exito");
		} catch (Exception ex) {
			logger.error("Se produjo una excepcion en EdicionConceptoTesoreriaAction.doTask()", ex);
			if (tx != null) {
				tx.rollback();
			}
		return mapping.findForward("error");
		}
	}
}
