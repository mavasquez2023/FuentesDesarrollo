package cl.araucana.ctasfam.resources.util;

import java.text.DecimalFormat;

public class Padder {
	private Padder() {
	}

	public static String lpad(int number, int width) {
		return lpad(number + "", width);
	}

	public static String lpad(long number, int width) {
		return lpad(number + "", width);
	}

	public static String lpad(String s, int width) {
		return lpad(s, width, ' ');
	}

	public static String lpad(int number, int width, char ch) {
		return lpad(number + "", width, ch);
	}

	public static String lpad(long number, int width, char ch) {
		return lpad(number + "", width, ch);
	}

	public static String lpad(String s, int width, char ch) {
		return pad(s, width, ch, true);
	}

	public static String rpad(int number, int width) {
		return rpad(number + "", width);
	}

	public static String rpad(long number, int width) {
		return rpad(number + "", width);
	}

	public static String rpad(String s, int width) {
		return rpad(s, width, ' ');
	}

	public static String rpad(int number, int width, char ch) {
		return rpad(number + "", width, ch);
	}

	public static String rpad(long number, int width, char ch) {
		return rpad(number + "", width, ch);
	}

	public static String rpad(String s, int width, char ch) {
		return pad(s, width, ch, false);
	}

	public static String pad(String s, int width, char ch, boolean left) {
		String string;
		if (s.length() < width) {
			StringBuffer buffer = new StringBuffer(s.length());
			for (int i = s.length(); i < width; ++i) {
				buffer.append(ch);
			}
			string = (left) ? buffer.toString() + s : s + buffer.toString();
		} else {
			string = s.substring(0, width);
		}
		return string;
	}

	public static String lpad(double number, int width, int nDecimals) {
		return lpad(number, width, nDecimals, false, ' ');
	}

	public static String lpad(double number, int width, int nDecimals,
			boolean notDecimalDot, char padder) {
		return pad(number, width, nDecimals, true, notDecimalDot, padder);
	}

	public static String rpad(double number, int width, int nDecimals) {
		return rpad(number, width, nDecimals, false, ' ');
	}

	public static String rpad(double number, int width, int nDecimals,
			boolean notDecimalDot, char padder) {
		return pad(number, width, nDecimals, false, notDecimalDot, padder);
	}

	public static String pad(double number, int with, int nDecimals,
			boolean left, boolean notDecimalDot, char padder) {
		long intPart = (long) number;
		double factor = Math.pow(10.0, nDecimals);
		int fractionalPart = (int) ((factor * (number - intPart) + 0.5));
		String formattedIntPart = "" + intPart;
		String formattedFractionalPart = Padder.rpad(fractionalPart, nDecimals, '0');
		String s;
		if (notDecimalDot) {
			s = formattedIntPart + "" + formattedFractionalPart;
		} else {
			s = formattedIntPart + "." + formattedFractionalPart;
		}
		return Padder.pad(s, with, padder, left);
	}

	public static String padSeparators(int number) {
		return padSeparators(number, '.');
	}

	public static String padSeparators(int number, char separator) {
		String s = "";
		int aux = number;
		DecimalFormat df = new DecimalFormat("000");
		while (aux != 0) {
			int p = aux % 1000;

			if (s.length() > 0) {
				s = separator + s;
			}
			aux = aux / 1000;
			if (aux > 0) {
				s = df.format(p) + s;
			} else {
				s = p + s;
			}
		}
		return s;
	}
}
