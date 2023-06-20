package com.araucana.legacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;


import cl.araucana.core.registry.Enterprise;
import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;

/*
* @(#) PoxyLDAP.java 1.4 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author j-factory
 * 
 * @version 1.4
 */
public class ProxyLDAP {
	//private static final Logger log = LogManager.getLogger(ProxyLDAP.class);
	private static final Logger log = Logger.getLogger(ProxyLDAP.class);
	/**
	 * user
	 * 
	 * @param userID
	 * @return
	 */
	public static User getUser(String userID) {
		UserRegistryConnection connection = null;
		try {
			connection = new UserRegistryConnection();

			return connection.getUser(userID);
		} catch (UserRegistryException e) {
			return null;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (UserRegistryException e) {
				}
			}
		}
	}

	public static String assignAppRolEnterprise(RolUsuarioEmpVO registro) {
		String messageError = null;
		String rutEmpresa = registro.getRutEmpresa().trim();
		String rutUsuario = registro.getRutUsuario().trim();
		String app = registro.getApp();
		String approl = registro.getApprol();

		UserRegistryConnection connection = null;

		try {
			connection = new UserRegistryConnection();
			log.info("conectado a LDAP");
			Enterprise enterprise;
			try {
				enterprise = connection.getEnterprise(rutEmpresa);
			} catch (Exception e) {
				messageError = "Error al crear rol '" + approl + "' a usuario " + rutUsuario + " en empresa "
						+ rutEmpresa + ", motivo: empresa no existe en LDAP";
			}
			if (messageError == null) {
				boolean existRol = connection.isUserInRole(rutUsuario, app, approl, rutEmpresa);
				if (!existRol) {
					if (registro.isAdd()) {
						connection.assignAppRole(rutUsuario, app, approl, rutEmpresa);
						log.info("rol '" + approl + "' fue asignado a " + rutUsuario + " en empresa " + rutEmpresa
								+ " en LDAP");
					}
				} else {
					if (!registro.isAdd()) {
						log.info("Quitando rol '" + approl + "' de aplicaci�n:'" + app + "' de usuario '" + rutUsuario
								+ "' y empresa '" + rutEmpresa + "' en LDAP");
						connection.unassignAppRole(rutUsuario, app, approl, rutEmpresa);
						log.info("rol '" + approl + "' fue quitado a " + rutUsuario + " en empresa " + rutEmpresa
								+ " en LDAP");
					}
				}
			}

		} catch (UserRegistryException e) {
			messageError = e.getMessage();
			log.error("assignAppRolEnterprise, error al crear/elim rol '" + approl + "' fue quitado a " + rutUsuario
					+ " en empresa " + rutEmpresa + ", mensaje:", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (UserRegistryException e) {
					log.error("error al cerrar conexi�n LDAP:", e);
				}
			}
		}
		return messageError;
	}

	public static Collection getUserEnterprisesAut(String userID, String appID, String rolID) {
		UserRegistryConnection connection = null;
		List salida = new ArrayList();
		try {
			connection = new UserRegistryConnection();
			return connection.getEnterprises(userID, appID, rolID);
		} catch (UserRegistryException e) {
			log.error("getUserEnterprisesAut, error al consultar empresa autorizadas de usuario:" + userID + " y rol:"
					+ rolID + ", mensaje:", e);
			salida.add("Error en consulta");
			return salida;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (UserRegistryException e) {
				}
			}
		}
	}

	public static boolean isUserInRoleEnterprise(String rutUsuario, String app, String approl, String rutEmpresa) {
		UserRegistryConnection connection = null;
		try {
			connection = new UserRegistryConnection();

			return connection.isUserInRole(rutUsuario, app, approl, rutEmpresa);
		} catch (UserRegistryException e) {
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (UserRegistryException e) {
				}
			}
		}
	}

	/**
	 * cambia password
	 * 
	 * @param idPersona
	 * @param newPassword
	 * @return
	 */
	public static String changePassword(String username, String newPassword) {
		String messageError = null;
		log.info("cambio password LDAP::" + username + "::");
		UserRegistryConnection connection = null;

		try {
			connection = new UserRegistryConnection();
			log.info("conectado a LDAP");
			connection.changeUserPassword(username, null, newPassword);
			log.info("usuario '" + username + "' cambio password en LDAP");
		} catch (UserRegistryException e) {
			log.error("error al cambiar pass para usuario:" + username + ", mensaje:" + e);
			messageError = e.getMessage();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (UserRegistryException e) {
					log.error("changePassword, error al cerrar conexi�n LDAP:", e);
				}
			}
		}
		return messageError;
	}

	public static Collection getUsersAppRoles(String app, String rol) {
		UserRegistryConnection connection = null;
		List listusers = new ArrayList();
		try {
			connection = new UserRegistryConnection();
			List users = (List) connection.getUsers(app, rol);
			for (Iterator iter = users.iterator(); iter.hasNext();) {
				String rut = (String) iter.next();
				listusers.add(rut.replaceAll("\\.", ""));
			}
			return listusers;
		} catch (UserRegistryException e) {
			log.error("getApplications, error al consultar usuarios de rol: " + rol + " de aplicacion:" + app
					+ ", mensaje:", e);
			return listusers;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (UserRegistryException e) {
				}
			}
		}
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// PersonaVO p = new PersonaVO();
		// p.setIdPersona(new Integer(555));
		// p.setNombres("aa");
		// ProxyLDAP.createUser("1234", p);
		// System.out.println("Usuario Encontrado: " + getUser("555-K"));

		// int idPersona = 12345678-5;
		// System.out.println(generaDV(555));
		/*
		 * System.out.println("ACA"); for(int i=10000000;i<9000100;i++){
		 * System.out.println(i+"-"+generaDV(i)); }
		 */
		// System.out.println("30000001-"+generaDV(1000001));
		// System.out.println("30000002-"+generaDV(1000002));
		// System.out.println("30000003-"+generaDV(1000003));
		// System.out.println("30000004-"+generaDV(1000004));
		// System.out.println("30000005-"+generaDV(1000005));
		// System.out.println(ProxyLDAP.changePassword("76021396-9", "8866"));
		System.out.println(ProxyLDAP.getUser("76021396-9"));

	}

}
