package cl.laaraucana.licenciasivr.ibatis.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.laaraucana.licenciasivr.ibatis.config.SqlMapLocator;
import cl.laaraucana.licenciasivr.vo.ConsultaVO;
import cl.laaraucana.licenciasivr.vo.LicenciaVO;

import com.ibatis.sqlmap.client.SqlMapClient;


public class LicenciaDAO {

	private static Log log = LogFactory.getLog(LicenciaDAO.class);

	public List<LicenciaVO> getLicencias(ConsultaVO consulta) throws SQLException{

		SqlMapClient sqlMap = SqlMapLocator.getInstanceAS400();
		@SuppressWarnings("unchecked")
		List<LicenciaVO> resultado = (List<LicenciaVO>)sqlMap.queryForList("licencias.listaLicencias", consulta );

		return resultado;
	}

}