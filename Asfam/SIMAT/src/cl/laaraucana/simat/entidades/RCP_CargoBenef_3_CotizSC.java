package cl.laaraucana.simat.entidades;

/*
 * Clase para resumen cotizaciones Previsionales, para bloque II de cargo de la beneficiaria
 * parte III, cotizaciones seguro cesantia
 * */
public class RCP_CargoBenef_3_CotizSC {

	//	parte 3
	private long afp_Cuprum_B2P3_repPreNatal;
	private long afp_Cuprum_B2P3_repPostNatal;
	private long afp_Cuprum_B2P3_repParental;
	private long afp_Cuprum_B2P3_perEnfmen1;
	private long afp_Cuprum_B2P3_Total;

	private long afp_Habitat_B2P3_repPrenatal;
	private long afp_Habitat_B2P3_repPostNatal;
	private long afp_Habitat_B2P3_repParental;
	private long afp_Habitat_B2P3_perEnfmen1;
	private long afp_Habitat_B2P3_Total;

	private long afp_PlanVital_B2P3_repPrenatal;
	private long afp_PlanVital_B2P3_repPostNatal;
	private long afp_PlanVital_B2P3_repParental;
	private long afp_PlanVital_B2P3_perEnfmen1;
	private long afp_PlanVital_B2P3_Total;

	private long afp_Provida_B2P3_repPrenatal;
	private long afp_Provida_B2P3_repPostNatal;
	private long afp_Provida_B2P3_repParental;
	private long afp_Provida_B2P3_perEnfmen1;
	private long afp_Provida_B2P3_Total;

	private long afp_Capital_B2P3_repPrenatal;
	private long afp_Capital_B2P3_repPostNatal;
	private long afp_Capital_B2P3_repParental;
	private long afp_Capital_B2P3_perEnfmen1;
	private long afp_Capital_B2P3_Total;

	private long afp_Modelo_B2P3_repPrenatal;
	private long afp_Modelo_B2P3_repPostNatal;
	private long afp_Modelo_B2P3_repParental;
	private long afp_Modelo_B2P3_perEnfmen1;
	private long afp_Modelo_B2P3_Total;

	private long entidEmpleadora_B2P3_repPrenatal;
	private long entidEmpleadora_B2P3_repPostNatal;
	private long entidEmpleadora_B2P3_repParental;
	private long entidEmpleadora_B2P3_perEnfmen1;
	private long entidEmpleadora_B2P3_Total;

	//fila subtotales
	private long subTotal_B2P3_repPrenatal;
	private long subTotal_B2P3_repPostNatal;
	private long subTotal_B2P3_repParental;
	private long subTotal_B2P3_perEnfmen1;
	private long subTotal_B2P3_Total;

	public RCP_CargoBenef_3_CotizSC() {

	}

