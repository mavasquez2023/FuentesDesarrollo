package cl.araucana.tesoreria.dao.dvo;

import java.util.Date;

public class GenericDVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String string;
	private long number;
	private double decimal;
	private Date date;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public double getDecimal() {
		return decimal;
	}

	public void setDecimal(double decimal) {
		this.decimal = decimal;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
