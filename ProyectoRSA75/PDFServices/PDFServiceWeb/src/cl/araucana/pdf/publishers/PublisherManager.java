

/*
 * @(#) PublisherManager.java    1.0 02-06-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cl.araucana.core.util.Mapping;
import cl.araucana.core.util.Property;
import cl.araucana.core.util.UnRepeatedArrayList;
import cl.araucana.core.util.XClass;

import cl.araucana.core.util.xml.XMLParserException;
import cl.araucana.core.util.xml.XMLPropertiesParser;


/**
 * This class is responsible for management of the defined publishers in the XML
 * document {@link #XML_DOC_PUBLISHERS}. This document is found as Java
 * resource. The configuration to a publisher is represented by
 * {@link Publisher} class. It's mandatory to configure one and only one
 * publisher as default.
 * 
 * <p> This class implements <i>Singleton</i> design pattern. Call
 * {@link #getInstance()} method to start working with it.</p>
 * 
 * <BR>
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 02-06-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class PublisherManager {

	/**
	 *  XML document with defined publishers. 
	 */
	public static final String XML_DOC_PUBLISHERS = "/etc/pdf/publishers.xml";

	private static PublisherManager instance;

	private Map publishers = new TreeMap();
	private Publisher defaultPublisher;

	/**
	 * Gets singleton instance of this class.
	 * 
	 * @return singleton instance.
	 * 
	 * @throws PDFPublisher If XML document
	 *         {@link #XML_DOC_PUBLISHERS} cannot be found or present errors. 
	 */
	public static synchronized PublisherManager getInstance()
			throws PDFPublisherException {

		if (instance == null) {
			instance = new PublisherManager();
		}

		return instance;
	}

	/**
	 * Gets the defined publisher names.
	 * 
	 * @return array of publisher names.
	 */
	public String[] getPublisherNames() {
		return (String[]) publishers.keySet().toArray(new String[0]);
	}

	/**
	 * Gets the defined publisher instance named <code>name</name>.
	 * 
	 * @param name Publisher name.
	 * 
	 * @return required publisher instance, <code>null</code> if none
	 *         publisher is known with the specified <code>name</code>.
	 */
	public Publisher getPublisher(String name) {
		return (Publisher) publishers.get(name);
	}

	/**
	 * Gets default defined publisher instance.
	 * 
	 * @return default publisher.
	 */
	public Publisher getDefaultPublisher() {
		return defaultPublisher;
	}

	private PublisherManager() throws PDFPublisherException {

		InputStream resourceInput =
				getClass().getResourceAsStream(XML_DOC_PUBLISHERS);

		if (resourceInput == null) {
			throw new PDFPublisherException(
					"Cannot find resource '" + XML_DOC_PUBLISHERS + "'.");
		}

		Reader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(resourceInput));
			XMLPropertiesParser parser = new XMLPropertiesParser(reader, false);

			parser.setXMLDocumentName(XML_DOC_PUBLISHERS);
			parser.parse();
			checkPublishers(parser);
		} catch (Exception e) {
			throw new PDFPublisherException(
					"Parsing resource '" + XML_DOC_PUBLISHERS + "' failed.", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch(Exception e) {}
			}
		}
	}

	private void checkPublishers(XMLPropertiesParser parser)
			throws XMLParserException {

		int publishersCount = parser.getPropertyCount("publishers.publisher");

		for (int i = 0; i < publishersCount; i++) {
			String $publisher = "publishers.publisher[" + i + "]";
			String name = parser.getStringProperty($publisher + ".name");

			/*
			 * Checks duplicated publisher names.
			 */
			Publisher publisher = (Publisher) publishers.get(name);

			if (publisher != null) {
				throw new XMLParserException(
						"duplicated publisher '" + name + "'");
			}

			String description =
					parser.getStringProperty($publisher + ".description", "");

			String className = parser.getStringProperty($publisher + ".class");

			/*
			 *  Checks specified class.
			 */
			Class pdfPublisherClass = null;

			try {
				XClass pdfPublisherXClass = new XClass(PDFPublisher.class);

				pdfPublisherClass =
						pdfPublisherXClass.getSubClass(className, true);
			} catch (ClassNotFoundException e) {
				throw new XMLParserException(e.getMessage());
			}

        	publisher = new Publisher();

			publisher.setName(name);
			publisher.setDescription(description);
			publisher.setType(pdfPublisherClass);

			boolean defaultOne =
					parser.getBooleanProperty(
							$publisher + ".default", new Boolean(false));

			if (defaultOne) {
				if (defaultPublisher == null) {
					defaultPublisher = publisher;
				} else {
					throw new XMLParserException(
							"Too many default publishers");
				}
			}

			publisher.setDefault(defaultOne);

			int publisherPropertyCount =
					parser.getPropertyCount(
							$publisher + ".properties.property");

			for (int j = 0; j < publisherPropertyCount; j++) {
				String $property =
						$publisher + ".properties.property[" + j + "]";

				String propertyName =
						parser.getStringProperty($property + ".name");

				String propertyDescription =
						parser.getStringProperty(
								$property + ".description", "");

				String propertyValue =
						parser.getStringProperty($property + ".value");

				Property property =
						new Property(
								propertyName,
								propertyValue,
								propertyDescription);

				publisher.addProperty(property);
			}

			String indexes = parser.getStringProperty($publisher + ".indexes");

			String mappedIndexes =
					parser.getStringProperty($publisher + ".mappedIndexes", "");

			publisher.setIndexes(getIndexes(indexes));

			if (!mappedIndexes.equals("")) {
				publisher.setMappedIndexes(getMappedIndexes(mappedIndexes));
			}

			/*
			 * Registers new PDF publisher.
			 */
         	publishers.put(name, publisher);
		}

		if (defaultPublisher == null) {
			throw new XMLParserException(
					"A default publisher must be defined");
		}
	}

	private List getIndexes(String xmlDocName)throws XMLParserException {

		InputStream resourceInput =	getClass().getResourceAsStream(xmlDocName);

		if (resourceInput == null) {
			throw new XMLParserException(
					"Cannot find resource '" + xmlDocName + "'");
		}

		Reader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(resourceInput));
			XMLPropertiesParser parser = new XMLPropertiesParser(reader, false);

			parser.setXMLDocumentName(xmlDocName);
			parser.parse();

			return getIndexes(parser);
		} catch (XMLParserException e) {
			throw e;
		} catch (Exception e) {
			throw new XMLParserException(
					"Parsing resource '" + xmlDocName + "' failed");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch(Exception e) {}
			}
		}
	}

	private List getIndexes(XMLPropertiesParser parser)
			throws XMLParserException {

		List indexes = new UnRepeatedArrayList();

		int indexesCount = parser.getPropertyCount("indexes.index");

		for (int i = 0; i < indexesCount; i++) {
			String $index = "indexes.index[" + i + "]";
			String name = parser.getStringProperty($index + ".name");

			String description =
					parser.getStringProperty($index + ".description", "");

        	Index index = new Index();

			index.setName(name);
			index.setDescription(description);

			/*
			 * Gets index's properties.
			 */
			int indexPropertyCount =
					parser.getPropertyCount(
							$index + ".properties.property");

			for (int j = 0; j < indexPropertyCount; j++) {
				String $property =
						$index + ".properties.property[" + j + "]";

				String propertyName =
						parser.getStringProperty($property + ".name");

				String propertyDescription =
						parser.getStringProperty(
								$property + ".description", "");

				String propertyValue =
						parser.getStringProperty($property + ".value");

				Property property =
						new Property(
								propertyName,
								propertyValue,
								propertyDescription);

				index.addProperty(property);
			}

			/*
			 * Gets index's fields.
			 */
			int indexFieldCount =
					parser.getPropertyCount(
							$index + ".fields.field");

			for (int j = 0; j < indexFieldCount; j++) {
				String $field =
						$index + ".fields.field[" + j + "]";

				String fieldName =
						parser.getStringProperty($field + ".name");

				String fieldDescription =
						parser.getStringProperty($field + ".description", "");

				String sFieldType =
						parser.getStringProperty($field + ".type");

				int fieldType = Field.getType(sFieldType);

				if (fieldType == -1) {
					throw new XMLParserException(
							"Unknown field type '" + sFieldType + "'");
				}

				int docID =
						parser.getIntProperty($field + ".docID", 0);

				Field field =
						new Field(
								fieldName,
								fieldType,
								docID,
								fieldDescription);

				index.addField(field);
			}

			/*
			 *  Checks docID attributes consistency.
			 */
			try {
				index.checkDocIDFields();
			} catch (IllegalArgumentException e) {
				throw new XMLParserException(e.getMessage());
			}

			/*
			 * Registers new index.
			 */
         	indexes.add(index);
		}

		return indexes;
	}

	private List getMappedIndexes(String xmlDocName)
			throws XMLParserException {

		InputStream resourceInput =	getClass().getResourceAsStream(xmlDocName);

		if (resourceInput == null) {
			throw new XMLParserException(
					"Cannot find resource '" + xmlDocName + "'");
		}

		Reader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(resourceInput));
			XMLPropertiesParser parser = new XMLPropertiesParser(reader, false);

			parser.setXMLDocumentName(xmlDocName);
			parser.parse();

			return getMappedIndexes(parser);
		} catch (XMLParserException e) {
			throw e;
		} catch (Exception e) {
			throw new XMLParserException(
					"Parsing resource '" + xmlDocName + "' failed");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch(Exception e) {}
			}
		}
	}

	private List getMappedIndexes(XMLPropertiesParser parser)
			throws XMLParserException {

		List mappedIndexes = new UnRepeatedArrayList();

		int mappedIndexesCount =
				parser.getPropertyCount("mappedIndexes.mappedIndex");

		for (int i = 0; i < mappedIndexesCount; i++) {
			String $mappedIndex = "mappedIndexes.mappedIndex[" + i + "]";
			String name = parser.getStringProperty($mappedIndex + ".name");

			String description =
					parser.getStringProperty($mappedIndex + ".description", "");

        	MappedIndex mappedIndex = new MappedIndex();

			mappedIndex.setName(name);
			mappedIndex.setDescription(description);

			/*
			 * Gets mapped index's mappings.
			 */
			int indexMappingsCount =
					parser.getPropertyCount(
							$mappedIndex + ".mappings.mapping");

			for (int j = 0; j < indexMappingsCount; j++) {
				String $mapping =
						$mappedIndex + ".mappings.mapping[" + j + "]";

				String mappedName =
						parser.getStringProperty($mapping + ".name");

				String fieldName =
						parser.getStringProperty($mapping + ".fieldName");

				Mapping mapping = new Mapping(mappedName, fieldName);

				mappedIndex.addMapping(mapping);
			}

			/*
			 * Registers new index.
			 */
         	mappedIndexes.add(mappedIndex);
		}

		return mappedIndexes;
	}
}
