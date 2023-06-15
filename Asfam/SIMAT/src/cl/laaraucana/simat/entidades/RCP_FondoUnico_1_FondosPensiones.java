package cl.laaraucana.simat.entidades;

/*
 * Clase para resumen cotizaciones Previsionales, para bloque I, de cargo del fondo unico de prestaciones familiares y subsidio cesantia
 * parte I, fondos de pensiones
 * */
public class RCP_FondoUnico_1_FondosPensiones {

	//	bloque I
	//parte 1 fondos de pensiones
	// filas * 5 columnas...
	private long afp_Cuprum_B1P1_repPreNatal;
	private long afp_Cuprum_B1P1_repPostNatal;
	private long afp_Cuprum_B1P1_repParental;
	private long afp_Cuprum_B1P1_perEnfmen1;
	private long afp_Cuprum_B1P1_Total;

	private long afp_Habitat_B1P1_repPrenatal;
	private long afp_Habitat_B1P1_repPostNatal;
	private long afp_Habitat_B1P1_repParental;
	private long afp_Habitat_B1P1_perEnfmen1;
	private long afp_Habitat_B1P1_Total;

	private long afp_PlanVital_B1P1_repPrenatal;
	private long afp_PlanVital_B1P1_repPostNatal;
	private long afp_PlanVital_B1P1_repParental;
	private long afp_PlanVital_B1P1_perEnfmen1;
	private long afp_PlanVital_B1P1_Total;

	private long afp_Provida_B1P1_repPrenatal;
	private long afp_Provida_B1P1_repPostNatal;
	private long afp_Provida_B1P1_repParental;
	private long afp_Provida_B1P1_perEnfmen1;
	private long afp_Provida_B1P1_Total;

	private long afp_Capital_B1P1_repPrenatal;
	private long afp_Capital_B1P1_repPostNatal;
	private long afp_Capital_B1P1_repParental;
	private long afp_Capital_B1P1_perEnfmen1;
	private long afp_Capital_B1P1_Total;

	private long afp_Modelo_B1P1_repPrenatal;
	private long afp_Modelo_B1P1_repPostNatal;
	private long afp_Modelo_B1P1_repParental;
	private long afp_Modelo_B1P1_perEnfmen1;
	private long afp_Modelo_B1P1_Total;

	//siguientes 5 valores en cero
	private long ips_B1P1_repPrenatal;
	private long ips_B1P1_repPostNatal;
	private long ips_B1P1_repParental;
	private long ips_B1P1_perEnfmen1;
	private long ips_B1P1_Total;
	//siguientes 5 valores en cero
	private long capredena_B1P1_repPrenatal;
	private long capredena_B1P1_repPostNatal;
	private long capredena_B1P1_repParental;
	private long capredena_B1P1_perEnfmen1;
	private long capredena_B1P1_Total;
	//siguientes 5 valores en cero
	private long dipreca_B1P1_repPrenatal;
	private long dipreca_B1P1_repPostNatal;
	private long dipreca_B1P1_repParental;
	private long dipreca_B1P1_perEnfmen1;
	private long dipreca_B1P1_Total;

	private long entidEmpleadora_B1P1_repPrenatal;
	private long entidEmpleadora_B1P1_repPostNatal;
	private long entidEmpleadora_B1P1_repParental;
	private long entidEmpleadora_B1P1_perEnfmen1;
	private long entidEmpleadora_B1P1_Total;

	private long subTotal_B1P1_repPrenatal;
	private long subTotal_B1P1_repPostNatal;
	private long subTotal_B1P1_repParental;
	private long subTotal_B1P1_perEnfmen1;
	private long subTotal_B1P1_Total;

	public RCP_FondoUnico_1_FondosPensiones() {

	}

