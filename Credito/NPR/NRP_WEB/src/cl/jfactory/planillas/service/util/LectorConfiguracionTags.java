package cl.jfactory.planillas.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import cl.jfactory.planillas.service.helper.ConfiguradorHelper;
import cl.liv.archivos.comun.xml.lector.dto.NodoDTO;
import cl.liv.archivos.comun.xml.lector.impl.ProcesarXML;

public class LectorConfiguracionTags {
	
	private static HashMap seccionesCFG = null;
	private static String PATH_CFG = ResourceBundle.getBundle("etc/workflow_configuraciones").getString("config.path.configuracion.tags.configurador");
	
	
	public static void init(){
		if(seccionesCFG == null){
			load();
		}
	}
	
	public static String getConfiguracionPorSeccion(String seccion){
		//if(true) {return "";}
		init();
		if(seccionesCFG != null && (String)seccionesCFG.get(seccion)!= null){
			return (String)seccionesCFG.get(seccion);
		}
		return "";
	}
	
	private static void load(){
		try {
			seccionesCFG = new HashMap();
			NodoDTO nodoPrincipal = ProcesarXML.init(PATH_CFG);
			if(nodoPrincipal != null){
				ArrayList secciones = ((NodoDTO)nodoPrincipal.getNodosHijos().get(0)).getNodosHijos();
				if(secciones != null && secciones.size() > 0){
					for(int i=0; i< secciones.size(); i++){
						String seccionCFG = "";
						String seccionId = "";
						NodoDTO seccion = (NodoDTO)secciones.get(i);
						seccionId = seccion.getValorAtributo("id");
						if(seccion != null && seccion.getNodosHijos().size()>0){
							NodoDTO tags = (NodoDTO)seccion.getNodosHijos().get(0);
							if(tags != null && tags.getNodosHijos() != null){
								ArrayList tagsCFG = tags.getNodosHijos();
								if(tagsCFG != null & tagsCFG.size() >0){
									for(int j=0; j< tagsCFG.size(); j++){
										NodoDTO tagCFG = (NodoDTO) tagsCFG.get(j);
										if(tagCFG != null){
											String cfg = (String)seccionesCFG.get(seccionId);
											if(cfg == null){
												cfg = "";
											}
											cfg = cfg + tagCFG.getValorAtributo("id")+":";
											NodoDTO campos = (NodoDTO)tagCFG.getNodosHijos().get(0);
											if(campos != null && campos.getNodosHijos().size()>0){
												ArrayList camposCFG = campos.getNodosHijos();
												for(int k =0; k<camposCFG.size(); k++){
													NodoDTO campo = (NodoDTO)camposCFG.get(k);
													cfg = cfg + campo.getValorAtributo("id")+",";
												}
											}
											cfg = cfg + ";";
											cfg = cfg.replace(",;", ";");
											seccionesCFG.put(seccionId, cfg);
										}
									}
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			seccionesCFG= null;
			e.printStackTrace();
		}
	}

}
