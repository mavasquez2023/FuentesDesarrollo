package cl.laaraucana.rendicionpagonomina.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

import javax.servlet.ServletOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cl.araucana.core.util.Rut;
import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
//import cl.laaraucana.rendicionpagonomina.entities.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleManualEntity;
import cl.laaraucana.rendicionpagonomina.exception.ErrorMessage;
import cl.laaraucana.rendicionpagonomina.exception.MiException;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.DetalleTefDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.DetalleTefDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ProductoDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ProductoDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.DetalleCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;
import cl.laaraucana.rendicionpagonomina.utils.ConvenioSingleton;
import cl.laaraucana.rendicionpagonomina.utils.Estados;
import cl.laaraucana.rendicionpagonomina.utils.FileManagmentUtils;
import cl.laaraucana.rendicionpagonomina.utils.GeneratorXLS;
import cl.laaraucana.rendicionpagonomina.utils.Utils;
import cl.laaraucana.rendicionpagonomina.vo.EnvioNominaVo;
import cl.laaraucana.rendicionpagonomina.vo.FTPConfigurationVO;
import cl.laaraucana.rendicionpagonomina.vo.ILF4500AVo;
import cl.laaraucana.rendicionpagonomina.vo.NominaVo;
import cl.laaraucana.rendicionpagonomina.vo.ParamRendicionVO;
import cl.laaraucana.rendicionpagonomina.vo.RendicionVO;
import cl.lib.export.txt.impl.FileGenerator;
import cl.liv.export.comun.util.file.ManejoArchivos;


@Service
public class ProcesaRendicionBESServiceImpl implements ProcesaRendicionBESService {

	private static final Logger logger = Logger.getLogger(ProcesaRendicionBESServiceImpl.class);

	@Autowired
	private CabeceraService cabeceraService;

	@Autowired
	private DetalleService detalleService;

	@Autowired
	private ILF4500AService ilf4500Aservice;

	@Autowired
	private WsService wsservice;

	@Autowired
	private ProcessService procesService;
	
	@Autowired
	private ParametrosService convenioService;
	
	@Autowired
	CabeceraManualService cabeceraManualService;
	
	@Autowired
	private DetalleManualService detalleManualService;
	
	@Autowired
	private FTPService ftpService;
	
	@Autowired
	MandatoAS400Service mandatoService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	BeneficioService beneficioService;
	
	@Autowired
	BecasService becasService;
	
	private ProductoDao productoDAO= new ProductoDaoImpl();
	
	@Value("${config.cron.bes.rendicion.sil.convenio}")
	private String comveniosxfecha;
	
	private final String convenio_BES_BEN= Configuraciones.getConfig("bes.convenio.beneficios");
	private final String convenio_BES_BECA= Configuraciones.getConfig("bes.convenio.becas");
	
	@SuppressWarnings("unchecked")
	private String rendicionNominasBES(ParamRendicionVO paramRendicion) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdh = new SimpleDateFormat("HH:mm:ss");

		String path = Configuraciones.getConfig("path.Nominas.recepcion");
		path= path.replaceAll("#banco#", paramRendicion.getCodigoBanco());


