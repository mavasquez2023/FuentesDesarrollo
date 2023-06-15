package cl.laaraucana.servicios.simuladorCreditoSocial;

public class CreditoVO  implements java.io.Serializable {
    private java.lang.String LOG_RESPUESTA;

    private int MONTO_CUOTA;

    private double TASA_INT_MENSUAL;

    private double TASA_INT_ANUAL;

    private double CAE;

    private int IMPUESTO;

    private int GASTO_NOTARIAL;

    private int CTC;

    private int COSTO_MENSUAL_DESGRAVAMEN;

    private int COSTO_TOTAL_DESGRAVAMEN;

    private int COSTO_MENSUAL_CESANTIA;

    private int COSTOS_TOTAL_CESANTIA;

    private int FECHA_PRIMER_VENCIMIENTO;

    private cl.laaraucana.servicios.simuladorCreditoSocial.DetalleVO[] DETALLE_CUOTA;

    public CreditoVO() {
    }

    public CreditoVO(
           java.lang.String LOG_RESPUESTA,
           int MONTO_CUOTA,
           double TASA_INT_MENSUAL,
           double TASA_INT_ANUAL,
           double CAE,
           int IMPUESTO,
           int GASTO_NOTARIAL,
           int CTC,
           int COSTO_MENSUAL_DESGRAVAMEN,
           int COSTO_TOTAL_DESGRAVAMEN,
           int COSTO_MENSUAL_CESANTIA,
           int COSTOS_TOTAL_CESANTIA,
           int FECHA_PRIMER_VENCIMIENTO,
           cl.laaraucana.servicios.simuladorCreditoSocial.DetalleVO[] DETALLE_CUOTA) {
           this.LOG_RESPUESTA = LOG_RESPUESTA;
           this.MONTO_CUOTA = MONTO_CUOTA;
           this.TASA_INT_MENSUAL = TASA_INT_MENSUAL;
           this.TASA_INT_ANUAL = TASA_INT_ANUAL;
           this.CAE = CAE;
           this.IMPUESTO = IMPUESTO;
           this.GASTO_NOTARIAL = GASTO_NOTARIAL;
           this.CTC = CTC;
           this.COSTO_MENSUAL_DESGRAVAMEN = COSTO_MENSUAL_DESGRAVAMEN;
           this.COSTO_TOTAL_DESGRAVAMEN = COSTO_TOTAL_DESGRAVAMEN;
           this.COSTO_MENSUAL_CESANTIA = COSTO_MENSUAL_CESANTIA;
           this.COSTOS_TOTAL_CESANTIA = COSTOS_TOTAL_CESANTIA;
           this.FECHA_PRIMER_VENCIMIENTO = FECHA_PRIMER_VENCIMIENTO;
           this.DETALLE_CUOTA = DETALLE_CUOTA;
    }


    /**
     * Gets the LOG_RESPUESTA value for this CreditoVO.
     * 
     * @return LOG_RESPUESTA
     */
    public java.lang.String getLOG_RESPUESTA() {
        return LOG_RESPUESTA;
    }


    /**
     * Sets the LOG_RESPUESTA value for this CreditoVO.
     * 
     * @param LOG_RESPUESTA
     */
    public void setLOG_RESPUESTA(java.lang.String LOG_RESPUESTA) {
        this.LOG_RESPUESTA = LOG_RESPUESTA;
    }


    /**
     * Gets the MONTO_CUOTA value for this CreditoVO.
     * 
     * @return MONTO_CUOTA
     */
    public int getMONTO_CUOTA() {
        return MONTO_CUOTA;
    }


    /**
     * Sets the MONTO_CUOTA value for this CreditoVO.
     * 
     * @param MONTO_CUOTA
     */
    public void setMONTO_CUOTA(int MONTO_CUOTA) {
        this.MONTO_CUOTA = MONTO_CUOTA;
    }


    /**
     * Gets the TASA_INT_MENSUAL value for this CreditoVO.
     * 
     * @return TASA_INT_MENSUAL
     */
    public double getTASA_INT_MENSUAL() {
        return TASA_INT_MENSUAL;
    }


    /**
     * Sets the TASA_INT_MENSUAL value for this CreditoVO.
     * 
     * @param TASA_INT_MENSUAL
     */
    public void setTASA_INT_MENSUAL(double TASA_INT_MENSUAL) {
        this.TASA_INT_MENSUAL = TASA_INT_MENSUAL;
    }


    /**
     * Gets the TASA_INT_ANUAL value for this CreditoVO.
     * 
     * @return TASA_INT_ANUAL
     */
    public double getTASA_INT_ANUAL() {
        return TASA_INT_ANUAL;
    }


    /**
     * Sets the TASA_INT_ANUAL value for this CreditoVO.
     * 
     * @param TASA_INT_ANUAL
     */
    public void setTASA_INT_ANUAL(double TASA_INT_ANUAL) {
        this.TASA_INT_ANUAL = TASA_INT_ANUAL;
    }


