/*
 * XML Type:  CT_ZONA_C1
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAC1
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme;


/**
 * An XML CT_ZONA_C1(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public interface CTZONAC1 extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTZONAC1.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA742B30DAA2FB6CF156658275424CF67").resolveHandle("ctzonac18aa1type");
    
    /**
     * Gets the "emp_nombre" element
     */
    java.lang.String getEmpNombre();
    
    /**
     * Gets (as xml) the "emp_nombre" element
     */
    org.apache.xmlbeans.XmlString xgetEmpNombre();
    
    /**
     * Sets the "emp_nombre" element
     */
    void setEmpNombre(java.lang.String empNombre);
    
    /**
     * Sets (as xml) the "emp_nombre" element
     */
    void xsetEmpNombre(org.apache.xmlbeans.XmlString empNombre);
    
    /**
     * Gets the "emp_rut" element
     */
    java.lang.String getEmpRut();
    
    /**
     * Gets (as xml) the "emp_rut" element
     */
    wwwLmeGovClLme.STRut xgetEmpRut();
    
    /**
     * Sets the "emp_rut" element
     */
    void setEmpRut(java.lang.String empRut);
    
    /**
     * Sets (as xml) the "emp_rut" element
     */
    void xsetEmpRut(wwwLmeGovClLme.STRut empRut);
    
    /**
     * Gets the "emp_telefono" element
     */
    wwwLmeGovClLme.CTTelefono getEmpTelefono();
    
    /**
     * True if has "emp_telefono" element
     */
    boolean isSetEmpTelefono();
    
    /**
     * Sets the "emp_telefono" element
     */
    void setEmpTelefono(wwwLmeGovClLme.CTTelefono empTelefono);
    
    /**
     * Appends and returns a new empty "emp_telefono" element
     */
    wwwLmeGovClLme.CTTelefono addNewEmpTelefono();
    
    /**
     * Unsets the "emp_telefono" element
     */
    void unsetEmpTelefono();
    
    /**
     * Gets the "emp_fecha_recepcion" element
     */
    java.util.Calendar getEmpFechaRecepcion();
    
    /**
     * Gets (as xml) the "emp_fecha_recepcion" element
     */
    org.apache.xmlbeans.XmlDateTime xgetEmpFechaRecepcion();
    
    /**
     * Sets the "emp_fecha_recepcion" element
     */
    void setEmpFechaRecepcion(java.util.Calendar empFechaRecepcion);
    
    /**
     * Sets (as xml) the "emp_fecha_recepcion" element
     */
    void xsetEmpFechaRecepcion(org.apache.xmlbeans.XmlDateTime empFechaRecepcion);
    
    /**
     * Gets the "emp_direccion" element
     */
    wwwLmeGovClLme.CTDireccion getEmpDireccion();
    
    /**
     * Sets the "emp_direccion" element
     */
    void setEmpDireccion(wwwLmeGovClLme.CTDireccion empDireccion);
    
    /**
     * Appends and returns a new empty "emp_direccion" element
     */
    wwwLmeGovClLme.CTDireccion addNewEmpDireccion();
    
    /**
     * Gets the "codigo_comuna_compin" element
     */
    java.math.BigInteger getCodigoComunaCompin();
    
    /**
     * Gets (as xml) the "codigo_comuna_compin" element
     */
    org.apache.xmlbeans.XmlInteger xgetCodigoComunaCompin();
    
    /**
     * True if has "codigo_comuna_compin" element
     */
    boolean isSetCodigoComunaCompin();
    
    /**
     * Sets the "codigo_comuna_compin" element
     */
    void setCodigoComunaCompin(java.math.BigInteger codigoComunaCompin);
    
    /**
     * Sets (as xml) the "codigo_comuna_compin" element
     */
    void xsetCodigoComunaCompin(org.apache.xmlbeans.XmlInteger codigoComunaCompin);
    
    /**
     * Unsets the "codigo_comuna_compin" element
     */
    void unsetCodigoComunaCompin();
    
    /**
     * Gets the "codigo_actividad_laboral" element
     */
    java.math.BigInteger getCodigoActividadLaboral();
    
    /**
     * Gets (as xml) the "codigo_actividad_laboral" element
     */
    wwwLmeGovClLme.STActividadLaboral xgetCodigoActividadLaboral();
    
    /**
     * Sets the "codigo_actividad_laboral" element
     */
    void setCodigoActividadLaboral(java.math.BigInteger codigoActividadLaboral);
    
    /**
     * Sets (as xml) the "codigo_actividad_laboral" element
     */
    void xsetCodigoActividadLaboral(wwwLmeGovClLme.STActividadLaboral codigoActividadLaboral);
    
    /**
     * Gets the "codigo_ocupacion" element
     */
    java.math.BigInteger getCodigoOcupacion();
    
    /**
     * Gets (as xml) the "codigo_ocupacion" element
     */
    wwwLmeGovClLme.STOcupacion xgetCodigoOcupacion();
    
    /**
     * Sets the "codigo_ocupacion" element
     */
    void setCodigoOcupacion(java.math.BigInteger codigoOcupacion);
    
    /**
     * Sets (as xml) the "codigo_ocupacion" element
     */
    void xsetCodigoOcupacion(wwwLmeGovClLme.STOcupacion codigoOcupacion);
    
    /**
     * Gets the "emp_otra_ocupacion" element
     */
    java.lang.String getEmpOtraOcupacion();
    
    /**
     * Gets (as xml) the "emp_otra_ocupacion" element
     */
    org.apache.xmlbeans.XmlString xgetEmpOtraOcupacion();
    
    /**
     * True if has "emp_otra_ocupacion" element
     */
    boolean isSetEmpOtraOcupacion();
    
    /**
     * Sets the "emp_otra_ocupacion" element
     */
    void setEmpOtraOcupacion(java.lang.String empOtraOcupacion);
    
    /**
     * Sets (as xml) the "emp_otra_ocupacion" element
     */
    void xsetEmpOtraOcupacion(org.apache.xmlbeans.XmlString empOtraOcupacion);
    
    /**
     * Unsets the "emp_otra_ocupacion" element
     */
    void unsetEmpOtraOcupacion();
    
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
        public static wwwLmeGovClLme.CTZONAC1 newInstance() {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static wwwLmeGovClLme.CTZONAC1 newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static wwwLmeGovClLme.CTZONAC1 parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAC1 parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static wwwLmeGovClLme.CTZONAC1 parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAC1 parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAC1 parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAC1 parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAC1 parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAC1 parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAC1 parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAC1 parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAC1 parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAC1 parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static wwwLmeGovClLme.CTZONAC1 parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static wwwLmeGovClLme.CTZONAC1 parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTZONAC1 parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.CTZONAC1 parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.CTZONAC1) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
