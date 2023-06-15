package cl.liv.cargas.masivas.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cl.liv.archivos.comun.ArchivosUtiles;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.cargas.masivas.dto.CargaDTO;
import cl.liv.cargas.masivas.dto.ParametroDTO;
import cl.liv.cargas.masivas.service.ICargaMasivaService;
import cl.liv.cargas.masivas.util.TiposEntrada;
import cl.liv.cargas.masivas.util.UtilLogCargas;
import cl.liv.cargas.masivas.util.Utiles;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.logs.UtilLogErrores;
import cl.sbs.util.reflection.impl.UtilReflectionImpl;

public class CargaMasivaImpl implements ICargaMasivaService{
	
	static String MENSAJE_EXITOSO = "LA INFORMACIÃ“N SE HA REGISTRADO CORRECTAMENTE";
	
	public static boolean detenerProceso = false;
	
	public int execute(MiHashMap config){
		UtilLogCargas.info ("executando carga masiva: "+ config);
		CargaDTO carga = LoadConfiguracionesCargas.getDataCarga(config.get("ID_CARGA").toString(),config.get("ID_PROPERTIES").toString());
		if(carga != null){
			int resultado = 0;
			String archivo = "";
			if(config.get("TIPO").equals("FTP"))
				archivo = ArchivosUtiles.descargarArchivoPorRutaFTP("FTP;"+config.get("ORIGEN").toString(), config.get("ARCHIVO").toString() , LoadConfiguracionesCargas.getPropertiesByKey("planillas").getString("carga.masiva.path.input") );
			else
				archivo = config.get("ORIGEN").toString();
			
			UtilLogCargas.info("ruta Archivo-> "+ archivo);
			
			if( Utiles.getExtension(config.get("archivo").toString()).equals("xlsx") ){

				resultado = cargaDesdeExcel(config, carga, archivo);
			}
			else{
				resultado =  cargaDesdeArchivoPlano(config, carga, archivo);
			}
			if(carga.isEliminarArchivoOriginal()){
				ArchivosUtiles.eliminarArchivoPorRuta(archivo);
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return resultado;
		}
		else{
			UtilLogCargas.info("saliendo de cargas masivas sin ejecución");
			return 0;
		}
	} 
	
	
	private int cargaDesdeArchivoPlano(MiHashMap config, CargaDTO carga, String archivo){
		config.put("cantidad_registros_encontrados", new Integer(0));
		
		BufferedReader br = ManejoArchivoTXT.getOpenedFileToRead(archivo,true, carga.getEncoding());
		String rutaResultado = archivo + carga.getExtensionSalida();
		PrintWriter pw = null;
		if(carga.isGenerarArchivoResultado()){
			pw = ManejoArchivoTXT.getOpenedFileToWrite(rutaResultado);
		}
		boolean quedanRegistros = true;
		int contadorLineas = 0;
		boolean conErrores = false;
		while(quedanRegistros){
			
			String registro = ManejoArchivoTXT.getLineFromFileOpened(br);
			if(registro != null){
				String resultado = procesarRegistro(carga, registro, config.get("CODIGO").toString());
				if(!resultado.contains(MENSAJE_EXITOSO)){
					conErrores = true;
				}
				if(carga.isGenerarArchivoResultado()){
					ManejoArchivoTXT.putLineFromFileOpened(pw, resultado);
				}
			}
			else{
				quedanRegistros = false;
			}
			contadorLineas++;
			if(detenerProceso){
				quedanRegistros = false; 

				UtilLogCargas.debug("deteniendo el proceso carga...");
			}
		}
		
		if(carga.getActionFinish() != null && carga.getActionFinish().length()>0){
			procesarActionFinish(carga);
		}
		
		config.put("cantidad_registros_encontrados", new Integer(contadorLineas));
		ManejoArchivoTXT.closeFileToRead(br);
		if(carga.isGenerarArchivoResultado()){
			ManejoArchivoTXT.closeFileToWrite(pw);
		}
		
		
		if(conErrores){
			UtilLogCargas.info("saliendo de cargas masivas con errores");
			return 2;
		}
		else{

			UtilLogCargas.info("saliendo de cargas masivas con ejecución exitosa");
			return 1;
		}
	}
	
	
	
	private int cargaDesdeExcel(MiHashMap config, CargaDTO carga, String archivo){
		
		File excel = new File(archivo);
		
		try {
			Workbook workbook = new XSSFWorkbook(excel);
			 Sheet datatypeSheet = workbook.getSheetAt(0);
	            Iterator iterator = datatypeSheet.iterator();
	            int contadorRow = 0;
	            while (iterator.hasNext()) {
	                Row currentRow = (Row)iterator.next();
	            	if(contadorRow >= carga.getFilaInicial()){
		                MiHashMap registro = new MiHashMap();
		                
		                for(int i=0; i< carga.getParametros().size(); i++){
		                	
		                	 Cell currentCell = currentRow.getCell(((ParametroDTO)carga.getParametros().get(i)).getPosicion());
		                	 
		                	 if (currentCell.getCellType() == Cell.CELL_TYPE_BLANK) {
				                	registro.put( ((ParametroDTO)carga.getParametros().get(i)).getKey() , ""  );
			                    } 
			                    else if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
				                	registro.put( ((ParametroDTO)carga.getParametros().get(i)).getKey() , currentCell.getStringCellValue()  );
			                    } 
			                    else if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				                	registro.put( ((ParametroDTO)carga.getParametros().get(i)).getKey() , new Double(currentCell.getNumericCellValue()) );
			                    } 
		                	 
		                }
		            	 procesarRegistro(carga, registro, config.get("CODIGO").toString());
	            	}
	            	contadorRow++;
	               }
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		   
		if(!procesarActionFinish(carga)){
			return 2;
		}
		return 1;
	}
	
