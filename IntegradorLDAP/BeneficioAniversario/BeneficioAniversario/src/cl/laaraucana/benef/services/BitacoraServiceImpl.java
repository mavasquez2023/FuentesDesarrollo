package cl.laaraucana.benef.services;

import cl.laaraucana.benef.dao.BitacoraDAO;
import cl.laaraucana.benef.vo.BitacoraVO;


public class BitacoraServiceImpl implements BitacoraService {

	private final BitacoraDAO dao = new BitacoraDAO();

	@Override
	public boolean guardarBitacora(BitacoraVO param) {

		try {
			return dao.insertBitacora(param);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
