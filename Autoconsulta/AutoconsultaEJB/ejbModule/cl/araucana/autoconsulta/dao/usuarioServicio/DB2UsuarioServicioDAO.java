package cl.araucana.autoconsulta.dao.usuarioServicio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.schema.util.FileSettings;
import com.schema.util.GeneralException;
import com.schema.util.dao.DB2Utils;

import cl.araucana.autoconsulta.dao.credito.DB2CreditoDAO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.AccesoServicioVO;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.laaraucana.utils.ConstantesUsuarioServicio;
import cl.laaraucana.utils.GetValueXML;

public final class DB2UsuarioServicioDAO implements IUsuarioServicioDAO {
	private static Logger logger = Logger.getLogger(DB2UsuarioServicioDAO.class);
	private DB2Utils usuarioUtil;

	private String sqlUsrEmp; // empresas de un usuario
	private String sqlEmpUsr; // usuario de una empresa
	private String sqlUsrSrv; // servicio de un usuario
	private String sqlEsUsrEmp; // es usuario de empresa
	private String sqlEsUsrSrv; // tiene acceso a servicio
	private String sqlEsClaveValida; // es clave valida
	private String sqlClaveServicioValida; // es clave valida para el servicio
	
	
	private String sqlDelUsrSrv; // elimina todos los servicio de un usuario
	private String sqlInsUsrSrv; // elimina todos los servicio de un usuario
	private String sqlUpdUsrSrv; // elimina todos los servicio de un usuario

	private GetValueXML getValueXml = new GetValueXML();
	
	public DB2UsuarioServicioDAO() {
		String datasource =
			FileSettings.getValue(
				AppConfig.getInstance().settingsFileName,
				"/application-settings/jdbc/autoconsulta");
		usuarioUtil = new DB2Utils(datasource, this);
		logger.info("datasource [" + datasource + "]");

		sqlEmpUsr = getValueXml.getValue("/empresa-usuarios/list");

		sqlEsUsrEmp = getValueXml.getValue("/empresa-usuarios/find");

		sqlDelUsrSrv = getValueXml.getValue("/servicio-usuarios/delete");

		sqlInsUsrSrv = getValueXml.getValue("/servicio-usuarios/insert");

		sqlUpdUsrSrv = getValueXml.getValue("/servicio-usuarios/update");

		sqlUsrSrv = getValueXml.getValue("/servicio-usuarios/listAll");

		sqlEsUsrSrv = getValueXml.getValue("/servicio-usuarios/find");
	
		sqlUsrEmp = getValueXml.getValue("/usuarios/empresas");

		sqlEsClaveValida = getValueXml.getValue("/usuarios/clave");
		
		sqlClaveServicioValida = getValueXml.getValue("/usuarios/claveServicio");

	}

	public boolean actualizaServicios(EmpresaACargoVO usuarioEmpresa, Collection servicios) throws Exception, BusinessException {
		eliminaServicios(usuarioEmpresa);
		return insertarServicios(usuarioEmpresa, servicios);
	}
	
	public boolean eliminaServicios(EmpresaACargoVO usuarioEmpresa) throws Exception, BusinessException {
		boolean bResl=true;
		logger.info(sqlDelUsrSrv + " -> [" + usuarioEmpresa.getRut() + "][" + usuarioEmpresa.getRutEncargado() + "]");
		usuarioUtil.prepareCall(sqlDelUsrSrv);		
		usuarioUtil.getStatement().setLong(1, usuarioEmpresa.getRut());
		usuarioUtil.getStatement().setLong(2, usuarioEmpresa.getRutEncargado());
		usuarioUtil.execute();
		return bResl;
	}

	public boolean insertarServicios(EmpresaACargoVO usuarioEmpresa, Collection servicios) throws Exception, BusinessException {
		boolean bResl=true;
		
		Iterator i = servicios.iterator();
		while (i.hasNext()) {
			AccesoServicioVO srv = (AccesoServicioVO)i.next();
			logger.info(sqlInsUsrSrv + " -> [" + usuarioEmpresa.getRut() + "][" + usuarioEmpresa.getRutEncargado() + "][" + srv.getCodigo() + "]");
			usuarioUtil.prepareCall(sqlInsUsrSrv);
			usuarioUtil.getStatement().setLong(1, usuarioEmpresa.getRut());
			usuarioUtil.getStatement().setLong(2, usuarioEmpresa.getRutEncargado());
			usuarioUtil.getStatement().setShort(3, (Short.parseShort(srv.getCodigo())));
			usuarioUtil.getStatement().setString(4, "SI");
			usuarioUtil.execute();
		}	
		return bResl;

	}

	public Collection tieneServicio(EmpresaACargoVO usuarioEmpresa, AccesoServicioVO servicio) throws Exception, BusinessException {
		Object []param = new Object[]{new Long( usuarioEmpresa.getRut() ), new Long(usuarioEmpresa.getRutEncargado()), new Integer ( servicio.getCodigo() )};
		return execSQLQuery(param, sqlEsUsrSrv, AccesoServicioVO.class);
	}

	public Collection esEncargado(long rutEmpresa, long rutUsuario) throws Exception, BusinessException {
		Object []param = new Object[]{new Long( rutEmpresa ), new Long(rutUsuario)};
		return execSQLQuery(param, sqlEsUsrEmp, EmpresaACargoVO.class);
	}