	public RCP_FondoUnico_1_FondosPensiones(long afp_Cuprum_B1P1_repPreNatal, long afp_Cuprum_B1P1_repPostNatal, long afp_Cuprum_B1P1_repParental, long afp_Cuprum_B1P1_perEnfmen1,
			long afp_Cuprum_B1P1_Total, long afp_Habitat_B1P1_repPrenatal, long afp_Habitat_B1P1_repPostNatal, long afp_Habitat_B1P1_repParental, long afp_Habitat_B1P1_perEnfmen1,
			long afp_Habitat_B1P1_Total, long afp_PlanVital_B1P1_repPrenatal, long afp_PlanVital_B1P1_repPostNatal, long afp_PlanVital_B1P1_repParental, long afp_PlanVital_B1P1_perEnfmen1,
			long afp_PlanVital_B1P1_Total, long afp_Provida_B1P1_repPrenatal, long afp_Provida_B1P1_repPostNatal, long afp_Provida_B1P1_repParental, long afp_Provida_B1P1_perEnfmen1,
			long afp_Provida_B1P1_Total, long afp_Capital_B1P1_repPrenatal, long afp_Capital_B1P1_repPostNatal, long afp_Capital_B1P1_repParental, long afp_Capital_B1P1_perEnfmen1,
			long afp_Capital_B1P1_Total, long afp_Modelo_B1P1_repPrenatal, long afp_Modelo_B1P1_repPostNatal, long afp_Modelo_B1P1_repParental, long afp_Modelo_B1P1_perEnfmen1,
			long afp_Modelo_B1P1_Total, long ips_B1P1_repPrenatal, long ips_B1P1_repPostNatal, long ips_B1P1_repParental, long ips_B1P1_perEnfmen1, long ips_B1P1_Total,
			long capredena_B1P1_repPrenatal, long capredena_B1P1_repPostNatal, long capredena_B1P1_repParental, long capredena_B1P1_perEnfmen1, long capredena_B1P1_Total,
			long dipreca_B1P1_repPrenatal, long dipreca_B1P1_repPostNatal, long dipreca_B1P1_repParental, long dipreca_B1P1_perEnfmen1, long dipreca_B1P1_Total, long entidEmpleadora_B1P1_repPrenatal,
			long entidEmpleadora_B1P1_repPostNatal, long entidEmpleadora_B1P1_repParental, long entidEmpleadora_B1P1_perEnfmen1, long entidEmpleadora_B1P1_Total, long subTotal_B1P1_repPrenatal,
			long subTotal_B1P1_repPostNatal, long subTotal_B1P1_repParental, long subTotal_B1P1_perEnfmen1, long subTotal_B1P1_Total) {
		super();
		this.afp_Cuprum_B1P1_repPreNatal = afp_Cuprum_B1P1_repPreNatal;
		this.afp_Cuprum_B1P1_repPostNatal = afp_Cuprum_B1P1_repPostNatal;
		this.afp_Cuprum_B1P1_repParental = afp_Cuprum_B1P1_repParental;
		this.afp_Cuprum_B1P1_perEnfmen1 = afp_Cuprum_B1P1_perEnfmen1;
		this.afp_Cuprum_B1P1_Total = afp_Cuprum_B1P1_Total;
		this.afp_Habitat_B1P1_repPrenatal = afp_Habitat_B1P1_repPrenatal;
		this.afp_Habitat_B1P1_repPostNatal = afp_Habitat_B1P1_repPostNatal;
		this.afp_Habitat_B1P1_repParental = afp_Habitat_B1P1_repParental;
		this.afp_Habitat_B1P1_perEnfmen1 = afp_Habitat_B1P1_perEnfmen1;
		this.afp_Habitat_B1P1_Total = afp_Habitat_B1P1_Total;
		this.afp_PlanVital_B1P1_repPrenatal = afp_PlanVital_B1P1_repPrenatal;
		this.afp_PlanVital_B1P1_repPostNatal = afp_PlanVital_B1P1_repPostNatal;
		this.afp_PlanVital_B1P1_repParental = afp_PlanVital_B1P1_repParental;
		this.afp_PlanVital_B1P1_perEnfmen1 = afp_PlanVital_B1P1_perEnfmen1;
		this.afp_PlanVital_B1P1_Total = afp_PlanVital_B1P1_Total;
		this.afp_Provida_B1P1_repPrenatal = afp_Provida_B1P1_repPrenatal;
		this.afp_Provida_B1P1_repPostNatal = afp_Provida_B1P1_repPostNatal;
		this.afp_Provida_B1P1_repParental = afp_Provida_B1P1_repParental;
		this.afp_Provida_B1P1_perEnfmen1 = afp_Provida_B1P1_perEnfmen1;
		this.afp_Provida_B1P1_Total = afp_Provida_B1P1_Total;
		this.afp_Capital_B1P1_repPrenatal = afp_Capital_B1P1_repPrenatal;
		this.afp_Capital_B1P1_repPostNatal = afp_Capital_B1P1_repPostNatal;
		this.afp_Capital_B1P1_repParental = afp_Capital_B1P1_repParental;
		this.afp_Capital_B1P1_perEnfmen1 = afp_Capital_B1P1_perEnfmen1;
		this.afp_Capital_B1P1_Total = afp_Capital_B1P1_Total;
		this.afp_Modelo_B1P1_repPrenatal = afp_Modelo_B1P1_repPrenatal;
		this.afp_Modelo_B1P1_repPostNatal = afp_Modelo_B1P1_repPostNatal;
		this.afp_Modelo_B1P1_repParental = afp_Modelo_B1P1_repParental;
		this.afp_Modelo_B1P1_perEnfmen1 = afp_Modelo_B1P1_perEnfmen1;
		this.afp_Modelo_B1P1_Total = afp_Modelo_B1P1_Total;
		this.ips_B1P1_repPrenatal = ips_B1P1_repPrenatal;
		this.ips_B1P1_repPostNatal = ips_B1P1_repPostNatal;
		this.ips_B1P1_repParental = ips_B1P1_repParental;
		this.ips_B1P1_perEnfmen1 = ips_B1P1_perEnfmen1;
		this.ips_B1P1_Total = ips_B1P1_Total;
		this.capredena_B1P1_repPrenatal = capredena_B1P1_repPrenatal;
		this.capredena_B1P1_repPostNatal = capredena_B1P1_repPostNatal;
		this.capredena_B1P1_repParental = capredena_B1P1_repParental;
		this.capredena_B1P1_perEnfmen1 = capredena_B1P1_perEnfmen1;
		this.capredena_B1P1_Total = capredena_B1P1_Total;
		this.dipreca_B1P1_repPrenatal = dipreca_B1P1_repPrenatal;
		this.dipreca_B1P1_repPostNatal = dipreca_B1P1_repPostNatal;
		this.dipreca_B1P1_repParental = dipreca_B1P1_repParental;
		this.dipreca_B1P1_perEnfmen1 = dipreca_B1P1_perEnfmen1;
		this.dipreca_B1P1_Total = dipreca_B1P1_Total;
		this.entidEmpleadora_B1P1_repPrenatal = entidEmpleadora_B1P1_repPrenatal;
		this.entidEmpleadora_B1P1_repPostNatal = entidEmpleadora_B1P1_repPostNatal;
		this.entidEmpleadora_B1P1_repParental = entidEmpleadora_B1P1_repParental;
		this.entidEmpleadora_B1P1_perEnfmen1 = entidEmpleadora_B1P1_perEnfmen1;
		this.entidEmpleadora_B1P1_Total = entidEmpleadora_B1P1_Total;
		this.subTotal_B1P1_repPrenatal = subTotal_B1P1_repPrenatal;
		this.subTotal_B1P1_repPostNatal = subTotal_B1P1_repPostNatal;
		this.subTotal_B1P1_repParental = subTotal_B1P1_repParental;
		this.subTotal_B1P1_perEnfmen1 = subTotal_B1P1_perEnfmen1;
		this.subTotal_B1P1_Total = subTotal_B1P1_Total;
	}

