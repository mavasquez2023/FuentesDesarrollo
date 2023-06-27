package cl.araucana.adminCpe.presentation.struts.actions.convenio;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanComparator;
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
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

import com.bh.talon.User;
/*
* @(#) EditarConvenioAction.java 1.10 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author malvarez
 * 
 * @version 1.10
 */
public class EditarConvenioAction extends AppAction
{
	private static Logger logger = Logger.getLogger(EditarConvenioAction.class);

	private EntidadesMgr entidadesMgr;
	private EmpresaMgr empresaMgr;
	private ConvenioMgr convenioMgr;
 
	private static final int CREAR = 0;
	private static final int EDITAR = 1;
	/**
	 * llena combos convenio
	 * @param actForm
	 * @param usuario
	 * @param tipoOperacion
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(EditarConvenioActionForm actForm, int tipoOperacion)  throws DaoException {
		
		List convenios = new ArrayList();
		List opcionesProc = new ArrayList();
		if (tipoOperacion == EDITAR) {
			//Combo convenios
			List listaConvenios = new ArrayList(this.convenioMgr.getConveniosEmpresa(actForm.getRutEmpresa()));
			ConvenioVO convenio;
			for (Iterator it = listaConvenios.iterator(); it.hasNext();) {
				convenio = (ConvenioVO) it.next();
				if (convenio.getIdEmpresa() != actForm.getRutEmpresa())
					continue;
				convenios.add(new LabelValueBean(convenio.getDescripcion().trim(), Integer.toString(convenio.getIdConvenio())));
			}
			Collections.sort(convenios, LabelValueBean.CASE_INSENSITIVE_ORDER);
		} else if (tipoOperacion == CREAR) {
			//Combo opciones proceso
			List listaOpcionesProc = this.convenioMgr.getListaOpcionesProcesos();
			OpcionProcVO opcion;
			DecimalFormat dFormat = new DecimalFormat("0000");  
			for (Iterator it = listaOpcionesProc.iterator(); it.hasNext();) {
				opcion = (OpcionProcVO) it.next();
				opcionesProc.add(new LabelValueBean(dFormat.format(opcion.getIdOpcion()), Integer.toString(opcion.getIdOpcion())));
			}
		}
		
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

/*		//Combo grupos de convenios
		List listaGrupos = new ArrayList(usuarioMgr.getPersona(usuario.getLogin()).getGruposConvenio());
		List grupos = new ArrayList();
		GrupoConvenioVO grupo;
		for (Iterator it = listaGrupos.iterator(); it.hasNext();) {
			grupo = (GrupoConvenioVO) it.next();
			grupos.add(new LabelValueBean(grupo.getNombre().trim(), Integer.toString(grupo.getIdGrupoConvenio())));
		}
		Collections.sort(grupos, LabelValueBean.CASE_INSENSITIVE_ORDER);
*/
		//Combo actividades economicas
		List listaActEconomicas = this.empresaMgr.getActividadesEconomicas();
		List actsEconomicas = new ArrayList();
		ActividadEconomicaVO actEconomica;
		for (Iterator it = listaActEconomicas.iterator(); it.hasNext();) {
			actEconomica = (ActividadEconomicaVO) it.next();
			actsEconomicas.add(new LabelValueBean(actEconomica.getNombre().trim(), Integer.toString(actEconomica.getIdActividad())));
		}
		Collections.sort(actsEconomicas, LabelValueBean.CASE_INSENSITIVE_ORDER);

		actForm.setConvenios(convenios);
		actForm.setMutuales(mutuales);
		actForm.setCajas(cajas);
//		actForm.setGrupos(grupos);
		actForm.setActividadesEconomicas(actsEconomicas);
		actForm.setOpcionesProcesos(opcionesProc);
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
	 * <dd>Si es conveniosEditar, muestra la edicion de convenios. Si es conveniosCrear,
	 * mustra la creacion de convenios.</dd>
	 * <dt>rutEmpresa</dt>
	 * <dd>El rut de la empresa (<code>int</code>) para la que se quiere editar el convenio.</dd>
	 * <dt>idConvenio</dt>
	 * <dd>El id del convenio que se quiere editar.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es Guardar, guarda el contenido del formulario en la base de datos. Si es Cancelar, se 
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
		int tipoOperacion = EDITAR;
		boolean bGuardar = false;
		try 
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			//Instancia los managers correspondientes
			this.entidadesMgr = new EntidadesMgr(session);
			this.empresaMgr = new EmpresaMgr(session);
			this.convenioMgr = new ConvenioMgr(session);

			if (request.getParameter("subSubAccion").equals("conveniosEditar"))
				tipoOperacion = EDITAR;
			else if (request.getParameter("subSubAccion").equals("conveniosCrear"))
				tipoOperacion = CREAR;

			int rut = -1;
			int idConvenio = -1;

			if (request.getParameter("operacion") != null) 
			{
				//Lamado desde dentro
				if (request.getParameter("operacion").equals("Guardar")) 
				{
					bGuardar = true;
					
					ConvenioVO convenio;
					
					rut = Integer.parseInt(request.getParameter("rutEmpresa"));
					if (tipoOperacion == EDITAR)
						idConvenio = Integer.parseInt(actForm.getOpcConvenio());
					else if (tipoOperacion == CREAR) {
						idConvenio = Integer.parseInt(actForm.getCodigoConvenio().trim());
						
						convenio = this.convenioMgr.getConvenioNoExcp(rut, idConvenio);
						if (convenio != null) {
							llenaCombosEdicion(actForm, tipoOperacion);
							
							tx.commit();

							ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "Convenio"));
							this.saveErrors(request, ae);
							
							return mapping.findForward("exitoCrear");
						}
					}

