package cl.araucana.adminCpe.presentation.struts.actions.empresa;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
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
import cl.araucana.adminCpe.presentation.mgr.PersonaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.empresa.DetalleEmpresaActionForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ActividadEconomicaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CiudadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComunaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RepresentanteLegalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

import com.bh.talon.User;
/*
* @(#) DetalleEmpresaAction.java 1.24 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.24
 */
public class DetalleEmpresaAction extends AppAction
{
	private static Logger logger = Logger.getLogger(DetalleEmpresaAction.class);
	
	private static final int EDITAR = 0;
	private static final int CREAR = 1;
	private static final int FICHA = 2;
	
	private EmpresaMgr empresaMgr;
	private EntidadesMgr entidadesMgr;
	private ConvenioMgr convenioMgr;
	private PersonaMgr personaMgr;

	/**
	 * llena combos empresa
	 * @param actForm
	 * @param deCombo
	 * @param rutEmpresa
	 * @param tipoOperacion
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(DetalleEmpresaActionForm actForm, boolean deCombo, int rutEmpresa, int tipoOperacion)  throws DaoException 
	{	
		if (tipoOperacion == CREAR) 
		{
			//CCAFs
			List listaCajas = this.entidadesMgr.getEntsCCAF();
			List cajas = new ArrayList();
			EntidadCCAFVO caja;
			for (Iterator it = listaCajas.iterator(); it.hasNext();) {
				caja = (EntidadCCAFVO) it.next();
				cajas.add(new LabelValueBean(caja.getNombre().trim(), Integer.toString(caja.getId())));
			}
			Collections.sort(cajas, LabelValueBean.CASE_INSENSITIVE_ORDER);
			actForm.setCajas(cajas);
			
			//Actividades economicas
			List listaActs = this.empresaMgr.getActividadesEconomicas();
			List actsEconomicas = new ArrayList();
			ActividadEconomicaVO actEc;
			for (Iterator it = listaActs.iterator(); it.hasNext();) {
				actEc = (ActividadEconomicaVO) it.next();
				actsEconomicas.add(new LabelValueBean(actEc.getNombre().trim(), Integer.toString(actEc.getIdActividad())));
			}
			Collections.sort(actsEconomicas, LabelValueBean.CASE_INSENSITIVE_ORDER);
			actForm.setActividadesEconomicas(actsEconomicas);
		}
		
		if (rutEmpresa != -1) 
		{
			List listaSucu = new ArrayList();
			SucursalVO sucursal;
			if (rutEmpresa != -1) 
			{
				List listaSucuEmp = this.empresaMgr.getSucursales(rutEmpresa);
				for (Iterator it = listaSucuEmp.iterator(); it.hasNext();) 
				{
					sucursal = (SucursalVO) it.next();
					listaSucu.add(new LabelValueBean(sucursal.getNombre().trim(), sucursal.getIdSucursal().trim()));
				}
			}
			actForm.setListaSucursales(listaSucu);
		}
		
		List listaMutuales = this.entidadesMgr.getEntsMutual();
		List mutuales = new ArrayList();
		EntidadMutualVO mutual;
		for (Iterator it = listaMutuales.iterator(); it.hasNext();) 
		{
			mutual = (EntidadMutualVO) it.next();
			mutuales.add(new LabelValueBean(mutual.getNombre(), Integer.toString(mutual.getId())));
		}
		Collections.sort(mutuales, LabelValueBean.CASE_INSENSITIVE_ORDER);
		actForm.setMutuales(mutuales);
		
		List regiones = new ArrayList(), ciudades = new ArrayList(), comunas = new ArrayList();
		List listaRegiones = this.entidadesMgr.getRegiones();
		RegionVO region;
		for (Iterator it = listaRegiones.iterator(); it.hasNext();) 
		{
			region = (RegionVO) it.next();
			regiones.add(new LabelValueBean(region.getNombre().trim(), Integer.toString(region.getIdRegion())));
		}

		if (!deCombo) {
			List listaCiudades = this.entidadesMgr.getCiudades(Integer.parseInt(actForm.getOpcRegionCasaMatriz()));
			CiudadVO ciudad;
			for (Iterator it = listaCiudades.iterator(); it.hasNext();) {
				ciudad = (CiudadVO) it.next();
				ciudades.add(new LabelValueBean(ciudad.getNombre().trim(), Integer.toString(ciudad.getIdCiudad())));
			}

			List listaComunas = this.entidadesMgr.getComunas(Integer.parseInt(actForm.getOpcCiudadCasaMatriz()));
			ComunaVO comuna;
			for (Iterator it = listaComunas.iterator(); it.hasNext();) {
				comuna = (ComunaVO) it.next();
				comunas.add(new LabelValueBean(comuna.getNombre().trim(), Integer.toString(comuna.getIdComuna())));
			}
		} else 
		{
			if (actForm.getOpcRegionCasaMatriz() != null) 
			{
				if (actForm.getOpcRegionCasaMatriz().equals("-1")) 
				{
					actForm.setOpcCiudadCasaMatriz("-1");
					actForm.setOpcComunaCasaMatriz("-1");
				} else 
				{
					List listaCiudades = this.entidadesMgr.getCiudades(Integer.parseInt(actForm.getOpcRegionCasaMatriz()));
					CiudadVO ciudad;
					for (Iterator it = listaCiudades.iterator(); it.hasNext();) {
						ciudad = (CiudadVO) it.next();
						ciudades.add(new LabelValueBean(ciudad.getNombre().trim(), Integer.toString(ciudad.getIdCiudad())));
					}

					if (actForm.getOpcCiudadCasaMatriz().equals("-1") && actForm.getOpcComunaCasaMatriz().equals("-1")) 
					{
						//Usuario cambio region
						actForm.setOpcCiudadCasaMatriz("-1");
					} else if (actForm.getOpcComunaCasaMatriz().equals("-1")) 
					{
						//Usuario cambio ciudad
						List listaComunas = this.entidadesMgr.getComunas(Integer.parseInt(actForm.getOpcCiudadCasaMatriz()));
						ComunaVO comuna;
						for (Iterator it = listaComunas.iterator(); it.hasNext();) 
						{
							comuna = (ComunaVO) it.next();
							comunas.add(new LabelValueBean(comuna.getNombre().trim(), Integer.toString(comuna.getIdComuna())));
						}
						actForm.setOpcComunaCasaMatriz("-1");
					}
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
	 * <dd>Si este parametro es empresaEditar, se presentara al usuario la edicion de empresas.
	 * Si es empresaDetalle, se presentara la ficha de la empresa.</dd>
	 * <dt>rutEmpresa</dt>
	 * <dd>El rut de la empresa (<code>int</code>) para la que se quiere mostrar la ficha o editar.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es Editar, se redirecciona a la edicion de empresa. Si es Ver Sucursales se redirecciona
	 * a la lista de sucursales de la empresa <code>rutEmpresa</code>. Si es Ver Convenios se
	 * redirecciona a la lista de convenios de la empresa <code>rutEmpresa</code>. Si es
	 * Guardar, guarda el contenido del formulario en la base de datos. Si es Cancelar, se 
	 * redirecciona a la lista de empresas.</dd>
	 * </dl> 
	 *
	 * @param	usuario		el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param	mapping		el objeto con los mapeos de accion para este <code>Action</code>
	 * @param	form		el objeto <code>ActionForm</code> correspondiente
	 * @param	request		el objeto <code>request</code> con los parametros para procesar
	 * @param	response	el objeto <code>response</code> con la respuesta al cliente
	 * @return	el mapeo de accion correspondiente
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  throws Exception
	{
		DetalleEmpresaActionForm actForm = (DetalleEmpresaActionForm) form;
		ActionMessages am = new ActionMessages(); 
		ActionErrors ae = new ActionErrors(); 
		Session session = null;
		Transaction tx = null;
		int tmpRut = 0, idGrupoTmp = 0;
		boolean bGuardar = false;
		int tipoOperacion = -1;
		try 
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			//Instancia los managers correspondientes
			this.empresaMgr = new EmpresaMgr(session);
			this.entidadesMgr = new EntidadesMgr(session);
			this.convenioMgr = new ConvenioMgr(session);
			this.personaMgr = new PersonaMgr(session);

			GregorianCalendar gCalendar = new GregorianCalendar();

			int rut = -1;

			logger.info("***DetalleEmpresaAction.doTask*** operacion: " + request.getParameter("operacion"));
			
			if (request.getParameter("subSubAccion").equals("empresaEditar"))
				tipoOperacion = EDITAR;
			else if (request.getParameter("subSubAccion").equals("empresaCrear"))
				tipoOperacion = CREAR;
			else if (request.getParameter("subSubAccion").equals("empresaDetalle"))
				tipoOperacion = FICHA;
			else
				tipoOperacion = -1;
			
			logger.info("***DetalleEmpresaAction.doTask*** operacion: " + request.getParameter("operacion")+"," + "Tipo Operacion"+ tipoOperacion);
			
			if ((request.getParameter("comboSucu") != null) && (request.getParameter("comboSucu").equals("true"))) 
			{
				SucursalVO sucu = this.empresaMgr.getSucursal(Utils.desFormatRut(actForm.getRutEmpresaEd()), actForm.getOpcSucursalCasaMatriz().trim());

				actForm.setOpcSucursalCasaMatriz(sucu.getIdSucursal().trim());
				actForm.setCodigoCasaMatriz(sucu.getIdSucursal().trim());
				actForm.setNombreCasaMatriz(sucu.getNombre().trim());
				actForm.setDireccionCasaMatriz(sucu.getDireccion().trim());
				actForm.setNoCasaMatriz(sucu.getNumero().trim());
				actForm.setDptoCasaMatriz(sucu.getDepartamento().trim());
				actForm.setOpcComunaCasaMatriz(Integer.toString(sucu.getComuna().getIdComuna()));
				actForm.setNombreComunaCasaMatriz(sucu.getComuna().getNombre().trim());
				actForm.setOpcCiudadCasaMatriz(Integer.toString(sucu.getComuna().getCiudad().getIdCiudad()));
				actForm.setNombreCiudadCasaMatriz(sucu.getComuna().getCiudad().getNombre().trim());
				actForm.setOpcRegionCasaMatriz(Integer.toString(sucu.getComuna().getCiudad().getRegion().getIdRegion()));
				actForm.setNombreRegionCasaMatriz(sucu.getComuna().getCiudad().getRegion().getNombre().trim());
				actForm.setEmailCasaMatriz(sucu.getEmail().trim());
				
				List fono=Utils.obtieneFono(sucu.getTelefono().trim());
				actForm.setCodigoAreaCasaMatriz((String)fono.get(0));
				actForm.setFonoCasaMatriz((String)fono.get(1));
				actForm.setCelCasaMatriz(sucu.getCelular() != 0 ? Integer.toString(sucu.getCelular()) : "");
				List fax=Utils.obtieneFono(sucu.getFax().trim());
				actForm.setCodigoAreaFaxCasaMatriz((String)fax.get(0));
				actForm.setFaxCasaMatriz((String)fax.get(1));
				
				llenaCombosEdicion(actForm, false, sucu.getIdEmpresa(), tipoOperacion);
				
				return mapping.findForward("exitoEditar");
			}
			
			if ((request.getParameter("operacion") == null)) 
			{
				if (request.getParameter("rutEmpresa") == null) 
				{
					//Usuario cambio los combos de region o ciudad
					if (tipoOperacion == EDITAR)
						llenaCombosEdicion(actForm, true, Utils.desFormatRut(actForm.getRutEmpresaEd()), tipoOperacion);
					else
						llenaCombosEdicion(actForm, true, -1, tipoOperacion);
					tx.commit();
					return mapping.findForward("exitoEditar");
				}
				rut = Integer.parseInt(request.getParameter("rutEmpresa"));
			} else 
			{
				//Lamado desde dentro
				if (request.getParameter("operacion").equals("Editar")) 
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Editar"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "empresaEditar");
					redirect.addParameter("rutEmpresa", Integer.toString(Utils.desFormatRut(actForm.getRutEmpresaEd())));
					
					tx.commit();
					
					return redirect;
				} else if (request.getParameter("operacion").equals("Ver Sucursales")) 
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("ListarSucursales"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", request.getParameter("sucursalesLista"));
					redirect.addParameter("rutEmpresa", Integer.toString(Utils.desFormatRut(actForm.getRutEmpresaEd())));

					tx.commit();
					
					return redirect;
				} else if (request.getParameter("operacion").equals("Ver Convenios")) 
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("ListarConvenios"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", request.getParameter("subAccion"));
					redirect.addParameter("rutEmpresa", Integer.toString(Utils.desFormatRut(actForm.getRutEmpresaEd())));

					tx.commit();
					
					return redirect;
				} else if (request.getParameter("operacion").equals("Guardar"))
				{
					bGuardar = true;

					int rutEmpresaEd = Utils.desFormatRut(actForm.getRutEmpresaEd());
					tmpRut = rutEmpresaEd;
					idGrupoTmp = actForm.getOpcGrupoConvenio();
					EmpresaVO empresa = this.empresaMgr.getEmpresaGet(rutEmpresaEd);
					if (empresa == null) 
					{
						empresa = new EmpresaVO();
						empresa.setIdEmpresa(rutEmpresaEd);
					} else if (tipoOperacion == CREAR) 
					{
						tx.commit();

						//Llena los combos para editar empresa
						llenaCombosEdicion(actForm, false, empresa.getIdEmpresa(), tipoOperacion);
						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "Empresa", Utils.formatRut(empresa.getIdEmpresa())));
						this.saveErrors(request.getSession(), ae);

						return mapping.findForward("exitoEditar");
					}
					
					empresa.setTipoPago(Constants.TIPO_PAGO_OBLIGATORIO);
					empresa.setRazonSocial(actForm.getRazonSocial().trim());
					empresa.setIdRepLegal(new Long(Utils.desFormatRut(actForm.getIdRepLegal())));
					empresa.setIdAdmin(new Long(Utils.desFormatRut(actForm.getIdAdmin())));
					gCalendar.set(Integer.parseInt(actForm.getYearVigRepLegal()),
							Integer.parseInt(actForm.getMesVigRepLegal()) - 1,
							Integer.parseInt(actForm.getDiaVigRepLegal()));
					empresa.setVigenciaRepLegal(new Date(gCalendar.getTimeInMillis()));
					empresa.setPrivada(new Integer(Integer.parseInt(actForm.getTipoEmpresa()) == 0 ? 0 : 1));
					empresa.setHabilitada(new Integer(actForm.getEstadoEmpresa()));

					PersonaVO personaRepLegal = new PersonaVO();
					personaRepLegal.setIdPersona(new Integer(Utils.desFormatRut(actForm.getIdRepLegal())));
					personaRepLegal.setNombres(actForm.getNombreRepLegal());
					personaRepLegal.setApellidoPaterno(actForm.getApPatRepLegal());
					personaRepLegal.setApellidoMaterno(actForm.getApMatRepLegal());
					personaRepLegal.setIdComuna(new Integer(Constants.ID_COMUNA_DEFAULT));

					PersonaVO personaAdmin = new PersonaVO();
					personaAdmin.setIdPersona(new Integer(Utils.desFormatRut(actForm.getIdAdmin())));
					personaAdmin.setNombres(actForm.getNombreAdmin().trim());
					personaAdmin.setApellidoPaterno(actForm.getApPatAdmin().trim());
					personaAdmin.setApellidoMaterno(actForm.getApMatAdmin().trim());
					personaAdmin.setIdComuna(new Integer(Constants.ID_COMUNA_DEFAULT));

					ConvenioVO convenio = new ConvenioVO();
					convenio.setIdMutual(Integer.parseInt(actForm.getOpcMutual()));
					convenio.setCalculoMovPersonal(actForm.getOpcCalculoMovPersonal());
					if (tipoOperacion == CREAR) 
					{
						convenio.setIdCcaf(Integer.parseInt(actForm.getOpcCaja()));
						convenio.setIdActividad(Integer.parseInt(actForm.getOpcActividadEconomica()));
						convenio.setIdGrupoConvenio(actForm.getOpcGrupoConvenio());
					}
// marco Habilita tasa para soportar trab. independientes
					convenio.setMutualCalculoIndividual(Boolean.valueOf(actForm.getOpcCalculoIndividual()).booleanValue() ? 1 : 0);
					if(actForm.getTasaAdicionalMutual()!= null && !actForm.getTasaAdicionalMutual().trim().equals(""))
						convenio.setMutualTasaAdicional(Float.parseFloat(actForm.getTasaAdicionalMutual().replace(',', '.')));
					else
						convenio.setMutualTasaAdicional(0);
					
					if (Integer.parseInt(actForm.getOpcMutual()) != Constants.SIN_MUTUAL) 
					{
//						convenio.setMutualCalculoIndividual(Boolean.valueOf(actForm.getOpcCalculoIndividual()).booleanValue() ? 1 : 0);
						convenio.setMutualNumeroAdherente(Integer.parseInt(actForm.getNumAdherentesMutual()));
//						convenio.setMutualTasaAdicional(Float.parseFloat(actForm.getTasaAdicionalMutual().replace(',', '.')));
					} else 
					{
//						convenio.setMutualCalculoIndividual(0);
						convenio.setMutualNumeroAdherente(0);
//						convenio.setMutualTasaAdicional(0.0f);
					}

					SucursalVO sucursal = new SucursalVO();
					sucursal.setIdEmpresa(empresa.getIdEmpresa());
					if (tipoOperacion == EDITAR)
						sucursal.setIdSucursal(actForm.getOpcSucursalCasaMatriz().trim());
					else if (tipoOperacion == CREAR)
						sucursal.setIdSucursal(actForm.getCodigoCasaMatriz().trim());

					sucursal.setNombre(actForm.getNombreCasaMatriz() == null ? "" : actForm.getNombreCasaMatriz().trim());
					sucursal.setDireccion(actForm.getDireccionCasaMatriz() == null ? "" : actForm.getDireccionCasaMatriz().trim());
					sucursal.setNumero(actForm.getNoCasaMatriz() == null ? "" : actForm.getNoCasaMatriz().trim());
					sucursal.setDepartamento(actForm.getDptoCasaMatriz() == null ? "" : actForm.getDptoCasaMatriz().trim());
					if ((actForm.getOpcComunaCasaMatriz() == null) || (Integer.parseInt(actForm.getOpcComunaCasaMatriz()) == -1))
						sucursal.setComuna(this.entidadesMgr.getComuna(Constants.ID_COMUNA_DEFAULT));
					else
						sucursal.setComuna(this.entidadesMgr.getComuna(Integer.parseInt(actForm.getOpcComunaCasaMatriz())));
					sucursal.setEmail(actForm.getEmailCasaMatriz() == null ? "" : actForm.getEmailCasaMatriz().trim());
					sucursal.setTelefono(actForm.getFonoCasaMatriz() == null && actForm.getCodigoAreaCasaMatriz()==null ? "" : "("+actForm.getCodigoAreaCasaMatriz()+")"+actForm.getFonoCasaMatriz().trim());
					sucursal.setCelular(actForm.getCelCasaMatriz().trim().equals("") ? 0 : Integer.parseInt(actForm.getCelCasaMatriz().trim()));
					
					sucursal.setFax(actForm.getFaxCasaMatriz().length() == 0 && actForm.getCodigoAreaFaxCasaMatriz().length()== 0? "" : "("+actForm.getCodigoAreaFaxCasaMatriz().trim()+")"+ actForm.getFaxCasaMatriz().trim());

					OpcionProcVO opcProc = new OpcionProcVO();
					if (tipoOperacion == CREAR) 
					{
						opcProc.setCalcCcaf(actForm.getCalcularMontoCCAF() == 1 ? true : false);
						opcProc.setCalInp(actForm.getCalcularMontoINP() == 1 ? true : false);
						opcProc.setCalMutual(actForm.getCalcularMontoMutual() == 1 ? true : false);
						opcProc.setCalcTotPrevision(actForm.getCalcularMontoTotalPrev() == 1 ? true : false);
						opcProc.setCalcTotSalud(actForm.getCalcularMontoTotalSalud() == 1 ? true : false);
						opcProc.setCcafSucursal(actForm.getGenCCAFPorSucursal() == 1 ? true : false);
						opcProc.setInpSucursal(actForm.getGenINPPorSucursal() == 1 ? true : false);
						opcProc.setMutualSucursal(actForm.getGenMutualPorSucursal() == 1 ? true : false);
						opcProc.setImprimirPlantillas(actForm.getImprimirPlanillas() == 1 ? true : false);
					}

					if (tipoOperacion == EDITAR) 
					{
						logger.info("a punto de modificar empresa: " + empresa.getIdEmpresa());
						this.empresaMgr.modificaEmpresa(empresa, personaRepLegal, personaAdmin, sucursal, convenio);
					} else if (tipoOperacion == CREAR) 
					{
						logger.info("a punto de crear empresa: " + empresa.getIdEmpresa());
						this.empresaMgr.crearEmpresa(empresa, personaRepLegal, personaAdmin, sucursal, convenio, opcProc);
					}

					if (tipoOperacion == CREAR) 
					{
						tx.commit();

						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Empresa", Utils.formatRut(empresa.getIdEmpresa())));
						this.saveMessages(request.getSession(), am);

						ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
						redirect.addParameter("accion", "admin");
						redirect.addParameter("subAccion", "empresas");
						redirect.addParameter("subSubAccion", "empresaLista");
						return redirect;
					}
					rut = empresa.getIdEmpresa();
					tx.commit();

					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", "admin");
					redirect.addParameter("subAccion", "empresas");
					redirect.addParameter("subSubAccion", "empresaLista");

					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Empresa", Utils.formatRut(rut)));
					this.saveMessages(request.getSession(), am);

					return redirect;
				} else if (request.getParameter("operacion").equals("Cancelar")) 
				{
					//TODO recuerda parametros de busqueda
					/*List lista = (List)request.getSession().getAttribute("listaPath");
					if (lista != null)
					{
						for (int i = lista.size() - 1; i >= 0; i--)
						{
							String enlace = (String)lista.get(i);
							if (enlace.lastIndexOf("Lista") > -1)
							{
								enlace = enlace.substring(enlace.indexOf("\"") + 1, enlace.indexOf("\"", enlace.indexOf("\"") + 1));
								logger.info("redireccionando en cancelar a:" + enlace + "::");

								ActionRedirect redirect = new ActionRedirect(enlace);					
								this.saveMessages(request, am);
								
								return redirect;
							}
						}
					}*/					
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", "admin");
					redirect.addParameter("subAccion", "empresas");
					redirect.addParameter("subSubAccion", "empresaLista");
					redirect.addParameter("limpiaPath", "");
					
