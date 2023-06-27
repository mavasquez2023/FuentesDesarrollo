/*
 * XML Type:  CTDetalleHaber
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTDetalleHaber
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme;


/**
 * An XML CTDetalleHaber(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public interface CTDetalleHaber extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTDetalleHaber.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA742B30DAA2FB6CF156658275424CF67").resolveHandle("ctdetallehaber00a8type");
    
    /**
     * Gets the "ano_mes_renta" element
     */
    java.util.Calendar getAnoMesRenta();
    
    /**
     * Gets (as xml) the "ano_mes_renta" element
     */
    org.apache.xmlbeans.XmlGYearMonth xgetAnoMesRenta();
    
    /**
     * Sets the "ano_mes_renta" element
     */
    void setAnoMesRenta(java.util.Calendar anoMesRenta);
    
    /**
     * Sets (as xml) the "ano_mes_renta" element
     */
    void xsetAnoMesRenta(org.apache.xmlbeans.XmlGYearMonth anoMesRenta);
    
    /**
     * Gets the "nombre_haber" element
     */
    java.lang.String getNombreHaber();
    
    /**
     * Gets (as xml) the "nombre_haber" element
     */
    org.apache.xmlbeans.XmlString xgetNombreHaber();
    
    /**
     * Sets the "nombre_haber" element
     */
    void setNombreHaber(java.lang.String nombreHaber);
    
    /**
     * Sets (as xml) the "nombre_haber" element
     */
    void xsetNombreHaber(org.apache.xmlbeans.XmlString nombreHaber);
    
    /**
     * Gets the "monto_haber" element
     */
    java.math.BigInteger getMontoHaber();
    
    /**
     * Gets (as xml) the "monto_haber" element
     */
    org.apache.xmlbeans.XmlInteger xgetMontoHaber();
    
    /**
     * Sets the "monto_haber" element
     */
    void setMontoHaber(java.math.BigInteger montoHaber);
    
    /**
     * Sets (as xml) the "monto_haber" element
     */
    void xsetMontoHaber(org.apache.xmlbeans.XmlInteger montoHaber);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static wwwLmeGovClLme.CTDetalleHaber newInstance() {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static wwwLmeGovClLme.CTDetalleHaber newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static wwwLmeGovClLme.CTDetalleHaber parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static wwwLmeGovClLme.CTDetalleHaber parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static wwwLmeGovClLme.CTDetalleHaber parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static wwwLmeGovClLme.CTDetalleHaber parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static wwwLmeGovClLme.CTDetalleHaber parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static wwwLmeGovClLme.CTDetalleHaber parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static wwwLmeGovClLme.CTDetalleHaber parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static wwwLmeGovClLme.CTDetalleHaber parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static wwwLmeGovClLme.CTDetalleHaber parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static wwwLmeGovClLme.CTDetalleHaber parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static wwwLmeGovClLme.CTDetalleHaber parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static wwwLmeGovClLme.CTDetalleHaber parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static wwwLmeGovClLme.CTDetalleHaber parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static wwwLmeGovClLme.CTDetalleHaber parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTDetalleHaber parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTDetalleHaber parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTDetalleHaber) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
