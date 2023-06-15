package cl.laaraucana.satelites.certificados.finiquito.VO;

import java.util.ArrayList;
import java.util.List;


public class CertificadoFiniquitoVO {
	
	public CertificadoFiniquitoVO() {}

	private String rut;
	private String nombre;
	private String rutEmpresa;
	private String nombreEmpresa;
	private String fechaFiniquito;
	private String fechaSolicitud;
	private double totalSocial;
	private double totalEspecial;
	private double totalEducacion;
	List<SalidaFiniquitoVO> listaCreditos = new ArrayList<SalidaFiniquitoVO>();
	List<SalidaFiniquitoVO> listaCreditosSocial = new ArrayList<SalidaFiniquitoVO>();
	List<SalidaFiniquitoVO> listaCreditosEspecial = new ArrayList<SalidaFiniquitoVO>();
	List<SalidaFiniquitoVO> listaCreditosEducacion = new ArrayList<SalidaFiniquitoVO>();
	
	
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getFechaFiniquito() {
		return fechaFiniquito;
	}
	public void setFechaFiniquito(String fechaFiniquito) {
		this.fechaFiniquito = fechaFiniquito;
	}
	public List<SalidaFiniquitoVO> getListaCreditos() {
		return listaCreditos;
	}
	public void setListaCreditos(List<SalidaFiniquitoVO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}
	public List<SalidaFiniquitoVO> getListaCreditosSocial() {
		return listaCreditosSocial;
	}
	public void setListaCreditosSocial(List<SalidaFiniquitoVO> listaCreditosSocial) {
		this.listaCreditosSocial = listaCreditosSocial;
	}
	public List<SalidaFiniquitoVO> getListaCreditosEspecial() {
		return listaCreditosEspecial;
	}
	public void setListaCreditosEspecial(List<SalidaFiniquitoVO> listaCreditosEspecial) {
		this.listaCreditosEspecial = listaCreditosEspecial;
	}
	public List<SalidaFiniquitoVO> getListaCreditosEducacion() {
		return listaCreditosEducacion;
	}
	public void setListaCreditosEducacion(List<SalidaFiniquitoVO> listaCreditosEducacion) {
		this.listaCreditosEducacion = listaCreditosEducacion;
	}
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public double getTotalSocial() {
		return totalSocial;
	}
	public void setTotalSocial(double totalSocial) {
		this.totalSocial = totalSocial;
	}
	public double getTotalEspecial() {
		return totalEspecial;
	}
	public void setTotalEspecial(double totalEspecial) {
		this.totalEspecial = totalEspecial;
	}
	public double getTotalEducacion() {
		return totalEducacion;
	}
	public void setTotalEducacion(double totalEducacion) {
		this.totalEducacion = totalEducacion;
	}
	
	public double getTotal() {
		return totalSocial+totalEspecial+totalEducacion;
	}
	
	

				
}
