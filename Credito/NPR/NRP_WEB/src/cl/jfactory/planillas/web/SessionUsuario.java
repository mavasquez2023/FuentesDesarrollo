package cl.jfactory.planillas.web;

import cl.liv.comun.utiles.MiHashMap;

public class SessionUsuario {

	public String idUsuario = null;
	public String nombreUsuario = null;
	public String token = null;
	public String perfiles = null;
	
	public SessionUsuario(String _idUsuario, String _nombreUsuario, String _token, String _perfiles){
		idUsuario = _idUsuario;
		nombreUsuario = _nombreUsuario;
		token = _token;
		perfiles = _perfiles;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public  boolean tieneElPerfil(String perfil){
		
		if(perfiles != null && perfiles.contains(perfil)){
			return true;
		}
		
		return false;
	}
	

	public String toString(){
		String retorno = "";
		
		retorno = retorno + "{";
		
		retorno = retorno + " 'idUsuario':'"+idUsuario+"', ";
		retorno = retorno + " 'nombreUsuario':'"+nombreUsuario+"', ";
		retorno = retorno + " 'token':'"+token+"', ";
		retorno = retorno + " 'perfiles':'"+perfiles+"'";
		retorno = retorno + "}";
		
		return retorno;
	}
	
	public MiHashMap getDataAsHashMap(){
		MiHashMap hash = new MiHashMap();
		hash.put("id_usuario", idUsuario);
		hash.put("nombre_usuario",nombreUsuario);
		hash.put("token",token);
		hash.put("perfiles",perfiles);
		
		return hash;
	}
}
