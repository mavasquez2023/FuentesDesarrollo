package cl.laaraucana.simat.entidades;

public class RCP_FondoUnico_Total {
	private long total_B1_prenatal;
	private long total_B1_postnatal;
	private long total_B1_parental;
	private long total_B1_perEnfmen1;
	private long total_B1_Total;

	public RCP_FondoUnico_Total() {

	};

	public RCP_FondoUnico_Total(long total_B1_prenatal, long total_B1_postnatal, long total_B1_parental, long total_B1_perEnfmen1, long total_B1_Total) {
		super();
		this.total_B1_prenatal = total_B1_prenatal;
		this.total_B1_postnatal = total_B1_postnatal;
		this.total_B1_parental = total_B1_parental;
		this.total_B1_perEnfmen1 = total_B1_perEnfmen1;
		this.total_B1_Total = total_B1_Total;
	}

	public long getTotal_B1_parental() {
		return total_B1_parental;
	}

	public long getTotal_B1_perEnfmen1() {
		return total_B1_perEnfmen1;
	}

	public long getTotal_B1_postnatal() {
		return total_B1_postnatal;
	}

	public long getTotal_B1_prenatal() {
		return total_B1_prenatal;
	}

	public long getTotal_B1_Total() {
		return total_B1_Total;
	}

	public void setTotal_B1_parental(long total_B1_parental) {
		this.total_B1_parental = total_B1_parental;
	}

	public void setTotal_B1_perEnfmen1(long total_B1_perEnfmen1) {
		this.total_B1_perEnfmen1 = total_B1_perEnfmen1;
	}

	public void setTotal_B1_postnatal(long total_B1_postnatal) {
		this.total_B1_postnatal = total_B1_postnatal;
	}

	public void setTotal_B1_prenatal(long total_B1_prenatal) {
		this.total_B1_prenatal = total_B1_prenatal;
	}

	public void setTotal_B1_Total(long total_B1_Total) {
		this.total_B1_Total = total_B1_Total;
	}

}
