package cl.araucana.adminCpe.presentation.struts.actions.estructuras;

import java.util.ArrayList;
import java.util.Iterator;
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
import org.apache.struts.action.ActionRedirect;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.EstructuraMgr;
import cl.araucana.adminCpe.presentation.struts.forms.estructuras.MovPersonalEditarActionForm;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMovimientoPersonalVO;
import com.bh.talon.User;
/*
* @(#) MovPersonalEditarAction.java 1.1 10/05/2009
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
public class MovPersonalEditarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(MovPersonalEditarAction.class);

	public MovPersonalEditarAction() 
	{
		super();
	}
	/**
	 * movimiento personal editar
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MovPersonalEditarActionForm actForm = (MovPersonalEditarActionForm) form;
		
		actForm.setNuevoTipo(false);
		
		boolean procesar = false;
		boolean bGuardar = false;
		
		ActionMessages am = new ActionMessages(); 
		ActionErrors ae=new ActionErrors();
		Session session = null;
		Transaction tx = null;
		
		try 
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			EstructuraMgr estructuraMgr = new EstructuraMgr(session);
			
			ActionRedirect redirect = new ActionRedirect();
			
			if(request.getParameter("accionInterna") != null){					
				if (request.getParameter("accionInterna").equals("CANCELAR")) {					
						redirect = new ActionRedirect(mapping.findForward("cancelar"));
						return redirect;
				} else if (request.getParameter("accionInterna").equals("GUARDAR")) {
					bGuardar = true;		
					TipoMovimientoPersonalVO tipoMovimientoPersonalVO = new TipoMovimientoPersonalVO();
					int id = -1;
					if(request.getParameter("id") != null)
						id = Integer.parseInt(request.getParameter("id"));
					tipoMovimientoPersonalVO.setId(id);
					actForm.setLista(new ArrayList());	
					
					if(request.getParameter("fechaTerminoObligatoria") != null)
						tipoMovimientoPersonalVO.setFechaTerminoObligatoria(Integer.parseInt(request.getParameter("fechaTerminoObligatoria")));

					if(request.getParameter("fechaInicoObligatoria") != null)
						tipoMovimientoPersonalVO.setFechaInicoObligatoria(Integer.parseInt(request.getParameter("fechaInicoObligatoria")));

					
					tipoMovimientoPersonalVO.setNombre(request.getParameter("nombre"));
					if(id == -1)
					{
						if(estructuraMgr.cantidadMovPersonal()<10)
						{
							estructuraMgr.saveTipoMovPersonal(tipoMovimientoPersonalVO);
							bGuardar=true;
						}else
							bGuardar=false;
					}else
						estructuraMgr.updateTipoMovPersonal(tipoMovimientoPersonalVO);

					if (bGuardar)
					{
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Tipo de mov. personal", tipoMovimientoPersonalVO.getNombre().trim()));
						this.saveMessages(request.getSession(), am);
					}else
					{
						ae.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.tipoMovPersonal"));
						saveErrors(request.getSession(),ae);
					}
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					tx.commit();
					return redirect;					
				} else if (request.getParameter("accionInterna").equals("DEL_TIPO")) {
					bGuardar = true;
					int id = Integer.parseInt(request.getParameter("id"));
					
					List lista = estructuraMgr.getEstructuraTipoMovPersonal(id);
					TipoMovimientoPersonalVO tipoMovimientoPersonalVO;
					String nombre = new String("");
					for (Iterator it = lista.iterator(); it.hasNext();) {
						tipoMovimientoPersonalVO = (TipoMovimientoPersonalVO) it.next();
						if(id == tipoMovimientoPersonalVO.getId()){
							nombre = tipoMovimientoPersonalVO.getNombre().trim();
						}
					}					
					
					estructuraMgr.delTipoMovPersonal(id,TipoMovimientoPersonalVO.class);
					if (bGuardar) {
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrar", "Tipo de mov. personal ", nombre));
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
					actForm.setFechaTerminoObligatoria(1);
					return mapping.findForward("exito");
				}
			}
			if(actualiza || procesar){					
				int id=-1;
				if(request.getParameter("id") != null){
					id=Integer.parseInt(String.valueOf(request.getParameter("id")));
				}
				List lista = estructuraMgr.getEstructuraTipoMovPersonal(id);
				TipoMovimientoPersonalVO tipoMovimientoPersonalVO;
				for (Iterator it = lista.iterator(); it.hasNext();) {
					tipoMovimientoPersonalVO = (TipoMovimientoPersonalVO) it.next();
					if(id == tipoMovimientoPersonalVO.getId()){
						actForm.setId(id);
						actForm.setNombre(tipoMovimientoPersonalVO.getNombre().trim());
						actForm.setFechaTerminoObligatoria(tipoMovimientoPersonalVO.getFechaTerminoObligatoria());
						actForm.setFechaInicoObligatoria(tipoMovimientoPersonalVO.getFechaInicoObligatoria());
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
			logger.error("Se produjo una excepcion en EdicionEstructuraMovPersonalAction.doTask()", ex);
			if (tx != null) {
				tx.rollback();
			}
		return mapping.findForward("error");
		}
	}
}
