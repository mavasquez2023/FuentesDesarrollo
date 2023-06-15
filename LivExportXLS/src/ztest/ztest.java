package ztest;

import java.util.HashMap;

import cl.lib.export.xls.impl.GenerarXLS;

public class ztest {
	
	
	public static void main(String[] args) {
		
		System.out.println( GenerarXLS.generar("reporte_visitas_por_fecha","id_usuario:1423;;fecha_inicio:2017-08-28;;fecha_termino:2017-09-02") );
		
	}
}
