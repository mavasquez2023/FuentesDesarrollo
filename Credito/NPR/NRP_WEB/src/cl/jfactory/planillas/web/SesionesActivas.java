package cl.jfactory.planillas.web;

import cl.liv.comun.utiles.MiHashMap;

public class SesionesActivas {

	public static MiHashMap sessiones = new MiHashMap();
	
	public static void agregarSession(SessionUsuario session){
		sessiones.put(session.getToken(), session);
		
	}
	
	public static void removeSession(String token){
		sessiones.remove(token);
	}
	
	public static MiHashMap getSessionAsHashMap(String token){
		MiHashMap hash = null;
		
		if(sessiones.get(token)!= null){
			hash = ((SessionUsuario)sessiones.get(token)).getDataAsHashMap();
		}
		
		return hash;
	}
	
	public static SessionUsuario getSession(String token){
		
		return ((SessionUsuario)sessiones.get(token));
		
	}
}
