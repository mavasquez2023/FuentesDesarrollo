package cl.araucana.clientewsfonasa.business.services;


public interface HiloEjecucionService {

	public void run();
	
	public void start();
	
	public void esperarTerminoHilo();

	public boolean corriendo();
}
