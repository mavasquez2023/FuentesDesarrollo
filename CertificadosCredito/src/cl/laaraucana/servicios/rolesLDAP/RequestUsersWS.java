/**
 * RequestUsersWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.rolesLDAP;

public class RequestUsersWS  implements java.io.Serializable {
    private java.lang.String rolId;

    private java.lang.String appId;

    public RequestUsersWS() {
    }

    public RequestUsersWS(
           java.lang.String rolId,
           java.lang.String appId) {
           this.rolId = rolId;
           this.appId = appId;
    }


    /**
     * Gets the rolId value for this RequestUsersWS.
     * 
     * @return rolId
     */
    public java.lang.String getRolId() {
        return rolId;
    }


    /**
     * Sets the rolId value for this RequestUsersWS.
     * 
     * @param rolId
     */
    public void setRolId(java.lang.String rolId) {
        this.rolId = rolId;
    }


    /**
     * Gets the appId value for this RequestUsersWS.
     * 
     * @return appId
     */
    public java.lang.String getAppId() {
        return appId;
    }


    /**
     * Sets the appId value for this RequestUsersWS.
     * 
     * @param appId
     */
    public void setAppId(java.lang.String appId) {
        this.appId = appId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RequestUsersWS)) return false;
        RequestUsersWS other = (RequestUsersWS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rolId==null && other.getRolId()==null) || 
             (this.rolId!=null &&
              this.rolId.equals(other.getRolId()))) &&
            ((this.appId==null && other.getAppId()==null) || 
             (this.appId!=null &&
              this.appId.equals(other.getAppId())));
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
        if (getRolId() != null) {
            _hashCode += getRolId().hashCode();
        }
        if (getAppId() != null) {
            _hashCode += getAppId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RequestUsersWS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/rolesLDAP", "requestUsersWS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rolId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rolId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appId"));
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
