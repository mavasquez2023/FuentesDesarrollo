package cl.laaraucana.rendicionpagonomina.services;

import java.util.List;

import cl.laaraucana.rendicionpagonomina.ibatis.vo.MandatoAS400Vo;



public interface MandatoAS400Service {

	public List<MandatoAS400Vo> consultaMandatos(int rut) throws Exception;

	public int revocarMandato(String banco, String codigoRechazo, int rut, int codbanco, long cuenta) throws Exception;
	

}