    /**
     * Gets the CAE value for this CreditoVO.
     * 
     * @return CAE
     */
    public double getCAE() {
        return CAE;
    }


    /**
     * Sets the CAE value for this CreditoVO.
     * 
     * @param CAE
     */
    public void setCAE(double CAE) {
        this.CAE = CAE;
    }


    /**
     * Gets the IMPUESTO value for this CreditoVO.
     * 
     * @return IMPUESTO
     */
    public int getIMPUESTO() {
        return IMPUESTO;
    }


    /**
     * Sets the IMPUESTO value for this CreditoVO.
     * 
     * @param IMPUESTO
     */
    public void setIMPUESTO(int IMPUESTO) {
        this.IMPUESTO = IMPUESTO;
    }


    /**
     * Gets the GASTO_NOTARIAL value for this CreditoVO.
     * 
     * @return GASTO_NOTARIAL
     */
    public int getGASTO_NOTARIAL() {
        return GASTO_NOTARIAL;
    }


    /**
     * Sets the GASTO_NOTARIAL value for this CreditoVO.
     * 
     * @param GASTO_NOTARIAL
     */
    public void setGASTO_NOTARIAL(int GASTO_NOTARIAL) {
        this.GASTO_NOTARIAL = GASTO_NOTARIAL;
    }


    /**
     * Gets the CTC value for this CreditoVO.
     * 
     * @return CTC
     */
    public int getCTC() {
        return CTC;
    }


    /**
     * Sets the CTC value for this CreditoVO.
     * 
     * @param CTC
     */
    public void setCTC(int CTC) {
        this.CTC = CTC;
    }


    /**
     * Gets the COSTO_MENSUAL_DESGRAVAMEN value for this CreditoVO.
     * 
     * @return COSTO_MENSUAL_DESGRAVAMEN
     */
    public int getCOSTO_MENSUAL_DESGRAVAMEN() {
        return COSTO_MENSUAL_DESGRAVAMEN;
    }


    /**
     * Sets the COSTO_MENSUAL_DESGRAVAMEN value for this CreditoVO.
     * 
     * @param COSTO_MENSUAL_DESGRAVAMEN
     */
    public void setCOSTO_MENSUAL_DESGRAVAMEN(int COSTO_MENSUAL_DESGRAVAMEN) {
        this.COSTO_MENSUAL_DESGRAVAMEN = COSTO_MENSUAL_DESGRAVAMEN;
    }


    /**
     * Gets the COSTO_TOTAL_DESGRAVAMEN value for this CreditoVO.
     * 
     * @return COSTO_TOTAL_DESGRAVAMEN
     */
    public int getCOSTO_TOTAL_DESGRAVAMEN() {
        return COSTO_TOTAL_DESGRAVAMEN;
    }


    /**
     * Sets the COSTO_TOTAL_DESGRAVAMEN value for this CreditoVO.
     * 
     * @param COSTO_TOTAL_DESGRAVAMEN
     */
    public void setCOSTO_TOTAL_DESGRAVAMEN(int COSTO_TOTAL_DESGRAVAMEN) {
        this.COSTO_TOTAL_DESGRAVAMEN = COSTO_TOTAL_DESGRAVAMEN;
    }


    /**
     * Gets the COSTO_MENSUAL_CESANTIA value for this CreditoVO.
     * 
     * @return COSTO_MENSUAL_CESANTIA
     */
    public int getCOSTO_MENSUAL_CESANTIA() {
        return COSTO_MENSUAL_CESANTIA;
    }


    /**
     * Sets the COSTO_MENSUAL_CESANTIA value for this CreditoVO.
     * 
     * @param COSTO_MENSUAL_CESANTIA
     */
    public void setCOSTO_MENSUAL_CESANTIA(int COSTO_MENSUAL_CESANTIA) {
        this.COSTO_MENSUAL_CESANTIA = COSTO_MENSUAL_CESANTIA;
    }


    /**
     * Gets the COSTOS_TOTAL_CESANTIA value for this CreditoVO.
     * 
     * @return COSTOS_TOTAL_CESANTIA
     */
    public int getCOSTOS_TOTAL_CESANTIA() {
        return COSTOS_TOTAL_CESANTIA;
    }


    /**
     * Sets the COSTOS_TOTAL_CESANTIA value for this CreditoVO.
     * 
     * @param COSTOS_TOTAL_CESANTIA
     */
    public void setCOSTOS_TOTAL_CESANTIA(int COSTOS_TOTAL_CESANTIA) {
        this.COSTOS_TOTAL_CESANTIA = COSTOS_TOTAL_CESANTIA;
    }


    /**
     * Gets the FECHA_PRIMER_VENCIMIENTO value for this CreditoVO.
     * 
     * @return FECHA_PRIMER_VENCIMIENTO
     */
    public int getFECHA_PRIMER_VENCIMIENTO() {
        return FECHA_PRIMER_VENCIMIENTO;
    }


