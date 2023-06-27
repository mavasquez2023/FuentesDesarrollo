package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class InfoMovimientoPreCasoVO implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;
	
	private long casoId=0;
	private String rut=null;
	private String digito=null;
	private String nombre=null;	
	private Date fecha=null;
	private double monto=0;
	private long folioTesoreria=0;
	private String usuario=null;
	private String tipoMovimiento=null;
	private Collection detalles=new ArrayList(); //DetalleMovimientoPreCasoVO


	/**
	 * @return
	 */
	public String getFullRut() {
		return rut + "-" + digito;
	}

	/**
	 * @return
	 */
	public long getCasoId() {
		return casoId;
	}

	/**
	 * @return
	 */
	public String getDigito() {
		return digito;
	}

	/**
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @return
	 */
	public long getFolioTesoreria() {
		return folioTesoreria;
	}

	/**
	 * @return
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @return
	 */
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	/**
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param l
	 */
	public void setCasoId(long l) {
		casoId = l;
	}

	/**
	 * @param string
	 */
	public void setDigito(String string) {
		digito = string;
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
	public void setFolioTesoreria(long l) {
		folioTesoreria = l;
	}

	/**
	 * @param d
	 */
	public void setMonto(double d) {
		monto = d;
	}

	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

	/**
	 * @param string
	 */
	public void setTipoMovimiento(String string) {
		tipoMovimiento = string;
	}

	/**
	 * @param string
	 */
	public void setUsuario(String string) {
		usuario = string;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @return
	 */
	public Collection getDetalles() {
		return detalles;
	}

	/**
	 * @param collection
	 */
	public void setDetalles(Collection collection) {
		detalles = collection;
	}

}
