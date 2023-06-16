package cl.laaraucana.simat.entidades;

/*
 * Clase para resumen cotizaciones Previsionales, para bloque I, de cargo del fondo unico de prestaciones familiares y subsidio cesantia
 * parte II, fondos de Salud
 * */
public class RCP_FondoUnico_2_FondosSalud {

	//	parte 2 fondos de salud

	//siguientes 5 valores en cero
	private long ips_B1P2_repPrenatal;
	private long ips_B1P2_repPostNatal;
	private long ips_B1P2_repParental;
	private long ips_B1P2_perEnfmen1;
	private long ips_B1P2_Total;

	private long ccaf_B1P2_repPrenatal;
	private long ccaf_B1P2_repPostNatal;
	private long ccaf_B1P2_repParental;
	private long ccaf_B1P2_perEnfmen1;
	private long ccaf_B1P2_Total;

	//siguientes 5 valores en cero
	private long isp_B1P2_repPrenatal;
	private long isp_B1P2_repPostNatal;
	private long isp_B1P2_repParental;
	private long isp_B1P2_perEnfmen1;
	private long isp_B1P2_Total;

	//siguientes 5 valores en cero
	private long capredena_B1P2_repPrenatal;
	private long capredena_B1P2_repPostNatal;
	private long capredena_B1P2_repParental;
	private long capredena_B1P2_perEnfmen1;
	private long capredena_B1P2_Total;

	//siguientes 5 valores en cero
	private long dipreca_B1P2_repPrenatal;
	private long dipreca_B1P2_repPostNatal;
	private long dipreca_B1P2_repParental;
	private long dipreca_B1P2_perEnfmen1;
	private long dipreca_B1P2_Total;

	private long entidEmpleadora_B1P2_repPrenatal;
	private long entidEmpleadora_B1P2_repPostNatal;
	private long entidEmpleadora_B1P2_repParental;
	private long entidEmpleadora_B1P2_perEnfmen1;
	private long entidEmpleadora_B1P2_Total;

	private long subTotal_B1P2_repPrenatal;
	private long subTotal_B1P2_repPostNatal;
	private long subTotal_B1P2_repParental;
	private long subTotal_B1P2_perEnfmen1;
	private long subTotal_B1P2_Total;

	public RCP_FondoUnico_2_FondosSalud() {

	}

