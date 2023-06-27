/*
 * XML Type:  CT_ZONA_B
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAB
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CT_ZONA_B(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTZONABImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTZONAB
{
    private static final long serialVersionUID = 1L;
    
    public CTZONABImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ZONAB1$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "ZONA_B1");
    private static final javax.xml.namespace.QName ZONABF$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "ZONA_BF");
    
    
    /**
     * Gets the "ZONA_B1" element
     */
    public wwwLmeGovClLme.CTZONAB1 getZONAB1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTZONAB1 target = null;
            target = (wwwLmeGovClLme.CTZONAB1)get_store().find_element_user(ZONAB1$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ZONA_B1" element
     */
    public void setZONAB1(wwwLmeGovClLme.CTZONAB1 zonab1)
    {
        generatedSetterHelperImpl(zonab1, ZONAB1$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "ZONA_B1" element
     */
    public wwwLmeGovClLme.CTZONAB1 addNewZONAB1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTZONAB1 target = null;
            target = (wwwLmeGovClLme.CTZONAB1)get_store().add_element_user(ZONAB1$0);
            return target;
        }
    }
    
    /**
     * Gets the "ZONA_BF" element
     */
    public wwwLmeGovClLme.CTZONAF getZONABF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTZONAF target = null;
            target = (wwwLmeGovClLme.CTZONAF)get_store().find_element_user(ZONABF$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ZONA_BF" element
     */
    public void setZONABF(wwwLmeGovClLme.CTZONAF zonabf)
    {
        generatedSetterHelperImpl(zonabf, ZONABF$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "ZONA_BF" element
     */
    public wwwLmeGovClLme.CTZONAF addNewZONABF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTZONAF target = null;
            target = (wwwLmeGovClLme.CTZONAF)get_store().add_element_user(ZONABF$2);
            return target;
        }
    }
}
