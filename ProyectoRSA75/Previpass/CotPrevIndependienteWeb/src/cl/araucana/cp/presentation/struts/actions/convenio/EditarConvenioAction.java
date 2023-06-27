package cl.araucana.cp.presentation.struts.actions.convenio;

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

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ActividadEconomicaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.EmpresaMgr;
import cl.araucana.cp.presentation.mgr.EntidadesMgr;
import cl.araucana.cp.presentation.struts.forms.convenio.EditarConvenioActionForm;

import com.bh.talon.User;

/*
 * @(#) PagarAction.java 1.18 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.18
 */
public class EditarConvenioAction extends AppAction
{
	private static Logger logger = Logger.getLogger(EditarConvenioAction.class);

	private EntidadesMgr entidadesMgr;
	private EmpresaMgr empresaMgr;
	private ConvenioMgr convenioMgr;

	private static final int CREAR = 0;
	private static final int EDITAR = 1;

	public EditarConvenioAction()
	{
		super();

		this.btns.add("guardar");
		this.btns.add("cancelar");
	}

	/**
	 * llena combos edicion
	 * 
	 * @param actForm
	 * @param usuario
	 * @param tipoOperacion
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(EditarConvenioActionForm actForm, int tipoOperacion) throws DaoException
	{
		List convenios = new ArrayList();
		if (tipoOperacion == EDITAR)
		{
			// Combo convenios
			List listaConvenios = new ArrayList(this.convenioMgr.getConveniosEmpresa(actForm.getRutEmpresa()));
			convenios = new ArrayList();
			ConvenioVO convenio;
			for (Iterator it = listaConvenios.iterator(); it.hasNext();)
			{
				convenio = (ConvenioVO) it.next();
				if (convenio.getIdEmpresa() != actForm.getRutEmpresa())
					continue;
				convenios.add(new LabelValueBean(convenio.getDescripcion().trim(), Integer.toString(convenio.getIdConvenio())));
			}
			Collections.sort(convenios, LabelValueBean.CASE_INSENSITIVE_ORDER);
		}

		// Combo mutuales
		List listaMutuales = new ArrayList(this.entidadesMgr.getEntsMutual());
		List mutuales = new ArrayList();
		EntidadMutualVO mutual;
		for (Iterator it = listaMutuales.iterator(); it.hasNext();)
		{
			mutual = (EntidadMutualVO) it.next();
			mutuales.add(new LabelValueBean(mutual.getNombre().trim(), Integer.toString(mutual.getId())));
		}
		Collections.sort(mutuales, LabelValueBean.CASE_INSENSITIVE_ORDER);

		// Combo cajas
		List listaCajas = new ArrayList(this.entidadesMgr.getEntsCCAF());
		List cajas = new ArrayList();
		EntidadCCAFVO caja;
		for (Iterator it = listaCajas.iterator(); it.hasNext();)
		{
			caja = (EntidadCCAFVO) it.next();
			cajas.add(new LabelValueBean(caja.getNombre().trim(), Integer.toString(caja.getId())));
		}
		Collections.sort(cajas, LabelValueBean.CASE_INSENSITIVE_ORDER);

		// Combo actividades economicas
		List listaActEconomicas = this.empresaMgr.getActividadesEconomicas();
		List actsEconomicas = new ArrayList();
		ActividadEconomicaVO actEconomica;
		for (Iterator it = listaActEconomicas.iterator(); it.hasNext();)
		{
			actEconomica = (ActividadEconomicaVO) it.next();
			actsEconomicas.add(new LabelValueBean(actEconomica.getNombre().trim(), Integer.toString(actEconomica.getIdActividad())));
		}
		Collections.sort(actsEconomicas, LabelValueBean.CASE_INSENSITIVE_ORDER);

		actForm.setConvenios(convenios);
		actForm.setMutuales(mutuales);
		actForm.setCajas(cajas);
		actForm.setActividadesEconomicas(actsEconomicas);
	}

	/**
	 * Procesa el request para generar la respuesta html que se le entregara al cliente.
	 * <p>
	 * Los parametros de <code>request</code> que se deben agregar al llamar a este Action son los siguientes:
	 * <dl>
	 * <dt>accion</dt>
	 * <dd>admin</dd>
	 * <dt>subAccion</dt>
	 * <dd>empresas</dd>
	 * <dt>subSubAccion</dt>
	 * <dd>conveniosEditar</dd>
	 * <dt>rutEmpresa</dt>
	 * <dd>El rut de la empresa (<code>int</code>) para la que se quiere editar el convenio.</dd>
	 * <dt>idConvenio</dt>
	 * <dd>El id del convenio que se quiere editar.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es Guardar, guarda el contenido del formulario en la base de datos. Si es Cancelar, se redirecciona a la lista de convenios.</dd>
	 * </dl>
	 * 
	 * @param usuario
	 *            el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param mapping
	 *            el objeto con los mapeos de accion para este <code>Action</code>
	 * @param form
	 *            el objeto <code>ActionForm</code> correspondiente
	 * @param request
	 *            el objeto <code>request</code> con los parametros para procesar
	 * @param response
	 *            el objeto <code>response</code> con la respuesta al cliente
	 * @return el mapeo de accion correspondiente
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

			// Instancia los managers correspondientes
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
				// Llamado desde dentro
				if (request.getParameter("operacion").equals(Constants.TXT_BTNS.getProperty("guardar")))
				{
					bGuardar = true;

					ConvenioVO convenio;

					rut = Integer.parseInt(request.getParameter("rutEmpresa"));
					if (tipoOperacion == EDITAR)
						idConvenio = Integer.parseInt(actForm.getOpcConvenio());
					else if (tipoOperacion == CREAR)
					{
						idConvenio = Integer.parseInt(actForm.getCodigoConvenio().trim());

						convenio = this.convenioMgr.getConvenioNoExcp(rut, idConvenio);
						if (convenio != null)
						{
							llenaCombosEdicion(actForm, tipoOperacion);

							tx.commit();

							ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "Convenio"));
							this.saveErrors(request, ae);

							return mapping.findForward("exitoCrear");
						}
					}

					// Valida que exista el grupo de convenios
					if (this.convenioMgr.getGrupoConvenioGet(Integer.parseInt(actForm.getCodigoGrupoConvenio().trim())) == null)
					{
						llenaCombosEdicion(actForm, tipoOperacion);

						actForm.setNombreGrupoConvenio("");

						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.noExiste", "Grupo de Convenios", actForm.getCodigoGrupoConvenio().trim()));
						this.saveErrors(request, ae);

						if (tipoOperacion == EDITAR)
							return mapping.findForward("exitoEditar");
						else if (tipoOperacion == CREAR)
							return mapping.findForward("exitoCrear");
					}
					if (!this.convenioMgr.getGrupoConvenioGetActivo(Integer.parseInt(actForm.getCodigoGrupoConvenio().trim())))
					{
						llenaCombosEdicion(actForm, tipoOperacion);

						actForm.setNombreGrupoConvenio("");

						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.noHabilitado", "Grupo de Convenios", actForm.getCodigoGrupoConvenio().trim()));
						this.saveErrors(request, ae);

						if (tipoOperacion == EDITAR)
							return mapping.findForward("exitoEditar");
						else if (tipoOperacion == CREAR)
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

					convenio.setDescripcion(actForm.getNombreConvenio());
					convenio.setHabilitado(Integer.parseInt(actForm.getOpcHabilitado()));
					convenio.setIdGrupoConvenio(Integer.parseInt(actForm.getCodigoGrupoConvenio()));
					convenio.setIdCcaf(Integer.parseInt(actForm.getOpcCaja()));
					convenio.setIdActividad(Integer.parseInt(actForm.getOpcActividadEconomica()));
					convenio.setCalculoMovPersonal(actForm.getOpcCalculoMovPersonal());

					if (tipoOperacion == CREAR)
						this.convenioMgr.guardaConvenio(convenio);

					int idConv = convenio.getIdConvenio();

					//Limpia el código de reenvío de nómina 
					this.empresaMgr.borraCRCporEmpresa(idConv, rut);

					tx.commit();

					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", "admin");
					redirect.addParameter("subAccion", "empresas");
					redirect.addParameter("subSubAccion", "conveniosLista");
					redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));

					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Convenio", new Integer(idConv)));
					this.saveMessages(request.getSession(), am);

					return redirect;
				} else if (request.getParameter("operacion").equals(Constants.TXT_BTNS.getProperty("cancelar")))
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", "admin");
					redirect.addParameter("subAccion", "empresas");
					redirect.addParameter("subSubAccion", "conveniosLista");
					redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));

					return redirect;
				}
			} else
			{
				// Llamado desde afuera o desde combo convenio
				rut = Integer.parseInt(request.getParameter("rutEmpresa"));

				if (tipoOperacion == EDITAR)
				{
					if (actForm.getOpcConvenio() == null)
					{
						idConvenio = Integer.parseInt(request.getParameter("idConvenio"));
					} else
					{
						idConvenio = Integer.parseInt(actForm.getOpcConvenio());
					}
				}
			}

			// Llena los combos para editar los convenios
			llenaCombosEdicion(actForm, tipoOperacion);

			EmpresaVO empresa = this.empresaMgr.getEmpresa(rut);
			actForm.setNombreEmpresa(empresa.getRazonSocial().trim());
			actForm.setRutEmpresaFmt(Utils.formatRut(empresa.getIdEmpresa()));
			actForm.setRutEmpresa(rut);
			actForm.setGrupos(this.convenioMgr.getGruposConveniosAdmin(((PersonaVO) usuario.getUserReference()).getIdPersona().intValue()));

			ConvenioVO convenio;
			GrupoConvenioVO grupo;
			if (tipoOperacion == EDITAR)
			{
				convenio = this.convenioMgr.getConvenio(rut, idConvenio);

				actForm.setOpcHabilitado(Integer.toString(convenio.getHabilitado()));
				// actForm.setOpcGrupoConvenio(Integer.toString(convenio.getIdGrupoConvenio()));
				actForm.setNombreConvenio(convenio.getDescripcion().trim());
				actForm.setCodigoConvenio(Integer.toString(convenio.getIdConvenio()));
				actForm.setOpcActividadEconomica(Integer.toString(convenio.getIdActividad()));
				actForm.setOpcActividadEconomicaMostrar(Integer.toString(convenio.getIdActividad()));
				actForm.setOpcCaja(Integer.toString(convenio.getIdCcaf()));
				actForm.setOpcCalculoMovPersonal(convenio.getCalculoMovPersonal() != 0 ? 1 : 0);

				grupo = this.convenioMgr.getGrupoConvenio(convenio.getIdGrupoConvenio());
				actForm.setCodigoGrupoConvenio(Integer.toString(grupo.getIdGrupoConvenio()));
				Collections.sort(actForm.getGrupos(), new BeanComparator("idGrupoConvenio"));

				actForm.setOpcConvenio(Integer.toString(idConvenio));
			} else if (tipoOperacion == CREAR)
			{
			}

			tx.commit();

			if (bGuardar)
			{
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
