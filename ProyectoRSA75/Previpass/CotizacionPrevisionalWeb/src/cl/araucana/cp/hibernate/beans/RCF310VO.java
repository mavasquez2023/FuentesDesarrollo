package cl.araucana.cp.hibernate.beans;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RCF310VO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int numeroCuota;
	private int nomtoGravamenes;
	private int estadoCuota;
	private int folioNomina;
	private int rutDeudor;
	private String dvRutDeudor;
	private int valorCuotaAnticipada;
	private int valorCuota;
	private int valorNoDescontada;
	private int folioCredito;
	private int oficinaProceso;

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
	
	public RCF310VO(){
		super();
	}
	
	public RCF310VO(int numeroCuota, int nomtoGravamenes, int estadoCuota, int folioNomina, int rutDeudor, String dvRutDeudor,
			int valorCuotaAnticipada, int valorCuota, int valorNoDescontada, int folioCredito, int oficinaProceso)
	{
		super();
		this.numeroCuota=numeroCuota;
		this.nomtoGravamenes=nomtoGravamenes;
		this.estadoCuota=estadoCuota;
		this.folioNomina=folioNomina;
		this.rutDeudor=rutDeudor;
		this.dvRutDeudor=dvRutDeudor;
		this.valorCuotaAnticipada=valorCuotaAnticipada;
		this.valorCuota=valorCuota;
		this.valorNoDescontada=valorNoDescontada;
		this.folioCredito=folioCredito;
		this.oficinaProceso=oficinaProceso;
	}

	
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}


	/**
	 * @return el dvRutDeudor
	 */
	public String getDvRutDeudor() {
		return dvRutDeudor;
	}


	/**
	 * @param dvRutDeudor el dvRutDeudor a establecer
	 */
	public void setDvRutDeudor(String dvRutDeudor) {
		this.dvRutDeudor = dvRutDeudor;
	}


	/**
	 * @return el estadoCuota
	 */
	public int getEstadoCuota() {
		return estadoCuota;
	}


	/**
	 * @param estadoCuota el estadoCuota a establecer
	 */
	public void setEstadoCuota(int estadoCuota) {
		this.estadoCuota = estadoCuota;
	}


	/**
	 * @return el folioCredito
	 */
	public int getFolioCredito() {
		return folioCredito;
	}


	/**
	 * @param folioCredito el folioCredito a establecer
	 */
	public void setFolioCredito(int folioCredito) {
		this.folioCredito = folioCredito;
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
	 * @return el nomtoGravamenes
	 */
	public int getNomtoGravamenes() {
		return nomtoGravamenes;
	}


	/**
	 * @param nomtoGravamenes el nomtoGravamenes a establecer
	 */
	public void setNomtoGravamenes(int nomtoGravamenes) {
		this.nomtoGravamenes = nomtoGravamenes;
	}


	/**
	 * @return el numeroCuota
	 */
	public int getNumeroCuota() {
		return numeroCuota;
	}


	/**
	 * @param numeroCuota el numeroCuota a establecer
	 */
	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}


	/**
	 * @return el oficinaProceso
	 */
	public int getOficinaProceso() {
		return oficinaProceso;
	}


	/**
	 * @param oficinaProceso el oficinaProceso a establecer
	 */
	public void setOficinaProceso(int oficinaProceso) {
		this.oficinaProceso = oficinaProceso;
	}


	/**
	 * @return el rutDeudor
	 */
	public int getRutDeudor() {
		return rutDeudor;
	}


	/**
	 * @param rutDeudor el rutDeudor a establecer
	 */
	public void setRutDeudor(int rutDeudor) {
		this.rutDeudor = rutDeudor;
	}


	/**
	 * @return el valorCuota
	 */
	public int getValorCuota() {
		return valorCuota;
	}


	/**
	 * @param valorCuota el valorCuota a establecer
	 */
	public void setValorCuota(int valorCuota) {
		this.valorCuota = valorCuota;
	}


	/**
	 * @return el valorCuotaAnticipada
	 */
	public int getValorCuotaAnticipada() {
		return valorCuotaAnticipada;
	}


	/**
	 * @param valorCuotaAnticipada el valorCuotaAnticipada a establecer
	 */
	public void setValorCuotaAnticipada(int valorCuotaAnticipada) {
		this.valorCuotaAnticipada = valorCuotaAnticipada;
	}


	/**
	 * @return el valorNoDescontada
	 */
	public int getValorNoDescontada() {
		return valorNoDescontada;
	}


	/**
	 * @param valorNoDescontada el valorNoDescontada a establecer
	 */
	public void setValorNoDescontada(int valorNoDescontada) {
		this.valorNoDescontada = valorNoDescontada;
	}


}
