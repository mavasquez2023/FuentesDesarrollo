/**
 * ResponseWSFonasaTO_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf290824.08 v62608133037
 */

package cl.araucana.clientewsfonasa.business.to;

public class ResponseWSFonasaTO_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public ResponseWSFonasaTO_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new cl.araucana.clientewsfonasa.business.to.ResponseWSFonasaTO();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_0) {
          ((ResponseWSFonasaTO)value).setEstado(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseShort(strValue));
          return true;}
        else if (qName==QName_0_6) {
          ((ResponseWSFonasaTO)value).setExtApellidoMat(strValue);
          return true;}
        else if (qName==QName_0_7) {
          ((ResponseWSFonasaTO)value).setExtApellidoPat(strValue);
          return true;}
        else if (qName==QName_0_8) {
          ((ResponseWSFonasaTO)value).setExtCodEstBen(strValue);
          return true;}
        else if (qName==QName_0_9) {
          ((ResponseWSFonasaTO)value).setExtDescEstado(strValue);
          return true;}
        else if (qName==QName_0_10) {
          ((ResponseWSFonasaTO)value).setExtFechaNacimi(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseDateTimeToDate(strValue));
          return true;}
        else if (qName==QName_0_11) {
          ((ResponseWSFonasaTO)value).setExtNombres(strValue);
          return true;}
        else if (qName==QName_0_12) {
          ((ResponseWSFonasaTO)value).setExtSexo(strValue);
          return true;}
        else if (qName==QName_0_13) {
          ((ResponseWSFonasaTO)value).setGloEstado(strValue);
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
    private final static javax.xml.namespace.QName QName_0_0 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "estado");
    private final static javax.xml.namespace.QName QName_0_12 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "extSexo");
    private final static javax.xml.namespace.QName QName_0_8 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "extCodEstBen");
    private final static javax.xml.namespace.QName QName_0_11 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "extNombres");
    private final static javax.xml.namespace.QName QName_0_7 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "extApellidoPat");
    private final static javax.xml.namespace.QName QName_0_13 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "gloEstado");
    private final static javax.xml.namespace.QName QName_0_9 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "extDescEstado");
    private final static javax.xml.namespace.QName QName_0_10 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "extFechaNacimi");
    private final static javax.xml.namespace.QName QName_0_6 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "extApellidoMat");
}
