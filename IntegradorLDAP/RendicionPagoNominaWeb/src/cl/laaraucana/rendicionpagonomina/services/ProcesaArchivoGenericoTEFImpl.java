
package cl.laaraucana.rendicionpagonomina.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ProductoDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ProductoDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BancoEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.utils.CargaMasivaUtil;
import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;
import cl.laaraucana.rendicionpagonomina.utils.Estados;
import cl.laaraucana.rendicionpagonomina.utils.FileManagmentUtils;
import cl.laaraucana.rendicionpagonomina.utils.Utils;
import cl.laaraucana.rendicionpagonomina.vo.Detalle24HVO;
import cl.laaraucana.rendicionpagonomina.vo.EnvioNominaRespuestaVo;
import cl.laaraucana.rendicionpagonomina.vo.EnvioNominaVo;
import cl.laaraucana.rendicionpagonomina.vo.FTPConfigurationVO;

@Service
public class ProcesaArchivoGenericoTEFImpl implements ProcesaArchivoGenericoTEF{


	private static final Logger logger = Logger.getLogger(ProcesaArchivoGenericoTEFImpl.class);

	@Autowired
	FTPServicePluss ftpServicePluss;
	
	@Autowired
	private CabeceraService cabeceraService;
	
	@Autowired
	private DetalleService detalleService;
	
	@Autowired
	private ProcessService procesService;
	
	@Autowired
	private WsService wsservice;
	
	@Autowired
	private BancoService bancoService;
	
	private ProductoDao productoDAO= new ProductoDaoImpl();
	
	private ConvenioDao convenioDAO= new ConvenioDaoImpl();
	
	@Value("${ftp.config.erp.conection.host}")
	private String FTP_ERP_HOST;
	
	@Value("${ftp.config.erp.conection.port}")
	private int FTP_ERP_PORT;
	
	@Value("${ftp.config.erp.conection.user}")
	private String FTP_ERP_USER;
	
	@Value("${ftp.config.erp.conection.pass}")
	private String FTP_ERP_PASS;
	
	@Value("${path.tranking.folder}")
	private String TRACKING_PATH;
	
	/*@Value("${ftp.config.historico.host}")
	private String FTP_HISTORICO_HOST;
	
	@Value("${ftp.config.historico.port}")
	private int FTP_HISTORICO_PORT;
	
	@Value("${ftp.config.historico.user}")
	private String FTP_HISTORICO_USER;
	
	@Value("${ftp.config.historico.pass}")
	private String FTP_HISTORICO_PASS;
	
	@Value("${ftp.config.historico.carga.path}")
	private String PATH_HISTORICO;
	*/
	SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddhhmm");

	@Autowired
	MailService mailService;

	HashMap<String, Object> producto =null;

	FTPConfigurationVO config = new FTPConfigurationVO();
	
