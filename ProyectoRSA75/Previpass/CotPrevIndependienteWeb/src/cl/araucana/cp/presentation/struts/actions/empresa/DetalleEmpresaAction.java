package cl.araucana.cp.presentation.struts.actions.empresa;

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

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ActividadEconomicaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CiudadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComunaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RepresentanteLegalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.dao.ParametrosDAO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.EmpresaMgr;
import cl.araucana.cp.presentation.mgr.EntidadesMgr;
import cl.araucana.cp.presentation.mgr.PersonaMgr;
import cl.araucana.cp.presentation.struts.forms.DetalleEmpresaActionForm;

import com.bh.talon.User;
/*
* @(#) DetalleEmpresaAction.java 1.28 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author aacuna
 * 
 * @version 1.28
 */
public class DetalleEmpresaAction extends AppAction
{
	private static Logger logger = Logger.getLogger(DetalleEmpresaAction.class);
	
	private EmpresaMgr empresaMgr;
	private EntidadesMgr entidadesMgr;
	private ConvenioMgr convenioMgr;
	private PersonaMgr personaMgr;
	
	private static final int EDITAR = 0;
	private static final int CREAR = 1;
	private static final int FICHA = 2;
	
	public DetalleEmpresaAction() {
		super();
		
		this.btns.add("editar");
		this.btns.add("verDirecciones");
		this.btns.add("verConvenios");
		this.btns.add("guardar");
		this.btns.add("cancelar");
	}
	/**
	 * llena combos edicion
	 * @param actForm
	 * @param deCombo
	 * @param rutEmpresa
	 * @param tipoOperacion
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(DetalleEmpresaActionForm actForm, boolean deCombo, int rutEmpresa, int tipoOperacion)  throws DaoException 
	{
		if (rutEmpresa > 0) 
		{
			List listaSucu = new ArrayList();
			SucursalVO sucursal;
			List listaSucuEmp = this.empresaMgr.getSucursales(rutEmpresa);
			for (Iterator it = listaSucuEmp.iterator(); it.hasNext();) 
			{
				sucursal = (SucursalVO) it.next();
				if(sucursal.getIdSucursal().trim().equals("PARTIC")){
					listaSucu.add(new LabelValueBean("Particular", sucursal.getIdSucursal().trim()));
					
				}else if(sucursal.getIdSucursal().trim().equals("ENVCOR")){
					listaSucu.add(new LabelValueBean("Envio Correspondencia", sucursal.getIdSucursal().trim()));
					
				}else if(sucursal.getIdSucursal().trim().equals("LABORA")){
					listaSucu.add(new LabelValueBean("Laboral", sucursal.getIdSucursal().trim()));
					
				}
				
			}
			actForm.setListaSucursales(listaSucu);
		}

		List mutuales = new ArrayList();
		EntidadMutualVO mutual;
		for (Iterator it = this.entidadesMgr.getEntsMutual().iterator(); it.hasNext();) 
		{
			mutual = (EntidadMutualVO) it.next();
			mutuales.add(new LabelValueBean(mutual.getNombre(), Integer.toString(mutual.getId())));
		}
		Collections.sort(mutuales, LabelValueBean.CASE_INSENSITIVE_ORDER);
		actForm.setMutuales(mutuales);

		if (tipoOperacion == CREAR || tipoOperacion == EDITAR)
		{
			List cajas = new ArrayList();
			EntidadCCAFVO caja;
			for (Iterator it = this.entidadesMgr.getEntsCCAF().iterator(); it.hasNext();) 
			{
				caja = (EntidadCCAFVO) it.next();
				cajas.add(new LabelValueBean(caja.getNombre(), Integer.toString(caja.getId())));
			}
			Collections.sort(cajas, LabelValueBean.CASE_INSENSITIVE_ORDER);
			actForm.setCajas(cajas);
			
			List listaActs = this.empresaMgr.getActividadesEconomicas();
			List actsEconomicas = new ArrayList();
			ActividadEconomicaVO actEc;
			for (Iterator it = listaActs.iterator(); it.hasNext();) 
			{
				actEc = (ActividadEconomicaVO) it.next();
				actsEconomicas.add(new LabelValueBean(actEc.getNombre().trim(), Integer.toString(actEc.getIdActividad())));
			}
			Collections.sort(actsEconomicas, LabelValueBean.CASE_INSENSITIVE_ORDER);
			actForm.setActividadesEconomicas(actsEconomicas);
		}

		List regiones = new ArrayList(), ciudades = new ArrayList(), comunas = new ArrayList();
		
		List listaRegiones = this.entidadesMgr.getRegiones();
		RegionVO region;
		for (Iterator it = listaRegiones.iterator(); it.hasNext();) 
		{
			region = (RegionVO) it.next();
			regiones.add(new LabelValueBean(region.getNombre().trim(), Integer.toString(region.getIdRegion())));
		}

		if (!deCombo && (actForm.getOpcRegionCasaMatriz() != null && !actForm.getOpcRegionCasaMatriz().trim().equals(""))) 
		{
			List listaCiudades = this.entidadesMgr.getCiudades(Integer.parseInt(actForm.getOpcRegionCasaMatriz()));
			CiudadVO ciudad;
			for (Iterator it = listaCiudades.iterator(); it.hasNext();) 
			{
				ciudad = (CiudadVO) it.next();
				ciudades.add(new LabelValueBean(ciudad.getNombre().trim(), Integer.toString(ciudad.getIdCiudad())));
			}

			List listaComunas = this.entidadesMgr.getComunas(Integer.parseInt(actForm.getOpcCiudadCasaMatriz()));
			ComunaVO comuna;
			for (Iterator it = listaComunas.iterator(); it.hasNext();) 
			{
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
					for (Iterator it = listaCiudades.iterator(); it.hasNext();) 
					{
						ciudad = (CiudadVO) it.next();
						ciudades.add(new LabelValueBean(ciudad.getNombre().trim(), Integer.toString(ciudad.getIdCiudad())));
					}
	
					if (actForm.getOpcCiudadCasaMatriz().equals("-1") && actForm.getOpcComunaCasaMatriz().equals("-1"))
						//Usuario cambio region
						actForm.setOpcCiudadCasaMatriz("-1");
					else if (actForm.getOpcComunaCasaMatriz().equals("-1")) 
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
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DetalleEmpresaActionForm actForm = (DetalleEmpresaActionForm) form;
		ActionMessages am = new ActionMessages(); 
		Session session = null;
		Transaction tx = null;
		boolean bGuardar = false;
		int tipoOperacion;
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
			UsuarioCP usuarioCP = (UsuarioCP) usuario;

			logger.info("***DetalleEmpresaAction.doTask*** operacion: " + request.getParameter("operacion"));

			if (request.getParameter("subSubAccion").equals("empresaEditar"))
				tipoOperacion = EDITAR;
			else if (request.getParameter("subSubAccion").equals("empresaCrear"))
				tipoOperacion = CREAR;
			else if (request.getParameter("subSubAccion").equals("empresaDetalle"))
				tipoOperacion = FICHA;
			else
				tipoOperacion = -1;
			
			if ((request.getParameter("comboSucu") != null) && (request.getParameter("comboSucu").equals("true"))) //solo si es edicion
				return cambiaSucursal(mapping, actForm, tipoOperacion);
			if ((request.getParameter("operacion") == null)) 
			{
				if (request.getParameter("rutEmpresa") == null) 
				{
					logger.info("operacion == null, rutEmpresa no viene en request");
					//Usuario cambio los combos de region o ciudad
					if (tipoOperacion == EDITAR)
					{
						llenaCombosEdicion(actForm, true, Utils.desFormatRut(actForm.getRutEmpresaEd()), tipoOperacion);
					//	else
						//llenaCombosEdicion(actForm, true, -1);
						tx.commit();
						return mapping.findForward("exitoEditar");
					}
				} else if (request.getParameter("rutEmpresa") != null && !request.getParameter("rutEmpresa").trim().equals(""))
					rut = Integer.parseInt(request.getParameter("rutEmpresa"));
			} else 
			{
				//Llamado desde dentro
				if (request.getParameter("operacion").equals("Editar")) 
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Editar"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "empresaEditar");
					redirect.addParameter("rutEmpresa", Integer.toString(Utils.desFormatRut(actForm.getRutEmpresaEd())));
					
					tx.commit();				
					return redirect;
				} else if (request.getParameter("operacion").equals("Ver Direcciones")) 
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
					EmpresaVO empresa = new EmpresaVO();
					empresa = this.empresaMgr.getEmpresaGet(Utils.desFormatRut(actForm.getRutEmpresaEd()));
					if (tipoOperacion == CREAR)
					{
						if (empresa != null)
						{
							tx.commit();
							//Llena los combos para editar empresa
							llenaCombosEdicion(actForm, false, empresa.getIdEmpresa(), tipoOperacion);
							actForm.setListaGrupos(this.convenioMgr.getGruposConveniosAdmin(usuarioCP.getIdPersona()));

							ActionErrors ae = new ActionErrors(); 
							ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.existe", "Independiente", Utils.formatRut(empresa.getIdEmpresa())));
							this.saveErrors(request.getSession(), ae);
							return mapping.findForward("exitoEditar");
						}
						empresa = new EmpresaVO(Utils.desFormatRut(actForm.getRutEmpresaEd()));
					}
					
					empresa.setRazonSocial(actForm.getRazonSocial().trim());
					empresa.setIdRepLegal(new Long(Utils.desFormatRut(actForm.getIdRepLegal())));
					gCalendar.set(Integer.parseInt(actForm.getYearVigRepLegal()),
							Integer.parseInt(actForm.getMesVigRepLegal()) - 1,
							Integer.parseInt(actForm.getDiaVigRepLegal()));
					empresa.setVigenciaRepLegal(new Date(gCalendar.getTimeInMillis()));
					empresa.setPrivada(new Integer(Integer.parseInt(actForm.getTipoEmpresa()) == 0 ? 0 : 1));
					empresa.setTipoPago(actForm.getTipoPagoObligatorio() != null ? Constants.TIPO_PAGO_OBLIGATORIO : Constants.TIPO_PAGO_VOLUNTARIAS);
					
					PersonaVO personaRepLegal = new PersonaVO();
					personaRepLegal.setIdPersona(new Integer(Utils.desFormatRut(actForm.getIdRepLegal())));
					personaRepLegal.setNombres(actForm.getNombreRepLegal());
					personaRepLegal.setApellidoPaterno(actForm.getApPatRepLegal());
					personaRepLegal.setApellidoMaterno(actForm.getApMatRepLegal());

					ConvenioVO convenio = new ConvenioVO();
					
					//gmallea carga la caja y actividad economica
					convenio.setIdActividad(actForm.getOpcActEconomica());
					convenio.setIdCcaf(actForm.getOpcCaja());
										
					convenio.setIdMutual(Integer.parseInt(actForm.getOpcMutual()));
					
					ParametrosDAO parametrosDao = new ParametrosDAO(session);
					List listaParams = new ArrayList();
					
					listaParams.add(new Integer(Constants.GRUPO_CONV_TRAB_INDEPENDIENTE_VALOR));			 
					
					ParametrosHash paramHash = parametrosDao.getParametrosHash(listaParams);
					String idGrupoConvenio = paramHash.get("" + Constants.GRUPO_CONV_TRAB_INDEPENDIENTE_VALOR);
					
					convenio.setIdGrupoConvenio(Integer.parseInt(idGrupoConvenio));
// marco habilta la grabacion de los valores asociados a la tasa adicional para soportar trabajadores independientes					
					convenio.setMutualCalculoIndividual(Boolean.valueOf(actForm.getOpcCalculoIndividual()).booleanValue() ? 1 : 0);
					if(actForm.getTasaAdicionalMutual()!= null && !actForm.getTasaAdicionalMutual().trim().equals(""))
						convenio.setMutualTasaAdicional(Float.parseFloat(actForm.getTasaAdicionalMutual().replace(',', '.')));
					else
						convenio.setMutualTasaAdicional(0);
					
					if (Integer.parseInt(actForm.getOpcMutual()) != Constants.SIN_MUTUAL) 
					{
						logger.info("asigna valores mutual a guardar:opcCalc:" + actForm.getOpcCalculoIndividual() + ":num:" + actForm.getNumAdherentesMutual() + ":tasa:" + actForm.getTasaAdicionalMutual() + "::");
//						convenio.setMutualCalculoIndividual(Boolean.valueOf(actForm.getOpcCalculoIndividual()).booleanValue() ? 1 : 0);
						convenio.setMutualNumeroAdherente(Integer.parseInt(actForm.getNumAdherentesMutual()));
//						convenio.setMutualTasaAdicional(Float.parseFloat(actForm.getTasaAdicionalMutual().replace(',', '.')));
					} else 
					{
// Marco 				
//						logger.info("asigna valores sin mutual");
						logger.info("asigna valores sin mutual: opcCalc:" + actForm.getOpcCalculoIndividual() + ":tasa:" + actForm.getTasaAdicionalMutual() + "::");
//						convenio.setMutualCalculoIndividual(0);
						convenio.setMutualNumeroAdherente(0);
//						convenio.setMutualTasaAdicional(0.0f);
					}
					
					SucursalVO sucursal = new SucursalVO();
					if (tipoOperacion == EDITAR)
						sucursal = this.empresaMgr.getSucursal(empresa.getIdEmpresa(), actForm.getOpcSucursalCasaMatriz().trim());
					else
						sucursal.setIdSucursal(actForm.getOpcSucursalCasaMatriz());
					
					sucursal.setNombre(actForm.getNombreCasaMatriz() == null ? "" : actForm.getNombreCasaMatriz().trim());
					sucursal.setDireccion(actForm.getDireccionCasaMatriz() == null ? "" : actForm.getDireccionCasaMatriz().trim());
					sucursal.setNumero(actForm.getNoCasaMatriz() == null ? "" : actForm.getNoCasaMatriz().trim());
					sucursal.setDepartamento(actForm.getDptoCasaMatriz() == null ? "" : actForm.getDptoCasaMatriz().trim());
					if ((actForm.getOpcComunaCasaMatriz() == null) || (Integer.parseInt(actForm.getOpcComunaCasaMatriz()) == -1))
						sucursal.setComuna(this.entidadesMgr.getComuna(Constants.ID_COMUNA_DEFAULT));
					else
						sucursal.setComuna(this.entidadesMgr.getComuna(Integer.parseInt(actForm.getOpcComunaCasaMatriz())));
					sucursal.setEmail(actForm.getEmailCasaMatriz() == null ? "" : actForm.getEmailCasaMatriz().trim());
					sucursal.setTelefono(actForm.getFonoCasaMatriz() == null && actForm.getCodigoAreaCasaMatriz() == null ? "" :"("+actForm.getCodigoAreaCasaMatriz()+")"+ actForm.getFonoCasaMatriz().trim());
					sucursal.setCelular(actForm.getCelCasaMatriz().trim().equals("") ? 0 : Integer.parseInt(actForm.getCelCasaMatriz().trim()));
					sucursal.setFax(actForm.getFaxCasaMatriz().length()<1 && actForm.getCodigoAreaFaxCasaMatriz().length()<1 ? "" : "("+actForm.getCodigoAreaFaxCasaMatriz().trim()+")"+actForm.getFaxCasaMatriz().trim());
					
					if (tipoOperacion == EDITAR) 
					{
						logger.info("a punto de modificar empresa: " + empresa.getIdEmpresa());
						this.empresaMgr.modificaEmpresa(empresa, personaRepLegal, sucursal, convenio);
						this.empresaMgr.borraCRCporEmpresa(empresa.getIdEmpresa());
					} else 
					{
						logger.info("a punto de crear empresa: " + empresa.getIdEmpresa());
						empresa.setIdAdmin(new Long(usuarioCP.getIdPersona()));
						empresa.setIniciacion(new Date((new java.util.Date()).getTime()));
						sucursal.setIdEmpresa(Utils.desFormatRut(actForm.getRutEmpresaEd()));
						empresa.setIdCasaMatriz(sucursal.getIdSucursal());
						empresa.setHabilitada(new Integer(Constants.COD_HABILITACION_EMPRESA));
						//TODO GMALLEA SE MARACA SI LA EMPRESA ES INDEPENDIENTE.
						empresa.setTipo(Constants.TIPO_EMPRESA_INDEPENDIENTE);
						convenio.setIdCcaf(actForm.getOpcCaja());
						convenio.setIdActividad(actForm.getOpcActEconomica());
						
						this.empresaMgr.crearEmpresa(empresa, personaRepLegal, sucursal, convenio);
					}
					
					rut = empresa.getIdEmpresa();
					
					tx.commit();
					
					ActionRedirect redirect = null;
					if(tipoOperacion == EDITAR){
						
						redirect = new ActionRedirect(mapping.findForward("Cancelar"));
						redirect.addParameter("accion", "admin");
						redirect.addParameter("subAccion", "empresas");
						redirect.addParameter("subSubAccion", "empresaLista");
						redirect.addParameter("limpiaPath", "");
						
						
					}else{
//						TODO GMALLEA Parametros requeridos
						redirect = new ActionRedirect(mapping.findForward("CrearNomina"));
						
						redirect.addParameter("idConvenio","1");
						redirect.addParameter("idEmpresa",String.valueOf(empresa.getIdEmpresa()));
						redirect.addParameter("tipoNomina","R");
						redirect.addParameter("accion", "inicio");
						redirect.addParameter("subAccion", "trabajadores");
						redirect.addParameter("subSubAccion", "nominaCrear");
						redirect.addParameter("limpiaPath", "");
						
					}

					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Independiente", Utils.formatRut(rut)));
					this.saveMessages(request.getSession(), am);

					return redirect;
				} else if (request.getParameter("operacion").equals("Cancelar")) 
				{
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
			
			EmpresaVO empresa = new EmpresaVO();
			if (tipoOperacion == EDITAR || tipoOperacion == FICHA)
			{
				empresa = this.empresaMgr.getEmpresa(rut);

				logger.info("Empresa obtenida: " + empresa.toString());			
				actForm.setRutEmpresaEd(Utils.formatRut(empresa.getIdEmpresa()));
				actForm.setRazonSocial(empresa.getRazonSocial().trim());
	//			actForm.setEstadoEmpresa(empresa.getHabilitada().intValue() != 0 ? "1" : "0");
	
				actForm.setIdRepLegal(Utils.formatRut(empresa.getIdRepLegal().intValue()));
				repLegal = this.empresaMgr.getRepresentanteLegal(empresa.getIdRepLegal().intValue());
				PersonaVO personaRepLegal = this.personaMgr.getPersona(repLegal.getIdRepresentanteLegal());
				actForm.setNombreRepLegal(personaRepLegal.getNombres().trim());
				actForm.setApPatRepLegal(personaRepLegal.getApellidoPaterno().trim());
				actForm.setApMatRepLegal(personaRepLegal.getApellidoMaterno().trim());
				actForm.setNombreCompletoRepLegal(actForm.getNombreRepLegal() + " " + actForm.getApPatRepLegal() + " "
						+ actForm.getApMatRepLegal());
				gCalendar.setTime(empresa.getVigenciaRepLegal());
				dFormat.applyPattern("00");
				actForm.setDiaVigRepLegal(dFormat.format(gCalendar.get(Calendar.DATE)));
				actForm.setMesVigRepLegal(dFormat.format(gCalendar.get(Calendar.MONTH) + 1));
				dFormat.applyPattern("0000");
				actForm.setYearVigRepLegal(dFormat.format(gCalendar.get(Calendar.YEAR)));
				actForm.setVigenciaRepLegal(dateFormat.format(empresa.getVigenciaRepLegal()));
				actForm.setTipoEmpresa(empresa.getPrivada().toString());

				//Llena los datos de la mutual
				ConvenioVO convenio = (ConvenioVO) this.convenioMgr.getConveniosEmpresa(empresa.getIdEmpresa()).get(0);
//			  marco los datos tasa para soportar trab independientes
				dFormat.applyPattern("#0.0#");
				actForm.setTasaAdicionalMutual(dFormat.format(convenio.getMutualTasaAdicional()).replace('.', ','));
				actForm.setOpcCalculoIndividual(Boolean.toString(convenio.getMutualCalculoIndividual() != 0));
				
				//gmallea carga la caja y actividad economica
				if(tipoOperacion == FICHA){
				EntidadCCAFVO caja;
					for (Iterator it = this.entidadesMgr.getEntsCCAF().iterator(); it.hasNext();) 
					{
						caja = (EntidadCCAFVO) it.next();
						if(convenio.getIdCcaf() == caja.getId()){		
							
							actForm.setCaja(caja.getNombre());
							break;
						}else{
						
							actForm.setCaja("sin caja");
						}
							
						
						//cajas.add(new LabelValueBean(caja.getNombre(), Integer.toString(caja.getId())));
					}
				
				ActividadEconomicaVO actEc;
				for (Iterator it = this.empresaMgr.getActividadesEconomicas().iterator(); it.hasNext();) 
					{
						actEc = (ActividadEconomicaVO) it.next();
						if(convenio.getIdActividad() == actEc.getIdActividad()){		
							
							actForm.setNomAcEconomina(actEc.getNombre().trim());
							break;
						}
					}
				}
				
				actForm.setOpcActEconomica(convenio.getIdActividad());
				actForm.setOpcActEconomMostrar(convenio.getIdActividad()); 
				actForm.setOpcCaja(convenio.getIdCcaf());
				
				//inicio gmallea
				actForm.setTipoPagoVolountarias(empresa.getTipoPago().trim().equals(Constants.TIPO_PAGO_VOLUNTARIAS.toUpperCase()) ? empresa.getTipoPago().trim() : null);
				actForm.setTipoPagoObligatorio(empresa.getTipoPago().trim().equals(Constants.TIPO_PAGO_OBLIGATORIO.toUpperCase()) ? empresa.getTipoPago().trim() : null);
				//fin gmallea
				
				llenaCombosEdicion(actForm, true, Utils.desFormatRut(actForm.getRutEmpresaEd()), tipoOperacion);
				
				if (convenio != null && convenio.getIdMutual() != Constants.SIN_MUTUAL) 
				{
					EntidadMutualVO mutual = this.entidadesMgr.getMutual(convenio.getIdMutual());
					actForm.setNombreMutual(mutual.getNombre().trim());
					actForm.setOpcMutual(Integer.toString(mutual.getId()));
					actForm.setNumAdherentesMutual(Integer.toString(convenio.getMutualNumeroAdherente()));
//					dFormat.applyPattern("#0.0#");
//					actForm.setTasaAdicionalMutual(dFormat.format(convenio.getMutualTasaAdicional()).replace('.', ','));
//					actForm.setOpcCalculoIndividual(Boolean.toString(convenio.getMutualCalculoIndividual() != 0));
				} else 
				{
					actForm.setNombreMutual("Sin Mutual");
					actForm.setOpcMutual("0");
					actForm.setNumAdherentesMutual("0");
//					actForm.setTasaAdicionalMutual("0");
//					actForm.setOpcCalculoIndividual("0");
				}

				//Llena los datos de la casa matriz
				//if (tipoOperacion == EDITAR)
					cargaSucursal(actForm, empresa);
				
				//Puede editar la empresa solo si es administrador de ella.
				if (usuarioCP.getEmpresasAdmin().contains(new Integer(empresa.getIdEmpresa())))
					actForm.setEditable(true);
				else
					actForm.setEditable(false);
			} else
				actForm.setListaGrupos(this.convenioMgr.getGruposConveniosAdmin(usuarioCP.getIdPersona()));
			//Llena los combos para editar empresa
			llenaCombosEdicion(actForm, false, empresa.getIdEmpresa(), tipoOperacion);
			
			//TODO 13-08-2012 GMALLEA Se setea el id grupo de convenio por defecto
			ParametrosDAO parametrosDao = new ParametrosDAO(session);
			List listaParams = new ArrayList();
			
			listaParams.add(new Integer(Constants.GRUPO_CONV_TRAB_INDEPENDIENTE_VALOR));			 
			
			ParametrosHash paramHash = parametrosDao.getParametrosHash(listaParams);
			String idGrupoConvenio = paramHash.get("" + Constants.GRUPO_CONV_TRAB_INDEPENDIENTE_VALOR);
			
			ArrayList listConvenio = new ArrayList();
			listConvenio.add(new LabelValueBean(""+Constants.GRUPO_CONV_TRAB_INDEPENDIENTE_ID,idGrupoConvenio));
			actForm.setListaGrupos(listConvenio);
			
			tx.commit();
			
			if (tipoOperacion == EDITAR || tipoOperacion == CREAR) 
			{
				if (bGuardar) 
				{
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardada", "Independiente", Utils.formatRut(empresa.getIdEmpresa())));
					this.saveMessages(request.getSession(), am);
				}
				return mapping.findForward("exitoEditar");
			} else if (tipoOperacion == FICHA)
				return mapping.findForward("exitoMostrar");
			else
				return null;
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en DetalleEmpresaAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}

	/**
	 * carga sucursal
	 * @param actForm
	 * @param empresa
	 * @throws DaoException
	 */
	private void cargaSucursal(DetalleEmpresaActionForm actForm, EmpresaVO empresa) throws DaoException 
	{
		SucursalVO sucu = this.empresaMgr.getSucursal(empresa.getIdEmpresa(), empresa.getIdCasaMatriz());
 
		actForm.setOpcSucursalCasaMatriz(sucu.getIdSucursal().trim());
			
			if(sucu.getIdSucursal().trim().equals("PARTIC")){
				actForm.setCodigoCasaMatriz("Particular");
								
			}else if(sucu.getIdSucursal().trim().equals("ENVCOR")){
				actForm.setCodigoCasaMatriz("Envio Correspondencia");
								
			}else if(sucu.getIdSucursal().trim().equals("LABORA")){
				actForm.setCodigoCasaMatriz("Laboral");
								
			}
		
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
		
//		actForm.setFonoCasaMatriz(sucu.getTelefono().trim());
		actForm.setCelCasaMatriz(sucu.getCelular() != 0 ? Integer.toString(sucu.getCelular()) : "");
		
		List fax=Utils.obtieneFono(sucu.getFax().trim());
		actForm.setCodigoAreaFaxCasaMatriz((String)fax.get(0));
		actForm.setFaxCasaMatriz((String)fax.get(1));
//		actForm.setFaxCasaMatriz(sucu.getFax().trim());
	}

