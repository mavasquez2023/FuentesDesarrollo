package cl.laaraucana.surakm.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.surakm.vo.SalidainfoAfiliadoVO;


public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
