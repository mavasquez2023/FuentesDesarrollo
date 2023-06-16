package cl.laaraucana.botonpago.web.manager;

import java.util.List;

import cl.laaraucana.botonpago.web.database.dao.SinaDtaDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Sinat03;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Sinat04;

public class ManagerSINADTA {

	/*****************************SINAT03************************/

	public static List<Sinat03> getSinat03() throws Exception {

		SinaDtaDAO sinaDtaDAO = new SinaDtaDAO();
		return sinaDtaDAO.getSinat03();

	}

	public static void agregarSinat03(Sinat03 sinat03) throws Exception {

		SinaDtaDAO sinaDtaDAO = new SinaDtaDAO();
		sinaDtaDAO.ingresaSinat03(sinat03);

	}

	public static void eliminarSinat03(Sinat03 sinat03) throws Exception {

		SinaDtaDAO sinaDtaDAO = new SinaDtaDAO();
		sinaDtaDAO.eliminarSinat03(sinat03);

	}

	public static List<Sinat03> buscarSinat03(String entrada) throws Exception {

		SinaDtaDAO sinaDtaDAO = new SinaDtaDAO();
		return sinaDtaDAO.buscarSinat03(entrada);

	}

	public static Sinat03 buscarEditarSinat03(String entrada) throws Exception {

		SinaDtaDAO sinaDtaDAO = new SinaDtaDAO();
		return sinaDtaDAO.buscarEditarSinat03(entrada);

	}

	public static void editarSinat03(Sinat03 sinat03) throws Exception {

		SinaDtaDAO sinaDtaDAO = new SinaDtaDAO();
		sinaDtaDAO.editarSinat03(sinat03);

	}

	/*****************************FIN SINAT03************************/

	/*****************************SINAT04************************/

	public static List<Sinat04> getSinat04(String codPro) throws Exception {

		SinaDtaDAO sinaDtaDAO = new SinaDtaDAO();
		return sinaDtaDAO.getSinat04(codPro);

	}

	public static void agregarSinat04(Sinat04 sinat04) throws Exception {

		SinaDtaDAO sinaDtaDAO = new SinaDtaDAO();
		sinaDtaDAO.ingresaSinat04(sinat04);

	}

	public static void eliminarSinat04(Sinat04 sinat04) throws Exception {

		SinaDtaDAO sinaDtaDAO = new SinaDtaDAO();
		sinaDtaDAO.eliminarSinat04(sinat04);

	}

	public static List<Sinat04> buscarSinat04(String entrada) throws Exception {

		SinaDtaDAO sinaDtaDAO = new SinaDtaDAO();
		return sinaDtaDAO.buscarSinat04(entrada);

	}

	public static Sinat04 buscarEditarSinat04(Sinat04 sinat04Edit) throws Exception {

		SinaDtaDAO sinaDtaDAO = new SinaDtaDAO();
		return sinaDtaDAO.buscarEditarSinat04(sinat04Edit);

	}

	public static void editarSinat04(Sinat04 sinat04) throws Exception {

		SinaDtaDAO sinaDtaDAO = new SinaDtaDAO();
		sinaDtaDAO.editarSinat04(sinat04);

	}

	/*****************************FIN SINAT04************************/

}
