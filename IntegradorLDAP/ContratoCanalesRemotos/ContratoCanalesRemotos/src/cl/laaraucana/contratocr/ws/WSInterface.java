package cl.laaraucana.contratocr.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.contratocr.vo.SalidainfoAfiliadoVO;



public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
