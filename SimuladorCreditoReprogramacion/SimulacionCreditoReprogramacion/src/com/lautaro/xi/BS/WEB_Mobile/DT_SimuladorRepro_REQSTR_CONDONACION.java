/**
 * DT_SimuladorRepro_REQSTR_CONDONACION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;

public class DT_SimuladorRepro_REQSTR_CONDONACION  implements java.io.Serializable {
    private java.math.BigDecimal PORCEN_CAPITAL;

    private java.math.BigDecimal PORCEN_HONO;

    private java.math.BigDecimal PORCEN_GC;

    private java.math.BigDecimal PORCEN_INT;

    private java.math.BigDecimal PORCEN_GRV;

    public DT_SimuladorRepro_REQSTR_CONDONACION() {
    }

    public DT_SimuladorRepro_REQSTR_CONDONACION(
           java.math.BigDecimal PORCEN_CAPITAL,
           java.math.BigDecimal PORCEN_HONO,
           java.math.BigDecimal PORCEN_GC,
           java.math.BigDecimal PORCEN_INT,
           java.math.BigDecimal PORCEN_GRV) {
           this.PORCEN_CAPITAL = PORCEN_CAPITAL;
           this.PORCEN_HONO = PORCEN_HONO;
           this.PORCEN_GC = PORCEN_GC;
           this.PORCEN_INT = PORCEN_INT;
           this.PORCEN_GRV = PORCEN_GRV;
    }


    /**
     * Gets the PORCEN_CAPITAL value for this DT_SimuladorRepro_REQSTR_CONDONACION.
     * 
     * @return PORCEN_CAPITAL
     */
    public java.math.BigDecimal getPORCEN_CAPITAL() {
        return PORCEN_CAPITAL;
    }


    /**
     * Sets the PORCEN_CAPITAL value for this DT_SimuladorRepro_REQSTR_CONDONACION.
     * 
     * @param PORCEN_CAPITAL
     */
    public void setPORCEN_CAPITAL(java.math.BigDecimal PORCEN_CAPITAL) {
        this.PORCEN_CAPITAL = PORCEN_CAPITAL;
    }


    /**
     * Gets the PORCEN_HONO value for this DT_SimuladorRepro_REQSTR_CONDONACION.
     * 
     * @return PORCEN_HONO
     */
    public java.math.BigDecimal getPORCEN_HONO() {
        return PORCEN_HONO;
    }


    /**
     * Sets the PORCEN_HONO value for this DT_SimuladorRepro_REQSTR_CONDONACION.
     * 
     * @param PORCEN_HONO
     */
    public void setPORCEN_HONO(java.math.BigDecimal PORCEN_HONO) {
        this.PORCEN_HONO = PORCEN_HONO;
    }


    /**
     * Gets the PORCEN_GC value for this DT_SimuladorRepro_REQSTR_CONDONACION.
     * 
     * @return PORCEN_GC
     */
    public java.math.BigDecimal getPORCEN_GC() {
        return PORCEN_GC;
    }


    /**
     * Sets the PORCEN_GC value for this DT_SimuladorRepro_REQSTR_CONDONACION.
     * 
     * @param PORCEN_GC
     */
    public void setPORCEN_GC(java.math.BigDecimal PORCEN_GC) {
        this.PORCEN_GC = PORCEN_GC;
    }


    /**
     * Gets the PORCEN_INT value for this DT_SimuladorRepro_REQSTR_CONDONACION.
     * 
     * @return PORCEN_INT
     */
    public java.math.BigDecimal getPORCEN_INT() {
        return PORCEN_INT;
    }


    /**
     * Sets the PORCEN_INT value for this DT_SimuladorRepro_REQSTR_CONDONACION.
     * 
     * @param PORCEN_INT
     */
    public void setPORCEN_INT(java.math.BigDecimal PORCEN_INT) {
        this.PORCEN_INT = PORCEN_INT;
    }


    /**
     * Gets the PORCEN_GRV value for this DT_SimuladorRepro_REQSTR_CONDONACION.
     * 
     * @return PORCEN_GRV
     */
    public java.math.BigDecimal getPORCEN_GRV() {
        return PORCEN_GRV;
    }


    /**
     * Sets the PORCEN_GRV value for this DT_SimuladorRepro_REQSTR_CONDONACION.
     * 
     * @param PORCEN_GRV
     */
    public void setPORCEN_GRV(java.math.BigDecimal PORCEN_GRV) {
        this.PORCEN_GRV = PORCEN_GRV;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SimuladorRepro_REQSTR_CONDONACION)) return false;
        DT_SimuladorRepro_REQSTR_CONDONACION other = (DT_SimuladorRepro_REQSTR_CONDONACION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.PORCEN_CAPITAL==null && other.getPORCEN_CAPITAL()==null) || 
             (this.PORCEN_CAPITAL!=null &&
              this.PORCEN_CAPITAL.equals(other.getPORCEN_CAPITAL()))) &&
            ((this.PORCEN_HONO==null && other.getPORCEN_HONO()==null) || 
             (this.PORCEN_HONO!=null &&
              this.PORCEN_HONO.equals(other.getPORCEN_HONO()))) &&
            ((this.PORCEN_GC==null && other.getPORCEN_GC()==null) || 
             (this.PORCEN_GC!=null &&
              this.PORCEN_GC.equals(other.getPORCEN_GC()))) &&
            ((this.PORCEN_INT==null && other.getPORCEN_INT()==null) || 
             (this.PORCEN_INT!=null &&
              this.PORCEN_INT.equals(other.getPORCEN_INT()))) &&
            ((this.PORCEN_GRV==null && other.getPORCEN_GRV()==null) || 
             (this.PORCEN_GRV!=null &&
              this.PORCEN_GRV.equals(other.getPORCEN_GRV())));
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
        if (getPORCEN_CAPITAL() != null) {
            _hashCode += getPORCEN_CAPITAL().hashCode();
        }
        if (getPORCEN_HONO() != null) {
            _hashCode += getPORCEN_HONO().hashCode();
        }
        if (getPORCEN_GC() != null) {
            _hashCode += getPORCEN_GC().hashCode();
        }
        if (getPORCEN_INT() != null) {
            _hashCode += getPORCEN_INT().hashCode();
        }
        if (getPORCEN_GRV() != null) {
            _hashCode += getPORCEN_GRV().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SimuladorRepro_REQSTR_CONDONACION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", ">DT_SimuladorRepro_REQ>STR_CONDONACION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PORCEN_CAPITAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PORCEN_CAPITAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PORCEN_HONO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PORCEN_HONO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PORCEN_GC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PORCEN_GC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PORCEN_INT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PORCEN_INT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PORCEN_GRV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PORCEN_GRV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
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
