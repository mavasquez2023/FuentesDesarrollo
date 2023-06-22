package cse.model.service;

import cse.model.businessobject.InformacionLaboral;
import cse.model.businessobject.Rut;
import cse.model.service.exception.InfoLaboralException;

public interface InformacionLaboralService {

	InformacionLaboral findInformacionLaboral(Rut rutEmpleado) throws InfoLaboralException;

}
