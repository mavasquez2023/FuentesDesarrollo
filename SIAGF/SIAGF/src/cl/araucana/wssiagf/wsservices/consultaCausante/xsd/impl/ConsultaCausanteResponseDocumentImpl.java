/*
 * An XML document type.
 * Localname: consultaCausanteResponse
 * Namespace: http://ws.siagf.paperless.cl
 * Java type: cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.consultaCausante.xsd.impl;


/**
 * A document containing one consultaCausanteResponse(@http://ws.siagf.paperless.cl) element.
 *
 * This is a complex type.
 */
public class ConsultaCausanteResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
    implements cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument {
    private static final javax.xml.namespace.QName CONSULTACAUSANTERESPONSE$0 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
            "consultaCausanteResponse");

    public ConsultaCausanteResponseDocumentImpl(
        org.apache.xmlbeans.SchemaType sType) {
        super(sType);
    }

    /**
     * Gets the "consultaCausanteResponse" element
     */
    public cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument.ConsultaCausanteResponse getConsultaCausanteResponse() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument.ConsultaCausanteResponse target =
                null;
            target = (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument.ConsultaCausanteResponse) get_store()
                                                                                                                                         .find_element_user(CONSULTACAUSANTERESPONSE$0,
                    0);

            if (target == null) {
                return null;
            }

            return target;
        }
    }

    /**
     * Sets the "consultaCausanteResponse" element
     */
    public void setConsultaCausanteResponse(
        cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument.ConsultaCausanteResponse consultaCausanteResponse) {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument.ConsultaCausanteResponse target =
                null;
            target = (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument.ConsultaCausanteResponse) get_store()
                                                                                                                                         .find_element_user(CONSULTACAUSANTERESPONSE$0,
                    0);

            if (target == null) {
                target = (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument.ConsultaCausanteResponse) get_store()
                                                                                                                                             .add_element_user(CONSULTACAUSANTERESPONSE$0);
            }

            target.set(consultaCausanteResponse);
        }
    }

    /**
     * Appends and returns a new empty "consultaCausanteResponse" element
     */
    public cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument.ConsultaCausanteResponse addNewConsultaCausanteResponse() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument.ConsultaCausanteResponse target =
                null;
            target = (cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument.ConsultaCausanteResponse) get_store()
                                                                                                                                         .add_element_user(CONSULTACAUSANTERESPONSE$0);

            return target;
        }
    }

    /**
     * An XML consultaCausanteResponse(@http://ws.siagf.paperless.cl).
     *
     * This is a complex type.
     */
    public static class ConsultaCausanteResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
        implements cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument.ConsultaCausanteResponse {
        private static final javax.xml.namespace.QName RETURN$0 = new javax.xml.namespace.QName("http://ws.siagf.paperless.cl",
                "return");

        public ConsultaCausanteResponseImpl(
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
