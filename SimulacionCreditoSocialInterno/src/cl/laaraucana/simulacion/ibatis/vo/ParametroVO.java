package cl.laaraucana.simulacion.ibatis.vo;

public class ParametroVO {
	private int codigoSimulador;
	private int codigoParametro;
	private String valor;

	public int getCodigoSimulador() {
		return codigoSimulador;
	}

	public void setCodigoSimulador(int codigoSimulador) {
		this.codigoSimulador = codigoSimulador;
	}

	public int getCodigoParametro() {
		return codigoParametro;
	}

	public void setCodigoParametro(int codigoParametro) {
		this.codigoParametro = codigoParametro;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
