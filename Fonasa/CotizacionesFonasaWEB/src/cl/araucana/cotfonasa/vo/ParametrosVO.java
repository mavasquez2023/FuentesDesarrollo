package cl.araucana.cotfonasa.vo;

public class ParametrosVO {
	
	
	private String idParametro;
	private String correoUsuario;
	private String correoAdmin;
    private String directorioEntrada;
    private String directorioSalida;
    
    
	
	public String getIdParametro() {
		return idParametro;
	}
	public void setIdParametro(String idParametro) {
		this.idParametro = idParametro;
	}
	public String getCorreoUsuario() {
		return correoUsuario.trim();
	}
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}
	public String getCorreoAdmin() {
		return correoAdmin.trim();
	}
	public void setCorreoAdmin(String correoAdmin) {
		this.correoAdmin = correoAdmin;
	}
	public String getDirectorioEntrada() {
		return directorioEntrada.trim();
	}
	public void setDirectorioEntrada(String directorioEntrada) {
		this.directorioEntrada = directorioEntrada;
	}
	public String getDirectorioSalida() {
		return directorioSalida.trim();
	}
	public void setDirectorioSalida(String directorioSalida) {
		this.directorioSalida = directorioSalida;
	}
    
    
	

}
