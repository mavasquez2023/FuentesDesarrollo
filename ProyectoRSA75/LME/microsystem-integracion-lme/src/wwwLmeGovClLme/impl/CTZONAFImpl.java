/*
 * XML Type:  CT_ZONA_F
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAF
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CT_ZONA_F(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTZONAFImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTZONAF
{
    private static final long serialVersionUID = 1L;
    
    public CTZONAFImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FIRMA$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "firma");
    private static final javax.xml.namespace.QName ID$2 = 
        new javax.xml.namespace.QName("", "id");
    
    
    /**
     * Gets array of all "firma" elements
     */
    public wwwLmeGovClLme.CTFirma[] getFirmaArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(FIRMA$0, targetList);
            wwwLmeGovClLme.CTFirma[] result = new wwwLmeGovClLme.CTFirma[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "firma" element
     */
    public wwwLmeGovClLme.CTFirma getFirmaArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTFirma target = null;
            target = (wwwLmeGovClLme.CTFirma)get_store().find_element_user(FIRMA$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "firma" element
     */
    public int sizeOfFirmaArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(FIRMA$0);
        }
    }
    
    /**
     * Sets array of all "firma" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setFirmaArray(wwwLmeGovClLme.CTFirma[] firmaArray)
    {
        check_orphaned();
        arraySetterHelper(firmaArray, FIRMA$0);
    }
    
    /**
     * Sets ith "firma" element
     */
    public void setFirmaArray(int i, wwwLmeGovClLme.CTFirma firma)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTFirma target = null;
            target = (wwwLmeGovClLme.CTFirma)get_store().find_element_user(FIRMA$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(firma);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "firma" element
     */
    public wwwLmeGovClLme.CTFirma insertNewFirma(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTFirma target = null;
            target = (wwwLmeGovClLme.CTFirma)get_store().insert_element_user(FIRMA$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "firma" element
     */
    public wwwLmeGovClLme.CTFirma addNewFirma()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTFirma target = null;
            target = (wwwLmeGovClLme.CTFirma)get_store().add_element_user(FIRMA$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "firma" element
     */
    public void removeFirma(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(FIRMA$0, i);
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
            target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().find_attribute_user(ID$2);
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
            return get_store().find_attribute_user(ID$2) != null;
        }
    }
    
    /**
     * Sets the "id" attribute
     */
    public void setId(org.apache.xmlbeans.XmlAnySimpleType id)
    {
        generatedSetterHelperImpl(id, ID$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
            target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().add_attribute_user(ID$2);
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
            get_store().remove_attribute(ID$2);
        }
    }
}
