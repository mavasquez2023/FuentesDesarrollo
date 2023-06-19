package cl.laaraucana.convenfsura.ibatis.dao;

import java.util.List;

public interface TelefonoDao {

	public List<String> getPrefijoTelefono(int tipo) throws Exception;

}
