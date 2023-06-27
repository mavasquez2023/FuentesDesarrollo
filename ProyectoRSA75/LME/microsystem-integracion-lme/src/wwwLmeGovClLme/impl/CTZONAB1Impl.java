/*
 * XML Type:  CT_ZONA_B1
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAB1
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CT_ZONA_B1(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTZONAB1Impl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTZONAB1
{
    private static final long serialVersionUID = 1L;
    
    public CTZONAB1Impl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESOLUCION$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "resolucion");
    private static final javax.xml.namespace.QName ID$2 = 
        new javax.xml.namespace.QName("", "id");
    
    
    /**
     * Gets array of all "resolucion" elements
     */
    public wwwLmeGovClLme.CTResolucion[] getResolucionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(RESOLUCION$0, targetList);
            wwwLmeGovClLme.CTResolucion[] result = new wwwLmeGovClLme.CTResolucion[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "resolucion" element
     */
    public wwwLmeGovClLme.CTResolucion getResolucionArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTResolucion target = null;
            target = (wwwLmeGovClLme.CTResolucion)get_store().find_element_user(RESOLUCION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "resolucion" element
     */
    public int sizeOfResolucionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RESOLUCION$0);
        }
    }
    
    /**
     * Sets array of all "resolucion" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setResolucionArray(wwwLmeGovClLme.CTResolucion[] resolucionArray)
    {
        check_orphaned();
        arraySetterHelper(resolucionArray, RESOLUCION$0);
    }
    
    /**
     * Sets ith "resolucion" element
     */
    public void setResolucionArray(int i, wwwLmeGovClLme.CTResolucion resolucion)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTResolucion target = null;
            target = (wwwLmeGovClLme.CTResolucion)get_store().find_element_user(RESOLUCION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(resolucion);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "resolucion" element
     */
    public wwwLmeGovClLme.CTResolucion insertNewResolucion(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTResolucion target = null;
            target = (wwwLmeGovClLme.CTResolucion)get_store().insert_element_user(RESOLUCION$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "resolucion" element
     */
    public wwwLmeGovClLme.CTResolucion addNewResolucion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTResolucion target = null;
            target = (wwwLmeGovClLme.CTResolucion)get_store().add_element_user(RESOLUCION$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "resolucion" element
     */
    public void removeResolucion(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RESOLUCION$0, i);
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
