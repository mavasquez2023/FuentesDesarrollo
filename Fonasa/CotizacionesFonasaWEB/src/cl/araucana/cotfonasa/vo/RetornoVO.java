package cl.araucana.cotfonasa.vo;

public class RetornoVO {

    private int sqlCode;
    private String sqlState;
    private String mensaje;
    
    
	public int getSqlCode() {
		return sqlCode;
	}
	public void setSqlCode(int sqlCode) {
		this.sqlCode = sqlCode;
	}
	public String getSqlState() {
		return sqlState;
	}
	public void setSqlState(String sqlState) {
		this.sqlState = sqlState;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

    
}
