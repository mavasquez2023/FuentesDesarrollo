/**
 * ActualizarCausanteStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
package cl.araucana.wssiagf.wsservices.actualizarCausante;


/*
 *  ActualizarCausanteStub java implementation
 */
public class ActualizarCausanteStub extends org.apache.axis2.client.Stub {
    protected org.apache.axis2.description.AxisOperation[] _operations;

    //hashmaps to keep the fault mapping
    private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
    private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
    private java.util.HashMap faultMessageMap = new java.util.HashMap();
    private javax.xml.namespace.QName[] opNameArray = null;

    /**
     *Constructor that takes in a configContext
     */
    public ActualizarCausanteStub(
        org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(configurationContext, targetEndpoint, false);
    }

    /**
     * Constructor that takes in a configContext  and useseperate listner
     */
    public ActualizarCausanteStub(
        org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
        //To populate AxisService
        populateAxisService();
        populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,
                _service);

        configurationContext = _serviceClient.getServiceContext()
                                             .getConfigurationContext();

        _serviceClient.getOptions()
                      .setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

        //Set the soap version
        _serviceClient.getOptions()
                      .setSoapVersionURI(org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
    }

    /**
     * Default Constructor
     */
    public ActualizarCausanteStub(
        org.apache.axis2.context.ConfigurationContext configurationContext)
        throws org.apache.axis2.AxisFault {
        this(configurationContext,
            "http://64.76.142.242:8081/axis2/services/ActualizarCausante");
    }

    /**
     * Default Constructor
     */
    public ActualizarCausanteStub() throws org.apache.axis2.AxisFault {
        this("http://64.76.142.242:8081/axis2/services/ActualizarCausante");
    }

    /**
     * Constructor taking the target endpoint
     */
    public ActualizarCausanteStub(java.lang.String targetEndpoint)
        throws org.apache.axis2.AxisFault {
        this(null, targetEndpoint);
    }

    private void populateAxisService() throws org.apache.axis2.AxisFault {
        //creating the Service with a unique name
        _service = new org.apache.axis2.description.AxisService(
                "ActualizarCausante" + this.hashCode());

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[1];

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName(
                "http://ws.siagf.paperless.cl", "actualizarCausante"));
        _service.addOperation(__operation);

        _operations[0] = __operation;
    }

    //populates the faults
    private void populateFaults() {
    }

    /**
     * Auto generated method signature
     * @see cl.araucana.wssiagf.wsservices.actualizarCausante.ActualizarCausante#actualizarCausante
     * @param actualizarCausante
     */
    public cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument actualizarCausante(
        cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteDocument actualizarCausante)
        throws java.rmi.RemoteException {
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
            _operationClient.getOptions().setAction("urn:actualizarCausante");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(_operationClient,
                org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                "&");

            // create a message context
            org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions()
                                                        .getSoapVersionURI()),
                    actualizarCausante,
                    optimizeContent(
                        new javax.xml.namespace.QName(
                            "http://ws.siagf.paperless.cl", "actualizarCausante")));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            //execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(_returnEnv.getBody()
                                                       .getFirstElement(),
                    cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.class,
                    getEnvelopeNamespaces(_returnEnv));
            _messageContext.getTransportOut().getSender()
                           .cleanup(_messageContext);

            return (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument) object;
        } catch (org.apache.axis2.AxisFault f) {
            org.apache.axiom.om.OMElement faultElt = f.getDetail();

            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
                    //make the fault by reflection
                    try {
                        java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();

                        //message class
                        java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,
                                messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                new java.lang.Class[] { messageClass });
                        m.invoke(ex, new java.lang.Object[] { messageObject });

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (java.lang.ClassCastException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        }
    }

    /**
     *  A utility method that copies the namepaces from the SOAPEnvelope
     */
    private java.util.Map getEnvelopeNamespaces(
        org.apache.axiom.soap.SOAPEnvelope env) {
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();

        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
        }

        return returnMap;
    }

    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        if (opNameArray == null) {
            return false;
        }

        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;
            }
        }

        return false;
    }

    //http://64.76.142.242:8081/axis2/services/ActualizarCausante
    private org.apache.axiom.om.OMElement toOM(
        cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteDocument param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        org.apache.axiom.om.impl.builder.StAXOMBuilder builder = new org.apache.axiom.om.impl.builder.StAXOMBuilder(org.apache.axiom.om.OMAbstractFactory.getOMFactory(),
                new org.apache.axis2.util.StreamWrapper(param.newXMLStreamReader()));
        org.apache.axiom.om.OMElement documentElement = builder.getDocumentElement();

        ((org.apache.axiom.om.impl.OMNodeEx) documentElement).setParent(null);

        return documentElement;
    }

    private org.apache.axiom.om.OMElement toOM(
        cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        org.apache.axiom.om.impl.builder.StAXOMBuilder builder = new org.apache.axiom.om.impl.builder.StAXOMBuilder(org.apache.axiom.om.OMAbstractFactory.getOMFactory(),
                new org.apache.axis2.util.StreamWrapper(param.newXMLStreamReader()));
        org.apache.axiom.om.OMElement documentElement = builder.getDocumentElement();

        ((org.apache.axiom.om.impl.OMNodeEx) documentElement).setParent(null);

        return documentElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteDocument param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        org.apache.axiom.soap.SOAPEnvelope envelope = factory.getDefaultEnvelope();

        if (param != null) {
            envelope.getBody().addChild(toOM(param, optimizeContent));
        }

        return envelope;
    }

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    public org.apache.xmlbeans.XmlObject fromOM(
        org.apache.axiom.om.OMElement param, java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault {
        try {
            if (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteDocument.class.equals(
                        type)) {
                if (extraNamespaces != null) {
                    return cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteDocument.Factory.parse(param.getXMLStreamReaderWithoutCaching(),
                        new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(
                            extraNamespaces));
                } else {
                    return cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteDocument.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                }
            }

            if (cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.class.equals(
                        type)) {
                if (extraNamespaces != null) {
                    return cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.Factory.parse(param.getXMLStreamReaderWithoutCaching(),
                        new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(
                            extraNamespaces));
                } else {
                    return cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                }
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }
}
