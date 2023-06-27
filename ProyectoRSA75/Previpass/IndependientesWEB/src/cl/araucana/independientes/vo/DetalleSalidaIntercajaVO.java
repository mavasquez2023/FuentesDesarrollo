package cl.araucana.independientes.vo;

public class DetalleSalidaIntercajaVO {

    /*Declaración de variables.*/
    private long idDetalleSalida;
    private long idMaestroArchivo;
    private int cantMovimientos;
    private long idSesionDirectorio;
    private long idTipoMovIntercaja;
    private long idTipoMovIndependientes;

    /*Establecimiento de get and set.*/
    public int getCantMovimientos() {
        return cantMovimientos;
    }
    public void setCantMovimientos(int cantMovimientos) {
        this.cantMovimientos = cantMovimientos;
    }
    public long getIdDetalleSalida() {
        return idDetalleSalida;
    }
    public void setIdDetalleSalida(long idDetalleSalida) {
        this.idDetalleSalida = idDetalleSalida;
    }
    public long getIdMaestroArchivo() {
        return idMaestroArchivo;
    }
    public void setIdMaestroArchivo(long idMaestroArchivo) {
        this.idMaestroArchivo = idMaestroArchivo;
    }
    public long getIdSesionDirectorio() {
        return idSesionDirectorio;
    }
    public void setIdSesionDirectorio(long idSesionDirectorio) {
        this.idSesionDirectorio = idSesionDirectorio;
    }
    public long getIdTipoMovIndependientes() {
        return idTipoMovIndependientes;
    }
    public void setIdTipoMovIndependientes(long idTipoMovIndependientes) {
        this.idTipoMovIndependientes = idTipoMovIndependientes;
    }
    public long getIdTipoMovIntercaja() {
        return idTipoMovIntercaja;
    }
    public void setIdTipoMovIntercaja(long idTipoMovIntercaja) {
        this.idTipoMovIntercaja = idTipoMovIntercaja;
    }
}
