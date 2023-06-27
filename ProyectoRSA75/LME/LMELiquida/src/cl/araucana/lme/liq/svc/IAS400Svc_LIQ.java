/*
 * Created on 11-10-2011
 *
 */

package cl.araucana.lme.liq.svc;

import java.util.List;
import java.util.Map;

import cl.araucana.lme.liq.ibatis.domain.Ilfe011VO;
import cl.araucana.lme.liq.ibatis.domain.Ilfe031VO;
import cl.araucana.lme.liq.ibatis.domain.Ilfe033VO;
import cl.araucana.lme.liq.ibatis.domain.IlfeOpeVO;
import cl.araucana.lme.liq.ibatis.domain.LmeLogVO;
import cl.araucana.lme.liq.ibatis.domain.ParametroVO;
import cl.araucana.lme.liq.svc.exception.SvcException;


/**
 * @author j-factory
 * 
 */

public interface IAS400Svc_LIQ {

	
	public List getIlfe011_Consumo(Map param) throws SvcException;
	
	public int limpiaIlfe011(Ilfe011VO vo);
	
	public Object getIlfe031 (Ilfe011VO vo) throws SvcException;
	
	public int updateIlfe031(Ilfe031VO vo); 
	
	public String insertIlfe033(Ilfe033VO vo);
	
	public List getIlfeOpe(IlfeOpeVO vo) throws SvcException; 

	public List getLog(LmeLogVO logVO) throws SvcException; 
		
	public String insertLog(LmeLogVO log); 
	
	public Map getEndPoints() throws Exception; 
	
	public Map getParametrosBd() throws SvcException;
	
	public int updateParametro(ParametroVO entrada) throws SvcException;
	
	public String insertEstadistica(Map h);
	
	public void startTransaction() throws Exception;
	
	public void endTransaction() throws Exception;
	
	public void commitTransaction() throws Exception;
	
	public void closeConnection() throws Exception;

}
