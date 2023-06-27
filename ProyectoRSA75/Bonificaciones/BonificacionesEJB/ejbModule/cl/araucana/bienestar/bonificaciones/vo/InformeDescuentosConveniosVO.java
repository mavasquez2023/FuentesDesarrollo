package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class InformeDescuentosConveniosVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private Date fechaDescuento=null;
	private long codigoDescuento=0;
	private int montoTotal=0;
	private int filas=0;
	private ArrayList detalleConvenios=new ArrayList(); //DetalleDescuentosConveniosVO


	public InformeDescuentosConveniosVO() {
	}
	

	/**
	 * @return
	 */
	public Date getFechaDescuento() {
		return fechaDescuento;
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

	/**
	 * @return
	 */
	public ArrayList getDetalleConvenios() {
		return detalleConvenios;
	}

	/**
	 * @param list
	 */
	public void setDetalleConvenios(ArrayList list) {
		detalleConvenios = list;
	}

}
