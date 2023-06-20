package cl.laaraucana.mandatocesantia.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.mandatocesantia.util.Configuraciones;
import cl.laaraucana.mandatocesantia.ws.ClienteQueryCompContHeaderIn;
import cl.laaraucana.mandatocesantia.ws.ClienteQueryContractHeaderIn;
import cl.laaraucana.mandatocesantia.ws.VO.EntradaQueryCompContHeaderInVO;
import cl.laaraucana.mandatocesantia.ws.VO.EntradaQueryContractHeaderInVO;
import cl.laaraucana.mandatocesantia.ws.VO.SalidaListaQueryCompContHeaderInVO;
import cl.laaraucana.mandatocesantia.ws.VO.SalidaQueryCompContHeaderInVO;
import cl.laaraucana.mandatocesantia.ws.VO.SalidaQueryContractHeaderInVO;

@Service
public class CreditoServiceImpl implements CreditoService {

	@Override
	public boolean findCreditoCesantiaByRut(String rut) throws Exception {
		boolean respuesta=false;
		//Llamada a Query Compact Contract Header
		ClienteQueryCompContHeaderIn clienteBanking= new ClienteQueryCompContHeaderIn();
		EntradaQueryCompContHeaderInVO entradaVO= new EntradaQueryCompContHeaderInVO();
		entradaVO.setRut(rut);
		entradaVO.setFlagTipoCredito(Configuraciones.getConfig("sap.obtencioncredito.condeuda"));
		SalidaListaQueryCompContHeaderInVO salidaVO = (SalidaListaQueryCompContHeaderInVO)clienteBanking.call(entradaVO);
		
		
		//Llamada a Query Compact Contract Header
		List<SalidaQueryCompContHeaderInVO> listaContratos= 
				salidaVO.getListaCreditos();
		for (Iterator iterator = listaContratos.iterator(); iterator.hasNext();) {
			SalidaQueryCompContHeaderInVO salidaQueryCompContHeaderInVO = (SalidaQueryCompContHeaderInVO) iterator
					.next();
			ClienteQueryContractHeaderIn clientePrepago= new ClienteQueryContractHeaderIn();
			EntradaQueryContractHeaderInVO entrada= new EntradaQueryContractHeaderInVO();
			entrada.setRut(rut);
			entrada.setAcnum_ext(salidaQueryCompContHeaderInVO.getContractNumber());
			SalidaQueryContractHeaderInVO salida= (SalidaQueryContractHeaderInVO)clientePrepago.call(entrada);
			if(salida.getUnemploymentinsur()!=null){
				String monto_cesantia=salida.getUnemploymentinsur().trim().replaceAll("\\.", "");
				if( Integer.parseInt(monto_cesantia)>0){
					respuesta= true;
					break;
				}
			}
		}
		return respuesta;
	}


}
