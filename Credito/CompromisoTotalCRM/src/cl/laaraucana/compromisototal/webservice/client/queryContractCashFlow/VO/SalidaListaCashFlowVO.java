package cl.laaraucana.compromisototal.webservice.client.queryContractCashFlow.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class SalidaListaCashFlowVO extends AbstractSalidaVO {

	List<SalidaCashFlowVO> listaBs = new ArrayList<SalidaCashFlowVO>();

	/**
	 * @return el listaBs
	 */
	public List<SalidaCashFlowVO> getListaBs() {
		return listaBs;
	}

	/**
	 * @param listaBs
	 *            el listaBs a establecer
	 */
	public void setListaBs(List<SalidaCashFlowVO> listaBs) {
		this.listaBs = listaBs;
	}

}
