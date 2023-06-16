package cl.laaraucana.simat.entidades;

/*
 * Clase para resumen cotizaciones Previsionales, para bloque II de cargo de la beneficiaria
 * parte II, fondos de Salud
 * */
public class RCP_CargoBenef_2_FondosSalud {

	//bloque II
	//	parte 2
	//valores siempre a cero
	private long ips_B2P2_repPrenatal;
	private long ips_B2P2_repPostnatal;
	private long ips_B2P2_repParental;
	private long ips_B2P2_perEnfmen1;
	private long ips_B2P2_Total;

	private long subTotal_B2P2_repPrenatal;
	private long subTotal_B2P2_repPostNatal;
	private long subTotal_B2P2_repParental;
	private long subTotal_B2P2_perEnfmen1;
	private long subTotal_B2P2_Total;

	public RCP_CargoBenef_2_FondosSalud() {

	}

	public RCP_CargoBenef_2_FondosSalud(long ips_B2P2_repPrenatal, long ips_B2P2_repPostnatal, long ips_B2P2_repParental, long ips_B2P2_perEnfmen1, long ips_B2P2_Total,
			long subTotal_B2P2_repPrenatal, long subTotal_B2P2_repPostNatal, long subTotal_B2P2_repParental, long subTotal_B2P2_perEnfmen1, long subTotal_B2P2_Total) {
		super();
		this.ips_B2P2_repPrenatal = ips_B2P2_repPrenatal;
		this.ips_B2P2_repPostnatal = ips_B2P2_repPostnatal;
		this.ips_B2P2_repParental = ips_B2P2_repParental;
		this.ips_B2P2_perEnfmen1 = ips_B2P2_perEnfmen1;
		this.ips_B2P2_Total = ips_B2P2_Total;
		this.subTotal_B2P2_repPrenatal = subTotal_B2P2_repPrenatal;
		this.subTotal_B2P2_repPostNatal = subTotal_B2P2_repPostNatal;
		this.subTotal_B2P2_repParental = subTotal_B2P2_repParental;
		this.subTotal_B2P2_perEnfmen1 = subTotal_B2P2_perEnfmen1;
		this.subTotal_B2P2_Total = subTotal_B2P2_Total;
	}

	public long getIps_B2P2_perEnfmen1() {
		return ips_B2P2_perEnfmen1;
	}

	public long getIps_B2P2_repParental() {
		return ips_B2P2_repParental;
	}

	public long getIps_B2P2_repPostnatal() {
		return ips_B2P2_repPostnatal;
	}

	public long getIps_B2P2_repPrenatal() {
		return ips_B2P2_repPrenatal;
	}

	public long getIps_B2P2_Total() {
		return ips_B2P2_Total;
	}

	public long getSubTotal_B2P2_perEnfmen1() {
		return subTotal_B2P2_perEnfmen1;
	}

	public long getSubTotal_B2P2_repParental() {
		return subTotal_B2P2_repParental;
	}

	public long getSubTotal_B2P2_repPostNatal() {
		return subTotal_B2P2_repPostNatal;
	}

	public long getSubTotal_B2P2_repPrenatal() {
		return subTotal_B2P2_repPrenatal;
	}

	public long getSubTotal_B2P2_Total() {
		return subTotal_B2P2_Total;
	}

	public void setIps_B2P2_perEnfmen1(long ips_B2P2_perEnfmen1) {
		this.ips_B2P2_perEnfmen1 = ips_B2P2_perEnfmen1;
	}

	public void setIps_B2P2_repParental(long ips_B2P2_repParental) {
		this.ips_B2P2_repParental = ips_B2P2_repParental;
	}

	public void setIps_B2P2_repPostnatal(long ips_B2P2_repPostnatal) {
		this.ips_B2P2_repPostnatal = ips_B2P2_repPostnatal;
	}

	public void setIps_B2P2_repPrenatal(long ips_B2P2_repPrenatal) {
		this.ips_B2P2_repPrenatal = ips_B2P2_repPrenatal;
	}

	public void setIps_B2P2_Total(long ips_B2P2_Total) {
		this.ips_B2P2_Total = ips_B2P2_Total;
	}

	public void setSubTotal_B2P2_perEnfmen1(long subTotal_B2P2_perEnfmen1) {
		this.subTotal_B2P2_perEnfmen1 = subTotal_B2P2_perEnfmen1;
	}

	public void setSubTotal_B2P2_repParental(long subTotal_B2P2_repParental) {
		this.subTotal_B2P2_repParental = subTotal_B2P2_repParental;
	}

	public void setSubTotal_B2P2_repPostNatal(long subTotal_B2P2_repPostNatal) {
		this.subTotal_B2P2_repPostNatal = subTotal_B2P2_repPostNatal;
	}

	public void setSubTotal_B2P2_repPrenatal(long subTotal_B2P2_repPrenatal) {
		this.subTotal_B2P2_repPrenatal = subTotal_B2P2_repPrenatal;
	}

	public void setSubTotal_B2P2_Total(long subTotal_B2P2_Total) {
		this.subTotal_B2P2_Total = subTotal_B2P2_Total;
	}

}
