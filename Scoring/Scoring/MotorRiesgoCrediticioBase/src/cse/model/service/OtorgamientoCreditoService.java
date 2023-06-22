package cse.model.service;

import cse.model.businessobject.EvaluacionRequest;
import cse.model.businessobject.EvaluacionResponse;
import cse.model.exception.EvaluadorCreditoException;


public interface OtorgamientoCreditoService {
	

public EvaluacionResponse evaluarCondicionesOtorgamiento(EvaluacionRequest request) throws EvaluadorCreditoException;	
	
	
}
