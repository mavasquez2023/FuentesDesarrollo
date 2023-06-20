package cl.laaraucana.silmsil.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.laaraucana.silmsil.util.Configuraciones;
import cl.laaraucana.silmsil.vo.CountVO;
import cl.laaraucana.silmsil.vo.ILFJAAUXVO;
import cl.laaraucana.silmsil.vo.LM_GlosaErrores_VO;
import cl.laaraucana.silmsil.vo.LM_VO;
import cl.laaraucana.silmsil.vo.Paginacion_VO;

/**
 * 
 *
 */
public class LM_DAO extends DaoParent {

	private static Logger logger = Logger.getLogger(LM_DAO.class);

	public static void insertar_LM(LM_VO lmVO) throws Exception {
		ArrayList lmList = null;
		try {
			getConn().insert("insertar_LM", lmVO);
		} catch (Exception e) {
			logger.error("Error al insertar" + e.getMessage());
			e.printStackTrace();
		}
	}//end 	insertar_LM

	/**
	 * insertar_LM_T(), metodo que se encarga de insertar un objeto del tipo LM temporal en tabla ILFLM050T
	 * @param lmVO
	 * @throws Exception
	 */
	public static void insertar_LM_T(LM_VO lmVO) throws Exception {

		ArrayList lmList = null;
		try {
			getConn().insert("insertar_LM_T", lmVO);

		} catch (Exception e) {
			logger.error("Error al insertar_T" + e.getMessage());
			e.printStackTrace();
		}
	}//end 	insertar_LM_T

	public static void actualizar(LM_VO lmVO) throws Exception {

		ArrayList lmList = null;
		try {
			int res = getConn().update("up_LM", lmVO);
			logger.info("Se actualizaron: " + res + " registros.");

		} catch (Exception e) {
			logger.error("Error al insertar" + e.getMessage());
			e.printStackTrace();
		}
	}//end 	actualizar

	public static void actualizar_LmPK54(HashMap<String, String> mapDatos) throws Exception {

		ArrayList lmList = null;
		try {
			int res = getConn().update("up_LM_PK54", mapDatos);
			logger.info("Se actualizaron: " + res + " registros.");

		} catch (Exception e) {
			logger.error("Error al insertar" + e.getMessage());
			e.printStackTrace();
		}
	}//end 	actualizar

	public static void eliminar(LM_VO lmVO) throws Exception {

		ArrayList lmList = null;
		try {
			int ac = getConn().delete("del_LM", lmVO);
			if (ac >= 1) {
				System.out.println("id eliminado" + lmVO.getCorrelativ());
				LM_DAO.upCorrelativo(String.valueOf(lmVO.getCorrelativ()), String.valueOf(lmVO.getFecproceso()));
			}

		} catch (Exception e) {
			logger.error("Error al insertar" + e.getMessage());
			e.printStackTrace();
		}
	}//end eliminar

	public static void eliminarDato(HashMap<String, String> map) throws Exception {

		ArrayList lmList = null;
		try {
			LM_VO lm = new LM_VO();
			lm.setFolio(map.get("nro_folio").trim());
			lm.setRuttrabaj(map.get("rut_trabaj").trim());
			lm.setFecproceso(Integer.parseInt(map.get("fecproceso").trim()));
			lm.setFecterrepo(Integer.parseInt(map.get("fecterrepo").trim()));
			String idEliminar = map.get("correlativo").trim();

			int ac = getConn().delete("del_LM", lm);
			if (ac >= 1) {
				logger.info("id eliminado" + idEliminar);
				LM_DAO.upCorrelativo(idEliminar, map.get("fecproceso"));
			}

		} catch (Exception e) {
			logger.error("Error al insertar" + e.getMessage());
			e.printStackTrace();
		}
	}//end eliminar

