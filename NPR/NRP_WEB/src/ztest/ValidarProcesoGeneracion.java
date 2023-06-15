package ztest;

import cl.jfactory.planillas.service.helper.GeneradorNominasHelper;

public class ValidarProcesoGeneracion {
	public static void main(String[] args) {
		boolean resultado = GeneradorNominasHelper.validarProcesoGeneracion();
		System.out.println("resultado: "+ resultado);
	}
}
