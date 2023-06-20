package cl.laaraucana.mandatopublico.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.mandatopublico.vo.SalidainfoAfiliadoVO;

public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
