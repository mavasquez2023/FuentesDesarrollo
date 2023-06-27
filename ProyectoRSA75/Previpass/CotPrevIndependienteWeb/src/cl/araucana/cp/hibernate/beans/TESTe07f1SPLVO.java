package cl.araucana.cp.hibernate.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class TESTe07f1SPLVO implements Serializable
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

	public TESTe07f1SPLVO()
	{
		super();
	}

	public TESTe07f1SPLVO(char te3ya, Date te40a, Time te1ta, char te41a, Date obf005, Time obf006, String sajkm92)
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

	public TESTe07f1SPLVO(long te3wa, char te3ya, Date te40a, Time te1ta, char te41a, Date obf005, Time obf006, String sajkm92)
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

	public Date getObf005()
	{
		return this.obf005;
	}
	public void setObf005(Date obf005)
	{
		this.obf005 = obf005;
	}
	public Time getObf006()
	{
		return this.obf006;
	}
	public void setObf006(Time obf006)
	{
		this.obf006 = obf006;
	}
	public String getSajkm92()
	{
		return this.sajkm92;
	}
	public void setSajkm92(String sajkm92)
	{
		this.sajkm92 = sajkm92;
	}
	public Time getTe1ta()
	{
		return this.te1ta;
	}
	public void setTe1ta(Time te1ta)
	{
		this.te1ta = te1ta;
	}
	public long getTe3wa()
	{
		return this.te3wa;
	}
	public void setTe3wa(long te3wa)
	{
		this.te3wa = te3wa;
	}
	public char getTe3ya()
	{
		return this.te3ya;
	}
	public void setTe3ya(char te3ya)
	{
		this.te3ya = te3ya;
	}
	public Date getTe40a()
	{
		return this.te40a;
	}
	public void setTe40a(Date te40a)
	{
		this.te40a = te40a;
	}
	public char getTe41a()
	{
		return this.te41a;
	}

	public void setTe41a(char te41a)
	{
		this.te41a = te41a;
	}

	public TESTe07f1SPLVO(long te3wa) {
		super();
		this.te3wa = te3wa;
	}
}
