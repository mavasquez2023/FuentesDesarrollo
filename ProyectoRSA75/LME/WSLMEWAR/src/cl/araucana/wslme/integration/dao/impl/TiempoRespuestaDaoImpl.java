package cl.araucana.wslme.integration.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.wslme.business.to.TiempoRespuesta;
import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.integration.dao.AbstractDao;
import cl.araucana.wslme.integration.dao.TiempoRespuestaDao;

public class TiempoRespuestaDaoImpl extends AbstractDao implements TiempoRespuestaDao {
	private Logger log = Logger.getLogger(TiempoRespuestaDaoImpl.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public TiempoRespuestaDaoImpl() throws WSLMEException {
		openConnection();
	}

	public List getTiemposRespuesta(Integer anoMes) throws WSLMEException{
		
		List listaTiemposRespuesta = new ArrayList();
		String call = "{ call WSLME.GET_TIEMRESP(?) }";
		try {
			CallableStatement cstmt = getConnection().prepareCall(call);
			cstmt.setInt(1, anoMes.intValue());

			ResultSet rsTiemposRespuesta = cstmt.executeQuery();

			while (rsTiemposRespuesta.next()) {
				TiempoRespuesta objTemp = new TiempoRespuesta();
				objTemp.setFecha(sdf.parse(rsTiemposRespuesta.getString(1)));
				objTemp.setSegundos(new Integer(rsTiemposRespuesta.getInt(2)));
				objTemp.setCantSol(new Integer(rsTiemposRespuesta.getInt(3)));
				listaTiemposRespuesta.add(objTemp);
			}
		} catch (SQLException e) {
			log
					.error("Codigo 0121: Ocurrio un problema al llamar al SP WSLME.GETEMP(?, ?, ?, ?)");
			throw new WSLMEException("0121",
					"Error, Ocurrio un problema al llamar al SP WSLME.GETEMP(?, ?, ?, ?).");
		} catch (Exception e) {
			log
					.error("Codigo 0122: No fue posible obtener los empleadores del rut ["
							+ "]");
			throw new WSLMEException("0122",
					"Error, No fue posible obtener los empleadores del rut ["
							+ "].");
		} finally {
			closeConnection();
		}

		log.debug("Se obtubieron " + listaTiemposRespuesta.size()
				+ " tiempos de respuesta para el año mes [" + anoMes +  "]");
		return listaTiemposRespuesta;
	}
}
