package cl.laaraucana.mandato.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.mandato.vo.SalidainfoAfiliadoVO;

public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
