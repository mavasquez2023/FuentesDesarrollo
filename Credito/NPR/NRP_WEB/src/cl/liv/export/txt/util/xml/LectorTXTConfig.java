package cl.liv.export.txt.util.xml;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cl.liv.archivos.comun.ArchivosUtiles;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.export.comun.util.Funciones;
import cl.liv.export.comun.util.SessionUtil;
import cl.liv.export.txt.util.PropertiesTXTUtil;

public class LectorTXTConfig {

	public static Document getXMLConfiguracion(String reporte) {
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			String fileConf = PropertiesTXTUtil.getProperty("export.path.resources.txt") + "/txts/" + reporte
					+ "/conf.xml";
			Document doc = docBuilder.parse(fileConf);

			doc.getDocumentElement().normalize();
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getXMLURLConfiguracion(String reporte) {
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			String fileConf = PropertiesTXTUtil.getProperty("export.path.resources.txt") + "/txts/" + reporte
					+ "/conf.xml";
			return fileConf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getKey(String reporte) {
		String prefijo = "exportlivtxt";
		return Funciones.getMD5(prefijo + reporte);
	}

	public static boolean createXMLreporte(String reporte, String contenido) {
		String fileConf = PropertiesTXTUtil.getProperty("export.path.resources.txt") + "/txts/" + reporte;
		File directorio = new File(fileConf);
		directorio.mkdir();
		if (directorio.exists()) {
			PrintWriter pw = ManejoArchivoTXT.getOpenedFileToWrite(fileConf + "/conf.xml");
			ManejoArchivoTXT.putLineFromFileOpened(pw, contenido);
			ManejoArchivoTXT.closeFileToWrite(pw);
			return true;
		}
		return false;
	}

	public static boolean deleteReporte(String reporte) {
		String fileConf = PropertiesTXTUtil.getProperty("export.path.resources.txt") + "/txts/" + reporte;

		return ArchivosUtiles.eliminarDirectorioRecursivo(fileConf);
	}

	public static void getDataXML(String reporte) {
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			String fileConf = PropertiesTXTUtil.getProperty("export.path.resources.txt") + "/txts/" + reporte
					+ "/conf.xml";
			//fileConf = fileConf.replaceAll("//", "/")
			//fileConf = "file:///"+ fileConf
			Document doc = docBuilder.parse(fileConf);

			doc.getDocumentElement().normalize();

			HashMap<String, Object> rep = new HashMap<String, Object>();

			NodeList resources = doc.getElementsByTagName("doc");
			for (int i = 0; i < resources.getLength(); i++) {
				Element resource = (Element) resources.item(i);
				if (resource.getNodeType() == Node.ELEMENT_NODE) {
					rep.put("nombre", resource.getAttribute("nombre"));
					rep.put("key", resource.getAttribute("key"));
					rep.put("output", resource.getAttribute("output"));
					rep.put("output_name", resource.getAttribute("output_name"));

				}

			}

			ArrayList<HashMap<String, Object>> queryList = new ArrayList<HashMap<String, Object>>();
			NodeList querys = doc.getElementsByTagName("query");
			for (int i = 0; i < querys.getLength(); i++) {
				Element query = (Element) querys.item(i);
				if (query.getNodeType() == Node.ELEMENT_NODE) {
					// rep.put("nombre", query.getAttribute("nombre"));
					HashMap<String, Object> queryItem = new HashMap<String, Object>();
					queryItem.put("id", query.getAttribute("id"));
					queryItem.put("datasource", query.getAttribute("datasource"));
					queryItem.put("sql", query.getTextContent().trim());
					queryItem.put("mapeo", query.getAttribute("mapeo"));
					queryItem.put("iterar", query.getAttribute("iterar"));
					queryItem.put("iterar_sobre", query.getAttribute("iterar_sobre"));
					queryItem.put("guardar_en_archivo", query.getAttribute("guardar_en_archivo"));
					queryItem.put("guardar_solo_en_iteracion", query.getAttribute("guardar_solo_en_iteracion"));
					queryItem.put("ejecutar", query.getAttribute("ejecutar"));
					queryItem.put("data", query.getAttribute("data"));
					queryList.add(queryItem);
				}

			}
			HashMap<String, Object> mapeosHash = new HashMap<String, Object>();

			NodeList mapeos = doc.getElementsByTagName("mapeo");
			for (int i = 0; i < mapeos.getLength(); i++) {
				Element mapeo = (Element) mapeos.item(i);
				if (mapeo.getNodeType() == Node.ELEMENT_NODE) {
					HashMap<String, Object> mapeoItem = new HashMap<String, Object>();
					ArrayList<HashMap<String, Object>> columnasMapeo = new ArrayList<HashMap<String, Object>>();

					NodeList columnas = mapeo.getElementsByTagName("columna");
					for (int i1 = 0; i1 < columnas.getLength(); i1++) {
						Element columna = (Element) columnas.item(i1);
						if (columna.getNodeType() == Node.ELEMENT_NODE) {
							HashMap<String, Object> inputItem = new HashMap<String, Object>();
							inputItem.put("header", columna.getAttribute("header"));
							inputItem.put("nombre", columna.getAttribute("nombre"));
							inputItem.put("largo", columna.getAttribute("largo"));
							inputItem.put("relleno", columna.getAttribute("relleno"));
							inputItem.put("alineamiento", columna.getAttribute("alineamiento"));
							inputItem.put("clase", columna.getAttribute("clase"));
							inputItem.put("metodo", columna.getAttribute("metodo"));
							inputItem.put("ejecutar_metodo", columna.getAttribute("ejecutar_metodo"));
							inputItem.put("data_ej", columna.getAttribute("data_ej"));
							inputItem.put("valor_default", columna.getAttribute("valor_default"));
							inputItem.put("no_incluir", columna.getAttribute("no_incluir"));
							inputItem.put("data", columna.getAttribute("data"));

							columnasMapeo.add(inputItem);
						}

					}

					try {

						String configuracionesNoIncluir = PropertiesTXTUtil
								.getProperty("export.config.data.tmp.mapeos");
						if (configuracionesNoIncluir.contains(mapeo.getAttribute("id") + ";")) {
										String configuraciones = PropertiesTXTUtil
									.getProperty("export.config.data.tmp.mapeos." + mapeo.getAttribute("id"));
							String[] columnasNoIncluir = configuraciones.split("\\|");
							if (columnasNoIncluir != null && columnasNoIncluir.length > 0) {
								for (int aux = 0; aux < columnasNoIncluir.length; aux++) {
									String[] partesColumna = columnasNoIncluir[aux].split(";");
									HashMap col = new HashMap();
									col.put("header", "");
									col.put("nombre", "");
									col.put("largo", "1");
									col.put("relleno", "");
									col.put("alineamiento", "");
									col.put("clase", "");
									col.put("metodo", "");
									col.put("ejecutar_metodo", "");
									col.put("data_ej", "");
									col.put("valor_default", "");
									col.put("no_incluir", "");
									col.put("data", "");
									
									boolean tieneAtributos = false;
									if (partesColumna != null && partesColumna.length > 0) {
										tieneAtributos = true;
										for (int aux1 = 0; aux1 < partesColumna.length; aux1++) {
											String[] partes = partesColumna[aux1].split(":");
											String clave = "";
											String valor = "";
											if(partes.length>0) {
												clave = partes[0];
												if(partes.length==2) {
													valor = partes[1];
												}
												col.put(clave, valor);
											}
										}
									}
									
									if(tieneAtributos) {
										columnasMapeo = excluirColumnaSiExiste(columnasMapeo, col);
										columnasMapeo.add(col);
									}
								}
							}
						}
					} catch (Exception e) {

					}

					mapeoItem.put("columnas", columnasMapeo);
					mapeosHash.put(mapeo.getAttribute("id"), mapeoItem);

				}

			}

			rep.put("mapeos", mapeosHash);
			rep.put("querys", queryList);
			SessionUtil.txts.put(reporte, rep);
		} catch (ParserConfigurationException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (SAXException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (IOException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}

	}

	public static ArrayList<HashMap<String, Object>> excluirColumnaSiExiste(ArrayList<HashMap<String, Object>> columnas, HashMap<String, Object> columna) {
		ArrayList<HashMap<String, Object>> columnasAUX = new ArrayList<HashMap<String,Object>>();
		System.out.println("Buscando columna ["+columna.get("nombre")+"]");
		boolean existe = false;
		if(columnas != null) {
			for(int i=0; i< columnas.size(); i++) {
				HashMap<String, Object> columnaTMP = columnas.get(i);
				if(columna != null && columnaTMP != null) {
					if(columna.get("nombre").toString().equalsIgnoreCase(columnaTMP.get("nombre").toString())) {		
						System.out.println("columna -> "+ columna.get("nombre") + ", ya existe no se agrega");
						existe = true;
					}
				}
			}
		}
		if(existe) {
			for(int i=0; i< columnas.size(); i++) {
				HashMap<String, Object> columnaTMP = columnas.get(i);
				if(columna != null && columnaTMP != null) {
					if(columna.get("nombre").toString().equalsIgnoreCase(columnaTMP.get("nombre").toString())) {		
						System.out.println(" se quita del arreglo ");
					}
					else {
						columnasAUX.add(columnaTMP);
					}
				}
			}
		}else {
			columnasAUX = columnas;
		}
		
		return columnasAUX;
	}

	public static String getTagValue(String tag, Element elemento) {

		NodeList lista = elemento.getElementsByTagName(tag).item(0).getChildNodes();

		Node valor = (Node) lista.item(0);

		return valor.getNodeValue();

	}

}