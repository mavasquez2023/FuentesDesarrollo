package cl.araucana.cp.presentation.struts.actions.cargasFamiliares;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.PersonaMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.struts.forms.cargasFamiliares.ConsultaNominasActionForm;

import com.bh.talon.User;

public class ConsultaNominasAction extends AppAction
{
	static Logger logger = Logger.getLogger(ConsultaNominasAction.class);

	static List columnas = new ArrayList(5);
	static List alts = new ArrayList(5);
	static String[] imgsrcs;
	static Map nombreTipoNominas = new HashMap();		// NOMINA_EN_LINEA

	/**
	 * listar action
	 * 
	 */
	public ConsultaNominasAction()
	{
		super();
		this.btns.add("filtro");
	}

	static
	{
		columnas.add("enviarNomina");
		columnas.add("corregirNomina");
		columnas.add("editarNomina");
		columnas.add("comprobanteEditar");
		columnas.add("resumenComprProvi");
		columnas.add("verComprobantes");

		alts.add("Enviar");
		alts.add("Corregir");
		alts.add("Edici&oacute;n N&oacute;mina");
		alts.add("Edici&oacute;n Comprobante");
		alts.add("Pagar");
		alts.add("Ver Comprobantes");
		alts.add("Ver Detalle N&oacute;mina");

		imgsrcs = new String[] {
				"/img/iconosResumen/noEnviada.gif",
				"/img/iconosResumen/verCmpPagado.gif",
				"/img/iconosResumen/corregirNomina.gif",
				"/img/iconosResumen/editarNomina.gif",
				"/img/iconosResumen/editarCmp.gif",
				"/img/iconosResumen/detalleCmp.gif",
				"/img/iconosResumen/pagar.gif",
				"/img/iconosResumen/verTrabajadores.gif",
				"/img/iconosResumen/crearNomina.gif",	// NOMINA_EN_LINEA
				"/img/iconosResumen/verErrores.jpg" 	//Ver Errores
		};

		nombreTipoNominas.put("R", "Remuneraciones");
		nombreTipoNominas.put("G", "Gratificaciones");
		nombreTipoNominas.put("D", "Dep&oacute;sitos Convenios e Indemnizaciones");
		nombreTipoNominas.put("A", "Reliquidaciones");		
	}

