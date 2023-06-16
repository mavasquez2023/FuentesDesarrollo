package cl.araucana.sivegam.vo;

public class PagosRetroactivosVO {

    private long idPagosRetroactivos;
    private long idEgresos;
    private long asigFamTrabActiv;
    private long asifFamTrabPens;
    private long asigFamTrabCes;
    private long asigFamTrabInst;
    private long totalPagosRetroactivos;

    public long getAsifFamTrabPens() {
        return asifFamTrabPens;
    }

    public void setAsifFamTrabPens(long asifFamTrabPens) {
        this.asifFamTrabPens = asifFamTrabPens;
    }

    public long getAsigFamTrabActiv() {
        return asigFamTrabActiv;
    }

    public void setAsigFamTrabActiv(long asigFamTrabActiv) {
        this.asigFamTrabActiv = asigFamTrabActiv;
    }

    public long getAsigFamTrabCes() {
        return asigFamTrabCes;
    }

    public void setAsigFamTrabCes(long asigFamTrabCes) {
        this.asigFamTrabCes = asigFamTrabCes;
    }

    public long getAsigFamTrabInst() {
        return asigFamTrabInst;
    }

    public void setAsigFamTrabInst(long asigFamTrabInst) {
        this.asigFamTrabInst = asigFamTrabInst;
    }

    public long getIdEgresos() {
        return idEgresos;
    }

    public void setIdEgresos(long idEgresos) {
        this.idEgresos = idEgresos;
    }

    public long getIdPagosRetroactivos() {
        return idPagosRetroactivos;
    }

    public void setIdPagosRetroactivos(long idPagosRetroactivos) {
        this.idPagosRetroactivos = idPagosRetroactivos;
    }

    public long getTotalPagosRetroactivos() {
        return totalPagosRetroactivos;
    }

    public void setTotalPagosRetroactivos(long totalPagosRetroactivos) {
        this.totalPagosRetroactivos = totalPagosRetroactivos;
    }

}
