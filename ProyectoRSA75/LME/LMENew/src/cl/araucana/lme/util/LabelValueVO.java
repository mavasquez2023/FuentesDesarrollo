/*
 * Created on 18-11-2011
 *
 */
package cl.araucana.lme.util;

import java.io.Serializable;

/**
 * @author j-factory
 * 
 */
public class LabelValueVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8362373280632270005L;
	private String label;
	private String value;

	/**
	 *  
	 */
	public LabelValueVO() {
		super();
	}

	public LabelValueVO(String label, String value) {
		this.label = label;
		this.value = value;
	}

	/**
	 * @return Returns the label.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            The label to set.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("LabelValueVO[");
		sb.append(this.label);
		sb.append(", ");
		sb.append(this.value);
		sb.append("]");
		return sb.toString();
	}
}
