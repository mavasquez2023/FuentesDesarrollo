package cl.laaraucana.mandato.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.laaraucana.mandato.ibatis.vo.MandatoAS400Vo;

public interface MandatoAS400Service {

	public List<MandatoAS400Vo> consultaMandatos(int rut) throws Exception;

	public void insertMandato(int rut, MandatoAS400Vo mandatos) throws Exception;
	
	public boolean deleteMandato(int rut) throws Exception;
	
	public boolean deleteMandatoById(long id) throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatosRev(int rut) throws Exception;
	
	public List<MandatoAS400Vo> consultaMandatosRechazados(int rut) throws Exception;

	public MandatoAS400Vo consultaMandatosById(long id) throws Exception;

	public MandatoAS400Vo consultaMandatosRevById(long id) throws Exception;

	public long consultaMandatosGetId() throws Exception;

	public MandatoAS400Vo consultaMandatoxRechazo(HashMap<String, String> params) throws Exception;
	
	public boolean rechazaMandato(MandatoAS400Vo mandato) throws Exception;
	
}
