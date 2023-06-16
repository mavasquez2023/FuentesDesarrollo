package cl.laaraucana.simat.entidades;

public class RCP_Subtotales_TotalesVO {

	//metodos para obtener subtotales de bloque 1 parte 1
	public long getSubTotal_RepPrenatal_B1P1(RCP_FondoUnico_1_FondosPensiones rcpB1P1) {

		long st = rcpB1P1.getAfp_Cuprum_B1P1_repPreNatal() + rcpB1P1.getAfp_Habitat_B1P1_repPrenatal() + rcpB1P1.getAfp_PlanVital_B1P1_repPrenatal() + rcpB1P1.getAfp_Provida_B1P1_repPrenatal()
				+ rcpB1P1.getAfp_Capital_B1P1_repPrenatal() + rcpB1P1.getAfp_Modelo_B1P1_repPrenatal() + rcpB1P1.getIps_B1P1_repPrenatal() + rcpB1P1.getCapredena_B1P1_repPrenatal()
				+ rcpB1P1.getDipreca_B1P1_repPrenatal() + rcpB1P1.getEntidEmpleadora_B1P1_repPrenatal();
		return st;
	}

	public long getSubTotal_RepPostnatal_B1P1(RCP_FondoUnico_1_FondosPensiones rcpB1P1) {

		long st = rcpB1P1.getAfp_Cuprum_B1P1_repPostNatal() + rcpB1P1.getAfp_Habitat_B1P1_repPostNatal() + rcpB1P1.getAfp_PlanVital_B1P1_repPostNatal() + rcpB1P1.getAfp_Provida_B1P1_repPostNatal()
				+ rcpB1P1.getAfp_Capital_B1P1_repPostNatal() + rcpB1P1.getAfp_Modelo_B1P1_repPostNatal() + rcpB1P1.getIps_B1P1_repPostNatal() + rcpB1P1.getCapredena_B1P1_repPostNatal()
				+ rcpB1P1.getDipreca_B1P1_repPostNatal() + rcpB1P1.getEntidEmpleadora_B1P1_repPostNatal();

		return st;
	}

	public long getSubTotal__RepParental_B1P1(RCP_FondoUnico_1_FondosPensiones rcpB1P1) {

		long st = rcpB1P1.getAfp_Cuprum_B1P1_repParental() + rcpB1P1.getAfp_Habitat_B1P1_repParental() + rcpB1P1.getAfp_PlanVital_B1P1_repParental() + rcpB1P1.getAfp_Provida_B1P1_repParental()
				+ rcpB1P1.getAfp_Capital_B1P1_repParental() + rcpB1P1.getAfp_Modelo_B1P1_repParental() + rcpB1P1.getIps_B1P1_repParental() + rcpB1P1.getCapredena_B1P1_repParental()
				+ rcpB1P1.getDipreca_B1P1_repParental() + rcpB1P1.getEntidEmpleadora_B1P1_repParental();

		return st;
	}

	public long getSubTotal_PerEnfmen1_B1P1(RCP_FondoUnico_1_FondosPensiones rcpB1P1) {

		long st = rcpB1P1.getAfp_Cuprum_B1P1_perEnfmen1() + rcpB1P1.getAfp_Habitat_B1P1_perEnfmen1() + rcpB1P1.getAfp_PlanVital_B1P1_perEnfmen1() + rcpB1P1.getAfp_Provida_B1P1_perEnfmen1()
				+ rcpB1P1.getAfp_Capital_B1P1_perEnfmen1() + rcpB1P1.getAfp_Modelo_B1P1_perEnfmen1() + rcpB1P1.getIps_B1P1_perEnfmen1() + rcpB1P1.getCapredena_B1P1_perEnfmen1()
				+ rcpB1P1.getDipreca_B1P1_perEnfmen1() + rcpB1P1.getEntidEmpleadora_B1P1_perEnfmen1();

		return st;
	}

	public long getSubTotal_Total_B1P1(RCP_FondoUnico_1_FondosPensiones rcpB1P1) {

		long st = rcpB1P1.getAfp_Cuprum_B1P1_Total() + rcpB1P1.getAfp_Habitat_B1P1_Total() + rcpB1P1.getAfp_PlanVital_B1P1_Total() + rcpB1P1.getAfp_Provida_B1P1_Total()
				+ rcpB1P1.getAfp_Capital_B1P1_Total() + rcpB1P1.getAfp_Modelo_B1P1_Total() + rcpB1P1.getIps_B1P1_Total() + rcpB1P1.getCapredena_B1P1_Total() + rcpB1P1.getDipreca_B1P1_Total()
				+ rcpB1P1.getEntidEmpleadora_B1P1_Total();
		return st;
	}

