
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile;
        
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
                  "Rut".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.Rut.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/Treasury".equals(namespaceURI) &&
                  "ResultCode".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryContractHeaderIn.treasury.ResultCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "QueryContractHeaderRequest".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.QueryContractHeaderRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "QueryContractHeaderResponse".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.QueryContractHeaderResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "ContractHeader".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.ContractHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "QueryContractHeader".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.QueryContractHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/Treasury".equals(namespaceURI) &&
                  "MessageHeader".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryContractHeaderIn.treasury.MessageHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/Treasury".equals(namespaceURI) &&
                  "Log".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryContractHeaderIn.treasury.Log.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    