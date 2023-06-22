/*
 * XML Type:  Exception
 * Namespace: http://axisversion.sample
 * Java type: cl.araucana.wssiagf.wsservices.version.xsd.Exception
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.version.xsd;


/**
 * An XML Exception(@http://axisversion.sample).
 *
 * This is a complex type.
 */
public interface Exception extends org.apache.xmlbeans.XmlObject {
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType) org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Exception.class.getClassLoader(),
            "schemaorg_apache_xmlbeans.system.s38E990E05BE3AC9D37B8584DAE263E67")
                                                                                                                           .resolveHandle("exception7f19type");

    /**
     * Gets the "Exception" element
     */
    org.apache.xmlbeans.XmlObject getException();

    /**
     * Tests for nil "Exception" element
     */
    boolean isNilException();

    /**
     * True if has "Exception" element
     */
    boolean isSetException();

    /**
     * Sets the "Exception" element
     */
    void setException(org.apache.xmlbeans.XmlObject exception);

    /**
     * Appends and returns a new empty "Exception" element
     */
    org.apache.xmlbeans.XmlObject addNewException();

    /**
     * Nils the "Exception" element
     */
    void setNilException();

    /**
     * Unsets the "Exception" element
     */
    void unsetException();

    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    public static final class Factory {
        private Factory() {
        } // No instance of this class allowed

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception newInstance() {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .newInstance(type,
                null);
        }

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception newInstance(
            org.apache.xmlbeans.XmlOptions options) {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .newInstance(type,
                options);
        }

        /** @param xmlAsString the string value to parse */
        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            java.lang.String xmlAsString)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(xmlAsString,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(xmlAsString,
                type, options);
        }

        /** @param file the file from which to load an xml document */
        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            java.io.File file)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(file,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            java.io.File file, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(file,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            java.net.URL u)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(u,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            java.net.URL u, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(u,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            java.io.InputStream is)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(is,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            java.io.InputStream is, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(is,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            java.io.Reader r)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(r,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            java.io.Reader r, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(r,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            javax.xml.stream.XMLStreamReader sr)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(sr,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            javax.xml.stream.XMLStreamReader sr,
            org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(sr,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(node,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(node,
                type, options);
        }

        /** @deprecated {@link XMLInputStream} */
        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(xis,
                type, null);
        }

        /** @deprecated {@link XMLInputStream} */
        public static cl.araucana.wssiagf.wsservices.version.xsd.Exception parse(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis,
            org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return (cl.araucana.wssiagf.wsservices.version.xsd.Exception) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                      .parse(xis,
                type, options);
        }

        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                               .newValidatingXMLInputStream(xis,
                type, null);
        }

        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis,
            org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                               .newValidatingXMLInputStream(xis,
                type, options);
        }
    }
}
