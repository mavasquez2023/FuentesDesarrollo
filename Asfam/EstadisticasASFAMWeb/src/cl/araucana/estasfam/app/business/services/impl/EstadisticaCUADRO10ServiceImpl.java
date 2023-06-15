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

import cl.araucana.estasfam.app.business.enums.EstadisticasEnum;
import cl.araucana.estasfam.app.business.enums.TiposEnum;
import cl.araucana.estasfam.app.business.model.CargasPorColumnaDTO;
import cl.araucana.estasfam.app.business.services.EstadisticaCUADRO10Service;
import cl.araucana.estasfam.app.persistence.dao.EstadisticaCUADRO10Dao;
import cl.araucana.estasfam.common.exceptions.DaoException;
import cl.araucana.estasfam.common.util.FechaUtil;
import cl.araucana.estasfam.common.util.NotNullUtil;

@Service
public class EstadisticaCUADRO10ServiceImpl implements EstadisticaCUADRO10Service {
	
	private @Value("${araucana.estasfam.pathxlsestadisticas}")
	String cnfPathXlsEstadisticas;
	
	private @Value("${araucana.estasfam.rutasplantillas.Cuadro10}")
	String cnfPathPlantillaCuadro10;

	@Autowired
	private EstadisticaCUADRO10Dao estadisticaCUADRO10Dao;
	
