

/**
 * QueryContractHeaderOUTService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile;

    /*
     *  QueryContractHeaderOUTService java interface
     */

    public interface QueryContractHeaderOUTService {
          

        /**
          * Auto generated method signature
          * 
                    * @param queryContractHeaderRequestOut0
                
         */

         
                     public cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.QueryContractHeaderResponseOut queryContractHeader(

                        cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.QueryContractHeaderRequestOut queryContractHeaderRequestOut0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param queryContractHeaderRequestOut0
            
          */
        public void startqueryContractHeader(

            cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.QueryContractHeaderRequestOut queryContractHeaderRequestOut0,

            final cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.QueryContractHeaderOUTServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    