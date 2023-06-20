package cl.laaraucana.silmsil.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.laaraucana.silmsil.util.Configuraciones;
import cl.laaraucana.silmsil.vo.CountVO;
import cl.laaraucana.silmsil.vo.ILFJAAUXVO;
import cl.laaraucana.silmsil.vo.Paginacion_VO;
import cl.laaraucana.silmsil.vo.SIL_GlosaErrores_VO;
import cl.laaraucana.silmsil.vo.SIL_VO;

/**
 * 
 *
 */
public class SIL_DAO extends DaoParent {

	private static Logger logger = Logger.getLogger(SIL_DAO.class);

	/**
	 * insertar_SIL(), metodo que se encarga de insertar un objeto del tipo SIL entabla ILFSIL050.
	 * @param silVO
	 * @throws Exception
	 */
	public static void insertar_SIL(SIL_VO silVO) throws Exception {
		ArrayList lmList = null;
		try {
			getConn().insert("insertar_SIL", silVO);
		} catch (Exception e) {
			logger.error("Error al insertar" + e.getMessage());
			e.printStackTrace();
		}
	}//end 	insertar_SIL

	/**
	 * insertar_SIL_T(), metodo que se encarga de insertar un objeto del tipo SIL temporal entabla ILFSIL050T
	 * @param silVO
	 * @throws Exception
	 */
	public static void insertar_SIL_T(SIL_VO silVO) throws Exception {

		ArrayList lmList = null;
		try {
			getConn().insert("insertar_SIL_T", silVO);

		} catch (Exception e) {
			logger.error("Error al insertar" + e.getMessage());
			e.printStackTrace();
		}
	}//end 	insertar_SIL_T

	/**
	 * actualizar(), metodo que se encarga de actualizar un objeto del tipo SIL en tabla ILFSIL050.
	 * no actualiza campos index (claves): NROFOLIO,RUTTRABAJ,PERPAG,PAGFOL,LICHASFEC 
	 * @param silVO
	 * @throws Exception
	 */
	public static void actualizar(SIL_VO silVO) throws Exception {

		ArrayList lmList = null;
		try {
			int res = getConn().update("up_SIL", silVO);
			logger.info("Se actualizaron: " + res + " registros.");

		} catch (Exception e) {
			logger.error("Error al insertar" + e.getMessage());
			e.printStackTrace();
		}
	}//end 	actualizar

	public static void actualizar_SilPK54(HashMap<String, String> mapDatos) throws Exception {

		ArrayList lmList = null;
		try {
			int res = getConn().update("up_SIL_PK54", mapDatos);
			logger.info("Se actualizaron: " + res + " registros.");

		} catch (Exception e) {
			logger.error("Error al insertar" + e.getMessage());
			e.printStackTrace();
		}
	}//end 	actualizar

	/**
	 * eliminar(), metodo que se encarga de eliminar un objeto del tipo SIL en tabla ILFSIL050.
	 * eliminara mediante clave: NROFOLIO,RUTTRABAJ,PERPAG,PAGFOL,LICHASFEC 
	 * @param silVO
	 * @throws Exception
	 */
	public static void eliminar(SIL_VO silVO) throws Exception {

		ArrayList lmList = null;
		try {
			int ac = getConn().delete("del_SIL", silVO);
			if (ac >= 1) {
				logger.info("id eliminado: " + silVO.getCorrelativ());
				SIL_DAO.upCorrelativo(String.valueOf(silVO.getCorrelativ()), String.valueOf(silVO.getPerpag()));
			}

		} catch (Exception e) {
			logger.error("Error al insertar" + e.getMessage());
			e.printStackTrace();
		}
	}//end eliminar

