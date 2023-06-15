package cl.laaraucana.simat.entidades;

public class InformeFinancieroVO {
	/*
	 * Identificador tabla Informe Financiero.
	 */
	private int id_inf_fin;
	/*
	 * nombre de identificacion de la entidad.
	 */
	private String nombre_entidad;

	/*
	 * Permite Identificar el periodo de la informacion al que corresponde el registro para el Informe Financiero.
	 */
	private String periodo;
	/*
	 * total subsidios liquidación del mes.
	 */
	private long a;
	/*
	 * Provisión subsidios liquidación del mes.
	 */
	private long a_1;
	/*
	 * Provisión cotizaciones de subsidios del mes, enviadas en el mes siguiente.
	 */
	private long a_2;
	/*
	 * total Provisión complementarias, subsidios liquidos del mes.
	 */
	private long a_3;
	/*
	 *  Provisión complementarias, subsidios liquidos del mes.
	 */
	private long a_3_1;
	/*
	 * Provisión complementarias, Cotizaciones previsionales de subsidios del mes.
	 */
	private long a_3_2;
	/*
	 * total Reintegro por cobro indebido, 
	 */
	private long a_4;
	/*
	 * Reintegro por cobro indebido, producto fiscalización "SUSESO".
	 */
	private long a_4_1;
	/*
	 * Reintegro por cobro indebido, otros reintegros.
	 */
	private long a_4_2;

	/*
	 * egresos
	 */
	private long b;
	/*
	 * subtotal  gastos en Subsidio
	 */
	private long c;
	/*
	 * Subsidio por reposo prenatal.
	 */
	private long c_1;

	/*
	 * Subsidios por reposo postnatal.
	 */
	private long c_2;
	/*
	 * Subsidios por reposo postnata parental.
	 */
	private long c_3;
	/*
	 *Subsidios po permiso por enfermedad grave del un niño
	 */
	private long c_4;
	/*
	 * Subsidios a mujeres sin contrato vigente 
	 */
	private long c_5;
	/*
	 * Documentos caducados
	 */
	private long c_6;
	/*
	 * Documentos caducados, subsidios por reposo prenatal.
	 */
	private long c_6_1;
	/*
	 * Documentos caducados, subsidios por reposo postnatal.
	 */
	private long c_6_2;
	/*
	 * Documentos caducados, subsidios porpermiso  postnatal parental.
	 */
	private long c_6_3;
	/*
	 * Documentos caducados, subsidios por enfermedad grave del niño menor de un año.
	 */
	private long c_6_4;
	/*
	 * Documentos caducados, subsidios a mujeres sin contratos vigentes.
	 */
	private long c_6_5;
	/*
	 * Documentos anulados, 
	 */
	private long c_7;
	/*
	 * Documentos anulados, por subsidios por reposo prenatal.
	 */
	private long c_7_1;
	/*
	 * Documentos anulados, por subsidios por reposo postnatal.
	 */
	private long c_7_2;
	/*
	 * Documentos anulados, por subsidios por reposo postnatal parental.
	 */
	private long c_7_3;
	/*
	 * Documentos anulados, subsidio por enfermedad grave de niño menor de un año.
	 */
	private long c_7_4;
	/*
	 * Documentos anulados, subsidio a mujeres sin contratos vigentes.
	 */
	private long c_7_5;

