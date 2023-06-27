/*
 * XML Type:  CT_ZONA_C4
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAC4
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CT_ZONA_C4(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTZONAC4Impl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTZONAC4
{
    private static final long serialVersionUID = 1L;
    
    public CTZONAC4Impl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName LMALICENCIASANT$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "lma_licencias_ant");
    private static final javax.xml.namespace.QName LICENCIAANTERIOR$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "licencia_anterior");
    private static final javax.xml.namespace.QName ID$4 = 
        new javax.xml.namespace.QName("", "id");
    
    
    /**
     * Gets the "lma_licencias_ant" element
     */
    public java.math.BigInteger getLmaLicenciasAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LMALICENCIASANT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "lma_licencias_ant" element
     */
    public wwwLmeGovClLme.STSiNo xgetLmaLicenciasAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STSiNo target = null;
            target = (wwwLmeGovClLme.STSiNo)get_store().find_element_user(LMALICENCIASANT$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "lma_licencias_ant" element
     */
    public void setLmaLicenciasAnt(java.math.BigInteger lmaLicenciasAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LMALICENCIASANT$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(LMALICENCIASANT$0);
            }
            target.setBigIntegerValue(lmaLicenciasAnt);
        }
    }
    
    /**
     * Sets (as xml) the "lma_licencias_ant" element
     */
    public void xsetLmaLicenciasAnt(wwwLmeGovClLme.STSiNo lmaLicenciasAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STSiNo target = null;
            target = (wwwLmeGovClLme.STSiNo)get_store().find_element_user(LMALICENCIASANT$0, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STSiNo)get_store().add_element_user(LMALICENCIASANT$0);
            }
            target.set(lmaLicenciasAnt);
        }
    }
    
    /**
     * Gets array of all "licencia_anterior" elements
     */
    public wwwLmeGovClLme.CTLicenciaAnterior[] getLicenciaAnteriorArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(LICENCIAANTERIOR$2, targetList);
            wwwLmeGovClLme.CTLicenciaAnterior[] result = new wwwLmeGovClLme.CTLicenciaAnterior[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "licencia_anterior" element
     */
    public wwwLmeGovClLme.CTLicenciaAnterior getLicenciaAnteriorArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTLicenciaAnterior target = null;
            target = (wwwLmeGovClLme.CTLicenciaAnterior)get_store().find_element_user(LICENCIAANTERIOR$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "licencia_anterior" element
     */
    public int sizeOfLicenciaAnteriorArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(LICENCIAANTERIOR$2);
        }
    }
    
    /**
     * Sets array of all "licencia_anterior" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setLicenciaAnteriorArray(wwwLmeGovClLme.CTLicenciaAnterior[] licenciaAnteriorArray)
    {
        check_orphaned();
        arraySetterHelper(licenciaAnteriorArray, LICENCIAANTERIOR$2);
    }
    
    /**
     * Sets ith "licencia_anterior" element
     */
    public void setLicenciaAnteriorArray(int i, wwwLmeGovClLme.CTLicenciaAnterior licenciaAnterior)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTLicenciaAnterior target = null;
            target = (wwwLmeGovClLme.CTLicenciaAnterior)get_store().find_element_user(LICENCIAANTERIOR$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(licenciaAnterior);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "licencia_anterior" element
     */
    public wwwLmeGovClLme.CTLicenciaAnterior insertNewLicenciaAnterior(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTLicenciaAnterior target = null;
            target = (wwwLmeGovClLme.CTLicenciaAnterior)get_store().insert_element_user(LICENCIAANTERIOR$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "licencia_anterior" element
     */
    public wwwLmeGovClLme.CTLicenciaAnterior addNewLicenciaAnterior()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTLicenciaAnterior target = null;
            target = (wwwLmeGovClLme.CTLicenciaAnterior)get_store().add_element_user(LICENCIAANTERIOR$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "licencia_anterior" element
     */
    public void removeLicenciaAnterior(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(LICENCIAANTERIOR$2, i);
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
            target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().find_attribute_user(ID$4);
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
            return get_store().find_attribute_user(ID$4) != null;
        }
    }
    
    /**
     * Sets the "id" attribute
     */
    public void setId(org.apache.xmlbeans.XmlAnySimpleType id)
    {
        generatedSetterHelperImpl(id, ID$4, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
            target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().add_attribute_user(ID$4);
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
            get_store().remove_attribute(ID$4);
        }
    }
}
