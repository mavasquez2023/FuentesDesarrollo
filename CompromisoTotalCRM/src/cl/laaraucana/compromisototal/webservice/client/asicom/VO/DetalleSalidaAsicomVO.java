package cl.laaraucana.compromisototal.webservice.client.asicom.VO;

import java.util.Date;

import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class DetalleSalidaAsicomVO extends AbstractSalidaVO {

	private String numero_cuota;
	private Date fecha_vencimiento;
	private String monto_capital_amortizado;
	private String monto_seguros;
	private String monto_interes;
	private String cuota_morosa;
	public DetalleSalidaAsicomVO(String numero_cuota, Date fecha_vencimiento, String monto_capital_amortizado, String monto_seguros, String monto_interes, String cuota_morosa) {
		super();
		this.numero_cuota = numero_cuota;
		this.fecha_vencimiento = fecha_vencimiento;
		this.monto_capital_amortizado = monto_capital_amortizado;
		this.monto_seguros = monto_seguros;
		this.monto_interes = monto_interes;
		this.cuota_morosa = cuota_morosa;
	}
	/**
	 * @return el numero_cuota
	 */
	public String getNumero_cuota() {
		return numero_cuota;
	}
	/**
	 * @param numero_cuota el numero_cuota a establecer
	 */
	public void setNumero_cuota(String numero_cuota) {
		this.numero_cuota = numero_cuota;
	}
	/**
	 * @return el fecha_vencimiento
	 */
	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	/**
	 * @param fecha_vencimiento el fecha_vencimiento a establecer
	 */
	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	/**
	 * @return el monto_capital_amortizado
	 */
	public String getMonto_capital_amortizado() {
		return monto_capital_amortizado;
	}
	/**
	 * @param monto_capital_amortizado el monto_capital_amortizado a establecer
	 */
	public void setMonto_capital_amortizado(String monto_capital_amortizado) {
		this.monto_capital_amortizado = monto_capital_amortizado;
	}
	/**
	 * @return el monto_seguros
	 */
	public String getMonto_seguros() {
		return monto_seguros;
	}
	/**
	 * @param monto_seguros el monto_seguros a establecer
	 */
	public void setMonto_seguros(String monto_seguros) {
		this.monto_seguros = monto_seguros;
	}
	/**
	 * @return el monto_interes
	 */
	public String getMonto_interes() {
		return monto_interes;
	}
	/**
	 * @param monto_interes el monto_interes a establecer
	 */
	public void setMonto_interes(String monto_interes) {
		this.monto_interes = monto_interes;
	}
	/**
	 * @return el cuota_morosa
	 */
	public String getCuota_morosa() {
		return cuota_morosa;
	}
	/**
	 * @param cuota_morosa el cuota_morosa a establecer
	 */
	public void setCuota_morosa(String cuota_morosa) {
		this.cuota_morosa = cuota_morosa;
	}

	
}
