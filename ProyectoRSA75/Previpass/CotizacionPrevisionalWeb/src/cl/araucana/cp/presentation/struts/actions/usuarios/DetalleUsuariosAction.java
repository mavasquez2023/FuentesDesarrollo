package cl.araucana.cp.presentation.struts.actions.usuarios;

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

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.CiudadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComunaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Empresa;
import cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio;
import cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.EntidadesMgr;
import cl.araucana.cp.presentation.mgr.ParametroMgr;
import cl.araucana.cp.presentation.mgr.PersonaMgr;
import cl.araucana.cp.presentation.struts.forms.usuario.DetalleUsuariosActionForm;
import cl.araucana.cp.utils.ProxyLDAP;

import com.bh.talon.User;
/*
* @(#) DetalleUsuariosAction.java 1.20 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.20
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

	public DetalleUsuariosAction() 
	{
		super();
		
		this.btns.add("guardar");
		this.btns.add("cancelar");
		this.btns.add("editar");
	}
	/**
	 * llena combos edicion
	 * @param actForm
	 * @param deCombo
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(DetalleUsuariosActionForm actForm, boolean deCombo)  throws DaoException 
	{	
		List regiones, ciudades, comunas;
		regiones = new ArrayList();
		ciudades = new ArrayList();
		comunas = new ArrayList();
		
		List listaRegiones = this.entidadesMgr.getRegiones();
		RegionVO region;
		for (Iterator it = listaRegiones.iterator(); it.hasNext();) {
			region = (RegionVO) it.next();
			regiones.add(new LabelValueBean(region.getNombre().trim(), Integer.toString(region.getIdRegion())));
		}

		if (!deCombo) {
			List listaCiudades = this.entidadesMgr.getCiudades(Integer.parseInt(actForm.getOpcRegion()));
			CiudadVO ciudad;
			for (Iterator it = listaCiudades.iterator(); it.hasNext();) {
				ciudad = (CiudadVO) it.next();
				ciudades.add(new LabelValueBean(ciudad.getNombre().trim(), Integer.toString(ciudad.getIdCiudad())));
			}

			List listaComunas = this.entidadesMgr.getComunas(Integer.parseInt(actForm.getOpcCiudad()));
			ComunaVO comuna;
			for (Iterator it = listaComunas.iterator(); it.hasNext();) {
				comuna = (ComunaVO) it.next();
				comunas.add(new LabelValueBean(comuna.getNombre().trim(), Integer.toString(comuna.getIdComuna())));
			}
		} else {
			if (actForm.getOpcRegion().equals("-1")) {
				actForm.setOpcCiudad("-1");
				actForm.setOpcComuna("-1");
			} else {
				List listaCiudades = this.entidadesMgr.getCiudades(Integer.parseInt(actForm.getOpcRegion()));
				CiudadVO ciudad;
				for (Iterator it = listaCiudades.iterator(); it.hasNext();) {
					ciudad = (CiudadVO) it.next();
					ciudades.add(new LabelValueBean(ciudad.getNombre().trim(), Integer.toString(ciudad.getIdCiudad())));
				}

				if (actForm.getOpcCiudad().equals("-1") && actForm.getOpcComuna().equals("-1")) {
					//Usuario cambio region
					actForm.setOpcCiudad("-1");
				} else if (actForm.getOpcComuna().equals("-1")) {
					//Usuario cambio ciudad
					List listaComunas = this.entidadesMgr.getComunas(Integer.parseInt(actForm.getOpcCiudad()));
					ComunaVO comuna;
					for (Iterator it = listaComunas.iterator(); it.hasNext();) {
						comuna = (ComunaVO) it.next();
						comunas.add(new LabelValueBean(comuna.getNombre().trim(), Integer.toString(comuna.getIdComuna())));
					}
					actForm.setOpcComuna("-1");
				}
			}
		}
		actForm.setRegiones(regiones);
		actForm.setCiudades(ciudades);
		actForm.setComunas(comunas);
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
	 * <dd>Si este parametro es usuarioCrear, se muestra la creacion de un usuario nuevo.
	 * Si el paramtero es usuarioEditar, se muestra la edicion de un usuario existente.</dd>
	 * <dt>idUsuario</dt>
	 * <dd>El id del usuario que se desea editar.</dd>
	 * <dt>combosLugaresClick</dt>
	 * <dd>Opcional. Tiene valor "true" si el request se origino al cambiar uno de los combos de seleccion
	 * de Region o Ciudad. Esto cargara los siguientes combos de lugares.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es "Editar", se redireccionara a la edicion del usuario idUsuario. 
	 * Si es "Guardar, se guarda el contenido del formulario en la base de datos.
	 * Si es "Cancelar", se redirecciona a la lista de usuarios.</dd>
	 * </dl> 
	 *
	 * @param	usuario		el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param	mapping		el objeto con los mapeos de accion para este <code>Action</code>
	 * @param	form		el objeto <code>ActionForm</code> correspondiente
	 * @param	request		el objeto <code>request</code> con los parametros para procesar
	 * @param	response	el objeto <code>response</code> con la respuesta al cliente
	 * @return	el mapeo de accion correspondiente
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DetalleUsuariosActionForm actForm = (DetalleUsuariosActionForm) form;

		ActionMessages am = new ActionMessages(); 
		Session session = null;
		Transaction tx = null;
		int tipoOperacion = -1;
		int idUsuario = -1;
		boolean bGuardar = false;
		UsuarioCP usuarioCP = (UsuarioCP) user; 
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			//Instancia los managers correspondientes
			this.entidadesMgr = new EntidadesMgr(session);
			this.personaMgr = new PersonaMgr(session);
			this.parametroMgr = new ParametroMgr(session);
			
			if (request.getParameter("subSubAccion").equals("usuarioEditar"))
				tipoOperacion = EDITAR;
			else if (request.getParameter("subSubAccion").equals("usuarioCrear"))
				tipoOperacion = CREAR;
			else if (request.getParameter("subSubAccion").equals("usuarioFicha"))
				tipoOperacion = FICHA;
		
			logger.info("***DetalleUsuariosAction.doTask*** operacion: " + request.getParameter("operacion"));
			logger.info("***DetalleUsuariosAction.doTask*** tipoOperacion: " + tipoOperacion);
			if (request.getParameter("operacion") == null) 
			{
				if ((request.getParameter("combosLugaresClick") != null) && request.getParameter("combosLugaresClick").equals("true")) 
				{
					llenaCombosEdicion(actForm, true);
					//tx.commit();
					if (tipoOperacion != CREAR)
						idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
					else 
						idUsuario = 0;
					List permisos = this.personaMgr.getPermEncLectorAdministrados(((PersonaVO) user.getUserReference()).getIdPersona().intValue(), idUsuario);
					actForm.setPermisos(permisos);
					return mapping.findForward("exitoEditar");
				} else if (tipoOperacion != CREAR) 
				{
					idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
				}
			} else 
			{
				if (request.getParameter("operacion").equals(Constants.TXT_BTNS.getProperty("guardar"))) 
				{
					bGuardar = true;

					EncargadoVO encargado;
					PersonaVO persona;
					idUsuario = Utils.desFormatRut(actForm.getIdUsuarioFmt());
					boolean bExiste = false;
					if (tipoOperacion == EDITAR) 
					{
						bExiste = true;
						this.personaMgr.borraPermisosEncargado(usuarioCP.getEmpresasAdmin(), idUsuario);
						session.flush();

						persona = this.personaMgr.getPersona(idUsuario);
					} else 
					{
						persona = this.personaMgr.getPersona(idUsuario);
						bExiste = true;
						if (persona == null) 
						{
							bExiste = false;
							persona = new PersonaVO();
							persona.setIdPersona(new Integer(Utils.desFormatRut(actForm.getIdUsuarioFmt())));
						}
					}

					persona.setNombres(actForm.getNombre().trim());
					persona.setApellidoPaterno(actForm.getApPat().trim());
					persona.setApellidoMaterno(actForm.getApMat().trim());
					persona.setEmail(actForm.getEmail().trim());
					persona.setTelefono(actForm.getFono() == null && actForm.getCodigoFono()==null ? "" : "("+actForm.getCodigoFono()+")"+actForm.getFono().trim());
					persona.setCelular(!actForm.getCelular().trim().equals("") ? Integer.parseInt(actForm.getCelular().trim()) : 0);
					persona.setFax(actForm.getFax().length()==0 && actForm.getCodigoFax().length()==0 ? "" : "("+actForm.getCodigoFax()+")"+actForm.getFax().trim());
					persona.setDireccion(actForm.getDireccion().trim());
					persona.setNumero(actForm.getNumero().trim());
					persona.setDpto(actForm.getDpto().trim());
					persona.setIdComuna(new Integer(Integer.parseInt(actForm.getOpcComuna().trim()) == -1 ? Constants.ID_COMUNA_DEFAULT : Integer.parseInt(actForm.getOpcComuna().trim())));
					boolean result = true;
					String password = null;
					if (tipoOperacion != EDITAR && !bExiste){
						int xx= 0;
						password = ProxyLDAP.getNewPassword();
						result = this.personaMgr.savePersona(persona, password);
					}

					if (result)
					{
						encargado = new EncargadoVO();
						encargado.setIdEncargado(persona.getIdPersona().intValue());
						encargado.setHabilitado(actForm.getOpcHabilitado().equals("0") ? 0 : 1);
						encargado.setPersona(persona);
						encargado.setEmpresa(1);
						this.personaMgr.guardaEncargado(encargado);
						this.personaMgr.guardaAccesoEncargadoLector(encargado, actForm.getPermisos(),Integer.parseInt(usuarioCP.getLogin()));

						tx.commit();
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Usuario", Utils.formatRut(idUsuario)));
						this.saveMessages(request.getSession(), am);
						
						
						if( password != null ){
							//Enviar e-mail con la clave creada
							List listParam = new ArrayList();
							listParam.add(new Integer(Constants.PARAM_MAIL_HOST_LOCAL));
							listParam.add(new Integer(Constants.PARAM_MAIL_FROM));
							listParam.add(new Integer(Constants.PARAM_MAIL_USER));
							listParam.add(new Integer(Constants.PARAM_MAIL_PASS));
							listParam.add(new Integer(Constants.PARAM_MAIL_HOST_TO));
							listParam.add(new Integer(Constants.PARAM_MAIL_PORT));
							
							ParametrosHash parametros = parametroMgr.getParametrosHash(listParam);
							
							String mailTo = persona.getEmail();
							String fullNombre = persona.getNameToShow(); 
							
							this.personaMgr.enviarMailClaveInicial(mailTo, fullNombre, parametros.getValores(), password);
						}
						
					} else
					{
						ActionErrors ae = new ActionErrors(); 
						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.ldap", "Usuario"));
						this.saveErrors(request.getSession(), ae);
					}

					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "usuarioLista");


					return redirect;
				} else if (request.getParameter("operacion").equals(Constants.TXT_BTNS.getProperty("cancelar"))) 
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "usuarioLista");
					
					tx.commit();
					
					return redirect;
				} else if (request.getParameter("operacion").equals(Constants.TXT_BTNS.getProperty("editar"))) 
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Editar"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "usuarioEditar");
					redirect.addParameter("idUsuario", request.getParameter("idUsuario"));
					
					tx.commit();
					
					return redirect;
				}
			}

			List permisos;
			if (tipoOperacion != CREAR) 
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
				actForm.setCodigoFono((String)fono.get(0));
				actForm.setFono((String)fono.get(1));

				actForm.setCelular(encargado.getPersona().getCelular() != 0 ? Integer.toString(encargado.getPersona().getCelular()) : "");
				
				List fax = Utils.obtieneFono(encargado.getPersona().getFax().trim());
				actForm.setCodigoFax((String)fax.get(0));
				actForm.setFax((String)fax.get(1));
				actForm.setDireccion(encargado.getPersona().getDireccion() == null ? "" : encargado.getPersona().getDireccion().trim());
				actForm.setNumero(encargado.getPersona().getNumero() == null ? "" : encargado.getPersona().getNumero().trim());
				actForm.setDpto(encargado.getPersona().getDpto() == null ? "" : encargado.getPersona().getDpto().trim());
				ComunaVO comuna = this.entidadesMgr.getComuna(encargado.getPersona().getIdComuna().intValue());
				actForm.setOpcComuna(Integer.toString(comuna.getIdComuna()));
				actForm.setNombreComuna(comuna.getNombre() == null ? "" : comuna.getNombre().trim());
				actForm.setOpcCiudad(Integer.toString(comuna.getCiudad().getIdCiudad()));
				actForm.setNombreCiudad(comuna.getCiudad().getNombre() == null ? "" : comuna.getCiudad().getNombre().trim());
				actForm.setOpcRegion(Integer.toString(comuna.getCiudad().getRegion().getIdRegion()));
				actForm.setNombreRegion(comuna.getCiudad().getRegion().getNombre() == null ? "" : comuna.getCiudad().getRegion().getNombre().trim());
				actForm.setOpcHabilitado(encargado.getHabilitado() != 0 ? "1" : "0");
				actForm.setNombreHabilitado(encargado.getHabilitado() != 0 ? "Habilitado" : "Deshabilitado");

				//Permisos acceso convenios
				permisos = this.personaMgr.getPermEncLectorAdministrados(((PersonaVO) user.getUserReference()).getIdPersona().intValue(), idUsuario);
			} else 
			{
				if (bGuardar) 
				{
					PersonaVO persona = this.personaMgr.getPersona(idUsuario);
					actForm.setOpcComuna(persona.getIdComuna().toString());

					permisos = this.personaMgr.getPermEncLectorAdministrados(((PersonaVO) user.getUserReference()).getIdPersona().intValue(), idUsuario);
				} else 
				{
					permisos = this.personaMgr.getPermEncLectorAdministrados(((PersonaVO) user.getUserReference()).getIdPersona().intValue(), 0);

					actForm.setOpcRegion("-1");
					actForm.setOpcCiudad("-1");
					actForm.setOpcComuna("-1");
				}
			}
			actForm.setPermisos(permisos);

			//Llena los combos para editar empresa
			llenaCombosEdicion(actForm, false);

			tx.commit();

			if ((tipoOperacion == EDITAR) || (tipoOperacion == CREAR)) 
			{
				if (bGuardar) 
				{
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Usuario", Utils.formatRut(idUsuario)));
					this.saveMessages(request.getSession(), am);
				}

				return mapping.findForward("exitoEditar");
			} else if (tipoOperacion == FICHA)
			{
				List permisosAsignados = actForm.getPermisos();
				if (permisosAsignados != null)
				{
					for (Iterator it = permisosAsignados.iterator(); it.hasNext();)
					{
						GrupoConvenio gc = (GrupoConvenio)it.next();
						for (Iterator it2 = gc.getEmpresas().iterator(); it2.hasNext();)
						{
							Empresa emp = (Empresa)it2.next();
							for (Iterator it3 = emp.getConvenios().iterator(); it3.hasNext();)
							{
								PermEncargadoLector perm = (PermEncargadoLector)it3.next();
								String sucs = perm.getSucursalLector().replaceAll("#", "<br />");
								if (sucs.length() > 0)
									perm.setSucursalLector(sucs);
								else
									perm.setSucursalLector(" ");
							}
						}
					}
				}
				return mapping.findForward("exitoFicha");
			}
			return mapping.findForward("exitoFicha");
		} catch (Exception ex) {
			logger.error("Se produjo una excepcion en DetalleUsuariosAction.doTask()", ex);
			if (tx != null)
				tx.rollback();

			return mapping.findForward("error");
		}
	}
}
