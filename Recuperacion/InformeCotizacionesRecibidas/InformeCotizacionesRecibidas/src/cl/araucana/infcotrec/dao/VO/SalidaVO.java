/**
 * 
 */
package cl.araucana.infcotrec.dao.VO;

import java.util.List;

/**
 * @author J-Factory
 *
 */
public class SalidaVO {
private ParamVO param;
private List<PagosVO> pagos;
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
 * @return the pagos
 */
public List<PagosVO> getPagos() {
	return pagos;
}
/**
 * @param pagos the pagos to set
 */
public void setPagos(List<PagosVO> pagos) {
	this.pagos = pagos;
}


}