	public Collection autenticar(long rutEmpresa, long rutUsuario, int clave) {
		try {
			usuarioUtil.prepareCall(sqlEsClaveValida);
			usuarioUtil.getStatement().setLong(1, rutEmpresa);
			usuarioUtil.getStatement().setLong(2, rutUsuario);
			usuarioUtil.getStatement().setInt(3, clave);
			return usuarioUtil.executeQuery(EmpresaACargoVO.class);
		} catch (GeneralException e) {
			logger.info("GeneralException " + e.getMessage());
		} catch (SQLException e) {
			logger.info("SQLException " + e.getMessage());
		}
		return null;
	}
	
	public Collection autenticar(long rutEmpresa, long rutUsuario, int clave, int servicio) {
		logger.info("autenticar: [" + rutEmpresa + "][" + rutUsuario + "] - servicio[" + servicio + "]");
		try {
			usuarioUtil.prepareCall(sqlClaveServicioValida);
			usuarioUtil.getStatement().setLong(1, rutEmpresa);
			usuarioUtil.getStatement().setLong(2, rutUsuario);
			usuarioUtil.getStatement().setInt(3, clave);
			usuarioUtil.getStatement().setInt(4, servicio);
			return usuarioUtil.executeQuery(EmpresaACargoVO.class);
		} catch (GeneralException e) {
			logger.info("GeneralException " + e.getMessage());
		} catch (SQLException e) {
			logger.info("SQLException " + e.getMessage());
		}
		return null;
	}

	public Collection consultaEncargados(long rutEmpresa) throws Exception, BusinessException {
		Object []param = new Object[]{new Long( rutEmpresa )};
		return execSQLQuery(param, sqlEmpUsr, EmpresaACargoVO.class);
	}

	public Collection consultaServicios(EmpresaACargoVO usuarioEmpresa) throws Exception, BusinessException {
		Object []param = new Object[]{new Long( usuarioEmpresa.getRut() ), new Long( usuarioEmpresa.getRutEncargado() )};
		return execSQLQuery(param, sqlUsrSrv, AccesoServicioVO.class);
	}


	private Collection execSQLQuery(Object[] params, String sqlConsulta, Class clase) {
		
		try {
			usuarioUtil.prepareCall(sqlConsulta);
			logger.info("execSQL iniciando setting param");
			for (int i = 0; i < params.length; i++)  {
				usuarioUtil.getStatement().setLong(i+1, ((Long)params[i]).longValue());
			}
			logger.info("execSQL iniciando consulta");
			return usuarioUtil.executeQuery(clase);
		} catch (GeneralException e) {
			logger.error("GeneralException = [" + e.getMessage() + "]");
		} catch (SQLException e) {
			logger.error("GeneralException = [" + e.getMessage() + "]");
		}
		return null;

	}

	public static AccesoServicioVO buildAccesoServicioVO(ResultSet rs) throws SQLException  {
		AccesoServicioVO resultado = new AccesoServicioVO();
		try {
			resultado.setCodigo(null != rs.getObject( ConstantesUsuarioServicio.US_XSRVCD ) ? rs.getString( ConstantesUsuarioServicio.US_XSRVCD ) : "");
			resultado.setNombre(null != rs.getObject( ConstantesUsuarioServicio.US_XSRVNM ) ? rs.getString( ConstantesUsuarioServicio.US_XSRVNM ) : "");
			resultado.setDescripcion(null != rs.getObject( ConstantesUsuarioServicio.US_XSRVDS ) ? rs.getString( ConstantesUsuarioServicio.US_XSRVDS ) : "");
			resultado.setHabilitado(null != rs.getObject( ConstantesUsuarioServicio.US_XRSRHB ) ? rs.getString( ConstantesUsuarioServicio.US_XRSRHB ).equals("SI") : false);
		} catch (SQLException e) {
			
		}
		return resultado;
	}
	
	public static EmpresaACargoVO buildEmpresaACargoVO(ResultSet rs) throws SQLException  {
		EmpresaACargoVO resultado = new EmpresaACargoVO();
		try {
			resultado.setRut(null != rs.getObject( ConstantesUsuarioServicio.EERUTEMP ) ? rs.getLong( ConstantesUsuarioServicio.EERUTEMP ) : 0);
			resultado.setDv(null != rs.getObject( ConstantesUsuarioServicio.EEDVEMP ) ? rs.getString( "EEDVEMP" ) : "");
			resultado.setRutEncargado(null != rs.getObject( ConstantesUsuarioServicio.RUTUSR) ? rs.getLong( ConstantesUsuarioServicio.RUTUSR ) : 0);
			resultado.setNombre(null != rs.getObject( ConstantesUsuarioServicio.EENOM ) ? rs.getString( ConstantesUsuarioServicio.EENOM ) : "");
			resultado.setApellidoPaterno(null != rs.getObject( ConstantesUsuarioServicio.EEAPEP ) ? rs.getString( ConstantesUsuarioServicio.EEAPEP ) : "");
			resultado.setApellidoMaterno(null != rs.getObject( ConstantesUsuarioServicio.EEAPEM ) ? rs.getString( ConstantesUsuarioServicio.EEAPEM ) : "");
			resultado.setAllOficinasSucursales(null != rs.getObject( ConstantesUsuarioServicio.EEALLOFI ) ? rs.getString( ConstantesUsuarioServicio.EEALLOFI ) : "");
		} catch (SQLException e) {
			logger.info("buildEmpresaACargoVO [" + e.getMessage() + "]");
		}
		return resultado;
	}

}
