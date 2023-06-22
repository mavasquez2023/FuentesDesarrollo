/**
 * 
 */
package cl.araucana.infdeupre.dao.VO;

import java.util.List;

/**
 * @author J-Factory
 *
 */
public class SalidaVO {
private ParamVO param;
private List<DeudaVO> deuda;
/**
 * @return the param
 */
public ParamVO getParam() {
	return param;
}
/**
 * @param param the param to set
 */
public void setParam(ParamVO param) {
	this.param = param;
}
/**
 * @return the deuda
 */
public List<DeudaVO> getDeuda() {
	return deuda;
}
/**
 * @param deuda the deuda to set
 */
public void setDeuda(List<DeudaVO> deuda) {
	this.deuda = deuda;
}

}
