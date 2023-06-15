package cl.araucana.estasfam.app.business.services;

import javax.servlet.http.HttpServletResponse;

public interface DescargaArchivoService {
	
	public void descargarArchivo(HttpServletResponse response, String rutaArchivo);

}
