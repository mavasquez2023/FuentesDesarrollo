package cl.araucana.adminCpe.presentation.struts.actions.entidad;

import java.util.ArrayList;
import java.util.Iterator;
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
import cl.araucana.adminCpe.presentation.mgr.EntidadesMgr;
import cl.araucana.adminCpe.presentation.struts.forms.entidad.ListaEntidadesExCajaActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaEntidadFicha;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadExCajaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegImpositivoVO;

import com.bh.talon.User;
/*
* @(#) ListaEntidadesExCajaAction.java 1.7 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.7
 */
public class ListaEntidadesExCajaAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListaEntidadesExCajaAction.class);

	public ListaEntidadesExCajaAction() 
	{
		super();		
		this.btns.add("imprimir");
	}
	/**
	 * lista entidades ex caja
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ListaEntidadesExCajaActionForm actForm = (ListaEntidadesExCajaActionForm) form;
		
		if(request.getParameter("origen") == null)
			actForm.setOrigen(request.getParameter("idEntPagadora"));
		else 
			actForm.setOrigen(request.getParameter("origen"));
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			//Instancia los managers correspondientes
			EntidadesMgr entidadesMgr= new EntidadesMgr(session);
			List entidadExCaja = this.orden(entidadesMgr.getEntsExCaja());			
			EntidadExCajaVO entidadExCajaVO;
			LineaEntidadFicha lEntidad;
			actForm.setListaEntidadExCaja(new ArrayList());
			actForm.getListaEntidadExCaja().clear();
			for (Iterator it = entidadExCaja.iterator(); it.hasNext();)
			{
				entidadExCajaVO = (EntidadExCajaVO) it.next();
				if(entidadExCajaVO.getIdEntPagadora() > 0){
				String nombre=entidadExCajaVO.getNombre().trim();
				List entidadRegimenImpositivo = entidadesMgr.getRegImp(entidadExCajaVO.getId());
				if(entidadRegimenImpositivo.size()>0){
					for (Iterator itF = entidadRegimenImpositivo.iterator(); itF.hasNext();){
						RegImpositivoVO reg = (RegImpositivoVO) itF.next();
						lEntidad = new LineaEntidadFicha();
						lEntidad.setNombre(nombre);nombre="";
						lEntidad.setIdEntPagadora(entidadExCajaVO.getIdEntPagadora());
						lEntidad.setTasaPension(String.valueOf(reg.getTasaPension()).replace('.',','));
						lEntidad.setDescripcion(reg.getDescripcion());
						lEntidad.setIdFoliacion(2);
						
						lEntidad.setIdEntidad(entidadExCajaVO.getId());
						actForm.getListaEntidadExCaja().add(lEntidad);
					}
				} else {
					lEntidad = new LineaEntidadFicha();
					lEntidad.setNombre(nombre);nombre="";
					lEntidad.setIdEntPagadora(entidadExCajaVO.getIdEntPagadora());
					lEntidad.setIdCodigo("-");
					lEntidad.setIdEntidad(entidadExCajaVO.getId());
					actForm.getListaEntidadExCaja().add(lEntidad);
				}
				}
			}			
			tx.commit();
			
			return mapping.findForward("exito");
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en ListaEntidadesExCajaAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}
	/**
	 * lista entidad orden
	 * @param lista
	 * @return
	 */
	public List orden(List lista){
		for(int a=0; a<lista.size();a++){
			for(int b=0; b<lista.size(); b++){
				EntidadVO entidad1 = (EntidadVO)lista.get(a);
				EntidadVO entidad2 = (EntidadVO)lista.get(b);
				if(entidad1.getNombre().compareTo(entidad2.getNombre()) < 0){
					EntidadVO temp = entidad1;
					lista.set(a, entidad2);
					lista.set(b, temp);
					
				}
				
			}
		}
		return lista;
	}
}
