
/*
 * @(#) TransaccionBesDAO.java    1.0 06-08-2009
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
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
