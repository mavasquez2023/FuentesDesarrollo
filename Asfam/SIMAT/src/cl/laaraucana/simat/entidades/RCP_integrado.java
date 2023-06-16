package cl.laaraucana.simat.entidades;

import cl.laaraucana.simat.utiles.ManejoFormatoNumeros;

public class RCP_integrado {

	private String periodo;
	private String entidad;

	private RCP_FondoUnico_1_FondosPensiones rcp_FondoUnico_1_FondosPensiones;
	private RCP_FondoUnico_2_FondosSalud rcp_FondoUnico_2_FondosSalud;
	private RCP_FondoUnico_3_CotizDI rcp_FondoUnico_3_CotizDI;
	private RCP_FondoUnico_Total rcp_FondoUnico_Total;

	private RCP_CargoBenef_1_FondosPensiones rcp_CargoBenef_1_FondosPensiones;
	private RCP_CargoBenef_2_FondosSalud rcp_CargoBenef_2_FondosSalud;
	private RCP_CargoBenef_3_CotizSC rcp_CargoBenef_3_CotizSC;
	private RCP_CargoBenef_Total rcp_CargoBenef_Total;

	public RCP_integrado() {

	}

	public RCP_integrado(String periodo, String entidad, RCP_CargoBenef_1_FondosPensiones rcp_CargoBenef_1_FondosPensiones, RCP_CargoBenef_2_FondosSalud rcp_CargoBenef_2_FondosSalud,
			RCP_CargoBenef_3_CotizSC rcp_CargoBenef_3_CotizSC, RCP_FondoUnico_1_FondosPensiones rcp_FondoUnico_1_FondosPensiones, RCP_FondoUnico_2_FondosSalud rcp_FondoUnico_2_FondosSalud,
			RCP_FondoUnico_3_CotizDI rcp_FondoUnico_3_CotizDI, RCP_FondoUnico_Total rcp_FondoUnico_Total, RCP_CargoBenef_Total rcp_CargoBenef_Total) {
		super();
		this.periodo = periodo;
		this.entidad = entidad;
		this.rcp_CargoBenef_1_FondosPensiones = rcp_CargoBenef_1_FondosPensiones;
		this.rcp_CargoBenef_2_FondosSalud = rcp_CargoBenef_2_FondosSalud;
		this.rcp_CargoBenef_3_CotizSC = rcp_CargoBenef_3_CotizSC;
		this.rcp_FondoUnico_1_FondosPensiones = rcp_FondoUnico_1_FondosPensiones;
		this.rcp_FondoUnico_2_FondosSalud = rcp_FondoUnico_2_FondosSalud;
		this.rcp_FondoUnico_3_CotizDI = rcp_FondoUnico_3_CotizDI;
		this.rcp_FondoUnico_Total = rcp_FondoUnico_Total;
		this.rcp_CargoBenef_Total = rcp_CargoBenef_Total;
	}

	//metodos agregados para obtener las variables de los objetos que componen la clase rcp_integrado

