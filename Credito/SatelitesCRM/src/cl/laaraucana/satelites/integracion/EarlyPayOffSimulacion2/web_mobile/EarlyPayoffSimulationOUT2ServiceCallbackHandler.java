
/**
 * EarlyPayoffSimulationOUT2ServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile;

    /**
     *  EarlyPayoffSimulationOUT2ServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class EarlyPayoffSimulationOUT2ServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public EarlyPayoffSimulationOUT2ServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public EarlyPayoffSimulationOUT2ServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for earlyPayoffSimulation method
            * override this method for handling normal response from earlyPayoffSimulation operation
            */
           public void receiveResultearlyPayoffSimulation(
                    cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.EarlyPayoffSimulationResponseOut2 result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from earlyPayoffSimulation operation
           */
            public void receiveErrorearlyPayoffSimulation(java.lang.Exception e) {
            }
                


    }
    