package cl.laaraucana.simat.entidades;

public class KeyPaginacionVO {

	private String tabla;
	private int inicio;
	private int fin;
	private int minId;
	private int maxId;

	public KeyPaginacionVO() {
	}

	public KeyPaginacionVO(String tabla, int inicio, int fin, int minId, int maxId) {
		super();
		this.tabla = tabla;
		this.inicio = inicio;
		this.fin = fin;
		this.minId = minId;
		this.maxId = maxId;
	}

	public int getFin() {
		return fin;
	}

	public int getInicio() {
		return inicio;
	}

	public int getMaxId() {
		return maxId;
	}

	public int getMinId() {
		return minId;
	}

	public String getTabla() {
		return tabla;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}

	public void setMinId(int minId) {
		this.minId = minId;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

}