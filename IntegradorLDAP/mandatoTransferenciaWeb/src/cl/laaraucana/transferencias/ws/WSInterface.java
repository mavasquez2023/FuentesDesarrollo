package cl.laaraucana.transferencias.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.transferencias.vo.SalidainfoAfiliadoVO;

public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
