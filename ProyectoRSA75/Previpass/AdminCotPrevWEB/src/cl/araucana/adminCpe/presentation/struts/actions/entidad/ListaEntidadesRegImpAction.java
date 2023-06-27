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
import cl.araucana.adminCpe.presentation.struts.forms.entidad.ListaEntidadesRegImpActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaEntidadFicha;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegImpositivoVO;

import com.bh.talon.User;
/*
* @(#) ListaEntidadesRegImpAction.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * @author aacuña
 * 
 * @version 1.2
 */
public class ListaEntidadesRegImpAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListaEntidadesRegImpAction.class);
	
	public ListaEntidadesRegImpAction() 
	{
		super();
		
		this.btns.add("imprimir");
	}
	
 	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ListaEntidadesRegImpActionForm actForm = (ListaEntidadesRegImpActionForm) form;
		
		if(request.getParameter("origenAfp") == null)
			actForm.setOrigenAfp(request.getParameter("origenAfp"));
		if(request.getParameter("origen") == null)
			actForm.setOrigen(request.getParameter("origen"));
		if(request.getParameter("idEntidad") != null){
			actForm.setIdExCaja(Integer.parseInt(request.getParameter("idEntidad")));
		}
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
									
			EntidadesMgr entidadesMgr= new EntidadesMgr(session);
			
			//List entidadRegimenImpositivo = this.orden(entidadesMgr.getRegImp());
			List entidadRegimenImpositivo = entidadesMgr.getRegImp(actForm.getIdExCaja());
			
			RegImpositivoVO entidadVO;
			LineaEntidadFicha lEntidad;
			actForm.setListaEntidadRegimenImpositivo(new ArrayList());
			for (Iterator it = entidadRegimenImpositivo.iterator(); it.hasNext();)
			{
				entidadVO = (RegImpositivoVO) it.next();
				if(entidadVO.getIdEntExCaja() > 0){
				String nombre=entidadVO.getDescripcion().trim();
				lEntidad = new LineaEntidadFicha();
				lEntidad.setNombre(nombre);nombre="";
				lEntidad.setIdEntidad(entidadVO.getIdEntExCaja());
				lEntidad.setIdCodigo(String.valueOf(entidadVO.getIdRegImpositivo()));
				lEntidad.setTasaPension(String.valueOf(entidadVO.getTasaPension()).replace(',', '.'));
				actForm.getListaEntidadRegimenImpositivo().add(lEntidad);
				
				}
			}			
			tx.commit();
			
			return mapping.findForward("exito");
		} catch (Exception ex) {
			logger.error("Se produjo una excepcion en ListaEntidadesRegImpAction.doTask()", ex);
			if (tx != null) {
				tx.rollback();
			}
			return mapping.findForward("error");
		}
	}
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
