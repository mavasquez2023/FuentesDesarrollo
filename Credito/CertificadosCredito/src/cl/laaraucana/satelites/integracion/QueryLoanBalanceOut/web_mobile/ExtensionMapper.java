
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://lautaro.com/xi/FICAX/WEB-Mobile".equals(namespaceURI) &&
                  "ResultCode".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.ResultCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/FICAX/WEB-Mobile".equals(namespaceURI) &&
                  "QueryLoanAcctBalance".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanAcctBalance.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/FICAX/WEB-Mobile".equals(namespaceURI) &&
                  "QueryLoanBalanceRequest".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanBalanceRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/FICAX/WEB-Mobile".equals(namespaceURI) &&
                  "DetalleMonto_type0".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.DetalleMonto_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/FICAX/WEB-Mobile".equals(namespaceURI) &&
                  "QueryLoanBalance".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanBalance.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/FICAX/WEB-Mobile".equals(namespaceURI) &&
                  "MessageHeader".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.MessageHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/FICAX/WEB-Mobile".equals(namespaceURI) &&
                  "Log".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.Log.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/FICAX/WEB-Mobile".equals(namespaceURI) &&
                  "QueryLoanBalanceResponse".equals(typeName)){
                   
                            return  cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanBalanceResponse.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    