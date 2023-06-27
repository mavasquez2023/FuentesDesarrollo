package cl.araucana.adminCpe.presentation.struts.actions.banco;

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
import cl.araucana.adminCpe.presentation.mgr.BancoMgr;
import cl.araucana.adminCpe.presentation.mgr.EntidadesMgr;
import cl.araucana.adminCpe.presentation.mgr.SPLMgr;
import cl.araucana.adminCpe.presentation.struts.forms.banco.EdicionBancoActionForm;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.BancoVO;

import com.bh.talon.User;
/*
* @(#) EdicionBancoAction.java 1.11 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.11
 */
public class EdicionBancoAction extends AppAction
{
	private static Logger logger = Logger.getLogger(EdicionBancoAction.class);
	
	public EdicionBancoAction() 
	{
		super();
	}
	/**
	 * edicion bancos
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ActionErrors ae = new ActionErrors(); 
		EdicionBancoActionForm actForm = (EdicionBancoActionForm) form;
		
		actForm.setNuevoTipo(false);
		
		boolean procesar = false;
		boolean bGuardar = false;
		
		ActionMessages am = new ActionMessages(); 
		Session session = null;
		Session sessionSPL = null;
		Transaction tx = null;
		Transaction txSPL = null;
		try 
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			BancoMgr bancoMgr = new BancoMgr(session);

			//Objetos formulario
			String accionInterna = request.getParameter("accionInterna");
			String tipoEdicion = request.getParameter("tipoEdicion");
			String idBanco = request.getParameter("idBanco");
			String rutBanco = request.getParameter("rutBanco");
			String nombre = request.getParameter("nombre");
			String codigoSPL = request.getParameter("codSplSeleccionado");
			String estado = request.getParameter("estado");
			
			if(accionInterna != null){					
				if (accionInterna.equals("CANCELAR"))
						return new ActionRedirect(mapping.findForward("cancelar"));
				else if (accionInterna.equals("GUARDAR")) 
				{						
					bGuardar = true;		
					BancoVO bancoVO = new BancoVO();
					if(idBanco != null)
					{
						if(!String.valueOf(idBanco).equals("-1"))
						{
							List tmp=bancoMgr.getBanco(Integer.parseInt(idBanco));
							bancoVO = (BancoVO)tmp.get(0);
						}
					}

					actForm.setLista(new ArrayList());
					int rutBancoAux = 0;
					if(rutBanco != null)
					{
						rutBancoAux = Utils.desFormatRut(rutBanco);
						logger.info("rut form::: "+rutBanco + "rut desformat::: "+rutBancoAux);
						bancoVO.setRutBanco(String.valueOf(rutBancoAux));
					}
					if(nombre != null){
						bancoVO.setNombre(String.valueOf(nombre));
					}
					bancoVO.setCodSpl(String.valueOf(codigoSPL));
					bancoVO.setEstado(Integer.parseInt(estado));
					bancoVO.setIdBanco(Integer.parseInt(idBanco));

					if(bancoMgr.existe("rutBanco",rutBancoAux) && bancoVO.getIdBanco()==-1)
					{
						if (bGuardar) 
						{
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "el RUT ingresado", ""));
							this.saveMessages(request, am);
						}
						tx.commit();
						procesar = true;
					} else 
					{
						if(bancoVO.getIdBanco() <= 0)
							bancoMgr.save(bancoVO);
						else
							bancoMgr.update(bancoVO);
						if (bGuardar) 
						{
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Banco", Utils.formatRut(new Integer(bancoVO.getRutBanco()).intValue())));
							this.saveMessages(request.getSession(), am);
						}
						tx.commit();
						return new ActionRedirect(mapping.findForward("cancelar"));
					}					
				} else if (accionInterna.equals("DEL_BANCO")) 
				{
					bGuardar = true;
					int id = Integer.parseInt(idBanco);
					List tmp=bancoMgr.getBanco(Integer.parseInt(idBanco));
					BancoVO bancoVO = (BancoVO)tmp.get(0);					
					//preguntar po
					
					EntidadesMgr entidadesMgr = new EntidadesMgr(session);
					int numr = entidadesMgr.consultaBancoAociadoEntidad(id);
					if (numr > 0){
						logger.info("- ---------- cantidad "+numr);
						// throw new DaoException("ERROR_DEPEN_BCO");
						bGuardar = false;
					} else
						bancoMgr.del(id,BancoVO.class);
					if (bGuardar) 
					{
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrar", "Banco ", Utils.formatRut(new Integer(bancoVO.getRutBanco()).intValue())));
						this.saveMessages(request.getSession(), am);
						tx.commit();
					}else
					{
						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.dependencia_de_banco", Utils.formatRut(new Integer(bancoVO.getRutBanco()).intValue())));
						this.saveMessages(request.getSession(), ae);
						if (tx != null)
							tx.rollback();
					}
					return new ActionRedirect(mapping.findForward("cancelar"));
				} 
			}
			
			boolean actualiza=false;
			if(tipoEdicion != null){
				if(tipoEdicion.equals("ACTUALIZA")){
					actualiza=true;	
				}
				 if(tipoEdicion.equals("NUEVO")){
					 actForm.setCodSpl("");
					 actForm.setEstado(1);
					 actForm.setNombre("");
					 actForm.setRutBanco("");
					 actForm.setDigitoRut("");
					 actForm.setNombre("");
					 sessionSPL = HibernateUtil.getSession("SPL");
					 SPLMgr splMgr =  new SPLMgr(sessionSPL);
					 txSPL = sessionSPL.beginTransaction();
					 actForm.setListaSpl(splMgr.getMediosPagoActivos());
					 txSPL.commit();
					 actForm.setIdBanco(-1);
					 return mapping.findForward("exito");
				}
			}
			if(actualiza || procesar){					
				int id=-1;
				if(idBanco != null){
					id=Integer.parseInt(String.valueOf(idBanco));
				}
				List lista = bancoMgr.getBanco(id);
				BancoVO bancoVO;
				for (Iterator it = lista.iterator(); it.hasNext();) {
					bancoVO = (BancoVO) it.next();
					if(id == bancoVO.getIdBanco()){
						actForm.setIdBanco(id);
						
						actForm.setRutBanco((Utils.formatMonto(new Integer(bancoVO.getRutBanco()).intValue())));
						actForm.setDigitoRut(String.valueOf(Utils.generaDV(Integer.parseInt(bancoVO.getRutBanco()))));
						actForm.setCodSpl(bancoVO.getCodSpl().trim());
						actForm.setEstado(bancoVO.getEstado());
						actForm.setNombre(bancoVO.getNombre().trim());
						sessionSPL = HibernateUtil.getSession("SPL");
						SPLMgr splMgr =  new SPLMgr(sessionSPL);
						txSPL = sessionSPL.beginTransaction();
						actForm.setListaSpl(splMgr.getMediosPagoActivos());
						txSPL.commit();
						
					}
				}
			}
			if(procesar == true)
			{
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("refresh"));
				if(tipoEdicion != null)
				{
					if(!tipoEdicion.equals(""))
						redirect.addParameter("tipoEdicion", tipoEdicion);
				}
				if(idBanco != null)
				{
					if(!idBanco.equals(""))
						redirect.addParameter("idBanco", idBanco);
				}
				return redirect;
			} 
			return mapping.findForward("exito");
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en EdicionBancoAction.doTask()", ex);
			if (tx != null) 
				tx.rollback();
			return mapping.findForward("error");
		}
	}
}
