package utilMonth;

import java.text.DecimalFormat;

public class Formato {
	
	public  String getNumber(String number) {
		double value;
		String numberFormat = "###,###,###,###";
		DecimalFormat formatter = new DecimalFormat(numberFormat);
		try {
		value = Double.parseDouble(number);
		} catch (Throwable t) {
		return null;
		}
		return formatter.format(value);
		} 

}
