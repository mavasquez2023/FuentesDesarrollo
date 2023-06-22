package cl.araucana.sivegam.vo.param;

import cl.araucana.sivegam.dao.ParametrosDAO;
import cl.araucana.sivegam.vo.CausalReliquidacionVO;
import cl.araucana.sivegam.vo.CodigoArchivoVO;
import cl.araucana.sivegam.vo.CodigoBancoVO;
import cl.araucana.sivegam.vo.CodigoErrorVO;
import cl.araucana.sivegam.vo.CodigoTramoVO;
import cl.araucana.sivegam.vo.DominioVO;
import cl.araucana.sivegam.vo.EstadoDelDocumentoVO;
import cl.araucana.sivegam.vo.FormatoReporteVO;
import cl.araucana.sivegam.vo.ModalidadDePagoVO;
import cl.araucana.sivegam.vo.PeriodoProcesoVO;
import cl.araucana.sivegam.vo.ProcesosAFCCesantiaVO;
import cl.araucana.sivegam.vo.StatusProcesoVO;
import cl.araucana.sivegam.vo.TipoArchivoVO;
import cl.araucana.sivegam.vo.TipoBeneficiarioVO;
import cl.araucana.sivegam.vo.TipoBeneficioVO;
import cl.araucana.sivegam.vo.TipoCausanteVO;
import cl.araucana.sivegam.vo.TipoDeclaracionVO;
import cl.araucana.sivegam.vo.TipoEgresoVO;
import cl.araucana.sivegam.vo.TipoEmisionVO;
import cl.araucana.sivegam.vo.TipoOrigenVO;
import cl.araucana.sivegam.vo.TipoProcesosVO;
import cl.araucana.sivegam.vo.TipoReintegroVO;
import cl.araucana.sivegam.vo.TipoReporteVO;
import cl.araucana.sivegam.vo.TipoSaldoVO;
import cl.araucana.sivegam.vo.ValoresConexionAS400VO;

/*	Clase: ListadoParametros.
 * 	Contiene arreglos de Parametros que serán usados en la aplicación.
 * */
public class ListadoParametros {

    private static ListadoParametros LISTA_UNICA = null;

    private String servletContextRealPath; // No esta en el constructor
    private TipoProcesosVO[] listTipoProcesos;
    private StatusProcesoVO[] listStatusProceso;
    private StatusProcesoVO[] listStatusProcesoCarga;
    private PeriodoProcesoVO[] listPeriodoProcesos;
    private FormatoReporteVO[] listFormatoReportes;
    private TipoReporteVO[] listTipoReportes;
    private ValoresConexionAS400VO[] listValoresConexionToAS400;
    private TipoOrigenVO[] listTipoOrigen;

    /* Parametricas de SIVEGAM */
    private TipoBeneficiarioVO[] listTipoBeneficiario;
    private TipoCausanteVO[] listTipoCausante;
    private TipoBeneficioVO[] listTipoBeneficio;
    private CodigoArchivoVO[] listCodigoArchivo;
    private TipoEmisionVO[] listTipoEmision;
    private TipoDeclaracionVO[] listTipoDeclaracion;
    private TipoReintegroVO[] listTipoReintegro;
    private TipoSaldoVO[] listTipoSaldo;
    private CausalReliquidacionVO[] listCausalReliquidacion;
    private TipoEgresoVO[] listTipoEgreso;
    private ModalidadDePagoVO[] listModalidadPago;
    private CodigoBancoVO[] listCodigoBanco;
    private EstadoDelDocumentoVO[] listEstadoDocumento;
    private TipoArchivoVO[] listTipoArchivo;
    private ProcesosAFCCesantiaVO[] listProcesoAfcCesantia;
    private CodigoTramoVO[] listCodigoTramo;
    /* Fin parametricas de SIVEGAM */
    /* Parametricas de CESANTIA - CMO */
    private CodigoErrorVO[] listCodigoError;
    private DominioVO[] listDominio;

    /* Parametricas de AFC */
    private CodigoErrorVO[] listCodigoErrorAFC;

    public CodigoErrorVO[] getListCodigoErrorAFC() {
        return listCodigoErrorAFC;
    }

    public void setListCodigoErrorAFC(CodigoErrorVO[] listCodigoErrorAFC) {
        this.listCodigoErrorAFC = listCodigoErrorAFC;
    }

