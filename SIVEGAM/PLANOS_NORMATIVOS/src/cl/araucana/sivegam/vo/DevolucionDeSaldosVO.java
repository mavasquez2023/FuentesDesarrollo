package cl.araucana.sivegam.vo;

public class DevolucionDeSaldosVO {

    private long idDevolucionDeSaldos;
    private long idInformeFinanciero;
    private long saldoFavorEmpleador;
    private long devolucionDocSAFEMCaducados;
    private long devolucionDocSAFEMAnulados;
    private long documentosSAFEMRevalidados;
    private long totalDevolucionesE;

    public long getDevolucionDocSAFEMAnulados() {
        return devolucionDocSAFEMAnulados;
    }

    public void setDevolucionDocSAFEMAnulados(long devolucionDocSAFEMAnulados) {
        this.devolucionDocSAFEMAnulados = devolucionDocSAFEMAnulados;
    }

    public long getDevolucionDocSAFEMCaducados() {
        return devolucionDocSAFEMCaducados;
    }

    public void setDevolucionDocSAFEMCaducados(long devolucionDocSAFEMCaducados) {
        this.devolucionDocSAFEMCaducados = devolucionDocSAFEMCaducados;
    }

    public long getDocumentosSAFEMRevalidados() {
        return documentosSAFEMRevalidados;
    }

    public void setDocumentosSAFEMRevalidados(long documentosSAFEMRevalidados) {
        this.documentosSAFEMRevalidados = documentosSAFEMRevalidados;
    }

    public long getIdDevolucionDeSaldos() {
        return idDevolucionDeSaldos;
    }

    public void setIdDevolucionDeSaldos(long idDevolucionDeSaldos) {
        this.idDevolucionDeSaldos = idDevolucionDeSaldos;
    }

    public long getIdInformeFinanciero() {
        return idInformeFinanciero;
    }

    public void setIdInformeFinanciero(long idInformeFinanciero) {
        this.idInformeFinanciero = idInformeFinanciero;
    }

    public long getSaldoFavorEmpleador() {
        return saldoFavorEmpleador;
    }

    public void setSaldoFavorEmpleador(long saldoFavorEmpleador) {
        this.saldoFavorEmpleador = saldoFavorEmpleador;
    }

    public long getTotalDevolucionesE() {
        return totalDevolucionesE;
    }

    public void setTotalDevolucionesE(long totalDevolucionesE) {
        this.totalDevolucionesE = totalDevolucionesE;
    }

}
