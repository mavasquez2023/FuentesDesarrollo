package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

import cl.araucana.prestamo.vo.CuotaVO;

/**
 * @author asepulveda
 *
 */
public class CuotaPrestamoVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private long codigoDescuento=0;
	private String codigoOficina=null;
	private String rut=null;
	private String dv=null;
	private int tipoPrestamo=0;
	private int numeroCuotasTotales=0;
	private int cuotaActual=0;
	private int monto=0;
	private Date fecha=null;
	private long saldoTotal = 0;
	private String tipoFinanciamiento;
	private String cuentaContable;	

	public CuotaPrestamoVO () {
	}

	/**
	 * Devuelve la descripcion del tipo de prestamo
	 * @return
	 */
	public String getDescripcionTipoPrestamo() {
		switch (tipoPrestamo) {
			case 1:
				return "Habitacional";
			case 2:
				return "Consumo";
			case 3:
				return "Emergencia";
			case 4:
				return "Médico";
			case 5:
				return "Prestamos de Auxilio F.P.";
			case 6:
				return "Educacional";
			default:
				return null;
		}
		
	}
	

	/**
	 * Constructor de una Cuota a partir de un Value Object de Prestamo
	 * @param cuo: CuotaVO de Prestamo 
	 */
	public CuotaPrestamoVO (CuotaVO cuo) {
		rut=cuo.getRut();
		tipoPrestamo=cuo.getTipoPrestamo();
		numeroCuotasTotales=cuo.getNumeroCuotasTotales();
		cuotaActual=cuo.getCuotaActual();
		monto=cuo.getMonto();
		fecha=cuo.getFecha();
		tipoFinanciamiento=cuo.getTipoFinanciamiento();
		cuentaContable=cuo.getCuentaContable();
	}

	
	/**
	 * Retorna el rut compuesto del Socio
	 * @return String con el rut
	 */
	public String getFullRut() {
		return rut + "-" + dv;
	}


	/**
	 * @return
	 */
	public int getCuotaActual() {
		return cuotaActual;
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
	public int getMonto() {
		return monto;
	}

	/**
	 * @return
	 */
	public int getNumeroCuotasTotales() {
		return numeroCuotasTotales;
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
	public int getTipoPrestamo() {
		return tipoPrestamo;
	}

	/**
	 * @param i
	 */
	public void setCuotaActual(int i) {
		cuotaActual = i;
	}

	/**
	 * @param date
	 */
	public void setFecha(Date date) {
		fecha = date;
	}

	/**
	 * @param i
	 */
	public void setMonto(int i) {
		monto = i;
	}

	/**
	 * @param i
	 */
	public void setNumeroCuotasTotales(int i) {
		numeroCuotasTotales = i;
	}

	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

	/**
	 * @param i
	 */
	public void setTipoPrestamo(int i) {
		tipoPrestamo = i;
	}

	/**
	 * @return
	 */
	public String getDv() {
		return dv;
	}


	/**
	 * @param string
	 */
	public void setDv(String string) {
		dv = string;
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
	public String getCodigoOficina() {
		return codigoOficina;
	}

	/**
	 * @param string
	 */
	public void setCodigoOficina(String string) {
		codigoOficina = string;
	}

	/**
	 * @return
	 */
	public long getSaldoTotal() {
		return saldoTotal;
	}

	/**
	 * @param l
	 */
	public void setSaldoTotal(long l) {
		saldoTotal = l;
	}

	public String getCuentaContable() {
		return cuentaContable;
	}

	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	public String getTipoFinanciamiento() {
		return tipoFinanciamiento;
	}

	public void setTipoFinanciamiento(String tipoFinanciamiento) {
		this.tipoFinanciamiento = tipoFinanciamiento;
	}

}
