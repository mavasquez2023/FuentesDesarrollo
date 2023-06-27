package cl.laaraucana.capaservicios.webservices.autogenerado.SolSMS;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.ws.Holder;

public class SMSServicePortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private cl.laaraucana.capaservicios.webservices.autogenerado.SolSMS.WSSMS _service = null;
        private cl.laaraucana.capaservicios.webservices.autogenerado.SolSMS.SMSServicePortType _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new cl.laaraucana.capaservicios.webservices.autogenerado.SolSMS.WSSMS(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (cl.laaraucana.capaservicios.webservices.autogenerado.SolSMS.WSSMS)ctx.lookup("java:comp/env/service/WS_SMS");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new cl.laaraucana.capaservicios.webservices.autogenerado.SolSMS.WSSMS();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getSMSServicePort();
        }

        public cl.laaraucana.capaservicios.webservices.autogenerado.SolSMS.SMSServicePortType getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http:mobile.asicom.cl", "SMSServicePort");
                _dispatch = _service.createDispatch(portQName, Source.class, Service.Mode.MESSAGE);

                String proxyEndpointUrl = getEndpoint();
                BindingProvider bp = (BindingProvider) _dispatch;
                String dispatchEndpointUrl = (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
                if (!dispatchEndpointUrl.equals(proxyEndpointUrl))
                    bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, proxyEndpointUrl);
            }
            return _dispatch;
        }

        public String getEndpoint() {
            BindingProvider bp = (BindingProvider) _proxy;
            return (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
        }

        public void setEndpoint(String endpointUrl) {
            BindingProvider bp = (BindingProvider) _proxy;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

            if (_dispatch != null ) {
                bp = (BindingProvider) _dispatch;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
            }
        }

        public void setMTOMEnabled(boolean enable) {
            SOAPBinding binding = (SOAPBinding) ((BindingProvider) _proxy).getBinding();
            binding.setMTOMEnabled(enable);
        }
    }

    public SMSServicePortProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public SMSServicePortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public SMSRegisters getRegisters(String usuario, String clave, String idc, String fechaInicial, String fechaFinal) {
        return _getDescriptor().getProxy().getRegisters(usuario,clave,idc,fechaInicial,fechaFinal);
    }

    public void sendSMS(String usuario, String clave, String destino, String mensaje, Holder<String> estado, Holder<String> nc, Holder<String> idmensaje) {
        _getDescriptor().getProxy().sendSMS(usuario,clave,destino,mensaje,estado,nc,idmensaje);
    }

    public void sendSMSStatus(String usuario, String clave, Holder<String> idmensaje, Holder<String> estadoEnvio, Holder<String> estadoEntrega) {
        _getDescriptor().getProxy().sendSMSStatus(usuario,clave,idmensaje,estadoEnvio,estadoEntrega);
    }

}