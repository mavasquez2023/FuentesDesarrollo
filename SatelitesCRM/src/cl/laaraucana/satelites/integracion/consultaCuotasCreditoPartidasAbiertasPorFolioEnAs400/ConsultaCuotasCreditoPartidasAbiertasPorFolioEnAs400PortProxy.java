package cl.laaraucana.satelites.integracion.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public class ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400PortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private cl.laaraucana.satelites.integracion.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400Service _service = null;
        private cl.laaraucana.satelites.integracion.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 _proxy = null;
        private Dispatch<Source> _dispatch = null;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new cl.laaraucana.satelites.integracion.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400Service(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            _service = new cl.laaraucana.satelites.integracion.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400Service();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400Port();
        }

        public cl.laaraucana.satelites.integracion.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 getProxy() {
            return _proxy;
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://delegate.toAS.legados.integracion.laaraucana.cl/", "ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400Port");
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

    public ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400PortProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400PortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public ConsultaCuotasCreditoPartidasAbiertaPorFolioSalidaVO consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400(ConsultaCuotasCreditoPartidasAbiertasPorFolioEntradaVO arg0) {
        return _getDescriptor().getProxy().consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400(arg0);
    }

}