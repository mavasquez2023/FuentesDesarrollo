package cl.laaraucana.simulacion.VO;

public class ParametrosSimulacionVO {

	private String tipoAfiliado = "";
	private String oficina = "";
	private String oficinaDesc = "";
	private String monto = "";
	private String cuotas = "";
	private String rutAfiliado = "";
	private String nombreAfiliado = "";
	private String rutEmpleador = "";
	private String sapEmpleador = "";
	private String seguroCesantia = "";
	private boolean seguroDesgravamen = true;
	private String tasaMensual = "";
	
	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getCuotas() {
		return cuotas;
	}

	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
	}

	public String getRutAfiliado() {
		return rutAfiliado;
	}

	public void setRutAfiliado(String rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}

	public String getOficinaDesc() {
		return oficinaDesc;
	}

	public void setOficinaDesc(String oficinaDesc) {
		this.oficinaDesc = oficinaDesc;
	}

	public boolean isSeguroDesgravamen() {
		return seguroDesgravamen;
	}

	public void setSeguroDesgravamen(boolean seguroDesgravamen) {
		this.seguroDesgravamen = seguroDesgravamen;
	}

	public String getRutEmpleador() {
		return rutEmpleador;
	}

	public void setRutEmpleador(String rutEmpleador) {
		this.rutEmpleador = rutEmpleador;
	}

	public String getSapEmpleador() {
		return sapEmpleador;
	}

	public void setSapEmpleador(String sapEmpleador) {
		this.sapEmpleador = sapEmpleador;
	}

	public String getSeguroCesantia() {
		return seguroCesantia;
	}

	public void setSeguroCesantia(String seguroCesantia) {
		this.seguroCesantia = seguroCesantia;
	}

	public String getNombreAfiliado() {
		return nombreAfiliado;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

	public String getTasaMensual() {
		return tasaMensual;
	}

	public void setTasaMensual(String tasaMensual) {
		this.tasaMensual = tasaMensual;
	}
	

}
