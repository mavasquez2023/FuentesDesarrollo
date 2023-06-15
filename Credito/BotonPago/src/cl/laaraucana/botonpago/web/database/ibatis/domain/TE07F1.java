package cl.laaraucana.botonpago.web.database.ibatis.domain;

public class TE07F1 {

	private String cmba; // Codigo de Oficina
	private String obf002; // Creation date
	private String obf003; // Creation time
	private String obf005; // Last change date
	private String obf006; // Last change time
	private String sajkm92; // Last change user
	private String sajkm94; // Creation user
	private String te10a; // Sesion
	private String te1ba; // Codigo Cajero
	private String te1ca; // Fecha Apertura
	private String te1sa; // Hora de Emision
	private String te1ta; // Hora Recaudacion Pago por Caja
	private String te3wa; // Folio Movimiento
	private String te3xa; // Tipo Movimiento
	private String te3ya; // Estado Movimiento Caja
	private String te3za; // Fecha de Emision
	private String te40a; // Fecha Recaudacion Pago por Caja
	private String te41a; // Forma de Pago
	private String te42a; // RUT 1
	private String te43a; // DV RUT 1
	private String te44a; // Identificacion 1
	private String te45a; // RUT 2
	private String te46a; // DV RUT 2
	private String te47a; // Identificacion 2
	private String te49a; // Observacion Mov Caja
	private String te4aa; // Sucursal de Empresa
	private String te4ba; // Estado de Autorizacion
	private String te4ca; // Tipo de Pago (Efectivo o Cheque)
	private String te4da; // Fecha Disponibilidad Egreso
	private String te4ea; // Emite Factura
	private String te4fa; // Ser Pagado por | Codigo de Oficina
	private String te4va; // Monto Interes
	private String te4xa; // Monto Reajuste
	private String te7ma; // Monto Informado
	private String te7na; // Monto Emitido
	private String te9ca; // Correlativo Pago
	private String tea7a; // Codigo de Barra
	private String teqa; // Codigo Area de Negocio
	private String operacion;
	private String estadoMovimientoValidacion;

	public TE07F1() {
		super();
	}

	//constructor insert
	public TE07F1(String folioMovimiento, String tipoMovimiento, String estadoMovimientoCaja, String formadePago, String rUT1, String dVRUT1, String identificacion1, String rUT2, String dVRUT2,
			String codigodeBarra, String identificacion2, String montoInformado, String montoInteres, String montoReajuste, String montoEmitido, String observacionMovCaja, String sucursaldeEmpresa,
			String estadodeAutorizacion, String tipodePago, String emiteFactura, String codigodeOficina, String correlativoPago, String codigoAreadeNegocio, String codigoCajero, String fechaApertura,
			String sesion, String serPagadprCodOfi, String creationuser, String lastchangeuse) {

		this.te3wa = folioMovimiento;
		this.te3xa = tipoMovimiento;
		this.te3ya = estadoMovimientoCaja;
		this.te41a = formadePago;
		this.te42a = rUT1;
		this.te43a = dVRUT1;
		this.te44a = identificacion1;
		this.te45a = rUT2;
		this.te46a = dVRUT2;
		this.tea7a = codigodeBarra;
		this.te47a = identificacion2;
		this.te7ma = montoInformado;
		this.te4va = montoInteres;
		this.te4xa = montoReajuste;
		this.te7na = montoEmitido;
		this.te49a = observacionMovCaja;
		this.te4aa = sucursaldeEmpresa;
		this.te4ba = estadodeAutorizacion;
		this.te4ca = tipodePago;
		this.te4ea = emiteFactura;
		this.cmba = codigodeOficina;
		this.te9ca = correlativoPago;
		this.teqa = codigoAreadeNegocio;
		this.te1ba = codigoCajero;
		this.te1ca = fechaApertura;
		this.te10a = sesion;
		this.te4fa = serPagadprCodOfi;
		this.sajkm94 = creationuser;
		this.sajkm92 = lastchangeuse;
	}

	public String getCmba() {
		return cmba;
	}

	public String getObf002() {
		return obf002;
	}

	public String getObf003() {
		return obf003;
	}

	public String getObf005() {
		return obf005;
	}

	public String getObf006() {
		return obf006;
	}

	public String getSajkm92() {
		return sajkm92;
	}

	public String getSajkm94() {
		return sajkm94;
	}

	public String getTe10a() {
		return te10a;
	}

	public String getTe1ba() {
		return te1ba;
	}

	public String getTe1ca() {
		return te1ca;
	}

	public String getTe1sa() {
		return te1sa;
	}

	public String getTe1ta() {
		return te1ta;
	}

	public String getTe3wa() {
		return te3wa;
	}

	public String getTe3xa() {
		return te3xa;
	}