					GrupoConvenioVO gc = null;
					if (actForm.getGrupoConvenio() != null && !actForm.getGrupoConvenio().equals(""))
						gc = this.convenioMgr.getGrupoConvenio(Integer.parseInt(actForm.getGrupoConvenio().trim()));
					if (gc == null) 
					{
						//Llena los combos para editar los convenios
						llenaCombosEdicion(actForm, tipoOperacion);

						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.noExiste", "Grupo de Convenios", new Integer(actForm.getGrupoConvenio().trim())));
						this.saveErrors(request, ae);

						tx.commit();

						if (tipoOperacion == EDITAR)
							return mapping.findForward("exitoEditar");
						return mapping.findForward("exitoCrear");
					} 
					if(!this.convenioMgr.isGrupoConvenioHabilitado(Integer.parseInt(actForm.getGrupoConvenio().trim())))
					{
						llenaCombosEdicion(actForm, tipoOperacion);

						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.noHabilitado", "Grupo de Convenios", new Integer(actForm.getGrupoConvenio().trim())));
						this.saveErrors(request, ae);

						tx.commit();

						if (tipoOperacion == EDITAR)
							return mapping.findForward("exitoEditar");
						return mapping.findForward("exitoCrear");
					}

					convenio = new ConvenioVO();
					if (tipoOperacion == EDITAR)
						convenio = this.convenioMgr.getConvenio(rut, idConvenio);
					else if (tipoOperacion == CREAR) 
					{
						convenio.setIdConvenio(Integer.parseInt(actForm.getCodigoConvenio().trim()));
						convenio.setIdEmpresa(rut);
					}
					
					convenio.setCalculoMovPersonal(actForm.getOpcCalculoMovPersonal());
					convenio.setDescripcion(actForm.getNombreConvenio());
					convenio.setHabilitado(Integer.parseInt(actForm.getOpcHabilitado()));
					convenio.setIdGrupoConvenio(gc.getIdGrupoConvenio());
					convenio.setIdCcaf(Integer.parseInt(actForm.getOpcCaja()));
					convenio.setIdActividad(Integer.parseInt(actForm.getOpcActividadEconomica()));

					if (tipoOperacion == CREAR)
						this.convenioMgr.guardaConvenio(convenio);

					idConvenio = convenio.getIdConvenio();

					tx.commit();

					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", "admin");
					redirect.addParameter("subAccion", "empresas");
					redirect.addParameter("subSubAccion", "conveniosLista");
					redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));

					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Convenio", new Integer(idConvenio)));
					this.saveMessages(request.getSession(), am);
					
					return redirect;
				} else if (request.getParameter("operacion").equals("Cancelar")) 
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", "admin");
					redirect.addParameter("subAccion", "empresas");
					redirect.addParameter("subSubAccion", "conveniosLista");
					redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));
					
					return redirect;
				}
			} else {
				//Llamado desde afuera o desde combo convenio
				rut = Integer.parseInt(request.getParameter("rutEmpresa"));
				
				if (tipoOperacion == EDITAR) {
					if (actForm.getOpcConvenio() == null) {
						idConvenio = Integer.parseInt(request.getParameter("idConvenio"));
					} else {
						idConvenio = Integer.parseInt(actForm.getOpcConvenio());
					}
				}
			}

			//Llena los combos para editar los convenios
			llenaCombosEdicion(actForm, tipoOperacion);
			
			EmpresaVO empresa = this.empresaMgr.getEmpresa(rut);
			actForm.setNombreEmpresa(empresa.getRazonSocial().trim());
			actForm.setRutEmpresaFmt(Utils.formatRut(empresa.getIdEmpresa()));
			actForm.setRutEmpresa(rut);
			
			ConvenioVO convenio;
			GrupoConvenioVO gConvenio;
			if (tipoOperacion == EDITAR) {
				convenio = this.convenioMgr.getConvenio(rut, idConvenio);
				gConvenio = this.convenioMgr.getGrupoConvenio(convenio.getIdGrupoConvenio());
				
				actForm.setOpcHabilitado(Integer.toString(convenio.getHabilitado()));
				actForm.setOpcGrupoConvenio(Integer.toString(convenio.getIdGrupoConvenio()));
				actForm.setGrupoConvenio(Integer.toString(gConvenio.getIdGrupoConvenio()));
				actForm.setGrupos(this.convenioMgr.getListaGruposConvenioActivos());
				Collections.sort(actForm.getGrupos(), new BeanComparator("idGrupoConvenio"));
				actForm.setNombreConvenio(convenio.getDescripcion().trim());
				actForm.setCodigoConvenio(Integer.toString(convenio.getIdConvenio()));
				actForm.setOpcActividadEconomica(Integer.toString(convenio.getIdActividad()));
				actForm.setOpcActividadEconomicaMostrar(Integer.toString(convenio.getIdActividad()));
				actForm.setOpcCaja(Integer.toString(convenio.getIdCcaf()));
				actForm.setOpcCalculoMovPersonal(convenio.getCalculoMovPersonal() != 0 ? 1 : 0);
				
				idConvenio = convenio.getIdConvenio();
				actForm.setOpcConvenio(Integer.toString(idConvenio));
			} else if (tipoOperacion == CREAR) {
			} 
			
			tx.commit();
			
			if (bGuardar) {
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Convenio", new Integer(idConvenio)));
				this.saveMessages(request, am);
			}

			if (tipoOperacion == EDITAR)
				return mapping.findForward("exitoEditar");
			else if (tipoOperacion == CREAR)
				return mapping.findForward("exitoCrear");
			else
				return null;
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en EditarConvenioAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}  
	}
}
