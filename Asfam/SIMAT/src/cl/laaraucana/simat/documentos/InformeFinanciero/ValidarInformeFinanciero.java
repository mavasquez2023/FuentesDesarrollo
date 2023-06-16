/*
 * clase que valida el informe financiero
 * 
 * 
 * */

package cl.laaraucana.simat.documentos.InformeFinanciero;

import cl.laaraucana.simat.entidades.InformeFinancieroVO;
import cl.laaraucana.simat.entidades.SumVO;
import cl.laaraucana.simat.mannagerDB2.Sum_InformeFinancieroMannager;

public class ValidarInformeFinanciero {
	/**
	 * 
	 * **/
	//metodo que obtiene los valores de base datos (cuadratura)
	public InformeFinancieroVO CuadrarInformeFinanciero(InformeFinancieroVO informeVO_BG) throws Exception{
		InformeFinancieroVO IF_Cuadratura=new InformeFinancieroVO(0,null, null, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
		//al cuadrar, 
		//obtenemos valores de tablas seguna reglas aplicadas.
		IF_Cuadratura=this.ObtenerValorCuadraturaIF(informeVO_BG);
		return IF_Cuadratura;
	}
	//metodo que completa los valores para la cuadratura de IF
	private InformeFinancieroVO ObtenerValorCuadraturaIF(InformeFinancieroVO informeVO_BG) throws Exception{
		
		InformeFinancieroVO infoFinDif=new InformeFinancieroVO(0,null, null, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
		//reglas de llenado por orden debido a suma de subcampos,
		infoFinDif=this.cargaBloque_A(infoFinDif,informeVO_BG);
		System.out.println("* * * * * * * * * * ** A:" +infoFinDif.getA());
		infoFinDif=this.cargaBloque_C(infoFinDif);
		System.out.println("* * * * * * * * * * ** C:" +infoFinDif.getC());
		infoFinDif=this.cargaBloque_E(infoFinDif);
		System.out.println("* * * * * * * * * * ** E:" +infoFinDif.getE());
		infoFinDif=this.cargaBloque_F(infoFinDif);
		System.out.println("* * * * * * * * * * ** F:" +infoFinDif.getF());
		infoFinDif=this.cargaBloque_G(infoFinDif);
		System.out.println("* * * * * * * * * * ** G:" +infoFinDif.getG());
		//d=e+f+g
		infoFinDif=this.cargaBloque_D(infoFinDif);
		System.out.println("* * * * * * * * * * ** D:" +infoFinDif.getD());
		//b= c+d
		infoFinDif=this.cargaBloque_B(infoFinDif);
		System.out.println("* * * * * * * * * * ** B:" +infoFinDif.getB());
		//h=a-b
		infoFinDif=this.cargaBloque_H(infoFinDif);
		System.out.println("* * * * * * * * * * ** H: "+infoFinDif.getH());
		return infoFinDif;
	}
	
	//metodos que extrae segun las reglas aplicadas los valores de tablas para el IF
	private InformeFinancieroVO cargaBloque_A(InformeFinancieroVO infoFin,InformeFinancieroVO informeVO_BG)throws Exception{
		System.out.println(" * * * * * VALIDADOR b_A IF");
//	modificacion	27-08-2014, se asignan valores absolutos para toda la seccion "A" del informe financiero.
		//para reintegros 
		
//		modificacion de 8/7/14, se mueven valores desde balanceGenearl a ifCuadratura., antes de extraer la diferencia.
		//a1,2,3, no son cuadrables versus tablas de planos, se transfiere valor de balanceGeneral.
		infoFin.setA_1(
				Long.parseLong(this.formatAbsoluto(informeVO_BG.getA_1()))
			);
		infoFin.setA_2(
				Long.parseLong(this.formatAbsoluto(informeVO_BG.getA_2()))
			);
		
		infoFin.setA_3_1(
				Long.parseLong(this.formatAbsoluto(informeVO_BG.getA_3_1()))
			);
		infoFin.setA_3_2(
				Long.parseLong(this.formatAbsoluto(informeVO_BG.getA_3_2()))
			);
		infoFin.setA_3(
				Long.parseLong(this.formatAbsoluto(infoFin.getA_3_1()))+
				Long.parseLong(this.formatAbsoluto(infoFin.getA_3_2()))
			);
		//cuadrar a.4, consultas sobre tabla 1(smf01)
		
		Sum_InformeFinancieroMannager sIF=new Sum_InformeFinancieroMannager();
		SumVO sumSQL=new SumVO();
		
		sumSQL=sIF.getSum_A_4_1(sumSQL);
		//infoFin.setA_4_1(sumSQL.getResultadoSum());
		infoFin.setA_4_1(Long.parseLong(this.formatAbsoluto(sumSQL.getResultadoSum())));
		
		sumSQL=sIF.getSum_A_4_2(sumSQL);
		//infoFin.setA_4_2(sumSQL.getResultadoSum());
		infoFin.setA_4_2(Long.parseLong(this.formatAbsoluto(sumSQL.getResultadoSum())));
		
		infoFin.setA_4(
				Long.parseLong(this.formatAbsoluto(infoFin.getA_4_1()))+
				Long.parseLong(this.formatAbsoluto(infoFin.getA_4_2()))
				);
		//set A, ingresos
		infoFin.setA(
				Long.parseLong(this.formatAbsoluto(infoFin.getA_1()))+
				Long.parseLong(this.formatAbsoluto(infoFin.getA_2()))+
				Long.parseLong(this.formatAbsoluto(infoFin.getA_3()))+
				Long.parseLong(this.formatAbsoluto(infoFin.getA_4()))
				);				
		return infoFin;		
	}
	
	private InformeFinancieroVO cargaBloque_B(InformeFinancieroVO infoFin)throws Exception{
		//egresos c+d
		infoFin.setB(
				Long.parseLong(infoFin.getC())+
				Long.parseLong(infoFin.getD())
				);
		return infoFin;
	}
	
	private InformeFinancieroVO cargaBloque_C(InformeFinancieroVO infoFin)throws Exception{
		System.out.println(" * * * * * VALIDADOR b_C IF");
		
		Sum_InformeFinancieroMannager sIF=new Sum_InformeFinancieroMannager();
		SumVO sumSQL=new SumVO();
//cuadrar c subtotal gastos en subsidios
	
		sumSQL=sIF.getSum_C_1(sumSQL);
		infoFin.setC_1(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_2(sumSQL);
		infoFin.setC_2(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_3(sumSQL);
		infoFin.setC_3(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_4(sumSQL);
		infoFin.setC_4(sumSQL.getResultadoSum());
		//valor en cero
		infoFin.setC_5(0);
		
		sumSQL=sIF.getSum_C_6_1(sumSQL);
		infoFin.setC_6_1(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_6_2(sumSQL);
		infoFin.setC_6_2(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_6_3(sumSQL);
		infoFin.setC_6_3(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_6_4(sumSQL);
		infoFin.setC_6_4(sumSQL.getResultadoSum());
		//valor siempre en cero
		infoFin.setC_6_5(0);
	//c6 total
		infoFin.setC_6(this.getIF_C_6(infoFin));

		sumSQL=sIF.getSum_C_7_1(sumSQL);
		infoFin.setC_7_1(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_7_2(sumSQL);
		infoFin.setC_7_2(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_7_3(sumSQL);
		infoFin.setC_7_3(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_7_4(sumSQL);
		infoFin.setC_7_4(sumSQL.getResultadoSum());
		//valor siempre en cero
		infoFin.setC_7_5(0);
		//c7 total		
		infoFin.setC_7(this.getIF_C_7(infoFin));
	
	//c8 reemitidos suma de montos sobre smf06(pra monto documento nuevo)
		sumSQL=sIF.getSum_C_8_1(sumSQL);
		infoFin.setC_8_1(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_8_2(sumSQL);
		infoFin.setC_8_2(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_8_3(sumSQL);
		infoFin.setC_8_3(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_8_4(sumSQL);
		infoFin.setC_8_4(sumSQL.getResultadoSum());
		//valor siempre en cero
		infoFin.setC_8_5(0);
	//c8 total
		infoFin.setC_8(this.getIF_C_8(infoFin));
		
	//c.9 bloque
		sumSQL=sIF.getSum_C_9_1(sumSQL);
		infoFin.setC_9_1(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_9_2(sumSQL);
		infoFin.setC_9_2(sumSQL.getResultadoSum());							
		sumSQL=sIF.getSum_C_9_3(sumSQL);
		infoFin.setC_9_3(sumSQL.getResultadoSum());
		sumSQL=sIF.getSum_C_9_4(sumSQL);
		infoFin.setC_9_4(sumSQL.getResultadoSum());	
		//valor siempre en cero
		infoFin.setC_9_5(0);		
		//c.9 total
		infoFin.setC_9(this.getIF_C_9(infoFin));
		
	//C total
		infoFin.setC(this.getIF_C(infoFin));
	
		return infoFin;
	}

	private InformeFinancieroVO cargaBloque_D(InformeFinancieroVO infoFin)throws Exception{
		//D, este blque requiere de los valores e+f+g, por ende se sumara al final.
		infoFin.setD(
				Long.parseLong(infoFin.getE())+
				Long.parseLong(infoFin.getF())+
				Long.parseLong(infoFin.getG())
				);
		return infoFin;
	}
	
	private InformeFinancieroVO cargaBloque_E(InformeFinancieroVO infoFin) throws Exception{
		System.out.println(" * * * * * VALIDADOR b_e IF");
		
		Sum_InformeFinancieroMannager sIF=new Sum_InformeFinancieroMannager();
		SumVO sumSQL=new SumVO();
		
		sumSQL=sIF.getSum_E1(sumSQL);
		infoFin.setE_1(sumSQL.getResultadoSum());
		
		sumSQL=sIF.getSum_E2(sumSQL);
		infoFin.setE_2(sumSQL.getResultadoSum());
		
		sumSQL=sIF.getSum_E3(sumSQL);
		infoFin.setE_3(sumSQL.getResultadoSum());
		
		sumSQL=sIF.getSum_E4(sumSQL);
		infoFin.setE_4(sumSQL.getResultadoSum());
		
		// campo siempre es cero
		infoFin.setE_5(0);
	//total E
		infoFin.setE(this.getIF_E(infoFin)); 
		
		return infoFin;
	}
	
	private InformeFinancieroVO cargaBloque_F(InformeFinancieroVO infoFin)throws Exception{
		System.out.println(" * * * * * VALIDADOR f IF");
		
		Sum_InformeFinancieroMannager sIF=new Sum_InformeFinancieroMannager();
		SumVO sumSQL=new SumVO();
		
		sumSQL=sIF.getSum_F1(sumSQL);
		infoFin.setF_1(sumSQL.getResultadoSum());
		
		sumSQL=sIF.getSum_F2(sumSQL);
		infoFin.setF_2(sumSQL.getResultadoSum());
		
		sumSQL=sIF.getSum_F3(sumSQL);	
		infoFin.setF_3(sumSQL.getResultadoSum());
		
		sumSQL=sIF.getSum_F4(sumSQL);
		infoFin.setF_4(sumSQL.getResultadoSum());
				
		//valor siempre en cero
		infoFin.setF_5(0);
		
	//f total
		infoFin.setF(this.getIF_F(infoFin));
		return infoFin;
	}
	
	private InformeFinancieroVO cargaBloque_G(InformeFinancieroVO infoFin)throws Exception{
	//bloque G valores en cero
		System.out.println(" * * * * * VALIDADOR b_G IF");
		infoFin.setG(0);
		infoFin.setG_1(0);
		infoFin.setG_2(0);
		infoFin.setG_3(0);
		infoFin.setG_4(0);
		return infoFin;		
	}
	
	private InformeFinancieroVO cargaBloque_H(InformeFinancieroVO infoFin)throws Exception{
		//bloque H deficit (A-B)
		infoFin.setH(
				Long.parseLong(infoFin.getA())-
				Long.parseLong(infoFin.getB()));
		return infoFin;
	}
	
	private long getIF_C_6(InformeFinancieroVO infoFinan ){
		return (
				Long.parseLong(infoFinan.getC_6_1())+
				Long.parseLong(infoFinan.getC_6_2())+
				Long.parseLong(infoFinan.getC_6_3())+
				Long.parseLong(infoFinan.getC_6_4())+
				Long.parseLong(infoFinan.getC_6_5())
				);
	}
	
	private long getIF_C_7(InformeFinancieroVO infoFinan ){
		return (
				Long.parseLong(infoFinan.getC_7_1())+
				Long.parseLong(infoFinan.getC_7_2())+
				Long.parseLong(infoFinan.getC_7_3())+
				Long.parseLong(infoFinan.getC_7_4())+
				Long.parseLong(infoFinan.getC_7_5())
				);
	}
		
	private long getIF_C_8(InformeFinancieroVO infoFinan ){
		return (
				Long.parseLong(infoFinan.getC_8_1())+
				Long.parseLong(infoFinan.getC_8_2())+
				Long.parseLong(infoFinan.getC_8_3())+
				Long.parseLong(infoFinan.getC_8_4())+
				Long.parseLong(infoFinan.getC_8_5())
				);
	}
	
	private long getIF_C_9(InformeFinancieroVO infoFinan ){
		return (
				Long.parseLong(infoFinan.getC_9_1())+
				Long.parseLong(infoFinan.getC_9_2())+
				Long.parseLong(infoFinan.getC_9_3())+
				Long.parseLong(infoFinan.getC_9_4())+
				Long.parseLong(infoFinan.getC_9_5())
				);
	}
	
	private long getIF_C(InformeFinancieroVO infoFinan ){
		return (
				Long.parseLong(infoFinan.getC_1())+
				Long.parseLong(infoFinan.getC_2())+
				Long.parseLong(infoFinan.getC_3())+
				Long.parseLong(infoFinan.getC_4())+
				Long.parseLong(infoFinan.getC_5())+
				Long.parseLong(infoFinan.getC_6())+
				Long.parseLong(infoFinan.getC_7())+
				Long.parseLong(infoFinan.getC_8())+
				Long.parseLong(infoFinan.getC_9())
				);
	}
	
	private long getIF_E(InformeFinancieroVO infoFinan ){
		return (
				Long.parseLong(infoFinan.getE_1())+
				Long.parseLong(infoFinan.getE_2())+
				Long.parseLong(infoFinan.getE_3())+
				Long.parseLong(infoFinan.getE_4())+
				Long.parseLong(infoFinan.getE_5())
				);
	}
		
	private long getIF_F(InformeFinancieroVO infoFinan ){
		return (
				Long.parseLong(infoFinan.getF_1())+
				Long.parseLong(infoFinan.getF_2())+
				Long.parseLong(infoFinan.getF_3())+
				Long.parseLong(infoFinan.getF_4())+
				Long.parseLong(infoFinan.getF_5())
				);
	}
	
	/**metodo que permite obtener el absoluto del valor.**/
	private String formatAbsoluto(String number){
		long n=Long.parseLong(number); 
		return Long.toString(Math.abs(n));
	}
	
	private String formatAbsoluto(long number){	
		return Long.toString(Math.abs(number));
	}
	
}
