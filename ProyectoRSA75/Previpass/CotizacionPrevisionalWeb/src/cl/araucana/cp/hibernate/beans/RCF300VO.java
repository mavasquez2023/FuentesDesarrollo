package cl.araucana.cp.hibernate.beans;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RCF300VO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int codigoNomina;
	private int rutEmpresa;
	private String dvRutEmpresa;
	private int folioNomina;
	private int numeroCaratula;
	private int montoAnticipado;
	private int montoCheque;
	private int montoEfectivo;
	private int montoNoDescontado;
	private int montoPagado;
	private int errorNomina;
	private int estadoNomina;
	private String fechaVencimiento;
	private int montoNomina;
	private int estadoProceso;
	private int tipoNomina;
	private int codigooficina;
	private Set detalleCuotas;
	/*
	 * Por medio de la TRX enviada al sistema de origen por SPL, se debera
	 * consultar la tabla PAGO, con el contenido del campo ID_Convenio, se debe
	 * accesar la tabla CONVENIO, en donde encontrara la cuenta corriente
	 * asociada (campo CTACTE), para obtener el Banco asociado al convenio
	 * segun la codificacion del sistema de tesoreria, se debera accesar a
	 * la tabla MEDIOPAGO con el contenido del campo id_mediopago de la tabla
	 * CONVENIO.
	 * 
	 * En la tabla MEDIOPAGO, el contenido del campo CODBANCO, correspondera al
	 * valor del banco para el sistema de tesoreria.
	 */

	public RCF300VO()
	{
		super();

	}
	
	public RCF300VO( int codigoNomina, int rutEmpresa, String dvRutEmpresa, int folioNomina, int numeroCaratula, int montoAnticipado , 
			int montoCheque, int montoEfectivo, int montoNoDescontado, int montoPagado, int errorNomina, int estadoNomina, String fechaVencimiento, 
			int montoNomina, int estadoProceso, int tipoNomina, int codigooficina, Set detalleCuotas)
	{
		super();
		this.codigoNomina=codigoNomina;
		this.rutEmpresa=rutEmpresa;
		this.dvRutEmpresa=dvRutEmpresa;
		this.folioNomina=folioNomina;
		this.numeroCaratula=numeroCaratula;
		this.montoAnticipado=montoAnticipado;
		this.montoCheque=montoCheque;
		this.montoEfectivo=montoEfectivo;
		this.montoNoDescontado=montoNoDescontado;
		this.montoPagado=montoPagado;
		this.errorNomina=errorNomina;
		this.estadoNomina=estadoNomina;
		this.fechaVencimiento=fechaVencimiento;
		this.montoNomina=montoNomina;
		this.estadoProceso=estadoProceso;
		this.tipoNomina=tipoNomina;
		this.codigooficina=codigooficina;
		this.detalleCuotas= detalleCuotas;
	}
	
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}


	/**
	 * @return el codigoNomina
	 */
	public int getCodigoNomina() {
		return codigoNomina;
	}


	/**
	 * @param codigoNomina el codigoNomina a establecer
	 */
	public void setCodigoNomina(int codigoNomina) {
		this.codigoNomina = codigoNomina;
	}


	/**
	 * @return el codigooficina
	 */
	public int getCodigooficina() {
		return codigooficina;
	}


	/**
	 * @param codigooficina el codigooficina a establecer
	 */
	public void setCodigooficina(int codigooficina) {
		this.codigooficina = codigooficina;
	}


	/**
	 * @return el dvRutEmpresa
	 */
	public String getDvRutEmpresa() {
		return dvRutEmpresa;
	}


	/**
	 * @param dvRutEmpresa el dvRutEmpresa a establecer
	 */
	public void setDvRutEmpresa(String dvRutEmpresa) {
		this.dvRutEmpresa = dvRutEmpresa;
	}


	/**
	 * @return el errorNomina
	 */
	public int getErrorNomina() {
		return errorNomina;
	}


	/**
	 * @param errorNomina el errorNomina a establecer
	 */
	public void setErrorNomina(int errorNomina) {
		this.errorNomina = errorNomina;
	}


	/**
	 * @return el estadoNomina
	 */
	public int getEstadoNomina() {
		return estadoNomina;
	}


	/**
	 * @param estadoNomina el estadoNomina a establecer
	 */
	public void setEstadoNomina(int estadoNomina) {
		this.estadoNomina = estadoNomina;
	}


	/**
	 * @return el estadoProceso
	 */
	public int getEstadoProceso() {
		return estadoProceso;
	}


	/**
	 * @param estadoProceso el estadoProceso a establecer
	 */
	public void setEstadoProceso(int estadoProceso) {
		this.estadoProceso = estadoProceso;
	}


	/**
	 * @return el fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}


	/**
	 * @param fechaVencimiento el fechaVencimiento a establecer
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	/**
	 * @return el folioNomina
	 */
	public int getFolioNomina() {
		return folioNomina;
	}


	/**
	 * @param folioNomina el folioNomina a establecer
	 */
	public void setFolioNomina(int folioNomina) {
		this.folioNomina = folioNomina;
	}


	/**
	 * @return el montoAnticipado
	 */
	public int getMontoAnticipado() {
		return montoAnticipado;
	}


	/**
	 * @param montoAnticipado el montoAnticipado a establecer
	 */
	public void setMontoAnticipado(int montoAnticipado) {
		this.montoAnticipado = montoAnticipado;
	}


	/**
	 * @return el montoCheque
	 */
	public int getMontoCheque() {
		return montoCheque;
	}


	/**
	 * @param montoCheque el montoCheque a establecer
	 */
	public void setMontoCheque(int montoCheque) {
		this.montoCheque = montoCheque;
	}


	/**
	 * @return el montoEfectivo
	 */
	public int getMontoEfectivo() {
		return montoEfectivo;
	}


	/**
	 * @param montoEfectivo el montoEfectivo a establecer
	 */
	public void setMontoEfectivo(int montoEfectivo) {
		this.montoEfectivo = montoEfectivo;
	}


	/**
	 * @return el montoNoDescontado
	 */
	public int getMontoNoDescontado() {
		return montoNoDescontado;
	}


	/**
	 * @param montoNoDescontado el montoNoDescontado a establecer
	 */
	public void setMontoNoDescontado(int montoNoDescontado) {
		this.montoNoDescontado = montoNoDescontado;
	}


	/**
	 * @return el montoNomina
	 */
	public int getMontoNomina() {
		return montoNomina;
	}


	/**
	 * @param montoNomina el montoNomina a establecer
	 */
	public void setMontoNomina(int montoNomina) {
		this.montoNomina = montoNomina;
	}


	/**
	 * @return el montoPagado
	 */
	public int getMontoPagado() {
		return montoPagado;
	}


	/**
	 * @param montoPagado el montoPagado a establecer
	 */
	public void setMontoPagado(int montoPagado) {
		this.montoPagado = montoPagado;
	}


	/**
	 * @return el numeroCaratula
	 */
	public int getNumeroCaratula() {
		return numeroCaratula;
	}


	/**
	 * @param numeroCaratula el numeroCaratula a establecer
	 */
	public void setNumeroCaratula(int numeroCaratula) {
		this.numeroCaratula = numeroCaratula;
	}


	/**
	 * @return el rutEmpresa
	 */
	public int getRutEmpresa() {
		return rutEmpresa;
	}


	/**
	 * @param rutEmpresa el rutEmpresa a establecer
	 */
	public void setRutEmpresa(int rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}


	/**
	 * @return el tipoNomina
	 */
	public int getTipoNomina() {
		return tipoNomina;
	}


	/**
	 * @param tipoNomina el tipoNomina a establecer
	 */
	public void setTipoNomina(int tipoNomina) {
		this.tipoNomina = tipoNomina;
	}

	/**
	 * @return el detalleCuotas
	 */
	public Set getDetalleCuotas() {
		return detalleCuotas;
	}

	/**
	 * @param detalleCuotas el detalleCuotas a establecer
	 */
	public void setDetalleCuotas(Set detalleCuotas) {
		this.detalleCuotas = detalleCuotas;
	}
}
