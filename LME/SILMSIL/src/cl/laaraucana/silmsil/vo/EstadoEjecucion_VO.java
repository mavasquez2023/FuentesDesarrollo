package cl.laaraucana.silmsil.vo;

import java.util.ArrayList;

/**
 * 
 *
 */
public class EstadoEjecucion_VO {

	
	private String periodo;
	private String proceso;
	private String ultimoEstado;
	private boolean keyEjecucion;
	private boolean keyReProceso;
	
	public EstadoEjecucion_VO() {
		super();
	}
	
	
	
	public EstadoEjecucion_VO(String periodo, String proceso,
			String ultimoEstado, boolean keyEjecucion, boolean keyReProceso) {
		super();
		this.periodo = periodo;
		this.proceso = proceso;
		this.ultimoEstado = ultimoEstado;
		this.keyEjecucion = keyEjecucion;
		this.keyReProceso = keyReProceso;
	}



	public String getPeriodo() {
		return periodo;
	}
	public String getProceso() {
		return proceso;
	}
	public String getUltimoEstado() {
		return ultimoEstado;
	}
	public boolean isKeyEjecucion() {
		return keyEjecucion;
	}
	public boolean isKeyReProceso() {
		return keyReProceso;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public void setUltimoEstado(String ultimoEstado) {
		this.ultimoEstado = ultimoEstado;
	}
	public void setKeyEjecucion(boolean keyEjecucion) {
		this.keyEjecucion = keyEjecucion;
	}
	public void setKeyReProceso(boolean keyReProceso) {
		this.keyReProceso = keyReProceso;
	}
	
	
}//end class
