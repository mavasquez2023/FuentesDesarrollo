/**
 * CuentaDescripcion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicio.mandato;

public class CuentaDescripcion  implements java.io.Serializable {
    private long ID_MANDATO;

    private java.lang.String NOMBRE_AFILIADO;

    private int CODIGO_BANCO;

    private java.lang.String NUM_CUENTA;

    private int TIPO_CUENTA;

    private java.lang.String TELEFONO;

    private java.lang.String CELULAR;

    private java.lang.String EMAIL;

    public CuentaDescripcion() {
    }

    public CuentaDescripcion(
           long ID_MANDATO,
           java.lang.String NOMBRE_AFILIADO,
           int CODIGO_BANCO,
           java.lang.String NUM_CUENTA,
           int TIPO_CUENTA,
           java.lang.String TELEFONO,
           java.lang.String CELULAR,
           java.lang.String EMAIL) {
           this.ID_MANDATO = ID_MANDATO;
           this.NOMBRE_AFILIADO = NOMBRE_AFILIADO;
           this.CODIGO_BANCO = CODIGO_BANCO;
           this.NUM_CUENTA = NUM_CUENTA;
           this.TIPO_CUENTA = TIPO_CUENTA;
           this.TELEFONO = TELEFONO;
           this.CELULAR = CELULAR;
           this.EMAIL = EMAIL;
    }


    /**
     * Gets the ID_MANDATO value for this CuentaDescripcion.
     * 
     * @return ID_MANDATO
     */
    public long getID_MANDATO() {
        return ID_MANDATO;
    }


    /**
     * Sets the ID_MANDATO value for this CuentaDescripcion.
     * 
     * @param ID_MANDATO
     */
    public void setID_MANDATO(long ID_MANDATO) {
        this.ID_MANDATO = ID_MANDATO;
    }


    /**
     * Gets the NOMBRE_AFILIADO value for this CuentaDescripcion.
     * 
     * @return NOMBRE_AFILIADO
     */
    public java.lang.String getNOMBRE_AFILIADO() {
        return NOMBRE_AFILIADO;
    }


    /**
     * Sets the NOMBRE_AFILIADO value for this CuentaDescripcion.
     * 
     * @param NOMBRE_AFILIADO
     */
    public void setNOMBRE_AFILIADO(java.lang.String NOMBRE_AFILIADO) {
        this.NOMBRE_AFILIADO = NOMBRE_AFILIADO;
    }


    /**
     * Gets the CODIGO_BANCO value for this CuentaDescripcion.
     * 
     * @return CODIGO_BANCO
     */
    public int getCODIGO_BANCO() {
        return CODIGO_BANCO;
    }


    /**
     * Sets the CODIGO_BANCO value for this CuentaDescripcion.
     * 
     * @param CODIGO_BANCO
     */
    public void setCODIGO_BANCO(int CODIGO_BANCO) {
        this.CODIGO_BANCO = CODIGO_BANCO;
    }


    /**
     * Gets the NUM_CUENTA value for this CuentaDescripcion.
     * 
     * @return NUM_CUENTA
     */
    public java.lang.String getNUM_CUENTA() {
        return NUM_CUENTA;
    }


    /**
     * Sets the NUM_CUENTA value for this CuentaDescripcion.
     * 
     * @param NUM_CUENTA
     */
    public void setNUM_CUENTA(java.lang.String NUM_CUENTA) {
        this.NUM_CUENTA = NUM_CUENTA;
    }


    /**
     * Gets the TIPO_CUENTA value for this CuentaDescripcion.
     * 
     * @return TIPO_CUENTA
     */
    public int getTIPO_CUENTA() {
        return TIPO_CUENTA;
    }


    /**
     * Sets the TIPO_CUENTA value for this CuentaDescripcion.
     * 
     * @param TIPO_CUENTA
     */
    public void setTIPO_CUENTA(int TIPO_CUENTA) {
        this.TIPO_CUENTA = TIPO_CUENTA;
    }


    /**
     * Gets the TELEFONO value for this CuentaDescripcion.
     * 
     * @return TELEFONO
     */
    public java.lang.String getTELEFONO() {
        return TELEFONO;
    }


    /**
     * Sets the TELEFONO value for this CuentaDescripcion.
     * 
     * @param TELEFONO
     */
    public void setTELEFONO(java.lang.String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }


    /**
     * Gets the CELULAR value for this CuentaDescripcion.
     * 
     * @return CELULAR
     */
    public java.lang.String getCELULAR() {
        return CELULAR;
    }


    /**
     * Sets the CELULAR value for this CuentaDescripcion.
     * 
     * @param CELULAR
     */
    public void setCELULAR(java.lang.String CELULAR) {
        this.CELULAR = CELULAR;
    }


    /**
     * Gets the EMAIL value for this CuentaDescripcion.
     * 
     * @return EMAIL
     */
    public java.lang.String getEMAIL() {
        return EMAIL;
    }


    /**
     * Sets the EMAIL value for this CuentaDescripcion.
     * 
     * @param EMAIL
     */
    public void setEMAIL(java.lang.String EMAIL) {
        this.EMAIL = EMAIL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CuentaDescripcion)) return false;
        CuentaDescripcion other = (CuentaDescripcion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.ID_MANDATO == other.getID_MANDATO() &&
            ((this.NOMBRE_AFILIADO==null && other.getNOMBRE_AFILIADO()==null) || 
             (this.NOMBRE_AFILIADO!=null &&
              this.NOMBRE_AFILIADO.equals(other.getNOMBRE_AFILIADO()))) &&
            this.CODIGO_BANCO == other.getCODIGO_BANCO() &&
            ((this.NUM_CUENTA==null && other.getNUM_CUENTA()==null) || 
             (this.NUM_CUENTA!=null &&
              this.NUM_CUENTA.equals(other.getNUM_CUENTA()))) &&
            this.TIPO_CUENTA == other.getTIPO_CUENTA() &&
            ((this.TELEFONO==null && other.getTELEFONO()==null) || 
             (this.TELEFONO!=null &&
              this.TELEFONO.equals(other.getTELEFONO()))) &&
            ((this.CELULAR==null && other.getCELULAR()==null) || 
             (this.CELULAR!=null &&
              this.CELULAR.equals(other.getCELULAR()))) &&
            ((this.EMAIL==null && other.getEMAIL()==null) || 
             (this.EMAIL!=null &&
              this.EMAIL.equals(other.getEMAIL())));
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
        _hashCode += new Long(getID_MANDATO()).hashCode();
        if (getNOMBRE_AFILIADO() != null) {
            _hashCode += getNOMBRE_AFILIADO().hashCode();
        }
        _hashCode += getCODIGO_BANCO();
        if (getNUM_CUENTA() != null) {
            _hashCode += getNUM_CUENTA().hashCode();
        }
        _hashCode += getTIPO_CUENTA();
        if (getTELEFONO() != null) {
            _hashCode += getTELEFONO().hashCode();
        }
        if (getCELULAR() != null) {
            _hashCode += getCELULAR().hashCode();
        }
        if (getEMAIL() != null) {
            _hashCode += getEMAIL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CuentaDescripcion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.laaraucana.cl/mandato", "cuentaDescripcion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID_MANDATO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ID_MANDATO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBRE_AFILIADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NOMBRE_AFILIADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGO_BANCO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODIGO_BANCO"));
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
        elemField.setFieldName("TIPO_CUENTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TIPO_CUENTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TELEFONO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TELEFONO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CELULAR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CELULAR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMAIL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EMAIL"));
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
