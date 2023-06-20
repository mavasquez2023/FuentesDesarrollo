package cl.laaraucana.licenciascompin.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FicherosVo {

	private CommonsMultipartFile medica;
	private CommonsMultipartFile adicional;
	private CommonsMultipartFile zonac;
	private CommonsMultipartFile renta1;
	private CommonsMultipartFile renta2;
	private CommonsMultipartFile renta3;
	private CommonsMultipartFile renta4;
	private CommonsMultipartFile renta5;
	private CommonsMultipartFile renta6;
	private boolean maternal;

	public CommonsMultipartFile getMedica() {
		return medica;
	}

	public void setMedica(CommonsMultipartFile medica) {
		this.medica = medica;
	}

	public CommonsMultipartFile getAdicional() {
		return adicional;
	}

	public void setAdicional(CommonsMultipartFile adicional) {
		this.adicional = adicional;
	}

	public CommonsMultipartFile getZonac() {
		return zonac;
	}

	public void setZonac(CommonsMultipartFile zonac) {
		this.zonac = zonac;
	}

	public CommonsMultipartFile getRenta1() {
		return renta1;
	}

	public void setRenta1(CommonsMultipartFile renta1) {
		this.renta1 = renta1;
	}

	public CommonsMultipartFile getRenta2() {
		return renta2;
	}

	public void setRenta2(CommonsMultipartFile renta2) {
		this.renta2 = renta2;
	}

	public CommonsMultipartFile getRenta3() {
		return renta3;
	}

	public void setRenta3(CommonsMultipartFile renta3) {
		this.renta3 = renta3;
	}

	public CommonsMultipartFile getRenta4() {
		return renta4;
	}

	public void setRenta4(CommonsMultipartFile renta4) {
		this.renta4 = renta4;
	}

	public CommonsMultipartFile getRenta5() {
		return renta5;
	}

	public void setRenta5(CommonsMultipartFile renta5) {
		this.renta5 = renta5;
	}

	public CommonsMultipartFile getRenta6() {
		return renta6;
	}

	public void setRenta6(CommonsMultipartFile renta6) {
		this.renta6 = renta6;
	}

	public boolean isMaternal() {
		return maternal;
	}

	public void setMaternal(boolean maternal) {
		this.maternal = maternal;
	}

}
