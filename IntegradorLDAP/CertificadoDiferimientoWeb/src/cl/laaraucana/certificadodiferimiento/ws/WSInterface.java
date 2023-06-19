package cl.laaraucana.certificadodiferimiento.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.certificadodiferimiento.vo.SalidainfoAfiliadoVO;

public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
