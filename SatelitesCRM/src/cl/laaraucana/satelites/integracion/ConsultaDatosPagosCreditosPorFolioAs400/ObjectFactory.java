//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.satelites.integracion.ConsultaDatosPagosCreditosPorFolioAs400;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.laaraucana.satelites.integracion.ConsultaDatosPagosCreditosPorFolioAs400 package. 
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

    private final static QName _ConsultaDatosPagosCreditosPorFolioAs400_QNAME = new QName("http://delegate.toAS.legados.integracion.laaraucana.cl/", "consultaDatosPagosCreditosPorFolioAs400");
    private final static QName _ConsultaDatosPagosCreditosPorFolioAs400Response_QNAME = new QName("http://delegate.toAS.legados.integracion.laaraucana.cl/", "consultaDatosPagosCreditosPorFolioAs400Response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.laaraucana.satelites.integracion.ConsultaDatosPagosCreditosPorFolioAs400
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaDatosPagosCreditosPorFolioAs400_Type }
     * 
     */
    public ConsultaDatosPagosCreditosPorFolioAs400_Type createConsultaDatosPagosCreditosPorFolioAs400_Type() {
        return new ConsultaDatosPagosCreditosPorFolioAs400_Type();
    }

    /**
     * Create an instance of {@link ConsultaDatosPagosCreditosPorFolioAs400Response }
     * 
     */
    public ConsultaDatosPagosCreditosPorFolioAs400Response createConsultaDatosPagosCreditosPorFolioAs400Response() {
        return new ConsultaDatosPagosCreditosPorFolioAs400Response();
    }

    /**
     * Create an instance of {@link Log }
     * 
     */
    public Log createLog() {
        return new Log();
    }

    /**
     * Create an instance of {@link DetalleConsultaDatosPagosCreditosPorFolioAs400SalidaVO }
     * 
     */
    public DetalleConsultaDatosPagosCreditosPorFolioAs400SalidaVO createDetalleConsultaDatosPagosCreditosPorFolioAs400SalidaVO() {
        return new DetalleConsultaDatosPagosCreditosPorFolioAs400SalidaVO();
    }

    /**
     * Create an instance of {@link ConsultaDatosPagosCreditosPorFolioAs400EntradaVO }
     * 
     */
    public ConsultaDatosPagosCreditosPorFolioAs400EntradaVO createConsultaDatosPagosCreditosPorFolioAs400EntradaVO() {
        return new ConsultaDatosPagosCreditosPorFolioAs400EntradaVO();
    }

    /**
     * Create an instance of {@link ConsultaDatosPagosCreditosPorFolioAs400ListaSalidaVO }
     * 
     */
    public ConsultaDatosPagosCreditosPorFolioAs400ListaSalidaVO createConsultaDatosPagosCreditosPorFolioAs400ListaSalidaVO() {
        return new ConsultaDatosPagosCreditosPorFolioAs400ListaSalidaVO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDatosPagosCreditosPorFolioAs400_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delegate.toAS.legados.integracion.laaraucana.cl/", name = "consultaDatosPagosCreditosPorFolioAs400")
    public JAXBElement<ConsultaDatosPagosCreditosPorFolioAs400_Type> createConsultaDatosPagosCreditosPorFolioAs400(ConsultaDatosPagosCreditosPorFolioAs400_Type value) {
        return new JAXBElement<ConsultaDatosPagosCreditosPorFolioAs400_Type>(_ConsultaDatosPagosCreditosPorFolioAs400_QNAME, ConsultaDatosPagosCreditosPorFolioAs400_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDatosPagosCreditosPorFolioAs400Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delegate.toAS.legados.integracion.laaraucana.cl/", name = "consultaDatosPagosCreditosPorFolioAs400Response")
    public JAXBElement<ConsultaDatosPagosCreditosPorFolioAs400Response> createConsultaDatosPagosCreditosPorFolioAs400Response(ConsultaDatosPagosCreditosPorFolioAs400Response value) {
        return new JAXBElement<ConsultaDatosPagosCreditosPorFolioAs400Response>(_ConsultaDatosPagosCreditosPorFolioAs400Response_QNAME, ConsultaDatosPagosCreditosPorFolioAs400Response.class, null, value);
    }

}
