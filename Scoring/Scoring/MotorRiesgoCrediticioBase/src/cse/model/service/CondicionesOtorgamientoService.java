package cse.model.service;

import cse.model.businessobject.Monto;
import cse.model.businessobject.Rut;
import cse.model.exception.ScoringModuleException;
import cse.model.service.data.EvaluarCondicionesResponse;


public interface CondicionesOtorgamientoService {

	
	public EvaluarCondicionesResponse evaluarCondicionesOtorgamiento(Rut rutSolicitante, Monto montoSolicitado, int tipoAfiliado) throws ScoringModuleException;

	public EvaluarCondicionesResponse evaluarCondicionesOtorgamientoFull(
			Rut myRut,
			int tipoAfiliado,
			Monto myMonto,
			String genero,
			String fechaNac,
			String estadoCivil,
			double remuneracion,
			String diasMorosidad,
			int numCreditosAnteriores,
			int numDiasLicencia,
			String antiguedadLaboral,
			Rut empRut,
			String clasifRiesgoEmpresa
			
			) throws ScoringModuleException;
	
}
