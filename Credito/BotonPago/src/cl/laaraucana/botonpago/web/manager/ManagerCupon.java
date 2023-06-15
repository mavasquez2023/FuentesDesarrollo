package cl.laaraucana.botonpago.web.manager;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.cobol.call.ConsultaFolioCall;
import cl.laaraucana.botonpago.web.cobol.vo.EntradaSalidaFolioVO;
import cl.laaraucana.botonpago.web.database.dao.CuponDAO;
import cl.laaraucana.botonpago.web.database.dao.TesoreriaDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.BpagF002;
import cl.laaraucana.botonpago.web.database.ibatis.domain.TE07F1;
import cl.laaraucana.botonpago.web.utils.CompletaUtil;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.RutUtil;
import cl.laaraucana.botonpago.web.utils.Util;
import cl.laaraucana.botonpago.web.vo.CreditoVO;

public class ManagerCupon {

	protected static Logger logger = Logger.getLogger(ManagerCupon.class);

	/**
	 * Crea el cupón en tesorería y en la tabla propia
	 * 
	 * @param credito
	 * @param rutDeudor
	 * @param tipoPago
	 * @return map<String,String> keys "codigoBarra","folioTesoreria"
	 * @throws Exception
	 */

	public static HashMap<String, String> crearCuponDePago(CreditoVO credito, String rutDeudor, String tipoPago, String totalAPagar) throws Exception {
		HashMap<String, String> ma = new HashMap<String, String>();

		// consulta si tiene cupones generados
		CuponDAO dao = new CuponDAO();
		BpagF002 consuCupon = new BpagF002();
		consuCupon.setOfiPro(Util.getOfiPro(credito.getOperacion(), credito.getOrigen()));
		consuCupon.setCreFol(Util.getCreFol(credito.getOperacion(), credito.getOrigen()));
		consuCupon.setEstProce(Constantes.getInstancia().ESTADO_IMPRESO);
		List<BpagF002> lista = dao.cuponesGeneradosOfiproCrefol(consuCupon);
		if (!lista.isEmpty()) {
			// si tiene cupon generado se debe anular
			for (BpagF002 bpagF002 : lista) {
				anularCuponDePago(bpagF002.getTesFol(), bpagF002.getAfiRut(), bpagF002.getAfiRutDv(), bpagF002.getTipoPago());
			}
		}

		// obtener folio
		ConsultaFolioCall genFolio = new ConsultaFolioCall();
		EntradaSalidaFolioVO entradaSalida = new EntradaSalidaFolioVO();
		entradaSalida.setCodigo("1");
		entradaSalida = genFolio.consultarFolio(entradaSalida);
		String folioTesoreria = entradaSalida.getFolio();

		String codigoBarra = "0";

		// crear folio en tabla
		BpagF002 cupon = new BpagF002();
		cupon.setTesFol(folioTesoreria);
		cupon.setTesOfi("1"); // deberia ser constante
		cupon.setCreFol(Util.getCreFol(credito.getOperacion(), credito.getOrigen()));
		cupon.setOfiPro(Util.getOfiPro(credito.getOperacion(), credito.getOrigen()));
		cupon.setAfiRut(rutDeudor.split("-")[0]);
		cupon.setAfiRutDv(rutDeudor.split("-")[1]);
		cupon.setValGrava(credito.getSumaGravamenes());
		cupon.setValGastos(credito.getGastosCobranza());
		cupon.setMtoPagar(totalAPagar);
		cupon.setTipoPago(tipoPago);
		cupon.setEstProce(Constantes.getInstancia().ESTADO_IMPRESO);
		cupon.setPorCondGra(credito.getPorcentCondGravamen());
		cupon.setPorCondGas(credito.getPorcentCondGastoCob());
		cupon.setValCondGra(credito.getSumaGravamenConCond());
		cupon.setValCondGas(credito.getGastoCobranzaConCond());
		cupon.setCodBarrAnt(""); // se deberia buscar antes si habia otro y
									// agregarlo antes de hacer este insert
		cupon.setOrigenCred(credito.getOrigen());
		cupon.setPrefijo(Constantes.getInstancia().PREFIJO_CODIGO_BARRA);

		cupon.setTipoCredito(credito.getProducto().trim());

		// inserta cupon y obtiene codigo barra
		codigoBarra = dao.ingresaCupon(cupon);

		try {

			TE07F1 cuponTeso = new TE07F1();

			cuponTeso.setCmba("1"); // CodigodeOficina
			cuponTeso.setObf002(""); // Creationdate
			cuponTeso.setObf003(""); // Creationtime
			cuponTeso.setObf005(""); // Lastchangedate
			cuponTeso.setObf006(""); // Lastchangetime
			cuponTeso.setSajkm92(Constantes.getInstancia().NOMBRE_USUARIO_APP); // Lastchangeuser
			cuponTeso.setSajkm94(Constantes.getInstancia().NOMBRE_USUARIO_APP); // Creationuser
			cuponTeso.setTe10a("0"); // Sesion
			cuponTeso.setTe1ba("0"); // CodigoCajero
			cuponTeso.setTe1ca("1900-01-01"); // FechaApertura
			cuponTeso.setTe1sa(""); // HoradeEmision
			cuponTeso.setTe1ta(""); // HoraRecaudacionPagoporCaja
			cuponTeso.setTe3wa(folioTesoreria); // FolioMovimiento
			cuponTeso.setTe3xa("I"); // TipoMovimiento
			cuponTeso.setTe3ya(Constantes.getInstancia().ESTADO_IMPRESO); // EstadoMovimientoCaja
			cuponTeso.setTe3za(""); // FechadeEmision
			cuponTeso.setTe40a(""); // FechaRecaudacionPagoporCaja
			cuponTeso.setTe41a(tipoPago); // FormadePago
			cuponTeso.setTe42a(rutDeudor.split("-")[0]); // RUT1
			cuponTeso.setTe43a(rutDeudor.split("-")[1]); // DVRUT1
			cuponTeso.setTe44a(""); // Identificacion1
			cuponTeso.setTe45a("0"); // RUT2
			cuponTeso.setTe46a(""); // DVRUT2
			cuponTeso.setTe47a(""); // Identificacion2
			cuponTeso.setTe49a("PAGO CREDITO SOCIAL"); // ObservacionMovCaja
			cuponTeso.setTe4aa("0"); // SucursaldeEmpresa
			cuponTeso.setTe4ba("A"); // EstadodeAutorizacion
			cuponTeso.setTe4ca("D"); // TipodePago(EfectivooCheque)
			cuponTeso.setTe4da(""); // FechaDisponibilidadEgreso
			cuponTeso.setTe4ea("N"); // EmiteFactura
			cuponTeso.setTe4fa("1"); // SerPagadopor|CodigodeOficina
			cuponTeso.setTe4va("0"); // MontoInteres
			cuponTeso.setTe4xa(totalAPagar); // MontoReajuste
			cuponTeso.setTe7ma(totalAPagar); // MontoInformado
			cuponTeso.setTe7na(totalAPagar); // MontoEmitido
			cuponTeso.setTe9ca("1"); // CorrelativoPago
			cuponTeso.setTea7a(Constantes.getInstancia().PREFIJO_CODIGO_BARRA + codigoBarra); // CodigodeBarra
			cuponTeso.setEstadoMovimientoValidacion(Constantes.getInstancia().ESTADO_IMPRESO);
			
			String codigoConcepto = "";
			if(credito.getOrigen().equals(Constantes.getInstancia().ORIGEN_SAP)){
				codigoConcepto = Constantes.getInstancia().ITEM_UNO_CODIGO_SAP;
			}else if(credito.getOrigen().equals(Constantes.getInstancia().ORIGEN_AS400)){
				codigoConcepto = Constantes.getInstancia().ITEM_UNO_CODIGO_AS400;
			}
			TesoreriaDAO tesDao = new TesoreriaDAO();
			String codAreaNegocio = tesDao.getCodAreaNegocio(codigoConcepto);
			cuponTeso.setTeqa(codAreaNegocio); // CodigoAreadeNegocio
			
			
			
			tesDao.ingresaTesoreria(cuponTeso);
			try {
				//SCA-01 Codigo de concepto segun origen del crédito
				cuponTeso.setOperacion(credito.getOperacion());
				ManagerDetalleTesoreria.ingresarDetallesTesoreria(cuponTeso, credito.getOrigen(), codigoConcepto);
				logger.debug("Se ingresa el detalle en tesoreria.");
			} catch (Exception e) {
				// anula tesoreria
				e.printStackTrace();
				tesDao.cambiaEstTesoDeGenerado(cuponTeso, Constantes.getInstancia().ESTADO_ANULADO);
				logger.error("Problemas en generar el detalle de tesoreria.");
				throw new Exception("Error al crear el cupón de pago.");
			}

		} catch (Exception e) {
			// anular folio tabla propia
			e.printStackTrace();
			logger.error("Problemas en generar el cupon en tesoreria. se debe anular en tabla de sistema");
			cupon.setEstadoParaActualizar("A");
			dao.cambiaEstadoCuponByRutTesFolEsta(cupon);
			throw new Exception("Error al crear el cupón de pago.");
		}

		ma.put("codigoBarra", codigoBarra);
		ma.put("folioTesoreria", folioTesoreria);

		return ma;
	}

