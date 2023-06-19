package cl.laaraucana.convenfsura.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.convenfsura.vo.SalidainfoAfiliadoVO;


public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
