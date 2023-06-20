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
import java.util.Iterator;
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
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ConvenioDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ConvenioDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.DetalleTefDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.DetalleTefDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ProductoDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ProductoDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;
import cl.laaraucana.rendicionpagonomina.utils.FileManagmentUtils;
import cl.laaraucana.rendicionpagonomina.vo.FTPConfigurationVO;

@Service
public class ProcesaRendicionNominasPNOLServiceImpl implements ProcesaRendicionNominasPNOLService {

	private static final Logger logger = Logger.getLogger(ProcesaRendicionNominasPNOLServiceImpl.class);
	
	@Autowired
	FTPServicePluss ftpServicePluss;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	MandatoAS400Service mandatoService;
	
	@Autowired
	BeneficioService beneficioService;
	
	@Autowired
	CabeceraService cabeceraService;
	
	@Autowired
	DetalleService detalleService;
	
	@Autowired
	CabeceraManualService cabeceraManualService;
	
	@Autowired
	DetalleManualService detalleManualService;
	
	@Value("${ftp.config.erp.conection.host}")
	private String FTP_ERP_HOST;
	
	@Value("${ftp.config.erp.conection.port}")
	private int FTP_ERP_PORT;
	
	@Value("${ftp.config.erp.conection.user}")
	private String FTP_ERP_USER;
	
	@Value("${ftp.config.erp.conection.pass}")
	private String FTP_ERP_PASS;
	
	@Value("${ftp.config.erp.conection.input.pnol.path}")
	private String FTP_ERP_GET_PATH;
	
	@Value("${ftp.config.erp.conection.input.pnol.name}")
	private String NAME_LOGIC;

	@Value("${path.rendicion.bci.folder}")
	private String RENDICION_PATH;

	SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddhhmm");
	
	@Value("${ftp.config.historico.rendicion.pnol.path}")
	private String PATH_HISTORICO;

	
	@Override
	public int loadData() throws MiException {
		logger.debug("buscando archivos rendicino PNOL...");
		FTPConfigurationVO config = new FTPConfigurationVO();
		config.setHost(FTP_ERP_HOST);
		config.setPort(FTP_ERP_PORT);
		config.setUser(FTP_ERP_USER);
		config.setPass(FTP_ERP_PASS);
		config.setPathToGet(FTP_ERP_GET_PATH);
		logger.debug("buscando archivos para procesar PNOL en ERP-Connection...");
		try {
			//return loadDataFromFile("RENDICION_PNOL_RENDARAU_RENDPNOL_15092021 _14463859");
			String[] archivos = ftpServicePluss.searchFilesFromFTP(config, NAME_LOGIC);
			if(archivos != null && archivos.length > 0) {
				for (String archivo : archivos) {
					try {
						logger.debug("Archivo encontrado ["+archivo+"]");	
						loadDataFromFile(archivo);
					} catch (Exception e) {
						logger.fatal("Error al procesar el Archivo ["+archivo+"], mensaje:" + e);
						//e.printStackTrace();
					}
				}
				return 1;
			}
			
			else {
				throw new MiException(new ErrorMessage(0, "No se encontraron archivos"));
			}
		} catch (Exception e) {
			logger.fatal(e);
		}
		
		return 0;
	}

	@Override
	public int loadDataFromFile(String fileNameFTP) throws MiException {
		int estadoSalida = 0;
		logger.debug("Iniciando el proceso de Rendicion para Nóminas PNOL...");
		
		//Paso1 - Traer Nómina desde FTP BCI
		logger.debug("Iniciando Proceso RendicionPNOL, loadData()");
		
		PrintWriter trackingFile = FileManagmentUtils.getOpenedFileToWrite(RENDICION_PATH + fileNameFTP +"_"+ formato.format(new Date()));
		
		FileManagmentUtils.putLineFromFileOpened(trackingFile, "Iniciando Proceso Rendicion para Nóminas PNOL :"+ new Date());
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"Obteniendo Información...");
		CommonLicenciaDao commonLicenciaDao = new CommonLicenciaDaoImpl();
		