	public static BpagF002 reemplazoCuponDePago(BpagF002 cuponOld) throws Exception {
		CuponDAO dao = new CuponDAO();

		// obtener nuevo folio tesoreria
		ConsultaFolioCall genFolio = new ConsultaFolioCall();
		EntradaSalidaFolioVO entradaSalida = new EntradaSalidaFolioVO();
		entradaSalida.setCodigo("1");
		entradaSalida = genFolio.consultarFolio(entradaSalida);
		String folioTesoreria = entradaSalida.getFolio();

		String codigoBarra = null;

		// crear folio en tabla
		BpagF002 cupon = cuponOld;
		cupon.setTesFol(folioTesoreria);
		cupon.setCodBarrAnt(cuponOld.getPrefijo() + cuponOld.getCodBarra());
		cupon.setPrefijo(Constantes.getInstancia().PREFIJO_CODIGO_BARRA);

		// inserta nuevo cupon y obtiene codigo barra
		codigoBarra = dao.ingresaCupon(cupon);

		try {
			TE07F1 cuponTeso = new TE07F1();

			cuponTeso.setCmba("1"); // CodigodeOficina
			cuponTeso.setObf002(""); // Creationdate
			cuponTeso.setObf003(""); // Creationtime
			cuponTeso.setObf005(""); // Lastchangedate
			cuponTeso.setObf006(""); // Lastchangetime
			cuponTeso.setSajkm92(Constantes.getInstancia().NOMBRE_USUARIO_APP); // Lastchangeuser
			cuponTeso.setSajkm94(Constantes.getInstancia().NOMBRE_USUARIO_APP); // Creationuser
			cuponTeso.setTe10a("0"); // Sesion
			cuponTeso.setTe1ba("0"); // CodigoCajero
			cuponTeso.setTe1ca("1900-01-01"); // FechaApertura
			cuponTeso.setTe1sa(""); // HoradeEmision
			cuponTeso.setTe1ta(""); // HoraRecaudacionPagoporCaja
			cuponTeso.setTe3wa(folioTesoreria); // FolioMovimiento
			cuponTeso.setTe3xa("I"); // TipoMovimiento
			cuponTeso.setTe3ya(Constantes.getInstancia().ESTADO_IMPRESO); // EstadoMovimientoCaja
			cuponTeso.setTe3za(""); // FechadeEmision
			cuponTeso.setTe40a(""); // FechaRecaudacionPagoporCaja
			cuponTeso.setTe41a(cuponOld.getTipoPago()); // FormadePago
			cuponTeso.setTe42a(cuponOld.getAfiRut()); // RUT1
			cuponTeso.setTe43a(cuponOld.getAfiRutDv()); // DVRUT1
			cuponTeso.setTe44a(""); // Identificacion1
			cuponTeso.setTe45a("0"); // RUT2
			cuponTeso.setTe46a(""); // DVRUT2
			cuponTeso.setTe47a(""); // Identificacion2
			cuponTeso.setTe49a("PAGO CREDITO SOCIAL"); // ObservacionMovCaja
			cuponTeso.setTe4aa("0"); // SucursaldeEmpresa
			cuponTeso.setTe4ba("A"); // EstadodeAutorizacion
			cuponTeso.setTe4ca("D"); // TipodePago(EfectivooCheque)
			cuponTeso.setTe4da(""); // FechaDisponibilidadEgreso
			cuponTeso.setTe4ea("N"); // EmiteFactura
			cuponTeso.setTe4fa("1"); // SerPagadopor|CodigodeOficina
			cuponTeso.setTe4va("0"); // MontoInteres
			cuponTeso.setTe4xa(cuponOld.getMtoPagar()); // MontoReajuste
			cuponTeso.setTe7ma(cuponOld.getMtoPagar()); // MontoInformado
			cuponTeso.setTe7na(cuponOld.getMtoPagar()); // MontoEmitido
			cuponTeso.setTe9ca("1"); // CorrelativoPago
			cuponTeso.setTea7a(Constantes.getInstancia().PREFIJO_CODIGO_BARRA + codigoBarra); // CodigodeBarra
			cuponTeso.setEstadoMovimientoValidacion(Constantes.getInstancia().ESTADO_IMPRESO);
			
			String codigoConcepto = "";
			if(cuponOld.getOrigenCred().equals(Constantes.getInstancia().ORIGEN_SAP)){
				codigoConcepto = Constantes.getInstancia().ITEM_UNO_CODIGO_SAP;
			}else if(cuponOld.getOrigenCred().equals(Constantes.getInstancia().ORIGEN_AS400)){
				codigoConcepto = Constantes.getInstancia().ITEM_UNO_CODIGO_AS400;
			}
			TesoreriaDAO tesDao = new TesoreriaDAO();
			String codAreaNegocio = tesDao.getCodAreaNegocio(codigoConcepto);
			cuponTeso.setTeqa(codAreaNegocio); // CodigoAreadeNegocio
			
			tesDao.ingresaTesoreria(cuponTeso);
			try {
				//SCA-01 Codigo de concepto segun origen del crédito
				cuponTeso.setOperacion(CompletaUtil.formateaNroOperacion(cuponOld.getOfiPro(), cuponOld.getCreFol()));
				ManagerDetalleTesoreria.ingresarDetallesTesoreria(cuponTeso, cuponOld.getOrigenCred(), codigoConcepto);

			} catch (Exception e) {
				// anula tesoreria
				e.printStackTrace();
				tesDao.cambiaEstTesoDeGenerado(cuponTeso, Constantes.getInstancia().ESTADO_ANULADO);
				throw new Exception("Error al crear el cupón de pago");
			}

		} catch (Exception e) {
			// anular folio tabla propia
			e.printStackTrace();
			cupon.setEstadoParaActualizar("A");
			dao.cambiaEstadoCuponByRutTesFolEsta(cupon);
			throw new Exception("Error al crear el cupón de pago");
		}

		return cupon;

	}

