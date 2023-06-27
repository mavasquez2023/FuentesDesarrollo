/*
 * XML Type:  CTPersona
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTPersona
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CTPersona(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTPersonaImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTPersona
{
    private static final long serialVersionUID = 1L;
    
    public CTPersonaImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName APELLIDOPATERNO$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "apellido_paterno");
    private static final javax.xml.namespace.QName APELLIDOMATERNO$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "apellido_materno");
    private static final javax.xml.namespace.QName NOMBRES$4 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "nombres");
    private static final javax.xml.namespace.QName RUT$6 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "rut");
    private static final javax.xml.namespace.QName FECHANACIMIENTO$8 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "fecha_nacimiento");
    private static final javax.xml.namespace.QName EDAD$10 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "edad");
    private static final javax.xml.namespace.QName SEXO$12 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "sexo");
    
    
    /**
     * Gets the "apellido_paterno" element
     */
    public java.lang.String getApellidoPaterno()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(APELLIDOPATERNO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "apellido_paterno" element
     */
    public org.apache.xmlbeans.XmlString xgetApellidoPaterno()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(APELLIDOPATERNO$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "apellido_paterno" element
     */
    public void setApellidoPaterno(java.lang.String apellidoPaterno)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(APELLIDOPATERNO$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(APELLIDOPATERNO$0);
            }
            target.setStringValue(apellidoPaterno);
        }
    }
    
    /**
     * Sets (as xml) the "apellido_paterno" element
     */
    public void xsetApellidoPaterno(org.apache.xmlbeans.XmlString apellidoPaterno)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(APELLIDOPATERNO$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(APELLIDOPATERNO$0);
            }
            target.set(apellidoPaterno);
        }
    }
    
    /**
     * Gets the "apellido_materno" element
     */
    public java.lang.String getApellidoMaterno()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(APELLIDOMATERNO$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "apellido_materno" element
     */
    public org.apache.xmlbeans.XmlString xgetApellidoMaterno()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(APELLIDOMATERNO$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "apellido_materno" element
     */
    public void setApellidoMaterno(java.lang.String apellidoMaterno)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(APELLIDOMATERNO$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(APELLIDOMATERNO$2);
            }
            target.setStringValue(apellidoMaterno);
        }
    }
    
    /**
     * Sets (as xml) the "apellido_materno" element
     */
    public void xsetApellidoMaterno(org.apache.xmlbeans.XmlString apellidoMaterno)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(APELLIDOMATERNO$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(APELLIDOMATERNO$2);
            }
            target.set(apellidoMaterno);
        }
    }
    
    /**
     * Gets the "nombres" element
     */
    public java.lang.String getNombres()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NOMBRES$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "nombres" element
     */
    public org.apache.xmlbeans.XmlString xgetNombres()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NOMBRES$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "nombres" element
     */
    public void setNombres(java.lang.String nombres)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NOMBRES$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NOMBRES$4);
            }
            target.setStringValue(nombres);
        }
    }
    
    /**
     * Sets (as xml) the "nombres" element
     */
    public void xsetNombres(org.apache.xmlbeans.XmlString nombres)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NOMBRES$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(NOMBRES$4);
            }
            target.set(nombres);
        }
    }
    
    /**
     * Gets the "rut" element
     */
    public java.lang.String getRut()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RUT$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "rut" element
     */
    public wwwLmeGovClLme.STRut xgetRut()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STRut target = null;
            target = (wwwLmeGovClLme.STRut)get_store().find_element_user(RUT$6, 0);
            return target;
        }
    }
    
    /**
     * Sets the "rut" element
     */
    public void setRut(java.lang.String rut)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RUT$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(RUT$6);
            }
            target.setStringValue(rut);
        }
    }
    
    /**
     * Sets (as xml) the "rut" element
     */
    public void xsetRut(wwwLmeGovClLme.STRut rut)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STRut target = null;
            target = (wwwLmeGovClLme.STRut)get_store().find_element_user(RUT$6, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STRut)get_store().add_element_user(RUT$6);
            }
            target.set(rut);
        }
    }
    
    /**
     * Gets the "fecha_nacimiento" element
     */
    public java.util.Calendar getFechaNacimiento()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FECHANACIMIENTO$8, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "fecha_nacimiento" element
     */
    public org.apache.xmlbeans.XmlDate xgetFechaNacimiento()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDate target = null;
            target = (org.apache.xmlbeans.XmlDate)get_store().find_element_user(FECHANACIMIENTO$8, 0);
            return target;
        }
    }
    
    /**
     * True if has "fecha_nacimiento" element
     */
    public boolean isSetFechaNacimiento()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(FECHANACIMIENTO$8) != 0;
        }
    }
    
    /**
     * Sets the "fecha_nacimiento" element
     */
    public void setFechaNacimiento(java.util.Calendar fechaNacimiento)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FECHANACIMIENTO$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FECHANACIMIENTO$8);
            }
            target.setCalendarValue(fechaNacimiento);
        }
    }
    
    /**
     * Sets (as xml) the "fecha_nacimiento" element
     */
    public void xsetFechaNacimiento(org.apache.xmlbeans.XmlDate fechaNacimiento)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDate target = null;
            target = (org.apache.xmlbeans.XmlDate)get_store().find_element_user(FECHANACIMIENTO$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDate)get_store().add_element_user(FECHANACIMIENTO$8);
            }
            target.set(fechaNacimiento);
        }
    }
    
    /**
     * Unsets the "fecha_nacimiento" element
     */
    public void unsetFechaNacimiento()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(FECHANACIMIENTO$8, 0);
        }
    }
    
    /**
     * Gets the "edad" element
     */
    public java.math.BigInteger getEdad()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EDAD$10, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "edad" element
     */
    public org.apache.xmlbeans.XmlInteger xgetEdad()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(EDAD$10, 0);
            return target;
        }
    }
    
    /**
     * True if has "edad" element
     */
    public boolean isSetEdad()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(EDAD$10) != 0;
        }
    }
    
    /**
     * Sets the "edad" element
     */
    public void setEdad(java.math.BigInteger edad)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EDAD$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EDAD$10);
            }
            target.setBigIntegerValue(edad);
        }
    }
    
    /**
     * Sets (as xml) the "edad" element
     */
    public void xsetEdad(org.apache.xmlbeans.XmlInteger edad)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(EDAD$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(EDAD$10);
            }
            target.set(edad);
        }
    }
    
    /**
     * Unsets the "edad" element
     */
    public void unsetEdad()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(EDAD$10, 0);
        }
    }
    
    /**
     * Gets the "sexo" element
     */
    public wwwLmeGovClLme.STSexo.Enum getSexo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SEXO$12, 0);
            if (target == null)
            {
                return null;
            }
            return (wwwLmeGovClLme.STSexo.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "sexo" element
     */
    public wwwLmeGovClLme.STSexo xgetSexo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STSexo target = null;
            target = (wwwLmeGovClLme.STSexo)get_store().find_element_user(SEXO$12, 0);
            return target;
        }
    }
    
    /**
     * True if has "sexo" element
     */
    public boolean isSetSexo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SEXO$12) != 0;
        }
    }
    
    /**
     * Sets the "sexo" element
     */
    public void setSexo(wwwLmeGovClLme.STSexo.Enum sexo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SEXO$12, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SEXO$12);
            }
            target.setEnumValue(sexo);
        }
    }
    
    /**
     * Sets (as xml) the "sexo" element
     */
    public void xsetSexo(wwwLmeGovClLme.STSexo sexo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STSexo target = null;
            target = (wwwLmeGovClLme.STSexo)get_store().find_element_user(SEXO$12, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STSexo)get_store().add_element_user(SEXO$12);
            }
            target.set(sexo);
        }
    }
    
    /**
     * Unsets the "sexo" element
     */
    public void unsetSexo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SEXO$12, 0);
        }
    }
}