	public RCP_CargoBenef_3_CotizSC(long afp_Cuprum_B2P3_repPreNatal, long afp_Cuprum_B2P3_repPostNatal, long afp_Cuprum_B2P3_repParental, long afp_Cuprum_B2P3_perEnfmen1, long afp_Cuprum_B2P3_Total,
			long afp_Habitat_B2P3_repPrenatal, long afp_Habitat_B2P3_repPostNatal, long afp_Habitat_B2P3_repParental, long afp_Habitat_B2P3_perEnfmen1, long afp_Habitat_B2P3_Total,
			long afp_PlanVital_B2P3_repPrenatal, long afp_PlanVital_B2P3_repPostNatal, long afp_PlanVital_B2P3_repParental, long afp_PlanVital_B2P3_perEnfmen1, long afp_PlanVital_B2P3_Total,
			long afp_Provida_B2P3_repPrenatal, long afp_Provida_B2P3_repPostNatal, long afp_Provida_B2P3_repParental, long afp_Provida_B2P3_perEnfmen1, long afp_Provida_B2P3_Total,
			long afp_Capital_B2P3_repPrenatal, long afp_Capital_B2P3_repPostNatal, long afp_Capital_B2P3_repParental, long afp_Capital_B2P3_perEnfmen1, long afp_Capital_B2P3_Total,
			long afp_Modelo_B2P3_repPrenatal, long afp_Modelo_B2P3_repPostNatal, long afp_Modelo_B2P3_repParental, long afp_Modelo_B2P3_perEnfmen1, long afp_Modelo_B2P3_Total,
			long entidEmpleadora_B2P3_repPrenatal, long entidEmpleadora_B2P3_repPostNatal, long entidEmpleadora_B2P3_repParental, long entidEmpleadora_B2P3_perEnfmen1,
			long entidEmpleadora_B2P3_Total, long subTotal_B2P3_repPrenatal, long subTotal_B2P3_repPostNatal, long subTotal_B2P3_repParental, long subTotal_B2P3_perEnfmen1, long subTotal_B2P3_Total) {
		super();
		this.afp_Cuprum_B2P3_repPreNatal = afp_Cuprum_B2P3_repPreNatal;
		this.afp_Cuprum_B2P3_repPostNatal = afp_Cuprum_B2P3_repPostNatal;
		this.afp_Cuprum_B2P3_repParental = afp_Cuprum_B2P3_repParental;
		this.afp_Cuprum_B2P3_perEnfmen1 = afp_Cuprum_B2P3_perEnfmen1;
		this.afp_Cuprum_B2P3_Total = afp_Cuprum_B2P3_Total;
		this.afp_Habitat_B2P3_repPrenatal = afp_Habitat_B2P3_repPrenatal;
		this.afp_Habitat_B2P3_repPostNatal = afp_Habitat_B2P3_repPostNatal;
		this.afp_Habitat_B2P3_repParental = afp_Habitat_B2P3_repParental;
		this.afp_Habitat_B2P3_perEnfmen1 = afp_Habitat_B2P3_perEnfmen1;
		this.afp_Habitat_B2P3_Total = afp_Habitat_B2P3_Total;
		this.afp_PlanVital_B2P3_repPrenatal = afp_PlanVital_B2P3_repPrenatal;
		this.afp_PlanVital_B2P3_repPostNatal = afp_PlanVital_B2P3_repPostNatal;
		this.afp_PlanVital_B2P3_repParental = afp_PlanVital_B2P3_repParental;
		this.afp_PlanVital_B2P3_perEnfmen1 = afp_PlanVital_B2P3_perEnfmen1;
		this.afp_PlanVital_B2P3_Total = afp_PlanVital_B2P3_Total;
		this.afp_Provida_B2P3_repPrenatal = afp_Provida_B2P3_repPrenatal;
		this.afp_Provida_B2P3_repPostNatal = afp_Provida_B2P3_repPostNatal;
		this.afp_Provida_B2P3_repParental = afp_Provida_B2P3_repParental;
		this.afp_Provida_B2P3_perEnfmen1 = afp_Provida_B2P3_perEnfmen1;
		this.afp_Provida_B2P3_Total = afp_Provida_B2P3_Total;
		this.afp_Capital_B2P3_repPrenatal = afp_Capital_B2P3_repPrenatal;
		this.afp_Capital_B2P3_repPostNatal = afp_Capital_B2P3_repPostNatal;
		this.afp_Capital_B2P3_repParental = afp_Capital_B2P3_repParental;
		this.afp_Capital_B2P3_perEnfmen1 = afp_Capital_B2P3_perEnfmen1;
		this.afp_Capital_B2P3_Total = afp_Capital_B2P3_Total;
		this.afp_Modelo_B2P3_repPrenatal = afp_Modelo_B2P3_repPrenatal;
		this.afp_Modelo_B2P3_repPostNatal = afp_Modelo_B2P3_repPostNatal;
		this.afp_Modelo_B2P3_repParental = afp_Modelo_B2P3_repParental;
		this.afp_Modelo_B2P3_perEnfmen1 = afp_Modelo_B2P3_perEnfmen1;
		this.afp_Modelo_B2P3_Total = afp_Modelo_B2P3_Total;
		this.entidEmpleadora_B2P3_repPrenatal = entidEmpleadora_B2P3_repPrenatal;
		this.entidEmpleadora_B2P3_repPostNatal = entidEmpleadora_B2P3_repPostNatal;
		this.entidEmpleadora_B2P3_repParental = entidEmpleadora_B2P3_repParental;
		this.entidEmpleadora_B2P3_perEnfmen1 = entidEmpleadora_B2P3_perEnfmen1;
		this.entidEmpleadora_B2P3_Total = entidEmpleadora_B2P3_Total;
		this.subTotal_B2P3_repPrenatal = subTotal_B2P3_repPrenatal;
		this.subTotal_B2P3_repPostNatal = subTotal_B2P3_repPostNatal;
		this.subTotal_B2P3_repParental = subTotal_B2P3_repParental;
		this.subTotal_B2P3_perEnfmen1 = subTotal_B2P3_perEnfmen1;
		this.subTotal_B2P3_Total = subTotal_B2P3_Total;
	}

	public long getAfp_Capital_B2P3_perEnfmen1() {
		return afp_Capital_B2P3_perEnfmen1;
	}

	public long getAfp_Capital_B2P3_repParental() {
		return afp_Capital_B2P3_repParental;
	}