	/*
	 * Documentos reemitidos
	 */
	private long c_8;
	/*
	 * Documentos reemitidos, subsidio por reposo prenatal.
	 */
	private long c_8_1;
	/*
	 * Documentos reemitidos, subsidio por reposo postnatal.
	 */
	private long c_8_2;
	/*
	 * Documentos reemitidos, subsidio por permiso postnatal parental.
	 */
	private long c_8_3;
	/*
	 * Documentos reemitidos, subsidio por enfermedad grave niño menor un año.
	 */
	private long c_8_4;
	/*
	 * Documentos reemitidos, subsidio a mujeres sin contrato vigente.
	 */
	private long c_8_5;
	/*
	 * Documentos revalidados,
	 */
	private long c_9;
	/*
	 * Documentos revalidados, subsidios por reposo prenatal.
	 */
	private long c_9_1;
	/*
	 * Documentos revalidados, subsidios por reposo postnatal.
	 */
	private long c_9_2;
	/*
	 * Documentos revalidados, subsidios por reposo postnatal parental.
	 */
	private long c_9_3;
	/*
	 * Documentos revalidados, subsidios por enfermedad grave del niño menor de un año.
	 */
	private long c_9_4;
	/*
	 * Documentos revalidados, subsidios a mujeres sin contrato vigente.
	 */
	private long c_9_5;
	/*
	 * Subtotal gasto en cotizaciones
	 * */
	private long d;
	/*
	 * Cotizaciones a fondos de pensiones
	 */
	private long e;
	/*
	 * Cotizaciones a fondos de pensiones, subsidios por reposo prenatal.
	 */
	private long e_1;
	/*
	 * Cotizaciones a fondos de pensiones, subsidios por reposo postnatal.
	 */
	private long e_2;
	/*
	 * Cotizaciones a fondos de pensiones, subsidios por reposo postnatal parental.
	 */
	private long e_3;
	/*
	 * Cotizaciones a fondos de pensiones, subsidios por enfermedad grave de niño menor de un año.
	 */
	private long e_4;
	/*
	 *  Cotizaciones a fondos de pensiones,subsidios  por mujeres sin contrato vigente
	 */
	private long e_5;

	/*
	 * Cotizaciones de salud, 
	 */

	private long f;
	/*
	 * Cotizaciones de salud, subsidio por reposo prenatal.
	 */
	private long f_1;
	/*
	 * Cotizaciones de salud, subsidio por reposo postnatal.
	 */
	private long f_2;
	/*
	 * Cotizaciones de salud, subsidio por reposo postnatal parental.
	 */
	private long f_3;
	/*
	 * Cotizaciones de salud, subsidio por enfermedad grave niño menor de un año.
	 */
	private long f_4;
	/*
	 * Cotizaciones de salud, subsidios  por mujeres sin contrato vigente
	 */
	private long f_5;
	/*
	 * Cotizaciones desahucio e indeminización, 
	 */
	private long g;
	/*
	 * Cotizaciones desahucio e indeminización,subsidios por reposo prenatal.
	 */
	private long g_1;
	/*
	 * Cotizaciones desahucio e indeminización, subsidios por postanal.
	 */
	private long g_2;
	/*
	 * Cotizaciones desahucio e indeminización, subsidios por permiso postnatal parental.
	 */
	private long g_3;
	/*
	 * Cotizaciones desahucio e indeminización, subsidios por enfermedad grave niño menor de un año.
	 */
	private long g_4;
	/*
	 *excedente
	 */
	private long h;
	/*
	 * Observaciones
	 */
	private String obs;

	public InformeFinancieroVO() {

	}