	public long getAfp_Capital_B1P1_perEnfmen1() {
		return afp_Capital_B1P1_perEnfmen1;
	}

	public void setAfp_Capital_B1P1_perEnfmen1(long afp_Capital_B1P1_perEnfmen1) {
		this.afp_Capital_B1P1_perEnfmen1 = afp_Capital_B1P1_perEnfmen1;
	}

	public long getAfp_Capital_B1P1_repParental() {
		return afp_Capital_B1P1_repParental;
	}

	public void setAfp_Capital_B1P1_repParental(long afp_Capital_B1P1_repParental) {
		this.afp_Capital_B1P1_repParental = afp_Capital_B1P1_repParental;
	}

	public long getAfp_Capital_B1P1_repPostNatal() {
		return afp_Capital_B1P1_repPostNatal;
	}

	public void setAfp_Capital_B1P1_repPostNatal(long afp_Capital_B1P1_repPostNatal) {
		this.afp_Capital_B1P1_repPostNatal = afp_Capital_B1P1_repPostNatal;
	}

	public long getAfp_Capital_B1P1_repPrenatal() {
		return afp_Capital_B1P1_repPrenatal;
	}

	public void setAfp_Capital_B1P1_repPrenatal(long afp_Capital_B1P1_repPrenatal) {
		this.afp_Capital_B1P1_repPrenatal = afp_Capital_B1P1_repPrenatal;
	}

