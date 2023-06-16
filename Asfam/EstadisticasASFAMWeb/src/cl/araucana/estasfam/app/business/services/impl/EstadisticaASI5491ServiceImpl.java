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
import cl.araucana.estasfam.app.business.model.CargasPorTipoDTO;
import cl.araucana.estasfam.app.business.model.PagosDirectoPorTipoDTO;
import cl.araucana.estasfam.app.business.services.EstadisticaASI5491Service;
import cl.araucana.estasfam.app.persistence.dao.EstadisticaASI5491Dao;
import cl.araucana.estasfam.app.persistence.dao.TramosDao;
import cl.araucana.estasfam.common.exceptions.DaoException;
import cl.araucana.estasfam.common.util.FechaUtil;
import cl.araucana.estasfam.common.util.NotNullUtil;

@Service
public class EstadisticaASI5491ServiceImpl implements EstadisticaASI5491Service{

	private @Value("${araucana.estasfam.pathxlsestadisticas}")
	String cnfPathXlsEstadisticas;
	
	private @Value("${araucana.estasfam.rutasplantillas.ASI5491}")
	String cnfPathPlantillaASI5491;
	
	@Autowired
	private EstadisticaASI5491Dao estadisticaASI5491Dao;
	
	@Autowired
	private TramosDao TramosDao;
	
