package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author asepulveda
 *
 */
public class CasosDescontadosVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private long casoId=0;
	private String rut=null;
	private int montoDescuento=0;	
	private int numeroCuotas=0;
	private int cuotaActual=0;
	private long codigoConvenio=0;
	private String convenio=null;
	private int aporteConvenio=0;
	private ArrayList detalle = new ArrayList(); //DetalleCasoDescontadoVO
	
	public CasosDescontadosVO() {	
	}
	
	/**
	 * Constructor de un CasosDescontadosVO a partir de un CasosDescontadosSinFormatoVO
	 * @param CasosDescontadosSinFormatoVO casoSinFormato
	 * @return CasosDescontadosVO
	 */
	public CasosDescontadosVO(CasosDescontadosSinFormatoVO casoSinFormato) {
		casoId=casoSinFormato.getCasoId();
		rut=casoSinFormato.getRut();
		montoDescuento=casoSinFormato.getMontoDescuento();	
		numeroCuotas=casoSinFormato.getNumeroCuotas();
		cuotaActual=casoSinFormato.getCuotaActual();
		codigoConvenio=casoSinFormato.getCodigoConvenio();
		convenio=casoSinFormato.getConvenio();
		aporteConvenio=casoSinFormato.getAporteConvenio();
	}
	
	
	/**
	 * Retorna la cuota en el formato cuotaActual/CuotasTotales
	 * @return String
	 */
	public String getFullCuota() {
		if(numeroCuotas>0)
			return cuotaActual+"/"+numeroCuotas;
		else
			return "";
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
	public long getCodigoConvenio() {
		return codigoConvenio;
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
	public int getCuotaActual() {
		return cuotaActual;
	}

	/**
	 * @return
	 */
	public int getMontoDescuento() {
		return montoDescuento;
	}

	/**
	 * @return
	 */
	public int getNumeroCuotas() {
		return numeroCuotas;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param l
	 */
	public void setCasoId(long l) {
		casoId = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoConvenio(long l) {
		codigoConvenio = l;
	}

	/**
	 * @param string
	 */
	public void setConvenio(String string) {
		convenio = string;
	}

	/**
	 * @param i
	 */
	public void setCuotaActual(int i) {
		cuotaActual = i;
	}

	/**
	 * @param i
	 */
	public void setMontoDescuento(int i) {
		montoDescuento = i;
	}

	/**
	 * @param i
	 */
	public void setNumeroCuotas(int i) {
		numeroCuotas = i;
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
	public int getAporteConvenio() {
		return aporteConvenio;
	}

	/**
	 * @param i
	 */
	public void setAporteConvenio(int i) {
		aporteConvenio = i;
	}

	/**
	 * @return
	 */
	public ArrayList getDetalle() {
		return detalle;
	}

	/**
	 * @param list
	 */
	public void setDetalle(ArrayList list) {
		detalle = list;
	}

}
