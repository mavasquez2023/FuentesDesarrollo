package cl.laaraucana.ventafullweb.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.ventafullweb.vo.SalidainfoAfiliadoVO;



public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