		try {
			String nombreArchivo = wsservice.rendicionWsNominasBES(paramRendicion);
			//String nombreArchivo="BECAS_ArchivoRendiciones_1.txt";
			if (nombreArchivo.isEmpty()) {

				logger.error("Sin archivo rendición para fecha: " + paramRendicion.getFechaDesde() + ", nomina: " + paramRendicion.getNumNomina());

				return "";
			}

			File file = new File(path + nombreArchivo);
			logger.info("Calculando CRC nómina: " + path + nombreArchivo );
			//Calculando el CRC32 del archivo
			byte[] bytesFile= FileUtils.readFileToByteArray(file);
			String crc32= getCRC32(bytesFile);
			HashMap params= new HashMap();
			params.put("crc", crc32);
			params.put("convenio", paramRendicion.getConvenio());
			logger.info(">>Validando si existe nómina, idCodConv=" + paramRendicion.getConvenio() + ", crc=" + crc32);
			//Se verifica si ya está procesado la misma nómina
			int idCabeceraCRC= cabeceraService.validaCRC(params);
			logger.info("id Cabecera con mismo crc: " + idCabeceraCRC);
			if(idCabeceraCRC==0){ //no existe nómina
				int i = 0;
				int cantidad = 0;
				long montototal = 0;
				int pagado = 0;
				long totalPagado = 0;
				int rechazado = 0;
				long totalRechazado = 0;
				int devuelto= 0;
				long totalDevuelto=0;
				int pendientes = 0;

				List<String> lines = new ArrayList<String>();
				List<DetalleEntity> detalles = new ArrayList<DetalleEntity>();
				//List<CabeceraEntiti> cabeceras = new ArrayList<CabeceraEntiti>();

				logger.info("Fichero: \\" + nombreArchivo);

				lines = FileUtils.readLines(file, "UTF-8");

				String codNomAnterior = "";

				DetalleEntity det = null;
				String estadoCabecera="";
				Map<String, RendicionVO> mapRendicion= new HashMap<String, RendicionVO>();
				int k=1;
				logger.info("A procesar " + lines.size() + " registros");
				for (String string2 : lines) {
					//logger.info("mapeando línea " + k);
					if(string2.trim().length()>0){

						String str2[] = string2.split(";");
						//Si nómina trabajador es distinta a anterior se procesa nómina anterior
						if (!str2[12].equals(codNomAnterior) && !codNomAnterior.equals("")) {
							RendicionVO rendicioVO= mapRendicion.get(codNomAnterior);
							CabeceraEntity cabecera= null;
							if(rendicioVO==null){
								rendicioVO= new RendicionVO();
								cabecera= new CabeceraEntity();
								rendicioVO.setDetalles(detalles);
							}else{
								cabecera= rendicioVO.getCabecera();
								rendicioVO.getDetalles().addAll(detalles);
							}
							if(!paramRendicion.getFechaDesde().equals("")){
								cabecera.setFechaCreacion(Utils.stringToDateBES(paramRendicion.getFechaDesde()));
							}

							//se setean datos de cabecera
							setCabecera(cabecera, paramRendicion, codNomAnterior, pendientes, estadoCabecera, cantidad, pagado, rechazado, devuelto, montototal, totalPagado, totalRechazado, totalDevuelto, crc32);

							rendicioVO.setCabecera(cabecera);
							mapRendicion.put(codNomAnterior, rendicioVO);

							//Se inicializa variables para nueva nómina
							cantidad = 0;
							montototal = 0;
							pagado = 0;
							totalPagado = 0;
							rechazado = 0;
							totalRechazado = 0;
							devuelto= 0;
							totalDevuelto=0;
							pendientes = 0;
							detalles = new ArrayList<DetalleEntity>();


						}
						//Se mapea detalle trabajador
						Long codigoNomina= Long.parseLong(str2[12].trim());
						long rutafiliado= Long.parseLong(str2[1].trim());
						det = new DetalleEntity();
						det.setCodigoNomina(codigoNomina);
						det.setRutAfiliado((int)rutafiliado);
						det.setDvAfiliado(String.valueOf((new Rut((int)rutafiliado)).getDV()));
						det.setNombres(str2[2]);
						det.setDescripcionFormaPago(str2[3]);
						det.setDescripcionBanco(str2[4]);
						det.setNumerocuenta(Long.parseLong(str2[5]));
						det.setMonto(Integer.parseInt(str2[6]));
						det.setDescripcionEstadoPago(str2[7]);
						det.setDescripcionRechazo(str2[8]);
						det.setReferencia2(str2[9]);
						det.setFechaCambio(Utils.stringIntToDate((str2[10])));
						det.setReferencia1(str2[11]);
						if(paramRendicion.getConvenio().equals(convenio_BES_BEN) || paramRendicion.getConvenio().equals(convenio_BES_BECA)){
							det.setIdDetalle(Long.parseLong(str2[11]));
						}

						//Datos de cabecera se van totalizando
						cantidad++;
						montototal += det.getMonto();
						String estado= det.getDescripcionEstadoPago().trim();
						if (estado.equals("PAGADO")) {
							pagado++;
							totalPagado += det.getMonto();
							det.setEstadoPago(Estados.PAGO_PAGADO);
						}else if (estado.equals("RECHAZADO") || estado.equals("RECHAZADO EN VALIDACION") ) {
							rechazado++;
							totalRechazado += det.getMonto();
							det.setEstadoPago(Estados.PAGO_RECHAZADO);
						}else if (estado.equals("DEVUELTO AL TOMADOR")) {
							devuelto++;
							totalDevuelto += det.getMonto();
							det.setEstadoPago(Estados.PAGO_DEVUELTO);
						}else if(estado.equals("PENDIENTE DE COBRO")){
							pendientes++;
							det.setEstadoPago(Estados.PAGO_PROCESO_PAGO);
						}else{
							pendientes++;
							det.setEstadoPago(Estados.PAGO_PROCESO_PAGO);
						}

						//Se agrega detalle a la lista
						detalles.add(det);
						codNomAnterior= str2[12];

					}
					k++;
				}

				//se graba última nómina
				RendicionVO rendicioVO= mapRendicion.get(codNomAnterior);
				CabeceraEntity cabecera= null;
				if(rendicioVO==null){
					rendicioVO= new RendicionVO();
					cabecera= new CabeceraEntity();
					rendicioVO.setDetalles(detalles);
				}else{
					cabecera= rendicioVO.getCabecera();
					rendicioVO.getDetalles().addAll(detalles);
				}
				if(!paramRendicion.getFechaDesde().equals("")){
					cabecera.setFechaCreacion(Utils.stringToDateBES(paramRendicion.getFechaDesde()));
				}

				//se setean datos de cabecera
				setCabecera(cabecera, paramRendicion, codNomAnterior, pendientes, estadoCabecera, cantidad, pagado, rechazado, devuelto, montototal, totalPagado, totalRechazado, totalDevuelto, crc32);

				rendicioVO.setCabecera(cabecera);
				mapRendicion.put(codNomAnterior, rendicioVO);
				
				//se itera sobre las nóminas para crear un mapa de los rut y folio por cada detalle
				//esto se usa para al leer los trabajadores pendientes de la base de datos encontrar la referencia inmediata en la nómina 
				for (Iterator iterator = mapRendicion.values().iterator(); iterator
						.hasNext();) {
					RendicionVO rendicionVO = (RendicionVO) iterator
							.next();
					List<DetalleEntity> listaDetalles= rendicionVO.getDetalles();
					Map<String, DetalleEntity> mapDetalles= new HashMap<String, DetalleEntity>();
					for (Iterator iterator2 = listaDetalles.iterator(); iterator2
							.hasNext();) {
						DetalleEntity detalleEntity = (DetalleEntity) iterator2
								.next();
						String llaveMap=detalleEntity.getRutAfiliado()+detalleEntity.getReferencia1()+detalleEntity.getMonto();

						if(paramRendicion.getConvenio().equals(convenio_BES_BEN) || paramRendicion.getConvenio().equals(convenio_BES_BECA)){
							llaveMap= String.valueOf(detalleEntity.getIdDetalle());
						}
						
						mapDetalles.put(llaveMap, detalleEntity);
					}
					rendicionVO.setMapDetalles(mapDetalles);
					
				}
				//Se inicia Grabación Cabecera y Detalle previo análisis de registros pendientes
				insertCabecerayDetalle(mapRendicion, paramRendicion.getNumNomina().equals(""));

				logger.info("Fin proceso archivo: "  +nombreArchivo);
				return path + nombreArchivo;
			}else{
				logger.info("nómina duplicada: "  +nombreArchivo + ", idCabecera: " + idCabeceraCRC);
			}
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en Rendición nómina, mensaje: " + e.getMessage());
			e.printStackTrace();
			return "";
		}
		return "";
	}
	
	public String getCRC32(byte[] data){
		CRC32 crc = new CRC32();
		crc.update(data);
		String enc = Long.toHexString(crc.getValue());
		return enc;
	}
    
	public CabeceraEntity setCabecera(CabeceraEntity cabecera, ParamRendicionVO paramRendicion, String codigoNomina, int pendientes, String estadoCabecera, int cantidad, int pagado, int rechazado, int devuelto, long montototal, long totalPagado, long totalRechazado, long totalDevuelto, String crc){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdh = new SimpleDateFormat("HH:mm:ss");
		int estadoNomina=0;
		//logger.info("Pendientes en cabecera " + cabecera.getCodigoNomina() + ":" + cabecera.getPendientes() + ", pendientes en nómina: " + pendientes);
		if((cabecera.getPendientes() +  pendientes)>0){
			estadoCabecera="EN_PROCESO";
			estadoNomina= Estados.NOMINA_ENPROCESO;
		}else{
			estadoCabecera="CERRADA";
			estadoNomina= Estados.NOMINA_CERRADA;
		}
		//logger.info("Estado nomina:" + estadoCabecera);
		cabecera.setFechaRendicion(new Date());
		cabecera.setPendientes(cabecera.getPendientes() + pendientes);
		cabecera.setCantidad(cabecera.getCantidad() + cantidad);
		cabecera.setCantidadPagado(cabecera.getCantidadPagado() + pagado);
		cabecera.setCantidadRechazado(cabecera.getCantidadRechazado() +  rechazado);
		cabecera.setCantidadDevuelto(cabecera.getCantidadDevuelto() +  devuelto);
		cabecera.setConvenio(paramRendicion.getConvenio());
		cabecera.setProducto("");
		cabecera.setCodigoNomina(Long.parseLong(codigoNomina));
		cabecera.setCodigoRechazoEnvio(0);
		cabecera.setMonto(cabecera.getMonto() +  montototal);
		cabecera.setTotalPagado(cabecera.getTotalPagado() +  totalPagado);
		cabecera.setTotalRechazado(cabecera.getTotalRechazado()+ totalRechazado);
		cabecera.setTotalDevuelto(cabecera.getTotalDevuelto() + totalDevuelto);
		cabecera.setCodigoRechazoRendicion(0);
		cabecera.setEstadoNomina(estadoNomina);
		cabecera.setFechaEnvio(null);
		cabecera.setGlosaRechazoEnvio("");
		cabecera.setGlosaRechazoRendicion("");
		cabecera.setCrc(crc);
		
		return cabecera;
	}
	
	public void insertCabecerayDetalle( Map<String, RendicionVO> mapRendicion, boolean xfecha){
		try {
			ProductoDao productoDAO= new ProductoDaoImpl();
			HashMap<String, Object> producto = null;
			long id_cabecera=0;
			
			for (Iterator iterator = mapRendicion.values().iterator(); iterator.hasNext();) {
				RendicionVO rendicionVO = (RendicionVO) iterator.next();
								
				long codigoNomina=rendicionVO.getCabecera().getCodigoNomina();
				boolean grabar_detalle=false;
				try {
					
					CabeceraEntity cabecera_agrabar= rendicionVO.getCabecera();
					logger.info("Verificando si existe cabecera código nómina: " + rendicionVO.getCabecera().getCodigoNomina());
					CabeceraEntity cabecera_actual= cabeceraService.findByCodigoNomina(cabecera_agrabar.getCodigoNomina());
					List<Long> cabecerasManual= new ArrayList<Long>();
					
					long totalPagado=0;
					int cantidadPagado=0;
					long totalDevuelto=0;
					int cantidadDevuelto=0;
					long totalRechazado=0;
					int cantidadRechazado=0;
					int total_registros=0;
					int procesados=0;
					
					//Si cabecera no es null se buscan registros pendientes de BD y se buscan en la nómina para actualizar estado y cabecera
					//En caso contrario se genera nueva cabecera y se graban todos los detalles de la nómina
					if(cabecera_actual != null){
						if(!xfecha){
							//Se busca datos de tabla Producto
							try {
								producto= productoDAO.consultaProducto(Integer.parseInt(cabecera_actual.getConvenio()), cabecera_actual.getProducto());
							} catch (Exception e2) {
								logger.fatal("Error al obtener datos Producto, mensaje:" + e2.getMessage());
							}

							id_cabecera=cabecera_actual.getIdCabecera();
							totalPagado= cabecera_actual.getTotalPagado();
							cantidadPagado= cabecera_actual.getCantidadPagado();
							totalDevuelto= cabecera_actual.getTotalDevuelto();
							cantidadDevuelto= cabecera_actual.getCantidadDevuelto();
							totalRechazado= cabecera_actual.getTotalRechazado();
							cantidadRechazado= cabecera_actual.getCantidadRechazado();
							if(cabecera_actual.getNombreNomina()==null){
								cabecera_actual.setNombreNomina("");
							}

							//***********Se Procesa los DETALLES
							DetalleTefDao detalleDao = new DetalleTefDaoImpl();


							//Se obtiene detalles pendientes en base de datos para buscar diferencia de estado con la nómina
							List<DetalleEntity> detallesPendientes= detalleService.findPendientesByIdCabecera(id_cabecera);
							logger.info("procesando registros pendientes: " + detallesPendientes.size());

							//se itera sobre lso registros pendientes de la base de datos
							for (DetalleEntity det : detallesPendientes) {
								//se busca registro trabajador en Mapa detalle
								String llaveMap=det.getRutAfiliado()+det.getReferencia1()+det.getMonto();

								if(cabecera_agrabar.getConvenio().equals(convenio_BES_BEN) || cabecera_agrabar.getConvenio().equals(convenio_BES_BECA)){
									llaveMap= String.valueOf(det.getIdDetalle());
								}
								 
								DetalleEntity detEntity= rendicionVO.getMapDetalles().get(llaveMap);

								//Si hay diferencia de estado se actualiza registro en BD
								//'detEntity' corresponde a trabajador en nómina y 'det' al registro de la BD
								if(detEntity!=null && det.getEstadoPago()!= detEntity.getEstadoPago()){
									det.setDescripcionFormaPago(detEntity.getDescripcionFormaPago());
									det.setDescripcionEstadoPago(detEntity.getDescripcionEstadoPago());
									det.setDescripcionRechazo(detEntity.getDescripcionRechazo());
									det.setEstadoPago(detEntity.getEstadoPago());
									det.setFechaCambio(detEntity.getFechaCambio());
									det.setCodigoNomina(cabecera_actual.getCodigoNomina());
									detalleService.saveOrupdate(det);
									//boolean result = detalleDao.actualizarDetalleRendicion(det);
									
									
									HashMap params_ben= null;
									if(cabecera_actual.getConvenio().equals(convenio_BES_BEN) ){
										params_ben= new HashMap();
										params_ben.put("nomnomina", cabecera_actual.getNombreNomina());
										params_ben.put("idpago", det.getReferencia1());
									}
									if(cabecera_actual.getConvenio().equals(convenio_BES_BECA)){
										params_ben= new HashMap();
										params_ben.put("idpago", det.getIdReferencia());
									}

									//Si pago es Rechazado se revoca mandato en AS400
									if(detEntity.getEstadoPago()==Estados.PAGO_RECHAZADO){
										totalRechazado+= detEntity.getMonto();
										cantidadRechazado++;
										if(params_ben!=null){
											if(cabecera_actual.getConvenio().equals(convenio_BES_BEN) ){
												params_ben.put("estado", String.valueOf(Estados.PAGO_RECHAZADO_BENEFICIOS));
											}else if(cabecera_actual.getConvenio().equals(convenio_BES_BECA) ){
												params_ben.put("estado", String.valueOf(Estados.PAGO_RECHAZADO));
											}
											
											params_ben.put("codrechazo", "RECHAZADO");
											params_ben.put("desrechazo", detEntity.getDescripcionRechazo());
										}
										//Si estado del pago es rechazado se invoca rutina para revocar mandato si corresponde
										try {
											int rutcliente= det.getRutAfiliado();
											int resultado_revocar= mandatoService.revocarMandato("BES", detEntity.getDescripcionRechazo(), rutcliente, Integer.parseInt(det.getCodigoBanco().trim()), det.getNumerocuenta());
											logger.info("Resultado revocar mandato (1-OK):" + resultado_revocar);

										} catch (Exception e) {
											logger.warn("Error al invocar revocarMandato para Rut " + det.getRutAfiliado());
											//e.printStackTrace();
										}
									}
									if(detEntity.getEstadoPago()==Estados.PAGO_DEVUELTO){
										totalDevuelto+= detEntity.getMonto();
										cantidadDevuelto++;
										if(params_ben!=null){
											if(cabecera_actual.getConvenio().equals(convenio_BES_BEN) ){
												params_ben.put("estado", String.valueOf(Estados.PAGO_RECHAZADO_BENEFICIOS));
											}else if(cabecera_actual.getConvenio().equals(convenio_BES_BECA) ){
												params_ben.put("estado", String.valueOf(Estados.PAGO_DEVUELTO));
											}
											
											params_ben.put("codrechazo", "DEVUELTO");
											params_ben.put("desrechazo", detEntity.getDescripcionRechazo());
										}
									}
									if(detEntity.getEstadoPago()==Estados.PAGO_PAGADO){
										totalPagado+= detEntity.getMonto();
										cantidadPagado++;
										if(params_ben!=null){
											if(cabecera_actual.getConvenio().equals(convenio_BES_BEN) ){
												params_ben.put("estado", String.valueOf(Estados.PAGO_PAGADO_BENEFICIOS));
											}else if(cabecera_actual.getConvenio().equals(convenio_BES_BECA) ){
												params_ben.put("estado", String.valueOf(Estados.PAGO_PAGADO));
											}
											
											params_ben.put("fechapago", detEntity.getFechaCambio());
										}
									}
									if(params_ben!=null && (String)params_ben.get("estado")!=null){
										try {
											//Se actualiza solo por el idpago
											if(cabecera_actual.getConvenio().equals(convenio_BES_BEN)){
												beneficioService.updateBeneficiarioRendicion(params_ben);
											}else if (cabecera_actual.getConvenio().equals(convenio_BES_BECA)){
												becasService.updateBecadoRendicion(params_ben);
											}
										} catch (Exception e) {
											logger.warn("Error al actualizar en beneficios el ESTADOPAGO " + detEntity.getEstadoPago() + " para rutafi: " + detEntity.getRutAfiliado());
											e.printStackTrace();
										}
									}
									procesados++;

								}


								//si es Carga Manual se actualiza origen en AS400
								if(producto!=null && producto.get("CargManual").toString().equals("S") && producto.get("URLNotific").toString().equals("MANUAL")){
									HashMap<String, String> params_man= new HashMap<String, String>();
									params_man.put("estado", String.valueOf(detEntity.getEstadoPago()));
									params_man.put("idDetalle", detEntity.getReferencia2());
									try {
										detalleManualService.updateEstadoDetalleManual(params_man);
										long idCabeceraManual= detalleManualService.getIdCabeceraByIdDetalle(Long.parseLong(detEntity.getReferencia2()));
										if(!cabecerasManual.contains(idCabeceraManual)){
											cabecerasManual.add(idCabeceraManual);
										}
									} catch (Exception e) {
										logger.warn("Error al actualizar en DetalleManula el ESTADOPAGO " + detEntity.getEstadoPago() + " para iderefencia: " + detEntity.getReferencia2().trim());
										e.printStackTrace();
									}
								}

								total_registros++;
								if(total_registros%100==0){
									logger.info("Procesando registro: " + total_registros);
								}
							}

							//Si hubo registros pendientes procesados se actualiza totales cabecera
							
							if(procesados>0){
								cabecera_actual.setTotalPagado(totalPagado);
								cabecera_actual.setCantidadPagado(cantidadPagado);
								cabecera_actual.setTotalDevuelto(totalDevuelto);
								cabecera_actual.setCantidadDevuelto(cantidadDevuelto);
								cabecera_actual.setTotalRechazado(totalRechazado);
								cabecera_actual.setCantidadRechazado(cantidadRechazado);				
								cabecera_actual.setCodigoRechazoRendicion(cabecera_agrabar.getCodigoRechazoRendicion());
								cabecera_actual.setGlosaRechazoRendicion(cabecera_agrabar.getGlosaRechazoRendicion());
								cabecera_actual.setFechaRendicion(new Date());
								cabecera_actual.setEstadoNomina(cabecera_agrabar.getEstadoNomina());
								cabecera_actual.setCrc(cabecera_agrabar.getCrc());
								cabeceraService.updateNomina(cabecera_actual);
							}	
							//si no cuadren los totales de BD + registros procesados con lo totalizado en nómina se envía correo a ejecutivo
							if(totalPagado!= cabecera_agrabar.getTotalPagado() 
									|| totalDevuelto!= cabecera_agrabar.getTotalDevuelto() 
									|| totalRechazado!= cabecera_agrabar.getTotalRechazado()){
								
								//Se obtiene el convenio asociado para obtener banco convenio y ejecutivo 
								ConvenioEntity convenio= ConvenioSingleton.getInstance().getConvenio(cabecera_actual.getConvenio());
								if(convenio.getEmailEjecutivo() != null && convenio.getEmailEjecutivo().length()>5) {
									logger.info("Enviando Mail a ejecutivo ["+convenio.getEmailEjecutivo()+"]");
									String body= Configuraciones.getConfig("envio.mail.descuadre.body");
									body= body.replaceAll("#nombre_nomina#", cabecera_actual.getNombreNomina());
									body= body.replaceAll("#producto#", cabecera_actual.getProducto());
									body= body.replaceAll("#monto#", String.valueOf(cabecera_actual.getMonto()));
									body= body.replaceAll("#cantidad#", String.valueOf(cabecera_actual.getCantidad()));
									String subject= Configuraciones.getConfig("envio.mail.descuadre.subject");
									mailService.sendEmail(convenio.getEmailEjecutivo(), subject, body);

								}
							}	

							logger.info("Total registros actualizados: " + procesados + "/" + rendicionVO.getDetalles().size());
							//Si cabecerasManual >0 se actualiza todas las cebeceras asociadasa los detalles procesados
							if(cabecerasManual.size()>0){
								for (Iterator iterator2 = cabecerasManual.iterator(); iterator2
										.hasNext();) {
									Long idCabecera = (Long) iterator2.next();
									try {
										cabeceraManualService.updateTotalesNomina(idCabecera);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}
							}

							//logger.info("Código Nómina: " + codigoNomina + " borrando detalle.");
							//detalleService.deleteByCodigoNomina(cabecera_agrabar.getCodigoNomina());
						}
					}else{
						//Cabecera Actual es null no existe en BD, se graba cabecera y detalles de Nómina						
						logger.info("Código Nómina: " + codigoNomina + " grabación nueva cabecera, estado " + cabecera_agrabar.getEstadoNomina());
						try {
							//Se busca datos de tabla Producto
							producto= productoDAO.consultaProducto(Integer.parseInt(cabecera_agrabar.getConvenio()));
						} catch (Exception e2) {
							logger.fatal("Error al obtener datos Producto, mensaje:" + e2.getMessage());
						}
						cabecera_agrabar.setProducto(producto.get("CodProd").toString());
						cabecera_actual= cabeceraService.save(cabecera_agrabar);
						id_cabecera= cabecera_actual.getIdCabecera();

						
						//Se obtiene detalles para grabar la nómina completa
						List<DetalleEntity> detallesNomina= rendicionVO.getDetalles();
						logger.info("Procesando registros en nómina: " + detallesNomina.size());
						
						//se itera sobre lso registros pendientes de la base de datos
						for (DetalleEntity detEntity : detallesNomina) {
							
							detEntity.setIdCabecera(id_cabecera);
							detalleService.save(detEntity);
							
							//Si pago es Rechazado se revoca mandato en AS400 no se revoca mandato ya que no tenemos código banco
							procesados++;
								
							//si es Carga Manual se actualiza origen en AS400
							if(producto!=null && producto.get("CargManual").toString().equals("S") && producto.get("URLNotific").toString().equals("MANUAL")){
								HashMap<String, String> params_man= new HashMap<String, String>();
								params_man.put("estado", String.valueOf(detEntity.getEstadoPago()));
								params_man.put("idDetalle", detEntity.getReferencia2());
								try {
									detalleManualService.updateEstadoDetalleManual(params_man);
									long idCabeceraManual= detalleManualService.getIdCabeceraByIdDetalle(Long.parseLong(detEntity.getReferencia2()));
									if(!cabecerasManual.contains(idCabeceraManual)){
										cabecerasManual.add(idCabeceraManual);
									}
								} catch (Exception e) {
									logger.warn("Error al actualizar en DetalleManula el ESTADOPAGO " + detEntity.getEstadoPago() + " para iderefencia: " + detEntity.getReferencia2().trim());
									e.printStackTrace();
								}
							}

							total_registros++;
							if(total_registros%100==0){
								logger.info("Procesando registro: " + total_registros);
							}
						}
					}					
					
				} catch (Exception e) {
					logger.warn("Error al grabar nómina: " + codigoNomina);
					cabeceraService.rollbackNominaTEF(codigoNomina);
					e.printStackTrace();
				}
			}
			

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
/*	@Override
	public void sendNomina() throws Exception {

		String path = Configuraciones.getConfig("path.envio");

		File nomina = new File(path);

		List<String> listaFiles = new ArrayList<String>();

		listaFiles.add(path + "\\" + nomina.getName());
		
		procesService.zipDir(listaFiles, path);

		EnvioNominaVo vo = new EnvioNominaVo();

		File fil = new File(path + "\\" + "files.zip");

		String encode = procesService.encodeBase64(FileUtils.readFileToByteArray(fil));

		vo.setConvenio("2820417209");
		vo.setTipoNomina("Pago");
		vo.setMetodo("ENVNOM001");
		vo.setConceptoPago("0000000000");
		vo.setCantidad("318");
		vo.setFechaPago("29/05/2020");
		vo.setMonto("101161884");
		vo.setNombreNomina("2820417209_20200525_test2");
		vo.setPlantilla("");
		vo.setArchivo(encode);

		String salida = wsservice.envioNominaWsBES(vo);

		logger.debug("salida " + salida);

		List<EnvioNominaVo> envio = new ArrayList<EnvioNominaVo>();

		envio = Utils.parseXmlEnvio(salida);

	}*/

	@Override
	public void procesoxNomina(String numNomina, String idUsuario, String convenios) throws Exception {
		// TODO Auto-generated method stub
		//NominaVo nomina= new NominaVo();
		FTPConfigurationVO config = new FTPConfigurationVO();
		HashMap<String, Object> producto = null;
		
		
		HashMap params= new HashMap();
		params.put("idUsuario", idUsuario);
		if(numNomina.equals("")){
			params.put("estadoNomina", String.valueOf(Estados.NOMINA_ENPROCESO));
			params.put("estadoNomina2", String.valueOf(Estados.NOMINA_ENVIADA));
			//nomina.setEstado("PENDIENTE");
		}else{
			params.put("codigoNomina", numNomina);
			//nomina.setNomina(numNomina);
		}
		if(convenios!=null && !convenios.equals("")){
			String[] lista_split= convenios.split(",");
			List<String> listaConvenios= new ArrayList<String>();
			for (int i = 0; i < lista_split.length; i++) {
				listaConvenios.add(lista_split[i]);
			}
			params.put("convenios", listaConvenios);
		}
		
		//List<CabeceraEntity> pendientes= cabeceraService.getNominasCabecera(nomina);
		List<CabeceraEntity> pendientes= cabeceraService.findNominasRendicion(params);
		logger.info("procesoxNomina, a procesar " + pendientes.size() + " nóminas pendientes");
		for (Iterator iterator = pendientes.iterator(); iterator.hasNext();) {
			CabeceraEntity cabeceraEntiti = (CabeceraEntity) iterator.next();
			ConvenioEntity convenio= ConvenioSingleton.getInstance().getConvenio(cabeceraEntiti.getConvenio());
			ParamRendicionVO paramVO= new ParamRendicionVO();
			paramVO.setConvenio(String.valueOf(cabeceraEntiti.getConvenio()));
			paramVO.setCodigoBanco(convenio.getCodigoBanco());
			paramVO.setConvenioBanco(convenio.getConvenioBanco());
			paramVO.setPlantilla(String.valueOf(convenio.getPlantilla()));
			paramVO.setNumNomina(String.valueOf(cabeceraEntiti.getCodigoNomina()));
			paramVO.setFechaDesde("");
			paramVO.setFechaHasta("");
			if(convenio.getCodigoBanco().equals("BES")){
				String archivo= this.rendicionNominasBES(paramVO);
				if(!archivo.equals("")){
					//Se buscan datos de ftp en producto
					
					producto = productoDAO.consultaProducto(Integer.parseInt(cabeceraEntiti.getConvenio()), cabeceraEntiti.getProducto());
					logger.info("URLNotificación: " + producto.get("URLNotific"));
					if(!producto.get("URLNotific").equals("AS400")){
						String host= producto.get("FtpServidor").toString();
						String user= producto.get("FtpUsuario").toString();
						String clave= producto.get("FtpClave").toString();
						String port= "21";
						String ftpCarpeta= producto.get("FtpNotificacion").toString();

						if(host!=null && !host.equals("")){
							File file = new File(archivo);
							String new_archivo= convenio.getDescripcionConvenio() + archivo.substring(archivo.lastIndexOf("_"));
							logger.info("conectando a FTP " + host + ", grabando archivo " + new_archivo + " en carpeta:" + ftpCarpeta );
							FTPService ftp = new FTPServiceImpl();
							ftp.connectToFTP(host, Integer.parseInt(port), user, clave);
							ftp.putFileToFTP(file, ftpCarpeta, new_archivo);
							ftp.disconnectFTP();
						}else{
							logger.warn("No se ha definido atributos de FTP para convenio: " + cabeceraEntiti.getConvenio());
						}
					}
				}
			}
		}
	}

	@Override
	public void procesoxFecha(String fechaDesde, String fechaHasta, String idUsuario, String convenios) throws Exception {
		
		String host = Configuraciones.getConfig("hostFTP");
		String port = Configuraciones.getConfig("portFTP");
		String user = Configuraciones.getConfig("usuarioFTP");
		String clave = Configuraciones.getConfig("claveFTP");
		String FTPCarpeta = Configuraciones.getConfig("FTPCarpeta");
		FTPService ftp = new FTPServiceImpl();
		
		ParamRendicionVO paramVO= new ParamRendicionVO();
		List<ConvenioEntity> lista_convenios=convenioService.consultaConveniosconPlantilla(idUsuario, convenios);
		logger.info("procesoxFecha, a procesar " + lista_convenios.size() + " convenios");
		for (Iterator iterator = lista_convenios.iterator(); iterator.hasNext();) {
			ConvenioEntity convenioEntity = (ConvenioEntity) iterator.next();
			if(comveniosxfecha.indexOf(convenioEntity.getCodigoConvenio())>-1){
				logger.info("Convenio: " + convenioEntity.getConvenioBanco() + ", plantilla: " + convenioEntity.getPlantilla() + ", fechaDesde:" + fechaDesde + ", fechaHasta" + fechaHasta);
				paramVO.setConvenio(convenioEntity.getCodigoConvenio());
				paramVO.setCodigoBanco(convenioEntity.getCodigoBanco());
				paramVO.setConvenioBanco(String.valueOf(convenioEntity.getConvenioBanco()));
				paramVO.setPlantilla(String.valueOf(convenioEntity.getPlantilla()));
				paramVO.setNumNomina("");
				paramVO.setFechaDesde(fechaDesde);
				paramVO.setFechaHasta(fechaHasta);
				if(convenioEntity.getCodigoBanco().equals("BES")){
					String archivo= this.rendicionNominasBES(paramVO);
					if(!archivo.equals("")){
						File file = new File(archivo);
						String new_archivo= convenioEntity.getDescripcionConvenio() + archivo.substring(archivo.lastIndexOf("_"));

						logger.info("conectando a FTP " + host + ", grabando archivo " + new_archivo + " en carpeta:" + FTPCarpeta );
						ftp.connectToFTP(host, Integer.parseInt(port), user, clave);
						ftp.putFileToFTP(file, FTPCarpeta, new_archivo);
						ftp.disconnectFTP();
					}
				}
			}
		}
		/*List<ConvenioEntity> convenios = (List<ConvenioEntity>)convenioService.getConvenio(Integer.parseInt(id));
		/*Date fechaInicio= Utils.stringToDate(fechaDesde);
		Date fechaTermino= Utils.stringToDate(fechaHasta);
		*/
		//for (ConvenioEntity tefConvenioEntiti : convenios) {
			
			
		//}
	}
	
}
