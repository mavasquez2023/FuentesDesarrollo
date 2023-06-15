package cl.araucana.wsafiliado.to;

public class TramoTO {

	private static final long serialVersionUID = 1284335245228030241L;

	public TramoTO() {

	}

	private String id;
	private String tupla;
	private String rutCausante;
	private String periodo;
	private String NumTramo;
	private String IngPromedio;
	private String MontoUnitarioBeneficio;
	private java.sql.Date fecIniTramo; 

	public String getIngPromedio() {
		return IngPromedio;
	}

	public void setIngPromedio(String ingPromedio) {
		IngPromedio = ingPromedio;
	}

	public String getMontoUnitarioBeneficio() {
		return MontoUnitarioBeneficio;
	}

	public void setMontoUnitarioBeneficio(String montoUnitarioBeneficio) {
		MontoUnitarioBeneficio = montoUnitarioBeneficio;
	}

	public String getNumTramo() {
		return NumTramo;
	}

	public void setNumTramo(String numTramo) {
		NumTramo = numTramo;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getRutCausante() {
		return rutCausante;
	}

	public void setRutCausante(String rutCausante) {
		this.rutCausante = rutCausante;
	}

	public String getTupla() {
		return tupla;
	}

	public void setTupla(String tupla) {
		this.tupla = tupla;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public java.sql.Date getFecIniTramo() {
		return fecIniTramo;
	}

	public void setFecIniTramo(java.sql.Date fecIniTramo) {
		this.fecIniTramo = fecIniTramo;
	}
	
}
