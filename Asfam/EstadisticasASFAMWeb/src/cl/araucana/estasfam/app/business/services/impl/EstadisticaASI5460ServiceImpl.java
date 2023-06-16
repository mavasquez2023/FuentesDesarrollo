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
import cl.araucana.estasfam.app.business.model.PagosDirectoPorTipoDTO;
import cl.araucana.estasfam.app.business.model.TramosDTO;
import cl.araucana.estasfam.app.business.services.EstadisticaASI5460Service;
import cl.araucana.estasfam.app.persistence.dao.EstadisticaASI5490Dao;
import cl.araucana.estasfam.app.persistence.dao.TramosDao;
import cl.araucana.estasfam.common.exceptions.DaoException;
import cl.araucana.estasfam.common.util.FechaUtil;
import cl.araucana.estasfam.common.util.NotNullUtil;

@Service
public class EstadisticaASI5460ServiceImpl implements EstadisticaASI5460Service {

	private @Value("${araucana.estasfam.pathxlsestadisticas}")
	String cnfPathXlsEstadisticas;
	
	private @Value("${araucana.estasfam.rutasplantillas.ASI5460}")
	String cnfPathPlantillaASI5460;
	
	@Autowired
	private EstadisticaASI5490Dao estadisticaASI5490Dao;
	@Autowired
	private TramosDao TramosDao;
	
