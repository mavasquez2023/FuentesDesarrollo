package cl.laaraucana.benef.dao;


import java.util.List;

import org.apache.log4j.Logger;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.laaraucana.benef.config.SqlConfig;
import cl.laaraucana.benef.vo.BeneficioRequestVO;
import cl.laaraucana.benef.vo.BeneficioVO;


public class BeneficiosDAO {

	protected static Logger logger = Logger.getLogger(BeneficiosDAO.class);

	public BeneficioVO getBeneficioByCodigo(String codigo) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		BeneficioVO response = (BeneficioVO) sqlMap.queryForObject("beneficioService.selectBeneficio", codigo);

		return response;

	}
	
	public boolean actualizaBeneficio(BeneficioRequestVO beneficio) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		int resultado= sqlMap.update("beneficioService.updateBeneficio", beneficio);
		
		return resultado==1 ?true:false;
	}
	
	public List<BeneficioVO> getPendientes() throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		List<BeneficioVO> lista = (List<BeneficioVO>) sqlMap.queryForList("beneficioService.selectPendientes", null);

		return lista;

	}
	
	public boolean actualizaPendiente(String codigo) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		int resultado= sqlMap.update("beneficioService.updatePendiente", codigo);
		
		return resultado==1 ?true:false;
	}
	
}
