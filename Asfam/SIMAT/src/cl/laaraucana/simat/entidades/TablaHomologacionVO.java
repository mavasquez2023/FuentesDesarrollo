package cl.laaraucana.simat.entidades;

public class TablaHomologacionVO {
	/*
	 * Correlativo secuencial para cada registro de la tabla.
	 */
	private int id_registro;
	/*
	 * Correlativo a la clasificacion de un subconjunto de registros (lista).
	 */
	private int clasificacion;
	/*
	 * 'Descripcion del registro de lista'.
	 */
	private String descripcion;
	/*
	 * Codigo suceso.
	 */
	private String codigo_suceso;
	/*
	 * Codigo La Araucana.
	 */
	private String codigo_la;

	public TablaHomologacionVO() {
	}

	public TablaHomologacionVO(int id_registro, int clasificacion, String descripcion, String codigo_suceso, String codigo_la) {
		super();
		this.id_registro = id_registro;
		this.clasificacion = clasificacion;
		this.descripcion = descripcion;
		this.codigo_suceso = codigo_suceso;
		this.codigo_la = codigo_la;
	}

	public String getId_registro() {
		return String.valueOf(id_registro);
	}

	public void setId_registro(int id_registro) {
		this.id_registro = id_registro;
	}

	public String getClasificacion() {
		return String.valueOf(clasificacion);
	}

	public String getCodigo_la() {
		return codigo_la;
	}

	public String getCodigo_suceso() {
		return codigo_suceso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setClasificacion(int clasificacion) {
		this.clasificacion = clasificacion;
	}

	public void setCodigo_la(String codigo_la) {
		this.codigo_la = codigo_la;
	}

	public void setCodigo_suceso(String codigo_suceso) {
		this.codigo_suceso = codigo_suceso;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
