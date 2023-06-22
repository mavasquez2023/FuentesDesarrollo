/*
 * An XML document type.
 * Localname: actualizarCausanteResponse
 * Namespace: http://ws.siagf.paperless.cl
 * Java type: cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.actualizarCausante.xsd;


/**
 * A document containing one actualizarCausanteResponse(@http://ws.siagf.paperless.cl) element.
 *
 * This is a complex type.
 */
public interface ActualizarCausanteResponseDocument extends org.apache.xmlbeans.XmlObject {
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType) org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ActualizarCausanteResponseDocument.class.getClassLoader(),
            "schemaorg_apache_xmlbeans.system.s4949A1FE3A8236C617EBEDF1F27488C9")
                                                                                                                           .resolveHandle("actualizarcausanteresponse4052doctype");

    /**
     * Gets the "actualizarCausanteResponse" element
     */
    cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse getActualizarCausanteResponse();

    /**
     * Sets the "actualizarCausanteResponse" element
     */
    void setActualizarCausanteResponse(
        cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse actualizarCausanteResponse);

    /**
     * Appends and returns a new empty "actualizarCausanteResponse" element
     */
    cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse addNewActualizarCausanteResponse();

    /**
     * An XML actualizarCausanteResponse(@http://ws.siagf.paperless.cl).
     *
     * This is a complex type.
     */
    public interface ActualizarCausanteResponse extends org.apache.xmlbeans.XmlObject {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType) org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ActualizarCausanteResponse.class.getClassLoader(),
                "schemaorg_apache_xmlbeans.system.s4949A1FE3A8236C617EBEDF1F27488C9")
                                                                                                                               .resolveHandle("actualizarcausanteresponse5339elemtype");

        /**
         * Gets the "return" element
         */
        java.lang.String getReturn();

        /**
         * Gets (as xml) the "return" element
         */
        org.apache.xmlbeans.XmlString xgetReturn();

        /**
         * Tests for nil "return" element
         */
        boolean isNilReturn();

        /**
         * True if has "return" element
         */
        boolean isSetReturn();

        /**
         * Sets the "return" element
         */
        void setReturn(java.lang.String xreturn);

        /**
         * Sets (as xml) the "return" element
         */
        void xsetReturn(org.apache.xmlbeans.XmlString xreturn);

        /**
         * Nils the "return" element
         */
        void setNilReturn();

        /**
         * Unsets the "return" element
         */
        void unsetReturn();

        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        public static final class Factory {
            private Factory() {
            } // No instance of this class allowed

            public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse newInstance() {
                return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                                         .newInstance(type,
                    null);
            }

            public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse newInstance(
                org.apache.xmlbeans.XmlOptions options) {
                return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                                         .newInstance(type,
                    options);
            }
        }
    }

    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    public static final class Factory {
        private Factory() {
        } // No instance of this class allowed

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument newInstance() {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .newInstance(type,
                null);
        }

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument newInstance(
            org.apache.xmlbeans.XmlOptions options) {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .newInstance(type,
                options);
        }

        /** @param xmlAsString the string value to parse */
        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            java.lang.String xmlAsString)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(xmlAsString,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(xmlAsString,
                type, options);
        }

        /** @param file the file from which to load an xml document */
        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            java.io.File file)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(file,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            java.io.File file, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(file,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            java.net.URL u)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(u,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            java.net.URL u, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(u,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            java.io.InputStream is)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(is,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            java.io.InputStream is, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(is,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            java.io.Reader r)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(r,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            java.io.Reader r, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(r,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            javax.xml.stream.XMLStreamReader sr)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(sr,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            javax.xml.stream.XMLStreamReader sr,
            org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(sr,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(node,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(node,
                type, options);
        }

        /** @deprecated {@link XMLInputStream} */
        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                          .parse(xis,
                type, null);
        }

        /** @deprecated {@link XMLInputStream} */
        public static cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument parse(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis,
            org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
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
