package cl.laaraucana.botonpago.web.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cl.laaraucana.botonpago.web.cobol.vo.SalidaDeudorVO;
import cl.laaraucana.botonpago.web.database.dao.DatosDeudorDAO;
import cl.laaraucana.botonpago.web.utils.Calculo;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.Util;
import cl.laaraucana.botonpago.web.vo.CreditoVO;
import cl.laaraucana.botonpago.web.vo.CuotasVO;
import cl.laaraucana.botonpago.web.vo.SalidaCreditosVO;
import cl.laaraucana.botonpago.web.vo.SalidaCuotasVO;
import cl.laaraucana.botonpago.web.vo.SalidaDeudor;
import cl.laaraucana.botonpago.web.webservices.clientes.querybpstatus.ClienteQueryBPStatus;
import cl.laaraucana.botonpago.web.webservices.clientes.querybpstatus.vo.EntradaQueryBPVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querybpstatus.vo.SalidaListaQueryBPVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querybpstatus.vo.SalidaQueryBPVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycompcontheader.ClienteQueryCompContHeader;
import cl.laaraucana.botonpago.web.webservices.clientes.querycompcontheader.vo.EntradaCompContHeaderVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycompcontheader.vo.SalidaCompContHeaderVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycompcontheader.vo.SalidaListaCompContHeaderVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycontractcashflow.ClienteQueryContractCashFlow;
import cl.laaraucana.botonpago.web.webservices.clientes.querycontractcashflow.vo.EntradaQueryContractCashFlowVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycontractcashflow.vo.SalidaListaQueryContractCashFlowVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycontractcashflow.vo.SalidaQueryContractCashFlowVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycontractheader.ClienteQueryContractHeader;
import cl.laaraucana.botonpago.web.webservices.clientes.querycontractheader.vo.EntradaContractHeaderVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycontractheader.vo.SalidaContractHeaderVO;

public class ManagerSAP {

	public void obtenerInfoUsuario(String rut) {

	}
	
	public SalidaCreditosVO obtenerCreditosAdm(String rut) throws Exception {

		SalidaCreditosVO salidaCreditos = new SalidaCreditosVO();
		EntradaCompContHeaderVO entrada = new EntradaCompContHeaderVO();
		entrada.setRut(rut);
		entrada.setFlagTipoCredito("1");
		ClienteQueryCompContHeader clienteCompCont = new ClienteQueryCompContHeader();
		SalidaListaCompContHeaderVO salida = (SalidaListaCompContHeaderVO) clienteCompCont.call(entrada);
		
		
		ArrayList<SalidaCompContHeaderVO> listaBanking = new ArrayList<SalidaCompContHeaderVO>();
		listaBanking = (ArrayList<SalidaCompContHeaderVO>) salida.getListaCreditos();

		ArrayList<CreditoVO> listaBS = new ArrayList<CreditoVO>();
		for (SalidaCompContHeaderVO salidaQueryCompCont : listaBanking) {
			CreditoVO credito = new CreditoVO();
			credito.setOperacion(salidaQueryCompCont.getContractNumber());
			credito.setCuotasTotales(salidaQueryCompCont.getInstallmentQuantity());
			credito.setTotalDeuda(salidaQueryCompCont.getContractAmount());
			listaBS.add(credito);
		}
		salidaCreditos.setListaCreditos(listaBS);
		return salidaCreditos;
	}
	
