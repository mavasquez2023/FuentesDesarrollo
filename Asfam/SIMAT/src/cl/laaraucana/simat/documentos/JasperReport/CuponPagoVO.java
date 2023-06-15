package cl.laaraucana.simat.documentos.JasperReport;

import java.awt.image.BufferedImage;

public class CuponPagoVO {

	private ClienteVO cliente;
	private ValoresVO valores;
	private RegistroCuponVO registro;

	public CuponPagoVO() {
	}

	public CuponPagoVO(ClienteVO cliente, ValoresVO valores, RegistroCuponVO registro) {
		super();
		this.cliente = cliente;
		this.valores = valores;
		this.registro = registro;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public ValoresVO getValores() {
		return valores;
	}

	public void setValores(ValoresVO valores) {
		this.valores = valores;
	}

	public RegistroCuponVO getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroCuponVO registro) {
		this.registro = registro;
	}

	/*para doc jasper*/
	/*para cliente*/

	public String getRut() {
		return this.cliente.getRut();
	}

	public String getNombre() {
		return this.cliente.getNombre();
	}

	public String getCiudad() {
		return this.cliente.getCiudad();
	}

	public String getRegion() {
		return this.cliente.getRegion();
	}

	public String getTelefono() {
		return this.cliente.getTelefono();
	}

	public String getFecha_emision() {
		return this.cliente.getFecha_emision();
	}

	/*para valores*/

	public String getCapital() {
		return this.valores.getCapital();
	}

	public String getSeguros() {
		return this.valores.getSeguros();
	}

	public String getInteres() {
		return this.valores.getInteres();
	}

	public String getGastos_cobranza() {
		return this.valores.getGastos_cobranza();
	}

	public String getDescuentos() {
		return this.valores.getDescuentos();
	}

	public String getTotal_pagar() {
		return this.valores.getTotal_pagar();
	}

	/*para registro*/

	public BufferedImage getCodeBar() {
		return this.registro.getCodeBar();
	}

	public String getFecha() {
		return this.registro.getFecha();
	}

	public String getHora() {
		return this.registro.getHora();
	}

	public String getFirma() {
		return this.registro.getFirma();
	}

}
