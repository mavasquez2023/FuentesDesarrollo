package cl.araucana.independientes.vo;

import java.util.Date;

public class BaseComunIntercajaVO {

    /*declaracion de variables de la clase.*/
    private String resultado;
    private int codResultado;

    private long idDetalleBaseComun;
    private long idMaestroArchivo;
    private long idCcafOrigen;
    private long idDocumento;
    private String digVerificador;
    private String nombreAfiliado;
    private String fechaSolicitud;
    private Date fechaSolicitudDate;
    private String fechaIngreso;
    private Date fechaIngresoDate;
    private long montoUltCotiz;
    private String fechaUltCotiz;
    private Date fechaUltCotizDate;

    /*Establecimiento de gent and set*/
    public int getCodResultado() {
        return codResultado;
    }
    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }
    public String getDigVerificador() {
        return digVerificador;
    }
    public void setDigVerificador(String digVerificador) {
        this.digVerificador = digVerificador;
    }
    public String getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public Date getFechaIngresoDate() {
        return fechaIngresoDate;
    }
    public void setFechaIngresoDate(Date fechaIngresoDate) {
        this.fechaIngresoDate = fechaIngresoDate;
    }
    public String getFechaSolicitud() {
        return fechaSolicitud;
    }
    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    public Date getFechaSolicitudDate() {
        return fechaSolicitudDate;
    }
    public void setFechaSolicitudDate(Date fechaSolicitudDate) {
        this.fechaSolicitudDate = fechaSolicitudDate;
    }
    public String getFechaUltCotiz() {
        return fechaUltCotiz;
    }
    public void setFechaUltCotiz(String fechaUltCotiz) {
        this.fechaUltCotiz = fechaUltCotiz;
    }
    public Date getFechaUltCotizDate() {
        return fechaUltCotizDate;
    }
    public void setFechaUltCotizDate(Date fechaUltCotizDate) {
        this.fechaUltCotizDate = fechaUltCotizDate;
    }
    public long getIdCcafOrigen() {
        return idCcafOrigen;
    }
    public void setIdCcafOrigen(long idCcafOrigen) {
        this.idCcafOrigen = idCcafOrigen;
    }
    public long getIdDetalleBaseComun() {
        return idDetalleBaseComun;
    }
    public void setIdDetalleBaseComun(long idDetalleBaseComun) {
        this.idDetalleBaseComun = idDetalleBaseComun;
    }
    public long getIdDocumento() {
        return idDocumento;
    }
    public void setIdDocumento(long idDocumento) {
        this.idDocumento = idDocumento;
    }
    public long getIdMaestroArchivo() {
        return idMaestroArchivo;
    }
    public void setIdMaestroArchivo(long idMaestroArchivo) {
        this.idMaestroArchivo = idMaestroArchivo;
    }
    public long getMontoUltCotiz() {
        return montoUltCotiz;
    }
    public void setMontoUltCotiz(long montoUltCotiz) {
        this.montoUltCotiz = montoUltCotiz;
    }
    public String getNombreAfiliado() {
        return nombreAfiliado;
    }
    public void setNombreAfiliado(String nombreAfiliado) {
        this.nombreAfiliado = nombreAfiliado;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }


}
