package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.Collection;
import java.util.HashMap;

public class PersonaVO extends AuditableVO implements Comparable
{
	private static final long serialVersionUID = 3540953888970593319L;
	private Integer idPersona;
	private Integer idComuna;
	private String nombres = "";
	private String apellidoPaterno = "";
	private String apellidoMaterno = "";
	private String email = "";
	private String telefono = "";
	private int celular;
	private String fax = "";
	private String direccion = "";
	private String numero = "";
	private String dpto = "";
	private Collection convenios;
	private Collection empresas;
	private boolean adminAraucana;
	private String nivelAcceso;
	private String rut;

	public PersonaVO()
	{
		super();
	}

	public PersonaVO(Integer idPersona)
	{
		super();
		this.idPersona = idPersona;
	}

	public String getRut() {
		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNivelAcceso() {
		return this.nivelAcceso;
	}

	public void setNivelAcceso(String nivelAcceso) {
		this.nivelAcceso = nivelAcceso;
	}

	public String getNameToShow()
	{
		StringBuffer sb = new StringBuffer(this.nombres.trim()).append(' ').append(this.apellidoPaterno.trim());
		if ((this.apellidoMaterno != null) && !this.apellidoMaterno.trim().equals(""))
		{
			sb.append(' ').append(this.apellidoMaterno.charAt(0)).append('.');
		}
		return sb.toString();
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdComuna() {
		return this.idComuna;
	}

	public void setIdComuna(Integer idComuna) {
		this.idComuna = idComuna;
	}

	public Integer getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public Collection getConvenios() {
		return this.convenios;
	}

	public void setConvenios(Collection convenios) {
		this.convenios = convenios;
	}
	
	public Collection getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(Collection empresas) {
		this.empresas = empresas;
	}

	public String toString(){
		try {
			return "Persona[idPersona: " + this.idPersona
				+ ", idComuna: " + this.idComuna
				+ "\", nombres: \"" + this.nombres
				+ "\", apellidoPaterno: \"" + this.apellidoPaterno
				+ "\", apellidoMaterno: \"" + this.apellidoMaterno
				+ "\", email: \"" + this.email
				+ "\", telefono: \"" + this.telefono
				+ "\", celular: " + this.celular
				+ ", fax: \"" + this.fax
				+ "\", direccion: \"" + this.direccion
				+ "\", numero: \"" + this.numero
				+ "\", dpto: \"" + this.dpto
				+ "\"]";
		} catch (Exception e) {
			return "[Error en descriptor]";
		}
	}

	public int getCelular()
	{
		return this.celular;
	}

	public void setCelular(int celular)
	{
		this.celular = celular;
	}

	public String getDireccion()
	{
		return this.direccion;
	}

	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}

	public String getDpto()
	{
		return this.dpto;
	}

	public void setDpto(String dpto)
	{
		this.dpto = dpto;
	}

	public String getFax()
	{
		return this.fax;
	}

	public void setFax(String fax)
	{
		this.fax = fax;
	}

	public String getNumero()
	{
		return this.numero;
	}

	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	public String getTelefono()
	{
		return this.telefono;
	}

	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}

	public boolean isAdminAraucana()
	{
		return this.adminAraucana;
	}

	public void setAdminAraucana(boolean adminAraucana)
	{
		this.adminAraucana = adminAraucana;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((this.idPersona == null) ? 0 : this.idPersona.hashCode());
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PersonaVO other = (PersonaVO) obj;
		if (this.idPersona == null)
		{
			if (other.idPersona != null)
				return false;
		} else if (!this.idPersona.equals(other.idPersona))
			return false;
		return true;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", "" + this.idPersona);
		parametros.put("2", String.valueOf(this.idComuna));
		parametros.put("3", this.nombres);
		parametros.put("4", this.apellidoPaterno);
		parametros.put("5", this.apellidoMaterno);
		parametros.put("6", this.email);
		parametros.put("7", String.valueOf(this.telefono));
		parametros.put("8", String.valueOf(this.celular));
		parametros.put("9", this.fax);
		parametros.put("10", this.direccion);
		parametros.put("11", this.numero);
		parametros.put("12", this.dpto);
		return parametros;
	}

	public int compareTo(Object o) 
	{
		return this.idPersona.compareTo(((PersonaVO)o).getIdPersona());
	}

}