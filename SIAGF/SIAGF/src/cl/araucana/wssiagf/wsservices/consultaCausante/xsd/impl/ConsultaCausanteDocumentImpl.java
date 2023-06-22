/*
 * An XML document type.
 * Localname: consultaCausante
 * Namespace: http://ws.siagf.paperless.cl
 * Java type: cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.consultaCausante.xsd.impl;


/**
 * A document containing one consultaCausante(@http://ws.siagf.paperless.cl) element.
 *
 * This is a complex type.
 */
public class ConsultaCausanteDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
    implements cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument {
    private static final javax.xml.namespace.QName CONSULTACAUSANTE$0 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
            "consultaCausante");

    public ConsultaCausanteDocumentImpl(org.apache.xmlbeans.SchemaType sType) {
        super(sType);
    }

    /**
     * Gets the "consultaCausante" element
     */
    public cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante getConsultaCausante() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante target =
                null;
            target = (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante) get_store()
                                                                                                                         .find_element_user(CONSULTACAUSANTE$0,
                    0);

            if (target == null) {
                return null;
            }

            return target;
        }
    }

    /**
     * Sets the "consultaCausante" element
     */
    public void setConsultaCausante(
        cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante consultaCausante) {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante target =
                null;
            target = (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante) get_store()
                                                                                                                         .find_element_user(CONSULTACAUSANTE$0,
                    0);

            if (target == null) {
                target = (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante) get_store()
                                                                                                                             .add_element_user(CONSULTACAUSANTE$0);
            }

            target.set(consultaCausante);
        }
    }

    /**
     * Appends and returns a new empty "consultaCausante" element
     */
    public cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante addNewConsultaCausante() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante target =
                null;
            target = (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante) get_store()
                                                                                                                         .add_element_user(CONSULTACAUSANTE$0);

            return target;
        }
    }

    /**
     * An XML consultaCausante(@http://ws.siagf.paperless.cl).
     *
     * This is a complex type.
     */
    public static class ConsultaCausanteImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
        implements cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument.ConsultaCausante {
        private static final javax.xml.namespace.QName TOKEN$0 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
                "Token");
        private static final javax.xml.namespace.QName RUTCAUSANTE$2 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
                "RutCausante");

        public ConsultaCausanteImpl(org.apache.xmlbeans.SchemaType sType) {
            super(sType);
        }

        /**
         * Gets the "Token" element
         */
        public java.lang.String getToken() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(TOKEN$0,
                        0);

                if (target == null) {
                    return null;
                }

                return target.getStringValue();
            }
        }

        /**
         * Gets (as xml) the "Token" element
         */
        public org.apache.xmlbeans.XmlString xgetToken() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(TOKEN$0,
                        0);

                return target;
            }
        }

        /**
         * Tests for nil "Token" element
         */
        public boolean isNilToken() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(TOKEN$0,
                        0);

                if (target == null) {
                    return false;
                }

                return target.isNil();
            }
        }

        /**
         * True if has "Token" element
         */
        public boolean isSetToken() {
            synchronized (monitor()) {
                check_orphaned();

                return get_store().count_elements(TOKEN$0) != 0;
            }
        }

        /**
         * Sets the "Token" element
         */
        public void setToken(java.lang.String token) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(TOKEN$0,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                                   .add_element_user(TOKEN$0);
                }

                target.setStringValue(token);
            }
        }

        /**
         * Sets (as xml) the "Token" element
         */
        public void xsetToken(org.apache.xmlbeans.XmlString token) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(TOKEN$0,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.XmlString) get_store()
                                                                 .add_element_user(TOKEN$0);
                }

                target.set(token);
            }
        }

        /**
         * Nils the "Token" element
         */
        public void setNilToken() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(TOKEN$0,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.XmlString) get_store()
                                                                 .add_element_user(TOKEN$0);
                }

                target.setNil();
            }
        }

        /**
         * Unsets the "Token" element
         */
        public void unsetToken() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(TOKEN$0, 0);
            }
        }

        /**
         * Gets the "RutCausante" element
         */
        public java.lang.String getRutCausante() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(RUTCAUSANTE$2,
                        0);

                if (target == null) {
                    return null;
                }

                return target.getStringValue();
            }
        }

        /**
         * Gets (as xml) the "RutCausante" element
         */
        public org.apache.xmlbeans.XmlString xgetRutCausante() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(RUTCAUSANTE$2,
                        0);

                return target;
            }
        }

        /**
         * Tests for nil "RutCausante" element
         */
        public boolean isNilRutCausante() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(RUTCAUSANTE$2,
                        0);

                if (target == null) {
                    return false;
                }

                return target.isNil();
            }
        }

        /**
         * True if has "RutCausante" element
         */
        public boolean isSetRutCausante() {
            synchronized (monitor()) {
                check_orphaned();

                return get_store().count_elements(RUTCAUSANTE$2) != 0;
            }
        }

        /**
         * Sets the "RutCausante" element
         */
        public void setRutCausante(java.lang.String rutCausante) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(RUTCAUSANTE$2,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                                   .add_element_user(RUTCAUSANTE$2);
                }

                target.setStringValue(rutCausante);
            }
        }

        /**
         * Sets (as xml) the "RutCausante" element
         */
        public void xsetRutCausante(org.apache.xmlbeans.XmlString rutCausante) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(RUTCAUSANTE$2,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.XmlString) get_store()
                                                                 .add_element_user(RUTCAUSANTE$2);
                }

                target.set(rutCausante);
            }
        }

        /**
         * Nils the "RutCausante" element
         */
        public void setNilRutCausante() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(RUTCAUSANTE$2,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.XmlString) get_store()
                                                                 .add_element_user(RUTCAUSANTE$2);
                }

                target.setNil();
            }
        }

        /**
         * Unsets the "RutCausante" element
         */
        public void unsetRutCausante() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(RUTCAUSANTE$2, 0);
            }
        }
    }
}
