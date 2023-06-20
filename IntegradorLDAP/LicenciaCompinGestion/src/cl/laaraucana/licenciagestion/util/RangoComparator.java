/**
 * 
 */
package cl.laaraucana.licenciagestion.util;

import java.util.Comparator;

import cl.laaraucana.licenciagestion.vo.OficinasxRango;

/**
 * @author IBM Software Factory
 *
 */
public class RangoComparator implements Comparator<OficinasxRango>{

	@Override
	public int compare(OficinasxRango o1, OficinasxRango o2) {
		int ret = new Integer(o1.getPercentil()).compareTo(new Integer(o2.getPercentil()));
		return ret;
	}

}
