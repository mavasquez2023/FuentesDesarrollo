package cl.laaraucana.satelites.certificados.finiquito.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaDetalleFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaListaDetalleFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaListaFiniquitoVO;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoPorFolioEnAs400.ClienteConsultaCreditosPorFolioEnAs400;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoPorFolioEnAs400.VO.EntradaConsultaCreditoPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoPorFolioEnAs400.VO.SalidaConsultaCreditoPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoPorFolioEnAs400.VO.SalidaListaConsultaCreditoPorFolioEnAs400VO;
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

public class ServiciosFiniquitoAs40022082013 {

	/**************************************************** creditos vigentes Cruzados por rut ***********************************************************/
	public static SalidaListaFiniquitoVO obtenerCreditosVigentesCruzadosAs400(String rut, String fechaFiniquito) {

		SalidaListaFiniquitoVO salidaVO = new SalidaListaFiniquitoVO();

		try {
			rut = CompletaUtil.llenaConCeros(rut, 11, true);
			String fechaColocacion =  Utils.pasaFechaWEBaAs400(fechaFiniquito);
			String periodoRepactacion = Utils.getFechaWEBaPeriodo(fechaFiniquito);

			//System.out.println("transformando fecha a formato yymmdd "+fechaColocacion);
			//System.out.println("transformando fecha a formato yymm "+periodoRepactacion);
			
			Map<String, SalidaFiniquitoVO> salidaVigentesVO = obtenerCreditosVigentesAs400Map(rut);
			SalidaListaFiniquitoVO salidaVigentesArepactarVO = obtenerCreditosARepactarAs400(Utils.obtenerValorAnteriorA(rut, "-"),fechaColocacion, periodoRepactacion );

			if (salidaVigentesVO != null && salidaVigentesArepactarVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
				salidaVO = mapearVigentesCruzados(salidaVigentesVO, salidaVigentesArepactarVO);
				if(salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
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
		
		
		
		/*for (Map.Entry<String, SalidaFiniquitoVO> entry : entradaVigentesVO.entrySet())
		{
			//System.out.println(entry.getKey() + "/" + entry.getValue());
			if(entry.getValue().getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim())){
				listaCreditosEducacionKey.add(entry.getKey());
			}
		}*/
		////////////////////////////////////////////////////////////////////////////////////////////revisar si funciona
		Iterator<Entry<String, SalidaFiniquitoVO>> iter = entradaVigentesVO.entrySet().iterator();
		while (iter.hasNext()) {
		    Entry<String, SalidaFiniquitoVO> entry = iter.next();
		    if(entry.getValue().getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim())){
		    	iter.remove();
		    }
		}

		//////////////////////////////////////////////////////////////////////////////////////////////////////////se quitan los creditos educacional de a repactar
		System.out.println("la cantidad de vigentes es "+entradaVigentesVO.size() +"contra "+listaCreditosARepactar.size());
		if(entradaVigentesVO.size() != listaCreditosARepactar.size()){
			salidaVO = new SalidaListaFiniquitoVO();
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			System.out.println("los valores no tienen la misma cantidad");
			salidaVO.setMensaje("Error al obtener datos del certificado");
			return salidaVO;
		}

		//boolean tieneCredito = false;
		for (SalidaFiniquitoVO detalleARepactar : listaCreditosARepactar) {
			//detalle a repactar viene sin 002- y creditos vigentes si, per se corta al guardar en el map.
			credito = entradaVigentesVO.get(detalleARepactar.getFolio());
			if (credito != null) {
				// credito.setFolio(detalleARepactar.getFolio()); dejar
				// comentado para que tome el folio de creditos vigentes
				// ///////////////////////////////////////////////////////////////////revisar
				credito.setCuotaDesde(detalleARepactar.getCuotasVigentes());
				credito.setGravamenes(detalleARepactar.getGravamenes());
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

	public static SalidaListaFiniquitoVO obtenerCreditosVigentesAs400(String rut) {

		EntradaConsultaCreditosPorRutEnAs400VO entradaVO = new EntradaConsultaCreditosPorRutEnAs400VO();
		entradaVO.setRut(rut);

		ClienteConsultaCreditosPorRutEnAs400 clienteAs400 = new ClienteConsultaCreditosPorRutEnAs400();
		SalidaListaFiniquitoVO salidaVO = new SalidaListaFiniquitoVO();
		try {
			salidaVO = mapearConsultaCreditosPorRutEnAs400((SalidaListaConsultaCreditosPorRutEnAs400VO) clienteAs400.call(entradaVO));
		} catch (Exception e) {
			salidaVO = new SalidaListaFiniquitoVO();
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Excepcion en ClienteConsultaCreditosPorRutEnAs400: " + e.getMessage());
			return salidaVO;
		}

		return salidaVO;
	}

	private static SalidaListaFiniquitoVO mapearConsultaCreditosPorRutEnAs400(SalidaListaConsultaCreditosPorRutEnAs400VO entrada) throws Exception {
		SalidaListaFiniquitoVO salidaVO = new SalidaListaFiniquitoVO();
		List<SalidaFiniquitoVO> listaCreditos = new ArrayList<SalidaFiniquitoVO>();
		SalidaFiniquitoVO credito = null;

		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
			salidaVO.setListaCreditos(listaCreditos);
			return salidaVO;
		}
		for (SalidaConsultaCreditosPorRutEnAs400VO detalle : entrada.getListaCreditos()) {
			if (detalle.getEstadoCredito()!= null && detalle.getEstadoCredito().trim().equalsIgnoreCase(ServiciosConst.RES_SERVICIOS.getString("creditos.vigentes.rut.as400.vigente")) || detalle.getEstadoCredito().trim().equalsIgnoreCase(ServiciosConst.RES_SERVICIOS.getString("creditos.vigentes.rut.as400.moroso"))) {
				credito = new SalidaFiniquitoVO();
				credito.setFolio(detalle.getFolioCredito());
				credito.setCuotaHasta(detalle.getCantidadCuotas());
				credito.setTipoCredito(detalle.getTipoProducto());
				credito.setSaldoCapital(Utils.stringToDouble(detalle.getMontoAdaudado()));
				credito.setEstadoCredito(detalle.getEstadoCredito());
				/////////////////////////////////////////////////////////////////////////el if es para no tomar en cuenta a los educacion CES
				if (credito.getTipoCredito() != null && credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim())) {
					/*SalidaListaDetalleFiniquitoVO detalleCredito = ServiciosFiniquitoAs400.obtenerDetalleCreditosVigentesAs400(credito.getFolio());
					if (detalleCredito != null && detalleCredito.getCodigoError().equalsIgnoreCase(ConstantesRespuestas.COD_RESPUESTA_SUCCESS)) {
						credito.setListaDetalle(detalleCredito);
					}*/
				}else{
					listaCreditos.add(credito);
				}
				
			}

		}
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		salidaVO.setListaCreditos(listaCreditos);

		return salidaVO;
	}

	public static Map<String, SalidaFiniquitoVO> obtenerCreditosVigentesAs400Map(String rut) throws Exception {

		EntradaConsultaCreditosPorRutEnAs400VO entradaVO = new EntradaConsultaCreditosPorRutEnAs400VO();
		entradaVO.setRut(rut);

		ClienteConsultaCreditosPorRutEnAs400 clienteAs400 = new ClienteConsultaCreditosPorRutEnAs400();
		Map<String, SalidaFiniquitoVO> map = new LinkedHashMap<String, SalidaFiniquitoVO>();
		//try {
			map = mapearConsultaCreditosPorRutEnAs400Map((SalidaListaConsultaCreditosPorRutEnAs400VO) clienteAs400.call(entradaVO));
		/*} catch (Exception e) {
			return null;
		}*/

		return map;
	}

	private static Map<String, SalidaFiniquitoVO> mapearConsultaCreditosPorRutEnAs400Map(SalidaListaConsultaCreditosPorRutEnAs400VO entrada) throws Exception {
		Map<String, SalidaFiniquitoVO> map = new LinkedHashMap<String, SalidaFiniquitoVO>();
		SalidaFiniquitoVO credito = null;

		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
			return null;
		}
		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
			for (SalidaConsultaCreditosPorRutEnAs400VO detalle : entrada.getListaCreditos()) {
				if (detalle.getEstadoCredito().trim().equalsIgnoreCase(ServiciosConst.RES_SERVICIOS.getString("creditos.vigentes.rut.as400.vigente")) || detalle.getEstadoCredito().trim().equalsIgnoreCase(ServiciosConst.RES_SERVICIOS.getString("creditos.vigentes.rut.as400.moroso"))) {
					if(detalle.getTipoCredito() != null && !detalle.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.intercaja.as400").trim())){
						credito = new SalidaFiniquitoVO();
						credito.setFolio(detalle.getFolioCredito());
						credito.setCuotaHasta(detalle.getCantidadCuotas());
	///////////////////////////////////////////////////////////////////////////////////////tiene que ser tipoProducto
						//credito.setTipoCredito(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim());
						credito.setTipoCredito(detalle.getTipoProducto());
						credito.setSaldoCapital(Utils.stringToDouble(detalle.getMontoAdaudado()));
						credito.setEstadoCredito(detalle.getEstadoCredito());
						if (credito.getTipoCredito() != null && credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim())) {
							SalidaListaDetalleFiniquitoVO detalleCredito = ServiciosFiniquitoAs40022082013.obtenerDetalleCreditosVigentesAs400(credito.getFolio());
							if (detalleCredito != null && detalleCredito.getCodigoError().equalsIgnoreCase("0")) {
								credito.setListaDetalle(detalleCredito);
							}
						}
						map.put(Utils.obtenerValorSiguienteA(detalle.getFolioCredito(), "-"), credito);
					}
				}
	
			}
		}
		return map;
	}

