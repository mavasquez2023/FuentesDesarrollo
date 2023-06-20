package cl.laaraucana.rendicionpagonomina.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ProductoDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ProductoDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ProductoEntity;
import cl.laaraucana.rendicionpagonomina.utils.FileManagmentUtils;
import cl.laaraucana.rendicionpagonomina.vo.FTPConfigurationVO;

@Service
public class ProcesaRendicionNominas24HServiceImpl implements ProcesaRendicionNominas24HService {

	private static final Logger logger = Logger.getLogger(ProcesaRendicionNominas24HServiceImpl.class);
	
	@Autowired
	FTPServicePluss ftpServicePluss;
	
	@Autowired
	CabeceraService cabeceraService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	MandatoAS400Service mandatoService;
	
	@Autowired
	private DetalleService detalleService;
	
	@Value("${ftp.config.erp.conection.host}")
	private String FTP_ERP_HOST;
	
	@Value("${ftp.config.erp.conection.port}")
	private int FTP_ERP_PORT;
	
	@Value("${ftp.config.erp.conection.user}")
	private String FTP_ERP_USER;
	
	@Value("${ftp.config.erp.conection.pass}")
	private String FTP_ERP_PASS;
	
	@Value("${ftp.config.erp.conection.input.24h.path}")
	private String FTP_ERP_GET_PATH;
	
	@Value("${ftp.config.erp.conection.input.24h.name}")
	private String NAME_LOGIC;

	@Value("${path.rendicion.bci.folder}")
	private String RENDICION_PATH;

	SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddhhmm");
	
	@Value("${ftp.config.historico.rendicion.24h.path}")
	private String PATH_HISTORICO;

	
	@Override
	public int loadData() throws MiException {
		logger.debug("buscando archivos rendicino 24h...");
		FTPConfigurationVO config = new FTPConfigurationVO();
		config.setHost(FTP_ERP_HOST);
		config.setPort(FTP_ERP_PORT);
		config.setUser(FTP_ERP_USER);
		config.setPass(FTP_ERP_PASS);
		config.setPathToGet(FTP_ERP_GET_PATH);
		logger.debug("buscando archivos para procesar 24h en ERP-Connection...");
		try {
			String[] archivos = ftpServicePluss.searchFilesFromFTP(config, NAME_LOGIC);
			if(archivos != null && archivos.length > 0) {
				for (String archivo : archivos) {
					logger.debug("Archivo encontrado ["+archivo+"]");	
					loadDataFromFile(archivo);
				}
			}
			else {
				throw new MiException(new ErrorMessage(0, "No se encontraron archivos"));
			}
			return 1;
		} catch (FTPErrors e) {
			logger.fatal(e);
			//throw new MiException(new ErrorMessage(0, "No se encontraron archivos"));
		}
		
		return 0;
	}
	
	@Override
	public int loadDataFromFile(String fileNameFTP) throws MiException {
		int estadoSalida = 0;
		logger.debug("Iniciando el proceso de Rendicion para Nóminas 24H...");
		
		//Paso1 - Traer Nómina desde FTP BCI
		logger.debug("Iniciando Proceso Rendicion24H, loadData()");
		
		PrintWriter trackingFile = FileManagmentUtils.getOpenedFileToWrite(RENDICION_PATH + fileNameFTP +"_"+ formato.format(new Date()));
		
		FileManagmentUtils.putLineFromFileOpened(trackingFile, "Iniciando Proceso Rendicion para Nóminas 24H :"+ new Date());
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
	
		FTPConfigurationVO config = new FTPConfigurationVO();
		
		config.setHost(FTP_ERP_HOST);
		config.setPort(FTP_ERP_PORT);
		config.setUser(FTP_ERP_USER);
		config.setPass(FTP_ERP_PASS);
		config.setPathToGet(FTP_ERP_GET_PATH);

		logger.debug("buscando archivo Maternales...");
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"buscando archivo Maternales...");
		
