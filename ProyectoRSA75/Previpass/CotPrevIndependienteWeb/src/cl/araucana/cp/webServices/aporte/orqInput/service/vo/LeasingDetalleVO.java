/**
 * LeasingDetalleVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.cp.webServices.aporte.orqInput.service.vo;

public class LeasingDetalleVO  implements java.io.Serializable {
    private int codigoOficina;

    private int fechaVencimiento;

    private int monto;

    private long montoUF;

    private java.lang.String numCuenta;

    public LeasingDetalleVO() {
    }

    public LeasingDetalleVO(
           int codigoOficina,
           int fechaVencimiento,
           int monto,
           long montoUF,
           java.lang.String numCuenta) {
           this.codigoOficina = codigoOficina;
           this.fechaVencimiento = fechaVencimiento;
           this.monto = monto;
           this.montoUF = montoUF;
           this.numCuenta = numCuenta;
    }


    /**
     * Gets the codigoOficina value for this LeasingDetalleVO.
     * 
     * @return codigoOficina
     */
    public int getCodigoOficina() {
        return codigoOficina;
    }


    /**
     * Sets the codigoOficina value for this LeasingDetalleVO.
     * 
     * @param codigoOficina
     */
    public void setCodigoOficina(int codigoOficina) {
        this.codigoOficina = codigoOficina;
    }


    /**
     * Gets the fechaVencimiento value for this LeasingDetalleVO.
     * 
     * @return fechaVencimiento
     */
    public int getFechaVencimiento() {
        return fechaVencimiento;
    }


    /**
     * Sets the fechaVencimiento value for this LeasingDetalleVO.
     * 
     * @param fechaVencimiento
     */
    public void setFechaVencimiento(int fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }


    /**
     * Gets the monto value for this LeasingDetalleVO.
     * 
     * @return monto
     */
    public int getMonto() {
        return monto;
    }


    /**
     * Sets the monto value for this LeasingDetalleVO.
     * 
     * @param monto
     */
    public void setMonto(int monto) {
        this.monto = monto;
    }


    /**
     * Gets the montoUF value for this LeasingDetalleVO.
     * 
     * @return montoUF
     */
    public long getMontoUF() {
        return montoUF;
    }


    /**
     * Sets the montoUF value for this LeasingDetalleVO.
     * 
     * @param montoUF
     */
    public void setMontoUF(long montoUF) {
        this.montoUF = montoUF;
    }


    /**
     * Gets the numCuenta value for this LeasingDetalleVO.
     * 
     * @return numCuenta
     */
    public java.lang.String getNumCuenta() {
        return numCuenta;
    }


    /**
     * Sets the numCuenta value for this LeasingDetalleVO.
     * 
     * @param numCuenta
     */
    public void setNumCuenta(java.lang.String numCuenta) {
        this.numCuenta = numCuenta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LeasingDetalleVO)) return false;
        LeasingDetalleVO other = (LeasingDetalleVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.codigoOficina == other.getCodigoOficina() &&
            this.fechaVencimiento == other.getFechaVencimiento() &&
            this.monto == other.getMonto() &&
            this.montoUF == other.getMontoUF() &&
            ((this.numCuenta==null && other.getNumCuenta()==null) || 
             (this.numCuenta!=null &&
              this.numCuenta.equals(other.getNumCuenta())));
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
        _hashCode += getCodigoOficina();
        _hashCode += getFechaVencimiento();
        _hashCode += getMonto();
        _hashCode += new Long(getMontoUF()).hashCode();
        if (getNumCuenta() != null) {
            _hashCode += getNumCuenta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LeasingDetalleVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "LeasingDetalleVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoOficina");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "codigoOficina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaVencimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "fechaVencimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "monto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoUF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "montoUF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numCuenta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "numCuenta"));
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
