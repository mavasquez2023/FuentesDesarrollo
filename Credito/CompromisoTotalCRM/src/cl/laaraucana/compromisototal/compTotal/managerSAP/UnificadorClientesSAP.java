package cl.laaraucana.compromisototal.compTotal.managerSAP;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import cl.laaraucana.compromisototal.VO.ContratoVO;
import cl.laaraucana.compromisototal.utils.Codigo;
import cl.laaraucana.compromisototal.utils.Utils;
import cl.laaraucana.compromisototal.webservice.client.QueryBPStatusOUT.ClienteQueryBPStatusOUT;
import cl.laaraucana.compromisototal.webservice.client.QueryBPStatusOUT.VO.EntradaAfiliadoVO;
import cl.laaraucana.compromisototal.webservice.client.QueryBPStatusOUT.VO.SalidaAfiliadoVO;
import cl.laaraucana.compromisototal.webservice.client.QueryBPStatusOUT.VO.SalidaListaAfiliadoVO;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader.ClienteCompContHeader;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader.VO.EntradaCompContHeaderVO;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader.VO.SalidaCompContHeaderVO;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader.VO.SalidaListaCompContHeaderVO;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader2.ClienteCompContHeader2;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader2.VO.EntradaCompContHeader2VO;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader2.VO.SalidaCompContHeader2VO;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader2.VO.SalidaListaCompContHeader2VO;

import cl.laaraucana.compromisototal.webservice.client.queryContractHeader.ClienteContractHeader;
import cl.laaraucana.compromisototal.webservice.client.queryContractHeader.VO.EntradaContractHeaderVO;
import cl.laaraucana.compromisototal.webservice.client.queryContractHeader.VO.SalidaContractHeaderVO;

public class UnificadorClientesSAP {

	protected Logger logger = Logger.getLogger(this.getClass());

	public SalidaUnida llamaServiciosBS(String rut) throws Exception {

		SalidaUnida salidaUnida = new SalidaUnida();

		// llama al servicio QueryCompContHeader

		EntradaCompContHeaderVO entradaBs1 = new EntradaCompContHeaderVO();
		entradaBs1.setRut(rut);
		entradaBs1.setCreditStatus("1");
		
		ClienteCompContHeader clienteCompCont1 = new ClienteCompContHeader();
		SalidaListaCompContHeaderVO salida1 = (SalidaListaCompContHeaderVO) clienteCompCont1.call(entradaBs1);
		
		
		EntradaCompContHeader2VO entradaBs2 = new EntradaCompContHeader2VO();
		entradaBs2.setRut(rut);
		entradaBs2.setCreditStatus("1");
		
		ClienteCompContHeader2 clienteCompCont2 = new ClienteCompContHeader2();
		SalidaListaCompContHeader2VO salida2 = (SalidaListaCompContHeader2VO) clienteCompCont2.call(entradaBs2);
		
		
		
		logger.debug("codigo de error SalidaListaCompContHeaderVO" + salida1.getCodigoError());
		logger.debug("codigo de error SalidaListaCompContHeader2VO" + salida2.getCodigoError());

		salidaUnida.setCodigoError(salida1.getCodigoError());

		if (!salida1.getCodigoError().equals(Codigo.OK)) {
			salidaUnida.setMensaje(salida1.getMensaje());
			
		}else{
			ArrayList<SalidaCompContHeaderVO> listaBanking = new ArrayList<SalidaCompContHeaderVO>();
			
			//cambiar por la union
			listaBanking = getListaBankingTotal(salida1.getDetalle(), salida2.getDetalle());
			//listaBanking = salida.getDetalle();
			
			logger.debug("mensaje desde SalidaListaCompContHeaderVO" + salida1.getMensaje());
			logger.debug("mensaje desde SalidaListaCompContHeader2VO" + salida2.getMensaje());

			EntradaAfiliadoVO entradaAfiliado = new EntradaAfiliadoVO();
			entradaAfiliado.setRut(rut);
			ArrayList<SalidaAfiliadoVO> listaAfiliado = new ArrayList<SalidaAfiliadoVO>();
			ClienteQueryBPStatusOUT clienteBPStatus = new ClienteQueryBPStatusOUT();

			SalidaListaAfiliadoVO salidaBPStatus = (SalidaListaAfiliadoVO) clienteBPStatus.call(entradaAfiliado);

//			if (!salidaBPStatus.getCodigoError().equals(Codigo.OK)) {
//				salidaUnida.setCodigoError("error");
//				salidaUnida.setMensaje(salidaBPStatus.getMensaje());
//			} else {

				listaAfiliado = salidaBPStatus.getListaAfiliado();
				ArrayList<ContratoVO> listaBS = new ArrayList<ContratoVO>();
				for (SalidaCompContHeaderVO salidaBankingVO : listaBanking) {

					EntradaContractHeaderVO entradaContract = new EntradaContractHeaderVO();
					entradaContract.setAcNUM_EXT(salidaBankingVO.getContractNumber());
					entradaContract.setRut(rut);
					ClienteContractHeader clienteContract = new ClienteContractHeader();
					SalidaContractHeaderVO salidaContract = (SalidaContractHeaderVO) clienteContract.call(entradaContract);
					logger.debug("codigo error 	SalidaContractHeaderVO:" + salidaContract.getCodigoError());

					if (!salidaContract.getCodigoError().equals(Codigo.OK)) {
						salidaUnida.setCodigoError(Codigo.ERROR);
						salidaUnida.setMensaje(salidaContract.getMensaje());
					} else {
						if(salidaContract.getStatus().equals("1") && salidaBankingVO.getCastigo()!=null && salidaBankingVO.getCastigo().equals("X")){
							salidaContract.setStatus("9");
						}
						listaBS.add(new ContratoVO("B", // Origen B = Banking
								Utils.formateaFolio(salidaBankingVO.getContractNumber()),// idContrato
								salidaContract.getComercialLine(),// lineaComercial
								Utils.stringToDouble(salidaContract.getMonthlyInterestRate()),// tasaInteres
								Utils.stringToDouble(salidaContract.getRemainingBalance()),// montoAdeudado
								Utils.stringToDouble(salidaBankingVO.getInstallmentAmount()),// montoCuota,
								salidaContract.getStatus(),// estadoCredito
								Utils.formateaRepactaSAP(salidaBankingVO.getRepacta()),// repactacion
								Utils.formateaReprogramacSAP(salidaBankingVO.getReprogramac()),// reprogramacion
								salidaBankingVO.getRole(),// titular
								Utils.formateaRolPagadorSAP(salidaBankingVO.getPayer().trim()),// rolPagador
								Utils.stringToDouble(salidaBankingVO.getContractAmount()),// montoSolicitado
								this.obtenerTipoAfiliado(listaAfiliado, salidaContract.getCompanyRUT()),// tipoAfiliado
								salidaBankingVO.getInstallmentQuantity(),// plazo
								Utils.stringToDateSAP(salidaBankingVO.getContractBegin()),// fechaOtorgamiento
								salidaBankingVO.getContractNumber(),
								salidaBankingVO.getBpAnexo(),
								salidaBankingVO.getRutEmpresa(),
								salidaBankingVO.getNroInscripcion()
								
								));

					}

				}

				salidaUnida.setListaBS(listaBS);
//			}
		}

		logger.debug("retorna salidaUnida");
		return salidaUnida;
	}

