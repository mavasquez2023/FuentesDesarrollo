//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.acepta.soap.ca4xml;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "ca4xml", targetNamespace = "http://acepta.com/soap/ca4xml", wsdlLocation = "WEB-INF/wsdl/acepta.wsdl")
public class Ca4Xml_Service
    extends Service
{

    private final static URL CA4XML_WSDL_LOCATION;
    private final static WebServiceException CA4XML_EXCEPTION;
    private final static QName CA4XML_QNAME = new QName("http://acepta.com/soap/ca4xml", "ca4xml");

    static {
            CA4XML_WSDL_LOCATION = com.acepta.soap.ca4xml.Ca4Xml_Service.class.getResource("/WEB-INF/wsdl/acepta.wsdl");
        WebServiceException e = null;
        if (CA4XML_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'WEB-INF/wsdl/acepta.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        CA4XML_EXCEPTION = e;
    }

    public Ca4Xml_Service() {
        super(__getWsdlLocation(), CA4XML_QNAME);
    }

    public Ca4Xml_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), CA4XML_QNAME, features);
    }

    public Ca4Xml_Service(URL wsdlLocation) {
        super(wsdlLocation, CA4XML_QNAME);
    }

    public Ca4Xml_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CA4XML_QNAME, features);
    }

    public Ca4Xml_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Ca4Xml_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Ca4Xml
     */
    @WebEndpoint(name = "ca4xml")
    public Ca4Xml getCa4Xml() {
        return super.getPort(new QName("http://acepta.com/soap/ca4xml", "ca4xml"), Ca4Xml.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Ca4Xml
     */
    @WebEndpoint(name = "ca4xml")
    public Ca4Xml getCa4Xml(WebServiceFeature... features) {
        return super.getPort(new QName("http://acepta.com/soap/ca4xml", "ca4xml"), Ca4Xml.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CA4XML_EXCEPTION!= null) {
            throw CA4XML_EXCEPTION;
        }
        return CA4XML_WSDL_LOCATION;
    }

}