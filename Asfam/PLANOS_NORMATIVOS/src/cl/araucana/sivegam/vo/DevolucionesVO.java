package cl.araucana.sivegam.vo;

public class DevolucionesVO {

    private long idDevoluciones;
    private long idInformeFinanciero;
    private long documentosCaducados;
    private long documentosAnulados;
    private long totalDevoluciones;

    public long getDocumentosAnulados() {
        return documentosAnulados;
    }

    public void setDocumentosAnulados(long documentosAnulados) {
        this.documentosAnulados = documentosAnulados;
    }

    public long getDocumentosCaducados() {
        return documentosCaducados;
    }

    public void setDocumentosCaducados(long documentosCaducados) {
        this.documentosCaducados = documentosCaducados;
    }

    public long getIdDevoluciones() {
        return idDevoluciones;
    }

    public void setIdDevoluciones(long idDevoluciones) {
        this.idDevoluciones = idDevoluciones;
    }

    public long getIdInformeFinanciero() {
        return idInformeFinanciero;
    }

    public void setIdInformeFinanciero(long idInformeFinanciero) {
        this.idInformeFinanciero = idInformeFinanciero;
    }

    public long getTotalDevoluciones() {
        return totalDevoluciones;
    }

    public void setTotalDevoluciones(long totalDevoluciones) {
        this.totalDevoluciones = totalDevoluciones;
    }
}
