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

import cl.araucana.estasfam.app.business.enums.DescripcionNumeroMes;
import cl.araucana.estasfam.app.business.enums.EstadisticasEnum;
import cl.araucana.estasfam.app.business.enums.TiposEnum;
import cl.araucana.estasfam.app.business.model.AfiliadosPorTipoDTO;
import cl.araucana.estasfam.app.business.model.TramosDTO;
import cl.araucana.estasfam.app.business.services.EstadisticaCUADRO8Service;
import cl.araucana.estasfam.app.persistence.dao.EstadisticaCUADRO8Dao;
import cl.araucana.estasfam.app.persistence.dao.TramosDao;
import cl.araucana.estasfam.common.exceptions.DaoException;
import cl.araucana.estasfam.common.util.FechaUtil;
import cl.araucana.estasfam.common.util.NotNullUtil;

@Service
public class EstadisticaCUADRO8ServiceImpl implements EstadisticaCUADRO8Service {

	private @Value("${araucana.estasfam.pathxlsestadisticas}")
	String cnfPathXlsEstadisticas;
	
	private @Value("${araucana.estasfam.rutasplantillas.Cuadro8}")
	String cnfPathPlantillaCuadro8;
	
	@Autowired
	private EstadisticaCUADRO8Dao estadisticaCUADRO8Dao;
	@Autowired
	private TramosDao TramosDao;
	