	public long getAfp_Capital_B1P1_Total() {
		return afp_Capital_B1P1_Total;
	}

	public void setAfp_Capital_B1P1_Total(long afp_Capital_B1P1_Total) {
		this.afp_Capital_B1P1_Total = afp_Capital_B1P1_Total;
	}

	public long getAfp_Cuprum_B1P1_perEnfmen1() {
		return afp_Cuprum_B1P1_perEnfmen1;
	}

	public void setAfp_Cuprum_B1P1_perEnfmen1(long afp_Cuprum_B1P1_perEnfmen1) {
		this.afp_Cuprum_B1P1_perEnfmen1 = afp_Cuprum_B1P1_perEnfmen1;
	}

	public long getAfp_Cuprum_B1P1_repParental() {
		return afp_Cuprum_B1P1_repParental;
	}

	public void setAfp_Cuprum_B1P1_repParental(long afp_Cuprum_B1P1_repParental) {
		this.afp_Cuprum_B1P1_repParental = afp_Cuprum_B1P1_repParental;
	}

	public long getAfp_Cuprum_B1P1_repPostNatal() {
		return afp_Cuprum_B1P1_repPostNatal;
	}

	public void setAfp_Cuprum_B1P1_repPostNatal(long afp_Cuprum_B1P1_repPostNatal) {
		this.afp_Cuprum_B1P1_repPostNatal = afp_Cuprum_B1P1_repPostNatal;
	}

	public long getAfp_Cuprum_B1P1_repPreNatal() {
		return afp_Cuprum_B1P1_repPreNatal;
	}

	public void setAfp_Cuprum_B1P1_repPreNatal(long afp_Cuprum_B1P1_repPreNatal) {
		this.afp_Cuprum_B1P1_repPreNatal = afp_Cuprum_B1P1_repPreNatal;
	}

	public long getAfp_Cuprum_B1P1_Total() {
		return afp_Cuprum_B1P1_Total;
	}

	public void setAfp_Cuprum_B1P1_Total(long afp_Cuprum_B1P1_Total) {
		this.afp_Cuprum_B1P1_Total = afp_Cuprum_B1P1_Total;
	}

	public long getAfp_Habitat_B1P1_perEnfmen1() {
		return afp_Habitat_B1P1_perEnfmen1;
	}

	public void setAfp_Habitat_B1P1_perEnfmen1(long afp_Habitat_B1P1_perEnfmen1) {
		this.afp_Habitat_B1P1_perEnfmen1 = afp_Habitat_B1P1_perEnfmen1;
	}

	public long getAfp_Habitat_B1P1_repParental() {
		return afp_Habitat_B1P1_repParental;
	}

	public void setAfp_Habitat_B1P1_repParental(long afp_Habitat_B1P1_repParental) {
		this.afp_Habitat_B1P1_repParental = afp_Habitat_B1P1_repParental;
	}

	public long getAfp_Habitat_B1P1_repPostNatal() {
		return afp_Habitat_B1P1_repPostNatal;
	}

	public void setAfp_Habitat_B1P1_repPostNatal(long afp_Habitat_B1P1_repPostNatal) {
		this.afp_Habitat_B1P1_repPostNatal = afp_Habitat_B1P1_repPostNatal;
	}

	public long getAfp_Habitat_B1P1_repPrenatal() {
		return afp_Habitat_B1P1_repPrenatal;
	}

	public void setAfp_Habitat_B1P1_repPrenatal(long afp_Habitat_B1P1_repPrenatal) {
		this.afp_Habitat_B1P1_repPrenatal = afp_Habitat_B1P1_repPrenatal;
	}

	public long getAfp_Habitat_B1P1_Total() {
		return afp_Habitat_B1P1_Total;
	}

	public void setAfp_Habitat_B1P1_Total(long afp_Habitat_B1P1_Total) {
		this.afp_Habitat_B1P1_Total = afp_Habitat_B1P1_Total;
	}

	public long getAfp_Modelo_B1P1_perEnfmen1() {
		return afp_Modelo_B1P1_perEnfmen1;
	}

