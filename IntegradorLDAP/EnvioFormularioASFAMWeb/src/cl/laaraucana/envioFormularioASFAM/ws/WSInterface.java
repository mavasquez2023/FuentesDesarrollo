package cl.laaraucana.envioFormularioASFAM.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.envioFormularioASFAM.vo.SalidainfoAfiliadoVO;

public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