	public long getAfp_Capital_B2P3_repPostNatal() {
		return afp_Capital_B2P3_repPostNatal;
	}

	public long getAfp_Capital_B2P3_repPrenatal() {
		return afp_Capital_B2P3_repPrenatal;
	}

	public long getAfp_Capital_B2P3_Total() {
		return afp_Capital_B2P3_Total;
	}

	public long getAfp_Cuprum_B2P3_perEnfmen1() {
		return afp_Cuprum_B2P3_perEnfmen1;
	}

	public long getAfp_Cuprum_B2P3_repParental() {
		return afp_Cuprum_B2P3_repParental;
	}

	public long getAfp_Cuprum_B2P3_repPostNatal() {
		return afp_Cuprum_B2P3_repPostNatal;
	}

	public long getAfp_Cuprum_B2P3_repPreNatal() {
		return afp_Cuprum_B2P3_repPreNatal;
	}

	public long getAfp_Cuprum_B2P3_Total() {
		return afp_Cuprum_B2P3_Total;
	}

	public long getAfp_Habitat_B2P3_perEnfmen1() {
		return afp_Habitat_B2P3_perEnfmen1;
	}

	public long getAfp_Habitat_B2P3_repParental() {
		return afp_Habitat_B2P3_repParental;
	}

	public long getAfp_Habitat_B2P3_repPostNatal() {
		return afp_Habitat_B2P3_repPostNatal;
	}

	public long getAfp_Habitat_B2P3_repPrenatal() {
		return afp_Habitat_B2P3_repPrenatal;
	}

	public long getAfp_Habitat_B2P3_Total() {
		return afp_Habitat_B2P3_Total;
	}

	public long getAfp_Modelo_B2P3_perEnfmen1() {
		return afp_Modelo_B2P3_perEnfmen1;
	}

	public long getAfp_Modelo_B2P3_repParental() {
		return afp_Modelo_B2P3_repParental;
	}

	public long getAfp_Modelo_B2P3_repPostNatal() {
		return afp_Modelo_B2P3_repPostNatal;
	}

	public long getAfp_Modelo_B2P3_repPrenatal() {
		return afp_Modelo_B2P3_repPrenatal;
	}

	public long getAfp_Modelo_B2P3_Total() {
		return afp_Modelo_B2P3_Total;
	}

	public long getAfp_PlanVital_B2P3_perEnfmen1() {
		return afp_PlanVital_B2P3_perEnfmen1;
	}

	public long getAfp_PlanVital_B2P3_repParental() {
		return afp_PlanVital_B2P3_repParental;
	}

	public long getAfp_PlanVital_B2P3_repPostNatal() {
		return afp_PlanVital_B2P3_repPostNatal;
	}

	public long getAfp_PlanVital_B2P3_repPrenatal() {
		return afp_PlanVital_B2P3_repPrenatal;
	}

	public long getAfp_PlanVital_B2P3_Total() {
		return afp_PlanVital_B2P3_Total;
	}

	public long getAfp_Provida_B2P3_perEnfmen1() {
		return afp_Provida_B2P3_perEnfmen1;
	}

	public long getAfp_Provida_B2P3_repParental() {
		return afp_Provida_B2P3_repParental;
	}

	public long getAfp_Provida_B2P3_repPostNatal() {
		return afp_Provida_B2P3_repPostNatal;
	}

	public long getAfp_Provida_B2P3_repPrenatal() {
		return afp_Provida_B2P3_repPrenatal;
	}

	public long getAfp_Provida_B2P3_Total() {
		return afp_Provida_B2P3_Total;
	}

	public long getEntidEmpleadora_B2P3_perEnfmen1() {
		return entidEmpleadora_B2P3_perEnfmen1;
	}

	public long getEntidEmpleadora_B2P3_repParental() {
		return entidEmpleadora_B2P3_repParental;
	}

	public long getEntidEmpleadora_B2P3_repPostNatal() {
		return entidEmpleadora_B2P3_repPostNatal;
	}

	public long getEntidEmpleadora_B2P3_repPrenatal() {
		return entidEmpleadora_B2P3_repPrenatal;
	}

	public long getEntidEmpleadora_B2P3_Total() {
		return entidEmpleadora_B2P3_Total;
	}

	public long getSubTotal_B2P3_perEnfmen1() {
		return subTotal_B2P3_perEnfmen1;
	}

	public long getSubTotal_B2P3_repParental() {
		return subTotal_B2P3_repParental;
	}

	public long getSubTotal_B2P3_repPostNatal() {
		return subTotal_B2P3_repPostNatal;
	}

