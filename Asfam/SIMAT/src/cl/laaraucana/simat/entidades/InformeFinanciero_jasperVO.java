package cl.laaraucana.simat.entidades;

import cl.laaraucana.simat.utiles.ManejoFormatoNumeros;

public class InformeFinanciero_jasperVO {

	private InformeFinancieroVO if_vo;

	public InformeFinanciero_jasperVO(InformeFinancieroVO if_vo) {
		super();
		this.if_vo = if_vo;
	}

	public String getA() {
		return formatMil(if_vo.getA());
	}

	public String getA_1() {
		return formatMil(if_vo.getA_1());
	}

	public String getA_2() {
		return formatMil(if_vo.getA_2());
	}

	public String getA_3() {
		return formatMil(if_vo.getA_3());
	}

	public String getA_3_1() {
		return formatMil(if_vo.getA_3_1());
	}

	public String getA_3_2() {
		return formatMil(if_vo.getA_3_2());
	}

	public String getA_4() {
		return formatMil(if_vo.getA_4());
	}

	public String getA_4_1() {
		return formatMil(if_vo.getA_4_1());
	}

	public String getA_4_2() {
		return formatMil(if_vo.getA_4_2());
	}

	public String getB() {
		return formatMil(if_vo.getB());
	}

	public String getC() {
		return formatMil(if_vo.getC());
	}

	public String getC_1() {
		return formatMil(if_vo.getC_1());
	}

	public String getC_2() {
		return formatMil(if_vo.getC_2());
	}

	public String getC_3() {
		return formatMil(if_vo.getC_3());
	}

	public String getC_4() {
		return formatMil(if_vo.getC_4());
	}

	public String getC_5() {
		return formatMil(if_vo.getC_5());
	}

	public String getC_6() {
		return formatMil(if_vo.getC_6());
	}

	public String getC_6_1() {
		return formatMil(if_vo.getC_6_1());
	}

	public String getC_6_2() {
		return formatMil(if_vo.getC_6_2());
	}

	public String getC_6_3() {
		return formatMil(if_vo.getC_6_3());
	}

	public String getC_6_4() {
		return formatMil(if_vo.getC_6_4());
	}

	public String getC_6_5() {
		return formatMil(if_vo.getC_6_5());
	}

	public String getC_7() {
		return formatMil(if_vo.getC_7());
	}

	public String getC_7_1() {
		return formatMil(if_vo.getC_7_1());
	}

	public String getC_7_2() {
		return formatMil(if_vo.getC_7_2());
	}

	public String getC_7_3() {
		return formatMil(if_vo.getC_7_3());
	}

	public String getC_7_4() {
		return formatMil(if_vo.getC_7_4());
	}

	public String getC_7_5() {
		return formatMil(if_vo.getC_7_5());
	}

	public String getC_8() {
		return formatMil(if_vo.getC_8());
	}

	public String getC_8_1() {
		return formatMil(if_vo.getC_8_1());
	}

	public String getC_8_2() {
		return formatMil(if_vo.getC_8_2());
	}

	public String getC_8_3() {
		return formatMil(if_vo.getC_8_3());
	}

	public String getC_8_4() {
		return formatMil(if_vo.getC_8_4());
	}

	public String getC_8_5() {
		return formatMil(if_vo.getC_8_5());
	}

	public String getC_9() {
		return formatMil(if_vo.getC_9());
	}

	public String getC_9_1() {
		return formatMil(if_vo.getC_9_1());
	}

	public String getC_9_2() {
		return formatMil(if_vo.getC_9_2());
	}

	public String getC_9_3() {
		return formatMil(if_vo.getC_9_3());
	}

	public String getC_9_4() {
		return formatMil(if_vo.getC_9_4());
	}

	public String getC_9_5() {
		return formatMil(if_vo.getC_9_5());
	}

	public String getD() {
		return formatMil(if_vo.getD());
	}

	public String getE() {
		return formatMil(if_vo.getE());
	}

	public String getE_1() {
		return formatMil(if_vo.getE_1());
	}

	public String getE_2() {
		return formatMil(if_vo.getE_2());
	}

	public String getE_3() {
		return formatMil(if_vo.getE_3());
	}

	public String getE_4() {
		return formatMil(if_vo.getE_4());
	}

	public String getE_5() {
		return formatMil(if_vo.getE_5());
	}

