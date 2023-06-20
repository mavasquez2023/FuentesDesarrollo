package cl.laaraucana.rendicionpagonomina.services;




public interface ProcesaRendicionBESService {

	public void procesoxNomina(String numNomina, String idUsuario, String convenios) throws Exception;
	
	public void procesoxFecha(String fechaDesde, String fechaHasta, String idUsuario, String convenios) throws Exception;
	
	//public void sendNomina() throws Exception;

}
