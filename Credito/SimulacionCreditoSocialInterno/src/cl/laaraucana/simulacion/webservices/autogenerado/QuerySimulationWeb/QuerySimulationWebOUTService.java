

/**
 * QuerySimulationWebOUTService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package cl.laaraucana.simulacion.webservices.autogenerado.QuerySimulationWeb;

    /*
     *  QuerySimulationWebOUTService java interface
     */

    public interface QuerySimulationWebOUTService {
          

        /**
          * Auto generated method signature
          * 
                    * @param querySimWebRequestOut0
                
         */

         
                     public cl.laaraucana.simulacion.webservices.autogenerado.QuerySimulationWeb.QuerySimWebResponseOut querySimulationWeb(

                        cl.laaraucana.simulacion.webservices.autogenerado.QuerySimulationWeb.QuerySimWebRequestOut querySimWebRequestOut0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param querySimWebRequestOut0
            
          */
        public void startquerySimulationWeb(

            cl.laaraucana.simulacion.webservices.autogenerado.QuerySimulationWeb.QuerySimWebRequestOut querySimWebRequestOut0,

            final cl.laaraucana.simulacion.webservices.autogenerado.QuerySimulationWeb.QuerySimulationWebOUTServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    