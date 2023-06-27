/*
 * Created on 11-10-2011
 *
 */

package cl.araucana.lme.svc;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cl.araucana.lme.ibatis.domain.Ilf1000VO;
import cl.araucana.lme.ibatis.domain.Ilfe000VO;
import cl.araucana.lme.ibatis.domain.Ilfe002VO;
import cl.araucana.lme.ibatis.domain.Ilfe004VO;
import cl.araucana.lme.ibatis.domain.Ilfe021VO;
import cl.araucana.lme.ibatis.domain.Ilfe051VO;
import cl.araucana.lme.ibatis.domain.IlfeOpeVO;
import cl.araucana.lme.ibatis.domain.LmeLogVO;
import cl.araucana.lme.ibatis.domain.ParametroVO;
import cl.araucana.lme.ibatis.domain.UrlBorderVO;
import cl.araucana.lme.svc.exception.SvcException;


/**
 * @author j-factory
 * 
 */

public interface IAS400Svc_LME {

	public List getIdeOpe(UrlBorderVO vo) throws SvcException; 
	
	public List getIlfe002R_Consumo(Map param) throws SvcException;
	
	public Object getIlfe004R(Map param) throws SvcException;
	
	public Object getIlfe009R(Map param) throws SvcException;
	
	public int getIlfe051(Ilfe002VO cab_licencia) throws SvcException;
	
	public int existsIlfe051(Ilfe051VO vo) throws SvcException;
	
	public boolean getAF03F1(int rutEmpresa, int rutAfiliado);

	public List getIlfeOpe(IlfeOpeVO vo) throws SvcException; 

	public List getLog(LmeLogVO logVO) throws SvcException; 

	public List getOpeVordel(UrlBorderVO vo) throws SvcException; 
	
	public int updateIlfe002R(Map h);

	public int updateIlfe002RError(Map h);
	
	public boolean updateLMECeroNumImprela(Map h);

	public boolean updateLMECeroNumImprela8600(Map h);

	public String insertIlfe002(Map h); 

	public String insertIlfe004(Map h); 

	public String insertIlfe005(Map h); 

	public String insertIlfe006(Map h); 

	public String insertIlfe007(Map h); 

	public String insertIlfe008(Map h); 

	public String insertIlfe009(Map h);
	
	public String insertIlfe021(Ilfe021VO vo); 
	
	public String insertIlfe051(Ilfe051VO vo);
	
	public int updateIlfe051(Ilfe051VO vo);
	
	public String updateIlf8600(Map h);

	public String insertLog(LmeLogVO log); 
	
	public Map getEndPoints() throws Exception; 
	
	public Map getParametrosBd() throws SvcException;
	
	public int updateParametro(ParametroVO entrada) throws SvcException;
	
	public int getIndiceConvenio(int rutemp, int rutafi);
	
	public String insertEstadistica(Map h);
	
	public Map getLicContinua(Map h);
	
	public Map getLicMismoPeriodo(Map h);
	
	public int getArchivoRentas(Map h);
	
	public List getEstadisticas(int periodo) throws SvcException;
	
	public void startTransaction() throws Exception;
	
	public void endTransaction() throws Exception;
	
	public void commitTransaction() throws Exception;
	
	public void closeConnection() throws Exception;

}