	//metodos que retornan los subtotales por columna del bloque I, parte II

	public long getSubTotal_RepPrenatal_B1P2(RCP_FondoUnico_2_FondosSalud rcpB1P2) {

		long st = rcpB1P2.getIsp_B1P2_repPrenatal() + rcpB1P2.getCcaf_B1P2_repPrenatal() + rcpB1P2.getIps_B1P2_repPrenatal() + rcpB1P2.getCapredena_B1P2_repPrenatal()
				+ rcpB1P2.getDipreca_B1P2_repPrenatal() + rcpB1P2.getEntidEmpleadora_B1P2_repPrenatal();
		return st;
	}

	public long getSubTotal_RepPostnatal_B1P2(RCP_FondoUnico_2_FondosSalud rcpB1P2) {

		long st = rcpB1P2.getIsp_B1P2_repPostNatal() + rcpB1P2.getCcaf_B1P2_repPostNatal() + rcpB1P2.getIps_B1P2_repPostNatal() + rcpB1P2.getCapredena_B1P2_repPostNatal()
				+ rcpB1P2.getDipreca_B1P2_repPostNatal() + rcpB1P2.getEntidEmpleadora_B1P2_repPostNatal();
		return st;
	}

	public long getSubTotal_RepParental_B1P2(RCP_FondoUnico_2_FondosSalud rcpB1P2) {

		long st = rcpB1P2.getIsp_B1P2_repParental() + rcpB1P2.getCcaf_B1P2_repParental() + rcpB1P2.getIps_B1P2_repParental() + rcpB1P2.getCapredena_B1P2_repParental()
				+ rcpB1P2.getDipreca_B1P2_repParental() + rcpB1P2.getEntidEmpleadora_B1P2_repParental();
		return st;
	}

	public long getSubTotal_PerEnfmen1_B1P2(RCP_FondoUnico_2_FondosSalud rcpB1P2) {

		long st = rcpB1P2.getIsp_B1P2_perEnfmen1() + rcpB1P2.getCcaf_B1P2_perEnfmen1() + rcpB1P2.getIps_B1P2_perEnfmen1() + rcpB1P2.getCapredena_B1P2_perEnfmen1()
				+ rcpB1P2.getDipreca_B1P2_perEnfmen1() + rcpB1P2.getEntidEmpleadora_B1P2_perEnfmen1();
		return st;
	}

	public long getSubTotal_Total_B1P2(RCP_FondoUnico_2_FondosSalud rcpB1P2) {

		long st = rcpB1P2.getIsp_B1P2_Total() + rcpB1P2.getCcaf_B1P2_Total() + rcpB1P2.getIps_B1P2_Total() + rcpB1P2.getCapredena_B1P2_Total() + rcpB1P2.getDipreca_B1P2_Total()
				+ rcpB1P2.getEntidEmpleadora_B1P2_Total();
		return st;
	}

	//	metodos que retornan los subtotales por columna del bloque I, parte III
	public long getsubTotal_RepPrenatal_B1P3(RCP_FondoUnico_3_CotizDI rcpB1P3) {
		long st = rcpB1P3.getIps_B1P3_repPrenatal() + rcpB1P3.getEntidEmpleadora_B1P3_repPrenatal();
		return st;
	}

	public long getsubTotal_RepPostnatal_B1P3(RCP_FondoUnico_3_CotizDI rcpB1P3) {
		long st = rcpB1P3.getIps_B1P3_repPrenatal() + rcpB1P3.getEntidEmpleadora_B1P3_repPrenatal();
		return st;
	}

	public long getsubTotal_RepParental_B1P3(RCP_FondoUnico_3_CotizDI rcpB1P3) {
		long st = rcpB1P3.getEntidEmpleadora_B1P3_repParental() + rcpB1P3.getIps_B1P3_repParental();
		return st;
	}

	public long getsubTotal_PerEnfmen1_B1P3(RCP_FondoUnico_3_CotizDI rcpB1P3) {
		long st = rcpB1P3.getEntidEmpleadora_B1P3_perEnfmen1() + rcpB1P3.getIps_B1P3_perEnfmen1();
		return st;
	}

	public long getsubTotal_Total_B1P3(RCP_FondoUnico_3_CotizDI rcpB1P3) {
		long st = rcpB1P3.getEntidEmpleadora_B1P3_Total() + rcpB1P3.getIps_B1P3_Total();
		return st;
	}

	//metodo que obtiene la suma de fila total del bloque I, en base a los subtotales de columnas de las 3 partes
	public long getTotal_PreNatal_B1(long st1, long st2, long st3) {
		long stt = st1 + st2 + st3;
		return stt;
	}

