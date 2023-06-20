package cl.laaraucana.rendicionpagonomina.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.exception.ErrorMessage;
import cl.laaraucana.rendicionpagonomina.exception.MiException;
import cl.laaraucana.rendicionpagonomina.ftp.exceptions.FTPErrors;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.CabeceraTefDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.CabeceraTefDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.CommonLicenciaDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.CommonLicenciaDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.DetalleTefDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.DetalleTefDaoImpl;
import cl.laaraucana.rendicionpagonomina.utils.FileManagmentUtils;
import cl.laaraucana.rendicionpagonomina.vo.FTPConfigurationVO;

@Service
public class ProcesaRendicionNominasValeVistaServiceImpl implements ProcesaRendicionNominasValeVistaService{

	private static final Logger logger = Logger.getLogger(ProcesaRendicionNominasValeVistaServiceImpl.class);
	
	@Autowired
	FTPServicePluss ftpServicePluss;
	
	@Autowired
	CabeceraService cabeceraService;
	
	@Autowired
	DetalleService detalleService;
	
	@Value("${ftp.config.erp.conection.host}")
	private String FTP_ERP_HOST;
	
	@Value("${ftp.config.erp.conection.port}")
	private int FTP_ERP_PORT;
	
	@Value("${ftp.config.erp.conection.user}")
	private String FTP_ERP_USER;
	
	@Value("${ftp.config.erp.conection.pass}")
	private String FTP_ERP_PASS;
	
	@Value("${ftp.config.erp.conection.input.vv.path}")
	private String FTP_ERP_GET_PATH;
	
	@Value("${ftp.config.erp.conection.input.vv.name}")
	private String NAME_LOGIC;

	@Value("${path.tranking.folder}")
	private String TRACKING_PATH;

	@Value("${ftp.config.historico.host}")
	private String FTP_HISTORICO_HOST;
	
	@Value("${ftp.config.historico.port}")
	private int FTP_HISTORICO_PORT;
	
	@Value("${ftp.config.historico.user}")
	private String FTP_HISTORICO_USER;
	
	@Value("${ftp.config.historico.pass}")
	private String FTP_HISTORICO_PASS;
	
	@Value("${ftp.config.historico.rendicion.vv.path}")
	private String PATH_HISTORICO;

	@Value("${ftp.config.notificacion.rendicion.vv.path}")
	private String PATH_NOTIFICACION;
	
	SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddhhmm");
	
	
	@Override
	public int loadData() throws MiException {
		logger.debug("buscando archivos rendicion VV");
		FTPConfigurationVO config = new FTPConfigurationVO();
		config.setHost(FTP_ERP_HOST);
		config.setPort(FTP_ERP_PORT);
		config.setUser(FTP_ERP_USER);
		config.setPass(FTP_ERP_PASS);
		config.setPathToGet(FTP_ERP_GET_PATH);
		logger.debug("buscando archivos para procesar VV en ERP-Connection...");
		try {
			String[] archivos = ftpServicePluss.searchFilesFromFTP(config, NAME_LOGIC);
			if(archivos != null && archivos.length > 0) {
				for (String archivo : archivos) {
					try {
						logger.debug("Archivo encontrado ["+archivo+"]");	
						return loadDataFromFile(archivo);
					} catch (Exception e) {
						logger.fatal("Error al procesar el Archivo ["+archivo+"], mensaje:" + e);
						//e.printStackTrace();
					}
				}
			}
			else {
				throw new MiException(new ErrorMessage(0, "No se encontraron archivos"));
			}
		} catch (FTPErrors e) {
			logger.fatal(e);
			throw new MiException(new ErrorMessage(0, "No se encontraron archivos"));
		}
		
		return 0;
	}

