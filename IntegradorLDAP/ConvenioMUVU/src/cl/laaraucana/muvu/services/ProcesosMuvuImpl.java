/**
 * 
 */
package cl.laaraucana.muvu.services;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.jfactory.utils.GeneratorXLS;
import cl.laaraucana.muvu.entities.Resumen;
import cl.laaraucana.muvu.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.muvu.ibatis.vo.AfiliadoMuvuVo;
import cl.laaraucana.muvu.ibatis.vo.AfiliadoSuraVo;
import cl.laaraucana.muvu.ibatis.vo.StockMuvuVo;
import cl.laaraucana.muvu.services.vo.ParamVO;
import cl.laaraucana.muvu.util.Configuraciones;
import cl.laaraucana.muvu.util.Utils;
import cl.laaraucana.muvu.util.UtilsFecha;
import cl.laaraucana.muvu.util.UtilsFile;
import cl.laaraucana.muvu.vo.AfiliadoVo;
import cl.laaraucana.muvu.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.muvu.ws.ClienteInfoAfiliado;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jcraft.jsch.ChannelSftp;

/**
 * @author IBM Software Factory
 *
 */
@Service
public class ProcesosMuvuImpl implements ProcesosMuvu {

	private static final Logger logger = Logger.getLogger(ProcesosMuvuImpl.class);

	@Autowired
	private SFTPService sFTPService;

	@Autowired
	private ResumenService resumenService;

	@Autowired
	private ReporteService reporteService;

	@Autowired
	private MailService mailService;

