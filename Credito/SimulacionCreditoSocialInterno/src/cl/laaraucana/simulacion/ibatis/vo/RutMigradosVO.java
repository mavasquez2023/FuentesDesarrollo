package cl.laaraucana.simulacion.ibatis.vo;

public class RutMigradosVO {

	private long rut;
	private String dv;
	private long fechaMigracion;
	private long origenBP;

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

	public long getFechaMigracion() {
		return fechaMigracion;
	}

	public void setFechaMigracion(long fechaMigracion) {
		this.fechaMigracion = fechaMigracion;
	}

	public long getOrigenBP() {
		return origenBP;
	}

	public void setOrigenBP(long origenBP) {
		this.origenBP = origenBP;
	}

}
