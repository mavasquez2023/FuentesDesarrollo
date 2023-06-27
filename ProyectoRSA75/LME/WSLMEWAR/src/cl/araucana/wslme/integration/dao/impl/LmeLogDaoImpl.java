package cl.araucana.wslme.integration.dao.impl;

import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;

import cl.araucana.wslme.business.to.Event;
import cl.araucana.wslme.integration.dao.AbstractDao;
import cl.araucana.wslme.integration.dao.LmeLogDao;
import cl.araucana.wslme.integration.jaxrpc.ws.Empleador;

public class LmeLogDaoImpl extends AbstractDao implements LmeLogDao {

	private static final String INSERT_LOGSERVLM = "INSERT INTO WSLME.LOGSERVLME(FECHEVEN, INCODOPE, INPASSOPE, INCODCCAF, INRUTAFIL, INRUTDV, OUTESTADO, OUTGLOEST, FECENDEVEN) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_LOGEMPLME = "INSERT INTO WSLME.LOGEMPLME(FECHEVEN, RUTEMP, RAZSOC, LASTCOT) VALUES(?, ?, ?, ?)";
	
	public void saveEvent(Event event) throws Exception {
		if (event == null)
			throw new Exception("Evento no puede ser nulo.");
		openConnection();
		PreparedStatement pStmt = getConnection().prepareStatement(INSERT_LOGSERVLM);
		pStmt.setTimestamp(1, event.getFechEven());
		pStmt.setString(2, event.getInCodOpe());
		pStmt.setString(3, event.getInPassOpe());
		pStmt.setString(4, event.getInCodCcaf());
		pStmt.setDouble(5, event.getInRutAfil().doubleValue());
		pStmt.setString(6, event.getInRutDv());
		pStmt.setDouble(7, event.getOutEstado().doubleValue());
		pStmt.setString(8, event.getOutGloest());
		pStmt.setTimestamp(9, event.getFechEndEven());
		pStmt.execute();
		closeConnection();
	}

	public void saveEventResult(Event event) throws Exception {
		if (event == null)
			throw new Exception("Evento no puede ser nulo.");
		if(event.getEmpresas() == null || event.getEmpresas().size() < 1)
			return;
		openConnection();
		PreparedStatement pStmt = getConnection().prepareStatement(INSERT_LOGEMPLME);
		pStmt.setTimestamp(1, event.getFechEven());
		List emp = event.getEmpresas();
		for (Iterator iterator = emp.iterator(); iterator.hasNext();) {
			Empleador respuesta = (Empleador) iterator.next();
			pStmt.setString(2, respuesta.getRutEmpleador());
			pStmt.setString(3, respuesta.getNomRazSoc());
			pStmt.setDouble(4, respuesta.getUltimaCotizacion().doubleValue());
			pStmt.execute();
		}
		closeConnection();
	}

}
