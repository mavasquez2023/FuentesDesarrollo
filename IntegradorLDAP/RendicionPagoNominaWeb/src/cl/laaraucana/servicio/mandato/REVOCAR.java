/**
 * REVOCAR.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicio.mandato;

public class REVOCAR  implements java.io.Serializable {
    private int RUT;

    private java.lang.String DV;

    private java.lang.String NOMBRE;

    private java.lang.String EMAIL;

    private java.lang.String MENSAJE;

    public REVOCAR() {
    }

    public REVOCAR(
           int RUT,
           java.lang.String DV,
           java.lang.String NOMBRE,
           java.lang.String EMAIL,
           java.lang.String MENSAJE) {
           this.RUT = RUT;
           this.DV = DV;
           this.NOMBRE = NOMBRE;
           this.EMAIL = EMAIL;
           this.MENSAJE = MENSAJE;
    }


    /**
     * Gets the RUT value for this REVOCAR.
     * 
     * @return RUT
     */
    public int getRUT() {
        return RUT;
    }


    /**
     * Sets the RUT value for this REVOCAR.
     * 
     * @param RUT
     */
    public void setRUT(int RUT) {
        this.RUT = RUT;
    }


    /**
     * Gets the DV value for this REVOCAR.
     * 
     * @return DV
     */
    public java.lang.String getDV() {
        return DV;
    }


    /**
     * Sets the DV value for this REVOCAR.
     * 
     * @param DV
     */
    public void setDV(java.lang.String DV) {
        this.DV = DV;
    }


    /**
     * Gets the NOMBRE value for this REVOCAR.
     * 
     * @return NOMBRE
     */
    public java.lang.String getNOMBRE() {
        return NOMBRE;
    }


    /**
     * Sets the NOMBRE value for this REVOCAR.
     * 
     * @param NOMBRE
     */
    public void setNOMBRE(java.lang.String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }


    /**
     * Gets the EMAIL value for this REVOCAR.
     * 
     * @return EMAIL
     */
    public java.lang.String getEMAIL() {
        return EMAIL;
    }


    /**
     * Sets the EMAIL value for this REVOCAR.
     * 
     * @param EMAIL
     */
    public void setEMAIL(java.lang.String EMAIL) {
        this.EMAIL = EMAIL;
    }


    /**
     * Gets the MENSAJE value for this REVOCAR.
     * 
     * @return MENSAJE
     */
    public java.lang.String getMENSAJE() {
        return MENSAJE;
    }


    /**
     * Sets the MENSAJE value for this REVOCAR.
     * 
     * @param MENSAJE
     */
    public void setMENSAJE(java.lang.String MENSAJE) {
        this.MENSAJE = MENSAJE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof REVOCAR)) return false;
        REVOCAR other = (REVOCAR) obj;
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
            ((this.NOMBRE==null && other.getNOMBRE()==null) || 
             (this.NOMBRE!=null &&
              this.NOMBRE.equals(other.getNOMBRE()))) &&
            ((this.EMAIL==null && other.getEMAIL()==null) || 
             (this.EMAIL!=null &&
              this.EMAIL.equals(other.getEMAIL()))) &&
            ((this.MENSAJE==null && other.getMENSAJE()==null) || 
             (this.MENSAJE!=null &&
              this.MENSAJE.equals(other.getMENSAJE())));
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
        if (getNOMBRE() != null) {
            _hashCode += getNOMBRE().hashCode();
        }
        if (getEMAIL() != null) {
            _hashCode += getEMAIL().hashCode();
        }
        if (getMENSAJE() != null) {
            _hashCode += getMENSAJE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(REVOCAR.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.laaraucana.cl/mandato", "REVOCAR"));
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
        elemField.setFieldName("NOMBRE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NOMBRE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMAIL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EMAIL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MENSAJE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MENSAJE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
