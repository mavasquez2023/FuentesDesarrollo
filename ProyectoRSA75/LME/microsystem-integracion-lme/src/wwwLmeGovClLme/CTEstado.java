/*
 * XML Type:  CTEstado
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTEstado
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme;


/**
 * An XML CTEstado(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public interface CTEstado extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTEstado.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA742B30DAA2FB6CF156658275424CF67").resolveHandle("ctestado5da1type");
    
    /**
     * Gets the "estado_licencia" element
     */
    java.math.BigInteger getEstadoLicencia();
    
    /**
     * Gets (as xml) the "estado_licencia" element
     */
    wwwLmeGovClLme.STEstadoLicencia xgetEstadoLicencia();
    
    /**
     * Sets the "estado_licencia" element
     */
    void setEstadoLicencia(java.math.BigInteger estadoLicencia);
    
    /**
     * Sets (as xml) the "estado_licencia" element
     */
    void xsetEstadoLicencia(wwwLmeGovClLme.STEstadoLicencia estadoLicencia);
    
    /**
     * Gets the "fecha_estado" element
     */
    java.util.Calendar getFechaEstado();
    
    /**
     * Gets (as xml) the "fecha_estado" element
     */
    org.apache.xmlbeans.XmlDateTime xgetFechaEstado();
    
    /**
     * Sets the "fecha_estado" element
     */
    void setFechaEstado(java.util.Calendar fechaEstado);
    
    /**
     * Sets (as xml) the "fecha_estado" element
     */
    void xsetFechaEstado(org.apache.xmlbeans.XmlDateTime fechaEstado);
    
    /**
     * Gets the "motivo_norecepcion" attribute
     */
    java.math.BigInteger getMotivoNorecepcion();
    
    /**
     * Gets (as xml) the "motivo_norecepcion" attribute
     */
    wwwLmeGovClLme.STMotivoNorecepcion xgetMotivoNorecepcion();
    
    /**
     * True if has "motivo_norecepcion" attribute
     */
    boolean isSetMotivoNorecepcion();
    
    /**
     * Sets the "motivo_norecepcion" attribute
     */
    void setMotivoNorecepcion(java.math.BigInteger motivoNorecepcion);
    
    /**
     * Sets (as xml) the "motivo_norecepcion" attribute
     */
    void xsetMotivoNorecepcion(wwwLmeGovClLme.STMotivoNorecepcion motivoNorecepcion);
    
    /**
     * Unsets the "motivo_norecepcion" attribute
     */
    void unsetMotivoNorecepcion();
    
    /**
     * Gets the "fecha_termino_relacion" attribute
     */
    java.util.Calendar getFechaTerminoRelacion();
    
    /**
     * Gets (as xml) the "fecha_termino_relacion" attribute
     */
    org.apache.xmlbeans.XmlDate xgetFechaTerminoRelacion();
    
    /**
     * True if has "fecha_termino_relacion" attribute
     */
    boolean isSetFechaTerminoRelacion();
    
    /**
     * Sets the "fecha_termino_relacion" attribute
     */
    void setFechaTerminoRelacion(java.util.Calendar fechaTerminoRelacion);
    
    /**
     * Sets (as xml) the "fecha_termino_relacion" attribute
     */
    void xsetFechaTerminoRelacion(org.apache.xmlbeans.XmlDate fechaTerminoRelacion);
    
    /**
     * Unsets the "fecha_termino_relacion" attribute
     */
    void unsetFechaTerminoRelacion();
    
    /**
     * Gets the "emp_rut" attribute
     */
    java.lang.String getEmpRut();
    
    /**
     * Gets (as xml) the "emp_rut" attribute
     */
    wwwLmeGovClLme.STRut xgetEmpRut();
    
    /**
     * True if has "emp_rut" attribute
     */
    boolean isSetEmpRut();
    
    /**
     * Sets the "emp_rut" attribute
     */
    void setEmpRut(java.lang.String empRut);
    
    /**
     * Sets (as xml) the "emp_rut" attribute
     */
    void xsetEmpRut(wwwLmeGovClLme.STRut empRut);
    
    /**
     * Unsets the "emp_rut" attribute
     */
    void unsetEmpRut();
    
    /**
     * Gets the "codigo_tramitacion_CCAF" attribute
     */
    java.math.BigInteger getCodigoTramitacionCCAF();
    
    /**
     * Gets (as xml) the "codigo_tramitacion_CCAF" attribute
     */
    wwwLmeGovClLme.STCodigoCCAF xgetCodigoTramitacionCCAF();
    
    /**
     * True if has "codigo_tramitacion_CCAF" attribute
     */
    boolean isSetCodigoTramitacionCCAF();
    
    /**
     * Sets the "codigo_tramitacion_CCAF" attribute
     */
    void setCodigoTramitacionCCAF(java.math.BigInteger codigoTramitacionCCAF);
    
    /**
     * Sets (as xml) the "codigo_tramitacion_CCAF" attribute
     */
    void xsetCodigoTramitacionCCAF(wwwLmeGovClLme.STCodigoCCAF codigoTramitacionCCAF);
    
    /**
     * Unsets the "codigo_tramitacion_CCAF" attribute
     */
    void unsetCodigoTramitacionCCAF();
    
    /**
     * Gets the "motivo_devolucion_CCAF" attribute
     */
    java.math.BigInteger getMotivoDevolucionCCAF();
    
    /**
     * Gets (as xml) the "motivo_devolucion_CCAF" attribute
     */
    wwwLmeGovClLme.STMotivoDevolucionCCAF xgetMotivoDevolucionCCAF();
    
    /**
     * True if has "motivo_devolucion_CCAF" attribute
     */
    boolean isSetMotivoDevolucionCCAF();
    
    /**
     * Sets the "motivo_devolucion_CCAF" attribute
     */
    void setMotivoDevolucionCCAF(java.math.BigInteger motivoDevolucionCCAF);
    
    /**
     * Sets (as xml) the "motivo_devolucion_CCAF" attribute
     */
    void xsetMotivoDevolucionCCAF(wwwLmeGovClLme.STMotivoDevolucionCCAF motivoDevolucionCCAF);
    
    /**
     * Unsets the "motivo_devolucion_CCAF" attribute
     */
    void unsetMotivoDevolucionCCAF();
    
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
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static wwwLmeGovClLme.CTEstado newInstance() {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static wwwLmeGovClLme.CTEstado newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static wwwLmeGovClLme.CTEstado parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static wwwLmeGovClLme.CTEstado parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static wwwLmeGovClLme.CTEstado parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static wwwLmeGovClLme.CTEstado parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static wwwLmeGovClLme.CTEstado parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static wwwLmeGovClLme.CTEstado parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static wwwLmeGovClLme.CTEstado parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static wwwLmeGovClLme.CTEstado parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static wwwLmeGovClLme.CTEstado parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static wwwLmeGovClLme.CTEstado parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static wwwLmeGovClLme.CTEstado parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static wwwLmeGovClLme.CTEstado parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static wwwLmeGovClLme.CTEstado parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static wwwLmeGovClLme.CTEstado parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTEstado parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTEstado parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTEstado) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
