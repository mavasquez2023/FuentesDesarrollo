package cl.laaraucana.licenciascompin.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.licenciascompin.vo.SalidainfoAfiliadoVO;

public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
