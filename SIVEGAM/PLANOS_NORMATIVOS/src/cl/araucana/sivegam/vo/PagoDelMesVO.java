package cl.araucana.sivegam.vo;

public class PagoDelMesVO {

    private long idPagoMes;
    private long idEgresos;
    private long asigFamTrabActivos;
    private long asigFamPensionados;
    private long asigFamTrabCesantes;
    private long asigFamInstituciones;
    private long totalPagoDelMes;

    public long getAsigFamInstituciones() {
        return asigFamInstituciones;
    }

    public void setAsigFamInstituciones(long asigFamInstituciones) {
        this.asigFamInstituciones = asigFamInstituciones;
    }

    public long getAsigFamPensionados() {
        return asigFamPensionados;
    }

    public void setAsigFamPensionados(long asigFamPensionados) {
        this.asigFamPensionados = asigFamPensionados;
    }

    public long getAsigFamTrabActivos() {
        return asigFamTrabActivos;
    }

    public void setAsigFamTrabActivos(long asigFamTrabActivos) {
        this.asigFamTrabActivos = asigFamTrabActivos;
    }

    public long getAsigFamTrabCesantes() {
        return asigFamTrabCesantes;
    }

    public void setAsigFamTrabCesantes(long asigFamTrabCesantes) {
        this.asigFamTrabCesantes = asigFamTrabCesantes;
    }

    public long getIdEgresos() {
        return idEgresos;
    }

    public void setIdEgresos(long idEgresos) {
        this.idEgresos = idEgresos;
    }

    public long getIdPagoMes() {
        return idPagoMes;
    }

    public void setIdPagoMes(long idPagoMes) {
        this.idPagoMes = idPagoMes;
    }

    public long getTotalPagoDelMes() {
        return totalPagoDelMes;
    }

    public void setTotalPagoDelMes(long totalPagoDelMes) {
        this.totalPagoDelMes = totalPagoDelMes;
    }
}