	public RCP_FondoUnico_2_FondosSalud(long ips_B1P2_repPrenatal, long ips_B1P2_repPostNatal, long ips_B1P2_repParental, long ips_B1P2_perEnfmen1, long ips_B1P2_Total, long ccaf_B1P2_repPrenatal,
			long ccaf_B1P2_repPostNatal, long ccaf_B1P2_repParental, long ccaf_B1P2_perEnfmen1, long ccaf_B1P2_Total, long isp_B1P2_repPrenatal, long isp_B1P2_repPostNatal, long isp_B1P2_repParental,
			long isp_B1P2_perEnfmen1, long isp_B1P2_Total, long capredena_B1P2_repPrenatal, long capredena_B1P2_repPostNatal, long capredena_B1P2_repParental, long capredena_B1P2_perEnfmen1,
			long capredena_B1P2_Total, long dipreca_B1P2_repPrenatal, long dipreca_B1P2_repPostNatal, long dipreca_B1P2_repParental, long dipreca_B1P2_perEnfmen1, long dipreca_B1P2_Total,
			long entidEmpleadora_B1P2_repPrenatal, long entidEmpleadora_B1P2_repPostNatal, long entidEmpleadora_B1P2_repParental, long entidEmpleadora_B1P2_perEnfmen1,
			long entidEmpleadora_B1P2_Total, long subTotal_B1P2_repPrenatal, long subTotal_B1P2_repPostNatal, long subTotal_B1P2_repParental, long subTotal_B1P2_perEnfmen1, long subTotal_B1P2_Total) {
		super();
		this.ips_B1P2_repPrenatal = ips_B1P2_repPrenatal;
		this.ips_B1P2_repPostNatal = ips_B1P2_repPostNatal;
		this.ips_B1P2_repParental = ips_B1P2_repParental;
		this.ips_B1P2_perEnfmen1 = ips_B1P2_perEnfmen1;
		this.ips_B1P2_Total = ips_B1P2_Total;
		this.ccaf_B1P2_repPrenatal = ccaf_B1P2_repPrenatal;
		this.ccaf_B1P2_repPostNatal = ccaf_B1P2_repPostNatal;
		this.ccaf_B1P2_repParental = ccaf_B1P2_repParental;
		this.ccaf_B1P2_perEnfmen1 = ccaf_B1P2_perEnfmen1;
		this.ccaf_B1P2_Total = ccaf_B1P2_Total;
		this.isp_B1P2_repPrenatal = isp_B1P2_repPrenatal;
		this.isp_B1P2_repPostNatal = isp_B1P2_repPostNatal;
		this.isp_B1P2_repParental = isp_B1P2_repParental;
		this.isp_B1P2_perEnfmen1 = isp_B1P2_perEnfmen1;
		this.isp_B1P2_Total = isp_B1P2_Total;
		this.capredena_B1P2_repPrenatal = capredena_B1P2_repPrenatal;
		this.capredena_B1P2_repPostNatal = capredena_B1P2_repPostNatal;
		this.capredena_B1P2_repParental = capredena_B1P2_repParental;
		this.capredena_B1P2_perEnfmen1 = capredena_B1P2_perEnfmen1;
		this.capredena_B1P2_Total = capredena_B1P2_Total;
		this.dipreca_B1P2_repPrenatal = dipreca_B1P2_repPrenatal;
		this.dipreca_B1P2_repPostNatal = dipreca_B1P2_repPostNatal;
		this.dipreca_B1P2_repParental = dipreca_B1P2_repParental;
		this.dipreca_B1P2_perEnfmen1 = dipreca_B1P2_perEnfmen1;
		this.dipreca_B1P2_Total = dipreca_B1P2_Total;
		this.entidEmpleadora_B1P2_repPrenatal = entidEmpleadora_B1P2_repPrenatal;
		this.entidEmpleadora_B1P2_repPostNatal = entidEmpleadora_B1P2_repPostNatal;
		this.entidEmpleadora_B1P2_repParental = entidEmpleadora_B1P2_repParental;
		this.entidEmpleadora_B1P2_perEnfmen1 = entidEmpleadora_B1P2_perEnfmen1;
		this.entidEmpleadora_B1P2_Total = entidEmpleadora_B1P2_Total;
		this.subTotal_B1P2_repPrenatal = subTotal_B1P2_repPrenatal;
		this.subTotal_B1P2_repPostNatal = subTotal_B1P2_repPostNatal;
		this.subTotal_B1P2_repParental = subTotal_B1P2_repParental;
		this.subTotal_B1P2_perEnfmen1 = subTotal_B1P2_perEnfmen1;
		this.subTotal_B1P2_Total = subTotal_B1P2_Total;
	}

	public long getCapredena_B1P2_perEnfmen1() {
		return capredena_B1P2_perEnfmen1;
	}

	public long getCapredena_B1P2_repParental() {
		return capredena_B1P2_repParental;
	}

	public long getCapredena_B1P2_repPostNatal() {
		return capredena_B1P2_repPostNatal;
	}

	public long getCapredena_B1P2_repPrenatal() {
		return capredena_B1P2_repPrenatal;
	}

	public long getCapredena_B1P2_Total() {
		return capredena_B1P2_Total;
	}

	public long getCcaf_B1P2_perEnfmen1() {
		return ccaf_B1P2_perEnfmen1;
	}

	public long getCcaf_B1P2_repParental() {
		return ccaf_B1P2_repParental;
	}

	public long getCcaf_B1P2_repPostNatal() {
		return ccaf_B1P2_repPostNatal;
	}

	public long getCcaf_B1P2_repPrenatal() {
		return ccaf_B1P2_repPrenatal;
	}

	public long getCcaf_B1P2_Total() {
		return ccaf_B1P2_Total;
	}

	public long getDipreca_B1P2_perEnfmen1() {
		return dipreca_B1P2_perEnfmen1;
	}

	public long getDipreca_B1P2_repParental() {
		return dipreca_B1P2_repParental;
	}

	public long getDipreca_B1P2_repPostNatal() {
		return dipreca_B1P2_repPostNatal;
	}

	public long getDipreca_B1P2_repPrenatal() {
		return dipreca_B1P2_repPrenatal;
	}

	public long getDipreca_B1P2_Total() {
		return dipreca_B1P2_Total;
	}

	public long getEntidEmpleadora_B1P2_perEnfmen1() {
		return entidEmpleadora_B1P2_perEnfmen1;
	}

	public long getEntidEmpleadora_B1P2_repParental() {
		return entidEmpleadora_B1P2_repParental;
	}

