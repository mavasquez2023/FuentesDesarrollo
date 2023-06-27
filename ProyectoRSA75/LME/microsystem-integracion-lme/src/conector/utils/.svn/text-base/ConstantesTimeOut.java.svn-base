package conector.utils;

import java.util.ResourceBundle;

public class ConstantesTimeOut {

	private static ConstantesTimeOut c;
	private static ResourceBundle parametros = ResourceBundle.getBundle("timeout");

	public static ConstantesTimeOut getInstancia() throws Exception {
		if (c == null) {
			// System.out.println("[* * * *] sin Instancia [*]");
			c = new ConstantesTimeOut();
			// asignar los valores desde la BD
			c.cargarParametros();
		} else {
			// System.out.println("\n\n[* * * * ]Instancia ya existe  [*]\n\n");
		}
		return c;
	}

	private void cargarParametros() throws Exception {
		setCONSULTA(parametros.getString("CONSULTA"));
		setDETALLE(parametros.getString("DETALLE"));
		setDEVOLUCION(parametros.getString("DEVOLUCION"));
		setLIQUIDACION(parametros.getString("LIQUIDACION"));
		setVALIDACION(parametros.getString("VALIDACION"));
		setZONA_C(parametros.getString("ZONA_C"));
	}

	public String CONSULTA;
	public String DETALLE;
	public String DEVOLUCION;
	public String LIQUIDACION;
	public String VALIDACION;
	public String ZONA_C;

	public void setCONSULTA(String cONSULTA) {
		CONSULTA = cONSULTA;
	}

	public void setDETALLE(String dETALLE) {
		DETALLE = dETALLE;
	}

	public void setDEVOLUCION(String dEVOLUCION) {
		DEVOLUCION = dEVOLUCION;
	}

	public void setLIQUIDACION(String lIQUIDACION) {
		LIQUIDACION = lIQUIDACION;
	}

	public void setVALIDACION(String vALIDACION) {
		VALIDACION = vALIDACION;
	}

	public void setZONA_C(String zONA_C) {
		ZONA_C = zONA_C;
	}

	/*public String getTimeOut(String nombre){
		return parametros.getString(nombre);
	}*/

}