	public long getSubTotal_B2P3_repPrenatal() {
		return subTotal_B2P3_repPrenatal;
	}

	public long getSubTotal_B2P3_Total() {
		return subTotal_B2P3_Total;
	}

	public void setAfp_Capital_B2P3_perEnfmen1(long afp_Capital_B2P3_perEnfmen1) {
		this.afp_Capital_B2P3_perEnfmen1 = afp_Capital_B2P3_perEnfmen1;
	}

	public void setAfp_Capital_B2P3_repParental(long afp_Capital_B2P3_repParental) {
		this.afp_Capital_B2P3_repParental = afp_Capital_B2P3_repParental;
	}

	public void setAfp_Capital_B2P3_repPostNatal(long afp_Capital_B2P3_repPostNatal) {
		this.afp_Capital_B2P3_repPostNatal = afp_Capital_B2P3_repPostNatal;
	}

	public void setAfp_Capital_B2P3_repPrenatal(long afp_Capital_B2P3_repPrenatal) {
		this.afp_Capital_B2P3_repPrenatal = afp_Capital_B2P3_repPrenatal;
	}

	public void setAfp_Capital_B2P3_Total(long afp_Capital_B2P3_Total) {
		this.afp_Capital_B2P3_Total = afp_Capital_B2P3_Total;
	}

	public void setAfp_Cuprum_B2P3_perEnfmen1(long afp_Cuprum_B2P3_perEnfmen1) {
		this.afp_Cuprum_B2P3_perEnfmen1 = afp_Cuprum_B2P3_perEnfmen1;
	}

	public void setAfp_Cuprum_B2P3_repParental(long afp_Cuprum_B2P3_repParental) {
		this.afp_Cuprum_B2P3_repParental = afp_Cuprum_B2P3_repParental;
	}

	public void setAfp_Cuprum_B2P3_repPostNatal(long afp_Cuprum_B2P3_repPostNatal) {
		this.afp_Cuprum_B2P3_repPostNatal = afp_Cuprum_B2P3_repPostNatal;
	}

	public void setAfp_Cuprum_B2P3_repPreNatal(long afp_Cuprum_B2P3_repPreNatal) {
		this.afp_Cuprum_B2P3_repPreNatal = afp_Cuprum_B2P3_repPreNatal;
	}

	public void setAfp_Cuprum_B2P3_Total(long afp_Cuprum_B2P3_Total) {
		this.afp_Cuprum_B2P3_Total = afp_Cuprum_B2P3_Total;
	}

	public void setAfp_Habitat_B2P3_perEnfmen1(long afp_Habitat_B2P3_perEnfmen1) {
		this.afp_Habitat_B2P3_perEnfmen1 = afp_Habitat_B2P3_perEnfmen1;
	}

	public void setAfp_Habitat_B2P3_repParental(long afp_Habitat_B2P3_repParental) {
		this.afp_Habitat_B2P3_repParental = afp_Habitat_B2P3_repParental;
	}

	public void setAfp_Habitat_B2P3_repPostNatal(long afp_Habitat_B2P3_repPostNatal) {
		this.afp_Habitat_B2P3_repPostNatal = afp_Habitat_B2P3_repPostNatal;
	}

	public void setAfp_Habitat_B2P3_repPrenatal(long afp_Habitat_B2P3_repPrenatal) {
		this.afp_Habitat_B2P3_repPrenatal = afp_Habitat_B2P3_repPrenatal;
	}

	public void setAfp_Habitat_B2P3_Total(long afp_Habitat_B2P3_Total) {
		this.afp_Habitat_B2P3_Total = afp_Habitat_B2P3_Total;
	}

	public void setAfp_Modelo_B2P3_perEnfmen1(long afp_Modelo_B2P3_perEnfmen1) {
		this.afp_Modelo_B2P3_perEnfmen1 = afp_Modelo_B2P3_perEnfmen1;
	}

	public void setAfp_Modelo_B2P3_repParental(long afp_Modelo_B2P3_repParental) {
		this.afp_Modelo_B2P3_repParental = afp_Modelo_B2P3_repParental;
	}

	public void setAfp_Modelo_B2P3_repPostNatal(long afp_Modelo_B2P3_repPostNatal) {
		this.afp_Modelo_B2P3_repPostNatal = afp_Modelo_B2P3_repPostNatal;
	}

	public void setAfp_Modelo_B2P3_repPrenatal(long afp_Modelo_B2P3_repPrenatal) {
		this.afp_Modelo_B2P3_repPrenatal = afp_Modelo_B2P3_repPrenatal;
	}

