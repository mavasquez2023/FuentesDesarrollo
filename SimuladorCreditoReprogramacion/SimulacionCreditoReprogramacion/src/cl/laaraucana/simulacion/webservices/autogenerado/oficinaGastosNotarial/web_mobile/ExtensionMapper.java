
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package cl.laaraucana.simulacion.webservices.autogenerado.oficinaGastosNotarial.web_mobile;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "OficinaGastoNotarialRequest2".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.oficinaGastosNotarial.web_mobile.OficinaGastoNotarialRequest2.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "OFICINAOUTPUT_type0".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.oficinaGastosNotarial.web_mobile.OFICINAOUTPUT_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "OficinaGastoNotarialResponse2".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.oficinaGastosNotarial.web_mobile.OficinaGastoNotarialResponse2.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "item_type0".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.oficinaGastosNotarial.web_mobile.Item_type0.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    