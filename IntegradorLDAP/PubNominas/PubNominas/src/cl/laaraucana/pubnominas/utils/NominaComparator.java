/**
 * 
 */
package cl.laaraucana.pubnominas.utils;

import java.util.Comparator;

import cl.laaraucana.pubnominas.vo.DescargasxRol;

/**
 * @author IBM Software Factory
 *
 */
public class NominaComparator implements Comparator<DescargasxRol>{

	@Override
	public int compare(DescargasxRol o1, DescargasxRol o2) {
		int ret = new Integer(o1.getCantidadEjecutivo()+o1.getCantidadEncargado()).compareTo(new Integer(o2.getCantidadEjecutivo()+o2.getCantidadEncargado()));
		return ret;
	}

}