	public SalidaCreditosVO obtenerCreditos(String rut) throws Exception {

		SalidaCreditosVO salidaCreditos = new SalidaCreditosVO();
		EntradaCompContHeaderVO entrada = new EntradaCompContHeaderVO();
		entrada.setRut(rut);
		entrada.setFlagTipoCredito("1");
		ClienteQueryCompContHeader clienteCompCont = new ClienteQueryCompContHeader();
		SalidaListaCompContHeaderVO salida = (SalidaListaCompContHeaderVO) clienteCompCont.call(entrada);
		salidaCreditos.setCodError(salida.getCodigoError());

		if (!salida.getCodigoError().equals(Constantes.getInstancia().APP_COD_SUCCESS)) {
			salidaCreditos.setMensaje(salida.getMensaje());
			return salidaCreditos;
		}
		ArrayList<SalidaCompContHeaderVO> listaBanking = new ArrayList<SalidaCompContHeaderVO>();
		listaBanking = (ArrayList<SalidaCompContHeaderVO>) salida.getListaCreditos();

		ArrayList<CreditoVO> listaBS = new ArrayList<CreditoVO>();
		for (SalidaCompContHeaderVO salidaQueryCompCont : listaBanking) {

			EntradaContractHeaderVO entradaContract = new EntradaContractHeaderVO();
			entradaContract.setAcnum_ext(salidaQueryCompCont.getContractNumber());
			entradaContract.setRut(rut);
			ClienteQueryContractHeader clienteContract = new ClienteQueryContractHeader();
			SalidaContractHeaderVO salidaContract = (SalidaContractHeaderVO) clienteContract.call(entradaContract);

			if (!salidaContract.getCodigoError().equals(Constantes.getInstancia().APP_COD_SUCCESS)) {
				salidaCreditos.setCodError(Constantes.getInstancia().APP_COD_ERROR);
				salidaCreditos.setMensaje(salidaContract.getMensaje());
			} else {

				EntradaQueryContractCashFlowVO entradaCashFlow = new EntradaQueryContractCashFlowVO();
				entradaCashFlow.setFolioCredito(salidaQueryCompCont.getContractNumber());
				entradaCashFlow.setIncluyeEPO("X");
				ClienteQueryContractCashFlow clienteCashFlow = new ClienteQueryContractCashFlow();
				SalidaListaQueryContractCashFlowVO salidacash = (SalidaListaQueryContractCashFlowVO) clienteCashFlow.call(entradaCashFlow);

				if (!salidacash.getCodigoError().equals(Constantes.getInstancia().WS_COD_SUCCESS)) {
					salidaCreditos.setCodError(Constantes.getInstancia().APP_COD_ERROR);
					salidaCreditos.setMensaje(salidacash.getMensaje());
				} else {
					List<SalidaQueryContractCashFlowVO> cuotas = salidacash.getListaCuotas();
					int cuotasCanceladas = 0;
					int totalDeuda = 0;
					int pagoMinimo = 0;
					
					DatosDeudorDAO dao = new DatosDeudorDAO();
					String fecha_pago_futuro= dao.getFechaPagoFuturo(salidaQueryCompCont.getContractNumber());
					if(fecha_pago_futuro==null){
						fecha_pago_futuro= Util.getFechaAs400();
					}
					for (SalidaQueryContractCashFlowVO cuota : cuotas) {

						if (cuota.getEstadoCuota().trim().equalsIgnoreCase("moroso")) {
							// se suman las cuotas morosas al totalDeuda
							// fecha actual es menor a la fecha de vencimiento
							totalDeuda += Integer.parseInt(Calculo.getCuota(cuota.getMontoCapital(), cuota.getMontoInteres(), cuota.getMontoServAdic(), cuota.getMontoAbono(), cuota
									.getMontoGravamenes()));

						}
						if (cuota.getEstadoCuota().trim().equalsIgnoreCase("vigente") && Util.isFechaMenorA(cuota.getFechaVencCuota(), fecha_pago_futuro)) {
							// se suma la primera cuota vigente al totalDeuda
							totalDeuda += Integer.parseInt(Calculo.getCuota(cuota.getMontoCapital(), cuota.getMontoInteres(), cuota.getMontoServAdic(), cuota.getMontoAbono(), cuota
									.getMontoGravamenes()));
						}

						if (cuota.getEstadoCuota().trim().toLowerCase().equals("cancelada")) {
							cuotasCanceladas++;
						}
						
						if(cuota.getNroCuota().equals("999") && !cuota.getEstadoCuota().toLowerCase().equals("cancelada")){
							totalDeuda= Integer.parseInt(Calculo.getCuotaEPO(cuota.getTotalCuota(), cuota.getMontoAbono()));
							cuotasCanceladas=0;
							break;
						}
					}
					//clillo 25/07/2017 se deja en cero gravamenes
					//int sumaGravamen = Integer.parseInt(salidaContract.getArrearsAmount());
					int sumaGravamen = 0;
					int gastosCobranza = Integer.parseInt(salidaContract.getUnpaidcharge());
					int sumaGravamenCond = Calculo.getCondGravamen(sumaGravamen);
					Map<String, Integer> map = Calculo.getCondGCob(gastosCobranza, salidaQueryCompCont.getContractBegin());
					int gastosCobranzaCond = map.get("gcob");
					int porcentGCob = map.get("porcent");
					gastosCobranza = Integer.parseInt(salidaContract.getUnpaidcharge());
					// se suman los gasts de cobranza al totalDeuda
					totalDeuda += gastosCobranzaCond;
					
					int montoCuota=Integer.parseInt(salidaQueryCompCont.getInstallmentAmount());
					pagoMinimo = Calculo.getPagoMinimo(montoCuota, (sumaGravamenCond + gastosCobranzaCond));
 
					CreditoVO credito = new CreditoVO();
					credito.setOrigen(Constantes.getInstancia().ORIGEN_SAP);
					credito.setProducto(Util.replace(salidaContract.getComercialLine(), Constantes.getInstancia().BUNDLE_LINEACOMERCIAL_SAP));
					credito.setOperacion(salidaQueryCompCont.getContractNumber());
					credito.setFechaColocacion(salidaQueryCompCont.getContractBegin());
					credito.setCuotasMorosas(salidaContract.getQuantityUnpaidinst());
					if(cuotasCanceladas==0){
						credito.setCuotasPagadas("-");
						credito.setCuotasTotales("-");
					}else{
						credito.setCuotasPagadas(String.valueOf(cuotasCanceladas));
						credito.setCuotasTotales(salidaQueryCompCont.getInstallmentQuantity());
					}
					credito.setTotalDeuda(String.valueOf(totalDeuda));
					credito.setTotalPagar(String.valueOf(totalDeuda));
					credito.setGastosCobranza(String.valueOf(gastosCobranza));
					credito.setSumaGravamenes(String.valueOf(sumaGravamen));
					credito.setGastoCobranzaConCond(String.valueOf(gastosCobranzaCond));
					credito.setSumaGravamenConCond(String.valueOf(sumaGravamenCond));
					credito.setPorcentCondGravamen(String.valueOf(Constantes.getInstancia().CONDONACION_GRAVAMENES.get(0).getPorcen()));
					credito.setPorcentCondGastoCob(String.valueOf(porcentGCob));
					credito.setMontoCuota(salidaQueryCompCont.getInstallmentAmount());
					credito.setPagoMinimo(String.valueOf(pagoMinimo));

					listaBS.add(credito);

				}
			}

			salidaCreditos.setListaCreditos(listaBS);
		}
		return salidaCreditos;
	}

