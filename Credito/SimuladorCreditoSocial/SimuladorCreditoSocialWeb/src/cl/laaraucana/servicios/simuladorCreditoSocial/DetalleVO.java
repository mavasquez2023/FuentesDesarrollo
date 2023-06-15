package cl.laaraucana.servicios.simuladorCreditoSocial;

public class DetalleVO  implements java.io.Serializable {
    private int NUM_CUOTA;

    private int FECHA_VENCIMIENTO;

    private int MONTO_CUOTA;

    private int MONTO_INTERES;

    private int SEGURO_DESGRAVAMEN;

    private int SEGURO_CESANTIA;

    private int SALDO_CAPITAL;

    public DetalleVO() {
    }

    public DetalleVO(
           int NUM_CUOTA,
           int FECHA_VENCIMIENTO,
           int MONTO_CUOTA,
           int MONTO_INTERES,
           int SEGURO_DESGRAVAMEN,
           int SEGURO_CESANTIA,
           int SALDO_CAPITAL) {
           this.NUM_CUOTA = NUM_CUOTA;
           this.FECHA_VENCIMIENTO = FECHA_VENCIMIENTO;
           this.MONTO_CUOTA = MONTO_CUOTA;
           this.MONTO_INTERES = MONTO_INTERES;
           this.SEGURO_DESGRAVAMEN = SEGURO_DESGRAVAMEN;
           this.SEGURO_CESANTIA = SEGURO_CESANTIA;
           this.SALDO_CAPITAL = SALDO_CAPITAL;
    }


    /**
     * Gets the NUM_CUOTA value for this DetalleVO.
     * 
     * @return NUM_CUOTA
     */
    public int getNUM_CUOTA() {
        return NUM_CUOTA;
    }


    /**
     * Sets the NUM_CUOTA value for this DetalleVO.
     * 
     * @param NUM_CUOTA
     */
    public void setNUM_CUOTA(int NUM_CUOTA) {
        this.NUM_CUOTA = NUM_CUOTA;
    }


    /**
     * Gets the FECHA_VENCIMIENTO value for this DetalleVO.
     * 
     * @return FECHA_VENCIMIENTO
     */
    public int getFECHA_VENCIMIENTO() {
        return FECHA_VENCIMIENTO;
    }


    /**
     * Sets the FECHA_VENCIMIENTO value for this DetalleVO.
     * 
     * @param FECHA_VENCIMIENTO
     */
    public void setFECHA_VENCIMIENTO(int FECHA_VENCIMIENTO) {
        this.FECHA_VENCIMIENTO = FECHA_VENCIMIENTO;
    }


    /**
     * Gets the MONTO_CUOTA value for this DetalleVO.
     * 
     * @return MONTO_CUOTA
     */
    public int getMONTO_CUOTA() {
        return MONTO_CUOTA;
    }


    /**
     * Sets the MONTO_CUOTA value for this DetalleVO.
     * 
     * @param MONTO_CUOTA
     */
    public void setMONTO_CUOTA(int MONTO_CUOTA) {
        this.MONTO_CUOTA = MONTO_CUOTA;
    }


    /**
     * Gets the MONTO_INTERES value for this DetalleVO.
     * 
     * @return MONTO_INTERES
     */
    public int getMONTO_INTERES() {
        return MONTO_INTERES;
    }


    /**
     * Sets the MONTO_INTERES value for this DetalleVO.
     * 
     * @param MONTO_INTERES
     */
    public void setMONTO_INTERES(int MONTO_INTERES) {
        this.MONTO_INTERES = MONTO_INTERES;
    }


    /**
     * Gets the SEGURO_DESGRAVAMEN value for this DetalleVO.
     * 
     * @return SEGURO_DESGRAVAMEN
     */
    public int getSEGURO_DESGRAVAMEN() {
        return SEGURO_DESGRAVAMEN;
    }


    /**
     * Sets the SEGURO_DESGRAVAMEN value for this DetalleVO.
     * 
     * @param SEGURO_DESGRAVAMEN
     */
    public void setSEGURO_DESGRAVAMEN(int SEGURO_DESGRAVAMEN) {
        this.SEGURO_DESGRAVAMEN = SEGURO_DESGRAVAMEN;
    }


    /**
     * Gets the SEGURO_CESANTIA value for this DetalleVO.
     * 
     * @return SEGURO_CESANTIA
     */
    public int getSEGURO_CESANTIA() {
        return SEGURO_CESANTIA;
    }


    /**
     * Sets the SEGURO_CESANTIA value for this DetalleVO.
     * 
     * @param SEGURO_CESANTIA
     */
    public void setSEGURO_CESANTIA(int SEGURO_CESANTIA) {
        this.SEGURO_CESANTIA = SEGURO_CESANTIA;
    }


    /**
     * Gets the SALDO_CAPITAL value for this DetalleVO.
     * 
     * @return SALDO_CAPITAL
     */
    public int getSALDO_CAPITAL() {
        return SALDO_CAPITAL;
    }


    /**
     * Sets the SALDO_CAPITAL value for this DetalleVO.
     * 
     * @param SALDO_CAPITAL
     */
    public void setSALDO_CAPITAL(int SALDO_CAPITAL) {
        this.SALDO_CAPITAL = SALDO_CAPITAL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DetalleVO)) return false;
        DetalleVO other = (DetalleVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.NUM_CUOTA == other.getNUM_CUOTA() &&
            this.FECHA_VENCIMIENTO == other.getFECHA_VENCIMIENTO() &&
            this.MONTO_CUOTA == other.getMONTO_CUOTA() &&
            this.MONTO_INTERES == other.getMONTO_INTERES() &&
            this.SEGURO_DESGRAVAMEN == other.getSEGURO_DESGRAVAMEN() &&
            this.SEGURO_CESANTIA == other.getSEGURO_CESANTIA() &&
            this.SALDO_CAPITAL == other.getSALDO_CAPITAL();
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
        _hashCode += getNUM_CUOTA();
        _hashCode += getFECHA_VENCIMIENTO();
        _hashCode += getMONTO_CUOTA();
        _hashCode += getMONTO_INTERES();
        _hashCode += getSEGURO_DESGRAVAMEN();
        _hashCode += getSEGURO_CESANTIA();
        _hashCode += getSALDO_CAPITAL();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DetalleVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/simuladorCreditoSocial", "detalleVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUM_CUOTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NUM_CUOTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHA_VENCIMIENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHA_VENCIMIENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_CUOTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_CUOTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_INTERES");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_INTERES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEGURO_DESGRAVAMEN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEGURO_DESGRAVAMEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEGURO_CESANTIA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEGURO_CESANTIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SALDO_CAPITAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SALDO_CAPITAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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
