package cl.laaraucana.simulacion.webservices.client.ConsultaCreditoPreAprob.VO;

import java.util.ArrayList;
import java.util.List;
import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;

public class SalidaListaConsultaCreditoPreAprob extends AbstractSalidaVO {

	private List<ConsultaCreditoPreAprobSalidaVO> detallePreAprobado = new ArrayList<ConsultaCreditoPreAprobSalidaVO>();

	public List<ConsultaCreditoPreAprobSalidaVO> getDetallePreAprobado() {
		return detallePreAprobado;
	}

	public void setDetallePreAprobado(List<ConsultaCreditoPreAprobSalidaVO> detallePreAprobado) {
		this.detallePreAprobado = detallePreAprobado;
	}

}