	@Override
	public void generarEstadistica() {
		HashMap<String, Integer> datos = inicializaDatos();
		
		Date fechaHoy = new Date(); //FechaUtil.parsearFecha("yyyyMMdd", "20140801");
		Date fechaCargas = FechaUtil.restarMeses(fechaHoy, 2);
		
		try {
			//Seccion cargas autorizadas (Cargas del mes - Cargas atrasadas)
			List<CargasPorColumnaDTO> listCargasAutorizadas = estadisticaASI5491Dao.getCargasAutorizadas(fechaCargas);
			for(CargasPorColumnaDTO obj : listCargasAutorizadas){
				if(TiposEnum.ASI5491_CONYUGUE.equals(obj.getCodTipo())){
					switch(obj.getCodColumna()){
					case 1: 
						datos.put("M1", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("M7", NotNullUtil.replaceNullToCero(datos.get("M7")) + obj.getCantidad());
						break;
					case 2:
						datos.put("A1", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("A7", NotNullUtil.replaceNullToCero(datos.get("A7")) + obj.getCantidad());
						break;
					}
				}else if(TiposEnum.ASI5491_ASCENDIENTES.equals(obj.getCodTipo())){
					switch(obj.getCodColumna()){
					case 1: 
						datos.put("M2", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("M7", NotNullUtil.replaceNullToCero(datos.get("M7")) + obj.getCantidad());
						break;
					case 2:
						datos.put("A2", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("A7", NotNullUtil.replaceNullToCero(datos.get("A7")) + obj.getCantidad());
						break;
					}
				}else if(TiposEnum.ASI5491_HIJOS.equals(obj.getCodTipo())){
					switch(obj.getCodColumna()){
					case 1: 
						datos.put("M3", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("M7", NotNullUtil.replaceNullToCero(datos.get("M7")) + obj.getCantidad());
						break;
					case 2:
						datos.put("A3", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("A7", NotNullUtil.replaceNullToCero(datos.get("A7")) + obj.getCantidad());
						break;
					}
				}else if(TiposEnum.ASI5491_MATERNAL.equals(obj.getCodTipo())){
					switch(obj.getCodColumna()){
					case 1: 
						datos.put("M4", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("M7", NotNullUtil.replaceNullToCero(datos.get("M7")) + obj.getCantidad());
						break;
					case 2:
						datos.put("A4", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("A7", NotNullUtil.replaceNullToCero(datos.get("A7")) + obj.getCantidad());
						break;
					}
				}else if(TiposEnum.ASI5491_INVALIDA.equals(obj.getCodTipo())){
					switch(obj.getCodColumna()){
					case 1: 
						datos.put("M6", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("M7", NotNullUtil.replaceNullToCero(datos.get("M7")) + obj.getCantidad());
						break;
					case 2:
						datos.put("A6", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("A7", NotNullUtil.replaceNullToCero(datos.get("A7")) + obj.getCantidad());
						break;
					}
				}else{
					switch(obj.getCodColumna()){
					case 1: 
						datos.put("M5", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("M7", NotNullUtil.replaceNullToCero(datos.get("M7")) + obj.getCantidad());
						break;
					case 2:
						datos.put("A5", NotNullUtil.replaceNullToCero(obj.getCantidad()));
						datos.put("A7", NotNullUtil.replaceNullToCero(datos.get("A7")) + obj.getCantidad());
						break;
					}
				}
			}
			
			//Suma Subsidios vigentes (Cargas del mes)
			List<CargasPorTipoDTO> listSubsiodiosVigentes = estadisticaASI5491Dao.getSubsidiosVigenetes();
			for(CargasPorTipoDTO obj : listSubsiodiosVigentes){
				if(TiposEnum.ASI5491_CONYUGUE.equals(obj.getCodTipo())){
					datos.put("M1", NotNullUtil.replaceNullToCero(datos.get("M1")) + obj.getCantidad());
					datos.put("M7", NotNullUtil.replaceNullToCero(datos.get("M7")) + obj.getCantidad());
				}else if(TiposEnum.ASI5491_ASCENDIENTES.equals(obj.getCodTipo())){
					datos.put("M2", NotNullUtil.replaceNullToCero(datos.get("M2")) + obj.getCantidad());
					datos.put("M7", NotNullUtil.replaceNullToCero(datos.get("M7")) + obj.getCantidad());
				}else if(TiposEnum.ASI5491_HIJOS.equals(obj.getCodTipo())){
					datos.put("M3", NotNullUtil.replaceNullToCero(datos.get("M3")) + obj.getCantidad());
					datos.put("M7", NotNullUtil.replaceNullToCero(datos.get("M7")) + obj.getCantidad());
				}else if(TiposEnum.ASI5491_MATERNAL.equals(obj.getCodTipo())){
					datos.put("M4", NotNullUtil.replaceNullToCero(datos.get("M4")) + obj.getCantidad());
					datos.put("M7", NotNullUtil.replaceNullToCero(datos.get("M7")) + obj.getCantidad());
				}else if(TiposEnum.ASI5491_INVALIDA.equals(obj.getCodTipo())){
					datos.put("M6", NotNullUtil.replaceNullToCero(datos.get("M6")) + obj.getCantidad());
					datos.put("M7", NotNullUtil.replaceNullToCero(datos.get("M7")) + obj.getCantidad());
				}else{
					datos.put("M5", NotNullUtil.replaceNullToCero(datos.get("M5")) + obj.getCantidad());
					datos.put("M7", NotNullUtil.replaceNullToCero(datos.get("M7")) + obj.getCantidad());
				}
			}
			
			//Pagos directos autorizados (Cargas atrasadas)
			Date mesPagosDir = FechaUtil.restarMeses(fechaHoy, 1);
			Date fechaPagosDirDesde = FechaUtil.parsearFecha("yyyyMMdd",  FechaUtil.formatearFecha("yyyyMM", mesPagosDir)+ "01"); 
			Date fechaPagosDirHasta = FechaUtil.parsearFecha("yyyyMMdd",  FechaUtil.formatearFecha("yyyyMM", fechaHoy)+ "01");
			List<PagosDirectoPorTipoDTO> listPagosDirectoAutorizados = estadisticaASI5491Dao
					.getPagosDirectosAutorizados(fechaHoy, fechaPagosDirDesde, fechaPagosDirHasta);
			for(PagosDirectoPorTipoDTO obj : listPagosDirectoAutorizados){
				if(TiposEnum.ASI5491_CONYUGUE.equals(obj.getCodTipo())){
					datos.put("A1", NotNullUtil.replaceNullToCero(datos.get("A1")) + obj.getCantidad());
					datos.put("A7", NotNullUtil.replaceNullToCero(datos.get("A7")) + obj.getCantidad());
				}else if(TiposEnum.ASI5491_ASCENDIENTES.equals(obj.getCodTipo())){
					datos.put("A2", NotNullUtil.replaceNullToCero(datos.get("A2")) + obj.getCantidad());
					datos.put("A7", NotNullUtil.replaceNullToCero(datos.get("A7")) + obj.getCantidad());
				}else if(TiposEnum.ASI5491_HIJOS.equals(obj.getCodTipo())){
					datos.put("A3", NotNullUtil.replaceNullToCero(datos.get("A3")) + obj.getCantidad());
					datos.put("A7", NotNullUtil.replaceNullToCero(datos.get("A7")) + obj.getCantidad());
				}else if(TiposEnum.ASI5491_MATERNAL.equals(obj.getCodTipo())){
					datos.put("A4", NotNullUtil.replaceNullToCero(datos.get("A4")) + obj.getCantidad());
					datos.put("A7", NotNullUtil.replaceNullToCero(datos.get("A7")) + obj.getCantidad());
				}else if(TiposEnum.ASI5491_INVALIDA.equals(obj.getCodTipo())){
					datos.put("A6", NotNullUtil.replaceNullToCero(datos.get("A6")) + obj.getCantidad());
					datos.put("A7", NotNullUtil.replaceNullToCero(datos.get("A7")) + obj.getCantidad());
				}else{
					datos.put("A5", NotNullUtil.replaceNullToCero(datos.get("A5")) + obj.getCantidad());
					datos.put("A7", NotNullUtil.replaceNullToCero(datos.get("A7")) + obj.getCantidad());
				}
			}
			
			//Columna totales
			datos.put("T1", NotNullUtil.replaceNullToCero(datos.get("M1")) + NotNullUtil.replaceNullToCero(datos.get("A1")));
			datos.put("T2", NotNullUtil.replaceNullToCero(datos.get("M2")) + NotNullUtil.replaceNullToCero(datos.get("A2")));
			datos.put("T3", NotNullUtil.replaceNullToCero(datos.get("M3")) + NotNullUtil.replaceNullToCero(datos.get("A3")));
			datos.put("T4", NotNullUtil.replaceNullToCero(datos.get("M4")) + NotNullUtil.replaceNullToCero(datos.get("A4")));
			datos.put("T5", NotNullUtil.replaceNullToCero(datos.get("M5")) + NotNullUtil.replaceNullToCero(datos.get("A5")));
			datos.put("T6", NotNullUtil.replaceNullToCero(datos.get("M6")) + NotNullUtil.replaceNullToCero(datos.get("A6")));
			datos.put("T7", NotNullUtil.replaceNullToCero(datos.get("M7")) + NotNullUtil.replaceNullToCero(datos.get("A7")));
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		generaXls(datos);
	}

	public void generaXls(java.util.HashMap<String, Integer> map) {
		
		try {
			// Se crea el libro
			InputStream stream = getClass().getResourceAsStream(cnfPathPlantillaASI5491);
			HSSFWorkbook estASI5490 = new HSSFWorkbook(stream);
			HSSFSheet hoja = estASI5490.getSheetAt(0);
			HSSFCellStyle stylePercent = null;
			HSSFCell cell = null;
			
			//Insertamos periodo
			int anho = FechaUtil.getAno(new Date());
			Date fechaAux = FechaUtil.restarMeses(new Date(), 1);
			String mesAux = FechaUtil.getDescripcionMes(FechaUtil.getMes(fechaAux));
			String mes = FechaUtil.getDescripcionMes(FechaUtil.getMes(new Date()));
			HSSFRichTextString cadena = new HSSFRichTextString(mesAux+" "+anho);
			stylePercent = hoja.getRow(2).getCell(11).getCellStyle();		    		
		    cell = hoja.getRow(2).getCell(11);
		    cell.setCellValue(cadena);
		    cell.setCellStyle(stylePercent);
		    
		    cadena = new HSSFRichTextString("0");
			int fil = 7;
			int col = 1;
			for(int j=1; j<8; j++){//Contador de columnas
				stylePercent = hoja.getRow(fil).getCell(col).getCellStyle();		    		
			    cell = hoja.getRow(fil).getCell(col);
			    if(map.containsKey("M"+j) && map.get("M"+j)>0){
			    	cell.setCellValue(map.get("M"+j));
			    }else{
			    	cell.setCellValue(cadena);
			    }
			    cell.setCellStyle(stylePercent);

				stylePercent = hoja.getRow(fil).getCell(col+7).getCellStyle();		    		
			    cell = hoja.getRow(fil).getCell(col+7);
			    if(map.containsKey("A"+j) && map.get("A"+j)>0){
			    	cell.setCellValue(map.get("A"+j));
			    }else{
			    	cell.setCellValue(cadena);
			    }
			    cell.setCellStyle(stylePercent);

				stylePercent = hoja.getRow(fil).getCell(col+14).getCellStyle();		    		
			    cell = hoja.getRow(fil).getCell(col+14);
			    if(map.containsKey("T"+j) && map.get("T"+j)>0){
			    	cell.setCellValue(map.get("T"+j));
			    }else{
			    	cell.setCellValue(cadena);
			    }
			    cell.setCellStyle(stylePercent);
				col++;
			}
			
			String mesAnterior = FechaUtil.getDescripcionMes(FechaUtil.getMes(new Date())-1>0 ? FechaUtil.getMes(new Date())-1 : 12);
			
			File file = new File(cnfPathXlsEstadisticas + EstadisticasEnum.ASI5491.getSoloNombreXls() 
													+ mesAnterior + EstadisticasEnum.ASI5491.getSoloExtencion());
			if(file.exists()) {
				if(file.delete()){
					System.out.println("Borrado");
				}else{
					System.out.println("No Borrado");
				}
			}
			FileOutputStream elFichero = new FileOutputStream(new File(
					cnfPathXlsEstadisticas + EstadisticasEnum.ASI5491.getSoloNombreXls() + mes + EstadisticasEnum.ASI5491.getSoloExtencion()));

			estASI5490.write(elFichero);
			elFichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private HashMap<String, Integer> inicializaDatos(){
		HashMap<String, Integer> datos = new HashMap<String, Integer>();
		for(int i = 1; i < 8; i++){
			datos.put("M" + i, 0);
			datos.put("A" + i, 0);
			datos.put("T" + i, 0);
		}
		return datos;
	}
}
