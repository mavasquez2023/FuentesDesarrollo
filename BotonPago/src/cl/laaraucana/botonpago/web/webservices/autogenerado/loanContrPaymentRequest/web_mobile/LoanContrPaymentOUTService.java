

/**
 * LoanContrPaymentOUTService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile;

    /*
     *  LoanContrPaymentOUTService java interface
     */

    public interface LoanContrPaymentOUTService {
          

        /**
          * Auto generated method signature
          * 
                    * @param reverLoanContractRequestOut0
                
         */

         
                     public cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverLoanContractResponseOut reverLoanContrPayment(

                        cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverLoanContractRequestOut reverLoanContractRequestOut0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param reverLoanContractRequestOut0
            
          */
        public void startreverLoanContrPayment(

            cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverLoanContractRequestOut reverLoanContractRequestOut0,

            final cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPaymentOUTServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param loanContrPaymentRequestOut2
                
         */

         
                     public cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPaymentResponseOut execLoanContrPayment(

                        cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPaymentRequestOut loanContrPaymentRequestOut2)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param loanContrPaymentRequestOut2
            
          */
        public void startexecLoanContrPayment(

            cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPaymentRequestOut loanContrPaymentRequestOut2,

            final cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPaymentOUTServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    