package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class DetalleDescuentosConveniosVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private Date fechaDescuento=null;
	private long codigoConvenio=0;
	private String convenio=null;
	private String rut=null;
	private String dv=null;
	private int montoTotal=0;
	private int filas=0;
	private ArrayList detalleCasos=new ArrayList(); //DetalleCasosDescontadosConvenioVO

	/**
	 * @return
	 */
	public String getFullRut() {
		return rut + " - " + dv;
	}

	/**
	 * @return
	 */
	public String getConvenio() {
		return convenio;
	}

	/**
	 * @return
	 */
	public String getDv() {
		return dv;
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
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param string
	 */
	public void setConvenio(String string) {
		convenio = string;
	}

	/**
	 * @param string
	 */
	public void setDv(String string) {
		dv = string;
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
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

	/**
	 * @return
	 */
	public ArrayList getDetalleCasos() {
		return detalleCasos;
	}

	/**
	 * @param list
	 */
	public void setDetalleCasos(ArrayList list) {
		detalleCasos = list;
	}

	/**
	 * @return
	 */
	public long getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @param l
	 */
	public void setCodigoConvenio(long l) {
		codigoConvenio = l;
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

}
