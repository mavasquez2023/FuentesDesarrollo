/*
 * An XML document type.
 * Localname: extincionReconocimiento
 * Namespace: http://ws.siagf.paperless.cl
 * Java type: cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd;


/**
 * A document containing one extincionReconocimiento(@http://ws.siagf.paperless.cl) element.
 *
 * This is a complex type.
 */
public interface ExtincionReconocimientoDocument extends org.apache.xmlbeans.XmlObject {
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType) org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ExtincionReconocimientoDocument.class.getClassLoader(),
            "schemaorg_apache_xmlbeans.system.sF0B7A7A194799DEDAEA9D503C83E6746")
                                                                                                                           .resolveHandle("extincionreconocimientod5bedoctype");

    /**
     * Gets the "extincionReconocimiento" element
     */
    cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument.ExtincionReconocimiento getExtincionReconocimiento();

    /**
     * Sets the "extincionReconocimiento" element
     */
    void setExtincionReconocimiento(
        cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument.ExtincionReconocimiento extincionReconocimiento);

    /**
     * Appends and returns a new empty "extincionReconocimiento" element
     */
    cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument.ExtincionReconocimiento addNewExtincionReconocimiento();

    /**
     * An XML extincionReconocimiento(@http://ws.siagf.paperless.cl).
     *
     * This is a complex type.
     */
    public interface ExtincionReconocimiento extends org.apache.xmlbeans.XmlObject {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType) org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ExtincionReconocimiento.class.getClassLoader(),
                "schemaorg_apache_xmlbeans.system.sF0B7A7A194799DEDAEA9D503C83E6746")
                                                                                                                               .resolveHandle("extincionreconocimientof6c1elemtype");

        /**
         * Gets the "Token" element
         */
        java.lang.String getToken();

        /**
         * Gets (as xml) the "Token" element
         */
        org.apache.xmlbeans.XmlString xgetToken();

        /**
         * Tests for nil "Token" element
         */
        boolean isNilToken();

        /**
         * True if has "Token" element
         */
        boolean isSetToken();

        /**
         * Sets the "Token" element
         */
        void setToken(java.lang.String token);

        /**
         * Sets (as xml) the "Token" element
         */
        void xsetToken(org.apache.xmlbeans.XmlString token);

        /**
         * Nils the "Token" element
         */
        void setNilToken();

        /**
         * Unsets the "Token" element
         */
        void unsetToken();

        /**
         * Gets the "XmlDetalle" element
         */
        java.lang.String getXmlDetalle();

        /**
         * Gets (as xml) the "XmlDetalle" element
         */
        org.apache.xmlbeans.XmlString xgetXmlDetalle();

        /**
         * Tests for nil "XmlDetalle" element
         */
        boolean isNilXmlDetalle();

        /**
         * True if has "XmlDetalle" element
         */
        boolean isSetXmlDetalle();

        /**
         * Sets the "XmlDetalle" element
         */
        void setXmlDetalle(java.lang.String xmlDetalle);

        /**
         * Sets (as xml) the "XmlDetalle" element
         */
        void xsetXmlDetalle(org.apache.xmlbeans.XmlString xmlDetalle);

        /**
         * Nils the "XmlDetalle" element
         */
        void setNilXmlDetalle();

        /**
         * Unsets the "XmlDetalle" element
         */
        void unsetXmlDetalle();

        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        public static final class Factory {
            private Factory() {
            } // No instance of this class allowed

            public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument.ExtincionReconocimiento newInstance() {
                return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument.ExtincionReconocimiento) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                                        .newInstance(type,
                    null);
            }

            public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument.ExtincionReconocimiento newInstance(
                org.apache.xmlbeans.XmlOptions options) {
                return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument.ExtincionReconocimiento) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
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

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument newInstance() {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .newInstance(type,
                null);
        }

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument newInstance(
            org.apache.xmlbeans.XmlOptions options) {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .newInstance(type,
                options);
        }

        /** @param xmlAsString the string value to parse */
        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            java.lang.String xmlAsString)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(xmlAsString,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(xmlAsString,
                type, options);
        }

        /** @param file the file from which to load an xml document */
        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            java.io.File file)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(file,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            java.io.File file, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(file,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            java.net.URL u)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(u,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            java.net.URL u, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(u,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            java.io.InputStream is)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(is,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            java.io.InputStream is, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(is,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            java.io.Reader r)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(r,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            java.io.Reader r, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(r,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            javax.xml.stream.XMLStreamReader sr)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(sr,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            javax.xml.stream.XMLStreamReader sr,
            org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(sr,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(node,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(node,
                type, options);
        }

        /** @deprecated {@link XMLInputStream} */
        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                            .parse(xis,
                type, null);
        }

        /** @deprecated {@link XMLInputStream} */
        public static cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument parse(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis,
            org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return (cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
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
