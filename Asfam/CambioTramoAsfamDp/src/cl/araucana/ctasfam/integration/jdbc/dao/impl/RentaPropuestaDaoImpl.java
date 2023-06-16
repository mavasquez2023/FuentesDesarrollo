package cl.araucana.ctasfam.integration.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.araucana.ctasfam.business.to.AceptacionPropuestaTO;
import cl.araucana.ctasfam.integration.jdbc.dao.RentaPropuestasDao;
import cl.araucana.ctasfam.integration.jdbc.dao.mapper.PropuestaToMapper;
import cl.araucana.ctasfam.integration.jdbc.dao.mapper.RowMapper;
import cl.araucana.ctasfam.integration.jdbc.exception.RentaPropuestasException;

public class RentaPropuestaDaoImpl extends GenericDaoImpl implements
		RentaPropuestasDao {

	private static final String SELECT_PROPUESTA = "SELECT RP.* FROM AFDTA.RXRENTAPRO RP WHERE RP.RUT_EMPRESA = ? AND RP.DV_RUT_EMPRESA = ? AND RP.RUT_ENCARGADO = ? AND RP.DV_RUT_ENCARGADO = ?";
	
	private static final String INSERT_PROPUESTA = "INSERT INTO AFDTA.RXRENTAPRO(RUT_EMPRESA, DV_RUT_EMPRESA, RAZON_SOCIAL, RUT_ENCARGADO, DV_RUT_ENCARGADO, MAIL_ENCARGADO, MAIL_ENCARGADO2, MAIL_ENCARGADO3, ESTADO_PROCESO, FORMATO_ARCHIVO, CANTIDAD_ARCHIVOS, FECHA_CREACION, HORA_CREACION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final Log log = LogFactory
			.getLog(RentaPropuestaDaoImpl.class);

	public RentaPropuestaDaoImpl(String dataSourceName) throws Exception {
		super(dataSourceName);
	}

	public boolean insert(AceptacionPropuestaTO propuesta)
			throws RentaPropuestasException {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = super.getConnection();
			ps = cn.prepareStatement(RentaPropuestaDaoImpl.INSERT_PROPUESTA);
			ps.setInt(1, propuesta.getRutEmpresa());
			ps.setString(2, propuesta.getDvRutEmpresa());
			ps.setString(3, propuesta.getRazonSocial());
			ps.setInt(4, propuesta.getRutEncargado());
			ps.setString(5, propuesta.getDvRutEncargado());
			ps.setString(6, propuesta.getMailEncargado());
			ps.setString(7, propuesta.getMailEncargado2());
			ps.setString(8, propuesta.getMailEncargado3());
			ps.setString(9, propuesta.getEstadoProceso());
			ps.setString(10, propuesta.getFormatoArchivo());
			ps.setInt(11, propuesta.getCantiadadArchivos());
			ps.setDate(12, propuesta.getFechaCreacion());
			ps.setTime(13, propuesta.getHoraCreacion());
			int result = ps.executeUpdate();
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			log.error("Error: al insertar una propuesta, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al insertar una propuesta, "
					+ e.getLocalizedMessage(), e);
		} finally {
			try {
				ps.close();
				cn.close();
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);
				throw new RentaPropuestasException(
						"Error: al finalizar conexiones, "
								+ e.getLocalizedMessage());
			}
		}
		return false;
	}

	public List select(int rutEmpresa, String dvEmpresa, int rutEncargado,
			String dvEncargado) throws RentaPropuestasException {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List result = new ArrayList();
		RowMapper rm = new PropuestaToMapper();
		try {
			cn = super.getConnection();
			ps = cn.prepareStatement(RentaPropuestaDaoImpl.SELECT_PROPUESTA);
			ps.setInt(1, rutEmpresa);
			ps.setString(2, dvEmpresa);
			ps.setInt(3, rutEncargado);
			ps.setString(4, dvEncargado);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.add(rm.mapRow(rs, 0));
			}
		} catch (SQLException e) {
			log.error("Error: al seleccionar una ó mas propuesta, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al seleccionar una ó mas propuesta, "
					+ e.getLocalizedMessage(), e);
		} finally {
			try {
				rs.close();
				ps.close();
				cn.close();
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);
				throw new RentaPropuestasException(
						"Error: al finalizar conexiones, "
								+ e.getLocalizedMessage());
			}
		}
		return result;
	}
}