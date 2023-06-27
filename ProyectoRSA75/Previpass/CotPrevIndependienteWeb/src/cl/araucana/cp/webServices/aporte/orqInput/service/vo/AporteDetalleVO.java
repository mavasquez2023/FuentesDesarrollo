/**
 * AporteDetalleVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.cp.webServices.aporte.orqInput.service.vo;

public class AporteDetalleVO  implements java.io.Serializable {
    private int fechaVencimiento;

    private int interesesReajuste;

    private int monto;

    private int rentaPromedio;

    public AporteDetalleVO() {
    }

    public AporteDetalleVO(
           int fechaVencimiento,
           int interesesReajuste,
           int monto,
           int rentaPromedio) {
           this.fechaVencimiento = fechaVencimiento;
           this.interesesReajuste = interesesReajuste;
           this.monto = monto;
           this.rentaPromedio = rentaPromedio;
    }


    /**
     * Gets the fechaVencimiento value for this AporteDetalleVO.
     * 
     * @return fechaVencimiento
     */
    public int getFechaVencimiento() {
        return fechaVencimiento;
    }


    /**
     * Sets the fechaVencimiento value for this AporteDetalleVO.
     * 
     * @param fechaVencimiento
     */
    public void setFechaVencimiento(int fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }


    /**
     * Gets the interesesReajuste value for this AporteDetalleVO.
     * 
     * @return interesesReajuste
     */
    public int getInteresesReajuste() {
        return interesesReajuste;
    }


    /**
     * Sets the interesesReajuste value for this AporteDetalleVO.
     * 
     * @param interesesReajuste
     */
    public void setInteresesReajuste(int interesesReajuste) {
        this.interesesReajuste = interesesReajuste;
    }


    /**
     * Gets the monto value for this AporteDetalleVO.
     * 
     * @return monto
     */
    public int getMonto() {
        return monto;
    }


    /**
     * Sets the monto value for this AporteDetalleVO.
     * 
     * @param monto
     */
    public void setMonto(int monto) {
        this.monto = monto;
    }


    /**
     * Gets the rentaPromedio value for this AporteDetalleVO.
     * 
     * @return rentaPromedio
     */
    public int getRentaPromedio() {
        return rentaPromedio;
    }


    /**
     * Sets the rentaPromedio value for this AporteDetalleVO.
     * 
     * @param rentaPromedio
     */
    public void setRentaPromedio(int rentaPromedio) {
        this.rentaPromedio = rentaPromedio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AporteDetalleVO)) return false;
        AporteDetalleVO other = (AporteDetalleVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.fechaVencimiento == other.getFechaVencimiento() &&
            this.interesesReajuste == other.getInteresesReajuste() &&
            this.monto == other.getMonto() &&
            this.rentaPromedio == other.getRentaPromedio();
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
        _hashCode += getFechaVencimiento();
        _hashCode += getInteresesReajuste();
        _hashCode += getMonto();
        _hashCode += getRentaPromedio();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AporteDetalleVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "AporteDetalleVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaVencimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "fechaVencimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interesesReajuste");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "interesesReajuste"));
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
        elemField.setFieldName("rentaPromedio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "rentaPromedio"));
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
