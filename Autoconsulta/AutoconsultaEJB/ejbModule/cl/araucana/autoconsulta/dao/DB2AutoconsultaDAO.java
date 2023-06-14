package cl.araucana.autoconsulta.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import cl.araucana.autoconsulta.common.Constants;
import cl.araucana.autoconsulta.common.Funciones;
import cl.araucana.autoconsulta.vo.ActividadVO;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.AhorranteVO;
import cl.araucana.autoconsulta.vo.AuditoriaVO;
import cl.araucana.autoconsulta.vo.CantidadChequesVO;
import cl.araucana.autoconsulta.vo.CantidadEstadoVO;
import cl.araucana.autoconsulta.vo.CargaFamiliarVO;
import cl.araucana.autoconsulta.vo.ChequeVO;
import cl.araucana.autoconsulta.vo.ClaveVO;
import cl.araucana.autoconsulta.vo.CodigoDescripcionVO;
import cl.araucana.autoconsulta.vo.ComisionVO;
import cl.araucana.autoconsulta.vo.ConceptoChequeVO;
import cl.araucana.autoconsulta.vo.ConsultaCreditoVO;
import cl.araucana.autoconsulta.vo.ConvenioEmpresaVO;
import cl.araucana.autoconsulta.vo.CreditoCuotasVO;
import cl.araucana.autoconsulta.vo.CuentaAhorroVO;
import cl.araucana.autoconsulta.vo.CuotaCreditoVO;
import cl.araucana.autoconsulta.vo.DateVO;
import cl.araucana.autoconsulta.vo.DatosAsignacionFamiliarVO;
import cl.araucana.autoconsulta.vo.DatosComplementariosVO;
import cl.araucana.autoconsulta.vo.DatosTitularCreditoVO;
import cl.araucana.autoconsulta.vo.DatosTrabajadoresLiquidacionesVO;
import cl.araucana.autoconsulta.vo.DatosValidacionVO;
import cl.araucana.autoconsulta.vo.DetalleCartolaVO;
import cl.araucana.autoconsulta.vo.DeudaIntercajaVO;
import cl.araucana.autoconsulta.vo.DoubleVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.EmpresaAfiliadoVO;
import cl.araucana.autoconsulta.vo.EmpresaPublicaVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.InformacionCreditoVO;
import cl.araucana.autoconsulta.vo.IntVO;
import cl.araucana.autoconsulta.vo.LicenciaMedicaCertificadoVO;
import cl.araucana.autoconsulta.vo.LicenciaMedicaDetalleVO;
import cl.araucana.autoconsulta.vo.LicenciaMedicaResumenVO;
import cl.araucana.autoconsulta.vo.LicenciaMedicaVO;
import cl.araucana.autoconsulta.vo.LiquidacionesVO;
import cl.araucana.autoconsulta.vo.MontoVO;
import cl.araucana.autoconsulta.vo.MovimientosLiquidacionVO;
import cl.araucana.autoconsulta.vo.OficinasSucursalesVO;
import cl.araucana.autoconsulta.vo.PensionadoVO;
import cl.araucana.autoconsulta.vo.PeriodoVO;
import cl.araucana.autoconsulta.vo.PublicidadVO;
import cl.araucana.autoconsulta.vo.PublicoVO;
import cl.araucana.autoconsulta.vo.ReajusteVO;
import cl.araucana.autoconsulta.vo.ResMensualPrestCompVO;
import cl.araucana.autoconsulta.vo.RutVO;
import cl.araucana.autoconsulta.vo.StringVO;
import cl.araucana.autoconsulta.vo.TipVO;
import cl.araucana.autoconsulta.vo.ValorValidableVO;
import cl.araucana.autoconsulta.vo.ValorCuotaActualVO;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import com.schema.util.FileSettings;
import com.schema.util.GeneralException;
import com.schema.util.dao.DB2Utils;

/**
 * @author asepulveda
 *
 */