	/**
	 * eliminar_T(), metodo que se encarga de eliminar los registros de la tabla LM temporal y log errores temporal
	 * se elimina todo registro.
	 * @param lmVO
	 * @throws Exception
	 */
	public static void eliminar_T(LM_VO lmVO) throws Exception {

		ArrayList lmList = null;
		try {
			getConn().delete("del_LM_T", lmVO);
			getConn().delete("del_LM_GLOSA_T", lmVO);

		} catch (Exception e) {
			logger.error("Error al insertar" + e.getMessage());
			e.printStackTrace();
		}
	}//end eliminar temporal

	public static ArrayList buscar(LM_VO lmVO) throws Exception {

		ArrayList lmList = null;
		try {
			logger.info("buscar(LM_VO lmVO)  " + lmVO.getFecproceso());
			//lmList=(ArrayList) getConn().queryForList("getBuscar_LM", lmVO);
			//se envian las cuatro pk, para comprobar existencia al insertar, editar. 
			//no se enviara Fecterrepo para busquedas por filtro.
			HashMap b = new HashMap();
			b.put("fecproceso", String.valueOf(lmVO.getFecproceso()));

			if (!lmVO.getFolio().equalsIgnoreCase("") && lmVO.getFolio() != null) {
				b.put("folio", lmVO.getFolio().trim().toUpperCase());
			}
			if (!lmVO.getRuttrabaj().equalsIgnoreCase("") && lmVO.getRuttrabaj() != null) {
				b.put("ruttrabaj", lmVO.getRuttrabaj().toUpperCase());
			}
			if (lmVO.getFecterrepo() != 0) {
				b.put("fecterrepo", lmVO.getFecterrepo());
			}
			b.put("paginacion", Configuraciones.getAtributoPaginacion("cantidadPaginacion"));
			lmList = (ArrayList) getConn().queryForList("getBuscar_LM", b);

		} catch (Exception e) {
			logger.error("Error al buscar" + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}

	/**
	 * obtenerErroresLM(), metodo que se encarga de obtener los errores registrados para unn registro
	 * LM temporal en la tabla de log errores temporales.
	 * los campos fecproceso, folio, RUTTRABAJ son obligatorios para realizar inner join.
	 * @param lmVO
	 * @throws Exception
	 */
	public static ArrayList obtenerErroresLM(LM_VO lmVO, String opcion) throws Exception {

		ArrayList lmList = null;
		try {

			HashMap b = new HashMap();
			b.put("fecproceso", String.valueOf(lmVO.getFecproceso()));

			if (!lmVO.getFolio().equalsIgnoreCase("") && lmVO.getFolio() != null) {
				b.put("folio", lmVO.getFolio());
			}
			if (!lmVO.getRuttrabaj().equalsIgnoreCase("") && lmVO.getRuttrabaj() != null) {
				b.put("ruttrabaj", lmVO.getRuttrabaj());
			}

			String fecterrepo = "";
			fecterrepo = String.valueOf(lmVO.getFecterrepo());
			b.put("fecterrepo", fecterrepo);

			if (opcion.equalsIgnoreCase("mostrarErrores")) {
				lmList = (ArrayList) getConn().queryForList("getErrorLM", b);
			} else if (opcion.equalsIgnoreCase("validarErrores")) {
				lmList = (ArrayList) getConn().queryForList("getErrorLM_T", b);
			}

		} catch (Exception e) {
			logger.error("Error al buscar" + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}//end buscar_T

	//paginacion
	public static ArrayList pagina_Inicio(Paginacion_VO pag) throws Exception {

		ArrayList lmList = null;
		try {
			HashMap mapPag = new HashMap<String, String>();
			mapPag.put("registrosPorPagina", pag.getRegistrosPorPagina());
			mapPag.put("fecproceso", pag.getFechaProceso());
			lmList = (ArrayList) getConn().queryForList("getPaginaLM_FW", mapPag);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}//end 	Pagina_Avanzar

	public static ArrayList pagina_Avanzar(Paginacion_VO pag) throws Exception {

		ArrayList lmList = null;
		try {
			HashMap mapPag = new HashMap<String, String>();
			mapPag.put("ultimoLista", pag.getUltimoLista());
			mapPag.put("registrosPorPagina", pag.getRegistrosPorPagina());
			mapPag.put("fecproceso", pag.getFechaProceso());

			lmList = (ArrayList) getConn().queryForList("getPaginaLM_FW", mapPag);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}//end 	Pagina_Avanzar

	public static ArrayList pagina_Retroceder(Paginacion_VO pag) throws Exception {

		ArrayList lmList = null;
		try {
			HashMap mapPag = new HashMap<String, String>();
			mapPag.put("primeroLista", pag.getPrimeroLista());
			mapPag.put("registrosPorPagina", pag.getRegistrosPorPagina());
			mapPag.put("fecproceso", pag.getFechaProceso());
			lmList = (ArrayList) getConn().queryForList("getPaginaLM_BK", mapPag);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}//end 	Pagina_Retroceder

	public static ArrayList pagina_Ultima(Paginacion_VO pag) throws Exception {

		ArrayList lmList = null;
		try {
			HashMap mapPag = new HashMap<String, String>();
			mapPag.put("registrosPorPagina", pag.getRegistrosPorPagina());
			mapPag.put("fecproceso", pag.getFechaProceso());
			lmList = (ArrayList) getConn().queryForList("getPaginaLM_Ultima", mapPag);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}//end 	pagina_Ultima

	public static int count_LM(String fechaProceso) {
		CountVO cantReg = null;

		ArrayList lmList = null;
		try {
			cantReg = (CountVO) getConn().queryForObject("getCount_LM", fechaProceso);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return cantReg.getResultadoCount();
	}

	public static void upCorrelativo(String idEliminado, String fechaProceso) throws Exception {

		ArrayList lmList = null;
		try {
			HashMap<String, String> hashCorrelativ = new HashMap<String, String>();
			hashCorrelativ.put("idEliminado", idEliminado);
			hashCorrelativ.put("fechaProceso", fechaProceso);
			getConn().update("upCorrelativ_LM", hashCorrelativ);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
	}//end 	upCorrelativo

	/* ************************************************************************************************************************* */
	/* **************************************Log de errores de proceso LM *******************************************/
	/* ************************************************************************************************************************* */

	/**
	 * Método que entrega conteo de datos totales a desplegarse en la paginación.
	 * @param perpag
	 * @return
	 */
	public static int count_LogLM(String perpag) {
		CountVO cantReg = null;

		ArrayList lmList = null;
		try {
			HashMap map = new HashMap();
			map.put("fecha", perpag);
			cantReg = (CountVO) getConn().queryForObject("getCount_LogLM", map);

		} catch (Exception e) {
			logger.error("Error select count: " + e.getMessage());
			e.printStackTrace();
		}
		return cantReg.getResultadoCount();
	}

	/**
	 * Método que entrega conteo de datos totales a desplegarse en la paginación.
	 * @param perpag
	 * @return
	 */
	public static int count_LogLMAgrupado(String perpag) {
		CountVO cantReg = null;

		ArrayList lmList = null;
		try {
			HashMap map = new HashMap();
			map.put("fecha", perpag);
			cantReg = (CountVO) getConn().queryForObject("getConteoAgrupadoLM", map);

		} catch (Exception e) {
			logger.error("Error select count: " + e.getMessage());
			e.printStackTrace();
		}
		return cantReg.getResultadoCount();
	}

	/**
	 * Método para traer de la base de datos el listado de paginación para log errores LM sin agrupar.
	 * @param fecha
	 * @param registrosPorPagina
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<LM_GlosaErrores_VO> logErrorLM(String fecha, String registrosPorPagina) throws Exception {

		ArrayList<LM_GlosaErrores_VO> lmList = null;
		LM_GlosaErrores_VO vo = new LM_GlosaErrores_VO();
		try {
			HashMap map = new HashMap();
			map.put("fecha", fecha);
			map.put("registrosPorPagina", registrosPorPagina);

			lmList = (ArrayList<LM_GlosaErrores_VO>) getConn().queryForList("getListaErrorLM", map);

		} catch (Exception ex) {
			logger.error("Error query logErrorLM: " + ex.getMessage());
			ex.printStackTrace();
		}
		return lmList;
	}//end 	logErrorLM

	public static ArrayList<LM_GlosaErrores_VO> paginaUltima_logErrorLM(String fecha, String registrosPorPagina) throws Exception {

		ArrayList<LM_GlosaErrores_VO> lmList = null;
		LM_GlosaErrores_VO vo = new LM_GlosaErrores_VO();
		try {
			HashMap map = new HashMap();
			map.put("fecha", fecha);
			map.put("registrosPorPagina", registrosPorPagina);

			lmList = (ArrayList<LM_GlosaErrores_VO>) getConn().queryForList("getUltimaPagErrorLM", map);

		} catch (Exception ex) {
			logger.error("Error query logErrorLM: " + ex.getMessage());
			ex.printStackTrace();
		}
		return lmList;
	}//end 	paginaUltima_logErrorLM

	/**
	 * Método para mostrar siguiente set de datos en paginación sin agrupar.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public static ArrayList pagina_AvanzarLogLM(Paginacion_VO pag) throws Exception {

		ArrayList lmList = null;
		try {
			logger.info("ultimoLista = " + pag.getUltimoLista() + "\n registrosPorPagina = " + pag.getRegistrosPorPagina() + "\n fecha = " + pag.getFechaProceso());

			HashMap mapPag = new HashMap<String, String>();
			mapPag.put("ultimoLista", pag.getUltimoLista());
			mapPag.put("registrosPorPagina", pag.getRegistrosPorPagina());
			mapPag.put("fecha", pag.getFechaProceso());

			lmList = (ArrayList) getConn().queryForList("getPaginaLogLM_FW", mapPag);

			logger.info("Tamaño listado avanzar = " + lmList.size());

		} catch (Exception e) {
			logger.error("Error query pagina_AvanzarLogLM(): " + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}//end 	pagina_AvanzarLogLM

	/**
	 * Método para mostrar el anterior set de datos en paginación logerror sin agrupar.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public static ArrayList pagina_RetrocederLogLM(Paginacion_VO pag) throws Exception {

		ArrayList lmList = null;
		try {
			logger.info("-----------Primero de la lista-----------");
			logger.info("primeroLista : " + pag.getPrimeroLista());
			logger.info("------------------------------------------");

			HashMap mapPag = new HashMap<String, String>();
			mapPag.put("primeroLista", pag.getPrimeroLista());
			mapPag.put("registrosPorPagina", pag.getRegistrosPorPagina());
			mapPag.put("fecha", pag.getFechaProceso());
			lmList = (ArrayList) getConn().queryForList("getPaginaLogLM_BK", mapPag);

		} catch (Exception e) {
			logger.error("Error query pagina_RetrocederLogLM: " + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}//end 	pagina_RetrocederLogLM

	/*AGRUPADOS LM*/
	/**
	 * Método para traer de la base de datos el listado de paginación para log errores LM 
	 * al seleccionar el campo agrupar.
	 * @param fecha
	 * @param registrosPorPagina
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<LM_GlosaErrores_VO> logErrorLMAgrupado(String fecha, String registrosPorPagina, String usuario) throws Exception {

		ArrayList<LM_GlosaErrores_VO> lmList = null;
		try {
			logger.info("Fecha = " + fecha);
			logger.info("Usuario = " + usuario);

			HashMap map = new HashMap();
			map.put("fecha", fecha);
			map.put("usuario", usuario);
			map.put("registrosPorPagina", registrosPorPagina);

			ILFJAAUXVO aux = new ILFJAAUXVO();
			aux.setPerpag(Integer.parseInt(fecha));
			aux.setUsuario(usuario);

			logger.info("Insertando datos en archivo LIEXP.ILFJAAUX para LM.");
			//Inserta datos a procesar (LIEXP.ILFJAAUX).
			getConn().insert("insertAuxTable_LM", map);

			logger.info("Consulta SQL en archivo LIEXP.ILFLM050, LIEXP.ILFLM054 y LIEXP.ILFJAAUX.");
			//Obtiene datos a desplegar (LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX).
			lmList = (ArrayList<LM_GlosaErrores_VO>) getConn().queryForList("getDatosAgrupadosLogLM", map);

			logger.info("Borrando datos recién creados de archivo auxiliar LIEXP.ILFJAAUX para LM.");
			//Borrando datos archivo LIEXP.ILFJAAUX. 
			getConn().delete("delILFJAAUX_LM", aux);

		} catch (Exception ex) {
			logger.error("Error query logErrorLMAgrupado: " + ex.getMessage());
			ex.printStackTrace();
		}
		return lmList;
	}//end 	logErrorLMAgrupado

	public static ArrayList<LM_GlosaErrores_VO> logErrorLMUltimaAgrupado(String fecha, String registrosPorPagina, String usuario) throws Exception {

		ArrayList<LM_GlosaErrores_VO> lmList = null;
		try {
			logger.info("Fecha = " + fecha);
			logger.info("Usuario = " + usuario);

			HashMap map = new HashMap();
			map.put("fecha", fecha);
			map.put("usuario", usuario);
			map.put("registrosPorPagina", registrosPorPagina);

			ILFJAAUXVO aux = new ILFJAAUXVO();
			aux.setPerpag(Integer.parseInt(fecha));
			aux.setUsuario(usuario);

			logger.info("Insertando datos en archivo LIEXP.ILFJAAUX para LM.");
			//Inserta datos a procesar (LIEXP.ILFJAAUX).
			getConn().insert("insertAuxTableUltima_LM", map);

			logger.info("Consulta SQL en archivo LIEXP.ILFLM050, LIEXP.ILFLM054 y LIEXP.ILFJAAUX.");
			//Obtiene datos a desplegar (LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX).
			lmList = (ArrayList<LM_GlosaErrores_VO>) getConn().queryForList("getDatosAgrupadosLogUltimaLM", map);

			logger.info("Borrando datos recién creados de archivo auxiliar LIEXP.ILFJAAUX para LM.");
			//Borrando datos archivo LIEXP.ILFJAAUX. 
			getConn().delete("delILFJAAUX_LM", aux);

		} catch (Exception ex) {
			logger.error("Error query logErrorLMAgrupado: " + ex.getMessage());
			ex.printStackTrace();
		}
		return lmList;
	}//end 	logErrorLMUltimaAgrupado

	/**
	 * Método para mostrar siguiente set de datos en paginación para agrupados.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<LM_GlosaErrores_VO> pagina_AvanzarLogLMAgrupados(Paginacion_VO pag, String usuario) throws Exception {

		ArrayList<LM_GlosaErrores_VO> lmList = null;
		try {
			logger.info("fecha = " + pag.getFechaProceso());
			logger.info("Usuario = " + usuario);
			logger.info("ultimoNroFolio = " + pag.getUltimoLista());
			logger.info("registrosPorPagina = " + pag.getRegistrosPorPagina());

			HashMap map = new HashMap<String, String>();
			map.put("ultimoNroFolio", pag.getUltimoLista());
			map.put("usuario", usuario);
			map.put("registrosPorPagina", pag.getRegistrosPorPagina());
			map.put("fecha", pag.getFechaProceso());

			ILFJAAUXVO aux = new ILFJAAUXVO();
			aux.setPerpag(Integer.parseInt(pag.getFechaProceso()));
			aux.setUsuario(usuario);

			logger.info("Insertando datos en archivo LIEXP.ILFJAAUX");
			//Inserta datos a procesar en archivo auxiliar (LIEXP.ILFJAAUX).
			getConn().insert("insertAuxTable_LM_FW", map);

			logger.info("Consulta SQL en archivo LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX.");
			//Obtiene datos a desplegar (LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX).
			lmList = (ArrayList<LM_GlosaErrores_VO>) getConn().queryForList("getDatosAgrupadosLogLM", map);
			logger.info("Tamaño listado avanzar = " + lmList.size());

			logger.info("Borrando datos recién creados de archivo auxiliar LIEXP.ILFJAAUX.");
			//Borrando datos archivo LIEXP.ILFJAAUX. 
			getConn().delete("delILFJAAUX_LM", aux);

		} catch (Exception e) {
			logger.error("Error query pagina_AvanzarLogLMAgrupados: " + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}//end 	pagina_AvanzarLogLMAgrupados

	/**
	 * Método para mostrar el anterior set de datos en paginación para agrupados.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<LM_GlosaErrores_VO> pagina_RetrocederLogLMAgrupados(Paginacion_VO pag, String usuario) throws Exception {

		ArrayList<LM_GlosaErrores_VO> lmList = null;
		try {
			logger.info("primerNroFolio = " + pag.getPrimeroLista());
			HashMap map = new HashMap<String, String>();
			map.put("primerNroFolio", pag.getPrimeroLista());
			map.put("usuario", usuario);
			map.put("registrosPorPagina", pag.getRegistrosPorPagina());
			map.put("fecha", pag.getFechaProceso());

			ILFJAAUXVO aux = new ILFJAAUXVO();
			aux.setPerpag(Integer.parseInt(pag.getFechaProceso()));
			aux.setUsuario(usuario);

			logger.info("Insertando datos en archivo LIEXP.ILFJAAUX");
			//Inserta datos a procesar en archivo auxiliar (LIEXP.ILFJAAUX).
			getConn().insert("insertAuxTable_LM_BK", map);

			logger.info("Consulta SQL en archivo LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX.");
			//Obtiene datos a desplegar (LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX).
			lmList = (ArrayList<LM_GlosaErrores_VO>) getConn().queryForList("getDatosAgrupadosLogLM", map);
			System.out.println("Tamaño listado avanzar = " + lmList.size());

			logger.info("Borrando datos recién creados de archivo auxiliar LIEXP.ILFJAAUX.");
			//Borrando datos archivo LIEXP.ILFJAAUX. 
			getConn().delete("delILFJAAUX_LM", aux);

		} catch (Exception e) {
			logger.error("Error query pagina_RetrocederLogLMAgrupados: " + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}//end 	Pagina_Retroceder

	/**
	 * Método para realizar la búsqueda de datos por n° folio o por rut trabajador.
	 * @param silVO
	 * @return
	 * @throws Exception
	 */
	public static ArrayList buscarLog_LM(LM_VO lmVO) throws Exception {

		ArrayList lmList = null;
		try {

			HashMap b = new HashMap();
			b.put("fecproceso", String.valueOf(lmVO.getFecproceso()));

			if (!lmVO.getFolio().equalsIgnoreCase("") && lmVO.getFolio() != null) {
				b.put("folio", lmVO.getFolio().toUpperCase());
			}
			if (!lmVO.getRuttrabaj().equalsIgnoreCase("") && lmVO.getRuttrabaj() != null) {
				b.put("ruttrabaj", lmVO.getRuttrabaj().toUpperCase());
			}
			b.put("paginacion", Configuraciones.getAtributoPaginacion("cantidadPaginacion"));
			lmList = (ArrayList) getConn().queryForList("getBuscarLog_LM", b);

		} catch (Exception e) {
			logger.error("Error al buscarLog_LM" + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}//end buscarLog_LM

}//end class
