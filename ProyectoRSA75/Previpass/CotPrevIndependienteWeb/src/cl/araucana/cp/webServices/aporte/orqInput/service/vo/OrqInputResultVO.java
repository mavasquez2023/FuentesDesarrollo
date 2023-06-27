/**
 * OrqInputResultVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.cp.webServices.aporte.orqInput.service.vo;

public class OrqInputResultVO  implements java.io.Serializable {
    private cl.araucana.cp.webServices.aporte.orqInput.service.vo.AporteResultVO aporteVO;

    private cl.araucana.cp.webServices.aporte.orqInput.service.vo.CreditoResultVO creditoVO;

    private cl.araucana.cp.webServices.aporte.orqInput.service.vo.ErrorResultVO errorVO;

    private cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingResultVO leasingBO;

    private cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingResultVO leasingVO;

    public OrqInputResultVO() {
    }

    public OrqInputResultVO(
           cl.araucana.cp.webServices.aporte.orqInput.service.vo.AporteResultVO aporteVO,
           cl.araucana.cp.webServices.aporte.orqInput.service.vo.CreditoResultVO creditoVO,
           cl.araucana.cp.webServices.aporte.orqInput.service.vo.ErrorResultVO errorVO,
           cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingResultVO leasingBO,
           cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingResultVO leasingVO) {
           this.aporteVO = aporteVO;
           this.creditoVO = creditoVO;
           this.errorVO = errorVO;
           this.leasingBO = leasingBO;
           this.leasingVO = leasingVO;
    }


    /**
     * Gets the aporteVO value for this OrqInputResultVO.
     * 
     * @return aporteVO
     */
    public cl.araucana.cp.webServices.aporte.orqInput.service.vo.AporteResultVO getAporteVO() {
        return aporteVO;
    }


    /**
     * Sets the aporteVO value for this OrqInputResultVO.
     * 
     * @param aporteVO
     */
    public void setAporteVO(cl.araucana.cp.webServices.aporte.orqInput.service.vo.AporteResultVO aporteVO) {
        this.aporteVO = aporteVO;
    }


    /**
     * Gets the creditoVO value for this OrqInputResultVO.
     * 
     * @return creditoVO
     */
    public cl.araucana.cp.webServices.aporte.orqInput.service.vo.CreditoResultVO getCreditoVO() {
        return creditoVO;
    }


    /**
     * Sets the creditoVO value for this OrqInputResultVO.
     * 
     * @param creditoVO
     */
    public void setCreditoVO(cl.araucana.cp.webServices.aporte.orqInput.service.vo.CreditoResultVO creditoVO) {
        this.creditoVO = creditoVO;
    }


    /**
     * Gets the errorVO value for this OrqInputResultVO.
     * 
     * @return errorVO
     */
    public cl.araucana.cp.webServices.aporte.orqInput.service.vo.ErrorResultVO getErrorVO() {
        return errorVO;
    }


    /**
     * Sets the errorVO value for this OrqInputResultVO.
     * 
     * @param errorVO
     */
    public void setErrorVO(cl.araucana.cp.webServices.aporte.orqInput.service.vo.ErrorResultVO errorVO) {
        this.errorVO = errorVO;
    }


    /**
     * Gets the leasingBO value for this OrqInputResultVO.
     * 
     * @return leasingBO
     */
    public cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingResultVO getLeasingBO() {
        return leasingBO;
    }


    /**
     * Sets the leasingBO value for this OrqInputResultVO.
     * 
     * @param leasingBO
     */
    public void setLeasingBO(cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingResultVO leasingBO) {
        this.leasingBO = leasingBO;
    }


    /**
     * Gets the leasingVO value for this OrqInputResultVO.
     * 
     * @return leasingVO
     */
    public cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingResultVO getLeasingVO() {
        return leasingVO;
    }


    /**
     * Sets the leasingVO value for this OrqInputResultVO.
     * 
     * @param leasingVO
     */
    public void setLeasingVO(cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingResultVO leasingVO) {
        this.leasingVO = leasingVO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OrqInputResultVO)) return false;
        OrqInputResultVO other = (OrqInputResultVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.aporteVO==null && other.getAporteVO()==null) || 
             (this.aporteVO!=null &&
              this.aporteVO.equals(other.getAporteVO()))) &&
            ((this.creditoVO==null && other.getCreditoVO()==null) || 
             (this.creditoVO!=null &&
              this.creditoVO.equals(other.getCreditoVO()))) &&
            ((this.errorVO==null && other.getErrorVO()==null) || 
             (this.errorVO!=null &&
              this.errorVO.equals(other.getErrorVO()))) &&
            ((this.leasingBO==null && other.getLeasingBO()==null) || 
             (this.leasingBO!=null &&
              this.leasingBO.equals(other.getLeasingBO()))) &&
            ((this.leasingVO==null && other.getLeasingVO()==null) || 
             (this.leasingVO!=null &&
              this.leasingVO.equals(other.getLeasingVO())));
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
        if (getAporteVO() != null) {
            _hashCode += getAporteVO().hashCode();
        }
        if (getCreditoVO() != null) {
            _hashCode += getCreditoVO().hashCode();
        }
        if (getErrorVO() != null) {
            _hashCode += getErrorVO().hashCode();
        }
        if (getLeasingBO() != null) {
            _hashCode += getLeasingBO().hashCode();
        }
        if (getLeasingVO() != null) {
            _hashCode += getLeasingVO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrqInputResultVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "OrqInputResultVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aporteVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "aporteVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "AporteResultVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditoVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "creditoVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "CreditoResultVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "errorVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "ErrorResultVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("leasingBO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "leasingBO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "LeasingResultVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("leasingVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "leasingVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.service.orqInput.aporte.araucana.cl", "LeasingResultVO"));
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
