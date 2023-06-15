package cl.laaraucana.simulacion.VO;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



public class UsuarioVO 
{
	private static final long serialVersionUID = 3540953888970593319L;
	private String username;
	private String clave="";
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String sexo;
	private String email;
	private String telefono;
	private String origen;
	private String estado;
	private String observaciones;
	private Collection empresasAutorizadas;
	private boolean blocked;
	private String question="";
	private String answer="";
	/**
	 * @return el apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return (apellidoMaterno!=null)? apellidoMaterno : "";
	}
	/**
	 * @param apellidoMaterno el apellidoMaterno a establecer
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * @return el apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return (apellidoPaterno!=null)? apellidoPaterno : "";
	}
	/**
	 * @param apellidoPaterno el apellidoPaterno a establecer
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * @return el clave
	 */
	public String getClave() {
		return (clave!=null)? clave : "";
	}
	/**
	 * @param clave el clave a establecer
	 */
	public void setClave(String clave) {
		this.clave = clave;
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
	 * @return el fono
	 */
	public String getTelefono() {
		return (telefono!=null)? telefono : "";
	}
	/**
	 * @param fono el fono a establecer
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return el nombre
	 */
	public String getNombres() {
		return (nombres!=null)? nombres : ""; 
	}
	/**
	 * @param nombre el nombre a establecer
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**
	 * @return el origen
	 */
	public String getOrigen() {
		return  (origen!=null)? origen : "";
	}
	/**
	 * @param origen el origen a establecer
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	/**
	 * @return el sexo
	 */
	public String getSexo() {
		return (sexo!=null)? sexo : "";
	}
	/**
	 * @param sexo el sexo a establecer
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/**
	 * @return el username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username el username a establecer
	 */
	public void setUsername(String username) {
		this.username = (username==null)?"":username.trim();
	}
	
	public Map toHashMap(){
		try {
			Map persona= new HashMap();
			persona.put("username", this.username);
			persona.put("nombres", this.nombres);
			persona.put("apellidoPaterno", this.apellidoPaterno);
			persona.put("apellidoMaterno", this.apellidoMaterno);
			persona.put("sexo", this.sexo);
			persona.put("email", this.email);
			persona.put("telefono", this.telefono);
			persona.put("origen", this.origen);
			persona.put("blocked", this.blocked);
			persona.put("question", this.question);
			persona.put("answer", this.answer);
			persona.put("observaciones", this.observaciones);
			boolean add= !this.estado.equalsIgnoreCase("E");
			persona.put("nuevo", add);
			return persona;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * @return el observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones el observaciones a establecer
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return el empresasAutorizadas
	 */
	public Collection getEmpresasAutorizadas() {
		return empresasAutorizadas;
	}
	/**
	 * @param empresasAutorizadas el empresasAutorizadas a establecer
	 */
	public void setEmpresasAutorizadas(Collection empresasAutorizadas) {
		this.empresasAutorizadas = empresasAutorizadas;
	}
	/**
	 * @return el blocked
	 */
	public boolean isBlocked() {
		return blocked;
	}
	/**
	 * @param blocked el blocked a establecer
	 */
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	/**
	 * @return el answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer el answer a establecer
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * @return el question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question el question a establecer
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
}