					tx.commit();
					
					return redirect;
				}
			}
			
			//Llena los datos para mostrar/editar la empresa
			DecimalFormat dFormat = new DecimalFormat(); 
			RepresentanteLegalVO repLegal;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
			AdministradorVO admin;
			
			EmpresaVO empresa = this.empresaMgr.getEmpresaCasaMatriz(rut);
			logger.info("Empresa obtenida: " + empresa.toString());
			
			actForm.setRutEmpresaEd(Utils.formatRut(empresa.getIdEmpresa()));
			actForm.setRazonSocial(empresa.getRazonSocial().trim());
			actForm.setEstadoEmpresa(empresa.getHabilitada().intValue() != 0 ? "1" : "0");
			
			actForm.setIdRepLegal(Utils.formatRut(empresa.getIdRepLegal().intValue()));
			repLegal = this.empresaMgr.getRepresentanteLegal(empresa.getIdRepLegal().intValue());
			PersonaVO personaRepLegal = this.personaMgr.getPersona(repLegal.getIdRepresentanteLegal());
			actForm.setNombreRepLegal(personaRepLegal.getNombres().trim());
			actForm.setApPatRepLegal(personaRepLegal.getApellidoPaterno().trim());
			actForm.setApMatRepLegal(personaRepLegal.getApellidoMaterno().trim());
			actForm.setNombreCompletoRepLegal(actForm.getNombreRepLegal() + " " + actForm.getApPatRepLegal() + " "
					+ actForm.getApMatRepLegal());
			gCalendar.setTime(empresa.getVigenciaRepLegal() != null ? empresa.getVigenciaRepLegal() : new Date(1));
			dFormat.applyPattern("00");
			actForm.setDiaVigRepLegal(dFormat.format(gCalendar.get(Calendar.DATE)));
			actForm.setMesVigRepLegal(dFormat.format(gCalendar.get(Calendar.MONTH) + 1));
			dFormat.applyPattern("0000");
			actForm.setYearVigRepLegal(dFormat.format(gCalendar.get(Calendar.YEAR)));
			actForm.setVigenciaRepLegal(dateFormat.format(empresa.getVigenciaRepLegal()));

			admin = this.empresaMgr.getAdministrador(empresa.getIdAdmin().intValue());
			actForm.setIdAdmin(Utils.formatRut(admin.getIdAdmin()));
			actForm.setNombreAdmin(admin.getAdministrador().getNombres().trim());
			actForm.setApPatAdmin(admin.getAdministrador().getApellidoPaterno().trim());
			actForm.setApMatAdmin(admin.getAdministrador().getApellidoMaterno().trim());
			actForm.setNombreCompletoAdmin(actForm.getNombreAdmin() + " " + actForm.getApPatAdmin() + " "
					+ actForm.getApMatAdmin());
			actForm.setTipoEmpresa(empresa.getPrivada().toString());

			//Llena los datos de la mutual
			ConvenioVO convenio = (ConvenioVO) this.convenioMgr.getConveniosEmpresa(empresa.getIdEmpresa()).get(0);
			EntidadMutualVO mutual = this.entidadesMgr.getMutual(convenio.getIdMutual());
			
