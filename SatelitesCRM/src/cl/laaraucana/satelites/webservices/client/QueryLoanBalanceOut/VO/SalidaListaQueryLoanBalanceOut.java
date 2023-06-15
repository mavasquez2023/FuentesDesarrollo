package cl.laaraucana.satelites.webservices.client.QueryLoanBalanceOut.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaQueryLoanBalanceOut extends AbstractSalidaVO {

	private List<SalidaQueryLoanBalanceOut> SalidaList = new ArrayList<SalidaQueryLoanBalanceOut>();

	public List<SalidaQueryLoanBalanceOut> getSalidaList() {
		return SalidaList;
	}

	public void setSalidaList(List<SalidaQueryLoanBalanceOut> salidaList) {
		SalidaList = salidaList;
	}

}
