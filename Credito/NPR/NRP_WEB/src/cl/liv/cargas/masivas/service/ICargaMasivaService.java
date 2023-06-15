package cl.liv.cargas.masivas.service;

import cl.liv.comun.utiles.MiHashMap;

public interface ICargaMasivaService {

	public int execute(MiHashMap config);
	public boolean validate(MiHashMap config);
	
}