	/**
	 * anula el cupón en tesorería y en la tabla propia en caso de que ya exista
	 * un cupon anterior
	 * 
	 * @param folioTesoreria
	 * @throws Exception
	 */
	public static void anularCuponDePago(String folioTesoreria, String rut, String rutDv, String tipoPago) throws Exception {
		// anula en tabla propia
		CuponDAO cuponDao = new CuponDAO();
		BpagF002 cupon = new BpagF002();
		cupon.setTesFol(folioTesoreria);
		cupon.setAfiRut(rut);
		cupon.setAfiRutDv(rutDv);
		cupon.setEstProce(Constantes.getInstancia().ESTADO_IMPRESO);
		cupon.setEstadoParaActualizar(Constantes.getInstancia().ESTADO_ANULADO);
		cuponDao.cambiaEstadoCuponByRutTesFolEsta(cupon);

		// anula en tesoreria
		TesoreriaDAO tesDao = new TesoreriaDAO();
		TE07F1 tescup = new TE07F1();
		tescup.setTe3wa(folioTesoreria);
		tescup.setTe42a(rut);
		tescup.setTe43a(rutDv);
		tescup.setSajkm92(Constantes.getInstancia().NOMBRE_USUARIO_APP);
		tescup.setTe41a(tipoPago);
		tescup.setEstadoMovimientoValidacion(Constantes.getInstancia().ESTADO_IMPRESO);
		tesDao.cambiaEstTesoDeGenerado(tescup, Constantes.getInstancia().ESTADO_ANULADO);

	}