	public long getTotal_PostNatal_B1(long st1, long st2, long st3) {
		long stt = st1 + st2 + st3;
		return stt;
	}

	public long getTotal_Parental_B1(long st1, long st2, long st3) {
		long stt = st1 + st2 + st3;
		return stt;
	}

	public long getTotal_Enfmen1_B1(long st1, long st2, long st3) {
		long stt = st1 + st2 + st3;
		return stt;
	}

	public long getTotal_Total_B1(long st1, long st2, long st3) {
		long stt = st1 + st2 + st3;
		return stt;
	}

	//	metodos que retornan los subtotales por columna del bloque II
	//parte 1
	public long getSubTotal_PreNatal_B2P1(RCP_CargoBenef_1_FondosPensiones rcpB2P1) {
		long st = rcpB2P1.getAfp_Capital_B2P1_repPrenatal() + rcpB2P1.getAfp_Cuprum_B2P1_repPreNatal() + rcpB2P1.getAfp_Habitat_B2P1_repPrenatal() + rcpB2P1.getAfp_Modelo_B2P1_repPrenatal()
				+ rcpB2P1.getAfp_PlanVital_B2P1_repPrenatal() + rcpB2P1.getAfp_Provida_B2P1_repPrenatal();
		return st;
	}

	public long getSubTotal_PostNatal_B2P1(RCP_CargoBenef_1_FondosPensiones rcpB2P1) {
		long st = rcpB2P1.getAfp_Capital_B2P1_repPostNatal() + rcpB2P1.getAfp_Cuprum_B2P1_repPostNatal() + rcpB2P1.getAfp_Habitat_B2P1_repPostNatal() + rcpB2P1.getAfp_Modelo_B2P1_repPostNatal()
				+ rcpB2P1.getAfp_PlanVital_B2P1_repPostNatal() + rcpB2P1.getAfp_Provida_B2P1_repPostNatal();
		return st;
	}

	public long getSubTotal_Parental_B2P1(RCP_CargoBenef_1_FondosPensiones rcpB2P1) {
		long st = rcpB2P1.getAfp_Capital_B2P1_repParental() + rcpB2P1.getAfp_Cuprum_B2P1_repParental() + rcpB2P1.getAfp_Habitat_B2P1_repParental() + rcpB2P1.getAfp_Modelo_B2P1_repParental()
				+ rcpB2P1.getAfp_PlanVital_B2P1_repParental() + rcpB2P1.getAfp_Provida_B2P1_repParental();
		return st;
	}

	public long getSubTotal_Enfmen1_B2P1(RCP_CargoBenef_1_FondosPensiones rcpB2P1) {
		long st = rcpB2P1.getAfp_Capital_B2P1_perEnfmen1() + rcpB2P1.getAfp_Cuprum_B2P1_perEnfmen1() + rcpB2P1.getAfp_Habitat_B2P1_perEnfmen1() + rcpB2P1.getAfp_Modelo_B2P1_perEnfmen1()
				+ rcpB2P1.getAfp_PlanVital_B2P1_perEnfmen1() + rcpB2P1.getAfp_Provida_B2P1_perEnfmen1();
		return st;
	}

	public long getSubTotal_Total_B2P1(RCP_CargoBenef_1_FondosPensiones rcpB2P1) {
		long st = rcpB2P1.getAfp_Capital_B2P1_Total() + rcpB2P1.getAfp_Cuprum_B2P1_Total() + rcpB2P1.getAfp_Habitat_B2P1_Total() + rcpB2P1.getAfp_Modelo_B2P1_Total()
				+ rcpB2P1.getAfp_PlanVital_B2P1_Total() + rcpB2P1.getAfp_Provida_B2P1_Total();
		return st;
	}

	//metodos para obtener suma de fil subtotal parte 2 bloque II
	public long getSubTotal_PreNatal_B2P2(RCP_CargoBenef_2_FondosSalud rcpB2P2) {
		long st = rcpB2P2.getIps_B2P2_repPrenatal();

		return st;
	}

	public long getSubTotal_PostNatal_B2P2(RCP_CargoBenef_2_FondosSalud rcpB2P2) {
		long st = rcpB2P2.getIps_B2P2_repParental();

		return st;
	}

	public long getSubTotal_Parental_B2P2(RCP_CargoBenef_2_FondosSalud rcpB2P2) {
		long st = rcpB2P2.getIps_B2P2_repParental();

		return st;
	}

	public long getSubTotal_Enfmen1_B2P2(RCP_CargoBenef_2_FondosSalud rcpB2P2) {
		long st = rcpB2P2.getIps_B2P2_perEnfmen1();

		return st;
	}

