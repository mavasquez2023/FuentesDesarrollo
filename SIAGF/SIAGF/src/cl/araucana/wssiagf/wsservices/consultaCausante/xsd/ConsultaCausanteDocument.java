/*
 * An XML document type.
 * Localname: consultaCausante
 * Namespace: http://ws.siagf.paperless.cl
 * Java type: cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.consultaCausante.xsd;


/**
 * A document containing one consultaCausante(@http://ws.siagf.paperless.cl) element.
 *
 * This is a complex type.
 */
public interface ConsultaCausanteDocument extends org.apache.xmlbeans.XmlObject {
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType) org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ConsultaCausanteDocument.class.getClassLoader(),
            "schemaorg_apache_xmlbeans.system.sA6DE77B2267E18638D68FE8F4BF9A0EC")
                                                                                                                           .resolveHandle("consultacausantea4aedoctype");

    /**
     * Gets the "consultaCausante" element
     */
    cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante getConsultaCausante();

    /**
     * Sets the "consultaCausante" element
     */
    void setConsultaCausante(
        cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante consultaCausante);

    /**
     * Appends and returns a new empty "consultaCausante" element
     */
    cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante addNewConsultaCausante();

    /**
     * An XML consultaCausante(@http://ws.siagf.paperless.cl).
     *
     * This is a complex type.
     */
    public interface ConsultaCausante extends org.apache.xmlbeans.XmlObject {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType) org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ConsultaCausante.class.getClassLoader(),
                "schemaorg_apache_xmlbeans.system.sA6DE77B2267E18638D68FE8F4BF9A0EC")
                                                                                                                               .resolveHandle("consultacausante5d39elemtype");

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
         * Gets the "RutCausante" element
         */
        java.lang.String getRutCausante();

        /**
         * Gets (as xml) the "RutCausante" element
         */
        org.apache.xmlbeans.XmlString xgetRutCausante();

        /**
         * Tests for nil "RutCausante" element
         */
        boolean isNilRutCausante();

        /**
         * True if has "RutCausante" element
         */
        boolean isSetRutCausante();

        /**
         * Sets the "RutCausante" element
         */
        void setRutCausante(java.lang.String rutCausante);

        /**
         * Sets (as xml) the "RutCausante" element
         */
        void xsetRutCausante(org.apache.xmlbeans.XmlString rutCausante);

        /**
         * Nils the "RutCausante" element
         */
        void setNilRutCausante();

        /**
         * Unsets the "RutCausante" element
         */
        void unsetRutCausante();

        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        public static final class Factory {
            private Factory() {
            } // No instance of this class allowed

            public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante newInstance() {
                return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                                                   .newInstance(type,
                    null);
            }

            public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante newInstance(
                org.apache.xmlbeans.XmlOptions options) {
                return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
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

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument newInstance() {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .newInstance(type,
                null);
        }

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument newInstance(
            org.apache.xmlbeans.XmlOptions options) {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .newInstance(type,
                options);
        }

        /** @param xmlAsString the string value to parse */
        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            java.lang.String xmlAsString)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(xmlAsString,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(xmlAsString,
                type, options);
        }

        /** @param file the file from which to load an xml document */
        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            java.io.File file)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(file,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            java.io.File file, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(file,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            java.net.URL u)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(u,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            java.net.URL u, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(u,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            java.io.InputStream is)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(is,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            java.io.InputStream is, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(is,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            java.io.Reader r)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(r,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            java.io.Reader r, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(r,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            javax.xml.stream.XMLStreamReader sr)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(sr,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            javax.xml.stream.XMLStreamReader sr,
            org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(sr,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(node,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(node,
                type, options);
        }

        /** @deprecated {@link XMLInputStream} */
        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                              .parse(xis,
                type, null);
        }

        /** @deprecated {@link XMLInputStream} */
        public static cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument parse(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis,
            org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
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
