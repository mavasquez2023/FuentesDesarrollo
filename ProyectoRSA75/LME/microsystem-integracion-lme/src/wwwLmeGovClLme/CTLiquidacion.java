/*
 * XML Type:  CTLiquidacion
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTLiquidacion
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme;


/**
 * An XML CTLiquidacion(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public interface CTLiquidacion extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTLiquidacion.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA742B30DAA2FB6CF156658275424CF67").resolveHandle("ctliquidacion0651type");
    
    /**
     * Gets the "periodo_renta" element
     */
    java.util.Calendar getPeriodoRenta();
    
    /**
     * Gets (as xml) the "periodo_renta" element
     */
    org.apache.xmlbeans.XmlGYearMonth xgetPeriodoRenta();
    
    /**
     * Sets the "periodo_renta" element
     */
    void setPeriodoRenta(java.util.Calendar periodoRenta);
    
    /**
     * Sets (as xml) the "periodo_renta" element
     */
    void xsetPeriodoRenta(org.apache.xmlbeans.XmlGYearMonth periodoRenta);
    
    /**
     * Gets the "monto_subsidio_diario" element
     */
    java.math.BigInteger getMontoSubsidioDiario();
    
    /**
     * Gets (as xml) the "monto_subsidio_diario" element
     */
    org.apache.xmlbeans.XmlInteger xgetMontoSubsidioDiario();
    
    /**
     * True if has "monto_subsidio_diario" element
     */
    boolean isSetMontoSubsidioDiario();
    
    /**
     * Sets the "monto_subsidio_diario" element
     */
    void setMontoSubsidioDiario(java.math.BigInteger montoSubsidioDiario);
    
    /**
     * Sets (as xml) the "monto_subsidio_diario" element
     */
    void xsetMontoSubsidioDiario(org.apache.xmlbeans.XmlInteger montoSubsidioDiario);
    
    /**
     * Unsets the "monto_subsidio_diario" element
     */
    void unsetMontoSubsidioDiario();
    
    /**
     * Gets the "monto_aporte_pensiones" element
     */
    java.math.BigInteger getMontoAportePensiones();
    
    /**
     * Gets (as xml) the "monto_aporte_pensiones" element
     */
    org.apache.xmlbeans.XmlInteger xgetMontoAportePensiones();
    
    /**
     * True if has "monto_aporte_pensiones" element
     */
    boolean isSetMontoAportePensiones();
    
    /**
     * Sets the "monto_aporte_pensiones" element
     */
    void setMontoAportePensiones(java.math.BigInteger montoAportePensiones);
    
    /**
     * Sets (as xml) the "monto_aporte_pensiones" element
     */
    void xsetMontoAportePensiones(org.apache.xmlbeans.XmlInteger montoAportePensiones);
    
    /**
     * Unsets the "monto_aporte_pensiones" element
     */
    void unsetMontoAportePensiones();
    
    /**
     * Gets the "monto_aporte_salud" element
     */
    java.math.BigInteger getMontoAporteSalud();
    
    /**
     * Gets (as xml) the "monto_aporte_salud" element
     */
    org.apache.xmlbeans.XmlInteger xgetMontoAporteSalud();
    
    /**
     * True if has "monto_aporte_salud" element
     */
    boolean isSetMontoAporteSalud();
    
    /**
     * Sets the "monto_aporte_salud" element
     */
    void setMontoAporteSalud(java.math.BigInteger montoAporteSalud);
    
    /**
     * Sets (as xml) the "monto_aporte_salud" element
     */
    void xsetMontoAporteSalud(org.apache.xmlbeans.XmlInteger montoAporteSalud);
    
    /**
     * Unsets the "monto_aporte_salud" element
     */
    void unsetMontoAporteSalud();
    
    /**
     * Gets the "monto_seguro_Cesantia" element
     */
    java.math.BigInteger getMontoSeguroCesantia();
    
    /**
     * Gets (as xml) the "monto_seguro_Cesantia" element
     */
    org.apache.xmlbeans.XmlInteger xgetMontoSeguroCesantia();
    
    /**
     * True if has "monto_seguro_Cesantia" element
     */
    boolean isSetMontoSeguroCesantia();
    
    /**
     * Sets the "monto_seguro_Cesantia" element
     */
    void setMontoSeguroCesantia(java.math.BigInteger montoSeguroCesantia);
    
    /**
     * Sets (as xml) the "monto_seguro_Cesantia" element
     */
    void xsetMontoSeguroCesantia(org.apache.xmlbeans.XmlInteger montoSeguroCesantia);
    
    /**
     * Unsets the "monto_seguro_Cesantia" element
     */
    void unsetMontoSeguroCesantia();
    
    /**
     * Gets the "monto_apagar_subsidio" element
     */
    java.math.BigInteger getMontoApagarSubsidio();
    
    /**
     * Gets (as xml) the "monto_apagar_subsidio" element
     */
    org.apache.xmlbeans.XmlInteger xgetMontoApagarSubsidio();
    
    /**
     * True if has "monto_apagar_subsidio" element
     */
    boolean isSetMontoApagarSubsidio();
    
    /**
     * Sets the "monto_apagar_subsidio" element
     */
    void setMontoApagarSubsidio(java.math.BigInteger montoApagarSubsidio);
    
    /**
     * Sets (as xml) the "monto_apagar_subsidio" element
     */
    void xsetMontoApagarSubsidio(org.apache.xmlbeans.XmlInteger montoApagarSubsidio);
    
    /**
     * Unsets the "monto_apagar_subsidio" element
     */
    void unsetMontoApagarSubsidio();
    
    /**
     * Gets the "fecha_desde_liquidacion" element
     */
    java.util.Calendar getFechaDesdeLiquidacion();
    
    /**
     * Gets (as xml) the "fecha_desde_liquidacion" element
     */
    org.apache.xmlbeans.XmlDate xgetFechaDesdeLiquidacion();
    
    /**
     * True if has "fecha_desde_liquidacion" element
     */
    boolean isSetFechaDesdeLiquidacion();
    
    /**
     * Sets the "fecha_desde_liquidacion" element
     */
    void setFechaDesdeLiquidacion(java.util.Calendar fechaDesdeLiquidacion);
    
    /**
     * Sets (as xml) the "fecha_desde_liquidacion" element
     */
    void xsetFechaDesdeLiquidacion(org.apache.xmlbeans.XmlDate fechaDesdeLiquidacion);
    
    /**
     * Unsets the "fecha_desde_liquidacion" element
     */
    void unsetFechaDesdeLiquidacion();
    
    /**
     * Gets the "fecha_hasta_liquidacion" element
     */
    java.util.Calendar getFechaHastaLiquidacion();
    
    /**
     * Gets (as xml) the "fecha_hasta_liquidacion" element
     */
    org.apache.xmlbeans.XmlDate xgetFechaHastaLiquidacion();
    
    /**
     * True if has "fecha_hasta_liquidacion" element
     */
    boolean isSetFechaHastaLiquidacion();
    
    /**
     * Sets the "fecha_hasta_liquidacion" element
     */
    void setFechaHastaLiquidacion(java.util.Calendar fechaHastaLiquidacion);
    
    /**
     * Sets (as xml) the "fecha_hasta_liquidacion" element
     */
    void xsetFechaHastaLiquidacion(org.apache.xmlbeans.XmlDate fechaHastaLiquidacion);
    
    /**
     * Unsets the "fecha_hasta_liquidacion" element
     */
    void unsetFechaHastaLiquidacion();
    
    /**
     * Gets the "fecha_pago_probable" element
     */
    java.util.Calendar getFechaPagoProbable();
    
    /**
     * Gets (as xml) the "fecha_pago_probable" element
     */
    org.apache.xmlbeans.XmlDate xgetFechaPagoProbable();
    
    /**
     * True if has "fecha_pago_probable" element
     */
    boolean isSetFechaPagoProbable();
    
    /**
     * Sets the "fecha_pago_probable" element
     */
    void setFechaPagoProbable(java.util.Calendar fechaPagoProbable);
    
    /**
     * Sets (as xml) the "fecha_pago_probable" element
     */
    void xsetFechaPagoProbable(org.apache.xmlbeans.XmlDate fechaPagoProbable);
    
    /**
     * Unsets the "fecha_pago_probable" element
     */
    void unsetFechaPagoProbable();
    
    /**
     * Gets the "ndias_apagar_subsidios" element
     */
    java.math.BigInteger getNdiasApagarSubsidios();
    
    /**
     * Gets (as xml) the "ndias_apagar_subsidios" element
     */
    org.apache.xmlbeans.XmlInteger xgetNdiasApagarSubsidios();
    
    /**
     * True if has "ndias_apagar_subsidios" element
     */
    boolean isSetNdiasApagarSubsidios();
    
    /**
     * Sets the "ndias_apagar_subsidios" element
     */
    void setNdiasApagarSubsidios(java.math.BigInteger ndiasApagarSubsidios);
    
    /**
     * Sets (as xml) the "ndias_apagar_subsidios" element
     */
    void xsetNdiasApagarSubsidios(org.apache.xmlbeans.XmlInteger ndiasApagarSubsidios);
    
    /**
     * Unsets the "ndias_apagar_subsidios" element
     */
    void unsetNdiasApagarSubsidios();
    
    /**
     * Gets the "ndias_apagar_prevision" element
     */
    java.math.BigInteger getNdiasApagarPrevision();
    
    /**
     * Gets (as xml) the "ndias_apagar_prevision" element
     */
    org.apache.xmlbeans.XmlInteger xgetNdiasApagarPrevision();
    
    /**
     * True if has "ndias_apagar_prevision" element
     */
    boolean isSetNdiasApagarPrevision();
    
    /**
     * Sets the "ndias_apagar_prevision" element
     */
    void setNdiasApagarPrevision(java.math.BigInteger ndiasApagarPrevision);
    
    /**
     * Sets (as xml) the "ndias_apagar_prevision" element
     */
    void xsetNdiasApagarPrevision(org.apache.xmlbeans.XmlInteger ndiasApagarPrevision);
    
    /**
     * Unsets the "ndias_apagar_prevision" element
     */
    void unsetNdiasApagarPrevision();
    
    /**
     * Gets the "tipo_liquidacion_CCAF" attribute
     */
    java.math.BigInteger getTipoLiquidacionCCAF();
    
    /**
     * Gets (as xml) the "tipo_liquidacion_CCAF" attribute
     */
    wwwLmeGovClLme.STTipoLiquidacionCCAF xgetTipoLiquidacionCCAF();
    
    /**
     * True if has "tipo_liquidacion_CCAF" attribute
     */
    boolean isSetTipoLiquidacionCCAF();
    
    /**
     * Sets the "tipo_liquidacion_CCAF" attribute
     */
    void setTipoLiquidacionCCAF(java.math.BigInteger tipoLiquidacionCCAF);
    
    /**
     * Sets (as xml) the "tipo_liquidacion_CCAF" attribute
     */
    void xsetTipoLiquidacionCCAF(wwwLmeGovClLme.STTipoLiquidacionCCAF tipoLiquidacionCCAF);
    
    /**
     * Unsets the "tipo_liquidacion_CCAF" attribute
     */
    void unsetTipoLiquidacionCCAF();
    
    /**
     * Gets the "evento_liquidacion_CCAF" attribute
     */
    java.math.BigInteger getEventoLiquidacionCCAF();
    
    /**
     * Gets (as xml) the "evento_liquidacion_CCAF" attribute
     */
    wwwLmeGovClLme.STEventoLiquidacionCCAF xgetEventoLiquidacionCCAF();
    
    /**
     * True if has "evento_liquidacion_CCAF" attribute
     */
    boolean isSetEventoLiquidacionCCAF();
    
    /**
     * Sets the "evento_liquidacion_CCAF" attribute
     */
    void setEventoLiquidacionCCAF(java.math.BigInteger eventoLiquidacionCCAF);
    
    /**
     * Sets (as xml) the "evento_liquidacion_CCAF" attribute
     */
    void xsetEventoLiquidacionCCAF(wwwLmeGovClLme.STEventoLiquidacionCCAF eventoLiquidacionCCAF);
    
    /**
     * Unsets the "evento_liquidacion_CCAF" attribute
     */
    void unsetEventoLiquidacionCCAF();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static wwwLmeGovClLme.CTLiquidacion newInstance() {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static wwwLmeGovClLme.CTLiquidacion newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static wwwLmeGovClLme.CTLiquidacion parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static wwwLmeGovClLme.CTLiquidacion parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static wwwLmeGovClLme.CTLiquidacion parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static wwwLmeGovClLme.CTLiquidacion parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static wwwLmeGovClLme.CTLiquidacion parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static wwwLmeGovClLme.CTLiquidacion parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static wwwLmeGovClLme.CTLiquidacion parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static wwwLmeGovClLme.CTLiquidacion parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static wwwLmeGovClLme.CTLiquidacion parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static wwwLmeGovClLme.CTLiquidacion parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static wwwLmeGovClLme.CTLiquidacion parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static wwwLmeGovClLme.CTLiquidacion parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static wwwLmeGovClLme.CTLiquidacion parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static wwwLmeGovClLme.CTLiquidacion parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTLiquidacion parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTLiquidacion parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTLiquidacion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
