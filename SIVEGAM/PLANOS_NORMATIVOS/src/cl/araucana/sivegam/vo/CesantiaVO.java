package cl.araucana.sivegam.vo;

public class CesantiaVO {

    /** Variables de la clase */
    private String resultado;
    private int codResultado;
    private LinCesantia041VO[] listCesantia041VO;
    private LinCesantia042VO[] listCesantia042VO;
    private LinCesantia043VO[] listCesantia043VO;
    private LinCesantia044VO[] listCesantia044VO;

    private String rutaExcelCesantia;
    private String rutaTxtCesantia;
    private String rutaErroresExcelCesantia;
    private String glosaTipoArchivoCesantia;

    private String nombreArchivoCesantia;
    private String flagReporteCesantia;
    private String statusProcesoCesantia;

    private String codError;
    private String descripcionError;

    /** Generacion de get and set */
    public int getCodResultado() {
        return codResultado;
    }

    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }

    public String getFlagReporteCesantia() {
        return flagReporteCesantia;
    }

    public void setFlagReporteCesantia(String flagReporteCesantia) {
        this.flagReporteCesantia = flagReporteCesantia;
    }

    public String getGlosaTipoArchivoCesantia() {
        return glosaTipoArchivoCesantia;
    }

    public void setGlosaTipoArchivoCesantia(String glosaTipoArchivoCesantia) {
        this.glosaTipoArchivoCesantia = glosaTipoArchivoCesantia;
    }

    public LinCesantia041VO[] getListCesantia041VO() {
        return listCesantia041VO;
    }

    public void setListCesantia041VO(LinCesantia041VO[] listCesantia041VO) {
        this.listCesantia041VO = listCesantia041VO;
    }

    public LinCesantia042VO[] getListCesantia042VO() {
        return listCesantia042VO;
    }

    public void setListCesantia042VO(LinCesantia042VO[] listCesantia042VO) {
        this.listCesantia042VO = listCesantia042VO;
    }

    public LinCesantia043VO[] getListCesantia043VO() {
        return listCesantia043VO;
    }

    public void setListCesantia043VO(LinCesantia043VO[] listCesantia043VO) {
        this.listCesantia043VO = listCesantia043VO;
    }

    public LinCesantia044VO[] getListCesantia044VO() {
        return listCesantia044VO;
    }

    public void setListCesantia044VO(LinCesantia044VO[] listCesantia044VO) {
        this.listCesantia044VO = listCesantia044VO;
    }

    public String getNombreArchivoCesantia() {
        return nombreArchivoCesantia;
    }

    public void setNombreArchivoCesantia(String nombreArchivoCesantia) {
        this.nombreArchivoCesantia = nombreArchivoCesantia;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getRutaErroresExcelCesantia() {
        return rutaErroresExcelCesantia;
    }

    public void setRutaErroresExcelCesantia(String rutaErroresExcelCesantia) {
        this.rutaErroresExcelCesantia = rutaErroresExcelCesantia;
    }

    public String getRutaExcelCesantia() {
        return rutaExcelCesantia;
    }

    public void setRutaExcelCesantia(String rutaExcelCesantia) {
        this.rutaExcelCesantia = rutaExcelCesantia;
    }

    public String getRutaTxtCesantia() {
        return rutaTxtCesantia;
    }

    public void setRutaTxtCesantia(String rutaTxtCesantia) {
        this.rutaTxtCesantia = rutaTxtCesantia;
    }

    public String getStatusProcesoCesantia() {
        return statusProcesoCesantia;
    }

    public void setStatusProcesoCesantia(String statusProcesoCesantia) {
        this.statusProcesoCesantia = statusProcesoCesantia;
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
