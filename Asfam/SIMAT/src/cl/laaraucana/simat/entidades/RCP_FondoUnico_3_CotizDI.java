package cl.laaraucana.simat.entidades;

/*
 * Clase para resumen cotizaciones Previsionales, para bloque I, de cargo del fondo unico de prestaciones familiares y subsidio cesantia
 * parte III, Cotizaciones desahucio e Indemnizaciones
 * */
public class RCP_FondoUnico_3_CotizDI {

	//bloque II
	//parte 3
	//valores siempre a cero.
	private long ips_B1P3_repPrenatal;
	private long ips_B1P3_repPostNatal;
	private long ips_B1P3_repParental;
	private long ips_B1P3_perEnfmen1;
	private long ips_B1P3_Total;

	//valores siempre a cero.
	private long entidEmpleadora_B1P3_repPrenatal;
	private long entidEmpleadora_B1P3_repPostNatal;
	private long entidEmpleadora_B1P3_repParental;
	private long entidEmpleadora_B1P3_perEnfmen1;
	private long entidEmpleadora_B1P3_Total;

	private long subTotal_B1P3_repPrenatal;
	private long subTotal_B1P3_repPostNatal;
	private long subTotal_B1P3_repParental;
	private long subTotal_B1P3_perEnfmen1;
	private long subTotal_B1P3_Total;

	public RCP_FondoUnico_3_CotizDI() {

	}

	public RCP_FondoUnico_3_CotizDI(long ips_B1P3_repPrenatal, long ips_B1P3_repPostNatal, long ips_B1P3_repParental, long ips_B1P3_perEnfmen1, long ips_B1P3_Total,
			long entidEmpleadora_B1P3_repPrenatal, long entidEmpleadora_B1P3_repPostNatal, long entidEmpleadora_B1P3_repParental, long entidEmpleadora_B1P3_perEnfmen1,
			long entidEmpleadora_B1P3_Total, long subTotal_B1P3_repPrenatal, long subTotal_B1P3_repPostNatal, long subTotal_B1P3_repParental, long subTotal_B1P3_perEnfmen1, long subTotal_B1P3_Total) {
		super();
		this.ips_B1P3_repPrenatal = ips_B1P3_repPrenatal;
		this.ips_B1P3_repPostNatal = ips_B1P3_repPostNatal;
		this.ips_B1P3_repParental = ips_B1P3_repParental;
		this.ips_B1P3_perEnfmen1 = ips_B1P3_perEnfmen1;
		this.ips_B1P3_Total = ips_B1P3_Total;
		this.entidEmpleadora_B1P3_repPrenatal = entidEmpleadora_B1P3_repPrenatal;
		this.entidEmpleadora_B1P3_repPostNatal = entidEmpleadora_B1P3_repPostNatal;
		this.entidEmpleadora_B1P3_repParental = entidEmpleadora_B1P3_repParental;
		this.entidEmpleadora_B1P3_perEnfmen1 = entidEmpleadora_B1P3_perEnfmen1;
		this.entidEmpleadora_B1P3_Total = entidEmpleadora_B1P3_Total;
		this.subTotal_B1P3_repPrenatal = subTotal_B1P3_repPrenatal;
		this.subTotal_B1P3_repPostNatal = subTotal_B1P3_repPostNatal;
		this.subTotal_B1P3_repParental = subTotal_B1P3_repParental;
		this.subTotal_B1P3_perEnfmen1 = subTotal_B1P3_perEnfmen1;
		this.subTotal_B1P3_Total = subTotal_B1P3_Total;
	}

	public long getEntidEmpleadora_B1P3_perEnfmen1() {
		return entidEmpleadora_B1P3_perEnfmen1;
	}

	public long getEntidEmpleadora_B1P3_repParental() {
		return entidEmpleadora_B1P3_repParental;
	}

	public long getEntidEmpleadora_B1P3_repPostNatal() {
		return entidEmpleadora_B1P3_repPostNatal;
	}

	public long getEntidEmpleadora_B1P3_repPrenatal() {
		return entidEmpleadora_B1P3_repPrenatal;
	}

	public long getEntidEmpleadora_B1P3_Total() {
		return entidEmpleadora_B1P3_Total;
	}

