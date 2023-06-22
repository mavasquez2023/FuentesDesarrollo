/**
 * PLATINUMConsultaRut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.equifax.wse.gru01.platinum.output;

public class PLATINUMConsultaRut  implements java.io.Serializable {
    private java.lang.String cantidadConsultasPeriodoDefinido;

    private java.lang.String cantidadUnidadesTiempo;

    private cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleConsultaRut[] detalleConsultasRut;

    private java.lang.String medidaTiempoUtilizada;

    public PLATINUMConsultaRut() {
    }

    public PLATINUMConsultaRut(
           java.lang.String cantidadConsultasPeriodoDefinido,
           java.lang.String cantidadUnidadesTiempo,
           cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleConsultaRut[] detalleConsultasRut,
           java.lang.String medidaTiempoUtilizada) {
           this.cantidadConsultasPeriodoDefinido = cantidadConsultasPeriodoDefinido;
           this.cantidadUnidadesTiempo = cantidadUnidadesTiempo;
           this.detalleConsultasRut = detalleConsultasRut;
           this.medidaTiempoUtilizada = medidaTiempoUtilizada;
    }


    /**
     * Gets the cantidadConsultasPeriodoDefinido value for this PLATINUMConsultaRut.
     * 
     * @return cantidadConsultasPeriodoDefinido
     */
    public java.lang.String getCantidadConsultasPeriodoDefinido() {
        return cantidadConsultasPeriodoDefinido;
    }


    /**
     * Sets the cantidadConsultasPeriodoDefinido value for this PLATINUMConsultaRut.
     * 
     * @param cantidadConsultasPeriodoDefinido
     */
    public void setCantidadConsultasPeriodoDefinido(java.lang.String cantidadConsultasPeriodoDefinido) {
        this.cantidadConsultasPeriodoDefinido = cantidadConsultasPeriodoDefinido;
    }


    /**
     * Gets the cantidadUnidadesTiempo value for this PLATINUMConsultaRut.
     * 
     * @return cantidadUnidadesTiempo
     */
    public java.lang.String getCantidadUnidadesTiempo() {
        return cantidadUnidadesTiempo;
    }


    /**
     * Sets the cantidadUnidadesTiempo value for this PLATINUMConsultaRut.
     * 
     * @param cantidadUnidadesTiempo
     */
    public void setCantidadUnidadesTiempo(java.lang.String cantidadUnidadesTiempo) {
        this.cantidadUnidadesTiempo = cantidadUnidadesTiempo;
    }


    /**
     * Gets the detalleConsultasRut value for this PLATINUMConsultaRut.
     * 
     * @return detalleConsultasRut
     */
    public cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleConsultaRut[] getDetalleConsultasRut() {
        return detalleConsultasRut;
    }


    /**
     * Sets the detalleConsultasRut value for this PLATINUMConsultaRut.
     * 
     * @param detalleConsultasRut
     */
    public void setDetalleConsultasRut(cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleConsultaRut[] detalleConsultasRut) {
        this.detalleConsultasRut = detalleConsultasRut;
    }


    /**
     * Gets the medidaTiempoUtilizada value for this PLATINUMConsultaRut.
     * 
     * @return medidaTiempoUtilizada
     */
    public java.lang.String getMedidaTiempoUtilizada() {
        return medidaTiempoUtilizada;
    }


    /**
     * Sets the medidaTiempoUtilizada value for this PLATINUMConsultaRut.
     * 
     * @param medidaTiempoUtilizada
     */
    public void setMedidaTiempoUtilizada(java.lang.String medidaTiempoUtilizada) {
        this.medidaTiempoUtilizada = medidaTiempoUtilizada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLATINUMConsultaRut)) return false;
        PLATINUMConsultaRut other = (PLATINUMConsultaRut) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cantidadConsultasPeriodoDefinido==null && other.getCantidadConsultasPeriodoDefinido()==null) || 
             (this.cantidadConsultasPeriodoDefinido!=null &&
              this.cantidadConsultasPeriodoDefinido.equals(other.getCantidadConsultasPeriodoDefinido()))) &&
            ((this.cantidadUnidadesTiempo==null && other.getCantidadUnidadesTiempo()==null) || 
             (this.cantidadUnidadesTiempo!=null &&
              this.cantidadUnidadesTiempo.equals(other.getCantidadUnidadesTiempo()))) &&
            ((this.detalleConsultasRut==null && other.getDetalleConsultasRut()==null) || 
             (this.detalleConsultasRut!=null &&
              java.util.Arrays.equals(this.detalleConsultasRut, other.getDetalleConsultasRut()))) &&
            ((this.medidaTiempoUtilizada==null && other.getMedidaTiempoUtilizada()==null) || 
             (this.medidaTiempoUtilizada!=null &&
              this.medidaTiempoUtilizada.equals(other.getMedidaTiempoUtilizada())));
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
        if (getCantidadConsultasPeriodoDefinido() != null) {
            _hashCode += getCantidadConsultasPeriodoDefinido().hashCode();
        }
        if (getCantidadUnidadesTiempo() != null) {
            _hashCode += getCantidadUnidadesTiempo().hashCode();
        }
        if (getDetalleConsultasRut() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetalleConsultasRut());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetalleConsultasRut(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMedidaTiempoUtilizada() != null) {
            _hashCode += getMedidaTiempoUtilizada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PLATINUMConsultaRut.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMConsultaRut"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadConsultasPeriodoDefinido");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadConsultasPeriodoDefinido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadUnidadesTiempo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadUnidadesTiempo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detalleConsultasRut");
        elemField.setXmlName(new javax.xml.namespace.QName("", "detalleConsultasRut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleConsultaRut"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medidaTiempoUtilizada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "medidaTiempoUtilizada"));
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