	public boolean validate(MiHashMap conf){
		UtilLogCargas.info("validando existencia de archivo: "+ conf);
		
		if(conf.get("TIPO").equals("FTP")){
			String ruta = ArchivosUtiles.existeArchivoPorRutaFTP("FTP;"+conf.get("ORIGEN").toString(), conf.get("ARCHIVO").toString());
			if(ruta != null && !ruta.toLowerCase().contains("error:"))
				return true;
		}
		else{
			String ruta = ArchivosUtiles.existeArchivoPorRuta(conf.get("ORIGEN").toString());
			if(ruta != null && !ruta.toLowerCase().contains("error:"))
				return true;
		}
		
		return false;
		
	} 
	
	int contadorRegistro = 0;
	long ultimaEjecucion = 0L;
	private String procesarRegistro(CargaDTO carga, String registro, String codigoTarea){
		HashMap parametros = new HashMap();
		if(ultimaEjecucion == 0L || (new Date().getTime() - ultimaEjecucion) > 5000){
			ultimaEjecucion = new Date().getTime();
			UtilLogCargas.info("." );
			
		}
		String[] partes = registro.split(carga.getSeparador());
		String error = "";
		parametros:
		
		for (int i=0; i<carga.getParametros().size(); i++) {
			ParametroDTO parametro = (ParametroDTO)carga.getParametros().get(i);
			if(parametro != null){
				
				String valorParametro = null;
				try{
					if(carga.getTipoEntrada() == TiposEntrada.POR_SEPARADOR)
						valorParametro = getValorFromArray(partes, parametro.getPosicion());
					if(carga.getTipoEntrada() == TiposEntrada.POR_INDICE)
						valorParametro = getValorRegistro(parametro, registro);
				}catch (Exception e){
					UtilLogCargas.error("->["+carga.getId()+" || "+parametro.getKey()+"]"+e);
					valorParametro = "null";
				}
				
				if(( valorParametro == null || valorParametro.trim().length()== 0 ) && parametro.getValorDefaultNull() != null && parametro.getValorDefaultNull().length() > 0){
					
					if(parametro.getValorDefaultNull().contains("java_reflection")){
						String[] partesDefault = parametro.getValorDefaultNull().split(":");
						valorParametro = (String) UtilReflectionImpl.executeReflection(partesDefault[1], partesDefault[2]);
					}
					else
						valorParametro = parametro.getValorDefaultNull();
				}
				
				if(parametro.getValidacion()!= null && parametro.getValidacion().length()>0){
					error = validaData(parametro.getPosicion(),valorParametro, parametro.getValidacion());
					if(error != null)
						break parametros;
				}
				
				if(parametro.getOnLoad() != null && parametro.getOnLoad().length() > 0){
					if(parametro.getOnLoad().contains("java_reflection")){
						String[] partesDefault = parametro.getOnLoad().split(":");
						
						Class[] paramsTypes = new Class[2];
						paramsTypes[0] = String.class;
						paramsTypes[1] = HashMap.class;
						
						Object[] pars = new Object[2];
						pars[0] = valorParametro;
						pars[1] = parametros;
						
						valorParametro = (String) UtilReflectionImpl.executeReflection(partesDefault[1], partesDefault[2],paramsTypes, pars);
					}
				}
				parametros.put(parametro.getKey(), valorParametro );
				
			}
		}
		String estado = MENSAJE_EXITOSO;
		if(error != null && error.length()>0){
			return registro+";::::"+error;
		}
		
		if(carga.getAction() != null && carga.getAction().length()>0){
			error = procesarAction(carga, parametros,codigoTarea);
		}
		

		if(error == null){	
			return registro+";::::"+estado;
		}
		else {
			return registro+";::::"+error;
		}
		
	}
	
