
/*
 * @(#) TransaccionBesDAO.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.spl.dao;


import cl.araucana.spl.beans.TransaccionBes;

/**
 * ...
 *
 * @author Gonzalo Mallea Lorca (gmallea@schema.cl)
 *
 * @version 1.0
 */

public interface TransaccionBesDAO {
	//Transacciones Bco Estado:
	public void insertTrx(TransaccionBes trx);
	public TransaccionBes findTransaccionByCodigoIdTrx(String codigoIdTrx);
	public void updateNotificacion(TransaccionBes trx);
}