	public long getSubTotal_Total_B2P2(RCP_CargoBenef_2_FondosSalud rcpB2P2) {
		long st = rcpB2P2.getIps_B2P2_Total();

		return st;
	}

	//	metodos para obtener suma de fil subtotal parte 3 blqoue ii
	public long getSubTotal_PreNatal_B2P3(RCP_CargoBenef_3_CotizSC rcpB2P3) {
		long st = rcpB2P3.getAfp_Capital_B2P3_repPrenatal() + rcpB2P3.getAfp_Cuprum_B2P3_repPreNatal() + rcpB2P3.getAfp_Habitat_B2P3_repPrenatal() + rcpB2P3.getAfp_Modelo_B2P3_repPrenatal()
				+ rcpB2P3.getAfp_PlanVital_B2P3_repPrenatal() + rcpB2P3.getAfp_Provida_B2P3_repPrenatal() + rcpB2P3.getEntidEmpleadora_B2P3_repPrenatal();
		return st;
	}

	public long getSubTotal_PostNatal_B2P3(RCP_CargoBenef_3_CotizSC rcpB2P3) {
		long st = rcpB2P3.getAfp_Capital_B2P3_repPostNatal() + rcpB2P3.getAfp_Cuprum_B2P3_repPostNatal() + rcpB2P3.getAfp_Habitat_B2P3_repPostNatal() + rcpB2P3.getAfp_Modelo_B2P3_repPostNatal()
				+ rcpB2P3.getAfp_PlanVital_B2P3_repPostNatal() + rcpB2P3.getAfp_Provida_B2P3_repPostNatal() + rcpB2P3.getEntidEmpleadora_B2P3_repPostNatal();
		return st;
	}

	public long getSubTotal_Parental_B2P3(RCP_CargoBenef_3_CotizSC rcpB2P3) {
		long st = rcpB2P3.getAfp_Capital_B2P3_repParental() + rcpB2P3.getAfp_Cuprum_B2P3_repParental() + rcpB2P3.getAfp_Habitat_B2P3_repParental() + rcpB2P3.getAfp_Modelo_B2P3_repParental()
				+ rcpB2P3.getAfp_PlanVital_B2P3_repParental() + rcpB2P3.getAfp_Provida_B2P3_repParental() + rcpB2P3.getEntidEmpleadora_B2P3_repParental();
		return st;
	}

	public long getSubTotal_Enfmen1_B2P3(RCP_CargoBenef_3_CotizSC rcpB2P3) {

		long st = rcpB2P3.getAfp_Capital_B2P3_perEnfmen1() + rcpB2P3.getAfp_Cuprum_B2P3_perEnfmen1() + rcpB2P3.getAfp_Habitat_B2P3_perEnfmen1() + rcpB2P3.getAfp_Modelo_B2P3_perEnfmen1()
				+ rcpB2P3.getAfp_PlanVital_B2P3_perEnfmen1() + rcpB2P3.getAfp_Provida_B2P3_perEnfmen1() + rcpB2P3.getEntidEmpleadora_B2P3_perEnfmen1();
		return st;
	}

	public long getSubTotal_Total_B2P3(RCP_CargoBenef_3_CotizSC rcpB2P3) {

		long st = rcpB2P3.getAfp_Capital_B2P3_Total() + rcpB2P3.getAfp_Cuprum_B2P3_Total() + rcpB2P3.getAfp_Habitat_B2P3_Total() + rcpB2P3.getAfp_Modelo_B2P3_Total()
				+ rcpB2P3.getAfp_PlanVital_B2P3_Total() + rcpB2P3.getAfp_Provida_B2P3_Total() + rcpB2P3.getEntidEmpleadora_B2P3_Total();
		return st;
	}

	//	metodo que obtiene la suma de fila total del bloque II, en base a los subtotales de columnas de las 3 partes
	public long getTotal_PreNatal_B2(long st1, long st2, long st3) {
		long stt = st1 + st2 + st3;
		return stt;
	}

	public long getTotal_PostNatal_B2(long st1, long st2, long st3) {
		long stt = st1 + st2 + st3;
		return stt;
	}

	public long getTotal_Parental_B2(long st1, long st2, long st3) {
		long stt = st1 + st2 + st3;
		return stt;
	}

	public long getTotal_Enfmen1_B2(long st1, long st2, long st3) {
		long stt = st1 + st2 + st3;
		return stt;
	}

	public long getTotal_Total_B2(long st1, long st2, long st3) {
		long stt = st1 + st2 + st3;
		return stt;
	}

}
