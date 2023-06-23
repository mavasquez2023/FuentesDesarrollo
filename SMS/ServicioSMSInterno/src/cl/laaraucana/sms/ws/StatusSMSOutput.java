/**
 * StatusSMSOutput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.sms.ws;

public class StatusSMSOutput  implements java.io.Serializable {
    private java.lang.String dateReceived;

    private java.lang.String dateSend;

    private java.lang.String dv;

    private java.lang.String id;

    private java.lang.String phone;

    private java.lang.String rut;

    private java.lang.String status;

    private java.lang.String statusCode;

    private java.lang.String statusDescription;

    private java.lang.String username;

    public StatusSMSOutput() {
    }

    public StatusSMSOutput(
           java.lang.String dateReceived,
           java.lang.String dateSend,
           java.lang.String dv,
           java.lang.String id,
           java.lang.String phone,
           java.lang.String rut,
           java.lang.String status,
           java.lang.String statusCode,
           java.lang.String statusDescription,
           java.lang.String username) {
           this.dateReceived = dateReceived;
           this.dateSend = dateSend;
           this.dv = dv;
           this.id = id;
           this.phone = phone;
           this.rut = rut;
           this.status = status;
           this.statusCode = statusCode;
           this.statusDescription = statusDescription;
           this.username = username;
    }


    /**
     * Gets the dateReceived value for this StatusSMSOutput.
     * 
     * @return dateReceived
     */
    public java.lang.String getDateReceived() {
        return dateReceived;
    }


    /**
     * Sets the dateReceived value for this StatusSMSOutput.
     * 
     * @param dateReceived
     */
    public void setDateReceived(java.lang.String dateReceived) {
        this.dateReceived = dateReceived;
    }


    /**
     * Gets the dateSend value for this StatusSMSOutput.
     * 
     * @return dateSend
     */
    public java.lang.String getDateSend() {
        return dateSend;
    }


    /**
     * Sets the dateSend value for this StatusSMSOutput.
     * 
     * @param dateSend
     */
    public void setDateSend(java.lang.String dateSend) {
        this.dateSend = dateSend;
    }


    /**
     * Gets the dv value for this StatusSMSOutput.
     * 
     * @return dv
     */
    public java.lang.String getDv() {
        return dv;
    }


    /**
     * Sets the dv value for this StatusSMSOutput.
     * 
     * @param dv
     */
    public void setDv(java.lang.String dv) {
        this.dv = dv;
    }


    /**
     * Gets the id value for this StatusSMSOutput.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this StatusSMSOutput.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the phone value for this StatusSMSOutput.
     * 
     * @return phone
     */
    public java.lang.String getPhone() {
        return phone;
    }


    /**
     * Sets the phone value for this StatusSMSOutput.
     * 
     * @param phone
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }


    /**
     * Gets the rut value for this StatusSMSOutput.
     * 
     * @return rut
     */
    public java.lang.String getRut() {
        return rut;
    }


    /**
     * Sets the rut value for this StatusSMSOutput.
     * 
     * @param rut
     */
    public void setRut(java.lang.String rut) {
        this.rut = rut;
    }


    /**
     * Gets the status value for this StatusSMSOutput.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this StatusSMSOutput.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the statusCode value for this StatusSMSOutput.
     * 
     * @return statusCode
     */
    public java.lang.String getStatusCode() {
        return statusCode;
    }


    /**
     * Sets the statusCode value for this StatusSMSOutput.
     * 
     * @param statusCode
     */
    public void setStatusCode(java.lang.String statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * Gets the statusDescription value for this StatusSMSOutput.
     * 
     * @return statusDescription
     */
    public java.lang.String getStatusDescription() {
        return statusDescription;
    }


    /**
     * Sets the statusDescription value for this StatusSMSOutput.
     * 
     * @param statusDescription
     */
    public void setStatusDescription(java.lang.String statusDescription) {
        this.statusDescription = statusDescription;
    }


    /**
     * Gets the username value for this StatusSMSOutput.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this StatusSMSOutput.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatusSMSOutput)) return false;
        StatusSMSOutput other = (StatusSMSOutput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dateReceived==null && other.getDateReceived()==null) || 
             (this.dateReceived!=null &&
              this.dateReceived.equals(other.getDateReceived()))) &&
            ((this.dateSend==null && other.getDateSend()==null) || 
             (this.dateSend!=null &&
              this.dateSend.equals(other.getDateSend()))) &&
            ((this.dv==null && other.getDv()==null) || 
             (this.dv!=null &&
              this.dv.equals(other.getDv()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.phone==null && other.getPhone()==null) || 
             (this.phone!=null &&
              this.phone.equals(other.getPhone()))) &&
            ((this.rut==null && other.getRut()==null) || 
             (this.rut!=null &&
              this.rut.equals(other.getRut()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.statusCode==null && other.getStatusCode()==null) || 
             (this.statusCode!=null &&
              this.statusCode.equals(other.getStatusCode()))) &&
            ((this.statusDescription==null && other.getStatusDescription()==null) || 
             (this.statusDescription!=null &&
              this.statusDescription.equals(other.getStatusDescription()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername())));
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
        if (getDateReceived() != null) {
            _hashCode += getDateReceived().hashCode();
        }
        if (getDateSend() != null) {
            _hashCode += getDateSend().hashCode();
        }
        if (getDv() != null) {
            _hashCode += getDv().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getPhone() != null) {
            _hashCode += getPhone().hashCode();
        }
        if (getRut() != null) {
            _hashCode += getRut().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getStatusCode() != null) {
            _hashCode += getStatusCode().hashCode();
        }
        if (getStatusDescription() != null) {
            _hashCode += getStatusDescription().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatusSMSOutput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.sms.laaraucana.cl/", "statusSMSOutput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateReceived");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateReceived"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateSend");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateSend"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rut");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
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
