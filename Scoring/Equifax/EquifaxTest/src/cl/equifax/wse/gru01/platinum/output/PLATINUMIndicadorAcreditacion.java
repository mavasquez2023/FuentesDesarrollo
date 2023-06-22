/**
 * PLATINUMIndicadorAcreditacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.equifax.wse.gru01.platinum.output;

public class PLATINUMIndicadorAcreditacion  implements java.io.Serializable {
    private java.lang.String anioUltimaExportacion;

    private java.lang.String anioUltimaImportacion;

    private java.lang.String cantidadBancos;

    private java.lang.String cantidadOtrosVehiculos;

    private java.lang.String cantidadPrendasSinDesplazamiento;

    private java.lang.String cantidadPropiedades;

    private java.lang.String cantidadRelacionesComoSociedad;

    private java.lang.String cantidadRelacionesComoSocio;

    private java.lang.String cantidadVehiculosLivianos;

    private java.lang.String cantidadVehiculosPesados;

    private java.lang.String deudaDirectaVigente;

    private java.lang.String estimacionRenta;

    private java.lang.String fechaUltimaVerificacionDomicilio;

    private java.lang.String folioDeclaracionImpuestos;

    private java.lang.String glosaEstimadorRenta;

    private java.lang.String lineaCreditoNoUtilizada;

    private java.lang.String montoTotalAvaluoFiscal;

    private java.lang.String montoUltimaExportacion;

    private java.lang.String montoUltimaImportacion;

    private cl.equifax.wse.gru01.platinum.output.sub.PLATINUMNombreBanco[] nombresBancos;

    private java.lang.String periodoDeclaracionImpuestos;

    private java.lang.String periodoVigente;

    public PLATINUMIndicadorAcreditacion() {
    }

    public PLATINUMIndicadorAcreditacion(
           java.lang.String anioUltimaExportacion,
           java.lang.String anioUltimaImportacion,
           java.lang.String cantidadBancos,
           java.lang.String cantidadOtrosVehiculos,
           java.lang.String cantidadPrendasSinDesplazamiento,
           java.lang.String cantidadPropiedades,
           java.lang.String cantidadRelacionesComoSociedad,
           java.lang.String cantidadRelacionesComoSocio,
           java.lang.String cantidadVehiculosLivianos,
           java.lang.String cantidadVehiculosPesados,
           java.lang.String deudaDirectaVigente,
           java.lang.String estimacionRenta,
           java.lang.String fechaUltimaVerificacionDomicilio,
           java.lang.String folioDeclaracionImpuestos,
           java.lang.String glosaEstimadorRenta,
           java.lang.String lineaCreditoNoUtilizada,
           java.lang.String montoTotalAvaluoFiscal,
           java.lang.String montoUltimaExportacion,
           java.lang.String montoUltimaImportacion,
           cl.equifax.wse.gru01.platinum.output.sub.PLATINUMNombreBanco[] nombresBancos,
           java.lang.String periodoDeclaracionImpuestos,
           java.lang.String periodoVigente) {
           this.anioUltimaExportacion = anioUltimaExportacion;
           this.anioUltimaImportacion = anioUltimaImportacion;
           this.cantidadBancos = cantidadBancos;
           this.cantidadOtrosVehiculos = cantidadOtrosVehiculos;
           this.cantidadPrendasSinDesplazamiento = cantidadPrendasSinDesplazamiento;
           this.cantidadPropiedades = cantidadPropiedades;
           this.cantidadRelacionesComoSociedad = cantidadRelacionesComoSociedad;
           this.cantidadRelacionesComoSocio = cantidadRelacionesComoSocio;
           this.cantidadVehiculosLivianos = cantidadVehiculosLivianos;
           this.cantidadVehiculosPesados = cantidadVehiculosPesados;
           this.deudaDirectaVigente = deudaDirectaVigente;
           this.estimacionRenta = estimacionRenta;
           this.fechaUltimaVerificacionDomicilio = fechaUltimaVerificacionDomicilio;
           this.folioDeclaracionImpuestos = folioDeclaracionImpuestos;
           this.glosaEstimadorRenta = glosaEstimadorRenta;
           this.lineaCreditoNoUtilizada = lineaCreditoNoUtilizada;
           this.montoTotalAvaluoFiscal = montoTotalAvaluoFiscal;
           this.montoUltimaExportacion = montoUltimaExportacion;
           this.montoUltimaImportacion = montoUltimaImportacion;
           this.nombresBancos = nombresBancos;
           this.periodoDeclaracionImpuestos = periodoDeclaracionImpuestos;
           this.periodoVigente = periodoVigente;
    }


    /**
     * Gets the anioUltimaExportacion value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return anioUltimaExportacion
     */
    public java.lang.String getAnioUltimaExportacion() {
        return anioUltimaExportacion;
    }


    /**
     * Sets the anioUltimaExportacion value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param anioUltimaExportacion
     */
    public void setAnioUltimaExportacion(java.lang.String anioUltimaExportacion) {
        this.anioUltimaExportacion = anioUltimaExportacion;
    }


    /**
     * Gets the anioUltimaImportacion value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return anioUltimaImportacion
     */
    public java.lang.String getAnioUltimaImportacion() {
        return anioUltimaImportacion;
    }


    /**
     * Sets the anioUltimaImportacion value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param anioUltimaImportacion
     */
    public void setAnioUltimaImportacion(java.lang.String anioUltimaImportacion) {
        this.anioUltimaImportacion = anioUltimaImportacion;
    }


    /**
     * Gets the cantidadBancos value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return cantidadBancos
     */
    public java.lang.String getCantidadBancos() {
        return cantidadBancos;
    }


    /**
     * Sets the cantidadBancos value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param cantidadBancos
     */
    public void setCantidadBancos(java.lang.String cantidadBancos) {
        this.cantidadBancos = cantidadBancos;
    }


    /**
     * Gets the cantidadOtrosVehiculos value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return cantidadOtrosVehiculos
     */
    public java.lang.String getCantidadOtrosVehiculos() {
        return cantidadOtrosVehiculos;
    }


    /**
     * Sets the cantidadOtrosVehiculos value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param cantidadOtrosVehiculos
     */
    public void setCantidadOtrosVehiculos(java.lang.String cantidadOtrosVehiculos) {
        this.cantidadOtrosVehiculos = cantidadOtrosVehiculos;
    }


    /**
     * Gets the cantidadPrendasSinDesplazamiento value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return cantidadPrendasSinDesplazamiento
     */
    public java.lang.String getCantidadPrendasSinDesplazamiento() {
        return cantidadPrendasSinDesplazamiento;
    }


    /**
     * Sets the cantidadPrendasSinDesplazamiento value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param cantidadPrendasSinDesplazamiento
     */
    public void setCantidadPrendasSinDesplazamiento(java.lang.String cantidadPrendasSinDesplazamiento) {
        this.cantidadPrendasSinDesplazamiento = cantidadPrendasSinDesplazamiento;
    }


    /**
     * Gets the cantidadPropiedades value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return cantidadPropiedades
     */
    public java.lang.String getCantidadPropiedades() {
        return cantidadPropiedades;
    }


    /**
     * Sets the cantidadPropiedades value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param cantidadPropiedades
     */
    public void setCantidadPropiedades(java.lang.String cantidadPropiedades) {
        this.cantidadPropiedades = cantidadPropiedades;
    }


    /**
     * Gets the cantidadRelacionesComoSociedad value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return cantidadRelacionesComoSociedad
     */
    public java.lang.String getCantidadRelacionesComoSociedad() {
        return cantidadRelacionesComoSociedad;
    }


    /**
     * Sets the cantidadRelacionesComoSociedad value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param cantidadRelacionesComoSociedad
     */
    public void setCantidadRelacionesComoSociedad(java.lang.String cantidadRelacionesComoSociedad) {
        this.cantidadRelacionesComoSociedad = cantidadRelacionesComoSociedad;
    }


    /**
     * Gets the cantidadRelacionesComoSocio value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return cantidadRelacionesComoSocio
     */
    public java.lang.String getCantidadRelacionesComoSocio() {
        return cantidadRelacionesComoSocio;
    }


    /**
     * Sets the cantidadRelacionesComoSocio value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param cantidadRelacionesComoSocio
     */
    public void setCantidadRelacionesComoSocio(java.lang.String cantidadRelacionesComoSocio) {
        this.cantidadRelacionesComoSocio = cantidadRelacionesComoSocio;
    }


    /**
     * Gets the cantidadVehiculosLivianos value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return cantidadVehiculosLivianos
     */
    public java.lang.String getCantidadVehiculosLivianos() {
        return cantidadVehiculosLivianos;
    }


    /**
     * Sets the cantidadVehiculosLivianos value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param cantidadVehiculosLivianos
     */
    public void setCantidadVehiculosLivianos(java.lang.String cantidadVehiculosLivianos) {
        this.cantidadVehiculosLivianos = cantidadVehiculosLivianos;
    }


    /**
     * Gets the cantidadVehiculosPesados value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return cantidadVehiculosPesados
     */
    public java.lang.String getCantidadVehiculosPesados() {
        return cantidadVehiculosPesados;
    }


    /**
     * Sets the cantidadVehiculosPesados value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param cantidadVehiculosPesados
     */
    public void setCantidadVehiculosPesados(java.lang.String cantidadVehiculosPesados) {
        this.cantidadVehiculosPesados = cantidadVehiculosPesados;
    }


    /**
     * Gets the deudaDirectaVigente value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return deudaDirectaVigente
     */
    public java.lang.String getDeudaDirectaVigente() {
        return deudaDirectaVigente;
    }


    /**
     * Sets the deudaDirectaVigente value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param deudaDirectaVigente
     */
    public void setDeudaDirectaVigente(java.lang.String deudaDirectaVigente) {
        this.deudaDirectaVigente = deudaDirectaVigente;
    }


    /**
     * Gets the estimacionRenta value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return estimacionRenta
     */
    public java.lang.String getEstimacionRenta() {
        return estimacionRenta;
    }


    /**
     * Sets the estimacionRenta value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param estimacionRenta
     */
    public void setEstimacionRenta(java.lang.String estimacionRenta) {
        this.estimacionRenta = estimacionRenta;
    }


    /**
     * Gets the fechaUltimaVerificacionDomicilio value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return fechaUltimaVerificacionDomicilio
     */
    public java.lang.String getFechaUltimaVerificacionDomicilio() {
        return fechaUltimaVerificacionDomicilio;
    }


    /**
     * Sets the fechaUltimaVerificacionDomicilio value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param fechaUltimaVerificacionDomicilio
     */
    public void setFechaUltimaVerificacionDomicilio(java.lang.String fechaUltimaVerificacionDomicilio) {
        this.fechaUltimaVerificacionDomicilio = fechaUltimaVerificacionDomicilio;
    }


    /**
     * Gets the folioDeclaracionImpuestos value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return folioDeclaracionImpuestos
     */
    public java.lang.String getFolioDeclaracionImpuestos() {
        return folioDeclaracionImpuestos;
    }


    /**
     * Sets the folioDeclaracionImpuestos value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param folioDeclaracionImpuestos
     */
    public void setFolioDeclaracionImpuestos(java.lang.String folioDeclaracionImpuestos) {
        this.folioDeclaracionImpuestos = folioDeclaracionImpuestos;
    }


    /**
     * Gets the glosaEstimadorRenta value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return glosaEstimadorRenta
     */
    public java.lang.String getGlosaEstimadorRenta() {
        return glosaEstimadorRenta;
    }


    /**
     * Sets the glosaEstimadorRenta value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param glosaEstimadorRenta
     */
    public void setGlosaEstimadorRenta(java.lang.String glosaEstimadorRenta) {
        this.glosaEstimadorRenta = glosaEstimadorRenta;
    }


    /**
     * Gets the lineaCreditoNoUtilizada value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return lineaCreditoNoUtilizada
     */
    public java.lang.String getLineaCreditoNoUtilizada() {
        return lineaCreditoNoUtilizada;
    }


    /**
     * Sets the lineaCreditoNoUtilizada value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param lineaCreditoNoUtilizada
     */
    public void setLineaCreditoNoUtilizada(java.lang.String lineaCreditoNoUtilizada) {
        this.lineaCreditoNoUtilizada = lineaCreditoNoUtilizada;
    }


    /**
     * Gets the montoTotalAvaluoFiscal value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return montoTotalAvaluoFiscal
     */
    public java.lang.String getMontoTotalAvaluoFiscal() {
        return montoTotalAvaluoFiscal;
    }


    /**
     * Sets the montoTotalAvaluoFiscal value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param montoTotalAvaluoFiscal
     */
    public void setMontoTotalAvaluoFiscal(java.lang.String montoTotalAvaluoFiscal) {
        this.montoTotalAvaluoFiscal = montoTotalAvaluoFiscal;
    }


    /**
     * Gets the montoUltimaExportacion value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return montoUltimaExportacion
     */
    public java.lang.String getMontoUltimaExportacion() {
        return montoUltimaExportacion;
    }


    /**
     * Sets the montoUltimaExportacion value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param montoUltimaExportacion
     */
    public void setMontoUltimaExportacion(java.lang.String montoUltimaExportacion) {
        this.montoUltimaExportacion = montoUltimaExportacion;
    }


    /**
     * Gets the montoUltimaImportacion value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return montoUltimaImportacion
     */
    public java.lang.String getMontoUltimaImportacion() {
        return montoUltimaImportacion;
    }


    /**
     * Sets the montoUltimaImportacion value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param montoUltimaImportacion
     */
    public void setMontoUltimaImportacion(java.lang.String montoUltimaImportacion) {
        this.montoUltimaImportacion = montoUltimaImportacion;
    }


    /**
     * Gets the nombresBancos value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return nombresBancos
     */
    public cl.equifax.wse.gru01.platinum.output.sub.PLATINUMNombreBanco[] getNombresBancos() {
        return nombresBancos;
    }


    /**
     * Sets the nombresBancos value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param nombresBancos
     */
    public void setNombresBancos(cl.equifax.wse.gru01.platinum.output.sub.PLATINUMNombreBanco[] nombresBancos) {
        this.nombresBancos = nombresBancos;
    }


    /**
     * Gets the periodoDeclaracionImpuestos value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return periodoDeclaracionImpuestos
     */
    public java.lang.String getPeriodoDeclaracionImpuestos() {
        return periodoDeclaracionImpuestos;
    }


    /**
     * Sets the periodoDeclaracionImpuestos value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param periodoDeclaracionImpuestos
     */
    public void setPeriodoDeclaracionImpuestos(java.lang.String periodoDeclaracionImpuestos) {
        this.periodoDeclaracionImpuestos = periodoDeclaracionImpuestos;
    }


    /**
     * Gets the periodoVigente value for this PLATINUMIndicadorAcreditacion.
     * 
     * @return periodoVigente
     */
    public java.lang.String getPeriodoVigente() {
        return periodoVigente;
    }


    /**
     * Sets the periodoVigente value for this PLATINUMIndicadorAcreditacion.
     * 
     * @param periodoVigente
     */
    public void setPeriodoVigente(java.lang.String periodoVigente) {
        this.periodoVigente = periodoVigente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLATINUMIndicadorAcreditacion)) return false;
        PLATINUMIndicadorAcreditacion other = (PLATINUMIndicadorAcreditacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.anioUltimaExportacion==null && other.getAnioUltimaExportacion()==null) || 
             (this.anioUltimaExportacion!=null &&
              this.anioUltimaExportacion.equals(other.getAnioUltimaExportacion()))) &&
            ((this.anioUltimaImportacion==null && other.getAnioUltimaImportacion()==null) || 
             (this.anioUltimaImportacion!=null &&
              this.anioUltimaImportacion.equals(other.getAnioUltimaImportacion()))) &&
            ((this.cantidadBancos==null && other.getCantidadBancos()==null) || 
             (this.cantidadBancos!=null &&
              this.cantidadBancos.equals(other.getCantidadBancos()))) &&
            ((this.cantidadOtrosVehiculos==null && other.getCantidadOtrosVehiculos()==null) || 
             (this.cantidadOtrosVehiculos!=null &&
              this.cantidadOtrosVehiculos.equals(other.getCantidadOtrosVehiculos()))) &&
            ((this.cantidadPrendasSinDesplazamiento==null && other.getCantidadPrendasSinDesplazamiento()==null) || 
             (this.cantidadPrendasSinDesplazamiento!=null &&
              this.cantidadPrendasSinDesplazamiento.equals(other.getCantidadPrendasSinDesplazamiento()))) &&
            ((this.cantidadPropiedades==null && other.getCantidadPropiedades()==null) || 
             (this.cantidadPropiedades!=null &&
              this.cantidadPropiedades.equals(other.getCantidadPropiedades()))) &&
            ((this.cantidadRelacionesComoSociedad==null && other.getCantidadRelacionesComoSociedad()==null) || 
             (this.cantidadRelacionesComoSociedad!=null &&
              this.cantidadRelacionesComoSociedad.equals(other.getCantidadRelacionesComoSociedad()))) &&
            ((this.cantidadRelacionesComoSocio==null && other.getCantidadRelacionesComoSocio()==null) || 
             (this.cantidadRelacionesComoSocio!=null &&
              this.cantidadRelacionesComoSocio.equals(other.getCantidadRelacionesComoSocio()))) &&
            ((this.cantidadVehiculosLivianos==null && other.getCantidadVehiculosLivianos()==null) || 
             (this.cantidadVehiculosLivianos!=null &&
              this.cantidadVehiculosLivianos.equals(other.getCantidadVehiculosLivianos()))) &&
            ((this.cantidadVehiculosPesados==null && other.getCantidadVehiculosPesados()==null) || 
             (this.cantidadVehiculosPesados!=null &&
              this.cantidadVehiculosPesados.equals(other.getCantidadVehiculosPesados()))) &&
            ((this.deudaDirectaVigente==null && other.getDeudaDirectaVigente()==null) || 
             (this.deudaDirectaVigente!=null &&
              this.deudaDirectaVigente.equals(other.getDeudaDirectaVigente()))) &&
            ((this.estimacionRenta==null && other.getEstimacionRenta()==null) || 
             (this.estimacionRenta!=null &&
              this.estimacionRenta.equals(other.getEstimacionRenta()))) &&
            ((this.fechaUltimaVerificacionDomicilio==null && other.getFechaUltimaVerificacionDomicilio()==null) || 
             (this.fechaUltimaVerificacionDomicilio!=null &&
              this.fechaUltimaVerificacionDomicilio.equals(other.getFechaUltimaVerificacionDomicilio()))) &&
            ((this.folioDeclaracionImpuestos==null && other.getFolioDeclaracionImpuestos()==null) || 
             (this.folioDeclaracionImpuestos!=null &&
              this.folioDeclaracionImpuestos.equals(other.getFolioDeclaracionImpuestos()))) &&
            ((this.glosaEstimadorRenta==null && other.getGlosaEstimadorRenta()==null) || 
             (this.glosaEstimadorRenta!=null &&
              this.glosaEstimadorRenta.equals(other.getGlosaEstimadorRenta()))) &&
            ((this.lineaCreditoNoUtilizada==null && other.getLineaCreditoNoUtilizada()==null) || 
             (this.lineaCreditoNoUtilizada!=null &&
              this.lineaCreditoNoUtilizada.equals(other.getLineaCreditoNoUtilizada()))) &&
            ((this.montoTotalAvaluoFiscal==null && other.getMontoTotalAvaluoFiscal()==null) || 
             (this.montoTotalAvaluoFiscal!=null &&
              this.montoTotalAvaluoFiscal.equals(other.getMontoTotalAvaluoFiscal()))) &&
            ((this.montoUltimaExportacion==null && other.getMontoUltimaExportacion()==null) || 
             (this.montoUltimaExportacion!=null &&
              this.montoUltimaExportacion.equals(other.getMontoUltimaExportacion()))) &&
            ((this.montoUltimaImportacion==null && other.getMontoUltimaImportacion()==null) || 
             (this.montoUltimaImportacion!=null &&
              this.montoUltimaImportacion.equals(other.getMontoUltimaImportacion()))) &&
            ((this.nombresBancos==null && other.getNombresBancos()==null) || 
             (this.nombresBancos!=null &&
              java.util.Arrays.equals(this.nombresBancos, other.getNombresBancos()))) &&
            ((this.periodoDeclaracionImpuestos==null && other.getPeriodoDeclaracionImpuestos()==null) || 
             (this.periodoDeclaracionImpuestos!=null &&
              this.periodoDeclaracionImpuestos.equals(other.getPeriodoDeclaracionImpuestos()))) &&
            ((this.periodoVigente==null && other.getPeriodoVigente()==null) || 
             (this.periodoVigente!=null &&
              this.periodoVigente.equals(other.getPeriodoVigente())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAnioUltimaExportacion() != null) {
            _hashCode += getAnioUltimaExportacion().hashCode();
        }
        if (getAnioUltimaImportacion() != null) {
            _hashCode += getAnioUltimaImportacion().hashCode();
        }
        if (getCantidadBancos() != null) {
            _hashCode += getCantidadBancos().hashCode();
        }
        if (getCantidadOtrosVehiculos() != null) {
            _hashCode += getCantidadOtrosVehiculos().hashCode();
        }
        if (getCantidadPrendasSinDesplazamiento() != null) {
            _hashCode += getCantidadPrendasSinDesplazamiento().hashCode();
        }
        if (getCantidadPropiedades() != null) {
            _hashCode += getCantidadPropiedades().hashCode();
        }
        if (getCantidadRelacionesComoSociedad() != null) {
            _hashCode += getCantidadRelacionesComoSociedad().hashCode();
        }
        if (getCantidadRelacionesComoSocio() != null) {
            _hashCode += getCantidadRelacionesComoSocio().hashCode();
        }
        if (getCantidadVehiculosLivianos() != null) {
            _hashCode += getCantidadVehiculosLivianos().hashCode();
        }
        if (getCantidadVehiculosPesados() != null) {
            _hashCode += getCantidadVehiculosPesados().hashCode();
        }
        if (getDeudaDirectaVigente() != null) {
            _hashCode += getDeudaDirectaVigente().hashCode();
        }
        if (getEstimacionRenta() != null) {
            _hashCode += getEstimacionRenta().hashCode();
        }
        if (getFechaUltimaVerificacionDomicilio() != null) {
            _hashCode += getFechaUltimaVerificacionDomicilio().hashCode();
        }
        if (getFolioDeclaracionImpuestos() != null) {
            _hashCode += getFolioDeclaracionImpuestos().hashCode();
        }
        if (getGlosaEstimadorRenta() != null) {
            _hashCode += getGlosaEstimadorRenta().hashCode();
        }
        if (getLineaCreditoNoUtilizada() != null) {
            _hashCode += getLineaCreditoNoUtilizada().hashCode();
        }
        if (getMontoTotalAvaluoFiscal() != null) {
            _hashCode += getMontoTotalAvaluoFiscal().hashCode();
        }
        if (getMontoUltimaExportacion() != null) {
            _hashCode += getMontoUltimaExportacion().hashCode();
        }
        if (getMontoUltimaImportacion() != null) {
            _hashCode += getMontoUltimaImportacion().hashCode();
        }
        if (getNombresBancos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNombresBancos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNombresBancos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPeriodoDeclaracionImpuestos() != null) {
            _hashCode += getPeriodoDeclaracionImpuestos().hashCode();
        }
        if (getPeriodoVigente() != null) {
            _hashCode += getPeriodoVigente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PLATINUMIndicadorAcreditacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMIndicadorAcreditacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anioUltimaExportacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anioUltimaExportacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anioUltimaImportacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anioUltimaImportacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadBancos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadBancos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadOtrosVehiculos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadOtrosVehiculos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadPrendasSinDesplazamiento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadPrendasSinDesplazamiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadPropiedades");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadPropiedades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadRelacionesComoSociedad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadRelacionesComoSociedad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadRelacionesComoSocio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadRelacionesComoSocio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadVehiculosLivianos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadVehiculosLivianos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadVehiculosPesados");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadVehiculosPesados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deudaDirectaVigente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deudaDirectaVigente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estimacionRenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estimacionRenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaUltimaVerificacionDomicilio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaUltimaVerificacionDomicilio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folioDeclaracionImpuestos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "folioDeclaracionImpuestos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("glosaEstimadorRenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "glosaEstimadorRenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineaCreditoNoUtilizada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lineaCreditoNoUtilizada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoTotalAvaluoFiscal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "montoTotalAvaluoFiscal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoUltimaExportacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "montoUltimaExportacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoUltimaImportacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "montoUltimaImportacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombresBancos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombresBancos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMNombreBanco"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodoDeclaracionImpuestos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "periodoDeclaracionImpuestos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodoVigente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "periodoVigente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
