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
import cl.araucana.adminCpe.presentation.mgr.CotizanteMgr;
import cl.araucana.adminCpe.presentation.mgr.EstructuraMgr;
import cl.araucana.adminCpe.presentation.struts.forms.estructuras.AsigFamiliarEditarActionForm;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;

import com.bh.talon.User;
/*
* @(#) AsigFamiliarEditarAction.java 1.4 10/05/2009
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
public class AsigFamiliarEditarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(AsigFamiliarEditarAction.class);

	public AsigFamiliarEditarAction() 
	{
		super();
	}
	/**
	 * asignacion familiar editar
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		AsigFamiliarEditarActionForm actForm = (AsigFamiliarEditarActionForm) form;
		
		actForm.setNuevoTipo(false);
		
		boolean procesar = false;
		boolean bGuardar = false;
		
		ActionMessages am = new ActionMessages(); 
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			EstructuraMgr estructuraMgr = new EstructuraMgr(session);
			CotizanteMgr cotizanteMgr = new CotizanteMgr(session);
			
			ActionRedirect redirect = new ActionRedirect();
			
			String accionInterna = request.getParameter("accionInterna");
			String idAsigFam = request.getParameter("id");
			
			if(accionInterna != null){					
				if (accionInterna.equals("CANCELAR")) {					
						redirect = new ActionRedirect(mapping.findForward("cancelar"));
						return redirect;
				} else if (accionInterna.equals("GUARDAR")) {
					String nombre = request.getParameter("nombre");
					String rentaMinima = request.getParameter("rentaMinima");
					String rentaMaxima = request.getParameter("rentaMaxima");
					String valorCarga = request.getParameter("valorCarga");
					
					bGuardar = true;		
					AsigFamVO asigFamVO = new AsigFamVO();
					int id = -5;
					if(idAsigFam != null){
						id = Integer.parseInt(idAsigFam);
					}
					actForm.setLista(new ArrayList());	
					if(estructuraMgr.existeAsigFamiliar("id", id)){
						List temp = estructuraMgr.getEstructuraTipoAsigFamiliar(id);
						if(temp.size()>0){
							asigFamVO  = (AsigFamVO)temp.get(0);
						}
					}
					if(rentaMinima != null)
						asigFamVO.setRentaMinima(new Float(Utils.desFormatMonto(rentaMinima)).floatValue());
					if(rentaMaxima != null)
						asigFamVO.setRentaMaxima(new Float(Utils.desFormatMonto(rentaMaxima)).floatValue());
					if(valorCarga != null)
						asigFamVO.setValorCarga(new Integer(Utils.desFormatMonto(valorCarga)).intValue());
					asigFamVO.setNombre(nombre);
					
					if(!estructuraMgr.existeAsigFamiliar("id", id))
						estructuraMgr.saveTipoAsigFamiliar(asigFamVO);
					else 
						estructuraMgr.updateTipoAsigFamiliar(asigFamVO);
					if (bGuardar) 
					{
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "asig. familiar", asigFamVO.getNombre().trim()));
						this.saveMessages(request.getSession(), am);
					}
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					tx.commit();
					return redirect;
					
				} else if (accionInterna.equals("DEL_TIPO")) {
					bGuardar = true;
					int id = Integer.parseInt(idAsigFam);
					AsigFamVO asigFamVO = cotizanteMgr.getAsigFam(id);
					
					String isDeleteTramoAsigFam = estructuraMgr.isDeleteTramoAsigFam(id); 
					if("".equals(isDeleteTramoAsigFam)){
						estructuraMgr.delTipoAsigFamiliar(id,AsigFamVO.class);
						if (bGuardar) {
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrada", "asig. familiar", asigFamVO.getNombre().trim()));
							this.saveMessages(request.getSession(), am);
						}
					} else {
						if ("CO".equalsIgnoreCase(isDeleteTramoAsigFam)) {
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.noBorroUserDep", "asig. familiar", asigFamVO.getNombre().trim(), "est&aacute; relacionada a un Cotizante"));

						} else if ("MTAF".equalsIgnoreCase(isDeleteTramoAsigFam)) {
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.noBorroUserDep", "asig. familiar", asigFamVO.getNombre().trim(), "est&aacute; relacionada a un Mapeo Tramo Asig.Fam."));
							
						}
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
					actForm.setRentaMinima("0");
					actForm.setRentaMaxima("0");
					actForm.setValorCarga("0");
					actForm.setId(-5);
					return mapping.findForward("exito");
				}
			}
			if(actualiza || procesar){					
				int id=-1;
				if(idAsigFam != null){
					id=Integer.parseInt(String.valueOf(idAsigFam));
				}
				List lista = estructuraMgr.getEstructuraTipoAsigFamiliar(id);
				AsigFamVO asigFamVO;
				for (Iterator it = lista.iterator(); it.hasNext();) {
					asigFamVO = (AsigFamVO) it.next();
					if(id == asigFamVO.getId()){
						actForm.setId(id);
						actForm.setNombre(asigFamVO.getNombre().trim());
						actForm.setRentaMinima(Utils.formatMonto(new Float(asigFamVO.getRentaMinima()).longValue()));
						/*
						actForm.setRentaMaxima(asigFamVO.getRentaMaxima());
						actForm.setValorCarga(asigFamVO.getValorCarga());
						*/
						
						actForm.setRentaMaxima(Utils.formatMonto(new Float(asigFamVO.getRentaMaxima()).longValue()));
						actForm.setValorCarga(Utils.formatMonto(new Integer(asigFamVO.getValorCarga()).longValue()));
						
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
				if(idAsigFam != null){
					if(!idAsigFam.equals("")){
						redirect.addParameter("id", idAsigFam);
					}
				}
				return redirect;
			} 
			return mapping.findForward("exito");
		} catch (Exception ex) {
			logger.error("Se produjo una excepcion en AsigFamiliarEditarAction.doTask()", ex);
			if (tx != null) {
				tx.rollback();
			}
		return mapping.findForward("error");
		}
	}
}
