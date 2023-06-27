/*
 * XML Type:  CTHuellaDigital
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTHuellaDigital
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CTHuellaDigital(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTHuellaDigitalImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTHuellaDigital
{
    private static final long serialVersionUID = 1L;
    
    public CTHuellaDigitalImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RUT$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "rut");
    private static final javax.xml.namespace.QName HUELLA$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "huella");
    private static final javax.xml.namespace.QName HASH$4 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "hash");
    
    
    /**
     * Gets the "rut" element
     */
    public java.lang.String getRut()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RUT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "rut" element
     */
    public wwwLmeGovClLme.STRut xgetRut()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STRut target = null;
            target = (wwwLmeGovClLme.STRut)get_store().find_element_user(RUT$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "rut" element
     */
    public void setRut(java.lang.String rut)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RUT$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(RUT$0);
            }
            target.setStringValue(rut);
        }
    }
    
    /**
     * Sets (as xml) the "rut" element
     */
    public void xsetRut(wwwLmeGovClLme.STRut rut)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STRut target = null;
            target = (wwwLmeGovClLme.STRut)get_store().find_element_user(RUT$0, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STRut)get_store().add_element_user(RUT$0);
            }
            target.set(rut);
        }
    }
    
    /**
     * Gets the "huella" element
     */
    public byte[] getHuella()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HUELLA$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getByteArrayValue();
        }
    }
    
    /**
     * Gets (as xml) the "huella" element
     */
    public org.apache.xmlbeans.XmlBase64Binary xgetHuella()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(HUELLA$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "huella" element
     */
    public void setHuella(byte[] huella)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HUELLA$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(HUELLA$2);
            }
            target.setByteArrayValue(huella);
        }
    }
    
    /**
     * Sets (as xml) the "huella" element
     */
    public void xsetHuella(org.apache.xmlbeans.XmlBase64Binary huella)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(HUELLA$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBase64Binary)get_store().add_element_user(HUELLA$2);
            }
            target.set(huella);
        }
    }
    
    /**
     * Gets the "hash" element
     */
    public byte[] getHash()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HASH$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getByteArrayValue();
        }
    }
    
    /**
     * Gets (as xml) the "hash" element
     */
    public org.apache.xmlbeans.XmlBase64Binary xgetHash()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(HASH$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "hash" element
     */
    public void setHash(byte[] hash)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HASH$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(HASH$4);
            }
            target.setByteArrayValue(hash);
        }
    }
    
    /**
     * Sets (as xml) the "hash" element
     */
    public void xsetHash(org.apache.xmlbeans.XmlBase64Binary hash)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(HASH$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBase64Binary)get_store().add_element_user(HASH$4);
            }
            target.set(hash);
        }
    }
}
