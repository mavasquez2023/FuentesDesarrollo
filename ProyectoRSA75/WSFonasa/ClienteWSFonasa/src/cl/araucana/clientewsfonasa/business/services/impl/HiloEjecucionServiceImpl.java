package cl.araucana.clientewsfonasa.business.services.impl;

import java.util.Date;

import cl.araucana.clientewsfonasa.business.services.CallWSFonasaService;
import cl.araucana.clientewsfonasa.business.services.HiloEjecucionService;
import cl.araucana.clientewsfonasa.business.services.LogWSFonasaService;
import cl.araucana.clientewsfonasa.business.to.CallWSFonasaTO;
import cl.araucana.clientewsfonasa.business.to.LogWSFonasaTO;
import cl.araucana.clientewsfonasa.common.enum.TipoLogWSFonasaEnum;
import cl.araucana.clientewsfonasa.common.exception.DaoException;
import cl.araucana.clientewsfonasa.common.exception.ServiceException;

public class HiloEjecucionServiceImpl extends Thread implements HiloEjecucionService {

	private CallWSFonasaTO callTo;
	private boolean running;
	
	public HiloEjecucionServiceImpl(String nameThread, CallWSFonasaTO callTo){
		super(nameThread);
		this.setDaemon(true);
		this.callTo = callTo;
		running = true;
	}
	
	public void run(){
		LogWSFonasaService logServ = new LogWSFonasaServiceImpl();
		CallWSFonasaService callServ = new CallWSFonasaServiceImpl();
		
		try {
			logServ.saveLogWSFona(new LogWSFonasaTO(null, callTo.getIdCall(), new Date(), 
					TipoLogWSFonasaEnum.DETECTO_CONSULTA_AS400.getCodLog(), 
					TipoLogWSFonasaEnum.DETECTO_CONSULTA_AS400.getDescLog()));
			
			callServ.consultarRutFonasa(callTo);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		running = false;
	}
	
	public void esperarTerminoHilo(){
		try {
			this.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean corriendo(){
		return running;
	}
}
