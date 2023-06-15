

/**
 * QueryBPStatusOUTService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package cl.laaraucana.simulacion.webservices.autogenerado.QueryBPStatus.web_mobile;

    /*
     *  QueryBPStatusOUTService java interface
     */

    public interface QueryBPStatusOUTService {
          

        /**
          * Auto generated method signature
          * 
                    * @param queryBPStatusRequestOut0
                
         */

         
                     public cl.laaraucana.simulacion.webservices.autogenerado.QueryBPStatus.web_mobile.QueryBPStatusResponseOut queryBPStatus(

                        cl.laaraucana.simulacion.webservices.autogenerado.QueryBPStatus.web_mobile.QueryBPStatusRequestOut queryBPStatusRequestOut0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param queryBPStatusRequestOut0
            
          */
        public void startqueryBPStatus(

            cl.laaraucana.simulacion.webservices.autogenerado.QueryBPStatus.web_mobile.QueryBPStatusRequestOut queryBPStatusRequestOut0,

            final cl.laaraucana.simulacion.webservices.autogenerado.QueryBPStatus.web_mobile.QueryBPStatusOUTServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    