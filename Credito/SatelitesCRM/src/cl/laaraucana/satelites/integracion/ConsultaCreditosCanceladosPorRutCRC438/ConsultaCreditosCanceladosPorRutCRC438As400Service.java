//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.satelites.integracion.ConsultaCreditosCanceladosPorRutCRC438;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "ConsultaCreditosCanceladosPorRutCRC438As400Service", targetNamespace = "http://delegate.toAS.legados.integracion.laaraucana.cl/", wsdlLocation = "WEB-INF/wsdl/ConsultaCreditosCanceladosPorRutCRC438As400.wsdl")
public class ConsultaCreditosCanceladosPorRutCRC438As400Service
    extends Service
{

    private final static URL CONSULTACREDITOSCANCELADOSPORRUTCRC438AS400SERVICE_WSDL_LOCATION;
    private final static WebServiceException CONSULTACREDITOSCANCELADOSPORRUTCRC438AS400SERVICE_EXCEPTION;
    private final static QName CONSULTACREDITOSCANCELADOSPORRUTCRC438AS400SERVICE_QNAME = new QName("http://delegate.toAS.legados.integracion.laaraucana.cl/", "ConsultaCreditosCanceladosPorRutCRC438As400Service");

    static {
            CONSULTACREDITOSCANCELADOSPORRUTCRC438AS400SERVICE_WSDL_LOCATION = cl.laaraucana.satelites.integracion.ConsultaCreditosCanceladosPorRutCRC438.ConsultaCreditosCanceladosPorRutCRC438As400Service.class.getResource("/WEB-INF/wsdl/ConsultaCreditosCanceladosPorRutCRC438As400.wsdl");
        WebServiceException e = null;
        if (CONSULTACREDITOSCANCELADOSPORRUTCRC438AS400SERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'WEB-INF/wsdl/ConsultaCreditosCanceladosPorRutCRC438As400.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        CONSULTACREDITOSCANCELADOSPORRUTCRC438AS400SERVICE_EXCEPTION = e;
    }

    public ConsultaCreditosCanceladosPorRutCRC438As400Service() {
        super(__getWsdlLocation(), CONSULTACREDITOSCANCELADOSPORRUTCRC438AS400SERVICE_QNAME);
    }

    public ConsultaCreditosCanceladosPorRutCRC438As400Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), CONSULTACREDITOSCANCELADOSPORRUTCRC438AS400SERVICE_QNAME, features);
    }

    public ConsultaCreditosCanceladosPorRutCRC438As400Service(URL wsdlLocation) {
        super(wsdlLocation, CONSULTACREDITOSCANCELADOSPORRUTCRC438AS400SERVICE_QNAME);
    }

    public ConsultaCreditosCanceladosPorRutCRC438As400Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CONSULTACREDITOSCANCELADOSPORRUTCRC438AS400SERVICE_QNAME, features);
    }

    public ConsultaCreditosCanceladosPorRutCRC438As400Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ConsultaCreditosCanceladosPorRutCRC438As400Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ConsultaCreditosCanceladosPorRutCRC438As400
     */
    @WebEndpoint(name = "ConsultaCreditosCanceladosPorRutCRC438As400Port")
    public ConsultaCreditosCanceladosPorRutCRC438As400 getConsultaCreditosCanceladosPorRutCRC438As400Port() {
        return super.getPort(new QName("http://delegate.toAS.legados.integracion.laaraucana.cl/", "ConsultaCreditosCanceladosPorRutCRC438As400Port"), ConsultaCreditosCanceladosPorRutCRC438As400.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ConsultaCreditosCanceladosPorRutCRC438As400
     */
    @WebEndpoint(name = "ConsultaCreditosCanceladosPorRutCRC438As400Port")
    public ConsultaCreditosCanceladosPorRutCRC438As400 getConsultaCreditosCanceladosPorRutCRC438As400Port(WebServiceFeature... features) {
        return super.getPort(new QName("http://delegate.toAS.legados.integracion.laaraucana.cl/", "ConsultaCreditosCanceladosPorRutCRC438As400Port"), ConsultaCreditosCanceladosPorRutCRC438As400.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CONSULTACREDITOSCANCELADOSPORRUTCRC438AS400SERVICE_EXCEPTION!= null) {
            throw CONSULTACREDITOSCANCELADOSPORRUTCRC438AS400SERVICE_EXCEPTION;
        }
        return CONSULTACREDITOSCANCELADOSPORRUTCRC438AS400SERVICE_WSDL_LOCATION;
    }

}
