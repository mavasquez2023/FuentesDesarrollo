package cl.laaraucana.capaservicios.database.vo;

public class TransferenciaVO {
	private long folioInterno;
	private long folioTesoreria;
	private String nroCotSAP;
	private long rutCliente;
	private String dvCliente;
	private long rutEmpresa;
	private String dvEmpresa;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String email;
	private String nroCelular;
	private String montoGiro;
	private String nroCuentaDestino;
	
	private String tipoCuenta;
	private String nombtipoCuenta;
	private String codBanco;
	private String nombBanco;
	
	private String estadoCol;
	private String estadoPago;
	private String glosaPago;
	private long folioSTL;
	private String folioCredito;
	
	private String comentario;
	private String origen;
	
	private String codOficina;
	private String evento;
	private String fechaGiro;
	private String horaGiro;
	
	public TransferenciaVO(){
	}

	public String getFullRut(){
		return rutCliente + "-" + dvCliente;
	}
	
	public long getFolioInterno() {
		return folioInterno;
	}

	public void setFolioInterno(long folioInterno) {
		this.folioInterno = folioInterno;
	}

	public long getFolioTesoreria() {
		return folioTesoreria;
	}

	public void setFolioTesoreria(long folioTesoreria) {
		this.folioTesoreria = folioTesoreria;
	}

	public String getNroCotSAP() {
		return nroCotSAP;
	}

	public void setNroCotSAP(String nroCotSAP) {
		this.nroCotSAP = nroCotSAP;
	}

	public long getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(long rutCliente) {
		this.rutCliente = rutCliente;
	}

	public String getDvCliente() {
		return dvCliente;
	}

	public void setDvCliente(String dvCliente) {
		this.dvCliente = dvCliente;
	}

	public long getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(long rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public String getDvEmpresa() {
		return dvEmpresa;
	}

	public void setDvEmpresa(String dvEmpresa) {
		this.dvEmpresa = dvEmpresa;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNroCelular() {
		return nroCelular;
	}

	public void setNroCelular(String nroCelular) {
		this.nroCelular = nroCelular;
	}

	public String getMontoGiro() {
		return montoGiro;
	}

	public void setMontoGiro(String montoGiro) {
		this.montoGiro = montoGiro;
	}

	public String getNroCuentaDestino() {
		return nroCuentaDestino;
	}

	public void setNroCuentaDestino(String nroCuentaDestino) {
		this.nroCuentaDestino = nroCuentaDestino;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getNombtipoCuenta() {
		return nombtipoCuenta;
	}

	public void setNombtipoCuenta(String nombtipoCuenta) {
		this.nombtipoCuenta = nombtipoCuenta;
	}

	public String getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}

	public String getNombBanco() {
		return nombBanco;
	}

	public void setNombBanco(String nombBanco) {
		this.nombBanco = nombBanco;
	}

	public String getEstadoCol() {
		return estadoCol;
	}

	public void setEstadoCol(String estadoCol) {
		this.estadoCol = estadoCol;
	}

	public String getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	public String getGlosaPago() {
		return glosaPago;
	}

	public void setGlosaPago(String glosaPago) {
		this.glosaPago = glosaPago;
	}

	public long getFolioSTL() {
		return folioSTL;
	}

	public void setFolioSTL(long folioSTL) {
		this.folioSTL = folioSTL;
	}

	public String getFolioCredito() {
		return folioCredito;
	}

	public void setFolioCredito(String folioCredito) {
		this.folioCredito = folioCredito;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getCodOficina() {
		return codOficina;
	}

	public void setCodOficina(String codOficina) {
		this.codOficina = codOficina;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getFechaGiro() {
		return fechaGiro;
	}

	public void setFechaGiro(String fechaGiro) {
		this.fechaGiro = fechaGiro;
	}

	public String getHoraGiro() {
		return horaGiro;
	}

	public void setHoraGiro(String horaGiro) {
		this.horaGiro = horaGiro;
	}
}