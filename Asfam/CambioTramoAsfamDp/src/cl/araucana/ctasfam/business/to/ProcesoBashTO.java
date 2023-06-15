package cl.araucana.ctasfam.business.to;

public class ProcesoBashTO {
	
public ProcesoBashTO(){
		
	}

    private int periodo; //AFP7A
    private int empresa; //AFOVA
    private String rutaArchivo; //AFP66ARC
    private String estado; //AFP66EST
    private String usuarioSube; //SAJKM94
    private String fechaSubida; //OBF002
    private String horaSubida; //OBF003
    private int registrosInformados; //AFP66TRI
    private String origen; //AFP66ORI
    private int cantidadIntento; // AFP66CIN
    
    
    

	public String getRutaArchivo() {
		return rutaArchivo;
	}
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getEmpresa() {
		return empresa;
	}
	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}
	public String getUsuarioSube() {
		return usuarioSube;
	}
	public void setUsuarioSube(String usuarioSube) {
		this.usuarioSube = usuarioSube;
	}
	public String getFechaSubida() {
		return fechaSubida;
	}
	public void setFechaSubida(String fechaSubida) {
		this.fechaSubida = fechaSubida;
	}
	public String getHoraSubida() {
		return horaSubida;
	}
	public void setHoraSubida(String horaSubida) {
		this.horaSubida = horaSubida;
	}
	public int getRegistrosInformados() {
		return registrosInformados;
	}
	public void setRegistrosInformados(int registrosInformados) {
		this.registrosInformados = registrosInformados;
	}
	public int getCantidadIntento() {
		return cantidadIntento;
	}
	public void setCantidadIntento(int cantidadIntento) {
		this.cantidadIntento = cantidadIntento;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

}
