package cl.araucana.clientewsfonasa.business.services.impl;

import java.util.Iterator;
import java.util.List;

import cl.araucana.clientewsfonasa.business.services.HiloEjecucionService;
import cl.araucana.clientewsfonasa.business.services.MonitorCallWSFonasaService;
import cl.araucana.clientewsfonasa.business.to.CallWSFonasaTO;
import cl.araucana.clientewsfonasa.common.enum.EstadoCallWSFonasaEnum;
import cl.araucana.clientewsfonasa.common.exception.DaoException;
import cl.araucana.clientewsfonasa.common.util.ConfigUtil;
import cl.araucana.clientewsfonasa.integration.dao.CallWSFonaDao;
import cl.araucana.clientewsfonasa.integration.dao.impl.CallWSFonaDaoImpl;

public class MonitorCallWSFonasaServiceImpl extends Thread implements MonitorCallWSFonasaService {
	
	private static boolean running;
	
	public MonitorCallWSFonasaServiceImpl(String nameThread){
		super(nameThread);
	}
	
	public MonitorCallWSFonasaServiceImpl(){}
	
	
	public void run(){

		running = true;
		int maxHilos = Integer.parseInt(ConfigUtil.getValor("araucana.clientewsfonasa.monitor.cantidadhilos"));
		int tiempoEspera = Integer.parseInt(ConfigUtil.getValor("araucana.clientewsfonasa.monitor.tiempoespera"));
		
		HiloEjecucionService [] hilos = new HiloEjecucionService[maxHilos];
		for(int i = 1; running; i++){
//			System.out.println(">>> Iteracion: " + i);
			 
			try {
				CallWSFonaDao callDao = new CallWSFonaDaoImpl();
				
				CallWSFonasaTO filtrosCall = new CallWSFonasaTO();
				filtrosCall.setEstado(EstadoCallWSFonasaEnum.NO_PROCESADO.getCodEstado());
				filtrosCall.setTipo(new Short((short)1));
			
				List list = callDao.getCallWSFona(filtrosCall, new Integer(maxHilos));
				
				Iterator ite = list.iterator();
				while(ite.hasNext()){
					CallWSFonasaTO objCall = (CallWSFonasaTO) ite.next();
//					Cambia el estado de la llamada a EN_PROCSO y guarda los datos 
//					con los que se realizara la llamada al WS Fonasa
					objCall.setEstado(EstadoCallWSFonasaEnum.EN_PROCESO.getCodEstado());
					objCall.getRequest().setFecCertifica(objCall.getFechaHora());
					objCall.getRequest().setRutInstitucion(ConfigUtil.getValor("araucana.clientewsfonasa.business.rutinstitucion"));
					objCall.getRequest().setCodigoUsuario(ConfigUtil.getValor("araucana.clientewsfonasa.business.codigousuario"));
					objCall.getRequest().setClaveUsuario(ConfigUtil.getValor("araucana.clientewsfonasa.business.claveusuario"));
					callDao.updateCallWSFonaStep1(objCall);
				}
				
//				System.out.println(">>> Cantidad de hilos: " + list.size());
				Iterator iter = list.iterator();
				for(int j = 0; iter.hasNext(); j++){
					CallWSFonasaTO objCall = (CallWSFonasaTO) iter.next();
					System.out.println(">>> Procesando llamada " + objCall.getIdCall());
					hilos[j] = new HiloEjecucionServiceImpl("HILO_EJECUCION_LLAMADO", objCall);
					hilos[j].start();
				}
			
//				for (int j = 0; j < hilos.length; j++) {
//					if(hilos[j] != null){
//						System.out.println(">>> Esperando termino de hilo id call " + ((CallWSFonasaTO)list.get(j)).getIdCall());
//						hilos[j].esperarTerminoHilo();
//						System.out.println(">>> Termino hilo id call " + ((CallWSFonasaTO)list.get(j)).getIdCall());
//					}
//				}

//				System.out.println(">>> Esperando " + (int)(tiempoEspera * 1000) + " segundos ");
				sleep((int)(tiempoEspera * 1000));
			} catch (DaoException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Se detubo el proceso. var running=" + running);
	}
	
	public void stopMonitor(){
		System.out.println("Deteniendo el proceso");
		running = false;
	}
}
