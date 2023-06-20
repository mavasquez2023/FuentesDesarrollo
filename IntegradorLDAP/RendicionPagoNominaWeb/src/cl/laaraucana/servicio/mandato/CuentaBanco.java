/**
 * CuentaBanco.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicio.mandato;

public class CuentaBanco  implements java.io.Serializable {
    private int RUT;

    private java.lang.String DV;

    private java.lang.String NOMBRE_AFILIADO;

    private java.lang.String CELULAR;

    private java.lang.String TELEFONO;

    private java.lang.String EMAIL;

    private int COD_BANCO;

    private java.lang.String NUM_CUENTA;

    private int COD_TIPO_CUENTA;

    public CuentaBanco() {
    }

    public CuentaBanco(
           int RUT,
           java.lang.String DV,
           java.lang.String NOMBRE_AFILIADO,
           java.lang.String CELULAR,
           java.lang.String TELEFONO,
           java.lang.String EMAIL,
           int COD_BANCO,
           java.lang.String NUM_CUENTA,
           int COD_TIPO_CUENTA) {
           this.RUT = RUT;
           this.DV = DV;
           this.NOMBRE_AFILIADO = NOMBRE_AFILIADO;
           this.CELULAR = CELULAR;
           this.TELEFONO = TELEFONO;
           this.EMAIL = EMAIL;
           this.COD_BANCO = COD_BANCO;
           this.NUM_CUENTA = NUM_CUENTA;
           this.COD_TIPO_CUENTA = COD_TIPO_CUENTA;
    }


    /**
     * Gets the RUT value for this CuentaBanco.
     * 
     * @return RUT
     */
    public int getRUT() {
        return RUT;
    }


    /**
     * Sets the RUT value for this CuentaBanco.
     * 
     * @param RUT
     */
    public void setRUT(int RUT) {
        this.RUT = RUT;
    }


    /**
     * Gets the DV value for this CuentaBanco.
     * 
     * @return DV
     */
    public java.lang.String getDV() {
        return DV;
    }


    /**
     * Sets the DV value for this CuentaBanco.
     * 
     * @param DV
     */
    public void setDV(java.lang.String DV) {
        this.DV = DV;
    }


    /**
     * Gets the NOMBRE_AFILIADO value for this CuentaBanco.
     * 
     * @return NOMBRE_AFILIADO
     */
    public java.lang.String getNOMBRE_AFILIADO() {
        return NOMBRE_AFILIADO;
    }


    /**
     * Sets the NOMBRE_AFILIADO value for this CuentaBanco.
     * 
     * @param NOMBRE_AFILIADO
     */
    public void setNOMBRE_AFILIADO(java.lang.String NOMBRE_AFILIADO) {
        this.NOMBRE_AFILIADO = NOMBRE_AFILIADO;
    }


    /**
     * Gets the CELULAR value for this CuentaBanco.
     * 
     * @return CELULAR
     */
    public java.lang.String getCELULAR() {
        return CELULAR;
    }


    /**
     * Sets the CELULAR value for this CuentaBanco.
     * 
     * @param CELULAR
     */
    public void setCELULAR(java.lang.String CELULAR) {
        this.CELULAR = CELULAR;
    }


    /**
     * Gets the TELEFONO value for this CuentaBanco.
     * 
     * @return TELEFONO
     */
    public java.lang.String getTELEFONO() {
        return TELEFONO;
    }


    /**
     * Sets the TELEFONO value for this CuentaBanco.
     * 
     * @param TELEFONO
     */
    public void setTELEFONO(java.lang.String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }


    /**
     * Gets the EMAIL value for this CuentaBanco.
     * 
     * @return EMAIL
     */
    public java.lang.String getEMAIL() {
        return EMAIL;
    }


    /**
     * Sets the EMAIL value for this CuentaBanco.
     * 
     * @param EMAIL
     */
    public void setEMAIL(java.lang.String EMAIL) {
        this.EMAIL = EMAIL;
    }


    /**
     * Gets the COD_BANCO value for this CuentaBanco.
     * 
     * @return COD_BANCO
     */
    public int getCOD_BANCO() {
        return COD_BANCO;
    }


    /**
     * Sets the COD_BANCO value for this CuentaBanco.
     * 
     * @param COD_BANCO
     */
    public void setCOD_BANCO(int COD_BANCO) {
        this.COD_BANCO = COD_BANCO;
    }


    /**
     * Gets the NUM_CUENTA value for this CuentaBanco.
     * 
     * @return NUM_CUENTA
     */
    public java.lang.String getNUM_CUENTA() {
        return NUM_CUENTA;
    }


    /**
     * Sets the NUM_CUENTA value for this CuentaBanco.
     * 
     * @param NUM_CUENTA
     */
    public void setNUM_CUENTA(java.lang.String NUM_CUENTA) {
        this.NUM_CUENTA = NUM_CUENTA;
    }


    /**
     * Gets the COD_TIPO_CUENTA value for this CuentaBanco.
     * 
     * @return COD_TIPO_CUENTA
     */
    public int getCOD_TIPO_CUENTA() {
        return COD_TIPO_CUENTA;
    }


    /**
     * Sets the COD_TIPO_CUENTA value for this CuentaBanco.
     * 
     * @param COD_TIPO_CUENTA
     */
    public void setCOD_TIPO_CUENTA(int COD_TIPO_CUENTA) {
        this.COD_TIPO_CUENTA = COD_TIPO_CUENTA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CuentaBanco)) return false;
        CuentaBanco other = (CuentaBanco) obj;
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
            ((this.NOMBRE_AFILIADO==null && other.getNOMBRE_AFILIADO()==null) || 
             (this.NOMBRE_AFILIADO!=null &&
              this.NOMBRE_AFILIADO.equals(other.getNOMBRE_AFILIADO()))) &&
            ((this.CELULAR==null && other.getCELULAR()==null) || 
             (this.CELULAR!=null &&
              this.CELULAR.equals(other.getCELULAR()))) &&
            ((this.TELEFONO==null && other.getTELEFONO()==null) || 
             (this.TELEFONO!=null &&
              this.TELEFONO.equals(other.getTELEFONO()))) &&
            ((this.EMAIL==null && other.getEMAIL()==null) || 
             (this.EMAIL!=null &&
              this.EMAIL.equals(other.getEMAIL()))) &&
            this.COD_BANCO == other.getCOD_BANCO() &&
            ((this.NUM_CUENTA==null && other.getNUM_CUENTA()==null) || 
             (this.NUM_CUENTA!=null &&
              this.NUM_CUENTA.equals(other.getNUM_CUENTA()))) &&
            this.COD_TIPO_CUENTA == other.getCOD_TIPO_CUENTA();
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
        if (getNOMBRE_AFILIADO() != null) {
            _hashCode += getNOMBRE_AFILIADO().hashCode();
        }
        if (getCELULAR() != null) {
            _hashCode += getCELULAR().hashCode();
        }
        if (getTELEFONO() != null) {
            _hashCode += getTELEFONO().hashCode();
        }
        if (getEMAIL() != null) {
            _hashCode += getEMAIL().hashCode();
        }
        _hashCode += getCOD_BANCO();
        if (getNUM_CUENTA() != null) {
            _hashCode += getNUM_CUENTA().hashCode();
        }
        _hashCode += getCOD_TIPO_CUENTA();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CuentaBanco.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.laaraucana.cl/mandato", "cuentaBanco"));
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
        elemField.setFieldName("NOMBRE_AFILIADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NOMBRE_AFILIADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CELULAR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CELULAR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TELEFONO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TELEFONO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMAIL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EMAIL"));
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
        elemField.setFieldName("COD_TIPO_CUENTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COD_TIPO_CUENTA"));
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