	public void initSched() throws MiException {
		logger.debug("init sched curativas...");
		loadData();
	}
	
	
	@Override
	public synchronized int loadData() throws MiException {
		logger.debug("buscando archivos desde producto...");

		String CARGA_MANUAL = "N";
		List<HashMap<String, Object>> productos = null;
		int total_procesados=0;
		try {
			productos = productoDAO.consultaProductosByConvenioAndCargaManual(null,CARGA_MANUAL);
			if(productos != null && productos.size()>0) {
				for (int i=0; i<productos.size(); i++) {
					producto = productos.get(i);
					logger.debug("getProducto encontrado ->"+ producto);
					try {
						if(producto.get("FormatoNomina") != null) {
							config.setHost(producto.get("FtpServidor").toString());
							config.setPort(21);
							config.setUser(producto.get("FtpUsuario").toString());
							config.setPass(producto.get("FtpClave").toString());
							config.setPathToGet(producto.get("FtpCarpeta").toString());
							
							logger.debug("buscando archivos para procesar ...");
							try {
								String[] archivos = ftpServicePluss.searchFilesFromFTP(config, "regex:^*");
								ConvenioEntity convenio = convenioDAO.getConvenio(Integer.parseInt( producto.get("IdCodConv").toString() ) );
								if(archivos != null && archivos.length > 0) {
									for (String archivo : archivos) {
										logger.info("Archivo encontrado ["+archivo+"]");
										total_procesados++;
										//Busncado en BD si ya fue procesada la nómina
										CabeceraTefDao cabeceraTefDao = new CabeceraTefDaoImpl();
										HashMap<String, Object> cabecera = cabeceraTefDao.getIdCabeceraPorNombreArchivo(archivo.trim());
										
										if(cabecera==null || cabecera.isEmpty()){
											HashMap<String, String> salida= loadDataFromFile(archivo, convenio);

											if(convenio.getEmailEjecutivo() != null && convenio.getEmailEjecutivo().length()>5) {
												logger.debug("Enviando Mail a ejecutivo ["+convenio.getEmailEjecutivo()+"]");
												String body= Configuraciones.getConfig("envio.mail.body");
												body= body.replaceAll("#nombre_nomina#", archivo);
												body= body.replaceAll("#producto#", producto.get("DescProd").toString());
												body= body.replaceAll("#monto#", salida.get("monto"));
												body= body.replaceAll("#cantidad#", salida.get("cantidad"));
												String subject= Configuraciones.getConfig("envio.mail.subject");
												mailService.sendEmail(convenio.getEmailEjecutivo(), subject, body);

											}
											else {
												logger.debug("No existe mail configurado para el convenio ["+convenio.getDescripcionConvenio()+"]");
											}
										}else{
											logger.warn("Nómina duplicada, no se procesa");
										}
									}
								}
								else {
									logger.debug("No se encontraron archivos");
								}
							} catch (FTPErrors e) {
								logger.warn("No se encontraron archivos, mensaje: " + e.getMessage());
								logger.fatal(e);
							}
						}
						else {
							logger.debug("Producto Sin formato, no se procesa :["+producto+"]");
						}
						
					}catch (Exception e1) {
						logger.fatal(e1);
						e1.printStackTrace();
						logger.debug("Problemas procesando Producto :["+producto+"]");
					}
				}
			}
			else {
				throw new MiException(new ErrorMessage(0, "Problemas al obtener productos "));
			}
		} catch (Exception e1) {
			logger.fatal(e1);
			throw new MiException(new ErrorMessage(0, "Problemas al obtener informacion de Producto :["+producto+"]"));
		}
		
		return total_procesados;
	}


	@Override
	public synchronized HashMap<String, String> loadDataFromFile(String fileNameFTP, ConvenioEntity convenio) throws MiException {
		//int estadoSalida = 0;
		HashMap<String, String> salida= new HashMap<String, String>();
		logger.debug("procesando archivo ["+producto.get("CodProd")+","+producto.get("IdCodConv")+","+producto.get("codBbanco")+"]: "+fileNameFTP);
		
		String banco= convenio.getCodigoBanco();
		String path= TRACKING_PATH.replaceAll("#banco#", banco);
		logger.debug("Iniciando Proceso archivo Generico Tef, loadDataFromFile()");
		PrintWriter trackingFile = FileManagmentUtils.getOpenedFileToWrite(path + fileNameFTP +"_"+ formato.format(new Date()));
		
		FileManagmentUtils.putLineFromFileOpened(trackingFile, "Iniciando Proceso Archivo Generico TEF :"+ new Date());
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"Obteniendo Información...");
		CommonLicenciaDao commonLicenciaDao = new CommonLicenciaDaoImpl();
		Map<String, BancoEntity> bancos = new HashMap<String, BancoEntity>();
		HashMap<String, String> formasPago = new HashMap<String, String>();
		try {
			bancos = bancoService.getMapBancos();
		} catch (Exception e1) {
			logger.fatal(e1);
			FileManagmentUtils.putLineFromFileOpened(trackingFile,"Error Obteniendo bancos");
			throw new MiException(new ErrorMessage(1, "Error Obteniendo bancos"));
		}
		
