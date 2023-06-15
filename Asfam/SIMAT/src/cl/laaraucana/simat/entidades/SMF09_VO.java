package cl.laaraucana.simat.entidades;

import java.sql.Date;

public class SMF09_VO {

	private String fecha_periodo;
	private String proceso;
	private String descripcion;
	private String estado_proceso;
	private Date fecha_proceso;
	private int hora_proceso;
	private String usuario_proceso;

	public SMF09_VO() {

	}

	public SMF09_VO(String fecha_periodo, String proceso, String descripcion, String estado_proceso, Date fecha_proceso, int hora_proceso, String usuario_proceso) {
		super();
		this.fecha_periodo = fecha_periodo;
		this.proceso = proceso;
		this.descripcion = descripcion;
		this.estado_proceso = estado_proceso;
		this.fecha_proceso = fecha_proceso;
		this.hora_proceso = hora_proceso;
		this.usuario_proceso = usuario_proceso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getEstado_proceso() {
		return estado_proceso;
	}

	public String getFecha_periodo() {
		return fecha_periodo;
	}

	public Date getFecha_proceso() {
		return fecha_proceso;
	}

	public int getHora_proceso() {
		return hora_proceso;
	}

	public String getProceso() {
		return proceso;
	}

	public String getUsuario_proceso() {
		return usuario_proceso;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstado_proceso(String estado_proceso) {
		this.estado_proceso = estado_proceso;
	}

	public void setFecha_periodo(String fecha_periodo) {
		this.fecha_periodo = fecha_periodo;
	}

	public void setFecha_proceso(Date fecha_proceso) {
		this.fecha_proceso = fecha_proceso;
	}

	public void setHora_proceso(int hora_proceso) {
		this.hora_proceso = hora_proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public void setUsuario_proceso(String usuario_proceso) {
		this.usuario_proceso = usuario_proceso;
	}

}