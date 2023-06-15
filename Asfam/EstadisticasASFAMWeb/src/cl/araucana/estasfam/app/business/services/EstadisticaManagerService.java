package cl.araucana.estasfam.app.business.services;

public interface EstadisticaManagerService {

	public void generarEstadisticas(String [] codEstadisticas) ;
	
	public void bloquarGeneracionEstadisticas(String [] codEstadisticas);
	
	public String validarEstadoGeneracionEstadisticas(String codigoEstadistica);
	
}
