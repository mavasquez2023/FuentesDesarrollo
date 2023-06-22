/**
 * PLATINUMIndicadorRiesgoCrediticio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.equifax.wse.gru01.platinum.output;

public class PLATINUMIndicadorRiesgoCrediticio  implements java.io.Serializable {
    private cl.equifax.wse.gru01.platinum.output.sub.PLATINUMAspectoRelevante[] aspectosRelevante;

    private java.lang.Object[] aspectosRelevantes;

    private java.lang.String clasificadorPersonaJuridica;

    private cl.equifax.wse.gru01.platinum.output.sub.PLATINUMEscalaPredictor[] escalaPredictores;

    private java.lang.String informaPredictorHistorico;

    private java.lang.String origenUsuario;

    private java.lang.String porcentajeMenorAlCalculado;

    private java.lang.String predictorPersonaNatural;

    private cl.equifax.wse.gru01.platinum.output.sub.PLATINUMPredictorHistorico[] predictoresHistoricos;

    public PLATINUMIndicadorRiesgoCrediticio() {
    }

    public PLATINUMIndicadorRiesgoCrediticio(
           cl.equifax.wse.gru01.platinum.output.sub.PLATINUMAspectoRelevante[] aspectosRelevante,
           java.lang.Object[] aspectosRelevantes,
           java.lang.String clasificadorPersonaJuridica,
           cl.equifax.wse.gru01.platinum.output.sub.PLATINUMEscalaPredictor[] escalaPredictores,
           java.lang.String informaPredictorHistorico,
           java.lang.String origenUsuario,
           java.lang.String porcentajeMenorAlCalculado,
           java.lang.String predictorPersonaNatural,
           cl.equifax.wse.gru01.platinum.output.sub.PLATINUMPredictorHistorico[] predictoresHistoricos) {
           this.aspectosRelevante = aspectosRelevante;
           this.aspectosRelevantes = aspectosRelevantes;
           this.clasificadorPersonaJuridica = clasificadorPersonaJuridica;
           this.escalaPredictores = escalaPredictores;
           this.informaPredictorHistorico = informaPredictorHistorico;
           this.origenUsuario = origenUsuario;
           this.porcentajeMenorAlCalculado = porcentajeMenorAlCalculado;
           this.predictorPersonaNatural = predictorPersonaNatural;
           this.predictoresHistoricos = predictoresHistoricos;
    }


    /**
     * Gets the aspectosRelevante value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @return aspectosRelevante
     */
    public cl.equifax.wse.gru01.platinum.output.sub.PLATINUMAspectoRelevante[] getAspectosRelevante() {
        return aspectosRelevante;
    }


    /**
     * Sets the aspectosRelevante value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @param aspectosRelevante
     */
    public void setAspectosRelevante(cl.equifax.wse.gru01.platinum.output.sub.PLATINUMAspectoRelevante[] aspectosRelevante) {
        this.aspectosRelevante = aspectosRelevante;
    }


    /**
     * Gets the aspectosRelevantes value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @return aspectosRelevantes
     */
    public java.lang.Object[] getAspectosRelevantes() {
        return aspectosRelevantes;
    }


    /**
     * Sets the aspectosRelevantes value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @param aspectosRelevantes
     */
    public void setAspectosRelevantes(java.lang.Object[] aspectosRelevantes) {
        this.aspectosRelevantes = aspectosRelevantes;
    }


    /**
     * Gets the clasificadorPersonaJuridica value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @return clasificadorPersonaJuridica
     */
    public java.lang.String getClasificadorPersonaJuridica() {
        return clasificadorPersonaJuridica;
    }


    /**
     * Sets the clasificadorPersonaJuridica value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @param clasificadorPersonaJuridica
     */
    public void setClasificadorPersonaJuridica(java.lang.String clasificadorPersonaJuridica) {
        this.clasificadorPersonaJuridica = clasificadorPersonaJuridica;
    }


    /**
     * Gets the escalaPredictores value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @return escalaPredictores
     */
    public cl.equifax.wse.gru01.platinum.output.sub.PLATINUMEscalaPredictor[] getEscalaPredictores() {
        return escalaPredictores;
    }


    /**
     * Sets the escalaPredictores value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @param escalaPredictores
     */
    public void setEscalaPredictores(cl.equifax.wse.gru01.platinum.output.sub.PLATINUMEscalaPredictor[] escalaPredictores) {
        this.escalaPredictores = escalaPredictores;
    }


    /**
     * Gets the informaPredictorHistorico value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @return informaPredictorHistorico
     */
    public java.lang.String getInformaPredictorHistorico() {
        return informaPredictorHistorico;
    }


    /**
     * Sets the informaPredictorHistorico value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @param informaPredictorHistorico
     */
    public void setInformaPredictorHistorico(java.lang.String informaPredictorHistorico) {
        this.informaPredictorHistorico = informaPredictorHistorico;
    }


    /**
     * Gets the origenUsuario value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @return origenUsuario
     */
    public java.lang.String getOrigenUsuario() {
        return origenUsuario;
    }


    /**
     * Sets the origenUsuario value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @param origenUsuario
     */
    public void setOrigenUsuario(java.lang.String origenUsuario) {
        this.origenUsuario = origenUsuario;
    }


    /**
     * Gets the porcentajeMenorAlCalculado value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @return porcentajeMenorAlCalculado
     */
    public java.lang.String getPorcentajeMenorAlCalculado() {
        return porcentajeMenorAlCalculado;
    }


    /**
     * Sets the porcentajeMenorAlCalculado value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @param porcentajeMenorAlCalculado
     */
    public void setPorcentajeMenorAlCalculado(java.lang.String porcentajeMenorAlCalculado) {
        this.porcentajeMenorAlCalculado = porcentajeMenorAlCalculado;
    }


    /**
     * Gets the predictorPersonaNatural value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @return predictorPersonaNatural
     */
    public java.lang.String getPredictorPersonaNatural() {
        return predictorPersonaNatural;
    }


    /**
     * Sets the predictorPersonaNatural value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @param predictorPersonaNatural
     */
    public void setPredictorPersonaNatural(java.lang.String predictorPersonaNatural) {
        this.predictorPersonaNatural = predictorPersonaNatural;
    }


    /**
     * Gets the predictoresHistoricos value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @return predictoresHistoricos
     */
    public cl.equifax.wse.gru01.platinum.output.sub.PLATINUMPredictorHistorico[] getPredictoresHistoricos() {
        return predictoresHistoricos;
    }


    /**
     * Sets the predictoresHistoricos value for this PLATINUMIndicadorRiesgoCrediticio.
     * 
     * @param predictoresHistoricos
     */
    public void setPredictoresHistoricos(cl.equifax.wse.gru01.platinum.output.sub.PLATINUMPredictorHistorico[] predictoresHistoricos) {
        this.predictoresHistoricos = predictoresHistoricos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLATINUMIndicadorRiesgoCrediticio)) return false;
        PLATINUMIndicadorRiesgoCrediticio other = (PLATINUMIndicadorRiesgoCrediticio) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.aspectosRelevante==null && other.getAspectosRelevante()==null) || 
             (this.aspectosRelevante!=null &&
              java.util.Arrays.equals(this.aspectosRelevante, other.getAspectosRelevante()))) &&
            ((this.aspectosRelevantes==null && other.getAspectosRelevantes()==null) || 
             (this.aspectosRelevantes!=null &&
              java.util.Arrays.equals(this.aspectosRelevantes, other.getAspectosRelevantes()))) &&
            ((this.clasificadorPersonaJuridica==null && other.getClasificadorPersonaJuridica()==null) || 
             (this.clasificadorPersonaJuridica!=null &&
              this.clasificadorPersonaJuridica.equals(other.getClasificadorPersonaJuridica()))) &&
            ((this.escalaPredictores==null && other.getEscalaPredictores()==null) || 
             (this.escalaPredictores!=null &&
              java.util.Arrays.equals(this.escalaPredictores, other.getEscalaPredictores()))) &&
            ((this.informaPredictorHistorico==null && other.getInformaPredictorHistorico()==null) || 
             (this.informaPredictorHistorico!=null &&
              this.informaPredictorHistorico.equals(other.getInformaPredictorHistorico()))) &&
            ((this.origenUsuario==null && other.getOrigenUsuario()==null) || 
             (this.origenUsuario!=null &&
              this.origenUsuario.equals(other.getOrigenUsuario()))) &&
            ((this.porcentajeMenorAlCalculado==null && other.getPorcentajeMenorAlCalculado()==null) || 
             (this.porcentajeMenorAlCalculado!=null &&
              this.porcentajeMenorAlCalculado.equals(other.getPorcentajeMenorAlCalculado()))) &&
            ((this.predictorPersonaNatural==null && other.getPredictorPersonaNatural()==null) || 
             (this.predictorPersonaNatural!=null &&
              this.predictorPersonaNatural.equals(other.getPredictorPersonaNatural()))) &&
            ((this.predictoresHistoricos==null && other.getPredictoresHistoricos()==null) || 
             (this.predictoresHistoricos!=null &&
              java.util.Arrays.equals(this.predictoresHistoricos, other.getPredictoresHistoricos())));
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
        if (getAspectosRelevante() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAspectosRelevante());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAspectosRelevante(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAspectosRelevantes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAspectosRelevantes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAspectosRelevantes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getClasificadorPersonaJuridica() != null) {
            _hashCode += getClasificadorPersonaJuridica().hashCode();
        }
        if (getEscalaPredictores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEscalaPredictores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEscalaPredictores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getInformaPredictorHistorico() != null) {
            _hashCode += getInformaPredictorHistorico().hashCode();
        }
        if (getOrigenUsuario() != null) {
            _hashCode += getOrigenUsuario().hashCode();
        }
        if (getPorcentajeMenorAlCalculado() != null) {
            _hashCode += getPorcentajeMenorAlCalculado().hashCode();
        }
        if (getPredictorPersonaNatural() != null) {
            _hashCode += getPredictorPersonaNatural().hashCode();
        }
        if (getPredictoresHistoricos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPredictoresHistoricos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPredictoresHistoricos(), i);
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
        new org.apache.axis.description.TypeDesc(PLATINUMIndicadorRiesgoCrediticio.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMIndicadorRiesgoCrediticio"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aspectosRelevante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aspectosRelevante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMAspectoRelevante"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aspectosRelevantes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aspectosRelevantes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "Array"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clasificadorPersonaJuridica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clasificadorPersonaJuridica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("escalaPredictores");
        elemField.setXmlName(new javax.xml.namespace.QName("", "escalaPredictores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMEscalaPredictor"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("informaPredictorHistorico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "informaPredictorHistorico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origenUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "origenUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("porcentajeMenorAlCalculado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "porcentajeMenorAlCalculado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("predictorPersonaNatural");
        elemField.setXmlName(new javax.xml.namespace.QName("", "predictorPersonaNatural"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("predictoresHistoricos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "predictoresHistoricos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMPredictorHistorico"));
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
