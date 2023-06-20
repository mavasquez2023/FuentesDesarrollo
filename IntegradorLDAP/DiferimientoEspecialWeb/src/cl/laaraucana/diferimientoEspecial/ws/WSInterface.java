package cl.laaraucana.diferimientoEspecial.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.diferimientoEspecial.vo.SalidainfoAfiliadoVO;

public interface WSInterface {

	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;

}