	private String procesarRegistro(CargaDTO carga, MiHashMap registro, String codigoTarea){
		HashMap parametros = new HashMap();
		String error = "";
		
		if(carga.getAction() != null && carga.getAction().length()>0){
			error = procesarAction(carga, registro,codigoTarea);
		}
		

		String estado = MENSAJE_EXITOSO;
		if(error == null){	
			return registro+";::::"+estado;
		}
		else {
			return registro+";::::"+error;
		}
		
	}
	
	private static String validaData(int posicionEnArchivo, String data, String validacion){
		
		if(validacion.contains("not_null")){
			if(data != null && data.length()>0){
				return null;
			}
			else{
				return "Error Validacion en la posicion ["+posicionEnArchivo+"] no puede ser nulo o vacÃ­o ";
			}
		}
		return null;
	}
	
	private String procesarAction(CargaDTO carga, HashMap parametros , String codigoTarea){
	
		if(parametros != null){
			parametros.put("__origen", codigoTarea);
		}
		
		if(carga.getAction().contains("persistencia.ibatis")){
			
		}
		else if(carga.getAction().contains("java_reflection")){
			String[] partesDefault = carga.getAction().split(";");
			
			Class[] paramsTypes = new Class[1];
			paramsTypes[0] = HashMap.class;
			
			Object[] pars = new Object[1];
			pars[0] = parametros;
			
			UtilReflectionImpl.executeReflection(partesDefault[1], partesDefault[2],paramsTypes, pars);
		} 
		return null;
	}
	
	private boolean procesarActionFinish(CargaDTO carga ){
		
		if(carga.getAction().contains("java_reflection")){
			String[] partesDefault = carga.getActionFinish().split(";");
			Class[] paramsTypes = new Class[0];
			Object[] pars = new Object[0];
			Object resultado = UtilReflectionImpl.executeReflection(partesDefault[1], partesDefault[2],paramsTypes, pars);
			if(resultado instanceof Boolean){
				if(!((Boolean)resultado).booleanValue()){
					return false;
				}
			}
		} 
		return true;
	}
	
		
	public static String getValorRegistro(ParametroDTO parametro, String registro){
		String valor = "";
		try{
		if(registro != null){
			valor = registro.substring(parametro.getPosicion(), parametro.getPosicion() + parametro.getLargo());
			
		}
		}catch(Exception e){

			UtilLogErrores.error(e);
			e.printStackTrace();
		}
		
		return valor;
	}
	
	public String getValorFromArray(String[] array, int posicion){
	
		if(array != null && array.length>posicion){
			return array[posicion];
		}
		
		return " ";
	}
	

	
}
