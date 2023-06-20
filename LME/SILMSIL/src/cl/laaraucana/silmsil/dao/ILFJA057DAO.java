package cl.laaraucana.silmsil.dao;

import org.apache.log4j.Logger;

import cl.laaraucana.silmsil.vo.ILFJA057VO;

public class ILFJA057DAO extends DaoParent {

	private static Logger logger = Logger.getLogger(ILFJA057DAO.class);

	/**
	 * Método para consulta de etiqueta de estado por identificador del estado.
	 * @param ideEstado
	 * @return
	 * @throws Exception
	 */
	public static String etiquetaEstados(int ideEstado) throws Exception {

		ILFJA057VO vo = new ILFJA057VO();
		String estado = null;

		try {
			logger.info("Consulta por estado.... idEstado = " + ideEstado);
			vo.setIdesta(ideEstado);
			vo = (ILFJA057VO) getConn().queryForObject("ilfja0571", vo);
			estado = vo.getEtqesta();
			System.out.println("Estado = " + estado);

		} catch (Exception e) {
			logger.error("Error etiquetaEstados() 2 : " + e.getMessage());
			e.printStackTrace();
		}
		return estado;
	}
}