	/**
	 * Obtiene los cupones mediante un rut segun un estado.
	 * 
	 * @param rut
	 * @param estadoProceso
	 * @return
	 * @throws Exception
	 */
	public static List<BpagF002> cuponesByEstadoAndRut(String rut, String estadoProceso) throws Exception {
		CuponDAO cuponDao = new CuponDAO();
		BpagF002 cupon = new BpagF002();
		cupon.setAfiRut(String.valueOf(RutUtil.getLongRut(rut)));
		cupon.setAfiRutDv(String.valueOf(RutUtil.getDv(rut)));
		cupon.setEstProce(estadoProceso);
		return cuponDao.cuponesByEstadoAndRut(cupon);
	}

	/**
	 * obtiene los cupones mediante el rut, el estado del proceso y el folio
	 * 
	 * @param rut
	 * @param estadoProceso
	 * @param folio
	 * @return
	 * @throws Exception
	 */
	public static BpagF002 cuponByEstadoAndFolioAndRut(String rut, String estadoProceso, String folio) throws Exception {
		CuponDAO cuponDao = new CuponDAO();
		BpagF002 cupon = new BpagF002();
		cupon.setAfiRut(String.valueOf(RutUtil.getLongRut(rut)));
		cupon.setAfiRutDv(String.valueOf(RutUtil.getDv(rut)));
		cupon.setTesFol(folio);
		cupon.setEstProce(estadoProceso);
		return cuponDao.cuponByEstadoAndFolioAndRut(cupon);
	}

