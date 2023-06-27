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
import cl.araucana.adminCpe.presentation.struts.forms.usuario.DetalleUsuariosActionForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.CiudadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComunaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Empresa;
import cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio;
import cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector;

import com.bh.talon.User;

/*
 * @(#) DetalleUsuarioAction.java 1.34 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.34
 */
public class DetalleUsuariosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(DetalleUsuariosAction.class);

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
	private void llenaCombosEdicion(DetalleUsuariosActionForm actForm, boolean deCombo) throws DaoException
	{
		List regiones, ciudades, comunas;
		regiones = new ArrayList();
		ciudades = new ArrayList();
		comunas = new ArrayList();

		List listaRegiones = this.entidadesMgr.getRegiones();
		RegionVO region;
		for (Iterator it = listaRegiones.iterator(); it.hasNext();)
		{
			region = (RegionVO) it.next();
			regiones.add(new LabelValueBean(region.getNombre().trim(), Integer.toString(region.getIdRegion())));
		}

		if (!deCombo)
		{
			List listaCiudades = this.entidadesMgr.getCiudades(actForm.getOpcRegion());
			CiudadVO ciudad;
			for (Iterator it = listaCiudades.iterator(); it.hasNext();)
			{
				ciudad = (CiudadVO) it.next();
				ciudades.add(new LabelValueBean(ciudad.getNombre().trim(), Integer.toString(ciudad.getIdCiudad())));
			}

			List listaComunas = this.entidadesMgr.getComunas(actForm.getOpcCiudad());
			ComunaVO comuna;
			for (Iterator it = listaComunas.iterator(); it.hasNext();)
			{
				comuna = (ComunaVO) it.next();
				comunas.add(new LabelValueBean(comuna.getNombre().trim(), Integer.toString(comuna.getIdComuna())));
			}
		} else
		{
			if (actForm.getOpcRegion() == -1)
			{
				actForm.setOpcCiudad(-1);
				actForm.setOpcComuna(-1);
			} else
			{
				List listaCiudades = this.entidadesMgr.getCiudades(actForm.getOpcRegion());
				CiudadVO ciudad;
				for (Iterator it = listaCiudades.iterator(); it.hasNext();)
				{
					ciudad = (CiudadVO) it.next();
					ciudades.add(new LabelValueBean(ciudad.getNombre().trim(), Integer.toString(ciudad.getIdCiudad())));
				}

				if (actForm.getOpcCiudad() == -1 && actForm.getOpcComuna() == -1)
				{
					// Usuario cambio region
					actForm.setOpcCiudad(-1);
				} else if (actForm.getOpcComuna() == -1)
				{
					// Usuario cambio ciudad
					List listaComunas = this.entidadesMgr.getComunas(actForm.getOpcCiudad());
					ComunaVO comuna;
					for (Iterator it = listaComunas.iterator(); it.hasNext();)
					{
						comuna = (ComunaVO) it.next();
						comunas.add(new LabelValueBean(comuna.getNombre().trim(), Integer.toString(comuna.getIdComuna())));
					}
					actForm.setOpcComuna(-1);
				}
			}
		}
		actForm.setRegiones(regiones);
		actForm.setCiudades(ciudades);
		actForm.setComunas(comunas);
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
		DetalleUsuariosActionForm actForm = (DetalleUsuariosActionForm) form;
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
			actForm.setEncargado("SI");
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			if (request.getAttribute("idUsuario") != null)
			{
				idUsuario = Integer.parseInt(((String) request.getAttribute("idUsuario")).trim());
				bBuscar = true;
				if (actForm.getOpcCiudad() == 0)
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

			logger.info("***DetalleUsuariosAction.doTask*** operacion: " + request.getParameter("operacion"));
			if (request.getParameter("operacion") == null)
			{
				if ((request.getParameter("combosLugaresClick") != null) && request.getParameter("combosLugaresClick").equals("true"))
				{
					llenaCombosEdicion(actForm, true);
					tx.commit();

					return mapping.findForward("exitoEditar");
				} else if (tipoOperacion != CREAR)
					idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
			} else
			{
				if (request.getParameter("operacion").equals("Guardar") && request.getAttribute("operacion") == null)
				{
					bGuardar = true;
					PersonaVO persona;
					if ("".equals(actForm.getIdUsuario()))
						idUsuario = Utils.desFormatRut(actForm.getIdUsuarioFmt());
					else
						idUsuario = Integer.parseInt(actForm.getIdUsuario());
					if (tipoOperacion == EDITAR)
						persona = this.personaMgr.getPersona(idUsuario);
					else
					{
						persona = this.personaMgr.getPersona(idUsuario);
						if (persona == null)
						{
							persona = new PersonaVO();
							persona.setIdPersona(new Integer(Utils.desFormatRut(actForm.getIdUsuarioFmt())));
							persona.setAdminAraucana(false);
						}
					}
					if ("".equals(actForm.getIdUsuarioFmt()))
						persona = this.personaMgr.getPersona(idUsuario);
					else
					{
						persona.setNombres(actForm.getNombre().trim());
						persona.setApellidoPaterno(actForm.getApPat().trim());
						persona.setApellidoMaterno(actForm.getApMat().trim());
						persona.setEmail(actForm.getEmail().trim());
						persona.setTelefono(actForm.getFono() == null && actForm.getCodigoFono() == null ? "" : "(" + actForm.getCodigoFono().trim() + ")" + actForm.getFono().trim());
						persona.setCelular(!actForm.getCelular().trim().equals("") ? Integer.parseInt(actForm.getCelular().trim()) : 0);
						if (actForm.getFax() != null && actForm.getCodigoFax() != null)
							persona.setFax(actForm.getFax().length() == 0 && actForm.getCodigoFax().length() == 0 ? "" : "(" + actForm.getCodigoFax().trim() + ")" + actForm.getFax().trim());
						else
							persona.setFax("");

						persona.setDireccion(actForm.getDireccion().trim());
						persona.setNumero(actForm.getNumero().trim());
						persona.setDpto(actForm.getDpto().trim());
						persona.setIdComuna(new Integer(actForm.getOpcComuna() == -1 ? Constants.ID_COMUNA_DEFAULT : actForm.getOpcComuna()));
					}
					boolean result = true;
					//TODO GMALLEA	24-04-2012 Se modifica para actualice la password
					String password = null;
					if (tipoOperacion == EDITAR)
						actForm.setMostrarPermiso("NO");
					else{
						//result = this.personaMgr.savePersona(getNewPassword(), persona);
						password = getNewPassword();
						result = this.personaMgr.savePersona(password, persona);
					}
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "usuarioLista");
					if (result)
					{
						EncargadoVO encargado = new EncargadoVO();
						encargado.setIdEncargado(persona.getIdPersona().intValue());
						encargado.setHabilitado(actForm.getOpcHabilitado().equals("1") ? 1 : 0);
						encargado.setPersona(persona);
						//TODO GMALLEA SE GUARDA EL ENCARGADO CON LAS MODIFICACION SI ES DE EMPRESA, INDEPENDIENTE O LOS DOS
						encargado.setEmpresa(actForm.isTipoAdminEmpresa() ? 1 : 0);
						encargado.setIndependiente(actForm.isTipoAdminIndependiente() ? 1 : 0);
						
						this.personaMgr.guardaAccesoEncargadoLector(encargado, actForm.getPermisos(), actForm.getNewPermisos());
						tx.commit();
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Encargado", Utils.formatRut(idUsuario)));
						
						if( password != null && encargado.getHabilitado() == 1){
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
					
						this.saveMessages(request.getSession(), am);
					} else
					{
						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.ldap", "Encargado"));
						this.saveErrors(request.getSession(), ae);
					}
					return redirect;
				} else if (request.getParameter("operacion").equals("Cancelar"))
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "usuarioLista");

					tx.commit();

					return redirect;
				} else if (request.getParameter("operacion").equals("Editar"))
				{
					actForm.setMostrarPermiso("NO");
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Editar"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "usuarioEditar");
					redirect.addParameter("idUsuario", request.getParameter("idUsuario"));

					tx.commit();

					return redirect;
				} else if (request.getParameter("operacion").equals("Buscar"))
				{
					if (tipoOperacion != CREAR)
						idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
					bBuscar = true;
				} else if (request.getParameter("operacion").equals(" Buscar "))
				{
					if (tipoOperacion != CREAR)
						idUsuario = Integer.parseInt(actForm.getIdUsuario());
					actForm.setMostrarDatos("NO");
					actForm.setMostrarPermiso("SI");
					bBuscar = true;
				} else if (request.getParameter("operacion").equals("  Buscar  "))
				{
					if (tipoOperacion != CREAR)
						idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
					bBuscar = true;
				} else if (request.getParameter("operacion").equals("Siguiente"))
				{
					actForm.setMostrarPermiso("SI");
					actForm.setMostrarDatos("NO");
					if (tipoOperacion != CREAR)
						idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
					bBuscar = true;
				} else if (request.getParameter("operacion").equals("Password") || "Password".equals(request.getAttribute("operacion")))
				{
					if (tipoOperacion != CREAR)
						idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
					actForm.setMostrarDatos("SI");
					actForm.setMostrarPermiso("NO");
					if (request.getAttribute("operacion") != null)
						if ("Password".equals(request.getAttribute("operacion")))
							bBuscar = false;
				} else if (request.getParameter("operacion").equals("Guardar Password"))
				{
					int intIdUsuario = Integer.parseInt(actForm.getIdUsuario());
					if (this.personaMgr.guardarPassword(actForm.getPassword(), intIdUsuario))
					{
						ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
						redirect.addParameter("accion", request.getParameter("accion"));
						redirect.addParameter("subAccion", request.getParameter("subAccion"));
						redirect.addParameter("subSubAccion", "usuarioLista");
						tx.commit();
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardarPassword", "Encargado", Utils.formatRut(intIdUsuario)));
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

			if (tipoOperacion != CREAR)
			{
				if (!bBuscar)
				{
					EncargadoVO encargado = this.personaMgr.getEncargado(idUsuario);
					if (encargado == null)
					{
						encargado = new EncargadoVO(idUsuario);
						encargado.setPersona(this.personaMgr.getPersona(idUsuario));
					}
					actForm.setIdUsuarioFmt(Utils.formatRut(encargado.getPersona().getIdPersona().intValue()));
					actForm.setNombre(encargado.getPersona().getNombres().trim());
					actForm.setApPat(encargado.getPersona().getApellidoPaterno().trim());
					actForm.setApMat(encargado.getPersona().getApellidoMaterno().trim());
					actForm.setEmail(encargado.getPersona().getEmail() == null ? "" : encargado.getPersona().getEmail().trim());

					List fono = Utils.obtieneFono(encargado.getPersona().getTelefono().trim());
					actForm.setCodigoFono((String) fono.get(0));
					actForm.setFono((String) fono.get(1));
					actForm.setCelular(encargado.getPersona().getCelular() != 0 ? Integer.toString(encargado.getPersona().getCelular()) : "");

					List fax = Utils.obtieneFono(encargado.getPersona().getFax().trim());
					actForm.setCodigoFax((String) fax.get(0));
					actForm.setFax((String) fax.get(1));

					actForm.setDireccion(encargado.getPersona().getDireccion() == null ? "" : encargado.getPersona().getDireccion().trim());
					actForm.setNumero(encargado.getPersona().getNumero() == null ? "" : encargado.getPersona().getNumero().trim());
					actForm.setDpto(encargado.getPersona().getDpto() == null ? "" : encargado.getPersona().getDpto().trim());
					ComunaVO comuna = this.entidadesMgr.getComuna(encargado.getPersona().getIdComuna().intValue());
					actForm.setOpcComuna(comuna.getIdComuna());
					actForm.setNombreComuna(comuna.getNombre() == null ? "" : comuna.getNombre().trim());
					actForm.setOpcCiudad(comuna.getCiudad().getIdCiudad());
					actForm.setNombreCiudad(comuna.getCiudad().getNombre() == null ? "" : comuna.getCiudad().getNombre().trim());
					actForm.setOpcRegion(comuna.getCiudad().getRegion().getIdRegion());
					actForm.setNombreRegion(comuna.getCiudad().getRegion().getNombre() == null ? "" : comuna.getCiudad().getRegion().getNombre().trim());
					actForm.setOpcHabilitado(encargado.getHabilitado() != 0 ? "1" : "0");
					actForm.setNombreHabilitado(encargado.getHabilitado() != 0 ? "Habilitado" : "Deshabilitado");

					actForm.setAdminSistemaAraucana(false);
					//TODO GMALLEA Se guarda si el encargado es empresa, independiente o los dos
					actForm.setTipoAdminEmpresa(encargado.getEmpresa() == 1 ? true : false);
					actForm.setTipoAdminIndependiente(encargado.getIndependiente() == 1 ? true : false);
					
				}
			} else
			{
				if (bGuardar)
				{
					PersonaVO persona = this.personaMgr.getPersona(idUsuario);
					actForm.setOpcComuna(persona.getIdComuna().intValue());
				} else
				{
					if (!bBuscar)
					{
						actForm.setOpcRegion(-1);
						actForm.setOpcCiudad(-1);
						actForm.setOpcComuna(-1);
					}
				}
			}

			// seteo datos tabla despliegue asignacion permisos
			actForm.setIdEncargadoFmt(idUsuario > 0 ? Utils.formatRut(idUsuario) : "");
			try
			{
				if (actForm.getIdEncargadoFmt() == null || "".equals(actForm.getIdEncargadoFmt()))
				{
					if (!actForm.getIdUsuarioFmt().equals(""))
					{
						actForm.setIdUsuario("" + Utils.desFormatRut(actForm.getIdUsuarioFmt()));
						request.setAttribute("idUsuarioNuevoFmt", Utils.formatRut(Utils.desFormatRut(actForm.getIdUsuarioFmt())));
					} else
						request.setAttribute("idUsuarioNuevoFmt", "");
				}
			} catch (Exception e)
			{
			}
			actForm.setNombre(actForm.getNombre() != null ? actForm.getNombre().trim() : "");
			actForm.setApellidos((actForm.getApPat() != null ? actForm.getApPat().trim() : "") + " " + (actForm.getApMat() != null ? actForm.getApMat().trim() : ""));

			/*
			 * retorna lista con permisos ya asignados, en posicion 0 y lista con permisos por asignar, en posicion 1
			 */
			String razonSocBusqueda = (actForm.getNombreEmpresa() != null ? actForm.getNombreEmpresa().trim() : "");
			String nomGrBusqueda = (actForm.getNombreGrConvenio() != null ? actForm.getNombreGrConvenio().trim() : "");
			int idEmpBusqueda = (actForm.getIdEmpresa() != null && !"".equals(actForm.getIdEmpresa().trim()) ? new Integer(Utils.desFormatRut(actForm.getIdEmpresa().trim())).intValue()
					: Constants.RUT_EMPRESA_FALSA);
			int idGRBusqueda = (actForm.getIdGrConvenio() != null && !"".equals(actForm.getIdGrConvenio().trim()) ? new Integer(actForm.getIdGrConvenio().trim()).intValue() : Constants.ID_GRUPO_FALSO);
			List permisos = this.personaMgr.getPermEncargadoLector(idUsuario, idEmpBusqueda, razonSocBusqueda, idGRBusqueda, nomGrBusqueda);
			actForm.setPermisos((List) permisos.get(0));
			actForm.setNewPermisos((List) permisos.get(1));

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
				if (request.getParameter("operacion") != null && request.getParameter("operacion").equals("Password"))
				{
					request.setAttribute("cambioParam", "Cambio Password");
					return mapping.findForward("editarPass");
				}
				return mapping.findForward("exitoEditar");
			}
			if (request.getParameter("operacion") != null)
			{
				if (request.getParameter("operacion").equals("Guardar Password"))
				{
					request.setAttribute("cambioParam", "Cambio Password");
					return mapping.findForward("editarPass");
				}
			}
			if (tipoOperacion == FICHA)
			{
				List permisosAsignados = actForm.getPermisos();
				if (permisosAsignados != null)
				{
					for (Iterator it = permisosAsignados.iterator(); it.hasNext();)
					{
						GrupoConvenio gc = (GrupoConvenio) it.next();
						for (Iterator it2 = gc.getEmpresas().iterator(); it2.hasNext();)
						{
							Empresa emp = (Empresa) it2.next();
							for (Iterator it3 = emp.getConvenios().iterator(); it3.hasNext();)
							{
								PermEncargadoLector perm = (PermEncargadoLector) it3.next();
								List sucursales = this.personaMgr.getSucursalesPermLector(idUsuario, emp.getIdEmpresa(), perm.getIdConvenio());
								StringBuffer sb = new StringBuffer();
								for (Iterator it4 = sucursales.iterator(); it4.hasNext();)
									sb.append(((SucursalVO) it4.next()).getIdSucursal().trim() + "<br />");
								if (sb.toString().length() > 0)
									perm.setSucursalLector(sb.toString().substring(0, sb.toString().length() - 6));
								else
									perm.setSucursalLector(" ");
							}
						}
					}
				}
				return mapping.findForward("exitoFicha");
			}
			return mapping.findForward("exitoFicha");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en DetalleUsuariosAction.doTask()", ex);
			if (tx != null)
				tx.rollback();

			return mapping.findForward("error");
		}
	}

	/**
	 * parametro
	 * 
	 * @param parametro
	 * @return
	 */
	boolean noVacio(String parametro)
	{
		return parametro != null && !"".equals(parametro);
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
