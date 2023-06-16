package cl.laaraucana.simat.entidades;

import java.sql.Timestamp;

public class LogProcesosVO {
	/*
	 * Correlativo secuencial para cada registro de log.
	 */
	private int id_registro;
	/*
	 * Permite catalogar los diversos tipos de registro de log que se manejaran.
	 */
	private String tipo_log;
	/*
	 * Fecha y hora de la creacion del registro de log.
	 */
	private Timestamp fecha_hora;
	/*
	 * Permite Identificar el periodo de la informacion al que corresponde el registro de log.
	 */
	private String periodo;
	/*
	 * Identificador del usuario o proceso que genera el registro de log.
	 */
	private String id_usuario;

	/*
	 * nombre del proceso que genero el registro del log.
	 */
	private String proceso_afectado;

	/*
	 * Identificación de la tabla referenciada en el registro de log.
	 */
	private String tabla;
	/*
	 * Identificador del registro referenciado por el registro de log.
	 */
	private String registro_id;
	/*
	 * Campo utilitario que permite describir el error detectado
	 */
	private String descripcion;

	/*
	 * key para poder mostrar cantidad de registros limitados
	 * */
	private int keyInicio;
	private int keyFin;
	private int paginacion;

	public LogProcesosVO() {
	}

	public LogProcesosVO(int id_registro, String tipo_log, Timestamp fecha_hora, String periodo, String id_usuario, String tabla, String registro_id, String descripcion, String proceso_afectado) {
		super();
		this.id_registro = id_registro;
		this.tipo_log = tipo_log;
		this.fecha_hora = fecha_hora;
		this.periodo = periodo;
		this.id_usuario = id_usuario;
		this.tabla = tabla;
		this.registro_id = registro_id;
		this.descripcion = descripcion;
		this.proceso_afectado = proceso_afectado;
	}

	//	----------------------------------------------------------
	public int getKeyFin() {
		return keyFin;
	}

	public int getKeyInicio() {
		return keyInicio;
	}

	public void setKeyFin(int keyFin) {
		this.keyFin = keyFin;
	}

	public void setKeyInicio(int keyInicio) {
		this.keyInicio = keyInicio;
	}

	public int getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(int paginacion) {
		this.paginacion = paginacion;
	}

	//-----------------------------------------------------------

	public String getProceso_afectado() {
		return proceso_afectado;
	}

	public void setProceso_afectado(String proceso_afectado) {
		this.proceso_afectado = proceso_afectado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Timestamp fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public String getId_registro() {
		return String.valueOf(id_registro);
	}

	public void setId_registro(int id_registro) {
		this.id_registro = id_registro;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getRegistro_id() {
		return registro_id;
	}

	public void setRegistro_id(String registro_id) {
		this.registro_id = registro_id;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getTipo_log() {
		return tipo_log;
	}

	public void setTipo_log(String tipo_log) {
		this.tipo_log = tipo_log;
	}

}
