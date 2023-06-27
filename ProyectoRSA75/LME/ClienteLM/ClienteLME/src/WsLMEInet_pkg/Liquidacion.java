/**
 * Liquidacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package WsLMEInet_pkg;

public class Liquidacion  implements java.io.Serializable {
    private java.util.Calendar periodoRenta;

    private java.math.BigInteger mtoApoPens;

    private java.math.BigInteger mtoApoSubs;

    private java.math.BigInteger mtoApoSalud;

    private java.math.BigInteger mtoSegCesantia;

    private java.util.Calendar fecProbPago;

    private java.util.Calendar fechaDesde;

    private java.util.Calendar fechaHasta;

    private java.math.BigInteger numDiasPagSub;

    private java.math.BigInteger numDiasPagApo;

    public Liquidacion() {
    }

    public Liquidacion(
           java.util.Calendar periodoRenta,
           java.math.BigInteger mtoApoPens,
           java.math.BigInteger mtoApoSubs,
           java.math.BigInteger mtoApoSalud,
           java.math.BigInteger mtoSegCesantia,
           java.util.Calendar fecProbPago,
           java.util.Calendar fechaDesde,
           java.util.Calendar fechaHasta,
           java.math.BigInteger numDiasPagSub,
           java.math.BigInteger numDiasPagApo) {
           this.periodoRenta = periodoRenta;
           this.mtoApoPens = mtoApoPens;
           this.mtoApoSubs = mtoApoSubs;
           this.mtoApoSalud = mtoApoSalud;
           this.mtoSegCesantia = mtoSegCesantia;
           this.fecProbPago = fecProbPago;
           this.fechaDesde = fechaDesde;
           this.fechaHasta = fechaHasta;
           this.numDiasPagSub = numDiasPagSub;
           this.numDiasPagApo = numDiasPagApo;
    }


    /**
     * Gets the periodoRenta value for this Liquidacion.
     * 
     * @return periodoRenta
     */
    public java.util.Calendar getPeriodoRenta() {
        return periodoRenta;
    }


    /**
     * Sets the periodoRenta value for this Liquidacion.
     * 
     * @param periodoRenta
     */
    public void setPeriodoRenta(java.util.Calendar periodoRenta) {
        this.periodoRenta = periodoRenta;
    }


    /**
     * Gets the mtoApoPens value for this Liquidacion.
     * 
     * @return mtoApoPens
     */
    public java.math.BigInteger getMtoApoPens() {
        return mtoApoPens;
    }


    /**
     * Sets the mtoApoPens value for this Liquidacion.
     * 
     * @param mtoApoPens
     */
    public void setMtoApoPens(java.math.BigInteger mtoApoPens) {
        this.mtoApoPens = mtoApoPens;
    }


    /**
     * Gets the mtoApoSubs value for this Liquidacion.
     * 
     * @return mtoApoSubs
     */
    public java.math.BigInteger getMtoApoSubs() {
        return mtoApoSubs;
    }


    /**
     * Sets the mtoApoSubs value for this Liquidacion.
     * 
     * @param mtoApoSubs
     */
    public void setMtoApoSubs(java.math.BigInteger mtoApoSubs) {
        this.mtoApoSubs = mtoApoSubs;
    }


    /**
     * Gets the mtoApoSalud value for this Liquidacion.
     * 
     * @return mtoApoSalud
     */
    public java.math.BigInteger getMtoApoSalud() {
        return mtoApoSalud;
    }


    /**
     * Sets the mtoApoSalud value for this Liquidacion.
     * 
     * @param mtoApoSalud
     */
    public void setMtoApoSalud(java.math.BigInteger mtoApoSalud) {
        this.mtoApoSalud = mtoApoSalud;
    }


    /**
     * Gets the mtoSegCesantia value for this Liquidacion.
     * 
     * @return mtoSegCesantia
     */
    public java.math.BigInteger getMtoSegCesantia() {
        return mtoSegCesantia;
    }


    /**
     * Sets the mtoSegCesantia value for this Liquidacion.
     * 
     * @param mtoSegCesantia
     */
    public void setMtoSegCesantia(java.math.BigInteger mtoSegCesantia) {
        this.mtoSegCesantia = mtoSegCesantia;
    }


    /**
     * Gets the fecProbPago value for this Liquidacion.
     * 
     * @return fecProbPago
     */
    public java.util.Calendar getFecProbPago() {
        return fecProbPago;
    }


    /**
     * Sets the fecProbPago value for this Liquidacion.
     * 
     * @param fecProbPago
     */
    public void setFecProbPago(java.util.Calendar fecProbPago) {
        this.fecProbPago = fecProbPago;
    }


    /**
     * Gets the fechaDesde value for this Liquidacion.
     * 
     * @return fechaDesde
     */
    public java.util.Calendar getFechaDesde() {
        return fechaDesde;
    }


    /**
     * Sets the fechaDesde value for this Liquidacion.
     * 
     * @param fechaDesde
     */
    public void setFechaDesde(java.util.Calendar fechaDesde) {
        this.fechaDesde = fechaDesde;
    }


    /**
     * Gets the fechaHasta value for this Liquidacion.
     * 
     * @return fechaHasta
     */
    public java.util.Calendar getFechaHasta() {
        return fechaHasta;
    }


    /**
     * Sets the fechaHasta value for this Liquidacion.
     * 
     * @param fechaHasta
     */
    public void setFechaHasta(java.util.Calendar fechaHasta) {
        this.fechaHasta = fechaHasta;
    }


    /**
     * Gets the numDiasPagSub value for this Liquidacion.
     * 
     * @return numDiasPagSub
     */
    public java.math.BigInteger getNumDiasPagSub() {
        return numDiasPagSub;
    }


    /**
     * Sets the numDiasPagSub value for this Liquidacion.
     * 
     * @param numDiasPagSub
     */
    public void setNumDiasPagSub(java.math.BigInteger numDiasPagSub) {
        this.numDiasPagSub = numDiasPagSub;
    }


    /**
     * Gets the numDiasPagApo value for this Liquidacion.
     * 
     * @return numDiasPagApo
     */
    public java.math.BigInteger getNumDiasPagApo() {
        return numDiasPagApo;
    }


    /**
     * Sets the numDiasPagApo value for this Liquidacion.
     * 
     * @param numDiasPagApo
     */
    public void setNumDiasPagApo(java.math.BigInteger numDiasPagApo) {
        this.numDiasPagApo = numDiasPagApo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Liquidacion)) return false;
        Liquidacion other = (Liquidacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.periodoRenta==null && other.getPeriodoRenta()==null) || 
             (this.periodoRenta!=null &&
              this.periodoRenta.equals(other.getPeriodoRenta()))) &&
            ((this.mtoApoPens==null && other.getMtoApoPens()==null) || 
             (this.mtoApoPens!=null &&
              this.mtoApoPens.equals(other.getMtoApoPens()))) &&
            ((this.mtoApoSubs==null && other.getMtoApoSubs()==null) || 
             (this.mtoApoSubs!=null &&
              this.mtoApoSubs.equals(other.getMtoApoSubs()))) &&
            ((this.mtoApoSalud==null && other.getMtoApoSalud()==null) || 
             (this.mtoApoSalud!=null &&
              this.mtoApoSalud.equals(other.getMtoApoSalud()))) &&
            ((this.mtoSegCesantia==null && other.getMtoSegCesantia()==null) || 
             (this.mtoSegCesantia!=null &&
              this.mtoSegCesantia.equals(other.getMtoSegCesantia()))) &&
            ((this.fecProbPago==null && other.getFecProbPago()==null) || 
             (this.fecProbPago!=null &&
              this.fecProbPago.equals(other.getFecProbPago()))) &&
            ((this.fechaDesde==null && other.getFechaDesde()==null) || 
             (this.fechaDesde!=null &&
              this.fechaDesde.equals(other.getFechaDesde()))) &&
            ((this.fechaHasta==null && other.getFechaHasta()==null) || 
             (this.fechaHasta!=null &&
              this.fechaHasta.equals(other.getFechaHasta()))) &&
            ((this.numDiasPagSub==null && other.getNumDiasPagSub()==null) || 
             (this.numDiasPagSub!=null &&
              this.numDiasPagSub.equals(other.getNumDiasPagSub()))) &&
            ((this.numDiasPagApo==null && other.getNumDiasPagApo()==null) || 
             (this.numDiasPagApo!=null &&
              this.numDiasPagApo.equals(other.getNumDiasPagApo())));
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
        if (getPeriodoRenta() != null) {
            _hashCode += getPeriodoRenta().hashCode();
        }
        if (getMtoApoPens() != null) {
            _hashCode += getMtoApoPens().hashCode();
        }
        if (getMtoApoSubs() != null) {
            _hashCode += getMtoApoSubs().hashCode();
        }
        if (getMtoApoSalud() != null) {
            _hashCode += getMtoApoSalud().hashCode();
        }
        if (getMtoSegCesantia() != null) {
            _hashCode += getMtoSegCesantia().hashCode();
        }
        if (getFecProbPago() != null) {
            _hashCode += getFecProbPago().hashCode();
        }
        if (getFechaDesde() != null) {
            _hashCode += getFechaDesde().hashCode();
        }
        if (getFechaHasta() != null) {
            _hashCode += getFechaHasta().hashCode();
        }
        if (getNumDiasPagSub() != null) {
            _hashCode += getNumDiasPagSub().hashCode();
        }
        if (getNumDiasPagApo() != null) {
            _hashCode += getNumDiasPagApo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Liquidacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "Liquidacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodoRenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PeriodoRenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mtoApoPens");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MtoApoPens"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mtoApoSubs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MtoApoSubs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mtoApoSalud");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MtoApoSalud"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mtoSegCesantia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MtoSegCesantia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecProbPago");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FecProbPago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaDesde");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FechaDesde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaHasta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FechaHasta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numDiasPagSub");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NumDiasPagSub"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numDiasPagApo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NumDiasPagApo"));
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
