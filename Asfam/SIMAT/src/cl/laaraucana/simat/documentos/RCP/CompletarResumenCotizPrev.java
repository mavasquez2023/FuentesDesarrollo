package cl.laaraucana.simat.documentos.RCP;

import cl.laaraucana.simat.entidades.QueryIdVO;
import cl.laaraucana.simat.entidades.RCP_CargoBenef_1_FondosPensiones;
import cl.laaraucana.simat.entidades.RCP_CargoBenef_2_FondosSalud;
import cl.laaraucana.simat.entidades.RCP_CargoBenef_3_CotizSC;
import cl.laaraucana.simat.entidades.RCP_CargoBenef_Total;
import cl.laaraucana.simat.entidades.RCP_FondoUnico_1_FondosPensiones;
import cl.laaraucana.simat.entidades.RCP_FondoUnico_2_FondosSalud;
import cl.laaraucana.simat.entidades.RCP_FondoUnico_3_CotizDI;
import cl.laaraucana.simat.entidades.RCP_FondoUnico_Total;
import cl.laaraucana.simat.entidades.RCP_Subtotales_TotalesVO;
import cl.laaraucana.simat.entidades.SumVO;
import cl.laaraucana.simat.mannagerDB2.QueryIdMannager;
import cl.laaraucana.simat.mannagerDB2.Sum_RCP_B1P1_Mannager;
import cl.laaraucana.simat.mannagerDB2.Sum_RCP_B1P2_Mannager;
import cl.laaraucana.simat.mannagerDB2.Sum_RCP_B2P3_Mannager;

/*
 * clase que completa los valores del cuadro resumen de cotizaciones previsionales
 * */

public class CompletarResumenCotizPrev {

