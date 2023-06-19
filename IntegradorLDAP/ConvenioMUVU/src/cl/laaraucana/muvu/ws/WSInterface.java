package cl.laaraucana.muvu.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.muvu.vo.SalidainfoAfiliadoVO;


public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
