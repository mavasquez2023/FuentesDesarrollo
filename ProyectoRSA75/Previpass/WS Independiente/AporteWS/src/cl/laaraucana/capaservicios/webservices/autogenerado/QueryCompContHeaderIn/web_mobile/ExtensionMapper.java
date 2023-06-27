
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.web_mobile;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://lautaro.com/xi/BS/Treasury".equals(namespaceURI) &&
                  "ResultCode".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.treasury.ResultCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "QueryCompactContract".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.web_mobile.QueryCompactContract.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "QueryCompactContractHeaderResponse".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.web_mobile.QueryCompactContractHeaderResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/Treasury".equals(namespaceURI) &&
                  "MessageHeader".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.treasury.MessageHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/Treasury".equals(namespaceURI) &&
                  "Log".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.treasury.Log.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "QueryCompactContractHeader".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.web_mobile.QueryCompactContractHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "QueryCompactContractHeaderRequest".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryCompContHeaderIn.web_mobile.QueryCompactContractHeaderRequest.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    