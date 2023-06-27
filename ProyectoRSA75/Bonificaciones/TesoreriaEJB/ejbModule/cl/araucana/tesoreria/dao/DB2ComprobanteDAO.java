package cl.araucana.tesoreria.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.tesoreria.model.Comprobante;
import cl.araucana.tesoreria.model.Detalle;

import com.schema.util.FileSettings;
import com.schema.util.dao.DB2Utils;

/**
 * @author asepulveda
 *
 */
public class DB2ComprobanteDAO implements ComprobanteDAO {
	Logger logger = Logger.getLogger(DB2ComprobanteDAO.class);
	private final static String PREFIX="DB2-";
	
	public static final int TESORERIA_BIENESTAR=0;
	public static final int TESORERIA_ARAUCANA=1;
	
	private String tesoreriaBienestarDatabase;
	private String tesoreriaAraucanaDatabase;
	private String tesoreriaBienestarJNDIDataSource;
	private String tesoreriaAraucanaJNDIDataSource;
	
	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	public DB2ComprobanteDAO(){
		tesoreriaBienestarDatabase=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/databases/tesoreria-bienestar");		
		tesoreriaBienestarJNDIDataSource=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/jdbc/tesoreria-bienestar");
		tesoreriaAraucanaDatabase=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/databases/tesoreria-araucana");		
		tesoreriaAraucanaJNDIDataSource=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/jdbc/tesoreria-araucana");
	}
	
