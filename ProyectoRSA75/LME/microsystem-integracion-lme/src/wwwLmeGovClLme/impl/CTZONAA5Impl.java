/*
 * XML Type:  CT_ZONA_A5
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAA5
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CT_ZONA_A5(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTZONAA5Impl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTZONAA5
{
    private static final long serialVersionUID = 1L;
    
    public CTZONAA5Impl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PROFESIONAL$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "profesional");
    private static final javax.xml.namespace.QName PROFESPECIALIDAD$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "prof_especialidad");
    private static final javax.xml.namespace.QName CODIGOTIPOPROFESIONAL$4 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "codigo_tipo_profesional");
    private static final javax.xml.namespace.QName PROFREGISTROCOLEGIO$6 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "prof_registro_colegio");
    private static final javax.xml.namespace.QName PROFEMAIL$8 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "prof_email");
    private static final javax.xml.namespace.QName PROFTELEFONO$10 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "prof_telefono");
    private static final javax.xml.namespace.QName PROFDIRECCION$12 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "prof_direccion");
    private static final javax.xml.namespace.QName PROFFAX$14 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "prof_fax");
    private static final javax.xml.namespace.QName ID$16 = 
        new javax.xml.namespace.QName("", "id");
    
    
    /**
     * Gets the "profesional" element
     */
    public wwwLmeGovClLme.CTPersona getProfesional()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTPersona target = null;
            target = (wwwLmeGovClLme.CTPersona)get_store().find_element_user(PROFESIONAL$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "profesional" element
     */
    public void setProfesional(wwwLmeGovClLme.CTPersona profesional)
    {
        generatedSetterHelperImpl(profesional, PROFESIONAL$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "profesional" element
     */
    public wwwLmeGovClLme.CTPersona addNewProfesional()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTPersona target = null;
            target = (wwwLmeGovClLme.CTPersona)get_store().add_element_user(PROFESIONAL$0);
            return target;
        }
    }
    
    /**
     * Gets the "prof_especialidad" element
     */
    public java.lang.String getProfEspecialidad()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PROFESPECIALIDAD$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "prof_especialidad" element
     */
    public org.apache.xmlbeans.XmlString xgetProfEspecialidad()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PROFESPECIALIDAD$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "prof_especialidad" element
     */
    public void setProfEspecialidad(java.lang.String profEspecialidad)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PROFESPECIALIDAD$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PROFESPECIALIDAD$2);
            }
            target.setStringValue(profEspecialidad);
        }
    }
    
    /**
     * Sets (as xml) the "prof_especialidad" element
     */
    public void xsetProfEspecialidad(org.apache.xmlbeans.XmlString profEspecialidad)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PROFESPECIALIDAD$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PROFESPECIALIDAD$2);
            }
            target.set(profEspecialidad);
        }
    }
    
    /**
     * Gets the "codigo_tipo_profesional" element
     */
    public java.math.BigInteger getCodigoTipoProfesional()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOTIPOPROFESIONAL$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "codigo_tipo_profesional" element
     */
    public wwwLmeGovClLme.STTipoProfesional xgetCodigoTipoProfesional()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STTipoProfesional target = null;
            target = (wwwLmeGovClLme.STTipoProfesional)get_store().find_element_user(CODIGOTIPOPROFESIONAL$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "codigo_tipo_profesional" element
     */
    public void setCodigoTipoProfesional(java.math.BigInteger codigoTipoProfesional)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOTIPOPROFESIONAL$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODIGOTIPOPROFESIONAL$4);
            }
            target.setBigIntegerValue(codigoTipoProfesional);
        }
    }
    
    /**
     * Sets (as xml) the "codigo_tipo_profesional" element
     */
    public void xsetCodigoTipoProfesional(wwwLmeGovClLme.STTipoProfesional codigoTipoProfesional)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STTipoProfesional target = null;
            target = (wwwLmeGovClLme.STTipoProfesional)get_store().find_element_user(CODIGOTIPOPROFESIONAL$4, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STTipoProfesional)get_store().add_element_user(CODIGOTIPOPROFESIONAL$4);
            }
            target.set(codigoTipoProfesional);
        }
    }
    
    /**
     * Gets the "prof_registro_colegio" element
     */
    public java.lang.String getProfRegistroColegio()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PROFREGISTROCOLEGIO$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "prof_registro_colegio" element
     */
    public org.apache.xmlbeans.XmlString xgetProfRegistroColegio()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PROFREGISTROCOLEGIO$6, 0);
            return target;
        }
    }
    
    /**
     * True if has "prof_registro_colegio" element
     */
    public boolean isSetProfRegistroColegio()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(PROFREGISTROCOLEGIO$6) != 0;
        }
    }
    
    /**
     * Sets the "prof_registro_colegio" element
     */
    public void setProfRegistroColegio(java.lang.String profRegistroColegio)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PROFREGISTROCOLEGIO$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PROFREGISTROCOLEGIO$6);
            }
            target.setStringValue(profRegistroColegio);
        }
    }
    
    /**
     * Sets (as xml) the "prof_registro_colegio" element
     */
    public void xsetProfRegistroColegio(org.apache.xmlbeans.XmlString profRegistroColegio)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PROFREGISTROCOLEGIO$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PROFREGISTROCOLEGIO$6);
            }
            target.set(profRegistroColegio);
        }
    }
    
    /**
     * Unsets the "prof_registro_colegio" element
     */
    public void unsetProfRegistroColegio()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(PROFREGISTROCOLEGIO$6, 0);
        }
    }
    
    /**
     * Gets the "prof_email" element
     */
    public java.lang.String getProfEmail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PROFEMAIL$8, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "prof_email" element
     */
    public wwwLmeGovClLme.STEmail xgetProfEmail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STEmail target = null;
            target = (wwwLmeGovClLme.STEmail)get_store().find_element_user(PROFEMAIL$8, 0);
            return target;
        }
    }
    
    /**
     * True if has "prof_email" element
     */
    public boolean isSetProfEmail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(PROFEMAIL$8) != 0;
        }
    }
    
    /**
     * Sets the "prof_email" element
     */
    public void setProfEmail(java.lang.String profEmail)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PROFEMAIL$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PROFEMAIL$8);
            }
            target.setStringValue(profEmail);
        }
    }
    
    /**
     * Sets (as xml) the "prof_email" element
     */
    public void xsetProfEmail(wwwLmeGovClLme.STEmail profEmail)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STEmail target = null;
            target = (wwwLmeGovClLme.STEmail)get_store().find_element_user(PROFEMAIL$8, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STEmail)get_store().add_element_user(PROFEMAIL$8);
            }
            target.set(profEmail);
        }
    }
    
    /**
     * Unsets the "prof_email" element
     */
    public void unsetProfEmail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(PROFEMAIL$8, 0);
        }
    }
    
    /**
     * Gets the "prof_telefono" element
     */
    public wwwLmeGovClLme.CTTelefono getProfTelefono()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTTelefono target = null;
            target = (wwwLmeGovClLme.CTTelefono)get_store().find_element_user(PROFTELEFONO$10, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "prof_telefono" element
     */
    public boolean isSetProfTelefono()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(PROFTELEFONO$10) != 0;
        }
    }
    
    /**
     * Sets the "prof_telefono" element
     */
    public void setProfTelefono(wwwLmeGovClLme.CTTelefono profTelefono)
    {
        generatedSetterHelperImpl(profTelefono, PROFTELEFONO$10, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "prof_telefono" element
     */
    public wwwLmeGovClLme.CTTelefono addNewProfTelefono()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTTelefono target = null;
            target = (wwwLmeGovClLme.CTTelefono)get_store().add_element_user(PROFTELEFONO$10);
            return target;
        }
    }
    
    /**
     * Unsets the "prof_telefono" element
     */
    public void unsetProfTelefono()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(PROFTELEFONO$10, 0);
        }
    }
    
    /**
     * Gets the "prof_direccion" element
     */
    public wwwLmeGovClLme.CTDireccion getProfDireccion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDireccion target = null;
            target = (wwwLmeGovClLme.CTDireccion)get_store().find_element_user(PROFDIRECCION$12, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "prof_direccion" element
     */
    public void setProfDireccion(wwwLmeGovClLme.CTDireccion profDireccion)
    {
        generatedSetterHelperImpl(profDireccion, PROFDIRECCION$12, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "prof_direccion" element
     */
    public wwwLmeGovClLme.CTDireccion addNewProfDireccion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDireccion target = null;
            target = (wwwLmeGovClLme.CTDireccion)get_store().add_element_user(PROFDIRECCION$12);
            return target;
        }
    }
    
    /**
     * Gets the "prof_fax" element
     */
    public wwwLmeGovClLme.CTTelefono getProfFax()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTTelefono target = null;
            target = (wwwLmeGovClLme.CTTelefono)get_store().find_element_user(PROFFAX$14, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "prof_fax" element
     */
    public boolean isSetProfFax()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(PROFFAX$14) != 0;
        }
    }
    
    /**
     * Sets the "prof_fax" element
     */
    public void setProfFax(wwwLmeGovClLme.CTTelefono profFax)
    {
        generatedSetterHelperImpl(profFax, PROFFAX$14, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "prof_fax" element
     */
    public wwwLmeGovClLme.CTTelefono addNewProfFax()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTTelefono target = null;
            target = (wwwLmeGovClLme.CTTelefono)get_store().add_element_user(PROFFAX$14);
            return target;
        }
    }
    
    /**
     * Unsets the "prof_fax" element
     */
    public void unsetProfFax()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(PROFFAX$14, 0);
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
            target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().find_attribute_user(ID$16);
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
            return get_store().find_attribute_user(ID$16) != null;
        }
    }
    
    /**
     * Sets the "id" attribute
     */
    public void setId(org.apache.xmlbeans.XmlAnySimpleType id)
    {
        generatedSetterHelperImpl(id, ID$16, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
            target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().add_attribute_user(ID$16);
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
            get_store().remove_attribute(ID$16);
        }
    }
}
