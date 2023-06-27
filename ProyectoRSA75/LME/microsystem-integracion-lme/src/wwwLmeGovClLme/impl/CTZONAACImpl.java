/*
 * XML Type:  CT_ZONA_AC
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAAC
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CT_ZONA_AC(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTZONAACImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTZONAAC
{
    private static final long serialVersionUID = 1L;
    
    public CTZONAACImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CODDIAGNOSTICOPRINCIPAL$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "coddiagnostico_principal");
    private static final javax.xml.namespace.QName DIAGNOSTICOSECUNDARIO$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "diagnostico_secundario");
    private static final javax.xml.namespace.QName CODDIAGNOSTICOSECUNDARIO$4 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "coddiagnostico_secundario");
    private static final javax.xml.namespace.QName CODDIAGNOSTICOOTRO$6 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "coddiagnostico_otro");
    private static final javax.xml.namespace.QName EMAILTRABAJADOR$8 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "email_trabajador");
    private static final javax.xml.namespace.QName CANALCONTACTO$10 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "canal_contacto");
    private static final javax.xml.namespace.QName CELULARCONTACTO$12 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "celular_contacto");
    private static final javax.xml.namespace.QName DIRECCIONCONTACTO$14 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "direccion_contacto");
    private static final javax.xml.namespace.QName ID$16 = 
        new javax.xml.namespace.QName("", "id");
    
    
    /**
     * Gets the "coddiagnostico_principal" element
     */
    public java.lang.String getCoddiagnosticoPrincipal()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODDIAGNOSTICOPRINCIPAL$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "coddiagnostico_principal" element
     */
    public org.apache.xmlbeans.XmlString xgetCoddiagnosticoPrincipal()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODDIAGNOSTICOPRINCIPAL$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "coddiagnostico_principal" element
     */
    public void setCoddiagnosticoPrincipal(java.lang.String coddiagnosticoPrincipal)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODDIAGNOSTICOPRINCIPAL$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODDIAGNOSTICOPRINCIPAL$0);
            }
            target.setStringValue(coddiagnosticoPrincipal);
        }
    }
    
    /**
     * Sets (as xml) the "coddiagnostico_principal" element
     */
    public void xsetCoddiagnosticoPrincipal(org.apache.xmlbeans.XmlString coddiagnosticoPrincipal)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODDIAGNOSTICOPRINCIPAL$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CODDIAGNOSTICOPRINCIPAL$0);
            }
            target.set(coddiagnosticoPrincipal);
        }
    }
    
    /**
     * Gets the "diagnostico_secundario" element
     */
    public java.lang.String getDiagnosticoSecundario()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DIAGNOSTICOSECUNDARIO$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "diagnostico_secundario" element
     */
    public org.apache.xmlbeans.XmlString xgetDiagnosticoSecundario()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DIAGNOSTICOSECUNDARIO$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "diagnostico_secundario" element
     */
    public boolean isSetDiagnosticoSecundario()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DIAGNOSTICOSECUNDARIO$2) != 0;
        }
    }
    
    /**
     * Sets the "diagnostico_secundario" element
     */
    public void setDiagnosticoSecundario(java.lang.String diagnosticoSecundario)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DIAGNOSTICOSECUNDARIO$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DIAGNOSTICOSECUNDARIO$2);
            }
            target.setStringValue(diagnosticoSecundario);
        }
    }
    
    /**
     * Sets (as xml) the "diagnostico_secundario" element
     */
    public void xsetDiagnosticoSecundario(org.apache.xmlbeans.XmlString diagnosticoSecundario)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DIAGNOSTICOSECUNDARIO$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DIAGNOSTICOSECUNDARIO$2);
            }
            target.set(diagnosticoSecundario);
        }
    }
    
    /**
     * Unsets the "diagnostico_secundario" element
     */
    public void unsetDiagnosticoSecundario()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DIAGNOSTICOSECUNDARIO$2, 0);
        }
    }
    
    /**
     * Gets the "coddiagnostico_secundario" element
     */
    public java.lang.String getCoddiagnosticoSecundario()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODDIAGNOSTICOSECUNDARIO$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "coddiagnostico_secundario" element
     */
    public org.apache.xmlbeans.XmlString xgetCoddiagnosticoSecundario()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODDIAGNOSTICOSECUNDARIO$4, 0);
            return target;
        }
    }
    
    /**
     * True if has "coddiagnostico_secundario" element
     */
    public boolean isSetCoddiagnosticoSecundario()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CODDIAGNOSTICOSECUNDARIO$4) != 0;
        }
    }
    
    /**
     * Sets the "coddiagnostico_secundario" element
     */
    public void setCoddiagnosticoSecundario(java.lang.String coddiagnosticoSecundario)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODDIAGNOSTICOSECUNDARIO$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODDIAGNOSTICOSECUNDARIO$4);
            }
            target.setStringValue(coddiagnosticoSecundario);
        }
    }
    
    /**
     * Sets (as xml) the "coddiagnostico_secundario" element
     */
    public void xsetCoddiagnosticoSecundario(org.apache.xmlbeans.XmlString coddiagnosticoSecundario)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODDIAGNOSTICOSECUNDARIO$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CODDIAGNOSTICOSECUNDARIO$4);
            }
            target.set(coddiagnosticoSecundario);
        }
    }
    
    /**
     * Unsets the "coddiagnostico_secundario" element
     */
    public void unsetCoddiagnosticoSecundario()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CODDIAGNOSTICOSECUNDARIO$4, 0);
        }
    }
    
    /**
     * Gets the "coddiagnostico_otro" element
     */
    public java.lang.String getCoddiagnosticoOtro()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODDIAGNOSTICOOTRO$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "coddiagnostico_otro" element
     */
    public org.apache.xmlbeans.XmlString xgetCoddiagnosticoOtro()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODDIAGNOSTICOOTRO$6, 0);
            return target;
        }
    }
    
    /**
     * True if has "coddiagnostico_otro" element
     */
    public boolean isSetCoddiagnosticoOtro()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CODDIAGNOSTICOOTRO$6) != 0;
        }
    }
    
    /**
     * Sets the "coddiagnostico_otro" element
     */
    public void setCoddiagnosticoOtro(java.lang.String coddiagnosticoOtro)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODDIAGNOSTICOOTRO$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODDIAGNOSTICOOTRO$6);
            }
            target.setStringValue(coddiagnosticoOtro);
        }
    }
    
    /**
     * Sets (as xml) the "coddiagnostico_otro" element
     */
    public void xsetCoddiagnosticoOtro(org.apache.xmlbeans.XmlString coddiagnosticoOtro)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODDIAGNOSTICOOTRO$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CODDIAGNOSTICOOTRO$6);
            }
            target.set(coddiagnosticoOtro);
        }
    }
    
    /**
     * Unsets the "coddiagnostico_otro" element
     */
    public void unsetCoddiagnosticoOtro()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CODDIAGNOSTICOOTRO$6, 0);
        }
    }
    
    /**
     * Gets the "email_trabajador" element
     */
    public java.lang.String getEmailTrabajador()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EMAILTRABAJADOR$8, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "email_trabajador" element
     */
    public wwwLmeGovClLme.STEmail xgetEmailTrabajador()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STEmail target = null;
            target = (wwwLmeGovClLme.STEmail)get_store().find_element_user(EMAILTRABAJADOR$8, 0);
            return target;
        }
    }
    
    /**
     * True if has "email_trabajador" element
     */
    public boolean isSetEmailTrabajador()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(EMAILTRABAJADOR$8) != 0;
        }
    }
    
    /**
     * Sets the "email_trabajador" element
     */
    public void setEmailTrabajador(java.lang.String emailTrabajador)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EMAILTRABAJADOR$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EMAILTRABAJADOR$8);
            }
            target.setStringValue(emailTrabajador);
        }
    }
    
    /**
     * Sets (as xml) the "email_trabajador" element
     */
    public void xsetEmailTrabajador(wwwLmeGovClLme.STEmail emailTrabajador)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STEmail target = null;
            target = (wwwLmeGovClLme.STEmail)get_store().find_element_user(EMAILTRABAJADOR$8, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STEmail)get_store().add_element_user(EMAILTRABAJADOR$8);
            }
            target.set(emailTrabajador);
        }
    }
    
    /**
     * Unsets the "email_trabajador" element
     */
    public void unsetEmailTrabajador()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(EMAILTRABAJADOR$8, 0);
        }
    }
    
    /**
     * Gets the "canal_contacto" element
     */
    public java.math.BigInteger getCanalContacto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CANALCONTACTO$10, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "canal_contacto" element
     */
    public wwwLmeGovClLme.STContacto xgetCanalContacto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STContacto target = null;
            target = (wwwLmeGovClLme.STContacto)get_store().find_element_user(CANALCONTACTO$10, 0);
            return target;
        }
    }
    
    /**
     * True if has "canal_contacto" element
     */
    public boolean isSetCanalContacto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CANALCONTACTO$10) != 0;
        }
    }
    
    /**
     * Sets the "canal_contacto" element
     */
    public void setCanalContacto(java.math.BigInteger canalContacto)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CANALCONTACTO$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CANALCONTACTO$10);
            }
            target.setBigIntegerValue(canalContacto);
        }
    }
    
    /**
     * Sets (as xml) the "canal_contacto" element
     */
    public void xsetCanalContacto(wwwLmeGovClLme.STContacto canalContacto)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STContacto target = null;
            target = (wwwLmeGovClLme.STContacto)get_store().find_element_user(CANALCONTACTO$10, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STContacto)get_store().add_element_user(CANALCONTACTO$10);
            }
            target.set(canalContacto);
        }
    }
    
    /**
     * Unsets the "canal_contacto" element
     */
    public void unsetCanalContacto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CANALCONTACTO$10, 0);
        }
    }
    
    /**
     * Gets the "celular_contacto" element
     */
    public java.math.BigInteger getCelularContacto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CELULARCONTACTO$12, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "celular_contacto" element
     */
    public org.apache.xmlbeans.XmlInteger xgetCelularContacto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(CELULARCONTACTO$12, 0);
            return target;
        }
    }
    
    /**
     * True if has "celular_contacto" element
     */
    public boolean isSetCelularContacto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CELULARCONTACTO$12) != 0;
        }
    }
    
    /**
     * Sets the "celular_contacto" element
     */
    public void setCelularContacto(java.math.BigInteger celularContacto)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CELULARCONTACTO$12, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CELULARCONTACTO$12);
            }
            target.setBigIntegerValue(celularContacto);
        }
    }
    
    /**
     * Sets (as xml) the "celular_contacto" element
     */
    public void xsetCelularContacto(org.apache.xmlbeans.XmlInteger celularContacto)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(CELULARCONTACTO$12, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(CELULARCONTACTO$12);
            }
            target.set(celularContacto);
        }
    }
    
    /**
     * Unsets the "celular_contacto" element
     */
    public void unsetCelularContacto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CELULARCONTACTO$12, 0);
        }
    }
    
    /**
     * Gets the "direccion_contacto" element
     */
    public wwwLmeGovClLme.CTDireccion getDireccionContacto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDireccion target = null;
            target = (wwwLmeGovClLme.CTDireccion)get_store().find_element_user(DIRECCIONCONTACTO$14, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "direccion_contacto" element
     */
    public boolean isSetDireccionContacto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DIRECCIONCONTACTO$14) != 0;
        }
    }
    
    /**
     * Sets the "direccion_contacto" element
     */
    public void setDireccionContacto(wwwLmeGovClLme.CTDireccion direccionContacto)
    {
        generatedSetterHelperImpl(direccionContacto, DIRECCIONCONTACTO$14, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "direccion_contacto" element
     */
    public wwwLmeGovClLme.CTDireccion addNewDireccionContacto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDireccion target = null;
            target = (wwwLmeGovClLme.CTDireccion)get_store().add_element_user(DIRECCIONCONTACTO$14);
            return target;
        }
    }
    
    /**
     * Unsets the "direccion_contacto" element
     */
    public void unsetDireccionContacto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DIRECCIONCONTACTO$14, 0);
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