	public String getAfp_Capital_B1P1_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Capital_B1P1_perEnfmen1());
	}

	public String getAfp_Capital_B1P1_repParental() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Capital_B1P1_repParental());
	}

	public String getAfp_Capital_B1P1_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Capital_B1P1_repPostNatal());
	}

	public String getAfp_Capital_B1P1_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Capital_B1P1_repPrenatal());
	}

	public String getAfp_Capital_B1P1_Total() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Capital_B1P1_Total());
	}

	public String getAfp_Cuprum_B1P1_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Cuprum_B1P1_perEnfmen1());
	}

	public String getAfp_Cuprum_B1P1_repParental() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Cuprum_B1P1_repParental());
	}

	public String getAfp_Cuprum_B1P1_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Cuprum_B1P1_repPostNatal());
	}

	public String getAfp_Cuprum_B1P1_repPreNatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Cuprum_B1P1_repPreNatal());
	}

	public String getAfp_Cuprum_B1P1_Total() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Cuprum_B1P1_Total());
	}

	public String getAfp_Habitat_B1P1_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Habitat_B1P1_perEnfmen1());
	}

	public String getAfp_Habitat_B1P1_repParental() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Habitat_B1P1_repParental());
	}

	public String getAfp_Habitat_B1P1_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Habitat_B1P1_repPostNatal());
	}

	public String getAfp_Habitat_B1P1_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Habitat_B1P1_repPrenatal());
	}

	public String getAfp_Habitat_B1P1_Total() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Habitat_B1P1_Total());
	}

	public String getAfp_Modelo_B1P1_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Modelo_B1P1_perEnfmen1());
	}

	public String getAfp_Modelo_B1P1_repParental() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Modelo_B1P1_repParental());
	}

	public String getAfp_Modelo_B1P1_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Modelo_B1P1_repPostNatal());
	}

	public String getAfp_Modelo_B1P1_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Modelo_B1P1_repPrenatal());
	}

	public String getAfp_Modelo_B1P1_Total() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Modelo_B1P1_Total());
	}

	public String getAfp_PlanVital_B1P1_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_PlanVital_B1P1_perEnfmen1());
	}

	public String getAfp_PlanVital_B1P1_repParental() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_PlanVital_B1P1_repParental());
	}

	public String getAfp_PlanVital_B1P1_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_PlanVital_B1P1_repPostNatal());
	}

	public String getAfp_PlanVital_B1P1_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_PlanVital_B1P1_repPrenatal());
	}

	public String getAfp_PlanVital_B1P1_Total() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_PlanVital_B1P1_Total());
	}

	public String getAfp_Provida_B1P1_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Provida_B1P1_perEnfmen1());
	}

	public String getAfp_Provida_B1P1_repParental() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Provida_B1P1_repParental());
	}

	public String getAfp_Provida_B1P1_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Provida_B1P1_repPostNatal());
	}

	public String getAfp_Provida_B1P1_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Provida_B1P1_repPrenatal());
	}

	public String getAfp_Provida_B1P1_Total() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getAfp_Provida_B1P1_Total());
	}

	public String getCapredena_B1P1_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getCapredena_B1P1_perEnfmen1());
	}

	public String getCapredena_B1P1_repParental() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getCapredena_B1P1_repParental());
	}

	public String getCapredena_B1P1_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getCapredena_B1P1_repPostNatal());
	}

	public String getCapredena_B1P1_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getCapredena_B1P1_repPrenatal());
	}

	public String getCapredena_B1P1_Total() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getCapredena_B1P1_Total());
	}

	public String getDipreca_B1P1_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getDipreca_B1P1_perEnfmen1());
	}

	public String getDipreca_B1P1_repParental() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getDipreca_B1P1_repParental());
	}

	public String getDipreca_B1P1_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getDipreca_B1P1_repPostNatal());
	}

	public String getDipreca_B1P1_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getDipreca_B1P1_repPrenatal());
	}

	public String getDipreca_B1P1_Total() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getDipreca_B1P1_Total());
	}

	public String getEntidEmpleadora_B1P1_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getEntidEmpleadora_B1P1_perEnfmen1());
	}

	public String getEntidEmpleadora_B1P1_repParental() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getEntidEmpleadora_B1P1_repParental());
	}

	public String getEntidEmpleadora_B1P1_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getEntidEmpleadora_B1P1_repPostNatal());
	}

	public String getEntidEmpleadora_B1P1_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getEntidEmpleadora_B1P1_repPrenatal());
	}

	public String getEntidEmpleadora_B1P1_Total() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getEntidEmpleadora_B1P1_Total());
	}

	public String getIps_B1P1_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getIps_B1P1_perEnfmen1());
	}

	public String getIps_B1P1_repParental() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getIps_B1P1_repParental());
	}

	public String getIps_B1P1_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getIps_B1P1_repPostNatal());
	}

	public String getIps_B1P1_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getIps_B1P1_repPrenatal());
	}

	public String getIps_B1P1_Total() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getIps_B1P1_Total());
	}

	public String getSubTotal_B1P1_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getSubTotal_B1P1_perEnfmen1());
	}

	public String getSubTotal_B1P1_repParental() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getSubTotal_B1P1_repParental());
	}

	public String getSubTotal_B1P1_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getSubTotal_B1P1_repPostNatal());
	}

	public String getSubTotal_B1P1_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getSubTotal_B1P1_repPrenatal());
	}

	public String getSubTotal_B1P1_Total() {
		return formatString_Mil(rcp_FondoUnico_1_FondosPensiones.getSubTotal_B1P1_Total());
	}

	//b1p2
	public String getCapredena_B1P2_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getCapredena_B1P2_perEnfmen1());
	}

	public String getCapredena_B1P2_repParental() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getCapredena_B1P2_repParental());
	}

	public String getCapredena_B1P2_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getCapredena_B1P2_repPostNatal());
	}

	public String getCapredena_B1P2_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getCapredena_B1P2_repPrenatal());
	}

	public String getCapredena_B1P2_Total() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getCapredena_B1P2_Total());
	}

	public String getCcaf_B1P2_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getCcaf_B1P2_perEnfmen1());
	}

	public String getCcaf_B1P2_repParental() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getCcaf_B1P2_repParental());
	}

	public String getCcaf_B1P2_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getCcaf_B1P2_repPostNatal());
	}

	public String getCcaf_B1P2_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getCcaf_B1P2_repPrenatal());
	}

	public String getCcaf_B1P2_Total() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getCcaf_B1P2_Total());
	}

	public String getDipreca_B1P2_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getDipreca_B1P2_perEnfmen1());
	}

	public String getDipreca_B1P2_repParental() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getDipreca_B1P2_repParental());
	}

	public String getDipreca_B1P2_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getDipreca_B1P2_repPostNatal());
	}

	public String getDipreca_B1P2_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getDipreca_B1P2_repPrenatal());
	}

	public String getDipreca_B1P2_Total() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getDipreca_B1P2_Total());
	}

	public String getEntidEmpleadora_B1P2_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getEntidEmpleadora_B1P2_perEnfmen1());
	}

	public String getEntidEmpleadora_B1P2_repParental() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getEntidEmpleadora_B1P2_repParental());
	}

	public String getEntidEmpleadora_B1P2_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getEntidEmpleadora_B1P2_repPostNatal());
	}

	public String getEntidEmpleadora_B1P2_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getEntidEmpleadora_B1P2_repPrenatal());
	}

	public String getEntidEmpleadora_B1P2_Total() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getEntidEmpleadora_B1P2_Total());
	}

	public String getIps_B1P2_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getIps_B1P2_perEnfmen1());
	}

	public String getIps_B1P2_repParental() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getIps_B1P2_repParental());
	}

	public String getIps_B1P2_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getIps_B1P2_repPostNatal());
	}

	public String getIps_B1P2_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getIps_B1P2_repPrenatal());
	}

	public String getIps_B1P2_Total() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getIps_B1P2_Total());
	}

	public String getIsp_B1P2_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getIsp_B1P2_perEnfmen1());
	}

	public String getIsp_B1P2_repParental() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getIsp_B1P2_repParental());
	}

	public String getIsp_B1P2_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getIsp_B1P2_repPostNatal());
	}

	public String getIsp_B1P2_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getIsp_B1P2_repPrenatal());
	}

	public String getIsp_B1P2_Total() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getIsp_B1P2_Total());
	}

	public String getSubTotal_B1P2_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getSubTotal_B1P2_perEnfmen1());
	}

	public String getSubTotal_B1P2_repParental() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getSubTotal_B1P2_repParental());
	}

	public String getSubTotal_B1P2_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getSubTotal_B1P2_repPostNatal());
	}

	public String getSubTotal_B1P2_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getSubTotal_B1P2_repPrenatal());
	}

	public String getSubTotal_B1P2_Total() {
		return formatString_Mil(rcp_FondoUnico_2_FondosSalud.getSubTotal_B1P2_Total());
	}

	//b1p3

	public String getEntidEmpleadora_B1P3_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getEntidEmpleadora_B1P3_perEnfmen1());
	}

	public String getEntidEmpleadora_B1P3_repParental() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getEntidEmpleadora_B1P3_repParental());
	}

	public String getEntidEmpleadora_B1P3_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getEntidEmpleadora_B1P3_repPostNatal());
	}

	public String getEntidEmpleadora_B1P3_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getEntidEmpleadora_B1P3_repPrenatal());
	}

	public String getEntidEmpleadora_B1P3_Total() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getEntidEmpleadora_B1P3_Total());
	}

	public String getIps_B1P3_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getIps_B1P3_perEnfmen1());
	}

	public String getIps_B1P3_repParental() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getIps_B1P3_repParental());
	}

	public String getIps_B1P3_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getIps_B1P3_repPostNatal());
	}

	public String getIps_B1P3_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getIps_B1P3_repPrenatal());
	}

	public String getIps_B1P3_Total() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getIps_B1P3_Total());
	}

	public String getSubTotal_B1P3_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getSubTotal_B1P3_perEnfmen1());
	}

	public String getSubTotal_B1P3_repParental() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getSubTotal_B1P3_repParental());
	}

	public String getSubTotal_B1P3_repPostNatal() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getSubTotal_B1P3_repPostNatal());
	}

	public String getSubTotal_B1P3_repPrenatal() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getSubTotal_B1P3_repPrenatal());
	}

	public String getSubTotal_B1P3_Total() {
		return formatString_Mil(rcp_FondoUnico_3_CotizDI.getSubTotal_B1P3_Total());
	}

	// total B1
	public String getTotal_B1_parental() {
		return formatString_Mil(rcp_FondoUnico_Total.getTotal_B1_parental());
	}

	public String getTotal_B1_perEnfmen1() {
		return formatString_Mil(rcp_FondoUnico_Total.getTotal_B1_perEnfmen1());
	}

	public String getTotal_B1_postnatal() {
		return formatString_Mil(rcp_FondoUnico_Total.getTotal_B1_postnatal());
	}

	public String getTotal_B1_prenatal() {
		return formatString_Mil(rcp_FondoUnico_Total.getTotal_B1_prenatal());
	}

	public String getTotal_B1_Total() {
		return formatString_Mil(rcp_FondoUnico_Total.getTotal_B1_Total());
	}

	//B2p1
	public String getAfp_Capital_B2P1_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Capital_B2P1_perEnfmen1());
	}

	public String getAfp_Capital_B2P1_repParental() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Capital_B2P1_repParental());
	}

	public String getAfp_Capital_B2P1_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Capital_B2P1_repPostNatal());
	}

	public String getAfp_Capital_B2P1_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Capital_B2P1_repPrenatal());
	}

	public String getAfp_Capital_B2P1_Total() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Capital_B2P1_Total());
	}

	public String getAfp_Cuprum_B2P1_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Cuprum_B2P1_perEnfmen1());
	}

	public String getAfp_Cuprum_B2P1_repParental() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Cuprum_B2P1_repParental());
	}

	public String getAfp_Cuprum_B2P1_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Cuprum_B2P1_repPostNatal());
	}

	public String getAfp_Cuprum_B2P1_repPreNatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Cuprum_B2P1_repPreNatal());
	}

	public String getAfp_Cuprum_B2P1_Total() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Cuprum_B2P1_Total());
	}

	public String getAfp_Habitat_B2P1_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Habitat_B2P1_perEnfmen1());
	}

	public String getAfp_Habitat_B2P1_repParental() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Habitat_B2P1_repParental());
	}

	public String getAfp_Habitat_B2P1_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Habitat_B2P1_repPostNatal());
	}

	public String getAfp_Habitat_B2P1_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Habitat_B2P1_repPrenatal());
	}

	public String getAfp_Habitat_B2P1_Total() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Habitat_B2P1_Total());
	}

	public String getAfp_Modelo_B2P1_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Modelo_B2P1_perEnfmen1());
	}

	public String getAfp_Modelo_B2P1_repParental() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Modelo_B2P1_repParental());
	}

	public String getAfp_Modelo_B2P1_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Modelo_B2P1_repPostNatal());
	}

	public String getAfp_Modelo_B2P1_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Modelo_B2P1_repPrenatal());
	}

	public String getAfp_Modelo_B2P1_Total() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Modelo_B2P1_Total());
	}

	public String getAfp_PlanVital_B2P1_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_PlanVital_B2P1_perEnfmen1());
	}

	public String getAfp_PlanVital_B2P1_repParental() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_PlanVital_B2P1_repParental());
	}

	public String getAfp_PlanVital_B2P1_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_PlanVital_B2P1_repPostNatal());
	}

	public String getAfp_PlanVital_B2P1_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_PlanVital_B2P1_repPrenatal());
	}

	public String getAfp_PlanVital_B2P1_Total() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_PlanVital_B2P1_Total());
	}

	public String getAfp_Provida_B2P1_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Provida_B2P1_perEnfmen1());
	}

	public String getAfp_Provida_B2P1_repParental() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Provida_B2P1_repParental());
	}

	public String getAfp_Provida_B2P1_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Provida_B2P1_repPostNatal());
	}

	public String getAfp_Provida_B2P1_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Provida_B2P1_repPrenatal());
	}

	public String getAfp_Provida_B2P1_Total() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getAfp_Provida_B2P1_Total());
	}

	public String getSubTotal_B2P1_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getSubTotal_B2P1_perEnfmen1());
	}

	public String getSubTotal_B2P1_repParental() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getSubTotal_B2P1_repParental());
	}

	public String getSubTotal_B2P1_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getSubTotal_B2P1_repPostNatal());
	}

	public String getSubTotal_B2P1_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getSubTotal_B2P1_repPrenatal());
	}

	public String getSubTotal_B2P1_Total() {
		return formatString_Mil(rcp_CargoBenef_1_FondosPensiones.getSubTotal_B2P1_Total());
	}

	//B2P2
	public String getIps_B2P2_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_2_FondosSalud.getIps_B2P2_perEnfmen1());
	}

	public String getIps_B2P2_repParental() {
		return formatString_Mil(rcp_CargoBenef_2_FondosSalud.getIps_B2P2_repParental());
	}

	public String getIps_B2P2_repPostnatal() {
		return formatString_Mil(rcp_CargoBenef_2_FondosSalud.getIps_B2P2_repPostnatal());
	}

	public String getIps_B2P2_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_2_FondosSalud.getIps_B2P2_repPrenatal());
	}

	public String getIps_B2P2_Total() {
		return formatString_Mil(rcp_CargoBenef_2_FondosSalud.getIps_B2P2_Total());
	}

	public String getSubTotal_B2P2_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_2_FondosSalud.getSubTotal_B2P2_perEnfmen1());
	}

	public String getSubTotal_B2P2_repParental() {
		return formatString_Mil(rcp_CargoBenef_2_FondosSalud.getSubTotal_B2P2_repParental());
	}

	public String getSubTotal_B2P2_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_2_FondosSalud.getSubTotal_B2P2_repPostNatal());
	}

	public String getSubTotal_B2P2_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_2_FondosSalud.getSubTotal_B2P2_repPrenatal());
	}

	public String getSubTotal_B2P2_Total() {
		return formatString_Mil(rcp_CargoBenef_2_FondosSalud.getSubTotal_B2P2_Total());
	}

	//B2P3
	public String getAfp_Capital_B2P3_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Capital_B2P3_perEnfmen1());
	}

	public String getAfp_Capital_B2P3_repParental() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Capital_B2P3_repParental());
	}

	public String getAfp_Capital_B2P3_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Capital_B2P3_repPostNatal());
	}

	public String getAfp_Capital_B2P3_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Capital_B2P3_repPrenatal());
	}

	public String getAfp_Capital_B2P3_Total() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Capital_B2P3_Total());
	}

	public String getAfp_Cuprum_B2P3_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Cuprum_B2P3_perEnfmen1());
	}

	public String getAfp_Cuprum_B2P3_repParental() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Cuprum_B2P3_repParental());
	}

	public String getAfp_Cuprum_B2P3_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Cuprum_B2P3_repPostNatal());
	}

	public String getAfp_Cuprum_B2P3_repPreNatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Cuprum_B2P3_repPreNatal());
	}

	public String getAfp_Cuprum_B2P3_Total() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Cuprum_B2P3_Total());
	}

	public String getAfp_Habitat_B2P3_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Habitat_B2P3_perEnfmen1());
	}

	public String getAfp_Habitat_B2P3_repParental() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Habitat_B2P3_repParental());
	}

	public String getAfp_Habitat_B2P3_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Habitat_B2P3_repPostNatal());
	}

	public String getAfp_Habitat_B2P3_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Habitat_B2P3_repPrenatal());
	}

	public String getAfp_Habitat_B2P3_Total() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Habitat_B2P3_Total());
	}

	public String getAfp_Modelo_B2P3_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Modelo_B2P3_perEnfmen1());
	}

	public String getAfp_Modelo_B2P3_repParental() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Modelo_B2P3_repParental());
	}

	public String getAfp_Modelo_B2P3_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Modelo_B2P3_repPostNatal());
	}

	public String getAfp_Modelo_B2P3_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Modelo_B2P3_repPrenatal());
	}

	public String getAfp_Modelo_B2P3_Total() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Modelo_B2P3_Total());
	}

	public String getAfp_PlanVital_B2P3_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_PlanVital_B2P3_perEnfmen1());
	}

	public String getAfp_PlanVital_B2P3_repParental() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_PlanVital_B2P3_repParental());
	}

	public String getAfp_PlanVital_B2P3_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_PlanVital_B2P3_repPostNatal());
	}

	public String getAfp_PlanVital_B2P3_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_PlanVital_B2P3_repPrenatal());
	}

	public String getAfp_PlanVital_B2P3_Total() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_PlanVital_B2P3_Total());
	}

	public String getAfp_Provida_B2P3_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Provida_B2P3_perEnfmen1());
	}

	public String getAfp_Provida_B2P3_repParental() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Provida_B2P3_repParental());
	}

	public String getAfp_Provida_B2P3_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Provida_B2P3_repPostNatal());
	}

	public String getAfp_Provida_B2P3_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Provida_B2P3_repPrenatal());
	}

	public String getAfp_Provida_B2P3_Total() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getAfp_Provida_B2P3_Total());
	}

	public String getEntidEmpleadora_B2P3_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getEntidEmpleadora_B2P3_perEnfmen1());
	}

	public String getEntidEmpleadora_B2P3_repParental() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getEntidEmpleadora_B2P3_repParental());
	}

	public String getEntidEmpleadora_B2P3_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getEntidEmpleadora_B2P3_repPostNatal());
	}

	public String getEntidEmpleadora_B2P3_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getEntidEmpleadora_B2P3_repPrenatal());
	}

	public String getEntidEmpleadora_B2P3_Total() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getEntidEmpleadora_B2P3_Total());
	}

	public String getSubTotal_B2P3_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getSubTotal_B2P3_perEnfmen1());
	}

	public String getSubTotal_B2P3_repParental() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getSubTotal_B2P3_repParental());
	}

	public String getSubTotal_B2P3_repPostNatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getSubTotal_B2P3_repPostNatal());
	}

	public String getSubTotal_B2P3_repPrenatal() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getSubTotal_B2P3_repPrenatal());
	}

	public String getSubTotal_B2P3_Total() {
		return formatString_Mil(rcp_CargoBenef_3_CotizSC.getSubTotal_B2P3_Total());
	}

	//B2
	public String getTotal_B2_parental() {
		return formatString_Mil(rcp_CargoBenef_Total.getTotal_B2_parental());
	}

	public String getTotal_B2_perEnfmen1() {
		return formatString_Mil(rcp_CargoBenef_Total.getTotal_B2_perEnfmen1());
	}

	public String getTotal_B2_postnatal() {
		return formatString_Mil(rcp_CargoBenef_Total.getTotal_B2_postnatal());
	}

	public String getTotal_B2_prenatal() {
		return formatString_Mil(rcp_CargoBenef_Total.getTotal_B2_prenatal());
	}

	public String getTotal_B2_Total() {
		return formatString_Mil(rcp_CargoBenef_Total.getTotal_B2_Total());
	}

	//metodos propios de rcp_integrado

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public RCP_CargoBenef_1_FondosPensiones getRcp_CargoBenef_1_FondosPensiones() {
		return rcp_CargoBenef_1_FondosPensiones;
	}

	public void setRcp_CargoBenef_1_FondosPensiones(RCP_CargoBenef_1_FondosPensiones rcp_CargoBenef_1_FondosPensiones) {
		this.rcp_CargoBenef_1_FondosPensiones = rcp_CargoBenef_1_FondosPensiones;
	}

	public RCP_CargoBenef_2_FondosSalud getRcp_CargoBenef_2_FondosSalud() {
		return rcp_CargoBenef_2_FondosSalud;
	}

	public void setRcp_CargoBenef_2_FondosSalud(RCP_CargoBenef_2_FondosSalud rcp_CargoBenef_2_FondosSalud) {
		this.rcp_CargoBenef_2_FondosSalud = rcp_CargoBenef_2_FondosSalud;
	}

	public RCP_CargoBenef_3_CotizSC getRcp_CargoBenef_3_CotizSC() {
		return rcp_CargoBenef_3_CotizSC;
	}

	public void setRcp_CargoBenef_3_CotizSC(RCP_CargoBenef_3_CotizSC rcp_CargoBenef_3_CotizSC) {
		this.rcp_CargoBenef_3_CotizSC = rcp_CargoBenef_3_CotizSC;
	}

	public RCP_CargoBenef_Total getRcp_CargoBenef_Total() {
		return rcp_CargoBenef_Total;
	}

	public void setRcp_CargoBenef_Total(RCP_CargoBenef_Total rcp_CargoBenef_Total) {
		this.rcp_CargoBenef_Total = rcp_CargoBenef_Total;
	}

	public RCP_FondoUnico_1_FondosPensiones getRcp_FondoUnico_1_FondosPensiones() {
		return rcp_FondoUnico_1_FondosPensiones;
	}

	public void setRcp_FondoUnico_1_FondosPensiones(RCP_FondoUnico_1_FondosPensiones rcp_FondoUnico_1_FondosPensiones) {
		this.rcp_FondoUnico_1_FondosPensiones = rcp_FondoUnico_1_FondosPensiones;
	}

	public RCP_FondoUnico_2_FondosSalud getRcp_FondoUnico_2_FondosSalud() {
		return rcp_FondoUnico_2_FondosSalud;
	}

	public void setRcp_FondoUnico_2_FondosSalud(RCP_FondoUnico_2_FondosSalud rcp_FondoUnico_2_FondosSalud) {
		this.rcp_FondoUnico_2_FondosSalud = rcp_FondoUnico_2_FondosSalud;
	}

	public RCP_FondoUnico_3_CotizDI getRcp_FondoUnico_3_CotizDI() {
		return rcp_FondoUnico_3_CotizDI;
	}

	public void setRcp_FondoUnico_3_CotizDI(RCP_FondoUnico_3_CotizDI rcp_FondoUnico_3_CotizDI) {
		this.rcp_FondoUnico_3_CotizDI = rcp_FondoUnico_3_CotizDI;
	}

	public RCP_FondoUnico_Total getRcp_FondoUnico_Total() {
		return rcp_FondoUnico_Total;
	}

	public void setRcp_FondoUnico_Total(RCP_FondoUnico_Total rcp_FondoUnico_Total) {
		this.rcp_FondoUnico_Total = rcp_FondoUnico_Total;
	}

	private String formatString_Mil(long number) {
		//long n=Long.parseLong(number);
		ManejoFormatoNumeros mfn = new ManejoFormatoNumeros();
		return mfn.getFormatoDecimal(number);
	}

}
