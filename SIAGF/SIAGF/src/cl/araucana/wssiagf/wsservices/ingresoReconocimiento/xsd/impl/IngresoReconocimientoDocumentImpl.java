/*
 * An XML document type.
 * Localname: ingresoReconocimiento
 * Namespace: http://ws.siagf.paperless.cl
 * Java type: cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.impl;


/**
 * A document containing one ingresoReconocimiento(@http://ws.siagf.paperless.cl) element.
 *
 * This is a complex type.
 */
public class IngresoReconocimientoDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
    implements cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument {
    private static final javax.xml.namespace.QName INGRESORECONOCIMIENTO$0 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
            "ingresoReconocimiento");

    public IngresoReconocimientoDocumentImpl(
        org.apache.xmlbeans.SchemaType sType) {
        super(sType);
    }

    /**
     * Gets the "ingresoReconocimiento" element
     */
    public cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument.IngresoReconocimiento getIngresoReconocimiento() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument.IngresoReconocimiento target =
                null;
            target = (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument.IngresoReconocimiento) get_store()
                                                                                                                                        .find_element_user(INGRESORECONOCIMIENTO$0,
                    0);

            if (target == null) {
                return null;
            }

            return target;
        }
    }

    /**
     * Sets the "ingresoReconocimiento" element
     */
    public void setIngresoReconocimiento(
        cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument.IngresoReconocimiento ingresoReconocimiento) {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument.IngresoReconocimiento target =
                null;
            target = (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument.IngresoReconocimiento) get_store()
                                                                                                                                        .find_element_user(INGRESORECONOCIMIENTO$0,
                    0);

            if (target == null) {
                target = (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument.IngresoReconocimiento) get_store()
                                                                                                                                            .add_element_user(INGRESORECONOCIMIENTO$0);
            }

            target.set(ingresoReconocimiento);
        }
    }

    /**
     * Appends and returns a new empty "ingresoReconocimiento" element
     */
    public cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument.IngresoReconocimiento addNewIngresoReconocimiento() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument.IngresoReconocimiento target =
                null;
            target = (cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument.IngresoReconocimiento) get_store()
                                                                                                                                        .add_element_user(INGRESORECONOCIMIENTO$0);

            return target;
        }
    }

    /**
     * An XML ingresoReconocimiento(@http://ws.siagf.paperless.cl).
     *
     * This is a complex type.
     */
    public static class IngresoReconocimientoImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
        implements cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument.IngresoReconocimiento {
        private static final javax.xml.namespace.QName TOKEN$0 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
                "Token");
        private static final javax.xml.namespace.QName XMLDETALLE$2 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
                "XmlDetalle");

        public IngresoReconocimientoImpl(org.apache.xmlbeans.SchemaType sType) {
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
         * Gets the "XmlDetalle" element
         */
        public java.lang.String getXmlDetalle() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(XMLDETALLE$2,
                        0);

                if (target == null) {
                    return null;
                }

                return target.getStringValue();
            }
        }

        /**
         * Gets (as xml) the "XmlDetalle" element
         */
        public org.apache.xmlbeans.XmlString xgetXmlDetalle() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(XMLDETALLE$2,
                        0);

                return target;
            }
        }

        /**
         * Tests for nil "XmlDetalle" element
         */
        public boolean isNilXmlDetalle() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(XMLDETALLE$2,
                        0);

                if (target == null) {
                    return false;
                }

                return target.isNil();
            }
        }

        /**
         * True if has "XmlDetalle" element
         */
        public boolean isSetXmlDetalle() {
            synchronized (monitor()) {
                check_orphaned();

                return get_store().count_elements(XMLDETALLE$2) != 0;
            }
        }

        /**
         * Sets the "XmlDetalle" element
         */
        public void setXmlDetalle(java.lang.String xmlDetalle) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(XMLDETALLE$2,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                                   .add_element_user(XMLDETALLE$2);
                }

                target.setStringValue(xmlDetalle);
            }
        }

        /**
         * Sets (as xml) the "XmlDetalle" element
         */
        public void xsetXmlDetalle(org.apache.xmlbeans.XmlString xmlDetalle) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(XMLDETALLE$2,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.XmlString) get_store()
                                                                 .add_element_user(XMLDETALLE$2);
                }

                target.set(xmlDetalle);
            }
        }

        /**
         * Nils the "XmlDetalle" element
         */
        public void setNilXmlDetalle() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(XMLDETALLE$2,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.XmlString) get_store()
                                                                 .add_element_user(XMLDETALLE$2);
                }

                target.setNil();
            }
        }

        /**
         * Unsets the "XmlDetalle" element
         */
        public void unsetXmlDetalle() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(XMLDETALLE$2, 0);
            }
        }
    }
}
