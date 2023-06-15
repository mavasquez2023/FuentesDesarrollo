package cl.araucana.sivegam.vo;

public class InformeFinancieroVO {

    /* variables de la clase informe financiero vo. */
    private String resultado;
    private int codResultado;
    // private LinInformeFinancieroVO[] listInformeFinanciero;
    private IngresosVO ingresoVO;
    private PagoDelMesVO pagoDelMesVO;
    private EgresosVO egresosVO;
    private PagosRetroactivosVO pagosRetroVO;
    private DevolucionDeSaldosVO devolucionSaldosVO;
    private InfoInformeFinancieroVO informacionInformeFinancieroVO;
    private DevolucionesVO devolucionesVO;

    /* generacion de get and set. */
    public DevolucionesVO getDevolucionesVO() {
        return devolucionesVO;
    }

    public void setDevolucionesVO(DevolucionesVO devolucionesVO) {
        this.devolucionesVO = devolucionesVO;
    }

    public DevolucionDeSaldosVO getDevolucionSaldosVO() {
        return devolucionSaldosVO;
    }

    public void setDevolucionSaldosVO(DevolucionDeSaldosVO devolucionSaldosVO) {
        this.devolucionSaldosVO = devolucionSaldosVO;
    }

    public EgresosVO getEgresosVO() {
        return egresosVO;
    }

    public void setEgresosVO(EgresosVO egresosVO) {
        this.egresosVO = egresosVO;
    }

    public InfoInformeFinancieroVO getInformacionInformeFinancieroVO() {
        return informacionInformeFinancieroVO;
    }

    public void setInformacionInformeFinancieroVO(InfoInformeFinancieroVO informacionInformeFinancieroVO) {
        this.informacionInformeFinancieroVO = informacionInformeFinancieroVO;
    }

    public IngresosVO getIngresoVO() {
        return ingresoVO;
    }

    public void setIngresoVO(IngresosVO ingresoVO) {
        this.ingresoVO = ingresoVO;
    }

    public PagoDelMesVO getPagoDelMesVO() {
        return pagoDelMesVO;
    }

    public void setPagoDelMesVO(PagoDelMesVO pagoDelMesVO) {
        this.pagoDelMesVO = pagoDelMesVO;
    }

    public PagosRetroactivosVO getPagosRetroVO() {
        return pagosRetroVO;
    }

    public void setPagosRetroVO(PagosRetroactivosVO pagosRetroVO) {
        this.pagosRetroVO = pagosRetroVO;
    }

    public int getCodResultado() {
        return codResultado;
    }

    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }

    /*
     * public LinInformeFinancieroVO[] getListInformeFinanciero() { return
     * listInformeFinanciero; } public void setListInformeFinanciero(
     * LinInformeFinancieroVO[] listInformeFinanciero) {
     * this.listInformeFinanciero = listInformeFinanciero; }
     */
    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
