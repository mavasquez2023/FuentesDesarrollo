package cl.laaraucana.mandatocesantia.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.mandatocesantia.vo.SalidainfoAfiliadoVO;

public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
