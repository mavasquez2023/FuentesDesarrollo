

package cl.araucana.wssiagf;


public interface Operations {

	public int OP_GET_VERSION = 0;
	public int OP_INGRESO_RECONOCIMIENTO = 1;
	public int OP_CONSULTA_CAUSANTE = 2;
	public int OP_EXTINCION_RECONOCIMIENTO = 3;
	public int OP_ACTUALIZAR_CAUSANTE = 4;

	public String[] opNames = {
		"getVersion",
		"ingresoReconocimiento",
		"consultaCausante",
		"extincionReconocimiento",
		"ActualizarCausante"
	};
}
