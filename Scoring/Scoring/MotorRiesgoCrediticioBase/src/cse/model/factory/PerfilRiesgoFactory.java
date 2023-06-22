package cse.model.factory;

import cse.model.businessobject.PerfilRiesgo;
import cse.model.exception.ScoringModuleException;

public class PerfilRiesgoFactory {

	private static PerfilRiesgo[] perfiles = {new PerfilRiesgo("A", 0, 23.25), new PerfilRiesgo("B", 23.26, 46.5),
		new PerfilRiesgo("C", 46.6, 64.43), new PerfilRiesgo("D", 64.44, 82.26), new PerfilRiesgo("E", 82.26, Double.MAX_VALUE)};

	public static PerfilRiesgo getPerfilRiesgo(short probabilidadMora) throws ScoringModuleException {
		for (int i = 0; i < perfiles.length; i++) {
			if (probabilidadMora < perfiles[i].getMaxValue()){
				return perfiles[i];	
			} 
		}
		throw new ScoringModuleException("No se encontró ningún perfil de riesgo asociado a: " + probabilidadMora);
		
	}
}