		try {
			formasPago = commonLicenciaDao.getFormasPago();
		} catch (Exception e1) {
			logger.fatal(e1);
			FileManagmentUtils.putLineFromFileOpened(trackingFile,"Error Obteniendo formas de pago");
			throw new MiException(new ErrorMessage(2, "Error Obteniendo formas de pago"));
		}
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"Informacion bancos bancos["+bancos+"], formasPago["+formasPago+"]");
		logger.debug("bancos ->"+ bancos);
		logger.debug("formasPago ->"+ formasPago);
		
	

		logger.debug("buscando archivo...");
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"buscando archivo Generico TEF ["+fileNameFTP+"] ...");
		
		String nombreArchivoOriginal = fileNameFTP;
		File file = null;
		try {
			file = ftpServicePluss.getFileFromFTP(config, nombreArchivoOriginal);
		} catch (FTPErrors e1) {
			logger.fatal(e1);
			FileManagmentUtils.putLineFromFileOpened(trackingFile,"Problemas al Obtener el archivo Generico TEF ["+fileNameFTP+"]");
			throw new MiException(new ErrorMessage(3, "Problemas al Obtener el archivo Generico TEF ["+fileNameFTP+"]"));
		}
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"muevo el archivo a la carpeta en proceso...");
		logger.debug("muevo el archivo a la carpeta en proceso...");
		config.setPathToPut(producto.get("FtpCarpeta")+"/Procesados_tmp/");
		int resultMove = -1;
		try {
			resultMove = ftpServicePluss.moveFileInFTP(config, file.getName().split("__")[0], file.getName().split("__")[0], true);
		} catch (FTPErrors e1) {
			logger.fatal(e1);
			FileManagmentUtils.putLineFromFileOpened(trackingFile,"Problemas moviendo el archivo a Procesados_tmp");
			throw new MiException(new ErrorMessage(4, "Problemas moviendo el archivo a Procesados_tmp"));
		}
		logger.debug("resultado de move file "+ resultMove);
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"resultado de move file "+ resultMove);
		logger.debug("archivo obtenido : "+ file);
		FileManagmentUtils.putLineFromFileOpened(trackingFile,"archivo obtenido : "+ file);
		if(file != null && file.length()>0) {
		

			ArrayList<Object> detalle =  CargaMasivaUtil.getInstance().getDetallesFromFile(producto.get("FormatoNomina").toString().toString(), file.getAbsolutePath());

			logger.debug("abriendo archivo : "+ file);
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "abriendo archivo : "+ file);
			
			SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddhhmm");

			CabeceraEntity cabecera = new CabeceraEntity();
			cabecera.setConvenio(producto.get("IdCodConv").toString());
			cabecera.setProducto(producto.get("CodProd").toString());
			cabecera.setFechaEnvio(new Date());
			cabecera.setNombreNomina(nombreArchivoOriginal);
			cabecera.setCodigoNomina(new Long(formato.format(new Date())));
			
			long montoTotal = 0;
			for (Object det : detalle) {
				montoTotal = montoTotal + (( DetalleEntity )det).getMonto();
			}
			salida.put("monto", Utils.formatoMiles(montoTotal));
			salida.put("cantidad", Utils.formatoMiles(detalle.size()));
			
			cabecera.setMonto(montoTotal);
			cabecera.setCantidad(detalle.size());
			cabecera.setEstadoNomina(1);
			cabecera.setTotalPagado(0);
			cabecera.setCantidadPagado(0);
			cabecera.setTotalRechazado(0);
			cabecera.setCantidadRechazado(0);
			cabecera.setTotalDevuelto(0);
			cabecera.setCantidadDevuelto(0);
			cabecera.setCodigoRechazoEnvio(0);
			cabecera.setGlosaRechazoEnvio("");
			cabecera.setCodigoRechazoRendicion(0);
			cabecera.setGlosaRechazoRendicion("");
			cabecera.setFechaRendicion(new Date());
			byte[] fileInBytes = null;
			try {
				fileInBytes = FileUtils.readFileToByteArray(file);
			}
			catch(Exception e) {
				logger.fatal(e);
				FileManagmentUtils.putLineFromFileOpened(trackingFile, "Problemas obteniendo el getBytes() del archivo ");
				throw new MiException(new ErrorMessage(5, "Problemas obteniendo el getBytes() del archivo "));
			}
			cabecera.setPlano(fileInBytes);
			cabecera.setCrc(null);
			cabecera.setFechaCreacion(new Date());
			
			
			
			CabeceraEntity cabeceraTEF = null;
			try {
				cabeceraTEF = cabeceraService.save(cabecera);
				logger.debug("IdCabecera -> "+ cabeceraTEF.getIdCabecera());
				FileManagmentUtils.putLineFromFileOpened(trackingFile, "IdCabecera -> "+ cabeceraTEF.getIdCabecera());
			} catch (Exception e) {
				logger.fatal(e);
				FileManagmentUtils.putLineFromFileOpened(trackingFile, "Problemas Insertando cabecera TEF "+e.getMessage());
				throw new MiException(new ErrorMessage(6, "Problemas Insertando cabecera TEF "));
			}
			
			
			for (Object d1 : detalle) {
				DetalleEntity det = (DetalleEntity) d1;
				det.setIdCabecera(cabeceraTEF.getIdCabecera());
				det.setDescripcionPago(producto.get("DescProd").toString());
				String descripcionBanco = "";
				if(bancos!=null) {
					if(convenio.getCodigoBanco().equals("BCI"))
						descripcionBanco = bancos.get(  Integer.parseInt( det.getCodigoBanco() )+"" ).getDescripcionBCI();
					else if(convenio.getCodigoBanco().equals("BES"))
						descripcionBanco = bancos.get(  Integer.parseInt( det.getCodigoBanco() )+"" ).getDescripcionBES();
				}
				det.setDescripcionBanco( descripcionBanco);
				String descripcionFormaPago = "";
				if(formasPago!=null) {
					descripcionFormaPago = formasPago.get( producto.get("codBbanco")+"_"+ det.getCodigoFormaPago() +"" );
				}
				det.setDescripcionFormaPago(descripcionFormaPago);
				logger.debug("detalle ->"+ det.toString());
				try {
					detalleService.save(det);
				} catch (Exception e) {
					logger.fatal(e);
					FileManagmentUtils.putLineFromFileOpened(trackingFile, "Problemas Insertando Detalle TEF Rut ["+det.getRutAfiliado()+"] - "+ e.getMessage());
				}
			}
			
			
			
			
			logger.debug("moviendo archivo a procesados ok...");
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "moviendo archivo a procesados ok...");
			config.setPathToGet( config.getPathToPut() );
			config.setPathToPut(producto.get("FtpCarpeta")+"/Procesados_ok/");
			try {
				resultMove = ftpServicePluss.moveFileInFTP(config, file.getName().split("__")[0], file.getName().split("__")[0], true);
			} catch (FTPErrors e) {
				logger.fatal(e);
				FileManagmentUtils.putLineFromFileOpened(trackingFile, "Problemas moviendo el archivo a Procesados_ok");
				throw new MiException(new ErrorMessage(7, "Problemas moviendo el archivo a Procesados_ok"));
			}
			logger.debug("resultado de move file "+ resultMove);
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "resultado de move file "+ resultMove);
			logger.debug("moviendo archivo a procesados ok!");
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "moviendo archivo a procesados ok!");
			
