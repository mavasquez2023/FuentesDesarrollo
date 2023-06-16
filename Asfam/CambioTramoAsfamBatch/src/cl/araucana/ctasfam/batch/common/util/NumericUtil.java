package cl.araucana.ctasfam.batch.common.util;

public class NumericUtil {
	public boolean isNumeric(String str) {
		try{
			Integer.parseInt(str);
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
