package cl.laaraucana.ventaremota.CRM.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.ventaremota.vo.SalidainfoAfiliadoVO;



public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
