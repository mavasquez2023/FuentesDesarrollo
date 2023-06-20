/**
 * 
 */
package cl.laaraucana.EnvioASFAMEmpresa.vo;

/**
 * @author usist24
 *
 */
public class RolUsuarioEmpVO {
	private String rutEmpresa;
	private String rutUsuario;
	private String approl;
	private String approl_original;
	private String app;
	private String estado;
	private boolean add;
	/**
	 * @return el approl
	 */
	public String getApprol() {
		return approl;
	}
	/**
	 * @param approl el approl a establecer
	 */
	public void setApprol(String approl) {
		this.approl_original= approl;
		if(approl!= null && approl.indexOf("cn=")>-1){
			String[] ramas= approl.split(",");
			String[] cn=ramas[0].split("=");
			this.approl = cn[1];
		}else{
			this.approl = (approl==null)?"":approl.trim();
		}
	}
	/**
	 * @return el estado
	 */
	public String getEstado() {
		return (estado==null)?"":estado.trim();
	}
	/**
	 * @param estado el estado a establecer
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return el rutEmpresa
	 */
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	/**
	 * @param rutEmpresa el rutEmpresa a establecer
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = (rutEmpresa==null)?"":rutEmpresa.trim();
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
	/**
	 * @return el app
	 */
	public String getApp() {
		return (app==null)?"":app.trim();
	}
	/**
	 * @param app el app a establecer
	 */
	public void setApp(String app) {
		this.app = app;
	}
	/**
	 * @return el approl_original
	 */
	public String getApprol_original() {
		return approl_original;
	}
	/**
	 * @param approl_original el approl_original a establecer
	 */
	public void setApprol_original(String approl_original) {
		this.approl_original = approl_original;
	}
}
