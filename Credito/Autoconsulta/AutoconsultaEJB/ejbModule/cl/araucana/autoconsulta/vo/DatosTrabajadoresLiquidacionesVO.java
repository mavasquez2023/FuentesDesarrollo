/*
 * Creado el 16-11-2005
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.autoconsulta.vo;

import java.io.Serializable;

/**
 * @author USIST15
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class DatosTrabajadoresLiquidacionesVO implements Serializable{

	private long convenioID = 0;
	private long rut = 0;
	private long rutEmp = 0;
	private String dv = null;
	private String dvEmp = null;
	private String nombre = null;
	private String apellido = null;
	private String mail = null;
	private String ctaCte = null;
	private int bancoID = 0;
	private String nombreBanco = null;
	private String nombreEmpresa = null;
	private String fullNombre = null;
	private String fullRutTra = null;
	private String fullRutEmpresa = null;
	
	public String getFullNombre(){
		return nombre.trim() + " " + apellido.trim();
	}

	public String getFullRut(){
		return String.valueOf(rut) + "-" + dv;
		
	}
	/**
	 * @return Apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @return ID del banco
	 */
	public int getBancoID() {
		return bancoID;
	}

	/**
	 * @return id del convenio
	 */
	public long getConvenioID() {
		return convenioID;
	}

	/**
	 * @return la cuenta corriente
	 */
	public String getCtaCte() {
		return ctaCte;
	}

	/**
	 * @return digito verificador
	 */
	public String getDv() {
		return dv;
	}

	/**
	 * @return mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return nombre banco
	 */
	public String getNombreBanco() {
		return nombreBanco;
	}

	/**
	 * @return nombre empresa.
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @return rut
	 */
	public long getRut() {
		return rut;
	}

	/**
	 * @param string
	 */
	public void setApellido(String string) {
		apellido = string;
	}

	/**
	 * @param i
	 */
	public void setBancoID(int i) {
		bancoID = i;
	}

	/**
	 * @param l
	 */
	public void setConvenioID(long l) {
		convenioID = l;
	}

	/**
	 * @param string
	 */
	public void setCtaCte(String string) {
		ctaCte = string;
	}

	/**
	 * @param string
	 */
	public void setDv(String string) {
		dv = string;
	}

	/**
	 * @param string
	 */
	public void setMail(String string) {
		mail = string;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @param string
	 */
	public void setNombreBanco(String string) {
		nombreBanco = string;
	}

	/**
	 * @param string
	 */
	public void setNombreEmpresa(String string) {
		nombreEmpresa = string;
	}

	/**
	 * @param l
	 */
	public void setRut(long l) {
		rut = l;
	}

	/**
	 * @return
	 */
	public String getFullRutTra() {
		return getRut() + " - " +getDv();
	}

	/**
	 * @param string
	 */
	public void setFullNombre(String string) {
		fullNombre = string;
	}

	/**
	 * @param string
	 */
	public void setFullRutTra(String string) {
		fullRutTra = string;
	}
	
	

	/**
	 * @param l
	 */
	public void setRutEmpresa(long l) {
		rutEmp = l;
		// TODO Apéndice de método generado automáticamente
		
	}

	/**
	 * @param string
	 */
	public void setDvEmpresa(String dv) {
		dvEmp = dv;
		// TODO Apéndice de método generado automáticamente
		
	}
	
	public long getRutEmpresa(){
		return rutEmp;
	}
	


	/**
	 * @return
	 */
	public String getFullRutEmpresa() {
		fullRutEmpresa = getRutEmpresa() + " - " + getDvEmpresa();
		return fullRutEmpresa;
	}

	/**
	 * @return
	 */
	private String getDvEmpresa() {
		// TODO Apéndice de método generado automáticamente
		return dvEmp;
	}

	/**
	 * @param string
	 */
	public void setFullRutEmpresa(String string) {
		fullRutEmpresa = string;
	}

}