	/**************************************************** creditos a repactar por rut 
	 * @throws Exception ***********************************************************/

	public static SalidaListaFiniquitoVO obtenerCreditosARepactarAs400(String rut, String fechaColocacion, String periodoRepactacion) throws Exception {

		EntradaCreditoARepactarVO entradaVO = new EntradaCreditoARepactarVO();
		entradaVO.setRut(rut);
		
		entradaVO.setFechaColocacion(fechaColocacion);
		entradaVO.setPeriodoRepactacion(periodoRepactacion);

		ClienteCreditoARepactarAs400 clienteAs400 = new ClienteCreditoARepactarAs400();
		SalidaListaFiniquitoVO salidaVO;

		//try {

			salidaVO = mapearConsultaCreditosARepactarPorRutEnAs400((SalidaListaCreditoARepactarVO) clienteAs400.call(entradaVO));

/*		} catch (Exception e) {
			e.printStackTrace();
			salidaVO = new SalidaListaFiniquitoVO();
			salidaVO.setCodigoError("1");
			salidaVO.setMensaje(e.getMessage());
		}*/

		return salidaVO;
	}

	private static SalidaListaFiniquitoVO mapearConsultaCreditosARepactarPorRutEnAs400(SalidaListaCreditoARepactarVO entrada) {
		SalidaListaFiniquitoVO salidaVO = new SalidaListaFiniquitoVO();
		List<SalidaFiniquitoVO> listaCreditos = new ArrayList<SalidaFiniquitoVO>();
		SalidaFiniquitoVO credito = null;

		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
			return salidaVO;
		}
		for (SalidaCreditoARepactarVO detalle : entrada.getListaCreditos()) {
			if (detalle.getEstadoCredito() != null && (Integer.parseInt(detalle.getEstadoCredito()) >= 0 && Integer.parseInt(detalle.getEstadoCredito()) <= 6)) {
				credito = new SalidaFiniquitoVO();
				credito.setFolio(detalle.getFolio());
				credito.setCuotasVigentes(detalle.getCuotasVigentes());
				// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////revisar
				
				credito.setGravamenes((Utils.stringToDouble(detalle.getMontoInteresMora()) + Utils.stringToDouble(detalle.getMontoReajustado())));
				credito.setMontoTotal(Utils.stringToDouble(detalle.getMontoRepactacion()));
				credito.setEstadoCredito(detalle.getEstadoCredito());
				listaCreditos.add(credito);
			}
		}
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		salidaVO.setListaCreditos(listaCreditos);

