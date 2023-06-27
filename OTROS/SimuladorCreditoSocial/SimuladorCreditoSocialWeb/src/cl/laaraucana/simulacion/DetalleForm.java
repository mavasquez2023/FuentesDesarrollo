package cl.laaraucana.simulacion;

public class DetalleForm implements java.io.Serializable {
	private String NUM_CUOTA;
	private String FECHA_VENCIMIENTO;
	private String MONTO_CUOTA;
	private String MONTO_INTERES;
	private String SEGURO_DESGRAVAMEN;
	private String SEGURO_CESANTIA;
	private String SALDO_CAPITAL;

	public DetalleForm() {
	}

	public String getNUM_CUOTA() {
		return NUM_CUOTA;
	}

	public void setNUM_CUOTA(String nUM_CUOTA) {
		NUM_CUOTA = nUM_CUOTA;
	}

	public String getFECHA_VENCIMIENTO() {
		return FECHA_VENCIMIENTO;
	}

	public void setFECHA_VENCIMIENTO(String fECHA_VENCIMIENTO) {
		FECHA_VENCIMIENTO = fECHA_VENCIMIENTO;
	}

	public String getMONTO_CUOTA() {
		return MONTO_CUOTA;
	}

	public void setMONTO_CUOTA(String mONTO_CUOTA) {
		MONTO_CUOTA = mONTO_CUOTA;
	}

	public String getMONTO_INTERES() {
		return MONTO_INTERES;
	}

	public void setMONTO_INTERES(String mONTO_INTERES) {
		MONTO_INTERES = mONTO_INTERES;
	}

	public String getSEGURO_DESGRAVAMEN() {
		return SEGURO_DESGRAVAMEN;
	}

	public void setSEGURO_DESGRAVAMEN(String sEGURO_DESGRAVAMEN) {
		SEGURO_DESGRAVAMEN = sEGURO_DESGRAVAMEN;
	}

	public String getSEGURO_CESANTIA() {
		return SEGURO_CESANTIA;
	}

	public void setSEGURO_CESANTIA(String sEGURO_CESANTIA) {
		SEGURO_CESANTIA = sEGURO_CESANTIA;
	}

	public String getSALDO_CAPITAL() {
		return SALDO_CAPITAL;
	}

	public void setSALDO_CAPITAL(String sALDO_CAPITAL) {
		SALDO_CAPITAL = sALDO_CAPITAL;
	}
}
