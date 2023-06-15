package cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.VO;

import java.util.ArrayList;
import java.util.List;
import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;

public class OficinaGastosNotarialSalidaLista extends AbstractSalidaVO {

	private List<OficinaGastosNotarialSalidaVO> oficinasGastosList = new ArrayList<OficinaGastosNotarialSalidaVO>();

	public List<OficinaGastosNotarialSalidaVO> getOficinasGastosList() {
		return oficinasGastosList;
	}

	public void setOficinasGastosList(List<OficinaGastosNotarialSalidaVO> oficinasGastosList) {
		this.oficinasGastosList = oficinasGastosList;
	}

}
