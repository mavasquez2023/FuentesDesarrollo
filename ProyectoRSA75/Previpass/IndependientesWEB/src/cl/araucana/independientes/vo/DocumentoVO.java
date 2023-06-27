package cl.araucana.independientes.vo;

import cl.araucana.independientes.vo.param.Documento;

public class DocumentoVO extends Documento{

    private long idSecuenciaDocumento;
    private long idSolicitud; 
    private int tipoDocumento;
    private int estadoDocumento;
    private String observacionesDocumento;
    private int alta;
    private int obligatorio;

    public int getAlta() {
        return alta;
    }
    public void setAlta(int alta) {
        this.alta = alta;
    }
    public int getObligatorio() {
        return obligatorio;
    }
    public void setObligatorio(int obligatorio) {
        this.obligatorio = obligatorio;
    }
    public int getEstadoDocumento() {
        return estadoDocumento;
    }
    public void setEstadoDocumento(int estadoDocumento) {
        this.estadoDocumento = estadoDocumento;
    }
    public long getIdSecuenciaDocumento() {
        return idSecuenciaDocumento;
    }
    public void setIdSecuenciaDocumento(long idSecuenciaDocumento) {
        this.idSecuenciaDocumento = idSecuenciaDocumento;
    }
    public long getIdSolicitud() {
        return idSolicitud;
    }
    public void setIdSolicitud(long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    public String getObservacionesDocumento() {
        return observacionesDocumento;
    }
    public void setObservacionesDocumento(String observacionesDocumento) {
        this.observacionesDocumento = observacionesDocumento;
    }
    public int getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
