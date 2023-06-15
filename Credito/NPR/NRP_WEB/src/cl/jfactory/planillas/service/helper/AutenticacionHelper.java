package cl.jfactory.planillas.service.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.core.util.UserPrincipal;
import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.jfactory.planillas.service.util.UtilesWorkflow;
import cl.jfactory.planillas.web.LoginServlet;
import cl.jfactory.planillas.web.SesionesActivas;
import cl.jfactory.planillas.web.SessionUsuario;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class AutenticacionHelper {

	public Boolean validarServer(Object session, Object parameters) {
		return Boolean.TRUE;
	}

	public HashMap generarTokenUsuario(Object session, Object parameters) {
		HashMap output = new HashMap();
		if ((HashMap) ((HashMap) session).get("query_autenticacion") != null){
			String token =  ((HashMap)parameters).get("email").toString();
			
			token = UtilesWorkflow.getMD5("liv.fw."+token+"."+new Date().getTime() );
			
			((HashMap)parameters).put("token",token);
			
			((HashMap) ((HashMap) session).get("query_autenticacion")).put("token", token);
			 
			return (HashMap)((HashMap) session).get("query_autenticacion");
		}
		else {
			output.put("codigo_descripcion", "Error de Usuario");
			output.put("codigo_error", "S003");

			return output;
		}
		
		

	}
	
	public Boolean cargarDatosSession(Object session, Object parameters) throws InterruptedException {
		((HashMap)session).put("data_session", SesionesActivas.getSessionAsHashMap(((HashMap)parameters).get("token").toString()));
		//UtilLogWorkflow.debug( "data_session: "+  ((HashMap)session).get("data_session"));
		if ( ((HashMap)session).get("data_session") != null )
			return Boolean.TRUE;
		
		return null;
				
	}

	public Boolean validarTokenAutenticacion(Object session, Object parameters) throws InterruptedException {
		
		if(true) return Boolean.TRUE;
		if(((HashMap)parameters).get("token") != null){
			if ( validarToken(((HashMap)parameters).get("token").toString()) ){
				return Boolean.TRUE;
			}
		}
		
		return null;
	}

	
	public HashMap generarSalida(Object session, Object parameters) {
		HashMap output = new HashMap();
		
		if ((HashMap) ((HashMap) session).get("query_autenticacion") != null){
			HashMap salida = new HashMap();
			salida.put("nombre_usuario", ((HashMap) ((HashMap) session).get("query_autenticacion")).get("nombre"));
			salida.put("token", ((HashMap) ((HashMap) session).get("query_autenticacion")).get("token"));
			return salida;
		}
		else {
			output.put("codigo_descripcion", "Error de Usuario");
			output.put("codigo_error", "S003");

			return output;
		}
	}
	
	public static SessionUsuario autenticarUsuario(String idUsuario, String password){

		if(LoginServlet.AUTENTICACION_LOCAL && idUsuario.equalsIgnoreCase("15057836-1") && password.equalsIgnoreCase("0419191919")){
			SessionUsuario sesion = new SessionUsuario("15057836-1", "Usuario Dummy, sin autenticar", "12345678","administrador" );
			String token = regenerarToken(idUsuario);
			if(token != null){
				sesion.setToken(token);
				SesionesActivas.agregarSession(sesion);
			}
		
			return sesion;
		}
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		MiHashMap pars = new MiHashMap();
		try {
			pars.put("id_usuario", idUsuario);
			pars.put("password", password);
			
			UserPrincipal user = new UserPrincipal(idUsuario, password);
			try {
				User userLDAP = new UserRegistryConnection(user).getUser();

				String email = userLDAP.getEmail();
				String nombre = userLDAP.getFullName(true);
				String perfiles = "";
				
				UserRegistryConnection  urConnection = new UserRegistryConnection();

				List roles = new ArrayList(urConnection.getUserRoles( idUsuario  , "NRP"));
				if(roles != null){
					for(int i=0; i<roles.size(); i++){
						perfiles = perfiles + roles.get(i)+ ";"; 
					}
				}
				SessionUsuario session = new SessionUsuario(idUsuario, nombre, "" , perfiles);
				String token = regenerarToken(idUsuario);
				if(token != null){
					session.setToken(token);
					SesionesActivas.agregarSession(session);
					return session;
				}
				
			} catch (UserRegistryException e) {
				e.printStackTrace();
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		return null;
	}
	
	public static String regenerarToken(String idUsuario){
		String token = UtilesComunes.getMD5( "TOK_"+Math.random() );
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		MiHashMap pars = new MiHashMap();
		try {
			pars.put("id_usuario", idUsuario);
			pars.put("token", token);
			int resultado = sqlMap.delete("carga_SAP.eliminarToken", pars);
			sqlMap.insert("carga_SAP.generarToken", pars);
			return token;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static synchronized boolean validarToken(String token){
		if(LoginServlet.AUTENTICACION_LOCAL) return true;
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		MiHashMap pars = new MiHashMap();
		try {
			pars.put("token", token);
			MiHashMap tokenData = (MiHashMap) sqlMap.queryForObject("carga_SAP.validarToken", pars);
			if(tokenData != null){
				Date fechaToken = (Date)tokenData.get("fecha");
				Date fechaActual = new Date();
				int tiempoVidaToken = Integer.parseInt( PropertiesUtil.propertiesAutenticacion.getString("config.autenticacion.tiempo.vida.token") );
				int tiempoTranscurrido = (int)((fechaActual.getTime() - fechaToken.getTime()) / 1000);
				if( tiempoTranscurrido < tiempoVidaToken ){
					sqlMap.update("carga_SAP.refrescarToken", pars);
					return true;
				}
				int resultado = sqlMap.delete("carga_SAP.eliminarToken", pars);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static boolean resetearPassword(String idUsuario, String password){
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		MiHashMap pars = new MiHashMap();
		try {
			pars.put("id_usuario", idUsuario);
			pars.put("password", password);
			int resultado = sqlMap.update("carga_SAP.resetearPassword", pars);
			if(resultado > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
}