	@Autowired
	private BitacoraService bitacoraService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cl.laaraucana.muvu.services.ProcesosMuvu#procesarAltas()
	 */
	@Override
	public boolean procesarAltas(String periodo) throws Exception {
		logger.info("Iniciando el proceso para altas Muvu ");
		try {
			String host = Configuraciones.getConfig("hostFTP");
			int port = Integer.parseInt(Configuraciones.getConfig("portFTP"));
			String username = Configuraciones.getConfig("usuarioFTP");
			String password = Configuraciones.getConfig("claveFTP");
			String directory = Configuraciones.getConfig("FTPCarpeta");
			// formato archivo altas: ALTAS_MUVU_AAAAMMDD.txt
			String downloadFile = Configuraciones.getConfig("FTPArchivo");
			if (periodo == null) {
				downloadFile = downloadFile.replaceAll("AAAAMMDD", UtilsFecha.getFechaAyerAs400());
			} else {
				downloadFile = downloadFile.replaceAll("AAAAMMDD", periodo);
			}
			String saveFile = Configuraciones.getConfig("carpeta.altas");

			// Se leen los archivos de SFTP
			logger.info("Conectando a host: " + host + ":" + port + ", con usuario:" + username);
			ChannelSftp sftp = sFTPService.connect(host, port, username, password);
			logger.info("is channel connected= " + sftp.isConnected());

			if (!sftp.isConnected()) {

				logger.warn("Saliendo Proceso Altas Muvu, Error al conectar a sitio SFTP");
				return false;
			}

			String pathfile = saveFile + downloadFile;
			logger.info("Descargando desde carpeta ftp " + directory + ", archivo " + downloadFile
					+ ", y dejando en ruta:" + pathfile);
			sFTPService.download(directory, downloadFile, pathfile, sftp);

			// Se rescatan los archivos validos de Altas
			/*
			 * List<String> archivos_validos= new ArrayList<String>(); for (Iterator
			 * iterator = lista.iterator(); iterator.hasNext();) { LsEntry entry = (LsEntry)
			 * iterator.next(); System.out.println("descargando archivo:" +
			 * entry.getFilename()); if(entry.getFilename().split("_")[0].equals("ALTAS")){
			 * sFTPService.download(directory, entry.getFilename(), saveFile +
			 * entry.getFilename(), sftp); if(!archivos_validos.contains(saveFile +
			 * entry.getFilename())){ archivos_validos.add(saveFile + entry.getFilename());
			 * } }
			 * 
			 * }
			 */
			// Se leen registros de archivo

			// for (Iterator iterator = archivos_validos.iterator(); iterator.hasNext();) {
			// String pathfile = (String) iterator.next();
			logger.info("Procesando archivo:" + pathfile);
			List<String> listaRegistros = UtilsFile.leerArchivo(pathfile);
			logger.info("Cantidad registros en archivo de altas:" + listaRegistros.size());
			if (listaRegistros.size() == 0) {
				logger.info("Listando archivos en carpeta FTP: " + directory);
				Vector<String> lista = sFTPService.listFiles(directory, sftp);
				logger.info("Archivos en carpeta FTP:");
				for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
					Object fileftp = (Object) iterator.next();
					logger.info(fileftp.toString());
				}

			}
			// Se recorre lista de registros y se actualiza fecha de Alta
			for (Iterator iterator2 = listaRegistros.iterator(); iterator2.hasNext();) {
				String registro = (String) iterator2.next();
				String[] columnas = registro.split(",");
				int rut = Integer.parseInt(columnas[1]);
				logger.info("Buscando en tabla Resumen, Rut: " + rut);
				Resumen resumen = resumenService.findByRut(rut);
				if (resumen != null) {
					Date fechaAlta = null;
					String email_alta = columnas[0].trim();
					Date fechaInscripcion = UtilsFecha.stringToDateSAP(columnas[3]);
					Date fechaSincronizacion = UtilsFecha.stringToDateSAP(columnas[4]);
					Date fechaEnrolamiento = resumen.getFechaEnrolamiento();
					if (fechaInscripcion.compareTo(fechaSincronizacion) > 0) {
						if (fechaInscripcion.compareTo(fechaEnrolamiento) > 0) {
							fechaAlta = fechaInscripcion;
						} else {
							fechaAlta = fechaEnrolamiento;
						}
					} else if (fechaSincronizacion.compareTo(fechaEnrolamiento) > 0) {
						fechaAlta = fechaSincronizacion;
					} else {
						fechaAlta = fechaEnrolamiento;
					}
					resumen.setFechaAlta(fechaAlta);
					resumen.setFechaActFisica(fechaSincronizacion);
					resumen.setFechaInscripcion(fechaInscripcion);
					resumen.setEstado("Vigente");
					if (!resumen.getEmail().trim().equals(email_alta)) {
						logger.info("Actualizando nuevo correo informado por MUVU: " + email_alta + ", anterior: "
								+ resumen.getEmail());
						resumen.setEmail(email_alta);
					}

					logger.info("Actualizando Resumen, Rut: " + rut);
					resumenService.updateResumen(resumen);

					// Rescatando nombre Afiliado
					// Conectando a CRM
					String rutAfiliado = resumen.getRutAfiliado() + "-" + resumen.getDvAfiliado();
					logger.info("Recuperando nombre afiliado en CRM para " + rutAfiliado);
					ClienteInfoAfiliado client = new ClienteInfoAfiliado();
					SalidainfoAfiliadoVO salida = (SalidainfoAfiliadoVO) client.getDataAfiliado(rutAfiliado);

					// seteando nombre afiliado
					if (salida.getNombreCompleto() != null) {
						resumen.setNombreAfiliado(salida.getNombreCompleto());
					}

					// ENVIANDO mail
					logger.info("Generando reporte Poliza Seguro Vida Sana en PDF");
					String rutaPDF = reporteService.generarReport(resumen, Configuraciones.getConfig("muvu.carpeta"));
					logger.info("Enviando contrato altas por correo a " + resumen.getEmail());
					mailService.sendEmailClie(resumen.getEmail(), Configuraciones.getConfig("mail.asunto.cliente"),
							Utils.emailClienteAlta(resumen.getNombreAfiliado()), rutAfiliado, rutaPDF);
				} else {
					logger.warn("Rut " + rut + " no encontrado en tabla Resumen, no procesado");
				}
			}
			// }

			logger.info("Fin proceso Altas Muvu ");
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error Proceso Altas Muvu ", e);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cl.laaraucana.muvu.services.ProcesosMuvu#procesarAltas()
	 */
	@Override
	public boolean procesarStock(String periodo) throws Exception {
		logger.info("Iniciando el proceso para Stock Muvu para periodo : " + periodo);
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}
		try {
			String host = Configuraciones.getConfig("hostFTP");
			int port = Integer.parseInt(Configuraciones.getConfig("portFTP"));
			String username = Configuraciones.getConfig("usuarioFTP");
			String password = Configuraciones.getConfig("claveFTP");
			String directory = Configuraciones.getConfig("FTPCarpeta");
			// formato archivo Stock: STOCK_MUVU_AAAAMM.txt
			String downloadFile = Configuraciones.getConfig("muvu.stock.FTPArchivo");
			if (periodo == null) {
				downloadFile = downloadFile.replaceAll("AAAAMM", UtilsFecha.getFechaPeriodo());
			} else {
				downloadFile = downloadFile.replaceAll("AAAAMM", periodo);
			}
			String saveFile = Configuraciones.getConfig("muvu.stock.carpeta");

			// Se leen los archivos de SFTP
			logger.info("Conectando a host: " + host + ":" + port + ", con usuario:" + username);
			ChannelSftp sftp = sFTPService.connect(host, port, username, password);
			logger.info("is channel connected= " + sftp.isConnected());

			if (!sftp.isConnected()) {

				logger.warn("Saliendo Proceso Stock Muvu, Error al conectar a sitio SFTP");
				return false;
			}

			String pathfile = saveFile + downloadFile;
			logger.info("Descargando desde carpeta ftp " + directory + ", archivo " + downloadFile
					+ ", y dejando en ruta:" + pathfile);
			sFTPService.download(directory, downloadFile, pathfile, sftp);
			sftp.exit();
			// Se leen registros de archivo

			logger.info("Procesando archivo:" + pathfile);
			List<String> listaRegistros = UtilsFile.leerArchivo(pathfile);
			logger.info("Cantidad registros en archivo de Stock:" + listaRegistros.size());

			/*
			 * Se recorre lista de registros. (1) Por cada uno se inserta una fila en
			 * stockMuvu y luego, (2) si corresponde ser dado de baja (regla de negocio), se
			 * actualizan registros en tabla resumen (2.1) y bitácora (2.2).
			 */
			for (Iterator<String> iterator2 = listaRegistros.iterator(); iterator2.hasNext();) {
				String registro = (String) iterator2.next();
				String[] columnas = registro.split(Configuraciones.getConfig("muvu.stock.separador"));
				int rut = Integer.parseInt(columnas[2]);

				if (columnas.length < 37) { // 9 campos de proceso + 28 de dias por para soportar Febrero, el mes con
											// menos dias.
					throw new Exception("Archivo Stock con menos columnas de las minimamente requeridas.");
				}

				// 1. se ingresa fila en tabla stockMuvu.
				StockMuvuVo stock = new StockMuvuVo();
				// campos mandatorios.
				stock.setPeriodo(new Long(columnas[0]));
				stock.setCorreoElectronico(columnas[1]);
				stock.setRut(new Long(columnas[2]));
				stock.setDv(columnas[3]);
				stock.setPromedioMensual(new Float(columnas[4]));
				stock.setDiasMes(new Integer(columnas[5]));
				stock.setTrimestre(new Float(columnas[6]));
				stock.setTrimestreAnt(new Float(columnas[7]));
				stock.setResultado(new Float(columnas[8]));
				// dias: campos opcionales.
				stock.setDia1(new Float(columnas[9]));
				stock.setDia2(new Float(columnas[10]));
				stock.setDia3(new Float(columnas[11]));
				stock.setDia4(new Float(columnas[12]));
				stock.setDia5(new Float(columnas[13]));
				stock.setDia6(new Float(columnas[14]));
				stock.setDia7(new Float(columnas[15]));
				stock.setDia8(new Float(columnas[16]));
				stock.setDia9(new Float(columnas[17]));
				stock.setDia10(new Float(columnas[18]));
				stock.setDia11(new Float(columnas[19]));
				stock.setDia12(new Float(columnas[20]));
				stock.setDia13(new Float(columnas[21]));
				stock.setDia14(new Float(columnas[22]));
				stock.setDia15(new Float(columnas[23]));
				stock.setDia16(new Float(columnas[24]));
				stock.setDia17(new Float(columnas[25]));
				stock.setDia18(new Float(columnas[26]));
				stock.setDia19(new Float(columnas[27]));
				stock.setDia20(new Float(columnas[28]));
				stock.setDia21(new Float(columnas[29]));
				stock.setDia22(new Float(columnas[30]));
				stock.setDia23(new Float(columnas[31]));
				stock.setDia24(new Float(columnas[32]));
				stock.setDia25(new Float(columnas[33]));
				stock.setDia26(new Float(columnas[34]));
				stock.setDia27(new Float(columnas[35]));
				stock.setDia28(new Float(columnas[36]));
				if (stock.getDiasMes().intValue() >= 29) {
					stock.setDia29(new Float(columnas[37]));
				}
				if (stock.getDiasMes().intValue() >= 30) {
					stock.setDia30(new Float(columnas[38]));
				}
				if (stock.getDiasMes().intValue() >= 31) {
					stock.setDia31(new Float(columnas[39]));
				}

				try {
					logger.info("Insertando en tabla Stock, Rut: " + rut);
					sqlMap.insert("formweb.insertStockMUVU", stock);
				} catch (SQLException e) {
					logger.error("PK duplicada (periodo, rut) = (" + stock.getPeriodo() + " , " + stock.getRut() + ")");
				}

				// 2. Regla de negocio: dar de baja usuarios con trimestre <= 60 y trimestreAnt
				// <= 60.
				// 2.1 Primero actualizamos email en objeto resumen si es necesario.
				Resumen resumen = resumenService.findByRut(rut);
				if (resumen != null) {
					// TODO: extraer como un método aparte. usar aquí y en
					// sincronizacionUsuariosCaja() ---->
					if (!resumen.getEmail().trim().equals(stock.getCorreoElectronico())) {
						logger.info("Actualizando nuevo correo informado por MUVU: " + stock.getCorreoElectronico()
								+ ", anterior: " + resumen.getEmail());
						resumen.setEmail(stock.getCorreoElectronico());
					}

					// 2.2 Si corresponder darlo de baja, lo hacemos primero en tabla Resumen
					if (stock.getTrimestre().floatValue() <= 60 && stock.getTrimestreAnt().floatValue() <= 60) {
						logger.info("Usuario debe ser dado de baja. Buscando en tabla Resumen, Rut: " + rut);

						resumen.setFechaBaja(new Date());
						resumen.setEstado("No Vigente");
						resumen.setMotivo(Configuraciones.getConfig("muvu.stock.resumen.baja"));
						if (!resumen.getEmail().trim().equals(stock.getCorreoElectronico())) {
							logger.info("Actualizando nuevo correo informado por MUVU: " + stock.getCorreoElectronico()
									+ ", anterior: " + resumen.getEmail());
							resumen.setEmail(stock.getCorreoElectronico());
						}

						logger.info("Actualizando Resumen, Rut: " + rut);
						resumenService.updateResumen(resumen);

						// 2.2. Seguido en bitácora (solo si existe en resumen).
						AfiliadoVo data_afiliado = new AfiliadoVo();
						// Afiliado en esta etapa queda siempre No Vigente
						data_afiliado.setEstado("0");
						data_afiliado.setRut(resumen.getRutAfiliado() + "-" + resumen.getDvAfiliado());
						data_afiliado.setEmail(resumen.getEmail());
						data_afiliado.setFechaBaja(new Date());
						data_afiliado.setFechaNacimiento(resumen.getFechaNacimiento());
						data_afiliado.setCausa(Configuraciones.getConfig("muvu.stock.bitacora.baja"));
						bitacoraService.insertBitacora(data_afiliado);
					}
				} else {
					logger.warn("Rut " + rut + " no encontrado en tabla Resumen, no procesado");
				}
			}

			logger.info("Fin proceso Stock Muvu ");
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error Proceso Stock Muvu ", e);
		}
		return false;
	}