	@Override
	public void generarEstadistica() {
		HashMap<String, Integer> datos = inicializaDatos();
		
		Date fechaHoy = new Date(); //FechaUtil.parsearFecha("yyyyMMdd", "20140801");
		//Mes actual menos 2
		String mesMenos2Meses = FechaUtil.formatearFecha("yyyyMM", FechaUtil.restarMeses(fechaHoy, 2));
		Date mesPeriodo = FechaUtil.parsearFecha("yyyyMMdd", mesMenos2Meses + "01");
		
		try {
			//Seccion cantidad afiliados
			List<AfiliadosPorTipoDTO> listDatosCUADRO8 = estadisticaCUADRO8Dao.getDatosCUADRO8(mesPeriodo);
			
			for(AfiliadosPorTipoDTO obj : listDatosCUADRO8){
				if(TiposEnum.CUADRO8_ACTIVOS.equals(obj.getCodTipo())){
					switch(obj.getCodTramo()){
					case -1:
						datos.put("A4", obj.getCantidad());
						datos.put("T4", NotNullUtil.replaceNullToCero(datos.get("T4")) + obj.getCantidad());
						break;
					case 0: 
						datos.put("A6", NotNullUtil.replaceNullToCero(datos.get("A6")) + obj.getCantidad());
						datos.put("T6", NotNullUtil.replaceNullToCero(datos.get("T6")) + obj.getCantidad());
						break;
					case 1: 
						datos.put("A1", obj.getCantidad());
						datos.put("T1", NotNullUtil.replaceNullToCero(datos.get("T1")) + obj.getCantidad());
						break;
					case 2:
						datos.put("A2", obj.getCantidad());
						datos.put("T2", NotNullUtil.replaceNullToCero(datos.get("T2")) + obj.getCantidad());
						break;
					case 3: 
						datos.put("A3", obj.getCantidad());
						datos.put("T3", NotNullUtil.replaceNullToCero(datos.get("T3")) + obj.getCantidad());
						break;
					case 4: 
						datos.put("A6", datos.get("A6") + obj.getCantidad());
						datos.put("T6", NotNullUtil.replaceNullToCero(datos.get("T6")) + obj.getCantidad());
						break;
					case 9: 
						datos.put("A6", datos.get("A6") + obj.getCantidad());
						datos.put("T6", NotNullUtil.replaceNullToCero(datos.get("T6")) + obj.getCantidad());
						break;
					}
				}if(TiposEnum.CUADRO8_SUBSIDIADOS.equals(obj.getCodTipo())){
					switch(obj.getCodTramo()){
					case 1: 
						datos.put("S1", obj.getCantidad());
						datos.put("T1", NotNullUtil.replaceNullToCero(datos.get("T1")) + obj.getCantidad());
						break;
					case -1:
						datos.put("S4", obj.getCantidad());
						datos.put("T4", NotNullUtil.replaceNullToCero(datos.get("T4")) + obj.getCantidad());
						break;
					}
				}
			}
			
			//Seccion TOTALES 
			Integer totalActivos = 0;
			Integer totalSubsidiados = 0;
			Integer totalTotal = 0;

			for(int i = 1; i < 5; i++){ 
				totalActivos = NotNullUtil.replaceNullToCero(datos.get("A" + i)) + totalActivos;
				totalSubsidiados = NotNullUtil.replaceNullToCero(datos.get("S" + i)) + totalSubsidiados;
				totalTotal = NotNullUtil.replaceNullToCero(datos.get("T" + i)) + totalTotal;

			}
			datos.put("A5", totalActivos);
			datos.put("S5", totalSubsidiados);
			datos.put("T5", totalTotal);
			datos.put("TF5", totalTotal+NotNullUtil.replaceNullToCero(datos.get("T6")));

		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		generaXls(datos);
		
	}
	
	public void generaXls(java.util.HashMap<String, Integer> map) {
		
		try {
			// Se crea el libro
			InputStream stream = getClass().getResourceAsStream(cnfPathPlantillaCuadro8);
			HSSFWorkbook estASI5490 = new HSSFWorkbook(stream);
			HSSFSheet hoja = estASI5490.getSheetAt(0);
			HSSFCellStyle stylePercent = null;
			HSSFCell cell = null;
			
			//Insertamos periodo de pago
			int anho = FechaUtil.getAno(new Date());
			String mes = FechaUtil.getDescripcionMes(FechaUtil.getMes(new Date()));
			String dia = FechaUtil.formatearFecha("dd", new Date());
			
			//Se busca mes anterior: mesAux
			Date fechaAux = FechaUtil.restarMeses(new Date(), 1);
			String mesAux = FechaUtil.getDescripcionMes(FechaUtil.getMes(fechaAux));
			
			//Se ajusta año de acuerdo a mes anterior 
			//clillo 27/03/2017
			String mesAnterior = mesAux;
			int anhoAnterior = anho;
			if(mesAnterior.equals(DescripcionNumeroMes.DICIEMBRE.getdMes())){
				anhoAnterior--;
			}
			
			HSSFRichTextString cadena = new HSSFRichTextString("PERIODO	  :   "+mesAux+" / "+anho);
			stylePercent = hoja.getRow(3).getCell(3).getCellStyle();		    		
		    cell = hoja.getRow(3).getCell(3);
		    cell.setCellValue(cadena);
		    cell.setCellStyle(stylePercent);
			
			cadena = new HSSFRichTextString("*Mes de cotizacion "+mesAnterior+" "+anhoAnterior+
									" cotizado al "+dia+" de "+mes.toLowerCase()+" "+anho+
									" Estadistica "+mes.toLowerCase()+" "+anho);
			stylePercent = hoja.getRow(12).getCell(1).getCellStyle();		    		
		    cell = hoja.getRow(12).getCell(1);
		    cell.setCellValue(cadena);
		    cell.setCellStyle(stylePercent);
		    
		    try {
		    	Date fechaHoy = FechaUtil.parsearFecha("yyyyMMdd", "20130601");
				Date fechaCargas = FechaUtil.restarMeses(fechaHoy, 2);int mesCargas = FechaUtil.getMes(fechaCargas);
				int anhoCargas = FechaUtil.getAno(fechaCargas);
				List<TramosDTO> listTramos = TramosDao.getTramos(mesCargas > 6 ? anhoCargas : anhoCargas-1);
				int tramo = 2;
				for(TramosDTO tramos:listTramos){
					stylePercent = hoja.getRow(6).getCell(tramo).getCellStyle();		    		
				    cell = hoja.getRow(6).getCell(tramo);
				    cell.setCellValue(tramos.getTramo());
				    cell.setCellStyle(stylePercent);
				    tramo++;
				}
			} catch (DaoException e1) {
				System.out.println("Callo tramosDAO");
			}
		    cadena = new HSSFRichTextString("0");
			int fil = 7;
			for(int i=0; i<4; i++){//Contador de filas
				int col = 2;
				for(int j=1; j<7; j++){//Contador de columnas
					if(fil==7){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("A"+j) && map.get("A"+j)>0){
					    	cell.setCellValue(map.get("A"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }	
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==8){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("S"+j) && map.get("S"+j)>0){
					    	cell.setCellValue(map.get("S"+j));
					    }else{
					    	if(j == 1 || j == 4 || j == 5)
					    		cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==9){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("T"+j) && map.get("T"+j)>0){
					    	cell.setCellValue(map.get("T"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}
					if(col == 6 & fil == 10){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("TF"+j) && map.get("TF"+j)>0){
					    	cell.setCellValue(map.get("TF"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}
					col++;
				}
				fil++;
			}
			String mesAnterior2 = FechaUtil.getDescripcionMes(FechaUtil.getMes(new Date())-1>0 ? FechaUtil.getMes(new Date())-1 : 12);
			
			File file = new File(cnfPathXlsEstadisticas + EstadisticasEnum.CUADRO8.getSoloNombreXls() 
													+ mesAnterior2 + EstadisticasEnum.CUADRO8.getSoloExtencion());
			if(file.exists()) {
				if(file.delete()){
					System.out.println("Borrado");
				}else{
					System.out.println("No Borrado");
				}
			}
			FileOutputStream elFichero = new FileOutputStream(new File(
					cnfPathXlsEstadisticas + EstadisticasEnum.CUADRO8.getSoloNombreXls() + mes + EstadisticasEnum.CUADRO8.getSoloExtencion()));
			estASI5490.write(elFichero);
			elFichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private HashMap<String, Integer> inicializaDatos(){
		HashMap<String, Integer> datos = new HashMap<String, Integer>();
		for(int i = 1; i <= 6; i++){
			datos.put("A" + i, 0);
			datos.put("S" + i, 0);
			datos.put("T" + i, 0);
		}
		return datos;
	}
}
