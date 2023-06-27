/*
 * XML Type:  CTRemuneracion
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTRemuneracion
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CTRemuneracion(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTRemuneracionImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTRemuneracion
{
    private static final long serialVersionUID = 1L;
    
    public CTRemuneracionImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CODIGOPREVISIONREMANT$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "codigo_prevision_rem_ant");
    private static final javax.xml.namespace.QName ANOMESREMANT$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "ano_mes_rem_ant");
    private static final javax.xml.namespace.QName NDIASREMANT$4 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "ndias_rem_ant");
    private static final javax.xml.namespace.QName MONTOIMPONIBLEREMANT$6 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "monto_imponible_rem_ant");
    private static final javax.xml.namespace.QName MONTOTOTALREMANT$8 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "monto_total_rem_ant");
    private static final javax.xml.namespace.QName MONTOINCAPACIDADREMANT$10 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "monto_incapacidad_rem_ant");
    private static final javax.xml.namespace.QName NDIASINCAPACIDADREMANT$12 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "ndias_incapacidad_rem_ant");
    
    
    /**
     * Gets the "codigo_prevision_rem_ant" element
     */
    public java.math.BigInteger getCodigoPrevisionRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOPREVISIONREMANT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "codigo_prevision_rem_ant" element
     */
    public org.apache.xmlbeans.XmlInteger xgetCodigoPrevisionRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(CODIGOPREVISIONREMANT$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "codigo_prevision_rem_ant" element
     */
    public void setCodigoPrevisionRemAnt(java.math.BigInteger codigoPrevisionRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODIGOPREVISIONREMANT$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODIGOPREVISIONREMANT$0);
            }
            target.setBigIntegerValue(codigoPrevisionRemAnt);
        }
    }
    
    /**
     * Sets (as xml) the "codigo_prevision_rem_ant" element
     */
    public void xsetCodigoPrevisionRemAnt(org.apache.xmlbeans.XmlInteger codigoPrevisionRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(CODIGOPREVISIONREMANT$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(CODIGOPREVISIONREMANT$0);
            }
            target.set(codigoPrevisionRemAnt);
        }
    }
    
    /**
     * Gets the "ano_mes_rem_ant" element
     */
    public java.util.Calendar getAnoMesRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ANOMESREMANT$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "ano_mes_rem_ant" element
     */
    public org.apache.xmlbeans.XmlGYearMonth xgetAnoMesRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlGYearMonth target = null;
            target = (org.apache.xmlbeans.XmlGYearMonth)get_store().find_element_user(ANOMESREMANT$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ano_mes_rem_ant" element
     */
    public void setAnoMesRemAnt(java.util.Calendar anoMesRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ANOMESREMANT$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ANOMESREMANT$2);
            }
            target.setCalendarValue(anoMesRemAnt);
        }
    }
    
    /**
     * Sets (as xml) the "ano_mes_rem_ant" element
     */
    public void xsetAnoMesRemAnt(org.apache.xmlbeans.XmlGYearMonth anoMesRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlGYearMonth target = null;
            target = (org.apache.xmlbeans.XmlGYearMonth)get_store().find_element_user(ANOMESREMANT$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlGYearMonth)get_store().add_element_user(ANOMESREMANT$2);
            }
            target.set(anoMesRemAnt);
        }
    }
    
    /**
     * Gets the "ndias_rem_ant" element
     */
    public java.math.BigInteger getNdiasRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NDIASREMANT$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "ndias_rem_ant" element
     */
    public org.apache.xmlbeans.XmlInteger xgetNdiasRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(NDIASREMANT$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ndias_rem_ant" element
     */
    public void setNdiasRemAnt(java.math.BigInteger ndiasRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NDIASREMANT$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NDIASREMANT$4);
            }
            target.setBigIntegerValue(ndiasRemAnt);
        }
    }
    
    /**
     * Sets (as xml) the "ndias_rem_ant" element
     */
    public void xsetNdiasRemAnt(org.apache.xmlbeans.XmlInteger ndiasRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(NDIASREMANT$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(NDIASREMANT$4);
            }
            target.set(ndiasRemAnt);
        }
    }
    
    /**
     * Gets the "monto_imponible_rem_ant" element
     */
    public java.math.BigInteger getMontoImponibleRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MONTOIMPONIBLEREMANT$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "monto_imponible_rem_ant" element
     */
    public org.apache.xmlbeans.XmlInteger xgetMontoImponibleRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(MONTOIMPONIBLEREMANT$6, 0);
            return target;
        }
    }
    
    /**
     * True if has "monto_imponible_rem_ant" element
     */
    public boolean isSetMontoImponibleRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(MONTOIMPONIBLEREMANT$6) != 0;
        }
    }
    
    /**
     * Sets the "monto_imponible_rem_ant" element
     */
    public void setMontoImponibleRemAnt(java.math.BigInteger montoImponibleRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MONTOIMPONIBLEREMANT$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MONTOIMPONIBLEREMANT$6);
            }
            target.setBigIntegerValue(montoImponibleRemAnt);
        }
    }
    
    /**
     * Sets (as xml) the "monto_imponible_rem_ant" element
     */
    public void xsetMontoImponibleRemAnt(org.apache.xmlbeans.XmlInteger montoImponibleRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(MONTOIMPONIBLEREMANT$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(MONTOIMPONIBLEREMANT$6);
            }
            target.set(montoImponibleRemAnt);
        }
    }
    
    /**
     * Unsets the "monto_imponible_rem_ant" element
     */
    public void unsetMontoImponibleRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(MONTOIMPONIBLEREMANT$6, 0);
        }
    }
    
    /**
     * Gets the "monto_total_rem_ant" element
     */
    public java.math.BigInteger getMontoTotalRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MONTOTOTALREMANT$8, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "monto_total_rem_ant" element
     */
    public org.apache.xmlbeans.XmlInteger xgetMontoTotalRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(MONTOTOTALREMANT$8, 0);
            return target;
        }
    }
    
    /**
     * True if has "monto_total_rem_ant" element
     */
    public boolean isSetMontoTotalRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(MONTOTOTALREMANT$8) != 0;
        }
    }
    
    /**
     * Sets the "monto_total_rem_ant" element
     */
    public void setMontoTotalRemAnt(java.math.BigInteger montoTotalRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MONTOTOTALREMANT$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MONTOTOTALREMANT$8);
            }
            target.setBigIntegerValue(montoTotalRemAnt);
        }
    }
    
    /**
     * Sets (as xml) the "monto_total_rem_ant" element
     */
    public void xsetMontoTotalRemAnt(org.apache.xmlbeans.XmlInteger montoTotalRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(MONTOTOTALREMANT$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(MONTOTOTALREMANT$8);
            }
            target.set(montoTotalRemAnt);
        }
    }
    
    /**
     * Unsets the "monto_total_rem_ant" element
     */
    public void unsetMontoTotalRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(MONTOTOTALREMANT$8, 0);
        }
    }
    
    /**
     * Gets the "monto_incapacidad_rem_ant" element
     */
    public java.math.BigInteger getMontoIncapacidadRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MONTOINCAPACIDADREMANT$10, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "monto_incapacidad_rem_ant" element
     */
    public org.apache.xmlbeans.XmlInteger xgetMontoIncapacidadRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(MONTOINCAPACIDADREMANT$10, 0);
            return target;
        }
    }
    
    /**
     * True if has "monto_incapacidad_rem_ant" element
     */
    public boolean isSetMontoIncapacidadRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(MONTOINCAPACIDADREMANT$10) != 0;
        }
    }
    
    /**
     * Sets the "monto_incapacidad_rem_ant" element
     */
    public void setMontoIncapacidadRemAnt(java.math.BigInteger montoIncapacidadRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MONTOINCAPACIDADREMANT$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MONTOINCAPACIDADREMANT$10);
            }
            target.setBigIntegerValue(montoIncapacidadRemAnt);
        }
    }
    
    /**
     * Sets (as xml) the "monto_incapacidad_rem_ant" element
     */
    public void xsetMontoIncapacidadRemAnt(org.apache.xmlbeans.XmlInteger montoIncapacidadRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(MONTOINCAPACIDADREMANT$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(MONTOINCAPACIDADREMANT$10);
            }
            target.set(montoIncapacidadRemAnt);
        }
    }
    
    /**
     * Unsets the "monto_incapacidad_rem_ant" element
     */
    public void unsetMontoIncapacidadRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(MONTOINCAPACIDADREMANT$10, 0);
        }
    }
    
    /**
     * Gets the "ndias_incapacidad_rem_ant" element
     */
    public java.math.BigInteger getNdiasIncapacidadRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NDIASINCAPACIDADREMANT$12, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "ndias_incapacidad_rem_ant" element
     */
    public org.apache.xmlbeans.XmlInteger xgetNdiasIncapacidadRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(NDIASINCAPACIDADREMANT$12, 0);
            return target;
        }
    }
    
    /**
     * True if has "ndias_incapacidad_rem_ant" element
     */
    public boolean isSetNdiasIncapacidadRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NDIASINCAPACIDADREMANT$12) != 0;
        }
    }
    
    /**
     * Sets the "ndias_incapacidad_rem_ant" element
     */
    public void setNdiasIncapacidadRemAnt(java.math.BigInteger ndiasIncapacidadRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NDIASINCAPACIDADREMANT$12, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NDIASINCAPACIDADREMANT$12);
            }
            target.setBigIntegerValue(ndiasIncapacidadRemAnt);
        }
    }
    
    /**
     * Sets (as xml) the "ndias_incapacidad_rem_ant" element
     */
    public void xsetNdiasIncapacidadRemAnt(org.apache.xmlbeans.XmlInteger ndiasIncapacidadRemAnt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(NDIASINCAPACIDADREMANT$12, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(NDIASINCAPACIDADREMANT$12);
            }
            target.set(ndiasIncapacidadRemAnt);
        }
    }
    
    /**
     * Unsets the "ndias_incapacidad_rem_ant" element
     */
    public void unsetNdiasIncapacidadRemAnt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NDIASINCAPACIDADREMANT$12, 0);
        }
    }
}
