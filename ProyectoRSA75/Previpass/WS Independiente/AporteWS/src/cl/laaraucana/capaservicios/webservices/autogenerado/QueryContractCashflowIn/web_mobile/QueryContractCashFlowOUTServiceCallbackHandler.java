
/**
 * QueryContractCashFlowOUTServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile;

    /**
     *  QueryContractCashFlowOUTServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class QueryContractCashFlowOUTServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public QueryContractCashFlowOUTServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public QueryContractCashFlowOUTServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for queryContractCashFlow method
            * override this method for handling normal response from queryContractCashFlow operation
            */
           public void receiveResultqueryContractCashFlow(
                    cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.QueryContractCashFlowResponseOut result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from queryContractCashFlow operation
           */
            public void receiveErrorqueryContractCashFlow(java.lang.Exception e) {
            }
                


    }
    