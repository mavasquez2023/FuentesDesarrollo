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
public class ClaveVO implements Serializable {

	//public static final String STD_ACTIVA="A";
	
	private int claveInicial=0;
	private int clavePersonal=0;
	private Date fechaUltBloqueo=null;
	private Time horaUltBloqueo=null;

	/**
	 * @return
	 */
	public int getClaveInicial() {
		return claveInicial;
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
	public Date getFechaUltBloqueo() {
		return fechaUltBloqueo;
	}

	/**
	 * @return
	 */
	public Time getHoraUltBloqueo() {
		return horaUltBloqueo;
	}

	/**
	 * @param i
	 */
	public void setClaveInicial(int i) {
		claveInicial = i;
	}

	/**
	 * @param i
	 */
	public void setClavePersonal(int i) {
		clavePersonal = i;
	}

	/**
	 * @param date
	 */
	public void setFechaUltBloqueo(Date date) {
		fechaUltBloqueo = date;
	}

	/**
	 * @param time
	 */
	public void setHoraUltBloqueo(Time time) {
		horaUltBloqueo = time;
	}

}
