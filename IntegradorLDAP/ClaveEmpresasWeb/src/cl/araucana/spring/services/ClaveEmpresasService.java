package cl.araucana.spring.services;

 

import cl.araucana.spring.entities.Formulario;

public interface ClaveEmpresasService {
	
	public void saveClaveEmpresa(Formulario form);
	
	public Formulario readFile(long id);

}