	public void setAfp_Modelo_B1P1_perEnfmen1(long afp_Modelo_B1P1_perEnfmen1) {
		this.afp_Modelo_B1P1_perEnfmen1 = afp_Modelo_B1P1_perEnfmen1;
	}

	public long getAfp_Modelo_B1P1_repParental() {
		return afp_Modelo_B1P1_repParental;
	}

	public void setAfp_Modelo_B1P1_repParental(long afp_Modelo_B1P1_repParental) {
		this.afp_Modelo_B1P1_repParental = afp_Modelo_B1P1_repParental;
	}

	public long getAfp_Modelo_B1P1_repPostNatal() {
		return afp_Modelo_B1P1_repPostNatal;
	}

	public void setAfp_Modelo_B1P1_repPostNatal(long afp_Modelo_B1P1_repPostNatal) {
		this.afp_Modelo_B1P1_repPostNatal = afp_Modelo_B1P1_repPostNatal;
	}

	public long getAfp_Modelo_B1P1_repPrenatal() {
		return afp_Modelo_B1P1_repPrenatal;
	}

	public void setAfp_Modelo_B1P1_repPrenatal(long afp_Modelo_B1P1_repPrenatal) {
		this.afp_Modelo_B1P1_repPrenatal = afp_Modelo_B1P1_repPrenatal;
	}

	public long getAfp_Modelo_B1P1_Total() {
		return afp_Modelo_B1P1_Total;
	}

	public void setAfp_Modelo_B1P1_Total(long afp_Modelo_B1P1_Total) {
		this.afp_Modelo_B1P1_Total = afp_Modelo_B1P1_Total;
	}

	public long getAfp_PlanVital_B1P1_perEnfmen1() {
		return afp_PlanVital_B1P1_perEnfmen1;
	}

	public void setAfp_PlanVital_B1P1_perEnfmen1(long afp_PlanVital_B1P1_perEnfmen1) {
		this.afp_PlanVital_B1P1_perEnfmen1 = afp_PlanVital_B1P1_perEnfmen1;
	}

	public long getAfp_PlanVital_B1P1_repParental() {
		return afp_PlanVital_B1P1_repParental;
	}

	public void setAfp_PlanVital_B1P1_repParental(long afp_PlanVital_B1P1_repParental) {
		this.afp_PlanVital_B1P1_repParental = afp_PlanVital_B1P1_repParental;
	}

	public long getAfp_PlanVital_B1P1_repPostNatal() {
		return afp_PlanVital_B1P1_repPostNatal;
	}

	public void setAfp_PlanVital_B1P1_repPostNatal(long afp_PlanVital_B1P1_repPostNatal) {
		this.afp_PlanVital_B1P1_repPostNatal = afp_PlanVital_B1P1_repPostNatal;
	}

	public long getAfp_PlanVital_B1P1_repPrenatal() {
		return afp_PlanVital_B1P1_repPrenatal;
	}

	public void setAfp_PlanVital_B1P1_repPrenatal(long afp_PlanVital_B1P1_repPrenatal) {
		this.afp_PlanVital_B1P1_repPrenatal = afp_PlanVital_B1P1_repPrenatal;
	}

	public long getAfp_PlanVital_B1P1_Total() {
		return afp_PlanVital_B1P1_Total;
	}

	public void setAfp_PlanVital_B1P1_Total(long afp_PlanVital_B1P1_Total) {
		this.afp_PlanVital_B1P1_Total = afp_PlanVital_B1P1_Total;
	}

	public long getAfp_Provida_B1P1_perEnfmen1() {
		return afp_Provida_B1P1_perEnfmen1;
	}

	public void setAfp_Provida_B1P1_perEnfmen1(long afp_Provida_B1P1_perEnfmen1) {
		this.afp_Provida_B1P1_perEnfmen1 = afp_Provida_B1P1_perEnfmen1;
	}

	public long getAfp_Provida_B1P1_repParental() {
		return afp_Provida_B1P1_repParental;
	}

	public void setAfp_Provida_B1P1_repParental(long afp_Provida_B1P1_repParental) {
		this.afp_Provida_B1P1_repParental = afp_Provida_B1P1_repParental;
	}

	public long getAfp_Provida_B1P1_repPostNatal() {
		return afp_Provida_B1P1_repPostNatal;
	}

