package cl.laaraucana.satelites.Utils;

import cl.laaraucana.satelites.dao.ConsultaPrimaDAO;
import cl.laaraucana.satelites.dao.VO.ConsultaPrimaVO;
import cl.laaraucana.satelites.main.VO.RepactarTablaVO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.VO.SalidaCreditoARepactarVO;
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.ClienteConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400;
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.VO.EntradaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.VO.SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.VO.SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;

public class UtilRepactarTabla {

	public static RepactarTablaVO getDesdePrima(SalidaCreditoARepactarVO salidaRepactarVO) {
		RepactarTablaVO repactarVO = new RepactarTablaVO();
		try {

			int cuotasImpagas = Integer.parseInt(salidaRepactarVO.getCuotasImpagas());
			int totalCuotasVigentes = Integer.parseInt(salidaRepactarVO.getCuotasVigentes());
			int plazo =Integer.parseInt(salidaRepactarVO.getPlazo());
			int cuotaDesde = plazo - (totalCuotasVigentes + cuotasImpagas) + 1;
			int cuotaHasta = cuotaDesde + cuotasImpagas;
			double prima = 0;
			if (cuotasImpagas > 0) {
				// llamar al dao
				ConsultaPrimaVO consulta = new ConsultaPrimaVO();
				
				consulta.setCrefol(Integer.parseInt(salidaRepactarVO.getFolio()));
				consulta.setOfipro(Integer.parseInt(salidaRepactarVO.getOficina()));
				consulta.setCuonumDesde(cuotaDesde);
				consulta.setCuonumHasta(cuotaHasta);
				
				prima =  ConsultaPrimaDAO.consultaPrima(consulta);
				System.out.println("===========================================================");
				System.out.println("la primaaaaa esssss "+prima);
				//prima = 2000; // el dao
				
				
				repactarVO.setTotalPrima(prima);
				repactarVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			}
			repactarVO.setCuotaDesde(cuotaDesde);
		} catch (Exception e) {
			repactarVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			repactarVO.setMensaje(e.getMessage());
		}
		return repactarVO;
	}

	public static double getTotalSinMontoAdeudado(RepactarTablaVO repactarTablaVO, SalidaCreditoARepactarVO salidaRepactarVO, String folio) {
		double resultado = 0;
		//positivos
		double comisionPrepago = Utils.stringToDouble(salidaRepactarVO.getComisionPrepago());
//		double gravamenes = Utils.stringToDouble(salidaRepactarVO.getMontoInteresMora()) + Utils.stringToDouble(salidaRepactarVO.getMontoReajustado());
		double gravamenes = getGravamenesCredito(folio);
		double primas = repactarTablaVO.getTotalPrima();
		double totalPositivos = comisionPrepago + gravamenes + primas;
		//negativos
		double montoInteres = Utils.stringToDouble(salidaRepactarVO.getMontoInteres()); 
		double seguroVida = Utils.stringToDouble(salidaRepactarVO.getMontoSeguros()); 
		double totalNegativos = montoInteres + seguroVida;
		
		resultado = totalPositivos - totalNegativos;
		
		return resultado;
	}

	/**Recibe el folio del credito en formato "oficina-folio" ej: 041-000306417, y retorna Double con sumatoria de los gravamenes*/
	public static double getGravamenesCredito(String folio){
		double gravamenes = 0;
		ClienteConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 cliente = new ClienteConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400();
		EntradaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO entradaVO = new EntradaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO();
//		SalidaListaDetalleCreditoVigenteVO salidaVO = new SalidaListaDetalleCreditoVigenteVO();
		
		if(folio.length()== 13){
			System.out.println("el folio es "+folio);
			entradaVO.setFolioCredito(folio);
			entradaVO.setTipoCredito(" ");
			

			try {
				System.out.println("Consulta datos ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 folio: " + entradaVO.getFolioCredito());
				SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO respuesta = (SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO) cliente.call(entradaVO);
				System.out.println("Llamada a servicio datos pagos creditos por folio correcta");
				
				gravamenes = retornaSumaGravamenesPorCuota(respuesta);
				System.out.println("Retorna sumatoria de gravamenes desde retornaSumaGravamenesPorCuota por folio: $"+gravamenes);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error obtener los ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 AS400: " + e.getMessage());
//				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
//				salidaVO.setMensaje(e.getMessage());
			}
			
		}
		return gravamenes;
	}
	
	private static double retornaSumaGravamenesPorCuota(SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO respuesta){
		double sumatoria = 0;
		respuesta.getListaCuotas();
		int contador= 0;
		if(respuesta.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
			
			for (SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO detalle : respuesta.getListaCuotas()) {
//				if(contador==0){
//					sumatoria = Double.valueOf(detalle.getMontoGravamen());
//					contador++;
//				}else{
					sumatoria = sumatoria + Double.valueOf(detalle.getMontoGravamen());
					contador++;
//				}
			}
		}
		System.out.println("se sumaron "+contador+" creditos y la sumatoria de gravamens fue: "+sumatoria);
		return sumatoria;
		
	}
	
	public static double getSaldoCapitalPrepago(double montoAdeudado, SalidaCreditoARepactarVO creditoRepacta) {
		double saldoCapital = 0;
		
		double montoInteres = Utils.stringToDouble(creditoRepacta.getMontoInteres()); 
		double seguroVida = Utils.stringToDouble(creditoRepacta.getMontoSeguros()); 
		saldoCapital = montoAdeudado - montoInteres + seguroVida;
		
		return saldoCapital;
	}
}
