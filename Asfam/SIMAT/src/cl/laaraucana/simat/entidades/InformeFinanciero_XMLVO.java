package cl.laaraucana.simat.entidades;

public class InformeFinanciero_XMLVO {
	/*
	 * Identificador tabla Informe Financiero.
	 */
	private String id_inf_fin;
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

	public InformeFinanciero_XMLVO(InformeFinancieroVO if_vo) {
		super();
		this.id_inf_fin = if_vo.getId_inf_fin();
		this.nombre_entidad = if_vo.getNombre_entidad();
		this.periodo = if_vo.getPeriodo();
		this.a = Math.abs(Long.parseLong(if_vo.getA()));
		this.a_1 = Math.abs(Long.parseLong(if_vo.getA_1()));
		this.a_2 = Math.abs(Long.parseLong(if_vo.getA_2()));
		this.a_3 = Math.abs(Long.parseLong(if_vo.getA_3()));
		this.a_3_1 = Math.abs(Long.parseLong(if_vo.getA_3_1()));
		this.a_3_2 = Math.abs(Long.parseLong(if_vo.getA_3_2()));
		this.a_4 = Math.abs(Long.parseLong(if_vo.getA_4()));
		this.a_4_1 = Math.abs(Long.parseLong(if_vo.getA_4_1()));
		this.a_4_2 = Math.abs(Long.parseLong(if_vo.getA_4_2()));
		this.b = Math.abs(Long.parseLong(if_vo.getB()));
		this.c = Math.abs(Long.parseLong(if_vo.getC()));
		this.c_1 = Math.abs(Long.parseLong(if_vo.getC_1()));
		this.c_2 = Math.abs(Long.parseLong(if_vo.getC_2()));
		this.c_3 = Math.abs(Long.parseLong(if_vo.getC_3()));
		this.c_4 = Math.abs(Long.parseLong(if_vo.getC_4()));
		this.c_5 = Math.abs(Long.parseLong(if_vo.getC_5()));
		this.c_6 = Math.abs(Long.parseLong(if_vo.getC_6()));
		this.c_6_1 = Math.abs(Long.parseLong(if_vo.getC_6_1()));
		this.c_6_2 = Math.abs(Long.parseLong(if_vo.getC_6_2()));
		this.c_6_3 = Math.abs(Long.parseLong(if_vo.getC_6_3()));
		this.c_6_4 = Math.abs(Long.parseLong(if_vo.getC_6_4()));
		this.c_6_5 = Math.abs(Long.parseLong(if_vo.getC_6_5()));
		this.c_7 = Math.abs(Long.parseLong(if_vo.getC_7()));
		this.c_7_1 = Math.abs(Long.parseLong(if_vo.getC_7_1()));
		this.c_7_2 = Math.abs(Long.parseLong(if_vo.getC_7_2()));
		this.c_7_3 = Math.abs(Long.parseLong(if_vo.getC_7_3()));
		this.c_7_4 = Math.abs(Long.parseLong(if_vo.getC_7_4()));
		this.c_7_5 = Math.abs(Long.parseLong(if_vo.getC_7_5()));
		this.c_8 = Math.abs(Long.parseLong(if_vo.getC_8()));
		this.c_8_1 = Math.abs(Long.parseLong(if_vo.getC_8_1()));
		this.c_8_2 = Math.abs(Long.parseLong(if_vo.getC_8_2()));
		this.c_8_3 = Math.abs(Long.parseLong(if_vo.getC_8_3()));
		this.c_8_4 = Math.abs(Long.parseLong(if_vo.getC_8_4()));
		this.c_8_5 = Math.abs(Long.parseLong(if_vo.getC_8_5()));
		this.c_9 = Math.abs(Long.parseLong(if_vo.getC_9()));
		this.c_9_1 = Math.abs(Long.parseLong(if_vo.getC_9_1()));
		this.c_9_2 = Math.abs(Long.parseLong(if_vo.getC_9_2()));
		this.c_9_3 = Math.abs(Long.parseLong(if_vo.getC_9_3()));
		this.c_9_4 = Math.abs(Long.parseLong(if_vo.getC_9_4()));
		this.c_9_5 = Math.abs(Long.parseLong(if_vo.getC_9_5()));
		this.d = Math.abs(Long.parseLong(if_vo.getD()));
		this.e = Math.abs(Long.parseLong(if_vo.getE()));
		this.e_1 = Math.abs(Long.parseLong(if_vo.getE_1()));
		this.e_2 = Math.abs(Long.parseLong(if_vo.getE_2()));
		this.e_3 = Math.abs(Long.parseLong(if_vo.getE_3()));
		this.e_4 = Math.abs(Long.parseLong(if_vo.getE_4()));
		this.e_5 = Math.abs(Long.parseLong(if_vo.getE_5()));
		this.f = Math.abs(Long.parseLong(if_vo.getF()));
		this.f_1 = Math.abs(Long.parseLong(if_vo.getF_1()));
		this.f_2 = Math.abs(Long.parseLong(if_vo.getF_2()));
		this.f_3 = Math.abs(Long.parseLong(if_vo.getF_3()));
		this.f_4 = Math.abs(Long.parseLong(if_vo.getF_4()));
		this.f_5 = Math.abs(Long.parseLong(if_vo.getF_5()));
		this.g = Math.abs(Long.parseLong(if_vo.getG()));
		this.g_1 = Math.abs(Long.parseLong(if_vo.getG_1()));
		this.g_2 = Math.abs(Long.parseLong(if_vo.getG_2()));
		this.g_3 = Math.abs(Long.parseLong(if_vo.getG_3()));
		this.g_4 = Math.abs(Long.parseLong(if_vo.getG_4()));
		this.h = Long.parseLong(if_vo.getH());
		this.obs = if_vo.getObs();
	}

