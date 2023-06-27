/**
 * LeasingResultVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.cp.webServices.aporte.orqInput.service.vo;

public class LeasingResultVO  implements java.io.Serializable {
    private cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingDetalleVO[] leasingDetalle;

    private int monto;

    private int numRegistros;

    public LeasingResultVO() {
    }

    public LeasingResultVO(
           cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingDetalleVO[] leasingDetalle,
           int monto,
           int numRegistros) {
           this.leasingDetalle = leasingDetalle;
           this.monto = monto;
           this.numRegistros = numRegistros;
    }


    /**
     * Gets the leasingDetalle value for this LeasingResultVO.
     * 
     * @return leasingDetalle
     */
    public cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingDetalleVO[] getLeasingDetalle() {
        return leasingDetalle;
    }


    /**
     * Sets the leasingDetalle value for this LeasingResultVO.
     * 
     * @param leasingDetalle
     */
    public void setLeasingDetalle(cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingDetalleVO[] leasingDetalle) {
        this.leasingDetalle = leasingDetalle;
    }


    /**
     * Gets the monto value for this LeasingResultVO.
     * 
     * @return monto
     */
    public int getMonto() {
        return monto;
    }


    /**
     * Sets the monto value for this LeasingResultVO.
     * 
     * @param monto
     */
    public void setMonto(int monto) {
        this.monto = monto;
    }


    /**
     * Gets the numRegistros value for this LeasingResultVO.
     * 
     * @return numRegistros
     */
    public int getNumRegistros() {
        return numRegistros;
    }


    /**
     * Sets the numRegistros value for this LeasingResultVO.
     * 
     * @param numRegistros
     */
    public void setNumRegistros(int numRegistros) {
        this.numRegistros = numRegistros;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LeasingResultVO)) return false;
        LeasingResultVO other = (LeasingResultVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.leasingDetalle==null && other.getLeasingDetalle()==null) || 
             (this.leasingDetalle!=null &&
              java.util.Arrays.equals(this.leasingDetalle, other.getLeasingDetalle()))) &&
            this.monto == other.getMonto() &&
            this.numRegistros == other.getNumRegistros();
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
        if (getLeasingDetalle() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLeasingDetalle());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLeasingDetalle(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getMonto();
        _hashCode += getNumRegistros();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LeasingResultVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "LeasingResultVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("leasingDetalle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "leasingDetalle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "LeasingDetalleVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://service.orqInput.aporte.araucana.cl", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "monto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numRegistros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "numRegistros"));
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
