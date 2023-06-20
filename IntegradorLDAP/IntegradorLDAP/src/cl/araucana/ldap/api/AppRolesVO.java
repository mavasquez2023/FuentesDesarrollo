/**
 * 
 */
package cl.araucana.ldap.api;

/**
 * @author usist24
 *
 */
public class AppRolesVO {
	private String appID;
	private String rolID;
	private String appDescripcion;
	private String rolDescripcion;
	private String rutUsuario;
	private String estado;
	private boolean add;
	/**
	 * @return el appDescripcion
	 */
	public String getAppDescripcion() {
		return appDescripcion;
	}
	/**
	 * @param appDescripcion el appDescripcion a establecer
	 */
	public void setAppDescripcion(String appDescripcion) {
		this.appDescripcion = (appDescripcion==null)?"":appDescripcion.trim();;
	}
	/**
	 * @return el appID
	 */
	public String getAppID() {
		return appID;
	}
	/**
	 * @param appID el appID a establecer
	 */
	public void setAppID(String appID) {
		this.appID = appID.trim();
	}
	/**
	 * @return el estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado el estado a establecer
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return el rolDescripcion
	 */
	public String getRolDescripcion() {
		return rolDescripcion;
	}
	/**
	 * @param rolDescripcion el rolDescripcion a establecer
	 */
	public void setRolDescripcion(String rolDescripcion) {
		this.rolDescripcion = (rolDescripcion==null)?"":rolDescripcion.trim();;
	}
	/**
	 * @return el rolID
	 */
	public String getRolID() {
		return rolID;
	}
	/**
	 * @param rolID el rolID a establecer
	 */
	public void setRolID(String rolID) {
		this.rolID = (rolID==null)?"":rolID.trim();
	}
	/**
	 * @return el rutUsuario
	 */
	public String getRutUsuario() {
		return rutUsuario;
	}
	/**
	 * @param rutUsuario el rutUsuario a establecer
	 */
	public void setRutUsuario(String rutUsuario) {
		this.rutUsuario = (rutUsuario==null)?"":rutUsuario.trim();
	}
	/**
	 * @return el add
	 */
	public boolean isAdd() {
		return (this.estado.toUpperCase().equals("E")?false:true);
	}
	/**
	 * @param add el add a establecer
	 */
	public void setAdd(boolean add) {
		this.add = add;
	}
	
}
