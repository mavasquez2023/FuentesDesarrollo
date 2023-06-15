package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class PeriodoVO implements Serializable {
	private long rePeriodo=0;
	private long codPeriodo=0;
	private String descPeriodo="";

	/**
	 * @return
	 */
	public long getCodPeriodo() {
		System.out.println("codPeriodo: " + codPeriodo);
		return codPeriodo;
	}

	/**
	 * @return
	 */
	public String getDescPeriodo() {
		return descPeriodo;
	}

	/**
	 * @param l
	 */
	public void setCodPeriodo(long l) {
		codPeriodo = l;
	}

	/**
	 * @param string
	 */
	public void setDescPeriodo(String string) {
		descPeriodo = string;
	}

	/**
	 * @return
	 */
	public long getRePeriodo() {
		return rePeriodo;
	}

	/**
	 * @param l
	 */
	public void setRePeriodo(long l) {
		rePeriodo = l;
	}

}
