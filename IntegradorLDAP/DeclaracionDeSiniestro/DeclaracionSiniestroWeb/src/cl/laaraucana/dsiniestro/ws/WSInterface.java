package cl.laaraucana.dsiniestro.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.dsiniestro.vo.SalidainfoAfiliadoVO;

public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
