package cl.araucana.sivegam.vo;

public class IngresosVO {

    private long idIngresos;
    private long idInformeFinanciero;
    private long provision;
    private long reintegro;
    private long totalIngresos;

    public long getIdInformeFinanciero() {
        return idInformeFinanciero;
    }

    public void setIdInformeFinanciero(long idInformeFinanciero) {
        this.idInformeFinanciero = idInformeFinanciero;
    }

    public long getIdIngresos() {
        return idIngresos;
    }

    public void setIdIngresos(long idIngresos) {
        this.idIngresos = idIngresos;
    }

    public long getProvision() {
        return provision;
    }

    public void setProvision(long provision) {
        this.provision = provision;
    }

    public long getReintegro() {
        return reintegro;
    }

    public void setReintegro(long reintegro) {
        this.reintegro = reintegro;
    }

    public long getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(long totalIngresos) {
        this.totalIngresos = totalIngresos;
    }
}
