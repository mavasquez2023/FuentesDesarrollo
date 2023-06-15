
/**
 * OficinaGastosNotarialOUT2ServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package cl.laaraucana.simulacion.webservices.autogenerado.oficinaGastosNotarial.web_mobile;

    /**
     *  OficinaGastosNotarialOUT2ServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class OficinaGastosNotarialOUT2ServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public OficinaGastosNotarialOUT2ServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public OficinaGastosNotarialOUT2ServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for oficinaGastosNotarialOUT2 method
            * override this method for handling normal response from oficinaGastosNotarialOUT2 operation
            */
           public void receiveResultoficinaGastosNotarialOUT2(
                    cl.laaraucana.simulacion.webservices.autogenerado.oficinaGastosNotarial.web_mobile.OficinaGastoNotarialResponse2E result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from oficinaGastosNotarialOUT2 operation
           */
            public void receiveErroroficinaGastosNotarialOUT2(java.lang.Exception e) {
            }
                


    }
    