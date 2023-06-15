
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package cl.laaraucana.compromisototal.webservice.autogenerado.sap.deudaInterCaja.web_mobile;
        
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
                  "DeudaInterCajaResponse".equals(typeName)){
                   
                            return  cl.laaraucana.compromisototal.webservice.autogenerado.sap.deudaInterCaja.web_mobile.DeudaInterCajaResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "DeudaInterCaja".equals(typeName)){
                   
                            return  cl.laaraucana.compromisototal.webservice.autogenerado.sap.deudaInterCaja.web_mobile.DeudaInterCaja.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "DeudaInterCajaRut".equals(typeName)){
                   
                            return  cl.laaraucana.compromisototal.webservice.autogenerado.sap.deudaInterCaja.web_mobile.DeudaInterCajaRut.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "DeudaInterCajaRequest".equals(typeName)){
                   
                            return  cl.laaraucana.compromisototal.webservice.autogenerado.sap.deudaInterCaja.web_mobile.DeudaInterCajaRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/Legacy".equals(namespaceURI) &&
                  "MessageHeader".equals(typeName)){
                   
                            return  cl.laaraucana.compromisototal.webservice.autogenerado.sap.deudaInterCaja.legacy.MessageHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/Legacy".equals(namespaceURI) &&
                  "Log".equals(typeName)){
                   
                            return  cl.laaraucana.compromisototal.webservice.autogenerado.sap.deudaInterCaja.legacy.Log.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    