	/**
	 * obtiene el listado de los cupones segun el estado del proceso.
	 * 
	 * @param estadoProceso
	 * @return
	 * @throws Exception
	 */
	public static List<BpagF002> cuponesByEstado(String estadoProceso) throws Exception {
		CuponDAO cuponDao = new CuponDAO();
		return cuponDao.cuponesByEstado(estadoProceso);
	}

	/**
	 * obtienwe los cupones segun el estado y el tipo de pago
	 * @param estadoProceso
	 * @param tipoPago
	 * @return
	 * @throws Exception
	 */
	public static List<BpagF002> cuponesEstadoAndTipoPago(String estadoProceso, String tipoPago) throws Exception {
		CuponDAO cuponDao = new CuponDAO();
		BpagF002 cupon = new BpagF002();
		cupon.setEstProce(estadoProceso);
		cupon.setTipoPago(tipoPago);
		return cuponDao.cuponesEstadoAndTipoPago(cupon);
	}

	

	
	public static void cursaCupondePago(BpagF002 cuponPago, String idTransaccion) throws Exception {
		// cursa en tabla propia
		CuponDAO cuponDao = new CuponDAO();
		BpagF002 cupon = cuponPago;
		cupon.setEstadoParaActualizar(Constantes.getInstancia().ESTADO_CURSADO);
		cupon.setIdspl(idTransaccion);
		cuponDao.cursaCuponByRutTesFolEsta(cupon);
	}

	
}
