package cl.laaraucana.capaservicios.database.vo;

import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ClienteVO;

public class DatosClienteVO {
	private long rutAfi;
	private String dvRutAfi;
	private ClienteVO cliente;
	
	public DatosClienteVO(){
	}
	
	public DatosClienteVO(long rutAfi, String dvRutAfi){
		setRutAfi(rutAfi);
		setDvRutAfi(dvRutAfi);
	}
	
	public String getDvRutAfi() {
		return dvRutAfi;
	}
	public void setDvRutAfi(String dvRutAfi) {
		this.dvRutAfi = dvRutAfi;
	}
	public long getRutAfi() {
		return rutAfi;
	}
	public void setRutAfi(long rutAfi) {
		this.rutAfi = rutAfi;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}
}
