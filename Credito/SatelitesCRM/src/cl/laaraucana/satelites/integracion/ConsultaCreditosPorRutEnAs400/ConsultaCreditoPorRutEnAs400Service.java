//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.satelites.integracion.ConsultaCreditosPorRutEnAs400;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "ConsultaCreditoPorRutEnAs400Service", targetNamespace = "http://delegate.toAS.legados.integracion.laaraucana.cl/", wsdlLocation = "WEB-INF/wsdl/ConsultaCreditoPorRutEnAs400.wsdl")
public class ConsultaCreditoPorRutEnAs400Service
    extends Service
{

    private final static URL CONSULTACREDITOPORRUTENAS400SERVICE_WSDL_LOCATION;
    private final static WebServiceException CONSULTACREDITOPORRUTENAS400SERVICE_EXCEPTION;
    private final static QName CONSULTACREDITOPORRUTENAS400SERVICE_QNAME = new QName("http://delegate.toAS.legados.integracion.laaraucana.cl/", "ConsultaCreditoPorRutEnAs400Service");

    static {
            CONSULTACREDITOPORRUTENAS400SERVICE_WSDL_LOCATION = cl.laaraucana.satelites.integracion.ConsultaCreditosPorRutEnAs400.ConsultaCreditoPorRutEnAs400Service.class.getResource("/WEB-INF/wsdl/ConsultaCreditoPorRutEnAs400.wsdl");
        WebServiceException e = null;
        if (CONSULTACREDITOPORRUTENAS400SERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'WEB-INF/wsdl/ConsultaCreditoPorRutEnAs400.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        CONSULTACREDITOPORRUTENAS400SERVICE_EXCEPTION = e;
    }

    public ConsultaCreditoPorRutEnAs400Service() {
        super(__getWsdlLocation(), CONSULTACREDITOPORRUTENAS400SERVICE_QNAME);
    }

    public ConsultaCreditoPorRutEnAs400Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), CONSULTACREDITOPORRUTENAS400SERVICE_QNAME, features);
    }

    public ConsultaCreditoPorRutEnAs400Service(URL wsdlLocation) {
        super(wsdlLocation, CONSULTACREDITOPORRUTENAS400SERVICE_QNAME);
    }

    public ConsultaCreditoPorRutEnAs400Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CONSULTACREDITOPORRUTENAS400SERVICE_QNAME, features);
    }

    public ConsultaCreditoPorRutEnAs400Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ConsultaCreditoPorRutEnAs400Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ConsultaCreditoPorRutEnAs400
     */
    @WebEndpoint(name = "ConsultaCreditoPorRutEnAs400Port")
    public ConsultaCreditoPorRutEnAs400 getConsultaCreditoPorRutEnAs400Port() {
        return super.getPort(new QName("http://delegate.toAS.legados.integracion.laaraucana.cl/", "ConsultaCreditoPorRutEnAs400Port"), ConsultaCreditoPorRutEnAs400.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ConsultaCreditoPorRutEnAs400
     */
    @WebEndpoint(name = "ConsultaCreditoPorRutEnAs400Port")
    public ConsultaCreditoPorRutEnAs400 getConsultaCreditoPorRutEnAs400Port(WebServiceFeature... features) {
        return super.getPort(new QName("http://delegate.toAS.legados.integracion.laaraucana.cl/", "ConsultaCreditoPorRutEnAs400Port"), ConsultaCreditoPorRutEnAs400.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CONSULTACREDITOPORRUTENAS400SERVICE_EXCEPTION!= null) {
            throw CONSULTACREDITOPORRUTENAS400SERVICE_EXCEPTION;
        }
        return CONSULTACREDITOPORRUTENAS400SERVICE_WSDL_LOCATION;
    }

}
