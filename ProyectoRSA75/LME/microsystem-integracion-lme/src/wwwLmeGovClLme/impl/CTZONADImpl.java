/*
 * XML Type:  CT_ZONA_D
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAD
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CT_ZONA_D(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTZONADImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTZONAD
{
    private static final long serialVersionUID = 1L;
    
    public CTZONADImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ZONAD1$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "ZONA_D1");
    private static final javax.xml.namespace.QName ZONADF$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "ZONA_DF");
    
    
    /**
     * Gets the "ZONA_D1" element
     */
    public wwwLmeGovClLme.CTZONAD1 getZONAD1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTZONAD1 target = null;
            target = (wwwLmeGovClLme.CTZONAD1)get_store().find_element_user(ZONAD1$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ZONA_D1" element
     */
    public void setZONAD1(wwwLmeGovClLme.CTZONAD1 zonad1)
    {
        generatedSetterHelperImpl(zonad1, ZONAD1$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "ZONA_D1" element
     */
    public wwwLmeGovClLme.CTZONAD1 addNewZONAD1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTZONAD1 target = null;
            target = (wwwLmeGovClLme.CTZONAD1)get_store().add_element_user(ZONAD1$0);
            return target;
        }
    }
    
    /**
     * Gets the "ZONA_DF" element
     */
    public wwwLmeGovClLme.CTZONAF getZONADF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTZONAF target = null;
            target = (wwwLmeGovClLme.CTZONAF)get_store().find_element_user(ZONADF$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ZONA_DF" element
     */
    public void setZONADF(wwwLmeGovClLme.CTZONAF zonadf)
    {
        generatedSetterHelperImpl(zonadf, ZONADF$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "ZONA_DF" element
     */
    public wwwLmeGovClLme.CTZONAF addNewZONADF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTZONAF target = null;
            target = (wwwLmeGovClLme.CTZONAF)get_store().add_element_user(ZONADF$2);
            return target;
        }
    }
}
