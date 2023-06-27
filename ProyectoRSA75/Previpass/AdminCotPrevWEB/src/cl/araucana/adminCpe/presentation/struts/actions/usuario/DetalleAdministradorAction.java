package cl.araucana.adminCpe.presentation.struts.actions.usuario;

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
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.EntidadesMgr;
import cl.araucana.adminCpe.presentation.mgr.ParametroMgr;
import cl.araucana.adminCpe.presentation.mgr.PersonaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.usuario.DetalleAdministradorActionForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorCajaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CiudadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComunaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

import com.bh.talon.User;

/*
 * @(#) DetalleAdministradorAction.java 123 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author vagurto
 * @author cchamblas
 * 
 * @version 1.23
 */
public class DetalleAdministradorAction extends AppAction
{
	private static Logger logger = Logger.getLogger(DetalleAdministradorAction.class);

	private EntidadesMgr entidadesMgr;
	private PersonaMgr personaMgr;
	private ParametroMgr parametroMgr;

	private static final int CREAR = 0;
	private static final int EDITAR = 1;
	private static final int FICHA = 2;

	/**
	 * llena combos edicion
	 * 
	 * @param actForm
	 * @param deCombo
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(DetalleAdministradorActionForm actForm, boolean deCombo) throws DaoException
	{
		List regiones, ciudades, comunas, cajas;
		regiones = new ArrayList();
		ciudades = new ArrayList();
		comunas = new ArrayList();
		cajas = new ArrayList();

		List listaRegiones = this.entidadesMgr.getRegiones();
		RegionVO region;
		for (Iterator it = listaRegiones.iterator(); it.hasNext();)
		{
			region = (RegionVO) it.next();
			regiones.add(new LabelValueBean(region.getNombre().trim(), Integer.toString(region.getIdRegion())));
		}

		if (!deCombo)
		{
			List listaCiudades = this.entidadesMgr.getCiudades(Integer.parseInt(actForm.getOpcRegion()));
			CiudadVO ciudad;
			for (Iterator it = listaCiudades.iterator(); it.hasNext();)
			{
				ciudad = (CiudadVO) it.next();
				ciudades.add(new LabelValueBean(ciudad.getNombre().trim(), Integer.toString(ciudad.getIdCiudad())));
			}

			List listaComunas = this.entidadesMgr.getComunas(Integer.parseInt(actForm.getOpcCiudad()));
			ComunaVO comuna;
			for (Iterator it = listaComunas.iterator(); it.hasNext();)
			{
				comuna = (ComunaVO) it.next();
				comunas.add(new LabelValueBean(comuna.getNombre().trim(), Integer.toString(comuna.getIdComuna())));
			}
		} else
		{
			if (actForm.getOpcRegion().equals("-1"))
			{
				actForm.setOpcCiudad("-1");
				actForm.setOpcComuna("-1");
			} else
			{
				List listaCiudades = this.entidadesMgr.getCiudades(Integer.parseInt(actForm.getOpcRegion()));
				CiudadVO ciudad;
				for (Iterator it = listaCiudades.iterator(); it.hasNext();)
				{
					ciudad = (CiudadVO) it.next();
					ciudades.add(new LabelValueBean(ciudad.getNombre().trim(), Integer.toString(ciudad.getIdCiudad())));
				}

				if (actForm.getOpcCiudad().equals("-1") && actForm.getOpcComuna().equals("-1"))
				{
					// Usuario cambio region
					actForm.setOpcCiudad("-1");
				} else if (actForm.getOpcComuna().equals("-1"))
				{
					// Usuario cambio ciudad
					List listaComunas = this.entidadesMgr.getComunas(Integer.parseInt(actForm.getOpcCiudad()));
					ComunaVO comuna;
					for (Iterator it = listaComunas.iterator(); it.hasNext();)
					{
						comuna = (ComunaVO) it.next();
						comunas.add(new LabelValueBean(comuna.getNombre().trim(), Integer.toString(comuna.getIdComuna())));
					}
					actForm.setOpcComuna("-1");
				}
			}
		}

		List listaCajas = this.entidadesMgr.getEntsCCAF();
		EntidadCCAFVO caja;
		for (Iterator it = listaCajas.iterator(); it.hasNext();) {
			caja = (EntidadCCAFVO) it.next();
			cajas.add(new LabelValueBean(caja.getNombre().trim(), Integer.toString(caja.getId())));
		}

		actForm.setRegiones(regiones);
		actForm.setCiudades(ciudades);
		actForm.setComunas(comunas);
		actForm.setCajas(cajas);
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
	 * <dd>Si este parametro es usuarioCrear, se muestra la creacion de un usuario nuevo. Si el paramtero es usuarioEditar, se muestra la edicion de un usuario existente.</dd>
	 * <dt>idUsuario</dt>
	 * <dd>El id del usuario que se desea editar.</dd>
	 * <dt>combosLugaresClick</dt>
	 * <dd>Opcional. Tiene valor "true" si el request se origino al cambiar uno de los combos de seleccion de Region o Ciudad. Esto cargara los siguientes combos de lugares.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es "Editar", se redireccionara a la edicion del usuario idUsuario. Si es "Guardar, se guarda el contenido del formulario en la base de datos. Si es "Cancelar", se redirecciona a la
	 * lista de usuarios. Tambien puede ser "Buscar", " Buscar " o " Buscar ", para filtrar con las cajas de busqueda de usuario sin privilegios.</dd>
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
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DetalleAdministradorActionForm actForm = (DetalleAdministradorActionForm) form;

		ActionErrors ae = new ActionErrors();
		ActionMessages am = new ActionMessages();
		Session session = null;
		Transaction tx = null;
		int tipoOperacion = -1;
		int idUsuario = -1;
		boolean bGuardar = false;
		boolean bBuscar = false;

		try
		{
			actForm.setMostrarDatos("SI");
			actForm.setMostrarPermiso("SI");
			actForm.setAdministrador("SI");
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			if (request.getAttribute("idUsuario") != null)
			{
				idUsuario = Integer.parseInt(((String) request.getAttribute("idUsuario")).trim());
				bBuscar = true;
				if (actForm.getOpcCiudad() == null || "".equals(actForm.getOpcCiudad()))
					bBuscar = false;
			}
			// Instancia los managers correspondientes
			this.entidadesMgr = new EntidadesMgr(session);
			this.personaMgr = new PersonaMgr(session);
			this.parametroMgr = new ParametroMgr(session);

			if (request.getParameter("subSubAccion").equals("usuarioEditar"))
			{
				tipoOperacion = EDITAR;
				actForm.setMostrarPermiso("NO");
			} else if (request.getParameter("subSubAccion").equals("usuarioCrear"))
			{
				tipoOperacion = CREAR;
				actForm.setMostrarPermiso("NO");
			} else if (request.getParameter("subSubAccion").equals("usuarioFicha"))
				tipoOperacion = FICHA;

			logger.info("***DetalleAdministradorAction.doTask*** operacion: " + request.getParameter("operacion"));
			String operacion = (String) request.getAttribute("operacion");
			if (operacion == null)
				operacion = request.getParameter("operacion");
			if (operacion == null)
			{
				if ((request.getParameter("combosLugaresClick") != null) && request.getParameter("combosLugaresClick").equals("true"))
				{
					llenaCombosEdicion(actForm, true);
					tx.commit();

					return mapping.findForward("exitoEditar");
				} else if (tipoOperacion != CREAR)
				{
					idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
				}
			} else
			{
				if (operacion.equals("Guardar"))
				{
					bGuardar = true;

					AdministradorVO admin;
					PersonaVO persona;
					if ("".equals(actForm.getIdUsuario()))
						idUsuario = Utils.desFormatRut(actForm.getIdUsuarioFmt());
					else
						idUsuario = Integer.parseInt(actForm.getIdUsuario());
					persona = this.personaMgr.getPersona(idUsuario);
					if ("".equals(actForm.getIdUsuarioFmt()))
						persona = this.personaMgr.getPersona(idUsuario);
					else
					{
						if (persona == null)
						{
							persona = new PersonaVO();
							persona.setIdPersona(new Integer(idUsuario));
						}
						persona.setNombres(actForm.getNombre().trim());
						persona.setApellidoPaterno(actForm.getApPat().trim());
						persona.setApellidoMaterno(actForm.getApMat().trim());
						persona.setEmail(actForm.getEmail().trim());
						persona.setTelefono(actForm.getFono() == null && actForm.getCodigoFono() == null ? "" : "(" + actForm.getCodigoFono() + ")" + actForm.getFono().trim());
						persona.setCelular(!actForm.getCelular().trim().equals("") ? Integer.parseInt(actForm.getCelular().trim()) : 0);
						persona.setFax(actForm.getFax().length() == 0 && actForm.getCodigoFax().length() == 0 ? "" : "(" + actForm.getCodigoFax().trim() + ")" + actForm.getFax().trim());
						persona.setDireccion(actForm.getDireccion().trim());
						persona.setNumero(actForm.getNumero().trim());
						persona.setDpto(actForm.getDpto().trim());
						persona.setIdComuna(new Integer(Integer.parseInt(actForm.getOpcComuna().trim()) == -1 ? Constants.ID_COMUNA_DEFAULT : Integer.parseInt(actForm.getOpcComuna().trim())));
						persona.setAdminAraucana(actForm.isAdminSistemaAraucana());
						persona.setAdminCCAF(actForm.isAdminCCAF());
					}
					boolean result = true;
					String password = getNewPassword();
					if (tipoOperacion == EDITAR)
						actForm.setMostrarPermiso("NO");
					else
						result = this.personaMgr.savePersona(password, persona);

					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "usuarioLista");
					if (result)
					{
						admin = new AdministradorVO();
						admin.setIdAdmin(persona.getIdPersona().intValue());
						admin.setHabilitado(actForm.getOpcHabilitado().equals("0") ? 0 : 1);
						admin.setAdministrador(persona);
						//GMALLEA Ingreso admin
						admin.setEmpresa(actForm.isTipoAdminEmpresa() ? 1 : 0);
						admin.setIndependiente(actForm.isTipoAdminIndependiente() ? 1 : 0);
						
						this.personaMgr.guardaAdministrador(admin);
						this.personaMgr.guardaEmpresasEsAdmin(admin, actForm.getConsultaPermAdminEmpOtros());
						this.personaMgr.validaDeshabilitacion(admin.getHabilitado(), idUsuario);
						
						//Ya que el usuario no es nuevo, no se realiza el cambio de clave.
						if( password != null && tipoOperacion != EDITAR){
							//Enviar e-mail con la clave creada
							List listParam = new ArrayList();
							listParam.add(new Integer(Constants.PARAM_MAIL_HOST_LOCAL));
							listParam.add(new Integer(Constants.PARAM_MAIL_FROM));
							listParam.add(new Integer(Constants.PARAM_MAIL_USER));
							listParam.add(new Integer(Constants.PARAM_MAIL_PASS));
							listParam.add(new Integer(Constants.PARAM_MAIL_HOST_TO));
							listParam.add(new Integer(Constants.PARAM_MAIL_PORT));
							
							ParametrosHash parametros = this.parametroMgr.getParametrosHash(listParam);
							
							String mailTo = persona.getEmail();
							String fullNombre = persona.getNameToShow(); 
							
							this.personaMgr.enviarMailClaveInicial(mailTo, fullNombre, parametros.getValores(), password);
						}

						//TODO Administrador de Cajas
						if(actForm.isAdminCCAF()) {
							this.personaMgr.guardaAdministradorCajas(persona.getIdPersona().intValue(), Integer.parseInt(actForm.getOpcCCAF()));
						}
						
						tx.commit();
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Administrador", Utils.formatRut(idUsuario)));
						this.saveMessages(request.getSession(), am);
					} else
					{
						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.ldap", "Administrador"));
						this.saveErrors(request.getSession(), ae);
					}
					return redirect;
				} else if (operacion.equals("Cancelar"))
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "usuarioLista");

					tx.commit();

					return redirect;
				} else if (operacion.equals("Editar"))
				{
					actForm.setMostrarPermiso("NO");
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Editar"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "usuarioEditar");
					redirect.addParameter("idUsuario", request.getParameter("idUsuario"));

					tx.commit();

					return redirect;
				} else if (operacion.equals("Buscar"))
				{
					if (tipoOperacion != CREAR)
						idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
					bBuscar = true;
				} else if (operacion.equals(" Buscar "))
				{
					if (tipoOperacion != CREAR)
						idUsuario = Integer.parseInt(actForm.getIdUsuario());
					actForm.setMostrarDatos("NO");
					actForm.setMostrarPermiso("SI");
					bBuscar = true;
				} else if (operacion.equals("Siguiente"))
				{
					actForm.setMostrarPermiso("SI");
					actForm.setMostrarDatos("NO");
					if (tipoOperacion != CREAR)
						idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
					bBuscar = true;
				} else if (operacion.equals("Password") || "Password".equals(request.getAttribute("operacion")))
				{
					if (tipoOperacion != CREAR)
						idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
					actForm.setMostrarDatos("SI");
					actForm.setMostrarPermiso("NO");
					if (request.getAttribute("operacion") != null)
						if ("Password".equals(request.getAttribute("operacion")))
							bBuscar = false;
				} else if (operacion.equals("Guardar Password"))
				{
					int intIdUsuario = Integer.parseInt(actForm.getIdUsuario());
					if (this.personaMgr.guardarPassword(actForm.getPassword(), intIdUsuario))
					{
						ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
						redirect.addParameter("accion", request.getParameter("accion"));
						redirect.addParameter("subAccion", request.getParameter("subAccion"));
						redirect.addParameter("subSubAccion", "usuarioLista");
						tx.commit();
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardarPassword", "Administrador", Utils.formatRut(intIdUsuario)));
						this.saveMessages(request.getSession(), am);

						return redirect;
					}
					tx.commit();
					ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.password"));
					request.setAttribute("operacion", "Password");
					request.setAttribute("idUsuario", actForm.getIdUsuario());

					this.saveErrors(request.getSession(), ae);
					return mapping.findForward("refresh");
				}
			}

			// seteo datos tabla despliegue asignacion permisos
			actForm.setIdEncargadoFmt(idUsuario > 0 ? Utils.formatRut(idUsuario) : "");
			actForm.setNombre(actForm.getNombre() != null ? actForm.getNombre().trim() : "");
			actForm.setApellidos((actForm.getApPat() != null ? actForm.getApPat().trim() : "") + " " + (actForm.getApMat() != null ? actForm.getApMat().trim() : ""));

			List empresasAdmin = new ArrayList();
			if (tipoOperacion != CREAR)
			{
				if (!bBuscar)
				{
					AdministradorVO adm = this.personaMgr.getAdministrador(idUsuario);
					if (adm == null)
					{
						adm = new AdministradorVO(idUsuario);
						adm.setAdministrador(this.personaMgr.getPersona(idUsuario));
					}

					actForm.setIdUsuarioFmt(Utils.formatRut(adm.getAdministrador().getIdPersona().intValue()));
					actForm.setNombre(adm.getAdministrador().getNombres().trim());
					actForm.setApPat(adm.getAdministrador().getApellidoPaterno().trim());
					actForm.setApMat(adm.getAdministrador().getApellidoMaterno().trim());
					actForm.setEmail(adm.getAdministrador().getEmail() == null ? "" : adm.getAdministrador().getEmail().trim());

					List fono = Utils.obtieneFono(adm.getAdministrador().getTelefono().trim());
					actForm.setCodigoFono((String) fono.get(0));
					actForm.setFono((String) fono.get(1));

					actForm.setCelular(adm.getAdministrador().getCelular() != 0 ? Integer.toString(adm.getAdministrador().getCelular()) : "");
					List fax = Utils.obtieneFono(adm.getAdministrador().getFax().trim());
					actForm.setCodigoFax((String) fax.get(0));
					actForm.setFax((String) fax.get(1));
					actForm.setDireccion(adm.getAdministrador().getDireccion() == null ? "" : adm.getAdministrador().getDireccion().trim());
					actForm.setNumero(adm.getAdministrador().getNumero() == null ? "" : adm.getAdministrador().getNumero().trim());
					actForm.setDpto(adm.getAdministrador().getDpto() == null ? "" : adm.getAdministrador().getDpto().trim());
					ComunaVO comuna = this.entidadesMgr.getComuna(adm.getAdministrador().getIdComuna().intValue());
					actForm.setOpcComuna(Integer.toString(comuna.getIdComuna()));
					actForm.setNombreComuna(comuna.getNombre() == null ? "" : comuna.getNombre().trim());
					actForm.setOpcCiudad(Integer.toString(comuna.getCiudad().getIdCiudad()));
					actForm.setNombreCiudad(comuna.getCiudad().getNombre() == null ? "" : comuna.getCiudad().getNombre().trim());
					actForm.setOpcRegion(Integer.toString(comuna.getCiudad().getRegion().getIdRegion()));
					actForm.setNombreRegion(comuna.getCiudad().getRegion().getNombre() == null ? "" : comuna.getCiudad().getRegion().getNombre().trim());
					actForm.setOpcHabilitado(adm.getHabilitado() != 0 ? "1" : "0");
					actForm.setNombreHabilitado(adm.getHabilitado() != 0 ? "Habilitado" : "Deshabilitado");
					//TODO GMALLEA Seteamos los valores si el administrador de empresas o independiente..
					actForm.setTipoAdminEmpresa(adm.getEmpresa() == 0 ? false : true);
					actForm.setTipoAdminIndependiente(adm.getIndependiente() == 0 ? false : true);

					actForm.setAdminSistemaAraucana(adm.getAdministrador().isAdminAraucana());

					//TODO Cargas Familiares. CAJAS
					actForm.setAdminCCAF(adm.getAdministrador().isAdminCCAF());
					String nombreCaja;
					int opcCCAF = -1;
					if (adm.getAdministrador().isAdminCCAF()) {
						List admCajas = this.personaMgr.getAdmCajas(adm.getIdAdmin());
						if (admCajas.size() == 1) {
							AdministradorCajaVO admCaja = (AdministradorCajaVO) admCajas.get(0);
							EntidadCCAFVO entidad = this.entidadesMgr.getEntsCCAF(admCaja.getIdCCAF());
							nombreCaja = entidad.getNombre().trim();
							opcCCAF = admCaja.getIdCCAF();
						} else {
							nombreCaja = "Todas";
						}
						actForm.setOpcCCAF(String.valueOf(opcCCAF));
						actForm.setNombreCCAF(nombreCaja);
					}

				}
				empresasAdmin = this.personaMgr.getListaEmpresasEsAdmin(idUsuario);
			} else
			{
				if (bGuardar)
				{
					PersonaVO persona = this.personaMgr.getPersona(idUsuario);
					actForm.setOpcComuna(persona.getIdComuna().toString());
				} else if (!bBuscar)
				{
					actForm.setOpcRegion("-1");
					actForm.setOpcCiudad("-1");
					actForm.setOpcComuna("-1");
					empresasAdmin = new ArrayList();
				} else
					empresasAdmin = this.personaMgr.getListaEmpresasEsAdmin(idUsuario);
			}
			actForm.setConsultaPermAdminEmp(empresasAdmin);
			try
			{
				if (actForm.getIdEncargadoFmt() == null || "".equals(actForm.getIdEncargadoFmt()))
					if (!actForm.getIdUsuarioFmt().equals(""))
						request.setAttribute("idUsuarioNuevoFmt", Utils.formatRut(Utils.desFormatRut(actForm.getIdUsuarioFmt())));
					else
						request.setAttribute("idUsuarioNuevoFmt", "");
			} catch (Exception e)
			{
			}
			/** ***** FILTRO PERMISOS DE ADMINISTRADOR ****** */
			List empresasAdminBorrar = new ArrayList();
			List empresasAdminOtros = new ArrayList();
			EmpresaVO lPAE;
			logger.debug("filtro para permisos administrador:" + actForm.getIdEmpresaAdmin() + ":" + actForm.getNombreEmpresaAdmin() + "::" + actForm.getIdGrConvenio() + "::"
					+ actForm.getNombreGrConvenio() + ":empresasAdmin:" + empresasAdmin.size() + "::");
			int idEmpBuscada = (actForm.getIdEmpresaAdmin() == null) || actForm.getIdEmpresaAdmin().trim().equals("") ? 0 : Utils.desFormatRut(actForm.getIdEmpresaAdmin().trim());
			String nomEmpBuscada = (actForm.getNombreEmpresaAdmin() == null || actForm.getNombreEmpresaAdmin().trim().equals("") ? "" : actForm.getNombreEmpresaAdmin().trim().toUpperCase());
			for (Iterator it = empresasAdmin.iterator(); it.hasNext();)
			{
				lPAE = (EmpresaVO) it.next();
				if (!lPAE.isEsAdmin())
				{
					empresasAdminBorrar.add(lPAE);
					if (idEmpBuscada == 0 && nomEmpBuscada.equals(""))
						continue;
					if (!nomEmpBuscada.equals("") && lPAE.getRazonSocial().trim().toUpperCase().indexOf(nomEmpBuscada) == -1)
						continue;
					if (idEmpBuscada > 0 && lPAE.getIdEmpresa() != idEmpBuscada)
						continue;
					lPAE.setEsAdmin(true);
					empresasAdminOtros.add(lPAE);
					logger.info("add empresasAdminOtros:" + lPAE.getIdEmpresa() + "::");
				}
			}
			List administrador = new ArrayList();
			if (actForm.getIdGrConvenio() != null && actForm.getNombreGrConvenio() != null)
				if (!"".equals(actForm.getIdGrConvenio().trim()) || !"".equals(actForm.getNombreGrConvenio().trim()))
					administrador = this.personaMgr.getListaEmpresasEsAdminGrupo(actForm.getIdGrConvenio(), actForm.getNombreGrConvenio(), idUsuario);

