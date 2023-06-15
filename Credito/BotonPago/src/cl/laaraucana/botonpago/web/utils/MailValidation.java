package cl.laaraucana.botonpago.web.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailValidation {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public MailValidation() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public static boolean IsValid(final String hex) {
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}

	//	public static void main(String[] args) {
	//		if (MailValidation.IsValid("juana@sd.cl")){
	//			System.out.println("valid");
	//		}
	//		else{
	//			System.out.println("not valid");
	//		}
	//	}

}
