package cl.araucana.sivegam.vo;

public class AfcVO {

    /** Variables de la clase. */
    private String resultado;
    private int codResultado;
    private LinAfcAFF01VO[] listAfcAFF01VO;
    private LinAfcAFF01EVO[] listLinAfcAFF01EVO;

    private String rutaExcel;
    private String rutaTxt;
    private String rutaErroresExcel;
    private String glosaTipoArchivo;

    private String nombreArchivo;
    private String flagReporteAfc;
    private String statusProcesoAfc;
    private String tipoArchivoAFC; // puede ser M o R
    private String codError;
    private String descripcionError;

    /** Generacion de get and set */
    public int getCodResultado() {
        return codResultado;
    }

    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }

    public String getGlosaTipoArchivo() {
        return glosaTipoArchivo;
    }

    public void setGlosaTipoArchivo(String glosaTipoArchivo) {
        this.glosaTipoArchivo = glosaTipoArchivo;
    }

    public LinAfcAFF01VO[] getListAfcAFF01VO() {
        return listAfcAFF01VO;
    }

    public void setListAfcAFF01VO(LinAfcAFF01VO[] listAfcAFF01VO) {
        this.listAfcAFF01VO = listAfcAFF01VO;
    }

    public LinAfcAFF01EVO[] getListLinAfcAFF01EVO() {
        return listLinAfcAFF01EVO;
    }

    public void setListLinAfcAFF01EVO(LinAfcAFF01EVO[] listLinAfcAFF01EVO) {
        this.listLinAfcAFF01EVO = listLinAfcAFF01EVO;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getRutaErroresExcel() {
        return rutaErroresExcel;
    }

    public void setRutaErroresExcel(String rutaErroresExcel) {
        this.rutaErroresExcel = rutaErroresExcel;
    }

    public String getRutaExcel() {
        return rutaExcel;
    }

    public void setRutaExcel(String rutaExcel) {
        this.rutaExcel = rutaExcel;
    }

    public String getRutaTxt() {
        return rutaTxt;
    }

    public void setRutaTxt(String rutaTxt) {
        this.rutaTxt = rutaTxt;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getFlagReporteAfc() {
        return flagReporteAfc;
    }

    public void setFlagReporteAfc(String flagReporteAfc) {
        this.flagReporteAfc = flagReporteAfc;
    }

    public String getStatusProcesoAfc() {
        return statusProcesoAfc;
    }

    public void setStatusProcesoAfc(String statusProcesoAfc) {
        this.statusProcesoAfc = statusProcesoAfc;
    }

    public String getTipoArchivoAFC() {
        return tipoArchivoAFC;
    }

    public void setTipoArchivoAFC(String tipoArchivoAFC) {
        this.tipoArchivoAFC = tipoArchivoAFC;
    }

    public String getCodError() {
        return codError;
    }

    public void setCodError(String codError) {
        this.codError = codError;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }
}
