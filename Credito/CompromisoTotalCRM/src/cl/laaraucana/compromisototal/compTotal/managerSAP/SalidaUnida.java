package cl.laaraucana.compromisototal.compTotal.managerSAP;

import java.util.ArrayList;

import cl.laaraucana.compromisototal.VO.ContratoVO;
import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class SalidaUnida extends AbstractSalidaVO{
	
	ArrayList<ContratoVO> listaBS = new ArrayList<ContratoVO>();

	public ArrayList<ContratoVO> getListaBS() {
		return listaBS;
	}

	public void setListaBS(ArrayList<ContratoVO> listaBS) {
		this.listaBS = listaBS;
	}
	

	
	
}
