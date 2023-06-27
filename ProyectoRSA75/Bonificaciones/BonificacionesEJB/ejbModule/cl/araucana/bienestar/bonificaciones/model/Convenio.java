package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class Convenio implements Serializable {

	/** Serial */
	private static final long serialVersionUID = 1L;

	
	public static final String STD_BORRADOR="BORRADOR";	
	public static final String STD_ACTIVO="ACTIVO";	
	public static final String STD_ELIMINADO="ELIMINADO";	
	
	private long codigo = 0;
	private long codigoConcepto = 0;
	private String descripcionConcepto = null;
	private String rut = null;
	private String dvRut = null;
	private String nombre = null;
	private int numeroMaximoCuotas = 0;
	private String estado = STD_BORRADOR;
	private Date fecInicio = null;
	private Date fecFin = null;
//	private long conceptoTesoreria=0;
	private ArrayList productos = new ArrayList();
	private ArrayList talonarios = new ArrayList();
	
	public Talonario getTalonario(int index) {
		return (Talonario)talonarios.get(index);
	}
	
	public Producto getProducto(int index) {
		return (Producto)productos.get(index);
	}
	
	public void removeAllTalonarios() {
		talonarios.clear();
	}

	public void removeAllProductos() {
		productos.clear();
	}

	/**
	 * @return
	 */
	public long getCodigo() {
		return codigo;
	}


	/**
	 * @return
	 */
	public String getDescripcionConcepto() {
		return descripcionConcepto;
	}

	/**
	 * @return
	 */
	public String getDvRut() {
		return dvRut;
	}

	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return
	 */
	public int getNumeroMaximoCuotas() {
		return numeroMaximoCuotas;
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
	public void setCodigo(long l) {
		codigo = l;
	}


	/**
	 * @param string
	 */
	public void setDescripcionConcepto(String string) {
		descripcionConcepto = string;
	}

	/**
	 * @param string
	 */
	public void setDvRut(String string) {
		dvRut = string;
	}

	/**
	 * @param string
	 */
	public void setEstado(String string) {
		estado = string;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @param i
	 */
	public void setNumeroMaximoCuotas(int i) {
		numeroMaximoCuotas = i;
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
	public ArrayList getProductos() {
		return productos;
	}

	/**
	 * @return
	 */
	public ArrayList getTalonarios() {
		return talonarios;
	}

	/**
	 * @param list
	 */
	public void setProductos(ArrayList list) {
		productos = list;
	}

	/**
	 * @param list
	 */
	public void setTalonarios(ArrayList list) {
		talonarios = list;
	}

	/**
	 * @return
	 */
	public long getCodigoConcepto() {
		return codigoConcepto;
	}

	/**
	 * @param l
	 */
	public void setCodigoConcepto(long l) {
		codigoConcepto = l;
	}

	/**
	 * @return
	 */
	public Date getFecFin() {
		return fecFin;
	}

	/**
	 * @return
	 */
	public Date getFecInicio() {
		return fecInicio;
	}

	/**
	 * @param date
	 */
	public void setFecFin(Date date) {
		fecFin = date;
	}

	/**
	 * @param date
	 */
	public void setFecInicio(Date date) {
		fecInicio = date;
	}

}
