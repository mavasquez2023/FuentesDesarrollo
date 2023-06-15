package cl.laaraucana.satelites.Utils;

import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.ClienteConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400;
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.VO.EntradaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.VO.SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.VO.SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO;

public class UtilCuota {
	
	/** Recibe un folio AS400 ej: "139-000035537" y retorna el valor montoSeguro de una cuota de ese credito.*/
	public static double getSeguroPartidasAbiertas (String folio, String fechaOtorgamiento){
		ClienteConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 cliente = new ClienteConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400();
		EntradaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO entradaVO = new EntradaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO();
		double seguro = 0.0;
		if(folio.length()== 13 && Integer.valueOf(fechaOtorgamiento)>20110930){
			entradaVO.setFolioCredito(folio);
			entradaVO.setTipoCredito(" ");
			
			try {
				System.out.println("Consulta datos ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 folio: " + entradaVO.getFolioCredito());
				SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO respuesta = (SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO) cliente.call(entradaVO);
				System.out.println("Llamada a servicio datos pagos creditos por folio correcta");
				
//				seguro = getSeguro(respuesta);
				for (SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO lista : respuesta.getListaCuotas()) {
					seguro = Double.valueOf(lista.getMontoSeguros());
					break;
				}
				System.out.println("Retorna montoSeguros desde retornaSumaGravamenesPorCuota por folio: $"+seguro);
	
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error obtener los ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 AS400: " + e.getMessage());
			}
	
		}
		return seguro;
	}
	
	@SuppressWarnings("unused")
	private static double getSeguro (SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO respuesta){
		double seguro = 0.0;
//		int i=0;
		for (SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO lista : respuesta.getListaCuotas()) {
			seguro = Double.valueOf(lista.getMontoSeguros());
			break;
		}
		return seguro;
			
	}
	
	
	public static double getMontoCuotaDetalleVigente(SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO detalle, String fechaOtorgamiento) throws Exception{
		String fechaOtorga = Utils.pasaFechaWEBaAs400(fechaOtorgamiento);
		double suma = 0;
		double montoAmortizado = Utils.stringToDouble(detalle.getMontoCapitalAmortizado());
		double montoInteres = Utils.stringToDouble(detalle.getMontoInteres());
		double montoseguro = Utils.stringToDouble(detalle.getMontoSeguros());
		int fecha = Integer.valueOf(fechaOtorga);
		if(fecha<=20110930){
			suma=montoAmortizado + montoInteres ;
		}else{
			suma=montoAmortizado + montoInteres+ montoseguro;
		}
		return suma;
	}

}
