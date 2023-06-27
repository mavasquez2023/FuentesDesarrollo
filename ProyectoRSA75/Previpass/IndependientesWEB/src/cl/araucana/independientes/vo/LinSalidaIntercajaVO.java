package cl.araucana.independientes.vo;

public class LinSalidaIntercajaVO {

    /*Declaracion de variables*/
    private int ccafProcedencia;
    private String rutAfiliado;
    private String digVerificador;
    private String fechaSolicitudAfiliacion;
    private String codigoMovimiento;
    private String nombreAfiliado;
    private String apellidoPaternoAfiliado;
    private String apellidoMaternoAfiliado;
    private String ceros;
    private String blancoUno;
    private String codigoInternoUno;
    private String codigoInternoDos;
    private long montoUltCotizacion;
    private String fechaUltCotizacion;
    private String blancoDos;

    /*Campos adicionales*/
    private String tipoEstadoSolicitud;
    private String folio;
    private String tipoSolicitud;
    private String idAfiliadoAgrupacion;

    private String tipoEstadoAfiliado;
    private String idPersonaAfiliado;
    private long tipoCajaOrigen;

    //contadores de homologacion a archivo de salida
    private int countAfiliacionesNuevas;
    private int countDesafiliados;
    private int countCambioCCAF;
    private int countFallecidos;

    
    
    public LinSalidaIntercajaVO()
    {}
    
