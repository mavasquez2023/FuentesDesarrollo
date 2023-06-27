/*
 * XML Type:  CT_ZONA_0
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONA0
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CT_ZONA_0(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTZONA0Impl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTZONA0
{
    private static final long serialVersionUID = 1L;
    
    public CTZONA0Impl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ZONA01$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "ZONA_01");
    private static final javax.xml.namespace.QName ZONA0F$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "ZONA_0F");
    
    
    /**
     * Gets the "ZONA_01" element
     */
    public wwwLmeGovClLme.CTZONA01 getZONA01()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTZONA01 target = null;
            target = (wwwLmeGovClLme.CTZONA01)get_store().find_element_user(ZONA01$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ZONA_01" element
     */
    public void setZONA01(wwwLmeGovClLme.CTZONA01 zona01)
    {
        generatedSetterHelperImpl(zona01, ZONA01$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "ZONA_01" element
     */
    public wwwLmeGovClLme.CTZONA01 addNewZONA01()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTZONA01 target = null;
            target = (wwwLmeGovClLme.CTZONA01)get_store().add_element_user(ZONA01$0);
            return target;
        }
    }
    
    /**
     * Gets the "ZONA_0F" element
     */
    public wwwLmeGovClLme.CTZONAF getZONA0F()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTZONAF target = null;
            target = (wwwLmeGovClLme.CTZONAF)get_store().find_element_user(ZONA0F$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ZONA_0F" element
     */
    public void setZONA0F(wwwLmeGovClLme.CTZONAF zona0F)
    {
        generatedSetterHelperImpl(zona0F, ZONA0F$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "ZONA_0F" element
     */
    public wwwLmeGovClLme.CTZONAF addNewZONA0F()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTZONAF target = null;
            target = (wwwLmeGovClLme.CTZONAF)get_store().add_element_user(ZONA0F$2);
            return target;
        }
    }
}
