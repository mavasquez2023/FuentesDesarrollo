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
import com.microsystem.lme.ibatis.domain.ParametroVO;
import com.microsystem.lme.ibatis.domain.UrlBorderVO;
import com.microsystem.lme.svc.exception.SvcException;

/**
 * @author microsystem
 * 
 */

public interface IAS400Svc_LME {


	public int deleteIlfe000(); // dejar

	public int deleteIlfe002(); // dejar

	public int deleteIlfe003(); // dejar

	public int deleteIlfe004(); // dejar

	public int deleteIlfe005(); // dejar

	public int deleteIlfe006(); // dejar

	public int deleteIlfe007(); // dejar

	public int deleteIlfe008(); // dejar

	public int deleteIlfe009(); // dejar

	public int deleteIlfe051(Map h); // dejar

	public int deleteIlfe081(Ilfe081VO vo); // dejar
	
	public int deleteIlfe085(String estado); // dejar

	public List getIdeOpe(UrlBorderVO vo) throws SvcException; // dejar

	public List getIlfe011() throws SvcException; // dejar

	public List getIlfe013(Ilfe013VO vo) throws SvcException; // dejar

	public List getIlfe021() throws SvcException; // dejar

	public List getIlfe031(Ilfe031VO ilfe031VO) throws SvcException; // dejar

	public List getIlfe033(Ilfe033VO vo) throws SvcException; // dejar

	public List getIlfe034(Ilfe034VO vo) throws SvcException; // dejar

	public List getIlfe051() throws SvcException; // dejar

	public List getIlfe051R() throws SvcException; // dejar

	public List getIlfeOpe(IlfeOpeVO vo) throws SvcException; // dejar

	public List getLog(LmeLogVO logVO) throws SvcException; // dejar

	public List getOpeVordel(UrlBorderVO vo) throws SvcException; // dejar

	public List getIlfe080() throws SvcException; // dejar
	
	public List getIlfe085() throws SvcException; // dejar

	public List getIlfe081() throws SvcException; // dejar

	public String insertIlfe000(Ilfe000VO vo); // dejar

	public String insertIlfe000HER(); // dejar

	public String insertIlfe002(Map h); // dejar

	public String insertIlfe003(Map h); // dejar

	public String insertIlfe004(Map h); // dejar

	public String insertIlfe005(Map h); // dejar

	public String insertIlfe006(Map h); // dejar

	public String insertIlfe007(Map h); // dejar

	public String insertIlfe008(Map h); // dejar

	public String insertIlfe009(Map h); // dejar

	public String insertIlfeRED(Map h); // dejar

	public String insertLog(LmeLogVO log); // dejar

	public String insertIlfe080(Ilfe080VO vo); // dejar
	
	public String insertIlfe085(Ilfe080VO vo); // dejar

	public String insertIlfe081(Ilfe081VO vo); // dejar

	public boolean insertIlfe082(Ilfe082VO vo) throws SvcException; // dejar

	public int deleteIlfe082(Ilfe082VO vo) throws SvcException; // dejar

	public int updateIlfe000(Map h); // dejar

	public int updateIlfe080(Ilfe080VO vo); // dejar
	
	public int updateIlfe085(Ilfe080VO vo); // dejar

	public int updateIlfe011(Ilfe011VO vo); // dejar

	public int updateIlfe021(Ilfe021VO vo); // dejar

	public int updateIlfe031(Ilfe031VO vo); // dejars

	public int updateIlfe031(Map h); // dejar

	public int updateIlfe051(Ilfe051VO vo); // dejar

	public int updateIlfe051R(Ilfe051RVO vo); // dejar

	public Map getEndPoints() throws Exception; // dejar
	
	public Map getParametrosBd() throws SvcException;
	
	public int updateParametro(ParametroVO entrada) throws SvcException;

}
