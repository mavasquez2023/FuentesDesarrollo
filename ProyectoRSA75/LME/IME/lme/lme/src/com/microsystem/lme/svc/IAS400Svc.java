/*
 * Created on 11-10-2011
 *
 */

package com.microsystem.lme.svc;

import java.util.List;
import java.util.Map;

import com.microsystem.lme.ibatis.domain.Ilf1000VO;
import com.microsystem.lme.ibatis.domain.Ilfe000VO;
import com.microsystem.lme.ibatis.domain.Ilfe002InversoVO;
import com.microsystem.lme.ibatis.domain.Ilfe002VO;
import com.microsystem.lme.ibatis.domain.Ilfe011VO;
import com.microsystem.lme.ibatis.domain.Ilfe013VO;
import com.microsystem.lme.ibatis.domain.Ilfe021VO;
import com.microsystem.lme.ibatis.domain.Ilfe031VO;
import com.microsystem.lme.ibatis.domain.Ilfe033VO;
import com.microsystem.lme.ibatis.domain.Ilfe034VO;
import com.microsystem.lme.ibatis.domain.Ilfe051RVO;
import com.microsystem.lme.ibatis.domain.Ilfe051VO;
import com.microsystem.lme.ibatis.domain.Ilfe080VO;
import com.microsystem.lme.ibatis.domain.Ilfe081VO;
import com.microsystem.lme.ibatis.domain.Ilfe082VO;
import com.microsystem.lme.ibatis.domain.IlfeOpeVO;
import com.microsystem.lme.ibatis.domain.LmeLogVO;
import com.microsystem.lme.ibatis.domain.UrlBorderVO;
import com.microsystem.lme.svc.exception.SvcException;

/**
 * @author microsystem
 *
 */

public interface IAS400Svc {

	public void closeSqlMap();

	public int deleteIlfe000();

	/***********/

	//new delete ilfe00inilfe2R
	public int deleteIlfe000InIlfe002R();

	public int deleteIlfe002();

	public int deleteIlfe002R(Map h);

	public int deleteIlfe003();

	public int deleteIlfe003R(Map h);

	public int deleteIlfe004();

	public int deleteIlfe004R(Map h);

	public int deleteIlfe005();

	public int deleteIlfe005R(Map h);

	public int deleteIlfe006();

	public int deleteIlfe007();
	
	public int deleteIlfe008();
	
	public int deleteIlfe009();

	public int deleteIlfe051(Map h);
	
	public int deleteIlfe080();

	public int deleteIlfe080(Ilfe080VO vo);
	
	public int deleteIlfe081(Ilfe081VO vo);

	public boolean existeEnIlfe000(Ilfe000VO vo);

	public List getIdeOpe(UrlBorderVO vo) throws SvcException; /*11-01-2013*/

	public List getIlf1000(Ilf1000VO vo) throws SvcException;

	public List getIlfe002R(Ilfe002VO vo) throws SvcException;

	public List getIlfe011() throws SvcException;

	public List getIlfe013(Ilfe013VO vo) throws SvcException;

	public List getIlfe021() throws SvcException;

	public List getIlfe031(Ilfe031VO ilfe031VO) throws SvcException;

	public List getIlfe033(Ilfe033VO vo) throws SvcException;

	public List getIlfe034(Ilfe034VO vo) throws SvcException;

	public List getIlfe051() throws SvcException; /*20120818*/

	public List getIlfe051R() throws SvcException;

	public List getIlfeOpe(IlfeOpeVO vo) throws SvcException;

	public List getInverso(Ilfe002InversoVO vo) throws SvcException;

	public List getLicencias() throws SvcException;

	public List getLicenciasMixtas() throws SvcException;

	public List getLmeCero() throws SvcException;

	public List getLog(LmeLogVO logVO) throws SvcException;

	public List getOpeVordel(UrlBorderVO vo) throws SvcException;

	public String getUrlVordel(UrlBorderVO vo) throws SvcException;
	
	public List getIlfe080() throws SvcException;
	
	public List getIlfe081() throws SvcException;

	public String insertIlfe000(Ilfe000VO vo);

	public String insertIlfe000HER();

	public String insertIlfe002(Map h);

	public String insertIlfe003(Map h);

	public String insertIlfe004(Map h);

	public String insertIlfe005(Map h);

	public String insertIlfe006(Map h);

	public String insertIlfe007(Map h); //new

	public String insertIlfe008(Map h);

	public String insertIlfe009(Map h);

	public String insertIlfeRED(Map h);

	public String insertLog(LmeLogVO log);
	
	public String insertIlfe080(Ilfe080VO vo);
	
	public String insertIlfe081(Ilfe081VO vo);
	
	//Nuevo
	public boolean insertIlfe082(Ilfe082VO vo) throws SvcException;
	public List getIlfe082() throws SvcException;
	public int deleteIlfe082(Ilfe082VO vo) throws SvcException;

	public int updateIlfe000(Map h);

	public int updateIlfe002R(Map h);

	public int updateIlfe002RError(Map h);
	
	public int updateIlfe080(Ilfe080VO vo);

	public int updateIlfe011(Ilfe011VO vo);

	public int updateIlfe021(Ilfe021VO vo);

	public int updateIlfe031(Ilfe031VO vo);

	public int updateIlfe031(Map h);

	public int updateIlfe051(Ilfe051VO vo); /*20120819**/

	public int updateIlfe051R(Ilfe051RVO vo);

	public boolean updateLMECero(Map h);

	public boolean updateLMECeroNumImprela(Map h);

	public boolean updateLMECeroNumImprela8600(Map h);

}