	@Override
	public int loadDataFromFile(String fileNameFTP) throws MiException {
		int estadoSalida = 0;
		logger.debug("Iniciando el proceso de Rendicion para Nóminas VV...");
		
		String banco= "BCI";
		String path= TRACKING_PATH.replaceAll("#banco#", banco);
		
		PrintWriter trackingFile = FileManagmentUtils.getOpenedFileToWrite(path + fileNameFTP +"_"+ formato.format(new Date()));
		
		FileManagmentUtils.putLineFromFileOpened(trackingFile, "Iniciando Proceso Rendicion para Nóminas VV :"+ new Date());
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"Obteniendo Información...");
		CommonLicenciaDao commonLicenciaDao = new CommonLicenciaDaoImpl();

		HashMap<String, String> formasPagoBCI = new HashMap<String, String>();		
		try {
			formasPagoBCI = commonLicenciaDao.getFormasPago();
		} catch (Exception e1) {
			logger.fatal(e1);
			FileManagmentUtils.putLineFromFileOpened(trackingFile,"Error Obteniendo formas de pago");
			throw new MiException(new ErrorMessage(2, "Error Obteniendo formas de pago"));
		}
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"formasPago["+formasPagoBCI+"]");
		logger.debug("formasPago ->"+ formasPagoBCI);
		
		
		FileManagmentUtils.putLineFromFileOpened(trackingFile, "procesando archivo  "+ fileNameFTP);
	
		FTPConfigurationVO config = new FTPConfigurationVO();
		
		config.setHost(FTP_ERP_HOST);
		config.setPort(FTP_ERP_PORT);
		config.setUser(FTP_ERP_USER);
		config.setPass(FTP_ERP_PASS);
		config.setPathToGet(FTP_ERP_GET_PATH);

		logger.debug("buscando archivo VV...");
		FileManagmentUtils.putLineFromFileOpened(trackingFile, "buscando archivo en FTP  "+ fileNameFTP);
		
		String nombreArchivoOriginal = fileNameFTP;
		File file = null;
		try {
			file = ftpServicePluss.getFileFromFTP(config, nombreArchivoOriginal);
		} catch (FTPErrors e1) {
			logger.fatal(e1);
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "Problemas al Obtener el archivo VV ["+fileNameFTP+"]");
			throw new MiException(new ErrorMessage(3, "Problemas al Obtener el archivo VV ["+fileNameFTP+"]"));
		}

		FileManagmentUtils.putLineFromFileOpened(trackingFile, "archivo encontrado length: "+file.length()+"bytes");
		HashMap<Long, Boolean> cabeceras = new HashMap<Long, Boolean>();
		logger.debug("archivo obtenido : "+ file);
		if(file != null && file.length()>0) {
			
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "leyendo registros archivo ...");
			logger.debug("leyendo data archivo.. : "+ file);
			BufferedReader br = FileManagmentUtils.getOpenedFileToRead(file.getAbsolutePath(), true, null);
			
			ArrayList<String> detalle = new ArrayList<String>();
			String registro = FileManagmentUtils.getLineFromFileOpened(br);
			while(registro != null ) {
				if(registro.startsWith(" 2")) {
					detalle.add(registro);
				}
				else if(registro.trim().length() > 0){
					FileManagmentUtils.putLineFromFileOpened(trackingFile, "detalleExcluido ["+registro+"]");
				}

				registro = FileManagmentUtils.getLineFromFileOpened(br);
			}
			FileManagmentUtils.closeFileToRead(br);

			FileManagmentUtils.putLineFromFileOpened(trackingFile, "detalles Encontrados -> "+ detalle.size());
			logger.debug("Data Leida, archivo cerrado ");
			

			//Paso2 - Actualizar data en BD
			long codigoNomina=0;
			for (String reg : detalle) {
				int rutAfiliado = Integer.parseInt(reg.substring(3, 11));
				String dv = reg.substring(11, 12);
				String estadoPagoNomina = reg.substring(168, 172);
				int estadoPago = 0;
				String descripcionEstado = "";
				String codigoFormaPago = "VVC";
				if("0001".equals(estadoPagoNomina)) {
					estadoPago = 1;
					descripcionEstado = "Abono otro banco efectuado";
				}
				else if("0003".equals(estadoPagoNomina)) {
					codigoFormaPago = null;
					estadoPago = 1;
					descripcionEstado = "Abono cuenta BCI Efectivo";
				}
				else if("5110".equals(estadoPagoNomina)) {
					estadoPago = 1;
					descripcionEstado = "Vale vista emitido";
				}
				else if("5115".equals(estadoPagoNomina)) {
					estadoPago = 1;
					descripcionEstado = "Vale vista entregado";
				}
				else if("5120".equals(estadoPagoNomina)) {
					estadoPago = 1;
					descripcionEstado = "Vale vista pagado";
				}
				else if("5125".equals(estadoPagoNomina)) {
					estadoPago = 6;
					descripcionEstado = "Vale vista Devuelto (Caducado, transitorio)";
				}
				else if("5135".equals(estadoPagoNomina)) {
					estadoPago = 6;
					descripcionEstado = "Vale vista Impreso devuelto al tomador";
				}
				else if("5145".equals(estadoPagoNomina)) {
					estadoPago = 6;
					descripcionEstado = "Fondo devuelto";
				}
				else if("5150".equals(estadoPagoNomina)) {
					estadoPago = 6;
					descripcionEstado = "Vale Vista Reintegrado";
				}
				String folioTesoreria = reg.substring(100, 112);
				
				
				DetalleTefDao detalleDao = new DetalleTefDaoImpl();
				
				HashMap<String, Long> params= new HashMap<String, Long>();
				params.put("rutAfiliado", new Long(rutAfiliado));
				params.put("referencia1", new Long(folioTesoreria));
				Long idCabecera;
				try {
					idCabecera = detalleDao.getIdCabeceraPorDetalle(params);
					if(idCabecera != null) {
						params.put("idCabecera", idCabecera);
						DetalleEntity detEntity = detalleService.findByRut(params);
						if(detEntity!=null){
							detEntity.setEstadoPago(estadoPago);
							detEntity.setDescripcionEstadoPago(descripcionEstado);
							detEntity.setDescripcionRechazo("");
							detEntity.setCodigoFormaPago(codigoFormaPago);
							detEntity.setDescripcionFormaPago(formasPagoBCI.get(  codigoFormaPago ));
							
							detalleService.saveOrupdate(detEntity);
							logger.debug("detalle update ->"+ detEntity.toString());
							FileManagmentUtils.putLineFromFileOpened(trackingFile, " detalle modificado "+ detEntity.toString());
						}
						if(!cabeceras.containsKey(idCabecera)){
							cabeceras.put(idCabecera, Boolean.TRUE);
						}
					}
				} catch (Exception e1) {
					logger.error("Error al grabar detalle, mensaje: " + e1.getMessage());
					e1.printStackTrace();
				}
				
			}
			
			
			//Actualizar totales cabecera
			

			CabeceraTefDao cabeceraTefDao = new CabeceraTefDaoImpl();
			
			if(cabeceras != null) {
				Iterator<Long> it =cabeceras.keySet().iterator();
				
				while(it.hasNext()) {
					Long idCabecera = it.next().longValue()  ;
										
					try {
						cabeceraService.updateTotalesNomina(idCabecera, codigoNomina);
					} catch (Exception e) {
						logger.error("Error actualizando estado y totales cabecera " + idCabecera);
						FileManagmentUtils.putLineFromFileOpened(trackingFile,"Cabecera error al actualizar estado y totales nómina id nómina -> ["+idCabecera+"]");
						throw new MiException(new ErrorMessage(6, "Cabecera error al actualizar estado y totales nómina id nómina  -> ["+idCabecera+"]") );
					}
					
					
					
				}
			}
			
			
			//Moviendo el archivo al Histórico
			FTPConfigurationVO configHistorico = new FTPConfigurationVO();
			
			configHistorico.setHost(FTP_ERP_HOST);
			configHistorico.setPort(FTP_ERP_PORT);
			configHistorico.setUser(FTP_ERP_USER);
			configHistorico.setPass(FTP_ERP_PASS);
			configHistorico.setPathToPut(PATH_HISTORICO);

			logger.debug("llevando archivo a historico ...");
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "llevando archivo a historico ...");
			int resultadoPut = 0;
			try {
				resultadoPut = ftpServicePluss.putFileToFTP(configHistorico, file, file.getName(), true);
			} catch (FTPErrors e) {
				logger.fatal(e);
				throw new MiException(new ErrorMessage(7, "Problemas al llevar el archivo al histórico ["+file.getName()+"]"));
			
			}
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "llevando archivo a historico resultado ="+resultadoPut);
			logger.debug("llevando archivo a historico resultado ="+resultadoPut);
			
			//moviendo el archivo a la Notificación
			FTPConfigurationVO configNotifica = new FTPConfigurationVO();
			
			configNotifica.setHost(FTP_HISTORICO_HOST);
			configNotifica.setPort(FTP_HISTORICO_PORT);
			configNotifica.setUser(FTP_HISTORICO_USER);
			configNotifica.setPass(FTP_HISTORICO_PASS);
			configNotifica.setPathToPut(PATH_NOTIFICACION);

			logger.debug("llevando archivo VV a Notifica...");
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "llevando archivo VV a Notifica ...");
			resultadoPut = 0;
			try {
				resultadoPut = ftpServicePluss.putFileToFTP(configNotifica, file, file.getName(), true);
			} catch (FTPErrors e) {
				logger.fatal(e);
				throw new MiException(new ErrorMessage(7, "Problemas al llevar el archivo VV al Notifica ["+file.getName()+"]"));
			
			}
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "llevando archivo VV a Notifica resultado ="+resultadoPut);
			logger.debug("llevando archivo VV a Notifica resultado ="+resultadoPut);
			
			logger.debug("Eliminando archivo en ERP-Connection...");
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "Eliminando archivo en ERP-Connection...");
			int resultDelete = 0;
			try {
				resultDelete = ftpServicePluss.deleteFileInFTP(config, config.getPathToGet(), fileNameFTP);
			} catch (FTPErrors e1) {
				logger.fatal(e1);
				throw new MiException(new ErrorMessage(8, "Problemas al eliminar archivo en ERP-Connection ["+fileNameFTP+"]"));
			}
			logger.debug("Eliminando archivo en ERP-Connection resultado->"+resultDelete);
			
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "Eliminando archivo en ERP-Connection resultado->"+resultDelete);
			
			estadoSalida = 1;
		}
		else{
			logger.debug("problemas leyendo el archivo, o bien está vacío");
			
		}

		logger.debug("Terminando proceso de Rendicion para Nominas VV, con estado ["+estadoSalida+"]");
		logger.debug("Cerrando tracking file...");

		FileManagmentUtils.putLineFromFileOpened(trackingFile, "Cerrando proceso ");
		FileManagmentUtils.closeFileToWrite(trackingFile);
		logger.debug("Cerrando tracking file OK !");
		return estadoSalida;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getAvailablesFiles() throws MiException {
		ArrayList<HashMap<String, Object>> files = new ArrayList<HashMap<String,Object>>();
		FTPConfigurationVO config = new FTPConfigurationVO();
		config.setHost(FTP_ERP_HOST);
		config.setPort(FTP_ERP_PORT);
		config.setUser(FTP_ERP_USER);
		config.setPass(FTP_ERP_PASS);
		config.setPathToGet(FTP_ERP_GET_PATH);
		logger.debug("buscando archivos para procesar ...");
		try {
			String[] archivos = ftpServicePluss.searchFilesFromFTP(config, NAME_LOGIC);
			if(archivos != null && archivos.length > 0) {
				for (String archivo : archivos) {
					logger.debug("Archivo encontrado ["+archivo+"]");	
					HashMap<String, Object> nomina = new HashMap<String, Object>();
					nomina.put("file",archivo);
					nomina.put("type","Vale Vista");
					nomina.put("status","disponible");
					nomina.put("enabled", true);
					files.add(nomina);
				}
			}
			
		} catch (FTPErrors e) {
			logger.fatal(e);
			throw new MiException(new ErrorMessage(0, "No se encontraron archivos"));
		}
		return files;
	}

}
