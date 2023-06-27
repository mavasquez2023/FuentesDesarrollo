package cl.araucana.adminCpe.presentation.struts.actions.estructuras;

import java.util.ArrayList;
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
import cl.araucana.adminCpe.presentation.mgr.EstructuraMgr;
import cl.araucana.adminCpe.presentation.struts.forms.estructuras.MovPersonalAfEditarActionForm;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;

import com.bh.talon.User;
/*
* @(#) MovPersonalAfEditarAction.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author malvarez
 * 
 * @version 1.1
 */
public class MovPersonalAfEditarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(MovPersonalAfEditarAction.class);

	public MovPersonalAfEditarAction() 
	{
		super();
	}
	/**
	 * movimiento personal af editar
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MovPersonalAfEditarActionForm actForm = (MovPersonalAfEditarActionForm) form;
		
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
			
			EstructuraMgr estructuraMgr = new EstructuraMgr(session);
			ActionRedirect redirect = new ActionRedirect();
			
			if(request.getParameter("accionInterna") != null){					
				if (request.getParameter("accionInterna").equals("CANCELAR")) 
				{
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					return redirect;
				} else if (request.getParameter("accionInterna").equals("GUARDAR")) 
				{						
					bGuardar = true;		
					TipoMvtoPersoAFVO tipoMvtoPersoAFVO = new TipoMvtoPersoAFVO();
					int id = -1;
					if(request.getParameter("id") != null)
						id = Integer.parseInt(request.getParameter("id"));

					actForm.setLista(new ArrayList());	
					if(estructuraMgr.existeMovPersonalAf("id", id))
					{
						List temp = estructuraMgr.getEstructuraTipoMovPersonalAf(id);
						if(temp.size()>0)
							tipoMvtoPersoAFVO  = (TipoMvtoPersoAFVO)temp.get(0);
					}
					if(request.getParameter("fechaTerminoObligatoria") != null)
						tipoMvtoPersoAFVO.setFechaTerminoObligatoria(Integer.parseInt(request.getParameter("fechaTerminoObligatoria")));
					if(request.getParameter("fechaInicioObligatoria") != null)
						tipoMvtoPersoAFVO.setFechaInicioObligatoria(Integer.parseInt(request.getParameter("fechaInicioObligatoria")));
					tipoMvtoPersoAFVO.setNombre(request.getParameter("nombre"));
					if(!estructuraMgr.existeMovPersonalAf("id", id))
						estructuraMgr.saveTipoMovPersonalAf(tipoMvtoPersoAFVO);
					else
						estructuraMgr.updateTipoMovPersonalAf(tipoMvtoPersoAFVO);

					if (bGuardar) 
					{
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Tipo de mov. personal", tipoMvtoPersoAFVO.getNombre().trim()));
						this.saveMessages(request.getSession(), am);
					}
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					tx.commit();
					return redirect;
				} else if (request.getParameter("accionInterna").equals("DEL_TIPO")) {
					bGuardar = true;
					int id = Integer.parseInt(request.getParameter("id"));
					
					List temp = estructuraMgr.getEstructuraTipoMovPersonalAf(id);
					String nombre = new String("");
					TipoMvtoPersoAFVO tipoMvtoPersoAFVO = new TipoMvtoPersoAFVO();
					if(temp.size()>0){
						tipoMvtoPersoAFVO  = (TipoMvtoPersoAFVO)temp.get(0);
						nombre = tipoMvtoPersoAFVO.getNombre().trim();
					}
					
					if(estructuraMgr.isDelTipoMovPersonal(id)){
						estructuraMgr.delTipoMovPersonalAf(id,TipoMvtoPersoAFVO.class);
						if (bGuardar) {
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrar", "Tipo de mov. personal ", nombre));
							this.saveMessages(request.getSession(), am);
						}
					} else {
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.noBorroUserDep", " el tipo de mov. personal. ", "", " existe dependencia"));
						this.saveMessages(request.getSession(), am);
					}
					tx.commit();
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					return redirect;
				} 
			}	
			boolean actualiza=false;
			if(request.getParameter("tipoEdicion") != null){
				if(request.getParameter("tipoEdicion").equals("ACTUALIZA")){
					actualiza=true;	
				}
				 if(request.getParameter("tipoEdicion").equals("NUEVO")){
					actForm.setNombre("");
					actForm.setId(-1);
					actForm.setFechaInicioObligatoria(1);
					actForm.setFechaTerminoObligatoria(1);
					return mapping.findForward("exito");
				}
			}
			if(actualiza || procesar){					
				int id=-1;
				if(request.getParameter("id") != null){
					id=Integer.parseInt(String.valueOf(request.getParameter("id")));
				}
				List lista = estructuraMgr.getEstructuraTipoMovPersonalAf(id);
				TipoMvtoPersoAFVO tipoMvtoPersoAFVO;
				for (Iterator it = lista.iterator(); it.hasNext();) {
					tipoMvtoPersoAFVO = (TipoMvtoPersoAFVO) it.next();
					if(id == tipoMvtoPersoAFVO.getId()){
						actForm.setId(id);
						actForm.setNombre(tipoMvtoPersoAFVO.getNombre().trim());
						actForm.setFechaTerminoObligatoria(tipoMvtoPersoAFVO.getFechaTerminoObligatoria());
						actForm.setFechaInicioObligatoria(tipoMvtoPersoAFVO.getFechaInicioObligatoria());
					}
				}
			}
			if(procesar == true){
				redirect = new ActionRedirect(mapping.findForward("refresh"));
				if(request.getParameter("tipoEdicion") != null){
					if(!request.getParameter("tipoEdicion").equals("")){
						redirect.addParameter("tipoEdicion", request.getParameter("tipoEdicion"));
					}
				}
				if(request.getParameter("id") != null){
					if(!request.getParameter("id").equals("")){
						redirect.addParameter("id", request.getParameter("id"));
					}
				}
				return redirect;
			} 
			tx.commit();
			return mapping.findForward("exito");
		} catch (Exception ex) {
			logger.error("Se produjo una excepcion en EdicionEstructuraMovPersonalAfAction.doTask()", ex);
			if (tx != null) {
				tx.rollback();
			}
		return mapping.findForward("error");
		}
	}
}
