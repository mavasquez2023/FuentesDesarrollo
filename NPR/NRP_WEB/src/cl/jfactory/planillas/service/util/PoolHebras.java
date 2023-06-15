package cl.jfactory.planillas.service.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import cl.liv.cargas.masivas.util.UtilLogCargas;
import cl.liv.comun.utiles.logs.UtilLogGeneracion;

public class PoolHebras {

	private ArrayList hebras = new ArrayList();
	
	private ITerminadorHebra notificador = null;
	
	int TIME_TO_LIVE = 1000 * 60 * 5;
	
	static String SUFIJO = "NRP_";
	
	int iteracionHebrasColgadas = 0;
	int ITERACIONES_HEBRAS_COLGADAS = 20;
	
	public static void eliminarHebras(Thread t) {
		
		UtilLogGeneracion.debug("<limpiandoHebras>...");
		
		if(t != null) {
			try {
				
				UtilLogThread.error("Matando hebra :"+ t.getName());
				UtilLogGeneracion.debug("Matando hebra especifica :"+ t.getName());
				t.stop();
				t = null;
			}catch(Exception e) {
				UtilLogThread.error("Error matando hebra "+ e.getMessage());
				UtilLogGeneracion.debug("Error matando hebra "+ e.getMessage());
			} catch(ThreadDeath e) {
				UtilLogThread.error("Error matando hebra "+ e.getMessage());
				UtilLogGeneracion.debug("Error matando hebra "+ e.getMessage());
			}
		}
		else {
			Set threadSet = Thread.getAllStackTraces().keySet();
			Thread[] threadArray = (Thread[])threadSet.toArray(new Thread[threadSet.size()]);
	
			for(int i=0; i< threadArray.length; i++){
				if(threadArray[i].getName().startsWith(SUFIJO)){
					try {
	
						UtilLogThread.error("Matando hebra :"+ threadArray[i].getName());
						UtilLogGeneracion.debug("Matando hebra :"+ threadArray[i].getName());
						threadArray[i].stop();
						threadArray[i] = null;
					}catch(Exception e) {
						UtilLogThread.error("Error matando hebra "+ e.getMessage());
						UtilLogGeneracion.debug("Error matando hebra "+ e.getMessage());
					} catch(ThreadDeath e) {
						UtilLogThread.error("Error matando hebra "+ e.getMessage());
						UtilLogGeneracion.debug("Error matando hebra "+ e.getMessage());
					}
				}
				
			}
		}
		UtilLogGeneracion.debug("</limpiandoHebras>");
		
	}
	
	public PoolHebras(int cantidadHebras, final String clase, final String metodo, final Class[] tipos, final Object[] parametros, ITerminadorHebra _notificador){
		
		iteracionHebrasColgadas = 0;
		notificador = _notificador;
		
		for( int i=0; i< cantidadHebras; i++){
			final  int aux = i;
			
			NRPHebra t = new NRPHebra( new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					try{
						new HebraUtil(SUFIJO+aux,clase, metodo,tipos,parametros);
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			});
			t.setName(SUFIJO+aux);
			t.start();
			hebras.add(t);
			
			
		}

		try {
			Thread.sleep(10000);
			boolean activas = validarHebrasActivas();
			while(activas){
				activas = validarHebrasActivas();
				Thread.sleep(30000);
			}
			if(notificador != null){
				UtilLogGeneracion.debug("notificando cierre ");
				notificador.notificarCierre(0);
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	public boolean validarHebrasActivas(){
		boolean hebrasActivas = false;
		int contadorHebras = 0;
		UtilLogCargas.debug("validando hebras...");
		UtilLogGeneracion.debug("validando hebras...");
		if(hebras != null && hebras.size()>0){
			for(int i=0; i<hebras.size(); i++){
				NRPHebra t = (NRPHebra) hebras.get(i);
				
				if( t.getState() != t.getState().TERMINATED){
					UtilLogCargas.debug("hebra ["+ t.getName() +"]  ["+ t.getState() +"]");
					hebrasActivas = true;
					contadorHebras++;
					
				}
				else {
					UtilLogGeneracion.debug(" ["+t.getName()+"] TERMINATED ");
				}
			}
		}
		UtilLogGeneracion.debug("hebras activas -> " + contadorHebras);
		UtilLogCargas.debug("hebras activas -> " + contadorHebras);
		if(contadorHebras < 3 ) {
			UtilLogGeneracion.debug(" Iteracion hebras colgadas -> "+ iteracionHebrasColgadas +" / "+ ITERACIONES_HEBRAS_COLGADAS);
			iteracionHebrasColgadas++;
		}
		
		if(iteracionHebrasColgadas > ITERACIONES_HEBRAS_COLGADAS) {
			UtilLogGeneracion.debug(" Elimnando Hebras Colgadas ");
			eliminarHebras(null);
		}
		return hebrasActivas;
	}
}
