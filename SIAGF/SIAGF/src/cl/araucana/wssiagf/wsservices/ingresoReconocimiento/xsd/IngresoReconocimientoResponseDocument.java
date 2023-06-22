/*
 * An XML document type.
 * Localname: ingresoReconocimientoResponse
 * Namespace: http://ws.siagf.paperless.cl
 * Java type: cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd;


/**
 * A document containing one ingresoReconocimientoResponse(@http://ws.siagf.paperless.cl) element.
 *
 * This is a complex type.
 */
public interface IngresoReconocimientoResponseDocument extends org.apache.xmlbeans.XmlObject {
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType) org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(IngresoReconocimientoResponseDocument.class.getClassLoader(),
            "schemaorg_apache_xmlbeans.system.s0FF3637DCEF037601CBA996AEBB26C07")
                                                                                                                           .resolveHandle("ingresoreconocimientoresponse3f97doctype");

    /**
     * Gets the "ingresoReconocimientoResponse" element
     */
    cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument.IngresoReconocimientoResponse getIngresoReconocimientoResponse();

    /**
     * Sets the "ingresoReconocimientoResponse" element
     */
    void setIngresoReconocimientoResponse(
        cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument.IngresoReconocimientoResponse ingresoReconocimientoResponse);

    /**
     * Appends and returns a new empty "ingresoReconocimientoResponse" element
     */
    cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument.IngresoReconocimientoResponse addNewIngresoReconocimientoResponse();

    /**
     * An XML ingresoReconocimientoResponse(@http://ws.siagf.paperless.cl).
     *
     * This is a complex type.
     */
    public interface IngresoReconocimientoResponse extends org.apache.xmlbeans.XmlObject {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType) org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(IngresoReconocimientoResponse.class.getClassLoader(),
                "schemaorg_apache_xmlbeans.system.s0FF3637DCEF037601CBA996AEBB26C07")
                                                                                                                               .resolveHandle("ingresoreconocimientoresponse64f3elemtype");

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

            public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument.IngresoReconocimientoResponse newInstance() {
                return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument.IngresoReconocimientoResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                                                  .newInstance(type,
                    null);
            }

            public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument.IngresoReconocimientoResponse newInstance(
                org.apache.xmlbeans.XmlOptions options) {
                return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument.IngresoReconocimientoResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
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

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument newInstance() {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .newInstance(type,
                null);
        }

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument newInstance(
            org.apache.xmlbeans.XmlOptions options) {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .newInstance(type,
                options);
        }

        /** @param xmlAsString the string value to parse */
        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            java.lang.String xmlAsString)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(xmlAsString,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(xmlAsString,
                type, options);
        }

        /** @param file the file from which to load an xml document */
        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            java.io.File file)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(file,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            java.io.File file, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(file,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            java.net.URL u)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(u,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            java.net.URL u, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(u,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            java.io.InputStream is)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(is,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            java.io.InputStream is, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(is,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            java.io.Reader r)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(r,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            java.io.Reader r, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(r,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            javax.xml.stream.XMLStreamReader sr)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(sr,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            javax.xml.stream.XMLStreamReader sr,
            org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(sr,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(node,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(node,
                type, options);
        }

        /** @deprecated {@link XMLInputStream} */
        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                .parse(xis,
                type, null);
        }

        /** @deprecated {@link XMLInputStream} */
        public static cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument parse(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis,
            org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
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
