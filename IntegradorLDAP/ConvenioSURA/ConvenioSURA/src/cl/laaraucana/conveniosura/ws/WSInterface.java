package cl.laaraucana.conveniosura.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.conveniosura.vo.SalidainfoAfiliadoVO;


public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