	protected void llenarListas(ConsultaNominasActionForm actForm, Session session, String tipoNomina, ProcesoMgr procesoMgr, User user) throws DaoException {

		PersonaMgr personaMgr = new PersonaMgr(session);
		UsuarioCP usuarioCP = (UsuarioCP) user;

		int idEmpresa = actForm.getEmpresa() != null ? actForm.getEmpresa().intValue() : 0;

		// Carga el combo de tipos de nómina
		Collection tiposDeNominas = procesoMgr.getTipoProceso(String.valueOf(Constants.TIPO_NOM_REMUNERACION));
		List tiposNomina = new ArrayList();
		TipoNominaVO tipoNominaVO;
		for (Iterator it = tiposDeNominas.iterator(); it.hasNext();) {
			tipoNominaVO = (TipoNominaVO) it.next();
			tiposNomina.add(new LabelValueBean(tipoNominaVO.getDescripcion(), tipoNominaVO.getIdTipoNomina()));
		}

		// Si viene de afuera, carga la opciones con el primer valor de los combos
		if (tipoNomina == null || tipoNomina.equals(""))
			tipoNomina = ((LabelValueBean) tiposNomina.get(0)).getValue();

		// Recupera las empresas de las que es encargado el usuario o administrador
		List listaEmpresas = personaMgr.getListaEmpresasIn(usuarioCP.getUnionEmpresasLectura());
		List empresas = new ArrayList();
		EmpresaVO itEmpresa;
		for (Iterator it = listaEmpresas.iterator(); it.hasNext();) {
			itEmpresa = (EmpresaVO) it.next();
			empresas.add(new LabelValueBean(itEmpresa.getRazonSocial().trim(), Integer.toString(itEmpresa.getIdEmpresa())));
		}
		Collections.sort(empresas, LabelValueBean.CASE_INSENSITIVE_ORDER);

		actForm.setTiposNomina(tiposNomina);
		actForm.setTipoNomina(tipoNomina);
		actForm.setEmpresas(empresas);
		actForm.setEmpresa(new Integer(idEmpresa));
		actForm.setImgsrcs(imgsrcs);
	}
	
	
	/**
	 * Procesa el request para generar la respuesta html que se le entregara al cliente.
	 * <p>
	 * Los parametros de <code>request</code> que se deben agregar al llamar a este Action son los siguientes:
	 * <dl>
	 * <dt>accion</dt>
	 * <dd>inicio</dd>
	 * <dt>subAccion</dt>
	 * <dd>procesos</dd>
	 * <dt>operacion</dt>
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
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ConsultaNominasActionForm actForm = (ConsultaNominasActionForm) actionForm;

		Session session = null;
		Transaction tx = null;

		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			ProcesoMgr procesoMgr = new ProcesoMgr(session);
			ConvenioMgr convenioMgr = new ConvenioMgr(session);
			UsuarioCP usuarioCP = (UsuarioCP) user;

			String tipoNomina = actForm.getTipoNomina();

			int idEstado  = Constants.EST_NOM_POR_PAGAR;// actForm.getEstado()  != null ? actForm.getEstado().intValue()  : 0;
			int idEmpresa = actForm.getEmpresa() != null ? actForm.getEmpresa().intValue() : 0;

			actForm.setEstado(new Integer(idEstado));
			request.setAttribute("cambioParam", "accion=inicio&subAccion=procesos&limpiaPath=&estado=" + idEstado + "&empresa=" + idEmpresa + (tipoNomina != null ? "&tipoNomina=" + tipoNomina : ""));

			String nombreTipoNomina = (String) nombreTipoNominas.get(tipoNomina); // NOMINA_EN_LINEA
			
			if (nombreTipoNomina == null) {
				nombreTipoNomina = (String) nombreTipoNominas.get("R");
			}
			
		    request.setAttribute("nombreTipoNomina", nombreTipoNominas.get(tipoNomina));

		    llenarListas(actForm, session, tipoNomina, procesoMgr, user);

			ConvenioVO convenio;

			// Obtiene los convenios de lectura
			List conveniosLectura = convenioMgr.getConveniosPermisos(((PersonaVO) user.getUserReference()).getIdPersona().intValue(), usuarioCP.getUnionEmpresasLectura());

			// Va a buscar las nóminas de los convenios
			NominaVO nomina;
			List nominas = new ArrayList();
			int flgNominasEnProgreso = 0;
			for (Iterator it = conveniosLectura.iterator(); it.hasNext();)
			{
				convenio = (ConvenioVO) it.next();
				// Filtra el convenio por empresa si es distinta de todas
				if (idEmpresa != 0 && convenio.getIdEmpresa() != idEmpresa)
					continue;

				// Carga la nomina para este convenio
				nomina = procesoMgr.getNomina(actForm.getTipoNomina(), convenio.getIdEmpresa(), convenio.getIdConvenio());
				/*if (nomina == null)
					nomina = procesoMgr.getNominaNoEnviada(tipoNomina, convenio.getIdEmpresa(), convenio.getIdConvenio());*/
				// Filtra por estado si es distinto de todos
				//if (nomina == null || nomina.getIdEstado() != Constants.EST_NOM_POR_PAGAR || (idEstado != 0 && nomina.getIdEstado() != idEstado))
				if (nomina == null || (nomina.getIdEstado() != Constants.EST_NOM_EN_PROCESO  && nomina.getIdEstado() != Constants.EST_NOM_EN_EJB && nomina.getIdEstado() != Constants.EST_NOM_POR_PAGAR)) //|| (idEstado != 0 && nomina.getIdEstado() != idEstado))
					continue;
				nominas.add(nomina);
				
