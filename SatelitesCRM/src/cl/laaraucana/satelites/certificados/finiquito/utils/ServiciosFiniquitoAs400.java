package cl.laaraucana.satelites.certificados.finiquito.utils;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.UtilRepactarTabla;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaListaFiniquitoVO;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoCrc456.ClienteConsultaCreditoCrc456;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoCrc456.VO.EntradaConsultaCreditoCrc456;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoCrc456.VO.SalidaConsultaCreditoCrc456;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.ClienteConsultaCreditosPorRutEnAs400;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.EntradaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.SalidaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.SalidaListaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

public class ServiciosFiniquitoAs400 {

	/**************************************************** creditos vigentes Cruzados por rut ***********************************************************/
	public static SalidaListaFiniquitoVO obtenerCreditosVigentesCruzadosAs400(String rut, String fechaFiniquito) {

		SalidaListaFiniquitoVO salidaVO = new SalidaListaFiniquitoVO();

		try {
			rut = CompletaUtil.llenaConCeros(rut, 11, true);
			String fechaColocacion = Utils.pasaFechaWEBaAs400(fechaFiniquito);
			String periodoRepactacion = Utils.getFechaWEBaPeriodo(fechaFiniquito);

			// System.out.println("transformando fecha a formato yymmdd "+fechaColocacion);
			// System.out.println("transformando fecha a formato yymm "+periodoRepactacion);
			
			salidaVO = obtenerCreditosVigentesAs400(rut, fechaColocacion);
			
			if (salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje(salidaVO.getMensaje());
				return salidaVO;
			}
		} catch (Exception e) {
			salidaVO = new SalidaListaFiniquitoVO();
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje(e.getMessage());
			e.printStackTrace();
		}

		return salidaVO;
	}



	/**************************************************** creditos vigentes por rut ***********************************************************/

	public static SalidaListaFiniquitoVO obtenerCreditosVigentesAs400(String rut, String fechaFiniquito) {

		EntradaConsultaCreditosPorRutEnAs400VO entradaVO = new EntradaConsultaCreditosPorRutEnAs400VO();
		entradaVO.setRut(rut);

		ClienteConsultaCreditosPorRutEnAs400 clienteAs400 = new ClienteConsultaCreditosPorRutEnAs400();
		SalidaListaFiniquitoVO salidaVO = new SalidaListaFiniquitoVO();
		try {
			salidaVO = mapearConsultaCreditosPorRutEnAs400((SalidaListaConsultaCreditosPorRutEnAs400VO) clienteAs400.call(entradaVO), fechaFiniquito);
		} catch (Exception e) {
			salidaVO = new SalidaListaFiniquitoVO();
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Excepcion en ClienteConsultaCreditosPorRutEnAs400: " + e.getMessage());
			return salidaVO;
		}

		return salidaVO;
	}

	private static SalidaListaFiniquitoVO mapearConsultaCreditosPorRutEnAs400(SalidaListaConsultaCreditosPorRutEnAs400VO entrada, String fechaFiniquito) throws Exception {
		SalidaListaFiniquitoVO salidaVO = new SalidaListaFiniquitoVO();
		List<SalidaFiniquitoVO> listaCreditos = new ArrayList<SalidaFiniquitoVO>();
		SalidaFiniquitoVO credito = null;

		if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
			salidaVO.setListaCreditos(listaCreditos);
			return salidaVO;
		}
		if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
			for (SalidaConsultaCreditosPorRutEnAs400VO detalle : entrada.getListaCreditos()) {
				
				if("S".equalsIgnoreCase(detalle.getRolPagador()) || "X".equalsIgnoreCase(detalle.getRolPagador())){
				
					if (!CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim().equalsIgnoreCase(detalle.getTipoProducto())) {
						if (detalle.getEstadoCredito().trim().equalsIgnoreCase(ServiciosConst.RES_SERVICIOS.getString("creditos.vigentes.rut.as400.vigente")) || detalle.getEstadoCredito().trim().equalsIgnoreCase(ServiciosConst.RES_SERVICIOS.getString("creditos.vigentes.rut.as400.moroso"))) {
							credito = new SalidaFiniquitoVO();
							credito.setFolio(detalle.getFolioCredito());
							credito.setCuotaHasta(CompletaUtil.quitaCerosIzqString(detalle.getCantidadCuotas()));
							credito.setTipoCredito(detalle.getTipoProducto());
							//credito.setSaldoCapital(Utils.stringToDouble(detalle.getMontoAdaudado()));
							credito.setEstadoCredito(detalle.getEstadoCredito());
							credito.setSumaCuotas(detalle.getSumaCuotas());
							credito.setSumaGCob(detalle.getSumaGCob());
							// se debe sacar el credito desde el saldo capital y los gravamenes y sumarlos
							double gravamenes = UtilRepactarTabla.getGravamenesCredito(detalle.getFolioCredito());
							credito.setGravamenes(gravamenes);
							String[] folioArray = detalle.getFolioCredito().split("-");
							String oficina = folioArray[0];
							String folio = folioArray[1];
							SalidaFiniquitoVO salidaCrc456VO = obtieneCreditoCrc456AS400(oficina, folio, fechaFiniquito);
							if (salidaCrc456VO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
								salidaVO.setCodigoError(salidaCrc456VO.getCodigoError());
								salidaVO.setMensaje(salidaCrc456VO.getMensaje());
								return salidaVO;
							}
							double saldoCapital = salidaCrc456VO.getMontoDeudaCapital();
							double gastoCobranza = Utils.stringToDouble(detalle.getSumaGCob());
							double montoTotal = saldoCapital + gravamenes + gastoCobranza;
							credito.setSaldoCapital(saldoCapital);
							credito.setCuotaDesde(CompletaUtil.quitaCerosIzqString(salidaCrc456VO.getCuotaDesde()));
							credito.setMontoTotal(montoTotal);
							
							listaCreditos.add(credito);
						}
					}
				}
			}
		}
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		salidaVO.setListaCreditos(listaCreditos);

		return salidaVO;
	}

	/**************************************************** creditos a obtieneCreditoCrc456AS400 por rut * @throws Exception ***********************************************************/

	public static SalidaFiniquitoVO obtieneCreditoCrc456AS400(String oficina, String folio, String fechaFiniquito) throws Exception {

		EntradaConsultaCreditoCrc456 entradaVO = new EntradaConsultaCreditoCrc456();
		entradaVO.setFolio(folio);
		entradaVO.setOficina(oficina);
		entradaVO.setFechaFiniquito(fechaFiniquito);

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
		//salidaVO.setMontoSaldoCredito(Utils.stringToDouble(entrada.getMontoSaldoCredito()));
		salidaVO.setCuotaDesde(entrada.getCuotaVigenteDesde());


		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		return salidaVO;
	}

}