	//completar valores por fila/columna
	//bloque 1 parte 1
	public RCP_FondoUnico_1_FondosPensiones getValores_B1P1() throws Exception {

		RCP_FondoUnico_1_FondosPensiones rcpB1P1 = new RCP_FondoUnico_1_FondosPensiones(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		Sum_RCP_B1P1_Mannager B1P1_mgr = new Sum_RCP_B1P1_Mannager();
		SumVO sumSQL = new SumVO();
		System.out.print("* * * * ** * * * ** * b1p1, columna prenatal");
		//columna prenatal
		try {
			sumSQL = B1P1_mgr.getSumB1P1_AFP_CUPRUM_prenatal(sumSQL);
			rcpB1P1.setAfp_Cuprum_B1P1_repPreNatal(sumSQL.getResultadoSum());
		} catch (Exception ex) {
			System.out.print("* * * * ** * * * ** * err: " + ex);
		}

		System.out.print("* * * * ** * * * ** * b1p1, columna prenatal");
		sumSQL = B1P1_mgr.getSumB1P1_AFP_HABITAT_prenatal(sumSQL);
		rcpB1P1.setAfp_Habitat_B1P1_repPrenatal(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_PLANVITAL_prenatal(sumSQL);
		rcpB1P1.setAfp_PlanVital_B1P1_repPrenatal(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_PROVIDA_prenatal(sumSQL);
		rcpB1P1.setAfp_Provida_B1P1_repPrenatal(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_CAPITAL_prenatal(sumSQL);
		rcpB1P1.setAfp_Capital_B1P1_repPrenatal(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_MODELO_prenatal(sumSQL);
		rcpB1P1.setAfp_Modelo_B1P1_repPrenatal(sumSQL.getResultadoSum());

		//		siguientes valores siempre en cero
		rcpB1P1.setIps_B1P1_repPrenatal(0);
		rcpB1P1.setCapredena_B1P1_repPrenatal(0);
		rcpB1P1.setDipreca_B1P1_repPrenatal(0);

		sumSQL = B1P1_mgr.getSumB1P1_EntidadEmpleadora_prenatal(sumSQL);
		rcpB1P1.setEntidEmpleadora_B1P1_repPrenatal(sumSQL.getResultadoSum());

		System.out.print("* * * * ** * * * ** * b1p1, columna postnatal");
		//columna postnatal
		sumSQL = B1P1_mgr.getSumB1P1_AFP_CUPRUM_postNatal(sumSQL);
		rcpB1P1.setAfp_Cuprum_B1P1_repPostNatal(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_HABITAT_postNatal(sumSQL);
		rcpB1P1.setAfp_Habitat_B1P1_repPostNatal(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_PLANVITAL_postNatal(sumSQL);
		rcpB1P1.setAfp_PlanVital_B1P1_repPostNatal(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_PROVIDA_postNatal(sumSQL);
		rcpB1P1.setAfp_Provida_B1P1_repPostNatal(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_CAPITAL_postNatal(sumSQL);
		rcpB1P1.setAfp_Capital_B1P1_repPostNatal(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_MODELO_postNatal(sumSQL);
		rcpB1P1.setAfp_Modelo_B1P1_repPostNatal(sumSQL.getResultadoSum());

		//siguientes valores siempre en cero
		rcpB1P1.setIps_B1P1_repPostNatal(0);
		rcpB1P1.setCapredena_B1P1_repPostNatal(0);
		rcpB1P1.setDipreca_B1P1_repPostNatal(0);

		sumSQL = B1P1_mgr.getSumB1P1_EntidadEmpleadora_postNatal(sumSQL);
		rcpB1P1.setEntidEmpleadora_B1P1_repPostNatal(sumSQL.getResultadoSum());

		System.out.print("* * * * ** * * * ** * b1p1, columna parental");
		//columna parental
		sumSQL = B1P1_mgr.getSumB1P1_AFP_CUPRUM_Parental(sumSQL);
		rcpB1P1.setAfp_Cuprum_B1P1_repParental(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_HABITAT_Parental(sumSQL);
		rcpB1P1.setAfp_Habitat_B1P1_repParental(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_PLANVITAL_Parental(sumSQL);
		rcpB1P1.setAfp_PlanVital_B1P1_repParental(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_PROVIDA_Parental(sumSQL);
		rcpB1P1.setAfp_Provida_B1P1_repParental(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_CAPITAL_Parental(sumSQL);
		rcpB1P1.setAfp_Capital_B1P1_repParental(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_MODELO_Parental(sumSQL);
		rcpB1P1.setAfp_Modelo_B1P1_repParental(sumSQL.getResultadoSum());

		//siguientes valores siempre en cero
		rcpB1P1.setIps_B1P1_repParental(0);
		rcpB1P1.setCapredena_B1P1_repParental(0);
		rcpB1P1.setDipreca_B1P1_repParental(0);

		sumSQL = B1P1_mgr.getSumB1P1_EntidadEmpleadora_Parental(sumSQL);
		rcpB1P1.setEntidEmpleadora_B1P1_repParental(sumSQL.getResultadoSum());

		System.out.print("* * * * ** * * * ** * b1p1, columna EnfGravNM");

		//		columna EnfGravNM
		sumSQL = B1P1_mgr.getSumB1P1_AFP_CUPRUM_EnfGraveNM(sumSQL);
		rcpB1P1.setAfp_Cuprum_B1P1_perEnfmen1(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_HABITAT_EnfGraveNM(sumSQL);
		rcpB1P1.setAfp_Habitat_B1P1_perEnfmen1(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_PLANVITAL_EnfGraveNM(sumSQL);
		rcpB1P1.setAfp_PlanVital_B1P1_perEnfmen1(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_PROVIDA_EnfGraveNM(sumSQL);
		rcpB1P1.setAfp_Provida_B1P1_perEnfmen1(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_CAPITAL_EnfGraveNM(sumSQL);
		rcpB1P1.setAfp_Capital_B1P1_perEnfmen1(sumSQL.getResultadoSum());

		sumSQL = B1P1_mgr.getSumB1P1_AFP_MODELO_EnfGraveNM(sumSQL);
		rcpB1P1.setAfp_Modelo_B1P1_perEnfmen1(sumSQL.getResultadoSum());

		//siguientes valores siempre en cero
		rcpB1P1.setIps_B1P1_perEnfmen1(0);
		rcpB1P1.setCapredena_B1P1_perEnfmen1(0);
		rcpB1P1.setDipreca_B1P1_perEnfmen1(0);

		sumSQL = B1P1_mgr.getSumB1P1_EntidadEmpleadora_EnfGraveNM(sumSQL);
		rcpB1P1.setEntidEmpleadora_B1P1_perEnfmen1(sumSQL.getResultadoSum());

		//asignar columna total 
		rcpB1P1.setAfp_Cuprum_B1P1_Total(rcpB1P1.getAfp_Cuprum_B1P1_repPreNatal() + rcpB1P1.getAfp_Cuprum_B1P1_repPostNatal() + rcpB1P1.getAfp_Cuprum_B1P1_repParental()
				+ rcpB1P1.getAfp_Cuprum_B1P1_perEnfmen1());
		rcpB1P1.setAfp_Habitat_B1P1_Total(rcpB1P1.getAfp_Habitat_B1P1_repPrenatal() + rcpB1P1.getAfp_Habitat_B1P1_repPostNatal() + rcpB1P1.getAfp_Habitat_B1P1_repParental()
				+ rcpB1P1.getAfp_Habitat_B1P1_perEnfmen1());
		rcpB1P1.setAfp_PlanVital_B1P1_Total(rcpB1P1.getAfp_PlanVital_B1P1_repPrenatal() + rcpB1P1.getAfp_PlanVital_B1P1_repPostNatal() + rcpB1P1.getAfp_PlanVital_B1P1_repParental()
				+ rcpB1P1.getAfp_PlanVital_B1P1_perEnfmen1());
		rcpB1P1.setAfp_Provida_B1P1_Total(rcpB1P1.getAfp_Provida_B1P1_repPrenatal() + rcpB1P1.getAfp_Provida_B1P1_repPostNatal() + rcpB1P1.getAfp_Provida_B1P1_repParental()
				+ rcpB1P1.getAfp_Provida_B1P1_perEnfmen1());
		rcpB1P1.setAfp_Capital_B1P1_Total(rcpB1P1.getAfp_Capital_B1P1_repPrenatal() + rcpB1P1.getAfp_Capital_B1P1_repPostNatal() + rcpB1P1.getAfp_Capital_B1P1_repParental()
				+ rcpB1P1.getAfp_Capital_B1P1_perEnfmen1());
		rcpB1P1.setAfp_Modelo_B1P1_Total(rcpB1P1.getAfp_Modelo_B1P1_repPrenatal() + rcpB1P1.getAfp_Modelo_B1P1_repPostNatal() + rcpB1P1.getAfp_Modelo_B1P1_repParental()
				+ rcpB1P1.getAfp_Modelo_B1P1_perEnfmen1());

		//valores siempre en cero
		rcpB1P1.setIps_B1P1_Total(0);
		rcpB1P1.setCapredena_B1P1_Total(0);
		rcpB1P1.setDipreca_B1P1_Total(0);

		rcpB1P1.setEntidEmpleadora_B1P1_Total(rcpB1P1.getEntidEmpleadora_B1P1_repPrenatal() + rcpB1P1.getEntidEmpleadora_B1P1_repPostNatal() + rcpB1P1.getEntidEmpleadora_B1P1_repParental()
				+ rcpB1P1.getEntidEmpleadora_B1P1_perEnfmen1());

		//obtener fila subtotales
		RCP_Subtotales_TotalesVO rcpST = new RCP_Subtotales_TotalesVO();

		rcpB1P1.setSubTotal_B1P1_repPrenatal(rcpST.getSubTotal_RepPrenatal_B1P1(rcpB1P1));
		rcpB1P1.setSubTotal_B1P1_repPostNatal(rcpST.getSubTotal_RepPostnatal_B1P1(rcpB1P1));
		rcpB1P1.setSubTotal_B1P1_repParental(rcpST.getSubTotal__RepParental_B1P1(rcpB1P1));
		rcpB1P1.setSubTotal_B1P1_perEnfmen1(rcpST.getSubTotal_PerEnfmen1_B1P1(rcpB1P1));
		rcpB1P1.setSubTotal_B1P1_Total(rcpST.getSubTotal_Total_B1P1(rcpB1P1));

		return rcpB1P1;
	}

	public RCP_FondoUnico_2_FondosSalud getValores_B1P2() throws Exception {
		RCP_FondoUnico_2_FondosSalud rcpB1P2 = new RCP_FondoUnico_2_FondosSalud(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		Sum_RCP_B1P2_Mannager B1P2_mgr = new Sum_RCP_B1P2_Mannager();
		SumVO sumSQL = new SumVO();

		sumSQL = B1P2_mgr.getSumB1P2_CCAF_prenatal(sumSQL);
		//rcpB1P2.setCcaf_B1P2_repPrenatal(this.getOP_MontoSalud_CCAF(sumSQL.getResultadoSum()));
		rcpB1P2.setCcaf_B1P2_repPrenatal(sumSQL.getResultadoSum());

		sumSQL = B1P2_mgr.getSumB1P2_ISP_prenatal(sumSQL);
		//rcpB1P2.setIsp_B1P2_repPrenatal(this.getOP_MontoSalud_ISP(sumSQL.getResultadoSum()));
		rcpB1P2.setIsp_B1P2_repPrenatal(sumSQL.getResultadoSum());

		sumSQL = B1P2_mgr.getSumB1P2_CCAF_postNatal(sumSQL);
		//rcpB1P2.setCcaf_B1P2_repPostNatal(this.getOP_MontoSalud_CCAF(sumSQL.getResultadoSum()));
		rcpB1P2.setCcaf_B1P2_repPostNatal(sumSQL.getResultadoSum());

		sumSQL = B1P2_mgr.getSumB1P2_ISP_postNatal(sumSQL);
		//rcpB1P2.setIsp_B1P2_repPostNatal(this.getOP_MontoSalud_ISP(sumSQL.getResultadoSum()));
		rcpB1P2.setIsp_B1P2_repPostNatal(sumSQL.getResultadoSum());

		sumSQL = B1P2_mgr.getSumB1P2_CCAF_EnfGraveNM(sumSQL);
		//rcpB1P2.setCcaf_B1P2_perEnfmen1(this.getOP_MontoSalud_CCAF(sumSQL.getResultadoSum()));
		rcpB1P2.setCcaf_B1P2_perEnfmen1(sumSQL.getResultadoSum());

		sumSQL = B1P2_mgr.getSumB1P2_ISP_EnfGraveNM(sumSQL);
		//rcpB1P2.setIsp_B1P2_perEnfmen1(this.getOP_MontoSalud_ISP(sumSQL.getResultadoSum()));
		rcpB1P2.setIsp_B1P2_perEnfmen1(sumSQL.getResultadoSum());

		sumSQL = B1P2_mgr.getSumB1P2_CCAF_Parental(sumSQL);
		//rcpB1P2.setCcaf_B1P2_repParental(this.getOP_MontoSalud_CCAF(sumSQL.getResultadoSum()));
		rcpB1P2.setCcaf_B1P2_repParental(sumSQL.getResultadoSum());

		sumSQL = B1P2_mgr.getSumB1P2_ISP_Parental(sumSQL);
		//rcpB1P2.setIsp_B1P2_repParental(this.getOP_MontoSalud_ISP(sumSQL.getResultadoSum()));
		rcpB1P2.setIsp_B1P2_repParental(sumSQL.getResultadoSum());

		sumSQL = B1P2_mgr.getSumB1P2_EntidadEmpleadora_Parental(sumSQL);
		rcpB1P2.setEntidEmpleadora_B1P2_repParental(sumSQL.getResultadoSum());

		sumSQL = B1P2_mgr.getSumB1P2_EntidadEmpleadora_prenatal(sumSQL);
		rcpB1P2.setEntidEmpleadora_B1P2_repPrenatal(sumSQL.getResultadoSum());

		sumSQL = B1P2_mgr.getSumB1P2_EntidadEmpleadora_postNatal(sumSQL);
		rcpB1P2.setEntidEmpleadora_B1P2_repPostNatal(sumSQL.getResultadoSum());

		sumSQL = B1P2_mgr.getSumB1P2_EntidadEmpleadora_EnfGraveNM(sumSQL);
		rcpB1P2.setEntidEmpleadora_B1P2_perEnfmen1(sumSQL.getResultadoSum());

		//valores siempre en cero

		rcpB1P2.setIps_B1P2_repPrenatal(0);
		rcpB1P2.setIps_B1P2_repPostNatal(0);
		rcpB1P2.setIps_B1P2_repParental(0);
		rcpB1P2.setIps_B1P2_perEnfmen1(0);

		rcpB1P2.setCapredena_B1P2_repPrenatal(0);
		rcpB1P2.setCapredena_B1P2_repPostNatal(0);
		rcpB1P2.setIps_B1P2_repParental(0);
		rcpB1P2.setCapredena_B1P2_perEnfmen1(0);

		rcpB1P2.setDipreca_B1P2_repPrenatal(0);
		rcpB1P2.setDipreca_B1P2_repPostNatal(0);
		rcpB1P2.setDipreca_B1P2_repParental(0);
		rcpB1P2.setDipreca_B1P2_perEnfmen1(0);

		rcpB1P2.setIps_B1P2_Total(0);
		rcpB1P2.setCapredena_B1P2_Total(0);
		rcpB1P2.setDipreca_B1P2_Total(0);

		//Asignar columna total
		rcpB1P2.setCcaf_B1P2_Total(rcpB1P2.getCcaf_B1P2_perEnfmen1() + rcpB1P2.getCcaf_B1P2_repParental() + rcpB1P2.getCcaf_B1P2_repPostNatal() + rcpB1P2.getCcaf_B1P2_repPrenatal());

		rcpB1P2.setEntidEmpleadora_B1P2_Total(rcpB1P2.getEntidEmpleadora_B1P2_perEnfmen1() + rcpB1P2.getEntidEmpleadora_B1P2_repParental() + rcpB1P2.getEntidEmpleadora_B1P2_repPostNatal()
				+ rcpB1P2.getEntidEmpleadora_B1P2_repPrenatal());

		rcpB1P2.setIsp_B1P2_Total(rcpB1P2.getIsp_B1P2_perEnfmen1() + rcpB1P2.getIsp_B1P2_repParental() + rcpB1P2.getIsp_B1P2_repPostNatal() + rcpB1P2.getIsp_B1P2_repPrenatal());

		//asignar fila Subtotal
		RCP_Subtotales_TotalesVO rcpST = new RCP_Subtotales_TotalesVO();

		rcpB1P2.setSubTotal_B1P2_perEnfmen1(rcpST.getSubTotal_PerEnfmen1_B1P2(rcpB1P2));
		rcpB1P2.setSubTotal_B1P2_repParental(rcpST.getSubTotal_RepParental_B1P2(rcpB1P2));
		rcpB1P2.setSubTotal_B1P2_repPostNatal(rcpST.getSubTotal_RepPostnatal_B1P2(rcpB1P2));
		rcpB1P2.setSubTotal_B1P2_repPrenatal(rcpST.getSubTotal_RepPrenatal_B1P2(rcpB1P2));
		rcpB1P2.setSubTotal_B1P2_Total(rcpST.getSubTotal_Total_B1P2(rcpB1P2));

		return rcpB1P2;

	}

	public RCP_FondoUnico_3_CotizDI getValores_B1P3() throws Exception {
		//bloque I parte 3		
		//valores siempre en cero, en instancia.
		RCP_FondoUnico_3_CotizDI rcpB1P3 = new RCP_FondoUnico_3_CotizDI(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		return rcpB1P3;

	}

	public RCP_CargoBenef_1_FondosPensiones getValores_B2P1() throws Exception {

		RCP_CargoBenef_1_FondosPensiones rcp_B2P1 = new RCP_CargoBenef_1_FondosPensiones(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		return rcp_B2P1;
	}

	public RCP_CargoBenef_2_FondosSalud getValores_B2P2() throws Exception {

		RCP_CargoBenef_2_FondosSalud rcp_B2P2 = new RCP_CargoBenef_2_FondosSalud(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		return rcp_B2P2;
	}

	public RCP_CargoBenef_3_CotizSC getValores_B2P3() throws Exception {

		RCP_CargoBenef_3_CotizSC rcpB2P3 = new RCP_CargoBenef_3_CotizSC(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		Sum_RCP_B2P3_Mannager B2P3_mgr = new Sum_RCP_B2P3_Mannager();
		SumVO sumSQL = new SumVO();
		//columna prenatal
		sumSQL = B2P3_mgr.getSumB2P3_AFP_CUPRUM_prenatal(sumSQL);
		rcpB2P3.setAfp_Cuprum_B2P3_repPreNatal(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_HABITAT_prenatal(sumSQL);
		rcpB2P3.setAfp_Habitat_B2P3_repPrenatal(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_PLANVITAL_prenatal(sumSQL);
		rcpB2P3.setAfp_PlanVital_B2P3_repPrenatal(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_PROVIDA_prenatal(sumSQL);
		rcpB2P3.setAfp_Provida_B2P3_repPrenatal(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_CAPITAL_prenatal(sumSQL);
		rcpB2P3.setAfp_Capital_B2P3_repPrenatal(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_MODELO_prenatal(sumSQL);
		rcpB2P3.setAfp_Modelo_B2P3_repPrenatal(sumSQL.getResultadoSum());

		//columna postnatal
		sumSQL = B2P3_mgr.getSumB2P3_AFP_CUPRUM_postNatal(sumSQL);
		rcpB2P3.setAfp_Cuprum_B2P3_repPostNatal(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_HABITAT_postNatal(sumSQL);
		rcpB2P3.setAfp_Habitat_B2P3_repPostNatal(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_PLANVITAL_postNatal(sumSQL);
		rcpB2P3.setAfp_PlanVital_B2P3_repPostNatal(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_PROVIDA_postNatal(sumSQL);
		rcpB2P3.setAfp_Provida_B2P3_repPostNatal(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_CAPITAL_postNatal(sumSQL);
		rcpB2P3.setAfp_Capital_B2P3_repPostNatal(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_MODELO_postNatal(sumSQL);
		rcpB2P3.setAfp_Modelo_B2P3_repPostNatal(sumSQL.getResultadoSum());

		//columna parental
		sumSQL = B2P3_mgr.getSumB2P3_AFP_CUPRUM_Parental(sumSQL);
		rcpB2P3.setAfp_Cuprum_B2P3_repParental(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_HABITAT_Parental(sumSQL);
		rcpB2P3.setAfp_Habitat_B2P3_repParental(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_PLANVITAL_Parental(sumSQL);
		rcpB2P3.setAfp_PlanVital_B2P3_repParental(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_PROVIDA_Parental(sumSQL);
		rcpB2P3.setAfp_Provida_B2P3_repParental(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_CAPITAL_Parental(sumSQL);
		rcpB2P3.setAfp_Capital_B2P3_repParental(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_MODELO_Parental(sumSQL);
		rcpB2P3.setAfp_Modelo_B2P3_repParental(sumSQL.getResultadoSum());

		//columna EnfGravNM

		sumSQL = B2P3_mgr.getSumB2P3_AFP_CUPRUM_EnfGraveNM(sumSQL);
		rcpB2P3.setAfp_Cuprum_B2P3_perEnfmen1(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_HABITAT_EnfGraveNM(sumSQL);
		rcpB2P3.setAfp_Habitat_B2P3_perEnfmen1(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_PLANVITAL_EnfGraveNM(sumSQL);
		rcpB2P3.setAfp_PlanVital_B2P3_perEnfmen1(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_PROVIDA_EnfGraveNM(sumSQL);
		rcpB2P3.setAfp_Provida_B2P3_perEnfmen1(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_CAPITAL_EnfGraveNM(sumSQL);
		rcpB2P3.setAfp_Capital_B2P3_perEnfmen1(sumSQL.getResultadoSum());

		sumSQL = B2P3_mgr.getSumB2P3_AFP_MODELO_EnfGraveNM(sumSQL);
		rcpB2P3.setAfp_Modelo_B2P3_perEnfmen1(sumSQL.getResultadoSum());

		//valores siempre en cero
		rcpB2P3.setEntidEmpleadora_B2P3_repPrenatal(0);
		rcpB2P3.setEntidEmpleadora_B2P3_repPostNatal(0);
		rcpB2P3.setEntidEmpleadora_B2P3_repPostNatal(0);
		rcpB2P3.setEntidEmpleadora_B2P3_perEnfmen1(0);
		rcpB2P3.setEntidEmpleadora_B2P3_Total(0);

		//obtener columna total
		rcpB2P3.setAfp_Cuprum_B2P3_Total(rcpB2P3.getAfp_Cuprum_B2P3_perEnfmen1() + rcpB2P3.getAfp_Cuprum_B2P3_repParental() + rcpB2P3.getAfp_Cuprum_B2P3_repPostNatal()
				+ rcpB2P3.getAfp_Cuprum_B2P3_repPreNatal());

		rcpB2P3.setAfp_Habitat_B2P3_Total(rcpB2P3.getAfp_Habitat_B2P3_perEnfmen1() + rcpB2P3.getAfp_Habitat_B2P3_repParental() + rcpB2P3.getAfp_Habitat_B2P3_repPostNatal()
				+ rcpB2P3.getAfp_Habitat_B2P3_repPrenatal());

		rcpB2P3.setAfp_PlanVital_B2P3_Total(rcpB2P3.getAfp_PlanVital_B2P3_perEnfmen1() + rcpB2P3.getAfp_PlanVital_B2P3_repParental() + rcpB2P3.getAfp_PlanVital_B2P3_repPostNatal()
				+ rcpB2P3.getAfp_PlanVital_B2P3_repPrenatal());

		rcpB2P3.setAfp_Provida_B2P3_Total(rcpB2P3.getAfp_Provida_B2P3_perEnfmen1() + rcpB2P3.getAfp_Provida_B2P3_repParental() + rcpB2P3.getAfp_Provida_B2P3_repPostNatal()
				+ rcpB2P3.getAfp_Provida_B2P3_repPrenatal());

		rcpB2P3.setAfp_Capital_B2P3_Total(rcpB2P3.getAfp_Capital_B2P3_perEnfmen1() + rcpB2P3.getAfp_Capital_B2P3_repParental() + rcpB2P3.getAfp_Capital_B2P3_repPostNatal()
				+ rcpB2P3.getAfp_Capital_B2P3_repPrenatal());

		rcpB2P3.setAfp_Modelo_B2P3_Total(rcpB2P3.getAfp_Modelo_B2P3_perEnfmen1() + rcpB2P3.getAfp_Modelo_B2P3_repParental() + rcpB2P3.getAfp_Modelo_B2P3_repPostNatal()
				+ rcpB2P3.getAfp_Modelo_B2P3_repPrenatal());

		//obtener fila Subtotales
		RCP_Subtotales_TotalesVO rcpST = new RCP_Subtotales_TotalesVO();

		rcpB2P3.setSubTotal_B2P3_perEnfmen1(rcpST.getSubTotal_Enfmen1_B2P3(rcpB2P3));
		rcpB2P3.setSubTotal_B2P3_repParental(rcpST.getSubTotal_Parental_B2P3(rcpB2P3));
		rcpB2P3.setSubTotal_B2P3_repPostNatal(rcpST.getSubTotal_PostNatal_B2P3(rcpB2P3));
		rcpB2P3.setSubTotal_B2P3_repPrenatal(rcpST.getSubTotal_PreNatal_B2P3(rcpB2P3));
		rcpB2P3.setSubTotal_B2P3_Total(rcpST.getSubTotal_Total_B2P3(rcpB2P3));

		return rcpB2P3;
	}

	public RCP_FondoUnico_Total getValores_TotalB1(RCP_FondoUnico_1_FondosPensiones fupB1P1, RCP_FondoUnico_2_FondosSalud fupB1P2, RCP_FondoUnico_3_CotizDI fupB1P3) throws Exception {
		RCP_FondoUnico_Total rcp_B1_Total = new RCP_FondoUnico_Total(0, 0, 0, 0, 0);
		RCP_Subtotales_TotalesVO rcpST = new RCP_Subtotales_TotalesVO();
		rcp_B1_Total.setTotal_B1_parental(rcpST.getTotal_Parental_B1(fupB1P1.getSubTotal_B1P1_repParental(), fupB1P2.getSubTotal_B1P2_repParental(), fupB1P3.getSubTotal_B1P3_repParental()));
		rcp_B1_Total.setTotal_B1_perEnfmen1(rcpST.getTotal_Enfmen1_B1(fupB1P1.getSubTotal_B1P1_perEnfmen1(), fupB1P2.getSubTotal_B1P2_perEnfmen1(), fupB1P3.getSubTotal_B1P3_perEnfmen1()));
		rcp_B1_Total.setTotal_B1_postnatal(rcpST.getTotal_PostNatal_B1(fupB1P1.getSubTotal_B1P1_repPostNatal(), fupB1P2.getSubTotal_B1P2_repPostNatal(), fupB1P3.getSubTotal_B1P3_repPostNatal()));
		rcp_B1_Total.setTotal_B1_prenatal(rcpST.getTotal_PreNatal_B1(fupB1P1.getSubTotal_B1P1_repPrenatal(), fupB1P2.getSubTotal_B1P2_repPrenatal(), fupB1P3.getSubTotal_B1P3_repPrenatal()));
		rcp_B1_Total.setTotal_B1_Total(rcpST.getTotal_Total_B1(fupB1P1.getSubTotal_B1P1_Total(), fupB1P2.getSubTotal_B1P2_Total(), fupB1P3.getSubTotal_B1P3_Total()));
		return rcp_B1_Total;

	}

	public RCP_CargoBenef_Total getValores_TotalB2(RCP_CargoBenef_1_FondosPensiones fupB2P1, RCP_CargoBenef_2_FondosSalud fupB2P2, RCP_CargoBenef_3_CotizSC fupB2P3) throws Exception {
		RCP_CargoBenef_Total rcp_B2_Total = new RCP_CargoBenef_Total(0, 0, 0, 0, 0);
		RCP_Subtotales_TotalesVO rcpST = new RCP_Subtotales_TotalesVO();

		rcp_B2_Total.setTotal_B2_parental(rcpST.getTotal_Parental_B2(fupB2P1.getSubTotal_B2P1_repParental(), fupB2P2.getSubTotal_B2P2_repParental(), fupB2P3.getSubTotal_B2P3_repParental()));

		rcp_B2_Total.setTotal_B2_perEnfmen1(rcpST.getTotal_Enfmen1_B2(fupB2P1.getSubTotal_B2P1_perEnfmen1(), fupB2P2.getSubTotal_B2P2_perEnfmen1(), fupB2P3.getSubTotal_B2P3_perEnfmen1()));

		rcp_B2_Total.setTotal_B2_postnatal(rcpST.getTotal_PostNatal_B2(fupB2P1.getSubTotal_B2P1_repPostNatal(), fupB2P2.getSubTotal_B2P2_repPostNatal(), fupB2P3.getSubTotal_B2P3_repPostNatal()));

		rcp_B2_Total.setTotal_B2_prenatal(rcpST.getTotal_PreNatal_B2(fupB2P1.getSubTotal_B2P1_repPrenatal(), fupB2P2.getSubTotal_B2P2_repPrenatal(), fupB2P3.getSubTotal_B2P3_repPrenatal()));

		rcp_B2_Total.setTotal_B2_Total(rcpST.getTotal_Total_B2(fupB2P1.getSubTotal_B2P1_Total(), fupB2P2.getSubTotal_B2P2_Total(), fupB2P3.getSubTotal_B2P3_Total()));

		return rcp_B2_Total;
	}

	//metodo para diferenciar  entidad previsional
	public int evaluarEntidadPrevisional(int ep) {
		//retornara 1 para cajas de compensacion
		switch (ep) {
		case 40103://cuprum
			ep = 1;
			break;
		case 40105://habitat
			ep = 2;
			break;
		case 40107://plan vital
			ep = 3;
			break;
		case 40109://provida
			ep = 4;
			break;
		case 40113://capital
			ep = 5;
			break;
		case 40114://modelo
			ep = 6;
			break;
		case 30100://instituto prevision social
			ep = 7;
			break;
		case 30501://capredena
			ep = 8;
			break;
		case 30502://dipreca
			ep = 9;
			break;
		/*
		case 99999://entidad empleadora
		ep=10;
		break;
		*/
		default:
			ep = 0;
			break;
		}
		return ep;
	}

	//se inabilita por agregacion a query	
	//metodo que hace el calculo del porcentaje del montoSalud que se debe asignar a ccaf
	/*	
	public int getOP_MontoSalud_CCAF(long montoSalud){
		double res=0;
		try{
		 res=(montoSalud*(0.6/7));
		}catch(Exception e){
			e.printStackTrace();
		}
		return (this.FormatoTruncateDecimal(res));
	}*/
	//se inabilita por agregacion a query
	//metodo que hace el calculo del porcentaje del montoSalud que se debe asignar a Isp
	/*
	public int getOP_MontoSalud_ISP(long montoSalud){
		
		double res=(montoSalud-(montoSalud*(0.6/7)));
		
		return (this.FormatoTruncateDecimal(res));
	}
	*/
	//metodo que trunca los decimales del valor recibido
	public int FormatoTruncateDecimal(double valor) {
		return ((int) Math.floor(valor));
	}

	//	metodo que obtiene el minimo y maximo id de una tabla consultada.
	public QueryIdVO obtenerMinMaxId(String tabla) throws Exception {
		QueryIdVO qid = new QueryIdVO();
		qid.setTabla(tabla);
		QueryIdMannager mquid = new QueryIdMannager();
		qid = mquid.getMinMaxId(qid);
		return qid;
	}

}