	/**
	 * eliminar(), metodo que se encarga de eliminar un objeto del tipo SIL en tabla ILFSIL050.
	 * eliminara mediante clave: NROFOLIO,RUTTRABAJ,PERPAG,PAGFOL,LICHASFEC 
	 * @param silVO
	 * @throws Exception
	 */
	public static void eliminarDato(HashMap<String, String> map) throws Exception {

		ArrayList lmList = null;
		try {
			SIL_VO sil = new SIL_VO();
			sil.setNrofol(map.get("nro_fol").trim());
			sil.setRuttrabaj(map.get("rut_trabaj").trim());
			sil.setPerpag(Integer.parseInt(map.get("perpag").trim()));
			sil.setLichasfec(Integer.parseInt(map.get("lichasfec").trim()));
			sil.setPagfol(Integer.parseInt(map.get("pag_fol").trim()));
			String idEliminando = map.get("correlativo").trim();

			logger.info("idEliminando---> " + idEliminando);

			int ac = getConn().delete("del_SIL", sil);
			if (ac >= 1) {
				//SIL_DAO.upCorrelativo(String.valueOf(silVO.getCorrelativ()), String.valueOf(silVO.getPerpag()));
				SIL_DAO.upCorrelativo(idEliminando, map.get("perpag"));
			}

		} catch (Exception e) {
			logger.error("Error al insertar" + e.getMessage());
			e.printStackTrace();
		}
	}//end eliminar

	/**
	 * eliminar_T(), metodo que se encarga de eliminar los registros de la tabla SIL temporal y log errores temporal
	 * se elimina todo registro.
	 * @param silVO
	 * @throws Exception
	 */
	public static void eliminar_T(SIL_VO silVO) throws Exception {

		ArrayList lmList = null;
		try {
			getConn().delete("del_SIL_T", silVO);
			getConn().delete("del_SIL_GLOSA_T", silVO);

		} catch (Exception e) {
			logger.error("Error al insertar" + e.getMessage());
			e.printStackTrace();
		}
	}//end eliminar temporal

	/**
	 * buscar(), metodo que se encarga de buscar dinamicamente un registro del tipo SIL en tabla ILFSIL050.
	 * buscara mediante almenos por uno de los siguientes campos: NROFOLIO,RUTTRABAJ,PAGFOL,LICHASFEC
	 * PERPAG es obligatorio
	 * @param silVO
	 * @throws Exception
	 */
	public static ArrayList buscar(SIL_VO silVO) throws Exception {

		ArrayList silList = null;
		try {
			logger.info("SILDAO :" + "\n Perpag = " + silVO.getPerpag() + "" + "\n NroFol = " + silVO.getNrofol() + " \n RutTrabaj = " + silVO.getRuttrabaj() + "\n Lichas = " + silVO.getLichasfec()
					+ "\n PagFol = " + silVO.getPagfol());

			HashMap b = new HashMap();
			b.put("perpag", String.valueOf(silVO.getPerpag()));

			if (!silVO.getNrofol().equalsIgnoreCase("") && silVO.getNrofol() != null) {
				b.put("nrofol", silVO.getNrofol().trim().toUpperCase());
			}
			if (!silVO.getRuttrabaj().equalsIgnoreCase("") && silVO.getRuttrabaj() != null) {
				b.put("ruttrabaj", silVO.getRuttrabaj().toUpperCase());
			}
			if (silVO.getLichasfec() != 0) {
				b.put("lichasfec", silVO.getLichasfec());
			}
			if (silVO.getPagfol() != 0) {
				b.put("pagfol", silVO.getPagfol());
			}
			b.put("paginacion", Configuraciones.getAtributoPaginacion("cantidadPaginacion"));

			silList = (ArrayList) getConn().queryForList("getBuscar_SIL", b);

		} catch (Exception e) {
			logger.error("Error al buscar" + e.getMessage());
			e.printStackTrace();
		}
		return silList;
	}//end buscar

