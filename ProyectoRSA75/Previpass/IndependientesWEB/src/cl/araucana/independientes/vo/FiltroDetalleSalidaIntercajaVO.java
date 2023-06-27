package cl.araucana.independientes.vo;

import java.util.Date;

public class FiltroDetalleSalidaIntercajaVO {

    /*Declaración de variables.*/
    private long 	idFiltroDetalle;
    private long 	idDocumento;
    private String	digVerificador;
    private String 	fechaFiltro;
    private Date 	fechaFiltroDate;

    public String getDigVerificador() {
        return digVerificador;
    }
    public void setDigVerificador(String digVerificador) {
        this.digVerificador = digVerificador;
    }
    public String getFechaFiltro() {
        return fechaFiltro;
    }
    public void setFechaFiltro(String fechaFiltro) {
        this.fechaFiltro = fechaFiltro;
    }
    public Date getFechaFiltroDate() {
        return fechaFiltroDate;
    }
    public void setFechaFiltroDate(Date fechaFiltroDate) {
        this.fechaFiltroDate = fechaFiltroDate;
    }
    public long getIdDocumento() {
        return idDocumento;
    }
    public void setIdDocumento(long idDocumento) {
        this.idDocumento = idDocumento;
    }
    public long getIdFiltroDetalle() {
        return idFiltroDetalle;
    }
    public void setIdFiltroDetalle(long idFiltroDetalle) {
        this.idFiltroDetalle = idFiltroDetalle;
    }
}
