package cl.laaraucana.capaservicios.database.vo;

public class MigracionVO {
	private long rut;
	private String dv;
	
	public MigracionVO(long rut, String dv){
		setRut(rut);
		setDv(dv);
	}
	
	public long getRut() {
		return rut;
	}
	public void setRut(long rut) {
		this.rut = rut;
	}
	public String getDv() {
		return dv;
	}
	public void setDv(String dv) {
		this.dv = dv;
	}
}
