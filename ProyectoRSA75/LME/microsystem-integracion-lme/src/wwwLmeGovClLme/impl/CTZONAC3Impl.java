/*
 * XML Type:  CT_ZONA_C3
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAC3
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CT_ZONA_C3(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTZONAC3Impl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTZONAC3
{
    private static final long serialVersionUID = 1L;
    
    public CTZONAC3Impl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName REMUNERACION$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "remuneracion");
    private static final javax.xml.namespace.QName PORCENDESAHUCIO$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "porcen_desahucio");
    private static final javax.xml.namespace.QName MONTOIMPONIBLEMESANTERIOR$4 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "monto_imponible_mes_anterior");
    private static final javax.xml.namespace.QName ID$6 = 
        new javax.xml.namespace.QName("", "id");
    
    
    /**
     * Gets array of all "remuneracion" elements
     */
    public wwwLmeGovClLme.CTRemuneracion[] getRemuneracionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(REMUNERACION$0, targetList);
            wwwLmeGovClLme.CTRemuneracion[] result = new wwwLmeGovClLme.CTRemuneracion[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "remuneracion" element
     */
    public wwwLmeGovClLme.CTRemuneracion getRemuneracionArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTRemuneracion target = null;
            target = (wwwLmeGovClLme.CTRemuneracion)get_store().find_element_user(REMUNERACION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "remuneracion" element
     */
    public int sizeOfRemuneracionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(REMUNERACION$0);
        }
    }
    
    /**
     * Sets array of all "remuneracion" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setRemuneracionArray(wwwLmeGovClLme.CTRemuneracion[] remuneracionArray)
    {
        check_orphaned();
        arraySetterHelper(remuneracionArray, REMUNERACION$0);
    }
    
    /**
     * Sets ith "remuneracion" element
     */
    public void setRemuneracionArray(int i, wwwLmeGovClLme.CTRemuneracion remuneracion)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTRemuneracion target = null;
            target = (wwwLmeGovClLme.CTRemuneracion)get_store().find_element_user(REMUNERACION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(remuneracion);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "remuneracion" element
     */
    public wwwLmeGovClLme.CTRemuneracion insertNewRemuneracion(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTRemuneracion target = null;
            target = (wwwLmeGovClLme.CTRemuneracion)get_store().insert_element_user(REMUNERACION$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "remuneracion" element
     */
    public wwwLmeGovClLme.CTRemuneracion addNewRemuneracion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTRemuneracion target = null;
            target = (wwwLmeGovClLme.CTRemuneracion)get_store().add_element_user(REMUNERACION$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "remuneracion" element
     */
    public void removeRemuneracion(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(REMUNERACION$0, i);
        }
    }
    
    /**
     * Gets the "porcen_desahucio" element
     */
    public java.math.BigDecimal getPorcenDesahucio()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PORCENDESAHUCIO$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigDecimalValue();
        }
    }
    
    /**
     * Gets (as xml) the "porcen_desahucio" element
     */
    public org.apache.xmlbeans.XmlDecimal xgetPorcenDesahucio()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDecimal target = null;
            target = (org.apache.xmlbeans.XmlDecimal)get_store().find_element_user(PORCENDESAHUCIO$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "porcen_desahucio" element
     */
    public boolean isSetPorcenDesahucio()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(PORCENDESAHUCIO$2) != 0;
        }
    }
    
    /**
     * Sets the "porcen_desahucio" element
     */
    public void setPorcenDesahucio(java.math.BigDecimal porcenDesahucio)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PORCENDESAHUCIO$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PORCENDESAHUCIO$2);
            }
            target.setBigDecimalValue(porcenDesahucio);
        }
    }
    
    /**
     * Sets (as xml) the "porcen_desahucio" element
     */
    public void xsetPorcenDesahucio(org.apache.xmlbeans.XmlDecimal porcenDesahucio)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDecimal target = null;
            target = (org.apache.xmlbeans.XmlDecimal)get_store().find_element_user(PORCENDESAHUCIO$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDecimal)get_store().add_element_user(PORCENDESAHUCIO$2);
            }
            target.set(porcenDesahucio);
        }
    }
    
    /**
     * Unsets the "porcen_desahucio" element
     */
    public void unsetPorcenDesahucio()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(PORCENDESAHUCIO$2, 0);
        }
    }
    
    /**
     * Gets the "monto_imponible_mes_anterior" element
     */
    public java.math.BigInteger getMontoImponibleMesAnterior()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MONTOIMPONIBLEMESANTERIOR$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "monto_imponible_mes_anterior" element
     */
    public org.apache.xmlbeans.XmlInteger xgetMontoImponibleMesAnterior()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(MONTOIMPONIBLEMESANTERIOR$4, 0);
            return target;
        }
    }
    
    /**
     * True if has "monto_imponible_mes_anterior" element
     */
    public boolean isSetMontoImponibleMesAnterior()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(MONTOIMPONIBLEMESANTERIOR$4) != 0;
        }
    }
    
    /**
     * Sets the "monto_imponible_mes_anterior" element
     */
    public void setMontoImponibleMesAnterior(java.math.BigInteger montoImponibleMesAnterior)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MONTOIMPONIBLEMESANTERIOR$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MONTOIMPONIBLEMESANTERIOR$4);
            }
            target.setBigIntegerValue(montoImponibleMesAnterior);
        }
    }
    
    /**
     * Sets (as xml) the "monto_imponible_mes_anterior" element
     */
    public void xsetMontoImponibleMesAnterior(org.apache.xmlbeans.XmlInteger montoImponibleMesAnterior)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(MONTOIMPONIBLEMESANTERIOR$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(MONTOIMPONIBLEMESANTERIOR$4);
            }
            target.set(montoImponibleMesAnterior);
        }
    }
    
    /**
     * Unsets the "monto_imponible_mes_anterior" element
     */
    public void unsetMontoImponibleMesAnterior()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(MONTOIMPONIBLEMESANTERIOR$4, 0);
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
