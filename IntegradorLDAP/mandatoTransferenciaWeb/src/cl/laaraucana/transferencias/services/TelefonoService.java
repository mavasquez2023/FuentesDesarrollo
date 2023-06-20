package cl.laaraucana.transferencias.services;

import java.util.List;

public interface TelefonoService {

	public List<String> getPrefijoTelefono() throws Exception;

	public List<String> getPrefijoCelular() throws Exception;

}
