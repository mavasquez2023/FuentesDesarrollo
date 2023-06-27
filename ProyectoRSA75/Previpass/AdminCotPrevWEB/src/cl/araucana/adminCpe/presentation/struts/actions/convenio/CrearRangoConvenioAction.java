package cl.araucana.adminCpe.presentation.struts.actions.convenio;

import java.util.ArrayList;
import java.util.Collections;
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
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.adminCpe.presentation.mgr.EmpresaMgr;
import cl.araucana.adminCpe.presentation.mgr.EntidadesMgr;
import cl.araucana.adminCpe.presentation.struts.forms.convenio.EditarConvenioActionForm;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ActividadEconomicaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

import com.bh.talon.User;
/*
* @(#) CrearRangoConvenioAction.java 1.5 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author aacuña
 * 
 * @version 1.5
 */
public class CrearRangoConvenioAction extends AppAction
{
	private static Logger logger = Logger.getLogger(EditarConvenioAction.class);

	private EntidadesMgr entidadesMgr;
	private EmpresaMgr empresaMgr;

	/**
	 * llena combos creacion
	 * @param actForm
	 * @param usuario
	 * @throws DaoException
	 */
	private void llenaCombosCreacion(EditarConvenioActionForm actForm)  throws DaoException {
		
		//Combo mutuales
		List listaMutuales = new ArrayList(this.entidadesMgr.getEntsMutual());
		List mutuales = new ArrayList();
		EntidadMutualVO mutual;
		for (Iterator it = listaMutuales.iterator(); it.hasNext();) {
			mutual = (EntidadMutualVO) it.next();
			mutuales.add(new LabelValueBean(mutual.getNombre().trim(), Integer.toString(mutual.getId())));
		}
		Collections.sort(mutuales, LabelValueBean.CASE_INSENSITIVE_ORDER);
		
		//Combo cajas
		List listaCajas = new ArrayList(this.entidadesMgr.getEntsCCAF());
		logger.debug("CAJAS: " + listaCajas);
		List cajas = new ArrayList();
		EntidadCCAFVO caja;
		for (Iterator it = listaCajas.iterator(); it.hasNext();) {
			caja = (EntidadCCAFVO) it.next();
			cajas.add(new LabelValueBean(caja.getNombre().trim(), Integer.toString(caja.getId())));
		}
		Collections.sort(cajas, LabelValueBean.CASE_INSENSITIVE_ORDER);

		//Combo actividades economicas
		List listaActEconomicas = this.empresaMgr.getActividadesEconomicas();
		List actsEconomicas = new ArrayList();
		ActividadEconomicaVO actEconomica;
		for (Iterator it = listaActEconomicas.iterator(); it.hasNext();) {
			actEconomica = (ActividadEconomicaVO) it.next();
			actsEconomicas.add(new LabelValueBean(actEconomica.getNombre().trim(), Integer.toString(actEconomica.getIdActividad())));
		}
		Collections.sort(actsEconomicas, LabelValueBean.CASE_INSENSITIVE_ORDER);

		actForm.setMutuales(mutuales);
		actForm.setCajas(cajas);
//		actForm.setGrupos(grupos);
		actForm.setActividadesEconomicas(actsEconomicas);
	}

