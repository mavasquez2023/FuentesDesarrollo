/**
 * PlatinumServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService;

public class PlatinumServiceSoapBindingStub extends org.apache.axis.client.Stub implements cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumImpl {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[1];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInforme");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "RUT"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "numeroSerie"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ICom"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "usoInternoDicom01"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "usoInternoDicom02"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "usoInternoDicom03"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "usuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://platinum.gru01.wse.equifax.cl", "PLATINUMOutput"));
        oper.setReturnClass(cl.equifax.wse.gru01.platinum.PLATINUMOutput.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInformeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

    }

    public PlatinumServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public PlatinumServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public PlatinumServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMAntecedentesLaborales");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.PLATINUMAntecedentesLaborales.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMAntecedentesParticulares");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.PLATINUMAntecedentesParticulares.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMConsultaRut");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.PLATINUMConsultaRut.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMIdentificacionEmpresa");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.PLATINUMIdentificacionEmpresa.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMIdentificacionPersona");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.PLATINUMIdentificacionPersona.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMIndicadorAcreditacion");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.PLATINUMIndicadorAcreditacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMIndicadoresPrevencionFraude");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.PLATINUMIndicadoresPrevencionFraude.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMIndicadorRiesgoCrediticio");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.PLATINUMIndicadorRiesgoCrediticio.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMJustificacion");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.PLATINUMJustificacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMRegistroMorosidadesYProtestos");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.PLATINUMRegistroMorosidadesYProtestos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMResumen");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.PLATINUMResumen.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMResumenMorosidadesYProtestosImpagos");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.PLATINUMResumenMorosidadesYProtestosImpagos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://platinum.gru01.wse.equifax.cl", "PLATINUMOutput");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.PLATINUMOutput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMAspectoRelevante");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMAspectoRelevante[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMAspectoRelevante");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMCuentaCorrienteCerrada");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMCuentaCorrienteCerrada[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMCuentaCorrienteCerrada");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMDetalleActividadEconomica");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleActividadEconomica[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleActividadEconomica");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMDetalleConsultaRut");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleConsultaRut[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleConsultaRut");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMDetalleEjecutivo");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleEjecutivo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleEjecutivo");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMDetalleONP");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleONP[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleONP");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMEscalaPredictor");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMEscalaPredictor[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMEscalaPredictor");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMNombreBanco");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMNombreBanco[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMNombreBanco");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMPredictorHistorico");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMPredictorHistorico[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMPredictorHistorico");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMAspectoRelevante");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMAspectoRelevante.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMCuentaCorrienteCerrada");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMCuentaCorrienteCerrada.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleActividadEconomica");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleActividadEconomica.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleConsultaRut");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleConsultaRut.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleEjecutivo");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleEjecutivo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleONP");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleONP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMEscalaPredictor");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMEscalaPredictor.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMNombreBanco");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMNombreBanco.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMPredictorHistorico");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub.PLATINUMPredictorHistorico.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMMorosidadBED");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBED[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMMorosidadBED");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMMorosidadBOLAB");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLAB[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMMorosidadBOLAB");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMMorosidadBOLCOM");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLCOM[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMMorosidadBOLCOM");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMMorosidadICOM");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadICOM[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMMorosidadICOM");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "ArrayOfPLATINUMPorMercado");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMPorMercado[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMPorMercado");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMMorosidadBED");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBED.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMMorosidadBOLAB");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLAB.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMMorosidadBOLCOM");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLCOM.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMMorosidadICOM");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadICOM.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMPorMercado");
            cachedSerQNames.add(qName);
            cls = cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMPorMercado.class;
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

    public cl.equifax.wse.gru01.platinum.PLATINUMOutput getInforme(java.lang.String RUT, java.lang.String numeroSerie, java.lang.String ICom, java.lang.String usoInternoDicom01, java.lang.String usoInternoDicom02, java.lang.String usoInternoDicom03, java.lang.String usuario, java.lang.String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://app.gru01.wse.equifax.cl", "getInforme"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {RUT, numeroSerie, ICom, usoInternoDicom01, usoInternoDicom02, usoInternoDicom03, usuario, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cl.equifax.wse.gru01.platinum.PLATINUMOutput) _resp;
            } catch (java.lang.Exception _exception) {
                return (cl.equifax.wse.gru01.platinum.PLATINUMOutput) org.apache.axis.utils.JavaUtils.convert(_resp, cl.equifax.wse.gru01.platinum.PLATINUMOutput.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