	public long getEntidEmpleadora_B1P2_repPostNatal() {
		return entidEmpleadora_B1P2_repPostNatal;
	}

	public long getEntidEmpleadora_B1P2_repPrenatal() {
		return entidEmpleadora_B1P2_repPrenatal;
	}

	public long getEntidEmpleadora_B1P2_Total() {
		return entidEmpleadora_B1P2_Total;
	}

	public long getIps_B1P2_perEnfmen1() {
		return ips_B1P2_perEnfmen1;
	}

	public long getIps_B1P2_repParental() {
		return ips_B1P2_repParental;
	}

	public long getIps_B1P2_repPostNatal() {
		return ips_B1P2_repPostNatal;
	}

	public long getIps_B1P2_repPrenatal() {
		return ips_B1P2_repPrenatal;
	}

	public long getIps_B1P2_Total() {
		return ips_B1P2_Total;
	}

	public long getIsp_B1P2_perEnfmen1() {
		return isp_B1P2_perEnfmen1;
	}

	public long getIsp_B1P2_repParental() {
		return isp_B1P2_repParental;
	}

	public long getIsp_B1P2_repPostNatal() {
		return isp_B1P2_repPostNatal;
	}

	public long getIsp_B1P2_repPrenatal() {
		return isp_B1P2_repPrenatal;
	}

	public long getIsp_B1P2_Total() {
		return isp_B1P2_Total;
	}

	public long getSubTotal_B1P2_perEnfmen1() {
		return subTotal_B1P2_perEnfmen1;
	}

	public long getSubTotal_B1P2_repParental() {
		return subTotal_B1P2_repParental;
	}

	public long getSubTotal_B1P2_repPostNatal() {
		return subTotal_B1P2_repPostNatal;
	}

	public long getSubTotal_B1P2_repPrenatal() {
		return subTotal_B1P2_repPrenatal;
	}

	public long getSubTotal_B1P2_Total() {
		return subTotal_B1P2_Total;
	}

	public void setCapredena_B1P2_perEnfmen1(long capredena_B1P2_perEnfmen1) {
		this.capredena_B1P2_perEnfmen1 = capredena_B1P2_perEnfmen1;
	}

	public void setCapredena_B1P2_repParental(long capredena_B1P2_repParental) {
		this.capredena_B1P2_repParental = capredena_B1P2_repParental;
	}

	public void setCapredena_B1P2_repPostNatal(long capredena_B1P2_repPostNatal) {
		this.capredena_B1P2_repPostNatal = capredena_B1P2_repPostNatal;
	}

	public void setCapredena_B1P2_repPrenatal(long capredena_B1P2_repPrenatal) {
		this.capredena_B1P2_repPrenatal = capredena_B1P2_repPrenatal;
	}

	public void setCapredena_B1P2_Total(long capredena_B1P2_Total) {
		this.capredena_B1P2_Total = capredena_B1P2_Total;
	}

	public void setCcaf_B1P2_perEnfmen1(long ccaf_B1P2_perEnfmen1) {
		this.ccaf_B1P2_perEnfmen1 = ccaf_B1P2_perEnfmen1;
	}

	public void setCcaf_B1P2_repParental(long ccaf_B1P2_repParental) {
		this.ccaf_B1P2_repParental = ccaf_B1P2_repParental;
	}

	public void setCcaf_B1P2_repPostNatal(long ccaf_B1P2_repPostNatal) {
		this.ccaf_B1P2_repPostNatal = ccaf_B1P2_repPostNatal;
	}

	public void setCcaf_B1P2_repPrenatal(long ccaf_B1P2_repPrenatal) {
		this.ccaf_B1P2_repPrenatal = ccaf_B1P2_repPrenatal;
	}

	public void setCcaf_B1P2_Total(long ccaf_B1P2_Total) {
		this.ccaf_B1P2_Total = ccaf_B1P2_Total;
	}

	public void setDipreca_B1P2_perEnfmen1(long dipreca_B1P2_perEnfmen1) {
		this.dipreca_B1P2_perEnfmen1 = dipreca_B1P2_perEnfmen1;
	}

	public void setDipreca_B1P2_repParental(long dipreca_B1P2_repParental) {
		this.dipreca_B1P2_repParental = dipreca_B1P2_repParental;
	}