	@Override
	public void generarEstadistica(){
		HashMap<String, Integer> datos = inicializaDatos();

		Date fechaHoy = new Date(); //FechaUtil.parsearFecha("yyyyMMdd", "20140801");
		
		try {
			//Pagos directos 
			Date mesPagosDir = FechaUtil.restarMeses(fechaHoy, 1);
			Date fechaPagosDirDesde = FechaUtil.parsearFecha("yyyyMMdd",  FechaUtil.formatearFecha("yyyyMM", mesPagosDir)+ "01"); 
			Date fechaPagosDirHasta = FechaUtil.parsearFecha("yyyyMMdd",  FechaUtil.formatearFecha("yyyyMM", fechaHoy)+ "01");
			
			List<PagosDirectoPorTipoDTO> listPagosDirectoConPago = estadisticaASI5490Dao.getPagosDirectosConPago(fechaHoy, fechaPagosDirDesde, fechaPagosDirHasta);
			
			for(PagosDirectoPorTipoDTO obj : listPagosDirectoConPago){
				if(!obj.getCodTipo().equals("A")){
					datos.put("V4", NotNullUtil.replaceNullToCero(datos.get("V4")) + obj.getMonto().intValue());
					datos.put("T4", NotNullUtil.replaceNullToCero(datos.get("T4")) + obj.getCantidad());
					
					if(TiposEnum.ASI5490_NORMAL.equals(obj.getCodTipo())){
						datos.put("N4", obj.getCantidad());
					}if(TiposEnum.ASI5490_MATERNAL.equals(obj.getCodTipo())){
						datos.put("M4", obj.getCantidad());
					}if(TiposEnum.ASI5490_INVALIDA.equals(obj.getCodTipo())){
						datos.put("I4", obj.getCantidad());
					}
				}else if(obj.getCodTipo().equals("A")){
					datos.put("A4", obj.getCantidad());
				}
			}
			
			
			//Seccion TOTALES
			datos.put("V5", datos.get("V4"));
			datos.put("T5", datos.get("T4"));
			datos.put("N5", datos.get("N4"));
			datos.put("M5", datos.get("M4"));
			datos.put("I5", datos.get("I4"));
			datos.put("A5", datos.get("A4"));
			
			datos.put("V8", datos.get("V4"));
			datos.put("T8", datos.get("T4"));
			datos.put("N8", datos.get("N4"));
			datos.put("M8", datos.get("M4"));
			datos.put("I8", datos.get("I4"));
			datos.put("A8", datos.get("A4"));
			
			//Seccion Promedios
			datos.put("P4", ((Integer)datos.get("T4")>0)?(Integer)datos.get("V4")/(Integer)datos.get("T4"):0);
			datos.put("P5", ((Integer)datos.get("T5")>0)?(Integer)datos.get("V5")/(Integer)datos.get("T5"):0);
			datos.put("P8", ((Integer)datos.get("T8")>0)?(Integer)datos.get("V8")/(Integer)datos.get("T8"):0);
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		generaXls(datos);
		
	}
	
	public void generaXls(java.util.HashMap<String, Integer> map) {
		
		try {
			// Se crea el libro
			InputStream stream = getClass().getResourceAsStream(cnfPathPlantillaASI5460);
			HSSFWorkbook estASI5490 = new HSSFWorkbook(stream);
			HSSFSheet hoja = estASI5490.getSheetAt(0);
			HSSFCellStyle stylePercent = null;
			HSSFCell cell = null;
			
			//Insertamos periodo de pago
			int anho = FechaUtil.getAno(new Date());
			Date fechaAux = FechaUtil.restarMeses(new Date(), 1);
			String mesAux = FechaUtil.getDescripcionMes(FechaUtil.getMes(fechaAux));
			String mes = FechaUtil.getDescripcionMes(FechaUtil.getMes(new Date()));
			HSSFRichTextString cadena = new HSSFRichTextString(mesAux+" / "+anho);
			stylePercent = hoja.getRow(3).getCell(5).getCellStyle();		    		
		    cell = hoja.getRow(3).getCell(5);
		    cell.setCellValue(cadena);
		    cell.setCellStyle(stylePercent);
		    
		    //Insertamos fecha
			String fecha = FechaUtil.formatearFecha("dd/MM/yy", new Date());
			cadena = new HSSFRichTextString(fecha);
			stylePercent = hoja.getRow(1).getCell(9).getCellStyle();		    		
		    cell = hoja.getRow(1).getCell(9);
		    cell.setCellValue(cadena);
		    cell.setCellStyle(stylePercent);
		    
		    try {
				int mesCargas = FechaUtil.getMes(fechaAux);
				int anhoCargas = FechaUtil.getAno(fechaAux);
				List<TramosDTO> listTramos = TramosDao.getTramos(mesCargas > 6 ? anhoCargas : anhoCargas-1);
				int tramo = 2;
				for(TramosDTO tramos:listTramos){
					stylePercent = hoja.getRow(7).getCell(tramo).getCellStyle();		    		
				    cell = hoja.getRow(7).getCell(tramo);
				    cell.setCellValue(tramos.getTramo());
				    cell.setCellStyle(stylePercent);
				    tramo++;
				}
			} catch (DaoException e1) {
				System.out.println("Callo tramosDAO");
			}
		    cadena = new HSSFRichTextString("0");
			int fil = 8;
			for(int i=0; i<8; i++){//Contador de filas
				int col = 5;
				for(int j=4; j<9; j++){//Contador de columnas
					if(col == 5 || col == 6 || col == 9){
						if(fil==8){
							stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
						    cell = hoja.getRow(fil).getCell(col);
						    if(map.containsKey("N"+j) && map.get("N"+j)>0){
						    	cell.setCellValue(map.get("N"+j));
						    }else{
						    	cell.setCellValue(cadena);
						    }
						    cell.setCellStyle(stylePercent);
						}else
						if(fil==9){
							stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
						    cell = hoja.getRow(fil).getCell(col);
						    if(map.containsKey("M"+j) && map.get("M"+j)>0){
						    	cell.setCellValue(map.get("M"+j));
						    }else{
						    	cell.setCellValue(cadena);
						    }
						    cell.setCellStyle(stylePercent);
						}else
						if(fil==10){
							stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
						    cell = hoja.getRow(fil).getCell(col);
						    if(map.containsKey("I"+j) && map.get("I"+j)>0){
						    	cell.setCellValue(map.get("I"+j));
						    }else{
						    	cell.setCellValue(cadena);
						    }
						    cell.setCellStyle(stylePercent);
						}else
						if(fil==11){
							stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
						    cell = hoja.getRow(fil).getCell(col);
						    if(map.containsKey("T"+j) && map.get("T"+j)>0){
						    	cell.setCellValue(map.get("T"+j));
						    }else{
						    	cell.setCellValue(cadena);
						    }
						    cell.setCellStyle(stylePercent);
						}else
						if(fil==12){
							stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
						    cell = hoja.getRow(fil).getCell(col);
						    if(map.containsKey("V"+j) && map.get("V"+j)>0){
						    	cell.setCellValue(map.get("V"+j));
						    }else{
						    	cell.setCellValue(cadena);
						    }
						    cell.setCellStyle(stylePercent);
						}else
						if(fil==13){
							stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
						    cell = hoja.getRow(fil).getCell(col);
						    if(map.containsKey("P"+j) && map.get("P"+j)>0){
						    	cell.setCellValue(map.get("P"+j));
						    }else{
						    	cell.setCellValue(cadena);
						    }
						    cell.setCellStyle(stylePercent);
						}else
						if(fil==14){
							stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
						    cell = hoja.getRow(fil).getCell(col);
						    if(map.containsKey("A"+j) && map.get("A"+j)>0){
						    	cell.setCellValue(map.get("A"+j));
						    }else{
						    	cell.setCellValue(cadena);
						    }
						    cell.setCellStyle(stylePercent);
						}
					}
					col++;
				}
				fil++;
			}
			
			String mesAnterior = FechaUtil.getDescripcionMes(FechaUtil.getMes(new Date())-1>0 ? FechaUtil.getMes(new Date())-1 : 12);
			
			File file = new File(cnfPathXlsEstadisticas + EstadisticasEnum.ASI5460.getSoloNombreXls() 
													+ mesAnterior + EstadisticasEnum.ASI5460.getSoloExtencion());
			if(file.exists()) {
				if(file.delete()){
					System.out.println("Borrado");
				}else{
					System.out.println("No Borrado");
				}
			}
			FileOutputStream elFichero = new FileOutputStream(new File(
					cnfPathXlsEstadisticas + EstadisticasEnum.ASI5460.getSoloNombreXls() + mes + EstadisticasEnum.ASI5460.getSoloExtencion()));
			estASI5490.write(elFichero);
			elFichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private HashMap<String, Integer> inicializaDatos(){
		HashMap<String, Integer> datos = new HashMap<String, Integer>();
		for(int i = 1; i < 9; i++){
			if(i == 4 || i == 5 || i == 8){
				datos.put("N" + i, 0);
				datos.put("M" + i, 0);
				datos.put("I" + i, 0);
				datos.put("T" + i, 0);
				datos.put("V" + i, 0);
				datos.put("P" + i, 0);
				datos.put("A" + i, 0);
			}
		}
		return datos;
	}
}