	public InformeFinancieroVO(int id_inf_fin, String nombre_entidad, String periodo, long a, long a_1, long a_2, long a_3, long a_3_1, long a_3_2, long a_4, long a_4_1, long a_4_2, long b, long c,
			long c_1, long c_2, long c_3, long c_4, long c_5, long c_6, long c_6_1, long c_6_2, long c_6_3, long c_6_4, long c_6_5, long c_7, long c_7_1, long c_7_2, long c_7_3, long c_7_4,
			long c_7_5, long c_8, long c_8_1, long c_8_2, long c_8_3, long c_8_4, long c_8_5, long c_9, long c_9_1, long c_9_2, long c_9_3, long c_9_4, long c_9_5, long d, long e, long e_1, long e_2,
			long e_3, long e_4, long e_5, long f, long f_1, long f_2, long f_3, long f_4, long f_5, long g, long g_1, long g_2, long g_3, long g_4, long h, String obs) {
		super();
		this.id_inf_fin = id_inf_fin;
		this.nombre_entidad = nombre_entidad;
		this.periodo = periodo;
		this.a = a;
		this.a_1 = a_1;
		this.a_2 = a_2;
		this.a_3 = a_3;
		this.a_3_1 = a_3_1;
		this.a_3_2 = a_3_2;
		this.a_4 = a_4;
		this.a_4_1 = a_4_1;
		this.a_4_2 = a_4_2;
		this.b = b;
		this.c = c;
		this.c_1 = c_1;
		this.c_2 = c_2;
		this.c_3 = c_3;
		this.c_4 = c_4;
		this.c_5 = c_5;
		this.c_6 = c_6;
		this.c_6_1 = c_6_1;
		this.c_6_2 = c_6_2;
		this.c_6_3 = c_6_3;
		this.c_6_4 = c_6_4;
		this.c_6_5 = c_6_5;
		this.c_7 = c_7;
		this.c_7_1 = c_7_1;
		this.c_7_2 = c_7_2;
		this.c_7_3 = c_7_3;
		this.c_7_4 = c_7_4;
		this.c_7_5 = c_7_5;
		this.c_8 = c_8;
		this.c_8_1 = c_8_1;
		this.c_8_2 = c_8_2;
		this.c_8_3 = c_8_3;
		this.c_8_4 = c_8_4;
		this.c_8_5 = c_8_5;
		this.c_9 = c_9;
		this.c_9_1 = c_9_1;
		this.c_9_2 = c_9_2;
		this.c_9_3 = c_9_3;
		this.c_9_4 = c_9_4;
		this.c_9_5 = c_9_5;
		this.d = d;
		this.e = e;
		this.e_1 = e_1;
		this.e_2 = e_2;
		this.e_3 = e_3;
		this.e_4 = e_4;
		this.e_5 = e_5;
		this.f = f;
		this.f_1 = f_1;
		this.f_2 = f_2;
		this.f_3 = f_3;
		this.f_4 = f_4;
		this.f_5 = f_5;
		this.g = g;
		this.g_1 = g_1;
		this.g_2 = g_2;
		this.g_3 = g_3;
		this.g_4 = g_4;
		this.h = h;
		this.obs = obs;
	}

	public String getA() {
		return Long.toString(a);
	}

	public String getA_1() {
		return Long.toString(a_1);
	}

	public String getA_2() {
		return Long.toString(a_2);
	}

	public String getA_3() {
		return Long.toString(a_3);
	}

	public String getA_3_1() {
		return Long.toString(a_3_1);
	}

	public String getA_3_2() {
		return Long.toString(a_3_2);
	}

	public String getA_4() {
		return Long.toString(a_4);
	}

	public String getA_4_1() {
		return Long.toString(a_4_1);
	}

	public String getA_4_2() {
		return Long.toString(a_4_2);
	}

	public String getB() {
		return Long.toString(b);
	}

	public String getC() {
		return Long.toString(c);
	}

	public String getC_1() {
		return Long.toString(c_1);
	}

	public String getC_2() {
		return Long.toString(c_2);
	}

	public String getC_3() {
		return Long.toString(c_3);
	}

	public String getC_4() {
		return Long.toString(c_4);
	}

	public String getC_5() {
		return Long.toString(c_5);
	}

	public String getC_6() {
		return Long.toString(c_6);
	}

	public String getC_6_1() {
		return Long.toString(c_6_1);
	}

	public String getC_6_2() {
		return Long.toString(c_6_2);
	}

	public String getC_6_3() {
		return Long.toString(c_6_3);
	}

	public String getC_6_4() {
		return Long.toString(c_6_4);
	}

	public String getC_6_5() {
		return Long.toString(c_6_5);
	}

	public String getC_7() {
		return Long.toString(c_7);
	}

	public String getC_7_1() {
		return Long.toString(c_7_1);
	}

