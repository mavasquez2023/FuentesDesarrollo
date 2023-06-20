/**
 * DT_INFO_AFILIADO_RES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.diferimientoEspecial.CRM.WebMovile;


/**
 * 8000003746
 */
public class DT_INFO_AFILIADO_RES  implements java.io.Serializable {
    private java.lang.String NOMBRE;

    private java.util.Date FECHA_NACIMIENTO;

    private java.lang.String TRABAJADOR;

    private java.lang.String PENSIONADO;

    private cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_TRAB[] ANEXO_TRAB;

    private cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_PENS[] ANEXO_PENS;

    private cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_DEUD[] ANEXO_DEUD;

    public DT_INFO_AFILIADO_RES() {
    }

    public DT_INFO_AFILIADO_RES(
           java.lang.String NOMBRE,
           java.util.Date FECHA_NACIMIENTO,
           java.lang.String TRABAJADOR,
           java.lang.String PENSIONADO,
           cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_TRAB[] ANEXO_TRAB,
           cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_PENS[] ANEXO_PENS,
           cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_DEUD[] ANEXO_DEUD) {
           this.NOMBRE = NOMBRE;
           this.FECHA_NACIMIENTO = FECHA_NACIMIENTO;
           this.TRABAJADOR = TRABAJADOR;
           this.PENSIONADO = PENSIONADO;
           this.ANEXO_TRAB = ANEXO_TRAB;
           this.ANEXO_PENS = ANEXO_PENS;
           this.ANEXO_DEUD = ANEXO_DEUD;
    }


    /**
     * Gets the NOMBRE value for this DT_INFO_AFILIADO_RES.
     * 
     * @return NOMBRE
     */
    public java.lang.String getNOMBRE() {
        return NOMBRE;
    }


    /**
     * Sets the NOMBRE value for this DT_INFO_AFILIADO_RES.
     * 
     * @param NOMBRE
     */
    public void setNOMBRE(java.lang.String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }


    /**
     * Gets the FECHA_NACIMIENTO value for this DT_INFO_AFILIADO_RES.
     * 
     * @return FECHA_NACIMIENTO
     */
    public java.util.Date getFECHA_NACIMIENTO() {
        return FECHA_NACIMIENTO;
    }


    /**
     * Sets the FECHA_NACIMIENTO value for this DT_INFO_AFILIADO_RES.
     * 
     * @param FECHA_NACIMIENTO
     */
    public void setFECHA_NACIMIENTO(java.util.Date FECHA_NACIMIENTO) {
        this.FECHA_NACIMIENTO = FECHA_NACIMIENTO;
    }


    /**
     * Gets the TRABAJADOR value for this DT_INFO_AFILIADO_RES.
     * 
     * @return TRABAJADOR
     */
    public java.lang.String getTRABAJADOR() {
        return TRABAJADOR;
    }


    /**
     * Sets the TRABAJADOR value for this DT_INFO_AFILIADO_RES.
     * 
     * @param TRABAJADOR
     */
    public void setTRABAJADOR(java.lang.String TRABAJADOR) {
        this.TRABAJADOR = TRABAJADOR;
    }


    /**
     * Gets the PENSIONADO value for this DT_INFO_AFILIADO_RES.
     * 
     * @return PENSIONADO
     */
    public java.lang.String getPENSIONADO() {
        return PENSIONADO;
    }


    /**
     * Sets the PENSIONADO value for this DT_INFO_AFILIADO_RES.
     * 
     * @param PENSIONADO
     */
    public void setPENSIONADO(java.lang.String PENSIONADO) {
        this.PENSIONADO = PENSIONADO;
    }


    /**
     * Gets the ANEXO_TRAB value for this DT_INFO_AFILIADO_RES.
     * 
     * @return ANEXO_TRAB
     */
    public cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_TRAB[] getANEXO_TRAB() {
        return ANEXO_TRAB;
    }


    /**
     * Sets the ANEXO_TRAB value for this DT_INFO_AFILIADO_RES.
     * 
     * @param ANEXO_TRAB
     */
    public void setANEXO_TRAB(cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_TRAB[] ANEXO_TRAB) {
        this.ANEXO_TRAB = ANEXO_TRAB;
    }

    public cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_TRAB getANEXO_TRAB(int i) {
        return this.ANEXO_TRAB[i];
    }

    public void setANEXO_TRAB(int i, cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_TRAB _value) {
        this.ANEXO_TRAB[i] = _value;
    }


    /**
     * Gets the ANEXO_PENS value for this DT_INFO_AFILIADO_RES.
     * 
     * @return ANEXO_PENS
     */
    public cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_PENS[] getANEXO_PENS() {
        return ANEXO_PENS;
    }


