package cl.araucana.adminCpe.presentation.struts.actions.parametro;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ComprobanteMgr;
import cl.araucana.adminCpe.presentation.mgr.FoliacionMgr;
import cl.araucana.adminCpe.presentation.mgr.LimpiarTablasMgr;
import cl.araucana.adminCpe.presentation.mgr.NominaMgr;
import cl.araucana.adminCpe.presentation.mgr.PeriodoMgr;
import cl.araucana.adminCpe.presentation.struts.forms.parametro.PeriodoForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

import com.bh.talon.User;

/*
 * @(#) PeriodoAction.java 1.1 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.17
 */
public class PeriodoAction extends AppAction
{
	public static final String FORWARD = "exito";
	private static Logger log = Logger.getLogger(PeriodoAction.class);

	/**
	 * Ejecuta el proceso de cierre periodo
	 * 
	 * @param User user
	 * @param ActionMapping mapping
	 * @param ActionForm formulario
	 * @param HttpServletRequest request
	 * @param ttpServletResponse response
	 * @return ActionForward
	 * @throws Exception
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			PeriodoForm form = (PeriodoForm) formulario;
			log.info("PeriodoAction:accion:" + request.getParameter("accion") + "::");
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			PeriodoMgr periodoMgr = new PeriodoMgr(session);
						
				try
				{
					if (request.getParameter("accion") != null && request.getParameter("accion").equals("cerrarE")){
						log.info("Se a iniciado el cierre periodo para las Empresas");
						this.cierrePeriodoEmpresa(session, request);
						tx.commit();
					
					}else if (request.getParameter("accion") != null && request.getParameter("accion").equals("cerrarI")){
						log.info("Se a iniciado el cierre periodo para los Independientes");
						this.cierrePeriodoIndependiente(session, request);
						tx.commit();
					}
							
				}catch (Exception e){
					log.info("PeriodoAction:problemas en cierre periodo::", e);
					ActionMessages am = new ActionMessages();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.inicioCierrePeriodo"));
					this.saveMessages(request, am);	
					tx.rollback();
				}
						form.setOperacion(null);
					
					HashMap numsInd = periodoMgr.cuentaDataTipoEmInd();
							
					form.setNumComprobantes((numsInd.containsKey("numCmpsEmp") ? Utils.formatMonto(((Integer) numsInd.get("numCmpsEmp")).intValue()) : "0"));
					form.setNumTrabajadores((numsInd.containsKey("numTrabsEmp") ? Utils.formatMonto(((Integer) numsInd.get("numTrabsEmp")).intValue()) : "0"));
					form.setNumNominasRE((numsInd.containsKey("numNomREEmp") ? Utils.formatMonto(((Integer) numsInd.get("numNomREEmp")).intValue()) : "0"));
					form.setNumNominasGR(numsInd.containsKey("numNomGREmp") ? Utils.formatMonto(((Integer) numsInd.get("numNomGREmp")).intValue()) : "0");
					form.setNumNominasRA(numsInd.containsKey("numNomRAEmp") ? Utils.formatMonto(((Integer) numsInd.get("numNomRAEmp")).intValue()) : "0");
					form.setNumNominasDC(numsInd.containsKey("numNomDCEmp") ? Utils.formatMonto(((Integer) numsInd.get("numNomDCEmp")).intValue()) : "0");
					
					form.setNumComprobantesInd(numsInd.containsKey("numCmpsInd") ? Utils.formatMonto(((Integer) numsInd.get("numCmpsInd")).intValue()) : "0");
					form.setNumTrabajadoresInd(numsInd.containsKey("numTrabsInd") ? Utils.formatMonto(((Integer) numsInd.get("numTrabsInd")).intValue()) : "0");
					form.setNumNominasREInd(numsInd.containsKey("numNomREInd") ? Utils.formatMonto(((Integer) numsInd.get("numNomREInd")).intValue()) : "0");
					form.setNumNominasGRInd(numsInd.containsKey("numNomGRInd") ? Utils.formatMonto(((Integer) numsInd.get("numNomGRInd")).intValue()) : "0");
					form.setNumNominasRAInd(numsInd.containsKey("numNomRAInd") ? Utils.formatMonto(((Integer) numsInd.get("numNomRAInd")).intValue()) : "0");
					form.setNumNominasDCInd(numsInd.containsKey("numNomDCInd") ? Utils.formatMonto(((Integer) numsInd.get("numNomDCInd")).intValue()) : "0");
					
			}catch (Exception e){
				log.info("PeriodoAction:problemas en cierre periodo::", e);
				ActionMessages am = new ActionMessages();
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.inicioCierrePeriodo"));
				this.saveMessages(request, am);	
				tx.rollback();
		}
		return mapping.findForward(FORWARD);

}
	
	/**
	 * Ejecuta el proceso de cierre periodo para los Independientes
	 * 
	 * @param Session session
	 * @param HttpServletRequest request
	 * @return
	 * @throws Exception
	 */
	private void cierrePeriodoIndependiente(Session session,HttpServletRequest request) throws DaoException{
		
		try{
				NominaMgr nominaMgr = new NominaMgr(session);
				LimpiarTablasMgr lTMgr = new LimpiarTablasMgr(session);
				
				List listRutIndependiente = nominaMgr.getTipoNomina(Constants.TIPO_EMPRESA_INDEPENDIENTE);
				List listCodigoBarraIndependiente = nominaMgr.getCodigoBarraByTipoEmpresa(Constants.TIPO_EMPRESA_INDEPENDIENTE);
				
				log.info("Se han encontrado "+listRutIndependiente.size() +" empresas y " + listCodigoBarraIndependiente.size() +" nominas de tipo Independiente");
				
				if(listRutIndependiente.size() >0){
							
				lTMgr.actualizaEstadoNominaReIndependiente(listRutIndependiente);
				
				ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);
				comprobanteMgr.actualizaComprobanteIndependiente(listCodigoBarraIndependiente);
				
				}else{
					ActionMessages am = new ActionMessages();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.inicioCierrePeriodoSinRegistros" , "Independiente"));
					this.saveMessages(request, am);	
					log.info("No se encontraron nominas y comprobantes de tipo Independiente para cerrar el periodo");
				}
		}catch (Exception ex) {
				throw new DaoException("Error en PeriodoAction.cierrePeriodoIndependiente", ex);
		}
	}
	
	/**
	 * Ejecuta el proceso de cierre periodo para las empresas
	 * 
	 * @param Session session
	 * @param HttpServletRequest request
	 * @return
	 * @throws Exception
	 */
	private void cierrePeriodoEmpresa(Session session,HttpServletRequest request) throws Exception{
		
		try{
				PeriodoMgr periodoMgr = new PeriodoMgr(session);

				NominaMgr nominaMgr = new NominaMgr(session);
				LimpiarTablasMgr lTMgr = new LimpiarTablasMgr(session);
				
				List listNominaEmpresa = nominaMgr.getTipoNomina(Constants.TIPO_EMPRESA);
				List listCodigoBarraEmpresa = nominaMgr.getCodigoBarraByTipoEmpresa(Constants.TIPO_EMPRESA);
				
				log.info("Se han encontrado "+listNominaEmpresa.size() +" empresas y " + listCodigoBarraEmpresa.size() +" nominas de tipo empresa");
				
				if(listNominaEmpresa.size() > 0 ){
					lTMgr.limpiaTablasNominasEmpresa(listNominaEmpresa);
				
					ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);
					comprobanteMgr.actualizaComprobanteEmpresa(listCodigoBarraEmpresa);
					
					lTMgr = new LimpiarTablasMgr(session);
					lTMgr.borraComprobantesEmpresa(listCodigoBarraEmpresa);
					
					//EntidadesMgr entidadesMgr = new EntidadesMgr(session);
					//Se comenta reinicioFolios a peticion de claudio lillo el dia 27-02-2013
					//entidadesMgr.reinicioFolios();
					periodoMgr.actualizaBalanceo();
					
					FoliacionMgr foliacionMgr = new FoliacionMgr(session); 
					foliacionMgr.reiniciaFolioActual();
					
				}else{
					ActionMessages am = new ActionMessages();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.inicioCierrePeriodoSinRegistros" , "Empresa"));
					this.saveMessages(request, am);	
					log.info("No se encontraron nominas y comprobantes de tipo empresa para cerrar el periodo");
				}
		}catch (Exception ex) {
			throw new DaoException("Error en PeriodoAction.cierrePeriodoEmpresa", ex);
		}
	}
}