	public void setDipreca_B1P2_repPostNatal(long dipreca_B1P2_repPostNatal) {
		this.dipreca_B1P2_repPostNatal = dipreca_B1P2_repPostNatal;
	}

	public void setDipreca_B1P2_repPrenatal(long dipreca_B1P2_repPrenatal) {
		this.dipreca_B1P2_repPrenatal = dipreca_B1P2_repPrenatal;
	}

	public void setDipreca_B1P2_Total(long dipreca_B1P2_Total) {
		this.dipreca_B1P2_Total = dipreca_B1P2_Total;
	}

	public void setEntidEmpleadora_B1P2_perEnfmen1(long entidEmpleadora_B1P2_perEnfmen1) {
		this.entidEmpleadora_B1P2_perEnfmen1 = entidEmpleadora_B1P2_perEnfmen1;
	}

	public void setEntidEmpleadora_B1P2_repParental(long entidEmpleadora_B1P2_repParental) {
		this.entidEmpleadora_B1P2_repParental = entidEmpleadora_B1P2_repParental;
	}

	public void setEntidEmpleadora_B1P2_repPostNatal(long entidEmpleadora_B1P2_repPostNatal) {
		this.entidEmpleadora_B1P2_repPostNatal = entidEmpleadora_B1P2_repPostNatal;
	}

	public void setEntidEmpleadora_B1P2_repPrenatal(long entidEmpleadora_B1P2_repPrenatal) {
		this.entidEmpleadora_B1P2_repPrenatal = entidEmpleadora_B1P2_repPrenatal;
	}

	public void setEntidEmpleadora_B1P2_Total(long entidEmpleadora_B1P2_Total) {
		this.entidEmpleadora_B1P2_Total = entidEmpleadora_B1P2_Total;
	}

	public void setIps_B1P2_perEnfmen1(long ips_B1P2_perEnfmen1) {
		this.ips_B1P2_perEnfmen1 = ips_B1P2_perEnfmen1;
	}

	public void setIps_B1P2_repParental(long ips_B1P2_repParental) {
		this.ips_B1P2_repParental = ips_B1P2_repParental;
	}

	public void setIps_B1P2_repPostNatal(long ips_B1P2_repPostNatal) {
		this.ips_B1P2_repPostNatal = ips_B1P2_repPostNatal;
	}

	public void setIps_B1P2_repPrenatal(long ips_B1P2_repPrenatal) {
		this.ips_B1P2_repPrenatal = ips_B1P2_repPrenatal;
	}

	public void setIps_B1P2_Total(long ips_B1P2_Total) {
		this.ips_B1P2_Total = ips_B1P2_Total;
	}

	public void setIsp_B1P2_perEnfmen1(long isp_B1P2_perEnfmen1) {
		this.isp_B1P2_perEnfmen1 = isp_B1P2_perEnfmen1;
	}

	public void setIsp_B1P2_repParental(long isp_B1P2_repParental) {
		this.isp_B1P2_repParental = isp_B1P2_repParental;
	}

	public void setIsp_B1P2_repPostNatal(long isp_B1P2_repPostNatal) {
		this.isp_B1P2_repPostNatal = isp_B1P2_repPostNatal;
	}

	public void setIsp_B1P2_repPrenatal(long isp_B1P2_repPrenatal) {
		this.isp_B1P2_repPrenatal = isp_B1P2_repPrenatal;
	}

	public void setIsp_B1P2_Total(long isp_B1P2_Total) {
		this.isp_B1P2_Total = isp_B1P2_Total;
	}

	public void setSubTotal_B1P2_perEnfmen1(long subTotal_B1P2_perEnfmen1) {
		this.subTotal_B1P2_perEnfmen1 = subTotal_B1P2_perEnfmen1;
	}

	public void setSubTotal_B1P2_repParental(long subTotal_B1P2_repParental) {
		this.subTotal_B1P2_repParental = subTotal_B1P2_repParental;
	}

	public void setSubTotal_B1P2_repPostNatal(long subTotal_B1P2_repPostNatal) {
		this.subTotal_B1P2_repPostNatal = subTotal_B1P2_repPostNatal;
	}

	public void setSubTotal_B1P2_repPrenatal(long subTotal_B1P2_repPrenatal) {
		this.subTotal_B1P2_repPrenatal = subTotal_B1P2_repPrenatal;
	}

	public void setSubTotal_B1P2_Total(long subTotal_B1P2_Total) {
		this.subTotal_B1P2_Total = subTotal_B1P2_Total;
	}

}
