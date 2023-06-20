package cl.araucana.ldap.ws;

import org.apache.axis.AxisFault;

import cl.araucana.ldap.ws.vo.SalidainfoAfiliadoVO;


public interface WSInterface {
	
	
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
	
}