public class DB2AutoconsultaDAO implements AutoconsultaDAO {
	Logger logger = Logger.getLogger(DB2AutoconsultaDAO.class);
	private final static String PREFIX = "DB2-";
	private DB2Utils empresaUtil;
	private DB2Utils afiliadosUtil;
	private DB2Utils licenciasUtil;
	private DB2Utils autoconsultaUtil;
	private DB2Utils generalUtil;
	private DB2Utils creditosUtil;
	private DB2Utils recuperacionCreditosUtil;
	private DB2Utils beneficiosUtil;
	private DB2Utils leasingUtil;
	private DB2Utils pensionadosUtil;
	private DB2Utils tesoreriaUtil;
	private DB2Utils liquidacionesReembolsosUtil; //RSA
	private DB2Utils resumenMensualPrestacionesComplementariasUtil; //RSA
	private DB2Utils publicoUtil;
	private DB2Utils empresaPublicaUtil;
	private String empresasDatabase;
	private String afiliadosDatabase;
	private String licenciasDatabase;
	private String autoconsultaDatabase;
	private String generalDatabase;
	private String creditosDatabase;
	private String recuperacionCreditosDatabase;
	private String beneficiosDatabase;
	private String leasingDatabase;
	private String pensionadosDatabase;
	private String tesoreriaDatabase;
	private String liquidacionesReembolsosDatabase; //RSA
	private String resumenMensualPrestacionesComplementariasDatabase; //RSA
	private String clientesDatabase;
	private String publicoDatabase;
	private String empresaPublicaDatabase;

	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	public DB2AutoconsultaDAO() {
		// Carga e datasources y creaciòn de DB2Util
		String datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/empresas");
		empresaUtil = new DB2Utils(datasource, this);
		datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/afiliados");
		afiliadosUtil = new DB2Utils(datasource, this);
		datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/publicos");
		publicoUtil = new DB2Utils(datasource, this);
		empresaPublicaUtil = new DB2Utils(datasource, this);
		datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/licencias");
		licenciasUtil = new DB2Utils(datasource, this);
		datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/autoconsulta");
		autoconsultaUtil = new DB2Utils(datasource, this);
		datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/general");
		generalUtil = new DB2Utils(datasource, this);
		datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/creditos");
		creditosUtil = new DB2Utils(datasource, this);
		datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/recuperaciones-creditos");
		recuperacionCreditosUtil = new DB2Utils(datasource, this);
		datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/beneficios");
		beneficiosUtil = new DB2Utils(datasource, this);
		datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/leasing");
		leasingUtil = new DB2Utils(datasource, this);
		datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/pensionados");
		pensionadosUtil = new DB2Utils(datasource, this);
		datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/tesoreria-araucana");
		tesoreriaUtil = new DB2Utils(datasource, this);
		datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/liquidacion-reembolsos");
		liquidacionesReembolsosUtil = new DB2Utils(datasource, this);
		datasource = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/jdbc/resumen-mensual-prestaciones-complementarias");
		resumenMensualPrestacionesComplementariasUtil = new DB2Utils(datasource, this);
		// Nombre de bases de datos			 
		empresasDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/empresas");
		afiliadosDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/afiliados");
		licenciasDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/licencias");
		autoconsultaDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/autoconsulta");
		generalDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/general");
		creditosDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/creditos");
		recuperacionCreditosDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/recuperaciones-creditos");
		beneficiosDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/beneficios");
		leasingDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/leasing");
		pensionadosDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/pensionados");
		tesoreriaDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/tesoreria-araucana");
		liquidacionesReembolsosDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/liquidacion-reembolsos");
		resumenMensualPrestacionesComplementariasDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				"/application-settings/databases/resumen-mensual-prestaciones-complementarias");
		clientesDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/clientes");
		publicoDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/base-publica");
		empresaPublicaDatabase = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/databases/base-publica");
	}

	/**
	 * Si la fecha entregada es igual a "0001-01-01", entonces retorna null
	 * Si la fecha es distinta a "0001-01-01" retorna a misma fecha entregada
	 * @param fecha
	 * @return Date ó null
	 * @throws SQLException
	 */
	private static Date validaFechaNula(java.sql.Date fecha) throws SQLException {
		Date fechaSalida = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date fechaDate = fecha;
			String fechaFormateada = formato.format(fecha);
			if (!fechaFormateada.equals("0001-01-01"))
				fechaSalida = fecha;
		} catch (Exception e) {
			//
		}
		return fechaSalida;
	}

	// Datos validacion Certificado
	/** 
	 * Registra los datos de identificación de un certificado
	 * @param DatosValidacionVO datosValidacion
	 * @return void
	 */
	public void insertDatosValidacion(DatosValidacionVO datosValidacion) throws Exception, BusinessException {
		String command = "INSERT INTO " + autoconsultaDatabase + ".AT01F1 " + "(ICID, ICTIPO, ICFECHA, ICRUT, ICFULLRUT, ICNOMBRE) " + "VALUES (?," + "?," + "?," + "?," + "?," + "?)";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, datosValidacion.getId());
		autoconsultaUtil.getStatement().setInt(2, datosValidacion.getTipo());
		autoconsultaUtil.getStatement().setTimestamp(3, new java.sql.Timestamp(datosValidacion.getFecha().getTime()));
		autoconsultaUtil.getStatement().setLong(4, datosValidacion.getRut());
		autoconsultaUtil.getStatement().setString(5, datosValidacion.getFullRut());
		autoconsultaUtil.getStatement().setString(6, datosValidacion.getFullNombre());
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		autoconsultaUtil.execute();
	}

	/** 
	 * Registra los valores validables de un certificado
	 * @param DatosValidacionVO datosValidacion
	 * @return void
	 */
	public void insertValorValidable(long id, ValorValidableVO variableValor) throws Exception, BusinessException {
		String command = "INSERT INTO " + autoconsultaDatabase + ".AT02F1 " + "(ICID, DVVARIABLE, DVVALOR) " + "VALUES (?," + "?," + "?)";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, id);
		autoconsultaUtil.getStatement().setString(2, variableValor.getVariable());
		autoconsultaUtil.getStatement().setString(3, variableValor.getValor());
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		autoconsultaUtil.execute();
	}

	/** 
	 * Obtiene Collection con un Objeto con la información básica del certificado
	 * @param long, id del certificado
	 * @return Collection de DatosValidacionVO
	 */
	public Collection getDatosValidacion(long id) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosValidacion[" + id + "]");
		logger.debug("ID: " + id);
		String command = "SELECT ICID, ICTIPO, ICFECHA, ICRUT, ICFULLRUT, ICNOMBRE " + "FROM " + autoconsultaDatabase + ".AT01F1 " + "WHERE ICID = ?";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, id);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(DatosValidacionVO.class);
	}

	/**
	 * @return DatosValidacionVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static DatosValidacionVO buildDatosValidacionVO(ResultSet ors) throws SQLException {
		DatosValidacionVO vo = new DatosValidacionVO();
		vo.setId(ors.getLong("ICID"));
		vo.setTipo(ors.getInt("ICTIPO"));
		vo.setFecha(ors.getDate("ICFECHA"));
		vo.setRut(ors.getLong("ICRUT"));
		vo.setFullNombre(ors.getString("ICNOMBRE"));
		vo.setFullRut(ors.getString("ICFULLRUT"));
		return vo;
	}

	/** 
	 * Obtiene Collection con los valores validables de un certificado
	 * @param long, id del certificado
	 * @return Collection de ValorValidableVO
	 */
	public Collection getValoresValidables(long id) throws Exception, BusinessException {
		System.out.println("DAO: metodo getValoresValidables[" + id + "]");
		logger.debug("ID: " + id);
		String command = "SELECT DVVARIABLE, DVVALOR " + "FROM " + autoconsultaDatabase + ".AT02F1 " + "WHERE ICID = ?";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, id);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(ValorValidableVO.class);
	}

	/**
	 * @return ValorValidableVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static ValorValidableVO buildValorValidableVO(ResultSet ors) throws SQLException {
		ValorValidableVO vo = new ValorValidableVO();
		vo.setVariable(ors.getString("DVVARIABLE"));
		vo.setValor(ors.getString("DVVALOR"));
		return vo;
	}

	// Fin Datos validacion Certificado
	// LICENCIAS MEDICAS
	/** 
	 * Obtiene Collection de licencias medicas de un empleado
	 * @param long, rut del empleado
	 * @param String, fechaDesde Fecha que indica a partir de cuando se comienza la busqueda
	 * @return Collection de LicenciaMedicaVO
	 */
	public Collection getListaLicenciasMedicasByEmpleado(long rut, String fechaDesde) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaLicenciasMedicasByEmpleado[" + rut + "][" + fechaDesde + "]");
		logger.debug("Rut: " + rut);
		logger.debug("Fecha Desde: " + fechaDesde);
		String command = "SELECT A.LICDESFEC, A.LICDIAS, A.LICEST, A.PAGFEC, A.OFIPGO, " + "A.PAGRESDIA, A.LICMONUP, A.LICCONIND, A.LICOBS1, A.LICOBS2, A.LICOBS3, 1 \"VISADA\", A.LICIMPNUM, "
				+ "A.LICFECING \"FECRECEP\", '' \"RESPONSA\", A.LICDECFEC AS OFIORIG, '' CMDA " + "FROM " + licenciasDatabase + ".ILF1000 A  " + "WHERE A.AFIRUT = ? AND A.LICFECING > ? " + "UNION "
				+ "SELECT B.LICDESFEC, B.LICDIAS, "
				// TODO poner VISADA = 0. Cambiada a 1 para test.
				+ "2, 0, -1, 0, 0 , 0 , 0 , 0, 0, 0 \"VISADA\", LICIMPNUM, " + "0 \"FECRECEP\", '' \"RESPONSA\", B.oficod, '' CMDA " + "FROM " + licenciasDatabase + ".ILL1251 B "
				+ "WHERE B.AFIRUT= ? AND LICFECING > ?  " + "UNION " 
				+ "SELECT C.LICDESFEC, C.DIASMED \"LICDIAS\", C.ESTADO \"LICEST\", "
				// TODO poner VISADA = 2. Cambiada a 1 para test.
				+ "0, -1, 0, 0, 0, 0, 0, 0, 2 \"VISADA\", C.LICIMPNUM, " + "C.FECRECEP, C.RESPONSA, C.OFIORIG AS OFIORIG, '' CMDA " + "FROM " + licenciasDatabase + ".ILF8600 C " + "WHERE C.AFIRUT= ? AND C.FECRECEP > ? "
				+ "AND C.ESTADO <> 4 " + " ORDER BY 1 DESC";
		// prepara llamado
		System.out.println("command: " + command); 
		
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setLong(1, rut);
		licenciasUtil.getStatement().setString(2, fechaDesde);
		licenciasUtil.getStatement().setLong(3, rut);
		licenciasUtil.getStatement().setString(4, fechaDesde);
		licenciasUtil.getStatement().setLong(5, rut);
		licenciasUtil.getStatement().setString(6, fechaDesde);
		
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(LicenciaMedicaVO.class);
	}
	
	/**
	 * VISADA = 1 para licencias obtenidas desde ILF1000 - Registro control S.I.L. (F-92)
	 * VISADA = 2 para licencias obtenidas desde ILF8600 - Envio a compin
	 * @param rutAfiliado
	 * @param nroLicencia
	 * @param fechaDesde
	 * @return
	 * @throws GeneralException
	 * @throws SQLException
	 */
	public Collection getLicenciaMedicaByNro(long rutAfiliado, long nroLicencia, String fechaDesde) throws GeneralException, SQLException{
		String command = 
			"SELECT C.LICDESFEC, C.DIASMED LICDIAS, C.ESTADO LICEST, 0 PAGFEC, -1 OFIPGO, 0 PAGRESDIA, 0 LICMONUP, 0 LICCONIND, 0 LICOBS1, 0 LICOBS2, 0 LICOBS3,"
			+ " 2 \"VISADA\", C.LICIMPNUM, " + "C.FECRECEP, C.RESPONSA, C.OFIORIG AS OFIORIG, D.CMDA " + "FROM " + licenciasDatabase + ".ILF8600 C, cmdta.cm01f1 D " 
			+ "WHERE C.AFIRUT= ? AND C.FECRECEP > ? "
			+ "AND C.ESTADO <> 4 AND C.LICIMPNUM=? AND D.CMBA = C.OFIORIG " 
		+ "UNION "
		+"SELECT A.LICDESFEC, A.LICDIAS, A.LICEST, A.PAGFEC, A.OFIPGO, " + "A.PAGRESDIA, A.LICMONUP, A.LICCONIND, A.LICOBS1, A.LICOBS2, A.LICOBS3, 1 \"VISADA\", A.LICIMPNUM, "
		+ "A.LICFECING \"FECRECEP\", '' \"RESPONSA\", A.LICDECFEC AS OFIORIG, D.CMDA " + "FROM " + licenciasDatabase + ".ILF1000 A , cmdta.cm01f1 D " 
		+ "WHERE D.CMBA = A.LICDECFEC AND A.AFIRUT = ? AND A.LICFECING > ? AND A.LICIMPNUM=? " 
		+ " ORDER BY VISADA, 1 DESC";
		System.out.println("command: " + command); 
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setLong(1, rutAfiliado);
		licenciasUtil.getStatement().setString(2, fechaDesde);
		licenciasUtil.getStatement().setLong(3, nroLicencia);
		
		licenciasUtil.getStatement().setLong(4, rutAfiliado);
		licenciasUtil.getStatement().setString(5, fechaDesde);
		licenciasUtil.getStatement().setLong(6, nroLicencia);
		return licenciasUtil.executeQuery(LicenciaMedicaVO.class);
	}
	
	public Collection obtenerFeriadosPeriodo(String periodo) throws GeneralException, SQLException{
		
		String command = "select * from cmdta.cm18a1 where sajkl5s = ?";
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setLong(1, Long.parseLong(periodo));
		System.out.println("command: " + command); 
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(List.class);
	}
	
	/**
	 * @return LicenciaMedicaVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static LicenciaMedicaVO buildLicenciaMedicaVO(ResultSet ors) throws SQLException {
		LicenciaMedicaVO vo = new LicenciaMedicaVO();
		vo.setCodOficinaPago(ors.getInt("OFIPGO"));
		vo.setDiasDePago(ors.getString("PAGRESDIA"));
		vo.setDiasLicencia(ors.getInt("LICDIAS"));
		vo.setCodigoEstadoLicencia(ors.getString("LICEST"));
		vo.setFechaDePago(ors.getString("PAGFEC"));
		vo.setFechaDesde(ors.getString("LICDESFEC"));
		vo.setCodigoFormaDePago(ors.getString("LICCONIND"));
		vo.setMontoAPagar(ors.getInt("LICMONUP"));
		vo.setNumeroLicencia(ors.getLong("LICIMPNUM"));
		vo.setCodigoObservacion1(ors.getInt("LICOBS1"));
		vo.setCodigoObservacion2(ors.getInt("LICOBS2"));
		vo.setCodigoObservacion3(ors.getInt("LICOBS3"));
		vo.setVisada(ors.getInt("VISADA"));
		vo.setFechaRecepcion(ors.getString("FECRECEP"));
		vo.setAnalistaReceptor(ors.getString("RESPONSA"));
		
		//Mapeo de campos nuevos
		vo.setCodOfiOrigen(ors.getString("OFIORIG"));
		vo.setCodRegionOrigen(ors.getString("CMDA"));
		return vo;
	}
	
	public static List buildList(ResultSet ors) throws SQLException{
		List lista = new ArrayList();
		
		for(int i=2;i<=ors.getMetaData().getColumnCount();i++){
				Integer feriado = new Integer(ors.getInt(i));
				lista.add(feriado);
		}
		return lista;
	}
	
	public String obtenerFechaResolucion(long rutAfiliado, long nroLicencia, String fechaHasta) throws SQLException, GeneralException, NumberFormatException, ParseException{
		Collection resFinal = null;
		//Si existe en ILL8602
		
		String command ="Select B.FECRESOL AS STRING " +
		"from (SELECT * FROM LIEXP.ILL8602 where ESTADO = 5 or ESTADO=6 AND LICIMPNUM=? AND AFIRUT=?) A " +
		" join (SELECT * FROM LIEXP.ILF8610 where FECRESOL <>0 AND LICIMPNUM=? AND AFIRUT=?) B ON " +
		"A.LICIMPNUM=B.LICIMPNUM AND A.AFIRUT=B.AFIRUT";
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setLong(1, nroLicencia);
		licenciasUtil.getStatement().setLong(2, rutAfiliado);
		licenciasUtil.getStatement().setLong(3, nroLicencia);
		licenciasUtil.getStatement().setLong(4, rutAfiliado);
		resFinal = licenciasUtil.executeQuery(StringVO.class);
		
		if(resFinal == null || resFinal.size()==0){
			command = "Select LICREAFEC AS STRING from " +licenciasDatabase + ".ILF1011 " +
			"where LICREAFEC > 20080400 AND LICIMPNUM=? AND AFIRUT=? AND LICHASFEC = ?";
			licenciasUtil.prepareCall(command);
			licenciasUtil.getStatement().setLong(1, nroLicencia);
			licenciasUtil.getStatement().setLong(2, rutAfiliado);
			licenciasUtil.getStatement().setLong(3, Long.parseLong(fechaHasta));
			resFinal = licenciasUtil.executeQuery(StringVO.class);
		}
		
			/*String command = "Select count(*) AS STRING from " +licenciasDatabase + ".ILL8602 " +
				"where ESTADO = 5 or ESTADO =6 AND LICIMPNUM=? AND AFIRUT=?";
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setLong(1, nroLicencia);
		licenciasUtil.getStatement().setLong(2, rutAfiliado);
		resCount = licenciasUtil.executeQuery(StringVO.class);
		cant = ((StringVO) ((List) resCount).get(0)).getTexto();
		
			if(!resCount.equals("0")){
			//			-Si Existe(ILF8610) and FECRESOL <>0
			command = "Select FECRESOL AS STRING from " +licenciasDatabase + ".ILF8610 " +
			"where FECRESOL <>0 AND LICIMPNUM=? AND AFIRUT=?";
			licenciasUtil.prepareCall(command);
			licenciasUtil.getStatement().setLong(1, nroLicencia);
			licenciasUtil.getStatement().setLong(2, rutAfiliado);
			System.out.println(command);
			resFinal = licenciasUtil.executeQuery(StringVO.class);
			
			
		}else{//-Si Existe(ILF1011) and LICREAFEC > 20080400
		 command = "Select count(*) AS STRING from " +licenciasDatabase + ".ILF1011 " +
			"where LICREAFEC > 20080400 AND LICIMPNUM=? AND AFIRUT=?";
			licenciasUtil.prepareCall(command);
			licenciasUtil.getStatement().setLong(1, nroLicencia);
			licenciasUtil.getStatement().setLong(2, rutAfiliado);
			resCount = licenciasUtil.executeQuery(StringVO.class);
			cant = ((StringVO) ((List) resCount).get(0)).getTexto();
			
			if(!resCount.equals("0")){*/

			//}
		//}
		
		if(resFinal==null || resFinal.size()==0){
			return "00/00/0000";
		}else{
			List res = (List) resFinal;
			return ((StringVO) res.get(0)).getTexto();
		}
	}

	/** 
	 * Recupera Collection de Observaciones predefinidas existentes
	 * @return Collection de CodigoDescripcionVO
	 */
	public Collection getListaObservaciones() throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaObservaciones");
		String command = "SELECT AT20CO \"" + Constants.ALIAS_CODIGO + "\" , " + "AT20DE \"" + Constants.ALIAS_DESCRIPCION + "\" " + "FROM " + autoconsultaDatabase + ".AT20F1";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(CodigoDescripcionVO.class);
	}

	/** 
	 * Recupera Collection de Oficinas de pago existentes
	 * @return Collection de OficinaVO
	 */
	public Collection getListaOficinasPago() throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaOficinasPago");
		String command = "SELECT CMBA \"" + Constants.ALIAS_CODIGO + "\", " + "CMCA \"" + Constants.ALIAS_DESCRIPCION + "\" " + "FROM " + empresasDatabase + ".CM01L1";
		// prepara llamado
		empresaUtil.prepareCall(command);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + empresaUtil.getStatement().toString());
		
		return empresaUtil.executeQuery(CodigoDescripcionVO.class);
	}

	/** 
	 * Recupera Collection de Instituciones Previcionales
	 * @param String letra
	 * @return Collection de InstitucionPrevicionalVO
	 */
	public Collection getListaInstitucionesPrevicionales(String letra) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaInstitucionesPrevicionales[" + letra + "]");
		String command = "SELECT TCODIGO \"" + Constants.ALIAS_CODIGO + "\", " + "TGLOSA \"" + Constants.ALIAS_DESCRIPCION + "\" " + "FROM " + generalDatabase + ".TAF1000 " + "WHERE TLETRA = ?";
		// prepara llamado
		generalUtil.prepareCall(command);
		generalUtil.getStatement().setString(1, letra);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + generalUtil.getStatement().toString());
		
		return generalUtil.executeQuery(CodigoDescripcionVO.class);
	}

	/** 
	 * Obtiene Collection de empleadores de un empleado
	 * @param long, rut del empleado
	 * @return Collection de EmpresaVO
	 */
	public Collection getListaEmpleadoresByEmpleado(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaEmpleadoresByEmpleado[" + rut + "]");
		String command = "SELECT E.CMNA \"RUT\", E.CMOA \"DV\", E.CMPA \"NOMBRE\", E.CMQA \"ESTADO\", E.CMSA \"TIPO\" " + "FROM " + empresasDatabase + ".CM02L1 E, " + afiliadosDatabase
				+ ".AF03U103 A " + "WHERE A.CMNA=E.CMNA AND A.SE5FAJC=?";
		// prepara llamado
		empresaUtil.prepareCall(command);
		empresaUtil.getStatement().setLong(1, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + empresaUtil.getStatement().toString());
		
		return empresaUtil.executeQuery(EmpresaVO.class);
	}

	/** 
	 * Obtiene Collection de empleadores de un empleado público
	 * @param long, rut del empleado público
	 * @return Collection de EmpresaVO
	 */
	public Collection getListaEmpleadoresByEmpleadoPublico(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaEmpleadoresByEmpleadoPublico[" + rut + "]");
		String command = "SELECT E.CMNA \"RUT\", E.CMOA \"DV\", E.CMPA \"NOMBRE\", E.CMQA \"ESTADO\", E.CMSA \"TIPO\" " + "FROM " + publicoDatabase + ".CM02L1 E, " + publicoDatabase + ".AF03U103 A "
				+ "WHERE A.CMNA=E.CMNA AND A.SE5FAJC=?";
		// prepara llamado
		empresaUtil.prepareCall(command);
		empresaUtil.getStatement().setLong(1, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + empresaUtil.getStatement().toString());
		
		return empresaUtil.executeQuery(EmpresaVO.class);
	}

	/** 
	 * Obtiene Collection de empleadores de un empleado
	 * Los datos los obtiene desde el historico
	 * @param long, rut del empleado
	 * @return Collection de EmpresaVO
	 */
	public Collection getListaHistoricoEmpleadoresByEmpleado(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaHistoricoEmpleadoresByEmpleado[" + rut + "]");
		String command = "SELECT CMNA \"RUT\", '' \"DV\", '' \"NOMBRE\", '' \"ESTADO\", '' \"TIPO\" " + "FROM " + afiliadosDatabase + ".AF08L1 " + "WHERE SE5FAJC = ? "
				+ "AND AF8BA = 'A' AND AF8AA = " + "(SELECT MAX(AF8AA) FROM " + afiliadosDatabase + ".AF08L1 " + "WHERE SE5FAJC = ? AND AF8BA = 'A' )";
		// prepara llamado
		afiliadosUtil.prepareCall(command);
		afiliadosUtil.getStatement().setLong(1, rut);
		afiliadosUtil.getStatement().setLong(2, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + afiliadosUtil.getStatement().toString());
		
		return afiliadosUtil.executeQuery(EmpresaVO.class);
	}

	/** 
	 * Obtiene Collection de empleadores de un empleado público
	 * Los datos los obtiene desde el historico
	 * @param long, rut del empleado público
	 * @return Collection de EmpresaVO
	 */
	public Collection getListaHistoricoEmpleadoresByEmpleadoPublico(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaHistoricoEmpleadoresByEmpleadoPublico[" + rut + "]");
		String command = "SELECT CMNA \"RUT\", '' \"DV\", '' \"NOMBRE\", '' \"ESTADO\", '' \"TIPO\" " + "FROM " + publicoDatabase + ".AF08L1 " + "WHERE SE5FAJC = ? " + "AND AF8BA = 'A' AND AF8AA = "
				+ "(SELECT MAX(AF8AA) FROM " + publicoDatabase + ".AF08L1 " + "WHERE SE5FAJC = ? AND AF8BA = 'A' )";
		// prepara llamado
		publicoUtil.prepareCall(command);
		publicoUtil.getStatement().setLong(1, rut);
		publicoUtil.getStatement().setLong(2, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + publicoUtil.getStatement().toString());
		
		return publicoUtil.executeQuery(EmpresaVO.class);
	}

	/** 
	 * Recupera Collection de Observaciones que un empleado tiene en Compin
	 * @param long, rut del empleado
	 * @param long, numLicencia
	 * @return Collection de ObservacionCompinVO
	 */
	public Collection getListaObservacionesCompinByEmpleado(long rut, long numLicencia) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaObservacionesCompinByEmpleado[" + rut + "][" + numLicencia + "]");
		Collection glosa, glosaCompin;
		String command = "SELECT GLOSA \"" + Constants.ALIAS_STRING + "\" " + "FROM " + licenciasDatabase + ".ILF1200 " + "WHERE AFIRUT = ? AND LICIMPNUM = ?";
		// prepara llamado
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setLong(1, rut);
		licenciasUtil.getStatement().setLong(2, numLicencia);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(StringVO.class);
	}

	/** 
	 * Recupera Collection de Observaciones que un empleado tiene en Compin
	 * @param long, rut del empleado
	 * @param long, numLicencia
	 * @return Collection de ObservacionCompinVO
	 */
	public Collection getListaObservacionesCompinByEmpleado2(long rut, long numLicencia) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaObservacionesCompinByEmpleado2[" + rut + "][" + numLicencia + "]");
		String command2 = "SELECT OBSCOMPIN \"" + Constants.ALIAS_STRING + "\" " + "FROM " + licenciasDatabase + ".ILF1210 " + "WHERE AFIRUT = ? AND LICIMPNUM = ?";
		// prepara llamado
		licenciasUtil.prepareCall(command2);
		licenciasUtil.getStatement().setLong(1, rut);
		licenciasUtil.getStatement().setLong(2, numLicencia);
		
		System.out.println("command: " + command2); 
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(StringVO.class);
	}

	/** 
	 * Recupera Collection de Observaciones que un empleado tiene en Compin de LIEXP/ILF8660
	 * @param long, rut del empleado
	 * @param long, numLicencia
	 * @return Collection de ObservacionCompinVO
	 */
	public Collection getListaObservacionesCompinOcc(long rut, long numLicencia) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaObservacionesCompinOcc[" + rut + "][" + numLicencia + "]");
		String command2 = "SELECT CONCAT(TIPOBSCOM, CONCAT('@', GLORESOL)) \"" + Constants.ALIAS_STRING + "\" " + "FROM " + licenciasDatabase + ".ILF8660 " + "WHERE AFIRUT = ? AND LICIMPNUM = ?";
		// prepara llamado
		licenciasUtil.prepareCall(command2);
		licenciasUtil.getStatement().setLong(1, rut);
		licenciasUtil.getStatement().setLong(2, numLicencia);
		
		System.out.println("command: " + command2); 
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(StringVO.class);
	}

	/** 
	 * Recupera Collection con última observación que un empleado tiene en Compin de LIEXP/ILF8660
	 * para las licencias devueltas por Fonasa
	 * @param long, rut del empleado
	 * @param long, numLicencia
	 * @return Collection de ObservacionCompinVO
	 */
	public Collection getUltimaObsCompinOcc(long rut, long numLicencia) throws Exception, BusinessException {
		System.out.println("DAO: metodo getUltimaObsCompinOcc[" + rut + "][" + numLicencia + "]");
		String command2 = "SELECT CONCAT(TIPOBSCOM, CONCAT('@', GLORESOL)) \"" + Constants.ALIAS_STRING + "\" " + "FROM " + licenciasDatabase + ".ILF8660 " + "WHERE AFIRUT = ? AND LICIMPNUM = ? "
				+ "AND (TIPOBSCOM=4 OR TIPOBSCOM=5) " + "AND HORAOBSCOM=" + "(SELECT MAX(HORAOBSCOM) FROM " + licenciasDatabase + ".ILF8660 " + "WHERE "
				+ "AFIRUT= ? AND LICIMPNUM= ? AND (TIPOBSCOM=4 OR TIPOBSCOM=5) AND " + "FECOBSCOM=" + "(SELECT MAX(FECOBSCOM) FROM " + licenciasDatabase + ".ILF8660 " + "WHERE "
				+ "AFIRUT = ? AND LICIMPNUM = ? AND (TIPOBSCOM=4 OR TIPOBSCOM=5))) " + "AND FECOBSCOM=" + "(SELECT MAX(FECOBSCOM) FROM " + licenciasDatabase + ".ILF8660 " + "WHERE "
				+ "AFIRUT = ? AND LICIMPNUM = ? " + "AND (TIPOBSCOM=4 OR TIPOBSCOM=5))";
		// prepara llamado
		licenciasUtil.prepareCall(command2);
		licenciasUtil.getStatement().setLong(1, rut);
		licenciasUtil.getStatement().setLong(2, numLicencia);
		licenciasUtil.getStatement().setLong(3, rut);
		licenciasUtil.getStatement().setLong(4, numLicencia);
		licenciasUtil.getStatement().setLong(5, rut);
		licenciasUtil.getStatement().setLong(6, numLicencia);
		licenciasUtil.getStatement().setLong(7, rut);
		licenciasUtil.getStatement().setLong(8, numLicencia);
		
		System.out.println("command: " + command2); 
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(StringVO.class);
	}

	/** 
	 * Recupera Collection de Observaciones que un empleado de una licencia No Visada
	 * @param long, rut del empleado
	 * @param long, numLicencia
	 * @return Collection de ObservacionCompinVO
	 */
	public Collection getListaObservacionesCompinByEmpleadoNoVisada(long rut, long numLicencia) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaObservacionesCompinByEmpleadoNoVisada[" + rut + "][" + numLicencia + "]");
		String command2 = "SELECT OBSCOMPIN \"" + Constants.ALIAS_STRING + "\" " + "FROM " + licenciasDatabase + ".ILF1210 " + "WHERE AFIRUT = ? AND LICIMPNUM = ?";
		// prepara llamado
		licenciasUtil.prepareCall(command2);
		licenciasUtil.getStatement().setLong(1, rut);
		licenciasUtil.getStatement().setLong(2, numLicencia);
		
		System.out.println("command: " + command2); 
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(StringVO.class);
	}

	/** 
	 * Recupera Collection de Detalles de las licencias medicas de un empleado
	 * @param long, rut del empleado
	 * @return Collection de LicenciaMedicaDetalleVO
	 */
	public Collection getListaDetallesLicenciasMedicasByEmpleadoB(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaDetallesLicenciasMedicasByEmpleado[" + rut + "]");
		//1Desde	 , 2Hasta	, 3Dias Licencia, 4FechaPago, 5DiasPago,6Montopagar	  , NumLicencia, FolioPago
		String command = "SELECT   A.PAGDESFEC, A.PAGHASFEC, A.PAGLIQDIA, A.PAGFEC, A.PAGRESDIA, A.PAGRESMON, A.LICIMPNUM, A.PAGFOL" + " FROM " + licenciasDatabase + ".ILF6600 A "
				+ " WHERE AFIRUT = ? " + " UNION " + " SELECT   B.PAGDESFEC, B.PAGHASFEC, B.PAGLIQDIA, B.PAGFEC, B.PAGRESDIA, B.PAGRESMON, B.LICIMPNUM, B.PAGFOL " + " FROM " + licenciasDatabase
				+ ".ILF6500 B " + " WHERE AFIRUT = ? " + " UNION " + " SELECT   C.PAGDESFEC, C.PAGHASFEC, C.PAGLIQDIA, C.PAGFEC, C.PAGRESDIA, C.PAGRESMON, C.LICIMPNUM, C.PAGFOL " + " FROM "
				+ licenciasDatabase + ".ILF2500 C " + " WHERE AFIRUT = ? " + " UNION " + " SELECT D.PAGDESFEC, D.PAGHASFEC, D.PAGLIQDIA, D.PAGFEC, D.PAGRESDIA, D.PAGRESMON, D.LICIMPNUM, D.PAGFOL "
				+ " FROM " + licenciasDatabase + ".ILF6510 D " + " WHERE AFIRUT = ? " + " ORDER BY 1 DESC ";
		// prepara llamado
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setLong(1, rut);
		licenciasUtil.getStatement().setLong(2, rut);
		licenciasUtil.getStatement().setLong(3, rut);
		licenciasUtil.getStatement().setLong(4, rut);
		//	licenciasUtil.getStatement().setLong(2, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(LicenciaMedicaDetalleVO.class);
	}
	
	public Collection getListaDetallesLicenciasMedicasByEmpleado(long rut) throws Exception, BusinessException {
		System.out.println("ASM__ DAO: metodo getListaDetallesLicenciasMedicasByEmpleado[" + rut + "]");
		logger.debug("Rut: " + rut);
		//Se elimina consulta a ILF6510, ya que sus registros están contenidos en ILF6500
		String command = "SELECT   A.PAGDESFEC, A.PAGHASFEC, A.PAGLIQDIA, A.PAGFEC, A.PAGRESDIA, A.PAGRESMON, A.LICIMPNUM, A.PAGFOL " +
				"FROM LIEXP.ILF6600 A  " +
				"WHERE AFIRUT = ? " +
				"UNION " +
				"SELECT  B.PAGDESFEC, B.PAGHASFEC, B.PAGLIQDIA, B.PAGFEC, B.PAGRESDIA, B.PAGRESMON, B.LICIMPNUM, B.PAGFOL " +
				"FROM LIEXP.ILL6504 B  WHERE AFIRUT = ? " +
				"UNION " +
				"SELECT  C.PAGDESFEC, C.PAGHASFEC, C.PAGLIQDIA, C.PAGFEC, C.PAGRESDIA, C.PAGRESMON, C.LICIMPNUM, C.PAGFOL " +
				"FROM LIEXP.ILF2500 C  " +
				"WHERE AFIRUT = ? " +
				"order by 1 desc";
				//"UNION " +
				//"SELECT  D.PAGDESFEC, D.PAGHASFEC, D.PAGLIQDIA, D.PAGFEC, D.PAGRESDIA, D.PAGRESMON, D.LICIMPNUM, D.PAGFOL " +
				//"FROM LIEXP.ILL6511 D  " +
				//"WHERE AFIRUT = ? " +
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setLong(1, rut);
		licenciasUtil.getStatement().setLong(2, rut);
		licenciasUtil.getStatement().setLong(3, rut);
		Collection listaLicencias1 = licenciasUtil.executeQuery(LicenciaMedicaDetalleVO.class);
		return listaLicencias1;
	}


	/**
	 * Recupera Collection de Detalles de las licencias medicas de un empleado
	 * @param long, rut del empleado
	 * @return Collection de LicenciaMedicaDetalleVO
	 */
	public Collection getListaDetallesLicenciasMedicasByEmpleadoLiquidacion(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaDetallesLicenciasMedicasByEmpleadoLiquidacion[" + rut + "]");
		//1Desde				  , 2Hasta				   , 3Dias Licencia			, 4Fecha de Pago	  , 5Dias de Pago		   , 6Monto a pagar
		String command = "SELECT PAGRESMON, LICIMPNUM , PAGFOL " + "FROM " + licenciasDatabase + ".ILF2520 " + "WHERE AFIRUT = ?";
		// prepara llamado
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setLong(1, rut);
		//	licenciasUtil.getStatement().setLong(2, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(LicenciaMedicaResumenVO.class);
	}

	/**
	 * @return LicenciaMedicaResumenVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static LicenciaMedicaResumenVO buildLicenciaMedicaResumenVO(ResultSet ors) throws SQLException {
		LicenciaMedicaResumenVO vo = new LicenciaMedicaResumenVO();
		vo.setNumeroLicencia(ors.getLong("LICIMPNUM"));
		vo.setMontoAPagar(ors.getInt("PAGRESMON"));
		vo.setFolio(ors.getString("PAGFOL"));
		return vo;
	}

	/**
	 * @return LicenciaMedicaAVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static LicenciaMedicaDetalleVO buildLicenciaMedicaDetalleVO(ResultSet ors) throws SQLException {
		LicenciaMedicaDetalleVO vo = new LicenciaMedicaDetalleVO();
		vo.setNumeroLicencia(ors.getLong("LICIMPNUM"));
		vo.setFechaDesde(ors.getString("PAGDESFEC"));
		vo.setFechaHasta(ors.getString("PAGHASFEC"));
		vo.setDiasLicencia(ors.getInt("PAGLIQDIA"));
		vo.setFechaDePago(ors.getString("PAGFEC"));
		vo.setDiaDePago(ors.getString("PAGRESDIA"));
		vo.setMontoAPagar(ors.getInt("PAGRESMON"));
		vo.setFolio(ors.getString("PAGFOL"));
		return vo;
	}

	public static DatosComplementariosVO buildDatosComplementariosVO(ResultSet ors) throws SQLException {
		DatosComplementariosVO vo = new DatosComplementariosVO();
		vo.setEmail(ors.getString("EMAIL"));
		vo.setTelefono(ors.getLong("TELEFONO"));
		return vo;
	}

	/** 
	 * Obtiene Collection de liscencias medicas con información para desplegar
	 * en el certificado
	 * @param long, rut del empleado
	 * @param String, fechaDesde Fecha que indica a partir de cuando se comienza la busqueda
	 * @return Collection de LicenciaMedicaCertificadoVO
	 */
	public Collection getListaLicenciasMedicasCertificadoByEmpleado(long rut, String fechaDesde) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaLicenciasMedicasCertificadoByEmpleado[" + rut + "][" + fechaDesde + "]");
		String command = "SELECT PAGDESFEC, PAGLIQDIA, PAGSUBMON, PAGEMIFEC, " + "LICRTAIMP, LICINSPRE, PAGCOTPOR, LICINSPRE, PAGHASFEC, PAGRESDIA, " + "LICAMPLET, LICIMPNUM " + "FROM "
				+ licenciasDatabase + ".ILL9001 " 
				+ "WHERE PAGDESFEC >= ? AND AFIRUT = ? "
				+ "AND  PAGAUX3 <=  1 "
				+ "AND LICDIAMON > 0 ";
		// prepara llamado
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setString(1, fechaDesde);
		licenciasUtil.getStatement().setLong(2, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(LicenciaMedicaCertificadoVO.class);
	}

	/**
	 * @return LicenciaMedicaCertificadoVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static LicenciaMedicaCertificadoVO buildLicenciaMedicaCertificadoVO(ResultSet ors) throws SQLException {
		LicenciaMedicaCertificadoVO vo = new LicenciaMedicaCertificadoVO();
		vo.setFechaDesde(ors.getString("PAGDESFEC"));
		vo.setFechaHasta(ors.getString("PAGHASFEC"));
		vo.setDiasLicencia(ors.getInt("PAGLIQDIA"));
		vo.setDiasPagados(ors.getInt("PAGRESDIA"));
		vo.setSubsidioPagado(ors.getInt("PAGSUBMON"));
		vo.setRentaImponible(ors.getInt("LICRTAIMP"));
		vo.setPorcentajeCotizacion(ors.getFloat("PAGCOTPOR"));
		vo.setCodInstitutoPrevisional(ors.getInt("LICINSPRE"));
		vo.setLetraAmpliacion(ors.getString("LICAMPLET"));
		vo.setLicImpNum(ors.getInt("LICIMPNUM"));
		vo.setTemporalPeriodo(ors.getString("PAGEMIFEC"));
		return vo;
	}

	/** 
	 * Recupera el valor del porcentaje de Seguro Cesantia registrado
	 * @param String letra, int codigo
	 * @return Collection de PorcentajeSeguroCesantiaVO con porcentaje de Seguro Cesantia
	 */
	public Collection getPorcentajeSeguroCesantia(String letra, int codigo) throws Exception, BusinessException {
		System.out.println("DAO: metodo getPorcentajeSeguroCesantia[" + letra + "][" + codigo + "]");
		String command = "SELECT TVALOR \"" + Constants.ALIAS_DOUBLE + "\" " + "FROM " + generalDatabase + ".TAF1000 " + "WHERE TLETRA = ? AND TCODIGO = ?";
		// prepara llamado
		generalUtil.prepareCall(command);
		generalUtil.getStatement().setString(1, letra);
		generalUtil.getStatement().setInt(2, codigo);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + generalUtil.getStatement().toString());
		
		return generalUtil.executeQuery(DoubleVO.class);
	}

	/** 
	 * Obtiene información para realizar el calclo del seguro de cesantia
	 * @param long, rut del empleado
	 * @param long, el numero de Licencia
	 * @param String, indicador si tiene o no seguro de cesantia
	 * @return Collection de DatosCalculoSeguroCesantiaVO
	 */
	public Collection getDatosCalculoSeguroCesantiaByEmpleado(long rut, long numLicencia, String tieneSeguro) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosCalculoSeguroCesantiaByEmpleado[" + rut + "][" + numLicencia + "][" + tieneSeguro + "]");
		String command = "SELECT RTAIMP90 \"" + Constants.ALIAS_DOUBLE + "\" " + "FROM " + licenciasDatabase + ".ILF1010 " + "WHERE AFIRUT = ? AND LICIMPNUM = ? AND LICSEGCES = ?";
		// prepara llamado
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setLong(1, rut);
		licenciasUtil.getStatement().setLong(2, numLicencia);
		licenciasUtil.getStatement().setString(3, tieneSeguro);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(DoubleVO.class);
	}

	// FIN LICENCIAS MEDICAS
	// DEUDA VIGENTE
	/** 
	 * Obtiene información de los créditos donde el rut consultado es el titular
	 * @param long, rut
	 * @return Collection de InformacionCreditoVO
	 */
	public Collection getCreditosByRutTitular(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getCreditosByRutTitular[" + rut + "]");
		String command = "SELECT 1 \"TIPODEUDA\", A.CREFOL, A.CRECUOMON, " + "A.CRETOP, A.CREESTPTM, A.OFIPRO, A.AFIRUT " + "FROM " + creditosDatabase + ".CSL1001 A, " + recuperacionCreditosDatabase
				+ ".CSL200 B " + "WHERE A.AFIRUT = ? " + "AND B.XCREFOL=A.CREFOL AND B.XOFIPRO=A.OFIPRO " + "AND B.CUOEST!=9 AND B.CUOEST!=8 AND B.CUOEST!=7 " + "AND A.CREESTPTM != 8 "
				+ "ORDER BY CREFOL ASC";
		// prepara llamado
		creditosUtil.prepareCall(command);
		creditosUtil.getStatement().setLong(1, rut);
		
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + creditosUtil.getStatement().toString());
		
		return creditosUtil.executeQuery(InformacionCreditoVO.class);
	}

	/** 
	 * Obtiene información de los créditos donde el rut consultado es el aval
	 * @param long, rut del empleado
	 * @return Collection de InformacionCreditoVO
	 */
	public Collection getCreditosByRutAval(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getCreditosByRutAval[" + rut + "]");
		String command = "SELECT 2 \"TIPODEUDA\", C.CREFOL, C.CRECUOMON, " + "C.CRETOP, C.CREESTPTM, C.OFIPRO, C.AFIRUT " + "FROM " + creditosDatabase + ".CSL1002 C, " + recuperacionCreditosDatabase
				+ ".CSL200 D " + "WHERE C.RUTAVAL1 = ? " + "AND D.XCREFOL=C.CREFOL AND D.XOFIPRO=C.OFIPRO " + "AND D.CUOEST!=9 AND D.CUOEST!=8 AND D.CUOEST!=7 " + "AND C.CREESTPTM != 8 " + "UNION "
				+ "SELECT 2 \"TIPODEUDA\", E.CREFOL, E.CRECUOMON, " + "E.CRETOP, E.CREESTPTM, E.OFIPRO, E.AFIRUT " + "FROM " + creditosDatabase + ".CSL1003 E, " + recuperacionCreditosDatabase
				+ ".CSL200 F " + "WHERE E.RUTAVAL2 = ? " + "AND F.XCREFOL=E.CREFOL AND F.XOFIPRO=E.OFIPRO " + "AND F.CUOEST!=9 AND F.CUOEST!=8 AND F.CUOEST!=7 " + "AND E.CREESTPTM != 8 "
				+ "ORDER BY CREFOL";
		// prepara llamado
		creditosUtil.prepareCall(command);
		creditosUtil.getStatement().setLong(1, rut);
		creditosUtil.getStatement().setLong(2, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + creditosUtil.getStatement().toString());
		
		return creditosUtil.executeQuery(InformacionCreditoVO.class);
	}

	/**
	 * @return InformacionCreditoVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static InformacionCreditoVO buildInformacionCreditoVO(ResultSet ors) throws SQLException {
		InformacionCreditoVO vo = new InformacionCreditoVO();
		vo.setEstado(ors.getInt("CREESTPTM"));
		vo.setFolio(ors.getString("CREFOL"));
		vo.setMontoCuota(ors.getInt("CRECUOMON"));
		vo.setOficina(ors.getString("OFIPRO"));
		vo.setTipoDeuda(ors.getInt("TIPODEUDA"));
		vo.setTipoOperacion(ors.getInt("CRETOP"));
		vo.setRutTitular(ors.getLong("AFIRUT"));
		return vo;
	}

	/** 
	 * Obtiene datos de los titulares de los créditos a partir del rut del aval
	 * @param long rut del aval
	 * @return Collection de DatosTitularCreditoVO
	 */
	public Collection getDatosTitularCreditoByAval(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosTitularCreditoByAval[" + rut + "]");
		logger.debug("getDatosTitularCreditoByAval");
		logger.debug("**********Rut: " + rut);
		String command = "SELECT A.CREFOL, B.SE5FBIO, B.SE5FBIM, B.SE5FBIK, " + "A.AFIRUT \"RUT\", A.AFIRUTDV \"DV\" " + "FROM " + creditosDatabase + ".CSL1002 A, " + afiliadosDatabase + ".AF02R1 B "
				+ "WHERE A.RUTAVAL1 = ? " + "AND A.AFIRUT = B.SE5FAJC " + "UNION " + "SELECT C.CREFOL, D.SE5FBIO, D.SE5FBIM, D.SE5FBIK, " + "C.AFIRUT \"RUT\", C.AFIRUTDV \"DV\" " + "FROM "
				+ creditosDatabase + ".CSL1003 C, " + afiliadosDatabase + ".AF02R1 D " + "WHERE C.RUTAVAL2 = ? " + "AND C.AFIRUT = D.SE5FAJC " + "ORDER BY SE5FBIK";
		// prepara llamado
		creditosUtil.prepareCall(command);
		creditosUtil.getStatement().setLong(1, rut);
		creditosUtil.getStatement().setLong(2, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + creditosUtil.getStatement().toString());
		
		
		return creditosUtil.executeQuery(DatosTitularCreditoVO.class);
	}

	/** 
	 * Obtiene datos de los titulares de los créditos
	 * @param long rut
	 * @return Collection de DatosTitularCreditoVO
	 */
	public Collection getDatosTitularCreditoByTitular(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosTitularCreditoByTitular[" + rut + "]");
		String command = "SELECT 0 \"CREFOL\", SE5FBIO, SE5FBIM, SE5FBIK, " + "SE5FAJC \"RUT\", SE5FBH3 \"DV\" " + "FROM " + afiliadosDatabase + ".AF02R1 " + "WHERE SE5FAJC = ?";
		// prepara llamado
		afiliadosUtil.prepareCall(command);
		afiliadosUtil.getStatement().setLong(1, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + afiliadosUtil.getStatement().toString());
		
		return afiliadosUtil.executeQuery(DatosTitularCreditoVO.class);
	}

	/** 
	 * Obtiene datos de los titulares públicos de los créditos
	 * @param long rut
	 * @return Collection de DatosTitularCreditoVO
	 */
	public Collection getDatosTitularCreditoByTitularPublico(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosTitularCreditoByTitularPublico[" + rut + "]");
		String command = "SELECT 0 \"CREFOL\", SE5FBIO, SE5FBIM, SE5FBIK, " + "SE5FAJC \"RUT\", SE5FBH3 \"DV\" " + "FROM " + publicoDatabase + ".AF02R1 " + "WHERE SE5FAJC = ?";
		// prepara llamado
		publicoUtil.prepareCall(command);
		publicoUtil.getStatement().setLong(1, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + publicoUtil.getStatement().toString());
		
		return publicoUtil.executeQuery(DatosTitularCreditoVO.class);
	}

	/**
	 * @return DatosTitularCreditoVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static DatosTitularCreditoVO buildDatosTitularCreditoVO(ResultSet ors) throws SQLException {
		DatosTitularCreditoVO vo = new DatosTitularCreditoVO();
		vo.setFolio(ors.getString("CREFOL"));
		vo.setApellidoMaterno(ors.getString("SE5FBIK"));
		vo.setApellidoPaterno(ors.getString("SE5FBIM"));
		vo.setDv(ors.getString("DV"));
		vo.setNombre(ors.getString("SE5FBIO"));
		vo.setRut(ors.getInt("RUT"));
		return vo;
	}

	/** 
	 * Obtiene las cuotas de los créstitos del rut consultado,
	 * tanto de los creditos en los cuales es el titular como en los que es aval
	 * @param long rut del aval
	 * @return Collection de CuotaCreditoVO
	 */
	public Collection getCuotasCreditosByRut(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getCuotasCreditosByRut[" + rut + "]");
		String command = "SELECT A.CREFOL, B.CUOEST, B.CUOVENFEC, B.CUONUM, B.CUOMONABO " + "FROM " + creditosDatabase + ".CSL1001 A, " + recuperacionCreditosDatabase + ".CSL200 B "
				+ "WHERE A.AFIRUT = ? " + "AND B.XCREFOL=A.CREFOL AND B.XOFIPRO=A.OFIPRO " + "UNION " + "SELECT C.CREFOL, D.CUOEST, D.CUOVENFEC, D.CUONUM, D.CUOMONABO " + "FROM " + creditosDatabase
				+ ".CSL1002 C, " + recuperacionCreditosDatabase + ".CSL200 D " + "WHERE C.RUTAVAL1 = ? " + "AND D.XCREFOL=C.CREFOL AND D.XOFIPRO=C.OFIPRO " + "UNION "
				+ "SELECT E.CREFOL, F.CUOEST, F.CUOVENFEC, F.CUONUM, F.CUOMONABO " + "FROM " + creditosDatabase + ".CSL1003 E, " + recuperacionCreditosDatabase + ".CSL200 F "
				+ "WHERE E.RUTAVAL2 = ? " + "AND F.XCREFOL=E.CREFOL AND F.XOFIPRO=E.OFIPRO " + "ORDER BY 1 ASC, 3 ASC";
		// prepara llamado
		creditosUtil.prepareCall(command);
		creditosUtil.getStatement().setLong(1, rut);
		creditosUtil.getStatement().setLong(2, rut);
		creditosUtil.getStatement().setLong(3, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + creditosUtil.getStatement().toString());
		
		return creditosUtil.executeQuery(CuotaCreditoVO.class);
	}

	/**
	 * @return CuotaCreditoVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static CuotaCreditoVO buildCuotaCreditoVO(ResultSet ors) throws SQLException {
		CuotaCreditoVO vo = new CuotaCreditoVO();
		vo.setFolio(ors.getString("CREFOL"));
		vo.setEstado(ors.getInt("CUOEST"));
		vo.setVencimiento(ors.getString("CUOVENFEC"));
		vo.setCuotaNumero(ors.getInt("CUONUM"));
		vo.setAbono(ors.getInt("CUOMONABO"));
		return vo;
	}

	// FIN DEUDA VIGENTE
	// CONSULTA CREDITOS
	/** 
	 * Obtiene información de los créditos del rut consultado
	 * @param long, rut
	 * @return Collection de ConsultaCreditoVO
	 */
	public Collection getCreditosByRut(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getCreditosByRut[" + rut + "]");
		String command = "SELECT	1 \"TITULARIDAD\", " + "	A.EMPRUT, " + "	A.EMPRUTDV, " + "	A.CREFOL, "
				+ "	A.CREOTOFEC, "
				+ "	CASE WHEN A.CREOTOFEC > 110930 THEN IFNULL((SELECT SUM(PRIMA) FROM CRDTA.CRF1860 S WHERE A.OFIPRO = S.OFIPRO AND A.CREFOL = S.CREFOL AND S.CUONUM = 1), 0) + CRECUOMON  ELSE CRECUOMON END AS CRECUOMON, "
				+ "	A.CRECUOTOT, "
				+ "	A.CREESTPTM, "
				+ "	A.OFIPRO,	 "
				+ //OFICINA PROCESO
				"	MIN(B.CUOVENFEC) \"DSCTOINI\", " + "	MAX(B.CUOVENFEC) \"DSCTOFIN\" " + "FROM	"
				+ creditosDatabase
				+ ".CSL1001 A, "
				+ recuperacionCreditosDatabase
				+ ".CSL200 B "
				+ "WHERE	A.AFIRUT = ? "
				+ //rut
				"AND	B.XCREFOL=A.CREFOL " + "AND	B.XOFIPRO=A.OFIPRO " + "AND	A.CREESTPTM!=7 " + "AND	A.CREESTPTM!=8 " + "AND	A.CREESTPTM!=9 " + "GROUP BY 	A.EMPRUT, " + "		A.EMPRUTDV, "
				+ "		A.CREFOL, " + "		A.CREOTOFEC, " + "		A.CRECUOMON, "
				+ "		A.CRECUOTOT, "
				+ "		A.CREESTPTM, "
				+ "		A.OFIPRO "
				+ //OFICINA PROCESO
				"UNION " + "SELECT	2 \"TITULARIDAD\", " + "	A.EMPRUT, " + "	A.EMPRUTDV, " + "	A.CREFOL, " + "	A.CREOTOFEC, " + "	CASE WHEN A.CREOTOFEC > 110930 THEN IFNULL((SELECT SUM(PRIMA) FROM CRDTA.CRF1860 S WHERE A.OFIPRO = S.OFIPRO AND A.CREFOL = S.CREFOL AND S.CUONUM = 1), 0) + CRECUOMON  ELSE CRECUOMON END AS CRECUOMON, "
				+ "	A.CRECUOTOT, "
				+ "	A.CREESTPTM, "
				+ "	A.OFIPRO,	 "
				+ //OFICINA PROCESO
				"	MIN(B.CUOVENFEC) \"DSCTOINI\", " + "	MAX(B.CUOVENFEC) \"DSCTOFIN\" " + "FROM	" + creditosDatabase + ".CSL1002 A, "
				+ recuperacionCreditosDatabase
				+ ".CSL200 B "
				+ "WHERE	A.RUTAVAL1 = ? "
				+ //rut
				"AND	B.XCREFOL=A.CREFOL " + "AND	B.XOFIPRO=A.OFIPRO " + "AND	A.CREESTPTM!=7 " + "AND	A.CREESTPTM!=8 " + "AND	A.CREESTPTM!=9 " + "GROUP BY	A.EMPRUT, " + "		A.EMPRUTDV, "
				+ "		A.CREFOL, " + "		A.CREOTOFEC, " + "		A.CRECUOMON, " + "		A.CRECUOTOT, " + "		A.CREESTPTM, "
				+ "		A.OFIPRO "
				+ //OFICINA PROCESO
				"UNION " + "SELECT	3 \"TITULARIDAD\", " + "	A.EMPRUT, " + "	A.EMPRUTDV, " + "	A.CREFOL, " + "	A.CREOTOFEC, " + "	CASE WHEN A.CREOTOFEC > 110930 THEN IFNULL((SELECT SUM(PRIMA) FROM CRDTA.CRF1860 S WHERE A.OFIPRO = S.OFIPRO AND A.CREFOL = S.CREFOL AND S.CUONUM = 1), 0) + CRECUOMON  ELSE CRECUOMON END AS CRECUOMON, " + "	A.CRECUOTOT, " + "	A.CREESTPTM, "
				+ "	A.OFIPRO,	 "
				+ //OFICINA PROCESO
				"	MIN(B.CUOVENFEC) \"DSCTOINI\", " + "	MAX(B.CUOVENFEC) \"DSCTOFIN\" " + "FROM	" + creditosDatabase + ".CSL1003 A, " + recuperacionCreditosDatabase + ".CSL200 B "
				+ "WHERE	A.RUTAVAL2 = ? "
				+ //rut
				"AND	B.XCREFOL=A.CREFOL " + "AND	B.XOFIPRO=A.OFIPRO " + "AND	A.CREESTPTM!=7 " + "AND	A.CREESTPTM!=8 " + "AND	A.CREESTPTM!=9 " + "GROUP BY	A.EMPRUT, " + "		A.EMPRUTDV, "
				+ "		A.CREFOL, " + "		A.CREOTOFEC, " + "		A.CRECUOMON, " + "		A.CRECUOTOT, " + "		A.CREESTPTM, " + "		A.OFIPRO " + //OFICINA PROCESO
				"ORDER BY	TITULARIDAD, " + "		EMPRUT";
		// prepara llamado
		creditosUtil.prepareCall(command);
		creditosUtil.getStatement().setLong(1, rut);
		creditosUtil.getStatement().setLong(2, rut);
		creditosUtil.getStatement().setLong(3, rut);
		
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + creditosUtil.getStatement().toString());
		
		
		return creditosUtil.executeQuery(ConsultaCreditoVO.class);
	}

	/**
	 * @return ConsultaCreditoVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static ConsultaCreditoVO buildConsultaCreditoVO(ResultSet ors) throws SQLException {
		SimpleDateFormat formatoLargo = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formatoCorto = new SimpleDateFormat("yyMMdd");
		ConsultaCreditoVO vo = new ConsultaCreditoVO();
		vo.setTitularidad(ors.getInt("TITULARIDAD"));
		vo.setRutEmpresa(ors.getLong("EMPRUT"));
		vo.setDvEmpresa(ors.getString("EMPRUTDV"));
		vo.setFolio(ors.getLong("CREFOL"));
		String fechaOtor = ors.getString("CREOTOFEC");
		switch (fechaOtor.length()) {
		case 1:
			fechaOtor = "00000" + fechaOtor;
			break;
		case 2:
			fechaOtor = "0000" + fechaOtor;
			break;
		case 3:
			fechaOtor = "000" + fechaOtor;
			break;
		case 4:
			fechaOtor = "00" + fechaOtor;
			break;
		case 5:
			fechaOtor = "0" + fechaOtor;
			break;
		}
		Date fechaOtorgamiento = formatoCorto.parse(fechaOtor, new ParsePosition(0));
		vo.setFechaOtorgamiento(fechaOtorgamiento);
		vo.setMontoCuota(ors.getInt("CRECUOMON"));
		vo.setNumeroCuotas(ors.getInt("CRECUOTOT"));
		vo.setCodigoEstadoPrestamo(ors.getInt("CREESTPTM"));
		Date primerDescuento = formatoLargo.parse(ors.getString("DSCTOINI"), new ParsePosition(0));
		vo.setPrimerDescuento(primerDescuento);
		Date ultimoDescuento = formatoLargo.parse(ors.getString("DSCTOFIN"), new ParsePosition(0));
		vo.setUltimoDescuento(ultimoDescuento);
		vo.setOficinaProceso(ors.getInt("OFIPRO"));
		return vo;
	}

	/** 
	 * Si obtiene información es porque el crédito es repactado, en caso contrario no
	 * @param long, folio
	 * @param int , ofipro
	 * @return Collection de IntVO
	 */
	public Collection getCreditoRepactado(long folio, int ofipro) throws Exception, BusinessException {
		System.out.println("DAO: metodo getCreditoRepactado[" + folio + "][" + ofipro + "]");
		String command = "SELECT	COUNT(*) \"" + Constants.ALIAS_INT + "\" " + "FROM	" + creditosDatabase + ".CRL1402 A " + "WHERE	A.CREFOL = ? " + "AND 	A.OFIPRO = ? ";
		// OFICINA PROCESO
		// prepara llamado
		creditosUtil.prepareCall(command);
		creditosUtil.getStatement().setLong(1, folio);
		creditosUtil.getStatement().setInt(2, ofipro);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + creditosUtil.getStatement().toString());
		
		
		return creditosUtil.executeQuery(IntVO.class);
	}

	/** 
	 * Obtiene las cuotas impagas de los créditos del rut consultado
	 * @param long, rut
	 * @return Collection de CreditoCuotasVO
	 */
	public Collection getCantidadCuotasImpagasCreditoByRut(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getCantidadCuotasImpagasCreditoByRut[" + rut + "]");
		String command = "SELECT	A.CREFOL, " + "	COUNT(*) \"" + Constants.ALIAS_INT + "\" " + "FROM	"
				+ creditosDatabase
				+ ".CSL1001 A, "
				+ recuperacionCreditosDatabase
				+ ".CSL200 B "
				+ "WHERE	A.AFIRUT = ? "
				+ //rut
				"AND	A.CREESTPTM!=7 " + "AND	A.CREESTPTM!=8 " + "AND	A.CREESTPTM!=9 " + "AND	B.XCREFOL=A.CREFOL " + "AND	B.XOFIPRO=A.OFIPRO " + "AND	B.CUOEST!=9 " + "AND	B.CUOEST!=8 "
				+ "AND	B.CUOEST!=7 " + "GROUP BY	A.CREFOL " + "UNION " + "SELECT	A.CREFOL, " + "	COUNT(*) \"" + Constants.ALIAS_INT + "\" " + "FROM	" + creditosDatabase + ".CSL1002 A, "
				+ recuperacionCreditosDatabase
				+ ".CSL200 B "
				+ "WHERE	A.RUTAVAL1 = ? "
				+ //rut
				"AND	A.CREESTPTM!=7 " + "AND	A.CREESTPTM!=8 " + "AND	A.CREESTPTM!=9 " + "AND	B.XCREFOL=A.CREFOL " + "AND	B.XOFIPRO=A.OFIPRO " + "AND	B.CUOEST!=9 " + "AND	B.CUOEST!=8 "
				+ "AND	B.CUOEST!=7 " + "GROUP BY	A.CREFOL " + "UNION " + "SELECT	A.CREFOL, " + "	COUNT(*) \"" + Constants.ALIAS_INT + "\" " + "FROM	" + creditosDatabase + ".CSL1003 A, "
				+ recuperacionCreditosDatabase + ".CSL200 B " + "WHERE	A.RUTAVAL2 = ? "
				+ //rut
				"AND	A.CREESTPTM!=7 " + "AND	A.CREESTPTM!=8 " + "AND	A.CREESTPTM!=9 " + "AND	B.XCREFOL=A.CREFOL " + "AND	B.XOFIPRO=A.OFIPRO " + "AND	B.CUOEST!=9 " + "AND	B.CUOEST!=8 "
				+ "AND	B.CUOEST!=7 " + "GROUP BY	A.CREFOL";
		// prepara llamado
		creditosUtil.prepareCall(command);
		creditosUtil.getStatement().setLong(1, rut);
		creditosUtil.getStatement().setLong(2, rut);
		creditosUtil.getStatement().setLong(3, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + creditosUtil.getStatement().toString());
		
		return creditosUtil.executeQuery(CreditoCuotasVO.class);
	}

	/**
	 * @return CreditoCuotasVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static CreditoCuotasVO buildCreditoCuotasVO(ResultSet ors) throws SQLException {
		CreditoCuotasVO vo = new CreditoCuotasVO();
		vo.setFolio(ors.getLong("CREFOL"));
		vo.setCuotasImpagas(ors.getInt(Constants.ALIAS_INT));
		return vo;
	}

	// FON CONSULTA CREDITOS
	// INICIO CARGAS FAMILIARES
	/** 
	 * Obtiene informació de una asignación familiar, los datos que obtiene son:
	 * 	fecha de afiliacion, nombre empresa y estado empresa
	 * @param long, rutAfiliado
	 * @param long, rutEmpleador
	 * @return Collection de DatosAsignacionFamiliarVO
	 */
	public Collection getDatosAsignacionFamiliar(long rutAfiliado, long rutEmpleador) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosAsignacionFamiliar[" + rutAfiliado + "][" + rutEmpleador + "]");
		String command = "SELECT B.SE5FBU9, A.CMPA, B.SE5FAR9 " + "FROM " + empresasDatabase + ".CM02L1 A, " + afiliadosDatabase + ".AF03A1 B, "
		//+ ".AF03F1 B, "
				+ afiliadosDatabase + ".AF02A1 C " + "WHERE B.SE5FAJC = ? " + "AND C.SE5FAJC = ? " + "AND A.CMNA = ? ";
		// prepara llamado
		empresaUtil.prepareCall(command);
		empresaUtil.getStatement().setLong(1, rutAfiliado);
		empresaUtil.getStatement().setLong(2, rutAfiliado);
		empresaUtil.getStatement().setLong(3, rutEmpleador);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + empresaUtil.getStatement().toString());
		
		
		return empresaUtil.executeQuery(DatosAsignacionFamiliarVO.class);
	}

	/**
	 * @return DatosAsignacionFamiliarVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static DatosAsignacionFamiliarVO buildDatosAsignacionFamiliarVO(ResultSet ors) throws SQLException {
		DatosAsignacionFamiliarVO vo = new DatosAsignacionFamiliarVO();
		vo.setFechaAfiliacion(ors.getString("SE5FBU9"));
		vo.setNombreEmpresa(ors.getString("CMPA"));
		vo.setCodigoEstadoEmpresa(ors.getString("SE5FAR9"));
		return vo;
	}

	/** 
	 * Obtiene el parentesco que tiene registrado una carga famiiar
	 * @param long, rutAfiliado
	 * @param int numeroCarga
	 * @return Collection de CargaFamiliarVO
	 */
	public Collection getParentescoCarga(long rutAfiliado, int numeroCarga) throws Exception, BusinessException {
		System.out.println("DAO: metodo getParentescoCarga[" + rutAfiliado + "][" + numeroCarga + "]");
		String command = "SELECT AF8KA \"" + Constants.ALIAS_STRING + "\" " + "FROM " + afiliadosDatabase + ".AF55L1 " + "WHERE SE5FAJC = ? " + "AND AF8DA = ?";
		//NumeroCarga		
		// prepara llamado
		afiliadosUtil.prepareCall(command);
		afiliadosUtil.getStatement().setLong(1, rutAfiliado);
		afiliadosUtil.getStatement().setInt(2, numeroCarga);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + afiliadosUtil.getStatement().toString());
		
		
		return afiliadosUtil.executeQuery(StringVO.class);
	}

	/** 
	 * Obtiene la información de las cargas familiares de un rut consultado
	 * @param long, rutAfiliado
	 * @param int estadoCarga
	 * @return Collection de CargaFamiliarVO
	 */
	public Collection getCargasFamiliares(long rutAfiliado, String estadoCarga) throws Exception, BusinessException {
		System.out.println("DAO: metodo getCargasFamiliares[" + rutAfiliado + "][" + estadoCarga + "]");
		boolean filtarEstado = false;
		String command = "SELECT AF8HA, AF8IA, AF8GA, AF8EA, AF8FA, AF8KA, AF8JA, " + "AF8RA, AF8TA, AF8LA, AF8PA, AF8QA, AF8NA, AF8DA " + "FROM " + afiliadosDatabase + ".AF05L1 "
				+ "WHERE SE5FAJC = ?";
		if (estadoCarga != null && estadoCarga.length() > 0) {
			command = command + " AND AF8LA = ?";
			filtarEstado = true;
		}
		// prepara llamado
		afiliadosUtil.prepareCall(command);
		afiliadosUtil.getStatement().setLong(1, rutAfiliado);
		if (filtarEstado)
			afiliadosUtil.getStatement().setString(2, estadoCarga);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + afiliadosUtil.getStatement().toString());
		
		
		return afiliadosUtil.executeQuery(CargaFamiliarVO.class);
	}

	/**
	 * @return CargaFamiliarVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static CargaFamiliarVO buildCargaFamiliarVO(ResultSet ors) throws SQLException {
		CargaFamiliarVO vo = new CargaFamiliarVO();
		vo.setRut(ors.getLong("AF8HA"));
		vo.setDv(ors.getString("AF8IA"));
		vo.setNombre(ors.getString("AF8GA"));
		vo.setApellidoMaterno(ors.getString("AF8FA"));
		vo.setApellidoPaterno(ors.getString("AF8EA"));
		vo.setCodigoParentezco(ors.getString("AF8KA"));
		vo.setFechaNacimiento(validaFechaNula(ors.getDate("AF8JA")));
		vo.setFechaVencimiento(validaFechaNula(ors.getDate("AF8RA")));
		vo.setCodigoCondicionInvalidez(ors.getString("AF8TA"));
		vo.setCodigoEstado(ors.getString("AF8LA"));
		vo.setFechaAutorizacion(validaFechaNula(ors.getDate("AF8PA")));
		vo.setFechaAnulacion(validaFechaNula(ors.getDate("AF8QA")));
		vo.setFechaProceso(validaFechaNula(ors.getDate("AF8NA")));
		vo.setNumeroCarga(ors.getInt("AF8DA"));
		return vo;
	}

	/** 
	 * Rescata maximo valor de la fecha de inicio de tramo AF2GA, siempre que sea
	 * menor o igual a la fecha del sistema
	 * @return Collection de DateVO
	 */
	public Collection getFechaInicioTramoAsignacionFamiliar() throws Exception, BusinessException {
		System.out.println("DAO: metodo getFechaInicioTramoAsignacionFamiliar");
		String command = "SELECT DISTINCT(MAX(AF2GA)) \"" + Constants.ALIAS_DATE + "\" " + "FROM " + afiliadosDatabase + ".AF11L1 " + "WHERE AF2GA <= CURRENT_DATE";
		// prepara llamado
		afiliadosUtil.prepareCall(command);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + afiliadosUtil.getStatement().toString());
		
		return afiliadosUtil.executeQuery(DateVO.class);
	}

	/** 
	 * Rescata codigo del tramo. Para obtenerlo lo hace con el rut del afiliado y la
	 * fecha de inicio de tramo rescatada en el punto anterior
	 * @param long rutAfiliado
	 * @param Date fechaInicioTramo
	 * @return Collection de IntVO
	 */
	public Collection getCodigoTramoAsignacionFamiliar(long rutAfiliado, Date fechaInicioTramo) throws Exception, BusinessException {
		System.out.println("DAO: metodo getCodigoTramoAsignacionFamiliar[" + rutAfiliado + "][" + fechaInicioTramo + "]");
		String command = "SELECT AF2HA \"" + Constants.ALIAS_INT + "\" " + "FROM " + afiliadosDatabase + ".AF09L1 " + "WHERE SE5FAJC = ? " + "AND AF2GA = ?";
		// prepara llamado
		afiliadosUtil.prepareCall(command);
		afiliadosUtil.getStatement().setLong(1, rutAfiliado);
		afiliadosUtil.getStatement().setDate(2, new java.sql.Date(fechaInicioTramo.getTime()));
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + afiliadosUtil.getStatement().toString());
		
		return afiliadosUtil.executeQuery(IntVO.class);
	}

	/** 
	 * Si existe registro en AF09L1, rescata el valor del tramo AF2KA de 
	 * la tabla AF11L1, caso contrario limpiar valor tramo
	 * @param Date fechaTramo
	 * @param int tramoVigente
	 * @return Collection de StringVO
	 */
	public Collection getValorTramoAsignacionFamiliar(Date fechaTramo, int tramoVigente) throws Exception, BusinessException {
		System.out.println("DAO: metodo getValorTramoAsignacionFamiliar[" + fechaTramo + "][" + tramoVigente + "]");
		String command = "SELECT AF2KA \"" + Constants.ALIAS_STRING + "\" " + "FROM " + afiliadosDatabase + ".AF11L1 " + "WHERE AF2GA = ? " + "AND AF2HA = ?";
		// prepara llamado
		afiliadosUtil.prepareCall(command);
		afiliadosUtil.getStatement().setDate(1, new java.sql.Date(fechaTramo.getTime()));
		afiliadosUtil.getStatement().setInt(2, tramoVigente);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + afiliadosUtil.getStatement().toString());
		
		return afiliadosUtil.executeQuery(StringVO.class);
	}

	/** 
	 * Devuelve el estado del pago de las cargas familiares dado el rut del afiliado 
	 * y el rut de la empresa
	 * @param long rutAfiliado
	 * @param long rutEmpleador
	 * @return Collection de StringVO
	 */
	public Collection getEstadoPagoCarga(long rutAfiliado, long rutEmpleador) throws Exception, BusinessException {
		System.out.println("DAO: metodo getEstadoPagoCarga[" + rutAfiliado + "][" + rutEmpleador + "]");
		String command = "SELECT SE5FBU4 \"" + Constants.ALIAS_STRING + "\" " + "FROM  " + afiliadosDatabase + ".AF03F1 " + "WHERE SE5FAJC = ? " + "AND CMNA = ?";
		// prepara llamado
		afiliadosUtil.prepareCall(command);
		afiliadosUtil.getStatement().setLong(1, rutAfiliado);
		afiliadosUtil.getStatement().setLong(2, rutEmpleador);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + afiliadosUtil.getStatement().toString());
		
		
		return afiliadosUtil.executeQuery(StringVO.class);
	}

	/** 
	 * Devuelve la cantidad de cargas que se encuentran en cada estado definido 
	 * @param long rutAfiliado
	 * @return Collection de CantidadEstadoVO
	 */
	public Collection getCantidadCargasPorEstado(long rutAfiliado) throws Exception, BusinessException {
		System.out.println("DAO: metodo getCantidadCargasPorEstado[" + rutAfiliado + "]");
		String command = "SELECT AF8LA, COUNT(1) \"CANTIDAD\" " + "FROM " + afiliadosDatabase + ".AF05L1 " + "WHERE SE5FAJC = ? " + "GROUP BY AF8LA";
		// prepara llamado
		afiliadosUtil.prepareCall(command);
		afiliadosUtil.getStatement().setLong(1, rutAfiliado);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + afiliadosUtil.getStatement().toString());
		
		return afiliadosUtil.executeQuery(CantidadEstadoVO.class);
	}

	/**
	 * @return CantidadEstadoVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static CantidadEstadoVO buildCantidadEstadoVO(ResultSet ors) throws SQLException {
		CantidadEstadoVO vo = new CantidadEstadoVO();
		vo.setEstado(ors.getString("AF8LA"));
		vo.setCantidad(ors.getInt("CANTIDAD"));
		return vo;
	}

	//	FIN CARGAS FAMILIARES
	// INICIO CARTOLA
	/** 
	 * Obtiene información de las cuentas de ahorro de un rut
	 * @param long rut
	 * @param String cuentaAhorro, si viene null no filtra por cuenta
	 * @return Collection de CuentaAhorroVO
	 */
	public Collection getListaCuentaAhorroByRut(long rut, String cuentaAhorro) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaCuentaAhorroByRut[" + rut + "][" + cuentaAhorro + "]");
		boolean filtrarPorCuenta = false;
		//		String command ="SELECT E.BCDIDIRGLO," +
		//						"B.CTAAHONUM, " +
		//						"B.CTAAHODV," +
		//						"F.BCNOMCOM," +
		//						"F.BCNOMCIU," +
		//						"C.FECCON, " +
		//						"B.CTAAHOTET, " +
		//						"D.AFVCUOVAL, " +
		//						"B.CTAAHOEST, " +
		//						"B.CTAAHOTIP, " +
		//						"B.CTAAHOPRO " +
		//						"FROM "+leasingDatabase+".I00501 A," +
		//						leasingDatabase+".I00C04 B," +
		//						leasingDatabase+".I00601 C," +
		//						leasingDatabase+".I00I01 D ," +
		//						"BCDTA.BC07F1 E," +
		//						"BCDTA.bc23f1 F " +
		//						"WHERE A.AHORUT = ? " +
		//						"AND B.AHORUT = ? " +
		//						"AND E.bcclrut = ? " +
		//						"AND C.FECCOR = 9 " +
		//						"AND D.AFVCUOFEC = C.FECCON " +
		//						"AND F.BCCODCOM = E.BCCODCOM";
		String command = "SELECT E.BCDIDIRGLO," + "B.CTAAHONUM, " + "B.CTAAHODV," + "F.BCNOMCOM," + "F.BCNOMCIU," + "C.FECCON," + "B.CTAAHOTET, " + "D.AFVCUOVAL, " + "B.CTAAHOEST, " + "B.CTAAHOTIP, "
				+ "B.CTAAHOPRO " + "FROM " + leasingDatabase + ".I00501 A," + leasingDatabase + ".I00C04 B," + leasingDatabase + ".I00601 C," + leasingDatabase + ".I00I01 D," + "BCDTA.BC07F1 E,"
				+ "BCDTA.bc23f1 F " + "WHERE A.AHORUT = ? " + "AND B.AHORUT = ? " + "AND E.bcclrut = ? " + "AND C.FECCOR = 9 " + "AND D.AFVCUOFEC = C.FECCON " + "AND F.BCCODCOM = E.BCCODCOM "
				+ "AND E.bcditip = 'P' " + "AND " + "(B.CTAAHOEST != 4 " + "AND B.CTAAHOEST != 5 " + "AND B.CTAAHOEST != 6 " + ")";
		if (cuentaAhorro != null && cuentaAhorro.length() > 0) {
			command = command + " AND B.CTAAHONUM = ?";
			filtrarPorCuenta = true;
		}
		// prepara llamado
		leasingUtil.prepareCall(command);
		leasingUtil.getStatement().setLong(1, rut);
		leasingUtil.getStatement().setLong(2, rut);
		leasingUtil.getStatement().setLong(3, rut);
		if (filtrarPorCuenta)
			leasingUtil.getStatement().setString(4, cuentaAhorro);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + leasingUtil.getStatement().toString());
		
		return leasingUtil.executeQuery(CuentaAhorroVO.class);
	}

	/**
	 * @return CuentaAhorroVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static CuentaAhorroVO buildCuentaAhorroVO(ResultSet ors) throws SQLException {
		CuentaAhorroVO vo = new CuentaAhorroVO();
		vo.setDireccion(ors.getString("BCDIDIRGLO"));
		//vo.setDireccion(ors.getString("AHODIR"));
		vo.setNumeroCuenta(ors.getString("CTAAHONUM"));
		vo.setDvNumeroCuenta(ors.getString("CTAAHODV"));
		vo.setComuna(ors.getString("BCNOMCOM"));
		//vo.setComuna(ors.getString("AHOCOM"));
		vo.setCiudad(ors.getString("BCNOMCIU"));
		//vo.setCiudad(ors.getString("AHOCIU"));
		vo.setFechaUltCartola(ors.getString("FECCON"));
		vo.setUltSaldoCuotas(ors.getDouble("CTAAHOTET"));
		vo.setUltValorCuota(ors.getFloat("AFVCUOVAL"));
		vo.setEstadoCuenta(ors.getInt("CTAAHOEST"));
		vo.setTipoCuenta(ors.getInt("CTAAHOTIP"));
		vo.setIndicadorPromesaArriendo(ors.getInt("CTAAHOPRO"));
		return vo;
	}

	/** 
	 * Obtiene el detalle de una cartola de ahorro
	 * @param CuentaAhorroVO cuenta
	 * @return Collection de DetalleCartolaVO
	 */
	public Collection getDetalleCuentaAhorro(CuentaAhorroVO cuenta) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDetalleCuentaAhorro[" + cuenta.getNumeroCuenta() + "][" + cuenta.getFechaUltCartola() + "]");
		logger.debug("Num Cuenta Ahorro: " + cuenta.getNumeroCuenta());
		logger.debug("Fecha Ult Cartola: " + cuenta.getFechaUltCartola());
		String command = "SELECT A.MOVDES, C.MOVFEC, C.MOVCUO, C.MOVMON, C.MOVCOD, " + "C.MOVEST, B.AFVCUOVAL, B.AFVCUOFEC " + "FROM " + leasingDatabase + ".I01301 A, " + leasingDatabase
				+ ".I00I01 B, " + leasingDatabase + ".IU103 C " + "WHERE C.CTAAHONUM = ? " + "AND C.MOVVALFEC > ? " + "AND A.MOVCOD = C.MOVCOD " + "AND B.AFVCUOFEC = (SELECT MAX(AFVCUOFEC) "
				+ "FROM " + leasingDatabase + ".I00I01) " + "OR C.CTAAHONUM = ? " + "AND C.MOVVALFEC = '00000000'" + "AND C.MOVCUO = 0 " + "AND A.MOVCOD = C.MOVCOD "
				+ "AND B.AFVCUOFEC = (SELECT MAX(AFVCUOFEC) " + "FROM " + leasingDatabase + ".I00I01)";
		// prepara llamado
		leasingUtil.prepareCall(command);
		leasingUtil.getStatement().setString(1, cuenta.getNumeroCuenta());
		leasingUtil.getStatement().setString(2, cuenta.getFechaUltCartola());
		leasingUtil.getStatement().setString(3, cuenta.getNumeroCuenta());
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + leasingUtil.getStatement().toString());
		
		return leasingUtil.executeQuery(DetalleCartolaVO.class);
	}

	/**
	 * @return DetalleCartolaVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static DetalleCartolaVO buildDetalleCartolaVO(ResultSet ors) throws SQLException {
		DetalleCartolaVO vo = new DetalleCartolaVO();
		vo.setDescripcionDetalle(ors.getString("MOVDES"));
		vo.setFechaDetalle(ors.getString("MOVFEC"));
		vo.setCuotas(ors.getDouble("MOVCUO"));
		vo.setTotalValor(ors.getInt("MOVMON"));
		vo.setCodigoMovimiento(ors.getInt("MOVCOD"));
		vo.setEstadoMovimiento(ors.getInt("MOVEST"));
		vo.setValorCuotaActual(ors.getFloat("AFVCUOVAL"));
		vo.setFechaBDUltValorCuota(ors.getString("AFVCUOFEC"));
		return vo;
	}

	// FIN CARTOLA
	// INICIO DATOS AFILIADO
	/** 
	 * Si encuentra datos quiere decir que el rut del empleado pertenece a la empresa
	 * Si no encuentra datos es porque no pertenece a la empresa
	 * @param long rutAfiliado
	 * @param long rutEmpresa
	 * @return Collection de IntVO
	 */
	public Collection getEmpleadoPerteneceEmpresa(long rutAfiliado, long rutEmpresa) throws Exception, BusinessException {
		System.out.println("DAO: metodo getEmpleadoPerteneceEmpresa[" + rutAfiliado + "][" + rutEmpresa + "]");
		String command = "SELECT 1 \"" + Constants.ALIAS_INT + "\" " + "FROM " + afiliadosDatabase + ".AF03U102 A ," + empresasDatabase + ".CM02F1 B " + "WHERE A.SE5FAJC = ? " + "AND B.CMNA = ? "
				+ "AND A.CMNA = B.CMNA";
		// prepara llamado
		System.out.println("sql: " + command);
		System.out.println("sql A.SE5FAJC: " + rutAfiliado);
		System.out.println("sql B.CMNA: " + rutEmpresa);
		afiliadosUtil.prepareCall(command);
		afiliadosUtil.getStatement().setLong(1, rutAfiliado);
		afiliadosUtil.getStatement().setLong(2, rutEmpresa);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + afiliadosUtil.getStatement().toString());
		
		return afiliadosUtil.executeQuery(IntVO.class);
	}

	/** 
	 * Si encuentra datos quiere decir que el rut del empleado pertenece a la empresa pública
	 * Si no encuentra datos es porque no pertenece a la empresa pública
	 * @param long rutPublico
	 * @param long rutEmpresaPublica
	 * @return Collection de IntVO
	 */
	public Collection getEmpleadoPerteneceEmpresaPublica(long rutPublico, long rutEmpresaPublica) throws Exception, BusinessException {
		System.out.println("DAO: metodo getEmpleadoPerteneceEmpresaPublica[" + rutPublico + "][" + rutEmpresaPublica + "]");
		String command = "SELECT 1 \"" + Constants.ALIAS_INT + "\" " + "FROM " + publicoDatabase + ".AF03U102 A ," + publicoDatabase + ".CM02F1 B " + "WHERE A.SE5FAJC = ? " + "AND B.CMNA = ? "
				+ "AND A.CMNA = B.CMNA";
		// prepara llamado
		System.out.println("sql: " + command);
		System.out.println("sql A.SE5FAJC: " + rutPublico);
		System.out.println("sql B.CMNA: " + rutEmpresaPublica);
		publicoUtil.prepareCall(command);
		publicoUtil.getStatement().setLong(1, rutPublico);
		publicoUtil.getStatement().setLong(2, rutEmpresaPublica);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + publicoUtil.getStatement().toString());
		
		return publicoUtil.executeQuery(IntVO.class);
	}

	/** 
	 * Obtiene los datos de un afiliado
	 * @param long rutAfiliado
	 * @return Collection de AfiliadoVO
	 */
	public Collection getDatosAfiliado(long rutAfiliado) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosAfiliado[" + rutAfiliado + "]");
		String command = "SELECT A.SE5FAJC, A.SE5FBH3, A.SE5FBIO, A.SE5FBIM, " + "A.SE5FBIK, B.SE5FAR9, C.CMBA, C.CM13A " + "FROM " + afiliadosDatabase + ".AF02A1 A, " + afiliadosDatabase
				+ ".AF03A1 B, " + afiliadosDatabase + ".AF03F1 C " + "WHERE A.SE5FAJC = ? " + "AND A.SE5FAJC = B.SE5FAJC " + "AND A.SE5FAJC = C.SE5FAJC";
		// prepara llamado
		System.out.println("sql: " + command);
		System.out.println("sql A.SE5FAJC: " + rutAfiliado);
		//System.out.println("el query es ["+command+"]");
		afiliadosUtil.prepareCall(command);
		afiliadosUtil.getStatement().setLong(1, rutAfiliado);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + afiliadosUtil.getStatement().toString());
		
		return afiliadosUtil.executeQuery(AfiliadoVO.class);
	}

	/**
	 * @return AfiliadoVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static AfiliadoVO buildAfiliadoVO(ResultSet ors) throws SQLException {
		AfiliadoVO vo = new AfiliadoVO();
		vo.setRut(ors.getLong("SE5FAJC"));
		vo.setDv(ors.getString("SE5FBH3"));
		vo.setNombre(ors.getString("SE5FBIO"));
		vo.setApellidoPaterno(ors.getString("SE5FBIM"));
		vo.setApellidoMaterno(ors.getString("SE5FBIK"));
		vo.setEstadoEmpresa(ors.getString("SE5FAR9"));
		vo.setCodigoOficina(ors.getLong("CMBA"));
		vo.setCodigoSucursal(ors.getLong("CM13A"));
		return vo;
	}

	// FIN DATOS AFILIADO
	// INICIO PERFIL USUARIO
	/** 
	 * Obtiene los datos de un empleado público
	 * @param long rutEmpleadoPublico
	 * @return Collection de PublicoVO
	 */
	public Collection getDatosPublico(long rutEmpleadoPublico) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosPublico[" + rutEmpleadoPublico + "]");
		String command = "SELECT A.SE5FAJC, A.SE5FBH3, A.SE5FBIO, A.SE5FBIM, " + "A.SE5FBIK, B.SE5FAR9, C.CMBA, C.CM13A " + "FROM " + publicoDatabase + ".AF02A1 A, " + publicoDatabase + ".AF03A1 B, "
				+ publicoDatabase + ".AF03F1 C " + "WHERE A.SE5FAJC = ? " + "AND A.SE5FAJC = B.SE5FAJC " + "AND A.SE5FAJC = C.SE5FAJC";
		// prepara llamado
		publicoUtil.prepareCall(command);
		publicoUtil.getStatement().setLong(1, rutEmpleadoPublico);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + publicoUtil.getStatement().toString());
		
		return publicoUtil.executeQuery(PublicoVO.class);
	}

	/**
	 * @return PublicoVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static PublicoVO buildPublicoVO(ResultSet ors) throws SQLException {
		PublicoVO vo = new PublicoVO();
		vo.setRut(ors.getLong("SE5FAJC"));
		vo.setDv(ors.getString("SE5FBH3"));
		vo.setNombre(ors.getString("SE5FBIO"));
		vo.setApellidoPaterno(ors.getString("SE5FBIM"));
		vo.setApellidoMaterno(ors.getString("SE5FBIK"));
		vo.setEstadoEmpresa(ors.getString("SE5FAR9"));
		vo.setCodigoOficina(ors.getLong("CMBA"));
		vo.setCodigoSucursal(ors.getLong("CM13A"));
		return vo;
	}

	/** 
	 * Obtiene los datos de un pensionado
	 * @param long rutPensionado
	 * @return Collection de PensionadoVO
	 */
	public Collection getDatosPensionado(long rutPensionado) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosPensionado[" + rutPensionado + "]");
		String command = "SELECT AFIRUT, AFIRUTDV, AFINOM, AFIAPE, AFIFECNAC, " + "AFIESTAFI " + "FROM " + pensionadosDatabase + ".PEF1500 " + "WHERE AFIRUT = ?";
		// prepara llamado
		pensionadosUtil.prepareCall(command);
		pensionadosUtil.getStatement().setLong(1, rutPensionado);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + pensionadosUtil.getStatement().toString());
		
		return pensionadosUtil.executeQuery(PensionadoVO.class);
	}

	/**
	 * @return PensionadoVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static PensionadoVO buildPensionadoVO(ResultSet ors) throws SQLException {
		PensionadoVO vo = new PensionadoVO();
		vo.setRut(ors.getLong("AFIRUT"));
		vo.setDv(ors.getString("AFIRUTDV"));
		vo.setNombre(ors.getString("AFINOM"));
		vo.setApellidos(ors.getString("AFIAPE"));
		vo.setFechaNacimiento(ors.getString("AFIFECNAC"));
		vo.setEstado(ors.getString("AFIESTAFI"));
		return vo;
	}

	/** 
	 * Obtiene los datos de un ahorrante
	 * @param long rutAhorrante
	 * @return Collection de AhorranteVO
	 */
	public Collection getDatosAhorrante(long rutAhorrante) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosAhorrante[" + rutAhorrante + "]");
		String command = "SELECT AHORUT, AHORUTDV, AHONOM, AHOAPEPAT, AHOAPEMAT, AHOTIP " + "FROM " + leasingDatabase + ".I00501 " + "WHERE AHORUT = ?";
		// prepara llamado
		leasingUtil.prepareCall(command);
		leasingUtil.getStatement().setLong(1, rutAhorrante);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + leasingUtil.getStatement().toString());
		
		return leasingUtil.executeQuery(AhorranteVO.class);
	}

	/**
	 * @return AhorranteVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static AhorranteVO buildAhorranteVO(ResultSet ors) throws SQLException {
		AhorranteVO vo = new AhorranteVO();
		vo.setRut(ors.getLong("AHORUT"));
		vo.setDv(ors.getString("AHORUTDV"));
		vo.setNombre(ors.getString("AHONOM"));
		vo.setApellidoPaterno(ors.getString("AHOAPEPAT"));
		vo.setApellidoMaterno(ors.getString("AHOAPEMAT"));
		vo.setTipo(ors.getString("AHOTIP"));
		return vo;
	}

	/**
	 * @return AhorranteVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static MontoVO buildMontoVO(ResultSet ors) throws SQLException {
		MontoVO vo = new MontoVO();
		vo.setMonto(ors.getLong("MONTOCREDITO"));
		return vo;
	}

	/** 
	 * Obtiene los datos de una empresa
	 * @param long rutEmpresa
	 * @return Collection de EmpresaVO
	 */
	public Collection getDatosEmpresa(long rutEmpresa) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosEmpresa[" + rutEmpresa + "]");
		String command = "SELECT A.CMNA \"RUT\", A.CMOA \"DV\", A.CMPA \"NOMBRE\", A.CMQA \"ESTADO\", A.CMSA \"TIPO\" " + "FROM " + empresasDatabase + ".CM02L1 A " + "INNER JOIN "
				+ "(SELECT DISTINCT(CMNA) FROM " + empresasDatabase + ".CM03F1) B " + "ON A.CMNA=B.CMNA " + "WHERE A.CMNA = ?";
		// prepara llamado
		empresaUtil.prepareCall(command);
		empresaUtil.getStatement().setLong(1, rutEmpresa);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + empresaUtil.getStatement().toString());
		
		return empresaUtil.executeQuery(EmpresaVO.class);
	}

	/** 
	 * Obtiene los datos de una empresa
	 * @param long rutEmpresa
	 * @return Collection de EmpresaVO
	 */
	public Collection getDatosEmpresaPublica(long rutEmpresaPublica) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosEmpresaPublica[" + rutEmpresaPublica + "]");
		String command = "SELECT CMNA \"RUT\", CMOA \"DV\", CMPA \"NOMBRE\", CMQA \"ESTADO\", CMSA \"TIPO\" " + "FROM " + empresaPublicaDatabase + ".CM02L1 " + "WHERE CMNA = ?";
		// prepara llamado
		empresaUtil.prepareCall(command);
		empresaUtil.getStatement().setLong(1, rutEmpresaPublica);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + empresaUtil.getStatement().toString());
		
		return empresaUtil.executeQuery(EmpresaPublicaVO.class);
	}

	// FIN PERFIL USUARIO
	// INICIO DATOS SEGURIDAD
	/** 
	 * Obtiene los datos de la clave de acceso del rut consultado
	 * @param long rut
	 * @return Collection de ClaveVO
	 */
	public Collection getDatosClaveAcceso(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosClaveAcceso[" + rut + "]");
		String command = "SELECT CLAVEINI, CLAVEPER, FECULTBLOQ, HORULTBLOQ " + "FROM " + autoconsultaDatabase + ".AT30F1 " + "WHERE RUTUSR = ?";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(ClaveVO.class);
	}

	/**
	 * @return ClaveVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static ClaveVO buildClaveVO(ResultSet ors) throws SQLException {
		ClaveVO vo = new ClaveVO();
		System.out.println("ENTRA A DE buildClaveVO DAO");
		System.out.println("----buildClaveVO **** vo.setClaveInicial:" + ors.getInt("CLAVEINI"));
		vo.setClaveInicial(ors.getInt("CLAVEINI"));
		System.out.println("----buildClaveVO **** vo.setClavePersonal:" + ors.getInt("CLAVEPER"));
		vo.setClavePersonal(ors.getInt("CLAVEPER"));
		System.out.println("----buildClaveVO **** vo.setHoraUltBloqueo:" + ors.getTime("HORULTBLOQ"));
		vo.setFechaUltBloqueo(ors.getDate("FECULTBLOQ"));
		System.out.println("----buildClaveVO **** vo.setHoraUltBloqueo:" + ors.getTime("HORULTBLOQ"));
		vo.setHoraUltBloqueo(ors.getTime("HORULTBLOQ"));
		System.out.println("SALE DE buildClaveVO DAO");
		return vo;
	}

	/** 
	 * Realiza el cambio de clave personal
	 * @param long rut
	 * @param int nueva password
	 * @return void
	 */
	public void updateClave(long rut, int password) throws Exception, BusinessException {
		System.out.println("ENTRA A DAO Update Clave");
		String command = "UPDATE " + autoconsultaDatabase + ".AT30F1 SET " + "CLAVEPER = ? " + "WHERE RUTUSR = ?";
		// prepara llamado
		System.out.println("Update Clave, SQL : " + command);
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setInt(1, password);
		autoconsultaUtil.getStatement().setLong(2, rut);
		int i = autoconsultaUtil.executeUpdate();
		logger.debug("Update Clave, Filas Afectadas: " + i);
		System.out.println("Update Clave, Filas Afectadas: " + i);
		System.out.println("SALE DE DAO Update Clave");
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
	}

	/** 
	 * Realiza el bloqueo de la clave
	 * @param long rut
	 * @param int nueva password
	 * @return void
	 */
	public void bloqueaClave(long rut, Date fecha, Time hora) throws Exception, BusinessException {
		System.out.println("DAO: metodo bloqueaClave[" + rut + "][" + fecha + "][" + hora + "]");
		String command = "UPDATE " + autoconsultaDatabase + ".AT30F1 SET " + "FECULTBLOQ = ?, " + "HORULTBLOQ = ? " + "WHERE RUTUSR = ?";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setDate(1, new java.sql.Date(fecha.getTime()));
		autoconsultaUtil.getStatement().setTime(2, hora);
		autoconsultaUtil.getStatement().setLong(3, rut);
		int i = autoconsultaUtil.executeUpdate();
		logger.debug("Bloquea Clave, Filas Afectadas: " + i);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
	}

	/** 
	 * Registra una transacción en la tabla de auditoria
	 * @param AuditoriaVO auditoria
	 * @return void
	 */
	public void insertAuditoria(AuditoriaVO auditoria) throws Exception, BusinessException {
		System.out.println("ENTRA A insertAuditoria DAO");
		String command = "INSERT INTO " + autoconsultaDatabase + ".AT31F1 " + "(RUTUSR, FECTRAN, HORTRAN, CODTRAN, RUTOPER, " + " IDOPER, CLAVEINI, CLAVEPER) " + "VALUES (?," + //rut
				"?," + //fechaBD
				"?," + //horaBD
				"?," + //0
				"?," + //null
				"?," + //null
				"?," + //claveInicial
				"?)"; //clavePersonal
		System.out.println("insertAuditoria SQL: " + command);
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		System.out.println("1: insertAuditoria auditoria.getRut() : " + auditoria.getRut());
		autoconsultaUtil.getStatement().setLong(1, auditoria.getRut());
		System.out.println("2: insertAuditoria  auditoria.getFechaTransaccion : " + new java.sql.Date(auditoria.getFechaTransaccion().getTime()));
		autoconsultaUtil.getStatement().setDate(2, new java.sql.Date(auditoria.getFechaTransaccion().getTime()));
		System.out.println("3: insertAuditoria  auditoria.getHoraTransaccion : " + auditoria.getHoraTransaccion());
		autoconsultaUtil.getStatement().setTime(3, auditoria.getHoraTransaccion());
		System.out.println("4: insertAuditoria  auditoria.getCodigoTransaccion : " + auditoria.getCodigoTransaccion());
		autoconsultaUtil.getStatement().setInt(4, auditoria.getCodigoTransaccion());
		System.out.println("5: insertAuditoria  auditoria.getRutOperador : " + auditoria.getRutOperador());
		autoconsultaUtil.getStatement().setString(5, auditoria.getRutOperador());
		System.out.println("6: insertAuditoria  auditoria.getIdOperador : " + auditoria.getIdOperador());
		autoconsultaUtil.getStatement().setString(6, auditoria.getIdOperador());
		System.out.println("7: insertAuditoria  auditoria.getClaveInicio : " + auditoria.getClaveInicio());
		autoconsultaUtil.getStatement().setInt(7, auditoria.getClaveInicio());
		System.out.println("8: insertAuditoria  auditoria.getClavePersonal : " + auditoria.getClavePersonal());
		autoconsultaUtil.getStatement().setInt(8, auditoria.getClavePersonal());
		autoconsultaUtil.execute();
		System.out.println("SALE DE insertAuditoria DAO");
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
	}

	/** 
	 * Registra una actividad en la tabla de actividades
	 * @param AuditoriaVO auditoria
	 * @return void
	 */
	public void insertActividad(ActividadVO actividad)
	throws Exception, BusinessException {
	System.out.println("DAO: metodo insertActividad2["+actividad+"][" + actividad.getRutAfiliado() + "]");
	String command =
		"INSERT INTO "
			+ autoconsultaDatabase
			+ ".AT10F1 "
			+ "(AT10DI, AT10FE, AT10HO, AT10FU, AT10RE, AT10RU, AT10RA) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	// prepara llamado
	autoconsultaUtil.prepareCall(command);
	autoconsultaUtil.getStatement().setString(1,actividad.getDispositivo());
	autoconsultaUtil.getStatement().setDate(2, new java.sql.Date(actividad.getFechaTransaccion().getTime()));
	autoconsultaUtil.getStatement().setTime(3, actividad.getHoraTransaccion());
	autoconsultaUtil.getStatement().setInt(4, actividad.getFuncion());
	autoconsultaUtil.getStatement().setLong(5, actividad.getRutEmpresa());
	autoconsultaUtil.getStatement().setLong(6, actividad.getRutUsuario());
	autoconsultaUtil.getStatement().setLong(7, actividad.getRutAfiliado());

	
	autoconsultaUtil.execute();
}
	// FIN DATOS SEGURIDAD
	// INICIA DATOS CHEQUES
	/** 
	 * Obtiene los cheques del rut de la empresa consultado a partir de la fecha indicada
	 * @param long rut
	 * @param java.sql.Date fechaDesde, fechaa partir desde la cual se consultan los cheques
	 * @return Collection ChequeVO
	 */
	public Collection getChequesEmpresaByRutEmpresa(long rut, java.sql.Date fechaDesde) throws Exception, BusinessException {
		System.out.println("DAO: metodo getChequesEmpresaByRutEmpresa[" + rut + "][" + fechaDesde + "]");
		String command = "SELECT " + "A.TE3WA, " + "A.TE7NA, " + "A.TE3ZA, " + "B.TE7JA, " + "A.TE41A, " + "C.TE40A, " + "A.TE4AA " + "FROM " + tesoreriaDatabase + ".TE07U120 A, " + tesoreriaDatabase
				+ ".TE12U102 B, " + tesoreriaDatabase + ".TE07F1 C " + "WHERE A.TE42A= ? " + " AND A.TE3XA='E' " + " AND A.TE4CA='C' " + "AND A.TE3WA = B.TE3WA " + "AND A.TE3WA= C.TE3WA "
				+ "AND A.TE3ZA >= ? " + //'2004-11-01' "+		//AAAA-MM-DD
				"ORDER BY A.TE3ZA DESC";
		logger.debug("-- command: " + command);
		logger.debug("--- Rut: " + rut);
		logger.debug("--- Fecha Desde: " + fechaDesde);
		// prepara llamado
		tesoreriaUtil.prepareCall(command);
		tesoreriaUtil.getStatement().setLong(1, rut);
		tesoreriaUtil.getStatement().setDate(2, fechaDesde);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + tesoreriaUtil.getStatement().toString());
		
		return tesoreriaUtil.executeQuery(ChequeVO.class);
	}

	/** 
	 * Obtiene los cheques del rut de la empresa consultado a partir de la fecha indicada
	 * @param long rut
	 * @param java.sql.Date  fechaDesde, fechaa partir desde la cual se consultan los cheques
	 * @return Collection 
	 */
	public Collection getConceptoChequesEmpresa(long rut, java.sql.Date fechaDesde) throws Exception, BusinessException {
		System.out.println("DAO: metodo getConceptoChequesEmpresa[" + rut + "][" + fechaDesde + "]");
		String command = "SELECT " + "A.TE3WA, " + "B.TE4TA, " + "C.TE23A, " + "B.TE4QA, " + "C.TE1YA " + "FROM " + tesoreriaDatabase + ".TE07U120 A, " + tesoreriaDatabase + ".TE07L2 B, "
				+ tesoreriaDatabase + ".TE04A1 C " + "WHERE A.TE42A= ?" + "AND A.TE3WA = B.TE3WA " + "AND B.TE1YA = C.TE1YA " + "AND A.TE3XA = 'E' " + "AND A.TE4CA = 'C' " + "AND A.TE3ZA >= ? " + //AAAA-MM-DD
				"ORDER BY A.TE3WA ASC";
		// prepara llamado
		tesoreriaUtil.prepareCall(command);
		tesoreriaUtil.getStatement().setLong(1, rut);
		tesoreriaUtil.getStatement().setDate(2, fechaDesde);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + tesoreriaUtil.getStatement().toString());
		
		return tesoreriaUtil.executeQuery(ConceptoChequeVO.class);
	}

	/** 
	 * Obtiene la cantidad de cheques del rut de la empresa consultado a partir de la fecha indicada
	 * @param long rut
	 * @param java.sql.Date fechaDesde, fecha partir desde la cual se consultan los cheques
	 * @return Collection 
	 */
	public Collection getCantidadChequesEmpresa(long rut, java.sql.Date fechaDesde) throws Exception, BusinessException {
		System.out.println("DAO: metodo getCantidadChequesEmpresa[" + rut + "][" + fechaDesde + "]");
		String command = "SELECT " + "A.TE3WA, " + "COUNT(1) \"CANTIDAD\" " + "FROM " + tesoreriaDatabase + ".TE07U120 A, " + tesoreriaDatabase + ".TE07L2 B " + "WHERE A.TE42A = ? "
				+ "AND A.TE3XA = 'E' " + "AND A.TE4CA = 'C' " + "AND A.TE3WA = B.TE3WA " + "AND A.TE3ZA >= ? " + //AAAA-MM-DD
				"GROUP BY A.TE3WA " + "ORDER BY A.TE3WA ASC";
		// prepara llamado
		tesoreriaUtil.prepareCall(command);
		tesoreriaUtil.getStatement().setLong(1, rut);
		tesoreriaUtil.getStatement().setDate(2, fechaDesde);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + tesoreriaUtil.getStatement().toString());
		
		return tesoreriaUtil.executeQuery(CantidadChequesVO.class);
	}

	// FIN DATOS CHEQUES
	/**
	 * @return CantidadChequesVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static CantidadChequesVO buildCantidadChequesVO(ResultSet ors) throws SQLException {
		CantidadChequesVO vo = new CantidadChequesVO();
		vo.setFolio(ors.getLong("TE3WA"));
		vo.setCantidad(ors.getInt("CANTIDAD"));
		return vo;
	}

	/**
	 * @return ConceptoChequeVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static ConceptoChequeVO buildConceptoChequeVO(ResultSet ors) throws SQLException {
		ConceptoChequeVO vo = new ConceptoChequeVO();
		vo.setFolio(ors.getLong("TE3WA"));
		vo.setObservacionDetalle(ors.getString("TE4TA"));
		vo.setItemGasto(ors.getString("TE23A"));
		vo.setItem(ors.getInt("TE4QA"));
		vo.setCodigoConcepto(ors.getInt("TE1YA"));
		return vo;
	}

	/**
	 * @return ChequeVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static ChequeVO buildChequeVO(ResultSet ors) throws SQLException {
		ChequeVO vo = new ChequeVO();
		vo.setFolio(ors.getLong("TE3WA"));
		vo.setMonto(ors.getInt("TE7NA"));
		vo.setPeriodo(validaFechaNula(ors.getDate("TE3ZA")));
		vo.setCodigoEstadoCheque(ors.getString("TE7JA"));
		vo.setCodigoFormaPago(ors.getString("TE41A"));
		vo.setFechaPago(validaFechaNula(ors.getDate("TE40A")));
		vo.setSucursal(ors.getString("TE4AA"));
		return vo;
	}

	/**
	 * @return EmpresaVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static EmpresaVO buildEmpresaVO(ResultSet ors) throws SQLException {
		EmpresaVO vo = new EmpresaVO();
		vo.setRut(ors.getLong("RUT"));
		vo.setDv(ors.getString("DV"));
		vo.setNombre(ors.getString("NOMBRE"));
		vo.setEstado(ors.getString("ESTADO"));
		vo.setTipo(ors.getString("TIPO"));
		return vo;
	}

	/**
	 * @return EmpresaVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static EmpresaPublicaVO buildEmpresaPublicaVO(ResultSet ors) throws SQLException {
		EmpresaPublicaVO vo = new EmpresaPublicaVO();
		vo.setRut(ors.getLong("RUT"));
		vo.setDv(ors.getString("DV"));
		vo.setNombre(ors.getString("NOMBRE"));
		vo.setEstado(ors.getString("ESTADO"));
		vo.setTipo(ors.getString("TIPO"));
		return vo;
	}

	/**
	 * @return EmpresaAfiliadoVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static EmpresaAfiliadoVO buildEmpresaAfiliadoVO(ResultSet ors) throws SQLException {
		EmpresaAfiliadoVO vo = new EmpresaAfiliadoVO();
		vo.setRut(ors.getLong("RUT"));
		vo.setNombre(ors.getString("NOMBRE"));
		return vo;
	}

	/**
	 * @return DateVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static DateVO buildFechaVO(ResultSet ors) throws SQLException {
		DateVO vo = new DateVO();
		vo.setFecha(validaFechaNula(ors.getDate(Constants.ALIAS_DATE)));
		return vo;
	}

	/**
	 * @return IntVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static IntVO buildIntVO(ResultSet ors) throws SQLException {
		IntVO vo = new IntVO();
		vo.setValor(ors.getInt(Constants.ALIAS_INT));
		return vo;
	}

	/**
	 * @return DoubleVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static DoubleVO buildDoubleVO(ResultSet ors) throws SQLException {
		DoubleVO vo = new DoubleVO();
		vo.setValor(ors.getDouble(Constants.ALIAS_DOUBLE));
		return vo;
	}

	/**
	 * @return StringVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static StringVO buildStringVO(ResultSet ors) throws SQLException {
		StringVO vo = new StringVO();
		vo.setTexto(ors.getString(Constants.ALIAS_STRING));
		return vo;
	}

	/**
	 * @return CodigoDescripcionVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static CodigoDescripcionVO buildCodigoDescripcionVO(ResultSet ors) throws SQLException {
		CodigoDescripcionVO vo = new CodigoDescripcionVO();
		vo.setCodigo(ors.getInt(Constants.ALIAS_CODIGO));
		vo.setDescripcion(ors.getString(Constants.ALIAS_DESCRIPCION));
		return vo;
	}

	/** 
	 * Obtiene la lista de empresas de las cuales está a cargo el usuario
	 * @param long rut
	 * @return Collection 
	 */
	public Collection getEmpresaACargo(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getEmpresaACargo[" + rut + "]");
		String command = "SELECT " + "A.EERUTEMP, A.EEDVEMP, A.RUTUSR, A.EEALLOFI, A.EENOM, A.EEAPEM, A.EEAPEP " + "FROM " + autoconsultaDatabase + ".AT03F1 A " + "WHERE A.RUTUSR = ? "
				+ "ORDER BY A.EERUTEMP ASC";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(EmpresaACargoVO.class);
	}

	/**
	 * @return EmpresaACargoVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static EmpresaACargoVO buildEmpresaACargoVO(ResultSet ors) throws SQLException {
		EmpresaACargoVO vo = new EmpresaACargoVO();
		vo.setRut(ors.getLong("EERUTEMP"));
		vo.setDv(ors.getString("EEDVEMP"));
		vo.setRutEncargado(ors.getLong("RUTUSR"));
		vo.setAllOficinasSucursales(ors.getString("EEALLOFI"));
		vo.setNombre(ors.getString("EENOM"));
		vo.setApellidoMaterno(ors.getString("EEAPEM"));
		vo.setApellidoPaterno(ors.getString("EEAPEP"));
		return vo;
	}

	/** 
	 * Obtiene la lista de encargados
	 * @param long rut empresa
	 * @return Collection 
	 */
	public Collection getEncargados(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getEncargados[" + rut + "]");
		String command = "SELECT " + "A.EERUTEMP, A.EEDVEMP, A.RUTUSR, A.EEALLOFI, A.EENOM, A.EEAPEM, A.EEAPEP " + "FROM " + autoconsultaDatabase + ".AT03F1 A " + "WHERE A.EERUTEMP = ? ";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(EmpresaACargoVO.class);
	}

	/** 
	 * Obtiene la lista de RUTs de usuarios
	 * @return Collection 
	 */
	public Collection getUsuarios() throws Exception, BusinessException {
		System.out.println("ENTRA A DAO getUsuarios");
		String command = "SELECT " + "A.RUTUSR " + "FROM " + autoconsultaDatabase + ".AT30F1 A " + "ORDER BY A.RUTUSR";
		System.out.println("getUsuarios SQL: " + command);
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(RutVO.class);
	}

	/**
	 * @return RutVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static RutVO buildRutVO(ResultSet ors) throws SQLException {
		RutVO vo = new RutVO();
		vo.setRut(ors.getLong("RUTUSR"));
		vo.setDv("?");
		return vo;
	}

	/** 
	 * Registra un encargado
	 * @param EmpresaACargoVO VO con el encargado
	 */
	public void insertEncargado(EmpresaACargoVO vo, String usuarioModificador) throws Exception, BusinessException {
		System.out.println("DAO: metodo insertEncargado[" + vo + "][" + usuarioModificador + "]");
		String command = "INSERT INTO " + autoconsultaDatabase + ".AT03F1 " + "(EERUTEMP, EEDVEMP, RUTUSR, EEALLOFI, EENOM, EEAPEM, EEAPEP) " + "VALUES (?," + "?," + "?," + "?," + "?," + "?," + "?)";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, vo.getRut());
		autoconsultaUtil.getStatement().setString(2, vo.getDv());
		autoconsultaUtil.getStatement().setLong(3, vo.getRutEncargado());
		autoconsultaUtil.getStatement().setString(4, vo.getAllOficinasSucursales());
		autoconsultaUtil.getStatement().setString(5, vo.getNombre());
		autoconsultaUtil.getStatement().setString(6, vo.getApellidoMaterno());
		autoconsultaUtil.getStatement().setString(7, vo.getApellidoPaterno());
		autoconsultaUtil.execute();
		command = "INSERT INTO " + autoconsultaDatabase + ".AT05F1 " + "(RUTUSR, EERUTEMP, EEDVEMP, IDOPER, ACCION) " + "VALUES (?," + "?," + "?," + "?," + "?)";
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, vo.getRutEncargado());
		autoconsultaUtil.getStatement().setLong(2, vo.getRut());
		autoconsultaUtil.getStatement().setString(3, vo.getDv());
		autoconsultaUtil.getStatement().setString(4, usuarioModificador);
		autoconsultaUtil.getStatement().setString(5, "CREACION");
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		autoconsultaUtil.execute();
	}

	/** 
	 * Elimina un encargado
	 * @param EmpresaACargoVO vo
	 */
	public void deleteEncargado(EmpresaACargoVO vo, String usuarioModificador) throws Exception, BusinessException {
		System.out.println("DAO: metodo deleteEncargado[" + vo + "][" + usuarioModificador + "]");
		String command = "DELETE FROM " + autoconsultaDatabase + ".AT03F1 " + "WHERE RUTUSR = ? AND EERUTEMP = ?";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, vo.getRutEncargado());
		autoconsultaUtil.getStatement().setLong(2, vo.getRut());
		autoconsultaUtil.execute();
		command = "INSERT INTO " + autoconsultaDatabase + ".AT05F1 " + "(RUTUSR, EERUTEMP, EEDVEMP, IDOPER, ACCION) " + "VALUES (?," + "?," + "?," + "?," + "?)";
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, vo.getRutEncargado());
		autoconsultaUtil.getStatement().setLong(2, vo.getRut());
		autoconsultaUtil.getStatement().setString(3, vo.getDv());
		autoconsultaUtil.getStatement().setString(4, usuarioModificador);
		autoconsultaUtil.getStatement().setString(5, "ELIMINACION");
		
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		
		autoconsultaUtil.execute();
	}

	/**  
	 * Indica si el encargado con el rut de la empresa ya esta en la BD
	 * @param	long, rut encargado
	 * 			long, rut de la empresa
	 * @return Collection de EmpresaACargoVO
	 */
	public Collection getEncargadoEmpresa(EmpresaACargoVO empresaACargoVO) throws Exception, BusinessException {
		System.out.println("DAO: metodo getEncargadoEmpresa[" + empresaACargoVO.getRutEncargado() + "][" + empresaACargoVO.getRut() + "]");
		String command = "SELECT " + "EERUTEMP, EEDVEMP, RUTUSR, EEALLOFI, EENOM, EEAPEM, EEAPEP " + "FROM " + autoconsultaDatabase + ".AT03F1 " + "WHERE RUTUSR = ? " + "AND   EERUTEMP = ?";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, empresaACargoVO.getRutEncargado());
		autoconsultaUtil.getStatement().setLong(2, empresaACargoVO.getRut());
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(EmpresaACargoVO.class);
	}

	/** 
	 * Obtiene Collection de ultimo empleadores de un empleado
	 * Los datos los obtiene desde el historico
	 * @param long, rut del empleado
	 * @return Collection de EmpresaVO
	 */
	public Collection getUltimaEmpresaHistorica(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getUltimaEmpresaHistorica[" + rut + "]");
		String command = "SELECT CMNA \"RUT\", '' \"DV\", '' \"NOMBRE\", AF8BA \"ESTADO\", '' \"TIPO\" " + "FROM " + afiliadosDatabase + ".AF08L1 " + "WHERE SE5FAJC = ? " + "AND AF8AA = "
				+ "(SELECT MAX(AF8AA) FROM " + afiliadosDatabase + ".AF08L1 " + "WHERE SE5FAJC = ?)";
		// prepara llamado
		afiliadosUtil.prepareCall(command);
		afiliadosUtil.getStatement().setLong(1, rut);
		afiliadosUtil.getStatement().setLong(2, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + afiliadosUtil.getStatement().toString());
		
		return afiliadosUtil.executeQuery(EmpresaVO.class);
	}

	/** 
	 * Obtiene Collection de las liquidaciones de un empleado
	 * @param long, rut del empleado
	 * @return Collection de LiquidacionesVO
	 */
	public Collection getLiquidacionesReembolsosByRut(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getLiquidacionesReembolsosByRut[" + rut + "]");
		String command = "SELECT LIQTRAFEC, LIQNRO, LIQSALPRP," + "LIQTOTSOL, LIQTOTPEN, LIQTOTREC, LIQSALPOP," + "LIQTOTBON, LIQCNVID " + "FROM " + liquidacionesReembolsosDatabase + ".PA01F1 "
				+ "WHERE " + "LIQTRARUT = ? " + " ORDER BY LIQTRAFEC desc ";
		// prepara llamado
		liquidacionesReembolsosUtil.prepareCall(command);
		liquidacionesReembolsosUtil.getStatement().setLong(1, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + liquidacionesReembolsosUtil.getStatement().toString());
		
		return liquidacionesReembolsosUtil.executeQuery(LiquidacionesVO.class);
	}

	public Collection getReajuste(long rut, String fechaLiq) throws Exception, BusinessException {
		System.out.println("DAO: metodo getReajuste[" + rut + "][" + fechaLiq + "]");
		String command = "SELECT CARMONPES, CARSIGNO " + "FROM " + liquidacionesReembolsosDatabase + ".pa03f1 " + "WHERE CARTRARUT = ? " + // yyyy-mm-dd
				"AND CARCOD = 108 " + "AND carfecha = ? ";
		liquidacionesReembolsosUtil.prepareCall(command);
		liquidacionesReembolsosUtil.getStatement().setLong(1, rut);
		liquidacionesReembolsosUtil.getStatement().setString(2, fechaLiq);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + liquidacionesReembolsosUtil.getStatement().toString());
		
		return liquidacionesReembolsosUtil.executeQuery(ReajusteVO.class);
	}

	public static ReajusteVO buildReajusteVO(ResultSet ors) throws SQLException {
		ReajusteVO vo = new ReajusteVO();
		String signo = ors.getString("CARSIGNO");
		if (signo.equals("+"))
			vo.setReajuste(ors.getDouble("CARMONPES"));
		else
			vo.setReajuste(ors.getDouble("CARMONPES") * (-1));
		return vo;
	}

	public Collection getComision(long rut, String fechaLiq) throws Exception, BusinessException {
		System.out.println("DAO: metodo getComision[" + rut + "][" + fechaLiq + "]");
		String command = "SELECT CARMONPES " + "FROM " + liquidacionesReembolsosDatabase + ".pa03f1 " + "WHERE CARTRARUT = ? " + // yyyy-mm-dd
				"AND CARCOD = 203 " + "AND carfecha = ? ";
		liquidacionesReembolsosUtil.prepareCall(command);
		liquidacionesReembolsosUtil.getStatement().setLong(1, rut);
		liquidacionesReembolsosUtil.getStatement().setString(2, fechaLiq);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + liquidacionesReembolsosUtil.getStatement().toString());
		
		return liquidacionesReembolsosUtil.executeQuery(ComisionVO.class);
	}

	public static ComisionVO buildComisionVO(ResultSet ors) throws SQLException {
		ComisionVO vo = new ComisionVO();
		vo.setComision(ors.getDouble("CARMONPES"));
		return vo;
	}

	/**
	 * @return LiquidacionesVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static LiquidacionesVO buildLiquidacionesVO(ResultSet ors) throws SQLException {
		LiquidacionesVO vo = new LiquidacionesVO();
		vo.setFechaLiquidacion(ors.getString("LIQTRAFEC"));
		vo.setNroLiquidacion(ors.getInt("LIQNRO"));
		vo.setSaldoPrevioLiquidacion(ors.getDouble("LIQSALPRP"));
		vo.setTotalSolicitadoPesos(ors.getDouble("LIQTOTSOL"));
		vo.setTotalPendientePesos(ors.getDouble("LIQTOTPEN"));
		vo.setTotalRechazadoPesos(ors.getDouble("LIQTOTREC"));
		vo.setSaldoPosterior(ors.getDouble("LIQSALPOP"));
		vo.setTotalBonificadoPesos(ors.getDouble("LIQTOTBON"));
		vo.setNumeroConvenio(ors.getInt("LIQCNVID"));
		return vo;
	}

	/** 
	 * Obtiene Collection de los datos de un empleado
	 * @param long, rut del empleado
	 * @return Collection de DatosTrabajadoresLiquidacionesVO
	 */
	public Collection getDatosTrabajadorLiquidaciones(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDatosTrabajadorLiquidaciones[" + rut + "]");
		String command = "SELECT A.TRACNVID,A.TRARUT, A.TRADV, A.TRANOMBRE," + " A.TRAAPELL,A.TRAMAIL, A.TRACTACTE, A.TRABANID, " + " B.BANNOMBRE, C.EMPNOMBRE, C.EMPRUT, C.EMPDV" + " FROM  "
				+ liquidacionesReembolsosDatabase + ".PA06F1 A , " + liquidacionesReembolsosDatabase + ".PA07F1 B, " + liquidacionesReembolsosDatabase + ".PA08F1 C " + " WHERE  A.TRARUT = ? AND "
				+ " A.TRABANID = B.BANID AND" + " A.TRAEMPID = C.EMPID";
		// prepara llamado
		liquidacionesReembolsosUtil.prepareCall(command);
		liquidacionesReembolsosUtil.getStatement().setLong(1, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + liquidacionesReembolsosUtil.getStatement().toString());
		
		return liquidacionesReembolsosUtil.executeQuery(DatosTrabajadoresLiquidacionesVO.class);
	}

	/**
	 * @return LiquidacionesVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static DatosTrabajadoresLiquidacionesVO buildDatosTrabajadoresLiquidacionesVO(ResultSet ors) throws SQLException {
		DatosTrabajadoresLiquidacionesVO vo = new DatosTrabajadoresLiquidacionesVO();
		vo.setConvenioID(ors.getLong("TRACNVID"));
		vo.setRut(ors.getLong("TRARUT"));
		vo.setDv(ors.getString("TRADV"));
		vo.setNombre(ors.getString("TRANOMBRE"));
		vo.setApellido(ors.getString("TRAAPELL"));
		vo.setMail(ors.getString("TRAMAIL"));
		vo.setCtaCte(ors.getString("TRACTACTE"));
		vo.setBancoID(ors.getInt("TRABANID"));
		vo.setNombreBanco(ors.getString("BANNOMBRE"));
		vo.setNombreEmpresa(ors.getString("EMPNOMBRE"));
		vo.setRutEmpresa(ors.getLong("EMPRUT"));
		vo.setDvEmpresa(ors.getString("EMPDV"));
		return vo;
	}

	/** 
	 * Obtiene Collection de los movimientos de una liquidacion
	 * @param long, rut del empleado
	 * @return Collection de DatosTrabajadoresLiquidacionesVO
	 */
	public Collection getMovimientosLiquidacion(long rut, String nroliq) throws Exception, BusinessException {
		System.out.println("DAO: metodo getMovimientosLiquidacion[" + rut + "][" + nroliq + "]");
		String command = "SELECT MOVNOSOL,MOVPRECOD,FPREDESC, MOVMONBAS, MOVMONBON," + "MOVMONPEN, MOVMONREC , MOVCARNUM " + "FROM " + liquidacionesReembolsosDatabase + ".pa02f1 , "
				+ liquidacionesReembolsosDatabase + ".PA04F1 " + "WHERE MOVNUMLIQ= ? " + " AND MOVTRARUT= ? " + " AND MOVPRECOD = PREID ";
		// prepara llamado
		liquidacionesReembolsosUtil.prepareCall(command);
		liquidacionesReembolsosUtil.getStatement().setString(1, nroliq);
		liquidacionesReembolsosUtil.getStatement().setLong(2, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + liquidacionesReembolsosUtil.getStatement().toString());
		
		return liquidacionesReembolsosUtil.executeQuery(MovimientosLiquidacionVO.class);
	}

	/**
	 * @return LiquidacionesVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static MovimientosLiquidacionVO buildMovimientosLiquidacionVO(ResultSet ors) throws SQLException {
		MovimientosLiquidacionVO vo = new MovimientosLiquidacionVO();
		vo.setNumeroDocumento(ors.getString("MOVNOSOL"));
		vo.setCodigoPrestacion(ors.getInt("MOVPRECOD"));
		vo.setDescripcionPrestacion(ors.getString("FPREDESC"));
		vo.setMontoBase(ors.getDouble("MOVMONBAS"));
		vo.setMontoBonificado(ors.getDouble("MOVMONBON"));
		vo.setMontoPendiente(ors.getDouble("MOVMONPEN"));
		vo.setMontoRechazado(ors.getDouble("MOVMONREC"));
		vo.setNumeroCarga(ors.getInt("MOVCARNUM"));
		return vo;
	}

	/** 
	 * Obtiene Collection de los movimientos de una liquidacion
	 * @param long, rut del empleado
	 * @return Collection de DatosTrabajadoresLiquidacionesVO
	 */
	public Collection getResumenMensualByEmpresa(long rutEmp, long nroConvenio, String periodo) throws Exception, BusinessException {
		System.out.println("DAO: metodo getResumenMensualByEmpresa[" + rutEmp + "][" + nroConvenio + "][" + periodo + "]");
		String command = "SELECT RETRA,RERUTTRA,REFONINI,REFECAFON1,REMONAFON1,REFECAFON2,REMONAFON2,REFECAFON3,REMONAFON3,"
				+ "REFECAFON4,REMONAFON4,REFECACOM1,REMONCOM1,REFECACOM2,REMONCOM2,REFECACOM3,REMONCOM3,REFECACOM4,"
				+ "REMONCOM4,REFECVPAG1,REMONVPAG1,REFECVPAG2,REMONVPAG2,REFECVPAG3,REMONVPAG3,REFECVPAG4,REMONVPAG4,"
				+ "REFECFACT1,REMONFACT1,REFECFACT2,REMONFACT2,REFECFACT3,REMONFACT3,REFECFACT4,REMONFACT4,REINTERES,"
				+ "REREAJUST,REFONFINAL,REPERIODO,RERUTEMP,RECNV,CARFECHA, REMONRET1,REFECARET1,REMONRET2,REFECARET2," + "REMONRET3,REFECARET3,REMONRET4,REFECARET4 " + "FROM "
				+ liquidacionesReembolsosDatabase + ".pa20f1 " + "WHERE RERUTEMP = ? " + " AND RECNV = ? " + " AND REPERIODO = ? " + " ORDER BY RETRA ";
		System.out.println("strsql: " + command);
		// prepara llamado
		liquidacionesReembolsosUtil.prepareCall(command);
		liquidacionesReembolsosUtil.getStatement().setLong(1, rutEmp);
		liquidacionesReembolsosUtil.getStatement().setLong(2, nroConvenio);
		liquidacionesReembolsosUtil.getStatement().setString(3, periodo);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + liquidacionesReembolsosUtil.getStatement().toString());
		
		return liquidacionesReembolsosUtil.executeQuery(ResMensualPrestCompVO.class);
	}

	private static String formatDate(String d) {
		SimpleDateFormat dateFormatter, dateFormatter1;
		String dateOut = null;
		dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date today = null;
		try {
			today = dateFormatter1.parse(d);
			dateOut = dateFormatter.format(today);
		} catch (ParseException e) {
			dateOut = " ";
		} catch (Exception e) {
			dateOut = " ";
		}
		return dateOut;
	}

	/**
	 * @return LiquidacionesVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static ResMensualPrestCompVO buildResMensualPrestCompVO(ResultSet ors) throws SQLException {
		ResMensualPrestCompVO vo = new ResMensualPrestCompVO();
		vo.setNombreTrabajador(ors.getString("RETRA"));
		String rut = ors.getString("RERUTTRA");
		String dv = rut.substring(rut.length() - 1, rut.length());
		vo.setRutTrabajador(Integer.parseInt(rut.substring(0, rut.length() - 1)) + "-" + dv);
		vo.setFondoInicial(ors.getDouble("REFONINI"));
		vo.setFechaAumentoFondoInicial1(formatDate(ors.getString("REFECAFON1")));
		vo.setMontoAumentoInicial1(ors.getDouble("REMONAFON1"));
		vo.setFechaAumentoFondoInicial2(formatDate(ors.getString("REFECAFON2")));
		vo.setMontoAumentoInicial2(ors.getDouble("REMONAFON2"));
		vo.setFechaAumentoFondoInicial3(formatDate(ors.getString("REFECAFON3")));
		vo.setMontoAumentoInicial3(ors.getDouble("REMONAFON3"));
		vo.setFechaAumentoFondoInicial4(formatDate(ors.getString("REFECAFON4")));
		vo.setMontoAumentoInicial4(ors.getDouble("REMONAFON4"));
		vo.setFechaComision1(formatDate(ors.getString("REFECACOM1")));
		vo.setMontoAumentoComision1(ors.getDouble("REMONCOM1"));
		vo.setFechaComision2(formatDate(ors.getString("REFECACOM2")));
		vo.setMontoAumentoComision2(ors.getDouble("REMONCOM2"));
		vo.setFechaComision3(formatDate(ors.getString("REFECACOM3")));
		vo.setMontoAumentoComision3(ors.getDouble("REMONCOM3"));
		vo.setFechaComision4(formatDate(ors.getString("REFECACOM4")));
		vo.setMontoAumentoComision4(ors.getDouble("REMONCOM4"));
		vo.setFechaValorPagado1(formatDate(ors.getString("REFECVPAG1")));
		vo.setMontoValorPagado1(ors.getDouble("REMONVPAG1"));
		vo.setFechaValorPagado2(formatDate(ors.getString("REFECVPAG2")));
		vo.setMontoValorPagado2(ors.getDouble("REMONVPAG2"));
		vo.setFechaValorPagado3(formatDate(ors.getString("REFECVPAG3")));
		vo.setMontoValorPagado3(ors.getDouble("REMONVPAG3"));
		vo.setFechaValorPagado4(formatDate(ors.getString("REFECVPAG4")));
		vo.setMontoValorPagado4(ors.getDouble("REMONVPAG4"));
		vo.setFechaMontoActualizado1(formatDate(ors.getString("REFECFACT1")));
		vo.setMontoActualizado1(ors.getDouble("REMONFACT1"));
		vo.setFechaMontoActualizado2(formatDate(ors.getString("REFECFACT2")));
		vo.setMontoActualizado2(ors.getDouble("REMONFACT2"));
		vo.setFechaMontoActualizado3(formatDate(ors.getString("REFECFACT3")));
		vo.setMontoActualizado3(ors.getDouble("REMONFACT3"));
		vo.setFechaMontoActualizado4(formatDate(ors.getString("REFECFACT4")));
		vo.setMontoActualizado4(ors.getDouble("REMONFACT4"));
		vo.setFechaRetiroSolEmp1(formatDate(ors.getString("REFECARET1")));
		vo.setMontoRetiroSolEmp1(ors.getDouble("REMONRET1"));
		vo.setFechaRetiroSolEmp2(formatDate(ors.getString("REFECARET2")));
		vo.setMontoRetiroSolEmp2(ors.getDouble("REMONRET2"));
		vo.setFechaRetiroSolEmp3(formatDate(ors.getString("REFECARET3")));
		System.out.println("REFECARET3: " + formatDate(ors.getString("REFECARET3")));
		vo.setMontoRetiroSolEmp3(ors.getDouble("REMONRET3"));
		vo.setFechaRetiroSolEmp4(formatDate(ors.getString("REFECARET4")));
		vo.setMontoRetiroSolEmp4(ors.getDouble("REMONRET4"));
		vo.setMontoInteres(ors.getDouble("REINTERES"));
		vo.setMontoReajuste(ors.getDouble("REREAJUST"));
		vo.setMontoReajuste(ors.getDouble("REREAJUST"));
		vo.setMontoFondoFinal(ors.getDouble("REFONFINAL"));
		vo.setPeriodo(ors.getString("REPERIODO"));
		vo.setRutEmpresa(ors.getLong("RERUTEMP"));
		vo.setConvenioID(ors.getLong("RECNV"));
		vo.setFechaCierre(formatDate(ors.getString("CARFECHA")));
		return vo;
	}

	/**
	 * @param rut
	 * @return
	 */
	public Collection getNumeroConvenio(long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getNumeroConvenio[" + rut + "]");
		String command = "SELECT CNVID, EMPNOMBRE,EMPRUT,EMPDV , FONDES, CNVTIPFIJA, CNVTIPVAL " + "FROM " + liquidacionesReembolsosDatabase + ".pa08f1, " + //EMPRESAS
				liquidacionesReembolsosDatabase + ".pa05f1, " + // CONVENIOS
				liquidacionesReembolsosDatabase + ".pa03f1, " + // CARTOLA
				liquidacionesReembolsosDatabase + ".pa10f1 " + // FONDOS
				"WHERE EMPRUT= ? " + " AND CARCNVID = CNVID " + " AND EMPID = CNVEMOID " + " AND FONTIPO = CARFONDO ";
		// prepara llamado
		liquidacionesReembolsosUtil.prepareCall(command);
		liquidacionesReembolsosUtil.getStatement().setLong(1, rut);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + liquidacionesReembolsosUtil.getStatement().toString());
		
		return liquidacionesReembolsosUtil.executeQuery(ConvenioEmpresaVO.class);
	}

	/**
	 * @return ConvenioEmpresaVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static ConvenioEmpresaVO buildResConvenioEmpresaVO(ResultSet ors) throws SQLException {
		ConvenioEmpresaVO vo = new ConvenioEmpresaVO();
		vo.setNroConvevio(ors.getLong("CNVID"));
		vo.setNombreEmpresa(ors.getString("EMPNOMBRE"));
		vo.setRutEmpresa(ors.getLong("EMPRUT"));
		vo.setDvEmpresa(ors.getString("EMPDV"));
		vo.setFondo(ors.getString("FONDES"));
		vo.setTipFija(ors.getInt("CNVTIPFIJA"));
		vo.setTipValor(ors.getInt("CNVTIPVAL"));
		return vo;
	}

	public Collection getTip(long agno, long mes) throws Exception, BusinessException {
		System.out.println("DAO: metodo getTip[" + agno + "][" + mes + "]");
		String command = "SELECT TIPVALOR, TIPPROPIA  " + "FROM " + liquidacionesReembolsosDatabase + ".pa09f1 " + // TIP
				"WHERE TIPANO = ? " + " AND TIPMES = ? ";
		// prepara llamado
		liquidacionesReembolsosUtil.prepareCall(command);
		liquidacionesReembolsosUtil.getStatement().setLong(1, agno);
		liquidacionesReembolsosUtil.getStatement().setLong(2, mes);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + liquidacionesReembolsosUtil.getStatement().toString());
		
		return liquidacionesReembolsosUtil.executeQuery(TipVO.class);
	}

	/**
	 * @return TipVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static TipVO buildTipVO(ResultSet ors) throws SQLException {
		TipVO vo = new TipVO();
		vo.setTipValor(ors.getString("TIPVALOR"));
		vo.setTipPropia(ors.getString("TIPPROPIA"));
		return vo;
	}

	public Collection getPublicidad() throws Exception, BusinessException {
		System.out.println("DAO: metodo getPublicidad");
		String command = "SELECT MENSAJE  " + "FROM " + liquidacionesReembolsosDatabase + ".pa11f1 ";
		// prepara llamado
		liquidacionesReembolsosUtil.prepareCall(command);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + liquidacionesReembolsosUtil.getStatement().toString());
		
		return liquidacionesReembolsosUtil.executeQuery(PublicidadVO.class);
	}

	/**
	 * @return TipVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static PublicidadVO buildPublicidadVO(ResultSet ors) throws SQLException {
		PublicidadVO vo = new PublicidadVO();
		vo.setMensaje(ors.getString("MENSAJE").trim());
		return vo;
	}

	/** 
	 *  Metodo 
	 * @see cl.araucana.autoconsulta.dao.AutoconsultaDAO#putPublicidad(java.lang.String)
	 */
	public void putPublicidad(String texto) throws Exception, BusinessException {
		System.out.println("DAO: metodo putPublicidad[" + texto + "]");
		String command = "UPDATE " + liquidacionesReembolsosDatabase + ".pa11f1 SET " + "MENSAJE = ? ";
		// prepara llamado
		liquidacionesReembolsosUtil.prepareCall(command);
		liquidacionesReembolsosUtil.getStatement().setString(1, texto);
		int i = liquidacionesReembolsosUtil.executeUpdate();
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + liquidacionesReembolsosUtil.getStatement().toString());
		
		logger.debug("Bloquea Clave, Filas Afectadas: " + i);
	}

	/** 
	 * Recupera Collection de oficinas y sucursales de la empresa, además
	 * indica si el encargado tiene la oficina o susursal a su cargo
	 * @param	long, rut encargado
	 * 			long, rut de la empresa
	 * @return Collection de OficinasSucursalesVO
	 */
	public Collection getListaOficinasSucursalesByEmpresaEncargado(long rutEncargado, long rutEmpresa) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaOficinasSucursalesByEmpresaEncargado[" + rutEncargado + "][" + rutEmpresa + "]");
		String command = "select  c.RUTUSR, a.cmba, b.cmca, a.cm13a, a.cm14a " 
				+ "FROM " + empresasDatabase + ".cm03f1 a "
				+ "JOIN " + empresasDatabase + ".cm01f1 b  ON a.cmba = b.cmba "
				+ "LEFT OUTER JOIN " + autoconsultaDatabase + ".at04f1 c " + "ON (c.RUTUSR = ? " + "AND C.EERUTEMP = ? " + "AND c.DOSOFI = A.CMBA " + "AND      C.DOSSUC = A.CM13A) "
				+ "where cmna = ? " + "and cm18a <> 'E' " + "and a.cmba = b.cmba " + "order by a.cmba, a.cm13a";
		// prepara llamado
		empresaUtil.prepareCall(command);
		empresaUtil.getStatement().setLong(1, rutEncargado);
		empresaUtil.getStatement().setLong(2, rutEmpresa);
		empresaUtil.getStatement().setLong(3, rutEmpresa);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + empresaUtil.getStatement().toString());
		
		return empresaUtil.executeQuery(OficinasSucursalesVO.class);
	}

	/** 
	 * Recupera Collection de oficinas y sucursales de la empresa pública, además
	 * indica si el encargado tiene la oficina o susursal a su cargo
	 * @param	long, rut encargado
	 * 			long, rut de la empresa pública
	 * @return Collection de OficinasSucursalesVO
	 */
	public Collection getListaOficinasSucursalesByEmpresaPublicaEncargado(long rutEncargado, long rutEmpresaPublica) throws Exception, BusinessException {
		System.out.println("DAO: metodo getListaOficinasSucursalesByEmpresaPublicaEncargado[" + rutEncargado + "][" + rutEmpresaPublica + "]");
		String command = "select  c.RUTUSR, " + "		a.cmba, b.cmca, " + "		a.cm13a, a.cm14a " + "from " + publicoDatabase + ".cm03f1 a, " + publicoDatabase + ".cm01f1 b LEFT OUTER JOIN  "
				+ autoconsultaDatabase + ".at04f1 c " + "ON      (c.RUTUSR = ? " + "AND         C.EERUTEMP = ? " + "AND      c.DOSOFI = A.CMBA " + "AND      C.DOSSUC = A.CM13A) "
				+ "where   cmna = ? " + "and     cm18a <> 'E' " + "and     a.cmba = b.cmba " + "order by a.cmba, a.cm13a";
		// prepara llamado
		empresaPublicaUtil.prepareCall(command);
		empresaPublicaUtil.getStatement().setLong(1, rutEncargado);
		empresaPublicaUtil.getStatement().setLong(2, rutEmpresaPublica);
		empresaPublicaUtil.getStatement().setLong(3, rutEmpresaPublica);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + empresaPublicaUtil.getStatement().toString());
		
		return empresaPublicaUtil.executeQuery(OficinasSucursalesVO.class);
	}

	/**
	 * @return OficinasSucursalesVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static OficinasSucursalesVO buildOficinasSucursalesVO(ResultSet ors) throws SQLException {
		OficinasSucursalesVO vo = new OficinasSucursalesVO();
		vo.setCodigoOficina(ors.getLong("CMBA"));
		vo.setOficina(ors.getString("CMCA"));
		vo.setCodigoSucursal(ors.getLong("CM13A"));
		vo.setSucursal(ors.getString("CM14A"));
		vo.setACargo(ors.getString("RUTUSR") == null ? false : true);
		return vo;
	}

	/** 
	 * 
	 * indica si el encargado tiene la oficina o susursal del ifiliado a su cargo
	 * @param	long, rut encargado
	 * 			long, rut de la empresa
	 * 			long codigoOficina
	 * 			long codigoSucursal
	 * @return Collection de DoubleVO
	 */
	public Collection getOficinaSucursalByFiltro(long rutEncargado, long rutEmpresa, long codigoOficina, long codigoSucursal) throws Exception, BusinessException {
		System.out.println("DAO: metodo getOficinaSucursalByFiltro[" + rutEncargado + "][" + rutEmpresa + "][" + codigoOficina + "][" + codigoSucursal + "]");
		String command = "SELECT RUTUSR \"" + Constants.ALIAS_DOUBLE + "\" " + "FROM " + autoconsultaDatabase + ".AT04F1 " + "WHERE RUTUSR = ? " + "AND   EERUTEMP = ? " + "AND   DOSOFI = ? "
				+ "AND   DOSSUC = ?";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, rutEncargado);
		autoconsultaUtil.getStatement().setLong(2, rutEmpresa);
		autoconsultaUtil.getStatement().setLong(3, codigoOficina);
		autoconsultaUtil.getStatement().setLong(4, codigoSucursal);
		
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(DoubleVO.class);
	}

	/** 
	 * Realiza actualizacion de encargago
	 * @param empresaACargoVO
	 * @return void
	 */
	public void updateEncargado(EmpresaACargoVO vo) throws Exception, BusinessException {
		System.out.println("DAO: metodo updateEncargado[" + vo + "]");
		String command = "UPDATE " + autoconsultaDatabase + ".AT03F1 SET " + "EEALLOFI = ?, " + "EENOM = ?, " + "EEAPEM = ?, " + "EEAPEP = ? " + "WHERE EERUTEMP = ? " + "AND RUTUSR = ?";
		//prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setString(1, vo.getAllOficinasSucursales());
		autoconsultaUtil.getStatement().setString(2, vo.getNombre());
		autoconsultaUtil.getStatement().setString(3, vo.getApellidoMaterno());
		autoconsultaUtil.getStatement().setString(4, vo.getApellidoPaterno());
		autoconsultaUtil.getStatement().setLong(5, vo.getRut());
		autoconsultaUtil.getStatement().setLong(6, vo.getRutEncargado());
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		int i = autoconsultaUtil.executeUpdate();
		logger.debug("Update Encargados, Filas Afectadas: " + i);
		
		
	}

	/** 
	 * Elimina la informacion del encargado en la tabla AT04F1
	 * @param long rutEncargado, long rutEmpresa
	 */
	public void deleteOficinaSucursalesEncargado(long rutEncargado, long rutEmpresa) throws Exception, BusinessException {
		System.out.println("DAO: metodo deleteOficinaSucursalesEncargado[" + rutEncargado + "][" + rutEmpresa + "]");
		String command = "DELETE FROM " + autoconsultaDatabase + ".AT04F1 " + "WHERE RUTUSR = ? AND EERUTEMP = ?";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, rutEncargado);
		autoconsultaUtil.getStatement().setLong(2, rutEmpresa);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		autoconsultaUtil.execute();
	}

	/** 
	 * Registra sucursales y oficinas de un encargado según empresa
	 * @param long rutEncargado,long rutEmpresa, long codigoOficina, long codigoSucursa
	 */
	public void insertOficinaSucursalesEncargado(long rutEncargado, long rutEmpresa, long codigoOficina, long codigoSucursa) throws Exception, BusinessException {
		System.out.println("DAO: metodo insertOficinaSucursalesEncargado[" + rutEncargado + "][" + rutEmpresa + "][" + codigoOficina + "][" + codigoSucursa + "]");
		String command = "INSERT INTO " + autoconsultaDatabase + ".AT04F1 " + "(RUTUSR, EERUTEMP, DOSOFI, DOSSUC) " + "VALUES (?," + "?," + "?," + "?)";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, rutEncargado);
		autoconsultaUtil.getStatement().setLong(2, rutEmpresa);
		autoconsultaUtil.getStatement().setLong(3, codigoOficina);
		autoconsultaUtil.getStatement().setLong(4, codigoSucursa);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		autoconsultaUtil.execute();
	}

	/** 
	 * Recupera Collection de Licencias de un afiliado
	 * @param long, rut del afiliado
	 * @param long, numLicencia
	 * @return Collection de StringVO
	 */
	public Collection listaObservacionesCompin(long rutAfiliado, long numLicencia) throws Exception, BusinessException {
		System.out.println("DAO: metodo listaObservacionesCompin[" + rutAfiliado + "][" + numLicencia + "]");
		String command = "SELECT LICDESCRI \"" + Constants.ALIAS_STRING + "\" " + "FROM " + licenciasDatabase + ".ILF1014 " + "WHERE AFIRUT = ? AND LICIMPNUM = ? " + "ORDER BY LICCORREL ASC";
		// prepara llamado
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setLong(1, rutAfiliado);
		licenciasUtil.getStatement().setLong(2, numLicencia);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(StringVO.class);
	}

	/** 
	 * Recupera Collection de PerìodosPrestCompl
	 * @return Collection de PeriodoVO
	 */
	public Collection getPeriodosPrestCompl(long rutEmpresa) throws Exception, BusinessException {
		System.out.println("rutEmpresa DAO: " + rutEmpresa);
		System.out.println("liquidacionesReembolsosDatabase: " + liquidacionesReembolsosDatabase);
		String command = "SELECT REPERIODO " + "FROM " + liquidacionesReembolsosDatabase + ".pa20f1 " + "WHERE RERUTEMP = ? " + "GROUP BY REPERIODO " + "HAVING COUNT(*)>=1 " + "ORDER BY REPERIODO";
		// prepara llamado
		System.out.println("strsql: " + command);
		liquidacionesReembolsosUtil.prepareCall(command);
		liquidacionesReembolsosUtil.getStatement().setLong(1, rutEmpresa);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + liquidacionesReembolsosUtil.getStatement().toString());
		
		System.out.println("ANTES DE EJECUTAR: ");
		return liquidacionesReembolsosUtil.executeQuery(PeriodoVO.class);
	}

	/**
	 * @return DatosValidacionVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static PeriodoVO buildDatosPeriodoVO(ResultSet ors) throws SQLException {
		System.out.println("buildDatosPeriodoVO: ");
		PeriodoVO vo = new PeriodoVO();
		vo.setRePeriodo(ors.getLong("REPERIODO"));
		vo.setDescPeriodo(getDescripPeriodo(ors.getLong("REPERIODO")));
		return vo;
	}

	/**
	 * Si la fecha entregada es igual a "0001-01-01", entonces retorna null
	 * Si la fecha es distinta a "0001-01-01" retorna a misma fecha entregada
	 * @param fecha
	 * @return Date ó null
	 * @throws SQLException
	 */
	private static String getDescripPeriodo(long periodo) throws SQLException {
		String descPeriodo = "";
		try {
			String periodoAux = String.valueOf(periodo);
			int mes = new Integer(periodoAux.substring(4, 6)).intValue();
			String agno = periodoAux.substring(0, 4);
			switch (mes) {
			case 1: {
				descPeriodo = "Enero ";
				break;
			}
			case 2: {
				descPeriodo = "Febrero ";
				break;
			}
			case 3: {
				descPeriodo = "Marzo ";
				break;
			}
			case 4: {
				descPeriodo = "Abril ";
				break;
			}
			case 5: {
				descPeriodo = "Mayo ";
				break;
			}
			case 6: {
				descPeriodo = "Junio ";
				break;
			}
			case 7: {
				descPeriodo = "Julio ";
				break;
			}
			case 8: {
				descPeriodo = "Agosto ";
				break;
			}
			case 9: {
				descPeriodo = "Septiembre ";
				break;
			}
			case 10: {
				descPeriodo = "Octubre ";
				break;
			}
			case 11: {
				descPeriodo = "Noviembre ";
				break;
			}
			case 12: {
				descPeriodo = "Diciembre ";
				break;
			}
			default:
				break;
			}
			return descPeriodo + agno;
		} catch (Exception e) {
			//
		}
		return descPeriodo;
	}

	/* (no Javadoc)
	 * @see cl.araucana.autoconsulta.dao.AutoconsultaDAO#montoCreditoPreaprobado(long)
	 */
	public Collection montoCreditoPreaprobado(long rutCliente) throws Exception, SQLException {
		System.out.println("DAO: metodo montoCreditoPreaprobado[" + rutCliente + "]");
		String command = "select MONTOPREAPRO as MONTOCREDITO from " + autoconsultaDatabase + ".AT52F1 where RUTCLIENTE = ?";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, rutCliente);
		Collection col = autoconsultaUtil.executeQuery(MontoVO.class);
		//usado para traer el monto
	
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return col;
	}

	/* (no Javadoc)
	 * @see cl.araucana.autoconsulta.dao.AutoconsultaDAO#existeRegistroSimulacion(long, java.util.Date)
	 */
	public Collection existeRegistroSimulacion(long rutCliente) throws Exception, SQLException {
		System.out.println("DAO: metodo existeRegistroSimulacion[" + rutCliente + "]");
		String command = "select rutcliente RUTUSR from " + autoconsultaDatabase + ".at51f1 where rutcliente = ? and fecharegistro = CURRENT DATE";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, rutCliente);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(RutVO.class);
		//usado para traer el monto
	}

	/* (no Javadoc)
	 * @see cl.araucana.autoconsulta.dao.AutoconsultaDAO#setRegistroSimulacionCredito(long, char, long, long, int, java.sql.Date, java.sql.Date, boolean, boolean, boolean, java.lang.String, long, java.lang.String, long, java.lang.String, long, java.lang.String, java.sql.Date)
	 */
	public void setRegistroSimulacionCredito(long rutCliente, String dvCliente, long remuneracionLiq, long montoSolicitado, int numeroCuotas, java.sql.Date fechaNac, java.sql.Date fechaIngEmp,
			boolean seguroDegravamen, boolean seguroVida, boolean seguroCesantia, //String numFolio, 
			long montoPrepactado, String nombresCliente, long telefono, String correoElectronico, long rutEmpresa, String nombreEmpresa) throws Exception, SQLException {
		System.out.println("DAO: metodo setRegistroSimulacionCredito[...]");
		// TODO Apéndice de método generado automáticamente
		Collection col = this.existeRegistroSimulacion(rutCliente);
		if (col != null && col.size() > 0) {
			//update 
			String command = "update " + autoconsultaDatabase + ".at51f1 " + "set MONTOREPACTA = ?," + "REMUMERACIONLIQ = ?," + "MONTOSOLICITADO = ?," + "NUMCUOTAS = ?," + "FECHANAC = ?,"
					+ "FECHAINGEMP = ?," + "SEGDEGRAVAMEN = ?," + "SEGVIDA = ?," + "SEGSESANTIA  = ? " + "where rutCliente = ? and fecharegistro = CURRENT DATE";
			// prepara llamado
			autoconsultaUtil.prepareCall(command);
			autoconsultaUtil.getStatement().setLong(1, montoPrepactado);
			autoconsultaUtil.getStatement().setLong(2, remuneracionLiq);
			autoconsultaUtil.getStatement().setLong(3, montoSolicitado);
			autoconsultaUtil.getStatement().setInt(4, numeroCuotas);
			autoconsultaUtil.getStatement().setDate(5, fechaNac);
			autoconsultaUtil.getStatement().setDate(6, fechaIngEmp);
			autoconsultaUtil.getStatement().setInt(7, (seguroDegravamen == false ? 0 : 1));
			autoconsultaUtil.getStatement().setInt(8, (seguroVida == false ? 0 : 1));
			autoconsultaUtil.getStatement().setInt(9, (seguroCesantia == false ? 0 : 1));
			autoconsultaUtil.getStatement().setLong(10, rutCliente);
			//ejecuto
			
			System.out.println("command: " + command); 
			System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
			
			autoconsultaUtil.execute();
		}
		if (col != null & col.size() == 0) {
			//insert
			String command = "INSERT INTO " + autoconsultaDatabase + ".AT51F1 " + "(MONTOREPACTA, " + "REMUMERACIONLIQ, " + "MONTOSOLICITADO, " + "NUMCUOTAS, " + "FECHANAC, " + "FECHAINGEMP, "
					+ "SEGDEGRAVAMEN, " + "SEGVIDA, " + "SEGSESANTIA, " + "RUTCLIENTE, " + "DVCLIENTE, " + "NOMBRECLIENTE, " + "TELEFONO, " + "CORREOELECTRONICO, " + "RUTEMPRESA, "
					+ "NOMBREEMPRESA, " + "FECHAREGISTRO) " + "VALUES " + "(?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?,"
					+ "CURRENT DATE)";
			// prepara llamado
			autoconsultaUtil.prepareCall(command);
			autoconsultaUtil.getStatement().setLong(1, montoPrepactado);
			autoconsultaUtil.getStatement().setLong(2, remuneracionLiq);
			autoconsultaUtil.getStatement().setLong(3, montoSolicitado);
			autoconsultaUtil.getStatement().setInt(4, numeroCuotas);
			autoconsultaUtil.getStatement().setDate(5, fechaNac);
			autoconsultaUtil.getStatement().setDate(6, fechaIngEmp);
			autoconsultaUtil.getStatement().setInt(7, (seguroDegravamen == false ? 0 : 1));
			autoconsultaUtil.getStatement().setInt(8, (seguroVida == false ? 0 : 1));
			autoconsultaUtil.getStatement().setInt(9, (seguroCesantia == false ? 0 : 1));
			autoconsultaUtil.getStatement().setLong(10, rutCliente);
			autoconsultaUtil.getStatement().setString(11, dvCliente);
			String array[] = Funciones.split(nombresCliente, " ");
			int countLetras = 0;
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; i++) {
				String s = array[i];
				if (!s.equals("")) {
					for (int j = 0; j < array[i].length(); j++) {
						if (countLetras > 50) {
							break;
						}
						sb.append(s.charAt(j));
						countLetras++;
					}
					if (countLetras < 50) {
						sb.append(" ");
						countLetras++;
					}
				}
			}
			nombresCliente = sb.toString();
			autoconsultaUtil.getStatement().setString(12, nombresCliente);
			autoconsultaUtil.getStatement().setLong(13, telefono);
			autoconsultaUtil.getStatement().setString(14, correoElectronico);
			autoconsultaUtil.getStatement().setLong(15, rutEmpresa);
			autoconsultaUtil.getStatement().setString(16, nombreEmpresa);
			//autoconsultaUtil.getStatement().setString(17, "CURRENT DATE");
			//ejecuto
			
			System.out.println("command: " + command); 
			System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
			
			autoconsultaUtil.execute();
		}
	}

	/* (no Javadoc)
	 * @see cl.araucana.autoconsulta.dao.AutoconsultaDAO#datosComplClienteSimulacion(long)
	 */
	public Collection datosComplClienteSimulacion(long rutCliente) throws Exception, SQLException {
		System.out.println("DAO: metodo datosComplClienteSimulacion[" + rutCliente + "]");
		//NOTA: puede devolver de [0,2] registros, debo obtener algun email y el primer telefono en caso
		//de que existan 2 registros
		String command = "select bccoema EMAIL,bccofonuno TELEFONO from bcdta.bc13f1 where bcclrut = ?";
		//		prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, rutCliente);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(DatosComplementariosVO.class);
	}

	/* (no Javadoc)
	 * @see cl.araucana.autoconsulta.dao.AutoconsultaDAO#datosEmpresaAfiliadoSimulacion(long)
	 */
	public Collection datosEmpresaAfiliadoSimulacion(long rutCliente) throws Exception, SQLException {
		System.out.println("DAO: metodo datosEmpresaAfiliadoSimulacion[" + rutCliente + "]");
		String command = "select AFDTA.AF03F1.cmna RUT,CMDTA.CM02L1.CMPA NOMBRE from AFDTA.AF03F1,CMDTA.CM02L1 where afdta.af03f1.CMNA=CMDTA.CM02L1.CMNA AND se5fajc = ?";
		//		prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, rutCliente);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(EmpresaAfiliadoVO.class);
	}

	/* (no Javadoc)
	 * @see cl.araucana.autoconsulta.dao.AutoconsultaDAO#setSimulacionEstadistica(java.lang.String, int)
	 */
	public void setSimulacionEstadistica(String ipSimulacion, int funcion) throws Exception, SQLException {
		System.out.println("DAO: metodo setSimulacionEstadistica[" + ipSimulacion + "][" + funcion + "]");
		String command = "insert into ATDTA.AT10F1 (at10di,at10fe,at10ho,at10fu) values (?,CURRENT DATE,CURRENT TIME,?)";
		//		prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setString(1, ipSimulacion);
		autoconsultaUtil.getStatement().setInt(2, funcion);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		autoconsultaUtil.execute();
	}

	/** 
	 * Recupera sexo de un afiliado
	 * @param long, rut del afiliado		 
	 * @return Collection de StringVO
	 */
	public Collection getSexoAfiliadoBaseComun(long rutAfiliado) throws Exception, BusinessException {
		System.out.println("DAO: metodo getSexoAfiliadoBaseComun[" + rutAfiliado + "]");
		String command = "SELECT BCCLSEX \"" + Constants.ALIAS_STRING + "\" " + "FROM " + clientesDatabase + ".BC04F1 " + "WHERE BCCLRUT = ? ";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, rutAfiliado);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(StringVO.class);
	}

	/**
	 * Este método obtiene la DeudaInterCaja del afiliado pasado por parámetro 
	 * 
	 * @param rutAfiliado long
	 * @return Collection resultado
	 * @throws Exception ex
	 * @throws BusinessException bex
	 * @author sebastian.helguera (Neoris Argentina)
	 * @version 30/09/2009
	 */
	public Collection getDeudaIntercaja(long rutAfiliado) throws Exception, BusinessException {
		System.out.println("DAO: metodo getDeudaIntercaja[" + rutAfiliado + "]");
		String command = "SELECT A.MONDEU, B.DESCAJA FROM " + creditosDatabase + ".DEF1000 A LEFT JOIN " + creditosDatabase + ".DEF005 B ON B.CODCAJA = " + "A.CODCAJA WHERE A.RUTDEU = ?";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		autoconsultaUtil.getStatement().setLong(1, rutAfiliado);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(DeudaIntercajaVO.class);
	}

	/**
	 * Este método se encarga de convertir el ResultSet obtenido en 
	 * la consulta en un objeto DeudaIntercajaVO 
	 * 
	 * @param ResultSet ors
	 * @return DeudaIntercajaVO vo
	 * @throws SQLException sqlex
	 * @author sebastian.helguera (Neoris Argentina)
	 * @version 30/09/2009
	 */
	public static DeudaIntercajaVO buildDeudaIntercajaVO(ResultSet ors) throws SQLException {
		System.out.println("buildDeudaIntercajaVO: ");
		DeudaIntercajaVO vo = new DeudaIntercajaVO();
		vo.setCaja(ors.getString("DESCAJA"));
		vo.setMonto(ors.getLong("MONDEU"));
		return vo;
	}

	/**
	 * Recupera el valor del campo PAGAUX3 del archivo LIEXP/ILL9005
	 * 
	 * @param  long, numero de licencia
	 * @return Collection de Integer.
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getPagosComplementarios(long numLicencia) throws Exception, BusinessException {
		System.out.println("DAO: metodo getPagosComplementarios[" + numLicencia + "]");
		String command = "SELECT PAGAUX3 \"" + Constants.ALIAS_INT + "\" " + "FROM " + licenciasDatabase + ".ILL9005 " + "WHERE LICIMPNUM = ? ";
		//		prepara llamado
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setLong(1, numLicencia);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(IntVO.class);
	}

	/**
	 * Recupera la cantidad de pagos liquidados para la licencia.
	 * 
	 * @param long, numero de licencia
	 * @return Collection de IntVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getPagosLiquidados(long numLicencia, long rut) throws Exception, BusinessException {
		System.out.println("DAO: metodo getPagosLiquidados[" + numLicencia + "][" + rut + "]");
		String command = "SELECT COUNT (*) \"" + Constants.ALIAS_INT + "\" " + "FROM " + licenciasDatabase + ".ILF2500 " + "WHERE LICIMPNUM = ? " + " AND AFIRUT = ? ";
		//		prepara llamado
		licenciasUtil.prepareCall(command);
		licenciasUtil.getStatement().setLong(1, numLicencia);
		licenciasUtil.getStatement().setLong(2, rut);
		
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + licenciasUtil.getStatement().toString());
		
		return licenciasUtil.executeQuery(IntVO.class);
	}

	/**
	 * Retorna la cantidad de cheques en estado Caducado para la licencia dada.
	 *  
	 * @param long, numero del folio de pago.
	 * @return Collection de IntVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	// LPC 2011-03-17 - se agrego parametro rutAfiliado
	public Collection getChequesCaducados(long folioPago, long rutAfiliado) throws Exception, BusinessException {
		String command = "SELECT COUNT (*) \"" + Constants.ALIAS_INT + "\" " + "FROM " + tesoreriaDatabase + ".TE12F1 C, " + tesoreriaDatabase + ".TE07F1 B, " + licenciasDatabase + ".ILF3500 A "
				+ "WHERE C.TE7JA = 'C' " + "AND C.TE3WA = B.TE3WA " + "AND B.TE3WA =A.FOLIO " + "AND A.PAGFOL = ? " + "AND b.TE42A= ? ";
		//          prepara llamado
		tesoreriaUtil.prepareCall(command);
		tesoreriaUtil.getStatement().setLong(1, folioPago);
		tesoreriaUtil.getStatement().setLong(2, rutAfiliado);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + tesoreriaUtil.getStatement().toString());
		
		return tesoreriaUtil.executeQuery(IntVO.class);
	}

	public Collection getValorCuotaActual() throws Exception, BusinessException {
		String command = "SELECT AFVCUOVAL FROM " + leasingDatabase + ".T0018  ORDER BY AFVCUOFEC DESC FETCH FIRST 1 ROWS ONLY";
		// prepara llamado
		autoconsultaUtil.prepareCall(command);
		
		System.out.println("command: " + command); 
		System.out.println("sql: " + autoconsultaUtil.getStatement().toString());
		
		return autoconsultaUtil.executeQuery(ValorCuotaActualVO.class);
		//return null;
	}

	public static ValorCuotaActualVO buildUltimoValorCuotaVO(ResultSet ors) throws SQLException {
		System.out.println("buildUltimoValorCuotaVO: ");
		ValorCuotaActualVO vo = new ValorCuotaActualVO();
		vo.setUltimoValorCuota(ors.getFloat("AFVCUOVAL"));
		return vo;
	}
	/*
	 * LPC 2011-03-17
	 public Collection getChequesCaducados(long folioPago) throws Exception, BusinessException {
	 System.out.println("DAO: metodo getChequesCaducados["+folioPago+"]");
	 String command =
	 "SELECT COUNT (*) \""
	 + Constants.ALIAS_INT
	 + "\" "
	 + "FROM "
	 + tesoreriaDatabase
	 + ".TE12F1 C "
	 + "WHERE C.TE7JA = 'C' AND C.TE3WA = ( "
	 + "SELECT B.TE3WA " 
	 + "FROM "
	 + tesoreriaDatabase 
	 + ".TE07F1 B "
	 + "WHERE B.TE41A = 'C' AND B.TE3WA = ( "
	 + "SELECT A.FOLIO "
	 + "FROM " 
	 + licenciasDatabase  
	 + ".ILF3500 A " 
	 + "WHERE A.PAGFOL = ?))";
	 //		prepara llamado
	 tesoreriaUtil.prepareCall(command);
	 tesoreriaUtil.getStatement().setLong(1, folioPago);
	 
	 return tesoreriaUtil.executeQuery(IntVO.class);
	 }
	 */
}
