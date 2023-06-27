package cl.araucana.prestamo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.prestamo.vo.CuotaPendiente_PrestamoVO;
import cl.araucana.prestamo.vo.CuotaVO;
import cl.araucana.prestamo.vo.TotalInteresesVO;
import cl.araucana.prestamo.vo.TotalPendienteDescuentoVO;

import com.schema.util.FileSettings;
import com.schema.util.dao.DB2Utils;

/**
 * @author asepulveda
 *
 */
public class DB2PrestamoDAO implements PrestamoDAO {
	Logger logger = Logger.getLogger(DB2PrestamoDAO.class);
	private final static String PREFIX="DB2-";
	
	private String prestamoDatabase;
	private String prestamoJNDIDataSource;
	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	public DB2PrestamoDAO(){
		prestamoDatabase=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/databases/prestamo");
		prestamoJNDIDataSource=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/jdbc/prestamo");
	}
	
	/**
	 * Obtiene las cuotas por Cobrar
	 * @return ArrayList de CuotaVO
	 * @throws Exception
	 * @throws BusinessException
	 */ 

	public ArrayList getCuotas() throws Exception, BusinessException {
	Connection conn = null;
	CallableStatement stmt = null;
	ResultSet ors = null;
	
	SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault());
		
	String command = "SELECT REGDESC FROM " + prestamoDatabase + ".BIF3015";
	
	ArrayList cuotas = new ArrayList();
								
