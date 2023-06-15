package ztest;

import java.sql.SQLException;

import cl.jfactory.planillas.service.helper.GeneradorNominasHelper;

public class TestNombreArchivo {

	public static void main(String[] args) throws SQLException {
		
		String nombre = new GeneradorNominasHelper().obtenerNombreArchivo("CREDdbpef.PERI_CORTO;.dbpef.EMPCOD;", "", "70015580");
		
		System.out.println("nombre->"+ nombre);
	}
	
}
