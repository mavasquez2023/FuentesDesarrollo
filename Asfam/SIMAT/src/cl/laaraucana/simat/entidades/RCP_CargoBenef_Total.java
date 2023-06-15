package cl.laaraucana.simat.entidades;

public class RCP_CargoBenef_Total {

	private long total_B2_prenatal;
	private long total_B2_postnatal;
	private long total_B2_parental;
	private long total_B2_perEnfmen1;
	private long total_B2_Total;

	public RCP_CargoBenef_Total() {

	}

	public RCP_CargoBenef_Total(long total_B2_prenatal, long total_B2_postnatal, long total_B2_parental, long total_B2_perEnfmen1, long total_B2_Total) {
		super();
		this.total_B2_prenatal = total_B2_prenatal;
		this.total_B2_postnatal = total_B2_postnatal;
		this.total_B2_parental = total_B2_parental;
		this.total_B2_perEnfmen1 = total_B2_perEnfmen1;
		this.total_B2_Total = total_B2_Total;
	}

	public long getTotal_B2_parental() {
		return total_B2_parental;
	}

	public long getTotal_B2_perEnfmen1() {
		return total_B2_perEnfmen1;
	}

	public long getTotal_B2_postnatal() {
		return total_B2_postnatal;
	}

	public long getTotal_B2_prenatal() {
		return total_B2_prenatal;
	}

	public long getTotal_B2_Total() {
		return total_B2_Total;
	}

	public void setTotal_B2_parental(long total_B2_parental) {
		this.total_B2_parental = total_B2_parental;
	}

	public void setTotal_B2_perEnfmen1(long total_B2_perEnfmen1) {
		this.total_B2_perEnfmen1 = total_B2_perEnfmen1;
	}

	public void setTotal_B2_postnatal(long total_B2_postnatal) {
		this.total_B2_postnatal = total_B2_postnatal;
	}

	public void setTotal_B2_prenatal(long total_B2_prenatal) {
		this.total_B2_prenatal = total_B2_prenatal;
	}

	public void setTotal_B2_Total(long total_B2_Total) {
		this.total_B2_Total = total_B2_Total;
	}

}