	public void setAfp_Provida_B1P1_repPostNatal(long afp_Provida_B1P1_repPostNatal) {
		this.afp_Provida_B1P1_repPostNatal = afp_Provida_B1P1_repPostNatal;
	}

	public long getAfp_Provida_B1P1_repPrenatal() {
		return afp_Provida_B1P1_repPrenatal;
	}

	public void setAfp_Provida_B1P1_repPrenatal(long afp_Provida_B1P1_repPrenatal) {
		this.afp_Provida_B1P1_repPrenatal = afp_Provida_B1P1_repPrenatal;
	}

	public long getAfp_Provida_B1P1_Total() {
		return afp_Provida_B1P1_Total;
	}

	public void setAfp_Provida_B1P1_Total(long afp_Provida_B1P1_Total) {
		this.afp_Provida_B1P1_Total = afp_Provida_B1P1_Total;
	}

	public long getCapredena_B1P1_perEnfmen1() {
		return capredena_B1P1_perEnfmen1;
	}

	public void setCapredena_B1P1_perEnfmen1(long capredena_B1P1_perEnfmen1) {
		this.capredena_B1P1_perEnfmen1 = capredena_B1P1_perEnfmen1;
	}

	public long getCapredena_B1P1_repParental() {
		return capredena_B1P1_repParental;
	}

	public void setCapredena_B1P1_repParental(long capredena_B1P1_repParental) {
		this.capredena_B1P1_repParental = capredena_B1P1_repParental;
	}

	public long getCapredena_B1P1_repPostNatal() {
		return capredena_B1P1_repPostNatal;
	}

	public void setCapredena_B1P1_repPostNatal(long capredena_B1P1_repPostNatal) {
		this.capredena_B1P1_repPostNatal = capredena_B1P1_repPostNatal;
	}

	public long getCapredena_B1P1_repPrenatal() {
		return capredena_B1P1_repPrenatal;
	}

	public void setCapredena_B1P1_repPrenatal(long capredena_B1P1_repPrenatal) {
		this.capredena_B1P1_repPrenatal = capredena_B1P1_repPrenatal;
	}

	public long getCapredena_B1P1_Total() {
		return capredena_B1P1_Total;
	}

	public void setCapredena_B1P1_Total(long capredena_B1P1_Total) {
		this.capredena_B1P1_Total = capredena_B1P1_Total;
	}

	public long getDipreca_B1P1_perEnfmen1() {
		return dipreca_B1P1_perEnfmen1;
	}

	public void setDipreca_B1P1_perEnfmen1(long dipreca_B1P1_perEnfmen1) {
		this.dipreca_B1P1_perEnfmen1 = dipreca_B1P1_perEnfmen1;
	}

	public long getDipreca_B1P1_repParental() {
		return dipreca_B1P1_repParental;
	}

	public void setDipreca_B1P1_repParental(long dipreca_B1P1_repParental) {
		this.dipreca_B1P1_repParental = dipreca_B1P1_repParental;
	}

	public long getDipreca_B1P1_repPostNatal() {
		return dipreca_B1P1_repPostNatal;
	}

	public void setDipreca_B1P1_repPostNatal(long dipreca_B1P1_repPostNatal) {
		this.dipreca_B1P1_repPostNatal = dipreca_B1P1_repPostNatal;
	}

	public long getDipreca_B1P1_repPrenatal() {
		return dipreca_B1P1_repPrenatal;
	}

	public void setDipreca_B1P1_repPrenatal(long dipreca_B1P1_repPrenatal) {
		this.dipreca_B1P1_repPrenatal = dipreca_B1P1_repPrenatal;
	}

	public long getDipreca_B1P1_Total() {
		return dipreca_B1P1_Total;
	}

	public void setDipreca_B1P1_Total(long dipreca_B1P1_Total) {
		this.dipreca_B1P1_Total = dipreca_B1P1_Total;
	}

	public long getEntidEmpleadora_B1P1_perEnfmen1() {
		return entidEmpleadora_B1P1_perEnfmen1;
	}

	public void setEntidEmpleadora_B1P1_perEnfmen1(long entidEmpleadora_B1P1_perEnfmen1) {
		this.entidEmpleadora_B1P1_perEnfmen1 = entidEmpleadora_B1P1_perEnfmen1;
	}

