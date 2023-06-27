/**
 * ResponseFormFonasaTO_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf290824.08 v62608133037
 */

package cl.araucana.clientewsfonasa.business.to;

public class ResponseFormFonasaTO_Helper {
    // Type metadata
    private static final com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(ResponseFormFonasaTO.class);

    static {
        typeDesc.setOption("buildNum","cf290824.08");
        com.ibm.ws.webservices.engine.description.FieldDesc field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("estado");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "estado"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "short"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("glosaEstado");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "glosaEstado"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("resultado");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "resultado"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "short"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("glosaResultado");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "glosaResultado"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
    };

    /**
     * Return type metadata object
     */
    public static com.ibm.ws.webservices.engine.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static com.ibm.ws.webservices.engine.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class javaType,  
           javax.xml.namespace.QName xmlType) {
        return 
          new ResponseFormFonasaTO_Ser(
            javaType, xmlType, typeDesc);
    };

    /**
     * Get Custom Deserializer
     */
    public static com.ibm.ws.webservices.engine.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class javaType,  
           javax.xml.namespace.QName xmlType) {
        return 
          new ResponseFormFonasaTO_Deser(
            javaType, xmlType, typeDesc);
    };

}
