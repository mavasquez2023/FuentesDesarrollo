package cl.laaraucana.tarjetatam.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.tarjetatam.vo.SalidainfoAfiliadoVO;

public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
