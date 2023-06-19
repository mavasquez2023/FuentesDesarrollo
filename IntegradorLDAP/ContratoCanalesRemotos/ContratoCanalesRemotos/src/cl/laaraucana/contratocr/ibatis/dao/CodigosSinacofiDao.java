/**
 * 
 */
package cl.laaraucana.contratocr.ibatis.dao;

import java.util.HashMap;
import java.util.List;

/**
 * @author IBM Software Factory
 *
 */
public interface CodigosSinacofiDao {
	
	public List<HashMap<String, String>> getCodigos() throws Exception;
}
