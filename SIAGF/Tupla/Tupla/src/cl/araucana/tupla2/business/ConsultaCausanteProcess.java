package cl.araucana.tupla2.business;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;

import cl.araucana.tupla2.exception.Tupla2Exception;
import cl.araucana.tupla2.multithread.Queue;
import cl.araucana.tupla2.struts.bussiness.TO.SqlParametersTO;
import cl.araucana.tupla2.struts.jdbcDAO.Araucanajdbcdao;
import cl.araucana.tupla2.struts.utils.Contadores;

public class ConsultaCausanteProcess implements BusinessProcess {
	private SqlParametersTO oSql;
	private String msg;

	public ConsultaCausanteProcess(SqlParametersTO oSql2) {
		this.oSql = oSql2;
	}

	@Override
	public void process() throws Tupla2Exception {
		long startTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
		SimpleDateFormat sdfh = new SimpleDateFormat("hh:MM:ss");
		Contadores contador = null;
		FileWriter fw = null;
		FileWriter fatalErrFile = null;
		Queue q = new Queue();

		//Consulta de los ruts...
		Araucanajdbcdao dao = new Araucanajdbcdao();
		//Vector strRuts = dao.getXML(oSql);
		Vector strRuts = dao.getConsulta(oSql);

		HashMap maximos = dao.getMaxIds(oSql);
		//Apertura de archivos
		try {
			fw = new FileWriter("c:/SIAGF/reprocess.txt");
			fatalErrFile = new FileWriter("c:/SIAGF/fatalErr.txt");
			contador = new Contadores(Integer.parseInt(maximos.get("TUPLA").toString()), Integer.parseInt(maximos.get("TRAMO").toString()));
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new Tupla2Exception("Error al inicializar proceso." + e1.getMessage());
		}

		//Por cada rut proceso....
		ConsultaCausanteRunnable consultaCausante = null;
		for (int i = 0; i < strRuts.size(); i++) {
			//System.out.println(strRuts.get(i).toString()+"|"+i);
			consultaCausante = new ConsultaCausanteRunnable(q, strRuts.get(i).toString(), contador, fatalErrFile, fw, oSql);
			if (oSql.isUseThread())
				q.initProcess(consultaCausante);
			else
				consultaCausante.run();
		}

		//Cierre de archivos
		synchronized (q) {
			while (!q.allFinished()) {
				System.out.println("Aun hay procesos corriendo....");
				try {
					q.wait();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		try {
			fw.close();
			fatalErrFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		System.out.println("Rut Procesados:" + contador.getCountRutProcesados());
		//System.out.println("Tuplas - INICIAL:" + maximos.get("TUPLA") + "-FINAL:" + contador.getCountTuplas());
		//System.out.println("Tramos - INICIAL:" + maximos.get("TRAMO") + "-FINAL:" + contador.getCountTramos());
		System.out.println("Tiempo Ejecucion:" + (endTime - startTime));
		msg = "Ruts Procesados:" + contador.getCountRutProcesados() + ".     Tiempo Ejecucion:" + (endTime - startTime);
	}

	public static void main(String arg[]) {
		SqlParametersTO oSql = new SqlParametersTO();
		oSql.setEsquemaorigen("siagf");
		oSql.setEsquemadestino("siagf");
		oSql.setTablaorigen("siagfca612");
		oSql.setTablatupla("");
		oSql.setTablatramo("");
		oSql.setMaxid("92000");
		//oSql.setMaxidtupla("0");
		//oSql.setMaxidtramo("0");

		try {
			ConsultaCausanteProcess process = new ConsultaCausanteProcess(oSql);
			process.process();
		} catch (Tupla2Exception e) {
			e.printStackTrace();
		}
	}

	public Object getMessage() {
		return msg;
	}
}
