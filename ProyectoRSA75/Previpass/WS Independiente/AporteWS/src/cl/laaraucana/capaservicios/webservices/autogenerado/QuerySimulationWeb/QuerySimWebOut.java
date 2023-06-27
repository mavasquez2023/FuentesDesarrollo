/**
 * QuerySimWebOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

package cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb;

/**
 * QuerySimWebOut bean class
 */
@SuppressWarnings({ "unchecked", "unused" })
public class QuerySimWebOut implements org.apache.axis2.databinding.ADBBean {
	/* This type was generated from the piece of schema that had
	        name = QuerySimWebOut
	        Namespace URI = http://lautaro.com/xi/BS/WEB-Mobile
	        Namespace Prefix = ns1
	        */

	/**
	 * field for MONTO_CUOTA
	 */

	protected java.lang.String localMONTO_CUOTA;

	/*  This tracker boolean wil be used to detect whether the user called the set method
	*   for this attribute. It will be used to determine whether to include this field
	*   in the serialized XML
	*/
	protected boolean localMONTO_CUOTATracker = false;

	public boolean isMONTO_CUOTASpecified() {
		return localMONTO_CUOTATracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getMONTO_CUOTA() {
		return localMONTO_CUOTA;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            MONTO_CUOTA
	 */
	public void setMONTO_CUOTA(java.lang.String param) {
		localMONTO_CUOTATracker = param != null;

		this.localMONTO_CUOTA = param;

	}

	/**
	 * field for CAE
	 */

	protected java.lang.String localCAE;

	/*  This tracker boolean wil be used to detect whether the user called the set method
	*   for this attribute. It will be used to determine whether to include this field
	*   in the serialized XML
	*/
	protected boolean localCAETracker = false;

	public boolean isCAESpecified() {
		return localCAETracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getCAE() {
		return localCAE;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            CAE
	 */
	public void setCAE(java.lang.String param) {
		localCAETracker = param != null;

		this.localCAE = param;

	}

	/**
	 * field for COSTO_TOTAL
	 */

	protected java.lang.String localCOSTO_TOTAL;

	/*  This tracker boolean wil be used to detect whether the user called the set method
	*   for this attribute. It will be used to determine whether to include this field
	*   in the serialized XML
	*/
	protected boolean localCOSTO_TOTALTracker = false;

	public boolean isCOSTO_TOTALSpecified() {
		return localCOSTO_TOTALTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getCOSTO_TOTAL() {
		return localCOSTO_TOTAL;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            COSTO_TOTAL
	 */
	public void setCOSTO_TOTAL(java.lang.String param) {
		localCOSTO_TOTALTracker = param != null;

		this.localCOSTO_TOTAL = param;

	}

	/**
	 * field for PAYMENT_OPTIONS This was an Array!
	 */

	protected cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1[] localPAYMENT_OPTIONS;

	/**
	 * Auto generated getter method
	 * 
	 * @return com.lautaro.xi.bs.web_mobile.PAYMENT_OPTIONS_type1[]
	 */
	public cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1[] getPAYMENT_OPTIONS() {
		return localPAYMENT_OPTIONS;
	}

	/**
	 * validate the array for PAYMENT_OPTIONS
	 */
	protected void validatePAYMENT_OPTIONS(cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1[] param) {

		if ((param != null) && (param.length < 1)) {
			throw new java.lang.RuntimeException();
		}

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            PAYMENT_OPTIONS
	 */
	public void setPAYMENT_OPTIONS(cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1[] param) {

		validatePAYMENT_OPTIONS(param);

		this.localPAYMENT_OPTIONS = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.lautaro.xi.bs.web_mobile.PAYMENT_OPTIONS_type1
	 */
	public void addPAYMENT_OPTIONS(cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1 param) {
		if (localPAYMENT_OPTIONS == null) {
			localPAYMENT_OPTIONS = new cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1[] {};
		}

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localPAYMENT_OPTIONS);
		list.add(param);
		this.localPAYMENT_OPTIONS = (cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1[]) list.toArray(new cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1[list.size()]);

	}

	/**
	 * 
	 * @param parentQName
	 * @param factory
	 * @return org.apache.axiom.om.OMElement
	 */
	public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

		org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, parentQName);
		return factory.createOMElement(dataSource, parentQName);

	}

	public void serialize(final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
		serialize(parentQName, xmlWriter, false);
	}

	public void serialize(final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter, boolean serializeType) throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

		java.lang.String prefix = null;
		java.lang.String namespace = null;

		prefix = parentQName.getPrefix();
		namespace = parentQName.getNamespaceURI();
		writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

		if (serializeType) {

			java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://lautaro.com/xi/BS/WEB-Mobile");
			if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", namespacePrefix + ":QuerySimWebOut", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "QuerySimWebOut", xmlWriter);
			}

		}
		if (localMONTO_CUOTATracker) {
			namespace = "";
			writeStartElement(null, namespace, "MONTO_CUOTA", xmlWriter);

			if (localMONTO_CUOTA == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("MONTO_CUOTA cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localMONTO_CUOTA);

			}

			xmlWriter.writeEndElement();
		}
		if (localCAETracker) {
			namespace = "";
			writeStartElement(null, namespace, "CAE", xmlWriter);

			if (localCAE == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("CAE cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localCAE);

			}

			xmlWriter.writeEndElement();
		}
		if (localCOSTO_TOTALTracker) {
			namespace = "";
			writeStartElement(null, namespace, "COSTO_TOTAL", xmlWriter);

			if (localCOSTO_TOTAL == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("COSTO_TOTAL cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localCOSTO_TOTAL);

			}

			xmlWriter.writeEndElement();
		}
		if (localPAYMENT_OPTIONS != null) {
			for (int i = 0; i < localPAYMENT_OPTIONS.length; i++) {
				if (localPAYMENT_OPTIONS[i] != null) {
					localPAYMENT_OPTIONS[i].serialize(new javax.xml.namespace.QName("", "PAYMENT_OPTIONS"), xmlWriter);
				} else {

					throw new org.apache.axis2.databinding.ADBException("PAYMENT_OPTIONS cannot be null!!");

				}

			}
		} else {

			throw new org.apache.axis2.databinding.ADBException("PAYMENT_OPTIONS cannot be null!!");

		}

		xmlWriter.writeEndElement();

	}

	private static java.lang.String generatePrefix(java.lang.String namespace) {
		if (namespace.equals("http://lautaro.com/xi/BS/WEB-Mobile")) {
			return "ns1";
		}
		return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
	}

	/**
	 * Utility method to write an element start tag.
	 */
	private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
		java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
		if (writerPrefix != null) {
			xmlWriter.writeStartElement(namespace, localPart);
		} else {
			if (namespace.length() == 0) {
				prefix = "";
			} else if (prefix == null) {
				prefix = generatePrefix(namespace);
			}

			xmlWriter.writeStartElement(prefix, localPart, namespace);
			xmlWriter.writeNamespace(prefix, namespace);
			xmlWriter.setPrefix(prefix, namespace);
		}
	}

	/**
	 * Util method to write an attribute with the ns prefix
	 */
	private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
		if (xmlWriter.getPrefix(namespace) == null) {
			xmlWriter.writeNamespace(prefix, namespace);
			xmlWriter.setPrefix(prefix, namespace);
		}
		xmlWriter.writeAttribute(namespace, attName, attValue);
	}

	/**
	 * Util method to write an attribute without the ns prefix
	 */
	private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
		if (namespace.equals("")) {
			xmlWriter.writeAttribute(attName, attValue);
		} else {
			registerPrefix(xmlWriter, namespace);
			xmlWriter.writeAttribute(namespace, attName, attValue);
		}
	}

	/**
	 * Util method to write an attribute without the ns prefix
	 */
	private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName, javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

		java.lang.String attributeNamespace = qname.getNamespaceURI();
		java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
		if (attributePrefix == null) {
			attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
		}
		java.lang.String attributeValue;
		if (attributePrefix.trim().length() > 0) {
			attributeValue = attributePrefix + ":" + qname.getLocalPart();
		} else {
			attributeValue = qname.getLocalPart();
		}

		if (namespace.equals("")) {
			xmlWriter.writeAttribute(attName, attributeValue);
		} else {
			registerPrefix(xmlWriter, namespace);
			xmlWriter.writeAttribute(namespace, attName, attributeValue);
		}
	}

	/**
	 * method to handle Qnames
	 */

	private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
		java.lang.String namespaceURI = qname.getNamespaceURI();
		if (namespaceURI != null) {
			java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
			if (prefix == null) {
				prefix = generatePrefix(namespaceURI);
				xmlWriter.writeNamespace(prefix, namespaceURI);
				xmlWriter.setPrefix(prefix, namespaceURI);
			}

			if (prefix.trim().length() > 0) {
				xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			} else {
				// i.e this is the default namespace
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}

		} else {
			xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
		}
	}

	private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

		if (qnames != null) {
			// we have to store this data until last moment since it is not
			// possible to write any
			// namespace data after writing the charactor data
			java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
			java.lang.String namespaceURI = null;
			java.lang.String prefix = null;

			for (int i = 0; i < qnames.length; i++) {
				if (i > 0) {
					stringToWrite.append(" ");
				}
				namespaceURI = qnames[i].getNamespaceURI();
				if (namespaceURI != null) {
					prefix = xmlWriter.getPrefix(namespaceURI);
					if ((prefix == null) || (prefix.length() == 0)) {
						prefix = generatePrefix(namespaceURI);
						xmlWriter.writeNamespace(prefix, namespaceURI);
						xmlWriter.setPrefix(prefix, namespaceURI);
					}

					if (prefix.trim().length() > 0) {
						stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				} else {
					stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
				}
			}
			xmlWriter.writeCharacters(stringToWrite.toString());
		}

	}

	/**
	 * Register a namespace prefix
	 */
	private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
		java.lang.String prefix = xmlWriter.getPrefix(namespace);
		if (prefix == null) {
			prefix = generatePrefix(namespace);
			javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
			while (true) {
				java.lang.String uri = nsContext.getNamespaceURI(prefix);
				if (uri == null || uri.length() == 0) {
					break;
				}
				prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
			}
			xmlWriter.writeNamespace(prefix, namespace);
			xmlWriter.setPrefix(prefix, namespace);
		}
		return prefix;
	}

