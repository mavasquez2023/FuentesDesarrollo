/**
 * WSFonaCajasSoap12Stub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WSFonaCajasNM;

public class WSFonaCajasSoap12Stub extends org.apache.axis.client.Stub implements WSFonaCajasNM.WSFonaCajasSoap {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[5];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ActEstLicCCAF");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodigoUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ClaveUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodigoAsegurador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"), short.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodigoOperador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ListaEstLCC"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ArrayOfEntrActEstLicCCAF"), WSFonaCajasNM.EntrActEstLicCCAF[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "EntrActEstLicCCAF"));
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RespActEstLicCCAF"));
        oper.setReturnClass(WSFonaCajasNM.RespActEstLicCCAF.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ActEstLicCCAFResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InfEstLicCCAF");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodigoUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ClaveUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodigoAsegurador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"), short.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodigoOperador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ListaEstLCC"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ArrayOfEntrInfEstLicCCAF"), WSFonaCajasNM.EntrInfEstLicCCAF[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "EntrInfEstLicCCAF"));
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RespInfEstLicCCAF"));
        oper.setReturnClass(WSFonaCajasNM.RespInfEstLicCCAF.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "InfEstLicCCAFResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LicCertifTrab");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RutBeneficiario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "FecCertifica"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RutInstitucion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodigoUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ClaveUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RespLicCertifTrab"));
        oper.setReturnClass(WSFonaCajasNM.RespLicCertifTrab.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LicCertifTrabResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("VerEstLicCCAF");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodigoUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ClaveUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodigoAsegurador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"), short.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodigoOperador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "TipFormulario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"), short.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "NumFormulario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RespVerEstLicCCAF"));
        oper.setReturnClass(WSFonaCajasNM.RespVerEstLicCCAF.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "VerEstLicCCAFResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConFormuLCC");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodigoUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ClaveUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodigoAsegurador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"), short.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodigoOperador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "TipFormulario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"), short.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "NumFormulario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RespConFormLCC"));
        oper.setReturnClass(WSFonaCajasNM.RespConFormLCC.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ConFormuLCCResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

    }

    public WSFonaCajasSoap12Stub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public WSFonaCajasSoap12Stub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public WSFonaCajasSoap12Stub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ArrayOfEntrActEstLicCCAF");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.EntrActEstLicCCAF[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "EntrActEstLicCCAF");
            qName2 = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "EntrActEstLicCCAF");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ArrayOfEntrInfEstLicCCAF");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.EntrInfEstLicCCAF[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "EntrInfEstLicCCAF");
            qName2 = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "EntrInfEstLicCCAF");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ArrayOfLstEstados");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.LstEstados[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LstEstados");
            qName2 = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LstEstados");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ArrayOfLstResAct");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.LstResAct[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LstResAct");
            qName2 = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LstResAct");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ArrayOfLstResInf");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.LstResInf[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LstResInf");
            qName2 = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LstResInf");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "EntrActEstLicCCAF");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.EntrActEstLicCCAF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "EntrInfEstLicCCAF");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.EntrInfEstLicCCAF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LstEstados");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.LstEstados.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LstResAct");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.LstResAct.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LstResInf");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.LstResInf.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RespActEstLicCCAF");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.RespActEstLicCCAF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RespConFormLCC");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.RespConFormLCC.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RespInfEstLicCCAF");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.RespInfEstLicCCAF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RespLicCertifTrab");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.RespLicCertifTrab.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RespVerEstLicCCAF");
            cachedSerQNames.add(qName);
            cls = WSFonaCajasNM.RespVerEstLicCCAF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public WSFonaCajasNM.RespActEstLicCCAF actEstLicCCAF(java.lang.String codigoUsuario, java.lang.String claveUsuario, short codigoAsegurador, java.lang.String codigoOperador, WSFonaCajasNM.EntrActEstLicCCAF[] listaEstLCC) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://WSFonaCajasNM/ActEstLicCCAF");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ActEstLicCCAF"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigoUsuario, claveUsuario, new java.lang.Short(codigoAsegurador), codigoOperador, listaEstLCC});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WSFonaCajasNM.RespActEstLicCCAF) _resp;
            } catch (java.lang.Exception _exception) {
                return (WSFonaCajasNM.RespActEstLicCCAF) org.apache.axis.utils.JavaUtils.convert(_resp, WSFonaCajasNM.RespActEstLicCCAF.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public WSFonaCajasNM.RespInfEstLicCCAF infEstLicCCAF(java.lang.String codigoUsuario, java.lang.String claveUsuario, short codigoAsegurador, java.lang.String codigoOperador, WSFonaCajasNM.EntrInfEstLicCCAF[] listaEstLCC) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://WSFonaCajasNM/InfEstLicCCAF");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "InfEstLicCCAF"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigoUsuario, claveUsuario, new java.lang.Short(codigoAsegurador), codigoOperador, listaEstLCC});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WSFonaCajasNM.RespInfEstLicCCAF) _resp;
            } catch (java.lang.Exception _exception) {
                return (WSFonaCajasNM.RespInfEstLicCCAF) org.apache.axis.utils.JavaUtils.convert(_resp, WSFonaCajasNM.RespInfEstLicCCAF.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public WSFonaCajasNM.RespLicCertifTrab licCertifTrab(java.lang.String rutBeneficiario, java.lang.String fecCertifica, java.lang.String rutInstitucion, java.lang.String codigoUsuario, java.lang.String claveUsuario) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://WSFonaCajasNM/LicCertifTrab");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LicCertifTrab"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {rutBeneficiario, fecCertifica, rutInstitucion, codigoUsuario, claveUsuario});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WSFonaCajasNM.RespLicCertifTrab) _resp;
            } catch (java.lang.Exception _exception) {
                return (WSFonaCajasNM.RespLicCertifTrab) org.apache.axis.utils.JavaUtils.convert(_resp, WSFonaCajasNM.RespLicCertifTrab.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public WSFonaCajasNM.RespVerEstLicCCAF verEstLicCCAF(java.lang.String codigoUsuario, java.lang.String claveUsuario, short codigoAsegurador, java.lang.String codigoOperador, short tipFormulario, int numFormulario) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://WSFonaCajasNM/VerEstLicCCAF");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "VerEstLicCCAF"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigoUsuario, claveUsuario, new java.lang.Short(codigoAsegurador), codigoOperador, new java.lang.Short(tipFormulario), new java.lang.Integer(numFormulario)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WSFonaCajasNM.RespVerEstLicCCAF) _resp;
            } catch (java.lang.Exception _exception) {
                return (WSFonaCajasNM.RespVerEstLicCCAF) org.apache.axis.utils.JavaUtils.convert(_resp, WSFonaCajasNM.RespVerEstLicCCAF.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public WSFonaCajasNM.RespConFormLCC conFormuLCC(java.lang.String codigoUsuario, java.lang.String claveUsuario, short codigoAsegurador, java.lang.String codigoOperador, short tipFormulario, int numFormulario) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://WSFonaCajasNM/ConFormuLCC");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ConFormuLCC"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigoUsuario, claveUsuario, new java.lang.Short(codigoAsegurador), codigoOperador, new java.lang.Short(tipFormulario), new java.lang.Integer(numFormulario)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WSFonaCajasNM.RespConFormLCC) _resp;
            } catch (java.lang.Exception _exception) {
                return (WSFonaCajasNM.RespConFormLCC) org.apache.axis.utils.JavaUtils.convert(_resp, WSFonaCajasNM.RespConFormLCC.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
