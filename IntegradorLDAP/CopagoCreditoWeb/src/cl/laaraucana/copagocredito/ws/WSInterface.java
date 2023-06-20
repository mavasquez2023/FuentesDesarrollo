package cl.laaraucana.copagocredito.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.copagocredito.vo.SalidainfoAfiliadoVO;

public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