	public String getC_7_2() {
		return Long.toString(c_7_2);
	}

	public String getC_7_3() {
		return Long.toString(c_7_3);
	}

	public String getC_7_4() {
		return Long.toString(c_7_4);
	}

	public String getC_7_5() {
		return Long.toString(c_7_5);
	}

	public String getC_8() {
		return Long.toString(c_8);
	}

	public String getC_8_1() {
		return Long.toString(c_8_1);
	}

	public String getC_8_2() {
		return Long.toString(c_8_2);
	}

	public String getC_8_3() {
		return Long.toString(c_8_3);
	}

	public String getC_8_4() {
		return Long.toString(c_8_4);
	}

	public String getC_8_5() {
		return Long.toString(c_8_5);
	}

	public String getC_9() {
		return Long.toString(c_9);
	}

	public String getC_9_1() {
		return Long.toString(c_9_1);
	}

	public String getC_9_2() {
		return Long.toString(c_9_2);
	}

	public String getC_9_3() {
		return Long.toString(c_9_3);
	}

	public String getC_9_4() {
		return Long.toString(c_9_4);
	}

	public String getC_9_5() {
		return Long.toString(c_9_5);
	}

	public String getD() {
		return Long.toString(d);
	}

	public String getE() {
		return Long.toString(e);
	}

	public String getE_1() {
		return Long.toString(e_1);
	}

	public String getE_2() {
		return Long.toString(e_2);
	}

	public String getE_3() {
		return Long.toString(e_3);
	}

	public String getE_4() {
		return Long.toString(e_4);
	}

	public String getE_5() {
		return Long.toString(e_5);
	}

	public String getNombre_entidad() {
		return nombre_entidad;
	}

	public String getF() {
		return Long.toString(f);
	}

	public String getF_1() {
		return Long.toString(f_1);
	}

	public String getF_2() {
		return Long.toString(f_2);
	}

	public String getF_3() {
		return Long.toString(f_3);
	}

	public String getF_4() {
		return Long.toString(f_4);
	}

	public String getF_5() {
		return Long.toString(f_5);
	}

	public String getG() {
		return Long.toString(g);
	}

	public String getG_1() {
		return Long.toString(g_1);
	}

	public String getG_2() {
		return Long.toString(g_2);
	}

	public String getG_3() {
		return Long.toString(g_3);
	}

	public String getG_4() {
		return Long.toString(g_4);
	}

	public String getH() {
		return Long.toString(h);
	}

	public String getId_inf_fin() {
		return Long.toString(id_inf_fin);
	}

	public String getObs() {
		return obs;
	}

	public String getPeriodo() {
		return periodo;
	}

	//SET ---------------------------------------------------------------------
	public void setA(long a) {
		this.a = a;
	}

	public void setA_1(long a_1) {
		this.a_1 = a_1;
	}

	public void setA_2(long a_2) {
		this.a_2 = a_2;
	}

	public void setA_3(long a_3) {
		this.a_3 = a_3;
	}

	public void setA_3_1(long a_3_1) {
		this.a_3_1 = a_3_1;
	}

	public void setA_3_2(long a_3_2) {
		this.a_3_2 = a_3_2;
	}

	public void setA_4(long a_4) {
		this.a_4 = a_4;
	}

	public void setA_4_1(long a_4_1) {
		this.a_4_1 = a_4_1;
	}

	public void setA_4_2(long a_4_2) {
		this.a_4_2 = a_4_2;
	}

	public void setB(long b) {
		this.b = b;
	}

	public void setC(long c) {
		this.c = c;
	}

	public void setC_1(long c_1) {
		this.c_1 = c_1;
	}

	public void setC_2(long c_2) {
		this.c_2 = c_2;
	}

	public void setC_3(long c_3) {
		this.c_3 = c_3;
	}

	public void setC_4(long c_4) {
		this.c_4 = c_4;
	}

