

/**
 * InformarDeudaInterCajaOUTService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package cl.laaraucana.compromisototal.webservice.autogenerado.sap.deudaInterCaja.web_mobile;

    /*
     *  InformarDeudaInterCajaOUTService java interface
     */

    public interface InformarDeudaInterCajaOUTService {
          

        /**
          * Auto generated method signature
          * 
                    * @param deudaInterCajaRequestOut0
                
         */

         
                     public cl.laaraucana.compromisototal.webservice.autogenerado.sap.deudaInterCaja.web_mobile.DeudaInterCajaResponseOut getIntercajaDataByRut(

                        cl.laaraucana.compromisototal.webservice.autogenerado.sap.deudaInterCaja.web_mobile.DeudaInterCajaRequestOut deudaInterCajaRequestOut0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param deudaInterCajaRequestOut0
            
          */
        public void startgetIntercajaDataByRut(

            cl.laaraucana.compromisototal.webservice.autogenerado.sap.deudaInterCaja.web_mobile.DeudaInterCajaRequestOut deudaInterCajaRequestOut0,

            final cl.laaraucana.compromisototal.webservice.autogenerado.sap.deudaInterCaja.web_mobile.InformarDeudaInterCajaOUTServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    