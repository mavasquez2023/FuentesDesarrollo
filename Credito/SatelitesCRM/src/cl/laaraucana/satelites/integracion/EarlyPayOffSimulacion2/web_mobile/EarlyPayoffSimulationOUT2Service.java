

/**
 * EarlyPayoffSimulationOUT2Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile;

    /*
     *  EarlyPayoffSimulationOUT2Service java interface
     */

    public interface EarlyPayoffSimulationOUT2Service {
          

        /**
          * Auto generated method signature
          * 
                    * @param earlyPayoffSimulationRequestOut20
                
         */

         
                     public cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.EarlyPayoffSimulationResponseOut2 earlyPayoffSimulation(

                        cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.EarlyPayoffSimulationRequestOut2 earlyPayoffSimulationRequestOut20)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param earlyPayoffSimulationRequestOut20
            
          */
        public void startearlyPayoffSimulation(

            cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.EarlyPayoffSimulationRequestOut2 earlyPayoffSimulationRequestOut20,

            final cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.EarlyPayoffSimulationOUT2ServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    