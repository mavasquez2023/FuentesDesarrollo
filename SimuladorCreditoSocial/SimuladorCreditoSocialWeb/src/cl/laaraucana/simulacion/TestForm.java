package cl.laaraucana.simulacion;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class TestForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String nombreCompleto;
	private String fecha;
	private String cuotas;
	private String monto;
	private String sucursal;
	private String tipoAfiliado;
	private String seguroCesantia;
	private String seguroDesgravamen;
	private String rut;
	private String MONTO_CUOTA;
	private String TASA_INT_MENSUAL;
	private String TASA_INT_ANUAL;
	private String CAE;
	private String IMPUESTO;
	private String GASTO_NOTARIAL;
	private String CTC;
	private String COSTO_MENSUAL_DESGRAVAMEN;
	private String COSTO_TOTAL_DESGRAVAMEN;
	private String COSTO_MENSUAL_CESANTIA;
	private String COSTOS_TOTAL_CESANTIA;
	private String FECHA_PRIMER_VENCIMIENTO;
	private ArrayList detalleCuotas;
	private String etapa;
	private String afiliadoActivo;

		
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCuotas() {
		return cuotas;
	}

	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
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

	public String getSeguroCesantia() {
		return seguroCesantia;
	}

	public void setSeguroCesantia(String seguroCesantia) {
		this.seguroCesantia = seguroCesantia;
	}

	public String getSeguroDesgravamen() {
		return seguroDesgravamen;
	}

	public void setSeguroDesgravamen(String seguroDesgravamen) {
		this.seguroDesgravamen = seguroDesgravamen;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getMONTO_CUOTA() {
		return MONTO_CUOTA;
	}

	public void setMONTO_CUOTA(String mONTO_CUOTA) {
		MONTO_CUOTA = mONTO_CUOTA;
	}

	public String getTASA_INT_MENSUAL() {
		return TASA_INT_MENSUAL;
	}

	public void setTASA_INT_MENSUAL(String tASA_INT_MENSUAL) {
		TASA_INT_MENSUAL = tASA_INT_MENSUAL;
	}

	public String getTASA_INT_ANUAL() {
		return TASA_INT_ANUAL;
	}

	public void setTASA_INT_ANUAL(String tASA_INT_ANUAL) {
		TASA_INT_ANUAL = tASA_INT_ANUAL;
	}

	public String getCAE() {
		return CAE;
	}

	public void setCAE(String cAE) {
		CAE = cAE;
	}

	public String getIMPUESTO() {
		return IMPUESTO;
	}

	public void setIMPUESTO(String iMPUESTO) {
		IMPUESTO = iMPUESTO;
	}

	public String getGASTO_NOTARIAL() {
		return GASTO_NOTARIAL;
	}

	public void setGASTO_NOTARIAL(String gASTO_NOTARIAL) {
		GASTO_NOTARIAL = gASTO_NOTARIAL;
	}

	public String getCTC() {
		return CTC;
	}

	public void setCTC(String cTC) {
		CTC = cTC;
	}

	public String getCOSTO_MENSUAL_DESGRAVAMEN() {
		return COSTO_MENSUAL_DESGRAVAMEN;
	}

	public void setCOSTO_MENSUAL_DESGRAVAMEN(String cOSTO_MENSUAL_DESGRAVAMEN) {
		COSTO_MENSUAL_DESGRAVAMEN = cOSTO_MENSUAL_DESGRAVAMEN;
	}

	public String getCOSTO_TOTAL_DESGRAVAMEN() {
		return COSTO_TOTAL_DESGRAVAMEN;
	}

	public void setCOSTO_TOTAL_DESGRAVAMEN(String cOSTO_TOTAL_DESGRAVAMEN) {
		COSTO_TOTAL_DESGRAVAMEN = cOSTO_TOTAL_DESGRAVAMEN;
	}

	public String getCOSTO_MENSUAL_CESANTIA() {
		return COSTO_MENSUAL_CESANTIA;
	}

	public void setCOSTO_MENSUAL_CESANTIA(String cOSTO_MENSUAL_CESANTIA) {
		COSTO_MENSUAL_CESANTIA = cOSTO_MENSUAL_CESANTIA;
	}

	public String getCOSTOS_TOTAL_CESANTIA() {
		return COSTOS_TOTAL_CESANTIA;
	}

	public void setCOSTOS_TOTAL_CESANTIA(String cOSTOS_TOTAL_CESANTIA) {
		COSTOS_TOTAL_CESANTIA = cOSTOS_TOTAL_CESANTIA;
	}

	public String getFECHA_PRIMER_VENCIMIENTO() {
		return FECHA_PRIMER_VENCIMIENTO;
	}

	public void setFECHA_PRIMER_VENCIMIENTO(String fECHA_PRIMER_VENCIMIENTO) {
		FECHA_PRIMER_VENCIMIENTO = fECHA_PRIMER_VENCIMIENTO;
	}

	public ArrayList getDetalleCuotas() {
		return detalleCuotas;
	}

	public void setDetalleCuotas(ArrayList detalleCuotas) {
		this.detalleCuotas = detalleCuotas;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public String getAfiliadoActivo() {
		return afiliadoActivo;
	}

	public void setAfiliadoActivo(String afiliadoActivo) {
		this.afiliadoActivo = afiliadoActivo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
