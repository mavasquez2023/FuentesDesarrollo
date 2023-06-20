/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.utils;

import java.util.Comparator;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;

/**
 * @author IBM Software Factory
 *
 */
public class CodigoNominaComparator implements Comparator<CabeceraEntity>{
	private boolean asc;
	
	public CodigoNominaComparator(boolean asc) {
		this.asc = asc;
	}
	
	@Override
	public int compare(CabeceraEntity o1, CabeceraEntity o2) {
		 int ret;
	        if (asc) {
	            ret = new Long(o1.getCodigoNomina()).compareTo(new Long(o2.getCodigoNomina()));
	        } else {
	            ret = new Long(o2.getCodigoNomina()).compareTo(new Long(o1.getCodigoNomina()));
	        }
	        return ret;
	}
	
}
