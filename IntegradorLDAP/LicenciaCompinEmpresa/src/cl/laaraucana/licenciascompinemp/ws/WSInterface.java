package cl.laaraucana.licenciascompinemp.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.licenciascompinemp.vo.SalidainfoAfiliadoVO;

public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
