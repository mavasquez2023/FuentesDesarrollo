package cl.laaraucana.botonpago.web.manager;

import java.util.List;

import cl.laaraucana.botonpago.web.database.dao.CuponYTesoreriaDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.BpagF002;
import cl.laaraucana.botonpago.web.database.ibatis.domain.TE07F1;
import cl.laaraucana.botonpago.web.database.vo.EntradaTesoCuponVO;
import cl.laaraucana.botonpago.web.utils.Constantes;

public class ManagerTesoreriaYCupon {

	/****************************demonio fin de mes****************************/

	/**
	 * obtiene los datos de tesoreria que se encuentran en estado generado
	 * en tesoreria y al mismo tiempo generados en la tabla de cupones.
	 * @return
	 * @throws Exception
	 */
	public static List<TE07F1> getTesoreriaGeneradoEnCupones() throws Exception {
		CuponYTesoreriaDAO tesoCuponDao = new CuponYTesoreriaDAO();
		EntradaTesoCuponVO entrada = new EntradaTesoCuponVO();
		entrada.setEstadoProceso(Constantes.getInstancia().ESTADO_IMPRESO);
		return tesoCuponDao.getTesoreriaGeneradoEnCupones(entrada);

	}

	/**
	 * cambia el estado de tesoreria segun el estado que se encuentra en tesoreria 
	 * y en la tabla de cupones.
	 * @throws Exception
	 */
	//	public static int cambiaEstadoTesoreriaSegunEstadoEnCupones(String estadoProceso, String estadoProcesoParaActualizar) throws Exception {
	//		CuponYTesoreriaDAO tesoCuponDao = new CuponYTesoreriaDAO();
	//		EntradaTesoCuponVO entrada = new EntradaTesoCuponVO();
	//		entrada.setEstadoProceso(estadoProceso);
	//		entrada.setEstadoProcesoDos(estadoProcesoParaActualizar);
	//		return tesoCuponDao.cambiaEstadoTesoreriaSegunEstadoEnCupones(entrada);
	//	}
	public static int AnulaEstadoTesoreria() throws Exception {
		CuponYTesoreriaDAO tesoCuponDao = new CuponYTesoreriaDAO();
		EntradaTesoCuponVO entrada = new EntradaTesoCuponVO();
		entrada.setGlosaAnula(Constantes.getInstancia().GLOSA_ANULA_DEMONIO);
		entrada.setUsuarioApp(Constantes.getInstancia().NOMBRE_USUARIO_APP);
		entrada.setEstadoProceso(Constantes.getInstancia().ESTADO_IMPRESO);
		entrada.setEstadoProcesoDos(Constantes.getInstancia().ESTADO_ANULADO);
		return tesoCuponDao.anulaTesoreriaDemonio(entrada);
	}

	/**
	 * cambia el estado del cupon segun otro estado.
	 * @throws Exception
	 */
	public static int cambiaEstadoCuponSegunEstado(String estadoProceso, String estadoProcesoParaActualizar, String glosaAnula) throws Exception {
		CuponYTesoreriaDAO tesoCuponDao = new CuponYTesoreriaDAO();
		EntradaTesoCuponVO entrada = new EntradaTesoCuponVO();
		entrada.setEstadoProceso(estadoProceso);
		entrada.setEstadoProcesoDos(estadoProcesoParaActualizar);
		entrada.setGlosaAnula(glosaAnula);
		int n = tesoCuponDao.cambiaEstadoCuponSegunEstado(entrada);
		return n;

	}

	/************************fin demonio fin de mes**************************/

	/************************demonio cursa cupones**************************/

	/**
	 * obtiene los cupones generados por caja y que se encuentren cursados en tesoreria
	 * @return
	 * @throws Exception
	 */
	public static List<BpagF002> getCuponesGeneradosPorCajaVsTesoreria() throws Exception {
		CuponYTesoreriaDAO tesoCuponDao = new CuponYTesoreriaDAO();
		EntradaTesoCuponVO entrada = new EntradaTesoCuponVO();
		entrada.setEstadoProceso(Constantes.getInstancia().ESTADO_IMPRESO);
		entrada.setEstadoProcesoDos(Constantes.getInstancia().ESTADO_CURSADO);
		entrada.setTipoPago(Constantes.getInstancia().TIPO_PAGO_CAJA);
		return tesoCuponDao.getCuponesGeneradosPorCajaVsTesoreria(entrada);
	}

	/**
	 * actualiza los cupones generados por caja y que se encuentren cursados en tesoreria
	 * @return 
	 * @throws Exception
	 */
	public static int cursaCuponesPorCajaDesdeTesoreria() throws Exception {
		CuponYTesoreriaDAO tesoCuponDao = new CuponYTesoreriaDAO();
		EntradaTesoCuponVO entrada = new EntradaTesoCuponVO();
		entrada.setEstadoProceso(Constantes.getInstancia().ESTADO_IMPRESO);
		entrada.setEstadoProcesoDos(Constantes.getInstancia().ESTADO_CURSADO);
		entrada.setTipoPago(Constantes.getInstancia().TIPO_PAGO_CAJA);
		return tesoCuponDao.cursaCuponesPorCajaDesdeTesoreria(entrada);
	}

	/****************************fin demonio cursa cupones**************************************/

}
