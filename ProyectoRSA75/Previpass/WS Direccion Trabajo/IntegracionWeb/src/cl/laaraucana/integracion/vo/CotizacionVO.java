package cl.laaraucana.integracion.vo;

public class CotizacionVO {
	
	
	String rutEmpresa = null;
	String rutTrabajador = null;
	String nombreTrabajador = null;
	String periodo=null;
	String tipoPago=null;
	String rentaImponible=null;
	String montoPagado=null;
	String folio=null;
	String codMovPersonal=null;
	String fechaDesde=null;
	String fechaHasta=null;
	String diasTrabajados=null;
	
	
	public String getCodMovPersonal() {
		return codMovPersonal;
	}
	public void setCodMovPersonal(String codMovPersonal) {
		this.codMovPersonal = codMovPersonal;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getMontoPagado() {
		return montoPagado;
	}
	public void setMontoPagado(String montoPagado) {
		this.montoPagado = montoPagado;
	}
	public String getNombreTrabajador() {
		return nombreTrabajador;
	}
	public void setNombreTrabajador(String nombreTrabajador) {
		this.nombreTrabajador = nombreTrabajador;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getRentaImponible() {
		return rentaImponible;
	}
	public void setRentaImponible(String rentaImponible) {
		this.rentaImponible = rentaImponible;
	}
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	public String getRutTrabajador() {
		return rutTrabajador;
	}
	public void setRutTrabajador(String rutTrabajador) {
		this.rutTrabajador = rutTrabajador;
	}
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	
	public String getDiasTrabajados() {
		return diasTrabajados;
	}
	public void setDiasTrabajados(String diasTrabajados) {
		this.diasTrabajados = diasTrabajados;
	}
	public String toString() {
		
		return "rutEmpresa:"+  rutEmpresa +
		", rutTrabajador:"+  rutTrabajador +
		", nombreTrabajador:"+  nombreTrabajador +
		", periodo:"+  periodo +
		", tipoPago:"+  tipoPago + 
		", rentaImponible:"+  rentaImponible +
		", montoPagado:"+  montoPagado +
		", folio:"+  folio +
		", codMovPersonal:"+  codMovPersonal +
		", fechaDesde:"+  fechaDesde +
		", fechaHasta:"+  fechaHasta +
		", diasTrabajados:"+  diasTrabajados ;
		
		
	}
	
	
	
	
	
	
	

}
