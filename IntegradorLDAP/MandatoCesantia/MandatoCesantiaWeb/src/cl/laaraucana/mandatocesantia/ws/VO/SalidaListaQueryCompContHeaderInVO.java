package cl.laaraucana.mandatocesantia.ws.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.mandatocesantia.vo.AbstractSalidaVO;

public class SalidaListaQueryCompContHeaderInVO extends AbstractSalidaVO {
	
	private List<SalidaQueryCompContHeaderInVO> listaCreditos = new ArrayList<SalidaQueryCompContHeaderInVO>();

	public List<SalidaQueryCompContHeaderInVO> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(
			List<SalidaQueryCompContHeaderInVO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}
}
