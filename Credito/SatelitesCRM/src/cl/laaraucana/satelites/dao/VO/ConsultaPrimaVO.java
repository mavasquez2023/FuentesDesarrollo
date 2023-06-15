package cl.laaraucana.satelites.dao.VO;

public class ConsultaPrimaVO {
	
	private int ofipro;
	private long crefol;
	private int cuonumDesde;
	private int cuonumHasta;
	private Integer prima;
	
	public Integer getPrima() {
		return prima;
	}
	public void setPrima(Integer prima) {
		this.prima = prima;
	}
	public int getOfipro() {
		return ofipro;
	}
	public void setOfipro(int ofipro) {
		this.ofipro = ofipro;
	}
	public long getCrefol() {
		return crefol;
	}
	public void setCrefol(long crefol) {
		this.crefol = crefol;
	}
	public int getCuonumDesde() {
		return cuonumDesde;
	}
	public void setCuonumDesde(int cuonumDesde) {
		this.cuonumDesde = cuonumDesde;
	}
	public int getCuonumHasta() {
		return cuonumHasta;
	}
	public void setCuonumHasta(int cuonumHasta) {
		this.cuonumHasta = cuonumHasta;
	}
}