	/**
	 * databinding method to get an XML representation of this object
	 * 
	 */
	public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName) throws org.apache.axis2.databinding.ADBException {

		java.util.ArrayList elementList = new java.util.ArrayList();
		java.util.ArrayList attribList = new java.util.ArrayList();

		if (localMONTO_CUOTATracker) {
			elementList.add(new javax.xml.namespace.QName("", "MONTO_CUOTA"));

			if (localMONTO_CUOTA != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMONTO_CUOTA));
			} else {
				throw new org.apache.axis2.databinding.ADBException("MONTO_CUOTA cannot be null!!");
			}
		}
		if (localCAETracker) {
			elementList.add(new javax.xml.namespace.QName("", "CAE"));

			if (localCAE != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCAE));
			} else {
				throw new org.apache.axis2.databinding.ADBException("CAE cannot be null!!");
			}
		}
		if (localCOSTO_TOTALTracker) {
			elementList.add(new javax.xml.namespace.QName("", "COSTO_TOTAL"));

			if (localCOSTO_TOTAL != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCOSTO_TOTAL));
			} else {
				throw new org.apache.axis2.databinding.ADBException("COSTO_TOTAL cannot be null!!");
			}
		}
		if (localPAYMENT_OPTIONS != null) {
			for (int i = 0; i < localPAYMENT_OPTIONS.length; i++) {

				if (localPAYMENT_OPTIONS[i] != null) {
					elementList.add(new javax.xml.namespace.QName("", "PAYMENT_OPTIONS"));
					elementList.add(localPAYMENT_OPTIONS[i]);
				} else {

					throw new org.apache.axis2.databinding.ADBException("PAYMENT_OPTIONS cannot be null !!");

				}

			}
		} else {

			throw new org.apache.axis2.databinding.ADBException("PAYMENT_OPTIONS cannot be null!!");

		}

		return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

	}

	/**
	 * Factory class that keeps the parse method
	 */
	public static class Factory {

		/**
		 * static method to create the object Precondition: If this object is an
		 * element, the current or next start element starts this object and any
		 * intervening reader events are ignorable If this object is not an
		 * element, it is a complex type and the reader is at the event just
		 * after the outer start element Postcondition: If this object is an
		 * element, the reader is positioned at its end element If this object
		 * is a complex type, the reader is positioned at the end element of its
		 * outer element
		 */
		public static QuerySimWebOut parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
			QuerySimWebOut object = new QuerySimWebOut();

			int event;
			java.lang.String nillableValue = null;
			java.lang.String prefix = "";
			java.lang.String namespaceuri = "";
			try {

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
					java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
					if (fullTypeName != null) {
						java.lang.String nsPrefix = null;
						if (fullTypeName.indexOf(":") > -1) {
							nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
						}
						nsPrefix = nsPrefix == null ? "" : nsPrefix;

						java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

						if (!"QuerySimWebOut".equals(type)) {
							// find namespace for the prefix
							java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (QuerySimWebOut) cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.ExtensionMapper.getTypeObject(nsUri, type, reader);
						}

					}

				}

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.
				java.util.Vector handledAttributes = new java.util.Vector();

				reader.next();

				java.util.ArrayList list4 = new java.util.ArrayList();

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new javax.xml.namespace.QName("", "MONTO_CUOTA").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException("The element: " + "MONTO_CUOTA" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setMONTO_CUOTA(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new javax.xml.namespace.QName("", "CAE").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException("The element: " + "CAE" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setCAE(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new javax.xml.namespace.QName("", "COSTO_TOTAL").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException("The element: " + "COSTO_TOTAL" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setCOSTO_TOTAL(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new javax.xml.namespace.QName("", "PAYMENT_OPTIONS").equals(reader.getName())) {

					// Process the array and step past its final element's end.
					list4.add(cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1.Factory.parse(reader));

					// loop until we find a start element that is not part of
					// this array
					boolean loopDone4 = false;
					while (!loopDone4) {
						// We should be at the end element, but make sure
						while (!reader.isEndElement())
							reader.next();
						// Step out of this element
						reader.next();
						// Step to next element event.
						while (!reader.isStartElement() && !reader.isEndElement())
							reader.next();
						if (reader.isEndElement()) {
							// two continuous end elements means we are exiting
							// the xml structure
							loopDone4 = true;
						} else {
							if (new javax.xml.namespace.QName("", "PAYMENT_OPTIONS").equals(reader.getName())) {
								list4.add(cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1.Factory.parse(reader));

							} else {
								loopDone4 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setPAYMENT_OPTIONS((cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1.class, list4));

				} // End of if for expected property start element

				else {
					// A start element we are not expecting indicates an invalid
					// parameter was passed
					throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement())
					// A start element we are not expecting indicates a trailing
					// invalid property
					throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());

			} catch (javax.xml.stream.XMLStreamException e) {
				throw new java.lang.Exception(e);
			}

			return object;
		}

	}// end of factory class

}
