package cse.database.dao.helper;

import cse.database.dao.exception.DAOException;
import cse.database.objects.Solicitud;

public class SolicitudDAOHelper {


	public static Integer translateGenero(Solicitud solicitud) throws DAOException {
		String genero = solicitud.getIdgenero();
		return translateGenero(genero);
	}

	/* Por ahora esta en duro para evitar llamadas extra a la BD*/
	private static Integer translateGenero(String genero) throws DAOException {
		if (null != genero) {
			if (genero.equals("M")) {
				return new Integer(1);
			}
			else if (genero.equals("F")) {
				return new Integer(2);
			}

		}
		else {
			// Estamos aceptando nulos dado que prima la regla de Equifax. La BD va a ir a mirar
			// a Equifax primero que el dato insertado directamente.
			//throw new DAOException("Parametro de invocación Género no es ni M ni F");
			return null;
		}
		//else throw new DAOException("Parametro de invocación Género viene vacío o nulo");
		return null;
	}

}