//			 marco habilta la grabacion de los valores asociados a la tasa adicional para soportar trabajadores independientes					
			dFormat.applyPattern("#0.0#");
			actForm.setTasaAdicionalMutual(dFormat.format(convenio.getMutualTasaAdicional()).replace('.', ','));
			actForm.setOpcCalculoIndividual(Boolean.toString(convenio.getMutualCalculoIndividual() != 0));

			if (convenio.getIdMutual() != Constants.SIN_MUTUAL) 
			{
				actForm.setNombreMutual(mutual.getNombre().trim());
				actForm.setOpcMutual(Integer.toString(mutual.getId()));
				actForm.setNumAdherentesMutual(Integer.toString(convenio.getMutualNumeroAdherente()));
//				dFormat.applyPattern("#0.0#");
//				actForm.setTasaAdicionalMutual(dFormat.format(convenio.getMutualTasaAdicional()).replace('.', ','));
//				actForm.setOpcCalculoIndividual(Boolean.toString(convenio.getMutualCalculoIndividual() != 0));
			} else 
			{
//				 Marco 				
//				logger.info("asigna valores sin mutual");
				logger.info("Sin Mutual: opcCalc:" + actForm.getOpcCalculoIndividual() + ":tasa:" + actForm.getTasaAdicionalMutual() + "::");
				actForm.setNombreMutual("Sin Mutual");
				actForm.setOpcMutual("0");
				actForm.setNumAdherentesMutual("");
//				actForm.setTasaAdicionalMutual("");
//				actForm.setOpcCalculoIndividual("");
			}
			actForm.setOpcCalculoMovPersonal(convenio.getCalculoMovPersonal());
			
			//Llena los datos de la casa matriz
			SucursalVO sucu = empresa.getCasaMatriz();
 
			actForm.setOpcSucursalCasaMatriz(sucu.getIdSucursal().trim());
			actForm.setCodigoCasaMatriz(sucu.getIdSucursal().trim());
			actForm.setNombreCasaMatriz(sucu.getNombre().trim());
			actForm.setDireccionCasaMatriz(sucu.getDireccion().trim());
			actForm.setNoCasaMatriz(sucu.getNumero().trim());
			actForm.setDptoCasaMatriz(sucu.getDepartamento().trim());
			actForm.setOpcComunaCasaMatriz(Integer.toString(sucu.getComuna().getIdComuna()));
			actForm.setNombreComunaCasaMatriz(sucu.getComuna().getNombre().trim());
			actForm.setOpcCiudadCasaMatriz(Integer.toString(sucu.getComuna().getCiudad().getIdCiudad()));
			actForm.setNombreCiudadCasaMatriz(sucu.getComuna().getCiudad().getNombre().trim());
			actForm.setOpcRegionCasaMatriz(Integer.toString(sucu.getComuna().getCiudad().getRegion().getIdRegion()));
			actForm.setNombreRegionCasaMatriz(sucu.getComuna().getCiudad().getRegion().getNombre().trim());
			actForm.setEmailCasaMatriz(sucu.getEmail().trim());
			actForm.setCelCasaMatriz(sucu.getCelular() != 0 ? Integer.toString(sucu.getCelular()) : "");
			List fono=Utils.obtieneFono(sucu.getTelefono().trim());
			actForm.setCodigoAreaCasaMatriz((String)fono.get(0));
			actForm.setFonoCasaMatriz((String)fono.get(1));

			List fax=Utils.obtieneFono(sucu.getFax().trim());
			actForm.setCodigoAreaFaxCasaMatriz((String)fax.get(0));
			actForm.setFaxCasaMatriz((String)fax.get(1));

			//Llena los combos para editar empresa
			llenaCombosEdicion(actForm, false, empresa.getIdEmpresa(), tipoOperacion);

			tx.commit();
			
			if ((tipoOperacion == CREAR) || (tipoOperacion == EDITAR)) 
			{
				if (bGuardar) 
				{
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Empresa", Utils.formatRut(empresa.getIdEmpresa())));
					this.saveMessages(request.getSession(), am);
				}

				request.setAttribute("cambioParam", "Edición Empresa");
				return mapping.findForward("exitoEditar");
			} else if (tipoOperacion == FICHA)
				return mapping.findForward("exitoMostrar");
			else
				return null;
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en DetalleEmpresaAction.doTask()", ex);
			if("ERROR_GRUPO_CONVENIO".equals(ex.getMessage()))
			{
				llenaCombosEdicion(actForm, false, tmpRut , tipoOperacion);
				ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.codigogrupo", "" + idGrupoTmp ) );
				this.saveErrors(request.getSession(), ae);				
				if (tx != null)
					tx.rollback();
				return mapping.findForward("exitoEditar");
			}
			if (tx != null)
				tx.rollback();
	
			return mapping.findForward("error");
		}  
	}
}
