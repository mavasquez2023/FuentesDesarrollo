package cl.araucana.independientes.vo;

import java.util.Date;

/* Clase SolicitudVO.
 * Contiene las variables que serán usadas para transportar la información referente al objeto SolicitudVO desde el formulario.
 * Las variables usadas son las siguientes:
 * 	.-	idSolicitud:corresponde al id de secuencia usado en el sistema y de caracter único e igual al id de solicitud de la tabla afiliado.
 * 	.-	idAfiliadoAgrupacion: al id de secuencia usado en el sistema y de caracter unico e igual al id de solicitud de la tabla afiliado y por tanto igual al idpersona de la tabla persona
 * 	.-	folio: corresponde al folio ingresado debido a la solicitud. Este es de caracter único. 
 * 	.-	tipoCajaOrigen: corresponde al id de la caja de compensación a la que pertenece la persona que solicita ser afiliado.
 * 	.-	fechaVigencia: corresponde a la en que entra en vigencia la solicitud de afiliación.
 * 	.-	fechaVigenciaDate: usada para seetar la fecha que se trae de la base de datos.
 * 	.-	tipoEstadoSolicitud:  corresponde al id del estado de la solicitud ingresada.
 * 	.-	desTipoEstadoSolicitud: corresponde a la glosa asociada al tipoEstadoSolicitud
 * 	.-	fechaIngreso: corresponde a la fecha de ingreso de la solicitud.
 * 	.-	fechaIngresoDate: usada para setear la fecha que se trae de la base de datos.
 * 	.-	idAnalista: corresponde al rut del analista que ingreso en el sistema y que ingreso la solicitud
 * 	.-	fechaFirma: relacionada a la fecha en que el afiliado firma la solicitud.
 * 	.-	fechaFirmaDate: usada para seetar la fecha que se trae de la base de datos.
 * 	.-	idCaptador: corresponde al rut del analista captador, casi similar a idanalista.
 * */
public class SolicitudVO {

    /*Declaracion de variables*/
    private long idSolicitud;
    private long idAfiliadoAgrupacion;
    private long folio;
    private int tipoSolicitud;
    private long tipoCajaOrigen;
    private String fechaVigencia;
    private Date fechaVigenciaDate;
    private int tipoMotivoDesafiliacion;
    private int descTipoMotivoDesafiliacion;
    private String observaciones;
    private int tipoEstadoSolicitud;
    private String desTipoEstadoSolicitud;
    private String fechaUltAporte;
    private Date fechaUltAporteDate;
    private int flagIntercaja;
    private String fechaIngreso;
    private Date fechaIngresoDate;
    private long idAnalista;
    private String fechaFirma;
    private Date fechaFirmaDate;
    private long idCaptador;
    private String horaCaptacion;
    private String resolucionDirectorio;	
    private int oficina;
    private String desOficina;
    private String comentarios;

    public String getDesOficina() {
        return desOficina;
    }
    public void setDesOficina(String desOficina) {
        this.desOficina = desOficina;
    }
    public String getDesTipoEstadoSolicitud() {
        return desTipoEstadoSolicitud;
    }
    public void setDesTipoEstadoSolicitud(String desTipoEstadoSolicitud) {
        this.desTipoEstadoSolicitud = desTipoEstadoSolicitud;
    }
    public String getFechaFirma() {
        return fechaFirma;
    }
    public void setFechaFirma(String fechaFirma) {
        this.fechaFirma = fechaFirma;
    }
    public Date getFechaFirmaDate() {
        return fechaFirmaDate;
    }
    public void setFechaFirmaDate(Date fechaFirmaDate) {
        this.fechaFirmaDate = fechaFirmaDate;
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
    public String getFechaUltAporte() {
        return fechaUltAporte;
    }
    public void setFechaUltAporte(String fechaUltAporte) {
        this.fechaUltAporte = fechaUltAporte;
    }
    public String getFechaVigencia() {
        return fechaVigencia;
    }
    public void setFechaVigencia(String fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }
    public Date getFechaVigenciaDate() {
        return fechaVigenciaDate;
    }
    public void setFechaVigenciaDate(Date fechaVigenciaDate) {
        this.fechaVigenciaDate = fechaVigenciaDate;
    }
    public int getFlagIntercaja() {
        return flagIntercaja;
    }
    public void setFlagIntercaja(int flagIntercaja) {
        this.flagIntercaja = flagIntercaja;
    }
    public long getFolio() {
        return folio;
    }
    public void setFolio(long folio) {
        this.folio = folio;
    }
    public String getHoraCaptacion() {
        return horaCaptacion;
    }
    public void setHoraCaptacion(String horaCaptacion) {
        this.horaCaptacion = horaCaptacion;
    }
    public long getIdAfiliadoAgrupacion() {
        return idAfiliadoAgrupacion;
    }
    public void setIdAfiliadoAgrupacion(long idAfiliadoAgrupacion) {
        this.idAfiliadoAgrupacion = idAfiliadoAgrupacion;
    }
    public long getIdAnalista() {
        return idAnalista;
    }
    public void setIdAnalista(long idAnalista) {
        this.idAnalista = idAnalista;
    }
    public long getIdCaptador() {
        return idCaptador;
    }
    public void setIdCaptador(long idCaptador) {
        this.idCaptador = idCaptador;
    }
    public long getIdSolicitud() {
        return idSolicitud;
    }
    public void setIdSolicitud(long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    public int getOficina() {
        return oficina;
    }
    public void setOficina(int oficina) {
        this.oficina = oficina;
    }
    public String getResolucionDirectorio() {
        return resolucionDirectorio;
    }
    public void setResolucionDirectorio(String resolucionDirectorio) {
        this.resolucionDirectorio = resolucionDirectorio;
    }
    public long getTipoCajaOrigen() {
        return tipoCajaOrigen;
    }
    public void setTipoCajaOrigen(long tipoCajaOrigen) {
        this.tipoCajaOrigen = tipoCajaOrigen;
    }
    public int getTipoEstadoSolicitud() {
        return tipoEstadoSolicitud;
    }
    public void setTipoEstadoSolicitud(int tipoEstadoSolicitud) {
        this.tipoEstadoSolicitud = tipoEstadoSolicitud;
    }
    public int getTipoMotivoDesafiliacion() {
        return tipoMotivoDesafiliacion;
    }
    public void setTipoMotivoDesafiliacion(int tipoMotivoDesafiliacion) {
        this.tipoMotivoDesafiliacion = tipoMotivoDesafiliacion;
    }
    public int getTipoSolicitud() {
        return tipoSolicitud;
    }
    public void setTipoSolicitud(int tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }
    public Date getFechaUltAporteDate() {
        return fechaUltAporteDate;
    }
    public void setFechaUltAporteDate(Date fechaUltAporteDate) {
        this.fechaUltAporteDate = fechaUltAporteDate;
    }
    public int getDescTipoMotivoDesafiliacion() {
        return descTipoMotivoDesafiliacion;
    }
    public void setDescTipoMotivoDesafiliacion(int descTipoMotivoDesafiliacion) {
        this.descTipoMotivoDesafiliacion = descTipoMotivoDesafiliacion;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getComentarios() {
        return comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