	public long getEntidEmpleadora_B1P1_repParental() {
		return entidEmpleadora_B1P1_repParental;
	}

	public void setEntidEmpleadora_B1P1_repParental(long entidEmpleadora_B1P1_repParental) {
		this.entidEmpleadora_B1P1_repParental = entidEmpleadora_B1P1_repParental;
	}

	public long getEntidEmpleadora_B1P1_repPostNatal() {
		return entidEmpleadora_B1P1_repPostNatal;
	}

	public void setEntidEmpleadora_B1P1_repPostNatal(long entidEmpleadora_B1P1_repPostNatal) {
		this.entidEmpleadora_B1P1_repPostNatal = entidEmpleadora_B1P1_repPostNatal;
	}

	public long getEntidEmpleadora_B1P1_repPrenatal() {
		return entidEmpleadora_B1P1_repPrenatal;
	}

	public void setEntidEmpleadora_B1P1_repPrenatal(long entidEmpleadora_B1P1_repPrenatal) {
		this.entidEmpleadora_B1P1_repPrenatal = entidEmpleadora_B1P1_repPrenatal;
	}

	public long getEntidEmpleadora_B1P1_Total() {
		return entidEmpleadora_B1P1_Total;
	}

	public void setEntidEmpleadora_B1P1_Total(long entidEmpleadora_B1P1_Total) {
		this.entidEmpleadora_B1P1_Total = entidEmpleadora_B1P1_Total;
	}

	public long getIps_B1P1_perEnfmen1() {
		return ips_B1P1_perEnfmen1;
	}

	public void setIps_B1P1_perEnfmen1(long ips_B1P1_perEnfmen1) {
		this.ips_B1P1_perEnfmen1 = ips_B1P1_perEnfmen1;
	}

	public long getIps_B1P1_repParental() {
		return ips_B1P1_repParental;
	}

	public void setIps_B1P1_repParental(long ips_B1P1_repParental) {
		this.ips_B1P1_repParental = ips_B1P1_repParental;
	}

	public long getIps_B1P1_repPostNatal() {
		return ips_B1P1_repPostNatal;
	}

	public void setIps_B1P1_repPostNatal(long ips_B1P1_repPostNatal) {
		this.ips_B1P1_repPostNatal = ips_B1P1_repPostNatal;
	}

	public long getIps_B1P1_repPrenatal() {
		return ips_B1P1_repPrenatal;
	}

	public void setIps_B1P1_repPrenatal(long ips_B1P1_repPrenatal) {
		this.ips_B1P1_repPrenatal = ips_B1P1_repPrenatal;
	}

	public long getIps_B1P1_Total() {
		return ips_B1P1_Total;
	}

	public void setIps_B1P1_Total(long ips_B1P1_Total) {
		this.ips_B1P1_Total = ips_B1P1_Total;
	}

	public long getSubTotal_B1P1_perEnfmen1() {
		return subTotal_B1P1_perEnfmen1;
	}

	public void setSubTotal_B1P1_perEnfmen1(long subTotal_B1P1_perEnfmen1) {
		this.subTotal_B1P1_perEnfmen1 = subTotal_B1P1_perEnfmen1;
	}

	public long getSubTotal_B1P1_repParental() {
		return subTotal_B1P1_repParental;
	}

	public void setSubTotal_B1P1_repParental(long subTotal_B1P1_repParental) {
		this.subTotal_B1P1_repParental = subTotal_B1P1_repParental;
	}

	public long getSubTotal_B1P1_repPostNatal() {
		return subTotal_B1P1_repPostNatal;
	}

	public void setSubTotal_B1P1_repPostNatal(long subTotal_B1P1_repPostNatal) {
		this.subTotal_B1P1_repPostNatal = subTotal_B1P1_repPostNatal;
	}

	public long getSubTotal_B1P1_repPrenatal() {
		return subTotal_B1P1_repPrenatal;
	}

	public void setSubTotal_B1P1_repPrenatal(long subTotal_B1P1_repPrenatal) {
		this.subTotal_B1P1_repPrenatal = subTotal_B1P1_repPrenatal;
	}

	public long getSubTotal_B1P1_Total() {
		return subTotal_B1P1_Total;
	}

	public void setSubTotal_B1P1_Total(long subTotal_B1P1_Total) {
		this.subTotal_B1P1_Total = subTotal_B1P1_Total;
	}

}