	try {
	  conn = DB2Utils.createConnection(prestamoJNDIDataSource);
	  stmt = conn.prepareCall(command);
		  
	  logger.debug("Inicia operación: " + command);
	  ors = stmt.executeQuery();
	  logger.debug("Finaliza operación: " + command);
		  	
	  while (ors.next()) {
		String respuesta = ors.getString("REGDESC");
		CuotaVO cuota = new CuotaVO();
		cuota.setRut(respuesta.substring(1, 9));
		cuota.setTipoPrestamo(Integer.parseInt(respuesta.substring(9, 10)));
		cuota.setNumeroCuotasTotales(
			Integer.parseInt(respuesta.substring(10, 12)));
		cuota.setCuotaActual(Integer.parseInt(respuesta.substring(12, 14)));
		cuota.setMonto(Integer.parseInt(respuesta.substring(14, 22)));
		cuota.setFecha(
			formato.parse(
				respuesta.substring(28,30) //dia
					+ "/"
					+ respuesta.substring(26, 28) //mes
					+ "/"
					+ respuesta.substring(22, 26))); //año
		cuota.setTipoFinanciamiento(respuesta.substring(30, 31));
		cuota.setCuentaContable(respuesta.substring(31, 37));
		cuotas.add(cuota);
	  }
	} catch (SQLException ex) {
		ex.printStackTrace();
		int code=ex.getErrorCode();
		throw new BusinessException(PREFIX+code);
	} finally {
	  DB2Utils.closeAll(conn, stmt, ors);
	}
		logger.debug("Retornaron: "+cuotas.size());
		return cuotas;		
	}

	
	/**
	 * @return DetalleMovimientosCuentaVO
	 * @throws SQLException
	 * @param ResultSet
	 */
	public static TotalPendienteDescuentoVO buildTotalPendienteDescuentoVO(ResultSet ors) throws SQLException { 
		TotalPendienteDescuentoVO vo = new TotalPendienteDescuentoVO();
		vo.setInteres(ors.getLong("totalIntereses"));
		return vo; 
	}

	/* (no Javadoc)
	 * @see cl.araucana.prestamo.dao.PrestamoDAO#getMontoUFPrestamoActivo(java.lang.String, int, int)
	 */
	public Collection getMontoUFPrestamoActivo(String rut, int cuotaLimiteInf, int numTotalCuotas) throws Exception, BusinessException {
		String command = "SELECT B.RUTCUO, B.TPCUO, sum(B.MONTOCUO ) montoUF, A.NROMP idPrestamo FROM " +
						"biexp.bif3010 a, biexp.bif3020 b WHERE A.RUTMP = B.RUTCUO and " +
						"a.ACTMP = 1 and B.ESTACUO = 1 and B.NROCCUO >= ? and A.NCUOMP = ? and A.RUTMP like '%"+rut+"%' " + 
						"group by b.rutcuo,B.TPCUO, A.NROMP";
		DB2Utils db2Util = new DB2Utils(prestamoJNDIDataSource,this);	
		// prepara llamado
		db2Util.prepareCall(command);
		
		db2Util.getStatement().setInt(1,cuotaLimiteInf);
		db2Util.getStatement().setInt(2,numTotalCuotas);
		
		return db2Util.executeQuery(CuotaPendiente_PrestamoVO.class);
	}
	
	public static CuotaPendiente_PrestamoVO buildCuotaPendiente_PrestamoVO(ResultSet ors) throws SQLException { 
		CuotaPendiente_PrestamoVO vo = new CuotaPendiente_PrestamoVO();
		//vo.setCuota(ors.getInt("cuota"));
		vo.setIdPrestamo(ors.getLong("idPrestamo"));
		vo.setMontoUF(ors.getFloat("montoUF"));
		return vo; 
	}

	/* (no Javadoc)
	 * @see cl.araucana.prestamo.dao.PrestamoDAO#getInteresesPagoAnticipado(long)
	 * Antiguos parametros int mes, int anioActual
	 */
	public Collection getInteresesPagoAnticipado(long idPrestamo,int cuotaIni,int cuotaFin) throws Exception, BusinessException {
		//OLD
		/*String command = "SELECT sum(IPACUO) as montoTotal FROM biexp.bif3020 WHERE NROPCUO = ? and ESTACUO = 1  " +
						"and ((FEMMCUO > ? and FEAACUO >= ? ) " +
						"OR (FEAACUO >=? + 1)) ";
		*/
		
		if(cuotaIni >= cuotaFin){
			throw new BusinessException("cuota inicial no puede ser mayor que la final");
		}
		
		String command = "SELECT sum(IPACUO) as montoTotal, 0 as mes, 0 " +
			" as anio FROM biexp.bif3020 WHERE NROPCUO = ? and  NROCCUO between ? and ?";
						
		DB2Utils db2Util = new DB2Utils(prestamoJNDIDataSource,this);	
		// prepara llamado
		db2Util.prepareCall(command);
		
		db2Util.getStatement().setLong(1,idPrestamo);
		db2Util.getStatement().setInt(2,cuotaIni);
		db2Util.getStatement().setInt(3,cuotaFin);
		//db2Util.getStatement().setLong(4,anioActual);
		
		return db2Util.executeQuery(TotalInteresesVO.class);
	}
	
	

	public static TotalInteresesVO buildTotalInteresesVO(ResultSet ors) throws SQLException { 
			TotalInteresesVO vo = new TotalInteresesVO();
			vo.setMontoTotal(ors.getLong("montoTotal"));
			vo.setMes(ors.getInt("mes"));
			vo.setAnio(ors.getInt("anio"));
			return vo; 
		}

	/* (no Javadoc)
	 * @see cl.araucana.prestamo.dao.PrestamoDAO#getInteresesPagoAnticipadoPeriodo(long, int, int)
	 */
	public Collection getInteresesPagoAnticipadoPeriodo(long idPrestamo, int idCuota)  throws Exception, BusinessException{
		String command = "SELECT sum(IPACUO) montoTotal,FEMMCUO  mes, FEAACUO anio FROM biexp.bif3020 WHERE NROPCUO=? and ESTACUO = 1 and NROCCUO = ? group by femmcuo, feaacuo";
						
		DB2Utils db2Util = new DB2Utils(prestamoJNDIDataSource,this);	
		// prepara llamado
		db2Util.prepareCall(command);
		
		db2Util.getStatement().setLong(1,idPrestamo);
		db2Util.getStatement().setInt(2,idCuota);
		
		return db2Util.executeQuery(TotalInteresesVO.class);
	}

	/* (no Javadoc)
	 * @see cl.araucana.prestamo.dao.PrestamoDAO#getCuotasAfiliado(java.lang.String)
	 */
	public ArrayList getCuotas(String rut) throws Exception, BusinessException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet ors = null;
	
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault());
		
		String command = "SELECT REGDESC FROM " + prestamoDatabase + ".BIF3015 where substr(regdesc, 1, 9) like '%"+rut+"%'";
	
		ArrayList cuotas = new ArrayList();
								
		try {
		  conn = DB2Utils.createConnection(prestamoJNDIDataSource);
		  stmt = conn.prepareCall(command);
		  //stmt.setString(1,rut);
		  logger.debug("Inicia operación: " + command);
		  ors = stmt.executeQuery();
		  logger.debug("Finaliza operación: " + command);
		  	
		  while (ors.next()) {
			String respuesta = ors.getString("REGDESC");
			CuotaVO cuota = new CuotaVO();
			cuota.setRut(respuesta.substring(1, 9));
			cuota.setTipoPrestamo(Integer.parseInt(respuesta.substring(9, 10)));
			cuota.setNumeroCuotasTotales(
				Integer.parseInt(respuesta.substring(10, 12)));
			cuota.setCuotaActual(Integer.parseInt(respuesta.substring(12, 14)));
			cuota.setMonto(Integer.parseInt(respuesta.substring(14, 22)));
			cuota.setFecha(
				formato.parse(
					respuesta.substring(28,30) //dia
						+ "/"
						+ respuesta.substring(26, 28) //mes
						+ "/"
						+ respuesta.substring(22, 26))); //año
			cuotas.add(cuota);
		  }
		} catch (SQLException ex) {
			ex.printStackTrace();
			int code=ex.getErrorCode();
			throw new BusinessException(PREFIX+code);
		} finally {
		  DB2Utils.closeAll(conn, stmt, ors);
		}
			logger.debug("Retornaron: "+cuotas.size());
			return cuotas;		
	}

}