		return salidaVO;
	}
	
	public static Map<String, SalidaFiniquitoVO> obtenerCreditosARepactarAs400Map(String rut) throws Exception {

		EntradaCreditoARepactarVO entradaVO = new EntradaCreditoARepactarVO();
		entradaVO.setRut(rut);

		ClienteCreditoARepactarAs400 clienteAs400 = new ClienteCreditoARepactarAs400();
		Map<String, SalidaFiniquitoVO> map = new LinkedHashMap<String, SalidaFiniquitoVO>();
		//try {
			map = mapearConsultaCreditosARepactarPorRutEnAs400Map((SalidaListaCreditoARepactarVO) clienteAs400.call(entradaVO));
		/*} catch (Exception e) {
			return null;
		}*/

		return map;
	}

	private static Map<String, SalidaFiniquitoVO> mapearConsultaCreditosARepactarPorRutEnAs400Map(SalidaListaCreditoARepactarVO entrada) {
		Map<String, SalidaFiniquitoVO> map = new LinkedHashMap<String, SalidaFiniquitoVO>();
		SalidaFiniquitoVO credito = null;

		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
			return null;
		}
		
		for (SalidaCreditoARepactarVO detalle : entrada.getListaCreditos()) {
			if (detalle.getEstadoCredito() != null && (Integer.parseInt(detalle.getEstadoCredito()) >= 0 && Integer.parseInt(detalle.getEstadoCredito()) <= 6)) {
				credito = new SalidaFiniquitoVO();
				credito.setFolio(detalle.getFolio());
				credito.setCuotasVigentes(detalle.getCuotasVigentes());
				credito.setGravamenes(Utils.stringToDouble(detalle.getMontoInteresMora()) + Utils.stringToDouble(detalle.getMontoReajustado()));
				credito.setMontoTotal(Utils.stringToDouble(detalle.getMontoRepactacion()));
				credito.setEstadoCredito(detalle.getEstadoCredito());
				map.put(detalle.getFolio(), credito);
			}
		}

		return map;
	}

	/**************************************** obtiene el detalle de los creditos por folio As400 
	 * @throws Exception **********************************/

	public static SalidaListaDetalleFiniquitoVO obtenerDetalleCreditosVigentesAs400(String folio) throws Exception {
		EntradaConsultaCreditoPorFolioEnAs400VO entradaVO = new EntradaConsultaCreditoPorFolioEnAs400VO();
		SalidaListaDetalleFiniquitoVO salidaVO = null;
		entradaVO.setFolioCredito(folio);
		entradaVO.setTipoCredito(" ");
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////revisar si se mantiene el tipo de credito
		ClienteConsultaCreditosPorFolioEnAs400 clienteWs = new ClienteConsultaCreditosPorFolioEnAs400();
		//try {
			salidaVO = mapearConsultaCreditosPorFolioEnAs400((SalidaListaConsultaCreditoPorFolioEnAs400VO) clienteWs.call(entradaVO), folio);

			/*if(salidaVO.getCodigoError().equals("0")){
				return salidaVO;
			}*/

		/*} catch (Exception e) {
			salidaVO = new SalidaListaDetalleFiniquitoVO();
			salidaVO.setCodigoError("1");
			salidaVO.setMensaje(e.getMessage());
			e.printStackTrace();
		}*/

		return salidaVO;
	}

	private static SalidaListaDetalleFiniquitoVO mapearConsultaCreditosPorFolioEnAs400(SalidaListaConsultaCreditoPorFolioEnAs400VO entrada, String folio) {
		SalidaListaDetalleFiniquitoVO salidaVO = new SalidaListaDetalleFiniquitoVO();
		SalidaDetalleFiniquitoVO cuotas = null;
		List<SalidaDetalleFiniquitoVO> listaCuotas = new ArrayList<SalidaDetalleFiniquitoVO>();
		
		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
			return salidaVO;
		}

		for (SalidaConsultaCreditoPorFolioEnAs400VO detalle : entrada.getListaCuotas()) {
			cuotas = new SalidaDetalleFiniquitoVO();
			cuotas.setnCuota(detalle.getNumeroCuota());
			cuotas.setMonto(Utils.stringToDouble(detalle.getSaldoCapital()));
			cuotas.setEstadoCuota(detalle.getEstadoCuota()); ///////////////////////////////////////////////////////////////////////////////////// validar información
			cuotas.setMontoGravamen(Utils.stringToDouble(detalle.getMontoGravamen()));
			cuotas.setFolio(folio);
			cuotas.setFechaVencimiento(detalle.getFechaVencimiento());  /////////////////////////////////////////////////////////////////////////estandarizar fecha
			listaCuotas.add(cuotas);
		}
		
		salidaVO.setListaCuotas(listaCuotas);
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		return salidaVO;
	}

}
