package ztest;

import java.util.ArrayList;

import cl.jfactory.planillas.service.util.ResultadosUtil;

public class TestLogResultados {
	public static void main(String[] args) {
		
		
		ResultadosUtil.addRegistro("err", "123456");
		ResultadosUtil.addRegistro("err", "123457");
		ResultadosUtil.addRegistro("err", "123458");
		ResultadosUtil.addRegistro("err", "123459");
		
		ResultadosUtil.registrarResultados("err", "/home/clillo/nrp/resultados/201808_gen_err");

		ResultadosUtil.addRegistro("err", "123459");
		ResultadosUtil.registrarResultados("err", "/home/clillo/nrp/resultados/201808_gen_err");

	}
}
