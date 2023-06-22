/*
 * An XML document type.
 * Localname: actualizarCausanteResponse
 * Namespace: http://ws.siagf.paperless.cl
 * Java type: cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.impl;

/**
 * A document containing one actualizarCausanteResponse(@http://ws.siagf.paperless.cl) element.
 *
 * This is a complex type.
 */
public class ActualizarCausanteResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
    implements cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument {
    private static final javax.xml.namespace.QName ACTUALIZARCAUSANTERESPONSE$0 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
            "actualizarCausanteResponse");

    public ActualizarCausanteResponseDocumentImpl(
        org.apache.xmlbeans.SchemaType sType) {
        super(sType);
    }

    /**
     * Gets the "actualizarCausanteResponse" element
     */
    public cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse getActualizarCausanteResponse() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse target =
                null;
            target = (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse) get_store()
                                                                                                                                               .find_element_user(ACTUALIZARCAUSANTERESPONSE$0,
                    0);

            if (target == null) {
                return null;
            }

            return target;
        }
    }

    /**
     * Sets the "actualizarCausanteResponse" element
     */
    public void setActualizarCausanteResponse(
        cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse actualizarCausanteResponse) {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse target =
                null;
            target = (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse) get_store()
                                                                                                                                               .find_element_user(ACTUALIZARCAUSANTERESPONSE$0,
                    0);

            if (target == null) {
                target = (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse) get_store()
                                                                                                                                                   .add_element_user(ACTUALIZARCAUSANTERESPONSE$0);
            }

            target.set(actualizarCausanteResponse);
        }
    }

    /**
     * Appends and returns a new empty "actualizarCausanteResponse" element
     */
    public cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse addNewActualizarCausanteResponse() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse target =
                null;
            target = (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse) get_store()
                                                                                                                                               .add_element_user(ACTUALIZARCAUSANTERESPONSE$0);

            return target;
        }
    }

    /**
     * An XML actualizarCausanteResponse(@http://ws.siagf.paperless.cl).
     *
     * This is a complex type.
     */
    public static class ActualizarCausanteResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
        implements cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.ActualizarCausanteResponse {
        private static final javax.xml.namespace.QName RETURN$0 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
                "return");

        public ActualizarCausanteResponseImpl(
            org.apache.xmlbeans.SchemaType sType) {
            super(sType);
        }

        /**
         * Gets the "return" element
         */
        public java.lang.String getReturn() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(RETURN$0,
                        0);

                if (target == null) {
                    return null;
                }

                return target.getStringValue();
            }
        }

        /**
         * Gets (as xml) the "return" element
         */
        public org.apache.xmlbeans.XmlString xgetReturn() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(RETURN$0,
                        0);

                return target;
            }
        }

        /**
         * Tests for nil "return" element
         */
        public boolean isNilReturn() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(RETURN$0,
                        0);

                if (target == null) {
                    return false;
                }

                return target.isNil();
            }
        }

        /**
         * True if has "return" element
         */
        public boolean isSetReturn() {
            synchronized (monitor()) {
                check_orphaned();

                return get_store().count_elements(RETURN$0) != 0;
            }
        }

        /**
         * Sets the "return" element
         */
        public void setReturn(java.lang.String xreturn) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                               .find_element_user(RETURN$0,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.SimpleValue) get_store()
                                                                   .add_element_user(RETURN$0);
                }

                target.setStringValue(xreturn);
            }
        }

        /**
         * Sets (as xml) the "return" element
         */
        public void xsetReturn(org.apache.xmlbeans.XmlString xreturn) {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(RETURN$0,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.XmlString) get_store()
                                                                 .add_element_user(RETURN$0);
                }

                target.set(xreturn);
            }
        }

        /**
         * Nils the "return" element
         */
        public void setNilReturn() {
            synchronized (monitor()) {
                check_orphaned();

                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString) get_store()
                                                             .find_element_user(RETURN$0,
                        0);

                if (target == null) {
                    target = (org.apache.xmlbeans.XmlString) get_store()
                                                                 .add_element_user(RETURN$0);
                }

                target.setNil();
            }
        }

        /**
         * Unsets the "return" element
         */
        public void unsetReturn() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(RETURN$0, 0);
            }
        }
    }
}
