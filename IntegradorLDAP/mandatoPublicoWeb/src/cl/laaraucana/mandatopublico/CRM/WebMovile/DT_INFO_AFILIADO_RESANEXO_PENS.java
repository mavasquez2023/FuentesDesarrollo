/**
 * DT_INFO_AFILIADO_RESANEXO_PENS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.mandatopublico.CRM.WebMovile;

public class DT_INFO_AFILIADO_RESANEXO_PENS  implements java.io.Serializable {
    private java.lang.String PARTNER;

    private java.lang.String NOMBRE;

    private java.lang.String INSCRIPCION;

    public DT_INFO_AFILIADO_RESANEXO_PENS() {
    }

    public DT_INFO_AFILIADO_RESANEXO_PENS(
           java.lang.String PARTNER,
           java.lang.String NOMBRE,
           java.lang.String INSCRIPCION) {
           this.PARTNER = PARTNER;
           this.NOMBRE = NOMBRE;
           this.INSCRIPCION = INSCRIPCION;
    }


    /**
     * Gets the PARTNER value for this DT_INFO_AFILIADO_RESANEXO_PENS.
     * 
     * @return PARTNER
     */
    public java.lang.String getPARTNER() {
        return PARTNER;
    }


    /**
     * Sets the PARTNER value for this DT_INFO_AFILIADO_RESANEXO_PENS.
     * 
     * @param PARTNER
     */
    public void setPARTNER(java.lang.String PARTNER) {
        this.PARTNER = PARTNER;
    }


    /**
     * Gets the NOMBRE value for this DT_INFO_AFILIADO_RESANEXO_PENS.
     * 
     * @return NOMBRE
     */
    public java.lang.String getNOMBRE() {
        return NOMBRE;
    }


    /**
     * Sets the NOMBRE value for this DT_INFO_AFILIADO_RESANEXO_PENS.
     * 
     * @param NOMBRE
     */
    public void setNOMBRE(java.lang.String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }


    /**
     * Gets the INSCRIPCION value for this DT_INFO_AFILIADO_RESANEXO_PENS.
     * 
     * @return INSCRIPCION
     */
    public java.lang.String getINSCRIPCION() {
        return INSCRIPCION;
    }


    /**
     * Sets the INSCRIPCION value for this DT_INFO_AFILIADO_RESANEXO_PENS.
     * 
     * @param INSCRIPCION
     */
    public void setINSCRIPCION(java.lang.String INSCRIPCION) {
        this.INSCRIPCION = INSCRIPCION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_INFO_AFILIADO_RESANEXO_PENS)) return false;
        DT_INFO_AFILIADO_RESANEXO_PENS other = (DT_INFO_AFILIADO_RESANEXO_PENS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.PARTNER==null && other.getPARTNER()==null) || 
             (this.PARTNER!=null &&
              this.PARTNER.equals(other.getPARTNER()))) &&
            ((this.NOMBRE==null && other.getNOMBRE()==null) || 
             (this.NOMBRE!=null &&
              this.NOMBRE.equals(other.getNOMBRE()))) &&
            ((this.INSCRIPCION==null && other.getINSCRIPCION()==null) || 
             (this.INSCRIPCION!=null &&
              this.INSCRIPCION.equals(other.getINSCRIPCION())));
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
        if (getPARTNER() != null) {
            _hashCode += getPARTNER().hashCode();
        }
        if (getNOMBRE() != null) {
            _hashCode += getNOMBRE().hashCode();
        }
        if (getINSCRIPCION() != null) {
            _hashCode += getINSCRIPCION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_INFO_AFILIADO_RESANEXO_PENS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", ">DT_INFO_AFILIADO_RES>ANEXO_PENS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PARTNER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PARTNER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("INSCRIPCION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INSCRIPCION"));
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