	/**
	 * obtenerErroresSIL(), metodo que se encarga de obtener los errores registrados para unn registro
	 * sil temporal en la tabla de log errores temporales.
	 * los campos PERPAG, NROFOL, RUTTRABAJ son obligatorios para realizar inner join.
	 * @param silVO
	 * @throws Exception
	 */
	public static ArrayList obtenerErroresSIL(SIL_VO silVO, String opcion) throws Exception {

		ArrayList silList = null;
		try {

			HashMap map = new HashMap();
			map.put("perpag", String.valueOf(silVO.getPerpag()));
			map.put("nrofol", silVO.getNrofol());
			map.put("ruttrabaj", silVO.getRuttrabaj());

			logger.info("perpag " + silVO.getPerpag() + "\n nrofol " + silVO.getNrofol() + "\n ruttrabaj " + silVO.getRuttrabaj());

			if (opcion.equalsIgnoreCase("mostrarErrores")) {
				silList = (ArrayList) getConn().queryForList("getErrorSIL", map);
			} else if (opcion.equalsIgnoreCase("validarErrores")) {
				silList = (ArrayList) getConn().queryForList("getErrorSIL_T", map);
			}

		} catch (Exception e) {
			logger.error("Error al buscar" + e.getMessage());
			e.printStackTrace();
		}
		return silList;
	}//end buscar_T

