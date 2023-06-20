package cl.araucana.ldap.business;

import java.util.*;

import org.apache.log4j.Logger;

import cl.araucana.core.registry.Enterprise;
import cl.araucana.core.registry.User;
import cl.araucana.ldap.api.*;
import cl.araucana.ldap.dao.GestorClavesDAO;
import cl.araucana.ldap.dao.LdapDB2DAO;
import cl.araucana.ldap.ibatis.vo.RegistroVO;
import cl.araucana.ldap.mail.EnviaMail;
import cl.araucana.ldap.mail.FormatoMail;
import cl.araucana.ldap.ws.ClienteInfoAfiliado;
import cl.araucana.ldap.ws.vo.SalidainfoAfiliadoVO;

public class IngresaUsuarioLDAP {

	private static Logger log = Logger.getLogger(IngresaUsuarioLDAP.class);
	
	public static synchronized HashMap procesarUsuarios(String enviarMail, String listamail){
		
		HashMap resultado = new HashMap();
		
		//Se obtienen tods los registros nuevos de DB2 a procesar
		List registros = LdapDB2DAO.obtenerRegistros("custom.obtener_data_LDAP1000", null);
		log.info("Cantidad de registros a procesar Usuarios:" + registros.size());
		int ok=0;
		for (Iterator iter = registros.iterator(); iter.hasNext();) {
			UsuarioVO usuario = (UsuarioVO) iter.next();
			String pass= usuario.getClave().trim();
			if(pass.equals("")){
				//Se genera la password con los primeros 4 digitos del Rut
				pass = usuario.getUsername().split("-")[0].substring(0, 4);
				usuario.setClave(pass);
			}
			//Se inserta usuario en LDAP
			String mensajeLDAP="";
			if(usuario.getEstado().equalsIgnoreCase("E")){
				mensajeLDAP= ProxyLDAP.deleteUser(usuario.getUsername());
			}else{
				mensajeLDAP= ProxyLDAP.procesaUser(usuario);
			} 
			if(mensajeLDAP!=null && !mensajeLDAP.equals("") && !mensajeLDAP.equals("NEW")){
				log.warn("Problemas en creación o modificación usuario, mensaje: " + mensajeLDAP);
			}
			boolean exist=false;
			//Validación de Creación en LDAP
			if(ProxyLDAP.getUser(usuario.getUsername().trim())!= null){
				exist= true;
			}
			boolean add= usuario.getEstado().equalsIgnoreCase("N") || usuario.getEstado().equalsIgnoreCase("M");
			if(add && exist){
				usuario.setEstado("");
//				Se actualiza estado de registro en DB2
				LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP1000", usuario);
				if(enviarMail.equals("true")){
					String mailusuario= usuario.getEmail();
					if(mailusuario!= null && !mailusuario.trim().equals("")){
						
						EnviaMail.enviarMail("Creación de su cuenta La Araucana. ",mailusuario , null,FormatoMail.obtenerTextoMailLdapOK(usuario.getUsername(), pass, usuario.getNombres(), usuario.getApellidoPaterno()));
					}
				}
				ok++;
			}
			else if(add && !exist){
				usuario.setEstado("X");
				usuario.setClave("");
//				Se actualiza estado de registro en DB2
				LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP1000", usuario);
				EnviaMail.enviarMail("Error en creación de cuenta LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapUsuarioError(usuario.getUsername(), mensajeLDAP));
			}
			if(!add && exist){
				usuario.setEstado("Y");
//				Se actualiza estado de registro en DB2
				LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP1000", usuario);
				EnviaMail.enviarMail("Error en eliminación de cuenta LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapUsuarioError(usuario.getUsername(), mensajeLDAP));
			}
			else if(!add && !exist){
//				Se elimina registro en DB2
				LdapDB2DAO.ejecutarDelete("custom.delete_reg_LDAP1000", usuario);
				ok++;
			}
			
			
		}
		resultado.put("RESULTADO", ok + " de " + registros.size() + " procesados exitosamente.");
		return resultado;
	}
	
public static synchronized HashMap procesarEmpresas(String listamail){
		
		HashMap resultado = new HashMap();
		
		//Se obtienen tods los registros nuevos de DB2 a procesar
		List registros = LdapDB2DAO.obtenerRegistros("custom.obtener_data_LDAP2000", null);
		log.info("Cantidad de registros a procesar Empresas:" + registros.size());
		int ok=0;
		for (Iterator iter = registros.iterator(); iter.hasNext();) {
			EmpresaVO empresa = (EmpresaVO) iter.next();
			
			//Se inserta empresa en LDAP
			String mensajeLDAP= ProxyLDAP.createEnterprise(empresa);
			
			//Validación de Cración en LDAP
			HashMap param= new HashMap();
			if(ProxyLDAP.getEnterprise(empresa.getRutEmpresa().trim())!= null && mensajeLDAP==null){
				param.put("estado", "");
				param.put("rutEmpresa", empresa.getRutEmpresa().trim());
				//Se actualiza estado de registro en DB2
				LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP2000", param);
				//Si está habilitado el envío de correo en archivo properties se envía mail a cliente.
				ok++;
			}
			else{
				param.put("estado", "X");
				param.put("rutEmpresa", empresa.getRutEmpresa().trim());
				//Se actualiza estado de registro en DB2 a Error
				LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP2000", param); 
				//String mailsCC= PrevipassDAO.obtenerRegistro("custom.obtenerDataMail",null).get("VALOR").toString();
				
				EnviaMail.enviarMail("Error en creación de Empresa LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapError(empresa.getRutEmpresa(), mensajeLDAP));
			}
		}
		resultado.put("RESULTADO", ok + " de " + registros.size() + " procesados exitosamente.");
		return resultado;
	}
	
public static synchronized HashMap procesarRolesEnterprise(String listamail){
	
	HashMap resultado = new HashMap();
		
	//Se obtiene aplicaciones y roles para mapear según registro obtenido por DB2
	HashMap mapeo= new HashMap();
	List apps= (List)ProxyLDAP.getApplications();
	for (Iterator iter = apps.iterator(); iter.hasNext();) {
		String app = (String) iter.next();
		List roles= (List)ProxyLDAP.getAppRoles(app);
		for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
			String rol = (String) iterator.next();
			mapeo.put(rol, app);
		}
	}
	
	//Se obtienen tods los registros nuevos de DB2 a procesar
	List registros = LdapDB2DAO.obtenerRegistros("custom.obtener_data_LDAP2500", null);
	log.info("Cantidad de registros a procesar Roles Empresas:" + registros.size());
	int ok=0;
	for (Iterator iter = registros.iterator(); iter.hasNext();) {
		RolUsuarioEmpVO regrolapp = (RolUsuarioEmpVO) iter.next();
		Object appmapeo= mapeo.get(regrolapp.getApprol());
		String mensajeLDAP=null;
		if(appmapeo==null){
			mensajeLDAP="No existe una aplicación asociada al Rol:" + regrolapp.getApprol();
		}else{
			regrolapp.setApp(appmapeo.toString());
			//Se inserta usuario en LDAP
			mensajeLDAP= ProxyLDAP.assignAppRolEnterprise(regrolapp);
		}
		if(mensajeLDAP==null){
			//Validación de Creación/Eliminación en LDAP
			boolean isUserinRole= ProxyLDAP.isUserInRoleEnterprise(regrolapp.getRutUsuario(), regrolapp.getApp(), regrolapp.getApprol(), regrolapp.getRutEmpresa());
			//Valida Creación
			if(regrolapp.isAdd() && isUserinRole){
				regrolapp.setEstado("");
//				Se actualiza estado de registro en DB2
				LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP2500", regrolapp);
				//Si está habilitado el envío de correo en archivo properties se envía mail a cliente.
				ok++;
			}
			else if(regrolapp.isAdd()){
				regrolapp.setEstado("X");
//				Se actualiza estado de registro en DB2
				LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP2500", regrolapp);
				//String mailsCC= PrevipassDAO.obtenerRegistro("custom.obtenerDataMail",null).get("VALOR").toString();
				
				EnviaMail.enviarMail("Error en creación de AppRol LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapError(regrolapp.getRutUsuario() + ", rol:" + regrolapp.getApprol(), mensajeLDAP));
			}
			//Valida Eliminación
			if(!regrolapp.isAdd() && !isUserinRole){
//				Se elimina registro en DB2
				LdapDB2DAO.ejecutarDelete("custom.delete_reg_LDAP2500", regrolapp);
				//Si está habilitado el envío de correo en archivo properties se envía mail a cliente.
				ok++;
			}
			else if(!regrolapp.isAdd()){
				regrolapp.setEstado("Y");
//				Se actualiza estado de registro en DB2
				LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP2500", regrolapp);
				//String mailsCC= PrevipassDAO.obtenerRegistro("custom.obtenerDataMail",null).get("VALOR").toString();
				EnviaMail.enviarMail("Error en eliminación de AppRol LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapError(regrolapp.getRutUsuario() + ", rol:" + regrolapp.getApprol(), mensajeLDAP));
			}
			
		}else{
			regrolapp.setEstado("X");
			//Se actualiza estado de registro en DB2 a Error
			LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP2500", regrolapp); 
			//String mailsCC= PrevipassDAO.obtenerRegistro("custom.obtenerDataMail",null).get("VALOR").toString();
			EnviaMail.enviarMail("Error en creación de AppRol LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapError( regrolapp.getRutUsuario() + ", rol:" + regrolapp.getApprol(), mensajeLDAP));
		}
		
	}
	resultado.put("RESULTADO", ok + " de " + registros.size() + " procesados exitosamente.");
	return resultado;
}

public static synchronized HashMap procesarAppRoles(String listamail){
	
	HashMap resultado = new HashMap();
	int i=0;
	//Se obtienen tods los registros nuevos de DB2 a procesar
	List registros = LdapDB2DAO.obtenerRegistros("custom.obtener_data_LDAP3500", null);
	List registros_eliminar = LdapDB2DAO.obtenerRegistros("custom.obtener_data_eliminar_LDAP3500", null);
	registros.addAll(registros_eliminar);
	log.info("Cantidad de registros a procesar AppRoles:" + registros.size());
	int ok=0;

	for (Iterator iter = registros.iterator(); iter.hasNext();) {
		AppRolesVO regrolapp = (AppRolesVO) iter.next();
		//Se inserta usuario en LDAP
		String mensajeLDAP= ProxyLDAP.createAppRolUsers(regrolapp);
		
		if(mensajeLDAP==null){
			String rutUsuario = regrolapp.getRutUsuario();
			String app = regrolapp.getAppID();
			String rol = regrolapp.getRolID();
			boolean exist;
			//Validación de Creación/Eliminación en LDAP
			if(rol.equals("") && rutUsuario.equals("")){
				exist= ProxyLDAP.isApplication(app);
				if(regrolapp.isAdd() && exist){
					regrolapp.setEstado("");
//					Se actualiza estado de registro en DB2
					LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP3500", regrolapp);
					ok++;
				}
				else if(regrolapp.isAdd() && !exist){
					regrolapp.setEstado("X");
//					Se actualiza estado de registro en DB2
					LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP3500", regrolapp);
					EnviaMail.enviarMail("Error en creación de Aplicación LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapError(regrolapp.getAppID(), mensajeLDAP));
				}
				if(!regrolapp.isAdd() && exist){
					regrolapp.setEstado("Y");
//					Se actualiza estado de registro en DB2
					LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP3500", regrolapp);
					//EnviaMail.enviarMail("Error en eliminación de Aplicación LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapError(regrolapp.getAppID(), mensajeLDAP));
				}
				else if(!regrolapp.isAdd() && !exist){
					//Se elimina registro en DB2
					LdapDB2DAO.ejecutarDelete("custom.delete_reg_LDAP3500", regrolapp);
					ok++;
				}
				
				
			}
			if(!rol.equals("") && rutUsuario.equals("")){
				exist= ProxyLDAP.isRolApp(app, rol);
				if(regrolapp.isAdd() && exist){
					regrolapp.setEstado("");
//					Se actualiza estado de registro en DB2
					LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP3500", regrolapp);
					ok++;
				}
				else if(regrolapp.isAdd() && !exist){
					regrolapp.setEstado("X");
//					Se actualiza estado de registro en DB2
					LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP3500", regrolapp);
					EnviaMail.enviarMail("Error en creación de AppRol LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapError(regrolapp.getAppID() + ", " + regrolapp.getRolID(), mensajeLDAP));
				}
				if(!regrolapp.isAdd() && exist){
					regrolapp.setEstado("Y");
//					Se actualiza estado de registro en DB2
					LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP3500", regrolapp);
					EnviaMail.enviarMail("Error en Eliminación de Rol de Aplicación LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapError(regrolapp.getAppID() + ", " + regrolapp.getRolID(), mensajeLDAP));
				}
				else if(!regrolapp.isAdd() && !exist){
//					Se elimina registro en DB2
					LdapDB2DAO.ejecutarDelete("custom.delete_reg_LDAP3500", regrolapp);
					ok++;
				}
				
				
			}
			if(!rol.equals("") && !rutUsuario.equals("")){
				exist= ProxyLDAP.isUserRolApp(rutUsuario, app, rol);
				if(regrolapp.isAdd() && exist){
					regrolapp.setEstado("");
//					Se actualiza estado de registro en DB2
					LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP3500", regrolapp);
					ok++;
				}
				else if(regrolapp.isAdd() && !exist){
					regrolapp.setEstado("X");
//					Se actualiza estado de registro en DB2
					LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP3500", regrolapp);
					EnviaMail.enviarMail("Error en creación de AppRol LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapError(regrolapp.getAppID() + ", " + regrolapp.getRolID(), mensajeLDAP));
				}
				else if(!regrolapp.isAdd() && exist){
					regrolapp.setEstado("Y");
//					Se actualiza estado de registro en DB2
					LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP3500", regrolapp);
					EnviaMail.enviarMail("Error en eliminación de Usuario en Rol de Aplicación LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapError(regrolapp.getAppID() + ", " + regrolapp.getRolID(), mensajeLDAP));
				}
				else if(!regrolapp.isAdd() && !exist){
//					Se elimina registro en DB2
					LdapDB2DAO.ejecutarDelete("custom.delete_reg_LDAP3500", regrolapp);
					ok++;
				}
				
				
			}
		}else{
			try {
				if(regrolapp.isAdd()){
					
					EnviaMail.enviarMail("Error en creación de AppRol LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapError( regrolapp.getRutUsuario() + ", rol:" + regrolapp.getRolID(), mensajeLDAP));
				}else{
					EnviaMail.enviarMail("Error en eliminación de AppRol LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapError( regrolapp.getRutUsuario() + ", rol:" + regrolapp.getRolID(), mensajeLDAP));
				}
			} catch (Exception e) {
				log.warn("Error notificando a usuario " + listamail);
				log.warn(e.getMessage());
				e.printStackTrace();
			}
			regrolapp.setEstado("X");
			//Se actualiza estado de registro en DB2 a Error
			LdapDB2DAO.ejecutarUpdate("custom.update_estado_LDAP3500", regrolapp); 
			//String mailsCC= PrevipassDAO.obtenerRegistro("custom.obtenerDataMail",null).get("VALOR").toString();
			
		}
	}
	resultado.put("RESULTADO", ok + " de " + registros.size() + " procesados exitosamente.");
	return resultado;
}

	public static UsuarioVO consultarUsuario(String idUser, String appID, String rolID, boolean buscarAS400){
		UsuarioVO persona=null;
		try {
			persona= new UsuarioVO();
			User user=null;
			try {
				user = ProxyLDAP.getUser(idUser);
			} catch (Exception e) {
				log.info("RUT Usuario no registrado en LDAP" + idUser);
			}

			if(user!=null){
				persona.setUsername(user.getID());
				persona.setEstado("M");//M: modificar
				persona.setNombres(user.getName());
				persona.setApellidoPaterno(user.getFirstName());
				persona.setApellidoMaterno(user.getLastName());
				persona.setTelefono(user.getPhone());
				persona.setEmail(user.getEmail());
				
				if(appID !=null && !appID.equals("") && rolID!=null && !rolID.equals("")){
					//System.out.println("Rol:" + rolID);
					List empresasAutorizadas= (List)ProxyLDAP.getUserEnterprisesAut(idUser, appID.trim(), rolID.trim());
					List empresas= new ArrayList();
					for (Iterator iter = empresasAutorizadas.iterator(); iter.hasNext();) {
						Enterprise element = (Enterprise) iter.next();
						HashMap empresa= new HashMap();
						empresa.put("ID", element.getID());
						empresa.put("NOMBRE", element.getRazonSocial());
						empresas.add(empresa);
					}
					persona.setEmpresasAutorizadas(empresas);
				}
			}else{
				persona.setObservaciones("RUT no registrado en Gestor de Claves.");
				persona.setEstado("N");
				//Cliente CRM
				ClienteInfoAfiliado clientInfo = new ClienteInfoAfiliado();
				SalidainfoAfiliadoVO salidaInfo = (SalidainfoAfiliadoVO) clientInfo.getDataAfiliado(idUser);
				
				String[] nombres= salidaInfo.getNombreCompleto().split(" ");
				
				//Descomponiendo nombre en 2 para setar nombre y apellido paterno
				int medio= Math.round(nombres.length/2);
				String nombre="";
				for (int i = 0; i < medio; i++) {
					nombre+= nombres[i] + " ";
				}
				String apellidos="";
				for (int i = medio; i < nombres.length; i++) {
					apellidos+= nombres[i] + " ";
				}
				persona.setNombres(nombre.trim());
				if(apellidos.indexOf(" ")>0){
					persona.setApellidoPaterno(apellidos.substring(0, apellidos.indexOf(" ")).trim());
					persona.setApellidoMaterno(apellidos.substring(apellidos.indexOf(" ")+1).trim());
				}else{
					persona.setApellidoPaterno(apellidos);
				}
				
				//Consulta GestorClaves SQLServer
				GestorClavesDAO gestorDao= new GestorClavesDAO();
				RegistroVO registroClaves= gestorDao.consultaRegistro(Integer.parseInt(idUser.split("-")[0]));
				if(registroClaves!=null){
					persona.setTelefono(registroClaves.getCelular());
					persona.setEmail(registroClaves.getEmail());
					
				}
				//persona.setAdd(true);
				
			}

			
			//user.isBlocked();
			//user.getQuestion();
			//user.getAnswer();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar usuario, mensaje: " + e.getMessage());
		}
		return persona;
	}
	
	public static boolean existeUsuario(String idUser){
		try {
			User user=null;
			try {
				user = ProxyLDAP.getUser(idUser);
			} catch (Exception e) {
				log.info("RUT Usuario registrado en LDAP" + idUser);
			}
			if(user!=null){
				return true;
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar usuario, mensaje: " + e.getMessage());
		}
		return false;
	}
	
	public static EmpresaVO consultarEmpresa(String idEmpresa, String appID, String rolID){
		EmpresaVO empresa=null;
		try {
			empresa= new EmpresaVO();
			Enterprise enterprise=null;
			try {
				enterprise = ProxyLDAP.getEnterprise(idEmpresa);
			} catch (Exception e) {
				log.info("Empresa no encontrada en LDAP " + idEmpresa);
			}
			//Se verifica si empresa es afiliada
			Integer rut= new Integer(idEmpresa.substring(0, idEmpresa.indexOf('-')));
			HashMap resultado= LdapDB2DAO.obtenerRegistro("custom.infoEmpresa", rut);
			
			if(enterprise!=null){
				empresa.setRutEmpresa(enterprise.getID());
				empresa.setRazonSocialEmpresa(enterprise.getRazonSocial());
				empresa.setAdd(false);
				/*empresa.setAfiliada(enterprise.getAfiliada());                   
				empresa.setTipo(enterprise.getTipo());                    
				empresa.setActividadEconomica(enterprise.getActividadEconomica());      
				empresa.setCodigoActividadEconomica(enterprise.getCodigoActividadEconomica());
				empresa.setComuna("-");
				empresa.setCiudad(enterprise.getCiudad());                  
				empresa.setRegion(enterprise.getRegion());                  
				empresa.setDireccion(enterprise.getDireccion());               
				empresa.setFono(enterprise.getFono());                    
				empresa.setEmail(enterprise.getEmail());                   
				empresa.setFax(enterprise.getFax());                     
				empresa.setHolding(enterprise.getDnHolding());               
				empresa.setComentario(enterprise.getComentario());              
				empresa.setRutRepLegal(enterprise.getRutRepLegal());             
				empresa.setNombreRepLegal(enterprise.getNombreRepLegal());          
				empresa.setApellidoPaternoRepLegal(enterprise.getApellidoPaternoRepLegal()); 
				empresa.setApellidoMaternoRepLegal(enterprise.getApellidoMaternoRepLegal()); 
				empresa.setRutEmpresaLider(enterprise.getRutEmpresaLider());
				*/
				if(resultado == null || resultado.size()==0){
					empresa.setObservaciones("Empresa no es Afiliada.");
					empresa.setAdd(false);
				}else{
					empresa.setEstado("");
					if(appID !=null && !appID.equals("") && rolID!=null && !rolID.equals("")){
						log.info("Consulta Usuarios en Empresa '" + idEmpresa + "' con Rol: '" + rolID.trim() + "' y aplicación '" + appID + "'");
						List usersRegistrados= (List)ProxyLDAP.getEnterpriseUsers(idEmpresa, appID.trim(), rolID.trim());
						/*List usuarios= new ArrayList();
					for (Iterator iter = usersRegistrados.iterator(); iter.hasNext();) {
						String element = (String) iter.next();
						HashMap usuario= new HashMap();
						usuario.put("ID", element);
						usuarios.add(usuario);
					}*/
						empresa.setUsers(usersRegistrados);
					}
				}
			}else{
				empresa.setObservaciones("Empresa no se encuentra registrada.");
				empresa.setAdd(true);

				if(resultado != null && resultado.size()>0){
					empresa.setRutEmpresa(idEmpresa);
					empresa.setRazonSocialEmpresa(resultado.get("NOMBRE").toString());
				}else{
					empresa.setObservaciones("Empresa no es Afiliada.");
					empresa.setAdd(false);
				}
			}
			//user.isBlocked();
			//user.getQuestion();
			//user.getAnswer();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar empresa, mensaje: " + e.getMessage());
		}
		return empresa;
	}
	
	public static EmpresaVO consultarAnexo(String idEmpresa){
		EmpresaVO empresa=null;
		try {
			empresa= new EmpresaVO();
			//Se verifica si empresa es afiliada
			Integer rut= new Integer(idEmpresa.substring(0, idEmpresa.indexOf('-')));
			HashMap resultado= LdapDB2DAO.obtenerRegistro("custom.infoEmpresa", rut);
			
			if(resultado!=null){
				empresa.setRutEmpresa(idEmpresa);
				empresa.setRazonSocialEmpresa(resultado.get("NOMBRE").toString());
				empresa.setAdd(false);
				empresa.setEstado("");
				log.info("Consulta Usuarios en Empresa '" + idEmpresa );
				List usersRegistrados= LdapDB2DAO.obtenerRegistros("custom.usuariosAnexo", rut);
				empresa.setUsers(usersRegistrados);
			}else{
				empresa.setObservaciones("Empresa no es Afiliada.");
				empresa.setAdd(false);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al consultar empresa, mensaje: " + e.getMessage());
		}
		return empresa;
	}
	
	public static int deleteUsuarioAnexo(String rutempresa, String rutusr){
		EmpresaVO empresa=null;
		try {
			empresa= new EmpresaVO();
			//Se verifica si empresa es afiliada
			Integer rut= new Integer(rutusr.substring(0, rutusr.indexOf('-')));
			Integer rutemp= new Integer(rutempresa.substring(0, rutempresa.indexOf('-')));
			HashMap param= new HashMap();
			param.put("rutemp", rutemp);
			param.put("rut", rut);
			int resultado1= LdapDB2DAO.ejecutarDelete("custom.delete_usuario_anexo", param);
			int resultado2= LdapDB2DAO.ejecutarDelete("custom.delete_oficinas_anexo", param);		
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al deleteUsuarioAnexo, mensaje: " + e.getMessage());
			return 0;
		}
		return 1;
	}
	
	public static int insertUsuarioAnexo(String rutempresa, String rutusr){
		int salida= 0;
		try {
			//Se verifica si empresa es afiliada
			Integer rut= new Integer(rutusr.substring(0, rutusr.indexOf('-')));
			HashMap resultado= LdapDB2DAO.obtenerRegistro("custom.infoCliente", rut);
			if(resultado==null){
				//Consulta GestorClaves SQLServer
				GestorClavesDAO gestorDao= new GestorClavesDAO();
				RegistroVO registroClaves= gestorDao.consultaRegistro(rut);
				if(registroClaves!=null){
					resultado= new HashMap();
					String nombre_completo= registroClaves.getNombre();
					String nombre="";
					String apellido_pat="";
					String apellido_mat="";
					
					//Sacando el nombre
					String[] palabras= getPrimeraPalabra(nombre_completo);
					nombre= palabras[0];
					palabras= getPrimeraPalabra(palabras[1]);
					apellido_pat= palabras[0];
					apellido_mat= palabras[1];
					
					resultado.put("NOMBRE", nombre);
					resultado.put("APELLIDO_P", apellido_pat);
					resultado.put("APELLIDO_M", apellido_mat);
				}
			}
			if(resultado!=null){
				Integer rutemp= new Integer(rutempresa.split("-")[0]);
				String dvemp= rutempresa.split("-")[1];
				HashMap param= new HashMap();
				param.put("rut", rut);
				param.put("rutemp", rutemp);
				param.put("dvemp", dvemp);
				param.put("allofi", "SI");
				param.put("nombre", resultado.get("NOMBRE").toString().trim());
				param.put("apepat", resultado.get("APELLIDO_P").toString().trim());
				param.put("apemat", resultado.get("APELLIDO_M").toString().trim());
				Integer numinsert= LdapDB2DAO.ejecutarInsert("custom.insert_usuario_anexo", param);
				salida=1;
			}else{
				salida=-2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al deleteUsuarioAnexo, mensaje: " + e.getMessage());
		}
		return salida;
	}
	
	public static String grabarEmpresa(EmpresaVO empresa){
//		Se inserta empresa en LDAP
		String mensajeLDAP= ProxyLDAP.createEnterprise(empresa);
		
		//Validación de Cración en LDAP
		HashMap param= new HashMap();
		if(ProxyLDAP.getEnterprise(empresa.getRutEmpresa().trim())!= null && mensajeLDAP==null){
			return "";
		}
		else{
			return mensajeLDAP;
		}
	}
	
	public static String newUsuario(UsuarioVO persona){
//		Se inserta usuario en LDAP
		String mensajeLDAP= ProxyLDAP.newUser(persona);
		if(mensajeLDAP!=null && mensajeLDAP.equals("NEW")){
			mensajeLDAP=null;
		}
		
		//Validación de Creación en LDAP
		HashMap param= new HashMap();
		if(ProxyLDAP.getUser(persona.getUsername().trim())!= null && mensajeLDAP==null){
			return "";
		}
		else{
			return mensajeLDAP;
		}
	}
	
	public static String grabarUsuario(UsuarioVO persona){
//		Se inserta usuario en LDAP
		String mensajeLDAP= ProxyLDAP.procesaUser(persona);
		if(mensajeLDAP!=null && mensajeLDAP.equals("NEW")){
			mensajeLDAP=null;
		}
		try {
			//Consulta GestorClaves SQLServer
			String[] rut= persona.getUsername().split("-");
			GestorClavesDAO gestorDao= new GestorClavesDAO();
			RegistroVO registroClaves= gestorDao.consultaRegistro(Integer.parseInt(rut[0]));
			
			RegistroVO registroVO= new RegistroVO();
			registroVO.setRut(Integer.parseInt(rut[0]));
			registroVO.setDv(rut[1]);
			String nombreCompleto=persona.getNombres().trim() + " " + persona.getApellidoPaterno().trim() + " " + persona.getApellidoMaterno().trim(); 
			registroVO.setNombre(nombreCompleto.trim());
			registroVO.setCelular(persona.getTelefono());
			registroVO.setEmail(persona.getEmail());
			registroVO.setCanal("Gestor Clave");
			
			if(registroClaves==null){
				gestorDao.insertBitacora(registroVO);
			}else{
				gestorDao.updateBitacora(registroVO);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Validación de Creación en LDAP
		HashMap param= new HashMap();
		if(ProxyLDAP.getUser(persona.getUsername().trim())!= null && mensajeLDAP==null){
			return "";
		}
		else{
			return mensajeLDAP;
		}
	}
	
	public static String updateBitacoraClave(UsuarioVO persona){
		String[] rut= persona.getUsername().split("-");
		try {
			//Consulta GestorClaves SQLServer
			GestorClavesDAO gestorDao= new GestorClavesDAO();
			RegistroVO registroClaves= gestorDao.consultaRegistro(Integer.parseInt(rut[0]));
			
			if(registroClaves==null){
				RegistroVO registroVO= new RegistroVO();
				registroVO.setRut(Integer.parseInt(rut[0]));
				registroVO.setDv(rut[1]);
				String nombreCompleto=persona.getNombres().trim() + " " + persona.getApellidoPaterno().trim() + " " + persona.getApellidoMaterno().trim(); 
				registroVO.setNombre(nombreCompleto.trim());
				registroVO.setCelular(persona.getTelefono());
				registroVO.setEmail(persona.getEmail());
				registroVO.setCanal("Gestor Clave");
				gestorDao.insertBitacora(registroVO);
			}else{
				gestorDao.updateBitacoraClave(Integer.parseInt(rut[0]));
			}
			return "";
		} catch (Exception e) {
			log.warn("Error actualizando bitácora por cambio clave Rut: "  + rut);
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	public static String eliminarUsuario(String username){
//		Se inserta usuario en LDAP
		String mensajeLDAP= ProxyLDAP.deleteUser(username);
		
		//Validación de Eliminación en LDAP
		HashMap param= new HashMap();
		if(ProxyLDAP.getUser(username)== null && mensajeLDAP==null){
			return "";
		}
		else{
			return mensajeLDAP;
		}
	}
	
	public static String grabarUsuarioRolEmp(RolUsuarioEmpVO rolemp){
		try {
			//		Se inserta usuario en LDAP
			String mensajeLDAP= ProxyLDAP.assignAppRolEnterprise(rolemp);
			boolean validacion=false;
			//Validación de Creación en LDAP
			boolean isUserinRole= ProxyLDAP.isUserInRoleEnterprise(rolemp.getRutUsuario(), rolemp.getApp(), rolemp.getApprol(), rolemp.getRutEmpresa());
			if(rolemp.getEstado().equals("N") && isUserinRole){
				validacion= true;
			}else if(rolemp.getEstado().equals("E") && !isUserinRole){
				validacion= true;
			}else {
				validacion= false;
			}
			if( validacion && mensajeLDAP==null){
				return "";
			}
			else if(!validacion){
				if(rolemp.getEstado().equals("E")){
					log.info("Operación realizada exitosamente pero validación erronea, usuario no fue eliminado de rol empresa, debe esperar o repetir operación.");
				}
				return "Validación erronea";
			}else{
				return mensajeLDAP;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al grabar usuario rol empresa, mensaje: " + e.getMessage());
			return e.getMessage();
		}
	}
	
	public static String grabarUsuarioRolApp(AppRolesVO approl){
		try {
			//		Se inserta usuario en LDAP
			String mensajeLDAP= ProxyLDAP.createAppRolUsers(approl);
			boolean validacion=false;
			//Validación de Creación en LDAP
			if(!approl.getRutUsuario().equals("")){
				List listausu= (List)ProxyLDAP.getUsersAppRoles(approl.getAppID(), approl.getRolID());
				if(approl.getEstado().equals("E") && listausu.size()==0){
					validacion=true;
				}
				for (Iterator iter = listausu.iterator(); iter.hasNext();) {
					String rut = (String) iter.next();
					rut= rut.replace(".", "");
					if(approl.getEstado().equals("N") && rut.equals(approl.getRutUsuario())){
						validacion= true;
						break;
					}else if(approl.getEstado().equals("E") && !rut.equals(approl.getRutUsuario())){
						validacion= true;
					}else {
						validacion= false;
					}
				}
			}else if(!approl.getRolID().equals("")){
				List listarol= (List)ProxyLDAP.getAppRoles(approl.getAppID());
				if(approl.getEstado().equals("E") && listarol.size()==0){
					validacion=true;
				}
				for (Iterator iter = listarol.iterator(); iter.hasNext();) {
					String rol = (String) iter.next();
					if(approl.getEstado().equals("N") && rol.equals(approl.getRolID())){
						validacion= true;
						break;
					}else if(approl.getEstado().equals("E") && !rol.equals(approl.getRolID())){
						validacion= true;
					}else {
						validacion= false;
					}
				}
			}else{
				List listaapp= (List)ProxyLDAP.getApplications();
				if(approl.getEstado().equals("E") && listaapp.size()==0){
					validacion=true;
				}
				for (Iterator iter = listaapp.iterator(); iter.hasNext();) {
					String app = (String) iter.next();
					if(approl.getEstado().equals("N") && app.equals(approl.getAppID())){
						validacion= true;
						break;
					}else if(approl.getEstado().equals("E")){
						validacion= true;
					}
				}
			}
			if( validacion && mensajeLDAP==null){
				return "";
			}
			else if(!validacion){
				if(approl.getEstado().equals("E")){
					log.info("Operación realizada exitosamente pero validación erronea, usuario no fue eliminado de rol-app, debe esperar o repetir operación.");
				}
				return "Validación erronea";
			}else{
				return mensajeLDAP;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al grabar usuario rol app, mensaje: " + e.getMessage());
			return e.getMessage();
		}
	}
	
	public static List consultarRegistrosPendientes() {

		List usuarios = LdapDB2DAO.obtenerRegistros( "custom.pendientes_LDAP1000", null);
		List empresas = LdapDB2DAO.obtenerRegistros( "custom.pendientes_LDAP2000", null);
		List roles = LdapDB2DAO.obtenerRegistros( "custom.pendientes_LDAP2500", null);
		List apps = LdapDB2DAO.obtenerRegistros( "custom.pendientes_LDAP3500", null);
		List pendientes= new ArrayList();
		pendientes.addAll(usuarios);
		pendientes.addAll(empresas);
		pendientes.addAll(roles);
		pendientes.addAll(apps);
		
		return pendientes;
	}
	
	public static HashMap consultarDato(String id, HashMap data) {

		HashMap resultado = LdapDB2DAO.obtenerRegistro( id, data);
		return resultado;
	}
	
	public static int grabarAuditoria(String id, HashMap data){
//		Se inserta usuario en LDAP
		int resultado= LdapDB2DAO.ejecutarInsert(id, data);
		return resultado;
	}
	public static String[] getPrimeraPalabra(String texto){
		String[] salida=new String[2];
		
		int posb= texto.indexOf(" ");
		if(posb>-1){
			salida[0]= texto.substring(0, posb);
			salida[1]= texto.substring(posb+1);
		}else{
			salida[0]= texto;
			salida[1]= "";
		}
		return salida;
	}
}
