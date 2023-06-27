/*
 * XML Type:  CT_ZONA_A4
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAA4
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme;


/**
 * An XML CT_ZONA_A4(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public interface CTZONAA4 extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTZONAA4.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA742B30DAA2FB6CF156658275424CF67").resolveHandle("ctzonaa49d3ctype");
    
    /**
     * Gets the "codigo_tipo_reposo" element
     */
    java.math.BigInteger getCodigoTipoReposo();
    
    /**
     * Gets (as xml) the "codigo_tipo_reposo" element
     */
    wwwLmeGovClLme.STTipoReposo xgetCodigoTipoReposo();
    
    /**
     * Sets the "codigo_tipo_reposo" element
     */
    void setCodigoTipoReposo(java.math.BigInteger codigoTipoReposo);
    
    /**
     * Sets (as xml) the "codigo_tipo_reposo" element
     */
    void xsetCodigoTipoReposo(wwwLmeGovClLme.STTipoReposo codigoTipoReposo);
    
    /**
     * Gets array of all "codigo_jornada_reposo" elements
     */
    wwwLmeGovClLme.STJornadaReposo.Enum[] getCodigoJornadaReposoArray();
    
    /**
     * Gets ith "codigo_jornada_reposo" element
     */
    wwwLmeGovClLme.STJornadaReposo.Enum getCodigoJornadaReposoArray(int i);
    
    /**
     * Gets (as xml) array of all "codigo_jornada_reposo" elements
     */
    wwwLmeGovClLme.STJornadaReposo[] xgetCodigoJornadaReposoArray();
    
    /**
     * Gets (as xml) ith "codigo_jornada_reposo" element
     */
    wwwLmeGovClLme.STJornadaReposo xgetCodigoJornadaReposoArray(int i);
    
    /**
     * Returns number of "codigo_jornada_reposo" element
     */
    int sizeOfCodigoJornadaReposoArray();
    
    /**
     * Sets array of all "codigo_jornada_reposo" element
     */
    void setCodigoJornadaReposoArray(wwwLmeGovClLme.STJornadaReposo.Enum[] codigoJornadaReposoArray);
    
    /**
     * Sets ith "codigo_jornada_reposo" element
     */
    void setCodigoJornadaReposoArray(int i, wwwLmeGovClLme.STJornadaReposo.Enum codigoJornadaReposo);
    
    /**
     * Sets (as xml) array of all "codigo_jornada_reposo" element
     */
    void xsetCodigoJornadaReposoArray(wwwLmeGovClLme.STJornadaReposo[] codigoJornadaReposoArray);
    
    /**
     * Sets (as xml) ith "codigo_jornada_reposo" element
     */
    void xsetCodigoJornadaReposoArray(int i, wwwLmeGovClLme.STJornadaReposo codigoJornadaReposo);
    
    /**
     * Inserts the value as the ith "codigo_jornada_reposo" element
     */
    void insertCodigoJornadaReposo(int i, wwwLmeGovClLme.STJornadaReposo.Enum codigoJornadaReposo);
    
    /**
     * Appends the value as the last "codigo_jornada_reposo" element
     */
    void addCodigoJornadaReposo(wwwLmeGovClLme.STJornadaReposo.Enum codigoJornadaReposo);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "codigo_jornada_reposo" element
     */
    wwwLmeGovClLme.STJornadaReposo insertNewCodigoJornadaReposo(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "codigo_jornada_reposo" element
     */
    wwwLmeGovClLme.STJornadaReposo addNewCodigoJornadaReposo();
    
    /**
     * Removes the ith "codigo_jornada_reposo" element
     */
    void removeCodigoJornadaReposo(int i);
    
    /**
     * Gets array of all "lugar_reposo" elements
     */
    wwwLmeGovClLme.CTLugarReposo[] getLugarReposoArray();
    
    /**
     * Gets ith "lugar_reposo" element
     */
    wwwLmeGovClLme.CTLugarReposo getLugarReposoArray(int i);
    
    /**
     * Returns number of "lugar_reposo" element
     */
    int sizeOfLugarReposoArray();
    
    /**
     * Sets array of all "lugar_reposo" element
     */
    void setLugarReposoArray(wwwLmeGovClLme.CTLugarReposo[] lugarReposoArray);
    
    /**
     * Sets ith "lugar_reposo" element
     */
    void setLugarReposoArray(int i, wwwLmeGovClLme.CTLugarReposo lugarReposo);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "lugar_reposo" element
     */
    wwwLmeGovClLme.CTLugarReposo insertNewLugarReposo(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "lugar_reposo" element
     */
    wwwLmeGovClLme.CTLugarReposo addNewLugarReposo();
    
    /**
     * Removes the ith "lugar_reposo" element
     */
    void removeLugarReposo(int i);
    
    /**
     * Gets array of all "telefono_reposo" elements
     */
    wwwLmeGovClLme.CTTelefono[] getTelefonoReposoArray();
    
    /**
     * Gets ith "telefono_reposo" element
     */
    wwwLmeGovClLme.CTTelefono getTelefonoReposoArray(int i);
    
    /**
     * Returns number of "telefono_reposo" element
     */
    int sizeOfTelefonoReposoArray();
    
    /**
     * Sets array of all "telefono_reposo" element
     */
    void setTelefonoReposoArray(wwwLmeGovClLme.CTTelefono[] telefonoReposoArray);
    
    /**
     * Sets ith "telefono_reposo" element
     */
    void setTelefonoReposoArray(int i, wwwLmeGovClLme.CTTelefono telefonoReposo);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "telefono_reposo" element
     */
    wwwLmeGovClLme.CTTelefono insertNewTelefonoReposo(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "telefono_reposo" element
     */
    wwwLmeGovClLme.CTTelefono addNewTelefonoReposo();
    
    /**
     * Removes the ith "telefono_reposo" element
     */
    void removeTelefonoReposo(int i);
    
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
        public static wwwLmeGovClLme.CTZONAA4 newInstance() {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA4 newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static wwwLmeGovClLme.CTZONAA4 parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA4 parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static wwwLmeGovClLme.CTZONAA4 parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA4 parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA4 parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA4 parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA4 parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA4 parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA4 parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA4 parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA4 parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA4 parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAA4 parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAA4 parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTZONAA4 parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTZONAA4 parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTZONAA4) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