		String nombreArchivoOriginal = fileNameFTP;
		File file = null;
		try {
			file = ftpServicePluss.getFileFromFTP(config, nombreArchivoOriginal);
		} catch (FTPErrors e1) {
			logger.fatal(e1);
			FileManagmentUtils.putLineFromFileOpened(trackingFile,"Problemas al Obtener el archivo Maternales ["+fileNameFTP+"]");
			throw new MiException(new ErrorMessage(3, "Problemas al Obtener el archivo Maternales ["+fileNameFTP+"]"));
		}
		
		//Paso2 - Actualizar data en BD
		
		logger.debug("archivo obtenido de ftp: "+ file);
		if(file != null && file.length()>0) {
		
			FileManagmentUtils.putLineFromFileOpened(trackingFile,"leyendo data archivo.. : "+ file);
			BufferedReader br = FileManagmentUtils.getOpenedFileToRead(file.getAbsolutePath(), true, null);

			String header = FileManagmentUtils.getLineFromFileOpened(br).trim();
			logger.debug("archivo asociado de cabecera: "+ header);
			
			HashMap<String, Object> cabecera = null;
			CabeceraTefDao cabeceraTefDao = new CabeceraTefDaoImpl();
			try {
				int pospunto= header.indexOf(".");
				if(pospunto>0){
					header=header.substring(0, pospunto).trim()+".txt";
				}
				logger.debug("obteniendo idCabecera por nombre: "+ header);
				cabecera = cabeceraTefDao.getIdCabeceraPorNombreArchivo(header);
			} catch (Exception e) {
				logger.fatal(e);
				FileManagmentUtils.putLineFromFileOpened(trackingFile,"Problemas al Obtener el idCabecera para el nombre archivo ["+header+"]");
				throw new MiException(new ErrorMessage(4, "Problemas al Obtener el idCabecera para el nombre archivo ["+header+"]"));
			}
			int idCodConv=0;
			String codProducto="";
			logger.debug("cabecera encontrado -> : "+ cabecera);
			FileManagmentUtils.putLineFromFileOpened(trackingFile,"cabecera encontrado -> : "+ cabecera);
			if(cabecera == null) {
				/*try {
					String bodyMail = MAIL_BODY;
					bodyMail= bodyMail.replace(":fecha_proceso", formato.format(new Date()));
					bodyMail= bodyMail.replace(":nombre_archivo_header", header.trim());
					bodyMail= bodyMail.replace(":nombre_archivo", fileNameFTP);
					bodyMail= bodyMail.replace(":Rut", formato.format(new Date()));
					bodyMail= bodyMail.replace(":fecha_proceso", formato.format(new Date()));
					mailService.sendEmail(MAIL_TO, MAIL_SUBJECT, bodyMail);
				} catch (Exception e1) {
					logger.fatal(e1);
					FileManagmentUtils.putLineFromFileOpened(trackingFile,"Error notificando por mail ["+MAIL_TO+","+MAIL_SUBJECT+"]");
				}
				*/
				FileManagmentUtils.putLineFromFileOpened(trackingFile,"Problemas al Obtener el idCabecera para el nombre archivo ["+header+"]");
				throw new MiException(new ErrorMessage(5, "Problemas al Obtener el idCabecera para el nombre archivo ["+header+"]"));
			}
			else {
				idCodConv = new Integer( cabecera.get("ID_COD_CONV").toString() ).intValue();
				codProducto= cabecera.get("CODIGO_PRODUCTO").toString();
				int estadoNomina = ((BigDecimal)cabecera.get("ESTADO_NOMINA")).intValue();
				Long idCabecera = ((BigDecimal)cabecera.get("ID_CABECERA")).longValue();
				//Condicion para ejecutar
				if( estadoNomina != 5  ) {

					ArrayList<String> detalle = new ArrayList<String>();
					String registro = FileManagmentUtils.getLineFromFileOpened(br);
					while(registro != null) {
						detalle.add(registro);
						registro = FileManagmentUtils.getLineFromFileOpened(br);
					}

					logger.info("detalles encontrados -> : "+ detalle.size());
					FileManagmentUtils.putLineFromFileOpened(trackingFile,"detalles encontrados -> : "+ detalle.size());
					long codigoNomina=0;
					for (String det : detalle) {
						int rutcliente= Integer.parseInt(det.substring(0, 8));
						try {
							codigoNomina=0;
							//Se busca detalle trabajador
							HashMap<String, Long> params= new HashMap<String, Long>();
							params.put("idCabecera", idCabecera);
							params.put("rutAfiliado", new Long(rutcliente));
							params.put("referencia1", new Long(det.substring(76, 88))); //referencia1

							DetalleEntity detEntity = detalleService.findByRut(params);

							String estadoPago = det.substring(130, 135);
							String formaPago= det.substring(88, 91);
							String descripcionPago = estadoPago + "-" + det.substring(135).trim();  
							
							logger.info("Rut " + rutcliente + ", forma pago=" + formaPago + ", estadoPago=" + estadoPago);
							
							if("00003".equals(estadoPago)&& !formaPago.equals("VVC")) {
								detEntity.setEstadoPago(1);
								detEntity.setCodigoFormaPago(formaPago);
								logger.info("Pagado");
							}
							else {
								detEntity.setEstadoPago(3);
								detEntity.setCodigoFormaPago("VVC");
								detEntity.setDescripcionFormaPago(formasPagoBCI.get(  detEntity.getCodigoFormaPago() ));
								logger.info("Pendiente de Pago");
								//Si estado del pago es rechazado se invoca rutina para revocar mandato si corresponde

								try {
									int resultado_revocar= mandatoService.revocarMandato("24HRS", det.substring(135).trim(), rutcliente, Integer.parseInt(detEntity.getCodigoBanco().trim()), detEntity.getNumerocuenta());
									logger.info("Resultado revocar mandato (1-OK):" + resultado_revocar);
								} catch (Exception e) {
									logger.warn("Error al invocar revocarMandato");
									e.printStackTrace();
								}
							}
							detEntity.setDescripcionEstadoPago(descripcionPago);
							detEntity.setDescripcionRechazo("");

							String fechaCambio = det.substring(122, 130);
							int fechaDia = Integer.parseInt(fechaCambio.substring(0, 2));
							int fechaMes = Integer.parseInt(fechaCambio.substring(2, 4));
							int fechaYear = Integer.parseInt(fechaCambio.substring(4, 8));

							GregorianCalendar gc = new GregorianCalendar();
							gc.set(fechaYear, fechaMes - 1, fechaDia);
							detEntity.setFechaCambio(gc.getTime());
							detEntity.setCodigoNomina(codigoNomina);
							
							logger.debug("actualizando item ->"+detEntity.toString());
							detalleService.saveOrupdate(detEntity);
							FileManagmentUtils.putLineFromFileOpened(trackingFile, " detalle modificado "+ detEntity.toString());
							
							/*DetalleTefDao detalleDao = new DetalleTefDaoImpl();

							boolean result = detalleDao.actualizarDetalleRendicion(detEntity);
							if(result) {
								FileManagmentUtils.putLineFromFileOpened(trackingFile, " detalle modificado "+ detEntity.toString());
							}*/
						} catch (Exception e) {
							logger.fatal(e);
							FileManagmentUtils.putLineFromFileOpened(trackingFile,"Problemas Actualizando Detalle TEF Rut ["+rutcliente+"]");
							throw new MiException(new ErrorMessage(4, "Problemas Actualizando Detalle TEF Rut ["+rutcliente+"]"));
						}
					}
					
					try {
						cabeceraService.updateTotalesNomina(idCabecera, codigoNomina);
					} catch (Exception e) {
						logger.error("Error actualizando estado y totales cabecera " + idCabecera);
						FileManagmentUtils.putLineFromFileOpened(trackingFile,"Cabecera error al actualizar estado y totales nómina id nómina -> ["+cabecera+"]");
						throw new MiException(new ErrorMessage(6, "Cabecera error al actualizar estado y totales nómina id nómina  -> ["+cabecera+"]") );
					}
				
				}
				else {
					
					FileManagmentUtils.putLineFromFileOpened(trackingFile,"Cabecera encontrada, pero no cumple la condicon (idCodConv in(7,8) && estadoNomina != 5 -> ["+cabecera+"]");
					throw new MiException(new ErrorMessage(6, "Cabecera encontrada, pero no cumple la condicon (idCodConv in(7,8) && estadoNomina != 5 -> ["+cabecera+"]") );
				}
				
			}
				
			FileManagmentUtils.closeFileToRead(br);
			logger.debug("Data Leida, archivo cerrado ");
			FileManagmentUtils.putLineFromFileOpened(trackingFile,"Data Leida, archivo cerrado ");

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
				FileManagmentUtils.putLineFromFileOpened(trackingFile,"Problemas al llevar el archivo al histórico ["+file.getName()+"]");
				throw new MiException(new ErrorMessage(7, "Problemas al llevar el archivo al histórico ["+file.getName()+"]"));
			
			}
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "llevando archivo a historico resultado ="+resultadoPut);
			logger.debug("llevando archivo a historico resultado ="+resultadoPut);
			
			ProductoDao productoDAO= new ProductoDaoImpl();
			HashMap<String, Object> producto = null;
			try {
				producto= productoDAO.consultaProducto(idCodConv, codProducto);
			} catch (Exception e2) {
				logger.fatal(e2);
				FileManagmentUtils.putLineFromFileOpened(trackingFile,"Problemas DAO Producto al llevar el archivo a notificación ["+file.getName()+"]");
				throw new MiException(new ErrorMessage(7, "Problemas DAO Producto al llevar el archivo a notificación ["+file.getName()+"]"));
			}
			FTPConfigurationVO configNotificacion = new FTPConfigurationVO();
			configNotificacion.setHost(producto.get("FtpServidor").toString());
			configNotificacion.setPort(21);
			configNotificacion.setUser(producto.get("FtpUsuario").toString());
			configNotificacion.setPass(producto.get("FtpClave").toString());
			configNotificacion.setPathToPut(producto.get("FtpNotificacion").toString());
	

			logger.debug("llevando archivo a Notificacion ...");
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "llevando archivo a Notificacion ...");
			resultadoPut = 0;
			try {
				resultadoPut = ftpServicePluss.putFileToFTP(configNotificacion, file, file.getName(), true);
			} catch (FTPErrors e) {
				logger.fatal(e);
				FileManagmentUtils.putLineFromFileOpened(trackingFile,"Problemas al llevar el archivo al Notificacion ["+file.getName()+"]");
				throw new MiException(new ErrorMessage(7, "Problemas al llevar el archivo a Notificación ["+file.getName()+"]"));
			
			}
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "llevando archivo a Notificación resultado ="+resultadoPut);
			logger.debug("llevando archivo a Notificación resultado ="+resultadoPut);
			
			
			logger.debug("Eliminando archivo en ERP-Connection...");
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "Eliminando archivo en ERP-Connection...");
			int resultDelete = 0;
			try {
				resultDelete = ftpServicePluss.deleteFileInFTP(config, config.getPathToGet(), fileNameFTP);
			} catch (FTPErrors e1) {
				logger.fatal(e1);
				FileManagmentUtils.putLineFromFileOpened(trackingFile,"Problemas al eliminar archivo en ERP-Connection ["+fileNameFTP+"]");
				throw new MiException(new ErrorMessage(8, "Problemas al eliminar archivo en ERP-Connection ["+fileNameFTP+"]"));
			}
			logger.debug("Eliminando archivo en ERP-Connection resultado->"+resultDelete);
			
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "Eliminando archivo en ERP-Connection resultado->"+resultDelete);
			
			estadoSalida = 1;
		}
		else{

			FileManagmentUtils.putLineFromFileOpened(trackingFile,"problemas leyendo el archivo, o bien está vacío");
			logger.debug("problemas leyendo el archivo, o bien está vacío");
			
		}
		
		
		//Paso3 - llevar nómina leida a historico.
		

		logger.debug("Terminando proceso de Rendicione para Nominas 24H, con estado ["+estadoSalida+"]");
		logger.debug("Cerrando tracking file...");
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"Terminando proceso de Rendicione para Nominas 24H, con estado ["+estadoSalida+"]");
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"Cerrando tracking file...");
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
					nomina.put("type","24H");
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
