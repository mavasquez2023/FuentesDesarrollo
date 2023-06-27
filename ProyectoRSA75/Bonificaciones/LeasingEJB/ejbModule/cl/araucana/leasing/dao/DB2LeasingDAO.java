package cl.araucana.leasing.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.leasing.vo.CuentaAhorroVO;
import cl.araucana.leasing.vo.DetalleMovimientosCuentaVO;
import cl.araucana.leasing.vo.UFVO;

import com.schema.util.FileSettings;
import com.schema.util.dao.DB2Utils;

/**
 * @author asepulveda
 *
 */
public class DB2LeasingDAO implements LeasingDAO {
	static Logger logger = Logger.getLogger(DB2LeasingDAO.class);
//	private final static String PREFIX="DB2-";
	

	private DB2Utils db2Utl;

	private String leasingDatabase;
	
	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	public DB2LeasingDAO(){
		// Carga de datasources y creación de DB2Util
		String datasource=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/jdbc/leasing");
		leasingDatabase=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			"/application-settings/databases/leasing");
		db2Utl = new DB2Utils(datasource,this);			 

	}

	/** 
	 * Obtiene información de las cuentas de ahorro que tiene el rut consultado
	 * @param long rut
	 * @return Collection 
	 */
	public Collection getListaCuentaAhorroByRut(long rut) throws Exception, BusinessException {
		
		String command = "SELECT "+
		"CTAAHONUM, "+
		"CTAAHODV, "+
		"CTAAHOFEC, "+
		"CTAAHOEST "+
		"FROM "+leasingDatabase+".T0012 "+
		"WHERE AHORUT = ? " +
		"AND CTAAHOEST NOT IN(4, 5, 6)";  
					   				 
		// prepara llamado
		db2Utl.prepareCall(command);
		db2Utl.getStatement().setLong(1,rut);
		
		return db2Utl.executeQuery(CuentaAhorroVO.class);
	}
	
	/** 
	 * Obtiene el detalle de una cartola de ahorro
	 * @param CuentaAhorroVO cuenta
	 * @return Collection DetalleMovimientosCuentaAhorroVO
	 */
	public Collection getDetalleCuentaAhorroByCuentaAhorro(CuentaAhorroVO cuenta) throws Exception, BusinessException {

		
		String command = "SELECT "+
		"A.MOVDES, "+
		"B.MOVFEC, "+
		"B.MOVCUO, "+
		"B.MOVMON, "+
		"B.MOVCOD, "+
		"B.MOVEST, "+
		"B.CTAAHONUM, "+
		"B.FORFOL "+
		"FROM "+leasingDatabase+".I01301 A, "+
		leasingDatabase+".IU103 B "+
		"WHERE B.CTAAHONUM= ? "+
		"AND B.MOVCOD = A.MOVCOD "+
		//"AND LSGDTA.IU103.MOVEST NOT IN(5, 7, 8, 9) "+
		"AND B.MOVEST NOT IN(8, 9) "+
		//"AND LSGDTA.IU103.MOVCUO = 0 "+
		"AND A.MOVCOD in(22,31)";

		// prepara llamado
		db2Utl.prepareCall(command);
		db2Utl.getStatement().setString(1,cuenta.getNumeroCuenta());
		
		return db2Utl.executeQuery(DetalleMovimientosCuentaVO.class);
	}
	
	/**
	 * @return DetalleMovimientosCuentaVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static DetalleMovimientosCuentaVO buildDetalleMovimientosCuentaVO(ResultSet ors) throws SQLException { 
		DetalleMovimientosCuentaVO vo = new DetalleMovimientosCuentaVO();
		vo.setDescripcionDetalle(ors.getString("MOVDES"));
		vo.setFechaDetalle(ors.getString("MOVFEC"));
		vo.setCuotas(ors.getDouble("MOVCUO"));
		vo.setTotalValor(ors.getInt("MOVMON"));
		vo.setCodigoMovimiento(ors.getInt("MOVCOD"));
		vo.setEstadoMovimiento(ors.getInt("MOVEST"));
		vo.setNumeroCuenta(ors.getString("CTAAHONUM"));
		vo.setFolioFormulario(ors.getString("FORFOL"));
		return vo; 
	}

	/**
	 * @return CuentaAhorroVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static CuentaAhorroVO buildCuentaAhorroVO(ResultSet ors) throws SQLException { 
		SimpleDateFormat formatoBD = new SimpleDateFormat ("yyyyMMdd");
		CuentaAhorroVO vo = new CuentaAhorroVO();
		vo.setNumeroCuenta(ors.getString("CTAAHONUM"));
		vo.setDvNumeroCuenta(ors.getString("CTAAHODV"));
		String fec = String.valueOf(ors.getBigDecimal("CTAAHOFEC"));
		logger.debug("Fecha texto: "+fec);
		if(fec.equals("0")){
			vo.setFechaAperturaCuenta(null);
		}else{
			java.util.Date fechaI = formatoBD.parse(fec,new ParsePosition(0));
			java.sql.Date fechaInicio = new java.sql.Date(fechaI.getTime());
			vo.setFechaAperturaCuenta(fechaInicio);			
		}
		
		vo.setEstadoCuenta(ors.getInt("CTAAHOEST"));

		return vo; 
	}

	/* (no Javadoc)
	 * @see cl.araucana.leasing.dao.LeasingDAO#getValorUF(java.sql.Date)
	 */
	public Collection getValorUF(String fechaCalculo) throws Exception {
		String command = "SELECT UFVAL FROM "+leasingDatabase+".t0016 WHERE UFFEC = ?";
			
		// prepara llamado
		db2Utl.prepareCall(command);
		db2Utl.getStatement().setString(1,fechaCalculo);
				
		return db2Utl.executeQuery(UFVO.class);
					
	}

	public static UFVO buildValorUFVO(ResultSet ors) throws SQLException{
		UFVO uf = new UFVO();
		
		uf.setValor(ors.getFloat("UFVAL"));
		//uf.setFecha(ors.getString("UFFEC"));
		
		return uf;
	}

}
