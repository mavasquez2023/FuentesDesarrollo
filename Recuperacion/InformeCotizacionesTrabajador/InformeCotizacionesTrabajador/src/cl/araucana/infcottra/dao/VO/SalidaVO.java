/**
 * 
 */
package cl.araucana.infcottra.dao.VO;

import java.util.List;

/**
 * @author J-Factory
 *
 */
public class SalidaVO {
private CotizacionVO param;
private List<CotizacionVO> cotizaciones;
/**
 * @return the param
 */
public CotizacionVO getParam() {
	return param;
}
/**
 * @param param the param to set
 */
public void setParam(CotizacionVO param) {
	this.param = param;
}
/**
 * @return the cotizaciones
 */
public List<CotizacionVO> getCotizaciones() {
	return cotizaciones;
}
/**
 * @param cotizaciones the cotizaciones to set
 */
public void setCotizaciones(List<CotizacionVO> cotizaciones) {
	this.cotizaciones = cotizaciones;
}


}