	/*
	 * Se revisan TODOS los registros vigentes en tabla resumen si estan inactivos
	 * en Afiliados de la Araucana (si sigue siendo afiliado a La Caja y si no ha
	 * fallecido)(non-Javadoc)
	 * 
	 * @see
	 * cl.laaraucana.muvu.services.ProcesosMuvu#sincronizacionUsuarios(java.lang.
	 * String)
	 */
	@Override
	public void sincronizacionUsuarios(String periodo) throws Exception {
		logger.info("Iniciando el proceso de sincronización usuarios inactivos Caja - Muvu para periodo : " + periodo);
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}
		ParamVO p = new ParamVO();
		Calendar cal = new GregorianCalendar();
		cal.setTime(new SimpleDateFormat("yyyyMM").parse(periodo));
		cal.set(Calendar.DAY_OF_MONTH, 1); // Fecha comienzo = primer dia del mes.
		p.setFechaComienzo(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // Fecha termino = último dia del
																						// mes.
		p.setFechaTermino(cal.getTime());
		List<AfiliadoSuraVo> afiliados = (List<AfiliadoSuraVo>) sqlMap
				.queryForList("formweb.consultaAfiliadosInactivosCaja", p);

		for (AfiliadoSuraVo afiliado : afiliados) {
			// dar de baja en Resumen e insertar registro en bitácora.

			// 1. Resumen
			int rut = afiliado.getRut().intValue();
			Resumen resumen = resumenService.findByRut(rut);
			if (resumen != null) {
				resumen.setFechaBaja(new Date());
				resumen.setEstado("No Vigente");
				resumen.setMotivo(Configuraciones.getConfig("muvu.sinc.resumen.baja"));
				logger.info("Actualizando Resumen, Rut: " + rut + ", marcandolo como no vigente.");
				resumenService.updateResumen(resumen);

				// 2. Seguido en bitácora, solo si existe en resumen.
				AfiliadoVo data_afiliado = new AfiliadoVo();
				// Afiliado en esta etapa queda siempre No Vigente
				data_afiliado.setEstado("0");
				data_afiliado.setRut(resumen.getRutAfiliado() + "-" + resumen.getDvAfiliado());
				data_afiliado.setEmail(resumen.getEmail());
				data_afiliado.setFechaBaja(new Date());
				data_afiliado.setFechaNacimiento(resumen.getFechaNacimiento());
				data_afiliado.setCausa(Configuraciones.getConfig("muvu.sinc.bitacora.baja"));
				bitacoraService.insertBitacora(data_afiliado);
			} else {
				logger.warn("Rut " + rut + " no encontrado en tabla Resumen, no procesado");
			}

		}

	}

