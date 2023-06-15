package cl.araucana.autoconsulta.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.autoconsulta.common.Constants;
import cl.araucana.autoconsulta.common.Servicio;
import cl.araucana.autoconsulta.dao.DAOFactory;
import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.common.env.AppConfig;

import com.schema.util.FileSettings;

/**
 * @version 	1.0
 * @author asepulveda
 */
public class LoginAction extends Action {
	
	private static Logger logger = Logger.getLogger(LoginAction.class);
	
	/*
	 * Se utiliza el valor de "dao-type" en esta clase para determinar si se está en Dummy o REAL
	 * Recurso DAO de Autoconsulta
	 */
	int daoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/autoconsulta/dao-type"));

	public static final String GLOBAL_FORWARD_welcome = "welcome";
	public static final String GLOBAL_FORWARD_loginpage = "loginpage";
	public static final String GLOBAL_FORWARD_logoutpage = "logout";
	public static final String GLOBAL_FORWARD_changeClave = "changeClave";
	public static final String GLOBAL_FORWARD_seleccionaPerfil = "seleccionaPerfil";
	public static final String GLOBAL_FORWARD_changeClave2 = "changeClave2";
	public static final String GLOBAL_FORWARD_seleccionaEmpresaACargo = "seleccionaEmpresaACargo";

	public static final String GLOBAL_FORWARD_passwordPage = "passwordPage";
		
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
			HttpSession session = request.getSession(true);
			System.out.println("---> ENTRA A SERVLET LoginAction ******************************* " );
			// DETECTA SUBAPLICACION			
			String subapp = (String)session.getAttribute("struts.subapplication");
			System.out.println("---> LoginAction *****subapp: "  + subapp);
			logger.debug("En: LoginAction");
			
			session.removeAttribute("security.message");
			
			ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
			
			String target=null;
			DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
			UsuarioVO usuario=null;
			String user = null;
			String tipoUsuario = null;
			
			// Por si viene de seleccion de empresa a cargo
			if (request.getParameter("empresaACargo")==null) {
				user = (String)daf.get("user");
				tipoUsuario =(String)daf.get("tipoUsuario");
			} else {
				tipoUsuario="1";				 
			}
			
			// Ajusta User si es que viene desde el Módulo
			if (subapp!=null && subapp.equals("modulo") && user!=null  && user.length()>3 ) {
				logger.debug("Ajustando user por canal 'modulo': "+user);
				user = user.substring(0,user.length()-1);
				logger.debug("Nuevo user: "+user);
			}

			String pass = (String)daf.get("pass");
			String accion = (String)daf.get("accion");
			String textNewPassword =(String)daf.get("newClave");
			String textReNewPassword =(String)daf.get("reNewClave");
			System.out.println("---> LoginAction ***** pass: "  + pass);
			System.out.println("---> LoginAction ***** accion: "  + accion);
			System.out.println("---> LoginAction ***** textNewPassword: "  + textNewPassword);
			System.out.println("---> LoginAction ***** textReNewPassword: "  + textReNewPassword);
			

