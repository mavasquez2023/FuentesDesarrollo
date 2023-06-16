package cl.laaraucana.simat.entidades;

public class BalanceGeneral {

	private String cuenta;
	private String texto_breve;
	private String resultado;

	public BalanceGeneral() {

	}

	public BalanceGeneral(String cuenta, String texto_breve, String resultado) {
		super();
		this.cuenta = cuenta;
		this.texto_breve = texto_breve;
		this.resultado = resultado;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getTexto_breve() {
		return texto_breve;
	}

	public void setTexto_breve(String texto_breve) {
		this.texto_breve = texto_breve;
	}

}
