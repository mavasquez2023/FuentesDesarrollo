package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.comun.conx.DB2DAOFactory;
import cl.laaraucana.simat.dao.InformeFinancieroDAO;
import cl.laaraucana.simat.dao.PaseContableDAO;
import cl.laaraucana.simat.entidades.CuentaPaseContableVO;
import cl.laaraucana.simat.entidades.InformeFinancieroVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2InformeFinancieroDAO implements InformeFinancieroDAO {

	public void Eliminar(InformeFinancieroVO informeFinanciero) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.delete("delInformeFinanciero", informeFinanciero);
	}

	public void Insertar(InformeFinancieroVO informeFinanciero) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.insert("insertarInformeFinanciero", informeFinanciero);
	}

	public ArrayList BuscarByPeriodo(String periodo) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			ArrayList listaInforme = new ArrayList();
			listaInforme = (ArrayList) sqlMap.queryForList("getInformeFinancieroByPeriodo", periodo);
			return listaInforme;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList getTodoInformeFinanciero() throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			ArrayList listaInforme = new ArrayList();
			Integer i = null;
			listaInforme = (ArrayList) sqlMap.queryForList("getTodoInformeFinanciero", i);
			return listaInforme;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Obtiene los resultados de las cuentas de los planos
	 */
	public HashMap<String, Long> getResultadoPlanos() throws Exception {
		SqlMapClient sqlMap = DB2DAOFactory.getDB2Connection();
		HashMap<String, Long> resultadoInforme = new HashMap<String, Long>();
		resultadoInforme  =(HashMap<String, Long>) sqlMap.queryForMap("resultadoPlanos",null,"key", "value");
		return resultadoInforme;
	}
	
/*	public static void main(String[] args) throws Exception {
		InformeFinancieroDAO paseDao = DAOFactory.getDAOFactory(DAOFactory.DB2).getInformeFinancieroDAO();
		HashMap<String, Long> resultadoInforme = paseDao.getResultadoPlanos();
		
		for (String name: resultadoInforme.keySet()){
	        System.out.println(name);
		} 
	}*/
}
