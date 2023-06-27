package cl.araucana.spl.dao;

import java.util.ArrayList;
import java.util.List;

import cl.araucana.spl.beans.ComprobanteTesoreriaMasivo;
import cl.araucana.spl.dao.config.DaoConfig;

import com.ibatis.dao.client.DaoManager;

public class test {

	/**
	 * @param args
	 */
	private TesoreriaDAO dao;
	
	private void ejecutarConsulta(){
		DaoManager mgr = DaoConfig.getDaoManager();
		dao = (TesoreriaDAO)mgr.getDao(TesoreriaDAO.class);
		ComprobanteTesoreriaMasivo entrada = new ComprobanteTesoreriaMasivo();
		entrada.setTipoPago("T");
		entrada.setUsuario("TEST123");
		List foliosTes = new ArrayList();
		foliosTes.add("1231232342");
		foliosTes.add("4324234322");
		foliosTes.add("987765654");
		entrada.setFoliosTes(foliosTes);
		dao.actualizarComprobanteTesoreria(entrada);
	}
	
	public static void main(String[] args) {
		test t = new test();
		t.ejecutarConsulta();

	}

}