	/**
	 * Crea un nuevo Comprobante de Reembolso en Tesoreria
	 * @param comprobante: el Objeto Comprobante
	 */
	public void insertComprobante(Comprobante comprobante,int tesoreria) throws Exception, BusinessException {
	  
	  String database =null;
	  String JNDIDataSource=null;
	  
	  switch (tesoreria) {
		case TESORERIA_BIENESTAR:
			database = tesoreriaBienestarDatabase;
			JNDIDataSource = tesoreriaBienestarJNDIDataSource;
		  	break;
		case TESORERIA_ARAUCANA:
			database = tesoreriaAraucanaDatabase;
			JNDIDataSource = tesoreriaAraucanaJNDIDataSource;
			break;
		default:
			throw new BusinessException("CCAF_TESO_SISTEMAINVALIDO",
					   "Debe indicar un Sistema de Tesoreria Válido");
	  }

	  
	  logger.debug("Tesorería, Folio Movimiento: " + comprobante.getFolioMovimiento());
	  if (comprobante == null) {
		throw new BusinessException("CCAF_TESO_COMPROBANTEINVALIDO",
								   "Se ha intentado crear un Comprobante Nulo");
	  }

	  Connection conn = null;
	  CallableStatement stmt = null;
	  ResultSet ors = null;
	  
	  String command = "INSERT INTO "+database+".TE07F1 (TE3WA, TE3XA, TE3YA, "+
		"TE3ZA, TE1SA, TE40A, TE1TA, TE41A, TE42A, TE43A, TE44A, TE45A, TE46A, TE47A, "+
		"TEA7A, TE7MA, TE4VA, TE4XA, TE7NA, TE49A, TE4AA, TE4BA, TE4CA, TE4DA, TE4EA, "+
		"CMBA, TE9CA, TEQA, TE1BA, TE1CA, TE10A, TE4FA, OBF002, OBF003, OBF005, OBF006, "+
		"SAJKM94, SAJKM92) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+
		"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	  try {
		conn = DB2Utils.createConnection(JNDIDataSource);
		
		stmt = conn.prepareCall(command);
		
		logger.debug("FolioMovimiento: " + comprobante.getFolioMovimiento());
		stmt.setDouble(1, comprobante.getFolioMovimiento());
		logger.debug("TipoMovimiento: " + comprobante.getTipoMovimiento());
		stmt.setString(2, comprobante.getTipoMovimiento());
		logger.debug("EstadoMovimientoCaja: " + comprobante.getEstadoMovimientoCaja());
		stmt.setString(3, comprobante.getEstadoMovimientoCaja());
		logger.debug("FechaEmision: " + comprobante.getFechaEmision());
		if (comprobante.getFechaEmision() != null)
			stmt.setDate(4,new java.sql.Date(comprobante.getFechaEmision().getTime()));
		else
			stmt.setNull(4,Types.DATE);
		logger.debug("HoraEmision: " + comprobante.getHoraEmision());
		if (comprobante.getHoraEmision() != null)
			stmt.setTime(5,new java.sql.Time(comprobante.getHoraEmision().getTime()));
		else
			stmt.setNull(5,Types.TIME);
		logger.debug("FechaRecaudacion: " + comprobante.getFechaRecaudacion());
		if (comprobante.getFechaRecaudacion() != null)
			stmt.setDate(6,new java.sql.Date(comprobante.getFechaRecaudacion().getTime()));
		else
			stmt.setNull(6,Types.DATE);
		logger.debug("HoraRecaudacion: " + comprobante.getHoraRecaudacion());
		if (comprobante.getHoraRecaudacion() != null)
			stmt.setTime(7,new java.sql.Time(comprobante.getHoraRecaudacion().getTime()));
		else
			stmt.setNull(7,Types.TIME);
		logger.debug("FormaPago: " + comprobante.getFormaPago());
		stmt.setString(8, comprobante.getFormaPago());
		logger.debug("Rut1: " + comprobante.getRut1());
		stmt.setString(9, String.valueOf(comprobante.getRut1()));
		logger.debug("Dv1: " + comprobante.getDv1());
		stmt.setString(10, comprobante.getDv1());
		logger.debug("NombreRut1: " + comprobante.getNombreRut1());
		stmt.setString(11, comprobante.getNombreRut1());
		logger.debug("Rut2: " + comprobante.getRut2());
		stmt.setString(12, String.valueOf(comprobante.getRut2()));
		logger.debug("Dv2: " + comprobante.getDv2());
		stmt.setString(13, comprobante.getDv2());
		logger.debug("NombreRut2: " + comprobante.getNombreRut2());
		stmt.setString(14, comprobante.getNombreRut2());
		logger.debug("CodigoBarra: " + comprobante.getCodigoBarra());
		stmt.setString(15, comprobante.getCodigoBarra());
		logger.debug("MontoInformado: " + comprobante.getMontoInformado());
		stmt.setString(16, String.valueOf(comprobante.getMontoInformado()));
		logger.debug("MontoInteres: " + comprobante.getMontoInteres());
		stmt.setString(17, String.valueOf(comprobante.getMontoInteres()));
		logger.debug("MontoReajuste: " + comprobante.getMontoReajuste());
		stmt.setString(18, String.valueOf(comprobante.getMontoReajuste()));
		logger.debug("MontoEmitido: " + comprobante.getMontoEmitido());
		stmt.setString(19, String.valueOf(comprobante.getMontoEmitido()));
		logger.debug("ObservaciónMovimientoCaja: " + comprobante.getObservaciónMovimientoCaja());
		stmt.setString(20, comprobante.getObservaciónMovimientoCaja());
		logger.debug("Sucursal: " + comprobante.getSucursal());
		stmt.setString(21, String.valueOf(comprobante.getSucursal()));
		logger.debug("EstadoAutorizacion: " + comprobante.getEstadoAutorizacion());
		stmt.setString(22, comprobante.getEstadoAutorizacion());
		logger.debug("TipoPago: " + comprobante.getTipoPago());
		stmt.setString(23, comprobante.getTipoPago());
		logger.debug("FechaDisponibilidad: " + comprobante.getFechaDisponibilidad());
		if (comprobante.getFechaDisponibilidad() != null)
			stmt.setTime(24,new java.sql.Time(comprobante.getFechaDisponibilidad().getTime()));
		else
			stmt.setNull(24,Types.TIME);
		logger.debug("EmiteFactura: " + comprobante.getEmiteFactura());
		stmt.setString(25, comprobante.getEmiteFactura());
		logger.debug("CodigoOficina: " + comprobante.getCodigoOficina());
		stmt.setString(26, String.valueOf(comprobante.getCodigoOficina()));
		logger.debug("CorrelativoPago: " + comprobante.getCorrelativoPago());
		stmt.setString(27, String.valueOf(comprobante.getCorrelativoPago()));
		logger.debug("CodigoAreaNegocio: " + comprobante.getCodigoAreaNegocio());
		stmt.setString(28, String.valueOf(comprobante.getCodigoAreaNegocio()));
		logger.debug("CodigoCajero: " + comprobante.getCodigoCajero());
		stmt.setString(29, String.valueOf(comprobante.getCodigoCajero()));
		logger.debug("FechaApertura: " + comprobante.getFechaApertura());
		if (comprobante.getFechaApertura() != null)
			stmt.setDate(30,new java.sql.Date(comprobante.getFechaApertura().getTime()));
		else
			stmt.setNull(30,Types.DATE);
		logger.debug("Sesion: " + comprobante.getSesion());
		stmt.setString(31, String.valueOf(comprobante.getSesion()));
		logger.debug("SerPagadoPorCodigoOficina: " + comprobante.getSerPagadoPorCodigoOficina());
		stmt.setString(32, String.valueOf(comprobante.getSerPagadoPorCodigoOficina()));
		logger.debug("FechaCreacion: " + comprobante.getFechaCreacion());
		if (comprobante.getFechaCreacion() != null)
			stmt.setDate(33,new java.sql.Date(comprobante.getFechaCreacion().getTime()));
		else
			stmt.setNull(33,Types.DATE);
		logger.debug("HoraCreacion: " + comprobante.getHoraCreacion());
		if (comprobante.getHoraCreacion() != null)
			stmt.setTime(34,new java.sql.Time(comprobante.getHoraCreacion().getTime()));
		else
			stmt.setNull(34,Types.TIME);
		logger.debug("FechaCambio: " + comprobante.getFechaCambio());
		if (comprobante.getFechaCambio() != null)
			stmt.setDate(35,new java.sql.Date(comprobante.getFechaCambio().getTime()));
		else
			stmt.setNull(35,Types.DATE);
		logger.debug("HoraCambio: " + comprobante.getHoraCambio());
		if (comprobante.getHoraCambio() != null)
			stmt.setTime(36,new java.sql.Time(comprobante.getHoraCambio().getTime()));
		else
			stmt.setNull(36,Types.TIME);
		logger.debug("UsuarioCreoRegistro: " + comprobante.getUsuarioCreoRegistro());
		stmt.setString(37, comprobante.getUsuarioCreoRegistro());
		logger.debug("UltimoUsuarioModifico: " + comprobante.getUltimoUsuarioModifico());
		stmt.setString(38, comprobante.getUltimoUsuarioModifico());
			
		logger.debug("Inicia operación: " + command);
		stmt.execute();
		
		logger.debug("Finaliza operación: " + command);
		
	  } catch (SQLException ex) {
		ex.printStackTrace();
		int code=ex.getErrorCode();
		throw new BusinessException(PREFIX+code);
	  } finally {
		DB2Utils.closeAll(conn, stmt, ors);
	  }
	}
	
	/**
	 * Crea un nuevo Detalle de Reembolso en Tesoreria
	 * @param Detalle: el Objeto Detalle
	 */
	public void insertDetalle(Detalle detalle, int tesoreria) throws Exception, BusinessException {
	  
		String database =null;
		String JNDIDataSource=null;
	  
		switch (tesoreria) {
		  case TESORERIA_BIENESTAR:
			  database = tesoreriaBienestarDatabase;
			  JNDIDataSource = tesoreriaBienestarJNDIDataSource;
			  break;
		  case TESORERIA_ARAUCANA:
			  database = tesoreriaAraucanaDatabase;
			  JNDIDataSource = tesoreriaAraucanaJNDIDataSource;
			  break;
		  default:
			  throw new BusinessException("CCAF_TESO_SISTEMAINVALIDO",
						 "Debe indicar un Sistema de Tesoreria Válido");
		}
	  
	  logger.debug("Tesorería, Folio Detalle: " + detalle.getFolioMovimiento());
	  if (detalle == null) {
		throw new BusinessException("CCAF_TESO_DETALLEINVALIDO",
								   "Se ha intentado crear un Detale Nulo");
	  }

	  Connection conn = null;
	  CallableStatement stmt = null;
	  ResultSet ors = null;
	  
	  String command = "INSERT INTO "+database+".TE07F2 (TE4QA, TE4RA, TE4SA, "+
		"TE4TA, TE2ZA, TE4UA, TE2XA, TE2YA, TE4YA, TE1YA, TE3WA, OBF002, OBF003, OBF005, "+
		"OBF006, SAJKM94, SAJKM92) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
	  try {
		conn = DB2Utils.createConnection(JNDIDataSource);
		
		stmt = conn.prepareCall(command);
		stmt.setInt(1, detalle.getItem());
		stmt.setString(2, detalle.getIdentificadorSubsistema());
		stmt.setInt(3, detalle.getMontoDetalle());
		stmt.setString(4, detalle.getObservaciónMovimientoDetalle());
		stmt.setInt(5, detalle.getMontoPagoEfectivo());
		stmt.setString(6, detalle.getDocumentoRespaldo());
		stmt.setInt(7, detalle.getCantidadDocumentos());
		stmt.setInt(8, detalle.getMontoPagoCheque());
		stmt.setInt(9, detalle.getNumeroCaratula());
		stmt.setInt(10, detalle.getCodigoConcepto());
		stmt.setLong(11, detalle.getFolioMovimiento());
		if (detalle.getCreationDate() != null)
			stmt.setDate(12,new java.sql.Date(detalle.getCreationDate().getTime()));
		else
			stmt.setNull(12,Types.DATE);
		if (detalle.getCreationTime() != null)
			stmt.setTime(13,new java.sql.Time(detalle.getCreationTime().getTime()));
		else
			stmt.setNull(13,Types.TIME);	
		if (detalle.getLastChangeDate() != null)
			stmt.setDate(14,new java.sql.Date(detalle.getLastChangeDate().getTime()));
		else
			stmt.setNull(14,Types.DATE);
		if (detalle.getLastChangeTime() != null)
			stmt.setTime(15,new java.sql.Time(detalle.getLastChangeTime().getTime()));
		else
			stmt.setNull(15,Types.TIME);		
		stmt.setString(16, detalle.getCreationUser());
		stmt.setString(17, detalle.getLastChangeUser());
 		
		logger.debug("Inicia operación: " + command);
		stmt.execute();
		logger.debug("Finaliza operación: " + command);
	  } catch (SQLException ex) {
		ex.printStackTrace();
		int code=ex.getErrorCode();
		throw new BusinessException(PREFIX+code);
	  } finally {
		DB2Utils.closeAll(conn, stmt, ors);
	  }
	}
	
	/**
	 * Consulta por el estado de un comprobante dado un número de folio
	 * Consulta por el folio en la Tesoreria indicada en el parametro
	 * @param long folio
	 * @param int tesoreria
	 * @return String:
	 * 				estado si encuentra el folio
	 * 				null si no encuentra el folio
	 * @throws Exception
	 * @throws BusinessException
	 */
	public String getEstadoComprobante(long folio, int tesoreria) throws Exception, BusinessException {

		String database =null;
		String JNDIDataSource=null;
		String estado=null;
	  
		switch (tesoreria) {
		  case TESORERIA_BIENESTAR:
			  database = tesoreriaBienestarDatabase;
			  JNDIDataSource = tesoreriaBienestarJNDIDataSource;
			  break;
		  case TESORERIA_ARAUCANA:
			  database = tesoreriaAraucanaDatabase;
			  JNDIDataSource = tesoreriaAraucanaJNDIDataSource;
			  break;
		  default:
			  throw new BusinessException("CCAF_TESO_SISTEMAINVALIDO",
						 "Debe indicar un Sistema de Tesoreria Válido");
		}
	  
	  logger.debug("Tesorería, Folio: " + folio);

	  Connection conn = null;
	  CallableStatement stmt = null;
	  ResultSet ors = null;
	  
	  String command = "SELECT	TE3YA " +
		"FROM "+database+".TE07F1 " +
			"WHERE	TE3WA = ?"; 
								
	  try {
		conn = DB2Utils.createConnection(JNDIDataSource);
		stmt = conn.prepareCall(command);
		stmt.setLong(1, folio);
  
		logger.debug("Inicia operación: " + command);
		ors = stmt.executeQuery();
		logger.debug("Finaliza operación: " + command);
	
		if (ors.next()) {
		  estado = ors.getString("TE3YA");
		}
	  
	  } catch (SQLException ex) {
		  ex.printStackTrace();
		  int code=ex.getErrorCode();
		  throw new BusinessException(PREFIX+code);
	  } finally {
		DB2Utils.closeAll(conn, stmt, ors);
	  }
	
	  return estado;
	} 	

	/**
	 * Anula un comprobante de ingreso
	 * @param long folio
	 * @param int tesoreria
	 * @param String usuario
	 * @return int que indica la cantidad de filas actualizadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int anulaComprobanteIngreso(long folio, int tesoreria, String usuario) throws Exception, BusinessException {

		String database =null;
		String JNDIDataSource=null;
//		String estado=null;
		int filasActualizadas =0;
	  
		switch (tesoreria) {
		  case TESORERIA_BIENESTAR:
			  database = tesoreriaBienestarDatabase;
			  JNDIDataSource = tesoreriaBienestarJNDIDataSource;
			  break;
		  case TESORERIA_ARAUCANA:
			  database = tesoreriaAraucanaDatabase;
			  JNDIDataSource = tesoreriaAraucanaJNDIDataSource;
			  break;
		  default:
			  throw new BusinessException("CCAF_TESO_SISTEMAINVALIDO",
						 "Debe indicar un Sistema de Tesoreria Válido");
		}
	  
	  logger.debug("Tesorería, Folio: " + folio);

	  Connection conn = null;
	  CallableStatement stmt = null;
	  ResultSet ors = null;
	  
	  String command = "UPDATE "+database+".TE07F1 " +
		"SET	TE3YA = 'A', " +
		"OBF005 = ?, " +	//fecha
		"OBF006 = ?, " +	//Hora
		"SAJKM92 = ? " +	//usuario
		"WHERE	TE3WA = ?"; //folio
								
	  try {
		conn = DB2Utils.createConnection(JNDIDataSource);
		stmt = conn.prepareCall(command);
		stmt.setDate(1,new java.sql.Date(new java.util.Date().getTime()));
		stmt.setTime(2,new java.sql.Time(new java.util.Date().getTime()));
		stmt.setString(3, usuario);
		stmt.setLong(4, folio);
  
		logger.debug("Inicia operación: " + command);
		filasActualizadas = stmt.executeUpdate();
		logger.debug("Finaliza operación: " + command);
		  
	  } catch (SQLException ex) {
		  ex.printStackTrace();
		  int code=ex.getErrorCode();
		  throw new BusinessException(PREFIX+code);
	  } finally {
		DB2Utils.closeAll(conn, stmt, ors);
	  }
	
	  return filasActualizadas;
	} 	

}
