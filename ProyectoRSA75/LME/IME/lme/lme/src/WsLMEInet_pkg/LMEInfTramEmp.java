/**
 * LMEInfTramEmp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WsLMEInet_pkg;

public class LMEInfTramEmp  implements java.io.Serializable {
    private java.lang.String codigoOperador;

    private java.lang.String rutEmpleador;

    private java.lang.String codUsuario;

    private java.lang.String clave;

    private java.util.Calendar fecProceso;

    private java.math.BigInteger numLicencia;

    private java.lang.String digLicencia;

    private byte[] datosZonaC;

    private java.math.BigInteger motivoNoRecepcion;

    public LMEInfTramEmp() {
    }

    public LMEInfTramEmp(
           java.lang.String codigoOperador,
           java.lang.String rutEmpleador,
           java.lang.String codUsuario,
           java.lang.String clave,
           java.util.Calendar fecProceso,
           java.math.BigInteger numLicencia,
           java.lang.String digLicencia,
           byte[] datosZonaC,
           java.math.BigInteger motivoNoRecepcion) {
           this.codigoOperador = codigoOperador;
           this.rutEmpleador = rutEmpleador;
           this.codUsuario = codUsuario;
           this.clave = clave;
           this.fecProceso = fecProceso;
           this.numLicencia = numLicencia;
           this.digLicencia = digLicencia;
           this.datosZonaC = datosZonaC;
           this.motivoNoRecepcion = motivoNoRecepcion;
    }


    /**
     * Gets the codigoOperador value for this LMEInfTramEmp.
     * 
     * @return codigoOperador
     */
    public java.lang.String getCodigoOperador() {
        return codigoOperador;
    }


    /**
     * Sets the codigoOperador value for this LMEInfTramEmp.
     * 
     * @param codigoOperador
     */
    public void setCodigoOperador(java.lang.String codigoOperador) {
        this.codigoOperador = codigoOperador;
    }


    /**
     * Gets the rutEmpleador value for this LMEInfTramEmp.
     * 
     * @return rutEmpleador
     */
    public java.lang.String getRutEmpleador() {
        return rutEmpleador;
    }


    /**
     * Sets the rutEmpleador value for this LMEInfTramEmp.
     * 
     * @param rutEmpleador
     */
    public void setRutEmpleador(java.lang.String rutEmpleador) {
        this.rutEmpleador = rutEmpleador;
    }


    /**
     * Gets the codUsuario value for this LMEInfTramEmp.
     * 
     * @return codUsuario
     */
    public java.lang.String getCodUsuario() {
        return codUsuario;
    }


    /**
     * Sets the codUsuario value for this LMEInfTramEmp.
     * 
     * @param codUsuario
     */
    public void setCodUsuario(java.lang.String codUsuario) {
        this.codUsuario = codUsuario;
    }


    /**
     * Gets the clave value for this LMEInfTramEmp.
     * 
     * @return clave
     */
    public java.lang.String getClave() {
        return clave;
    }


    /**
     * Sets the clave value for this LMEInfTramEmp.
     * 
     * @param clave
     */
    public void setClave(java.lang.String clave) {
        this.clave = clave;
    }


    /**
     * Gets the fecProceso value for this LMEInfTramEmp.
     * 
     * @return fecProceso
     */
    public java.util.Calendar getFecProceso() {
        return fecProceso;
    }


    /**
     * Sets the fecProceso value for this LMEInfTramEmp.
     * 
     * @param fecProceso
     */
    public void setFecProceso(java.util.Calendar fecProceso) {
        this.fecProceso = fecProceso;
    }


    /**
     * Gets the numLicencia value for this LMEInfTramEmp.
     * 
     * @return numLicencia
     */
    public java.math.BigInteger getNumLicencia() {
        return numLicencia;
    }


    /**
     * Sets the numLicencia value for this LMEInfTramEmp.
     * 
     * @param numLicencia
     */
    public void setNumLicencia(java.math.BigInteger numLicencia) {
        this.numLicencia = numLicencia;
    }


    /**
     * Gets the digLicencia value for this LMEInfTramEmp.
     * 
     * @return digLicencia
     */
    public java.lang.String getDigLicencia() {
        return digLicencia;
    }


    /**
     * Sets the digLicencia value for this LMEInfTramEmp.
     * 
     * @param digLicencia
     */
    public void setDigLicencia(java.lang.String digLicencia) {
        this.digLicencia = digLicencia;
    }


    /**
     * Gets the datosZonaC value for this LMEInfTramEmp.
     * 
     * @return datosZonaC
     */
    public byte[] getDatosZonaC() {
        return datosZonaC;
    }


    /**
     * Sets the datosZonaC value for this LMEInfTramEmp.
     * 
     * @param datosZonaC
     */
    public void setDatosZonaC(byte[] datosZonaC) {
        this.datosZonaC = datosZonaC;
    }


    /**
     * Gets the motivoNoRecepcion value for this LMEInfTramEmp.
     * 
     * @return motivoNoRecepcion
     */
    public java.math.BigInteger getMotivoNoRecepcion() {
        return motivoNoRecepcion;
    }


    /**
     * Sets the motivoNoRecepcion value for this LMEInfTramEmp.
     * 
     * @param motivoNoRecepcion
     */
    public void setMotivoNoRecepcion(java.math.BigInteger motivoNoRecepcion) {
        this.motivoNoRecepcion = motivoNoRecepcion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LMEInfTramEmp)) return false;
        LMEInfTramEmp other = (LMEInfTramEmp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoOperador==null && other.getCodigoOperador()==null) || 
             (this.codigoOperador!=null &&
              this.codigoOperador.equals(other.getCodigoOperador()))) &&
            ((this.rutEmpleador==null && other.getRutEmpleador()==null) || 
             (this.rutEmpleador!=null &&
              this.rutEmpleador.equals(other.getRutEmpleador()))) &&
            ((this.codUsuario==null && other.getCodUsuario()==null) || 
             (this.codUsuario!=null &&
              this.codUsuario.equals(other.getCodUsuario()))) &&
            ((this.clave==null && other.getClave()==null) || 
             (this.clave!=null &&
              this.clave.equals(other.getClave()))) &&
            ((this.fecProceso==null && other.getFecProceso()==null) || 
             (this.fecProceso!=null &&
              this.fecProceso.equals(other.getFecProceso()))) &&
            ((this.numLicencia==null && other.getNumLicencia()==null) || 
             (this.numLicencia!=null &&
              this.numLicencia.equals(other.getNumLicencia()))) &&
            ((this.digLicencia==null && other.getDigLicencia()==null) || 
             (this.digLicencia!=null &&
              this.digLicencia.equals(other.getDigLicencia()))) &&
            ((this.datosZonaC==null && other.getDatosZonaC()==null) || 
             (this.datosZonaC!=null &&
              java.util.Arrays.equals(this.datosZonaC, other.getDatosZonaC()))) &&
            ((this.motivoNoRecepcion==null && other.getMotivoNoRecepcion()==null) || 
             (this.motivoNoRecepcion!=null &&
              this.motivoNoRecepcion.equals(other.getMotivoNoRecepcion())));
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
        if (getCodigoOperador() != null) {
            _hashCode += getCodigoOperador().hashCode();
        }
        if (getRutEmpleador() != null) {
            _hashCode += getRutEmpleador().hashCode();
        }
        if (getCodUsuario() != null) {
            _hashCode += getCodUsuario().hashCode();
        }
        if (getClave() != null) {
            _hashCode += getClave().hashCode();
        }
        if (getFecProceso() != null) {
            _hashCode += getFecProceso().hashCode();
        }
        if (getNumLicencia() != null) {
            _hashCode += getNumLicencia().hashCode();
        }
        if (getDigLicencia() != null) {
            _hashCode += getDigLicencia().hashCode();
        }
        if (getDatosZonaC() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDatosZonaC());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDatosZonaC(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMotivoNoRecepcion() != null) {
            _hashCode += getMotivoNoRecepcion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LMEInfTramEmp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfTramEmp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoOperador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CodigoOperador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutEmpleador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RutEmpleador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CodUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clave");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Clave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecProceso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FecProceso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NumLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DigLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosZonaC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DatosZonaC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("motivoNoRecepcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MotivoNoRecepcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
