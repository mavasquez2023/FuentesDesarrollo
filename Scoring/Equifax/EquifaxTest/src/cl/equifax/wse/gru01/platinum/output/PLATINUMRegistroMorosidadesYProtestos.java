/**
 * PLATINUMRegistroMorosidadesYProtestos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.equifax.wse.gru01.platinum.output;

public class PLATINUMRegistroMorosidadesYProtestos  implements java.io.Serializable {
    private java.lang.String cantidadDocumentosBoletinProtestosEImpagos;

    private java.lang.String cantidadDocumentosICOM;

    private java.lang.String cantidadImpagosInformados;

    private java.lang.String cantidadMorososComercio;

    private java.lang.String cantidadMultasEInfraccionesEnLaboralYPrevisional;

    private java.lang.String montoTotalImpago;

    private cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBED[] morosidadesBED;

    private cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLAB[] morosidadesBOLAB;

    private cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLCOM[] morosidadesBOLCOM;

    private cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadICOM[] morosidadesICOM;

    private cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMPorMercado[] porMercados;

    public PLATINUMRegistroMorosidadesYProtestos() {
    }

    public PLATINUMRegistroMorosidadesYProtestos(
           java.lang.String cantidadDocumentosBoletinProtestosEImpagos,
           java.lang.String cantidadDocumentosICOM,
           java.lang.String cantidadImpagosInformados,
           java.lang.String cantidadMorososComercio,
           java.lang.String cantidadMultasEInfraccionesEnLaboralYPrevisional,
           java.lang.String montoTotalImpago,
           cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBED[] morosidadesBED,
           cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLAB[] morosidadesBOLAB,
           cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLCOM[] morosidadesBOLCOM,
           cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadICOM[] morosidadesICOM,
           cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMPorMercado[] porMercados) {
           this.cantidadDocumentosBoletinProtestosEImpagos = cantidadDocumentosBoletinProtestosEImpagos;
           this.cantidadDocumentosICOM = cantidadDocumentosICOM;
           this.cantidadImpagosInformados = cantidadImpagosInformados;
           this.cantidadMorososComercio = cantidadMorososComercio;
           this.cantidadMultasEInfraccionesEnLaboralYPrevisional = cantidadMultasEInfraccionesEnLaboralYPrevisional;
           this.montoTotalImpago = montoTotalImpago;
           this.morosidadesBED = morosidadesBED;
           this.morosidadesBOLAB = morosidadesBOLAB;
           this.morosidadesBOLCOM = morosidadesBOLCOM;
           this.morosidadesICOM = morosidadesICOM;
           this.porMercados = porMercados;
    }


    /**
     * Gets the cantidadDocumentosBoletinProtestosEImpagos value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @return cantidadDocumentosBoletinProtestosEImpagos
     */
    public java.lang.String getCantidadDocumentosBoletinProtestosEImpagos() {
        return cantidadDocumentosBoletinProtestosEImpagos;
    }


    /**
     * Sets the cantidadDocumentosBoletinProtestosEImpagos value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @param cantidadDocumentosBoletinProtestosEImpagos
     */
    public void setCantidadDocumentosBoletinProtestosEImpagos(java.lang.String cantidadDocumentosBoletinProtestosEImpagos) {
        this.cantidadDocumentosBoletinProtestosEImpagos = cantidadDocumentosBoletinProtestosEImpagos;
    }


    /**
     * Gets the cantidadDocumentosICOM value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @return cantidadDocumentosICOM
     */
    public java.lang.String getCantidadDocumentosICOM() {
        return cantidadDocumentosICOM;
    }


    /**
     * Sets the cantidadDocumentosICOM value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @param cantidadDocumentosICOM
     */
    public void setCantidadDocumentosICOM(java.lang.String cantidadDocumentosICOM) {
        this.cantidadDocumentosICOM = cantidadDocumentosICOM;
    }


    /**
     * Gets the cantidadImpagosInformados value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @return cantidadImpagosInformados
     */
    public java.lang.String getCantidadImpagosInformados() {
        return cantidadImpagosInformados;
    }


    /**
     * Sets the cantidadImpagosInformados value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @param cantidadImpagosInformados
     */
    public void setCantidadImpagosInformados(java.lang.String cantidadImpagosInformados) {
        this.cantidadImpagosInformados = cantidadImpagosInformados;
    }


    /**
     * Gets the cantidadMorososComercio value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @return cantidadMorososComercio
     */
    public java.lang.String getCantidadMorososComercio() {
        return cantidadMorososComercio;
    }


    /**
     * Sets the cantidadMorososComercio value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @param cantidadMorososComercio
     */
    public void setCantidadMorososComercio(java.lang.String cantidadMorososComercio) {
        this.cantidadMorososComercio = cantidadMorososComercio;
    }


    /**
     * Gets the cantidadMultasEInfraccionesEnLaboralYPrevisional value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @return cantidadMultasEInfraccionesEnLaboralYPrevisional
     */
    public java.lang.String getCantidadMultasEInfraccionesEnLaboralYPrevisional() {
        return cantidadMultasEInfraccionesEnLaboralYPrevisional;
    }


    /**
     * Sets the cantidadMultasEInfraccionesEnLaboralYPrevisional value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @param cantidadMultasEInfraccionesEnLaboralYPrevisional
     */
    public void setCantidadMultasEInfraccionesEnLaboralYPrevisional(java.lang.String cantidadMultasEInfraccionesEnLaboralYPrevisional) {
        this.cantidadMultasEInfraccionesEnLaboralYPrevisional = cantidadMultasEInfraccionesEnLaboralYPrevisional;
    }


    /**
     * Gets the montoTotalImpago value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @return montoTotalImpago
     */
    public java.lang.String getMontoTotalImpago() {
        return montoTotalImpago;
    }


    /**
     * Sets the montoTotalImpago value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @param montoTotalImpago
     */
    public void setMontoTotalImpago(java.lang.String montoTotalImpago) {
        this.montoTotalImpago = montoTotalImpago;
    }


    /**
     * Gets the morosidadesBED value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @return morosidadesBED
     */
    public cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBED[] getMorosidadesBED() {
        return morosidadesBED;
    }


    /**
     * Sets the morosidadesBED value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @param morosidadesBED
     */
    public void setMorosidadesBED(cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBED[] morosidadesBED) {
        this.morosidadesBED = morosidadesBED;
    }


    /**
     * Gets the morosidadesBOLAB value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @return morosidadesBOLAB
     */
    public cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLAB[] getMorosidadesBOLAB() {
        return morosidadesBOLAB;
    }


    /**
     * Sets the morosidadesBOLAB value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @param morosidadesBOLAB
     */
    public void setMorosidadesBOLAB(cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLAB[] morosidadesBOLAB) {
        this.morosidadesBOLAB = morosidadesBOLAB;
    }


    /**
     * Gets the morosidadesBOLCOM value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @return morosidadesBOLCOM
     */
    public cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLCOM[] getMorosidadesBOLCOM() {
        return morosidadesBOLCOM;
    }


    /**
     * Sets the morosidadesBOLCOM value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @param morosidadesBOLCOM
     */
    public void setMorosidadesBOLCOM(cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLCOM[] morosidadesBOLCOM) {
        this.morosidadesBOLCOM = morosidadesBOLCOM;
    }


    /**
     * Gets the morosidadesICOM value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @return morosidadesICOM
     */
    public cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadICOM[] getMorosidadesICOM() {
        return morosidadesICOM;
    }


    /**
     * Sets the morosidadesICOM value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @param morosidadesICOM
     */
    public void setMorosidadesICOM(cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadICOM[] morosidadesICOM) {
        this.morosidadesICOM = morosidadesICOM;
    }


    /**
     * Gets the porMercados value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @return porMercados
     */
    public cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMPorMercado[] getPorMercados() {
        return porMercados;
    }


    /**
     * Sets the porMercados value for this PLATINUMRegistroMorosidadesYProtestos.
     * 
     * @param porMercados
     */
    public void setPorMercados(cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMPorMercado[] porMercados) {
        this.porMercados = porMercados;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLATINUMRegistroMorosidadesYProtestos)) return false;
        PLATINUMRegistroMorosidadesYProtestos other = (PLATINUMRegistroMorosidadesYProtestos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cantidadDocumentosBoletinProtestosEImpagos==null && other.getCantidadDocumentosBoletinProtestosEImpagos()==null) || 
             (this.cantidadDocumentosBoletinProtestosEImpagos!=null &&
              this.cantidadDocumentosBoletinProtestosEImpagos.equals(other.getCantidadDocumentosBoletinProtestosEImpagos()))) &&
            ((this.cantidadDocumentosICOM==null && other.getCantidadDocumentosICOM()==null) || 
             (this.cantidadDocumentosICOM!=null &&
              this.cantidadDocumentosICOM.equals(other.getCantidadDocumentosICOM()))) &&
            ((this.cantidadImpagosInformados==null && other.getCantidadImpagosInformados()==null) || 
             (this.cantidadImpagosInformados!=null &&
              this.cantidadImpagosInformados.equals(other.getCantidadImpagosInformados()))) &&
            ((this.cantidadMorososComercio==null && other.getCantidadMorososComercio()==null) || 
             (this.cantidadMorososComercio!=null &&
              this.cantidadMorososComercio.equals(other.getCantidadMorososComercio()))) &&
            ((this.cantidadMultasEInfraccionesEnLaboralYPrevisional==null && other.getCantidadMultasEInfraccionesEnLaboralYPrevisional()==null) || 
             (this.cantidadMultasEInfraccionesEnLaboralYPrevisional!=null &&
              this.cantidadMultasEInfraccionesEnLaboralYPrevisional.equals(other.getCantidadMultasEInfraccionesEnLaboralYPrevisional()))) &&
            ((this.montoTotalImpago==null && other.getMontoTotalImpago()==null) || 
             (this.montoTotalImpago!=null &&
              this.montoTotalImpago.equals(other.getMontoTotalImpago()))) &&
            ((this.morosidadesBED==null && other.getMorosidadesBED()==null) || 
             (this.morosidadesBED!=null &&
              java.util.Arrays.equals(this.morosidadesBED, other.getMorosidadesBED()))) &&
            ((this.morosidadesBOLAB==null && other.getMorosidadesBOLAB()==null) || 
             (this.morosidadesBOLAB!=null &&
              java.util.Arrays.equals(this.morosidadesBOLAB, other.getMorosidadesBOLAB()))) &&
            ((this.morosidadesBOLCOM==null && other.getMorosidadesBOLCOM()==null) || 
             (this.morosidadesBOLCOM!=null &&
              java.util.Arrays.equals(this.morosidadesBOLCOM, other.getMorosidadesBOLCOM()))) &&
            ((this.morosidadesICOM==null && other.getMorosidadesICOM()==null) || 
             (this.morosidadesICOM!=null &&
              java.util.Arrays.equals(this.morosidadesICOM, other.getMorosidadesICOM()))) &&
            ((this.porMercados==null && other.getPorMercados()==null) || 
             (this.porMercados!=null &&
              java.util.Arrays.equals(this.porMercados, other.getPorMercados())));
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
        if (getCantidadDocumentosBoletinProtestosEImpagos() != null) {
            _hashCode += getCantidadDocumentosBoletinProtestosEImpagos().hashCode();
        }
        if (getCantidadDocumentosICOM() != null) {
            _hashCode += getCantidadDocumentosICOM().hashCode();
        }
        if (getCantidadImpagosInformados() != null) {
            _hashCode += getCantidadImpagosInformados().hashCode();
        }
        if (getCantidadMorososComercio() != null) {
            _hashCode += getCantidadMorososComercio().hashCode();
        }
        if (getCantidadMultasEInfraccionesEnLaboralYPrevisional() != null) {
            _hashCode += getCantidadMultasEInfraccionesEnLaboralYPrevisional().hashCode();
        }
        if (getMontoTotalImpago() != null) {
            _hashCode += getMontoTotalImpago().hashCode();
        }
        if (getMorosidadesBED() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMorosidadesBED());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMorosidadesBED(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMorosidadesBOLAB() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMorosidadesBOLAB());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMorosidadesBOLAB(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMorosidadesBOLCOM() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMorosidadesBOLCOM());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMorosidadesBOLCOM(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMorosidadesICOM() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMorosidadesICOM());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMorosidadesICOM(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPorMercados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPorMercados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPorMercados(), i);
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
        new org.apache.axis.description.TypeDesc(PLATINUMRegistroMorosidadesYProtestos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMRegistroMorosidadesYProtestos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadDocumentosBoletinProtestosEImpagos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadDocumentosBoletinProtestosEImpagos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadDocumentosICOM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadDocumentosICOM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadImpagosInformados");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadImpagosInformados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadMorososComercio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadMorososComercio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadMultasEInfraccionesEnLaboralYPrevisional");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadMultasEInfraccionesEnLaboralYPrevisional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoTotalImpago");
        elemField.setXmlName(new javax.xml.namespace.QName("", "montoTotalImpago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("morosidadesBED");
        elemField.setXmlName(new javax.xml.namespace.QName("", "morosidadesBED"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMMorosidadBED"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("morosidadesBOLAB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "morosidadesBOLAB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMMorosidadBOLAB"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("morosidadesBOLCOM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "morosidadesBOLCOM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMMorosidadBOLCOM"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("morosidadesICOM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "morosidadesICOM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMMorosidadICOM"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("porMercados");
        elemField.setXmlName(new javax.xml.namespace.QName("", "porMercados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMPorMercado"));
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
