
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package cl.laaraucana.satelites.integracion.QueryBPStatusOUT.web_mobile;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://lautaro.com/xi/CRM/Legacy".equals(namespaceURI) &&
                  "ResultCode".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryBPStatusOUT.legacy.ResultCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "QueryBPStatusRut".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryBPStatusOUT.web_mobile.QueryBPStatusRut.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "QueryBPStatus".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryBPStatusOUT.web_mobile.QueryBPStatus.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "Rut".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryBPStatusOUT.web_mobile.Rut.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/WEB-Mobile".equals(namespaceURI) &&
                  "QueryBPStatusResponse".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryBPStatusOUT.web_mobile.QueryBPStatusResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/Legacy".equals(namespaceURI) &&
                  "MessageHeader".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryBPStatusOUT.legacy.MessageHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/CRM/Legacy".equals(namespaceURI) &&
                  "Log".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryBPStatusOUT.legacy.Log.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    