package cl.laaraucana.EnvioASFAMEmpresa.ws;

import org.apache.axis.AxisFault;

import cl.laaraucana.EnvioASFAMEmpresa.vo.SalidainfoAfiliadoVO;

public interface WSInterface {

	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;

}
