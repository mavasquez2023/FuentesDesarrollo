package cl.laaraucana.silmsil.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.laaraucana.silmsil.vo.ILFJA058VO;

public class ILFJA058DAO extends DaoParent {

	private static Logger logger = Logger.getLogger(ILFJA058DAO.class);

	/**
	 * Método para obtener ide. estado de proceso específico.
	 * @param ideProceso
	 * @param periodo
	 * @return
	 * @throws Exception
	 */
	public static void updateEstadoIniciado(HashMap<String, String> map) throws Exception {

		String idEstado = null;
		try {
			//Si existe un estado para proceso en período seleccionado.
			logger.info("->Se inserto nuevo estado para proceso [" + map.get("proceso") + " - " + map.get("fecha") + "].");
			getConn().insert("insertILFJA058", map);

		} catch (Exception e) {
			logger.error("Error consultaEstados() 2 : " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Consulta archivo ILFJA058 por estado de cada procesos, y los entrega ordenados 
	 * en un listado. Estos datos dependen del período actual en que fue ejecutada la 
	 * consulta SQL.
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<ILFJA058VO> consultaEstados(String periodo) throws Exception {
		logger.info("Método ILFJA058DAO.consultaEstados(String periodo)");

		ArrayList<ILFJA058VO> listaEstados = new ArrayList<ILFJA058VO>();
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("fecha", periodo);

			listaEstados = (ArrayList<ILFJA058VO>) getConn().queryForList("porPeriodoActualDos", map);
			logger.info("-->" + listaEstados.size());

		} catch (Exception e) {
			logger.error("Error consultaEstados() 2 : " + e.getMessage());
			e.printStackTrace();
		}
		return listaEstados;
	}

	/**
	 * Método para obtener ide. estado de proceso específico.
	 * @param ideProceso
	 * @param periodo
	 * @return
	 * @throws Exception
	 */
	public static ILFJA058VO consultaEstados(String ideProceso, String periodo) throws Exception {

		ILFJA058VO vo = new ILFJA058VO();
		ArrayList<ILFJA058VO> listaEstados = new ArrayList<ILFJA058VO>();

		String idEstado = null;
		try {
			vo.setIdproc(Integer.parseInt(ideProceso));
			vo.setPepret(periodo);

			vo = (ILFJA058VO) getConn().queryForObject("porPeriodoProceso", vo);

		} catch (Exception e) {
			logger.error("Error consultaEstados() 2 : " + e.getMessage());
			e.printStackTrace();
		}
		return vo;
	}

	/**
	 * Método para obtener informacion de ultimo estado validado para el periodo recibido
	 * @param ideProceso
	 * @param periodo
	 * @return
	 * @throws Exception
	 */
	public static ILFJA058VO consultaEstadosValidados(String ideProceso, String periodo) throws Exception {

		ILFJA058VO vo = new ILFJA058VO();
		ArrayList<ILFJA058VO> listaEstados = new ArrayList<ILFJA058VO>();
		String idEstado = null;
		try {
			vo.setIdproc(Integer.parseInt(ideProceso));
			vo.setPepret(periodo);
			vo = (ILFJA058VO) getConn().queryForObject("porPeriodoProcesoValidado", vo);

		} catch (Exception e) {
			logger.error("Error consultaEstados() 2 : " + e.getMessage());
			e.printStackTrace();
		}
		return vo;
	}//END porPeriodoProcesoValidado

	public static ArrayList<ILFJA058VO> consultaEstadosValidadosConError(String periodo) throws Exception {

		ILFJA058VO vo = new ILFJA058VO();
		ArrayList<ILFJA058VO> listaEstados = new ArrayList<ILFJA058VO>();
		String idEstado = null;
		try {
			//vo.setIdproc(Integer.parseInt(ideProceso)); 
			//vo.setPepret(periodo);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("fecha", periodo);
			listaEstados = (ArrayList<ILFJA058VO>) getConn().queryForList("porPeriodoProcesoValidadoConError", map);

		} catch (Exception e) {
			logger.error("Error consultaEstados() 2 : " + e.getMessage());
			e.printStackTrace();
		}
		return listaEstados;
	}//END porPeriodoProcesoValidadoConError

	public static ArrayList<ILFJA058VO> consultaEstadosValidadosCeros(String periodo) throws Exception {

		ILFJA058VO vo = new ILFJA058VO();
		ArrayList listCero = new ArrayList();
		ArrayList<ILFJA058VO> listaEstados = new ArrayList<ILFJA058VO>();
		String idEstado = null;
		try {
			vo.setPepret(periodo);
			listCero = (ArrayList) getConn().queryForList("porPeriodoProcesoValidadoCero", vo);

		} catch (Exception e) {
			logger.error("Error consultaEstadosValidadosCeros() 2 : " + e.getMessage());
			e.printStackTrace();
		}
		return listCero;
	}//END porPeriodoProcesoValidadoCero

}
