/*
 * XML Type:  CT_ZONA_A6
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAA6
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme;


/**
 * An XML CT_ZONA_A6(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public interface CTZONAA6 extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTZONAA6.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA742B30DAA2FB6CF156658275424CF67").resolveHandle("ctzonaa6bafatype");
    
    /**
     * Gets the "diagnostico_principal" element
     */
    java.lang.String getDiagnosticoPrincipal();
    
    /**
     * Gets (as xml) the "diagnostico_principal" element
     */
    org.apache.xmlbeans.XmlString xgetDiagnosticoPrincipal();
    
    /**
     * Sets the "diagnostico_principal" element
     */
    void setDiagnosticoPrincipal(java.lang.String diagnosticoPrincipal);
    
    /**
     * Sets (as xml) the "diagnostico_principal" element
     */
    void xsetDiagnosticoPrincipal(org.apache.xmlbeans.XmlString diagnosticoPrincipal);
    
    /**
     * Gets the "diagnostico_otro" element
     */
    java.lang.String getDiagnosticoOtro();
    
    /**
     * Gets (as xml) the "diagnostico_otro" element
     */
    org.apache.xmlbeans.XmlString xgetDiagnosticoOtro();
    
    /**
     * True if has "diagnostico_otro" element
     */
    boolean isSetDiagnosticoOtro();
    
    /**
     * Sets the "diagnostico_otro" element
     */
    void setDiagnosticoOtro(java.lang.String diagnosticoOtro);
    
    /**
     * Sets (as xml) the "diagnostico_otro" element
     */
    void xsetDiagnosticoOtro(org.apache.xmlbeans.XmlString diagnosticoOtro);
    
    /**
     * Unsets the "diagnostico_otro" element
     */
    void unsetDiagnosticoOtro();
    
    /**
     * Gets the "antecedentes_clinicos" element
     */
    java.lang.String getAntecedentesClinicos();
    
    /**
     * Gets (as xml) the "antecedentes_clinicos" element
     */
    org.apache.xmlbeans.XmlString xgetAntecedentesClinicos();
    
    /**
     * True if has "antecedentes_clinicos" element
     */
    boolean isSetAntecedentesClinicos();
    
    /**
     * Sets the "antecedentes_clinicos" element
     */
    void setAntecedentesClinicos(java.lang.String antecedentesClinicos);
    
    /**
     * Sets (as xml) the "antecedentes_clinicos" element
     */
    void xsetAntecedentesClinicos(org.apache.xmlbeans.XmlString antecedentesClinicos);
    
    /**
     * Unsets the "antecedentes_clinicos" element
     */
    void unsetAntecedentesClinicos();
    
    /**
     * Gets the "examenes_apoyo" element
     */
    java.lang.String getExamenesApoyo();
    
    /**
     * Gets (as xml) the "examenes_apoyo" element
     */
    org.apache.xmlbeans.XmlString xgetExamenesApoyo();
    
    /**
     * True if has "examenes_apoyo" element
     */
    boolean isSetExamenesApoyo();
    
    /**
     * Sets the "examenes_apoyo" element
     */
    void setExamenesApoyo(java.lang.String examenesApoyo);
    
    /**
     * Sets (as xml) the "examenes_apoyo" element
     */
    void xsetExamenesApoyo(org.apache.xmlbeans.XmlString examenesApoyo);
    
    /**
     * Unsets the "examenes_apoyo" element
     */
    void unsetExamenesApoyo();
    
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
        public static wwwLmeGovClLme.CTZONAA6 newInstance() {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA6 newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static wwwLmeGovClLme.CTZONAA6 parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA6 parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static wwwLmeGovClLme.CTZONAA6 parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA6 parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA6 parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA6 parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA6 parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA6 parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA6 parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA6 parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA6 parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA6 parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA6 parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA6 parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTZONAA6 parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTZONAA6 parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTZONAA6) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
