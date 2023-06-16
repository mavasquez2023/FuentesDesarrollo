package cl.araucana.sivegam.vo;

public class LinInformeFinancieroVO {

    /**
     * Variables de la clase LinInformeFinancieroVO, contiene los datos del
     * formulario y por ende los carga en esta clase que los administra como una
     * lista.
     */

    // CUADRO INFORMATIVO
    private String periodo;
    private String codigoEntidad;
    private String nombreEntidad;
    private String fecDepositoExcedente;

    // INGRESOS
    private long provision;
    private long reintegro;
    private long totalIngresos;

    // EGRESOS
    private long asigFamTrabActivos;
    private long asigFamPensionados;
    private long asigFamTrabCesantes;
    private long asigFamInstituciones;
    private long totalPagoDelMes;

    private long asigFamTrabActiv;
    private long asifFamTrabPens;
    private long asigFamTrabCes;
    private long asigFamTrabInst;
    private long totalPagosRetroactivos;

    private long docRevalidados;
    private long comisionAdministracion;
    private long totalEgresos;

    // DEVOLUCIONES
    private long documentosCaducados;
    private long documentosAnulados;
    private long totalDevoluciones;

    // SUPERAVIT O DEFICIT POR PAGO DE ASIGNACION FAMILIAR Y MATERNAL
    private long TotalD;

    // DEVOLUCION DE SALDOS A FAVOR DE EMPLEADORES
    private long saldoFavorEmpleador;
    private long devolucionDocSAFEMCaducados;
    private long devolucionDocSAFEMAnulados;
    private long documentosSAFEMRevalidados;
    private long totalDevolucionesE;

    // SUPERAVIT O DEFICIT FINAL
    private long totalF;

    /** Generacion de getting and setting de los campos de la clase. */
    public long getAsifFamTrabPens() {
        return asifFamTrabPens;
    }

    public void setAsifFamTrabPens(long asifFamTrabPens) {
        this.asifFamTrabPens = asifFamTrabPens;
    }

    public long getAsigFamInstituciones() {
        return asigFamInstituciones;
    }

    public void setAsigFamInstituciones(long asigFamInstituciones) {
        this.asigFamInstituciones = asigFamInstituciones;
    }

    public long getAsigFamPensionados() {
        return asigFamPensionados;
    }

    public void setAsigFamPensionados(long asigFamPensionados) {
        this.asigFamPensionados = asigFamPensionados;
    }

    public long getAsigFamTrabActiv() {
        return asigFamTrabActiv;
    }

    public void setAsigFamTrabActiv(long asigFamTrabActiv) {
        this.asigFamTrabActiv = asigFamTrabActiv;
    }

    public long getAsigFamTrabActivos() {
        return asigFamTrabActivos;
    }

    public void setAsigFamTrabActivos(long asigFamTrabActivos) {
        this.asigFamTrabActivos = asigFamTrabActivos;
    }

    public long getAsigFamTrabCes() {
        return asigFamTrabCes;
    }

    public void setAsigFamTrabCes(long asigFamTrabCes) {
        this.asigFamTrabCes = asigFamTrabCes;
    }

    public long getAsigFamTrabCesantes() {
        return asigFamTrabCesantes;
    }

    public void setAsigFamTrabCesantes(long asigFamTrabCesantes) {
        this.asigFamTrabCesantes = asigFamTrabCesantes;
    }

    public long getAsigFamTrabInst() {
        return asigFamTrabInst;
    }

    public void setAsigFamTrabInst(long asigFamTrabInst) {
        this.asigFamTrabInst = asigFamTrabInst;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public long getComisionAdministracion() {
        return comisionAdministracion;
    }

    public void setComisionAdministracion(long comisionAdministracion) {
        this.comisionAdministracion = comisionAdministracion;
    }

    public long getDevolucionDocSAFEMAnulados() {
        return devolucionDocSAFEMAnulados;
    }

    public void setDevolucionDocSAFEMAnulados(long devolucionDocSAFEMAnulados) {
        this.devolucionDocSAFEMAnulados = devolucionDocSAFEMAnulados;
    }

    public long getDevolucionDocSAFEMCaducados() {
        return devolucionDocSAFEMCaducados;
    }

    public void setDevolucionDocSAFEMCaducados(long devolucionDocSAFEMCaducados) {
        this.devolucionDocSAFEMCaducados = devolucionDocSAFEMCaducados;
    }

    public long getDocRevalidados() {
        return docRevalidados;
    }

    public void setDocRevalidados(long docRevalidados) {
        this.docRevalidados = docRevalidados;
    }

    public long getDocumentosAnulados() {
        return documentosAnulados;
    }

    public void setDocumentosAnulados(long documentosAnulados) {
        this.documentosAnulados = documentosAnulados;
    }

    public long getDocumentosCaducados() {
        return documentosCaducados;
    }

    public void setDocumentosCaducados(long documentosCaducados) {
        this.documentosCaducados = documentosCaducados;
    }

    public long getDocumentosSAFEMRevalidados() {
        return documentosSAFEMRevalidados;
    }

    public void setDocumentosSAFEMRevalidados(long documentosSAFEMRevalidados) {
        this.documentosSAFEMRevalidados = documentosSAFEMRevalidados;
    }

    public String getFecDepositoExcedente() {
        return fecDepositoExcedente;
    }

    public void setFecDepositoExcedente(String fecDepositoExcedente) {
        this.fecDepositoExcedente = fecDepositoExcedente;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public long getProvision() {
        return provision;
    }

    public void setProvision(long provision) {
        this.provision = provision;
    }

    public long getReintegro() {
        return reintegro;
    }

    public void setReintegro(long reintegro) {
        this.reintegro = reintegro;
    }

    public long getSaldoFavorEmpleador() {
        return saldoFavorEmpleador;
    }

    public void setSaldoFavorEmpleador(long saldoFavorEmpleador) {
        this.saldoFavorEmpleador = saldoFavorEmpleador;
    }

    public long getTotalD() {
        return TotalD;
    }

    public void setTotalD(long totalD) {
        TotalD = totalD;
    }

    public long getTotalDevoluciones() {
        return totalDevoluciones;
    }

    public void setTotalDevoluciones(long totalDevoluciones) {
        this.totalDevoluciones = totalDevoluciones;
    }

    public long getTotalDevolucionesE() {
        return totalDevolucionesE;
    }

    public void setTotalDevolucionesE(long totalDevolucionesE) {
        this.totalDevolucionesE = totalDevolucionesE;
    }

    public long getTotalEgresos() {
        return totalEgresos;
    }

    public void setTotalEgresos(long totalEgresos) {
        this.totalEgresos = totalEgresos;
    }

    public long getTotalF() {
        return totalF;
    }

    public void setTotalF(long totalF) {
        this.totalF = totalF;
    }

    public long getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(long totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public long getTotalPagoDelMes() {
        return totalPagoDelMes;
    }

    public void setTotalPagoDelMes(long totalPagoDelMes) {
        this.totalPagoDelMes = totalPagoDelMes;
    }

    public long getTotalPagosRetroactivos() {
        return totalPagosRetroactivos;
    }

    public void setTotalPagosRetroactivos(long totalPagosRetroactivos) {
        this.totalPagosRetroactivos = totalPagosRetroactivos;
    }
}