	public void setAfp_Modelo_B2P3_Total(long afp_Modelo_B2P3_Total) {
		this.afp_Modelo_B2P3_Total = afp_Modelo_B2P3_Total;
	}

	public void setAfp_PlanVital_B2P3_perEnfmen1(long afp_PlanVital_B2P3_perEnfmen1) {
		this.afp_PlanVital_B2P3_perEnfmen1 = afp_PlanVital_B2P3_perEnfmen1;
	}

	public void setAfp_PlanVital_B2P3_repParental(long afp_PlanVital_B2P3_repParental) {
		this.afp_PlanVital_B2P3_repParental = afp_PlanVital_B2P3_repParental;
	}

	public void setAfp_PlanVital_B2P3_repPostNatal(long afp_PlanVital_B2P3_repPostNatal) {
		this.afp_PlanVital_B2P3_repPostNatal = afp_PlanVital_B2P3_repPostNatal;
	}

	public void setAfp_PlanVital_B2P3_repPrenatal(long afp_PlanVital_B2P3_repPrenatal) {
		this.afp_PlanVital_B2P3_repPrenatal = afp_PlanVital_B2P3_repPrenatal;
	}

	public void setAfp_PlanVital_B2P3_Total(long afp_PlanVital_B2P3_Total) {
		this.afp_PlanVital_B2P3_Total = afp_PlanVital_B2P3_Total;
	}

	public void setAfp_Provida_B2P3_perEnfmen1(long afp_Provida_B2P3_perEnfmen1) {
		this.afp_Provida_B2P3_perEnfmen1 = afp_Provida_B2P3_perEnfmen1;
	}

	public void setAfp_Provida_B2P3_repParental(long afp_Provida_B2P3_repParental) {
		this.afp_Provida_B2P3_repParental = afp_Provida_B2P3_repParental;
	}

	public void setAfp_Provida_B2P3_repPostNatal(long afp_Provida_B2P3_repPostNatal) {
		this.afp_Provida_B2P3_repPostNatal = afp_Provida_B2P3_repPostNatal;
	}

	public void setAfp_Provida_B2P3_repPrenatal(long afp_Provida_B2P3_repPrenatal) {
		this.afp_Provida_B2P3_repPrenatal = afp_Provida_B2P3_repPrenatal;
	}

	public void setAfp_Provida_B2P3_Total(long afp_Provida_B2P3_Total) {
		this.afp_Provida_B2P3_Total = afp_Provida_B2P3_Total;
	}

	public void setEntidEmpleadora_B2P3_perEnfmen1(long entidEmpleadora_B2P3_perEnfmen1) {
		this.entidEmpleadora_B2P3_perEnfmen1 = entidEmpleadora_B2P3_perEnfmen1;
	}

	public void setEntidEmpleadora_B2P3_repParental(long entidEmpleadora_B2P3_repParental) {
		this.entidEmpleadora_B2P3_repParental = entidEmpleadora_B2P3_repParental;
	}

	public void setEntidEmpleadora_B2P3_repPostNatal(long entidEmpleadora_B2P3_repPostNatal) {
		this.entidEmpleadora_B2P3_repPostNatal = entidEmpleadora_B2P3_repPostNatal;
	}

	public void setEntidEmpleadora_B2P3_repPrenatal(long entidEmpleadora_B2P3_repPrenatal) {
		this.entidEmpleadora_B2P3_repPrenatal = entidEmpleadora_B2P3_repPrenatal;
	}

	public void setEntidEmpleadora_B2P3_Total(long entidEmpleadora_B2P3_Total) {
		this.entidEmpleadora_B2P3_Total = entidEmpleadora_B2P3_Total;
	}

	public void setSubTotal_B2P3_perEnfmen1(long subTotal_B2P3_perEnfmen1) {
		this.subTotal_B2P3_perEnfmen1 = subTotal_B2P3_perEnfmen1;
	}

	public void setSubTotal_B2P3_repParental(long subTotal_B2P3_repParental) {
		this.subTotal_B2P3_repParental = subTotal_B2P3_repParental;
	}

	public void setSubTotal_B2P3_repPostNatal(long subTotal_B2P3_repPostNatal) {
		this.subTotal_B2P3_repPostNatal = subTotal_B2P3_repPostNatal;
	}

	public void setSubTotal_B2P3_repPrenatal(long subTotal_B2P3_repPrenatal) {
		this.subTotal_B2P3_repPrenatal = subTotal_B2P3_repPrenatal;
	}

	public void setSubTotal_B2P3_Total(long subTotal_B2P3_Total) {
		this.subTotal_B2P3_Total = subTotal_B2P3_Total;
	}

}
