/**
 * PLATINUMEscalaPredictor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.equifax.wse.gru01.platinum.output.sub;

public class PLATINUMEscalaPredictor  implements java.io.Serializable {
    private java.lang.String claseRiesgo;

    private java.lang.String porcentaje;

    private java.lang.String predictorMaximo;

    private java.lang.String predictorMinimo;

    private java.lang.String probabilidad;

    public PLATINUMEscalaPredictor() {
    }

    public PLATINUMEscalaPredictor(
           java.lang.String claseRiesgo,
           java.lang.String porcentaje,
           java.lang.String predictorMaximo,
           java.lang.String predictorMinimo,
           java.lang.String probabilidad) {
           this.claseRiesgo = claseRiesgo;
           this.porcentaje = porcentaje;
           this.predictorMaximo = predictorMaximo;
           this.predictorMinimo = predictorMinimo;
           this.probabilidad = probabilidad;
    }


    /**
     * Gets the claseRiesgo value for this PLATINUMEscalaPredictor.
     * 
     * @return claseRiesgo
     */
    public java.lang.String getClaseRiesgo() {
        return claseRiesgo;
    }


    /**
     * Sets the claseRiesgo value for this PLATINUMEscalaPredictor.
     * 
     * @param claseRiesgo
     */
    public void setClaseRiesgo(java.lang.String claseRiesgo) {
        this.claseRiesgo = claseRiesgo;
    }


    /**
     * Gets the porcentaje value for this PLATINUMEscalaPredictor.
     * 
     * @return porcentaje
     */
    public java.lang.String getPorcentaje() {
        return porcentaje;
    }


    /**
     * Sets the porcentaje value for this PLATINUMEscalaPredictor.
     * 
     * @param porcentaje
     */
    public void setPorcentaje(java.lang.String porcentaje) {
        this.porcentaje = porcentaje;
    }


    /**
     * Gets the predictorMaximo value for this PLATINUMEscalaPredictor.
     * 
     * @return predictorMaximo
     */
    public java.lang.String getPredictorMaximo() {
        return predictorMaximo;
    }


    /**
     * Sets the predictorMaximo value for this PLATINUMEscalaPredictor.
     * 
     * @param predictorMaximo
     */
    public void setPredictorMaximo(java.lang.String predictorMaximo) {
        this.predictorMaximo = predictorMaximo;
    }


    /**
     * Gets the predictorMinimo value for this PLATINUMEscalaPredictor.
     * 
     * @return predictorMinimo
     */
    public java.lang.String getPredictorMinimo() {
        return predictorMinimo;
    }


    /**
     * Sets the predictorMinimo value for this PLATINUMEscalaPredictor.
     * 
     * @param predictorMinimo
     */
    public void setPredictorMinimo(java.lang.String predictorMinimo) {
        this.predictorMinimo = predictorMinimo;
    }


    /**
     * Gets the probabilidad value for this PLATINUMEscalaPredictor.
     * 
     * @return probabilidad
     */
    public java.lang.String getProbabilidad() {
        return probabilidad;
    }


    /**
     * Sets the probabilidad value for this PLATINUMEscalaPredictor.
     * 
     * @param probabilidad
     */
    public void setProbabilidad(java.lang.String probabilidad) {
        this.probabilidad = probabilidad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLATINUMEscalaPredictor)) return false;
        PLATINUMEscalaPredictor other = (PLATINUMEscalaPredictor) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.claseRiesgo==null && other.getClaseRiesgo()==null) || 
             (this.claseRiesgo!=null &&
              this.claseRiesgo.equals(other.getClaseRiesgo()))) &&
            ((this.porcentaje==null && other.getPorcentaje()==null) || 
             (this.porcentaje!=null &&
              this.porcentaje.equals(other.getPorcentaje()))) &&
            ((this.predictorMaximo==null && other.getPredictorMaximo()==null) || 
             (this.predictorMaximo!=null &&
              this.predictorMaximo.equals(other.getPredictorMaximo()))) &&
            ((this.predictorMinimo==null && other.getPredictorMinimo()==null) || 
             (this.predictorMinimo!=null &&
              this.predictorMinimo.equals(other.getPredictorMinimo()))) &&
            ((this.probabilidad==null && other.getProbabilidad()==null) || 
             (this.probabilidad!=null &&
              this.probabilidad.equals(other.getProbabilidad())));
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
        if (getClaseRiesgo() != null) {
            _hashCode += getClaseRiesgo().hashCode();
        }
        if (getPorcentaje() != null) {
            _hashCode += getPorcentaje().hashCode();
        }
        if (getPredictorMaximo() != null) {
            _hashCode += getPredictorMaximo().hashCode();
        }
        if (getPredictorMinimo() != null) {
            _hashCode += getPredictorMinimo().hashCode();
        }
        if (getProbabilidad() != null) {
            _hashCode += getProbabilidad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PLATINUMEscalaPredictor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMEscalaPredictor"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claseRiesgo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "claseRiesgo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("porcentaje");
        elemField.setXmlName(new javax.xml.namespace.QName("", "porcentaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("predictorMaximo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "predictorMaximo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("predictorMinimo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "predictorMinimo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("probabilidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "probabilidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
