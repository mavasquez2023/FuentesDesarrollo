
/**
 * LoanContrPaymentOUTServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile;

    /**
     *  LoanContrPaymentOUTServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class LoanContrPaymentOUTServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public LoanContrPaymentOUTServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public LoanContrPaymentOUTServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for reverLoanContrPayment method
            * override this method for handling normal response from reverLoanContrPayment operation
            */
           public void receiveResultreverLoanContrPayment(
                    cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverLoanContractResponseOut result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from reverLoanContrPayment operation
           */
            public void receiveErrorreverLoanContrPayment(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for execLoanContrPayment method
            * override this method for handling normal response from execLoanContrPayment operation
            */
           public void receiveResultexecLoanContrPayment(
                    cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPaymentResponseOut result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from execLoanContrPayment operation
           */
            public void receiveErrorexecLoanContrPayment(java.lang.Exception e) {
            }
                


    }
    