	public String getF() {
		return formatMil(if_vo.getF());
	}

	public String getF_1() {
		return formatMil(if_vo.getF_1());
	}

	public String getF_2() {
		return formatMil(if_vo.getF_2());
	}

	public String getF_3() {
		return formatMil(if_vo.getF_3());
	}

	public String getF_4() {
		return formatMil(if_vo.getF_4());
	}

	public String getF_5() {
		return formatMil(if_vo.getF_5());
	}

	public String getG() {
		return formatMil(if_vo.getG());
	}

	public String getG_1() {
		return formatMil(if_vo.getG_1());
	}

	public String getG_2() {
		return formatMil(if_vo.getG_2());
	}

	public String getG_3() {
		return formatMil(if_vo.getG_3());
	}

	public String getG_4() {
		return formatMil(if_vo.getG_4());
	}

	public String getH() {
		return formatMil(if_vo.getH());
	}

	public String getNombre_entidad() {
		return if_vo.getNombre_entidad();
	}

	public String getId_inf_fin() {
		return if_vo.getId_inf_fin();
	}

	public String getObs() {
		return if_vo.getObs();
	}

	public String getPeriodo() {
		return if_vo.getPeriodo();
	}

	private String formatMil(String number) {
		long n = Long.parseLong(number);
		ManejoFormatoNumeros mfn = new ManejoFormatoNumeros();
		return mfn.getFormatoDecimal(n);
	}

	public String toString() {
		return formatMil(if_vo.getA()) + "\n" + formatMil(if_vo.getA_1()) + "\n" + formatMil(if_vo.getA_2()) + "\n" + formatMil(if_vo.getA_3()) + "\n" + formatMil(if_vo.getA_3_1()) + "\n"
				+ formatMil(if_vo.getA_3_2()) + "\n" + formatMil(if_vo.getB()) + "\n" + formatMil(if_vo.getC()) + "\n" + formatMil(if_vo.getC_1()) + "\n" + formatMil(if_vo.getC_2()) + "\n"
				+ formatMil(if_vo.getC_3()) + "\n" + formatMil(if_vo.getC_4()) + "\n" + formatMil(if_vo.getC_5()) + "\n" + formatMil(if_vo.getC_6()) + "\n" + formatMil(if_vo.getC_6_1()) + "\n"
				+ formatMil(if_vo.getC_6_2()) + "\n" + formatMil(if_vo.getC_6_3()) + "\n" + formatMil(if_vo.getC_6_4()) + "\n" + formatMil(if_vo.getC_6_5()) + "\n" + formatMil(if_vo.getC_7()) + "\n"
				+ formatMil(if_vo.getC_7_1()) + "\n" + formatMil(if_vo.getC_7_2()) + "\n" + formatMil(if_vo.getC_7_3()) + "\n" + formatMil(if_vo.getC_7_4()) + "\n" + formatMil(if_vo.getC_7_5())
				+ "\n" + formatMil(if_vo.getC_8()) + "\n" + formatMil(if_vo.getC_8_1()) + "\n" + formatMil(if_vo.getC_8_2()) + "\n" + formatMil(if_vo.getC_8_3()) + "\n" + formatMil(if_vo.getC_8_4())
				+ "\n" + formatMil(if_vo.getC_8_5()) + "\n" + formatMil(if_vo.getD()) + "\n" + formatMil(if_vo.getE()) + "\n" + formatMil(if_vo.getE_1()) + "\n" + formatMil(if_vo.getE_2()) + "\n"
				+ formatMil(if_vo.getE_3()) + "\n" + formatMil(if_vo.getE_4()) + "\n" + formatMil(if_vo.getE_5()) + "\n" + formatMil(if_vo.getF()) + "\n" + formatMil(if_vo.getF_1()) + "\n"
				+ formatMil(if_vo.getF_2()) + "\n" + formatMil(if_vo.getF_3()) + "\n" + formatMil(if_vo.getF_4()) + "\n" + formatMil(if_vo.getF_5()) + "\n" + formatMil(if_vo.getG()) + "\n"
				+ formatMil(if_vo.getG_1()) + "\n" + formatMil(if_vo.getG_2()) + "\n" + formatMil(if_vo.getG_3()) + "\n" + formatMil(if_vo.getG_4()) + "\n" + formatMil(if_vo.getH());

	}
}
