/*
 * An XML document type.
 * Localname: getVersionResponse
 * Namespace: http://axisversion.sample
 * Java type: cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.version.xsd.impl;


/**
 * A document containing one getVersionResponse(@http://axisversion.sample) element.
 *
 * This is a complex type.
 */
public class GetVersionResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
    implements cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument {
    private static final javax.xml.namespace.QName GETVERSIONRESPONSE$0 = new javax.xml.namespace.QName("http://axisversion.sample",
            "getVersionResponse");

    public GetVersionResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType) {
        super(sType);
    }

    /**
     * Gets the "getVersionResponse" element
     */
    public cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument.GetVersionResponse getGetVersionResponse() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument.GetVersionResponse target =
                null;
            target = (cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument.GetVersionResponse) get_store()
                                                                                                                    .find_element_user(GETVERSIONRESPONSE$0,
                    0);

            if (target == null) {
                return null;
            }

            return target;
        }
    }

    /**
     * Sets the "getVersionResponse" element
     */
    public void setGetVersionResponse(
        cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument.GetVersionResponse getVersionResponse) {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument.GetVersionResponse target =
                null;
            target = (cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument.GetVersionResponse) get_store()
                                                                                                                    .find_element_user(GETVERSIONRESPONSE$0,
                    0);

            if (target == null) {
                target = (cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument.GetVersionResponse) get_store()
                                                                                                                        .add_element_user(GETVERSIONRESPONSE$0);
            }

            target.set(getVersionResponse);
        }
    }

    /**
     * Appends and returns a new empty "getVersionResponse" element
     */
    public cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument.GetVersionResponse addNewGetVersionResponse() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument.GetVersionResponse target =
                null;
            target = (cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument.GetVersionResponse) get_store()
                                                                                                                    .add_element_user(GETVERSIONRESPONSE$0);

            return target;
        }
    }

    /**
     * An XML getVersionResponse(@http://axisversion.sample).
     *
     * This is a complex type.
     */
    public static class GetVersionResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
        implements cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument.GetVersionResponse {
        private static final javax.xml.namespace.QName RETURN$0 = new javax.xml.namespace.QName("http://axisversion.sample",
                "return");

        public GetVersionResponseImpl(org.apache.xmlbeans.SchemaType sType) {
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
