/*
 * An XML document type.
 * Localname: LME
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.LMEDocument
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme;


/**
 * A document containing one LME(@urn:www:lme:gov:cl:lme) element.
 *
 * This is a complex type.
 */
public interface LMEDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(LMEDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA742B30DAA2FB6CF156658275424CF67").resolveHandle("lme0416doctype");
    
    /**
     * Gets the "LME" element
     */
    wwwLmeGovClLme.LMEDocument.LME getLME();
    
    /**
     * Sets the "LME" element
     */
    void setLME(wwwLmeGovClLme.LMEDocument.LME lme);
    
    /**
     * Appends and returns a new empty "LME" element
     */
    wwwLmeGovClLme.LMEDocument.LME addNewLME();
    
    /**
     * An XML LME(@urn:www:lme:gov:cl:lme).
     *
     * This is a complex type.
     */
    public interface LME extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(LME.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA742B30DAA2FB6CF156658275424CF67").resolveHandle("lmecec6elemtype");
        
        /**
         * Gets the "ZONA_0" element
         */
        wwwLmeGovClLme.CTZONA0 getZONA0();
        
        /**
         * Sets the "ZONA_0" element
         */
        void setZONA0(wwwLmeGovClLme.CTZONA0 zona0);
        
        /**
         * Appends and returns a new empty "ZONA_0" element
         */
        wwwLmeGovClLme.CTZONA0 addNewZONA0();
        
        /**
         * Gets the "ZONA_A" element
         */
        wwwLmeGovClLme.CTZONAA getZONAA();
        
        /**
         * True if has "ZONA_A" element
         */
        boolean isSetZONAA();
        
        /**
         * Sets the "ZONA_A" element
         */
        void setZONAA(wwwLmeGovClLme.CTZONAA zonaa);
        
        /**
         * Appends and returns a new empty "ZONA_A" element
         */
        wwwLmeGovClLme.CTZONAA addNewZONAA();
        
        /**
         * Unsets the "ZONA_A" element
         */
        void unsetZONAA();
        
        /**
         * Gets the "ZONA_B" element
         */
        wwwLmeGovClLme.CTZONAB getZONAB();
        
        /**
         * True if has "ZONA_B" element
         */
        boolean isSetZONAB();
        
        /**
         * Sets the "ZONA_B" element
         */
        void setZONAB(wwwLmeGovClLme.CTZONAB zonab);
        
        /**
         * Appends and returns a new empty "ZONA_B" element
         */
        wwwLmeGovClLme.CTZONAB addNewZONAB();
        
        /**
         * Unsets the "ZONA_B" element
         */
        void unsetZONAB();
        
        /**
         * Gets the "ZONA_C" element
         */
        wwwLmeGovClLme.CTZONAC getZONAC();
        
        /**
         * True if has "ZONA_C" element
         */
        boolean isSetZONAC();
        
        /**
         * Sets the "ZONA_C" element
         */
        void setZONAC(wwwLmeGovClLme.CTZONAC zonac);
        
        /**
         * Appends and returns a new empty "ZONA_C" element
         */
        wwwLmeGovClLme.CTZONAC addNewZONAC();
        
        /**
         * Unsets the "ZONA_C" element
         */
        void unsetZONAC();
        
        /**
         * Gets array of all "ZONA_D" elements
         */
        wwwLmeGovClLme.CTZONAD[] getZONADArray();
        
        /**
         * Gets ith "ZONA_D" element
         */
        wwwLmeGovClLme.CTZONAD getZONADArray(int i);
        
        /**
         * Returns number of "ZONA_D" element
         */
        int sizeOfZONADArray();
        
        /**
         * Sets array of all "ZONA_D" element
         */
        void setZONADArray(wwwLmeGovClLme.CTZONAD[] zonadArray);
        
        /**
         * Sets ith "ZONA_D" element
         */
        void setZONADArray(int i, wwwLmeGovClLme.CTZONAD zonad);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "ZONA_D" element
         */
        wwwLmeGovClLme.CTZONAD insertNewZONAD(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "ZONA_D" element
         */
        wwwLmeGovClLme.CTZONAD addNewZONAD();
        
        /**
         * Removes the ith "ZONA_D" element
         */
        void removeZONAD(int i);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static wwwLmeGovClLme.LMEDocument.LME newInstance() {
              return (wwwLmeGovClLme.LMEDocument.LME) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static wwwLmeGovClLme.LMEDocument.LME newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (wwwLmeGovClLme.LMEDocument.LME) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static wwwLmeGovClLme.LMEDocument newInstance() {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static wwwLmeGovClLme.LMEDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static wwwLmeGovClLme.LMEDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static wwwLmeGovClLme.LMEDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static wwwLmeGovClLme.LMEDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static wwwLmeGovClLme.LMEDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static wwwLmeGovClLme.LMEDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static wwwLmeGovClLme.LMEDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static wwwLmeGovClLme.LMEDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static wwwLmeGovClLme.LMEDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static wwwLmeGovClLme.LMEDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static wwwLmeGovClLme.LMEDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static wwwLmeGovClLme.LMEDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static wwwLmeGovClLme.LMEDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static wwwLmeGovClLme.LMEDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static wwwLmeGovClLme.LMEDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.LMEDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static wwwLmeGovClLme.LMEDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (wwwLmeGovClLme.LMEDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