	/**
	 * Procesa el request para generar la respuesta html que se le entregara
	 * al cliente.
	 * <p>
	 * Los parametros de <code>request</code> que se deben agregar al llamar a este Action son
	 * los siguientes:
	 * <dl>
	 * <dt>accion</dt>
	 * <dd>admin</dd>
	 * <dt>subAccion</dt>
	 * <dd>empresas</dd>
	 * <dt>subSubAccion</dt>
	 * <dd>conveniosCrearRango</dd>
	 * <dt>rutEmpresa</dt>
	 * <dd>El rut de la empresa (<code>int</code>) para la que se quiere crear el rango de convenios.</dd>
	 * <dt>idConvenio</dt>
	 * <dd>El id del convenio que se quiere editar.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es Generar, guarda el contenido del formulario en la base de datos. Si es Cancelar, se 
	 * redirecciona a la lista de convenios.</dd>
	 * </dl> 
	 *
	 * @param	usuario		el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param	mapping		el objeto con los mapeos de accion para este <code>Action</code>
	 * @param	form		el objeto <code>ActionForm</code> correspondiente
	 * @param	request		el objeto <code>request</code> con los parametros para procesar
	 * @param	response	el objeto <code>response</code> con la respuesta al cliente
	 * @return	el mapeo de accion correspondiente
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EditarConvenioActionForm actForm = (EditarConvenioActionForm) form;
		
		ActionMessages am = new ActionMessages(); 
		ActionErrors ae = new ActionErrors(); 
		Session session = null;
		Transaction tx = null;
		boolean bGuardar = false;
		try 
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			//Instancia los managers correspondientes
			this.entidadesMgr = new EntidadesMgr(session);
			this.empresaMgr = new EmpresaMgr(session);
			ConvenioMgr convenioMgr = new ConvenioMgr(session);

			int rut = -1;
			int idConvenioDesde = -1;
			int idConvenioHasta = -1;
			
			if (request.getParameter("operacion") != null) {
				//Lamado desde dentro
				rut = Integer.parseInt(request.getParameter("rutEmpresa"));
				if (request.getParameter("operacion").equals("Generar")) {
					bGuardar = true;

					idConvenioDesde = Integer.parseInt(actForm.getCodigoConvenioDesde());
					idConvenioHasta = Integer.parseInt(actForm.getCodigoConvenioHasta());

					GrupoConvenioVO grupo = convenioMgr.getGrupoConvenio(Integer.parseInt(actForm.getGrupoConvenio()));
					if (grupo == null) {
						llenaCombosCreacion(actForm);

						tx.commit();

						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.noExiste", "Grupo de Convenios", actForm.getGrupoConvenio().trim()));
						this.saveErrors(request, ae);

						return mapping.findForward("exito");
					}
					
					if (convenioMgr.existeConvenioEnRango(rut, idConvenioDesde, idConvenioHasta)) {
						llenaCombosCreacion(actForm);
						
						tx.commit();
						
						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existeEnRango", "Convenios", new Integer(idConvenioDesde), new Integer(idConvenioHasta)));
						this.saveErrors(request, ae);
						
						return mapping.findForward("exito");
					}

					ConvenioVO datos = new ConvenioVO();
					datos.setIdEmpresa(rut);
					datos.setDescripcion(actForm.getNombreConvenio().trim());
					datos.setHabilitado(Integer.parseInt(actForm.getOpcHabilitado()));
					datos.setIdGrupoConvenio(Integer.parseInt(actForm.getGrupoConvenio().trim()));
					datos.setIdCcaf(Integer.parseInt(actForm.getOpcCaja()));
					datos.setIdActividad(Integer.parseInt(actForm.getOpcActividadEconomica()));
					datos.setCalculoMovPersonal(actForm.getOpcCalculoMovPersonal());
					
					convenioMgr.generarRangoConvenios(datos, idConvenioDesde, idConvenioHasta);
				} else if (request.getParameter("operacion").equals("Cancelar")) {
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", "admin");
					redirect.addParameter("subAccion", "empresas");
					redirect.addParameter("subSubAccion", "conveniosLista");
					redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));
					
					return redirect;
				}
			} else {
				rut = Integer.parseInt(request.getParameter("rutEmpresa"));
			}
			
			EmpresaVO empresa = this.empresaMgr.getEmpresa(rut);
			actForm.setRutEmpresa(empresa.getIdEmpresa());
			actForm.setRutEmpresaFmt(Utils.formatRut(empresa.getIdEmpresa()));
			actForm.setNombreEmpresa(empresa.getRazonSocial().trim());
			
			//Llena los combos para editar los convenios
			llenaCombosCreacion(actForm);
			
			tx.commit();
			
			if (bGuardar) 
			{
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.generar", "Rango de convenios", new Integer(idConvenioDesde), new Integer(idConvenioHasta)));
				this.saveMessages(request.getSession(), am);
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("exitoCrear"));
				redirect.addParameter("accion", request.getParameter("accion"));
				redirect.addParameter("subAccion", request.getParameter("subAccion"));
				redirect.addParameter("subSubAccion", "conveniosLista");
				redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));

				return redirect;
			}

			return mapping.findForward("exito");
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en CrearRangoConvenioAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}
}
