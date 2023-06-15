
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile;
        
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
                  "LoanContract".equals(typeName)){
                   
                            return  cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContract.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "ReverseLoanContract".equals(typeName)){
                   
                            return  cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverseLoanContract.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "Log".equals(typeName)){
                   
                            return  cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.Log.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "LoanContrPayment".equals(typeName)){
                   
                            return  cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPayment.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "MessageHeader".equals(typeName)){
                   
                            return  cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.MessageHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "LoanContrPaymentRequest".equals(typeName)){
                   
                            return  cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPaymentRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "ReverLoanContractRequest".equals(typeName)){
                   
                            return  cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverLoanContractRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "ReverLoanContractResponse".equals(typeName)){
                   
                            return  cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverLoanContractResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "LoanContrPaymentResponse".equals(typeName)){
                   
                            return  cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPaymentResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://lautaro.com/xi/BS/WEB-Mobile".equals(namespaceURI) &&
                  "LoanArrearsInfo".equals(typeName)){
                   
                            return  cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanArrearsInfo.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    