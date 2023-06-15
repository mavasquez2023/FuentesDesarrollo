//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.compromisototal.webservice.autogenerado.asicom;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.laaraucana.compromisototal.integracion.asicom package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetDetalleCredito_QNAME = new QName("http://server.laaraucana.ws.asicom/", "getDetalleCredito");
    private final static QName _GetDetalleCreditoResponse_QNAME = new QName("http://server.laaraucana.ws.asicom/", "getDetalleCreditoResponse");
    private final static QName _GetDatosCreditoResponse_QNAME = new QName("http://server.laaraucana.ws.asicom/", "getDatosCreditoResponse");
    private final static QName _GetDatosCredito_QNAME = new QName("http://server.laaraucana.ws.asicom/", "getDatosCredito");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.laaraucana.compromisototal.integracion.asicom
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDatosCredito }
     * 
     */
    public GetDatosCredito createGetDatosCredito() {
        return new GetDatosCredito();
    }

    /**
     * Create an instance of {@link GetDatosCreditoResponse }
     * 
     */
    public GetDatosCreditoResponse createGetDatosCreditoResponse() {
        return new GetDatosCreditoResponse();
    }

    /**
     * Create an instance of {@link GetDetalleCredito }
     * 
     */
    public GetDetalleCredito createGetDetalleCredito() {
        return new GetDetalleCredito();
    }

    /**
     * Create an instance of {@link GetDetalleCreditoResponse }
     * 
     */
    public GetDetalleCreditoResponse createGetDetalleCreditoResponse() {
        return new GetDetalleCreditoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDetalleCredito }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.laaraucana.ws.asicom/", name = "getDetalleCredito")
    public JAXBElement<GetDetalleCredito> createGetDetalleCredito(GetDetalleCredito value) {
        return new JAXBElement<GetDetalleCredito>(_GetDetalleCredito_QNAME, GetDetalleCredito.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDetalleCreditoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.laaraucana.ws.asicom/", name = "getDetalleCreditoResponse")
    public JAXBElement<GetDetalleCreditoResponse> createGetDetalleCreditoResponse(GetDetalleCreditoResponse value) {
        return new JAXBElement<GetDetalleCreditoResponse>(_GetDetalleCreditoResponse_QNAME, GetDetalleCreditoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDatosCreditoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.laaraucana.ws.asicom/", name = "getDatosCreditoResponse")
    public JAXBElement<GetDatosCreditoResponse> createGetDatosCreditoResponse(GetDatosCreditoResponse value) {
        return new JAXBElement<GetDatosCreditoResponse>(_GetDatosCreditoResponse_QNAME, GetDatosCreditoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDatosCredito }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.laaraucana.ws.asicom/", name = "getDatosCredito")
    public JAXBElement<GetDatosCredito> createGetDatosCredito(GetDatosCredito value) {
        return new JAXBElement<GetDatosCredito>(_GetDatosCredito_QNAME, GetDatosCredito.class, null, value);
    }

}
