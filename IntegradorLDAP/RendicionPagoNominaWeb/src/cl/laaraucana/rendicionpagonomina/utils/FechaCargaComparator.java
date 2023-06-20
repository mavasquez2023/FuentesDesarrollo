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
public class FechaCargaComparator implements Comparator<CabeceraEntity>{
	
	private boolean asc;
	
	public FechaCargaComparator(boolean asc) {
		 this.asc = asc;
	}
	
	@Override
	 public int compare(CabeceraEntity o1, CabeceraEntity o2){
		 int ret;
	        if (asc) {
	            ret = o1.getFechaCreacion().compareTo(o2.getFechaCreacion());
	        } else {
	            ret =o2.getFechaCreacion().compareTo(o1.getFechaCreacion());
	        }
	        return ret;
	 }
}
