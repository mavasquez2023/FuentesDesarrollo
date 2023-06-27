/*
 * XML Type:  CTLugar_reposo
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTLugarReposo
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CTLugar_reposo(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTLugarReposoImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTLugarReposo
{
    private static final long serialVersionUID = 1L;
    
    public CTLugarReposoImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CODIGOLUGARREPOSO$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "codigo_lugar_reposo");
    private static final javax.xml.namespace.QName DIRECCIONREPOSO$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "direccion_reposo");
    private static final javax.xml.namespace.QName JUSTIFICADOMICILIO$4 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "justifica_domicilio");
    
    
    /**
     * Gets the "codigo_lugar_reposo" element
     */
    public java.math.BigInteger getCodigoLugarReposo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOLUGARREPOSO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "codigo_lugar_reposo" element
     */
    public wwwLmeGovClLme.STTipoLugarReposo xgetCodigoLugarReposo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STTipoLugarReposo target = null;
            target = (wwwLmeGovClLme.STTipoLugarReposo)get_store().find_element_user(CODIGOLUGARREPOSO$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "codigo_lugar_reposo" element
     */
    public void setCodigoLugarReposo(java.math.BigInteger codigoLugarReposo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOLUGARREPOSO$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODIGOLUGARREPOSO$0);
            }
            target.setBigIntegerValue(codigoLugarReposo);
        }
    }
    
    /**
     * Sets (as xml) the "codigo_lugar_reposo" element
     */
    public void xsetCodigoLugarReposo(wwwLmeGovClLme.STTipoLugarReposo codigoLugarReposo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STTipoLugarReposo target = null;
            target = (wwwLmeGovClLme.STTipoLugarReposo)get_store().find_element_user(CODIGOLUGARREPOSO$0, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STTipoLugarReposo)get_store().add_element_user(CODIGOLUGARREPOSO$0);
            }
            target.set(codigoLugarReposo);
        }
    }
    
    /**
     * Gets the "direccion_reposo" element
     */
    public wwwLmeGovClLme.CTDireccion getDireccionReposo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDireccion target = null;
            target = (wwwLmeGovClLme.CTDireccion)get_store().find_element_user(DIRECCIONREPOSO$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "direccion_reposo" element
     */
    public void setDireccionReposo(wwwLmeGovClLme.CTDireccion direccionReposo)
    {
        generatedSetterHelperImpl(direccionReposo, DIRECCIONREPOSO$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "direccion_reposo" element
     */
    public wwwLmeGovClLme.CTDireccion addNewDireccionReposo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDireccion target = null;
            target = (wwwLmeGovClLme.CTDireccion)get_store().add_element_user(DIRECCIONREPOSO$2);
            return target;
        }
    }
    
    /**
     * Gets the "justifica_domicilio" element
     */
    public java.lang.String getJustificaDomicilio()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(JUSTIFICADOMICILIO$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "justifica_domicilio" element
     */
    public org.apache.xmlbeans.XmlString xgetJustificaDomicilio()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(JUSTIFICADOMICILIO$4, 0);
            return target;
        }
    }
    
    /**
     * True if has "justifica_domicilio" element
     */
    public boolean isSetJustificaDomicilio()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(JUSTIFICADOMICILIO$4) != 0;
        }
    }
    
    /**
     * Sets the "justifica_domicilio" element
     */
    public void setJustificaDomicilio(java.lang.String justificaDomicilio)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(JUSTIFICADOMICILIO$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(JUSTIFICADOMICILIO$4);
            }
            target.setStringValue(justificaDomicilio);
        }
    }
    
    /**
     * Sets (as xml) the "justifica_domicilio" element
     */
    public void xsetJustificaDomicilio(org.apache.xmlbeans.XmlString justificaDomicilio)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(JUSTIFICADOMICILIO$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(JUSTIFICADOMICILIO$4);
            }
            target.set(justificaDomicilio);
        }
    }
    
    /**
     * Unsets the "justifica_domicilio" element
     */
    public void unsetJustificaDomicilio()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(JUSTIFICADOMICILIO$4, 0);
        }
    }
}