    public LinSalidaIntercajaVO(String rutAfiliado, String digVerificador, String fechaSolicitudAfiliacion, String nombreAfiliado, 
    	String apellidoPaternoAfiliado, String apellidoMaternoAfiliado, long montoUltCotizacion, String fechaUltCotizacion, 
    	String tipoEstadoSolicitud, String folio, String tipoSolicitud, String idAfiliadoAgrupacion, String tipoEstadoAfiliado, 
    	String idPersonaAfiliado, long tipoCajaOrigen) {
		super();
		this.rutAfiliado = rutAfiliado;
		this.digVerificador = digVerificador;
		this.fechaSolicitudAfiliacion = fechaSolicitudAfiliacion;
		this.nombreAfiliado = nombreAfiliado;
		this.apellidoPaternoAfiliado = apellidoPaternoAfiliado;
		this.apellidoMaternoAfiliado = apellidoMaternoAfiliado;
		this.montoUltCotizacion = montoUltCotizacion;
		this.fechaUltCotizacion = fechaUltCotizacion;
		this.tipoEstadoSolicitud = tipoEstadoSolicitud;
		this.folio = folio;
		this.tipoSolicitud = tipoSolicitud;
		this.idAfiliadoAgrupacion = idAfiliadoAgrupacion;
		this.tipoEstadoAfiliado = tipoEstadoAfiliado;
		this.idPersonaAfiliado = idPersonaAfiliado;
		this.tipoCajaOrigen = tipoCajaOrigen;
	}
	/*generacion de get and set*/
    public String getApellidoMaternoAfiliado() {
        return apellidoMaternoAfiliado;
    }
    public void setApellidoMaternoAfiliado(String apellidoMaternoAfiliado) {
        this.apellidoMaternoAfiliado = apellidoMaternoAfiliado;
    }
    public String getApellidoPaternoAfiliado() {
        return apellidoPaternoAfiliado;
    }
    public void setApellidoPaternoAfiliado(String apellidoPaternoAfiliado) {
        this.apellidoPaternoAfiliado = apellidoPaternoAfiliado;
    }
    public String getBlancoDos() {
        return blancoDos;
    }
    public void setBlancoDos(String blancoDos) {
        this.blancoDos = blancoDos;
    }
    public String getBlancoUno() {
        return blancoUno;
    }
    public void setBlancoUno(String blancoUno) {
        this.blancoUno = blancoUno;
    }
    public int getCcafProcedencia() {
        return ccafProcedencia;
    }
    public void setCcafProcedencia(int ccafProcedencia) {
        this.ccafProcedencia = ccafProcedencia;
    }
    public String getCeros() {
        return ceros;
    }
    public void setCeros(String ceros) {
        this.ceros = ceros;
    }
    public String getCodigoInternoDos() {
        return codigoInternoDos;
    }
    public void setCodigoInternoDos(String codigoInternoDos) {
        this.codigoInternoDos = codigoInternoDos;
    }
    public String getCodigoInternoUno() {
        return codigoInternoUno;
    }
    public void setCodigoInternoUno(String codigoInternoUno) {
        this.codigoInternoUno = codigoInternoUno;
    }
    public String getCodigoMovimiento() {
        return codigoMovimiento;
    }
    public void setCodigoMovimiento(String codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }
    public int getCountAfiliacionesNuevas() {
        return countAfiliacionesNuevas;
    }
    public void setCountAfiliacionesNuevas(int countAfiliacionesNuevas) {
        this.countAfiliacionesNuevas = countAfiliacionesNuevas;
    }
    public int getCountCambioCCAF() {
        return countCambioCCAF;
    }
    public void setCountCambioCCAF(int countCambioCCAF) {
        this.countCambioCCAF = countCambioCCAF;
    }
    public int getCountDesafiliados() {
        return countDesafiliados;
    }
    public void setCountDesafiliados(int countDesafiliados) {
        this.countDesafiliados = countDesafiliados;
    }
    public int getCountFallecidos() {
        return countFallecidos;
    }
    public void setCountFallecidos(int countFallecidos) {
        this.countFallecidos = countFallecidos;
    }
    public String getDigVerificador() {
        return digVerificador;
    }
    public void setDigVerificador(String digVerificador) {
        this.digVerificador = digVerificador;
    }
    public String getFechaSolicitudAfiliacion() {
        return fechaSolicitudAfiliacion;
    }
    public void setFechaSolicitudAfiliacion(String fechaSolicitudAfiliacion) {
        this.fechaSolicitudAfiliacion = fechaSolicitudAfiliacion;
    }
    public String getFechaUltCotizacion() {
        return fechaUltCotizacion;
    }
    public void setFechaUltCotizacion(String fechaUltCotizacion) {
        this.fechaUltCotizacion = fechaUltCotizacion;
    }
    public String getFolio() {
        return folio;
    }
    public void setFolio(String folio) {
        this.folio = folio;
    }
    public String getIdAfiliadoAgrupacion() {
        return idAfiliadoAgrupacion;
    }
    public void setIdAfiliadoAgrupacion(String idAfiliadoAgrupacion) {
        this.idAfiliadoAgrupacion = idAfiliadoAgrupacion;
    }
    public String getIdPersonaAfiliado() {
        return idPersonaAfiliado;
    }
    public void setIdPersonaAfiliado(String idPersonaAfiliado) {
        this.idPersonaAfiliado = idPersonaAfiliado;
    }
    public long getMontoUltCotizacion() {
        return montoUltCotizacion;
    }
    public void setMontoUltCotizacion(long montoUltCotizacion) {
        this.montoUltCotizacion = montoUltCotizacion;
    }
    public String getNombreAfiliado() {
        return nombreAfiliado;
    }
    public void setNombreAfiliado(String nombreAfiliado) {
        this.nombreAfiliado = nombreAfiliado;
    }
    public String getRutAfiliado() {
        return rutAfiliado;
    }
    public void setRutAfiliado(String rutAfiliado) {
        this.rutAfiliado = rutAfiliado;
    }
    public long getTipoCajaOrigen() {
        return tipoCajaOrigen;
    }
    public void setTipoCajaOrigen(long tipoCajaOrigen) {
        this.tipoCajaOrigen = tipoCajaOrigen;
    }
    public String getTipoEstadoAfiliado() {
        return tipoEstadoAfiliado;
    }
    public void setTipoEstadoAfiliado(String tipoEstadoAfiliado) {
        this.tipoEstadoAfiliado = tipoEstadoAfiliado;
    }
    public String getTipoEstadoSolicitud() {
        return tipoEstadoSolicitud;
    }
    public void setTipoEstadoSolicitud(String tipoEstadoSolicitud) {
        this.tipoEstadoSolicitud = tipoEstadoSolicitud;
    }
    public String getTipoSolicitud() {
        return tipoSolicitud;
    }
    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

}
