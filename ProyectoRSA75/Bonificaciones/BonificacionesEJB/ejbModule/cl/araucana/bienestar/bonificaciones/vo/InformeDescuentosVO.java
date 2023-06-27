package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class InformeDescuentosVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private Date fechaDescuento=null;
	private long codigoDescuento=0;
	private int montoTotal=0;
	private int filas=0;
	private ArrayList detalleOficinas=new ArrayList(); //DetalleDescuentosOficinaVO

	public InformeDescuentosVO() {
	}
	

	/**
	 * @return
	 */
	public ArrayList getDetalleOficinas() {
		return detalleOficinas;
	}

	/**
	 * @return
	 */
	public Date getFechaDescuento() {
		return fechaDescuento;
	}

	/**
	 * @param list
	 */
	public void setDetalleOficinas(ArrayList list) {
		detalleOficinas = list;
	}

	/**
	 * @param date
	 */
	public void setFechaDescuento(Date date) {
		fechaDescuento = date;
	}

	/**
	 * @return
	 */
	public long getCodigoDescuento() {
		return codigoDescuento;
	}

	/**
	 * @param l
	 */
	public void setCodigoDescuento(long l) {
		codigoDescuento = l;
	}

	/**
	 * @return
	 */
	public int getFilas() {
		return filas;
	}

	/**
	 * @return
	 */
	public int getMontoTotal() {
		return montoTotal;
	}

	/**
	 * @param i
	 */
	public void setFilas(int i) {
		filas = i;
	}

	/**
	 * @param i
	 */
	public void setMontoTotal(int i) {
		montoTotal = i;
	}

}
