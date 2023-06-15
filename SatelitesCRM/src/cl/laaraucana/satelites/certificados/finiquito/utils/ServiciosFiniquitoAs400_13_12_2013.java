package cl.laaraucana.satelites.certificados.finiquito.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.UtilRepactarTabla;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaListaFiniquitoVO;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.main.VO.RepactarTablaVO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoCrc456.ClienteConsultaCreditoCrc456;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoCrc456.VO.EntradaConsultaCreditoCrc456;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoCrc456.VO.SalidaConsultaCreditoCrc456;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.ClienteCreditoARepactarAs400;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.VO.EntradaCreditoARepactarVO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.VO.SalidaCreditoARepactarVO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.VO.SalidaListaCreditoARepactarVO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.ClienteConsultaCreditosPorRutEnAs400;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.EntradaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.SalidaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.SalidaListaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

public class ServiciosFiniquitoAs400_13_12_2013 {

	/**************************************************** creditos vigentes Cruzados por rut ***********************************************************/
	public static SalidaListaFiniquitoVO obtenerCreditosVigentesCruzadosAs400(String rut, String fechaFiniquito) {

		SalidaListaFiniquitoVO salidaVO = new SalidaListaFiniquitoVO();

		try {
			rut = CompletaUtil.llenaConCeros(rut, 11, true);
			String fechaColocacion = Utils.pasaFechaWEBaAs400(fechaFiniquito);
			String periodoRepactacion = Utils.getFechaWEBaPeriodo(fechaFiniquito);

			// System.out.println("transformando fecha a formato yymmdd "+fechaColocacion);
			// System.out.println("transformando fecha a formato yymm "+periodoRepactacion);

			Map<String, SalidaFiniquitoVO> salidaVigentesVO = obtenerCreditosVigentesAs400Map(rut);
			SalidaListaFiniquitoVO salidaVigentesArepactarVO = obtenerCreditosARepactarAs400(Utils.obtenerValorAnteriorA(rut, "-"), fechaColocacion, periodoRepactacion);
			if (salidaVigentesVO.containsKey("error")) {
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje(salidaVigentesVO.get("error").getMensaje());
				return salidaVO;
			}
			if (salidaVigentesArepactarVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje(salidaVigentesArepactarVO.getMensaje());
				return salidaVO;
			}
			if (!salidaVigentesVO.containsKey("error") && salidaVigentesArepactarVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
				salidaVO = mapearVigentesCruzados(salidaVigentesVO, salidaVigentesArepactarVO);
				if (salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
					return salidaVO;
				}
			} else {
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje("Ocurrio un problema al realizar el cruce de datos");
			}
		} catch (Exception e) {
			salidaVO = new SalidaListaFiniquitoVO();
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje(e.getMessage());
			e.printStackTrace();
		}

		return salidaVO;
	}

	private static SalidaListaFiniquitoVO mapearVigentesCruzados(Map<String, SalidaFiniquitoVO> entradaVigentesVO, SalidaListaFiniquitoVO entradaVigentesArepactarVO) {

		SalidaListaFiniquitoVO salidaVO = new SalidaListaFiniquitoVO();
		List<SalidaFiniquitoVO> listaCreditos = new ArrayList<SalidaFiniquitoVO>();
		SalidaFiniquitoVO credito = null;
		List<SalidaFiniquitoVO> listaCreditosARepactar = entradaVigentesArepactarVO.getListaCreditos();

		/*if(entradaVigentesVO.size() != listaCreditosARepactar.size()){
			salidaVO = new SalidaListaFiniquitoVO();
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			System.out.println("los valores no tienen la misma cantidad");
			salidaVO.setMensaje("Error al obtener datos del certificado");
			return salidaVO;
		}*/

		// boolean tieneCredito = false;
		for (SalidaFiniquitoVO detalleARepactar : listaCreditosARepactar) {
			// detalle a repactar viene sin 002- y creditos vigentes si, per se
			// corta al guardar en el map.
			credito = entradaVigentesVO.get(detalleARepactar.getFolio());
			if (credito != null) {
				// credito.setFolio(detalleARepactar.getFolio()); dejar
				// comentado para que tome el folio de creditos vigentes
				credito.setCuotaDesde(detalleARepactar.getCuotaDesde());
				credito.setGravamenes(detalleARepactar.getGravamenes());
				credito.setMontoTotal(detalleARepactar.getMontoTotal());
				credito.setSaldoCapital(detalleARepactar.getSaldoCapital());
				credito.setMontoTotal(detalleARepactar.getMontoTotal()+Utils.stringToDouble(credito.getSumaGCob()));
				listaCreditos.add(credito);
			} else {
				salidaVO = new SalidaListaFiniquitoVO();
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje("Error al obtener datos del certificado");
				System.out.println("el crédito no existe");
				return salidaVO;
			}

		}
		salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
		salidaVO.setMensaje("Se realizo el cruce correctamente");

		salidaVO.setListaCreditos(listaCreditos);

		return salidaVO;
	}

