package cl.araucana.independientes.vo;

import java.util.Date;

public class AfiliadoBeneficiosVO {

    private long idDocumentoPersona; //RUT
    private long idPersonaAfiliado;
    private int tipoEstadoAfiliado; //EstadoAfiliado
    private String glosaTipoestadoAfiliado;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private String fechaVigencia; //Fecha Vigencia Afiliacion
    private Date fechaVigenciaDate;
    private long totalDias; // 
    private int estadoAporte;
    private String glosaEstadoAporte;
    //INICIO REQ 7002
    private long totalDeudaAporte;

    public long getTotalDeudaAporte() {
        return totalDeudaAporte;
    }
    public void setTotalDeudaAporte(long totalDeudaAporte) {
        this.totalDeudaAporte = totalDeudaAporte;
    }
    //FIN REQ 7002
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public int getEstadoAporte() {
        return estadoAporte;
    }
    public void setEstadoAporte(int estadoAporte) {
        this.estadoAporte = estadoAporte;
    }
    public String getFechaVigencia() {
        return fechaVigencia;
    }
    public void setFechaVigencia(String fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }
    public Date getFechaVigenciaDate() {
        return fechaVigenciaDate;
    }
    public void setFechaVigenciaDate(Date fechaVigenciaDate) {
        this.fechaVigenciaDate = fechaVigenciaDate;
    }
    public String getGlosaEstadoAporte() {
        return glosaEstadoAporte;
    }
    public void setGlosaEstadoAporte(String glosaEstadoAporte) {
        this.glosaEstadoAporte = glosaEstadoAporte;
    }
    public String getGlosaTipoestadoAfiliado() {
        return glosaTipoestadoAfiliado;
    }
    public void setGlosaTipoestadoAfiliado(String glosaTipoestadoAfiliado) {
        this.glosaTipoestadoAfiliado = glosaTipoestadoAfiliado;
    }
    public long getIdDocumentoPersona() {
        return idDocumentoPersona;
    }
    public void setIdDocumentoPersona(long idDocumentoPersona) {
        this.idDocumentoPersona = idDocumentoPersona;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public int getTipoEstadoAfiliado() {
        return tipoEstadoAfiliado;
    }
    public void setTipoEstadoAfiliado(int tipoEstadoAfiliado) {
        this.tipoEstadoAfiliado = tipoEstadoAfiliado;
    }
    public long getTotalDias() {
        return totalDias;
    }
    public void setTotalDias(long totalDias) {
        this.totalDias = totalDias;
    }
    public long getIdPersonaAfiliado() {
        return idPersonaAfiliado;
    }
    public void setIdPersonaAfiliado(long idPersonaAfiliado) {
        this.idPersonaAfiliado = idPersonaAfiliado;
    }
}