	@Override
	public boolean procesarBajas(String periodo) throws Exception {
		logger.info("Iniciando el proceso para bajas Muvu para periodo : " + periodo);
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		ParamVO p = new ParamVO();
		Date periodoProceso = new SimpleDateFormat("yyyyMMdd").parse(periodo + "01");
		Calendar cal = new GregorianCalendar();
		cal.setTime(periodoProceso);
		cal.set(Calendar.DAY_OF_MONTH, 1); // Fecha comienzo = primer dia del mes.
		p.setFechaComienzo(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // Fecha termino = último dia del
																						// mes.
		p.setFechaTermino(cal.getTime());
		List<AfiliadoMuvuVo> afiliados = (List<AfiliadoMuvuVo>) sqlMap.queryForList("formweb.consultaAfiliadosBajaMuvu",
				p);
		if (afiliados.size() > 0) {
			// TODO: actualmente queda en C:\Program Files
			// (x86)\IBM\WebSphere\AppServer\profile
			String archivoBajasMuvu = "BAJAS_MUVU_" + periodo;
			String uploadFile = Configuraciones.getConfig("muvu.carpeta.bajas") + archivoBajasMuvu+".txt";
			GeneratorXLS xls = new GeneratorXLS(uploadFile);
			// Configurando columnas a desplegar que deben estar definidas en el entity,
			// sino no existe la variable dr declara automáticamente como una constante.
			String[] columnas = { "rut", "dv", "correoElectronico", "motivo" };

			// Configurando columnas de titulos.
			String[] titulos = { "rut_afiliado", "dv_afiliado", "correo_electronico", "motivo" };

			// se invoca la generación
			xls.generarCSVfromCollection(afiliados, columnas, titulos);
			// TODO: COPIAR ARCHIVO A FTP
			try {
				String host = Configuraciones.getConfig("hostFTP");
				int port = Integer.parseInt(Configuraciones.getConfig("portFTP"));
				String username = Configuraciones.getConfig("usuarioFTP");
				String password = Configuraciones.getConfig("claveFTP");
				String directory = Configuraciones.getConfig("muvu.bajas.FTPCarpeta");

				// SFTP
				logger.info("Conectando a host (sFTP) : " + host + ":" + port + ", con usuario:" + username);
				ChannelSftp sftp = sFTPService.connect(host, port, username, password);
				logger.info("is channel connected= " + sftp.isConnected());

				if (!sftp.isConnected()) {

					logger.warn("Saliendo Proceso bajas Muvu, Error al conectar a sitio SFTP");
					return false;
				}

				logger.info("Subiendo a carpeta ftp " + directory + ", archivo " + uploadFile);
				sFTPService.upload(directory, uploadFile, sftp);
				sftp.exit();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error subir archivo bajas Muvu a FTP", e);
				return false;
			}
		} else {
			logger.info("No hay afiliados a procesar en bajas Muvu");
		}
		return true;
	}
}