	public long getIps_B1P3_perEnfmen1() {
		return ips_B1P3_perEnfmen1;
	}

	public long getIps_B1P3_repParental() {
		return ips_B1P3_repParental;
	}

	public long getIps_B1P3_repPostNatal() {
		return ips_B1P3_repPostNatal;
	}

	public long getIps_B1P3_repPrenatal() {
		return ips_B1P3_repPrenatal;
	}

	public long getIps_B1P3_Total() {
		return ips_B1P3_Total;
	}

	public long getSubTotal_B1P3_perEnfmen1() {
		return subTotal_B1P3_perEnfmen1;
	}

	public long getSubTotal_B1P3_repParental() {
		return subTotal_B1P3_repParental;
	}

	public long getSubTotal_B1P3_repPostNatal() {
		return subTotal_B1P3_repPostNatal;
	}

	public long getSubTotal_B1P3_repPrenatal() {
		return subTotal_B1P3_repPrenatal;
	}

	public long getSubTotal_B1P3_Total() {
		return subTotal_B1P3_Total;
	}

	public void setEntidEmpleadora_B1P3_perEnfmen1(long entidEmpleadora_B1P3_perEnfmen1) {
		this.entidEmpleadora_B1P3_perEnfmen1 = entidEmpleadora_B1P3_perEnfmen1;
	}

	public void setEntidEmpleadora_B1P3_repParental(long entidEmpleadora_B1P3_repParental) {
		this.entidEmpleadora_B1P3_repParental = entidEmpleadora_B1P3_repParental;
	}

	public void setEntidEmpleadora_B1P3_repPostNatal(long entidEmpleadora_B1P3_repPostNatal) {
		this.entidEmpleadora_B1P3_repPostNatal = entidEmpleadora_B1P3_repPostNatal;
	}

	public void setEntidEmpleadora_B1P3_repPrenatal(long entidEmpleadora_B1P3_repPrenatal) {
		this.entidEmpleadora_B1P3_repPrenatal = entidEmpleadora_B1P3_repPrenatal;
	}

	public void setEntidEmpleadora_B1P3_Total(long entidEmpleadora_B1P3_Total) {
		this.entidEmpleadora_B1P3_Total = entidEmpleadora_B1P3_Total;
	}

	public void setIps_B1P3_perEnfmen1(long ips_B1P3_perEnfmen1) {
		this.ips_B1P3_perEnfmen1 = ips_B1P3_perEnfmen1;
	}

	public void setIps_B1P3_repParental(long ips_B1P3_repParental) {
		this.ips_B1P3_repParental = ips_B1P3_repParental;
	}

	public void setIps_B1P3_repPostNatal(long ips_B1P3_repPostNatal) {
		this.ips_B1P3_repPostNatal = ips_B1P3_repPostNatal;
	}

	public void setIps_B1P3_repPrenatal(long ips_B1P3_repPrenatal) {
		this.ips_B1P3_repPrenatal = ips_B1P3_repPrenatal;
	}

	public void setIps_B1P3_Total(long ips_B1P3_Total) {
		this.ips_B1P3_Total = ips_B1P3_Total;
	}

	public void setSubTotal_B1P3_perEnfmen1(long subTotal_B1P3_perEnfmen1) {
		this.subTotal_B1P3_perEnfmen1 = subTotal_B1P3_perEnfmen1;
	}

	public void setSubTotal_B1P3_repParental(long subTotal_B1P3_repParental) {
		this.subTotal_B1P3_repParental = subTotal_B1P3_repParental;
	}

	public void setSubTotal_B1P3_repPostNatal(long subTotal_B1P3_repPostNatal) {
		this.subTotal_B1P3_repPostNatal = subTotal_B1P3_repPostNatal;
	}

	public void setSubTotal_B1P3_repPrenatal(long subTotal_B1P3_repPrenatal) {
		this.subTotal_B1P3_repPrenatal = subTotal_B1P3_repPrenatal;
	}

	public void setSubTotal_B1P3_Total(long subTotal_B1P3_Total) {
		this.subTotal_B1P3_Total = subTotal_B1P3_Total;
	}

}
