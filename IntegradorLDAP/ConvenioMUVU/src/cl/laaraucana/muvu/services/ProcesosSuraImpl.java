package cl.laaraucana.muvu.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jcraft.jsch.ChannelSftp;

import java.io.File;
import cl.laaraucana.jfactory.utils.GeneratorXLS;
import cl.laaraucana.muvu.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.muvu.ibatis.vo.AfiliadoSuraVo;
import cl.laaraucana.muvu.services.vo.ParamVO;
import cl.laaraucana.muvu.util.Configuraciones;

@Service
public class ProcesosSuraImpl implements ProcesosSura {

	private static final Logger logger = Logger.getLogger(ProcesosMuvuImpl.class);

	@Autowired
	private FTPService ftpservice;

	private List<AfiliadoSuraVo> getAfiliadosAltas(Date fechaProceso) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");// TODO: logear algo en log4j
		}
		ParamVO p = new ParamVO();
		Calendar c = new GregorianCalendar();
		c.setTime(fechaProceso);
		c.set(Calendar.DAY_OF_MONTH, 1); // Fecha comienzo = primer dia del mes.
		p.setFechaComienzo(c.getTime());
		// System.out.println("First day of month is:" + c.getTime());
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH)); // Fecha termino = último dia del mes.
		p.setFechaTermino(c.getTime());
		// System.out.println("Last day of month is:" + c.getTime());
		List<AfiliadoSuraVo> afiliados = (List<AfiliadoSuraVo>) sqlMap
				.queryForList("formweb.consultaAfiliadosSuraAltas", p);

		return afiliados;
	}

	private List<AfiliadoSuraVo> getAfiliadosBajas(Date fechaProceso) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");// TODO
		}
		ParamVO p = new ParamVO();
		Calendar c = new GregorianCalendar();
		c.setTime(fechaProceso);
		c.set(Calendar.DAY_OF_MONTH, 1); // Fecha comienzo = primer dia del mes.
		p.setFechaComienzo(c.getTime());
		// System.out.println("First day of month is:" + c.getTime());
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH)); // Fecha termino = último dia del mes.
		p.setFechaTermino(c.getTime());
		// System.out.println("Last day of month is:" + c.getTime());
		List<AfiliadoSuraVo> afiliados = (List<AfiliadoSuraVo>) sqlMap
				.queryForList("formweb.consultaAfiliadosSuraBajas", p);

		return afiliados;
	}

	@Override
	public boolean procesarAltas(String periodoProceso) throws Exception {
		logger.info("Iniciando el proceso de altas Sura para periodo : " + periodoProceso);
		Calendar cal = new GregorianCalendar();
		cal.setTime(new SimpleDateFormat("yyyyMM").parse(periodoProceso));
		List<AfiliadoSuraVo> afiliados = getAfiliadosAltas(cal.getTime());

		if (afiliados.size() > 0) {
			// TODO: actualmente queda en C:\Program Files
			// (x86)\IBM\WebSphere\AppServer\profile
			String filepath = Configuraciones.getConfig("sura.carpeta.altas") + "/ALTAS_SURA_" + periodoProceso
					+ ".txt";
			GeneratorXLS xls = new GeneratorXLS(filepath);

			// Configurando columnas a desplegar que deben estar definidas en el entity,
			// sino no existe la variable dr declara automáticamente como una constante.
			String[] columnas = { "poliza", "grupos", "ParsedVigenciaInicialTitular", "tipoAsegurado", "rut", "dv",
					"rut", "nombres", "ApellidoPaterno", "ApellidoMaterno", "ParsedFechaNacimiento", "rut", "dv",
					"nombresCarga", "apellidoPaternoCarga", "apellidoMaternoCarga", "fechaNacimientoCarga", "moneda",
					"renta", "tipoCuenta", "numeroCuenta", "codBanco", "celular", "correoElectronico", "codModulo",
					"continuidadCobertura", "sexo", "estatura", "peso" };

			// Configurando columnas de titulos.
			String[] titulos = { "poliza", "grupos", "vigencia_inicial", "tipo_asegurado", "rut", "dv",
					"rut_titular_carga", "nombres", "Apellido_paterno", "Apellido_materno", "fecha_nacimiento",
					"rut_carga", "dv_carga", "nombres_carga", "apellido_paterno_carga", "apellido_materno_carga",
					"fecha_nacimiento_carga", "moneda", "renta", "tipo_cuenta", "numero_cuenta", "cod_banco", "celular",
					"mail", "cod_modulo", "continuidad_cobertura", "sexo", "estatura", "peso" };

			// se invoca la generación
			xls.generarCSVfromCollection(afiliados, columnas, titulos);
			try {
				String host = Configuraciones.getConfig("sura.hostFTP");
				int port = Integer.parseInt(Configuraciones.getConfig("sura.portFTP"));
				String username = Configuraciones.getConfig("sura.usuarioFTP");
				String password = Configuraciones.getConfig("sura.claveFTP");
				String directory = Configuraciones.getConfig("sura.FTPCarpeta.altas");

				uploadFileViaFTP(host, port, username, password, directory, filepath);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error subir archivo altas sura a FTP", e);
				return false;
			}
		} else {
			logger.info("No hay afiliados a procesar en altas Sura");
		}
		return true;
	}

	public void uploadFileViaFTP(String host, int port, String username, String password, String directory,
			String localFilePath) throws Exception {
		// FTP
		logger.info("Conectando a host (FTP) : " + host + ":" + port + ", con usuario:" + username);
		ftpservice.connectToFTP(host, port, username, password);

		logger.info("Subiendo a carpeta ftp " + directory + ", archivo " + localFilePath);
		File fileToUpload = new File(localFilePath);
		ftpservice.uploadFileToFTP(fileToUpload, directory, fileToUpload.getName());
		ftpservice.disconnectFTP();
	}

	@Override
	public boolean procesarBajas(String periodoProceso) throws Exception {
		logger.info("Iniciando el proceso de bajas Sura para periodo : " + periodoProceso);
		Calendar cal = new GregorianCalendar();
		cal.setTime(new SimpleDateFormat("yyyyMM").parse(periodoProceso));

		List<AfiliadoSuraVo> afiliados = getAfiliadosBajas(cal.getTime());

		if (afiliados.size() > 0) {
			String filepath = Configuraciones.getConfig("sura.carpeta.bajas") + "/BAJAS_SURA_" + periodoProceso
					+ ".txt";
			GeneratorXLS xls = new GeneratorXLS(filepath);

			// Configurando columnas a desplegar que deben estar definidas en el entity,
			// sino no existe la variable dr declara automáticamente como una constante.
			String[] columnas2 = { "poliza", "grupos", "rut", "dv", "rut", "rol", "ParsedFechaExclusion" };

			// Configurando columnas de titulos.
			String[] titulos2 = { "poliza", "grupos", "rut", "dv", "rut", "rol", "fecha_exclusion" };

			// se invoca la generación
			xls.generarCSVfromCollection(afiliados, columnas2, titulos2);
			try {
				String host = Configuraciones.getConfig("sura.hostFTP");
				int port = Integer.parseInt(Configuraciones.getConfig("sura.portFTP"));
				String username = Configuraciones.getConfig("sura.usuarioFTP");
				String password = Configuraciones.getConfig("sura.claveFTP");
				String directory = Configuraciones.getConfig("sura.FTPCarpeta.bajas");

				uploadFileViaFTP(host, port, username, password, directory, filepath);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error subir archivo altas sura a FTP", e);
				return false;
			}

		} else {
			logger.info("No hay afiliados a procesar en bajas Sura");
		}

		return true;

	}
}
