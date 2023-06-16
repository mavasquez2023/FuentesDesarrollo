package cl.laaraucana.simat.entidades;

public class CuentaIF {

	private String nombre;
	private String valorCuenta;

	public CuentaIF() {

	}

	public CuentaIF(String nombre, String valorCuenta) {
		super();
		this.nombre = nombre;
		this.valorCuenta = valorCuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public String getValorCuenta() {
		return valorCuenta;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setValorCuenta(String valorCuenta) {
		this.valorCuenta = valorCuenta;
	}

}
