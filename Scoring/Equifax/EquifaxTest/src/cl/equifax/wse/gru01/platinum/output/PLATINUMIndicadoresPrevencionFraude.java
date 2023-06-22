/**
 * PLATINUMIndicadoresPrevencionFraude.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.equifax.wse.gru01.platinum.output;

public class PLATINUMIndicadoresPrevencionFraude  implements java.io.Serializable {
    private java.lang.String cantidadAnotacionesTributariasVigentes;

    private java.lang.String cantidadCuentasCorrientesBloqueadas;

    private java.lang.String cantidadONP;

    private java.lang.String cantidadOPNUltimosMeses;

    private java.lang.String cantidadPublicaciones;

    private java.lang.String cantidadUltimosMesesOPN;

    private cl.equifax.wse.gru01.platinum.output.sub.PLATINUMCuentaCorrienteCerrada[] cuentasCorrientesCerradas;

    private cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleONP[] detalleONPs;

    private java.lang.String fechaDefuncion;

    private java.lang.String fechaInterdicto;

    private java.lang.String fechaUltimoBoletinLaboral;

    private java.lang.String indicadorImpedidoAbrirCuentaCorriente;

    private java.lang.String indicadorPersonaDifunta;

    private java.lang.String indicadorTieneDeudaMorosaVencidaCastigada;

    private java.lang.String indicadorVerificacionIdentidad;

    public PLATINUMIndicadoresPrevencionFraude() {
    }

    public PLATINUMIndicadoresPrevencionFraude(
           java.lang.String cantidadAnotacionesTributariasVigentes,
           java.lang.String cantidadCuentasCorrientesBloqueadas,
           java.lang.String cantidadONP,
           java.lang.String cantidadOPNUltimosMeses,
           java.lang.String cantidadPublicaciones,
           java.lang.String cantidadUltimosMesesOPN,
           cl.equifax.wse.gru01.platinum.output.sub.PLATINUMCuentaCorrienteCerrada[] cuentasCorrientesCerradas,
           cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleONP[] detalleONPs,
           java.lang.String fechaDefuncion,
           java.lang.String fechaInterdicto,
           java.lang.String fechaUltimoBoletinLaboral,
           java.lang.String indicadorImpedidoAbrirCuentaCorriente,
           java.lang.String indicadorPersonaDifunta,
           java.lang.String indicadorTieneDeudaMorosaVencidaCastigada,
           java.lang.String indicadorVerificacionIdentidad) {
           this.cantidadAnotacionesTributariasVigentes = cantidadAnotacionesTributariasVigentes;
           this.cantidadCuentasCorrientesBloqueadas = cantidadCuentasCorrientesBloqueadas;
           this.cantidadONP = cantidadONP;
           this.cantidadOPNUltimosMeses = cantidadOPNUltimosMeses;
           this.cantidadPublicaciones = cantidadPublicaciones;
           this.cantidadUltimosMesesOPN = cantidadUltimosMesesOPN;
           this.cuentasCorrientesCerradas = cuentasCorrientesCerradas;
           this.detalleONPs = detalleONPs;
           this.fechaDefuncion = fechaDefuncion;
           this.fechaInterdicto = fechaInterdicto;
           this.fechaUltimoBoletinLaboral = fechaUltimoBoletinLaboral;
           this.indicadorImpedidoAbrirCuentaCorriente = indicadorImpedidoAbrirCuentaCorriente;
           this.indicadorPersonaDifunta = indicadorPersonaDifunta;
           this.indicadorTieneDeudaMorosaVencidaCastigada = indicadorTieneDeudaMorosaVencidaCastigada;
           this.indicadorVerificacionIdentidad = indicadorVerificacionIdentidad;
    }


    /**
     * Gets the cantidadAnotacionesTributariasVigentes value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return cantidadAnotacionesTributariasVigentes
     */
    public java.lang.String getCantidadAnotacionesTributariasVigentes() {
        return cantidadAnotacionesTributariasVigentes;
    }


    /**
     * Sets the cantidadAnotacionesTributariasVigentes value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param cantidadAnotacionesTributariasVigentes
     */
    public void setCantidadAnotacionesTributariasVigentes(java.lang.String cantidadAnotacionesTributariasVigentes) {
        this.cantidadAnotacionesTributariasVigentes = cantidadAnotacionesTributariasVigentes;
    }


    /**
     * Gets the cantidadCuentasCorrientesBloqueadas value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return cantidadCuentasCorrientesBloqueadas
     */
    public java.lang.String getCantidadCuentasCorrientesBloqueadas() {
        return cantidadCuentasCorrientesBloqueadas;
    }


    /**
     * Sets the cantidadCuentasCorrientesBloqueadas value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param cantidadCuentasCorrientesBloqueadas
     */
    public void setCantidadCuentasCorrientesBloqueadas(java.lang.String cantidadCuentasCorrientesBloqueadas) {
        this.cantidadCuentasCorrientesBloqueadas = cantidadCuentasCorrientesBloqueadas;
    }


    /**
     * Gets the cantidadONP value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return cantidadONP
     */
    public java.lang.String getCantidadONP() {
        return cantidadONP;
    }


    /**
     * Sets the cantidadONP value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param cantidadONP
     */
    public void setCantidadONP(java.lang.String cantidadONP) {
        this.cantidadONP = cantidadONP;
    }


    /**
     * Gets the cantidadOPNUltimosMeses value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return cantidadOPNUltimosMeses
     */
    public java.lang.String getCantidadOPNUltimosMeses() {
        return cantidadOPNUltimosMeses;
    }


    /**
     * Sets the cantidadOPNUltimosMeses value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param cantidadOPNUltimosMeses
     */
    public void setCantidadOPNUltimosMeses(java.lang.String cantidadOPNUltimosMeses) {
        this.cantidadOPNUltimosMeses = cantidadOPNUltimosMeses;
    }


    /**
     * Gets the cantidadPublicaciones value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return cantidadPublicaciones
     */
    public java.lang.String getCantidadPublicaciones() {
        return cantidadPublicaciones;
    }


    /**
     * Sets the cantidadPublicaciones value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param cantidadPublicaciones
     */
    public void setCantidadPublicaciones(java.lang.String cantidadPublicaciones) {
        this.cantidadPublicaciones = cantidadPublicaciones;
    }


    /**
     * Gets the cantidadUltimosMesesOPN value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return cantidadUltimosMesesOPN
     */
    public java.lang.String getCantidadUltimosMesesOPN() {
        return cantidadUltimosMesesOPN;
    }


    /**
     * Sets the cantidadUltimosMesesOPN value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param cantidadUltimosMesesOPN
     */
    public void setCantidadUltimosMesesOPN(java.lang.String cantidadUltimosMesesOPN) {
        this.cantidadUltimosMesesOPN = cantidadUltimosMesesOPN;
    }


    /**
     * Gets the cuentasCorrientesCerradas value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return cuentasCorrientesCerradas
     */
    public cl.equifax.wse.gru01.platinum.output.sub.PLATINUMCuentaCorrienteCerrada[] getCuentasCorrientesCerradas() {
        return cuentasCorrientesCerradas;
    }


    /**
     * Sets the cuentasCorrientesCerradas value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param cuentasCorrientesCerradas
     */
    public void setCuentasCorrientesCerradas(cl.equifax.wse.gru01.platinum.output.sub.PLATINUMCuentaCorrienteCerrada[] cuentasCorrientesCerradas) {
        this.cuentasCorrientesCerradas = cuentasCorrientesCerradas;
    }


    /**
     * Gets the detalleONPs value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return detalleONPs
     */
    public cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleONP[] getDetalleONPs() {
        return detalleONPs;
    }


    /**
     * Sets the detalleONPs value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param detalleONPs
     */
    public void setDetalleONPs(cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleONP[] detalleONPs) {
        this.detalleONPs = detalleONPs;
    }


    /**
     * Gets the fechaDefuncion value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return fechaDefuncion
     */
    public java.lang.String getFechaDefuncion() {
        return fechaDefuncion;
    }


    /**
     * Sets the fechaDefuncion value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param fechaDefuncion
     */
    public void setFechaDefuncion(java.lang.String fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }


    /**
     * Gets the fechaInterdicto value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return fechaInterdicto
     */
    public java.lang.String getFechaInterdicto() {
        return fechaInterdicto;
    }


    /**
     * Sets the fechaInterdicto value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param fechaInterdicto
     */
    public void setFechaInterdicto(java.lang.String fechaInterdicto) {
        this.fechaInterdicto = fechaInterdicto;
    }


    /**
     * Gets the fechaUltimoBoletinLaboral value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return fechaUltimoBoletinLaboral
     */
    public java.lang.String getFechaUltimoBoletinLaboral() {
        return fechaUltimoBoletinLaboral;
    }


    /**
     * Sets the fechaUltimoBoletinLaboral value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param fechaUltimoBoletinLaboral
     */
    public void setFechaUltimoBoletinLaboral(java.lang.String fechaUltimoBoletinLaboral) {
        this.fechaUltimoBoletinLaboral = fechaUltimoBoletinLaboral;
    }


    /**
     * Gets the indicadorImpedidoAbrirCuentaCorriente value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return indicadorImpedidoAbrirCuentaCorriente
     */
    public java.lang.String getIndicadorImpedidoAbrirCuentaCorriente() {
        return indicadorImpedidoAbrirCuentaCorriente;
    }


    /**
     * Sets the indicadorImpedidoAbrirCuentaCorriente value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param indicadorImpedidoAbrirCuentaCorriente
     */
    public void setIndicadorImpedidoAbrirCuentaCorriente(java.lang.String indicadorImpedidoAbrirCuentaCorriente) {
        this.indicadorImpedidoAbrirCuentaCorriente = indicadorImpedidoAbrirCuentaCorriente;
    }


    /**
     * Gets the indicadorPersonaDifunta value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return indicadorPersonaDifunta
     */
    public java.lang.String getIndicadorPersonaDifunta() {
        return indicadorPersonaDifunta;
    }


    /**
     * Sets the indicadorPersonaDifunta value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param indicadorPersonaDifunta
     */
    public void setIndicadorPersonaDifunta(java.lang.String indicadorPersonaDifunta) {
        this.indicadorPersonaDifunta = indicadorPersonaDifunta;
    }


    /**
     * Gets the indicadorTieneDeudaMorosaVencidaCastigada value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return indicadorTieneDeudaMorosaVencidaCastigada
     */
    public java.lang.String getIndicadorTieneDeudaMorosaVencidaCastigada() {
        return indicadorTieneDeudaMorosaVencidaCastigada;
    }


    /**
     * Sets the indicadorTieneDeudaMorosaVencidaCastigada value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param indicadorTieneDeudaMorosaVencidaCastigada
     */
    public void setIndicadorTieneDeudaMorosaVencidaCastigada(java.lang.String indicadorTieneDeudaMorosaVencidaCastigada) {
        this.indicadorTieneDeudaMorosaVencidaCastigada = indicadorTieneDeudaMorosaVencidaCastigada;
    }


    /**
     * Gets the indicadorVerificacionIdentidad value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @return indicadorVerificacionIdentidad
     */
    public java.lang.String getIndicadorVerificacionIdentidad() {
        return indicadorVerificacionIdentidad;
    }


    /**
     * Sets the indicadorVerificacionIdentidad value for this PLATINUMIndicadoresPrevencionFraude.
     * 
     * @param indicadorVerificacionIdentidad
     */
    public void setIndicadorVerificacionIdentidad(java.lang.String indicadorVerificacionIdentidad) {
        this.indicadorVerificacionIdentidad = indicadorVerificacionIdentidad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLATINUMIndicadoresPrevencionFraude)) return false;
        PLATINUMIndicadoresPrevencionFraude other = (PLATINUMIndicadoresPrevencionFraude) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cantidadAnotacionesTributariasVigentes==null && other.getCantidadAnotacionesTributariasVigentes()==null) || 
             (this.cantidadAnotacionesTributariasVigentes!=null &&
              this.cantidadAnotacionesTributariasVigentes.equals(other.getCantidadAnotacionesTributariasVigentes()))) &&
            ((this.cantidadCuentasCorrientesBloqueadas==null && other.getCantidadCuentasCorrientesBloqueadas()==null) || 
             (this.cantidadCuentasCorrientesBloqueadas!=null &&
              this.cantidadCuentasCorrientesBloqueadas.equals(other.getCantidadCuentasCorrientesBloqueadas()))) &&
            ((this.cantidadONP==null && other.getCantidadONP()==null) || 
             (this.cantidadONP!=null &&
              this.cantidadONP.equals(other.getCantidadONP()))) &&
            ((this.cantidadOPNUltimosMeses==null && other.getCantidadOPNUltimosMeses()==null) || 
             (this.cantidadOPNUltimosMeses!=null &&
              this.cantidadOPNUltimosMeses.equals(other.getCantidadOPNUltimosMeses()))) &&
            ((this.cantidadPublicaciones==null && other.getCantidadPublicaciones()==null) || 
             (this.cantidadPublicaciones!=null &&
              this.cantidadPublicaciones.equals(other.getCantidadPublicaciones()))) &&
            ((this.cantidadUltimosMesesOPN==null && other.getCantidadUltimosMesesOPN()==null) || 
             (this.cantidadUltimosMesesOPN!=null &&
              this.cantidadUltimosMesesOPN.equals(other.getCantidadUltimosMesesOPN()))) &&
            ((this.cuentasCorrientesCerradas==null && other.getCuentasCorrientesCerradas()==null) || 
             (this.cuentasCorrientesCerradas!=null &&
              java.util.Arrays.equals(this.cuentasCorrientesCerradas, other.getCuentasCorrientesCerradas()))) &&
            ((this.detalleONPs==null && other.getDetalleONPs()==null) || 
             (this.detalleONPs!=null &&
              java.util.Arrays.equals(this.detalleONPs, other.getDetalleONPs()))) &&
            ((this.fechaDefuncion==null && other.getFechaDefuncion()==null) || 
             (this.fechaDefuncion!=null &&
              this.fechaDefuncion.equals(other.getFechaDefuncion()))) &&
            ((this.fechaInterdicto==null && other.getFechaInterdicto()==null) || 
             (this.fechaInterdicto!=null &&
              this.fechaInterdicto.equals(other.getFechaInterdicto()))) &&
            ((this.fechaUltimoBoletinLaboral==null && other.getFechaUltimoBoletinLaboral()==null) || 
             (this.fechaUltimoBoletinLaboral!=null &&
              this.fechaUltimoBoletinLaboral.equals(other.getFechaUltimoBoletinLaboral()))) &&
            ((this.indicadorImpedidoAbrirCuentaCorriente==null && other.getIndicadorImpedidoAbrirCuentaCorriente()==null) || 
             (this.indicadorImpedidoAbrirCuentaCorriente!=null &&
              this.indicadorImpedidoAbrirCuentaCorriente.equals(other.getIndicadorImpedidoAbrirCuentaCorriente()))) &&
            ((this.indicadorPersonaDifunta==null && other.getIndicadorPersonaDifunta()==null) || 
             (this.indicadorPersonaDifunta!=null &&
              this.indicadorPersonaDifunta.equals(other.getIndicadorPersonaDifunta()))) &&
            ((this.indicadorTieneDeudaMorosaVencidaCastigada==null && other.getIndicadorTieneDeudaMorosaVencidaCastigada()==null) || 
             (this.indicadorTieneDeudaMorosaVencidaCastigada!=null &&
              this.indicadorTieneDeudaMorosaVencidaCastigada.equals(other.getIndicadorTieneDeudaMorosaVencidaCastigada()))) &&
            ((this.indicadorVerificacionIdentidad==null && other.getIndicadorVerificacionIdentidad()==null) || 
             (this.indicadorVerificacionIdentidad!=null &&
              this.indicadorVerificacionIdentidad.equals(other.getIndicadorVerificacionIdentidad())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCantidadAnotacionesTributariasVigentes() != null) {
            _hashCode += getCantidadAnotacionesTributariasVigentes().hashCode();
        }
        if (getCantidadCuentasCorrientesBloqueadas() != null) {
            _hashCode += getCantidadCuentasCorrientesBloqueadas().hashCode();
        }
        if (getCantidadONP() != null) {
            _hashCode += getCantidadONP().hashCode();
        }
        if (getCantidadOPNUltimosMeses() != null) {
            _hashCode += getCantidadOPNUltimosMeses().hashCode();
        }
        if (getCantidadPublicaciones() != null) {
            _hashCode += getCantidadPublicaciones().hashCode();
        }
        if (getCantidadUltimosMesesOPN() != null) {
            _hashCode += getCantidadUltimosMesesOPN().hashCode();
        }
        if (getCuentasCorrientesCerradas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCuentasCorrientesCerradas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCuentasCorrientesCerradas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDetalleONPs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetalleONPs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetalleONPs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFechaDefuncion() != null) {
            _hashCode += getFechaDefuncion().hashCode();
        }
        if (getFechaInterdicto() != null) {
            _hashCode += getFechaInterdicto().hashCode();
        }
        if (getFechaUltimoBoletinLaboral() != null) {
            _hashCode += getFechaUltimoBoletinLaboral().hashCode();
        }
        if (getIndicadorImpedidoAbrirCuentaCorriente() != null) {
            _hashCode += getIndicadorImpedidoAbrirCuentaCorriente().hashCode();
        }
        if (getIndicadorPersonaDifunta() != null) {
            _hashCode += getIndicadorPersonaDifunta().hashCode();
        }
        if (getIndicadorTieneDeudaMorosaVencidaCastigada() != null) {
            _hashCode += getIndicadorTieneDeudaMorosaVencidaCastigada().hashCode();
        }
        if (getIndicadorVerificacionIdentidad() != null) {
            _hashCode += getIndicadorVerificacionIdentidad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PLATINUMIndicadoresPrevencionFraude.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMIndicadoresPrevencionFraude"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadAnotacionesTributariasVigentes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadAnotacionesTributariasVigentes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadCuentasCorrientesBloqueadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadCuentasCorrientesBloqueadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadONP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadONP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadOPNUltimosMeses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadOPNUltimosMeses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadPublicaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadPublicaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadUltimosMesesOPN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadUltimosMesesOPN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cuentasCorrientesCerradas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cuentasCorrientesCerradas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMCuentaCorrienteCerrada"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detalleONPs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "detalleONPs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleONP"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaDefuncion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaDefuncion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInterdicto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaInterdicto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaUltimoBoletinLaboral");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaUltimoBoletinLaboral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicadorImpedidoAbrirCuentaCorriente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicadorImpedidoAbrirCuentaCorriente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicadorPersonaDifunta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicadorPersonaDifunta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicadorTieneDeudaMorosaVencidaCastigada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicadorTieneDeudaMorosaVencidaCastigada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicadorVerificacionIdentidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicadorVerificacionIdentidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
