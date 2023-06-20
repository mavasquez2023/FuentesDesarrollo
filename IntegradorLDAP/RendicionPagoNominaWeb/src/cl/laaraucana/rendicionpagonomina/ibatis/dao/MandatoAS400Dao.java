package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.List;


import cl.laaraucana.rendicionpagonomina.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.RechazoVo;


public interface MandatoAS400Dao {
	
	
	public List<MandatoAS400Vo> consultaMandatos(int rut) throws Exception;
	
	public void insertMandatoRechazado(RechazoVo mandato) throws Exception;
	
	public int deleteMandatoRechazado(long rutafi) throws Exception;
	
}
