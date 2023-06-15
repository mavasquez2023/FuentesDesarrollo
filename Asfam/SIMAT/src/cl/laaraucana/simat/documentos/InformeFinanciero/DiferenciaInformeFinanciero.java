package cl.laaraucana.simat.documentos.InformeFinanciero;

import cl.laaraucana.simat.entidades.InformeFinancieroVO;

public class DiferenciaInformeFinanciero {

	//metodo que resta los campos del IF manual - el IFde cuadratura.
	public InformeFinancieroVO obtenerDiferenciaIF(InformeFinancieroVO infoFinManual, InformeFinancieroVO infoFinCuadratura) throws Exception {
		System.out.println(" * * * * * VALIDADOR DIFERENCIA IF * * * * *");
		InformeFinancieroVO infoFinDiferencia = new InformeFinancieroVO();

		//bloque A

		infoFinDiferencia.setA(this.getDiferencia(Long.parseLong(infoFinManual.getA()), Long.parseLong(infoFinCuadratura.getA())));
		//agregados. a1,a2,a3 (a3.1,a3.2)
		infoFinDiferencia.setA_1(this.getDiferencia(Long.parseLong(infoFinManual.getA_1()), Long.parseLong(infoFinCuadratura.getA_1())));
		infoFinDiferencia.setA_2(this.getDiferencia(Long.parseLong(infoFinManual.getA_2()), Long.parseLong(infoFinCuadratura.getA_2())));
		infoFinDiferencia.setA_3(this.getDiferencia(Long.parseLong(infoFinManual.getA_3()), Long.parseLong(infoFinCuadratura.getA_3())));
		infoFinDiferencia.setA_3_1(this.getDiferencia(Long.parseLong(infoFinManual.getA_3_1()), Long.parseLong(infoFinCuadratura.getA_3_1())));
		infoFinDiferencia.setA_3_2(this.getDiferencia(Long.parseLong(infoFinManual.getA_3_2()), Long.parseLong(infoFinCuadratura.getA_3_2())));

		//a4
		infoFinDiferencia.setA_4(this.getDiferencia(Long.parseLong(infoFinManual.getA_4()), Long.parseLong(infoFinCuadratura.getA_4())));
		infoFinDiferencia.setA_4_1(this.getDiferencia(Long.parseLong(infoFinManual.getA_4_1()), Long.parseLong(infoFinCuadratura.getA_4_1())));
		infoFinDiferencia.setA_4_2(this.getDiferencia(Long.parseLong(infoFinManual.getA_4_2()), Long.parseLong(infoFinCuadratura.getA_4_2())));

		//bloque B		
		infoFinDiferencia.setB(this.getDiferencia(Long.parseLong(infoFinManual.getB()), Long.parseLong(infoFinCuadratura.getB())));

		infoFinDiferencia.setC(this.getDiferencia(Long.parseLong(infoFinManual.getC()), Long.parseLong(infoFinCuadratura.getC())));

		infoFinDiferencia.setC_1(this.getDiferencia(Long.parseLong(infoFinManual.getC_1()), Long.parseLong(infoFinCuadratura.getC_1())));
		infoFinDiferencia.setC_2(this.getDiferencia(Long.parseLong(infoFinManual.getC_2()), Long.parseLong(infoFinCuadratura.getC_2())));
		infoFinDiferencia.setC_3(this.getDiferencia(Long.parseLong(infoFinManual.getC_3()), Long.parseLong(infoFinCuadratura.getC_3())));
		infoFinDiferencia.setC_4(this.getDiferencia(Long.parseLong(infoFinManual.getC_4()), Long.parseLong(infoFinCuadratura.getC_4())));
		infoFinDiferencia.setC_5(this.getDiferencia(Long.parseLong(infoFinManual.getC_5()), Long.parseLong(infoFinCuadratura.getC_5())));
		//restar  
		infoFinDiferencia.setC_6(this.getDiferencia(Long.parseLong(infoFinManual.getC_6()), Long.parseLong(infoFinCuadratura.getC_6())));
		infoFinDiferencia.setC_6_1(this.getDiferencia(Long.parseLong(infoFinManual.getC_6_1()), Long.parseLong(infoFinCuadratura.getC_6_1())));
		infoFinDiferencia.setC_6_2(this.getDiferencia(Long.parseLong(infoFinManual.getC_6_2()), Long.parseLong(infoFinCuadratura.getC_6_2())));
		infoFinDiferencia.setC_6_3(this.getDiferencia(Long.parseLong(infoFinManual.getC_6_3()), Long.parseLong(infoFinCuadratura.getC_6_3())));
		infoFinDiferencia.setC_6_4(this.getDiferencia(Long.parseLong(infoFinManual.getC_6_4()), Long.parseLong(infoFinCuadratura.getC_6_4())));
		infoFinDiferencia.setC_6_5(this.getDiferencia(Long.parseLong(infoFinManual.getC_6_5()), Long.parseLong(infoFinCuadratura.getC_6_5())));

		infoFinDiferencia.setC_7(this.getDiferencia(Long.parseLong(infoFinManual.getC_7()), Long.parseLong(infoFinCuadratura.getC_7())));
		infoFinDiferencia.setC_7_1(this.getDiferencia(Long.parseLong(infoFinManual.getC_7_1()), Long.parseLong(infoFinCuadratura.getC_7_1())));
		infoFinDiferencia.setC_7_2(this.getDiferencia(Long.parseLong(infoFinManual.getC_7_2()), Long.parseLong(infoFinCuadratura.getC_7_2())));
		infoFinDiferencia.setC_7_3(this.getDiferencia(Long.parseLong(infoFinManual.getC_7_3()), Long.parseLong(infoFinCuadratura.getC_7_3())));
		infoFinDiferencia.setC_7_4(this.getDiferencia(Long.parseLong(infoFinManual.getC_7_4()), Long.parseLong(infoFinCuadratura.getC_7_4())));
		infoFinDiferencia.setC_7_5(this.getDiferencia(Long.parseLong(infoFinManual.getC_7_5()), Long.parseLong(infoFinCuadratura.getC_7_5())));
		//
		infoFinDiferencia.setC_8(this.getDiferencia(Long.parseLong(infoFinManual.getC_8()), Long.parseLong(infoFinCuadratura.getC_8())));
		infoFinDiferencia.setC_8_1(this.getDiferencia(Long.parseLong(infoFinManual.getC_8_1()), Long.parseLong(infoFinCuadratura.getC_8_1())));
		infoFinDiferencia.setC_8_2(this.getDiferencia(Long.parseLong(infoFinManual.getC_8_2()), Long.parseLong(infoFinCuadratura.getC_8_2())));
		infoFinDiferencia.setC_8_3(this.getDiferencia(Long.parseLong(infoFinManual.getC_8_3()), Long.parseLong(infoFinCuadratura.getC_8_3())));
		infoFinDiferencia.setC_8_4(this.getDiferencia(Long.parseLong(infoFinManual.getC_8_4()), Long.parseLong(infoFinCuadratura.getC_8_4())));
		infoFinDiferencia.setC_8_5(this.getDiferencia(Long.parseLong(infoFinManual.getC_8_5()), Long.parseLong(infoFinCuadratura.getC_8_5())));

		infoFinDiferencia.setC_9(this.getDiferencia(Long.parseLong(infoFinManual.getC_9()), Long.parseLong(infoFinCuadratura.getC_9())));
		infoFinDiferencia.setC_9_1(this.getDiferencia(Long.parseLong(infoFinManual.getC_9_1()), Long.parseLong(infoFinCuadratura.getC_9_1())));
		infoFinDiferencia.setC_9_2(this.getDiferencia(Long.parseLong(infoFinManual.getC_9_2()), Long.parseLong(infoFinCuadratura.getC_9_2())));
		infoFinDiferencia.setC_9_3(this.getDiferencia(Long.parseLong(infoFinManual.getC_9_3()), Long.parseLong(infoFinCuadratura.getC_9_3())));
		infoFinDiferencia.setC_9_4(this.getDiferencia(Long.parseLong(infoFinManual.getC_9_4()), Long.parseLong(infoFinCuadratura.getC_9_4())));
		infoFinDiferencia.setC_9_5(this.getDiferencia(Long.parseLong(infoFinManual.getC_9_5()), Long.parseLong(infoFinCuadratura.getC_9_5())));

		infoFinDiferencia.setD(this.getDiferencia(Long.parseLong(infoFinManual.getD()), Long.parseLong(infoFinCuadratura.getD())));

		infoFinDiferencia.setE(this.getDiferencia(Long.parseLong(infoFinManual.getE()), Long.parseLong(infoFinCuadratura.getE())));
		infoFinDiferencia.setE_1(this.getDiferencia(Long.parseLong(infoFinManual.getE_1()), Long.parseLong(infoFinCuadratura.getE_1())));
		infoFinDiferencia.setE_2(this.getDiferencia(Long.parseLong(infoFinManual.getE_2()), Long.parseLong(infoFinCuadratura.getE_2())));
		infoFinDiferencia.setE_3(this.getDiferencia(Long.parseLong(infoFinManual.getE_3()), Long.parseLong(infoFinCuadratura.getE_3())));
		infoFinDiferencia.setE_4(this.getDiferencia(Long.parseLong(infoFinManual.getE_4()), Long.parseLong(infoFinCuadratura.getE_4())));
		infoFinDiferencia.setE_5(this.getDiferencia(Long.parseLong(infoFinManual.getE_5()), Long.parseLong(infoFinCuadratura.getE_5())));

		infoFinDiferencia.setF(this.getDiferencia(Long.parseLong(infoFinManual.getF()), Long.parseLong(infoFinCuadratura.getF())));
		infoFinDiferencia.setF_1(this.getDiferencia(Long.parseLong(infoFinManual.getF_1()), Long.parseLong(infoFinCuadratura.getF_1())));
		infoFinDiferencia.setF_2(this.getDiferencia(Long.parseLong(infoFinManual.getF_2()), Long.parseLong(infoFinCuadratura.getF_2())));
		infoFinDiferencia.setF_3(this.getDiferencia(Long.parseLong(infoFinManual.getF_3()), Long.parseLong(infoFinCuadratura.getF_3())));
		infoFinDiferencia.setF_4(this.getDiferencia(Long.parseLong(infoFinManual.getF_4()), Long.parseLong(infoFinCuadratura.getF_4())));
		infoFinDiferencia.setF_5(this.getDiferencia(Long.parseLong(infoFinManual.getF_5()), Long.parseLong(infoFinCuadratura.getF_5())));

		infoFinDiferencia.setG(this.getDiferencia(Long.parseLong(infoFinManual.getG()), Long.parseLong(infoFinCuadratura.getG())));
		infoFinDiferencia.setG_1(this.getDiferencia(Long.parseLong(infoFinManual.getG_1()), Long.parseLong(infoFinCuadratura.getG_1())));
		infoFinDiferencia.setG_2(this.getDiferencia(Long.parseLong(infoFinManual.getG_2()), Long.parseLong(infoFinCuadratura.getG_2())));
		infoFinDiferencia.setG_3(this.getDiferencia(Long.parseLong(infoFinManual.getG_3()), Long.parseLong(infoFinCuadratura.getG_3())));
		infoFinDiferencia.setG_4(this.getDiferencia(Long.parseLong(infoFinManual.getG_4()), Long.parseLong(infoFinCuadratura.getG_4())));

		infoFinDiferencia.setH(this.getDiferencia(Long.parseLong(infoFinManual.getH()), Long.parseLong(infoFinCuadratura.getH())));

		return infoFinDiferencia;
	}

	private long getDiferencia(long v1, long v2) {
		return (v1 - v2);
	}

}