    /**
     * Sets the FECHA_PRIMER_VENCIMIENTO value for this CreditoVO.
     * 
     * @param FECHA_PRIMER_VENCIMIENTO
     */
    public void setFECHA_PRIMER_VENCIMIENTO(int FECHA_PRIMER_VENCIMIENTO) {
        this.FECHA_PRIMER_VENCIMIENTO = FECHA_PRIMER_VENCIMIENTO;
    }


    /**
     * Gets the DETALLE_CUOTA value for this CreditoVO.
     * 
     * @return DETALLE_CUOTA
     */
    public cl.laaraucana.servicios.simuladorCreditoSocial.DetalleVO[] getDETALLE_CUOTA() {
        return DETALLE_CUOTA;
    }


    /**
     * Sets the DETALLE_CUOTA value for this CreditoVO.
     * 
     * @param DETALLE_CUOTA
     */
    public void setDETALLE_CUOTA(cl.laaraucana.servicios.simuladorCreditoSocial.DetalleVO[] DETALLE_CUOTA) {
        this.DETALLE_CUOTA = DETALLE_CUOTA;
    }

    public cl.laaraucana.servicios.simuladorCreditoSocial.DetalleVO getDETALLE_CUOTA(int i) {
        return this.DETALLE_CUOTA[i];
    }

    public void setDETALLE_CUOTA(int i, cl.laaraucana.servicios.simuladorCreditoSocial.DetalleVO _value) {
        this.DETALLE_CUOTA[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreditoVO)) return false;
        CreditoVO other = (CreditoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.LOG_RESPUESTA==null && other.getLOG_RESPUESTA()==null) || 
             (this.LOG_RESPUESTA!=null &&
              this.LOG_RESPUESTA.equals(other.getLOG_RESPUESTA()))) &&
            this.MONTO_CUOTA == other.getMONTO_CUOTA() &&
            this.TASA_INT_MENSUAL == other.getTASA_INT_MENSUAL() &&
            this.TASA_INT_ANUAL == other.getTASA_INT_ANUAL() &&
            this.CAE == other.getCAE() &&
            this.IMPUESTO == other.getIMPUESTO() &&
            this.GASTO_NOTARIAL == other.getGASTO_NOTARIAL() &&
            this.CTC == other.getCTC() &&
            this.COSTO_MENSUAL_DESGRAVAMEN == other.getCOSTO_MENSUAL_DESGRAVAMEN() &&
            this.COSTO_TOTAL_DESGRAVAMEN == other.getCOSTO_TOTAL_DESGRAVAMEN() &&
            this.COSTO_MENSUAL_CESANTIA == other.getCOSTO_MENSUAL_CESANTIA() &&
            this.COSTOS_TOTAL_CESANTIA == other.getCOSTOS_TOTAL_CESANTIA() &&
            this.FECHA_PRIMER_VENCIMIENTO == other.getFECHA_PRIMER_VENCIMIENTO() &&
            ((this.DETALLE_CUOTA==null && other.getDETALLE_CUOTA()==null) || 
             (this.DETALLE_CUOTA!=null &&
              java.util.Arrays.equals(this.DETALLE_CUOTA, other.getDETALLE_CUOTA())));
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
        if (getLOG_RESPUESTA() != null) {
            _hashCode += getLOG_RESPUESTA().hashCode();
        }
        _hashCode += getMONTO_CUOTA();
        _hashCode += new Double(getTASA_INT_MENSUAL()).hashCode();
        _hashCode += new Double(getTASA_INT_ANUAL()).hashCode();
        _hashCode += new Double(getCAE()).hashCode();
        _hashCode += getIMPUESTO();
        _hashCode += getGASTO_NOTARIAL();
        _hashCode += getCTC();
        _hashCode += getCOSTO_MENSUAL_DESGRAVAMEN();
        _hashCode += getCOSTO_TOTAL_DESGRAVAMEN();
        _hashCode += getCOSTO_MENSUAL_CESANTIA();
        _hashCode += getCOSTOS_TOTAL_CESANTIA();
        _hashCode += getFECHA_PRIMER_VENCIMIENTO();
        if (getDETALLE_CUOTA() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDETALLE_CUOTA());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDETALLE_CUOTA(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreditoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/simuladorCreditoSocial", "creditoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOG_RESPUESTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOG_RESPUESTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_CUOTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_CUOTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TASA_INT_MENSUAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TASA_INT_MENSUAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TASA_INT_ANUAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TASA_INT_ANUAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CAE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CAE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IMPUESTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IMPUESTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GASTO_NOTARIAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GASTO_NOTARIAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CTC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CTC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COSTO_MENSUAL_DESGRAVAMEN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COSTO_MENSUAL_DESGRAVAMEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COSTO_TOTAL_DESGRAVAMEN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COSTO_TOTAL_DESGRAVAMEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COSTO_MENSUAL_CESANTIA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COSTO_MENSUAL_CESANTIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COSTOS_TOTAL_CESANTIA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COSTOS_TOTAL_CESANTIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHA_PRIMER_VENCIMIENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHA_PRIMER_VENCIMIENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DETALLE_CUOTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DETALLE_CUOTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/simuladorCreditoSocial", "detalleVO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
