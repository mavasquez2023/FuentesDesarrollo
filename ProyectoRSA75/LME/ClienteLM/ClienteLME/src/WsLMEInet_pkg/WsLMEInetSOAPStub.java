/**
 * WsLMEInetSOAPStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package WsLMEInet_pkg;

public class WsLMEInetSOAPStub extends org.apache.axis.client.Stub implements WsLMEInet_pkg.WsLMEInet_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[11];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LMEEvenLcc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LMEEvenLcc"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:WsLMEInet", "LMEEvenLcc"), WsLMEInet_pkg.LMEEvenLcc.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEEvenLccResponse"));
        oper.setReturnClass(WsLMEInet_pkg.LMEEvenLccResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "LMEEvenLccReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LMEEvenFec");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LMEEvenFec"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:WsLMEInet", "LMEEvenFec"), WsLMEInet_pkg.LMEEvenFec.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEEvenFecResponse"));
        oper.setReturnClass(WsLMEInet_pkg.LMEEvenFecResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "LMEEvenFecReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LMEDetLcc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LMEDetLcc"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:WsLMEInet", "LMEDetLcc"), WsLMEInet_pkg.LMEDetLcc.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEDetLccResponse"));
        oper.setReturnClass(WsLMEInet_pkg.LMEDetLccResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "LMEDetLccReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LMEInfResol");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LMEInfResolRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfResol"), WsLMEInet_pkg.LMEInfResol.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfResolResponse"));
        oper.setReturnClass(WsLMEInet_pkg.LMEInfResolResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "LMEInfResolReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LMEDevEmp");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LMEDevEmp"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:WsLMEInet", "LMEDevEmp"), WsLMEInet_pkg.LMEDevEmp.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEDevEmpResponse"));
        oper.setReturnClass(WsLMEInet_pkg.LMEDevEmpResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "LMEDevEmpReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LMEInfValCCAF");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LMEInfValCCAF"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfValCCAF"), WsLMEInet_pkg.LMEInfValCCAF.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfValCCAFResponse"));
        oper.setReturnClass(WsLMEInet_pkg.LMEInfValCCAFResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "LMEInfValCCAFReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LMEInfLiquid");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LMEInfLiquid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfLiquid"), WsLMEInet_pkg.LMEInfLiquid.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfLiquidResponse"));
        oper.setReturnClass(WsLMEInet_pkg.LMEInfLiquidResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "LMEInfLiquidReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LMEInfSeccC");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LMEInfSeccC"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfSeccC"), WsLMEInet_pkg.LMEInfSeccC.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfSeccCResponse"));
        oper.setReturnClass(WsLMEInet_pkg.LMEInfSeccCResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "LMEInfSeccCReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LMEVerTramEmp");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LMEVerTramEmp"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:WsLMEInet", "LMEVerTramEmp"), WsLMEInet_pkg.LMEVerTramEmp.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEVerTramEmpResponse"));
        oper.setReturnClass(WsLMEInet_pkg.LMEVerTramEmpResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "LMEVerTramEmpReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LMEInfTramEmp");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LMEInfTramEmp"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfTramEmp"), WsLMEInet_pkg.LMEInfTramEmp.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfTramEmpResponse"));
        oper.setReturnClass(WsLMEInet_pkg.LMEInfTramEmpResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "LMEInfTramEmpReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LMEVerLccTrab");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LMEVerLccTrab"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:WsLMEInet", "LMEVerLccTrab"), WsLMEInet_pkg.LMEVerLccTrab.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEVerLccTrabResponse"));
        oper.setReturnClass(WsLMEInet_pkg.LMEVerLccTrabResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "LMEVerLccTrabReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[10] = oper;

    }

    public WsLMEInetSOAPStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public WsLMEInetSOAPStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public WsLMEInetSOAPStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LicenciaCompletaType");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LicenciaCompletaType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LicenciaSimpleType");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LicenciaSimpleType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LicenciaType");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LicenciaType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "Liquidacion");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.Liquidacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "ListaLicenciasCompletaType");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LicenciaCompletaType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LicenciaCompletaType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "ListaLicenciasSimpleType");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LicenciaSimpleType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LicenciaSimpleType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "ListaLicenciasType");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LicenciaType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LicenciaType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "ListaLiquidacionType");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.Liquidacion[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "Liquidacion");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "ListaLmeTrabType");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LmeTrabType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LmeTrabType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "ListaResultadoType");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.ResultadoType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "ResultadoType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEDetLcc");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEDetLcc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEDetLccResponse");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEDetLccResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEDevEmp");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEDevEmp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEDevEmpResponse");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEDevEmpResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEEvenFec");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEEvenFec.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEEvenFecResponse");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEEvenFecResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEEvenLcc");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEEvenLcc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEEvenLccResponse");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEEvenLccResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfLiquid");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEInfLiquid.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfLiquidResponse");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEInfLiquidResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfResol");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEInfResol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfResolResponse");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEInfResolResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfSeccC");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEInfSeccC.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfSeccCResponse");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEInfSeccCResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfTramEmp");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEInfTramEmp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfTramEmpResponse");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEInfTramEmpResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfValCCAF");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEInfValCCAF.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfValCCAFResponse");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEInfValCCAFResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LmeTrabType");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LmeTrabType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEVerLccTrab");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEVerLccTrab.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEVerLccTrabResponse");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEVerLccTrabResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEVerTramEmp");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEVerTramEmp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "LMEVerTramEmpResponse");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.LMEVerTramEmpResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:WsLMEInet", "ResultadoType");
            cachedSerQNames.add(qName);
            cls = WsLMEInet_pkg.ResultadoType.class;
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
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
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


    /**
     * Consulta de eventos asociados a licencia médica electrónica.
     */
    public WsLMEInet_pkg.LMEEvenLccResponse LMEEvenLcc(WsLMEInet_pkg.LMEEvenLcc LMEEvenLcc) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:WsLMEInet");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEEvenLcc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LMEEvenLcc});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WsLMEInet_pkg.LMEEvenLccResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (WsLMEInet_pkg.LMEEvenLccResponse) org.apache.axis.utils.JavaUtils.convert(_resp, WsLMEInet_pkg.LMEEvenLccResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Consulta de eventos asociados a licencia médica electrónica
     * filtrados por rango de fechas.
     */
    public WsLMEInet_pkg.LMEEvenFecResponse LMEEvenFec(WsLMEInet_pkg.LMEEvenFec LMEEvenFec) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:WsLMEInet");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEEvenFec"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LMEEvenFec});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WsLMEInet_pkg.LMEEvenFecResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (WsLMEInet_pkg.LMEEvenFecResponse) org.apache.axis.utils.JavaUtils.convert(_resp, WsLMEInet_pkg.LMEEvenFecResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Consulta detalle licencias a pronunciar o validar.
     */
    public WsLMEInet_pkg.LMEDetLccResponse LMEDetLcc(WsLMEInet_pkg.LMEDetLcc LMEDetLcc) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:WsLMEInet");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEDetLcc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LMEDetLcc});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WsLMEInet_pkg.LMEDetLccResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (WsLMEInet_pkg.LMEDetLccResponse) org.apache.axis.utils.JavaUtils.convert(_resp, WsLMEInet_pkg.LMEDetLccResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Informa resolución lme pronunciada.
     */
    public WsLMEInet_pkg.LMEInfResolResponse LMEInfResol(WsLMEInet_pkg.LMEInfResol LMEInfResolRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:WsLMEInet");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfResol"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LMEInfResolRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WsLMEInet_pkg.LMEInfResolResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (WsLMEInet_pkg.LMEInfResolResponse) org.apache.axis.utils.JavaUtils.convert(_resp, WsLMEInet_pkg.LMEInfResolResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Devolución licencia medica electrónica.
     */
    public WsLMEInet_pkg.LMEDevEmpResponse LMEDevEmp(WsLMEInet_pkg.LMEDevEmp LMEDevEmp) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:WsLMEInet");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEDevEmp"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LMEDevEmp});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WsLMEInet_pkg.LMEDevEmpResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (WsLMEInet_pkg.LMEDevEmpResponse) org.apache.axis.utils.JavaUtils.convert(_resp, WsLMEInet_pkg.LMEDevEmpResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Validación licencia medica electrónica ccaf.
     */
    public WsLMEInet_pkg.LMEInfValCCAFResponse LMEInfValCCAF(WsLMEInet_pkg.LMEInfValCCAF LMEInfValCCAF) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:WsLMEInet");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfValCCAF"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LMEInfValCCAF});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WsLMEInet_pkg.LMEInfValCCAFResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (WsLMEInet_pkg.LMEInfValCCAFResponse) org.apache.axis.utils.JavaUtils.convert(_resp, WsLMEInet_pkg.LMEInfValCCAFResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Liquidación licencia medica electrónica.
     */
    public WsLMEInet_pkg.LMEInfLiquidResponse LMEInfLiquid(WsLMEInet_pkg.LMEInfLiquid LMEInfLiquid) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:WsLMEInet");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfLiquid"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LMEInfLiquid});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WsLMEInet_pkg.LMEInfLiquidResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (WsLMEInet_pkg.LMEInfLiquidResponse) org.apache.axis.utils.JavaUtils.convert(_resp, WsLMEInet_pkg.LMEInfLiquidResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Informar datos zona c  licencia médica semi-electrónica.
     */
    public WsLMEInet_pkg.LMEInfSeccCResponse LMEInfSeccC(WsLMEInet_pkg.LMEInfSeccC LMEInfSeccC) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:WsLMEInet");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfSeccC"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LMEInfSeccC});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WsLMEInet_pkg.LMEInfSeccCResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (WsLMEInet_pkg.LMEInfSeccCResponse) org.apache.axis.utils.JavaUtils.convert(_resp, WsLMEInet_pkg.LMEInfSeccCResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Consulta de lme a tramitar empleadores.
     */
    public WsLMEInet_pkg.LMEVerTramEmpResponse LMEVerTramEmp(WsLMEInet_pkg.LMEVerTramEmp LMEVerTramEmp) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:WsLMEInet");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEVerTramEmp"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LMEVerTramEmp});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WsLMEInet_pkg.LMEVerTramEmpResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (WsLMEInet_pkg.LMEVerTramEmpResponse) org.apache.axis.utils.JavaUtils.convert(_resp, WsLMEInet_pkg.LMEVerTramEmpResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Recepción  resultado tramitación desde empleadores.
     */
    public WsLMEInet_pkg.LMEInfTramEmpResponse LMEInfTramEmp(WsLMEInet_pkg.LMEInfTramEmp LMEInfTramEmp) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:WsLMEInet");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfTramEmp"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LMEInfTramEmp});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WsLMEInet_pkg.LMEInfTramEmpResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (WsLMEInet_pkg.LMEInfTramEmpResponse) org.apache.axis.utils.JavaUtils.convert(_resp, WsLMEInet_pkg.LMEInfTramEmpResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Consulta de lme por trabajador.
     */
    public WsLMEInet_pkg.LMEVerLccTrabResponse LMEVerLccTrab(WsLMEInet_pkg.LMEVerLccTrab LMEVerLccTrab) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:WsLMEInet");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEVerLccTrab"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LMEVerLccTrab});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (WsLMEInet_pkg.LMEVerLccTrabResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (WsLMEInet_pkg.LMEVerLccTrabResponse) org.apache.axis.utils.JavaUtils.convert(_resp, WsLMEInet_pkg.LMEVerLccTrabResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
