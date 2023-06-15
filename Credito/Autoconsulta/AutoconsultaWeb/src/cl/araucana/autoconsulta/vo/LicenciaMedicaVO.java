package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author asepulveda
 *
 */
public class LicenciaMedicaVO implements Serializable {
	
	public static final int VISADA=1;
	public static final int NOVISADA_COMPINOCCIDENTE=2;
	public static final int PERTENECE_ISAPRE=4;
	public static final int BLOQUEADA_FONASA=5;
	
	private long numeroLicencia=0;
	private String fechaDesde=null;
	private String fechaHasta=null; // se debe calcular según días de licencia
	private int diasLicencia=0;
	private String codigoEstadoLicencia=null;
	private String fechaDePago=null;
	private int codOficinaPago=0;
	private String oficinaPago=null;
	private String	diasDePago=null;
	private int montoAPagar=0;
	private String codigoFormaDePago=null;
	private int codigoObservacion1=0;
	private int codigoObservacion2=0;
	private int codigoObservacion3=0;
	private String observacion1=null;
	private String observacion2=null;
	private String observacion3=null;
	private Collection listaObservacionesCompin=new ArrayList(); //StringVO
	private int visada=0;
	private String fechaRecepcion = null;
	private String analistaReceptor = null;
	private String obsIsapre = null;	
	private int codigoSubEstadoLicencia = 0;
	
	/** NUEVO CAMPOS REQ-8540 **/
	//Logica
	private String fechaIngreso;
	private String fechaEnvioCompin;
	private String fechaRecepcionCompin;
	//Desde BD
	private String codOfiOrigen;
	private String codRegionOrigen;

	public void setCodRegionOrigen(String codRegionOrigen) {
		this.codRegionOrigen = codRegionOrigen;
	}
	
	public String getCodRegionOrigen() {
		return codRegionOrigen;
	}

	public void setCodOfiOrigen(String codOfiOrigen) {
		this.codOfiOrigen = codOfiOrigen;
	}
	
