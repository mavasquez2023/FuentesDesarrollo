/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

package cl.laaraucana.satelites.integracion.ConsultaCreditoCRC456Old;

/**
 * ExtensionMapper class
 */
@SuppressWarnings({ "unchecked", "unused" })
public class ExtensionMapper {

	public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
			java.lang.String typeName, javax.xml.stream.XMLStreamReader reader)
			throws java.lang.Exception {

		if ("http://delegate.toAS.legados456.integracion.laaraucana.cl/"
				.equals(namespaceURI)
				&& "consultaCreditoCrc456Response".equals(typeName)) {

			return cl.laaraucana.satelites.integracion.ConsultaCreditoCRC456Old.ConsultaCreditoCrc456Response.Factory
					.parse(reader);

		}

		if ("http://delegate.toAS.legados456.integracion.laaraucana.cl/"
				.equals(namespaceURI)
				&& "consultaCreditoCRC456EntradaVO".equals(typeName)) {

			return cl.laaraucana.satelites.integracion.ConsultaCreditoCRC456Old.ConsultaCreditoCRC456EntradaVO.Factory
					.parse(reader);

		}

		if ("http://delegate.toAS.legados456.integracion.laaraucana.cl/"
				.equals(namespaceURI)
				&& "consultaCreditoCrc456".equals(typeName)) {

			return cl.laaraucana.satelites.integracion.ConsultaCreditoCRC456Old.ConsultaCreditoCrc456.Factory
					.parse(reader);

		}

		if ("http://delegate.toAS.legados456.integracion.laaraucana.cl/"
				.equals(namespaceURI)
				&& "consultaCreditoCRC456SalidaVO".equals(typeName)) {

			return cl.laaraucana.satelites.integracion.ConsultaCreditoCRC456Old.ConsultaCreditoCRC456SalidaVO.Factory
					.parse(reader);

		}

		if ("http://delegate.toAS.legados456.integracion.laaraucana.cl/"
				.equals(namespaceURI) && "log".equals(typeName)) {

			return cl.laaraucana.satelites.integracion.ConsultaCreditoCRC456Old.Log.Factory
					.parse(reader);

		}

		throw new org.apache.axis2.databinding.ADBException("Unsupported type "
				+ namespaceURI + " " + typeName);
	}

}
