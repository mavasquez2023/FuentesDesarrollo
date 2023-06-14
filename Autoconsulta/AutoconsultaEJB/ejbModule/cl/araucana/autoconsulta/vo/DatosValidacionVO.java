package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class DatosValidacionVO implements Serializable {
	
	private long id=0;
	private int tipo=0;
	private Date fecha=null;
	private long rut=0;
	private String fullNombre=null;
	private String fullRut=null;
	private Collection listaValores = new ArrayList(); // ValorValidableVO
	

	/**
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return
	 */
	public Collection getListaValores() {
		return listaValores;
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
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param date
	 */
	public void setFecha(Date date) {
		fecha = date;
	}

	/**
	 * @param l
	 */
	public void setId(long l) {
		id = l;
	}

	/**
	 * @param collection
	 */
	public void setListaValores(Collection collection) {
		listaValores = collection;
	}

	/**
	 * @param l
	 */
	public void setRut(long l) {
		rut = l;
	}

	/**
	 * @param i
	 */
	public void setTipo(int i) {
		tipo = i;
	}

	/**
	 * @return
	 */
	public String getFullNombre() {
		return fullNombre;
	}

	/**
	 * @return
	 */
	public String getFullRut() {
		return fullRut;
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
	public void setFullRut(String string) {
		fullRut = string;
	}

}
