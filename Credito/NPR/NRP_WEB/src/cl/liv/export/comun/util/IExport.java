package cl.liv.export.comun.util;

import java.io.PrintWriter;

public interface IExport {
	//public String generar(String txt, String pars);
	public String generar(String txt, String pars,String formato,String separador, PrintWriter archivoResultado);
}