/* clillo 31/03/2023 se elimina respaldo en histórico
			int resultPut = 0;
			
			FTPConfigurationVO configHistorico = new FTPConfigurationVO();
			
			configHistorico.setHost(FTP_HISTORICO_HOST);
			configHistorico.setPort(FTP_HISTORICO_PORT);
			configHistorico.setUser(FTP_HISTORICO_USER);
			configHistorico.setPass(FTP_HISTORICO_PASS);
			configHistorico.setPathToPut(PATH_HISTORICO);
			try {
				logger.debug("Enviando archivo ["+fileNameFTP+"] a histórico...");
				FileManagmentUtils.putLineFromFileOpened(trackingFile, "Enviando archivo ["+fileNameFTP+"] a histórico...");
				resultPut = ftpServicePluss.putFileToFTP(configHistorico, file, fileNameFTP, true);
				logger.debug("Resultado envío a historico ["+fileNameFTP+"] estado -> "+ resultPut);
				FileManagmentUtils.putLineFromFileOpened(trackingFile, "Resultado envío a historico ["+fileNameFTP+"] estado -> "+ resultPut);
			} catch (FTPErrors e) {
				logger.fatal(e);
				FileManagmentUtils.putLineFromFileOpened(trackingFile, "Problemas moviendo archivo ["+fileNameFTP+"] a histórico");
				throw new MiException(new ErrorMessage(9, "Problemas moviendo archivo ["+fileNameFTP+"] a histórico"));
			}
*/
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "Proceso de carga terminado para ["+fileNameFTP+"]");
			logger.debug("Proceso de carga terminado para ["+fileNameFTP+"]");
			//estadoSalida = 1;
		}
		else{

			logger.debug("moviendo archivo a procesados error ...");
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "moviendo archivo a procesados error ...");
			config.setPathToGet( config.getPathToPut() );
			config.setPathToPut(producto.get("FtpCarpeta")+"/Procesados_error/");
			try {
				resultMove = ftpServicePluss.moveFileInFTP(config, file.getName().split("__")[0], file.getName().split("__")[0], true);
			} catch (FTPErrors e) {
				logger.fatal(e);
				FileManagmentUtils.putLineFromFileOpened(trackingFile, "Problemas moviendo el archivo a Procesados_error");
				throw new MiException(new ErrorMessage(10, "Problemas moviendo el archivo a Procesados_error"));
			}
			logger.debug("resultado de move file "+ resultMove);
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "resultado de move file "+ resultMove);
			logger.debug("moviendo archivo a procesados error!");
			FileManagmentUtils.putLineFromFileOpened(trackingFile, "moviendo archivo a procesados error!");
			//estadoSalida = 2;
		}
		
		logger.debug("Cerrando tracking file...");
		FileManagmentUtils.putLineFromFileOpened(trackingFile, "Cerrando tracking file...");
		FileManagmentUtils.closeFileToWrite(trackingFile);
		logger.debug("Cerrando tracking file OK !");
		return salida;
		
	
	}


	@Override
	public ArrayList<HashMap<String, Object>> getAvailablesFiles() throws MiException {
		ArrayList<HashMap<String, Object>> files = new ArrayList<HashMap<String,Object>>();
		FTPConfigurationVO config = new FTPConfigurationVO();
		
		return files;
	}
	
	@Override
	public synchronized EnvioNominaRespuestaVo sendFiles(long idCabecera) throws Exception {
		
		FTPConfigurationVO configERP = new FTPConfigurationVO();
		
		configERP.setHost(FTP_ERP_HOST);
		configERP.setPort(FTP_ERP_PORT);
		configERP.setUser(FTP_ERP_USER);
		configERP.setPass(FTP_ERP_PASS);
		//configERP.setPathToPut(FTP_ERP_PUT_PAT);
		
		CabeceraEntity cabecera = cabeceraService.findById(idCabecera);
		
		EnvioNominaRespuestaVo respuesta_envio=new EnvioNominaRespuestaVo();
		respuesta_envio.setCodigoRetorno("0");
		respuesta_envio.setNombreNomina(cabecera.getNombreNomina());
		respuesta_envio.setGlsErrorEnvio("");
		
		if(cabecera.getEstadoNomina()== Estados.NOMINA_PENDIENTE){

			ConvenioEntity convenio = convenioDAO.getConvenio(Integer.parseInt( cabecera.getConvenio() ) );

			if(convenio.getCodigoBanco().equals("BCI")){
				logger.debug("Procesando convenio BCI");
				if(convenio != null && convenio.getPathECOutput()  != null && convenio.getPathECOutput().trim().length()>3) {
					configERP.setPathToPut(convenio.getPathECOutput());	

					logger.debug("leyendo archivo " + cabecera.getNombreNomina());
					if(cabecera != null && cabecera.getPlano().length>0) {
						InputStream inputStream = new ByteArrayInputStream(cabecera.getPlano());

						try {
							logger.info("Actualizando BD antes de enviar a ERP-Conecction");
							//Actualizando basede datos
							cabecera.setEstadoNomina(2);
							cabecera.setFechaEnvio(new Date());
							cabeceraService.update(cabecera);

							//Se setean todos los trabajadores en Pendiente de Pago
							logger.info("Se setean todos los trabajadores en Pendiente de Pago");
							detalleService.updateDetallePendientedePago(cabecera.getIdCabecera(), cabecera.getCodigoNomina());


							logger.info("Enviando archivo " + configERP.getPathToPut() + cabecera.getNombreNomina() + " a ERP-Connection... (" + FTP_ERP_HOST  +")" );
							int resultPut = ftpServicePluss.putFileToFTP(configERP, inputStream, cabecera.getNombreNomina(), true);
							logger.debug("Resultado envío a ERP-Connection : "+ resultPut);

							if(resultPut > 0) {							
								respuesta_envio.setCodigoRetorno("1");
							}else{
								respuesta_envio.setGlsErrorEnvio("Error al mover el archivo a ERP-Connection");
							}
						} catch (FTPErrors e) {
							logger.fatal(e);
							respuesta_envio.setGlsErrorEnvio(e.getMessage());
							throw new MiException(new ErrorMessage(8, "Problemas moviendo archivo a ERP-Connection"));
						}
					}
				}
				else {
					logger.debug("No existe path ERP_CONNECTION configurado para en convenio ["+convenio.getDescripcionConvenio()+"]");
				}
			}else if(convenio.getCodigoBanco().equals("BES")){
				logger.debug("Procesando convenio BES");

				if(cabecera != null && cabecera.getPlano().length>0) {

					try {
						logger.info("Enviando archivo a WS BES:" + cabecera.getNombreNomina());
						InputStream inputStream = new ByteArrayInputStream(cabecera.getPlano());

						//Se guarda archvo en carpeta
						String path = Configuraciones.getConfig("path.envio");
						path= path.replaceAll("#banco#", "BES");
						File targetFile = new File( path + cabecera.getNombreNomina());

						FileUtils.copyInputStreamToFile(inputStream, targetFile);

						//se guarda archivo en una lista para comprimir Zip
						List<String> listaFiles = new ArrayList<String>();
						listaFiles.add(path + cabecera.getNombreNomina());

						//Se comprime ZIP
						logger.info("Se comprime archivo: " + path + "files.zip");
						procesService.zipDir(listaFiles, path);

						//Se codifica archivo
						logger.info("Se codifica archivo");
						File fil = new File(path + "\\" + "files.zip");
						String encode = procesService.encodeBase64(FileUtils.readFileToByteArray(fil));

						//Se genera bean objeto para enviar a BES
						EnvioNominaVo vo = new EnvioNominaVo();
						String metodo_envio= Configuraciones.getConfig("bes.metodo.envio");
						vo.setConvenio(convenio.getConvenioBanco());
						vo.setTipoNomina("Pago");
						vo.setMetodo(metodo_envio);
						vo.setConceptoPago("0000000000");
						vo.setCantidad(String.valueOf(cabecera.getCantidad()));
						vo.setFechaPago(Utils.dateToStringBES(new Date()));
						vo.setMonto(String.valueOf(cabecera.getMonto()));
						vo.setNombreNomina(cabecera.getNombreNomina().substring(0, cabecera.getNombreNomina().lastIndexOf(".")));
						vo.setPlantilla(convenio.getPlantillaEnvio());
						vo.setArchivo(encode);

						logger.info("Se envia nómina a WS BES");
						String salida = wsservice.envioNominaWsBES(vo);
						logger.info("respuesta envío: " + salida);

						respuesta_envio = Utils.parseXmlEnvio(salida);
						logger.info("respuesta, codigo: " + respuesta_envio.getCodigoRetorno() + ", descripcion: " + respuesta_envio.getGlsErrorEnvio());

						//SI código es 1 se actualiza cabecera
						if(respuesta_envio.getCodigoRetorno()!=null && Integer.parseInt(respuesta_envio.getCodigoRetorno())==1) {
							cabecera.setEstadoNomina(2);
							long codigoNomina=Long.parseLong(respuesta_envio.getNumNomina().trim());
							cabecera.setCodigoNomina(codigoNomina);
							cabecera.setFechaEnvio(new Date());	
							cabeceraService.update(cabecera);

							//Se setean todos los trabajadores en Pendiente de Pago
							detalleService.updateDetallePendientedePago(cabecera.getIdCabecera(), codigoNomina);

							respuesta_envio.setCodigoRetorno("1");
						}else{
							respuesta_envio.setCodigoRetorno("0");
						}

					} catch (Exception e) {
						logger.fatal(e);
						respuesta_envio.setGlsErrorEnvio(e.getMessage());
						throw new MiException(new ErrorMessage(8, "Problemas enviando archivo a WS BES"));
					}
				}
			}

		}
		
		return respuesta_envio;
	}
	
}