			if(tipoUsuario==null || tipoUsuario.length()==0) {
				if(accion!=null && accion.length()>0) {
					// Verifica logoff
					if(accion.equals("logoff")) {
						logger.debug("Logoff");
						session.removeAttribute("datosUsuario");
						session.removeAttribute("application.username");
						session.removeAttribute("encargadoEmpresa");
						session.removeAttribute("nombreEmpresas");
						session.removeAttribute("empresasACargo");
						session.removeAttribute("consulta.detallada");
						session.removeAttribute("totales");
						session.removeAttribute("lista.resumen");
						session.removeAttribute("empresa.convenio");
						session.removeAttribute("periodo");
						session.removeAttribute("tips");
						
						/**
						 * INICIO NUEVO
						 * se elimina la imagen generada al vuelo con la publicidad 
						 * (ULTIMA PRUEBA, ya que se borra en modulo/welcome.jsp y web/)
						 */
						//String path = session.getAttribute("pathPublicidad") != null ? (String) session.getAttribute("pathPublicidad") : "";
	    				//if(path != null && !path.equals(""))
	    				//	cl.araucana.autoconsulta.common.ImageProcessing.deleteImg(path,session.getId());
						/**
						 * FIN NUEVO
						 */
						target=GLOBAL_FORWARD_logoutpage;			
						return mapping.findForward(target);
					}
					else if(accion.equals("changeInicial")) {
						System.out.println("---> LoginAction ***** accion (changeInicial?): "  + accion);
						target=GLOBAL_FORWARD_changeClave;

						// Detectando si es Login por pasos (caso Modulos)
						String pasostr =(String)daf.get("paso");
						if (pasostr!=null && !pasostr.equalsIgnoreCase("")) {
							int paso = Integer.parseInt(pasostr);
							if (paso==1) {
								// va a pedir la 3da password
								target=GLOBAL_FORWARD_changeClave2;
								System.out.println("---> LoginAction ***** PASO=1-->(MODULO) target: "  + target);
								session.setAttribute("volverA","Login.do?accion=changeInicial");			
								return mapping.findForward(target);
							}
						}
																					
						if((textNewPassword==null || textNewPassword.length()==0) && (textReNewPassword==null || textReNewPassword.length()==0)) {
							session.setAttribute("volverA","Login.do?accion=changeInicial");
							System.out.println("---> LoginAction ***** textNewPasswordt==NULL O BLANCO "  + textNewPassword);			
							return mapping.findForward(target);
						}
			
						if(textNewPassword.length()<4 || textReNewPassword.length()<4) {
							session.setAttribute("security.message","errors.security.largo.invalido");
							session.setAttribute("volverA","Login.do?accion=changeInicial");			
							return mapping.findForward(target);
						}
			
						int newPassword=Integer.parseInt(textNewPassword);
						int reNewPassword=Integer.parseInt(textReNewPassword);
			
						if(newPassword!=reNewPassword){

							session.setAttribute("security.message","errors.security.claves.nuevas.no.coinciden");
							session.setAttribute("volverA","Login.do?accion=changeInicial");			
							return mapping.findForward(target);
						}

						usuario = (UsuarioVO)session.getAttribute("datosUsuario");
						System.out.println("---> LoginAction ***** ENTRA A  modificarClavePersonal " );
						delegate.modificarClavePersonal(usuario.getRut(),newPassword);
						System.out.println("---> LoginAction ***** SALE DE  modificarClavePersonal " );
						pass=textNewPassword;
						System.out.println("---> LoginAction ***** pass " + pass);
						user=String.valueOf(usuario.getRut());
						System.out.println("---> LoginAction ***** user " + user);
						session.removeAttribute("volverA");
					}
				}
				
				// Detectando si es Login por pasos (caso Modulos)
				String pasostr =(String)daf.get("paso");
				System.out.println("---> LoginAction ***** Detectando si es Login por pasos (caso Modulos) -->pasostr: " + pasostr);
				if (pasostr!=null && !pasostr.equalsIgnoreCase("")) {
					int paso = Integer.parseInt(pasostr);
					if (paso==1) {
						// va a pedir la password
						target=GLOBAL_FORWARD_passwordPage;
						System.out.println("---> LoginAction *****(1) PASOS=1 (caso Modulos) -->target: " + target);
						return mapping.findForward(target);
					}
				}
								
				logger.debug("Autenticando a: "+user);
				System.out.println("---> LoginAction ***** Autenticando a: " + user);		
				long rutUsuario = -1;
				try {
					rutUsuario = Long.parseLong(user);
				} catch (Exception ex) {
					target=GLOBAL_FORWARD_loginpage;
					System.out.println("---> LoginAction ***** CATCH formato usuario invalido --> target" + target);
					session.setAttribute("security.message","errors.security.formatuserinvalido");			
					return mapping.findForward(target);
				}
			
				int password = -1;
				try {
					password = Integer.parseInt(pass);
				} catch (Exception ex) {
					target=GLOBAL_FORWARD_loginpage;
					System.out.println("---> LoginAction ***** CATCH formato clave invalida --> target" + target);
					session.setAttribute("security.message","errors.security.formatclaveinvalida");
					return mapping.findForward(target);
				}
						 
				//Valida el dao-type para determinar si se está en DUMMY o REAL
				System.out.println("---> LoginAction ***** CATCH formato clave invalida --> target" + target);
				if(daoType==DAOFactory.DUMMY) {
					//DUMMY
					logger.debug("Validando en Dummy");	
					System.out.println("---> LoginAction ***** Validando en Dummy");
					UsuarioVO dummy = new UsuarioVO();
					dummy.setAutenticacion(Constants.AUT_CLAVE_PERSONAL_CORRECTA);
					dummy.setDv("7");
					dummy.setEsAfiliadoActivo(true);
					dummy.setEsAfiliadoCesado(false);
					dummy.setEsAhorrante(true);
					dummy.setEsEmpresa(false);
					dummy.setEsPensionado(true);
					dummy.setNombre("Usuario Dummy");
					dummy.setRut(10546515);
					usuario = dummy;
					session.setAttribute("datosUsuario",dummy);			
				}else {
					//REAL
					logger.debug("Validando contra Base de Datos");
					System.out.println("---> LoginAction ***** Validando contra Base de Datos");
					System.out.println("---> LoginAction ***** entra a getAutenticacion: rutUsuario : " + rutUsuario + " - password: " + password);
					usuario = delegate.getAutenticacion(rutUsuario, password);
					System.out.println("---> LoginAction ***** sale de getAutenticacion: usuario : " + usuario );
				}
				
				session.removeAttribute("encargadoEmpresa");
				session.removeAttribute("nombreEmpresas");
				session.removeAttribute("empresasACargo");	
				session.removeAttribute("totales");
				session.removeAttribute("lista.resumen");
				session.removeAttribute("empresa.convenio");
				session.removeAttribute("periodo");
				session.removeAttribute("tips");
			
			} else {
						
						
				// Detectando si es Login por pasos (caso Modulos)
				String pasostr =(String)daf.get("paso");
				System.out.println("---> LoginAction *****Detectando si es Login por pasos (caso Modulos)");
				if (pasostr!=null && !pasostr.equalsIgnoreCase("")) {
					int paso = Integer.parseInt(pasostr);
					if (paso==1) {
						// va a pedir la password
						target=GLOBAL_FORWARD_passwordPage;
						System.out.println("---> LoginAction *****(2) PASOS=1 (caso Modulos) -->target: " + target);
						return mapping.findForward(target);
					}
				}						
				
				usuario = (UsuarioVO)session.getAttribute("datosUsuario");
			}
			
