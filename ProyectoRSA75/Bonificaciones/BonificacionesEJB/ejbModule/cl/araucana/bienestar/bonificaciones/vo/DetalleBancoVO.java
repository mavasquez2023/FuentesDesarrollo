package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class DetalleBancoVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	/** Código Banco */
	private String codigoBanco;
	
	/** Banco */
	private String banco;	

	/** Monto total a transferiri al banco */
	private long monto;

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public long getMonto() {
		return monto;
	}

	public void setMonto(long monto) {
		this.monto = monto;
	}

}
