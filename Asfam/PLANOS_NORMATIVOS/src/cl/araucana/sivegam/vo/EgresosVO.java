package cl.araucana.sivegam.vo;

public class EgresosVO {

    private long idEgresos;
    private long idInformeFinanciero;
    private long docRevalidados;
    private long comisionAdministracion;
    private long totalEgresos;

    public long getComisionAdministracion() {
        return comisionAdministracion;
    }

    public void setComisionAdministracion(long comisionAdministracion) {
        this.comisionAdministracion = comisionAdministracion;
    }

    public long getDocRevalidados() {
        return docRevalidados;
    }

    public void setDocRevalidados(long docRevalidados) {
        this.docRevalidados = docRevalidados;
    }

    public long getIdEgresos() {
        return idEgresos;
    }

    public void setIdEgresos(long idEgresos) {
        this.idEgresos = idEgresos;
    }

    public long getIdInformeFinanciero() {
        return idInformeFinanciero;
    }

    public void setIdInformeFinanciero(long idInformeFinanciero) {
        this.idInformeFinanciero = idInformeFinanciero;
    }

    public long getTotalEgresos() {
        return totalEgresos;
    }

    public void setTotalEgresos(long totalEgresos) {
        this.totalEgresos = totalEgresos;
    }
}
