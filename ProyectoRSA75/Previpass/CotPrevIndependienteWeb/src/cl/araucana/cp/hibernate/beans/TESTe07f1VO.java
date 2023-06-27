package cl.araucana.cp.hibernate.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class TESTe07f1VO implements Serializable
{
	private static final long serialVersionUID = -1388337810269658021L;
	private long te3wa;//folio
	private char te3ya;//Estado comprobante: Cursado 'C'
	private Date te40a;//Fecha Recaudacion Pago por Caja: Igual a la fecha del dia
	private Time te1ta;//Hora de recaudacion Pago por Caja: Igual a la hora actual
	private char te41a;//Forma de Pago: PACC 'P'
	private Date obf005;//fechaCambio
	private Time obf006;//horaCambio
	private String sajkm92;//ultimoUsuarioModifico
	private int te1ba;//para el filtro 1 de busqueda del estado
	private Date te1ca;//para el filtro 2 de busqueda del estado
	private int te10a;//para el filtro 2 de busqueda del estado
	private long te7ma;

	public TESTe07f1VO()
	{
		super();
	}

	public TESTe07f1VO(char te3ya, Date te40a, Time te1ta, char te41a, Date obf005, Time obf006, String sajkm92)
	{
		super();
		this.te3ya = te3ya;
		this.te40a = te40a;
		this.te1ta = te1ta;
		this.te41a = te41a;
		this.obf005 = obf005;
		this.obf006 = obf006;
		this.sajkm92 = sajkm92;
	}

	public TESTe07f1VO(long te3wa, char te3ya, Date te40a, Time te1ta, char te41a, Date obf005, Time obf006, String sajkm92)
	{
		super();
		this.te3wa = te3wa;
		this.te3ya = te3ya;
		this.te40a = te40a;
		this.te1ta = te1ta;
		this.te41a = te41a;
		this.obf005 = obf005;
		this.obf006 = obf006;
		this.sajkm92 = sajkm92;
	}

	/**
	 * Fecha de cambio
	 * @return
	 */
	public Date getObf005()
	{
		return this.obf005;
	}
	/**
	 * Fecha de cambio
	 * @param obf005
	 */
	public void setObf005(Date obf005)
	{
		this.obf005 = obf005;
	}
	
	/**
	 * Hora de Cambio
	 * @return
	 */
	public Time getObf006()
	{
		return this.obf006;
	}
	/**
	 * Hora de Cambio
	 * @param obf006
	 */
	public void setObf006(Time obf006)
	{
		this.obf006 = obf006;
	}
	
	/**
	 * Último Usuario Modificó
	 * @return
	 */
	public String getSajkm92()
	{
		return this.sajkm92;
	}
	/**
	 * Último Usuario Modificó
	 * @param sajkm92
	 */
	public void setSajkm92(String sajkm92)
	{
		this.sajkm92 = sajkm92;
	}

	/**
	 * Hora Recaudación Pago por Caja
	 * @return
	 */
	public Time getTe1ta()
	{
		return this.te1ta;
	}
	/**
	 * Hora Recaudación Pago por Caja
	 * @param te1ta
	 */
	public void setTe1ta(Time te1ta)
	{
		this.te1ta = te1ta;
	}
	/**
	 * Folio
	 * @return
	 */
	public long getTe3wa()
	{
		return this.te3wa;
	}
	/**
	 * Folio
	 * @param te3wa
	 */
	public void setTe3wa(long te3wa)
	{
		this.te3wa = te3wa;
	}

	/**
	 * Estado del Comprobante
	 * @return
	 */
	public char getTe3ya()
	{
		return this.te3ya;
	}
	/**
	 * Estado del Comprobante
	 * @param te3ya
	 */
	public void setTe3ya(char te3ya)
	{
		this.te3ya = te3ya;
	}
	/**
	 * Fecha Recaudación Pago por Caja
	 * @return
	 */
	public Date getTe40a()
	{
		return this.te40a;
	}
	/**
	 * Fecha Recaudación Pago por Caja
	 * @param te40a
	 */
	public void setTe40a(Date te40a)
	{
		this.te40a = te40a;
	}
	/**
	 * Forma de Pago
	 * @return
	 */
	public char getTe41a()
	{
		return this.te41a;
	}
	/**
	 * Forma de Pago
	 * @param te41a
	 */
	public void setTe41a(char te41a)
	{
		this.te41a = te41a;
	}

	/**
	 * Para el filtro 3 de búsqueda del estado
	 * @return
	 */
	public int getTe10a() {
		return this.te10a;
	}
	/**
	 * Para el filtro 3 de búsqueda del estado
	 * @param te10a
	 */
	public void setTe10a(int te10a) {
		this.te10a = te10a;
	}

	/**
	 * Para el filtro 1 de búsqueda del estado
	 * @return
	 */
	public int getTe1ba() {
		return this.te1ba;
	}
	/**
	 * Para el filtro 1 de búsqueda del estado
	 * @param te1ba
	 */
	public void setTe1ba(int te1ba) {
		this.te1ba = te1ba;
	}

	/**
	 * Para el filtro 2 de búsqueda del estado
	 * @return
	 */
	public Date getTe1ca() {
		return this.te1ca;
	}
	/**
	 * Para el filtro 2 de búsqueda del estado
	 * @param te1ca
	 */
	public void setTe1ca(Date te1ca) {
		this.te1ca = te1ca;
	}

	public TESTe07f1VO(long te3wa) {
		super();
		this.te3wa = te3wa;
	}

	public long getTe7ma() {
		return te7ma;
	}

	public void setTe7ma(long te7ma) {
		this.te7ma = te7ma;
	}
	
}
