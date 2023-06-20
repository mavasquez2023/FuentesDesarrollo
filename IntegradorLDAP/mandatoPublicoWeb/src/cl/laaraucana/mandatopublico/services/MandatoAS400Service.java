package cl.laaraucana.mandatopublico.services;

import java.util.List;

import cl.laaraucana.mandatopublico.ibatis.vo.MandatoAS400Vo;

public interface MandatoAS400Service {

	public List<MandatoAS400Vo> consultaMandatos(int rut) throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatosRev(int rut) throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatosRechazados(int rut) throws Exception;

	public MandatoAS400Vo consultaMandatosById(long id) throws Exception;

	public MandatoAS400Vo consultaMandatosRevById(long id) throws Exception;

	public long consultaMandatosGetId() throws Exception;


}
