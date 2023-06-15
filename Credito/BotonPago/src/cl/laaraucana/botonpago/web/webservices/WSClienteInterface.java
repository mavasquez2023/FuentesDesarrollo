package cl.laaraucana.botonpago.web.webservices;

import org.apache.axis.AxisFault;
import cl.laaraucana.botonpago.web.webservices.model.SalidainfoAfiliadoVO;

/**
 * Consulta información de la CRM
 * @author LaAraucana
 *
 */
public interface WSClienteInterface {
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault;
}
