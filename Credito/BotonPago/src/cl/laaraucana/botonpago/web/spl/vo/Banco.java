package cl.laaraucana.botonpago.web.spl.vo;

import java.util.List;

public class Banco {

	private List<String> codigo;

	public Banco() {
	}

	public Banco(List<String> codigo) {
		super();
		this.codigo = codigo;
	}

	public List<String> getCodigo() {
		return codigo;
	}

	public void setCodigo(List<String> codigo) {
		this.codigo = codigo;
	}

}
