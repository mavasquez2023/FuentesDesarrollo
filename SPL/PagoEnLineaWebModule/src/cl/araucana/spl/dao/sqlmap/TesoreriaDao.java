package cl.araucana.spl.dao.sqlmap;

import cl.araucana.spl.beans.ComprobanteTesoreria;
import cl.araucana.spl.dao.TesoreriaDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class TesoreriaDao extends SqlMapDaoTemplate implements TesoreriaDAO {

	public TesoreriaDao(DaoManager daoManager) {
		super(daoManager);
	}
	
	/**
	 * Actualiza estado de comprobante de tesorería a cursado y asigna tipo de pago
	 * @param entrada
	 * @return
	 * @throws Exception
	 */
	public boolean actualizarComprobanteTesoreria(ComprobanteTesoreria entrada) {
		boolean res = false;
		queryForObject("actualizaTesoreria", entrada);
		if(entrada.getCantActualizados()>0) res = true;
		return res;
	}

	
	/*
	 * Update directo a la tabla
	 * 	public boolean actualizarComprobanteTesoreria(ComprobanteTesoreria entrada) {
		boolean res = false;
		//entrada.setUsuario(Constantes.USER_SISTEMA_ORIGEN); TODO constantes
		int cant = update("actCompTesoreria", entrada);
		if(cant>0) res = true;
		return res;
	}*/
}
