/*
 * XML Type:  Exception
 * Namespace: http://axisversion.sample
 * Java type: cl.araucana.wssiagf.wsservices.version.xsd.Exception
 *
 * Automatically generated - do not modify.
 */
package cl.araucana.wssiagf.wsservices.version.xsd.impl;


/**
 * An XML Exception(@http://axisversion.sample).
 *
 * This is a complex type.
 */
public class ExceptionImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl
    implements cl.araucana.wssiagf.wsservices.version.xsd.Exception {
    private static final javax.xml.namespace.QName EXCEPTION$0 = new javax.xml.namespace.QName("http://axisversion.sample",
            "Exception");

    public ExceptionImpl(org.apache.xmlbeans.SchemaType sType) {
        super(sType);
    }

    /**
     * Gets the "Exception" element
     */
    public org.apache.xmlbeans.XmlObject getException() {
        synchronized (monitor()) {
            check_orphaned();

            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject) get_store()
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

            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject) get_store()
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
    public void setException(org.apache.xmlbeans.XmlObject exception) {
        synchronized (monitor()) {
            check_orphaned();

            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject) get_store()
                                                         .find_element_user(EXCEPTION$0,
                    0);

            if (target == null) {
                target = (org.apache.xmlbeans.XmlObject) get_store()
                                                             .add_element_user(EXCEPTION$0);
            }

            target.set(exception);
        }
    }

    /**
     * Appends and returns a new empty "Exception" element
     */
    public org.apache.xmlbeans.XmlObject addNewException() {
        synchronized (monitor()) {
            check_orphaned();

            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject) get_store()
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

            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject) get_store()
                                                         .find_element_user(EXCEPTION$0,
                    0);

            if (target == null) {
                target = (org.apache.xmlbeans.XmlObject) get_store()
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
