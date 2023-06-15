

/**
 * EarlyPayoffSimulationOUTService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package cl.laaraucana.satelites.integracion.EarlyPayoffSimulationOUT.web_mobile;

    /*
     *  EarlyPayoffSimulationOUTService java interface
     */

    public interface EarlyPayoffSimulationOUTService {
          

        /**
          * Auto generated method signature
          * 
                    * @param earlyPayoffSimulationRequestOut0
                
         */

         
                     public cl.laaraucana.satelites.integracion.EarlyPayoffSimulationOUT.web_mobile.EarlyPayoffSimulationResponseOut earlyPayoffSimulation(

                        cl.laaraucana.satelites.integracion.EarlyPayoffSimulationOUT.web_mobile.EarlyPayoffSimulationRequestOut earlyPayoffSimulationRequestOut0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param earlyPayoffSimulationRequestOut0
            
          */
        public void startearlyPayoffSimulation(

            cl.laaraucana.satelites.integracion.EarlyPayoffSimulationOUT.web_mobile.EarlyPayoffSimulationRequestOut earlyPayoffSimulationRequestOut0,

            final cl.laaraucana.satelites.integracion.EarlyPayoffSimulationOUT.web_mobile.EarlyPayoffSimulationOUTServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    