package cl.araucana.independientes.vo;

public class EntradaIntercajaVO {

    /*declaracion de variables de la clase.*/
    private String resultado;
    private int codResultado;

    private String tipoArchivoLeido;
    private String fechaCabecera;
    private String rutaArchivo;
    private String nombreArchivo;
    private int tipoArchivo;
    private String fechaUltDia;
    private int statusProceso;

    /*Para consulta de la grilla*/
    private LinEntradaIntercajaVO[] lisEntradaIntercaja;
    private String fechaInicio;
    private String fechaFin;

    /*para estadistica por procesamiento de archivo Cobol*/
    private int totalRegProcesados;
    private int totalRegAplNegocio;
    private int totalRegPendientes;
    private String glosaIdTipoArchivo;
    private String glosaStatusProceso;
    private String fechaProceso;
    private long idMaestroArchivo;

    /*para retorno de idLogErrorAS400*/
    private String idLogAS400;

    /*Rangos para intercaja*/
    private String rangoUno;
    private String rangoDos;
    private String rangoTres;

    /*Fecha primer y ultimo dia del mes actual*/
    private String fechaConPimerDiaMes;
    private String fechaConUltimoDiaMes;

    /*Establecimiento de get and set*/
    public String getRangoDos() {
        return rangoDos;
    }
    public void setRangoDos(String rangoDos) {
        this.rangoDos = rangoDos;
    }
    public String getRangoTres() {
        return rangoTres;
    }
    public void setRangoTres(String rangoTres) {
        this.rangoTres = rangoTres;
    }
    public String getRangoUno() {
        return rangoUno;
    }
    public void setRangoUno(String rangoUno) {
        this.rangoUno = rangoUno;
    }	
    public String getIdLogAS400() {
        return idLogAS400;
    }
    public void setIdLogAS400(String idLogAS400) {
        this.idLogAS400 = idLogAS400;
    }
    public long getIdMaestroArchivo() {
        return idMaestroArchivo;
    }
    public void setIdMaestroArchivo(long idMaestroArchivo) {
        this.idMaestroArchivo = idMaestroArchivo;
    }
    public String getFechaProceso() {
        return fechaProceso;
    }
    public void setFechaProceso(String fechaProceso) {
        this.fechaProceso = fechaProceso;
    }
    public String getGlosaIdTipoArchivo() {
        return glosaIdTipoArchivo;
    }
    public void setGlosaIdTipoArchivo(String glosaIdTipoArchivo) {
        this.glosaIdTipoArchivo = glosaIdTipoArchivo;
    }
    public String getGlosaStatusProceso() {
        return glosaStatusProceso;
    }
    public void setGlosaStatusProceso(String glosaStatusProceso) {
        this.glosaStatusProceso = glosaStatusProceso;
    }
    public int getTotalRegAplNegocio() {
        return totalRegAplNegocio;
    }
    public void setTotalRegAplNegocio(int totalRegAplNegocio) {
        this.totalRegAplNegocio = totalRegAplNegocio;
    }
    public int getTotalRegPendientes() {
        return totalRegPendientes;
    }
    public void setTotalRegPendientes(int totalRegPendientes) {
        this.totalRegPendientes = totalRegPendientes;
    }
    public int getTotalRegProcesados() {
        return totalRegProcesados;
    }
    public void setTotalRegProcesados(int totalRegProcesados) {
        this.totalRegProcesados = totalRegProcesados;
    }
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public LinEntradaIntercajaVO[] getLisEntradaIntercaja() {
        return lisEntradaIntercaja;
    }
    public void setLisEntradaIntercaja(LinEntradaIntercajaVO[] lisEntradaIntercaja) {
        this.lisEntradaIntercaja = lisEntradaIntercaja;
    }
    public String getFechaUltDia() {
        return fechaUltDia;
    }
    public void setFechaUltDia(String fechaUltDia) {
        this.fechaUltDia = fechaUltDia;
    }
    public int getCodResultado() {
        return codResultado;
    }
    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }
    public String getFechaCabecera() {
        return fechaCabecera;
    }
    public void setFechaCabecera(String fechaCabecera) {
        this.fechaCabecera = fechaCabecera;
    }
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public String getRutaArchivo() {
        return rutaArchivo;
    }
    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
    public int getTipoArchivo() {
        return tipoArchivo;
    }
    public void setTipoArchivo(int tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }
    public String getTipoArchivoLeido() {
        return tipoArchivoLeido;
    }
    public void setTipoArchivoLeido(String tipoArchivoLeido) {
        this.tipoArchivoLeido = tipoArchivoLeido;
    }
    public int getStatusProceso() {
        return statusProceso;
    }
    public void setStatusProceso(int statusProceso) {
        this.statusProceso = statusProceso;
    }
    public String getFechaConPimerDiaMes() {
        return fechaConPimerDiaMes;
    }
    public void setFechaConPimerDiaMes(String fechaConPimerDiaMes) {
        this.fechaConPimerDiaMes = fechaConPimerDiaMes;
    }
    public String getFechaConUltimoDiaMes() {
        return fechaConUltimoDiaMes;
    }
    public void setFechaConUltimoDiaMes(String fechaConUltimoDiaMes) {
        this.fechaConUltimoDiaMes = fechaConUltimoDiaMes;
    }


}
