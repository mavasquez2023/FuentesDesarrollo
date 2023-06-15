package cl.araucana.sivegam.vo;

public class RespuestaVO {

    /* variables de la clase. */
    private int codRespuesta;
    private String msgRespuesta;
    private String rutaArchivo;
    private String periodoProceso;
    private int fechaProceso;
    private int idTipoReporte;
    private long maxId;
    private int mesConsultado;
    private int status;
    private int statusCarga;
    private String archivoCarga;

    /* generacion de get and set */

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMesConsultado() {
        return mesConsultado;
    }

    public void setMesConsultado(int mesConsultado) {
        this.mesConsultado = mesConsultado;
    }

    public long getMaxId() {
        return maxId;
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }

    public int getIdTipoReporte() {
        return idTipoReporte;
    }

    public void setIdTipoReporte(int idTipoReporte) {
        this.idTipoReporte = idTipoReporte;
    }

    public String getPeriodoProceso() {
        return periodoProceso;
    }

    public void setPeriodoProceso(String periodoProceso) {
        this.periodoProceso = periodoProceso;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public int getCodRespuesta() {
        return codRespuesta;
    }

    public void setCodRespuesta(int codRespuesta) {
        this.codRespuesta = codRespuesta;
    }

    public String getMsgRespuesta() {
        return msgRespuesta;
    }

    public void setMsgRespuesta(String msgRespuesta) {
        this.msgRespuesta = msgRespuesta;
    }

    public int getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(int fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public String getArchivoCarga() {
        return archivoCarga;
    }

    public void setArchivoCarga(String archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public void setStatusCarga(int status_carga) {
        this.statusCarga = status_carga;
    }

    public int getStatusCarga() {
        return statusCarga;
    }

}