	/**
	 * cambia sucursal
	 * @param mapping
	 * @param actForm
	 * @return
	 * @throws DaoException
	 */
	private ActionForward cambiaSucursal(ActionMapping mapping, DetalleEmpresaActionForm actForm, int tipoOperacion) throws DaoException 
	{
		SucursalVO sucu = this.empresaMgr.getSucursal(Utils.desFormatRut(actForm.getRutEmpresaEd()), actForm.getOpcSucursalCasaMatriz().trim());

		actForm.setOpcSucursalCasaMatriz(sucu.getIdSucursal().trim());
		
		if(sucu.getIdSucursal().trim().equals("PARTIC")){
			actForm.setCodigoCasaMatriz("Particular");
										
		}else if(sucu.getIdSucursal().trim().equals("ENVCOR")){
			actForm.setCodigoCasaMatriz("Envio Correspondencia");
										
		}else if(sucu.getIdSucursal().trim().equals("LABORA")){
			actForm.setCodigoCasaMatriz("Laboral");
										
		}
		
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
		
//		actForm.setFonoCasaMatriz(sucu.getTelefono().trim());
		List fono=Utils.obtieneFono(sucu.getTelefono().trim());
		actForm.setCodigoAreaCasaMatriz((String)fono.get(0));
		actForm.setFonoCasaMatriz((String)fono.get(1));		
		
		actForm.setCelCasaMatriz(sucu.getCelular() != 0 ? Integer.toString(sucu.getCelular()) : "");
		
		
		List fax=Utils.obtieneFono(sucu.getFax().trim());
		actForm.setCodigoAreaFaxCasaMatriz((String)fax.get(0));
		actForm.setFaxCasaMatriz((String)fax.get(1));
//		actForm.setFaxCasaMatriz(sucu.getFax().trim());

		llenaCombosEdicion(actForm, false, sucu.getIdEmpresa(), tipoOperacion);

		return mapping.findForward("exitoEditar");
	}
}