	/**************************************************** creditos vigentes por rut ***********************************************************/

	public static Map<String, SalidaFiniquitoVO> obtenerCreditosVigentesAs400Map(String rut) throws Exception {

		EntradaConsultaCreditosPorRutEnAs400VO entradaVO = new EntradaConsultaCreditosPorRutEnAs400VO();
		entradaVO.setRut(rut);

		ClienteConsultaCreditosPorRutEnAs400 clienteAs400 = new ClienteConsultaCreditosPorRutEnAs400();
		Map<String, SalidaFiniquitoVO> map = new LinkedHashMap<String, SalidaFiniquitoVO>();

		map = mapearConsultaCreditosPorRutEnAs400Map((SalidaListaConsultaCreditosPorRutEnAs400VO) clienteAs400.call(entradaVO));

		return map;
	}

	private static Map<String, SalidaFiniquitoVO> mapearConsultaCreditosPorRutEnAs400Map(SalidaListaConsultaCreditosPorRutEnAs400VO entrada) throws Exception {
		Map<String, SalidaFiniquitoVO> map = new LinkedHashMap<String, SalidaFiniquitoVO>();
		SalidaFiniquitoVO credito = null;

		if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
			credito = new SalidaFiniquitoVO();
			credito.setMensaje(entrada.getMensaje());
			map.put("error", credito);
			return map;
		}
		if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
			for (SalidaConsultaCreditosPorRutEnAs400VO detalle : entrada.getListaCreditos()) {
				if (!CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim().equalsIgnoreCase(detalle.getTipoProducto())) {
					if (detalle.getEstadoCredito().trim().equalsIgnoreCase(ServiciosConst.RES_SERVICIOS.getString("creditos.vigentes.rut.as400.vigente")) || detalle.getEstadoCredito().trim().equalsIgnoreCase(ServiciosConst.RES_SERVICIOS.getString("creditos.vigentes.rut.as400.moroso"))) {
						credito = new SalidaFiniquitoVO();
						credito.setFolio(detalle.getFolioCredito());
						credito.setCuotaHasta(CompletaUtil.quitaCerosIzqString(detalle.getCantidadCuotas()));
						credito.setTipoCredito(detalle.getTipoProducto());
						// credito.setSaldoCapital(Utils.stringToDouble(detalle.getMontoAdaudado()));
						credito.setEstadoCredito(detalle.getEstadoCredito());
						credito.setSumaCuotas(detalle.getSumaCuotas());
						credito.setSumaGCob(detalle.getSumaGCob());
						map.put(Utils.obtenerValorSiguienteA(detalle.getFolioCredito(), "-"), credito);
					}
				}

			}
		}
		return map;
	}

	/**************************************************** creditos a repactar por rut * @throws Exception ***********************************************************/

	public static SalidaListaFiniquitoVO obtenerCreditosARepactarAs400(String rut, String fechaColocacion, String periodoRepactacion) throws Exception {

		EntradaCreditoARepactarVO entradaVO = new EntradaCreditoARepactarVO();
		entradaVO.setRut(rut);

		entradaVO.setFechaColocacion(fechaColocacion);
		entradaVO.setPeriodoRepactacion(periodoRepactacion);

		ClienteCreditoARepactarAs400 clienteAs400 = new ClienteCreditoARepactarAs400();
		SalidaListaFiniquitoVO salidaVO;

		salidaVO = mapearConsultaCreditosARepactarPorRutEnAs400((SalidaListaCreditoARepactarVO) clienteAs400.call(entradaVO));

		return salidaVO;
	}

	private static SalidaListaFiniquitoVO mapearConsultaCreditosARepactarPorRutEnAs400(SalidaListaCreditoARepactarVO entrada) throws Exception {
		SalidaListaFiniquitoVO salidaVO = new SalidaListaFiniquitoVO();
		List<SalidaFiniquitoVO> listaCreditos = new ArrayList<SalidaFiniquitoVO>();
		SalidaFiniquitoVO creditoRepactar = null;

		if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
			return salidaVO;
		}
		for (SalidaCreditoARepactarVO detalle : entrada.getListaCreditos()) {
			if (detalle.getEstadoCredito() != null && (Integer.parseInt(detalle.getEstadoCredito()) >= 0 && Integer.parseInt(detalle.getEstadoCredito()) <= 6)) {
				creditoRepactar = new SalidaFiniquitoVO();
				creditoRepactar.setFolio(detalle.getFolio());
				// credito.setCuotasVigentes(detalle.getCuotasVigentes());
				RepactarTablaVO repactarTablaVO = UtilRepactarTabla.getDesdePrima(detalle);
				if (repactarTablaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
					salidaVO.setCodigoError(repactarTablaVO.getCodigoError());
					salidaVO.setMensaje(repactarTablaVO.getMensaje());
					return salidaVO;
				}
				//credito.setGravamenes((Utils.stringToDouble(detalle.getMontoInteresMora()) + Utils.stringToDouble(detalle.getMontoReajustado())));
				// credito.setMontoTotal(Utils.stringToDouble(detalle.getMontoRepactacion()));
				//credito.setMontoTotal(UtilRepactarTabla.getTotalSinMontoAdeudado(repactarTablaVO, detalle, folioCompleto));
				String folioCompleto = detalle.getOficina() + "-" + detalle.getFolio();		
				creditoRepactar.setCuotaDesde(String.valueOf(repactarTablaVO.getCuotaDesde()));
				creditoRepactar.setMontoPrima(repactarTablaVO.getTotalPrima());
				
				double gravamen = UtilRepactarTabla.getGravamenesCredito(folioCompleto);
				// llamar al servicio para que me retorne el saldo capital.
				SalidaFiniquitoVO creditoCrc456 = obtieneCreditoCrc456AS400(detalle.getOficina(), detalle.getFolio());
				if (creditoCrc456.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
					salidaVO.setCodigoError(creditoCrc456.getCodigoError());
					salidaVO.setMensaje(creditoCrc456.getMensaje());
					return salidaVO;
				}
				
				creditoRepactar.setSaldoCapital(creditoCrc456.getMontoDeudaCapital());
				creditoRepactar.setMontoTotal(creditoCrc456.getMontoDeudaCapital() + gravamen);
				creditoRepactar.setGravamenes(gravamen);
				
				
				creditoRepactar.setEstadoCredito(detalle.getEstadoCredito());
				listaCreditos.add(creditoRepactar);
			}
		}
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		salidaVO.setListaCreditos(listaCreditos);

		return salidaVO;
	}
	
	
	public static SalidaFiniquitoVO obtieneCreditoCrc456AS400(String oficina, String folio) throws Exception {

		EntradaConsultaCreditoCrc456 entradaVO = new EntradaConsultaCreditoCrc456();
		entradaVO.setFolio(folio);
		entradaVO.setOficina(oficina);

		ClienteConsultaCreditoCrc456 clienteAs400 = new ClienteConsultaCreditoCrc456();
		SalidaFiniquitoVO salidaVO;

		salidaVO = mapearCreditoCrc456AS400((SalidaConsultaCreditoCrc456) clienteAs400.call(entradaVO));

		return salidaVO;
	}
	
	
	private static SalidaFiniquitoVO mapearCreditoCrc456AS400(SalidaConsultaCreditoCrc456 entrada) {
		SalidaFiniquitoVO salidaVO = new SalidaFiniquitoVO();

		if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
			return salidaVO;
		}
		salidaVO.setMontoDeudaCapital(Utils.stringToDouble(entrada.getMontoDeudaCapital()));
		salidaVO.setMontoSaldoCredito(Utils.stringToDouble(entrada.getMontoSaldoCredito()));
		
		System.out.println("======================== yaaaaaaaaaaaaaaaaaa");
		System.out.println("el motno deuda es "+entrada.getMontoDeudaCapital());
		System.out.println("el monto saldo credito es "+entrada.getMontoSaldoCredito());
		
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());


		return salidaVO;
	}
	
	
	

}
