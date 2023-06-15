
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile;
        
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
                  "CreaCotizacionOut".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.CreaCotizacionOut.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "CreaCotizacionResponse".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.CreaCotizacionResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "TasaInteres".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.TasaInteres.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/Legacy".equals(namespaceURI) &&
                  "ResultCode".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.legacy.ResultCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "CreaCotizacionRequest".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.CreaCotizacionRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/Legacy".equals(namespaceURI) &&
                  "Rut".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.legacy.Rut.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "CreaCotizacion".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.CreaCotizacion.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/Legacy".equals(namespaceURI) &&
                  "MessageHeader".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.legacy.MessageHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/Legacy".equals(namespaceURI) &&
                  "Log".equals(typeName)){
                   
                            return  cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.legacy.Log.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    