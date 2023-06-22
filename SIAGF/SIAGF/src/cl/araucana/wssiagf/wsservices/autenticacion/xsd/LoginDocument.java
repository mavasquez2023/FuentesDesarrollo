/*
 * An XML document type.
 * Localname: login
 * Namespace: http://ws.siagf.paperless.cl
 * Java type: cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.autenticacion.xsd;


/**
 * A document containing one login(@http://ws.siagf.paperless.cl) element.
 *
 * This is a complex type.
 */
public interface LoginDocument extends org.apache.xmlbeans.XmlObject {
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType) org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(LoginDocument.class.getClassLoader(),
            "schemaorg_apache_xmlbeans.system.s73036C3595274D648DA197F2FB60D65F")
                                                                                                                           .resolveHandle("logina386doctype");

    /**
     * Gets the "login" element
     */
    cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login getLogin();

    /**
     * Sets the "login" element
     */
    void setLogin(
        cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login login);

    /**
     * Appends and returns a new empty "login" element
     */
    cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login addNewLogin();

    /**
     * An XML login(@http://ws.siagf.paperless.cl).
     *
     * This is a complex type.
     */
    public interface Login extends org.apache.xmlbeans.XmlObject {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType) org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Login.class.getClassLoader(),
                "schemaorg_apache_xmlbeans.system.s73036C3595274D648DA197F2FB60D65F")
                                                                                                                               .resolveHandle("login8ed1elemtype");

        /**
         * Gets the "CodigoEntidad" element
         */
        int getCodigoEntidad();

        /**
         * Gets (as xml) the "CodigoEntidad" element
         */
        org.apache.xmlbeans.XmlInt xgetCodigoEntidad();

        /**
         * True if has "CodigoEntidad" element
         */
        boolean isSetCodigoEntidad();

        /**
         * Sets the "CodigoEntidad" element
         */
        void setCodigoEntidad(int codigoEntidad);

        /**
         * Sets (as xml) the "CodigoEntidad" element
         */
        void xsetCodigoEntidad(org.apache.xmlbeans.XmlInt codigoEntidad);

        /**
         * Unsets the "CodigoEntidad" element
         */
        void unsetCodigoEntidad();

        /**
         * Gets the "LoginUsuario" element
         */
        java.lang.String getLoginUsuario();

        /**
         * Gets (as xml) the "LoginUsuario" element
         */
        org.apache.xmlbeans.XmlString xgetLoginUsuario();

        /**
         * Tests for nil "LoginUsuario" element
         */
        boolean isNilLoginUsuario();

        /**
         * True if has "LoginUsuario" element
         */
        boolean isSetLoginUsuario();

        /**
         * Sets the "LoginUsuario" element
         */
        void setLoginUsuario(java.lang.String loginUsuario);

        /**
         * Sets (as xml) the "LoginUsuario" element
         */
        void xsetLoginUsuario(org.apache.xmlbeans.XmlString loginUsuario);

        /**
         * Nils the "LoginUsuario" element
         */
        void setNilLoginUsuario();

        /**
         * Unsets the "LoginUsuario" element
         */
        void unsetLoginUsuario();

        /**
         * Gets the "ClaveUsuario" element
         */
        java.lang.String getClaveUsuario();

        /**
         * Gets (as xml) the "ClaveUsuario" element
         */
        org.apache.xmlbeans.XmlString xgetClaveUsuario();

        /**
         * Tests for nil "ClaveUsuario" element
         */
        boolean isNilClaveUsuario();

        /**
         * True if has "ClaveUsuario" element
         */
        boolean isSetClaveUsuario();

        /**
         * Sets the "ClaveUsuario" element
         */
        void setClaveUsuario(java.lang.String claveUsuario);

        /**
         * Sets (as xml) the "ClaveUsuario" element
         */
        void xsetClaveUsuario(org.apache.xmlbeans.XmlString claveUsuario);

        /**
         * Nils the "ClaveUsuario" element
         */
        void setNilClaveUsuario();

        /**
         * Unsets the "ClaveUsuario" element
         */
        void unsetClaveUsuario();

        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        public static final class Factory {
            private Factory() {
            } // No instance of this class allowed

            public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login newInstance() {
                return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                          .newInstance(type,
                    null);
            }

            public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login newInstance(
                org.apache.xmlbeans.XmlOptions options) {
                return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
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

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument newInstance() {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .newInstance(type,
                null);
        }

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument newInstance(
            org.apache.xmlbeans.XmlOptions options) {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .newInstance(type,
                options);
        }

        /** @param xmlAsString the string value to parse */
        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            java.lang.String xmlAsString)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(xmlAsString,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(xmlAsString,
                type, options);
        }

        /** @param file the file from which to load an xml document */
        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            java.io.File file)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(file,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            java.io.File file, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(file,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            java.net.URL u)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(u,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            java.net.URL u, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(u,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            java.io.InputStream is)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(is,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            java.io.InputStream is, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(is,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            java.io.Reader r)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(r,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            java.io.Reader r, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException, java.io.IOException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(r,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            javax.xml.stream.XMLStreamReader sr)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(sr,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            javax.xml.stream.XMLStreamReader sr,
            org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(sr,
                type, options);
        }

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(node,
                type, null);
        }

        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(node,
                type, options);
        }

        /** @deprecated {@link XMLInputStream} */
        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
                                                                                                                .parse(xis,
                type, null);
        }

        /** @deprecated {@link XMLInputStream} */
        public static cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument parse(
            org.apache.xmlbeans.xml.stream.XMLInputStream xis,
            org.apache.xmlbeans.XmlOptions options)
            throws org.apache.xmlbeans.XmlException,
                org.apache.xmlbeans.xml.stream.XMLStreamException {
            return (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader()
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
