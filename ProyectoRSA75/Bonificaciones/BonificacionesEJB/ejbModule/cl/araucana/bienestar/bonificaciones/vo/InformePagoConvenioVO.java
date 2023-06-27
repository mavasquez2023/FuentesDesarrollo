package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class InformePagoConvenioVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private Date fechaPago=null;
	private long codigoPago=0;
	private int montoTotal=0;
	private int filas=0;
	private ArrayList detalleConvenios=new ArrayList(); //DetallePagoConvenioVO

	/**
	 * @return
	 */
	public long getCodigoPago() {
		return codigoPago;
	}

	/**
	 * @return
	 */
	public ArrayList getDetalleConvenios() {
		return detalleConvenios;
	}

	/**
	 * @return
	 */
	public Date getFechaPago() {
		return fechaPago;
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
	 * @param l
	 */
	public void setCodigoPago(long l) {
		codigoPago = l;
	}

	/**
	 * @param list
	 */
	public void setDetalleConvenios(ArrayList list) {
		detalleConvenios = list;
	}

	/**
	 * @param date
	 */
	public void setFechaPago(Date date) {
		fechaPago = date;
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
