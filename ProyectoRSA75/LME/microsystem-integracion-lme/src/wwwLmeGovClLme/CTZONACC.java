/*
 * XML Type:  CT_ZONA_CC
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONACC
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme;


/**
 * An XML CT_ZONA_CC(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public interface CTZONACC extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTZONACC.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA742B30DAA2FB6CF156658275424CF67").resolveHandle("ctzonacc964ftype");
    
    /**
     * Gets the "codigo_tramitacion_CCAF" element
     */
    java.math.BigInteger getCodigoTramitacionCCAF();
    
    /**
     * Gets (as xml) the "codigo_tramitacion_CCAF" element
     */
    wwwLmeGovClLme.STCodigoCCAF xgetCodigoTramitacionCCAF();
    
    /**
     * Sets the "codigo_tramitacion_CCAF" element
     */
    void setCodigoTramitacionCCAF(java.math.BigInteger codigoTramitacionCCAF);
    
    /**
     * Sets (as xml) the "codigo_tramitacion_CCAF" element
     */
    void xsetCodigoTramitacionCCAF(wwwLmeGovClLme.STCodigoCCAF codigoTramitacionCCAF);
    
    /**
     * Gets the "tiene_mas100" element
     */
    java.math.BigInteger getTieneMas100();
    
    /**
     * Gets (as xml) the "tiene_mas100" element
     */
    wwwLmeGovClLme.STSiNo xgetTieneMas100();
    
    /**
     * True if has "tiene_mas100" element
     */
    boolean isSetTieneMas100();
    
    /**
     * Sets the "tiene_mas100" element
     */
    void setTieneMas100(java.math.BigInteger tieneMas100);
    
    /**
     * Sets (as xml) the "tiene_mas100" element
     */
    void xsetTieneMas100(wwwLmeGovClLme.STSiNo tieneMas100);
    
    /**
     * Unsets the "tiene_mas100" element
     */
    void unsetTieneMas100();
    
    /**
     * Gets the "haberes" element
     */
    wwwLmeGovClLme.CTHaberes getHaberes();
    
    /**
     * Sets the "haberes" element
     */
    void setHaberes(wwwLmeGovClLme.CTHaberes haberes);
    
    /**
     * Appends and returns a new empty "haberes" element
     */
    wwwLmeGovClLme.CTHaberes addNewHaberes();
    
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
        public static wwwLmeGovClLme.CTZONACC newInstance() {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static wwwLmeGovClLme.CTZONACC newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static wwwLmeGovClLme.CTZONACC parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static wwwLmeGovClLme.CTZONACC parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static wwwLmeGovClLme.CTZONACC parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static wwwLmeGovClLme.CTZONACC parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static wwwLmeGovClLme.CTZONACC parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static wwwLmeGovClLme.CTZONACC parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static wwwLmeGovClLme.CTZONACC parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static wwwLmeGovClLme.CTZONACC parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static wwwLmeGovClLme.CTZONACC parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static wwwLmeGovClLme.CTZONACC parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static wwwLmeGovClLme.CTZONACC parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static wwwLmeGovClLme.CTZONACC parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static wwwLmeGovClLme.CTZONACC parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static wwwLmeGovClLme.CTZONACC parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTZONACC parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTZONACC parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTZONACC) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