	public SalidaCuotasVO obtenerCuotas(String folio) throws Exception {

		SalidaCuotasVO salidaCuotas = new SalidaCuotasVO();

		EntradaQueryContractCashFlowVO entrada = new EntradaQueryContractCashFlowVO();
		entrada.setFolioCredito(folio);
		entrada.setIncluyeEPO("X");
		ClienteQueryContractCashFlow clienteCashFlow = new ClienteQueryContractCashFlow();
		SalidaListaQueryContractCashFlowVO salidacash = (SalidaListaQueryContractCashFlowVO) clienteCashFlow.call(entrada);
		salidaCuotas.setCodError(salidacash.getCodigoError());

		if (!salidaCuotas.getCodError().equals(Constantes.getInstancia().WS_COD_SUCCESS)) {
			salidaCuotas.setMensaje(salidacash.getMensaje());
		} else {
			ArrayList<CuotasVO> listacuotas = new ArrayList<CuotasVO>();
			ArrayList<SalidaQueryContractCashFlowVO> listacash = (ArrayList<SalidaQueryContractCashFlowVO>) salidacash.getListaCuotas();
			
			DatosDeudorDAO dao = new DatosDeudorDAO();
			String fecha_pago_futuro= dao.getFechaPagoFuturo(folio);
			if(fecha_pago_futuro==null){
				fecha_pago_futuro= Util.getFechaAs400();
			}
			for (SalidaQueryContractCashFlowVO salidaCashFlowVO : listacash) {

				if ((salidaCashFlowVO.getEstadoCuota().trim().equalsIgnoreCase("vigente")&& Util.isFechaMenorA(salidaCashFlowVO.getFechaVencCuota(), fecha_pago_futuro)  || salidaCashFlowVO.getEstadoCuota().trim().equalsIgnoreCase("moroso"))) {
					CuotasVO cuota = new CuotasVO();
					cuota.setNcuota(salidaCashFlowVO.getNroCuota());
					cuota.setFechaVencimiento(salidaCashFlowVO.getFechaVencCuota());
					cuota.setEstadoCuota(salidaCashFlowVO.getEstadoCuota());
					cuota.setMontoCapital(salidaCashFlowVO.getMontoCapital());
					cuota.setMontoSeguro(salidaCashFlowVO.getMontoServAdic());
					cuota.setMontoIntereses(salidaCashFlowVO.getMontoInteres());
//					cuota.setMontoDeuda(salidaCashFlowVO.getTotalCuota());
					cuota.setMontoAbono(salidaCashFlowVO.getMontoAbono());
					cuota.setMontoDeuda(Calculo.getCuota(salidaCashFlowVO.getMontoCapital(), salidaCashFlowVO.getMontoInteres(), salidaCashFlowVO.getMontoServAdic(), salidaCashFlowVO.getMontoAbono(), salidaCashFlowVO.getMontoGravamenes()));

					listacuotas.add(cuota);
				}
				if(salidaCashFlowVO.getNroCuota().equals("999") && !salidaCashFlowVO.getEstadoCuota().toLowerCase().equals("cancelada")){
					CuotasVO cuota = new CuotasVO();
					cuota.setNcuota(salidaCashFlowVO.getNroCuota());
					cuota.setFechaVencimiento(salidaCashFlowVO.getFechaVencCuota());
					cuota.setEstadoCuota(salidaCashFlowVO.getEstadoCuota());
					cuota.setMontoCapital(salidaCashFlowVO.getMontoCapital());
					cuota.setMontoSeguro(salidaCashFlowVO.getMontoServAdic());
					cuota.setMontoIntereses(salidaCashFlowVO.getMontoInteres());
//					cuota.setMontoDeuda(salidaCashFlowVO.getTotalCuota());
					cuota.setMontoAbono(salidaCashFlowVO.getMontoAbono());
					cuota.setMontoDeuda(Calculo.getCuotaEPO(salidaCashFlowVO.getTotalCuota(), salidaCashFlowVO.getMontoAbono()));
					listacuotas = new ArrayList<CuotasVO>();
					listacuotas.add(cuota);
					break;
				}
			}
			salidaCuotas.setListaCuotas(listacuotas);
		}
		return salidaCuotas;

	}

