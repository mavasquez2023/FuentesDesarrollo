/**
 * UsersRol.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.rolesLDAP;

public class UsersRol  implements java.io.Serializable {
    private int CODIGO_RESPUESTA;

    private java.lang.String[] USERS;

    public UsersRol() {
    }

    public UsersRol(
           int CODIGO_RESPUESTA,
           java.lang.String[] USERS) {
           this.CODIGO_RESPUESTA = CODIGO_RESPUESTA;
           this.USERS = USERS;
    }


    /**
     * Gets the CODIGO_RESPUESTA value for this UsersRol.
     * 
     * @return CODIGO_RESPUESTA
     */
    public int getCODIGO_RESPUESTA() {
        return CODIGO_RESPUESTA;
    }


    /**
     * Sets the CODIGO_RESPUESTA value for this UsersRol.
     * 
     * @param CODIGO_RESPUESTA
     */
    public void setCODIGO_RESPUESTA(int CODIGO_RESPUESTA) {
        this.CODIGO_RESPUESTA = CODIGO_RESPUESTA;
    }


    /**
     * Gets the USERS value for this UsersRol.
     * 
     * @return USERS
     */
    public java.lang.String[] getUSERS() {
        return USERS;
    }


    /**
     * Sets the USERS value for this UsersRol.
     * 
     * @param USERS
     */
    public void setUSERS(java.lang.String[] USERS) {
        this.USERS = USERS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UsersRol)) return false;
        UsersRol other = (UsersRol) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.CODIGO_RESPUESTA == other.getCODIGO_RESPUESTA() &&
            ((this.USERS==null && other.getUSERS()==null) || 
             (this.USERS!=null &&
              java.util.Arrays.equals(this.USERS, other.getUSERS())));
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
        _hashCode += getCODIGO_RESPUESTA();
        if (getUSERS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUSERS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUSERS(), i);
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
        new org.apache.axis.description.TypeDesc(UsersRol.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/rolesLDAP", "usersRol"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGO_RESPUESTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODIGO_RESPUESTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USERS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "USERS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "USERID"));
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
