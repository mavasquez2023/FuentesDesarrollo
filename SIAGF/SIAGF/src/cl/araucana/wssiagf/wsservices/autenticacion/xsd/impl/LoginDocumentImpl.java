/*
 * An XML document type.
 * Localname: login
 * Namespace: http://ws.siagf.paperless.cl
 * Java type: cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.autenticacion.xsd.impl;


/**
 * A document containing one login(@http://ws.siagf.paperless.cl) element.
 *
 * This is a complex type.
 */
public class LoginDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
    implements cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument {
    private static final javax.xml.namespace.QName LOGIN$0 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
            "login");

    public LoginDocumentImpl(org.apache.xmlbeans.SchemaType sType) {
        super(sType);
    }

    /**
     * Gets the "login" element
     */
    public cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login getLogin() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login target =
                null;
            target = (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login) get_store()
                                                                                                .find_element_user(LOGIN$0,
                    0);

            if (target == null) {
                return null;
            }

            return target;
        }
    }

    /**
     * Sets the "login" element
     */
    public void setLogin(
        cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login login) {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login target =
                null;
            target = (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login) get_store()
                                                                                                .find_element_user(LOGIN$0,
                    0);

            if (target == null) {
                target = (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login) get_store()
                                                                                                    .add_element_user(LOGIN$0);
            }

            target.set(login);
        }
    }

    /**
     * Appends and returns a new empty "login" element
     */
    public cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login addNewLogin() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login target =
                null;
            target = (cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login) get_store()
                                                                                                .add_element_user(LOGIN$0);

            return target;
        }
    }

    /**
     * An XML login(@http://ws.siagf.paperless.cl).
     *
     * This is a complex type.
     */
    public static class LoginImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
        implements cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument.Login {
        private static final javax.xml.namespace.QName CODIGOENTIDAD$0 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
                "CodigoEntidad");
        private static final javax.xml.namespace.QName LOGINUSUARIO$2 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
                "LoginUsuario");
        private static final javax.xml.namespace.QName CLAVEUSUARIO$4 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
                "ClaveUsuario");

        public LoginImpl(org.apache.xmlbeans.SchemaType sType) {
            super(sType);
        }

        /**
         * Gets the "CodigoEntidad" element
         */
        public int getCodigoEntidad() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(CODIGOENTIDAD$0,
                        0);

                if (target == null) {
                    return 0;
                }

                return target.getIntValue();
            }
        }

        /**
         * Gets (as xml) the "CodigoEntidad" element
         */
        public org.apache.xmlbeans.XmlInt xgetCodigoEntidad() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlInt target = null;
                target = (org.apache.xmlbeans.XmlInt) get_store()
                                                          .find_element_user(CODIGOENTIDAD$0,
                        0);

                return target;
            }
        }

        /**
         * True if has "CodigoEntidad" element
         */
        public boolean isSetCodigoEntidad() {
            synchronized (monitor()) {
                check_orphaned();

                return get_store().count_elements(CODIGOENTIDAD$0) != 0;
            }
        }

        /**
         * Sets the "CodigoEntidad" element
         */
        public void setCodigoEntidad(int codigoEntidad) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(CODIGOENTIDAD$0,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                                   .add_element_user(CODIGOENTIDAD$0);
                }

                target.setIntValue(codigoEntidad);
            }
        }

        /**
         * Sets (as xml) the "CodigoEntidad" element
         */
        public void xsetCodigoEntidad(org.apache.xmlbeans.XmlInt codigoEntidad) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlInt target = null;
                target = (org.apache.xmlbeans.XmlInt) get_store()
                                                          .find_element_user(CODIGOENTIDAD$0,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.XmlInt) get_store()
                                                              .add_element_user(CODIGOENTIDAD$0);
                }

                target.set(codigoEntidad);
            }
        }

        /**
         * Unsets the "CodigoEntidad" element
         */
        public void unsetCodigoEntidad() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(CODIGOENTIDAD$0, 0);
            }
        }

        /**
         * Gets the "LoginUsuario" element
         */
        public java.lang.String getLoginUsuario() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(LOGINUSUARIO$2,
                        0);

                if (target == null) {
                    return null;
                }

                return target.getStringValue();
            }
        }

        /**
         * Gets (as xml) the "LoginUsuario" element
         */
        public org.apache.xmlbeans.XmlString xgetLoginUsuario() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(LOGINUSUARIO$2,
                        0);

                return target;
            }
        }

        /**
         * Tests for nil "LoginUsuario" element
         */
        public boolean isNilLoginUsuario() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(LOGINUSUARIO$2,
                        0);

                if (target == null) {
                    return false;
                }

                return target.isNil();
            }
        }

        /**
         * True if has "LoginUsuario" element
         */
        public boolean isSetLoginUsuario() {
            synchronized (monitor()) {
                check_orphaned();

                return get_store().count_elements(LOGINUSUARIO$2) != 0;
            }
        }

        /**
         * Sets the "LoginUsuario" element
         */
        public void setLoginUsuario(java.lang.String loginUsuario) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(LOGINUSUARIO$2,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                                   .add_element_user(LOGINUSUARIO$2);
                }

                target.setStringValue(loginUsuario);
            }
        }

        /**
         * Sets (as xml) the "LoginUsuario" element
         */
        public void xsetLoginUsuario(org.apache.xmlbeans.XmlString loginUsuario) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(LOGINUSUARIO$2,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.XmlString) get_store()
                                                                 .add_element_user(LOGINUSUARIO$2);
                }

                target.set(loginUsuario);
            }
        }

        /**
         * Nils the "LoginUsuario" element
         */
        public void setNilLoginUsuario() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(LOGINUSUARIO$2,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.XmlString) get_store()
                                                                 .add_element_user(LOGINUSUARIO$2);
                }

                target.setNil();
            }
        }

        /**
         * Unsets the "LoginUsuario" element
         */
        public void unsetLoginUsuario() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(LOGINUSUARIO$2, 0);
            }
        }

        /**
         * Gets the "ClaveUsuario" element
         */
        public java.lang.String getClaveUsuario() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(CLAVEUSUARIO$4,
                        0);

                if (target == null) {
                    return null;
                }

                return target.getStringValue();
            }
        }

        /**
         * Gets (as xml) the "ClaveUsuario" element
         */
        public org.apache.xmlbeans.XmlString xgetClaveUsuario() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(CLAVEUSUARIO$4,
                        0);

                return target;
            }
        }

        /**
         * Tests for nil "ClaveUsuario" element
         */
        public boolean isNilClaveUsuario() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(CLAVEUSUARIO$4,
                        0);

                if (target == null) {
                    return false;
                }

                return target.isNil();
            }
        }

        /**
         * True if has "ClaveUsuario" element
         */
        public boolean isSetClaveUsuario() {
            synchronized (monitor()) {
                check_orphaned();

                return get_store().count_elements(CLAVEUSUARIO$4) != 0;
            }
        }

        /**
         * Sets the "ClaveUsuario" element
         */
        public void setClaveUsuario(java.lang.String claveUsuario) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(CLAVEUSUARIO$4,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                                   .add_element_user(CLAVEUSUARIO$4);
                }

                target.setStringValue(claveUsuario);
            }
        }

        /**
         * Sets (as xml) the "ClaveUsuario" element
         */
        public void xsetClaveUsuario(org.apache.xmlbeans.XmlString claveUsuario) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(CLAVEUSUARIO$4,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.XmlString) get_store()
                                                                 .add_element_user(CLAVEUSUARIO$4);
                }

                target.set(claveUsuario);
            }
        }

        /**
         * Nils the "ClaveUsuario" element
         */
        public void setNilClaveUsuario() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(CLAVEUSUARIO$4,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.XmlString) get_store()
                                                                 .add_element_user(CLAVEUSUARIO$4);
                }

                target.setNil();
            }
        }

        /**
         * Unsets the "ClaveUsuario" element
         */
        public void unsetClaveUsuario() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(CLAVEUSUARIO$4, 0);
            }
        }
    }
}
