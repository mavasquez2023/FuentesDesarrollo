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
import cl.araucana.estasfam.app.business.model.CargasPorTipoDTO;
import cl.araucana.estasfam.app.business.model.PagosDirectoPorTipoDTO;
import cl.araucana.estasfam.app.business.model.TramosDTO;
import cl.araucana.estasfam.app.business.services.EstadisticaASI5490Service;
import cl.araucana.estasfam.app.persistence.dao.EstadisticaASI5490Dao;
import cl.araucana.estasfam.app.persistence.dao.TramosDao;
import cl.araucana.estasfam.common.exceptions.DaoException;
import cl.araucana.estasfam.common.util.FechaUtil;
import cl.araucana.estasfam.common.util.NotNullUtil;

@Service
public class EstadisticaASI5490ServiceImpl implements EstadisticaASI5490Service {

	private @Value("${araucana.estasfam.pathxlsestadisticas}")
	String cnfPathXlsEstadisticas;
	
	private @Value("${araucana.estasfam.rutasplantillas.ASI5490}")
	String cnfPathPlantillaASI5490;

	@Autowired
	private EstadisticaASI5490Dao estadisticaASI5490Dao;
	@Autowired
	private TramosDao TramosDao;
	
	@Override
	public void generarEstadistica(){
		HashMap<String, Integer> datos = inicializaDatos();
		
		//TODO cambiar fechaHoy antes de subir
		Date fechaHoy = new Date(); //FechaUtil.parsearFecha("yyyyMMdd", "20140801");
		Date fechaCargas = FechaUtil.restarMeses(fechaHoy, 2);
		
		try {
			//Seccion cargas del mes con pago
			List<CargasPorTipoDTO> listCargasMesConPago = estadisticaASI5490Dao.getCargasMesConPago(fechaCargas);
			for(CargasPorTipoDTO obj : listCargasMesConPago){
				if(TiposEnum.ASI5490_NORMAL.equals(obj.getCodTipo())){
					switch(obj.getCodTramo()){
					case 1: 
						datos.put("N1", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("V1", NotNullUtil.replaceNullToCero(datos.get("V1")) + obj.getMonto());
						datos.put("T1", NotNullUtil.replaceNullToCero(datos.get("T1")) + obj.getCantidad());
						break;
					case 2:
						datos.put("N2", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("V2", NotNullUtil.replaceNullToCero(datos.get("V2")) + obj.getMonto());
						datos.put("T2", NotNullUtil.replaceNullToCero(datos.get("T2")) + obj.getCantidad());
						break;
					case 3: 
						datos.put("N3", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("V3", NotNullUtil.replaceNullToCero(datos.get("V3")) + obj.getMonto());
						datos.put("T3", NotNullUtil.replaceNullToCero(datos.get("T3")) + obj.getCantidad());
						break;
					default: 
						break;
					}
				}if(TiposEnum.ASI5490_MATERNAL.equals(obj.getCodTipo())){
					switch(obj.getCodTramo()){
					case 1: 
						datos.put("M1", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("V1", NotNullUtil.replaceNullToCero(datos.get("V1")) + obj.getMonto());
						datos.put("T1", NotNullUtil.replaceNullToCero(datos.get("T1")) + obj.getCantidad());
						break;
					case 2:
						datos.put("M2", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("V2", NotNullUtil.replaceNullToCero(datos.get("V2")) + obj.getMonto());
						datos.put("T2", NotNullUtil.replaceNullToCero(datos.get("T2")) + obj.getCantidad());
						break;
					case 3: 
						datos.put("M3", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("V3", NotNullUtil.replaceNullToCero(datos.get("V3")) + obj.getMonto());
						datos.put("T3", NotNullUtil.replaceNullToCero(datos.get("T3")) + obj.getCantidad());
						break;
					default: 
						break;
				}
				}if(TiposEnum.ASI5490_INVALIDA.equals(obj.getCodTipo())){
					switch(obj.getCodTramo()){
					case 1: 
						datos.put("I1", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("V1", NotNullUtil.replaceNullToCero(datos.get("V1")) + obj.getMonto());
						datos.put("T1", NotNullUtil.replaceNullToCero(datos.get("T1")) + obj.getCantidad());
						break;
					case 2:
						datos.put("I2", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("V2", NotNullUtil.replaceNullToCero(datos.get("V2")) + obj.getMonto());
						datos.put("T2", NotNullUtil.replaceNullToCero(datos.get("T2")) + obj.getCantidad());
						break;
					case 3: 
						datos.put("I3", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("V3", NotNullUtil.replaceNullToCero(datos.get("V3")) + obj.getMonto());
						datos.put("T3", NotNullUtil.replaceNullToCero(datos.get("T3")) + obj.getCantidad());
						break;
					default: 
						break;
				}
				}
			}
			
			//Seccion cargas atrasadas con pago + pagos directos 
			List<CargasPorTipoDTO> listCargasAtrasadasConPago = estadisticaASI5490Dao.getCargasAtrasadasConPago(fechaCargas);
			for(CargasPorTipoDTO obj : listCargasAtrasadasConPago){
				datos.put("V4", NotNullUtil.replaceNullToCero(datos.get("V4")) + obj.getMonto());
				datos.put("T4", NotNullUtil.replaceNullToCero(datos.get("T4")) + obj.getCantidad());
				if(TiposEnum.ASI5490_NORMAL.equals(obj.getCodTipo())){
					datos.put("N4", obj.getCantidad());
				}if(TiposEnum.ASI5490_MATERNAL.equals(obj.getCodTipo())){
					datos.put("M4", obj.getCantidad());
				}if(TiposEnum.ASI5490_INVALIDA.equals(obj.getCodTipo())){
					datos.put("I4", obj.getCantidad());
				}
			}
			Date mesPagosDir = FechaUtil.restarMeses(fechaHoy, 1);
			Date fechaPagosDirDesde = FechaUtil.parsearFecha("yyyyMMdd",  FechaUtil.formatearFecha("yyyyMM", mesPagosDir)+ "01"); 
			Date fechaPagosDirHasta = FechaUtil.parsearFecha("yyyyMMdd",  FechaUtil.formatearFecha("yyyyMM", fechaHoy)+ "01");
			List<PagosDirectoPorTipoDTO> listPagosDirectoConPago = estadisticaASI5490Dao.getPagosDirectosConPago(fechaHoy, fechaPagosDirDesde, fechaPagosDirHasta);
			for(PagosDirectoPorTipoDTO obj : listPagosDirectoConPago){
				if(!obj.getCodTipo().equals("A")){
					datos.put("V4", NotNullUtil.replaceNullToCero(datos.get("V4")) + obj.getMonto().intValue());
					datos.put("T4", NotNullUtil.replaceNullToCero(datos.get("T4")) + obj.getCantidad());
					if(TiposEnum.ASI5490_NORMAL.equals(obj.getCodTipo())){
						datos.put("N4", datos.get("N4") + obj.getCantidad());
					}if(TiposEnum.ASI5490_MATERNAL.equals(obj.getCodTipo())){
						datos.put("M4", datos.get("M4") + obj.getCantidad());
					}if(TiposEnum.ASI5490_INVALIDA.equals(obj.getCodTipo())){
						datos.put("I4", datos.get("I4") + obj.getCantidad());
					}
				}
			}
			
			//Seccion cargas del mes sin pago
			List<CargasPorTipoDTO> listCargasMesSinPago = estadisticaASI5490Dao.getCargasMesSinPago(fechaCargas);
			for(CargasPorTipoDTO obj : listCargasMesSinPago){
				datos.put("V6", NotNullUtil.replaceNullToCero(datos.get("V6")) + obj.getMonto());
				datos.put("T6", NotNullUtil.replaceNullToCero(datos.get("T6")) + obj.getCantidad());
				if(TiposEnum.ASI5490_NORMAL.equals(obj.getCodTipo())){
					datos.put("N6", obj.getCantidad());
				}if(TiposEnum.ASI5490_MATERNAL.equals(obj.getCodTipo())){
					datos.put("M6", obj.getCantidad());
				}if(TiposEnum.ASI5490_INVALIDA.equals(obj.getCodTipo())){
					datos.put("I6", obj.getCantidad());
				}
			}
			
			//Seccion cargas del atrasadas sin pago
			List<CargasPorTipoDTO> listCargasAtrasadasSinPago = estadisticaASI5490Dao.getCargasAtrasadasSinPago(fechaCargas);
			for(CargasPorTipoDTO obj : listCargasAtrasadasSinPago){
				datos.put("V7", NotNullUtil.replaceNullToCero(datos.get("V7")) + obj.getMonto());
				datos.put("T7", NotNullUtil.replaceNullToCero(datos.get("T7")) + obj.getCantidad());
				if(TiposEnum.ASI5490_NORMAL.equals(obj.getCodTipo())){
					datos.put("N7", obj.getCantidad());
				}if(TiposEnum.ASI5490_MATERNAL.equals(obj.getCodTipo())){
					datos.put("M7", obj.getCantidad());
				}if(TiposEnum.ASI5490_INVALIDA.equals(obj.getCodTipo())){
					datos.put("I7", obj.getCantidad());
				}
			}
			
			//Seccion TOTALES del mes
			Integer totalMesNormal = 0;
			Integer totalMesMaternal = 0;
			Integer totalMesInvalida = 0;
			Integer totalMesTotal = 0;
			Integer totalMesMonto = 0;
			for(int i = 1; i < 5; i++){ 
				totalMesNormal = NotNullUtil.replaceNullToCero(datos.get("N" + i)) + totalMesNormal;
				totalMesMaternal = NotNullUtil.replaceNullToCero(datos.get("M" + i)) + totalMesMaternal;
				totalMesInvalida = NotNullUtil.replaceNullToCero(datos.get("I" + i)) + totalMesInvalida;
				totalMesTotal = NotNullUtil.replaceNullToCero(datos.get("T" + i)) + totalMesTotal;
				totalMesMonto = NotNullUtil.replaceNullToCero(datos.get("V" + i)) + totalMesMonto;
			}
			datos.put("N5", totalMesNormal);
			datos.put("M5", totalMesMaternal);
			datos.put("I5", totalMesInvalida);
			datos.put("T5", totalMesTotal);
			datos.put("V5", totalMesMonto);

			//Seccion TOTALES
			Integer totalNormal = 0;
			Integer totalMaternal = 0;
			Integer totalInvalida = 0;
			Integer totalTotal = 0;
			Integer totalMonto = 0;
			for(int i = 5; i < 8; i++){ 
				totalNormal = NotNullUtil.replaceNullToCero(datos.get("N" + i)) + totalNormal;
				totalMaternal = NotNullUtil.replaceNullToCero(datos.get("M" + i)) + totalMaternal;
				totalInvalida = NotNullUtil.replaceNullToCero(datos.get("I" + i)) + totalInvalida;
				totalTotal = NotNullUtil.replaceNullToCero(datos.get("T" + i)) + totalTotal;
				totalMonto = NotNullUtil.replaceNullToCero(datos.get("V" + i)) + totalMonto;
			}
			datos.put("N8", totalNormal);
			datos.put("M8", totalMaternal);
			datos.put("I8", totalInvalida);
			datos.put("T8", totalTotal);
			datos.put("V8", totalMonto);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		generaXls(datos);

	}

	public void generaXls(java.util.HashMap<String, Integer> map) {
		
		try {
			// Se crea el libro
			InputStream stream = getClass().getResourceAsStream(cnfPathPlantillaASI5490);
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
			stylePercent = hoja.getRow(2).getCell(6).getCellStyle();		    		
		    cell = hoja.getRow(2).getCell(6);
		    cell.setCellValue(cadena);
		    cell.setCellStyle(stylePercent);
		    
		    //Insertamos fecha
			String fecha = FechaUtil.formatearFecha("dd/MM/yy", new Date());
			cadena = new HSSFRichTextString(fecha);
			stylePercent = hoja.getRow(1).getCell(10).getCellStyle();		    		
		    cell = hoja.getRow(1).getCell(10);
		    cell.setCellValue(cadena);
		    cell.setCellStyle(stylePercent);
		    			
			try {
				int mesCargas = FechaUtil.getMes(fechaAux);
				int anhoCargas = FechaUtil.getAno(fechaAux);
				List<TramosDTO> listTramos = TramosDao.getTramos(mesCargas > 6 ? anhoCargas : anhoCargas-1);
				int tramo = 3;
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
			for(int i=0; i<5; i++){//Contador de filas
				int col = 3;
				for(int j=1; j<9; j++){//Contador de columnas
					if(fil==7){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("N"+j) && map.get("N"+j)>0){
					    	cell.setCellValue(map.get("N"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==8){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("M"+j) && map.get("M"+j)>0){
					    	cell.setCellValue(map.get("M"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==9){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("I"+j) && map.get("I"+j)>0){
					    	cell.setCellValue(map.get("I"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==10){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("T"+j) && map.get("T"+j)>0){
					    	cell.setCellValue(map.get("T"+j));
					    }else{
					    	cell.setCellValue(cadena);
					    }
					    cell.setCellStyle(stylePercent);
					}else
					if(fil==11){
						stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
					    cell = hoja.getRow(fil).getCell(col);
					    if(map.containsKey("V"+j) && map.get("V"+j)>0){
					    	cell.setCellValue(map.get("V"+j));
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
			
			File file = new File(cnfPathXlsEstadisticas + EstadisticasEnum.ASI5490.getSoloNombreXls() 
													+ mesAnterior + EstadisticasEnum.ASI5490.getSoloExtencion());
			if(file.exists()) {
				if(file.delete()){
					System.out.println("Borrado");
				}else{
					System.out.println("No Borrado");
				}
			}
			FileOutputStream elFichero = new FileOutputStream(new File(
					cnfPathXlsEstadisticas + EstadisticasEnum.ASI5490.getSoloNombreXls() + mes + EstadisticasEnum.ASI5490.getSoloExtencion()));
			estASI5490.write(elFichero);
			elFichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private HashMap<String, Integer> inicializaDatos(){
		HashMap<String, Integer> datos = new HashMap<String, Integer>();
		for(int i = 1; i < 9; i++){
			datos.put("N" + i, 0);
			datos.put("M" + i, 0);
			datos.put("I" + i, 0);
			datos.put("T" + i, 0);
			datos.put("V" + i, 0);
		}
		return datos;
	}
	
}
