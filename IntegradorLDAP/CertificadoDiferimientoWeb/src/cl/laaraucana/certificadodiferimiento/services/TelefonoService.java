package cl.laaraucana.certificadodiferimiento.services;

import java.util.List;

import cl.laaraucana.certificadodiferimiento.entities.PrefijoTelefonoEntity;



public interface TelefonoService {

	public List<PrefijoTelefonoEntity> getPrefijoTelefono() throws Exception;

	public List<PrefijoTelefonoEntity> getPrefijoCelular() throws Exception;

}