				if (flgNominasEnProgreso == 0 && (nomina.getIdEstado() == Constants.EST_NOM_EN_PROCESO  || nomina.getIdEstado() == Constants.EST_NOM_EN_EJB) ) {
					flgNominasEnProgreso = 1;
				}

			}

			//Variable usada para determinar si se debe colocar el ícono "Actualizar", (1) Sí (0) No
			request.setAttribute("flgNominasEnProgreso", String.valueOf(flgNominasEnProgreso));

			NominaVO nom;
			boolean hayPagables = false;//, mostrarTotal = false;
			if (nominas.size() > 0)
			{
				/*if (actForm.getEstado() != null && (actForm.getEstado().intValue() == Constants.EST_NOM_POR_PAGAR ||
													actForm.getEstado().intValue() == Constants.EST_NOM_PAGADO    ||
													actForm.getEstado().intValue() == Constants.EST_NOM_PAGADO_PARCIALMENTE ||
													actForm.getEstado().intValue() == Constants.EST_NOM_PRECURSADA))
					mostrarTotal = true;*/
				for (Iterator it = nominas.iterator(); it.hasNext();)
				{
					nom = (NominaVO) it.next();
					nom.setIdformateado(Utils.formatRut(nom.getRutEmpresa()));

					/*if (mostrarTotal && nom.getIdCodigoBarras() > 0 && (nom.getIdEstado() == Constants.EST_NOM_POR_PAGAR ||
																		nom.getIdEstado() == Constants.EST_NOM_PAGADO    ||
																		nom.getIdEstado() == Constants.EST_NOM_PAGADO_PARCIALMENTE ||
																		nom.getIdEstado() == Constants.EST_NOM_PRECURSADA) )
						nom.setMonto(Utils.formatMonto(comprobanteMgr.getComprobante(new Long(nom.getIdCodigoBarras())).getTotal()));
					else*/
					nom.setMonto("");

					boolean puedeEditar = (usuarioCP.isEmpresaAdministrada(nom.getRutEmpresa()) ? true : usuarioCP.isConvenioEditor(nom.getRutEmpresa(), nom.getIdConvenio()) ? true : false);
					if (nom.getIdEstado() == Constants.EST_NOM_POR_PAGAR && puedeEditar)
					{// Si el usuario tiene permisos de escritura sobre el convenio)
						hayPagables = true;
						nom.setMostrarChkBox(true);
					}

					nom.setAccion1("");
					nom.setAccion2("");
					nom.setAccion3("");
					nom.setAccion4("");
					nom.setAccion5("");
					nom.setAccion6("");
					nom.setAccion7("");
					nom.setAccion8("");
					nom.setAccion9(""); // NOMINA_EN_LINEA

					if (puedeEditar)
					{
						if (procesoMgr.isNominaConAvisosDeCarga(nom.getEmpresa().getIdEmpresa(), nom.getIdConvenio(), actForm.getTipoNomina())) {
							if (nom.getIdEstado() == Constants.EST_CMP_POR_PAGAR)
								nom.setAccion3("X");
						} else 
							nom.setAccion10("X");
						/*else if (nom.getIdEstado() != Constants.EST_NOM_EN_PROCESO  && nom.getIdEstado() != Constants.EST_NOM_EN_EJB)
							nom.setAccion10("X");
						
						if (nom.getEstado().getAcciones().indexOf("editarNomina") != -1)
							nom.setAccion4("X");

						if (nom.getEstado().getAcciones().indexOf("soloVer") != -1)
							nom.setAccion8("X");*/

					} /*else
					{
						if (nom.getEstado().getAcciones().indexOf("soloVer") != -1)
							nom.setAccion8("X");
						else if (nom.getEstado().getAcciones().indexOf("corregirNomina") != -1 || nom.getEstado().getAcciones().indexOf("editarNomina") != -1)
							nom.setAccion8("X");
					}
					if (nom.getEstado().getAcciones().indexOf("verComprobantes") != -1)
						nom.setAccion2("X");
					if (nom.getEstado().getAcciones().indexOf("detalleComprobante") != -1)
						nom.setAccion6("X");*/
				}
				Collections.sort(nominas);
			} else
			{
				ActionMessages am = new ActionMessages();
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("nominas.noHayPorEstado"));
				this.saveMessages(request.getSession(), am);
			}
			// Usted no tiene permisos para la gestión de ningún Convenio/Empresa.

			/*int pagina = request.getParameter("paginaNumero") != null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
			HashMap paginacion = Utils.llenarPaginacionCL(pagina, nominas);
			actForm.setNominas((List) paginacion.get("data"));*/
			actForm.setNominas(nominas);
			//actForm.setnumeroFilas((List) paginacion.get("paginas"));// enlaces listos, llegar y mostrar. llama a funcion JS

			actForm.setMostrarTotal(false);
			actForm.setMostrarValidar(true);
			actForm.setHayPagables(hayPagables ? 1 : 0);

			tx.commit();

			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			if (tx != null)
				tx.rollback();
			logger.error("Ha ocurrido la siguiente excepcion: ", ex);
			return mapping.findForward("error");
		}
	}
}
