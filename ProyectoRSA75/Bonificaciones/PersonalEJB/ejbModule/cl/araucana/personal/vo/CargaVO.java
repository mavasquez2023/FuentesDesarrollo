package cl.araucana.personal.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 *
 */
public class CargaVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;
	
	private long socioRut;
	private long cargaRut;
	private char cargaDv;
	private String cargaNombre;
	private String cargaApeMat;
	private String cargaApePat;
	private int cargaNum;
	private char cargaSexo;
	private char cargaTipo;
	private char cargaEstado;
	private Date cargaFecFin;
	private Date cargaFecIniBeneficios;
	private Date cargaFecNac;




	/**
	 * @return
	 */
	public String getCargaApeMat() {
		return cargaApeMat;
	}

	/**
	 * @return
	 */
	public String getCargaApePat() {
		return cargaApePat;
	}

	/**
	 * @return
	 */
	public char getCargaDv() {
		return cargaDv;
	}

	/**
	 * @return
	 */
	public char getCargaEstado() {
		return cargaEstado;
	}

	/**
	 * @return
	 */
	public Date getCargaFecFin() {
		return cargaFecFin;
	}

	/**
	 * @return
	 */
	public Date getCargaFecIniBeneficios() {
		return cargaFecIniBeneficios;
	}

	/**
	 * @return
	 */
	public Date getCargaFecNac() {
		return cargaFecNac;
	}

	/**
	 * @return
	 */
	public String getCargaNombre() {
		return cargaNombre;
	}

	/**
	 * @return
	 */
	public int getCargaNum() {
		return cargaNum;
	}

	/**
	 * @return
	 */
	public long getCargaRut() {
		return cargaRut;
	}

	/**
	 * @return
	 */
	public char getCargaSexo() {
		return cargaSexo;
	}

	/**
	 * @return
	 */
	public char getCargaTipo() {
		return cargaTipo;
	}

	/**
	 * @return
	 */
	public long getSocioRut() {
		return socioRut;
	}

	/**
	 * @param string
	 */
	public void setCargaApeMat(String string) {
		cargaApeMat = string;
	}

	/**
	 * @param string
	 */
	public void setCargaApePat(String string) {
		cargaApePat = string;
	}

	/**
	 * @param c
	 */
	public void setCargaDv(char c) {
		cargaDv = c;
	}

	/**
	 * @param c
	 */
	public void setCargaEstado(char c) {
		cargaEstado = c;
	}

	/**
	 * @param date
	 */
	public void setCargaFecFin(Date date) {
		cargaFecFin = date;
	}

	/**
	 * @param date
	 */
	public void setCargaFecIniBeneficios(Date date) {
		cargaFecIniBeneficios = date;
	}

	/**
	 * @param date
	 */
	public void setCargaFecNac(Date date) {
		cargaFecNac = date;
	}

	/**
	 * @param string
	 */
	public void setCargaNombre(String string) {
		cargaNombre = string;
	}

	/**
	 * @param i
	 */
	public void setCargaNum(int i) {
		cargaNum = i;
	}

	/**
	 * @param l
	 */
	public void setCargaRut(long l) {
		cargaRut = l;
	}

	/**
	 * @param c
	 */
	public void setCargaSexo(char c) {
		cargaSexo = c;
	}

	/**
	 * @param c
	 */
	public void setCargaTipo(char c) {
		cargaTipo = c;
	}

	/**
	 * @param l
	 */
	public void setSocioRut(long l) {
		socioRut = l;
	}

}
