
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "Log".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.Log.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "QuerySimWebIn".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.QuerySimWebIn.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "MessageHeader".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.MessageHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "PAYMENT_OPTIONS_type0".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "PAYMENT_OPTIONS_type1".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "QuerySimWebResponse".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.QuerySimWebResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "QuerySimWebOut".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.QuerySimWebOut.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "QuerySimWebRequest".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.QuerySimWebRequest.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    