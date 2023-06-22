package cl.araucana.sivegam.vo;

public class Sif018VO {

    /* variables de la clase Sif018VO */
    private String resultado;
    private int codResultado;

    public LinSif018VO[] listSif018;
    private String archivoInforme;
    private String rutaArchivo;
    private String nombreArchivo;
    private long rutSearch;
    private int tipoArchivo;
    private int periodoArchivo;
    private String tipoArchivoGlosa;
    private String periodoArchivoGlosa;
    private long idsif018;
    private int statusProceso;
    private long rangoUno;
    private long rangoDos;

    /* Establecimiento de get and set */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getArchivoInforme() {
        return archivoInforme;
    }

    public void setArchivoInforme(String archivoInforme) {
        this.archivoInforme = archivoInforme;
    }

    public int getCodResultado() {
        return codResultado;
    }

    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }

    public LinSif018VO[] getListSif018() {
        return listSif018;
    }

    public void setListSif018(LinSif018VO[] listSif018) {
        this.listSif018 = listSif018;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public long getRutSearch() {
        return rutSearch;
    }

    public void setRutSearch(long rutSearch) {
        this.rutSearch = rutSearch;
    }

    public int getPeriodoArchivo() {
        return periodoArchivo;
    }

    public void setPeriodoArchivo(int periodoArchivo) {
        this.periodoArchivo = periodoArchivo;
    }

    public String getPeriodoArchivoGlosa() {
        return periodoArchivoGlosa;
    }

    public void setPeriodoArchivoGlosa(String periodoArchivoGlosa) {
        this.periodoArchivoGlosa = periodoArchivoGlosa;
    }

    public int getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(int tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getTipoArchivoGlosa() {
        return tipoArchivoGlosa;
    }

    public void setTipoArchivoGlosa(String tipoArchivoGlosa) {
        this.tipoArchivoGlosa = tipoArchivoGlosa;
    }

    public long getIdsif018() {
        return idsif018;
    }

    public void setIdsif018(long idsif018) {
        this.idsif018 = idsif018;
    }

    public int getStatusProceso() {
        return statusProceso;
    }

    public void setStatusProceso(int statusProceso) {
        this.statusProceso = statusProceso;
    }

    public long getRangoDos() {
        return rangoDos;
    }

    public void setRangoDos(long rangoDos) {
        this.rangoDos = rangoDos;
    }

    public long getRangoUno() {
        return rangoUno;
    }

    public void setRangoUno(long rangoUno) {
        this.rangoUno = rangoUno;
    }
}
