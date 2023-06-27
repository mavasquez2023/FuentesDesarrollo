/*
 * XML Type:  CT_ZONA_A3
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTZONAA3
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CT_ZONA_A3(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTZONAA3Impl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTZONAA3
{
    private static final long serialVersionUID = 1L;
    
    public CTZONAA3Impl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CODIGOTIPOLICENCIA$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "codigo_tipo_licencia");
    private static final javax.xml.namespace.QName CODIGORECUPERABILIDAD$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "codigo_recuperabilidad");
    private static final javax.xml.namespace.QName CODIGOINICIOTRAMINV$4 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "codigo_inicio_tram_inv");
    private static final javax.xml.namespace.QName FECHAACCIDENTE$6 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "fecha_accidente");
    private static final javax.xml.namespace.QName CODIGOTRAYECTO$8 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "codigo_trayecto");
    private static final javax.xml.namespace.QName FECHACONCEPCION$10 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "fecha_concepcion");
    private static final javax.xml.namespace.QName ID$12 = 
        new javax.xml.namespace.QName("", "id");
    
    
    /**
     * Gets the "codigo_tipo_licencia" element
     */
    public java.math.BigInteger getCodigoTipoLicencia()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOTIPOLICENCIA$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "codigo_tipo_licencia" element
     */
    public wwwLmeGovClLme.STTipoLicencia xgetCodigoTipoLicencia()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STTipoLicencia target = null;
            target = (wwwLmeGovClLme.STTipoLicencia)get_store().find_element_user(CODIGOTIPOLICENCIA$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "codigo_tipo_licencia" element
     */
    public void setCodigoTipoLicencia(java.math.BigInteger codigoTipoLicencia)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOTIPOLICENCIA$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODIGOTIPOLICENCIA$0);
            }
            target.setBigIntegerValue(codigoTipoLicencia);
        }
    }
    
    /**
     * Sets (as xml) the "codigo_tipo_licencia" element
     */
    public void xsetCodigoTipoLicencia(wwwLmeGovClLme.STTipoLicencia codigoTipoLicencia)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STTipoLicencia target = null;
            target = (wwwLmeGovClLme.STTipoLicencia)get_store().find_element_user(CODIGOTIPOLICENCIA$0, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STTipoLicencia)get_store().add_element_user(CODIGOTIPOLICENCIA$0);
            }
            target.set(codigoTipoLicencia);
        }
    }
    
    /**
     * Gets the "codigo_recuperabilidad" element
     */
    public java.math.BigInteger getCodigoRecuperabilidad()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGORECUPERABILIDAD$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "codigo_recuperabilidad" element
     */
    public wwwLmeGovClLme.STSiNo xgetCodigoRecuperabilidad()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STSiNo target = null;
            target = (wwwLmeGovClLme.STSiNo)get_store().find_element_user(CODIGORECUPERABILIDAD$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "codigo_recuperabilidad" element
     */
    public void setCodigoRecuperabilidad(java.math.BigInteger codigoRecuperabilidad)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGORECUPERABILIDAD$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODIGORECUPERABILIDAD$2);
            }
            target.setBigIntegerValue(codigoRecuperabilidad);
        }
    }
    
    /**
     * Sets (as xml) the "codigo_recuperabilidad" element
     */
    public void xsetCodigoRecuperabilidad(wwwLmeGovClLme.STSiNo codigoRecuperabilidad)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STSiNo target = null;
            target = (wwwLmeGovClLme.STSiNo)get_store().find_element_user(CODIGORECUPERABILIDAD$2, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STSiNo)get_store().add_element_user(CODIGORECUPERABILIDAD$2);
            }
            target.set(codigoRecuperabilidad);
        }
    }
    
    /**
     * Gets the "codigo_inicio_tram_inv" element
     */
    public java.math.BigInteger getCodigoInicioTramInv()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOINICIOTRAMINV$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "codigo_inicio_tram_inv" element
     */
    public wwwLmeGovClLme.STSiNo xgetCodigoInicioTramInv()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STSiNo target = null;
            target = (wwwLmeGovClLme.STSiNo)get_store().find_element_user(CODIGOINICIOTRAMINV$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "codigo_inicio_tram_inv" element
     */
    public void setCodigoInicioTramInv(java.math.BigInteger codigoInicioTramInv)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOINICIOTRAMINV$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODIGOINICIOTRAMINV$4);
            }
            target.setBigIntegerValue(codigoInicioTramInv);
        }
    }
    
    /**
     * Sets (as xml) the "codigo_inicio_tram_inv" element
     */
    public void xsetCodigoInicioTramInv(wwwLmeGovClLme.STSiNo codigoInicioTramInv)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STSiNo target = null;
            target = (wwwLmeGovClLme.STSiNo)get_store().find_element_user(CODIGOINICIOTRAMINV$4, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STSiNo)get_store().add_element_user(CODIGOINICIOTRAMINV$4);
            }
            target.set(codigoInicioTramInv);
        }
    }
    
    /**
     * Gets the "fecha_accidente" element
     */
    public java.util.Calendar getFechaAccidente()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FECHAACCIDENTE$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "fecha_accidente" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetFechaAccidente()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(FECHAACCIDENTE$6, 0);
            return target;
        }
    }
    
    /**
     * True if has "fecha_accidente" element
     */
    public boolean isSetFechaAccidente()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(FECHAACCIDENTE$6) != 0;
        }
    }
    
    /**
     * Sets the "fecha_accidente" element
     */
    public void setFechaAccidente(java.util.Calendar fechaAccidente)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FECHAACCIDENTE$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FECHAACCIDENTE$6);
            }
            target.setCalendarValue(fechaAccidente);
        }
    }
    
    /**
     * Sets (as xml) the "fecha_accidente" element
     */
    public void xsetFechaAccidente(org.apache.xmlbeans.XmlDateTime fechaAccidente)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(FECHAACCIDENTE$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(FECHAACCIDENTE$6);
            }
            target.set(fechaAccidente);
        }
    }
    
    /**
     * Unsets the "fecha_accidente" element
     */
    public void unsetFechaAccidente()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(FECHAACCIDENTE$6, 0);
        }
    }
    
    /**
     * Gets the "codigo_trayecto" element
     */
    public java.math.BigInteger getCodigoTrayecto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOTRAYECTO$8, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "codigo_trayecto" element
     */
    public wwwLmeGovClLme.STSiNo xgetCodigoTrayecto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STSiNo target = null;
            target = (wwwLmeGovClLme.STSiNo)get_store().find_element_user(CODIGOTRAYECTO$8, 0);
            return target;
        }
    }
    
    /**
     * True if has "codigo_trayecto" element
     */
    public boolean isSetCodigoTrayecto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CODIGOTRAYECTO$8) != 0;
        }
    }
    
    /**
     * Sets the "codigo_trayecto" element
     */
    public void setCodigoTrayecto(java.math.BigInteger codigoTrayecto)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOTRAYECTO$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODIGOTRAYECTO$8);
            }
            target.setBigIntegerValue(codigoTrayecto);
        }
    }
    
    /**
     * Sets (as xml) the "codigo_trayecto" element
     */
    public void xsetCodigoTrayecto(wwwLmeGovClLme.STSiNo codigoTrayecto)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STSiNo target = null;
            target = (wwwLmeGovClLme.STSiNo)get_store().find_element_user(CODIGOTRAYECTO$8, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STSiNo)get_store().add_element_user(CODIGOTRAYECTO$8);
            }
            target.set(codigoTrayecto);
        }
    }
    
    /**
     * Unsets the "codigo_trayecto" element
     */
    public void unsetCodigoTrayecto()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CODIGOTRAYECTO$8, 0);
        }
    }
    
    /**
     * Gets the "fecha_concepcion" element
     */
    public java.util.Calendar getFechaConcepcion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FECHACONCEPCION$10, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "fecha_concepcion" element
     */
    public org.apache.xmlbeans.XmlDate xgetFechaConcepcion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDate target = null;
            target = (org.apache.xmlbeans.XmlDate)get_store().find_element_user(FECHACONCEPCION$10, 0);
            return target;
        }
    }
    
    /**
     * True if has "fecha_concepcion" element
     */
    public boolean isSetFechaConcepcion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(FECHACONCEPCION$10) != 0;
        }
    }
    
    /**
     * Sets the "fecha_concepcion" element
     */
    public void setFechaConcepcion(java.util.Calendar fechaConcepcion)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FECHACONCEPCION$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FECHACONCEPCION$10);
            }
            target.setCalendarValue(fechaConcepcion);
        }
    }
    
    /**
     * Sets (as xml) the "fecha_concepcion" element
     */
    public void xsetFechaConcepcion(org.apache.xmlbeans.XmlDate fechaConcepcion)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDate target = null;
            target = (org.apache.xmlbeans.XmlDate)get_store().find_element_user(FECHACONCEPCION$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDate)get_store().add_element_user(FECHACONCEPCION$10);
            }
            target.set(fechaConcepcion);
        }
    }
    
    /**
     * Unsets the "fecha_concepcion" element
     */
    public void unsetFechaConcepcion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(FECHACONCEPCION$10, 0);
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
            target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().find_attribute_user(ID$12);
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
            return get_store().find_attribute_user(ID$12) != null;
        }
    }
    
    /**
     * Sets the "id" attribute
     */
    public void setId(org.apache.xmlbeans.XmlAnySimpleType id)
    {
        generatedSetterHelperImpl(id, ID$12, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
            target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().add_attribute_user(ID$12);
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
            get_store().remove_attribute(ID$12);
        }
    }
}
