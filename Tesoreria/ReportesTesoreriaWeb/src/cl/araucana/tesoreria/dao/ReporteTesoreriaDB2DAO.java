package cl.araucana.tesoreria.dao;

import cl.araucana.tesoreria.dao.dvo.ComprobanteIngresoDVO;
import cl.araucana.tesoreria.dao.dvo.ReporteComprobantesDVO;
import cl.araucana.tesoreria.modelo.Usuario;

public interface ReporteTesoreriaDB2DAO {
	public void crearUsuario(Usuario usuarioDVO) throws Exception;
	public void eliminarUsuario(Usuario usuarioDVO) throws Exception;
	public String obtenerRolUsuario(String usuario) throws Exception;
	public ComprobanteIngresoDVO[] obtenerComprobantesIngresoPorOficina(String fechaDesde, String fechaHasta, String oficina) throws Exception;
	public ReporteComprobantesDVO[] obtenerReporteComprobantes(String fechaDesde, String fechaHasta) throws Exception;
	public ReporteComprobantesDVO[] obtenerReporteComprobantesPorOficina(String fechaDesde, String fechaHasta, String oficina) throws Exception;
	
}
