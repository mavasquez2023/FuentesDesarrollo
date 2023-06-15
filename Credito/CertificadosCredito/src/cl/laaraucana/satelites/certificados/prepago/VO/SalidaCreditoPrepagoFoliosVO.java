package cl.laaraucana.satelites.certificados.prepago.VO;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaCreditoPrepagoFoliosVO extends AbstractSalidaVO {

	private String detalle;
	private String valorFolio1;
	private String valorFolio2;
	private String valorFolio3;
	private String valorFolio4;
	private String valorFolio5;
	private String valorFolio6;
	private String valorFolio7;
	private boolean cabecera;

	public SalidaCreditoPrepagoFoliosVO() {

	}
	
	public SalidaCreditoPrepagoFoliosVO(String detalle) {
		this.detalle= detalle;
	}
	
	public SalidaCreditoPrepagoFoliosVO(String detalle, String valorFolio1,
			String valorFolio2, String valorFolio3, String valorFolio4,
			String valorFolio5, String valorFolio6, String valorFolio7) {
		super();
		this.detalle = detalle;
		this.valorFolio1 = valorFolio1;
		this.valorFolio2 = valorFolio2;
		this.valorFolio3 = valorFolio3;
		this.valorFolio4 = valorFolio4;
		this.valorFolio5 = valorFolio5;
		this.valorFolio6 = valorFolio6;
		this.valorFolio7 = valorFolio7;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getValorFolio1() {
		return valorFolio1;
	}

	public void setValorFolio1(String valorFolio1) {
		this.valorFolio1 = valorFolio1;
	}

	public String getValorFolio2() {
		return valorFolio2;
	}

	public void setValorFolio2(String valorFolio2) {
		this.valorFolio2 = valorFolio2;
	}

	public String getValorFolio3() {
		return valorFolio3;
	}

	public void setValorFolio3(String valorFolio3) {
		this.valorFolio3 = valorFolio3;
	}

	public String getValorFolio4() {
		return valorFolio4;
	}

	public void setValorFolio4(String valorFolio4) {
		this.valorFolio4 = valorFolio4;
	}

	public String getValorFolio5() {
		return valorFolio5;
	}

	public void setValorFolio5(String valorFolio5) {
		this.valorFolio5 = valorFolio5;
	}

	public String getValorFolio6() {
		return valorFolio6;
	}

	public void setValorFolio6(String valorFolio6) {
		this.valorFolio6 = valorFolio6;
	}

	public String getValorFolio7() {
		return valorFolio7;
	}

	public void setValorFolio7(String valorFolio7) {
		this.valorFolio7 = valorFolio7;
	}

	public boolean isCabecera() {
		return cabecera;
	}

	public void setCabecera(boolean cabecera) {
		this.cabecera = cabecera;
	}

	

}
