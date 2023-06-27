/*
 * XML Type:  CT_ZONA_CC
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONACC
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CT_ZONA_CC(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTZONACCImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTZONACC
{
    private static final long serialVersionUID = 1L;
    
    public CTZONACCImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CODIGOTRAMITACIONCCAF$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "codigo_tramitacion_CCAF");
    private static final javax.xml.namespace.QName TIENEMAS100$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "tiene_mas100");
    private static final javax.xml.namespace.QName HABERES$4 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "haberes");
    private static final javax.xml.namespace.QName ID$6 = 
        new javax.xml.namespace.QName("", "id");
    
    
    /**
     * Gets the "codigo_tramitacion_CCAF" element
     */
    public java.math.BigInteger getCodigoTramitacionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOTRAMITACIONCCAF$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "codigo_tramitacion_CCAF" element
     */
    public wwwLmeGovClLme.STCodigoCCAF xgetCodigoTramitacionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STCodigoCCAF target = null;
            target = (wwwLmeGovClLme.STCodigoCCAF)get_store().find_element_user(CODIGOTRAMITACIONCCAF$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "codigo_tramitacion_CCAF" element
     */
    public void setCodigoTramitacionCCAF(java.math.BigInteger codigoTramitacionCCAF)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOTRAMITACIONCCAF$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODIGOTRAMITACIONCCAF$0);
            }
            target.setBigIntegerValue(codigoTramitacionCCAF);
        }
    }
    
    /**
     * Sets (as xml) the "codigo_tramitacion_CCAF" element
     */
    public void xsetCodigoTramitacionCCAF(wwwLmeGovClLme.STCodigoCCAF codigoTramitacionCCAF)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STCodigoCCAF target = null;
            target = (wwwLmeGovClLme.STCodigoCCAF)get_store().find_element_user(CODIGOTRAMITACIONCCAF$0, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STCodigoCCAF)get_store().add_element_user(CODIGOTRAMITACIONCCAF$0);
            }
            target.set(codigoTramitacionCCAF);
        }
    }
    
    /**
     * Gets the "tiene_mas100" element
     */
    public java.math.BigInteger getTieneMas100()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIENEMAS100$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "tiene_mas100" element
     */
    public wwwLmeGovClLme.STSiNo xgetTieneMas100()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STSiNo target = null;
            target = (wwwLmeGovClLme.STSiNo)get_store().find_element_user(TIENEMAS100$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "tiene_mas100" element
     */
    public boolean isSetTieneMas100()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TIENEMAS100$2) != 0;
        }
    }
    
    /**
     * Sets the "tiene_mas100" element
     */
    public void setTieneMas100(java.math.BigInteger tieneMas100)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIENEMAS100$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIENEMAS100$2);
            }
            target.setBigIntegerValue(tieneMas100);
        }
    }
    
    /**
     * Sets (as xml) the "tiene_mas100" element
     */
    public void xsetTieneMas100(wwwLmeGovClLme.STSiNo tieneMas100)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STSiNo target = null;
            target = (wwwLmeGovClLme.STSiNo)get_store().find_element_user(TIENEMAS100$2, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STSiNo)get_store().add_element_user(TIENEMAS100$2);
            }
            target.set(tieneMas100);
        }
    }
    
    /**
     * Unsets the "tiene_mas100" element
     */
    public void unsetTieneMas100()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TIENEMAS100$2, 0);
        }
    }
    
    /**
     * Gets the "haberes" element
     */
    public wwwLmeGovClLme.CTHaberes getHaberes()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTHaberes target = null;
            target = (wwwLmeGovClLme.CTHaberes)get_store().find_element_user(HABERES$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "haberes" element
     */
    public void setHaberes(wwwLmeGovClLme.CTHaberes haberes)
    {
        generatedSetterHelperImpl(haberes, HABERES$4, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "haberes" element
     */
    public wwwLmeGovClLme.CTHaberes addNewHaberes()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTHaberes target = null;
            target = (wwwLmeGovClLme.CTHaberes)get_store().add_element_user(HABERES$4);
            return target;
        }
    }
    
    /**
     * Gets the "id" attribute
     */
    public org.apache.xmlbeans.XmlAnySimpleType getId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnySimpleType target = null;
            target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().find_attribute_user(ID$6);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "id" attribute
     */
    public boolean isSetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(ID$6) != null;
        }
    }
    
    /**
     * Sets the "id" attribute
     */
    public void setId(org.apache.xmlbeans.XmlAnySimpleType id)
    {
        generatedSetterHelperImpl(id, ID$6, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "id" attribute
     */
    public org.apache.xmlbeans.XmlAnySimpleType addNewId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnySimpleType target = null;
            target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().add_attribute_user(ID$6);
            return target;
        }
    }
    
    /**
     * Unsets the "id" attribute
     */
    public void unsetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(ID$6);
        }
    }
}
