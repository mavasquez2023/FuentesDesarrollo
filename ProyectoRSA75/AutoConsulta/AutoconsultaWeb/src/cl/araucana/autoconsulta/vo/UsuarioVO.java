package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class UsuarioVO implements Serializable {

	private long rutusuarioAutenticado = 0;
	private long rut = 0;
	private String dv = null;
	private String nombre = null;
	private long rutAfiliado = 0;
	private long rutEmpresa = 0;
	private boolean esAfiliadoActivo = false;
	private boolean esAfiliadoCesado = false;
	private boolean esPensionado = false;
	private boolean esAhorrante = false;
	private boolean esEmpresa = false;
	private boolean esEncargadoEmpresa = false;
	private boolean esPublico = false;
	private boolean esEmpresaPublica = false;
	private int autenticacion = 0;
	private String ipConexion = null;

	public UsuarioVO(){
		
	}
	
	public UsuarioVO(UsuarioVO copia) {
		this.rutusuarioAutenticado = copia.getRutusuarioAutenticado();
		this.rut = copia.getRut();
		this.dv = copia.getDv();
		this.nombre = copia.getNombre();
		this.esAfiliadoActivo = copia.isEsAfiliadoActivo();
		this.esAfiliadoCesado = copia.isEsAfiliadoCesado();
		this.esPensionado = copia.isEsPensionado();
		this.esAhorrante = copia.isEsAhorrante();
		this.esEmpresa = copia.isEsEmpresa();
		this.autenticacion = copia.getAutenticacion();
		this.esEmpresaPublica = copia.isEsEmpresaPublica();
	}

	/**
	 * Devuelve el rut completo
	 * @return
	 */
	public String getFullRut() {
		return rut + " - " + dv;
	}

	/**
	 * @return
	 */
	public String getDv() {
		return dv;
	}

	/**
	 * @return
	 */
	public boolean isEsAfiliadoActivo() {
		return esAfiliadoActivo;
	}

	/**
	 * @return
	 */
	public boolean isEsAfiliadoCesado() {
		return esAfiliadoCesado;
	}

	/**
	 * @return
	 */
	public boolean isEsAhorrante() {
		return esAhorrante;
	}

	/**
	 * @return
	 */
	public boolean isEsEmpresa() {
		return esEmpresa;
	}

	/**
	 * @return
	 */
	public boolean isEsPensionado() {
		return esPensionado;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return
	 */
	public long getRut() {
		return rut;
	}

	/**
	 * @param string
	 */
	public void setDv(String string) {
		dv = string;
	}

	/**
	 * @param b
	 */
	public void setEsAfiliadoActivo(boolean b) {
		esAfiliadoActivo = b;
	}

	/**
	 * @param b
	 */
	public void setEsAfiliadoCesado(boolean b) {
		esAfiliadoCesado = b;
	}

	/**
	 * @param b
	 */
	public void setEsAhorrante(boolean b) {
		esAhorrante = b;
	}

	/**
	 * @param b
	 */
	public void setEsEmpresa(boolean b) {
		esEmpresa = b;
	}

	/**
	 * @param b
	 */
	public void setEsPensionado(boolean b) {
		esPensionado = b;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
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
	public int getAutenticacion() {
		return autenticacion;
	}

	/**
	 * @param i
	 */
	public void setAutenticacion(int i) {
		autenticacion = i;
	}

	/**
	 * @return
	 */
	public long getRutusuarioAutenticado() {
		return rutusuarioAutenticado;
	}

	/**
	 * @param l
	 */
	public void setRutusuarioAutenticado(long l) {
		rutusuarioAutenticado = l;
	}

	/**
	 * @return
	 */
	public boolean isEsEncargadoEmpresa() {
		return esEncargadoEmpresa;
	}

	/**
	 * @param b
	 */
	public void setEsEncargadoEmpresa(boolean b) {
		esEncargadoEmpresa = b;
	}

	/**
	 * @return
	 */
	public boolean isEsPublico() {
		return esPublico;
	}

	/**
	 * @param b
	 */
	public void setEsPublico(boolean b) {
		esPublico = b;
	}

	/**
	 * @return
	 */
	public boolean isEsEmpresaPublica() {
		return esEmpresaPublica;
	}

	/**
	 * @param b
	 */
	public void setEsEmpresaPublica(boolean b) {
		esEmpresaPublica = b;
	}

	public String getIpConexion() {
		return ipConexion;
	}

	public void setIpConexion(String ipConexion) {
		this.ipConexion = ipConexion;
	}

	public long getRutAfiliado() {
		return rutAfiliado;
	}

	public void setRutAfiliado(long rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}

	public long getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(long rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

}
