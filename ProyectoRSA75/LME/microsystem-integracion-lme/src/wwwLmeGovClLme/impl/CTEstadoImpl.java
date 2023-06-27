/*
 * XML Type:  CTEstado
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTEstado
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CTEstado(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTEstadoImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTEstado
{
    private static final long serialVersionUID = 1L;
    
    public CTEstadoImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ESTADOLICENCIA$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "estado_licencia");
    private static final javax.xml.namespace.QName FECHAESTADO$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "fecha_estado");
    private static final javax.xml.namespace.QName MOTIVONORECEPCION$4 = 
        new javax.xml.namespace.QName("", "motivo_norecepcion");
    private static final javax.xml.namespace.QName FECHATERMINORELACION$6 = 
        new javax.xml.namespace.QName("", "fecha_termino_relacion");
    private static final javax.xml.namespace.QName EMPRUT$8 = 
        new javax.xml.namespace.QName("", "emp_rut");
    private static final javax.xml.namespace.QName CODIGOTRAMITACIONCCAF$10 = 
        new javax.xml.namespace.QName("", "codigo_tramitacion_CCAF");
    private static final javax.xml.namespace.QName MOTIVODEVOLUCIONCCAF$12 = 
        new javax.xml.namespace.QName("", "motivo_devolucion_CCAF");
    private static final javax.xml.namespace.QName TIPOLIQUIDACIONCCAF$14 = 
        new javax.xml.namespace.QName("", "tipo_liquidacion_CCAF");
    
    
    /**
     * Gets the "estado_licencia" element
     */
    public java.math.BigInteger getEstadoLicencia()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ESTADOLICENCIA$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "estado_licencia" element
     */
    public wwwLmeGovClLme.STEstadoLicencia xgetEstadoLicencia()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STEstadoLicencia target = null;
            target = (wwwLmeGovClLme.STEstadoLicencia)get_store().find_element_user(ESTADOLICENCIA$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "estado_licencia" element
     */
    public void setEstadoLicencia(java.math.BigInteger estadoLicencia)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ESTADOLICENCIA$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ESTADOLICENCIA$0);
            }
            target.setBigIntegerValue(estadoLicencia);
        }
    }
    
    /**
     * Sets (as xml) the "estado_licencia" element
     */
    public void xsetEstadoLicencia(wwwLmeGovClLme.STEstadoLicencia estadoLicencia)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STEstadoLicencia target = null;
            target = (wwwLmeGovClLme.STEstadoLicencia)get_store().find_element_user(ESTADOLICENCIA$0, 0);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STEstadoLicencia)get_store().add_element_user(ESTADOLICENCIA$0);
            }
            target.set(estadoLicencia);
        }
    }
    
    /**
     * Gets the "fecha_estado" element
     */
    public java.util.Calendar getFechaEstado()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FECHAESTADO$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "fecha_estado" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetFechaEstado()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(FECHAESTADO$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "fecha_estado" element
     */
    public void setFechaEstado(java.util.Calendar fechaEstado)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FECHAESTADO$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FECHAESTADO$2);
            }
            target.setCalendarValue(fechaEstado);
        }
    }
    
    /**
     * Sets (as xml) the "fecha_estado" element
     */
    public void xsetFechaEstado(org.apache.xmlbeans.XmlDateTime fechaEstado)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(FECHAESTADO$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(FECHAESTADO$2);
            }
            target.set(fechaEstado);
        }
    }
    
    /**
     * Gets the "motivo_norecepcion" attribute
     */
    public java.math.BigInteger getMotivoNorecepcion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(MOTIVONORECEPCION$4);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "motivo_norecepcion" attribute
     */
    public wwwLmeGovClLme.STMotivoNorecepcion xgetMotivoNorecepcion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STMotivoNorecepcion target = null;
            target = (wwwLmeGovClLme.STMotivoNorecepcion)get_store().find_attribute_user(MOTIVONORECEPCION$4);
            return target;
        }
    }
    
    /**
     * True if has "motivo_norecepcion" attribute
     */
    public boolean isSetMotivoNorecepcion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(MOTIVONORECEPCION$4) != null;
        }
    }
    
    /**
     * Sets the "motivo_norecepcion" attribute
     */
    public void setMotivoNorecepcion(java.math.BigInteger motivoNorecepcion)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(MOTIVONORECEPCION$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(MOTIVONORECEPCION$4);
            }
            target.setBigIntegerValue(motivoNorecepcion);
        }
    }
    
    /**
     * Sets (as xml) the "motivo_norecepcion" attribute
     */
    public void xsetMotivoNorecepcion(wwwLmeGovClLme.STMotivoNorecepcion motivoNorecepcion)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STMotivoNorecepcion target = null;
            target = (wwwLmeGovClLme.STMotivoNorecepcion)get_store().find_attribute_user(MOTIVONORECEPCION$4);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STMotivoNorecepcion)get_store().add_attribute_user(MOTIVONORECEPCION$4);
            }
            target.set(motivoNorecepcion);
        }
    }
    
    /**
     * Unsets the "motivo_norecepcion" attribute
     */
    public void unsetMotivoNorecepcion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(MOTIVONORECEPCION$4);
        }
    }
    
    /**
     * Gets the "fecha_termino_relacion" attribute
     */
    public java.util.Calendar getFechaTerminoRelacion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(FECHATERMINORELACION$6);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "fecha_termino_relacion" attribute
     */
    public org.apache.xmlbeans.XmlDate xgetFechaTerminoRelacion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDate target = null;
            target = (org.apache.xmlbeans.XmlDate)get_store().find_attribute_user(FECHATERMINORELACION$6);
            return target;
        }
    }
    
    /**
     * True if has "fecha_termino_relacion" attribute
     */
    public boolean isSetFechaTerminoRelacion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(FECHATERMINORELACION$6) != null;
        }
    }
    
    /**
     * Sets the "fecha_termino_relacion" attribute
     */
    public void setFechaTerminoRelacion(java.util.Calendar fechaTerminoRelacion)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(FECHATERMINORELACION$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(FECHATERMINORELACION$6);
            }
            target.setCalendarValue(fechaTerminoRelacion);
        }
    }
    
    /**
     * Sets (as xml) the "fecha_termino_relacion" attribute
     */
    public void xsetFechaTerminoRelacion(org.apache.xmlbeans.XmlDate fechaTerminoRelacion)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDate target = null;
            target = (org.apache.xmlbeans.XmlDate)get_store().find_attribute_user(FECHATERMINORELACION$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDate)get_store().add_attribute_user(FECHATERMINORELACION$6);
            }
            target.set(fechaTerminoRelacion);
        }
    }
    
    /**
     * Unsets the "fecha_termino_relacion" attribute
     */
    public void unsetFechaTerminoRelacion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(FECHATERMINORELACION$6);
        }
    }
    
    /**
     * Gets the "emp_rut" attribute
     */
    public java.lang.String getEmpRut()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(EMPRUT$8);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "emp_rut" attribute
     */
    public wwwLmeGovClLme.STRut xgetEmpRut()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STRut target = null;
            target = (wwwLmeGovClLme.STRut)get_store().find_attribute_user(EMPRUT$8);
            return target;
        }
    }
    
    /**
     * True if has "emp_rut" attribute
     */
    public boolean isSetEmpRut()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(EMPRUT$8) != null;
        }
    }
    
    /**
     * Sets the "emp_rut" attribute
     */
    public void setEmpRut(java.lang.String empRut)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(EMPRUT$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(EMPRUT$8);
            }
            target.setStringValue(empRut);
        }
    }
    
    /**
     * Sets (as xml) the "emp_rut" attribute
     */
    public void xsetEmpRut(wwwLmeGovClLme.STRut empRut)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STRut target = null;
            target = (wwwLmeGovClLme.STRut)get_store().find_attribute_user(EMPRUT$8);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STRut)get_store().add_attribute_user(EMPRUT$8);
            }
            target.set(empRut);
        }
    }
    
    /**
     * Unsets the "emp_rut" attribute
     */
    public void unsetEmpRut()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(EMPRUT$8);
        }
    }
    
    /**
     * Gets the "codigo_tramitacion_CCAF" attribute
     */
    public java.math.BigInteger getCodigoTramitacionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CODIGOTRAMITACIONCCAF$10);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "codigo_tramitacion_CCAF" attribute
     */
    public wwwLmeGovClLme.STCodigoCCAF xgetCodigoTramitacionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STCodigoCCAF target = null;
            target = (wwwLmeGovClLme.STCodigoCCAF)get_store().find_attribute_user(CODIGOTRAMITACIONCCAF$10);
            return target;
        }
    }
    
    /**
     * True if has "codigo_tramitacion_CCAF" attribute
     */
    public boolean isSetCodigoTramitacionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(CODIGOTRAMITACIONCCAF$10) != null;
        }
    }
    
    /**
     * Sets the "codigo_tramitacion_CCAF" attribute
     */
    public void setCodigoTramitacionCCAF(java.math.BigInteger codigoTramitacionCCAF)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CODIGOTRAMITACIONCCAF$10);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(CODIGOTRAMITACIONCCAF$10);
            }
            target.setBigIntegerValue(codigoTramitacionCCAF);
        }
    }
    
    /**
     * Sets (as xml) the "codigo_tramitacion_CCAF" attribute
     */
    public void xsetCodigoTramitacionCCAF(wwwLmeGovClLme.STCodigoCCAF codigoTramitacionCCAF)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STCodigoCCAF target = null;
            target = (wwwLmeGovClLme.STCodigoCCAF)get_store().find_attribute_user(CODIGOTRAMITACIONCCAF$10);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STCodigoCCAF)get_store().add_attribute_user(CODIGOTRAMITACIONCCAF$10);
            }
            target.set(codigoTramitacionCCAF);
        }
    }
    
    /**
     * Unsets the "codigo_tramitacion_CCAF" attribute
     */
    public void unsetCodigoTramitacionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(CODIGOTRAMITACIONCCAF$10);
        }
    }
    
    /**
     * Gets the "motivo_devolucion_CCAF" attribute
     */
    public java.math.BigInteger getMotivoDevolucionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(MOTIVODEVOLUCIONCCAF$12);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "motivo_devolucion_CCAF" attribute
     */
    public wwwLmeGovClLme.STMotivoDevolucionCCAF xgetMotivoDevolucionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STMotivoDevolucionCCAF target = null;
            target = (wwwLmeGovClLme.STMotivoDevolucionCCAF)get_store().find_attribute_user(MOTIVODEVOLUCIONCCAF$12);
            return target;
        }
    }
    
    /**
     * True if has "motivo_devolucion_CCAF" attribute
     */
    public boolean isSetMotivoDevolucionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(MOTIVODEVOLUCIONCCAF$12) != null;
        }
    }
    
    /**
     * Sets the "motivo_devolucion_CCAF" attribute
     */
    public void setMotivoDevolucionCCAF(java.math.BigInteger motivoDevolucionCCAF)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(MOTIVODEVOLUCIONCCAF$12);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(MOTIVODEVOLUCIONCCAF$12);
            }
            target.setBigIntegerValue(motivoDevolucionCCAF);
        }
    }
    
    /**
     * Sets (as xml) the "motivo_devolucion_CCAF" attribute
     */
    public void xsetMotivoDevolucionCCAF(wwwLmeGovClLme.STMotivoDevolucionCCAF motivoDevolucionCCAF)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STMotivoDevolucionCCAF target = null;
            target = (wwwLmeGovClLme.STMotivoDevolucionCCAF)get_store().find_attribute_user(MOTIVODEVOLUCIONCCAF$12);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STMotivoDevolucionCCAF)get_store().add_attribute_user(MOTIVODEVOLUCIONCCAF$12);
            }
            target.set(motivoDevolucionCCAF);
        }
    }
    
    /**
     * Unsets the "motivo_devolucion_CCAF" attribute
     */
    public void unsetMotivoDevolucionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(MOTIVODEVOLUCIONCCAF$12);
        }
    }
    
    /**
     * Gets the "tipo_liquidacion_CCAF" attribute
     */
    public java.math.BigInteger getTipoLiquidacionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TIPOLIQUIDACIONCCAF$14);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "tipo_liquidacion_CCAF" attribute
     */
    public wwwLmeGovClLme.STTipoLiquidacionCCAF xgetTipoLiquidacionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STTipoLiquidacionCCAF target = null;
            target = (wwwLmeGovClLme.STTipoLiquidacionCCAF)get_store().find_attribute_user(TIPOLIQUIDACIONCCAF$14);
            return target;
        }
    }
    
    /**
     * True if has "tipo_liquidacion_CCAF" attribute
     */
    public boolean isSetTipoLiquidacionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(TIPOLIQUIDACIONCCAF$14) != null;
        }
    }
    
    /**
     * Sets the "tipo_liquidacion_CCAF" attribute
     */
    public void setTipoLiquidacionCCAF(java.math.BigInteger tipoLiquidacionCCAF)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TIPOLIQUIDACIONCCAF$14);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TIPOLIQUIDACIONCCAF$14);
            }
            target.setBigIntegerValue(tipoLiquidacionCCAF);
        }
    }
    
    /**
     * Sets (as xml) the "tipo_liquidacion_CCAF" attribute
     */
    public void xsetTipoLiquidacionCCAF(wwwLmeGovClLme.STTipoLiquidacionCCAF tipoLiquidacionCCAF)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.STTipoLiquidacionCCAF target = null;
            target = (wwwLmeGovClLme.STTipoLiquidacionCCAF)get_store().find_attribute_user(TIPOLIQUIDACIONCCAF$14);
            if (target == null)
            {
                target = (wwwLmeGovClLme.STTipoLiquidacionCCAF)get_store().add_attribute_user(TIPOLIQUIDACIONCCAF$14);
            }
            target.set(tipoLiquidacionCCAF);
        }
    }
    
    /**
     * Unsets the "tipo_liquidacion_CCAF" attribute
     */
    public void unsetTipoLiquidacionCCAF()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(TIPOLIQUIDACIONCCAF$14);
        }
    }
}
