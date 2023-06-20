/**
 * 
 */
package cl.araucana.ldap.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author usist24
 *
 */
public class EmpresaVO {
	private String rutEmpresa;
	private String razonSocialEmpresa;
	private int afiliada;                   
	private String tipo;                    
	private String actividadEconomica;      
	private String codigoActividadEconomica;
	private String comuna;
	private String ciudad;                  
	private String region;                  
	private String direccion;               
	private String fono;                    
	private String email;                   
	private String fax;                     
	private String holding;               
	private String comentario;              
	private String rutRepLegal;             
	private String nombreRepLegal;          
	private String apellidoPaternoRepLegal; 
	private String apellidoMaternoRepLegal; 
	private String rutEmpresaLider;
	private String estado;
	private String observaciones;
	private Collection users;
	private boolean add;
	/**
	 * @return el actividadEconomica
	 */
	public String getActividadEconomica() {
		return (actividadEconomica!=null)? actividadEconomica : "";
	}
	/**
	 * @param actividadEconomica el actividadEconomica a establecer
	 */
	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}
	/**
	 * @return el afiliada
	 */
	public int getAfiliada() {
		return afiliada;
	}
	/**
	 * @param afiliada el afiliada a establecer
	 */
	public void setAfiliada(int afiliada) {
		this.afiliada = afiliada;
	}
	/**
	 * @return el apellidoMaternoRepLegal
	 */
	public String getApellidoMaternoRepLegal() {
		return (apellidoMaternoRepLegal!=null)? apellidoMaternoRepLegal : "";
	}
	/**
	 * @param apellidoMaternoRepLegal el apellidoMaternoRepLegal a establecer
	 */
	public void setApellidoMaternoRepLegal(String apellidoMaternoRepLegal) {
		this.apellidoMaternoRepLegal = apellidoMaternoRepLegal;
	}
	/**
	 * @return el apellidoPaternoRepLegal
	 */
	public String getApellidoPaternoRepLegal() {
		return (apellidoPaternoRepLegal!=null)? apellidoPaternoRepLegal : "";
	}
	/**
	 * @param apellidoPaternoRepLegal el apellidoPaternoRepLegal a establecer
	 */
	public void setApellidoPaternoRepLegal(String apellidoPaternoRepLegal) {
		this.apellidoPaternoRepLegal = apellidoPaternoRepLegal;
	}
	/**
	 * @return el ciudad
	 */
	public String getCiudad() {
		return (ciudad!=null)? ciudad : "";
	}
	/**
	 * @param ciudad el ciudad a establecer
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return el codigoActividadEconomica
	 */
	public String getCodigoActividadEconomica() {
		return (codigoActividadEconomica!=null)? codigoActividadEconomica : "";
	}
	/**
	 * @param codigoActividadEconomica el codigoActividadEconomica a establecer
	 */
	public void setCodigoActividadEconomica(String codigoActividadEconomica) {
		this.codigoActividadEconomica = codigoActividadEconomica;
	}
	/**
	 * @return el comentario
	 */
	public String getComentario() {
		return (comentario!=null)? comentario : "";
	}
	/**
	 * @param comentario el comentario a establecer
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	/**
	 * @return el direccion
	 */
	public String getDireccion() {
		return (direccion!=null)? direccion : "";
	}
	/**
	 * @param direccion el direccion a establecer
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return el dnHolding
	 */
	public String getHolding() {
		return (this.holding!=null)? this.holding : "";
		 
	}
	/**
	 * @param dnHolding el dnHolding a establecer
	 */
	public void setHolding(String holding) {
		this.holding= holding;
	}
	/**
	 * @return el email
	 */
	public String getEmail() {
		return (email!=null)? email : "";
	}
	/**
	 * @param email el email a establecer
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return el fax
	 */
	public String getFax() {
		return (fax!=null)? fax : "";
	}
	/**
	 * @param fax el fax a establecer
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * @return el fono
	 */
	public String getFono() {
		return (fono!=null)? fono : "";
	}
	/**
	 * @param fono el fono a establecer
	 */
	public void setFono(String fono) {
		this.fono = fono;
	}
	/**
	 * @return el nombreRepLegal
	 */
	public String getNombreRepLegal() {
		return (nombreRepLegal!=null)? nombreRepLegal : "";
	}
	/**
	 * @param nombreRepLegal el nombreRepLegal a establecer
	 */
	public void setNombreRepLegal(String nombreRepLegal) {
		this.nombreRepLegal = nombreRepLegal;
	}
	/**
	 * @return el region
	 */
	public String getRegion() {
		return (region!=null)? region : "";
	}
	/**
	 * @param region el region a establecer
	 */
	public void setRegion(String region) {
		this.region = region;
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
	 * @return el rutEmpresaLider
	 */
	public String getRutEmpresaLider() {
		return (rutEmpresa!=null)? rutEmpresa : "";
	}
	/**
	 * @param rutEmpresaLider el rutEmpresaLider a establecer
	 */
	public void setRutEmpresaLider(String rutEmpresaLider) {
		this.rutEmpresaLider = rutEmpresaLider;
	}
	/**
	 * @return el rutRepLegal
	 */
	public String getRutRepLegal() {
		return (rutRepLegal!=null)? rutRepLegal : "";
	}
	/**
	 * @param rutRepLegal el rutRepLegal a establecer
	 */
	public void setRutRepLegal(String rutRepLegal) {
		this.rutRepLegal = rutRepLegal;
	}
	/**
	 * @return el tipo
	 */
	public String getTipo() {
		return (tipo!=null)? tipo : "";
	}
	/**
	 * @param tipo el tipo a establecer
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return el comuna
	 */
	public String getComuna() {
		return (comuna!=null)? comuna : "";
	}
	/**
	 * @param comuna el comuna a establecer
	 */
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
	/**
	 * @return el razonSocialEmpresa
	 */
	public String getRazonSocialEmpresa() {
		return (razonSocialEmpresa!=null)? razonSocialEmpresa : "";
	}
	/**
	 * @param razonSocialEmpresa el razonSocialEmpresa a establecer
	 */
	public void setRazonSocialEmpresa(String razonSocialEmpresa) {
		this.razonSocialEmpresa = razonSocialEmpresa;
	}
	/**
	 * @return el estado
	 */
	public String getEstado() {
		return (estado!=null)? estado : "";
	}
	/**
	 * @param estado el estado a establecer
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return el observaciones
	 */
	public String getObservaciones() {
		return (observaciones!=null)? observaciones : "";
	}
	/**
	 * @param observaciones el observaciones a establecer
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	} 
	public Map toHashMap(){
		try {
			Map empresa= new HashMap();
			empresa.put("rutEmpresa", this.rutEmpresa);
			empresa.put("razonSocialEmpresa", this.razonSocialEmpresa);
			empresa.put("afiliada", new Integer(this.afiliada));                  
			empresa.put("tipo", this.tipo);                   
			empresa.put("actividadEconomica", this.actividadEconomica);     
			empresa.put("codigoActividadEconomica", this.codigoActividadEconomica);
			empresa.put("comuna", this.comuna);
			empresa.put("ciudad", this.ciudad);                 
			empresa.put("region", this.region);                 
			empresa.put("direccion", this.direccion);              
			empresa.put("fono", this.fono);                   
			empresa.put("email", this.email);                   
			empresa.put("fax", this.fax);                    
			//empresa.put("holding", limpia_dnHolding(getHolding()));            
			empresa.put("holding", getHolding());
			empresa.put("comentario", this.comentario);             
			empresa.put("rutRepLegal", this.rutRepLegal);            
			empresa.put("nombreRepLegal", this.nombreRepLegal);          
			empresa.put("apellidoPaternoRepLegal", this.apellidoPaternoRepLegal);
			empresa.put("apellidoMaternoRepLegal", this.apellidoMaternoRepLegal); 
			empresa.put("rutEmpresaLider", this.rutEmpresaLider);
			empresa.put("estado", this.estado);
			empresa.put("observaciones", this.observaciones);
			empresa.put("nuevoe", this.add);
			return empresa;
		} catch (Exception e) {
			return null;
		}
	}
	
	public String limpia_dnHolding(String holding){
		if(holding!=null && holding.indexOf("cn=")>-1){
			//dato de tipo "cn=<holding>,ou=holdings,o=araucana,c=cl"
			String[] dn= holding.split(",");
			String[] cn= dn[0].split("=");
			return cn[1];
		}else{
			return holding;
		}
		
	}
	/**
	 * @return el users
	 */
	public Collection getUsers() {
		return users;
	}
	/**
	 * @param users el users a establecer
	 */
	public void setUsers(Collection users) {
		this.users = users;
	}
	/**
	 * @return el add
	 */
	public boolean isAdd() {
		return add;
	}
	/**
	 * @param add el add a establecer
	 */
	public void setAdd(boolean add) {
		this.add = add;
	}
}
