/*
 * XML Type:  CT_ZONA_A3
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAA3
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme;


/**
 * An XML CT_ZONA_A3(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public interface CTZONAA3 extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTZONAA3.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA742B30DAA2FB6CF156658275424CF67").resolveHandle("ctzonaa30e5dtype");
    
    /**
     * Gets the "codigo_tipo_licencia" element
     */
    java.math.BigInteger getCodigoTipoLicencia();
    
    /**
     * Gets (as xml) the "codigo_tipo_licencia" element
     */
    wwwLmeGovClLme.STTipoLicencia xgetCodigoTipoLicencia();
    
    /**
     * Sets the "codigo_tipo_licencia" element
     */
    void setCodigoTipoLicencia(java.math.BigInteger codigoTipoLicencia);
    
    /**
     * Sets (as xml) the "codigo_tipo_licencia" element
     */
    void xsetCodigoTipoLicencia(wwwLmeGovClLme.STTipoLicencia codigoTipoLicencia);
    
    /**
     * Gets the "codigo_recuperabilidad" element
     */
    java.math.BigInteger getCodigoRecuperabilidad();
    
    /**
     * Gets (as xml) the "codigo_recuperabilidad" element
     */
    wwwLmeGovClLme.STSiNo xgetCodigoRecuperabilidad();
    
    /**
     * Sets the "codigo_recuperabilidad" element
     */
    void setCodigoRecuperabilidad(java.math.BigInteger codigoRecuperabilidad);
    
    /**
     * Sets (as xml) the "codigo_recuperabilidad" element
     */
    void xsetCodigoRecuperabilidad(wwwLmeGovClLme.STSiNo codigoRecuperabilidad);
    
    /**
     * Gets the "codigo_inicio_tram_inv" element
     */
    java.math.BigInteger getCodigoInicioTramInv();
    
    /**
     * Gets (as xml) the "codigo_inicio_tram_inv" element
     */
    wwwLmeGovClLme.STSiNo xgetCodigoInicioTramInv();
    
    /**
     * Sets the "codigo_inicio_tram_inv" element
     */
    void setCodigoInicioTramInv(java.math.BigInteger codigoInicioTramInv);
    
    /**
     * Sets (as xml) the "codigo_inicio_tram_inv" element
     */
    void xsetCodigoInicioTramInv(wwwLmeGovClLme.STSiNo codigoInicioTramInv);
    
    /**
     * Gets the "fecha_accidente" element
     */
    java.util.Calendar getFechaAccidente();
    
    /**
     * Gets (as xml) the "fecha_accidente" element
     */
    org.apache.xmlbeans.XmlDateTime xgetFechaAccidente();
    
    /**
     * True if has "fecha_accidente" element
     */
    boolean isSetFechaAccidente();
    
    /**
     * Sets the "fecha_accidente" element
     */
    void setFechaAccidente(java.util.Calendar fechaAccidente);
    
    /**
     * Sets (as xml) the "fecha_accidente" element
     */
    void xsetFechaAccidente(org.apache.xmlbeans.XmlDateTime fechaAccidente);
    
    /**
     * Unsets the "fecha_accidente" element
     */
    void unsetFechaAccidente();
    
    /**
     * Gets the "codigo_trayecto" element
     */
    java.math.BigInteger getCodigoTrayecto();
    
    /**
     * Gets (as xml) the "codigo_trayecto" element
     */
    wwwLmeGovClLme.STSiNo xgetCodigoTrayecto();
    
    /**
     * True if has "codigo_trayecto" element
     */
    boolean isSetCodigoTrayecto();
    
    /**
     * Sets the "codigo_trayecto" element
     */
    void setCodigoTrayecto(java.math.BigInteger codigoTrayecto);
    
    /**
     * Sets (as xml) the "codigo_trayecto" element
     */
    void xsetCodigoTrayecto(wwwLmeGovClLme.STSiNo codigoTrayecto);
    
    /**
     * Unsets the "codigo_trayecto" element
     */
    void unsetCodigoTrayecto();
    
    /**
     * Gets the "fecha_concepcion" element
     */
    java.util.Calendar getFechaConcepcion();
    
    /**
     * Gets (as xml) the "fecha_concepcion" element
     */
    org.apache.xmlbeans.XmlDate xgetFechaConcepcion();
    
    /**
     * True if has "fecha_concepcion" element
     */
    boolean isSetFechaConcepcion();
    
    /**
     * Sets the "fecha_concepcion" element
     */
    void setFechaConcepcion(java.util.Calendar fechaConcepcion);
    
    /**
     * Sets (as xml) the "fecha_concepcion" element
     */
    void xsetFechaConcepcion(org.apache.xmlbeans.XmlDate fechaConcepcion);
    
    /**
     * Unsets the "fecha_concepcion" element
     */
    void unsetFechaConcepcion();
    
    /**
     * Gets the "id" attribute
     */
    org.apache.xmlbeans.XmlAnySimpleType getId();
    
    /**
     * True if has "id" attribute
     */
    boolean isSetId();
    
    /**
     * Sets the "id" attribute
     */
    void setId(org.apache.xmlbeans.XmlAnySimpleType id);
    
    /**
     * Appends and returns a new empty "id" attribute
     */
    org.apache.xmlbeans.XmlAnySimpleType addNewId();
    
    /**
     * Unsets the "id" attribute
     */
    void unsetId();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static wwwLmeGovClLme.CTZONAA3 newInstance() {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA3 newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static wwwLmeGovClLme.CTZONAA3 parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA3 parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static wwwLmeGovClLme.CTZONAA3 parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA3 parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA3 parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA3 parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA3 parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA3 parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA3 parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA3 parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA3 parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA3 parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA3 parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA3 parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTZONAA3 parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTZONAA3 parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTZONAA3) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
