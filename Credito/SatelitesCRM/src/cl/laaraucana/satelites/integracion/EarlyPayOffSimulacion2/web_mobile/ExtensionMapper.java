
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile;
        
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
                   
                            return  cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.treasury.ResultCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "EarlyPayoffContractResponse2".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.EarlyPayoffContractResponse2.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "DetalleCuotasSIMULATION_IN_2".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.DetalleCuotasSIMULATION_IN_2.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "EarlyPayoffContract2".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.EarlyPayoffContract2.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "PayoffContract2".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.PayoffContract2.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "EarlyPayoffContractRequest2".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.EarlyPayoffContractRequest2.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/Treasury".equals(namespaceURI) &&
                  "MessageHeader".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.treasury.MessageHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/Treasury".equals(namespaceURI) &&
                  "Log".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.treasury.Log.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    