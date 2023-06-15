package cl.araucana.estasfam.app.business.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.araucana.estasfam.app.business.enums.ActividadesEconomicasEnum;
import cl.araucana.estasfam.app.business.enums.EstadisticasEnum;
import cl.araucana.estasfam.app.business.model.CargasPagosDirectoDTO;
import cl.araucana.estasfam.app.business.services.EstadisticaASI4560Service;
import cl.araucana.estasfam.app.persistence.dao.EstadisticaASI4560Dao;
import cl.araucana.estasfam.common.util.FechaUtil;

@Service
public class EstadisticaASI4560ServiceImpl implements EstadisticaASI4560Service {

	private @Value("${araucana.estasfam.pathxlsestadisticas}")
	String cnfPathXlsEstadisticas;
	
	private @Value("${araucana.estasfam.rutasplantillas.ASI4560}")
	String cnfPathPlantillaASI4560;
	
	
	@Autowired
	private EstadisticaASI4560Dao estadisticaASI4560Dao;
	
	private String [] numRomanoRegion = {"I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII","XIII","XIV","XV","XVI"};
	
	@Override
	public void generarEstadistica() {
		HashMap<String, Integer> datos = null;
		//TODO cambiar fechaHoy antes de subir
		Date fechaHoy = new Date(); //FechaUtil.parsearFecha("yyyyMMdd", "20141130");
		Date fechaCargas = FechaUtil.restarMeses(fechaHoy, 2);
		Date mesPagosDir = FechaUtil.restarMeses(fechaHoy, 1);
		Date fechaPagosDirDesde = FechaUtil.parsearFecha("yyyyMMdd",  FechaUtil.formatearFecha("yyyyMM", mesPagosDir)+ "01"); 
		Date fechaPagosDirHasta = FechaUtil.parsearFecha("yyyyMMdd",  FechaUtil.formatearFecha("yyyyMM", fechaHoy)+ "01");
		
		try {
//			List<ActividadEconomicaDTO> listActEco = estadisticaASI4560Dao.getActividades();
			datos = inicializaDatosFinales();
			
			List<CargasPagosDirectoDTO> listEst4560 = estadisticaASI4560Dao.getCargasPagosDirectosPorRegion(fechaCargas, fechaHoy, fechaPagosDirDesde, fechaPagosDirHasta);
			for(CargasPagosDirectoDTO cargasPorActegion : listEst4560){
				Integer codActEco = cargasPorActegion.getCodActividad();
				Integer codRegion = cargasPorActegion.getCodRegion();
				
				if(codRegion != null && codRegion.intValue()>0){
					if(codActEco != null){
						ActividadesEconomicasEnum aeEnum = ActividadesEconomicasEnum.getActividadesEconomicasEnum(codActEco.toString());
						if(aeEnum != null){
							String key = numRomanoRegion[codRegion-1]+aeEnum.getContColumna(); 
							datos.put(key, datos.get(key) + cargasPorActegion.getCantidad());
						}else{
							String key = numRomanoRegion[codRegion-1]+"10"; 
							datos.put(key, datos.get(key) + cargasPorActegion.getCantidad());
						}
					}else {
						String key = numRomanoRegion[codRegion-1]+"10"; 
						datos.put(key, datos.get(key) + cargasPorActegion.getCantidad());
					}
					String keyTotal = numRomanoRegion[codRegion-1]+"11";
					datos.put(keyTotal, datos.get(keyTotal) + cargasPorActegion.getCantidad());
				}
			}
			
			for(int j = 1; j < 12; j++){
				Integer total = 0;
				for(int i = 0; i < numRomanoRegion.length; i++){
					String key = numRomanoRegion[i] + j;
					total = total + datos.get(key);
				}
				datos.put("TP" + j, total);
			}
			generaXls(datos);
					
				
				
				
			
			
//			Integer totalActNoEsp = 0;
//			for(ActividadEconomicaDTO actEco : listActEco){
//				Integer codActEco = actEco.getCodActividad();
//				
//				Integer totalActEco = 0;
//				for(int i = 0; i < numRomanoRegion.length; i++){
//					Integer codRegion = i + 1;
//					
//					for(CargasPagosDirectoDTO datoEst4560 : listEst4560){
//						Integer codRegionTemp = datoEst4560.getCodRegion();
//						Integer codActEcoTemp = datoEst4560.getCodActividad();
//						
//						if(codRegionTemp != null && codActEcoTemp != null 
//								&& codRegionTemp.equals(codRegion) && codActEcoTemp.equals(codActEco)){
//							datos.put(numRomanoRegion[i] + codActEco, datoEst4560.getCantidad());
//							totalActEco += datoEst4560.getCantidad();
//						}else if(codActEcoTemp == null){
//							Integer temp = datos.get(numRomanoRegion[i] + "-1"); 
//							totalActNoEsp += datoEst4560.getCantidad();
//							datos.put(numRomanoRegion[i] + "-1", temp + datoEst4560.getCantidad());
//						}
//					}
//				}
//				datos.put("TP" + codActEco, totalActEco);
//			}
//			datos.put("TP-1", totalActNoEsp);
//					
//			for(int i = 0; i < numRomanoRegion.length; i++){
//				Integer totalReg = 0;
//				for(ActividadEconomicaDTO actEco : listActEco){
//					totalReg += datos.get(numRomanoRegion[i] + actEco.getCodActividad());
//				}
//				datos.put(numRomanoRegion[i] + "-2", totalReg);
//			}
			
//			generaXls(generaMap(datos, listActEco));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generaXls(HashMap<String, Integer> map) {
		
		try {
			// Se crea el libro
			InputStream stream = getClass().getResourceAsStream(cnfPathPlantillaASI4560);
			HSSFWorkbook estASI5490 = new HSSFWorkbook(stream);
			HSSFSheet hoja = estASI5490.getSheetAt(0);
			HSSFCellStyle stylePercent = null;
			HSSFCell cell = null;
			
			//Insertamos periodo de pago
			int anho = FechaUtil.getAno(new Date());
			Date fechaAux = FechaUtil.restarMeses(new Date(), 1);
			String mesAux = FechaUtil.getDescripcionMes(FechaUtil.getMes(fechaAux));
			String mes = FechaUtil.getDescripcionMes(FechaUtil.getMes(new Date()));
			HSSFRichTextString cadena = new HSSFRichTextString("PERIODO "+mesAux+" DE "+anho);
			stylePercent = hoja.getRow(1).getCell(4).getCellStyle();		    		
		    cell = hoja.getRow(1).getCell(4);
		    cell.setCellValue(cadena);
		    cell.setCellStyle(stylePercent);
		    
		    //Insertamos fecha
			String fecha = FechaUtil.formatearFecha("dd/MM/yy", new Date());
			cadena = new HSSFRichTextString(fecha);
			stylePercent = hoja.getRow(1).getCell(13).getCellStyle();		    		
		    cell = hoja.getRow(1).getCell(13);
		    cell.setCellValue(cadena);
		    cell.setCellStyle(stylePercent);
		    
		    cadena = new HSSFRichTextString("0");
			int fil = 4;
			for(int i=0; i<17; i++){//Contador de filas
				int col = 3;
				for(int j=1; j<12; j++){//Contador de columnas
					if(fil==4){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("XV"+j) && map.get("XV"+j)>0){
					    	cell.setCellValue(map.get("XV"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==5){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("I"+j) && map.get("I"+j)>0){
					    	cell.setCellValue(map.get("I"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==6){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("II"+j) && map.get("II"+j)>0){
					    	cell.setCellValue(map.get("II"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==7){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("III"+j) && map.get("III"+j)>0){
					    	cell.setCellValue(map.get("III"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==8){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("IV"+j) && map.get("IV"+j)>0){
					    	cell.setCellValue(map.get("IV"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==9){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("V"+j) && map.get("V"+j)>0){
					    	cell.setCellValue(map.get("V"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==10){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("VI"+j) && map.get("VI"+j)>0){
					    	cell.setCellValue(map.get("VI"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==11){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("VII"+j) && map.get("VII"+j)>0){
					    	cell.setCellValue(map.get("VII"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==12){
							stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
						    cell = hoja.getRow(fil).getCell(col);
						    if(map.containsKey("XVI"+j) && map.get("XVI"+j)>0){
						    	cell.setCellValue(map.get("XVI"+j));
						    }else{
						    	cell.setCellValue(cadena);
						    }
						    cell.setCellStyle(stylePercent);
					}else
					if(fil==13){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("VIII"+j) && map.get("VIII"+j)>0){
					    	cell.setCellValue(map.get("VIII"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==14){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("IX"+j) && map.get("IX"+j)>0){
					    	cell.setCellValue(map.get("IX"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==15){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("XIV"+j) && map.get("XIV"+j)>0){
					    	cell.setCellValue(map.get("XIV"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==16){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("X"+j) && map.get("X"+j)>0){
					    	cell.setCellValue(map.get("X"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==17){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("XI"+j) && map.get("XI"+j)>0){
					    	cell.setCellValue(map.get("XI"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==18){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("XII"+j) && map.get("XII"+j)>0){
					    	cell.setCellValue(map.get("XII"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==19){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("XIII"+j) && map.get("XIII"+j)>0){
					    	cell.setCellValue(map.get("XIII"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==20){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("TP"+j) && map.get("TP"+j)>0){
					    	cell.setCellValue(map.get("TP"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}
					col++;
				}
				fil++;
			}
			
			String mesAnterior = FechaUtil.getDescripcionMes(FechaUtil.getMes(new Date())-1>0 ? FechaUtil.getMes(new Date())-1 : 12);
			
			File file = new File(cnfPathXlsEstadisticas + EstadisticasEnum.ASI4560.getSoloNombreXls() 
													+ mesAnterior + EstadisticasEnum.ASI4560.getSoloExtencion());
			if(file.exists()) {
				if(file.delete()){
					System.out.println("Borrado");
				}else{
					System.out.println("No Borrado");
				}
			}
			FileOutputStream elFichero = new FileOutputStream(new File(
					cnfPathXlsEstadisticas + EstadisticasEnum.ASI4560.getSoloNombreXls() + mes + EstadisticasEnum.ASI4560.getSoloExtencion()));
			
			estASI5490.write(elFichero);
			elFichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
//	private HashMap<String, Integer> inicializaDatos(List<ActividadEconomicaDTO> listActEco){
//		HashMap<String, Integer> datos = new HashMap<String, Integer>();
//
//		for(ActividadEconomicaDTO actEco : listActEco){
//			Integer i = actEco.getCodActividad();
//			datos.put(numRomanoRegion[0] + i, 0);
//			datos.put(numRomanoRegion[1] + i, 0);
//			datos.put(numRomanoRegion[2] + i, 0);
//			datos.put(numRomanoRegion[3] + i, 0);
//			datos.put(numRomanoRegion[4] + i, 0);
//			datos.put(numRomanoRegion[5] + i, 0);
//			datos.put(numRomanoRegion[6] + i, 0);
//			datos.put(numRomanoRegion[7] + i, 0);
//			datos.put(numRomanoRegion[8] + i, 0);
//			datos.put(numRomanoRegion[9] + i, 0);
//			datos.put(numRomanoRegion[10] + i, 0);
//			datos.put(numRomanoRegion[11] + i, 0);
//			datos.put(numRomanoRegion[12] + i, 0);
//			datos.put(numRomanoRegion[13] + i, 0);
//			datos.put(numRomanoRegion[14] + i, 0);
//			datos.put("TP" + i, 0);
//		}
//		
//		//Columna actividad no espesificada
//		datos.put(numRomanoRegion[0] + "-1", 0);
//		datos.put(numRomanoRegion[1] + "-1", 0);
//		datos.put(numRomanoRegion[2] + "-1", 0);
//		datos.put(numRomanoRegion[3] + "-1", 0);
//		datos.put(numRomanoRegion[4] + "-1", 0);
//		datos.put(numRomanoRegion[5] + "-1", 0);
//		datos.put(numRomanoRegion[6] + "-1", 0);
//		datos.put(numRomanoRegion[7] + "-1", 0);
//		datos.put(numRomanoRegion[8] + "-1", 0);
//		datos.put(numRomanoRegion[9] + "-1", 0);
//		datos.put(numRomanoRegion[10] + "-1", 0);
//		datos.put(numRomanoRegion[11] + "-1", 0);
//		datos.put(numRomanoRegion[12] + "-1", 0);
//		datos.put(numRomanoRegion[13] + "-1", 0);
//		datos.put(numRomanoRegion[14] + "-1", 0);
//		datos.put("TP-1", 0);
//		
//		//Columna Total
//		datos.put(numRomanoRegion[0] + "-2", 0);
//		datos.put(numRomanoRegion[1] + "-2", 0);
//		datos.put(numRomanoRegion[2] + "-2", 0);
//		datos.put(numRomanoRegion[3] + "-2", 0);
//		datos.put(numRomanoRegion[4] + "-2", 0);
//		datos.put(numRomanoRegion[5] + "-2", 0);
//		datos.put(numRomanoRegion[6] + "-2", 0);
//		datos.put(numRomanoRegion[7] + "-2", 0);
//		datos.put(numRomanoRegion[8] + "-2", 0);
//		datos.put(numRomanoRegion[9] + "-2", 0);
//		datos.put(numRomanoRegion[10] + "-2", 0);
//		datos.put(numRomanoRegion[11] + "-2", 0);
//		datos.put(numRomanoRegion[12] + "-2", 0);
//		datos.put(numRomanoRegion[13] + "-2", 0);
//		datos.put(numRomanoRegion[14] + "-2", 0);
//		datos.put("TP-2", 0);
//		
//		return datos;
//	}
//	
//	
//	public HashMap<String, Integer> generaMap(HashMap<String, Integer> map, List<ActividadEconomicaDTO> listActividades) {
//		HashMap<String, Integer> mapa = inicializaDatosFinales();
//		try{
//			Integer suma = 0;
//			for(int i=0; i<15; i++){
//				int contCol = 1;
//				suma = map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.AGRICULTURA_GANADERIA_CAZA_SILVIC.getCodActividadEconomica())
//						+ map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.PESCA.getCodActividadEconomica());
//				mapa.put(numRomanoRegion[i]+contCol, suma);
//				
//				contCol++;
//				suma = map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.EXPLOTACION_DE_MINAS_Y_CANTERAS.getCodActividadEconomica());
//				mapa.put(numRomanoRegion[i]+contCol, suma);
//				
//				contCol++;
//				suma = map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.INDUSTRIAS_MANUFACTURERAS.getCodActividadEconomica());
//				mapa.put(numRomanoRegion[i]+contCol, suma);
//				
//				contCol++;
//				suma = map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.SUMINISTRO_DE_ELECTRICIDAD_GAS_Y_AGUA.getCodActividadEconomica());
//				mapa.put(numRomanoRegion[i]+contCol, suma);
//				
//				contCol++;
//				suma = map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.CONSTRUCCION.getCodActividadEconomica());
//				mapa.put(numRomanoRegion[i]+contCol, suma);
//				
//				contCol++;
//				suma = map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.COMERCIO.getCodActividadEconomica());
//				mapa.put(numRomanoRegion[i]+contCol, suma);
//				
//				contCol++;
//				suma = map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.TRANSPORTE_ALMACENAMIENTO_Y_COMUNICACI.getCodActividadEconomica());
//				mapa.put(numRomanoRegion[i]+contCol, suma);
//				
//				contCol++;
//				suma = map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.INTERMEDIACION_FINANCIERA.getCodActividadEconomica());
//				mapa.put(numRomanoRegion[i]+contCol, suma);
//				
//				contCol++;
//				suma = map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.SERVICIOS_SOCIALES_Y_DE_SALUD.getCodActividadEconomica())
//						+ map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.OTRAS_ACT_SE_SERV_COMUNI_SOC_Y_PERS.getCodActividadEconomica());
//				mapa.put(numRomanoRegion[i]+contCol, suma);
//				
//				suma = map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.HOTELES_Y_RESTAURANTES.getCodActividadEconomica())
//						+ map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.ACT_INMOBIL_EMPRESARIALES_Y_ALQUILER.getCodActividadEconomica())
//						+ map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.ADM_PUB_Y_DEF_PLANES_SEGUR_SOC_AFIL.getCodActividadEconomica())
//						+ map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.ENSEÑANZA.getCodActividadEconomica())
//						+ map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.HOGARES_PRIVADOS_CON_SERVICIO_DOMESTICO.getCodActividadEconomica())
//						+ map.get(numRomanoRegion[i]+ActividadesEconomicasEnum.ORGANIZ_Y_ORGANOS_EXTRATERRITORALES.getCodActividadEconomica())
//						+ map.get(numRomanoRegion[i]+"-1");
//				mapa.put(numRomanoRegion[i]+contCol, suma);
//				
//				contCol++;
//				suma = map.get(numRomanoRegion[i]+"-2");
//				mapa.put(numRomanoRegion[i]+contCol, suma);
//			}
//			
//			for(int i = 0; i < numRomanoRegion.length; i++){
//				Integer totalReg = 0;
//				for(int j = 1; j < 11; j++){
//					totalReg += mapa.get(numRomanoRegion[i] + j);
//				}
//				mapa.put(numRomanoRegion[i] + "11", totalReg);
//			}
//			
//			for(int i = 1; i < 12; i++){
//				Integer totalAct = 0;
//				for(int j = 0; j < numRomanoRegion.length; j++){
//					totalAct += mapa.get(numRomanoRegion[j] + i);
//				}
//				mapa.put("TP" + i, totalAct);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return mapa;
//	}
	
	private HashMap<String, Integer> inicializaDatosFinales(){
		HashMap<String, Integer> datos = new HashMap<String, Integer>();
		for(int i = 1; i < 12; i++){
			datos.put(numRomanoRegion[0] + i, 0);
			datos.put(numRomanoRegion[1] + i, 0);
			datos.put(numRomanoRegion[2] + i, 0);
			datos.put(numRomanoRegion[3] + i, 0);
			datos.put(numRomanoRegion[4] + i, 0);
			datos.put(numRomanoRegion[5] + i, 0);
			datos.put(numRomanoRegion[6] + i, 0);
			datos.put(numRomanoRegion[7] + i, 0);
			datos.put(numRomanoRegion[8] + i, 0);
			datos.put(numRomanoRegion[9] + i, 0);
			datos.put(numRomanoRegion[10] + i, 0);
			datos.put(numRomanoRegion[11] + i, 0);
			datos.put(numRomanoRegion[12] + i, 0);
			datos.put(numRomanoRegion[13] + i, 0);
			datos.put(numRomanoRegion[14] + i, 0);
			datos.put(numRomanoRegion[15] + i, 0);
			datos.put("TP" + i, 0);
		}
		return datos;
	}
	
}
