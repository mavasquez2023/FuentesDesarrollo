/*
 * An XML document type.
 * Localname: Exception
 * Namespace: http://axisversion.sample
 * Java type: cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.version.xsd.impl;


/**
 * A document containing one Exception(@http://axisversion.sample) element.
 *
 * This is a complex type.
 */
public class ExceptionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
    implements cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument {
    private static final javax.xml.namespace.QName EXCEPTION$0 = new javax.xml.namespace.QName("http://axisversion.sample",
            "Exception");

    public ExceptionDocumentImpl(org.apache.xmlbeans.SchemaType sType) {
        super(sType);
    }

    /**
     * Gets the "Exception" element
     */
    public cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument.Exception getException() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument.Exception target =
                null;
            target = (cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument.Exception) get_store()
                                                                                                  .find_element_user(EXCEPTION$0,
                    0);

            if (target == null) {
                return null;
            }

            return target;
        }
    }

    /**
     * Sets the "Exception" element
     */
    public void setException(
        cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument.Exception exception) {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument.Exception target =
                null;
            target = (cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument.Exception) get_store()
                                                                                                  .find_element_user(EXCEPTION$0,
                    0);

            if (target == null) {
                target = (cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument.Exception) get_store()
                                                                                                      .add_element_user(EXCEPTION$0);
            }

            target.set(exception);
        }
    }

    /**
     * Appends and returns a new empty "Exception" element
     */
    public cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument.Exception addNewException() {
        synchronized (monitor()) {
            check_orphaned();

            cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument.Exception target =
                null;
            target = (cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument.Exception) get_store()
                                                                                                  .add_element_user(EXCEPTION$0);

            return target;
        }
    }

    /**
     * An XML Exception(@http://axisversion.sample).
     *
     * This is a complex type.
     */
    public static class ExceptionImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
        implements cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument.Exception {
        private static final javax.xml.namespace.QName EXCEPTION$0 = new javax.xml.namespace.QName("http://axisversion.sample",
                "Exception");

        public ExceptionImpl(org.apache.xmlbeans.SchemaType sType) {
            super(sType);
        }

        /**
         * Gets the "Exception" element
         */
        public cl.araucana.wssiagf.wsservices.version.xsd.Exception getException() {
            synchronized (monitor()) {
                check_orphaned();

                cl.araucana.wssiagf.wsservices.version.xsd.Exception target = null;
                target = (cl.araucana.wssiagf.wsservices.version.xsd.Exception) get_store()
                                                                                    .find_element_user(EXCEPTION$0,
                        0);

                if (target == null) {
                    return null;
                }

                return target;
            }
        }

        /**
         * Tests for nil "Exception" element
         */
        public boolean isNilException() {
            synchronized (monitor()) {
                check_orphaned();

                cl.araucana.wssiagf.wsservices.version.xsd.Exception target = null;
                target = (cl.araucana.wssiagf.wsservices.version.xsd.Exception) get_store()
                                                                                    .find_element_user(EXCEPTION$0,
                        0);

                if (target == null) {
                    return false;
                }

                return target.isNil();
            }
        }

        /**
         * True if has "Exception" element
         */
        public boolean isSetException() {
            synchronized (monitor()) {
                check_orphaned();

                return get_store().count_elements(EXCEPTION$0) != 0;
            }
        }

        /**
         * Sets the "Exception" element
         */
        public void setException(
            cl.araucana.wssiagf.wsservices.version.xsd.Exception exception) {
            synchronized (monitor()) {
                check_orphaned();

                cl.araucana.wssiagf.wsservices.version.xsd.Exception target = null;
                target = (cl.araucana.wssiagf.wsservices.version.xsd.Exception) get_store()
                                                                                    .find_element_user(EXCEPTION$0,
                        0);

                if (target == null) {
                    target = (cl.araucana.wssiagf.wsservices.version.xsd.Exception) get_store()
                                                                                        .add_element_user(EXCEPTION$0);
                }

                target.set(exception);
            }
        }

        /**
         * Appends and returns a new empty "Exception" element
         */
        public cl.araucana.wssiagf.wsservices.version.xsd.Exception addNewException() {
            synchronized (monitor()) {
                check_orphaned();

                cl.araucana.wssiagf.wsservices.version.xsd.Exception target = null;
                target = (cl.araucana.wssiagf.wsservices.version.xsd.Exception) get_store()
                                                                                    .add_element_user(EXCEPTION$0);

                return target;
            }
        }

        /**
         * Nils the "Exception" element
         */
        public void setNilException() {
            synchronized (monitor()) {
                check_orphaned();

                cl.araucana.wssiagf.wsservices.version.xsd.Exception target = null;
                target = (cl.araucana.wssiagf.wsservices.version.xsd.Exception) get_store()
                                                                                    .find_element_user(EXCEPTION$0,
                        0);

                if (target == null) {
                    target = (cl.araucana.wssiagf.wsservices.version.xsd.Exception) get_store()
                                                                                        .add_element_user(EXCEPTION$0);
                }

                target.setNil();
            }
        }

        /**
         * Unsets the "Exception" element
         */
        public void unsetException() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(EXCEPTION$0, 0);
            }
        }
    }
}
