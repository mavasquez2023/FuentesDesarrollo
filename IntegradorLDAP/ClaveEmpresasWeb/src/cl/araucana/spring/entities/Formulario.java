package cl.araucana.spring.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name = "SolicitudClaveEmpresa")
@NamedQuery(name = "Formulario.findAll", query = "SELECT f FROM Formulario f")
public class Formulario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Transient
	private CommonsMultipartFile fichero;
	@Column(name = "rut", length = 10)
	private String rut;
	@Column(name = "dv", length = 1)
	private String dv;
	@Column(name = "nombre", length = 80)
	private String nombre;
	@Column(name = "apePat", length = 80)
	private String apePat;
	@Column(name = "apeMat", length = 80)
	private String apeMat;
	@Column(name = "celular", length = 15)
	private String celular;
	@Column(name = "telefono", length = 15)
	private String telefono;
	@Column(name = "email", length = 100)
	private String email;
	@Column(name = "rutemp", length = 10)
	private String rutemp;
	@Column(name = "dvEmp", length = 1)
	private String dvEmp;
	@Column(name = "nomEmp", length = 80)
	private String nomEmp;
	@Column(name = "telEmp", length = 15)
	private String telEmp;
	@Column(name = "emailEmp", length = 100)
	private String emailEmp;
	@Column(name = "rutRepr", length = 10)
	private String rutRepr;
	@Column(name = "dvRepr", length = 1)
	private String dvRepr;
	@Column(name = "nomRepr", length = 80)
	private String nomRepr;
	@Column(name = "apePatRepr", length = 80)
	private String apePatRepr;
	@Column(name = "apeMatRepr", length = 80)
	private String apeMatRepr;
	@Column(name = "archivoAdj", length = 5000000)
	private byte[] archivoAdj;
	@Column(name = "mensaje", length = 1000)
	private String mensaje;
	@Column(name = "fechaEnvio")
	private Date fechaEnvio;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CommonsMultipartFile getFichero() {
		return fichero;
	}

	public void setFichero(CommonsMultipartFile fichero) {
		this.fichero = fichero;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApePat() {
		return apePat;
	}

	public void setApePat(String apePat) {
		this.apePat = apePat;
	}

	public String getApeMat() {
		return apeMat;
	}

	public void setApeMat(String apeMat) {
		this.apeMat = apeMat;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRutemp() {
		return rutemp;
	}

	public void setRutemp(String rutemp) {
		this.rutemp = rutemp;
	}

	public String getDvEmp() {
		return dvEmp;
	}

	public void setDvEmp(String dvEmp) {
		this.dvEmp = dvEmp;
	}

	public String getNomEmp() {
		return nomEmp;
	}

	public void setNomEmp(String nomEmp) {
		this.nomEmp = nomEmp;
	}

	public String getTelEmp() {
		return telEmp;
	}

	public void setTelEmp(String telEmp) {
		this.telEmp = telEmp;
	}

	public String getEmailEmp() {
		return emailEmp;
	}

	public void setEmailEmp(String emailEmp) {
		this.emailEmp = emailEmp;
	}

	public String getRutRepr() {
		return rutRepr;
	}

	public void setRutRepr(String rutRepr) {
		this.rutRepr = rutRepr;
	}

	public String getDvRepr() {
		return dvRepr;
	}

	public void setDvRepr(String dvRepr) {
		this.dvRepr = dvRepr;
	}

	public String getNomRepr() {
		return nomRepr;
	}

	public void setNomRepr(String nomRepr) {
		this.nomRepr = nomRepr;
	}

	public String getApePatRepr() {
		return apePatRepr;
	}

	public void setApePatRepr(String apePatRepr) {
		this.apePatRepr = apePatRepr;
	}

	public String getApeMatRepr() {
		return apeMatRepr;
	}

	public void setApeMatRepr(String apeMatRepr) {
		this.apeMatRepr = apeMatRepr;
	}

	public byte[] getArchivoAdj() {
		return archivoAdj;
	}

	public void setArchivoAdj(byte[] archivoAdj) {
		this.archivoAdj = archivoAdj;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

}
