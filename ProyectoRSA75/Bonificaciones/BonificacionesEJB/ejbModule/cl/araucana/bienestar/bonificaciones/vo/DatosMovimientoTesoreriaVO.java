package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author asepulveda
 *
 */
public class DatosMovimientoTesoreriaVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2237088109039466426L;
	private String rut=null;
	private String digito=null;
	private String nombre=null;
	private String apePat=null;
	private String apeMat=null;
	private String tipoPago="";
	private long areaNegocio=0;
	private String observacionMovCaja=null;
	private ArrayList listaCasos=new ArrayList(); //CasoVO
	private double monto=0;
	private double montoTotalProfesionales=0;
	String tipoMovimiento = null;
	private ArrayList listaProfesionales=new ArrayList(); //DatosProfesionalesVO
	private Collection detalles = new ArrayList(); //DetalleMovimientoPreCasoVO 
	
	
	/**
	 * Retorna el rut con el dv incluido
	 * @return String con el rut y dv
	 */
	public String getFullRut() {
		return rut+"-"+digito;
	}
	
	/**
	 * Retorna el nombre completo
	 * @return String con el nombre completo
	 */
	public String getFullNombre() {
		String result=nombre;
		
		if(apePat!=null)
			result=result+" "+apePat;
		
		if(apeMat!=null)	
			result=result+" "+apeMat;
			
		return result;
	}	

	/**
	 * @return
	 */
	public String getApeMat() {
		return apeMat;
	}

	/**
	 * @return
	 */
	public String getApePat() {
		return apePat;
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
	public String getNombre() {
		return nombre;
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
	public void setApeMat(String string) {
		apeMat = string;
	}

	/**
	 * @param string
	 */
	public void setApePat(String string) {
		apePat = string;
	}

	/**
	 * @param string
	 */
	public void setDigito(String string) {
		digito = string;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
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
	public String getTipoPago() {
		return tipoPago;
	}

	/**
	 * @param string
	 */
	public void setTipoPago(String string) {
		tipoPago = string;
	}

	/**
	 * @return
	 */
	public ArrayList getListaCasos() {
		return listaCasos;
	}

	/**
	 * @param list
	 */
	public void setListaCasos(ArrayList list) {
		listaCasos = list;
	}

	/**
	 * @return
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * @param d
	 */
	public void setMonto(double d) {
		monto = d;
	}

	/**
	 * @return
	 */
	public ArrayList getListaProfesionales() {
		return listaProfesionales;
	}

	/**
	 * @param list
	 */
	public void setListaProfesionales(ArrayList list) {
		listaProfesionales = list;
	}

	/**
	 * @return
	 */
	public double getMontoTotalProfesionales() {
		return montoTotalProfesionales;
	}

	/**
	 * @param d
	 */
	public void setMontoTotalProfesionales(double d) {
		montoTotalProfesionales = d;
	}

	/**
	 * @return
	 */
	public long getAreaNegocio() {
		return areaNegocio;
	}

	/**
	 * @param l
	 */
	public void setAreaNegocio(long l) {
		areaNegocio = l;
	}

	/**
	 * @return
	 */
	public String getObservacionMovCaja() {
		return observacionMovCaja;
	}

	/**
	 * @param string
	 */
	public void setObservacionMovCaja(String string) {
		observacionMovCaja = string;
	}

	/**
	 * @return
	 */
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	/**
	 * @param string
	 */
	public void setTipoMovimiento(String string) {
		tipoMovimiento = string;
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
