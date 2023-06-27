/*
 * XML Type:  CTDireccion
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTDireccion
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CTDireccion(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTDireccionImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTDireccion
{
    private static final long serialVersionUID = 1L;
    
    public CTDireccionImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CALLE$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "calle");
    private static final javax.xml.namespace.QName NUMERO$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "numero");
    private static final javax.xml.namespace.QName DEPTO$4 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "depto");
    private static final javax.xml.namespace.QName COMUNA$6 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "comuna");
    private static final javax.xml.namespace.QName CIUDAD$8 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "ciudad");
    private static final javax.xml.namespace.QName PAIS$10 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "pais");
    
    
    /**
     * Gets the "calle" element
     */
    public java.lang.String getCalle()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CALLE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "calle" element
     */
    public org.apache.xmlbeans.XmlString xgetCalle()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CALLE$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "calle" element
     */
    public void setCalle(java.lang.String calle)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CALLE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CALLE$0);
            }
            target.setStringValue(calle);
        }
    }
    
    /**
     * Sets (as xml) the "calle" element
     */
    public void xsetCalle(org.apache.xmlbeans.XmlString calle)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CALLE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CALLE$0);
            }
            target.set(calle);
        }
    }
    
    /**
     * Gets the "numero" element
     */
    public java.lang.String getNumero()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NUMERO$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "numero" element
     */
    public org.apache.xmlbeans.XmlString xgetNumero()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NUMERO$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "numero" element
     */
    public boolean isSetNumero()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NUMERO$2) != 0;
        }
    }
    
    /**
     * Sets the "numero" element
     */
    public void setNumero(java.lang.String numero)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NUMERO$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NUMERO$2);
            }
            target.setStringValue(numero);
        }
    }
    
    /**
     * Sets (as xml) the "numero" element
     */
    public void xsetNumero(org.apache.xmlbeans.XmlString numero)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NUMERO$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(NUMERO$2);
            }
            target.set(numero);
        }
    }
    
    /**
     * Unsets the "numero" element
     */
    public void unsetNumero()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NUMERO$2, 0);
        }
    }
    
    /**
     * Gets the "depto" element
     */
    public java.lang.String getDepto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DEPTO$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "depto" element
     */
    public org.apache.xmlbeans.XmlString xgetDepto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DEPTO$4, 0);
            return target;
        }
    }
    
    /**
     * True if has "depto" element
     */
    public boolean isSetDepto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DEPTO$4) != 0;
        }
    }
    
    /**
     * Sets the "depto" element
     */
    public void setDepto(java.lang.String depto)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DEPTO$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DEPTO$4);
            }
            target.setStringValue(depto);
        }
    }
    
    /**
     * Sets (as xml) the "depto" element
     */
    public void xsetDepto(org.apache.xmlbeans.XmlString depto)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DEPTO$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DEPTO$4);
            }
            target.set(depto);
        }
    }
    
    /**
     * Unsets the "depto" element
     */
    public void unsetDepto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DEPTO$4, 0);
        }
    }
    
    /**
     * Gets the "comuna" element
     */
    public wwwLmeGovClLme.STCodigoComuna.Enum getComuna()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMUNA$6, 0);
            if (target == null)
            {
                return null;
            }
            return (wwwLmeGovClLme.STCodigoComuna.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "comuna" element
     */
    public wwwLmeGovClLme.STCodigoComuna xgetComuna()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STCodigoComuna target = null;
            target = (wwwLmeGovClLme.STCodigoComuna)get_store().find_element_user(COMUNA$6, 0);
            return target;
        }
    }
    
    /**
     * Sets the "comuna" element
     */
    public void setComuna(wwwLmeGovClLme.STCodigoComuna.Enum comuna)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMUNA$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COMUNA$6);
            }
            target.setEnumValue(comuna);
        }
    }
    
    /**
     * Sets (as xml) the "comuna" element
     */
    public void xsetComuna(wwwLmeGovClLme.STCodigoComuna comuna)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STCodigoComuna target = null;
            target = (wwwLmeGovClLme.STCodigoComuna)get_store().find_element_user(COMUNA$6, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STCodigoComuna)get_store().add_element_user(COMUNA$6);
            }
            target.set(comuna);
        }
    }
    
    /**
     * Gets the "ciudad" element
     */
    public java.lang.String getCiudad()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CIUDAD$8, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ciudad" element
     */
    public org.apache.xmlbeans.XmlString xgetCiudad()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CIUDAD$8, 0);
            return target;
        }
    }
    
    /**
     * True if has "ciudad" element
     */
    public boolean isSetCiudad()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CIUDAD$8) != 0;
        }
    }
    
    /**
     * Sets the "ciudad" element
     */
    public void setCiudad(java.lang.String ciudad)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CIUDAD$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CIUDAD$8);
            }
            target.setStringValue(ciudad);
        }
    }
    
    /**
     * Sets (as xml) the "ciudad" element
     */
    public void xsetCiudad(org.apache.xmlbeans.XmlString ciudad)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CIUDAD$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CIUDAD$8);
            }
            target.set(ciudad);
        }
    }
    
    /**
     * Unsets the "ciudad" element
     */
    public void unsetCiudad()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CIUDAD$8, 0);
        }
    }
    
    /**
     * Gets the "pais" element
     */
    public java.lang.String getPais()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PAIS$10, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "pais" element
     */
    public org.apache.xmlbeans.XmlString xgetPais()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PAIS$10, 0);
            return target;
        }
    }
    
    /**
     * Sets the "pais" element
     */
    public void setPais(java.lang.String pais)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PAIS$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PAIS$10);
            }
            target.setStringValue(pais);
        }
    }
    
    /**
     * Sets (as xml) the "pais" element
     */
    public void xsetPais(org.apache.xmlbeans.XmlString pais)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PAIS$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PAIS$10);
            }
            target.set(pais);
        }
    }
}
