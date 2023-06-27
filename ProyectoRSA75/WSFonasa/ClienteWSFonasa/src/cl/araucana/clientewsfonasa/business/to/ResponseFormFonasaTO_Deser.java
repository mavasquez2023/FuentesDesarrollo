/**
 * ResponseFormFonasaTO_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf290824.08 v62608133037
 */

package cl.araucana.clientewsfonasa.business.to;

public class ResponseFormFonasaTO_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public ResponseFormFonasaTO_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new cl.araucana.clientewsfonasa.business.to.ResponseFormFonasaTO();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_0) {
          ((ResponseFormFonasaTO)value).setEstado(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseShort(strValue));
          return true;}
        else if (qName==QName_0_1) {
          ((ResponseFormFonasaTO)value).setGlosaEstado(strValue);
          return true;}
        else if (qName==QName_0_2) {
          ((ResponseFormFonasaTO)value).setResultado(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseShort(strValue));
          return true;}
        else if (qName==QName_0_3) {
          ((ResponseFormFonasaTO)value).setGlosaResultado(strValue);
          return true;}
        return false;
    }
    protected boolean tryAttributeSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        return false;
    }
    protected boolean tryElementSetFromObject(javax.xml.namespace.QName qName, java.lang.Object objValue) {
        if (objValue == null) {
          return true;
        }
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_0_2 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "resultado");
    private final static javax.xml.namespace.QName QName_0_3 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "glosaResultado");
    private final static javax.xml.namespace.QName QName_0_1 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "glosaEstado");
    private final static javax.xml.namespace.QName QName_0_0 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "estado");
}
