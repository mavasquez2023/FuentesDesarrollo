package cl.laaraucana.simat.entidades;

public class ProcesoPeriodo {
	public static String PROCESO_CARGA = "CARGA";
	public static String PROCESO_VALIDACION = "VALIDACION";
	public static String PROCESO_PASE_CONTABLE = "PASE";
	
	private String nombreProceso;
	private String codEstado;
	private String desEstado;
	
	public String getCodEstado() {
		return codEstado;
	}
	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}
	public String getNombreProceso() {
		return nombreProceso;
	}
	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}
	public String getDesEstado() {
		return desEstado;
	}
	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}
}
