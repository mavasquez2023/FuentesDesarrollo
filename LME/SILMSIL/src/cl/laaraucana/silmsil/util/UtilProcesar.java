package cl.laaraucana.silmsil.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.laaraucana.silmsil.dao.LM_DAO;
import cl.laaraucana.silmsil.dao.SIL_DAO;
import cl.laaraucana.silmsil.vo.LM_VO;
import cl.laaraucana.silmsil.vo.SIL_VO;

public class UtilProcesar {

	private Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * Método que entrega el mes.
	 * @param checkbox
	 * @param sep
	 * @return
	 */
	//public String getMesProceso(String checkbox, int sep){
	public String getMesProceso(String checkbox){
		try{
			String mes = null;
			
			//int num = Integer.parseInt(checkbox.substring(sep));
			int auxLength=checkbox.length();
			int num = Integer.parseInt(checkbox.substring(auxLength-2,auxLength));
			
			//Entrega mes del proceso en String.
			switch(num){
				case 1: mes = "01";
					break;
				case 2: mes = "02";
					break;
				case 3: mes = "03";
					break;
				case 4: mes = "04";
					break;
				case 5: mes = "05";
					break;
				case 6: mes = "06";
					break;
				case 7: mes = "07";
					break;
				case 8: mes = "08";
					break;
				case 9: mes = "09";
					break;
				case 10: mes = "10";
					break;
				case 11: mes = "11";
					break;
				case 12: mes = "12";
					break;
			}
			System.out.println("Mes ->" + mes);
			return mes;
		}
		catch(Exception ex){
			log.error("Error en getMesProceso()" + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Método que entrega el mes con las tres primeras letras.
	 * @param checkbox
	 * @param sep
	 * @return
	 */
	public String getMesProcesoMes(String checkbox){
		try{
			String mes = null;
			
			//int num = Integer.parseInt(checkbox.substring(sep));
			int auxLength=checkbox.length();
			int num = Integer.parseInt(checkbox.substring(auxLength-2,auxLength));
			
			//Entrega mes del proceso en String.
			switch(num){
				case 1: mes = ".ENE";
					break;
				case 2: mes = ".FEB";
					break;
				case 3: mes = ".MAR";
					break;
				case 4: mes = ".ABR";
					break;
				case 5: mes = ".MAY";
					break;
				case 6: mes = ".JUN";
					break;
				case 7: mes = ".JUL";
					break;
				case 8: mes = ".AGO";
					break;
				case 9: mes = ".SEP";
					break;
				case 10: mes = ".OCT";
					break;
				case 11: mes = ".NOV";
					break;
				case 12: mes = ".DIC";
					break;
			}
			System.out.println("Mes ->" + mes);
			return mes;
		}
		catch(Exception ex){
			log.error("Error en getMesProceso()" + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	public static int actualizarRegistroSIL(SIL_VO sil, HashMap<String, String> map)
		throws Exception{
		int validacion = 0;
		
		String new_key = sil.getNrofol()+"_"+sil.getRuttrabaj()+"_"+sil.getPerpag()+"_"+sil.getLichasfec()+"_"+sil.getPagfol();
		String old_key = map.get("nro_fol")+"_"+map.get("rut_trabaj")+"_"+map.get("perpag")+"_"+map.get("lichasfec")+"_"+map.get("pag_fol");
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Old :" + old_key);
		System.out.println("New :" + new_key);
		System.out.println("------------------------------------------------------------");
		
		if(new_key.equalsIgnoreCase(old_key)){
			//Update dato.
			SIL_DAO.actualizar(sil);
			
			validacion = 1;
		}else{
			//Consulta si nueva PK existe con anterioridad.
			ArrayList<SIL_VO> lista = SIL_DAO.buscar(sil);
			
			if(lista == null || lista.size()==0){
				System.out.println("Entro al insertar y Eliminar!!!!!!!!!!!!!!!");
				//Inserta nuevo registro con PK nueva.
				SIL_DAO.insertar_SIL(sil);
				
				HashMap<String,String> mapDatos =new HashMap<String, String>();
				//pk original
				
				mapDatos.put("old_rut", map.get("rut_trabaj"));
				mapDatos.put("old_folio", map.get("nro_fol"));
				mapDatos.put("old_fecTerminoLic", map.get("lichasfec"));
				mapDatos.put("old_pagfol", map.get("pag_fol"));
				mapDatos.put("old_periodo", map.get("perpag"));
				
				//pk nueva
				mapDatos.put("new_rut", sil.getRuttrabaj());
				mapDatos.put("new_folio", sil.getNrofol());
				mapDatos.put("new_fecTerminoLic", String.valueOf(sil.getLichasfec()));
				mapDatos.put("new_pagfol", String.valueOf(sil.getPagfol()));
				
				SIL_DAO.actualizar_SilPK54(mapDatos);
				
				//Elimina registro con PK antigua.
				SIL_DAO.eliminarDato(map);
				
				validacion = 2;
			}else {
				validacion = 3;
			}
		}
		
		return validacion;
	}
	
	public static int actualizarRegistroLM(LM_VO lm, HashMap<String, String> map)
		throws Exception{
		int validacion = 0;
			
		String new_key = lm.getFolio()+"_"+lm.getRuttrabaj()+"_"+lm.getFecproceso()+
			"_"+lm.getFecterrepo();
		String old_key = map.get("nro_folio")+"_"+map.get("rut_trabaj")+"_"+map.get("fecproceso")
			+"_"+map.get("fecterrepo");
			
		System.out.println("------------------------------------------------------------");
		System.out.println("Old :" + old_key);
		System.out.println("New :" + new_key);
		System.out.println("------------------------------------------------------------");
			
		if(new_key.equalsIgnoreCase(old_key)){
			//Update dato.
			LM_DAO.actualizar(lm);
				
			validacion = 1;
		}else{
			//Consulta si nueva PK existe con anterioridad.
			ArrayList<LM_VO> lista = LM_DAO.buscar(lm);
				
			if(lista.size()==0){
				System.out.println("Entro al insertar y Eliminar!!!!!!!!!!!!!!!");
				//Inserta nuevo registro con PK nueva.
				LM_DAO.insertar_LM(lm);

				//actualizo BD PK54
				
				HashMap<String,String> mapDatos =new HashMap<String, String>();
				//pk original				
				mapDatos.put("old_rut", map.get("rut_trabaj"));
				mapDatos.put("old_folio", map.get("nro_folio"));
				mapDatos.put("old_fecTerminoLic", map.get("fecterrepo"));
				mapDatos.put("old_periodo", map.get("fecproceso"));
				
				//pk nueva
				mapDatos.put("new_rut", lm.getRuttrabaj());
				mapDatos.put("new_folio", lm.getFolio());
				mapDatos.put("new_fecTerminoLic", String.valueOf(lm.getFecterrepo()));
				
				LM_DAO.actualizar_LmPK54(mapDatos);
				
				//Elimina registro con PK antigua. de BD 50
				LM_DAO.eliminarDato(map);
					
				validacion = 2;
			}else {
				validacion = 3;
			}
		}	
		return validacion;
	}
	
	/**
	 * Método para generar fecha actual.
	 * @return
	 */
	public static String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		return date;
	}
	
	public static String getHours(){
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String hours = sdf.format(new Date());
		return hours;
	}
	
}
