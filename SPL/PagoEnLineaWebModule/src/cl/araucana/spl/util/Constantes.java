package cl.araucana.spl.util;

import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import cl.araucana.spl.beans.MedioPago;
import cl.araucana.spl.mgr.MedioPagoManager;
import cl.araucana.spl.mgr.ParametrosManager;

public class Constantes {

	/* para instancia de clase */
	private static Constantes c;

	private static ResourceBundle parametros = ResourceBundle.getBundle("parametros");

	public static void kill() throws Exception {
		c = null;
	}

	public static Constantes getInstancia() throws Exception {

		if (c == null) {
			c = new Constantes();
			// asignar los valores desde la BD
			c.cargarParametros();
		} else {
		}
		return c;
	}

	// recarga los parametros desde la base de datos
	/*
	 * public static void recargarParametros() throws Exception { c = new
	 * Constantes(); // Reasignar los valores desde la BD cargarParametros(); }
	 */

	public int DIAS_RESTA;
	public String TIEMPO_JOB;
	//Tesoreria
	public String TIPO_PAGO_TES;
	public String USUARIO_TES;
	public String EST_MOV_TES;
	//Libro de  banco
	public String TIPO_ABONO;
	public String COD_OP_INTERNA;
	public String DESC_PAGO_MEDIO_ONLINE;
	
	//Tipos de banco
	public HashMap MEDIO_PAGO;
	public String COD_TIPO_BCO_BATCH = "0";
	public String COD_TIPO_BCO_ONLINE = "1";
	
	private void cargarParametros() throws Exception {
		ParametrosManager param = new ParametrosManager();
		HashMap map = param.getParametros();

		setTIEMPO_JOB((String) map.get(parametros.getString("hora.ejecucion.job")), parametros.getString("expresion.cronjob"));
		setDIAS_RESTA((String) map.get(parametros.getString("cantidad.dias.resta")));

		TIPO_PAGO_TES = (String) map.get(parametros.getString("tipo.pago.tes"));
		USUARIO_TES = (String) map.get(parametros.getString("usuario.tes"));
		EST_MOV_TES = (String) map.get(parametros.getString("est.mov.tes"));
		
		TIPO_ABONO = (String ) map.get(parametros.getString("tipo.abono"));
		COD_OP_INTERNA = (String ) map.get(parametros.getString("cod.op.interna"));
		DESC_PAGO_MEDIO_ONLINE = (String ) map.get(parametros.getString("desc.medio.online"));
		
		MedioPagoManager medioPago = new MedioPagoManager();
		List mediosPago = medioPago.getMediosPago();
		MEDIO_PAGO = new HashMap();
		for (int i = 0; i < mediosPago.size(); i++) {
			MedioPago aux = (MedioPago)mediosPago.get(i);
			MEDIO_PAGO.put(aux.getCodigo(), aux);
		}
	}

	public static void setC(Constantes c) {
		Constantes.c = c;
	}

	public void setTIEMPO_JOB(String horaejecucion, String expresion) {
		String s = expresion;
		s = s.replaceAll("HH", horaejecucion.split(":")[0]);
		s = s.replaceAll("mm", horaejecucion.split(":")[1]);
		TIEMPO_JOB = s;
//		TIEMPO_JOB = parametros.getString("expresion.prueba");//prueba
		
	}

	public void setDIAS_RESTA(String dias_resta) {
		DIAS_RESTA = Integer.parseInt(dias_resta.trim());
//		DIAS_RESTA = 1;//prueba
	}

}
