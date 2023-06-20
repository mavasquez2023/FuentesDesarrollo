package cl.laaraucana.licenciasivr.ibatis.dao;

import java.sql.SQLException;
import java.util.List;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.laaraucana.licenciasivr.ibatis.config.SqlMapLocator;
import cl.laaraucana.licenciasivr.vo.EmpresaVO;

public class EmpresaDAO {

	public EmpresaVO getEmpresa(EmpresaVO empresa) throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstanceAS400();
		@SuppressWarnings("unchecked")
		List<EmpresaVO> resultado = (List<EmpresaVO>)sqlMap.queryForList("licencias.getEmpresa", empresa );
		if(null != resultado ) {
			return resultado.size()!=0?resultado.get(0):null;
		} else {
			return null;
		}
	}
	
	public Integer getILF3500(Integer folioPago) throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstanceAS400();
		return (Integer) sqlMap.queryForObject("licencias.getILF3500", folioPago );
	}
	
	public String getILF4500A(Integer folioPago) throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstanceAS400();
		return (String) sqlMap.queryForObject("licencias.getILF4500A", folioPago );
	}
	
	public String getTE07F1(Integer folioPago) throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstanceAS400();
		return (String) sqlMap.queryForObject("licencias.getTE07F1", folioPago );
	}
}
