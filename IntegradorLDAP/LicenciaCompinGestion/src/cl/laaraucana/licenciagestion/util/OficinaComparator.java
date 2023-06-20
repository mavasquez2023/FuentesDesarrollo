/**
 * 
 */
package cl.laaraucana.licenciagestion.util;

import java.util.Comparator;

import cl.laaraucana.licenciagestion.vo.OficinaViaIngreso;

/**
 * @author IBM Software Factory
 *
 */
public class OficinaComparator implements Comparator<OficinaViaIngreso>{

	@Override
	public int compare(OficinaViaIngreso o1, OficinaViaIngreso o2) {
		int ret = new Integer(o1.getCantidadAfiliado()+o1.getCantidadEmpresa()).compareTo(new Integer(o2.getCantidadAfiliado()+o2.getCantidadEmpresa()));
		return ret;
	}

}
