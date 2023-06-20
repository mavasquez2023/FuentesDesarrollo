/**
 * 
 */
package cl.laaraucana.mandato.util;

import java.util.Comparator;

import cl.laaraucana.mandato.ibatis.vo.MandatoAS400Vo;

/**
 * @author J-Factory
 *
 */
public class MandatoComparator implements Comparator<MandatoAS400Vo>{
	private boolean asc;

	public MandatoComparator(boolean asc) {
        this.asc = asc;
    }
    @Override
    public int compare(MandatoAS400Vo o1, MandatoAS400Vo o2) {
        int ret;
        if (asc) {
            ret = new Long(o1.getIdMandato()).compareTo(new Long(o2.getIdMandato()));
        } else {
            ret = new Long(o2.getIdMandato()).compareTo(new Long(o1.getIdMandato()));
        }
        return ret;
    }
}