    /**
     * Sets the ANEXO_PENS value for this DT_INFO_AFILIADO_RES.
     * 
     * @param ANEXO_PENS
     */
    public void setANEXO_PENS(cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_PENS[] ANEXO_PENS) {
        this.ANEXO_PENS = ANEXO_PENS;
    }

    public cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_PENS getANEXO_PENS(int i) {
        return this.ANEXO_PENS[i];
    }

    public void setANEXO_PENS(int i, cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_PENS _value) {
        this.ANEXO_PENS[i] = _value;
    }


    /**
     * Gets the ANEXO_DEUD value for this DT_INFO_AFILIADO_RES.
     * 
     * @return ANEXO_DEUD
     */
    public cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_DEUD[] getANEXO_DEUD() {
        return ANEXO_DEUD;
    }


    /**
     * Sets the ANEXO_DEUD value for this DT_INFO_AFILIADO_RES.
     * 
     * @param ANEXO_DEUD
     */
    public void setANEXO_DEUD(cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_DEUD[] ANEXO_DEUD) {
        this.ANEXO_DEUD = ANEXO_DEUD;
    }

    public cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_DEUD getANEXO_DEUD(int i) {
        return this.ANEXO_DEUD[i];
    }

    public void setANEXO_DEUD(int i, cl.laaraucana.diferimientoEspecial.CRM.WebMovile.DT_INFO_AFILIADO_RESANEXO_DEUD _value) {
        this.ANEXO_DEUD[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_INFO_AFILIADO_RES)) return false;
        DT_INFO_AFILIADO_RES other = (DT_INFO_AFILIADO_RES) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NOMBRE==null && other.getNOMBRE()==null) || 
             (this.NOMBRE!=null &&
              this.NOMBRE.equals(other.getNOMBRE()))) &&
            ((this.FECHA_NACIMIENTO==null && other.getFECHA_NACIMIENTO()==null) || 
             (this.FECHA_NACIMIENTO!=null &&
              this.FECHA_NACIMIENTO.equals(other.getFECHA_NACIMIENTO()))) &&
            ((this.TRABAJADOR==null && other.getTRABAJADOR()==null) || 
             (this.TRABAJADOR!=null &&
              this.TRABAJADOR.equals(other.getTRABAJADOR()))) &&
            ((this.PENSIONADO==null && other.getPENSIONADO()==null) || 
             (this.PENSIONADO!=null &&
              this.PENSIONADO.equals(other.getPENSIONADO()))) &&
            ((this.ANEXO_TRAB==null && other.getANEXO_TRAB()==null) || 
             (this.ANEXO_TRAB!=null &&
              java.util.Arrays.equals(this.ANEXO_TRAB, other.getANEXO_TRAB()))) &&
            ((this.ANEXO_PENS==null && other.getANEXO_PENS()==null) || 
             (this.ANEXO_PENS!=null &&
              java.util.Arrays.equals(this.ANEXO_PENS, other.getANEXO_PENS()))) &&
            ((this.ANEXO_DEUD==null && other.getANEXO_DEUD()==null) || 
             (this.ANEXO_DEUD!=null &&
              java.util.Arrays.equals(this.ANEXO_DEUD, other.getANEXO_DEUD())));
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
        if (getNOMBRE() != null) {
            _hashCode += getNOMBRE().hashCode();
        }
        if (getFECHA_NACIMIENTO() != null) {
            _hashCode += getFECHA_NACIMIENTO().hashCode();
        }
        if (getTRABAJADOR() != null) {
            _hashCode += getTRABAJADOR().hashCode();
        }
        if (getPENSIONADO() != null) {
            _hashCode += getPENSIONADO().hashCode();
        }
        if (getANEXO_TRAB() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getANEXO_TRAB());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getANEXO_TRAB(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getANEXO_PENS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getANEXO_PENS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getANEXO_PENS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getANEXO_DEUD() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getANEXO_DEUD());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getANEXO_DEUD(), i);
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
        new org.apache.axis.description.TypeDesc(DT_INFO_AFILIADO_RES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", "DT_INFO_AFILIADO_RES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBRE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NOMBRE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHA_NACIMIENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHA_NACIMIENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRABAJADOR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRABAJADOR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PENSIONADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PENSIONADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ANEXO_TRAB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ANEXO_TRAB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", ">DT_INFO_AFILIADO_RES>ANEXO_TRAB"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ANEXO_PENS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ANEXO_PENS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", ">DT_INFO_AFILIADO_RES>ANEXO_PENS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ANEXO_DEUD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ANEXO_DEUD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", ">DT_INFO_AFILIADO_RES>ANEXO_DEUD"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
