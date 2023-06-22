package cl.laaraucana.util.objectvalidate;

import java.util.ArrayList;

public class ValidateResultVO {
	private boolean isValid;
	private ArrayList<String> detailValidate = new ArrayList<String>();

	public ValidateResultVO() {
	}
	
	public ValidateResultVO(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public ArrayList<String> getDetailValidate() {
		return detailValidate;
	}

	public void setDetailValidate(ArrayList<String> detailValidate) {
		this.detailValidate = detailValidate;
	}

}
