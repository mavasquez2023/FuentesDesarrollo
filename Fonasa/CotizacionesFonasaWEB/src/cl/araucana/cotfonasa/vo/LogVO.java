package cl.araucana.cotfonasa.vo;

import java.util.Date;



public class LogVO {
	
	private String idEncabezado;
	private String fechaInicioProceso;
	private String fechaTerminoProceso;
	private String periodo;
	private String periodoPrint;
	private String archivoEntrada;
	private String archivoSalida;
	private int estado;
    private String horaTerminoProceso;
    private String horaInicioProceso;
    private int periodoInsert;
    private Date fechaInicioProcesoDate;
	private Date fechaTerminoProcesoDate;
	private Date horaTerminoProcesoDate;
    private Date horaInicioProcesoDate;
    private String nombreArchivoEntrada;
    
    
	public String getIdEncabezado() {
		return idEncabezado;
	}
	public void setIdEncabezado(String idEncabezado) {
		this.idEncabezado = idEncabezado;
	}
	public String getFechaInicioProceso() {
		return fechaInicioProceso;
	}
	public void setFechaInicioProceso(String fechaInicioProceso) {
		this.fechaInicioProceso = fechaInicioProceso;
	}
	public String getFechaTerminoProceso() {
		return fechaTerminoProceso;
	}
	public void setFechaTerminoProceso(String fechaTerminoProceso) {
		this.fechaTerminoProceso = fechaTerminoProceso;
	}
	public String getPeriodo() {
		return periodo.substring(4) + periodo.substring(0,4);
	}
	public void setPeriodo(String periodo) {
		
		this.periodo = periodo;
	}
	
	public String getPeriodoPrint() {
		return periodo.substring(4) + "/" + periodo.substring(0,4);
	}
	public void setPeriodoPrint(String periodoPrint) {
		
		this.periodoPrint = periodoPrint;
	}
	public String getArchivoEntrada() {
		return archivoEntrada;
	}
	public void setArchivoEntrada(String archivoEntrada) {
		this.archivoEntrada = archivoEntrada;
	}
	public String getArchivoSalida() {
		return archivoSalida;
	}
	public void setArchivoSalida(String archivoSalida) {
		this.archivoSalida = archivoSalida;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public String getHoraTerminoProceso() {
		return horaTerminoProceso;
	}
	public void setHoraTerminoProceso(String horaTerminoProceso) {
		this.horaTerminoProceso = horaTerminoProceso;
	}
	public String getHoraInicioProceso() {
		return horaInicioProceso;
	}
	public void setHoraInicioProceso(String horaInicioProceso) {
		this.horaInicioProceso = horaInicioProceso;
	}
	public int getPeriodoInsert() {
		return periodoInsert;
	}
	public void setPeriodoInsert(int periodoInsert) {
		this.periodoInsert = periodoInsert;
	}
	public Date getFechaInicioProcesoDate() {
		return fechaInicioProcesoDate;
	}
	public void setFechaInicioProcesoDate(Date fechaInicioProcesoDate) {
		this.fechaInicioProcesoDate = fechaInicioProcesoDate;
	}
	public Date getFechaTerminoProcesoDate() {
		return fechaTerminoProcesoDate;
	}
	public void setFechaTerminoProcesoDate(Date fechaTerminoProcesoDate) {
		this.fechaTerminoProcesoDate = fechaTerminoProcesoDate;
	}
	public Date getHoraTerminoProcesoDate() {
		return horaTerminoProcesoDate;
	}
	public void setHoraTerminoProcesoDate(Date horaTerminoProcesoDate) {
		this.horaTerminoProcesoDate = horaTerminoProcesoDate;
	}
	public Date getHoraInicioProcesoDate() {
		return horaInicioProcesoDate;
	}
	public void setHoraInicioProcesoDate(Date horaInicioProcesoDate) {
		this.horaInicioProcesoDate = horaInicioProcesoDate;
	}
	public String getNombreArchivoEntrada() {
		
		
		String line[] = archivoEntrada.split("\\\\");
		// devuelve el ultimo string de split
		
		return line[line.length -1];
	}
	public void setNombreArchivoEntrada(String nombreArchivoEntrada) {
		this.nombreArchivoEntrada = nombreArchivoEntrada;
	}
	
	
	
	
	
	
	

}