			empresasAdmin.removeAll(empresasAdminBorrar);
			logger.info("resultado busqueda:AdminOtros:" + empresasAdminOtros.size() + ":por grupo:" + administrador.size() + "::");
			if (empresasAdminOtros.size() > 0)
				actForm.setConsultaPermAdminEmpOtros(empresasAdminOtros);
			else
				actForm.setConsultaPermAdminEmpOtros(administrador);

			EmpresaVO empresa = null;
			boolean bAdmin = false;
			for (Iterator it = empresasAdmin.iterator(); it.hasNext();)
			{
				empresa = (EmpresaVO) it.next();
				if (empresa.isEsAdmin())
				{
					bAdmin = true;
					break;
				}
			}
			actForm.setAdmin(bAdmin);
			
			//TODO this.personaMgr.enviarMailClaveInicial(mailTo, fullNombre, parametros.getValores(), password);

			// Llena los combos para editar empresa
			if ("SI".equals(actForm.getMostrarDatos()))
				llenaCombosEdicion(actForm, false);

			tx.commit();

			if ((tipoOperacion == EDITAR) || (tipoOperacion == CREAR))
			{
				if (bGuardar)
				{
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Usuario", Utils.formatRut(idUsuario)));
					this.saveMessages(request.getSession(), am);
				}
				if (operacion != null && operacion.equals("Password"))
				{
					request.setAttribute("cambioParam", "Cambio Password");
					return mapping.findForward("editarPass");
				}
				return mapping.findForward("exitoEditar");
			}
			if (operacion != null)
			{
				if (operacion.equals("Guardar Password"))
				{
					request.setAttribute("cambioParam", "Cambio Password");
					return mapping.findForward("editarPass");
				}
			}

			return mapping.findForward("exitoFicha");
		} catch (Exception ex)
		{
			if (ex.getMessage().equals("ERROR_EMP_HABILITADA"))
			{
				llenaCombosEdicion(actForm, false);
				ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.adminEmpHabilitada", "" + idUsuario));
				this.saveErrors(request.getSession(), ae);
				if (tx != null)
					tx.rollback();
				return mapping.findForward("exitoEditar");
			}
			logger.error("Se produjo una excepcion en DetalleAdministradorAction.doTask()", ex);
			if (tx != null)
				tx.rollback();

			return mapping.findForward("error");
		}
	}
	
	/**
	 * Genera password random de 4 digitos
	 * @return
	 */
	private static String getNewPassword() {
				
		String passwordInicial;
		
		int min=1000;
		int max=9999;
		
		int f= (int)(Math.random()*(max-min))+min;
		
		passwordInicial = String.valueOf( f );
		
		return passwordInicial;						
	}
}