	public SalidaDeudorVO esDeudorSapYpStatus(String rut) throws Exception {
		SalidaDeudorVO salidaDeudor = new SalidaDeudorVO();
		boolean esAfiliado = false;
		/*				ClienteQueryBPStatus clienteBpStatus = new ClienteQueryBPStatus();
		EntradaQueryBPVO entradaBpStatus = new EntradaQueryBPVO();
		entradaBpStatus.setRutCliente(rut);
		SalidaListaQueryBPVO salidaBpStatusVO = (SalidaListaQueryBPVO) clienteBpStatus.call(entradaBpStatus);

		if (Constantes.getInstancia().APP_COD_SUCCESS.equals(salidaBpStatusVO.getCodigoError())) {
			SalidaQueryBPVO salidaBP = isAfiliado(salidaBpStatusVO);
			//salidaDeudor.setAppDeu(salidaBP.getNombreCompleto());
			splitNombres(salidaDeudor, salidaBP.getNombreCompleto());
			
			//salidaDeudor.setNombreDeudor(salidaBP.getNombreCompleto());
			salidaDeudor.setRutDeudor(rut);
		} else {
			salidaDeudor.setCodSalida(salidaBpStatusVO.getCodigoError());
			salidaDeudor.setMsjSalida(salidaBpStatusVO.getMensaje());
			return salidaDeudor;
		}
*/
	//	if (!esAfiliado) {
			EntradaCompContHeaderVO entrada = new EntradaCompContHeaderVO();
			entrada.setRut(rut);
			// DUMMY dejar constante
			entrada.setFlagTipoCredito("1");
			ClienteQueryCompContHeader clienteCompCont = new ClienteQueryCompContHeader();
			SalidaListaCompContHeaderVO salidaCompCont = (SalidaListaCompContHeaderVO) clienteCompCont.call(entrada);
			if (Constantes.getInstancia().APP_COD_SUCCESS.equals(salidaCompCont.getCodigoError())) {
				if (salidaCompCont.getListaCreditos().size() > 0) {
					salidaDeudor.setCodSalida(Constantes.getInstancia().APP_COD_SUCCESS);
					salidaDeudor.setDeudor(true);
				}
			} else {
				salidaDeudor.setCodSalida(salidaCompCont.getCodigoError());
				salidaDeudor.setMsjSalida(salidaCompCont.getMensaje());
				return salidaDeudor;
			}
		/*} else {
			salidaDeudor.setCodError(Constantes.getInstancia().APP_COD_SUCCESS);
			salidaDeudor.setDeudor(false);
			salidaDeudor.setMensaje("usuario no deudor");
		}*/
		return salidaDeudor;

	}
	
