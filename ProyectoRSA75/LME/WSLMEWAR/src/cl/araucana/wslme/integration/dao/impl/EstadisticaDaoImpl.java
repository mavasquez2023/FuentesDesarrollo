package cl.araucana.wslme.integration.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.wslme.business.to.Estadistica;
import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.integration.dao.AbstractDao;
import cl.araucana.wslme.integration.dao.EstadisticaDao;

public class EstadisticaDaoImpl extends AbstractDao implements EstadisticaDao {
	private Logger log = Logger.getLogger(EstadisticaDaoImpl.class);

	 private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public EstadisticaDaoImpl() throws WSLMEException {
		openConnection();
	}

	public List getEstadisticas(Integer anoMes) throws WSLMEException {

		List listaEstadisticas = new ArrayList();
		String call = "{ call WSLME.GET_ESTADISTICAS(?) }";
		try {
			CallableStatement cstmt = getConnection().prepareCall(call);
			cstmt.setInt(1, anoMes.intValue());

			ResultSet rsEstadisticas = cstmt.executeQuery();

			while (rsEstadisticas.next()) {
				Estadistica objTemp = new Estadistica();
				objTemp.setFecha(sdf.parse(rsEstadisticas.getString(1)));
				objTemp.setSolTot(new Integer(rsEstadisticas.getInt(2)));
				objTemp.setRespTot(new Integer(rsEstadisticas.getInt(2)));
				objTemp.setRespNok(new Integer(rsEstadisticas.getInt(3)));
				objTemp.setRespOkConPago(new Integer(rsEstadisticas.getInt(4)));
				objTemp.setRespOkSinPago(new Integer(rsEstadisticas.getInt(5)));
				objTemp.setHoraPrimeraSol(rsEstadisticas.getString(6));
				objTemp.setHoraUltimaSol(rsEstadisticas.getString(7));
				listaEstadisticas.add(objTemp);
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

		log.info("Se obtubieron " + listaEstadisticas.size()
				+ " estadisticas para el año mes [" + anoMes +  "]");
		return listaEstadisticas;
	}
}
