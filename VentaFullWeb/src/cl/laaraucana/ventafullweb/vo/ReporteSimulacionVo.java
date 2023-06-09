package cl.laaraucana.ventafullweb.vo;

import java.io.Serializable;
import java.util.List;

public class ReporteSimulacionVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String fechaHora;
	private String rut;
	private String sucursal;
	private String tipoAfiliado;
	private String monto;
	private String numCuotas;
	private String seguroCesantiaAnual;
	private String seguroCesantiaMensual;
	private String seguroDesgravamenAnual;
	private String seguroDesgravamenMensual;
	private String impuesto;
	private String gastoNotarial;
	private String valorCuota;
	private String valorCuotaSinSeguro;
	private String tasaInteresMensual;
	private String tasaInteresAnual;
	private String pagoPrimeraCuota;
	private String costoTotalCredito;
	private String cae;
	private String fecha;
	private String unidMonetaria;
	private String incluyeSC;
	private String incluyeSD;
	private List<DetalleCuotasVo> detalleCuotas;
	
	
	public List<DetalleCuotasVo> getDetalleCuotas() {
		return detalleCuotas;
	}
	public void setDetalleCuotas(List<DetalleCuotasVo> detalleCuotas) {
		this.detalleCuotas = detalleCuotas;
	}
	public String getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getTipoAfiliado() {
		return tipoAfiliado;
	}
	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getNumCuotas() {
		return numCuotas;
	}
	public void setNumCuotas(String numCuotas) {
		this.numCuotas = numCuotas;
	}
	public String getSeguroCesantiaAnual() {
		return seguroCesantiaAnual;
	}
	public void setSeguroCesantiaAnual(String seguroCesantiaAnual) {
		this.seguroCesantiaAnual = seguroCesantiaAnual;
	}
	public String getSeguroCesantiaMensual() {
		return seguroCesantiaMensual;
	}
	public void setSeguroCesantiaMensual(String seguroCesantiaMensual) {
		this.seguroCesantiaMensual = seguroCesantiaMensual;
	}
	public String getSeguroDesgravamenAnual() {
		return seguroDesgravamenAnual;
	}
	public void setSeguroDesgravamenAnual(String seguroDesgravamenAnual) {
		this.seguroDesgravamenAnual = seguroDesgravamenAnual;
	}
	public String getSeguroDesgravamenMensual() {
		return seguroDesgravamenMensual;
	}
	public void setSeguroDesgravamenMensual(String seguroDesgravamenMensual) {
		this.seguroDesgravamenMensual = seguroDesgravamenMensual;
	}
	public String getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}
	public String getGastoNotarial() {
		return gastoNotarial;
	}
	public void setGastoNotarial(String gastoNotarial) {
		this.gastoNotarial = gastoNotarial;
	}
	public String getValorCuota() {
		return valorCuota;
	}
	public void setValorCuota(String valorCuota) {
		this.valorCuota = valorCuota;
	}
	
	/**
	 * @return the valorCuotaSinSeguro
	 */
	public String getValorCuotaSinSeguro() {
		return valorCuotaSinSeguro;
	}
	/**
	 * @param valorCuotaSinSeguro the valorCuotaSinSeguro to set
	 */
	public void setValorCuotaSinSeguro(String valorCuotaSinSeguro) {
		this.valorCuotaSinSeguro = valorCuotaSinSeguro;
	}
	public String getTasaInteresMensual() {
		return tasaInteresMensual;
	}
	public void setTasaInteresMensual(String tasaInteresMensual) {
		this.tasaInteresMensual = tasaInteresMensual;
	}
	
	/**
	 * @return the tasaInteresAnual
	 */
	public String getTasaInteresAnual() {
		return tasaInteresAnual;
	}
	/**
	 * @param tasaInteresAnual the tasaInteresAnual to set
	 */
	public void setTasaInteresAnual(String tasaInteresAnual) {
		this.tasaInteresAnual = tasaInteresAnual;
	}
	public String getPagoPrimeraCuota() {
		return pagoPrimeraCuota;
	}
	public void setPagoPrimeraCuota(String pagoPrimeraCuota) {
		this.pagoPrimeraCuota = pagoPrimeraCuota;
	}
	public String getCostoTotalCredito() {
		return costoTotalCredito;
	}
	public void setCostoTotalCredito(String costoTotalCredito) {
		this.costoTotalCredito = costoTotalCredito;
	}
	public String getCae() {
		return cae;
	}
	public void setCae(String cae) {
		this.cae = cae;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getUnidMonetaria() {
		return unidMonetaria;
	}
	public void setUnidMonetaria(String unidMonetaria) {
		this.unidMonetaria = unidMonetaria;
	}
	public String getIncluyeSC() {
		return incluyeSC;
	}
	public void setIncluyeSC(String incluyeSC) {
		this.incluyeSC = incluyeSC;
	}
	public String getIncluyeSD() {
		return incluyeSD;
	}
	public void setIncluyeSD(String incluyeSD) {
		this.incluyeSD = incluyeSD;
	}
	
	
}
