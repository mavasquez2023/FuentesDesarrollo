/**
 * 
 */
package cl.laaraucana.ventaremota.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cl.laaraucana.ventaremota.ibatis.dao.CodigosSinacofiDao;
import cl.laaraucana.ventaremota.ibatis.dao.CodigosSinacofiDaoImpl;

/**
 * @author IBM Software Factory
 *
 */
public class CodigosSinacofiServiceImpl implements CodigosSinacofiService {
	
	CodigosSinacofiDao daoSina= new CodigosSinacofiDaoImpl();
	/* (non-Javadoc)
	 * @see cl.laaraucana.ventaremota.services.CodigosSinacofiService#getCodigosSinacofi()
	 */
	@Override
	public HashMap<String, String> getCodigosSinacofi() throws Exception {
		List<HashMap<String, String>> listaCodigos= daoSina.getCodigos();
		HashMap<String, String> listaCodigosSinacofi= new HashMap<String, String>();
		for (Iterator iterator = listaCodigos.iterator(); iterator.hasNext();) {
			HashMap<String, String> codigodes = (HashMap<String, String>) iterator
					.next();
			listaCodigosSinacofi.put(codigodes.get("codigoRetorno"), codigodes.get("descripcion"));
		}
		return listaCodigosSinacofi;
	}

}
