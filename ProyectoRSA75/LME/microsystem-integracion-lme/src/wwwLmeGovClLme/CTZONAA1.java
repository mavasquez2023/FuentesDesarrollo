/*
 * XML Type:  CT_ZONA_A1
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAA1
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme;


/**
 * An XML CT_ZONA_A1(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public interface CTZONAA1 extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTZONAA1.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA742B30DAA2FB6CF156658275424CF67").resolveHandle("ctzonaa1f09ftype");
    
    /**
     * Gets the "trabajador" element
     */
    wwwLmeGovClLme.CTPersona getTrabajador();
    
    /**
     * Sets the "trabajador" element
     */
    void setTrabajador(wwwLmeGovClLme.CTPersona trabajador);
    
    /**
     * Appends and returns a new empty "trabajador" element
     */
    wwwLmeGovClLme.CTPersona addNewTrabajador();
    
    /**
     * Gets the "fecha_emision" element
     */
    java.util.Calendar getFechaEmision();
    
    /**
     * Gets (as xml) the "fecha_emision" element
     */
    org.apache.xmlbeans.XmlDateTime xgetFechaEmision();
    
    /**
     * Sets the "fecha_emision" element
     */
    void setFechaEmision(java.util.Calendar fechaEmision);
    
    /**
     * Sets (as xml) the "fecha_emision" element
     */
    void xsetFechaEmision(org.apache.xmlbeans.XmlDateTime fechaEmision);
    
    /**
     * Gets the "fecha_inicio_reposo" element
     */
    java.util.Calendar getFechaInicioReposo();
    
    /**
     * Gets (as xml) the "fecha_inicio_reposo" element
     */
    org.apache.xmlbeans.XmlDateTime xgetFechaInicioReposo();
    
    /**
     * Sets the "fecha_inicio_reposo" element
     */
    void setFechaInicioReposo(java.util.Calendar fechaInicioReposo);
    
    /**
     * Sets (as xml) the "fecha_inicio_reposo" element
     */
    void xsetFechaInicioReposo(org.apache.xmlbeans.XmlDateTime fechaInicioReposo);
    
    /**
     * Gets the "tra_ndias" element
     */
    java.math.BigInteger getTraNdias();
    
    /**
     * Gets (as xml) the "tra_ndias" element
     */
    org.apache.xmlbeans.XmlInteger xgetTraNdias();
    
    /**
     * Sets the "tra_ndias" element
     */
    void setTraNdias(java.math.BigInteger traNdias);
    
    /**
     * Sets (as xml) the "tra_ndias" element
     */
    void xsetTraNdias(org.apache.xmlbeans.XmlInteger traNdias);
    
    /**
     * Gets the "tra_ndias_palabras" element
     */
    java.lang.String getTraNdiasPalabras();
    
    /**
     * Gets (as xml) the "tra_ndias_palabras" element
     */
    org.apache.xmlbeans.XmlString xgetTraNdiasPalabras();
    
    /**
     * Sets the "tra_ndias_palabras" element
     */
    void setTraNdiasPalabras(java.lang.String traNdiasPalabras);
    
    /**
     * Sets (as xml) the "tra_ndias_palabras" element
     */
    void xsetTraNdiasPalabras(org.apache.xmlbeans.XmlString traNdiasPalabras);
    
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
        public static wwwLmeGovClLme.CTZONAA1 newInstance() {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA1 newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static wwwLmeGovClLme.CTZONAA1 parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA1 parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static wwwLmeGovClLme.CTZONAA1 parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA1 parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA1 parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA1 parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA1 parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA1 parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA1 parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA1 parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA1 parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA1 parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA1 parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA1 parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTZONAA1 parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTZONAA1 parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTZONAA1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