	public String obtenerTipoAfiliado(ArrayList<SalidaAfiliadoVO> lista, String rutEmpresa) throws Exception {

		logger.debug("entra a obtener tipo afiliado desde el resultado de BPStatus y rutEmpresa");
		String tipoAfiliado = "";
		if (lista != null && rutEmpresa != null) {
			for (SalidaAfiliadoVO salidaAfiliadoVO : lista) {
				if (salidaAfiliadoVO.getRut().equals(rutEmpresa)) {
					tipoAfiliado = salidaAfiliadoVO.getRol();
					break;
				}
			}
		}

		return tipoAfiliado;
	}
	
	
	public ArrayList<SalidaCompContHeaderVO> getListaBankingTotal(ArrayList<SalidaCompContHeaderVO> listaBanking1, ArrayList<SalidaCompContHeader2VO> listaBanking2){
		
		ArrayList<SalidaCompContHeaderVO> salidaListaBanking = new ArrayList<SalidaCompContHeaderVO>();
		
		for (SalidaCompContHeaderVO salidaCompContHeaderVO : listaBanking1) {
			
			for (SalidaCompContHeader2VO salidaCompContHeader2VO : listaBanking2) {
				if(salidaCompContHeader2VO.getContractNumber().equalsIgnoreCase(salidaCompContHeaderVO.getContractNumber())){
					salidaCompContHeaderVO.setBpAnexo(salidaCompContHeader2VO.getBpAnexo());
					salidaCompContHeaderVO.setRutEmpresa(salidaCompContHeader2VO.getRutEmpresa());
					salidaCompContHeaderVO.setNroInscripcion(salidaCompContHeader2VO.getNroInscripcion());
				}
			}
			
			salidaListaBanking.add(salidaCompContHeaderVO);
		}
		
		
		return salidaListaBanking;
		
	}
	
	
	

}
