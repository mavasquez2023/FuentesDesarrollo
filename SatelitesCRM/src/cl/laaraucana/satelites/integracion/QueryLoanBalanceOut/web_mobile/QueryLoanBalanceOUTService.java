

/**
 * QueryLoanBalanceOUTService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile;

    /*
     *  QueryLoanBalanceOUTService java interface
     */

    public interface QueryLoanBalanceOUTService {
          

        /**
          * Auto generated method signature
          * 
                    * @param queryLoanBalanceRequestOut0
                
         */

         
                     public cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanBalanceResponseOut queryLoanBalance(

                        cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanBalanceRequestOut queryLoanBalanceRequestOut0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param queryLoanBalanceRequestOut0
            
          */
        public void startqueryLoanBalance(

            cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanBalanceRequestOut queryLoanBalanceRequestOut0,

            final cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanBalanceOUTServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    