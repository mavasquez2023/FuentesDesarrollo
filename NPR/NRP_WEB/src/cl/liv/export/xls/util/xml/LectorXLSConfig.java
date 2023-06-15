package cl.liv.export.xls.util.xml;

import java.io.File;
import java.io.IOException;
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

import cl.lib.export.xls.impl.GenerarXLS;
import cl.liv.export.comun.util.Mensajes;
import cl.liv.export.comun.util.PropertiesComunUtil;
import cl.liv.export.comun.util.SessionUtil;
import cl.liv.export.comun.util.file.Util;
import cl.liv.export.xls.util.PropertiesXLSUtil;

public class LectorXLSConfig {
	
	
	public static void getDataXML(String reporte){
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			String fileConf = PropertiesComunUtil.getProperty("export.path.resources")+"exportXLS/xlss/"+reporte+"/conf.xml";
			Mensajes.info("ruta conf: "+ fileConf);
			
			if(new File(fileConf).exists()){
				Mensajes.info("existe configuracion ["+fileConf+"]");
			}
			else{
				Mensajes.info("no existe configuracion ["+fileConf+"]");
			}
			
			Document doc = docBuilder.parse (fileConf);
			Mensajes.info("build ok ");
			String key = Util.getMD5("liv/xlss/"+reporte);
			
			doc.getDocumentElement ().normalize ();
			
			HashMap<String, Object> rep = new HashMap<String, Object>();

			NodeList resources = doc.getElementsByTagName("xls");
			for (int i = 0; i < resources.getLength(); i++) {
				Element resource = (Element) resources.item(i);
				if (resource.getNodeType() == Node.ELEMENT_NODE){
					rep.put("nombre", resource.getAttribute("nombre"));

					rep.put("key", resource.getAttribute("key"));
				}
				
			}
			Mensajes.info("header ok :" + key);
			if(! rep.get("key").equals(key)){
				Mensajes.info("La Key del reporte ["+reporte+"], archivo [conf.xml] no es válida, por favor comuníquese con "+ PropertiesComunUtil.getProperty("comunicarse.con") );
				return ;
			}
			
			ArrayList<HashMap<String, Object>> queryList = new ArrayList<HashMap<String,Object>>();
			NodeList querys = doc.getElementsByTagName("query");
			for (int i = 0; i < querys.getLength(); i++) {
				Element query = (Element) querys.item(i);
				if (query.getNodeType() == Node.ELEMENT_NODE){
					//rep.put("nombre", query.getAttribute("nombre"));
					HashMap<String, Object> queryItem = new HashMap<String, Object>();
					queryItem.put("id", query.getAttribute("id"));
					queryItem.put("datasource", query.getAttribute("datasource"));
					queryItem.put("id_mapeo", query.getAttribute("id_mapeo"));
					queryItem.put("for_query", query.getAttribute("for_query"));
					queryItem.put("only_for", query.getAttribute("only_for"));
					queryItem.put("mostrar_titulo", query.getAttribute("mostrar_titulo"));
					if(query.getAttribute("tipo") == null || ( query.getAttribute("tipo")!= null && query.getAttribute("tipo").toString().length()==0) ){
						queryItem.put("tipo", "query");
					}
					else{
						queryItem.put("tipo", query.getAttribute("tipo"));
					}
					//queryItem.put("sql", query.getTextContent().trim());
					queryItem = procesarQuery(queryItem, query ); 
					queryList.add(queryItem);
				}
				
			}
			Mensajes.info("querys ok ");
			

			HashMap<String, Object> mapeos = new HashMap<String, Object>();
			NodeList mapeo = doc.getElementsByTagName("mapeo");
			for (int i = 0; i < mapeo.getLength(); i++) {
				Element m = (Element) mapeo.item(i);
				if (m.getNodeType() == Node.ELEMENT_NODE){
					//inputItem.put("id", columna.getAttribute("id"));
					ArrayList<HashMap<String, String>> columnasList = new ArrayList<HashMap<String,String>>();
					NodeList columnas = m.getElementsByTagName("columna");
					for (int j = 0; j < columnas.getLength(); j++) {
						Element columna = (Element) columnas.item(j);
						if (columna.getNodeType() == Node.ELEMENT_NODE){
							HashMap<String, String> columnaItem = new HashMap<String, String>();
							columnaItem.put("header", columna.getAttribute("header"));
							columnaItem.put("nombre", columna.getAttribute("nombre"));
							columnasList.add(columnaItem);
						}
						
					}
					mapeos.put(m.getAttribute("id"), columnasList);
				}
			}
			Mensajes.info("columnas ok ");
			rep.put("mapeos", mapeos);
			rep.put("querys", queryList);
			SessionUtil.xlss.put(reporte, rep);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
		
	public static HashMap<String, Object> procesarQuery(HashMap<String, Object> queryItem, Node query){
		//queryItem = procesarQuery(HashMap<String, Object> queryItem,String query.getTextContent().trim() );
		HashMap<String , Object> dinamicData = new HashMap<String, Object>();
		
		String[] dinamics = query.getTextContent().trim().split("<isNotNull");
		
	
		
		for (int i=1; i< dinamics.length ; i++) {
			
			String filtro = dinamics[i].split(">")[1].split("<")[0];
			queryItem.put("tiene_dinamic", true);
			String key = dinamics[i].split("key=\"")[1].split("\"")[0];

			filtro = filtro.replace("[mayor]", ">");
			filtro = filtro.replace("[menor]", "<");
			dinamicData.put(key, filtro);
			
		}

		queryItem.put("dinamic", dinamicData);
		dinamics[0] = dinamics[0].replace("[mayor]", ">");
		dinamics[0] = dinamics[0].replace("[menor]", "<");
		queryItem.put("sql", dinamics[0]);
		
		return queryItem;
	}
	
	public static String getTagValue(String tag, Element elemento) {

		NodeList lista = elemento.getElementsByTagName(tag).item(0).getChildNodes();

		Node valor = (Node) lista.item(0);

		return valor.getNodeValue();

		}

	
}
