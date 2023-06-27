package cl.araucana.clientewsfonasa.business.to;

import java.util.Date;

import cl.araucana.clientewsfonasa.common.util.FechaUtil;

public class ResponseWSFonasaTO {

	private Short estado;
    private String gloEstado;
    private String extApellidoPat;
    private String extApellidoMat;
    private String extNombres;
    private String extSexo;
    private Date extFechaNacimi;
    private String extCodEstBen;
    private String extDescEstado;
    
    public ResponseWSFonasaTO(){}
    
	public ResponseWSFonasaTO(Short estado, String gloEstado, String extApellidoPat, String extApellidoMat, String extNombres, String extSexo, Date extFechaNacimi, String extCodEstBen, String extDescEstado) {
		this.estado = estado;
		this.gloEstado = gloEstado;
		this.extApellidoPat = extApellidoPat;
		this.extApellidoMat = extApellidoMat;
		this.extNombres = extNombres;
		this.extSexo = extSexo;
		this.extFechaNacimi = extFechaNacimi;
		this.extCodEstBen = extCodEstBen;
		this.extDescEstado = extDescEstado;
	}

	public Short getEstado() {
		return estado;
	}
	
	public String getEstadoFormat() {
		return (estado!=null)?estado.toString():"";
	}

	public void setEstado(Short estado) {
		this.estado = estado;
	}

	public String getExtApellidoMat() {
		return extApellidoMat;
	}

	public void setExtApellidoMat(String extApellidoMat) {
		this.extApellidoMat = extApellidoMat;
	}

	public String getExtApellidoPat() {
		return extApellidoPat;
	}

	public void setExtApellidoPat(String extApellidoPat) {
		this.extApellidoPat = extApellidoPat;
	}

	public String getExtCodEstBen() {
		return extCodEstBen;
	}

	public void setExtCodEstBen(String extCodEstBen) {
		this.extCodEstBen = extCodEstBen;
	}

	public String getExtDescEstado() {
		return extDescEstado;
	}

	public void setExtDescEstado(String extDescEstado) {
		this.extDescEstado = extDescEstado;
	}

	public Date getExtFechaNacimi() {
		return extFechaNacimi;
	}
	
	public String getExtFechaNacimiFormat() {
		
		return (extFechaNacimi!=null)?FechaUtil.formatearFecha("dd-MM-yyyy", extFechaNacimi):"";
		
	}

	public void setExtFechaNacimi(Date extFechaNacimi) {
		this.extFechaNacimi = extFechaNacimi;
	}

	public String getExtNombres() {
		return extNombres;
	}

	public void setExtNombres(String extNombres) {
		this.extNombres = extNombres;
	}

	public String getExtSexo() {
		return extSexo;
	}

	public void setExtSexo(String extSexo) {
		this.extSexo = extSexo;
	}

	public String getGloEstado() {
		return gloEstado;
	}

	public void setGloEstado(String gloEstado) {
		this.gloEstado = gloEstado;
	}
}
