

/**
 * InfoProtestosOUTService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package cl.laaraucana.satelites.integracion.infoProtestos.web_mobile;

    /*
     *  InfoProtestosOUTService java interface
     */

    public interface InfoProtestosOUTService {
          

        /**
          * Auto generated method signature
          * 
                    * @param infoProtestoRequestOut0
                
         */

         
                     public cl.laaraucana.satelites.integracion.infoProtestos.web_mobile.InfoProtestoResponseOut infoProtestosIN(

                        cl.laaraucana.satelites.integracion.infoProtestos.web_mobile.InfoProtestoRequestOut infoProtestoRequestOut0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param infoProtestoRequestOut0
            
          */
        public void startinfoProtestosIN(

            cl.laaraucana.satelites.integracion.infoProtestos.web_mobile.InfoProtestoRequestOut infoProtestoRequestOut0,

            final cl.laaraucana.satelites.integracion.infoProtestos.web_mobile.InfoProtestosOUTServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    