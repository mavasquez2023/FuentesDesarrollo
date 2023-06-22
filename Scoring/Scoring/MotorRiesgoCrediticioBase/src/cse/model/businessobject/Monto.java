package cse.model.businessobject;

public class Monto {

	public Monto(int valor) {
		super();
		this.valor = valor;
	}

	public Monto() {
		super();
	}

	private int valor;

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String toString() {
		return super.toString() + "[ " + this.getValor() + " ]";
	}

}
