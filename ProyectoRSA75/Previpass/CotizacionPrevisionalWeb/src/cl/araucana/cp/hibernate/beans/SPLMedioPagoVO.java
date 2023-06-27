package cl.araucana.cp.hibernate.beans;

import java.io.Serializable;

public class SPLMedioPagoVO implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	private int codBanco;

	public SPLMedioPagoVO() {
		super();
	}

	public SPLMedioPagoVO(int id, int codBanco) {
		super();
		this.id = id;
		this.codBanco = codBanco;
	}

	public int getCodBanco() {
		return this.codBanco;
	}
	public void setCodBanco(int codBanco) {
		this.codBanco = codBanco;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
