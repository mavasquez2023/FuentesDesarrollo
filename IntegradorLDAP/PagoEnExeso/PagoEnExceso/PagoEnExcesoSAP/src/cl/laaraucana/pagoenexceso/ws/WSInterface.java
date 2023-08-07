package cl.laaraucana.pagoenexceso.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.pagoenexceso.vo.SalidainfoAfiliadoVO;


public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
