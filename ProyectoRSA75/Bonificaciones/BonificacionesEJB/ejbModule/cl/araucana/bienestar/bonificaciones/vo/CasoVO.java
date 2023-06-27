package cl.araucana.bienestar.bonificaciones.vo;

import java.util.Date;

import cl.araucana.bienestar.bonificaciones.model.Caso;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class CasoVO extends Caso{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3456891710165427540L;
	private String dvRutSocio = null;
	private String nombreSocio = null;
	private String dvRutCarga = null;
	private Date fecIngSocio=null;
	private String nombreCarga = null;
	private String nombreConvenio = null;
	private int numeroMaximoCuotas = 0;
	private long codigoConcepto = 0;
	private String descripcionConcepto = null;
	
	private int montoEgresoTesoreriaPrevio = 0;
	private int montoIngresoIsapreTesoreriaPrevio = 0;
	private int montoIngresoOtrosTesoreriaPrevio = 0;
	
	
	/**
	 * Devuelve el monto sobre el cual se generará el egreso (se descuenta los egresos previos)
	 * @return int
	 */
	public int getMontoEgresoTesoreria() {
		//return (int) (super.getTotal() - montoEgresoTesoreriaPrevio);
		return (int) (super.getTotal() - montoEgresoTesoreriaPrevio + montoIngresoIsapreTesoreriaPrevio + montoIngresoOtrosTesoreriaPrevio);
	}
	
	/**
	 * Devuelve el monto sobre el cual se generará el ingreso isapre (se descuenta los ingresos "isapre" previos)
	 *  @return int
	 */
	public int getMontoIngresoIsapreTesoreria() {
		return (int) (super.getAporteIsapre() - montoIngresoIsapreTesoreriaPrevio);
	}	

	/**
	 * Devuelve el monto sobre el cual se generará el ingreso otros (se descuenta los ingresos "otros" previos)
	 *  @return int
	 */
	public int getMontoIngresoOtrosTesoreria() {
		return (int) (super.getMontoDescuento() - montoIngresoOtrosTesoreriaPrevio);
	}
	
	/**
	 * Retorna el rut compuesto del Socio
	 * @return String con el rut
	 */
	public String getFullRutSocio() {
		return super.getRutSocio() + "-" + dvRutSocio;
	}
	
	/**
	 * Retorna el rut compuesto de la Carga
	 * @return String con el rut
	 */
	public String getFullRutCarga() {
		return super.getRutCarga() + "-" + dvRutCarga;
	}
	

	/**
	 * @return
	 */
	public String getDvRutCarga() {
		return dvRutCarga;
	}

	/**
	 * @return
	 */
	public String getDvRutSocio() {
		return dvRutSocio;
	}

	/**
	 * @return
	 */
	public String getNombreCarga() {
		return nombreCarga;
	}

	/**
	 * @return
	 */
	public String getNombreConvenio() {
		return nombreConvenio;
	}

	/**
	 * @return
	 */
	public String getNombreSocio() {
		return nombreSocio;
	}

	/**
	 * @param string
	 */
	public void setDvRutCarga(String string) {
		dvRutCarga = string;
	}

	/**
	 * @param string
	 */
	public void setDvRutSocio(String string) {
		dvRutSocio = string;
	}

	/**
	 * @param string
	 */
	public void setNombreCarga(String string) {
		nombreCarga = string;
	}

	/**
	 * @param string
	 */
	public void setNombreConvenio(String string) {
		nombreConvenio = string;
	}

	/**
	 * @param string
	 */
	public void setNombreSocio(String string) {
		nombreSocio = string;
	}


	/**
	 * @return
	 */
	public int getNumeroMaximoCuotas() {
		return numeroMaximoCuotas;
	}

	/**
	 * @param i
	 */
	public void setNumeroMaximoCuotas(int i) {
		numeroMaximoCuotas = i;
	}

	/**
	 * @return
	 */
	public Date getFecIngSocio() {
		return fecIngSocio;
	}

	/**
	 * @param date
	 */
	public void setFecIngSocio(Date date) {
		fecIngSocio = date;
	}

	/**
	 * @return
	 */
	public long getCodigoConcepto() {
		return codigoConcepto;
	}

	/**
	 * @return
	 */
	public String getDescripcionConcepto() {
		return descripcionConcepto;
	}

	/**
	 * @param l
	 */
	public void setCodigoConcepto(long l) {
		codigoConcepto = l;
	}

	/**
	 * @param string
	 */
	public void setDescripcionConcepto(String string) {
		descripcionConcepto = string;
	}

	/**
	 * @return
	 */
	public int getMontoEgresoTesoreriaPrevio() {
		return montoEgresoTesoreriaPrevio;
	}

	/**
	 * @param i
	 */
	public void setMontoEgresoTesoreriaPrevio(int i) {
		montoEgresoTesoreriaPrevio = i;
	}



	/**
	 * @return
	 */
	public int getMontoIngresoIsapreTesoreriaPrevio() {
		return montoIngresoIsapreTesoreriaPrevio;
	}

	/**
	 * @param i
	 */
	public void setMontoIngresoIsapreTesoreriaPrevio(int i) {
		montoIngresoIsapreTesoreriaPrevio = i;
	}

	/**
	 * @return
	 */
	public int getMontoIngresoOtrosTesoreriaPrevio() {
		return montoIngresoOtrosTesoreriaPrevio;
	}

	/**
	 * @param i
	 */
	public void setMontoIngresoOtrosTesoreriaPrevio(int i) {
		montoIngresoOtrosTesoreriaPrevio = i;
	}

}
