package cl.araucana.independientes.vo;

import java.util.Date;

public class BeneficioVO {

    private long idBeneficioAfiliado;
    private long idPersonaAfiliado;
    private int	idbeneficio;
    private String glosaCortaBeneficio; //Agregado para usar este VO en Consulta Historica
    private Date fechaSolicitud;
    private String strFechaSolicitud; //Agregado para usar este VO en Consulta Historica
    private int estado;
    private String glosaEstado;
    private String nombreCausante;
    private long rutCausante;
    private String strRutCausante;
    private Date fechaCausante;
    private long copago;
    private long montoPagar;
    private String strMontoPagar; 
    private Date fechaPago;
    private long rutTercero;
    private String nombreTercero;
    private long idAnalista;
    private String strIdAnalista;
    private int tipoComprobante;
    private String glosaTipoComporbante;
    private long folio;
    private long folioReversado;
    private String formaPago;//REQ 6988 JLGN 03-04-2013

    public String getFormaPago() {
        return formaPago;
    }
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    public String getStrIdAnalista() {
        return strIdAnalista;
    }
    public void setStrIdAnalista(String strIdAnalista) {
        this.strIdAnalista = strIdAnalista;
    }
    public String getStrRutCausante() {
        return strRutCausante;
    }
    public void setStrRutCausante(String strRutCausante) {
        this.strRutCausante = strRutCausante;
    }
    public long getCopago() {
        return copago;
    }
    public void setCopago(long copago) {
        this.copago = copago;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    public Date getFechaCausante() {
        return fechaCausante;
    }
    public void setFechaCausante(Date fechaCausante) {
        this.fechaCausante = fechaCausante;
    }
    public Date getFechaPago() {
        return fechaPago;
    }
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    public long getIdAnalista() {
        return idAnalista;
    }
    public void setIdAnalista(long idAnalista) {
        this.idAnalista = idAnalista;
    }
    public int getIdbeneficio() {
        return idbeneficio;
    }
    public void setIdbeneficio(int idbeneficio) {
        this.idbeneficio = idbeneficio;
    }
    public long getIdBeneficioAfiliado() {
        return idBeneficioAfiliado;
    }
    public void setIdBeneficioAfiliado(long idBeneficioAfiliado) {
        this.idBeneficioAfiliado = idBeneficioAfiliado;
    }
    public long getIdPersonaAfiliado() {
        return idPersonaAfiliado;
    }
    public void setIdPersonaAfiliado(long idPersonaAfiliado) {
        this.idPersonaAfiliado = idPersonaAfiliado;
    }
    public long getMontoPagar() {
        return montoPagar;
    }
    public void setMontoPagar(long montoPagar) {
        this.montoPagar = montoPagar;
    }
    public String getNombreCausante() {
        return nombreCausante;
    }
    public void setNombreCausante(String nombreCausante) {
        this.nombreCausante = nombreCausante;
    }
    public long getRutTercero() {
        return rutTercero;
    }
    public void setRutTercero(long rutTercero) {
        this.rutTercero = rutTercero;
    }
    public String getGlosaCortaBeneficio() {
        return glosaCortaBeneficio;
    }
    public void setGlosaCortaBeneficio(String glosaCortaBeneficio) {
        this.glosaCortaBeneficio = glosaCortaBeneficio;
    }
    public String getStrFechaSolicitud() {
        return strFechaSolicitud;
    }
    public void setStrFechaSolicitud(String strFechaSolicitud) {
        this.strFechaSolicitud = strFechaSolicitud;
    }
    public long getRutCausante() {
        return rutCausante;
    }
    public void setRutCausante(long rutCausante) {
        this.rutCausante = rutCausante;
    }
    public String getStrMontoPagar() {
        return strMontoPagar;
    }
    public void setStrMontoPagar(String strMontoPagar) {
        this.strMontoPagar = strMontoPagar;
    }
    public String getGlosaEstado() {
        return glosaEstado;
    }
    public void setGlosaEstado(String glosaEstado) {
        this.glosaEstado = glosaEstado;
    }
    public long getFolio() {
        return folio;
    }
    public void setFolio(long folio) {
        this.folio = folio;
    }
    public long getFolioReversado() {
        return folioReversado;
    }
    public void setFolioReversado(long folioReversado) {
        this.folioReversado = folioReversado;
    }
    public String getGlosaTipoComporbante() {
        return glosaTipoComporbante;
    }
    public void setGlosaTipoComporbante(String glosaTipoComporbante) {
        this.glosaTipoComporbante = glosaTipoComporbante;
    }
    public int getTipoComprobante() {
        return tipoComprobante;
    }
    public void setTipoComprobante(int tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }
    public String getNombreTercero() {
        return nombreTercero;
    }
    public void setNombreTercero(String nombreTercero) {
        this.nombreTercero = nombreTercero;
    }

}