			if(usuario==null) {
				target=GLOBAL_FORWARD_loginpage;
				System.out.println("---> LoginAction *****usuario=null **target: " + target);
				session.setAttribute("security.message","errors.security.nouser");
			}
			else {				
								
				// Reintentos
				String reintentos  = (String)session.getAttribute("security.tries."+usuario.getRut());
				int totReintentos = (reintentos == null ? 1 : Integer.parseInt(reintentos) + 1);
				System.out.println("---> LoginAction ***** reintentos: " + reintentos);
				System.out.println("---> LoginAction ***** totReintentos: " + totReintentos);
				
				session.setAttribute("security.tries."+usuario.getRut(),String.valueOf(totReintentos));
				switch (usuario.getAutenticacion()) {
					case Constants.AUT_CLAVE_NO_TIENE:
						target=GLOBAL_FORWARD_loginpage;
						System.out.println("---> LoginAction ***** AUT CLAVE NO TIENE -- target: " + target);
						session.setAttribute("security.message","errors.security.noclave");
						break;
					case Constants.AUT_CLAVE_BLOQUEADA:
						target=GLOBAL_FORWARD_loginpage;
						System.out.println("---> LoginAction ***** AUT CLAVE BLOQUEADA -- target: " + target);
						session.setAttribute("security.message","errors.security.clavebloqueada");
						break;
					case Constants.AUT_CLAVE_INCORRECTA:
						
						if (totReintentos>=3) {
							System.out.println("---> LoginAction ***** SI totReintentos>3 ==>bloquearClave (RUT" + usuario.getRut());
							delegate.bloquearClave(usuario.getRut());
							
							target=GLOBAL_FORWARD_loginpage;
							System.out.println("---> LoginAction ***** SALE DE bloquearClave -- target:" + target);
							session.setAttribute("security.message","errors.security.clavebloqueada.new");					
							session.removeAttribute("security.tries."+usuario.getRut());
						} else {
							//verificar que aplicación es primero
							target =
								(subapp != null && subapp.equals("modulo"))
									? GLOBAL_FORWARD_passwordPage
									: GLOBAL_FORWARD_loginpage;
							System.out.println("---> LoginAction ***** verificar que aplicación es primero -- target:" + target);		
							session.setAttribute("security.message","errors.security.claveincorrecta");
						}
						System.out.println("---> LoginAction ***** AUT CLAVE INCORRECTA-- target: " + target);
						break;
						
					case Constants.AUT_CLAVE_INICIAL_CORRECTA:
						session.setAttribute("datosUsuario",usuario);
						session.setAttribute("forzarCambio","ok");
						session.removeAttribute("security.tries."+usuario.getRut());
						session.setAttribute("volverA","Login.do?accion=changeInicial");
						target=GLOBAL_FORWARD_changeClave;
						System.out.println("---> LoginAction ***** AUT_CLAVE_INICIAL_CORRECTA-- target: " + target);
						break;
					case Constants.AUT_CLAVE_PERSONAL_CORRECTA:
						session.setAttribute("datosUsuario",usuario);
						
					    logger.debug("Usuario empresa?: "+usuario.isEsEmpresa());
						logger.debug("Usuario AfilAct?: "+usuario.isEsAfiliadoActivo());
						logger.debug("Usuario AfilPas?: "+usuario.isEsAfiliadoCesado());
						logger.debug("Usuario Pensionado?: "+usuario.isEsPensionado());
						logger.debug("Usuario Ahorrante?: "+usuario.isEsAhorrante());
											
						
						// Verifica si el rut de usuario es persona y 
						// empresa a la vez, si es así debe preguntar 
						// al usuario como va entar a la aplicación (empresa o persona)
						if (usuario.isEsEmpresa()
							&& (usuario.isEsAfiliadoActivo()
								|| usuario.isEsAfiliadoCesado()
								|| usuario.isEsPensionado()
								|| usuario.isEsAhorrante())) {
							if (tipoUsuario == null
								|| tipoUsuario.length() == 0) {
								target=GLOBAL_FORWARD_seleccionaPerfil;
								return mapping.findForward(target);
							}else {
								if(Integer.parseInt(tipoUsuario)==1){ //Seleccionó Empresa
									usuario.setEsAfiliadoActivo(false);
									usuario.setEsAfiliadoCesado(false);
									usuario.setEsAhorrante(false);
									usuario.setEsPensionado(false);
								}
								else{	//Seleccionó persona
									usuario.setEsEmpresa(false);
								}
								session.setAttribute("datosUsuario",usuario);
							}
						} 

						// Verifica caso en que puede ser encargado de empresa
						if (!usuario.isEsEmpresa()) {
							Collection empresas = delegate.getEmpresaACargo(usuario.getRut());
							session.setAttribute("empresasACargo",empresas);
							
							if (empresas!=null && empresas.size()>0) {
								// Usuario es encargado de empresa!!!
								String empresaACargo = request.getParameter("empresaACargo");
								if (empresaACargo == null
									|| empresaACargo.length() == 0) {
										
									// Le carga nombre a las empresas
									Hashtable nomEmpresas = new Hashtable();
									Iterator it = empresas.iterator();
									while (it.hasNext()) {
										EmpresaACargoVO emp = (EmpresaACargoVO)it.next();
										// Data de la empresa
										Collection listaEmpresas = delegate.getDatosEmpresa(emp.getRut());										
										if (listaEmpresas!=null && listaEmpresas.size()>0) {
											EmpresaVO empresa = (EmpresaVO)listaEmpresas.iterator().next();
											nomEmpresas.put(String.valueOf(emp.getRut()),empresa.getNombre());
										} else {
											nomEmpresas.put(String.valueOf(emp.getRut()),"-- sin información --");											
										}
										session.setAttribute("nombreEmpresas",nomEmpresas);
									}									
										
									target=GLOBAL_FORWARD_seleccionaEmpresaACargo;
									return mapping.findForward(target);
								} else {
									logger.debug("Seleccion de Encargado: "+empresaACargo);
									user=String.valueOf(usuario.getRut());
									if(Long.parseLong(empresaACargo)!=-1){ // Seleccionó actuar como empresa!!
										usuario.setEsAfiliadoActivo(false);
										usuario.setEsAfiliadoCesado(false);
										usuario.setEsAhorrante(false);
										usuario.setEsPensionado(false);
										usuario.setEsEncargadoEmpresa(true);
										logger.debug("Ajustando modo a Empresa a Cargo");
										session.setAttribute("encargadoEmpresa",String.valueOf(usuario.getRut()));

										// Valida que sea empresa valida del user
										Iterator it = empresas.iterator();
										boolean found = false;
										while (!found && it.hasNext()) {
											found = ((EmpresaACargoVO)it.next()).getRut()==Long.parseLong(empresaACargo);
										}
																				
										// Data de la empresa
										Collection listaEmpresas = delegate.getDatosEmpresa(Long.parseLong(empresaACargo));										
										if (!found || listaEmpresas==null || listaEmpresas.size()<=0) {
											target=GLOBAL_FORWARD_loginpage;
											session.setAttribute("security.message","errors.security.empresaacargonovalida");
											session.removeAttribute("datosUsuario");
											session.removeAttribute("application.username");
											session.removeAttribute("encargadoEmpresa");
											session.removeAttribute("nombreEmpresas");
											session.removeAttribute("empresasACargo");
											session.removeAttribute("lista.resumen");
											session.removeAttribute("empresa.convenio");
											session.removeAttribute("periodo");
											session.removeAttribute("tips");

											return mapping.findForward(target);			
										}
										EmpresaVO empresa = (EmpresaVO)listaEmpresas.iterator().next();
										
										usuario.setDv(empresa.getDv());
										usuario.setEsEmpresa(true);
										usuario.setNombre(empresa.getNombre());
										
										//Esto es para mantener el rut del usuario autenticado
										//en el caso de los encagados de empresas se utiliza
										//para poder determinar si puede consulta por algun afiliado de su
										//empresa a cargo o no
										usuario.setRutusuarioAutenticado(usuario.getRut());
										
										usuario.setRut(empresa.getRut());
										session.setAttribute("datosUsuario",usuario);
										user=String.valueOf(usuario.getRut());
										
									}									
								}
							}
						} 
						
						session.removeAttribute("forzarCambio");
						session.setAttribute("application.username",user.toUpperCase().trim());							
						
						logger.debug("Obtencion de los Servicios para el usuario");
					    session.removeAttribute("servicios");
					    
					    // Obtiene los servicios para el usuario y para el ambiente de ejecución (web o módulo)	
						System.out.println("---> LoginAction ***** ENTRA A Obtener los servicios para el usuario y para el ambiente de ejecución (web o módulo)");			
						session.setAttribute("servicios",getServicios(usuario,subapp));
						System.out.println("---> LoginAction ***** SALE DE Obtener los servicios para el usuario y para el ambiente de ejecución (web o módulo)");				
						session.removeAttribute("security.tries."+usuario.getRut());
												
					    target=GLOBAL_FORWARD_welcome;	
						System.out.println("---> SALE DE LoginAction ***** target: " + target);
						break;
					default:
						target=GLOBAL_FORWARD_loginpage;
						session.setAttribute("security.message","errors.security.default");
						break;
				}
			}
 		    return mapping.findForward(target);
	}
	
	public ArrayList getServicios(UsuarioVO usuario, String subapp) {
		
		String servicesFile = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
							"/application-settings/common/services-file");
											 					    
		String clavesServicios = FileSettings.getValue(servicesFile,
							"/services-settings/AutoconsultaWeb/services/keys/"+subapp);

		ArrayList servicios = new ArrayList();
		logger.debug("Claves de Servicio: "+clavesServicios);
		if (clavesServicios!=null) {
			StringTokenizer st = new StringTokenizer(clavesServicios,";");
			while (st.hasMoreTokens()) {
				String clave = st.nextToken();
				Servicio servicio = new Servicio();
								
				logger.debug("Aplicando servicio: "+clave);
				servicio.setClave(clave);
				servicio.setNombre(FileSettings.getValue(servicesFile,
				"/services-settings/AutoconsultaWeb/services/"+clave+"/nombre"));
				servicio.setAction(FileSettings.getValue(servicesFile,
				"/services-settings/AutoconsultaWeb/services/"+clave+"/action"));
				servicio.setTipo(Integer.parseInt(FileSettings.getValue(servicesFile,
				"/services-settings/AutoconsultaWeb/services/"+clave+"/tipo")));
				String data = FileSettings.getValue(servicesFile,
				"/services-settings/AutoconsultaWeb/services/"+clave+"/siempreActivo");
				servicio.setSiempreActivo(data!=null && data.equalsIgnoreCase("on"));
								
				// Perfiles
				data = FileSettings.getValue(servicesFile,
					"/services-settings/AutoconsultaWeb/services/"+clave+"/perfil/empresa");
				servicio.setForEmpresa(data!=null && data.equalsIgnoreCase("on"));									
				data = FileSettings.getValue(servicesFile,
					"/services-settings/AutoconsultaWeb/services/"+clave+"/perfil/afiliadoActivo");
				servicio.setForAfiliadoActivo(data!=null && data.equalsIgnoreCase("on") && usuario.isEsAfiliadoActivo());									
				data = FileSettings.getValue(servicesFile,
					"/services-settings/AutoconsultaWeb/services/"+clave+"/perfil/afiliadoPasivo");
				servicio.setForAfiliadoPasivo(data!=null && data.equalsIgnoreCase("on") && usuario.isEsAfiliadoCesado());									
				data = FileSettings.getValue(servicesFile,
					"/services-settings/AutoconsultaWeb/services/"+clave+"/perfil/pensionado");
				servicio.setForPensionado(data!=null && data.equalsIgnoreCase("on") && usuario.isEsPensionado());									
				data = FileSettings.getValue(servicesFile,
					"/services-settings/AutoconsultaWeb/services/"+clave+"/perfil/ahorrante");
				servicio.setForAhorrante(data!=null && data.equalsIgnoreCase("on") && usuario.isEsAhorrante());
								
				// Determina la validez del servicio
				servicio.setValid(
					(servicio.isForEmpresa() && usuario.isEsEmpresa()) || 
					(servicio.isForAfiliadoActivo() && usuario.isEsAfiliadoActivo()) ||
					(servicio.isForAfiliadoPasivo() && usuario.isEsAfiliadoCesado()) ||
					(servicio.isForPensionado() && usuario.isEsPensionado()) ||
					(servicio.isForAhorrante() && usuario.isEsAhorrante()) 
				);
				
				//Valida el dao-type para determinar si se está en DUMMY o REAL
				if(daoType==DAOFactory.DUMMY) {
					servicio.setValid(true); //DUMMY
				}
								
				logger.debug("Se activa servicio?: "+servicio.isValid());
								
				if (servicio.isValid() || servicio.isSiempreActivo())
					servicios.add(servicio);
			}						
		}
		return servicios;
	}
}
