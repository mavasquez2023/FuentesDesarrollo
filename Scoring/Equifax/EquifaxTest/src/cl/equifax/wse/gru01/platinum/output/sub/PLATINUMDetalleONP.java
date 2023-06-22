/**
 * PLATINUMDetalleONP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.equifax.wse.gru01.platinum.output.sub;

public class PLATINUMDetalleONP  implements java.io.Serializable {
    private java.lang.String banco;

    private java.lang.String fechaPublicacion;

    private java.lang.String fuente;

    private java.lang.String motivo;

    private java.lang.String nroCheque;

    private java.lang.String nroCuenta;

    private java.lang.String nroSerie;

    private java.lang.String nroSucursal;

    public PLATINUMDetalleONP() {
    }

    public PLATINUMDetalleONP(
           java.lang.String banco,
           java.lang.String fechaPublicacion,
           java.lang.String fuente,
           java.lang.String motivo,
           java.lang.String nroCheque,
           java.lang.String nroCuenta,
           java.lang.String nroSerie,
           java.lang.String nroSucursal) {
           this.banco = banco;
           this.fechaPublicacion = fechaPublicacion;
           this.fuente = fuente;
           this.motivo = motivo;
           this.nroCheque = nroCheque;
           this.nroCuenta = nroCuenta;
           this.nroSerie = nroSerie;
           this.nroSucursal = nroSucursal;
    }


    /**
     * Gets the banco value for this PLATINUMDetalleONP.
     * 
     * @return banco
     */
    public java.lang.String getBanco() {
        return banco;
    }


    /**
     * Sets the banco value for this PLATINUMDetalleONP.
     * 
     * @param banco
     */
    public void setBanco(java.lang.String banco) {
        this.banco = banco;
    }


    /**
     * Gets the fechaPublicacion value for this PLATINUMDetalleONP.
     * 
     * @return fechaPublicacion
     */
    public java.lang.String getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this PLATINUMDetalleONP.
     * 
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(java.lang.String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the fuente value for this PLATINUMDetalleONP.
     * 
     * @return fuente
     */
    public java.lang.String getFuente() {
        return fuente;
    }


    /**
     * Sets the fuente value for this PLATINUMDetalleONP.
     * 
     * @param fuente
     */
    public void setFuente(java.lang.String fuente) {
        this.fuente = fuente;
    }


    /**
     * Gets the motivo value for this PLATINUMDetalleONP.
     * 
     * @return motivo
     */
    public java.lang.String getMotivo() {
        return motivo;
    }


    /**
     * Sets the motivo value for this PLATINUMDetalleONP.
     * 
     * @param motivo
     */
    public void setMotivo(java.lang.String motivo) {
        this.motivo = motivo;
    }


    /**
     * Gets the nroCheque value for this PLATINUMDetalleONP.
     * 
     * @return nroCheque
     */
    public java.lang.String getNroCheque() {
        return nroCheque;
    }


    /**
     * Sets the nroCheque value for this PLATINUMDetalleONP.
     * 
     * @param nroCheque
     */
    public void setNroCheque(java.lang.String nroCheque) {
        this.nroCheque = nroCheque;
    }


    /**
     * Gets the nroCuenta value for this PLATINUMDetalleONP.
     * 
     * @return nroCuenta
     */
    public java.lang.String getNroCuenta() {
        return nroCuenta;
    }


    /**
     * Sets the nroCuenta value for this PLATINUMDetalleONP.
     * 
     * @param nroCuenta
     */
    public void setNroCuenta(java.lang.String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }


    /**
     * Gets the nroSerie value for this PLATINUMDetalleONP.
     * 
     * @return nroSerie
     */
    public java.lang.String getNroSerie() {
        return nroSerie;
    }


    /**
     * Sets the nroSerie value for this PLATINUMDetalleONP.
     * 
     * @param nroSerie
     */
    public void setNroSerie(java.lang.String nroSerie) {
        this.nroSerie = nroSerie;
    }


    /**
     * Gets the nroSucursal value for this PLATINUMDetalleONP.
     * 
     * @return nroSucursal
     */
    public java.lang.String getNroSucursal() {
        return nroSucursal;
    }


    /**
     * Sets the nroSucursal value for this PLATINUMDetalleONP.
     * 
     * @param nroSucursal
     */
    public void setNroSucursal(java.lang.String nroSucursal) {
        this.nroSucursal = nroSucursal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLATINUMDetalleONP)) return false;
        PLATINUMDetalleONP other = (PLATINUMDetalleONP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.banco==null && other.getBanco()==null) || 
             (this.banco!=null &&
              this.banco.equals(other.getBanco()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.fuente==null && other.getFuente()==null) || 
             (this.fuente!=null &&
              this.fuente.equals(other.getFuente()))) &&
            ((this.motivo==null && other.getMotivo()==null) || 
             (this.motivo!=null &&
              this.motivo.equals(other.getMotivo()))) &&
            ((this.nroCheque==null && other.getNroCheque()==null) || 
             (this.nroCheque!=null &&
              this.nroCheque.equals(other.getNroCheque()))) &&
            ((this.nroCuenta==null && other.getNroCuenta()==null) || 
             (this.nroCuenta!=null &&
              this.nroCuenta.equals(other.getNroCuenta()))) &&
            ((this.nroSerie==null && other.getNroSerie()==null) || 
             (this.nroSerie!=null &&
              this.nroSerie.equals(other.getNroSerie()))) &&
            ((this.nroSucursal==null && other.getNroSucursal()==null) || 
             (this.nroSucursal!=null &&
              this.nroSucursal.equals(other.getNroSucursal())));
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
        if (getBanco() != null) {
            _hashCode += getBanco().hashCode();
        }
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getFuente() != null) {
            _hashCode += getFuente().hashCode();
        }
        if (getMotivo() != null) {
            _hashCode += getMotivo().hashCode();
        }
        if (getNroCheque() != null) {
            _hashCode += getNroCheque().hashCode();
        }
        if (getNroCuenta() != null) {
            _hashCode += getNroCuenta().hashCode();
        }
        if (getNroSerie() != null) {
            _hashCode += getNroSerie().hashCode();
        }
        if (getNroSucursal() != null) {
            _hashCode += getNroSucursal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PLATINUMDetalleONP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleONP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("banco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "banco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fuente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fuente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("motivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "motivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nroCheque");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nroCheque"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nroCuenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nroCuenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nroSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nroSerie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nroSucursal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nroSucursal"));
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
