package cl.araucana.adminCpe.presentation.struts.actions.entidad;

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
import cl.araucana.adminCpe.presentation.mgr.EntidadesMgr;
import cl.araucana.adminCpe.presentation.struts.forms.entidad.EdicionEntidadesRegImpActionForm;
import cl.araucana.cp.distribuidor.hibernate.beans.RegImpositivoVO;

import com.bh.talon.User;
/*
* @(#) EdicionEntidadesRegImpAction.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * 
 * @version 1.1
 */
public class EdicionEntidadesRegImpAction extends AppAction
{
	private static Logger logger = Logger.getLogger(EdicionEntidadesRegImpAction.class);

	public EdicionEntidadesRegImpAction() 
	{
		super();
	}
	/**
	 * edicion entidad reg imp
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EdicionEntidadesRegImpActionForm actForm = (EdicionEntidadesRegImpActionForm) form;
		
		actForm.setNuevaEntidad(false);
		actForm.setLista(null);

		actForm.setOrigen(request.getParameter("origen"));
		actForm.setOrigenAfp(request.getParameter("origenAfp"));
		actForm.setIdEntidadExCajaSeleccionada(Integer.parseInt(request.getParameter("idEntidadExCajaSeleccionada")));
				
		boolean actualiza=false;
		boolean volverError=false;
		
		ActionMessages am = new ActionMessages(); 
		Session session = null;
		Transaction tx = null;
		try 
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			EntidadesMgr entidadesMgr = new EntidadesMgr(session);
			
			ActionRedirect redirect = new ActionRedirect();
			
			if(request.getParameter("codigoEntidadAntiguo")!=null)
				if(!request.getParameter("codigoEntidadAntiguo").equals(""))
					actForm.setCodigoEntidadAntiguo(Integer.parseInt(request.getParameter("codigoEntidadAntiguo")));
				else
					actForm.setCodigoEntidadAntiguo(Integer.parseInt(request.getParameter("codigoEntidad")));

			if(request.getParameter("accionInterna") != null)
			{					
				if (request.getParameter("accionInterna").equals("CANCELAR")) 
				{					
						redirect = new ActionRedirect(mapping.findForward("cancelar"));
						redirect.addParameter("origen", request.getParameter("origen"));
						redirect.addParameter("origenAfp", request.getParameter("origenAfp"));
						redirect.addParameter("idEntidad", request.getParameter("idEntidadExCajaSeleccionada"));
						return redirect;
				} else if (request.getParameter("accionInterna").equals("GUARDAR")) {//GUARDA LA ENTIDAD
					RegImpositivoVO regimen = new RegImpositivoVO();
					
					int idEntidadExCajaSeleccionada=-1;
					int codigoEntidad=-1;
					String descripcion = "";
					float tasaPension=0;
					if(request.getParameter("idEntidadExCajaSeleccionada") != null){
						idEntidadExCajaSeleccionada = Integer.parseInt(request.getParameter("idEntidadExCajaSeleccionada"));
					}					
					if(request.getParameter("codigoEntidad") != null){
						codigoEntidad = Integer.parseInt(request.getParameter("codigoEntidad"));
					}
					if(request.getParameter("descripcion") != null){
						descripcion = request.getParameter("descripcion");
					}
					if(request.getParameter("tasaPension") != null){
						String tasaTmp = request.getParameter("tasaPension").replace(',','.');
						tasaPension = Float.parseFloat(tasaTmp);
					}
					regimen.setIdEntExCaja(idEntidadExCajaSeleccionada);
					regimen.setIdRegImpositivo(codigoEntidad);
					regimen.setDescripcion(descripcion);
					regimen.setTasaPension(tasaPension);
					if(entidadesMgr.getExiste(RegImpositivoVO.class, idEntidadExCajaSeleccionada, "idEntExCaja"
																, codigoEntidad, "idRegImpositivo" )){
						if(actForm.getCodigoEntidadAntiguo() != codigoEntidad){
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "un Código de entidad", ""));
							this.saveMessages(request, am);
							volverError=true;
						} else {
							entidadesMgr.guardaEntsRegimenImpositivo(regimen);
						}
					} else {
						entidadesMgr.borraEntRegimenImpositivo(idEntidadExCajaSeleccionada, actForm.getCodigoEntidadAntiguo());
						entidadesMgr.nuevaEntsRegimenImpositivo(regimen);
					}
					if(!volverError){
						redirect = new ActionRedirect(mapping.findForward("cancelar"));
						redirect.addParameter("origen", request.getParameter("origen"));
						redirect.addParameter("origenAfp", request.getParameter("origenAfp"));
						redirect.addParameter("idEntidad", request.getParameter("idEntidadExCajaSeleccionada"));
						tx.commit();
						return redirect;
					}
				} else if (request.getParameter("accionInterna").equals("DEL_ENTIDAD")) {//BORRA LA ENTIDAD
					int codigoEntidad = Integer.parseInt(request.getParameter("codigoEntidad"));
					int idEntidadExCajaSeleccionada = Integer.parseInt(request.getParameter("idEntidadExCajaSeleccionada"));
					entidadesMgr.borraEntRegimenImpositivo(idEntidadExCajaSeleccionada, codigoEntidad);
					
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrar", "Entidad ", ""));
					this.saveMessages(request, am);
					
					tx.commit();
					actualiza=true;
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					redirect.addParameter("origen", request.getParameter("origen"));
					redirect.addParameter("origenAfp", request.getParameter("origenAfp"));
					redirect.addParameter("idEntidad", request.getParameter("idEntidadExCajaSeleccionada"));
					return redirect;
				} else if (request.getParameter("accionInterna").equals("NEW")){							
					RegImpositivoVO regimen = new RegImpositivoVO();
					int idEntidadExCajaSeleccionada=-1;
					int codigoEntidad=-1;
					String descripcion = "";
					float tasaPension=0;
					if(request.getParameter("idEntidadExCajaSeleccionada") != null){
						idEntidadExCajaSeleccionada = Integer.parseInt(request.getParameter("idEntidadExCajaSeleccionada"));
					}					
					if(request.getParameter("codigoEntidad") != null){
						codigoEntidad = Integer.parseInt(request.getParameter("codigoEntidad"));
					}
					if(request.getParameter("descripcion") != null){
						descripcion = request.getParameter("descripcion");
					}
					if(request.getParameter("tasaPension") != null){
						String tasaTmp = request.getParameter("tasaPension").replace(',','.');
						tasaPension = Float.parseFloat(tasaTmp);
					}
					regimen.setIdEntExCaja(idEntidadExCajaSeleccionada);
					regimen.setIdRegImpositivo(codigoEntidad);
					regimen.setDescripcion(descripcion);
					regimen.setTasaPension(tasaPension);
					
					if(entidadesMgr.getExiste(RegImpositivoVO.class, idEntidadExCajaSeleccionada, "idEntExCaja"
							, codigoEntidad, "idRegImpositivo" )){
						
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "El Código Entidad ",	""));
						this.saveMessages(request, am);
						volverError=true;
					} else {
						entidadesMgr.nuevaEntsRegimenImpositivo(regimen);
						tx.commit();
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Entidad ",	regimen.getDescripcion().trim()));
						this.saveMessages(request.getSession(), am);
						redirect = new ActionRedirect(mapping.findForward("cancelar"));
						redirect.addParameter("origen", request.getParameter("origen"));
						redirect.addParameter("origenAfp", request.getParameter("origenAfp"));
						redirect.addParameter("idEntidad", request.getParameter("idEntidadExCajaSeleccionada"));
						return redirect;
					}
				}
			} else {
				actualiza=true;
			}
			
				if(request.getParameter("tipoEdicion") != null){
					if(request.getParameter("tipoEdicion").equals("NUEVO")){
						actForm.setNuevaEntidad(true);
						actualiza=false;
						actForm.setTasaPension(0);
						actForm.setDescripcion("");	
						actForm.setIdEntExCaja(actForm.getIdEntidadExCajaSeleccionada());
						actForm.setCodigoEntidad("");
						actForm.setLista(entidadesMgr.getEntsExCaja(Integer.parseInt(actForm.getOrigen())));
					}
				}
			if(volverError){
				{
					if(request.getParameter("codigoEntidad") != null)
						actForm.setCodigoEntidad(request.getParameter("codigoEntidad"));
					if(request.getParameter("descripcion") != null)
						actForm.setDescripcion(request.getParameter("descripcion"));
					if(request.getParameter("tasaPension") != null){
						String tasaTmp = request.getParameter("tasaPension").replace(',','.');
						actForm.setTasaPension(Float.parseFloat(tasaTmp));
					}
					if(request.getParameter("idEntidadExCajaSeleccionada") != null)
						actForm.setIdEntExCaja(Integer.parseInt(request.getParameter("idEntidadExCajaSeleccionada")));

					actForm.setLista(entidadesMgr.getEntsExCaja(Integer.parseInt(actForm.getOrigen())));
				}				
				return mapping.findForward("exito");
			}
			if(actualiza){
				if(request.getParameter("idEntPagadora") != null)
					actForm.setIdEntPagadora(Integer.parseInt(request.getParameter("idEntPagadora")));
				actForm.setLista(entidadesMgr.getEntsExCaja(actForm.getIdEntPagadora()));
				if(request.getParameter("codigoEntidad") != null)
					actForm.setCodigoEntidad(request.getParameter("codigoEntidad"));
				if(request.getParameter("idEntidadExCajaSeleccionada") != null)
					actForm.setIdEntExCaja(Integer.parseInt(request.getParameter("idEntidadExCajaSeleccionada")));
				
				RegImpositivoVO regimen = entidadesMgr.getRegImp(actForm.getIdEntExCaja(), Integer.parseInt(actForm.getCodigoEntidad()));
				
				if(regimen!=null){
					actForm.setTasaPension(regimen.getTasaPension());
					actForm.setDescripcion(regimen.getDescripcion().trim());	
					actForm.setIdEntExCaja(regimen.getIdEntExCaja());
					actForm.setCodigoEntidad(String.valueOf(regimen.getIdRegImpositivo()).trim());
					actForm.setCodigoEntidadAntiguo(regimen.getIdRegImpositivo());
					actForm.setLista(entidadesMgr.getEntsExCaja(Integer.parseInt(actForm.getOrigen())));
				}
			}
			 
			redirect = new ActionRedirect(mapping.findForward("exito"));
			redirect.addParameter("origen", request.getParameter("origen"));
			redirect.addParameter("origenAfp", request.getParameter("origenAfp"));
			redirect.addParameter("idEntidad", request.getParameter("idEntidadExCajaSeleccionada"));
			return redirect;
		} catch (Exception ex) {
			logger.error("Se produjo una excepcion en EdicionEntidadesRegimenImpositivoAction.doTask()", ex);
			if (tx != null) {
				tx.rollback();
			}
			return mapping.findForward("error");
		}
	}		
}
