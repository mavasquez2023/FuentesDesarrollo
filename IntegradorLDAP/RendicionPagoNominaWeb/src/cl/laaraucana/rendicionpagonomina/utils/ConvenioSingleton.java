/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.services.ParametrosService;
import cl.laaraucana.rendicionpagonomina.services.ParametrosServiceImpl;

/**
 * @author IBM Software Factory
 *
 */
public class ConvenioSingleton {
	private static ConvenioSingleton instance = new ConvenioSingleton();
	Map<String, ConvenioEntity> convenios= new HashMap<String, ConvenioEntity>();

	public static ConvenioSingleton getInstance(){
			return instance;
	}

	public ConvenioSingleton(){
		try {
			ParametrosService params= new ParametrosServiceImpl();
			List<ConvenioEntity> listConvenios= params.consultaConvenios();
			for (Iterator iterator = listConvenios.iterator(); iterator
					.hasNext();) {
				ConvenioEntity convenioEntity = (ConvenioEntity) iterator
						.next();
				convenios.put(convenioEntity.getCodigoConvenio(), convenioEntity);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the convenios
	 */
	public ConvenioEntity getConvenio(String idConvenio) {
		return convenios.get(idConvenio);
	}
	
}