	@Override
	public void generarEstadistica() {
		HashMap<String, Integer> datos = inicializaDatos();
		
		Date fechaHoy = new Date(); //FechaUtil.parsearFecha("yyyyMMdd", "20140801");
		Date fecPeriodo = FechaUtil.restarMeses(fechaHoy, 2);
		
		try {
			//Seccion cantidad afiliados
			List<CargasPorColumnaDTO> listDatosCUADRO10 = estadisticaCUADRO10Dao.getDatosCUADRO10(fecPeriodo);
			
			for(CargasPorColumnaDTO obj : listDatosCUADRO10){
				if(TiposEnum.CUADRO10_CONYUGUE.equals(obj.getCodTipo())){
					switch(obj.getCodColumna()){
					case 1: 
						datos.put("C1", obj.getCantidad());
						datos.put("T1", NotNullUtil.replaceNullToCero(datos.get("T1")) + obj.getCantidad());
						break;
					case 2:
						datos.put("C2", obj.getCantidad());
						datos.put("T2", NotNullUtil.replaceNullToCero(datos.get("T2")) + obj.getCantidad());
						break;
					}
				}else if(TiposEnum.CUADRO10_HIJOS.equals(obj.getCodTipo())){
					switch(obj.getCodColumna()){
					case 1: 
						datos.put("H1", obj.getCantidad());
						datos.put("T1", NotNullUtil.replaceNullToCero(datos.get("T1")) + obj.getCantidad());
						break;
					case 2:
						datos.put("H2", obj.getCantidad());
						datos.put("T2", NotNullUtil.replaceNullToCero(datos.get("T2")) + obj.getCantidad());
						break;
					}
				}else if(TiposEnum.CUADRO10_MATERNAL.equals(obj.getCodTipo())){
					switch(obj.getCodColumna()){
					case 1: 
						datos.put("M1", obj.getCantidad());
						datos.put("T1", NotNullUtil.replaceNullToCero(datos.get("T1")) + obj.getCantidad());
						break;
					case 2:
						datos.put("M2", obj.getCantidad());
						datos.put("T2", NotNullUtil.replaceNullToCero(datos.get("T2")) + obj.getCantidad());
						break;
					}
				}else if(TiposEnum.CUADRO10_ASCENDIENTES.equals(obj.getCodTipo())){
					switch(obj.getCodColumna()){
					case 1: 
						datos.put("A1", obj.getCantidad());
						datos.put("T1", NotNullUtil.replaceNullToCero(datos.get("T1")) + obj.getCantidad());
						break;
					case 2:
						datos.put("A2", obj.getCantidad());
						datos.put("T2", NotNullUtil.replaceNullToCero(datos.get("T2")) + obj.getCantidad());
						break;
					}
				}else{
					switch(obj.getCodColumna()){
					case 1: 
						datos.put("O1", NotNullUtil.replaceNullToCero(datos.get("O1")) + obj.getCantidad());
						datos.put("T1", NotNullUtil.replaceNullToCero(datos.get("T1")) + obj.getCantidad());
						break;
					case 2:
						datos.put("O2", NotNullUtil.replaceNullToCero(datos.get("O2")) + obj.getCantidad());
						datos.put("T2", NotNullUtil.replaceNullToCero(datos.get("T2")) + obj.getCantidad());
						break;
					}
				}
			}
			
			//Seccion TOTALES 
			Integer totalConyugue = 0;
			Integer totalHijos = 0;
			Integer totalMaternal = 0;
			Integer totalAscendentes = 0;
			Integer totalOtros = 0;
			

			for(int i = 1; i < 5; i++){ 
				totalConyugue = NotNullUtil.replaceNullToCero(datos.get("C" + i)) + totalConyugue;
				totalHijos = NotNullUtil.replaceNullToCero(datos.get("H" + i)) + totalHijos;
				totalMaternal = NotNullUtil.replaceNullToCero(datos.get("M" + i)) + totalMaternal;
				totalAscendentes = NotNullUtil.replaceNullToCero(datos.get("A" + i)) + totalAscendentes;
				totalOtros = NotNullUtil.replaceNullToCero(datos.get("O" + i)) + totalOtros;
			}
			datos.put("C3", totalConyugue);
			datos.put("H3", totalHijos);
			datos.put("M3", totalMaternal);
			datos.put("A3", totalAscendentes);
			datos.put("O3", totalOtros);
			datos.put("T3", totalOtros+totalConyugue+totalHijos+totalMaternal+totalAscendentes);

		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		generaXls(datos);
	}
	
	public void generaXls(java.util.HashMap<String, Integer> map) {
		
		try {
			// Se crea el libro
			InputStream stream = getClass().getResourceAsStream(cnfPathPlantillaCuadro10);
			HSSFWorkbook estASI5490 = new HSSFWorkbook(stream);
			HSSFSheet hoja = estASI5490.getSheetAt(0);
			HSSFCellStyle stylePercent = null;
			HSSFCell cell = null;
			
			//Insertamos periodo
			int anho = FechaUtil.getAno(new Date());
			Date fechaAux = FechaUtil.restarMeses(new Date(), 1);
			String mesAux = FechaUtil.getDescripcionMes(FechaUtil.getMes(fechaAux));
			String mes = FechaUtil.getDescripcionMes(FechaUtil.getMes(new Date()));
			HSSFRichTextString cadena = new HSSFRichTextString("MES	  :   "+mesAux+" "+anho);
			stylePercent = hoja.getRow(3).getCell(2).getCellStyle();		    		
		    cell = hoja.getRow(3).getCell(2);
		    cell.setCellValue(cadena);
		    cell.setCellStyle(stylePercent);
			
			int fil = 6;
			for(int i=0; i<6; i++){//Contador de filas
				int col = 2;
				for(int j=1; j<4; j++){//Contador de columnas
					if(fil==6){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    cell.setCellValue(map.containsKey("C"+j) ? map.get("C"+j) : 0);
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==7){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    cell.setCellValue(map.containsKey("H"+j) ? map.get("H"+j) : 0);
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==8){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    cell.setCellValue(map.containsKey("M"+j) ? map.get("M"+j) : 0);
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==9){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    cell.setCellValue(map.containsKey("A"+j) ? map.get("A"+j) : 0);
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==10){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    cell.setCellValue(map.containsKey("O"+j) ? map.get("O"+j) : 0);
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==11){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    cell.setCellValue(map.containsKey("T"+j) ? map.get("T"+j) : 0);
					    cell.setCellStyle(stylePercent);
					}
					col++;
				}
				fil++;
			}
			String mesAnterior = FechaUtil.getDescripcionMes(FechaUtil.getMes(new Date())-1>0 ? FechaUtil.getMes(new Date())-1 : 12);
			
			File file = new File(cnfPathXlsEstadisticas + EstadisticasEnum.CUADRO10.getSoloNombreXls() 
													+ mesAnterior + EstadisticasEnum.CUADRO10.getSoloExtencion());
			if(file.exists()) {
				if(file.delete()){
					System.out.println("Borrado");
				}else{
					System.out.println("No Borrado");
				}
			}
			FileOutputStream elFichero = new FileOutputStream(new File(
					cnfPathXlsEstadisticas + EstadisticasEnum.CUADRO10.getSoloNombreXls() + mes + EstadisticasEnum.CUADRO10.getSoloExtencion()));
			estASI5490.write(elFichero);
			elFichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private HashMap<String, Integer> inicializaDatos(){
		HashMap<String, Integer> datos = new HashMap<String, Integer>();
		for(int i = 1; i < 6; i++){
			datos.put("C" + i, 0);
			datos.put("H" + i, 0);
			datos.put("M" + i, 0);
			datos.put("A" + i, 0);
			datos.put("O" + i, 0);
			datos.put("T" + i, 0);
		}
		return datos;
	}

}