	// DUMMY
	private SalidaQueryBPVO isAfiliado(SalidaListaQueryBPVO entradaBpStatusVO) {
		SalidaQueryBPVO salida=null;
		ArrayList<SalidaQueryBPVO> listadoBp = new ArrayList<SalidaQueryBPVO>();
		listadoBp = entradaBpStatusVO.getLista();
		for (SalidaQueryBPVO salidaQueryBPVO : listadoBp) {
			// DUMMY cambiar el codigo
			if ("ACTIVO".equals(salidaQueryBPVO.getEstadoAfiliacion())) {
				return salidaQueryBPVO;
			}
			salida= salidaQueryBPVO;
		}

		return salida;
	}

	public SalidaDeudor tieneDeuda(String rut) throws Exception {
		SalidaDeudor salidaDeudor = new SalidaDeudor();

		EntradaCompContHeaderVO entrada = new EntradaCompContHeaderVO();
		entrada.setRut(rut);
		// DUMMY dejar constante
		entrada.setFlagTipoCredito("1");
		ClienteQueryCompContHeader clienteCompCont = new ClienteQueryCompContHeader();
		SalidaListaCompContHeaderVO salidaCompCont = (SalidaListaCompContHeaderVO) clienteCompCont.call(entrada);

		if (Constantes.getInstancia().APP_COD_SUCCESS.equals(salidaCompCont.getCodigoError())) {
			if (salidaCompCont.getListaCreditos().size() > 0) {
				salidaDeudor.setCodError(Constantes.getInstancia().APP_COD_SUCCESS);
				salidaDeudor.setDeudor(true);
			}
		} else {
			salidaDeudor.setCodError(salidaCompCont.getCodigoError());
			salidaDeudor.setMensaje(salidaCompCont.getMensaje());
			return salidaDeudor;
		}
		return salidaDeudor;

	}
	
	public void splitNombres(SalidaDeudorVO salidaDeudor, String nombreCompleto){
		String[] nomfull= nombreCompleto.split(" ");
		switch (nomfull.length) {
		case 1:
			salidaDeudor.setNombreDeudor(nomfull[0]);
			break;
		case 2:
			salidaDeudor.setNombreDeudor(nomfull[0]);
			salidaDeudor.setAppDeu(nomfull[1]);
			break;
		case 3:
			salidaDeudor.setNombreDeudor(nomfull[0]);
			salidaDeudor.setAppDeu(nomfull[1]);
			salidaDeudor.setApmDeu(nomfull[2]);
			break;
		case 4:
			salidaDeudor.setNombreDeudor(nomfull[0] + " " + nomfull[1]);
			salidaDeudor.setAppDeu(nomfull[2]);
			salidaDeudor.setApmDeu(nomfull[3]);
			break;
		case 5:
			salidaDeudor.setNombreDeudor(nomfull[0] + " " + nomfull[1]);
			salidaDeudor.setAppDeu(nomfull[2] + " " + nomfull[3]);
			salidaDeudor.setApmDeu(nomfull[4]);
			break;
		case 6:
			salidaDeudor.setNombreDeudor(nomfull[0] + " " + nomfull[1]);
			salidaDeudor.setAppDeu(nomfull[2] + " " + nomfull[3] + " " + nomfull[4]);
			salidaDeudor.setApmDeu(nomfull[5]);
			break;
		case 7:
			salidaDeudor.setNombreDeudor(nomfull[0] + " " + nomfull[1] + " " + nomfull[2]);
			salidaDeudor.setAppDeu(nomfull[3] + " " + nomfull[4] + " " + nomfull[5]);
			salidaDeudor.setApmDeu(nomfull[6]);
			break;
		case 8:
			salidaDeudor.setNombreDeudor(nomfull[0] + " " + nomfull[1] + " " + nomfull[2]);
			salidaDeudor.setAppDeu(nomfull[3] + " " + nomfull[4] + " " + nomfull[5]);
			salidaDeudor.setApmDeu(nomfull[6] + " " + nomfull[7]);
			break;
		default:
			salidaDeudor.setNombreDeudor(nombreCompleto);
			salidaDeudor.setAppDeu(".");
			salidaDeudor.setApmDeu(".");
			break;
		}
	}

}