	public String getCodOfiOrigen() {
		return codOfiOrigen;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFechaEnvioCompin() {
		return fechaEnvioCompin;
	}

	public void setFechaEnvioCompin(String fechaEnvioCompin) {
		this.fechaEnvioCompin = fechaEnvioCompin;
	}

	public String getFechaRecepcionCompin() {
		return fechaRecepcionCompin;
	}

	public void setFechaRecepcionCompin(String fechaRecepcionCompin) {
		this.fechaRecepcionCompin = fechaRecepcionCompin;
	}



	public LicenciaMedicaVO() {
	}
	
	/**
	 * Constructor de LicenciaMedicaVO a partir de un Value Object de LicenciaMedicaDetalleVO
	 * @param LicenciaMedicaDetalleVO
	 */
	public LicenciaMedicaVO(LicenciaMedicaDetalleVO licenciaDetallada) {
		diasDePago = licenciaDetallada.getDiaDePago();
		diasLicencia = licenciaDetallada.getDiasLicencia();
		fechaDePago = licenciaDetallada.getFechaDePago();
		fechaDesde = licenciaDetallada.getFechaDesde();
		fechaHasta = licenciaDetallada.getFechaHasta();
		montoAPagar = licenciaDetallada.getMontoAPagar();
		numeroLicencia = licenciaDetallada.getNumeroLicencia();
	}


	/**
	 * @return
	 */
	public String getCodigoEstadoLicencia() {
		return codigoEstadoLicencia;
	}

	/**
	 * @return
	 */
	public String getCodigoFormaDePago() {
		return codigoFormaDePago;
	}

	/**
	 * @return
	 */
	public int getCodigoObservacion1() {
		return codigoObservacion1;
	}

	/**
	 * @return
	 */
	public int getCodigoObservacion2() {
		return codigoObservacion2;
	}

	/**
	 * @return
	 */
	public int getCodigoObservacion3() {
		return codigoObservacion3;
	}

	/**
	 * @return
	 */
	public int getCodOficinaPago() {
		return codOficinaPago;
	}

	/**
	 * @return
	 */
	public String getDiasDePago() {
		return diasDePago;
	}

	/**
	 * @return
	 */
	public int getDiasLicencia() {
		return diasLicencia;
	}

	/**
	 * @return
	 */
	public String getFechaDePago() {
		return fechaDePago;
	}

	/**
	 * @return
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @return
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @return
	 */
	public int getMontoAPagar() {
		return montoAPagar;
	}

	/**
	 * @return
	 */
	public long getNumeroLicencia() {
		return numeroLicencia;
	}

	/**
	 * @return
	 */
	public String getObservacion1() {
		return observacion1;
	}

	/**
	 * @return
	 */
	public String getObservacion2() {
		return observacion2;
	}

	/**
	 * @return
	 */
	public String getObservacion3() {
		return observacion3;
	}

	/**
	 * @return
	 */
	public String getOficinaPago() {
		return oficinaPago;
	}

	/**
	 * @return
	 */
	public int getVisada() {
		return visada;
	}

	/**
	 * @param string
	 */
	public void setCodigoEstadoLicencia(String string) {
		codigoEstadoLicencia = string;
	}

	/**
	 * @param string
	 */
	public void setCodigoFormaDePago(String string) {
		codigoFormaDePago = string;
	}

	/**
	 * @param i
	 */
	public void setCodigoObservacion1(int i) {
		codigoObservacion1 = i;
	}

	/**
	 * @param i
	 */
	public void setCodigoObservacion2(int i) {
		codigoObservacion2 = i;
	}

	/**
	 * @param i
	 */
	public void setCodigoObservacion3(int i) {
		codigoObservacion3 = i;
	}

	/**
	 * @param i
	 */
	public void setCodOficinaPago(int i) {
		codOficinaPago = i;
	}

	/**
	 * @param string
	 */
	public void setDiasDePago(String string) {
		diasDePago = string;
	}

	/**
	 * @param i
	 */
	public void setDiasLicencia(int i) {
		diasLicencia = i;
	}
	
	
	/**
	 * @param string
	 */
	public void setFechaDePago(String string) {
		fechaDePago = string;
	}

	/**
	 * @param string
	 */
	public void setFechaDesde(String string) {
		fechaDesde = string;
	}

	/**
	 * @param string
	 */
	public void setFechaHasta(String string) {
		fechaHasta = string;
	}


	/**
	 * @param i
	 */
	public void setMontoAPagar(int i) {
		montoAPagar = i;
	}

	/**
	 * @param l
	 */
	public void setNumeroLicencia(long l) {
		numeroLicencia = l;
	}

	/**
	 * @param string
	 */
	public void setObservacion1(String string) {
		observacion1 = string;
	}

	/**
	 * @param string
	 */
	public void setObservacion2(String string) {
		observacion2 = string;
	}

	/**
	 * @param string
	 */
	public void setObservacion3(String string) {
		observacion3 = string;
	}

	/**
	 * @param string
	 */
	public void setOficinaPago(String string) {
		oficinaPago = string;
	}

	/**
	 * @param i
	 */
	public void setVisada(int i) {
		visada = i;
	}

	/**
	 * @return
	 */
	public Collection getListaObservacionesCompin() {
		return listaObservacionesCompin;
	}

	/**
	 * @param collection
	 */
	public void setListaObservacionesCompin(Collection collection) {
		listaObservacionesCompin = collection;
	}

	/**
	 * @return
	 */
	public String getAnalistaReceptor() {
		return analistaReceptor;
	}

	/**
	 * @return
	 */
	public String getFechaRecepcion() {
		return fechaRecepcion;
	}

	/**
	 * @param string
	 */
	public void setAnalistaReceptor(String string) {
		analistaReceptor = string;
	}

	/**
	 * @param string
	 */
	public void setFechaRecepcion(String string) {
		fechaRecepcion = string;
	}

	/**
	 * @return
	 */
	public String getObsIsapre() {
		return obsIsapre;
	}

	/**
	 * @param string
	 */
	public void setObsIsapre(String string) {
		obsIsapre = string;
	}

	/**
	 * @return
	 */
	public int getCodigoSubEstadoLicencia() {
		return codigoSubEstadoLicencia;
	}

	/**
	 * @param i
	 */
	public void setCodigoSubEstadoLicencia(int i) {
		codigoSubEstadoLicencia = i;
	}

}