    private ListadoParametros() {

    	try{
	        this.servletContextRealPath = "";
	        this.listTipoProcesos = ParametrosDAO.obtenerTipoProcesos();
	        this.listStatusProceso = ParametrosDAO.obtenerStatusProcesos();
	        this.listStatusProcesoCarga = ParametrosDAO.obtenerStatusProcesoCarga();
	        this.listPeriodoProcesos = ParametrosDAO.obtenerPeridosProcesos();
	        this.listFormatoReportes = ParametrosDAO.obtenerFormatoReportes();
	        this.listTipoReportes = ParametrosDAO.obtenerTipoReportes();
	        this.listValoresConexionToAS400 = ParametrosDAO.obtenerValoresConexionAS400();
	        this.listTipoOrigen = ParametrosDAO.obtenerTipoOrigen();
	
	        this.listTipoBeneficiario = ParametrosDAO.obtenerTipoBeneficiario();
	        this.listTipoCausante = ParametrosDAO.obtenerTipoCausante();
	        this.listTipoBeneficio = ParametrosDAO.obtenerTipoBeneficio();
	        this.listCodigoArchivo = ParametrosDAO.obtenerCodigoArchivo();
	        this.listTipoEmision = ParametrosDAO.obtenerTipoEmision();
	        this.listTipoDeclaracion = ParametrosDAO.obtenerTipoDeclaracion();
	        this.listTipoReintegro = ParametrosDAO.obtenerTipoReintegro();
	        this.listTipoSaldo = ParametrosDAO.obtenerTipoSaldo();
	        this.listCausalReliquidacion = ParametrosDAO.obtenerCausalReliquidacion();
	        this.listTipoEgreso = ParametrosDAO.obtenerTipoEgreso();
	        this.listModalidadPago = ParametrosDAO.obtenerModalidadDePago();
	        this.listCodigoBanco = ParametrosDAO.obtenerCodigoBanco();
	        this.listEstadoDocumento = ParametrosDAO.obtenerEstadoDocumento();
	        this.listTipoArchivo = ParametrosDAO.obtenerTipoArchivo();
	        this.listProcesoAfcCesantia = ParametrosDAO.obtenerProcesosAfcCesantia();
	        this.listCodigoTramo = ParametrosDAO.obtenerCodigoTramo();
	        this.listCodigoError = ParametrosDAO.obtenerCodigoError();
	        this.listDominio = ParametrosDAO.obtenerDominio();
            this.listCodigoErrorAFC = ParametrosDAO.obtenerCodigoErrorAFC();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

    /* Inicializador */
    public synchronized static ListadoParametros getInstancia() {
        if (LISTA_UNICA == null) {
            LISTA_UNICA = new ListadoParametros();
        }
        return LISTA_UNICA;
    }

    /* Creación de Getting and Setting de clase ListadoParametros */
    public String getServletContextRealPath() {
        return servletContextRealPath;
    }

    public void setServletContextRealPath(String servletContextRealPath) {
        this.servletContextRealPath = servletContextRealPath;
    }

    public StatusProcesoVO[] getListStatusProceso() {
        return listStatusProceso;
    }

    public void setListStatusProcesoCarga(StatusProcesoVO[] listStatusProcesoCarga) {
        this.listStatusProcesoCarga = listStatusProcesoCarga;
    }

    public StatusProcesoVO[] getListStatusProcesoCarga() {
        return listStatusProcesoCarga;
    }

    public void setListStatusProceso(StatusProcesoVO[] listStatusProceso) {
        this.listStatusProceso = listStatusProceso;
    }

    public TipoProcesosVO[] getListTipoProcesos() {
        return listTipoProcesos;
    }

    public void setListTipoProcesos(TipoProcesosVO[] listTipoProcesos) {
        this.listTipoProcesos = listTipoProcesos;
    }

    public TipoReporteVO[] getListTipoReportes() {
        return listTipoReportes;
    }

    public void setListTipoReportes(TipoReporteVO[] listTipoReportes) {
        this.listTipoReportes = listTipoReportes;
    }

    public FormatoReporteVO[] getListFormatoReportes() {
        return listFormatoReportes;
    }

    public void setListFormatoReportes(FormatoReporteVO[] listFormatoReportes) {
        this.listFormatoReportes = listFormatoReportes;
    }

    public PeriodoProcesoVO[] getListPeriodoProcesos() {
        return listPeriodoProcesos;
    }

    public void setListPeriodoProcesos(PeriodoProcesoVO[] listPeriodoProcesos) {
        this.listPeriodoProcesos = listPeriodoProcesos;
    }

    public ValoresConexionAS400VO[] getListValoresConexionToAS400() {
        return listValoresConexionToAS400;
    }

    public void setListValoresConexionToAS400(ValoresConexionAS400VO[] listValoresConexionToAS400) {
        this.listValoresConexionToAS400 = listValoresConexionToAS400;
    }

    public CausalReliquidacionVO[] getListCausalReliquidacion() {
        return listCausalReliquidacion;
    }

    public void setListCausalReliquidacion(CausalReliquidacionVO[] listCausalReliquidacion) {
        this.listCausalReliquidacion = listCausalReliquidacion;
    }

    public CodigoArchivoVO[] getListCodigoArchivo() {
        return listCodigoArchivo;
    }

    public void setListCodigoArchivo(CodigoArchivoVO[] listCodigoArchivo) {
        this.listCodigoArchivo = listCodigoArchivo;
    }

    public TipoBeneficiarioVO[] getListTipoBeneficiario() {
        return listTipoBeneficiario;
    }

    public void setListTipoBeneficiario(TipoBeneficiarioVO[] listTipoBeneficiario) {
        this.listTipoBeneficiario = listTipoBeneficiario;
    }

    public TipoBeneficioVO[] getListTipoBeneficio() {
        return listTipoBeneficio;
    }

    public void setListTipoBeneficio(TipoBeneficioVO[] listTipoBeneficio) {
        this.listTipoBeneficio = listTipoBeneficio;
    }

    public TipoCausanteVO[] getListTipoCausante() {
        return listTipoCausante;
    }

    public void setListTipoCausante(TipoCausanteVO[] listTipoCausante) {
        this.listTipoCausante = listTipoCausante;
    }

    public TipoDeclaracionVO[] getListTipoDeclaracion() {
        return listTipoDeclaracion;
    }

    public void setListTipoDeclaracion(TipoDeclaracionVO[] listTipoDeclaracion) {
        this.listTipoDeclaracion = listTipoDeclaracion;
    }

    public TipoEgresoVO[] getListTipoEgreso() {
        return listTipoEgreso;
    }

    public void setListTipoEgreso(TipoEgresoVO[] listTipoEgreso) {
        this.listTipoEgreso = listTipoEgreso;
    }

    public TipoEmisionVO[] getListTipoEmision() {
        return listTipoEmision;
    }

    public void setListTipoEmision(TipoEmisionVO[] listTipoEmision) {
        this.listTipoEmision = listTipoEmision;
    }

    public TipoReintegroVO[] getListTipoReintegro() {
        return listTipoReintegro;
    }

    public void setListTipoReintegro(TipoReintegroVO[] listTipoReintegro) {
        this.listTipoReintegro = listTipoReintegro;
    }

    public TipoSaldoVO[] getListTipoSaldo() {
        return listTipoSaldo;
    }

    public void setListTipoSaldo(TipoSaldoVO[] listTipoSaldo) {
        this.listTipoSaldo = listTipoSaldo;
    }

    public CodigoBancoVO[] getListCodigoBanco() {
        return listCodigoBanco;
    }

    public void setListCodigoBanco(CodigoBancoVO[] listCodigoBanco) {
        this.listCodigoBanco = listCodigoBanco;
    }

    public ModalidadDePagoVO[] getListModalidadPago() {
        return listModalidadPago;
    }

    public void setListModalidadPago(ModalidadDePagoVO[] listModalidadPago) {
        this.listModalidadPago = listModalidadPago;
    }

    public EstadoDelDocumentoVO[] getListEstadoDocumento() {
        return listEstadoDocumento;
    }

    public void setListEstadoDocumento(EstadoDelDocumentoVO[] listEstadoDocumento) {
        this.listEstadoDocumento = listEstadoDocumento;
    }

    public TipoOrigenVO[] getListTipoOrigen() {
        return listTipoOrigen;
    }

    public void setListTipoOrigen(TipoOrigenVO[] listTipoOrigen) {
        this.listTipoOrigen = listTipoOrigen;
    }

    public TipoArchivoVO[] getListTipoArchivo() {
        return listTipoArchivo;
    }

    public void setListTipoArchivo(TipoArchivoVO[] listTipoArchivo) {
        this.listTipoArchivo = listTipoArchivo;
    }

    public CodigoTramoVO[] getListCodigoTramo() {
        return listCodigoTramo;
    }

    public void setListCodigoTramo(CodigoTramoVO[] listCodigoTramo) {
        this.listCodigoTramo = listCodigoTramo;
    }

    public ProcesosAFCCesantiaVO[] getListProcesoAfcCesantia() {
        return listProcesoAfcCesantia;
    }

    public void setListProcesoAfcCesantia(ProcesosAFCCesantiaVO[] listProcesoAfcCesantia) {
        this.listProcesoAfcCesantia = listProcesoAfcCesantia;
    }

    public CodigoErrorVO[] getListCodigoError() {
        return listCodigoError;
    }

    public void setListCodigoError(CodigoErrorVO[] listCodigoError) {
        this.listCodigoError = listCodigoError;
    }

    public DominioVO[] getListDominio() {
        return listDominio;
    }

    public void setListDominio(DominioVO[] listDominio) {
        this.listDominio = listDominio;
    }

}
