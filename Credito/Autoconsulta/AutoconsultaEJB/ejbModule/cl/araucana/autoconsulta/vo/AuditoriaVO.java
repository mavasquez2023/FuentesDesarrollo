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
public class AuditoriaVO implements Serializable {
	
	private long rut=0;
	private Date fechaTransaccion=null;
	private Time horaTransaccion=null;
	private int codigoTransaccion=0;
	private String rutOperador=null;
	private String idOperador=null;
	private int claveInicio=0;
	private int clavePersonal=0;
	

	/**
	 * @return
	 */
	public int getClaveInicio() {
		return claveInicio;
	}

	/**
	 * @return
	 */
	public int getClavePersonal() {
		return clavePersonal;
	}

	/**
	 * @return
	 */
	public int getCodigoTransaccion() {
		return codigoTransaccion;
	}

	/**
	 * @return
	 */
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	/**
	 * @return
	 */
	public Time getHoraTransaccion() {
		return horaTransaccion;
	}

	/**
	 * @return
	 */
	public String getIdOperador() {
		return idOperador;
	}

	/**
	 * @return
	 */
	public long getRut() {
		return rut;
	}

	/**
	 * @return
	 */
	public String getRutOperador() {
		return rutOperador;
	}

	/**
	 * @param i
	 */
	public void setClaveInicio(int i) {
		claveInicio = i;
	}

	/**
	 * @param i
	 */
	public void setClavePersonal(int i) {
		clavePersonal = i;
	}

	/**
	 * @param i
	 */
	public void setCodigoTransaccion(int i) {
		codigoTransaccion = i;
	}

	/**
	 * @param date
	 */
	public void setFechaTransaccion(Date date) {
		fechaTransaccion = date;
	}

	/**
	 * @param time
	 */
	public void setHoraTransaccion(Time time) {
		horaTransaccion = time;
	}

	/**
	 * @param string
	 */
	public void setIdOperador(String string) {
		idOperador = string;
	}

	/**
	 * @param l
	 */
	public void setRut(long l) {
		rut = l;
	}

	/**
	 * @param string
	 */
	public void setRutOperador(String string) {
		rutOperador = string;
	}

}