	public void setC_5(long c_5) {
		this.c_5 = c_5;
	}

	public void setC_6(long c_6) {
		this.c_6 = c_6;
	}

	public void setC_6_1(long c_6_1) {
		this.c_6_1 = c_6_1;
	}

	public void setC_6_2(long c_6_2) {
		this.c_6_2 = c_6_2;
	}

	public void setC_6_3(long c_6_3) {
		this.c_6_3 = c_6_3;
	}

	public void setC_6_4(long c_6_4) {
		this.c_6_4 = c_6_4;
	}

	public void setC_6_5(long c_6_5) {
		this.c_6_5 = c_6_5;
	}

	public void setC_7(long c_7) {
		this.c_7 = c_7;
	}

	public void setC_7_1(long c_7_1) {
		this.c_7_1 = c_7_1;
	}

	public void setC_7_2(long c_7_2) {
		this.c_7_2 = c_7_2;
	}

	public void setC_7_3(long c_7_3) {
		this.c_7_3 = c_7_3;
	}

	public void setC_7_4(long c_7_4) {
		this.c_7_4 = c_7_4;
	}

	public void setC_7_5(long c_7_5) {
		this.c_7_5 = c_7_5;
	}

	public void setC_8(long c_8) {
		this.c_8 = c_8;
	}

	public void setC_8_1(long c_8_1) {
		this.c_8_1 = c_8_1;
	}

	public void setC_8_2(long c_8_2) {
		this.c_8_2 = c_8_2;
	}

	public void setC_8_3(long c_8_3) {
		this.c_8_3 = c_8_3;
	}

	public void setC_8_4(long c_8_4) {
		this.c_8_4 = c_8_4;
	}

	public void setC_8_5(long c_8_5) {
		this.c_8_5 = c_8_5;
	}

	public void setC_9(long c_9) {
		this.c_9 = c_9;
	}

	public void setC_9_1(long c_9_1) {
		this.c_9_1 = c_9_1;
	}

	public void setC_9_2(long c_9_2) {
		this.c_9_2 = c_9_2;
	}

	public void setC_9_3(long c_9_3) {
		this.c_9_3 = c_9_3;
	}

	public void setC_9_4(long c_9_4) {
		this.c_9_4 = c_9_4;
	}

	public void setC_9_5(long c_9_5) {
		this.c_9_5 = c_9_5;
	}

	public void setD(long d) {
		this.d = d;
	}

	public void setE(long e) {
		this.e = e;
	}

	public void setE_1(long e_1) {
		this.e_1 = e_1;
	}

	public void setE_2(long e_2) {
		this.e_2 = e_2;
	}

	public void setE_3(long e_3) {
		this.e_3 = e_3;
	}

	public void setE_4(long e_4) {
		this.e_4 = e_4;
	}

	public void setE_5(long e_5) {
		this.e_5 = e_5;
	}

	public void setNombre_entidad(String nombre_entidad) {
		this.nombre_entidad = nombre_entidad;
	}

	public void setF(long f) {
		this.f = f;
	}

	public void setF_1(long f_1) {
		this.f_1 = f_1;
	}

	public void setF_2(long f_2) {
		this.f_2 = f_2;
	}

	public void setF_3(long f_3) {
		this.f_3 = f_3;
	}

	public void setF_4(long f_4) {
		this.f_4 = f_4;
	}

	public void setF_5(long f_5) {
		this.f_5 = f_5;
	}

	public void setG(long g) {
		this.g = g;
	}

	public void setG_1(long g_1) {
		this.g_1 = g_1;
	}

	public void setG_2(long g_2) {
		this.g_2 = g_2;
	}

	public void setG_3(long g_3) {
		this.g_3 = g_3;
	}

	public void setG_4(long g_4) {
		this.g_4 = g_4;
	}

	public void setH(long h) {
		this.h = h;
	}

	public void setId_inf_fin(int id_inf_fin) {
		this.id_inf_fin = id_inf_fin;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