		HashMap<String, String> descripcionRechazoBCI = new HashMap<String, String>();		
		try {
			descripcionRechazoBCI = commonLicenciaDao.getDescripcionRechazo();
		} catch (Exception e1) {
			logger.fatal(e1);
			FileManagmentUtils.putLineFromFileOpened(trackingFile,"Error Obteniendo formas de pago");
			throw new MiException(new ErrorMessage(2, "Error Obteniendo formas de pago"));
		}
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"descripcionRechazo["+descripcionRechazoBCI+"]");
		logger.debug("descripcionRechazoBCI ->"+ descripcionRechazoBCI);
	
		FTPConfigurationVO config = new FTPConfigurationVO();
		
		config.setHost(FTP_ERP_HOST);
		config.setPort(FTP_ERP_PORT);
		config.setUser(FTP_ERP_USER);
		config.setPass(FTP_ERP_PASS);
		config.setPathToGet(FTP_ERP_GET_PATH);

		logger.debug("buscando archivo PNOL " + fileNameFTP + " en " + FTP_ERP_HOST + ", ruta: " + FTP_ERP_GET_PATH);
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"buscando archivo PNOL...");
		
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
		
		logger.debug("archivo obtenido : "+ file);
		if(file != null && file.length()>0) {
		

			logger.debug("leyendo data archivo.. : "+ file);
			FileManagmentUtils.putLineFromFileOpened(trackingFile,"leyendo data archivo.. : "+ file);
			BufferedReader br = FileManagmentUtils.getOpenedFileToRead(file.getAbsolutePath(), true, null);

			String header = FileManagmentUtils.getLineFromFileOpened(br);
			
			HashMap<String, Object> cabecera = null;
			CabeceraTefDao cabeceraTefDao = new CabeceraTefDaoImpl();
			try {
				cabecera = cabeceraTefDao.getIdCabeceraPorNombreArchivo(header.trim());
			} catch (Exception e) {
				logger.fatal("No se pudo obtener Cabecera por nombre de archivo");
				FileManagmentUtils.putLineFromFileOpened(trackingFile,"Problemas al Obtener el idCabecera para el nombre archivo ["+header.trim()+"]");
				throw new MiException(new ErrorMessage(4, "Problemas al Obtener el idCabecera para el nombre archivo ["+header.trim()+"]"));
			}
			int idCodConv=0;
			String codProducto="";
			logger.debug("cabecera encontrado -> : "+ cabecera);
			FileManagmentUtils.putLineFromFileOpened(trackingFile,"cabecera encontrado -> : "+ cabecera);
			
			ProductoDao productoDAO= new ProductoDaoImpl();
			HashMap<String, Object> producto = null;
			
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
				FileManagmentUtils.putLineFromFileOpened(trackingFile,"Problemas al Obtener el idCabecera para el nombre archivo ["+header.trim()+"]");
				throw new MiException(new ErrorMessage(5, "Problemas al Obtener el idCabecera para el nombre archivo ["+header.trim()+"]"));
			}
			else {
				idCodConv = new Integer( cabecera.get("ID_COD_CONV").toString() ).intValue();
				codProducto= cabecera.get("CODIGO_PRODUCTO").toString();
				int estadoNomina = ((BigDecimal)cabecera.get("ESTADO_NOMINA")).intValue();
				Long idCabecera = ((BigDecimal)cabecera.get("ID_CABECERA")).longValue();
				
				//Se busca datos de tabla Producto
				try {
					producto= productoDAO.consultaProducto(idCodConv, codProducto);
				} catch (Exception e2) {
					logger.fatal(e2);
					FileManagmentUtils.putLineFromFileOpened(trackingFile,"Problemas DAO Producto al llevar el archivo a notificación ["+file.getName()+"]");
					throw new MiException(new ErrorMessage(7, "Problemas DAO Producto al llevar el archivo a notificación ["+file.getName()+"]"));
				}
				
				//Condicion para ejecutar
				if( estadoNomina != 5  ) {

					ArrayList<String> detalle = new ArrayList<String>();
					String registro = FileManagmentUtils.getLineFromFileOpened(br);
					int j=1;
					while(registro != null) {
						if(j>1 && registro.length()>1){
							detalle.add(registro);
						}
						registro = FileManagmentUtils.getLineFromFileOpened(br);
						j++;
					}

					logger.debug("detalles encontrados -> : "+ detalle.size());
					FileManagmentUtils.putLineFromFileOpened(trackingFile,"detalles encontrados -> : "+ detalle.size());
					//String folio_nomina="";
					List<Long> cabecerasManual= new ArrayList<Long>();
					long codigoNomina=0;
					for (String det : detalle) {
						String[]columnas= det.split(";");
						int rutcliente= Integer.parseInt(columnas[2].split("-")[0]);
						try {
							codigoNomina=  Long.parseLong(columnas[1].trim());
							
							//Se busca detalle trabajador
							HashMap<String, Long> params= new HashMap<String, Long>();
							params.put("idCabecera", idCabecera);
							params.put("rutAfiliado", new Long(rutcliente));
							params.put("referencia1", new Long(columnas[10])); //referencia1
							params.put("referencia2", new Long(columnas[11])); //referencia2
							params.put("monto", new Long(columnas[4])); //monto
							
							DetalleEntity detEntity = detalleService.findByRut(params);
							if(detEntity!=null){								
								String estadoPago="4"; //rechazado
								String estadoPago_beneficios="";
								String descripcionPago="";
								String descripcionRechazo="";
								String codigoRechazo="";
								
								//Se setean los campos del registro que vienen informado por columnas en nómina
								//(0)Fecha de Pago;(1)Nro Folio;(2)RUT Beneficiario;(3)Nombre Beneficiario;(4)Monto $;(5)Cuenta Destino;(6)Banco Destino;(7)Estado Nomina;(8)Mensaje al Beneficiario;(9)Estado de Pago;(10)Boleta/Factura;(11)Orden de Compra;(12)Ingreso;Firmas
								if(columnas[7].equals("PRO") && (columnas[9].equals("PRO") || columnas[9].equals("PAG"))){
									estadoPago="1"; //Pagado
									estadoPago_beneficios="4";
									if( columnas[9].equals("PRO")){
										descripcionPago="PRO – Realizada";
									}else if( columnas[9].equals("PAG")){
										descripcionPago="PAG – Realizada";
									}
								}else{
									codigoRechazo= columnas[9];
									descripcionRechazo= columnas[9] + "-" + descripcionRechazoBCI.get( columnas[9] );
									estadoPago_beneficios="3";
									//Si estado del pago es rechazado se invoca rutina para revocar mandato si corresponde
									try {
										//se revoca mandato, columnas[9] corresponde al código de rechazo
										int resultado_revocar= mandatoService.revocarMandato("PNOL", columnas[9], rutcliente, Integer.parseInt(detEntity.getCodigoBanco().trim()), detEntity.getNumerocuenta());

										logger.info("Resultado revocar mandato (1-OK):" + resultado_revocar);
									} catch (Exception e) {
										logger.warn("Error al invocar revocarMandato, mensaje " + e.getMessage());
										e.printStackTrace();
									}						

								}
								detEntity.setEstadoPago(Integer.parseInt(estadoPago));
								detEntity.setDescripcionEstadoPago(descripcionPago);
								detEntity.setDescripcionRechazo(descripcionRechazo);
								String fechaCambio = columnas[0];
								int fechaDia = Integer.parseInt(fechaCambio.substring(0, 2));
								int fechaMes = Integer.parseInt(fechaCambio.substring(3, 5));
								int fechaYear = Integer.parseInt(fechaCambio.substring(6, 10));

								GregorianCalendar gc = new GregorianCalendar();
								gc.set(fechaYear, fechaMes - 1, fechaDia);

								detEntity.setFechaCambio(gc.getTime());
								detEntity.setCodigoNomina(Long.parseLong(columnas[1].trim()));

								String referencia1= columnas[10].trim();
								String referencia2= columnas[11].trim();

								//ACTUALIZA SISTEMA ORIGEN Carga Manual y Beneficios
								//Si convenio es 1 (Beneficios) y UrlNotificación de tabla Producto es "AS400" se actualiza estado pago en Beneficios
								if(idCodConv==1 && producto.get("URLNotific").toString().equals("AS400")){
									HashMap params_ben= new HashMap();
									params_ben.put("estado", estadoPago_beneficios);
									params_ben.put("nomnomina", fileNameFTP);
									params_ben.put("folnomina", String.valueOf(codigoNomina));
									params_ben.put("idpago", referencia1);
									params_ben.put("fechapago", gc.getTime());
									if(estadoPago_beneficios.equals("3")){
										params_ben.put("codrechazo", codigoRechazo);
										params_ben.put("desrechazo", descripcionRechazo.split("-")[1]);
									}
									try {
										beneficioService.updateBeneficiarioById(params_ben);
									} catch (Exception e) {
										logger.warn("Error al actualizar en beneficios el ESTADOPAGO " + estadoPago_beneficios + " para idpagotef: " + columnas[10].trim());
										e.printStackTrace();
									}
								}else if(producto.get("CargManual").toString().equals("S") && producto.get("URLNotific").toString().equals("MANUAL")){
									HashMap params_man= new HashMap();
									params_man.put("estado", estadoPago);
									params_man.put("idDetalle", referencia2);
									params_man.put("fechatrans", detEntity.getFechaCambio());
									try {
										detalleManualService.updateEstadoDetalleManual(params_man);
										Long idcabe_m= detalleManualService.getIdCabeceraByIdDetalle(Long.parseLong(columnas[11].trim()));
										if(!cabecerasManual.contains(idcabe_m)){
											cabecerasManual.add(idcabe_m);
										}
									} catch (Exception e) {
										logger.warn("Error al actualizar en DetalleManula el ESTADOPAGO " + estadoPago + " para iderefencia: " + columnas[11].trim());
										e.printStackTrace();
									}

								}


								logger.debug("actualizando item ->"+detEntity.getIdDetalle());
								
								detalleService.saveOrupdate(detEntity);
								FileManagmentUtils.putLineFromFileOpened(trackingFile, " detalle modificado "+ detEntity.toString());
								
								/*DetalleTefDao detalleDao = new DetalleTefDaoImpl();
								boolean result = detalleDao.actualizarDetalleRendicion(detEntity);
								if(result) {
									FileManagmentUtils.putLineFromFileOpened(trackingFile, " detalle modificado "+ detEntity.toString());
								}*/
							}
						} catch (Exception e1) {
							logger.warn("Error procesando trabajador " + rutcliente);
							e1.printStackTrace();
						}
					}
					
					//actualizar estado y totales cabecera
					try {
						cabeceraService.updateTotalesNomina(idCabecera, codigoNomina);
					} catch (Exception e) {
						logger.error("Error actualizando estado y totales cabecera " + idCabecera);
						FileManagmentUtils.putLineFromFileOpened(trackingFile,"Cabecera error al actualizar estado y totales nómina id nómina -> ["+cabecera+"]");
						throw new MiException(new ErrorMessage(6, "Cabecera error al actualizar estado y totales nómina id nómina  -> ["+cabecera+"]") );
					}
					//Si cabecerasManual >0 se actualiza todas las cebeceras asociadasa los detalles procesados
					if(cabecerasManual.size()>0){
						for (Iterator iterator = cabecerasManual.iterator(); iterator
								.hasNext();) {
							Long idCabeceraManual = (Long) iterator.next();
							try {
								cabeceraManualService.updateTotalesNomina(idCabeceraManual);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
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
			
			
			if(producto.get("CargManual").toString().equals("N") && (producto.get("FtpNotificacion")!=null || !producto.get("FtpNotificacion").equals(""))){
				FTPConfigurationVO configNotificacion = new FTPConfigurationVO();
				configNotificacion.setHost(producto.get("FtpServidor").toString());
				configNotificacion.setPort(21);
				configNotificacion.setUser(producto.get("FtpUsuario").toString());
				configNotificacion.setPass(producto.get("FtpClave").toString());
				configNotificacion.setPathToPut(producto.get("FtpNotificacion").toString());


				logger.info("llevando archivo a Notificacion ...");
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
				logger.debug("Archivo  Notificado resultado ="+resultadoPut);
			}
			
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
		

		logger.debug("Terminando proceso de Rendicione para Nomina PNOL " + fileNameFTP + ", con estado ["+estadoSalida+"]");
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"Terminando proceso de Rendicione para Nominas PNOL, con estado ["+estadoSalida+"]");
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
					nomina.put("type","PNOL");
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
