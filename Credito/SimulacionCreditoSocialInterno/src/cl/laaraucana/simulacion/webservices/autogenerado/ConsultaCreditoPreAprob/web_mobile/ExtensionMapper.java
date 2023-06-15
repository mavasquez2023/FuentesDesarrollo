
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile;
        
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
                  "ConsultaCreditoPreAprob".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.ConsultaCreditoPreAprob.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "CreditoEspecial".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "ConsultaCreditoPreAprobResponse".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.ConsultaCreditoPreAprobResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "ConsultaCreditoPreAprobRequest".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.ConsultaCreditoPreAprobRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/Legacy".equals(namespaceURI) &&
                  "MessageHeader".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.MessageHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/Legacy".equals(namespaceURI) &&
                  "Log".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    