/**
 * CuentaEdit.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicio.transferencias;

public class CuentaEdit  implements java.io.Serializable {
    private int RUT;

    private java.lang.String DV;

    private int COD_BANCO;

    private java.lang.String NUM_CUENTA;

    private java.lang.String TIPO_PRODUCTO;

    public CuentaEdit() {
    }

    public CuentaEdit(
           int RUT,
           java.lang.String DV,
           int COD_BANCO,
           java.lang.String NUM_CUENTA,
           java.lang.String TIPO_PRODUCTO) {
           this.RUT = RUT;
           this.DV = DV;
           this.COD_BANCO = COD_BANCO;
           this.NUM_CUENTA = NUM_CUENTA;
           this.TIPO_PRODUCTO = TIPO_PRODUCTO;
    }


    /**
     * Gets the RUT value for this CuentaEdit.
     * 
     * @return RUT
     */
    public int getRUT() {
        return RUT;
    }


    /**
     * Sets the RUT value for this CuentaEdit.
     * 
     * @param RUT
     */
    public void setRUT(int RUT) {
        this.RUT = RUT;
    }


    /**
     * Gets the DV value for this CuentaEdit.
     * 
     * @return DV
     */
    public java.lang.String getDV() {
        return DV;
    }


    /**
     * Sets the DV value for this CuentaEdit.
     * 
     * @param DV
     */
    public void setDV(java.lang.String DV) {
        this.DV = DV;
    }


    /**
     * Gets the COD_BANCO value for this CuentaEdit.
     * 
     * @return COD_BANCO
     */
    public int getCOD_BANCO() {
        return COD_BANCO;
    }


    /**
     * Sets the COD_BANCO value for this CuentaEdit.
     * 
     * @param COD_BANCO
     */
    public void setCOD_BANCO(int COD_BANCO) {
        this.COD_BANCO = COD_BANCO;
    }


    /**
     * Gets the NUM_CUENTA value for this CuentaEdit.
     * 
     * @return NUM_CUENTA
     */
    public java.lang.String getNUM_CUENTA() {
        return NUM_CUENTA;
    }


    /**
     * Sets the NUM_CUENTA value for this CuentaEdit.
     * 
     * @param NUM_CUENTA
     */
    public void setNUM_CUENTA(java.lang.String NUM_CUENTA) {
        this.NUM_CUENTA = NUM_CUENTA;
    }


    /**
     * Gets the TIPO_PRODUCTO value for this CuentaEdit.
     * 
     * @return TIPO_PRODUCTO
     */
    public java.lang.String getTIPO_PRODUCTO() {
        return TIPO_PRODUCTO;
    }


    /**
     * Sets the TIPO_PRODUCTO value for this CuentaEdit.
     * 
     * @param TIPO_PRODUCTO
     */
    public void setTIPO_PRODUCTO(java.lang.String TIPO_PRODUCTO) {
        this.TIPO_PRODUCTO = TIPO_PRODUCTO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CuentaEdit)) return false;
        CuentaEdit other = (CuentaEdit) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.RUT == other.getRUT() &&
            ((this.DV==null && other.getDV()==null) || 
             (this.DV!=null &&
              this.DV.equals(other.getDV()))) &&
            this.COD_BANCO == other.getCOD_BANCO() &&
            ((this.NUM_CUENTA==null && other.getNUM_CUENTA()==null) || 
             (this.NUM_CUENTA!=null &&
              this.NUM_CUENTA.equals(other.getNUM_CUENTA()))) &&
            ((this.TIPO_PRODUCTO==null && other.getTIPO_PRODUCTO()==null) || 
             (this.TIPO_PRODUCTO!=null &&
              this.TIPO_PRODUCTO.equals(other.getTIPO_PRODUCTO())));
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
        _hashCode += getRUT();
        if (getDV() != null) {
            _hashCode += getDV().hashCode();
        }
        _hashCode += getCOD_BANCO();
        if (getNUM_CUENTA() != null) {
            _hashCode += getNUM_CUENTA().hashCode();
        }
        if (getTIPO_PRODUCTO() != null) {
            _hashCode += getTIPO_PRODUCTO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CuentaEdit.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.laaraucana.cl/cuentabancaria", "cuentaEdit"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COD_BANCO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COD_BANCO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUM_CUENTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NUM_CUENTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPO_PRODUCTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TIPO_PRODUCTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