	//paginacion
	/**
	 * pagina_Inicio(), metodo que se encarga de obtener la primera cantidad de registros de la tabla ILFSIL050
	 * mediante clave: NROFOLIO,RUTTRABAJ,PAGFOL,LICHASFEC,PERPAG
	 * hashMap: registrosPorPagina: equivale a la cantidad de registros que se desea, segun properties.
	 * se ordenan por correlativo.
	 * 
	 * @param silVO
	 * @throws Exception
	 */
	public static ArrayList pagina_Inicio(Paginacion_VO pag) throws Exception {

		ArrayList lmList = null;

		try {
			HashMap mapPag = new HashMap<String, String>();
			mapPag.put("registrosPorPagina", pag.getRegistrosPorPagina());
			mapPag.put("perpag", pag.getFechaProceso());
			lmList = (ArrayList) getConn().queryForList("getPaginaSIL_FW", mapPag);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}//end 	Pagina_Avanzar

	/**
	 * pagina_Avanzar(), metodo que se encarga de obtener una cantidad de registros de la tabla ILFSIL050
	 * mediante clave: NROFOLIO,RUTTRABAJ,PAGFOL,LICHASFEC,PERPAG
	 * hashMap: "registrosPorPagina" equivale a la cantidad de registros que se desea, segun properties.
	 * hashMap: "ultimoLista" equivale a al ultimo correlativo mostrado en lista obtenida anterior. 
	 * se ordenan por correlativo.
	 * 
	 * @param silVO
	 * @throws Exception
	 */
	public static ArrayList pagina_Avanzar(Paginacion_VO pag) throws Exception {

		ArrayList lmList = null;
		try {
			HashMap mapPag = new HashMap<String, String>();
			mapPag.put("ultimoLista", pag.getUltimoLista());
			mapPag.put("registrosPorPagina", pag.getRegistrosPorPagina());
			mapPag.put("perpag", pag.getFechaProceso());

			lmList = (ArrayList) getConn().queryForList("getPaginaSIL_FW", mapPag);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}//end 	Pagina_Avanzar

	/**
	 * pagina_Retroceder(), metodo que se encarga de obtener una cantidad de registros de la tabla ILFSIL050
	 * mediante clave: NROFOLIO,RUTTRABAJ,PAGFOL,LICHASFEC,PERPAG
	 * hashMap: "registrosPorPagina" equivale a la cantidad de registros que se desea, segun properties.
	 * hashMap: "primeroLista" equivale al primer correlativo mostrado en lista obtenida anterior. 
	 * se ordenan por correlativo.
	 * 
	 * @param silVO
	 * @throws Exception
	 */
	public static ArrayList pagina_Retroceder(Paginacion_VO pag) throws Exception {

		ArrayList lmList = null;
		try {
			HashMap mapPag = new HashMap<String, String>();
			mapPag.put("primeroLista", pag.getPrimeroLista());
			mapPag.put("registrosPorPagina", pag.getRegistrosPorPagina());
			mapPag.put("perpag", pag.getFechaProceso());
			lmList = (ArrayList) getConn().queryForList("getPaginaSIL_BK", mapPag);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return lmList;
	}//end 	Pagina_Retroceder

	public static ArrayList pagina_Ultima(Paginacion_VO pag) throws Exception {

		ArrayList silList = null;
		try {
			HashMap mapPag = new HashMap<String, String>();
			mapPag.put("registrosPorPagina", pag.getRegistrosPorPagina());
			mapPag.put("perpag", pag.getFechaProceso());
			silList = (ArrayList) getConn().queryForList("getPaginaSIL_Ultima", mapPag);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return silList;
	}//end 	pagina_Ultima

	/**
	 * count_SIL(), metodo que se encarga de contar la cantidad total de registros de ILFSIL050
	 * mediante clave: PERPAG
	 * 
	 * @param silVO
	 * @throws Exception
	 */
	public static int count_SIL(String perpag) {
		CountVO cantReg = null;

		ArrayList lmList = null;
		try {
			cantReg = (CountVO) getConn().queryForObject("getCount_SIL", perpag);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return cantReg.getResultadoCount();
	}

	/**
	 * upCorrelativo(), metodo que se encarga de actualizar el campo correlativ de ilfSIL050
	 * mediante clave: PERPAG y CORRELATIV seteado desde el ultimo eliminado.
	 * para mantener orden de correlativos secuenciales.
	 * 
	 * @param silVO
	 * @throws Exception
	 */
	public static void upCorrelativo(String idEliminado, String perpag) throws Exception {

		ArrayList lmList = null;
		try {
			HashMap<String, String> hashCorrelativ = new HashMap<String, String>();
			hashCorrelativ.put("idEliminado", idEliminado);
			hashCorrelativ.put("perpag", perpag);
			getConn().update("upCorrelativ_SIL", hashCorrelativ);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
	}//end 	obtenerLista

	/* ************************************************************************************************************************* */
	/* **************************************Sesión para log de errores de proceso SIL *******************************************/
	/* ************************************************************************************************************************* */

	/**
	 * Método que entrega conteo de datos totales a desplegarse en la paginación.
	 * @param perpag
	 * @return
	 */
	public static int count_LogSIL(String perpag) {
		CountVO cantReg = null;

		ArrayList lmList = null;
		try {
			HashMap map = new HashMap();
			map.put("fecha", perpag);
			cantReg = (CountVO) getConn().queryForObject("getCount_LogSIL", map);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return cantReg.getResultadoCount();
	}

	/**
	 * Método que entrega conteo de datos totales a desplegarse en la paginación.
	 * @param perpag
	 * @return
	 */
	public static int count_LogSILAgrupado(String perpag) {
		CountVO cantReg = null;

		ArrayList lmList = null;
		try {
			HashMap map = new HashMap();
			map.put("fecha", perpag);
			cantReg = (CountVO) getConn().queryForObject("getConteoAgrupadoSIL", map);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return cantReg.getResultadoCount();
	}

	/**
	 * Método para traer de la base de datos el listado de paginación para log errores SIL.
	 * @param fecha
	 * @param registrosPorPagina
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<SIL_GlosaErrores_VO> logErrorSil(String fecha, String registrosPorPagina) throws Exception {

		ArrayList<SIL_GlosaErrores_VO> silList = null;

		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("fecha", fecha);
			map.put("registrosPorPagina", registrosPorPagina);

			silList = (ArrayList<SIL_GlosaErrores_VO>) getConn().queryForList("getListaErrorSIL", map);

		} catch (Exception ex) {
			logger.error("Error logErrorSil(2): " + ex.getMessage());
			ex.printStackTrace();
		}
		return silList;
	}//end 	logErrorSil

	/**
	 * Método para mostrar siguiente set de datos en paginación.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public static ArrayList pagina_AvanzarLogSil(Paginacion_VO pag) throws Exception {

		ArrayList silList = null;
		try {
			logger.info("ultimoLista = " + pag.getUltimoLista() + "\n registrosPorPagina = " + pag.getRegistrosPorPagina() + "\n fecha = " + pag.getFechaProceso());

			HashMap mapPag = new HashMap<String, String>();
			mapPag.put("ultimoLista", pag.getUltimoLista());
			mapPag.put("registrosPorPagina", pag.getRegistrosPorPagina());
			mapPag.put("fecha", pag.getFechaProceso());

			silList = (ArrayList) getConn().queryForList("getPaginaLogSIL_FW", mapPag);
			logger.info("Tamaño listado avanzar = " + silList.size());

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return silList;
	}//end 	Pagina_Avanzar

	/**
	 * Método para mostrar el anterior set de datos en paginación.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public static ArrayList pagina_RetrocederLogSil(Paginacion_VO pag) throws Exception {

		ArrayList silList = null;
		try {
			logger.info("primeroLista : " + pag.getPrimeroLista());

			HashMap mapPag = new HashMap<String, String>();
			mapPag.put("primeroLista", pag.getPrimeroLista());
			mapPag.put("registrosPorPagina", pag.getRegistrosPorPagina());
			mapPag.put("fecha", pag.getFechaProceso());
			silList = (ArrayList) getConn().queryForList("getPaginaLogSIL_BK", mapPag);

		} catch (Exception e) {
			logger.error("Error obtenerLista: " + e.getMessage());
			e.printStackTrace();
		}
		return silList;
	}//end 	Pagina_Retroceder

	public static ArrayList<SIL_GlosaErrores_VO> paginaUltima_logErrorSil(String fecha, String registrosPorPagina) throws Exception {

		ArrayList<SIL_GlosaErrores_VO> silList = null;
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("fecha", fecha);
			map.put("registrosPorPagina", registrosPorPagina);

			silList = (ArrayList<SIL_GlosaErrores_VO>) getConn().queryForList("getUltimaPagErrorSIL", map);

		} catch (Exception ex) {
			logger.error("Error logErrorSil(2): " + ex.getMessage());
			ex.printStackTrace();
		}
		return silList;
	}//end 	logErrorSil

	/*AGRUPADOS SIL*/
	/**
	 * Método para traer de la base de datos el listado de paginación para log errores SIL 
	 * al seleccionar el campo agrupar.
	 * @param fecha
	 * @param registrosPorPagina
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<SIL_GlosaErrores_VO> logErrorSilAgrupado(String fecha, String usuario, String registrosPorPagina) throws Exception {

		ArrayList<SIL_GlosaErrores_VO> silList = null;
		SIL_GlosaErrores_VO vo = new SIL_GlosaErrores_VO();
		try {
			HashMap map = new HashMap();
			map.put("fecha", fecha);
			map.put("usuario", usuario);
			map.put("registrosPorPagina", registrosPorPagina);

			ILFJAAUXVO aux = new ILFJAAUXVO();
			aux.setPerpag(Integer.parseInt(fecha));
			aux.setUsuario(usuario);

			logger.info("Insertando datos en archivo LIEXP.ILFJAAUX");
			//Inserta datos a procesar (LIEXP.ILFJAAUX).
			getConn().insert("insertAuxTable", map);

			logger.info("Consulta SQL en archivo LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX.");
			//Obtiene datos a desplegar (LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX).
			silList = (ArrayList<SIL_GlosaErrores_VO>) getConn().queryForList("getDatosAgrupadosLogSIL", map);

			logger.info("Borrando datos recién creados de archivo auxiliar LIEXP.ILFJAAUX.");
			//Borrando datos archivo LIEXP.ILFJAAUX. 
			int d = getConn().delete("delILFJAAUX", aux);
			logger.info("datos eliminados de ILFJAAUX: " + d);

		} catch (Exception ex) {
			logger.error("Error logErrorSil(2): " + ex.getMessage());
			ex.printStackTrace();
		}
		return silList;
	}//end 	Pagina_Avanzar

	public static ArrayList<SIL_GlosaErrores_VO> logErrorSilUltimaAgrupado(String fecha, String usuario, String registrosPorPagina) throws Exception {

		ArrayList<SIL_GlosaErrores_VO> silList = null;
		SIL_GlosaErrores_VO vo = new SIL_GlosaErrores_VO();
		try {
			HashMap map = new HashMap();
			map.put("fecha", fecha);
			map.put("usuario", usuario);
			map.put("registrosPorPagina", registrosPorPagina);

			ILFJAAUXVO aux = new ILFJAAUXVO();
			aux.setPerpag(Integer.parseInt(fecha));
			aux.setUsuario(usuario);

			logger.info("Insertando datos en archivo LIEXP.ILFJAAUX");
			//Inserta datos a procesar (LIEXP.ILFJAAUX).
			getConn().insert("insertAuxTableUltima", map);

			logger.info("Consulta SQL en archivo LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX.");
			//Obtiene datos a desplegar (LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX).
			silList = (ArrayList<SIL_GlosaErrores_VO>) getConn().queryForList("getDatosAgrupadosLogUltimaSIL", map);

			logger.info("Borrando datos recién creados de archivo auxiliar LIEXP.ILFJAAUX.");
			//Borrando datos archivo LIEXP.ILFJAAUX. 
			int d = getConn().delete("delILFJAAUX", aux);
			logger.info("datos eliminados de ILFJAAUX: " + d);

		} catch (Exception ex) {
			logger.error("Error logErrorSil(2): " + ex.getMessage());
			ex.printStackTrace();
		}
		return silList;
	}//end 	Pagina_Avanzar

	/**
	 * Método para mostrar siguiente set de datos en paginación para agrupados.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<SIL_GlosaErrores_VO> pagina_AvanzarLogSilAgrupados(Paginacion_VO pag, String usuario) throws Exception {

		ArrayList<SIL_GlosaErrores_VO> silList = null;
		try {
			//Seteo de datos.
			HashMap map = new HashMap();
			map.put("ultimoNroFolio", pag.getUltimoLista());
			map.put("usuario", usuario);
			map.put("registrosPorPagina", pag.getRegistrosPorPagina());
			map.put("fecha", pag.getFechaProceso());

			ILFJAAUXVO aux = new ILFJAAUXVO();
			aux.setPerpag(Integer.parseInt(pag.getFechaProceso()));
			aux.setUsuario(usuario);

			logger.info("ultimoNroFolio = " + pag.getUltimoLista() + "\n registrosPorPagina = " + pag.getRegistrosPorPagina() + "\n Fecha = " + aux.getPerpag() + "\n Usuario = " + aux.getUsuario());

			logger.info("Insertando datos en archivo LIEXP.ILFJAAUX");
			//Inserta datos a procesar en archivo auxiliar (LIEXP.ILFJAAUX).
			getConn().insert("insertAuxTable_FW", map);

			logger.info("Consulta SQL en archivo LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX.");

			//Obtiene datos a desplegar (LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX).
			silList = (ArrayList<SIL_GlosaErrores_VO>) getConn().queryForList("getDatosAgrupadosLogSIL", map);
			logger.info("Tamaño listado avanzar = " + silList.size());

			logger.info("Borrando datos recién creados de archivo auxiliar LIEXP.ILFJAAUX.");
			//Borrando datos archivo LIEXP.ILFJAAUX. 
			int d = getConn().delete("delILFJAAUX", aux);
			logger.info("datos eliminados de ILFJAAUX: " + d);

		} catch (Exception e) {
			logger.error("Error pagina_AvanzarLogSilAgrupados: " + e.getMessage());
			e.printStackTrace();
		}
		return silList;
	}//end 	Pagina_Avanzar

	/**
	 * Método para mostrar el anterior set de datos en paginación para agrupados.
	 * @param pag
	 * @return
	 * @throws Exception
	 */
	public static ArrayList pagina_RetrocederLogSilAgrupados(Paginacion_VO pag, String usuario) throws Exception {

		ArrayList silList = null;

		try {
			//Seteo de datos.
			HashMap map = new HashMap();
			map.put("primerNroFolio", pag.getPrimeroLista());
			map.put("usuario", usuario);
			map.put("registrosPorPagina", pag.getRegistrosPorPagina());
			map.put("fecha", pag.getFechaProceso());

			ILFJAAUXVO aux = new ILFJAAUXVO();
			aux.setPerpag(Integer.parseInt(pag.getFechaProceso()));
			aux.setUsuario(usuario);

			logger.info("primerNroFolio = " + pag.getPrimeroLista() + "\n Fecha = " + aux.getPerpag() + "\n Usuario = " + aux.getUsuario());

			logger.info("Insertando datos en archivo LIEXP.ILFJAAUX");
			//Inserta datos a procesar en archivo auxiliar (LIEXP.ILFJAAUX).
			getConn().insert("insertAuxTable_BK", map);

			logger.info("Consulta SQL en archivo LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX.");

			//Obtiene datos a desplegar (LIEXP.ILF050, LIEXP.ILF054 y LIEXP.ILFJAAUX).
			silList = (ArrayList<SIL_GlosaErrores_VO>) getConn().queryForList("getDatosAgrupadosLogSIL", map);
			logger.info("Tamaño listado avanzar = " + silList.size());

			logger.info("Borrando datos recién creados de archivo auxiliar LIEXP.ILFJAAUX.");
			//Borrando datos archivo LIEXP.ILFJAAUX. 
			int d = getConn().delete("delILFJAAUX", aux);
			logger.info("datos eliminados de ILFJAAUX: " + d);

		} catch (Exception e) {
			logger.error("Error pagina_RetrocederLogSilAgrupados: " + e.getMessage());
			e.printStackTrace();
		}
		return silList;
	}//end 	Pagina_Retroceder

	/**
	 * Método para realizar la búsqueda de datos por n° folio o por rut trabajador.
	 * @param silVO
	 * @return
	 * @throws Exception
	 */
	public static ArrayList buscarLog(SIL_GlosaErrores_VO silVO) throws Exception {

		ArrayList silList = null;
		try {
			silVO.setNrofol((silVO.getNrofol() == null) ? null : silVO.getNrofol().toUpperCase());
			silVO.setRuttrabaj((silVO.getRuttrabaj() == null) ? null : silVO.getRuttrabaj().toUpperCase());
			silVO.setPeriodo(Configuraciones.getAtributoPaginacion("cantidadPaginacion"));

			logger.info("NroFolio : " + silVO.getNrofol() + "\n RutTrabaj : " + silVO.getRuttrabaj() + "" + "\n Fecha : " + silVO.getPerpag());

			//			HashMap b=new HashMap();
			//			b.put("perpag", String.valueOf(silVO.getPerpag()));
			//			
			//			if(!silVO.getNrofol().equalsIgnoreCase("") && silVO.getNrofol()!=null){
			//				b.put("nrofol", silVO.getNrofol());
			//			}
			//			if(!silVO.getRuttrabaj().equalsIgnoreCase("") && silVO.getRuttrabaj()!=null){
			//				b.put("ruttrabaj", silVO.getRuttrabaj());
			//			}
			silList = (ArrayList) getConn().queryForList("getBuscarLog_SIL", silVO);
			logger.info("Tamaño lista Buscador por n° folio : " + silList.size());

		} catch (Exception e) {
			logger.error("Error al buscarLog" + e.getMessage());
			e.printStackTrace();
		}
		return silList;
	}

}//end class