	public String getTe3ya() {
		return te3ya;
	}

	public String getTe3za() {
		return te3za;
	}

	public String getTe40a() {
		return te40a;
	}

	public String getTe41a() {
		return te41a;
	}

	public String getTe42a() {
		return te42a;
	}

	public String getTe43a() {
		return te43a;
	}

	public String getTe44a() {
		return te44a;
	}

	public String getTe45a() {
		return te45a;
	}

	public String getTe46a() {
		return te46a;
	}

	public String getTe47a() {
		return te47a;
	}

	public String getTe49a() {
		return te49a;
	}

	public String getTe4aa() {
		return te4aa;
	}

	public String getTe4ba() {
		return te4ba;
	}

	public String getTe4ca() {
		return te4ca;
	}

	public String getTe4da() {
		return te4da;
	}

	public String getTe4ea() {
		return te4ea;
	}

	public String getTe4fa() {
		return te4fa;
	}

	public String getTe4va() {
		return te4va;
	}

	public String getTe4xa() {
		return te4xa;
	}

	public String getTe7ma() {
		return te7ma;
	}

	public String getTe7na() {
		return te7na;
	}

	public String getTe9ca() {
		return te9ca;
	}

	public String getTea7a() {
		return tea7a;
	}

	public String getTeqa() {
		return teqa;
	}

	public void setCmba(String cmba) {
		this.cmba = cmba;
	}

	public void setObf002(String obf002) {
		this.obf002 = obf002;
	}

	public void setObf003(String obf003) {
		this.obf003 = obf003;
	}

	public void setObf005(String obf005) {
		this.obf005 = obf005;
	}

	public void setObf006(String obf006) {
		this.obf006 = obf006;
	}

	public void setSajkm92(String sajkm92) {
		this.sajkm92 = sajkm92;
	}

	public void setSajkm94(String sajkm94) {
		this.sajkm94 = sajkm94;
	}

	public void setTe10a(String te10a) {
		this.te10a = te10a;
	}

	public void setTe1ba(String te1ba) {
		this.te1ba = te1ba;
	}

	public void setTe1ca(String te1ca) {
		this.te1ca = te1ca;
	}

	public void setTe1sa(String te1sa) {
		this.te1sa = te1sa;
	}

	public void setTe1ta(String te1ta) {
		this.te1ta = te1ta;
	}

	public void setTe3wa(String te3wa) {
		this.te3wa = te3wa;
	}

	public void setTe3xa(String te3xa) {
		this.te3xa = te3xa;
	}

	public void setTe3ya(String te3ya) {
		this.te3ya = te3ya;
	}

	public void setTe3za(String te3za) {
		this.te3za = te3za;
	}

	public void setTe40a(String te40a) {
		this.te40a = te40a;
	}

	public void setTe41a(String te41a) {
		this.te41a = te41a;
	}

	public void setTe42a(String te42a) {
		this.te42a = te42a;
	}

	public void setTe43a(String te43a) {
		this.te43a = te43a;
	}

	public void setTe44a(String te44a) {
		this.te44a = te44a;
	}

	public void setTe45a(String te45a) {
		this.te45a = te45a;
	}

	public void setTe46a(String te46a) {
		this.te46a = te46a;
	}

	public void setTe47a(String te47a) {
		this.te47a = te47a;
	}

	public void setTe49a(String te49a) {
		this.te49a = te49a;
	}

	public void setTe4aa(String te4aa) {
		this.te4aa = te4aa;
	}

	public void setTe4ba(String te4ba) {
		this.te4ba = te4ba;
	}

	public void setTe4ca(String te4ca) {
		this.te4ca = te4ca;
	}

	public void setTe4da(String te4da) {
		this.te4da = te4da;
	}

	public void setTe4ea(String te4ea) {
		this.te4ea = te4ea;
	}

	public void setTe4fa(String te4fa) {
		this.te4fa = te4fa;
	}

	public void setTe4va(String te4va) {
		this.te4va = te4va;
	}

	public void setTe4xa(String te4xa) {
		this.te4xa = te4xa;
	}

	public void setTe7ma(String te7ma) {
		this.te7ma = te7ma;
	}

	public void setTe7na(String te7na) {
		this.te7na = te7na;
	}

	public void setTe9ca(String te9ca) {
		this.te9ca = te9ca;
	}

	public void setTea7a(String tea7a) {
		this.tea7a = tea7a;
	}

	public void setTeqa(String teqa) {
		this.teqa = teqa;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getEstadoMovimientoValidacion() {
		return estadoMovimientoValidacion;
	}

	public void setEstadoMovimientoValidacion(String estadoMovimientoValidacion) {
		this.estadoMovimientoValidacion = estadoMovimientoValidacion;
	}
	
	
}
