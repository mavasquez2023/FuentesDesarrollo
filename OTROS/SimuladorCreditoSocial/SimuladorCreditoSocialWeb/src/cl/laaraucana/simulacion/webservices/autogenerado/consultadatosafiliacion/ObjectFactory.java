//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//

package cl.laaraucana.simulacion.webservices.autogenerado.consultadatosafiliacion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * cl.laaraucana.integracion.legados.toas.delegate package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _ConsultaDatosAfiliacionAs400Response_QNAME = new QName(
			"http://delegate.toAS.legados.integracion.laaraucana.cl/", "consultaDatosAfiliacionAs400Response");
	private final static QName _ConsultaDatosAfiliacionAs400_QNAME = new QName(
			"http://delegate.toAS.legados.integracion.laaraucana.cl/", "consultaDatosAfiliacionAs400");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema
	 * derived classes for package: cl.laaraucana.integracion.legados.toas.delegate
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link ConsultaDatosAfiliacionAs400_Type }
	 * 
	 */
	public ConsultaDatosAfiliacionAs400_Type createConsultaDatosAfiliacionAs400_Type() {
		return new ConsultaDatosAfiliacionAs400_Type();
	}

	/**
	 * Create an instance of {@link ConsultaDatosAfiliacionAs400Response }
	 * 
	 */
	public ConsultaDatosAfiliacionAs400Response createConsultaDatosAfiliacionAs400Response() {
		return new ConsultaDatosAfiliacionAs400Response();
	}

	/**
	 * Create an instance of {@link Log }
	 * 
	 */
	public Log createLog() {
		return new Log();
	}

	/**
	 * Create an instance of {@link ConsultaDatosAfiliacionAs400SalidaVO }
	 * 
	 */
	public ConsultaDatosAfiliacionAs400SalidaVO createConsultaDatosAfiliacionAs400SalidaVO() {
		return new ConsultaDatosAfiliacionAs400SalidaVO();
	}

	/**
	 * Create an instance of {@link ConsultaDatosAfiliacionAs400EntradaVO }
	 * 
	 */
	public ConsultaDatosAfiliacionAs400EntradaVO createConsultaDatosAfiliacionAs400EntradaVO() {
		return new ConsultaDatosAfiliacionAs400EntradaVO();
	}

	/**
	 * Create an instance of {@link DetalleConsultaDatosAfiliacionAs400SalidaVO }
	 * 
	 */
	public DetalleConsultaDatosAfiliacionAs400SalidaVO createDetalleConsultaDatosAfiliacionAs400SalidaVO() {
		return new DetalleConsultaDatosAfiliacionAs400SalidaVO();
	}

	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link ConsultaDatosAfiliacionAs400Response }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://delegate.toAS.legados.integracion.laaraucana.cl/", name = "consultaDatosAfiliacionAs400Response")
	public JAXBElement<ConsultaDatosAfiliacionAs400Response> createConsultaDatosAfiliacionAs400Response(
			ConsultaDatosAfiliacionAs400Response value) {
		return new JAXBElement<ConsultaDatosAfiliacionAs400Response>(_ConsultaDatosAfiliacionAs400Response_QNAME,
				ConsultaDatosAfiliacionAs400Response.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link ConsultaDatosAfiliacionAs400_Type }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://delegate.toAS.legados.integracion.laaraucana.cl/", name = "consultaDatosAfiliacionAs400")
	public JAXBElement<ConsultaDatosAfiliacionAs400_Type> createConsultaDatosAfiliacionAs400(
			ConsultaDatosAfiliacionAs400_Type value) {
		return new JAXBElement<ConsultaDatosAfiliacionAs400_Type>(_ConsultaDatosAfiliacionAs400_QNAME,
				ConsultaDatosAfiliacionAs400_Type.class, null, value);
	}

}
