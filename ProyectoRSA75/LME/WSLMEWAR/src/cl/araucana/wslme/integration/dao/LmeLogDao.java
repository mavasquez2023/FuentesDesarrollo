package cl.araucana.wslme.integration.dao;

import cl.araucana.wslme.business.to.Event;

public interface LmeLogDao {

	public void saveEvent(Event event) throws Exception;

	public void saveEventResult(Event event) throws Exception;

}
