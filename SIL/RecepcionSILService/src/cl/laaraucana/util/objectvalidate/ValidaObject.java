package cl.laaraucana.util.objectvalidate;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.text.WordUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import cl.laaraucana.recepcionsil.service.vo.HijoVO;

public class ValidaObject {

	private static String CONF_TAG_CONFIG = "configuracion";
	private static String CONF_TAG_METODO = "metodo";
	private static String CONF_TAG_PARAMETRO = "parametro";
	private static String CONF_ATTR_ID = "id";
	private static String CONF_ATTR_NAME = "nombre";
	private static String CONF_ATTR_MESSAGE = "mensaje";
	private static String CONF_ATTR_VALUE_NAME_VALOR = "valor";

	private static String DEF_TAG_ATRIBUTO = "atributo";
	private static String DEF_TAG_VALIDACION = "valida";
	private static String DEF_TAG_FORMATO = "formato";
	private static String DEF_ATTR_NAME = "name";
	private static String DEF_ATTR_ID = "id";
	//private static String DEF_ATTR_RELLENO = "relleno";
	//private static String DEF_ATTR_LARGO = "largo";

	//private static String DEF_ATTR_VALUE_LARGO = "largo";
	private static String DEF_ATTR_VALUE_RELLENO = "relleno";

	private static Document DOCUMENT_XML_CONFIG = getDocument(ValidaObject.class);

