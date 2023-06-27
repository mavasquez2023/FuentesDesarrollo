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
import com.microsystem.lme.ibatis.domain.Ilfe080VO;
import com.microsystem.lme.svc.exception.SvcException;

/**
 * @author microsystem
 * 
 */

public interface IAS400Svc_SIL {

	public int deleteIlfe000InIlfe002R();

	public int deleteIlfe002R(Map h);

	public int deleteIlfe003R(Map h);

	public int deleteIlfe004R(Map h);

	public int deleteIlfe005R(Map h);

	public int deleteIlfe080(String estado);

	public int deleteIlfe080(Ilfe080VO vo);

	public boolean existeEnIlfe000(Ilfe000VO vo);

	public List getIlf1000(Ilf1000VO vo) throws SvcException;

	public List getIlfe002R(Ilfe002VO vo) throws SvcException;

	public List getInverso(Ilfe002InversoVO vo) throws SvcException;

	public List getLicencias() throws SvcException;

	public List getLicenciasMixtas() throws SvcException;

	public List getLmeCero() throws SvcException;

	public List getIlfe082() throws SvcException;

	public int updateIlfe002R(Map h);

	public int updateIlfe002RError(Map h);

	public boolean updateLMECero(Map h);

	public boolean updateLMECeroNumImprela(Map h);

	public boolean updateLMECeroNumImprela8600(Map h);

}
