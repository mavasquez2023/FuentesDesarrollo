package cl.laaraucana.satelites.certificados.creditovigente;


import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaListaCreditoVigenteVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.ClienteQueryCompContHeaderIn;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.EntradaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaListaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;

public class ServicioCreditosVigentes {

	protected static Logger logger = Logger.getLogger(ServicioCreditosVigentes.class);

	/**
	 * Llamada a servicio de consulta de créditos SAP banking, específicamente
	 * para créditos vigentes
	 * 
	 * @param rut
	 * @return
	 * @throws Exception Si la llamada al servicio o el mapeo no fue exitoso
	 */
	public static SalidaListaCreditoVigenteVO obtenerCreditosVigentesBanking(String rut, boolean castigado) {
		EntradaQueryCompContHeaderInVO entradaVO = new EntradaQueryCompContHeaderInVO();
		entradaVO.setRut(rut);
		entradaVO.setFlagTipoCredito("1");//1: obtiene creditos vigentes.

		ClienteQueryCompContHeaderIn clienteBanking = new ClienteQueryCompContHeaderIn();
		SalidaListaCreditoVigenteVO salidaVO = new SalidaListaCreditoVigenteVO();

		try {
			SalidaListaQueryCompContHeaderInVO respuesta = (SalidaListaQueryCompContHeaderInVO) clienteBanking.call(entradaVO, castigado);
			logger.debug("Créditos vigentes: Llamada a banking exitosa.");
			salidaVO = mapearQueryCompContHeaderIn(respuesta);
			logger.debug("Créditos vigentes: Mapeo de datos exitoso.");

		} catch (Exception e) {
			e.printStackTrace();
			//Setea codigo de error salida
			logger.debug("Error al obtener los datos de SAP: " + e.getMessage());
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error: " + e.getMessage());
		}

		return salidaVO;
	}

	/**
	 * @param entrada
	 * @return
	 * @throws Exception si no se pudo realizar correctamente el mapeo de datos
	 */
	private static SalidaListaCreditoVigenteVO mapearQueryCompContHeaderIn(SalidaListaQueryCompContHeaderInVO entrada) throws Exception {
		SalidaListaCreditoVigenteVO salidaVO = new SalidaListaCreditoVigenteVO();
		List<SalidaCreditoVigenteVO> listaCreditos = new ArrayList<SalidaCreditoVigenteVO>();
		SalidaCreditoVigenteVO credito = null;

		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		if (entrada.getListaCreditos() == null) {
			return salidaVO;
		}

		for (SalidaQueryCompContHeaderInVO detalle : entrada.getListaCreditos()) {
			credito = new SalidaCreditoVigenteVO();

			//credito.setFolio(Utils.formatearFolio(detalle.getContractNumber()));
			credito.setFolio(detalle.getContractNumber());
			credito.setMontoSolicitado(Utils.formateaMontoSAPDouble(detalle.getContractAmount()));
			credito.setFechaOtorgamiento(Utils.pasaFechaSAPaWEB(detalle.getContractBegin()));
			credito.setMontoCuota(Utils.formateaMontoSAPDouble(detalle.getInstallmentAmount()));
			credito.setPlazo(detalle.getInstallmentQuantity());
			credito.setTipoMoneda(detalle.getContractCurrency());//UF o CLP
			credito.setFlagTipoCredito("1");//1: indica que es credito de banking
			String insolvencia= detalle.getInsolvencia();
			credito.setInsolvencia(insolvencia==null?"":insolvencia);
			String desgravamen= detalle.getSeg_Desgravamen();
			credito.setDesgravamen(desgravamen==null?"":desgravamen);
			String cesantia= detalle.getSeg_Cesantia();
			credito.setCesantia(cesantia==null?"":cesantia);
			if (detalle.getRole() == null) {
				credito.setRolAsociado(detalle.getRole());
			}else if (detalle.getRole().trim().equals("BCA010")) {
				credito.setRolAsociado("Titular");
			} else if (detalle.getRole().trim().equals("ZAVAL1")) {
				credito.setRolAsociado("Aval 1");
			} else if (detalle.getRole().trim().equals("ZAVAL2")) {
				credito.setRolAsociado("Aval 2");
			}
			

			listaCreditos.add(credito);
		}

		salidaVO.setListaCreditos(listaCreditos);
		return salidaVO;
	}
	
	
}