	public static ValidateResultVO validar(Object objeto, Class<?> clase) {
		ValidateResultVO result = new ValidateResultVO();
		result.setValid(true);
		try {
			Document doc = getDocument(clase);
			NodeList campos = doc.getElementsByTagName(DEF_TAG_ATRIBUTO);
			for (int temp = 0; temp < campos.getLength(); temp++) {
				Element elementoCampo = (Element) campos.item(temp);
				NodeList elementList = elementoCampo.getElementsByTagName(DEF_TAG_VALIDACION);
				for (int i = 0; i < elementList.getLength(); i++) {
					Element elementoValida = (Element) elementList.item(i);
					String detalleValidacion = validaDinamico(getMetodoById(DOCUMENT_XML_CONFIG, elementoValida.getAttribute(DEF_ATTR_ID)), elementoCampo, elementoValida, clase, objeto);
					if (detalleValidacion != null) {
						result.setValid(false);
						result.getDetailValidate().add(detalleValidacion);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void formatear(Object objeto, Class<?> clase) {
		ValidateResultVO result = new ValidateResultVO();
		result.setValid(true);
		try {
			Document doc = getDocument(clase);
			NodeList campos = doc.getElementsByTagName(DEF_TAG_ATRIBUTO);
			for (int temp = 0; temp < campos.getLength(); temp++) {
				Element elementoCampo = (Element) campos.item(temp);
				NodeList elementList = elementoCampo.getElementsByTagName(DEF_TAG_FORMATO);
				for (int i = 0; i < elementList.getLength(); i++) {
					Element elementoValida = (Element) elementList.item(i);
					objeto = formateoDinamico(getMetodoById(DOCUMENT_XML_CONFIG, elementoValida.getAttribute(DEF_ATTR_ID)), elementoCampo, elementoValida, clase, objeto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String objectToString(Object objeto, Class<?> clase) {
		StringBuffer buffer = new StringBuffer();
		try {
			Document doc = getDocument(clase);
			NodeList campos = doc.getElementsByTagName(DEF_TAG_ATRIBUTO);
			for (int temp = 0; temp < campos.getLength(); temp++) {
				Element elementoCampo = (Element) campos.item(temp);
				NodeList elementList = elementoCampo.getElementsByTagName(DEF_TAG_FORMATO);
				for (int i = 0; i < elementList.getLength(); i++) {
					Element elementoValida = (Element) elementList.item(i);
					objeto = formateoDinamico(getMetodoById(DOCUMENT_XML_CONFIG, elementoValida.getAttribute(DEF_ATTR_ID)), elementoCampo, elementoValida, clase, objeto);

					if (elementoValida.getAttribute(DEF_ATTR_ID).equals(DEF_ATTR_VALUE_RELLENO)) {
						Method userMethods = clase.getDeclaredMethod("get".concat(WordUtils.capitalize(elementoCampo.getAttribute(DEF_ATTR_NAME))), null);
						buffer.append((String) userMethods.invoke(objeto, new Object[0]));
						//System.out.println("#" + (String) userMethods.invoke(objeto, new Object[0]) + "#");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	private static String validaDinamico(Element elementConf, Element elementAttr, Element elementVal, Class<?> clase, Object obj) {
		String detalleValidacion = null;
		try {
			String nombreMetodo = elementConf.getAttribute(CONF_ATTR_NAME);
			String mensajeError = elementConf.getAttribute(CONF_ATTR_MESSAGE);
			NodeList nListParametros = elementConf.getElementsByTagName(CONF_TAG_PARAMETRO);
			ArrayList<String> atributoParam = new ArrayList<String>();
			Method metodoGet = clase.getMethod("get".concat(WordUtils.capitalize(elementAttr.getAttribute(DEF_ATTR_NAME))), null);
			for (int temp = 0; temp < nListParametros.getLength(); temp++) {
				Element elemento = (Element) nListParametros.item(temp);
				if (elemento.getAttribute(CONF_ATTR_NAME).equals(CONF_ATTR_VALUE_NAME_VALOR)) {
					atributoParam.add((String) metodoGet.invoke(obj, null));
				} else {
					atributoParam.add(elementVal.getAttribute(elemento.getAttribute(CONF_ATTR_NAME)));
				}
			}
			Method metodo = Funciones.class.getMethod(nombreMetodo, getListaMetodos(nListParametros));
			String[] array = new String[atributoParam.size()];
			atributoParam.toArray(array);

			boolean isValid = (Boolean) metodo.invoke(new Funciones(), array);
			if (!isValid) {
				mensajeError = !mensajeError.isEmpty() ? mensajeError : "no es válido";
				detalleValidacion = "Campo " + elementAttr.getAttribute(DEF_ATTR_NAME) + " " + mensajeError + ":" + array[0];
			}
		} catch (Exception e) {
			e.printStackTrace();
			detalleValidacion = "Exception: Error al validar... " + elementAttr.getAttribute(DEF_ATTR_NAME) + " " + e.getMessage();
		}
		return detalleValidacion;

	}

	private static Object formateoDinamico(Element elementConf, Element elementAttr, Element elementVal, Class<?> clase, Object obj) {

		String nombreMetodo = elementConf.getAttribute(CONF_ATTR_NAME);
		try {
			NodeList nListParametros = elementConf.getElementsByTagName(CONF_TAG_PARAMETRO);
			ArrayList<String> atributoParam = new ArrayList<String>();
			Method metodoGet = clase.getMethod("get".concat(WordUtils.capitalize(elementAttr.getAttribute(DEF_ATTR_NAME))), null);
			Method metodoSet = clase.getMethod("set".concat(WordUtils.capitalize(elementAttr.getAttribute(DEF_ATTR_NAME))), String.class);
			for (int temp = 0; temp < nListParametros.getLength(); temp++) {
				Element elemento = (Element) nListParametros.item(temp);
				if (elemento.getAttribute(CONF_ATTR_NAME).equals(CONF_ATTR_VALUE_NAME_VALOR)) {
					atributoParam.add((String) metodoGet.invoke(obj, null));
				} else {
					atributoParam.add(elementVal.getAttribute(elemento.getAttribute(CONF_ATTR_NAME)));
				}
			}
			Method metodo = Funciones.class.getMethod(nombreMetodo, getListaMetodos(nListParametros));
			String[] array = new String[atributoParam.size()];
			atributoParam.toArray(array);
			String valor = (String) metodo.invoke(new Funciones(), array);
			metodoSet.invoke(obj, valor);
		} catch (Exception e) {
			System.out.println("Error en el metodo " + nombreMetodo);
			e.printStackTrace();
		}
		return obj;

	}

	private static Class<?>[] getListaMetodos(NodeList nListParametros) throws ClassNotFoundException, DOMException {
		ArrayList<Class> listaClass = new ArrayList<Class>();
		for (int temp = 0; temp < nListParametros.getLength(); temp++) {
			Element elemento = (Element) nListParametros.item(temp);
			elemento.getTextContent();
			listaClass.add(String.class);
		}
		Class[] array = new Class[listaClass.size()];
		listaClass.toArray(array);
		return array;
	}

	private static Element getMetodoById(Document doc, String id) {
		NodeList campos = doc.getElementsByTagName(CONF_TAG_CONFIG);
		for (int temp = 0; temp < campos.getLength(); temp++) {
			Element elementoCampo = (Element) campos.item(temp);
			NodeList elementList = elementoCampo.getElementsByTagName(CONF_TAG_METODO);
			for (int i = 0; i < elementList.getLength(); i++) {
				Element elementoMetodo = (Element) elementList.item(i);
				if (elementoMetodo.getAttribute(CONF_ATTR_ID).equals(id)) {
					return elementoMetodo;
				}
			}
		}
		return null;
	}

	/*
		public static String objectToString(Object objeto, Class<?> clase) {
			try {
				StringBuffer buffer = new StringBuffer();
				Document doc = getDocument(clase);
				doc.getDocumentElement().normalize();
				// lista de campos
				NodeList campos = doc.getElementsByTagName(DEF_TAG_ATRIBUTO);
				for (int temp = 0; temp < campos.getLength(); temp++) {
					Element elementoCampo = (Element) campos.item(temp);
					// lista de validaciones
					NodeList validaciones = elementoCampo.getElementsByTagName(DEF_TAG_VALIDACION);
					for (int i = 0; i < validaciones.getLength(); i++) {
						Element elemento = (Element) validaciones.item(i);
						// busca elemento con artibuto de tipo largo
						if (elemento.getAttribute(DEF_ATTR_ID).equals(DEF_ATTR_VALUE_RELLENO)) {
							String nombreMetodo = "get".concat(WordUtils.capitalize(elementoCampo.getAttribute(DEF_ATTR_NAME)));
							Method userMethods = clase.getDeclaredMethod(nombreMetodo, null);
							buffer.append(Funciones.rellenar((String) userMethods.invoke(objeto, new Object[0]), (elemento.getTextContent()), elemento.getAttribute(DEF_ATTR_RELLENO)));
						}
					}
				}

				return buffer.toString();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	*/
	private static Document getDocument(Class<?> clase) {
		Document doc = null;
		try {
			InputStream in = clase.getResourceAsStream(clase.getSimpleName() + ".xml");

			DocumentBuilderFactory factory = null;
			DocumentBuilder builder = null;

			try {
				factory = DocumentBuilderFactory.newInstance();
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}

			try {
				doc = builder.parse(new InputSource(in));
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// /-------------------------------------

			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	/*	public static Properties loadPropertie() {
			Properties prop = new Properties();
			InputStream input = null;
			try {
				input = ValidaObject.class.getResourceAsStream("validaObject.properties");
				prop.load(input);
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return prop;

		}
	public static String obtieneNombreMetodo(Properties prop, String Key) {
		return prop.getProperty(Key);
	}
	 */

	/*public static String ejecutaMetodoValidacion(Element elementoCampo, Element elementoValidate, Class<?> clase, Object objeto) {
		try {
			String campo = elementoCampo.getAttribute(DEF_ATTR_NAME);
			String tipo = elementoValidate.getAttribute(DEF_ATTR_ID);
			String nombreMetodo = "get".concat(WordUtils.capitalize(elementoCampo.getAttribute(DEF_ATTR_NAME)));
			Method userMethods = clase.getDeclaredMethod(nombreMetodo, null);
			String valorObjeto = (String) userMethods.invoke(objeto, new Object[0]);

			try {
				if (tipo.equals(DEF_ATTR_VALUE_LARGO)) {
					Funciones.maxLength(valorObjeto, elementoValidate.getTextContent());
				} else if (tipo.equals(DEF_ATTR_VALUE_NUMERICO)) {
					Funciones.isInteger(valorObjeto);
				} else if (tipo.equals(DEF_ATTR_VALUE_RUT)) {

				}

			} catch (ValidateException e) {
				// System.out.println(e.getMetodo());
				return "El atributo " + campo + " " + e.getMensaje();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	*/
	/*	public static String ejecutaMetodoFormateo(Element elementoCampo, Element elementoValidate, Class<?> clase, Object objeto) {
			try {
				String campo = elementoCampo.getAttribute(DEF_ATTR_NAME);
				String tipo = elementoValidate.getAttribute(DEF_ATTR_ID);
				String nombreMetodoGet = "get".concat(WordUtils.capitalize(elementoCampo.getAttribute(DEF_ATTR_NAME)));
				String nombreMetodoSet = "set".concat(WordUtils.capitalize(elementoCampo.getAttribute(DEF_ATTR_NAME)));

				String valorObjeto = (String) clase.getDeclaredMethod(nombreMetodoGet, null).invoke(objeto, new Object[0]);

				try {
					if (tipo.equals(DEF_ATTR_VALUE_LARGO)) {
						String valorFormateado = Funciones.rellenar(valorObjeto, elementoValidate.getTextContent(), elementoValidate.getAttribute(DEF_ATTR_RELLENO));
						clase.getDeclaredMethod(nombreMetodoSet, String.class).invoke(objeto, valorFormateado);
					} else if (tipo.equals(DEF_ATTR_VALUE_NUMERICO)) {
						Funciones.isInteger(valorObjeto);
					} else if (tipo.equals(DEF_ATTR_VALUE_RUT)) {

					}

				} catch (ValidateException e) {
					// System.out.println(e.getMetodo());
					return "El atributo " + campo + " " + e.getMensaje();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}*/

	public static void main(String[] args) {
		HijoVO h = new HijoVO();

		h.setRutHijo("1234-3");
		h.setFecNacMenorUnAnio("19990215");
		h.setFolioDefuncion("999999");
		h.setHijoNacidoMuerto("no");
		h.setNombreHijo("Alberto Canifru");

		// System.out.println(objectToString(h, h.getClass()));

		ValidateResultVO resultado = validar(h, h.getClass());
		System.out.println(resultado.isValid());
		for (String string : resultado.getDetailValidate()) {
			System.out.println(string);
		}
		System.out.println(h.getFecNacMenorUnAnio());
		formatear(h, h.getClass());
		System.out.println(h.getFecNacMenorUnAnio());

		System.out.println(objectToString(h, h.getClass()));

	}

}