	public long getA() {
		return a;
	}

	public long getA_1() {
		return a_1;
	}

	public long getA_2() {
		return a_2;
	}

	public long getA_3() {
		return a_3;
	}

	public long getA_3_1() {
		return a_3_1;
	}

	public long getA_3_2() {
		return a_3_2;
	}

	public long getA_4() {
		return a_4;
	}

	public long getA_4_1() {
		return a_4_1;
	}

	public long getA_4_2() {
		return a_4_2;
	}

	public long getB() {
		return b;
	}

	public long getC() {
		return c;
	}

	public long getC_1() {
		return c_1;
	}

	public long getC_2() {
		return c_2;
	}

	public long getC_3() {
		return c_3;
	}

	public long getC_4() {
		return c_4;
	}

	public long getC_5() {
		return c_5;
	}

	public long getC_6() {
		return c_6;
	}

	public long getC_6_1() {
		return c_6_1;
	}

	public long getC_6_2() {
		return c_6_2;
	}

	public long getC_6_3() {
		return c_6_3;
	}

	public long getC_6_4() {
		return c_6_4;
	}

	public long getC_6_5() {
		return c_6_5;
	}

	public long getC_7() {
		return c_7;
	}

	public long getC_7_1() {
		return c_7_1;
	}

	public long getC_7_2() {
		return c_7_2;
	}

	public long getC_7_3() {
		return c_7_3;
	}

	public long getC_7_4() {
		return c_7_4;
	}

	public long getC_7_5() {
		return c_7_5;
	}

	public long getC_8() {
		return c_8;
	}

	public long getC_8_1() {
		return c_8_1;
	}

	public long getC_8_2() {
		return c_8_2;
	}

	public long getC_8_3() {
		return c_8_3;
	}

	public long getC_8_4() {
		return c_8_4;
	}

	public long getC_8_5() {
		return c_8_5;
	}

	public long getC_9() {
		return c_9;
	}

	public long getC_9_1() {
		return c_9_1;
	}

	public long getC_9_2() {
		return c_9_2;
	}

	public long getC_9_3() {
		return c_9_3;
	}

	public long getC_9_4() {
		return c_9_4;
	}

	public long getC_9_5() {
		return c_9_5;
	}

	public long getD() {
		return d;
	}

	public long getE() {
		return e;
	}

	public long getE_1() {
		return e_1;
	}

	public long getE_2() {
		return e_2;
	}

	public long getE_3() {
		return e_3;
	}

	public long getE_4() {
		return e_4;
	}

	public long getE_5() {
		return e_5;
	}

	public long getF() {
		return f;
	}

	public long getF_1() {
		return f_1;
	}

	public long getF_2() {
		return f_2;
	}

	public long getF_3() {
		return f_3;
	}

	public long getF_4() {
		return f_4;
	}

	public long getF_5() {
		return f_5;
	}

	public long getG() {
		return g;
	}

	public long getG_1() {
		return g_1;
	}

	public long getG_2() {
		return g_2;
	}

	public long getG_3() {
		return g_3;
	}

	public long getG_4() {
		return g_4;
	}

	public long getH() {
		return h;
	}

	public String getNombre_entidad() {
		return nombre_entidad;
	}

	public String getId_inf_fin() {
		return id_inf_fin;
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

	public void setId_inf_fin(String id_inf_fin) {
		this.id_inf_fin = id_inf_fin;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
