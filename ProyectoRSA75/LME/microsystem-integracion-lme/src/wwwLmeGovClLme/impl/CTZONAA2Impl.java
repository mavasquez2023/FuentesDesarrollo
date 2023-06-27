/*
 * XML Type:  CT_ZONA_A2
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAA2
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CT_ZONA_A2(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTZONAA2Impl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTZONAA2
{
    private static final long serialVersionUID = 1L;
    
    public CTZONAA2Impl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName HIJO$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "hijo");
    private static final javax.xml.namespace.QName ID$2 = 
        new javax.xml.namespace.QName("", "id");
    
    
    /**
     * Gets the "hijo" element
     */
    public wwwLmeGovClLme.CTPersona getHijo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTPersona target = null;
            target = (wwwLmeGovClLme.CTPersona)get_store().find_element_user(HIJO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "hijo" element
     */
    public void setHijo(wwwLmeGovClLme.CTPersona hijo)
    {
        generatedSetterHelperImpl(hijo, HIJO$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "hijo" element
     */
    public wwwLmeGovClLme.CTPersona addNewHijo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTPersona target = null;
            target = (wwwLmeGovClLme.CTPersona)get_store().add_element_user(HIJO$0);
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
