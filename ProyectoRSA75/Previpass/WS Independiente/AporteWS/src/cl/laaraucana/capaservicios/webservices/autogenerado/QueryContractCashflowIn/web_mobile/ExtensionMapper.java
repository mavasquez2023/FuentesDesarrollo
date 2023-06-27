
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile;
        
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
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.treasury.ResultCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "LineaComercial".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.LineaComercial.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "QueryContractCashFlowResponse".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.QueryContractCashFlowResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/Treasury".equals(namespaceURI) &&
                  "MessageHeader".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.treasury.MessageHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/Treasury".equals(namespaceURI) &&
                  "Log".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.treasury.Log.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "QueryContractCashFlowRequest".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.QueryContractCashFlowRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "NroCuenta".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.NroCuenta.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "DetalleCuotasCF".equals(typeName)){
                   
                            return  cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    