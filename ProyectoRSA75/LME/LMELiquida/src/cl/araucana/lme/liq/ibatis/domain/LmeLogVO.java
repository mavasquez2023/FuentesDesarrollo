/*
 * Created on 10-11-2011
 *
 */
package cl.araucana.lme.liq.ibatis.domain;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author j-factory
 *
 */
public class LmeLogVO implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 4593905078531169371L;
	protected String tipoEvento;//TIPO
	protected String evento;//EVENTO
	protected String codOpe;//CODOPE
	protected String fechaEvento;//FECLOG
	protected String horaEvento;//HORLOG
	protected String msg;//MENSAJE	

	/**
	 * 
	 */
	public LmeLogVO() {
		super();
	}

	/**
	 * @param tipoEvento
	 * @param evento
	 * @param codOpe
	 * @param fechaEvento
	 * @param horaEvento
	 */
	public LmeLogVO(String tipoEvento, String evento, String codOpe, String fechaEvento, String horaEvento, String msg) {
		super();
		this.tipoEvento = tipoEvento;
		this.evento = evento;
		this.codOpe = codOpe;
		this.fechaEvento = fechaEvento;
		this.horaEvento = horaEvento;
		this.msg = msg;
	}

	/**
	 * @return Returns the codOpe.
	 */
	public String getCodOpe() {
		return codOpe;
	}

	/**
	 * @param codOpe The codOpe to set.
	 */
	public void setCodOpe(String codOpe) {
		this.codOpe = codOpe;
	}

	/**
	 * @return Returns the evento.
	 */
	public String getEvento() {
		return evento;
	}

	/**
	 * @param evento The evento to set.
	 */
	public void setEvento(String evento) {
		this.evento = evento;
	}

	/**
	 * @return Returns the fechaEvento.
	 */
	public String getFechaEvento() {
		return fechaEvento;
	}

	/**
	 * @param fechaEvento The fechaEvento to set.
	 */
	public void setFechaEvento(String fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	/**
	 * @return Returns the horaEvento.
	 */
	public String getHoraEvento() {
		return horaEvento;
	}

	/**
	 * @param horaEvento The horaEvento to set.
	 */
	public void setHoraEvento(String horaEvento) {
		this.horaEvento = horaEvento;
	}

	/**
	 * @return Returns the tipoEvento.
	 */
	public String getTipoEvento() {
		return tipoEvento;
	}

	/**
	 * @param tipoEvento The tipoEvento to set.
	 */
	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	/**
	 * @return Returns the msg.
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg The msg to set.
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		try {
			Class c = Class.forName(this.getClass().getName());
			Field[] f = c.getDeclaredFields();
			for (int i = 0; i < f.length; i++)
				sb.append(f[i].getName() + "=").append(f[i].get(this)).append("\n");

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
