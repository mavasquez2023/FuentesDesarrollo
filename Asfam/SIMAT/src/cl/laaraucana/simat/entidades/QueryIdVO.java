package cl.laaraucana.simat.entidades;

public class QueryIdVO {

	private String tabla;
	private int maxId;
	private int minId;

	public QueryIdVO() {
	}

	public QueryIdVO(String tabla, int maxId, int minId) {
		super();
		this.tabla = tabla;
		this.maxId = maxId;
		this.minId = minId;
	}

	public int getMaxId() {
		return maxId;
	}

	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}

	public int getMinId() {
		return minId;
	}

	public void setMinId(int minId) {
		this.minId = minId;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

}
