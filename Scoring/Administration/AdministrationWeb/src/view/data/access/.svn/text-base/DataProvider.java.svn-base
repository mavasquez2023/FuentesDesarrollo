package view.data.access;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import view.data.CondicionesStruct;
import view.data.CondicionMasterKey;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;


public class DataProvider {

	private static final String IBATIS_XML_CONFIG_FILE = "sql-map-config.xml";

	private static Logger logger = Logger.getLogger(DataProvider.class.getName());

	private static SqlMapClient sqlMap;
	

	public DataProvider() throws DataAccessException {
		String resource = IBATIS_XML_CONFIG_FILE;
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);			
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (IOException e) {
			throw new DataAccessException(e);
		}
	}
		
	public CondicionesStruct findSingleCondicionesStruct(CondicionMasterKey key) throws DataAccessException{
		CondicionesStruct condicionMasterInstance;
		try {
			condicionMasterInstance = (CondicionesStruct) sqlMap.queryForObject("dbo_MatrizCondicionOtorgamiento.selectSingleMaster", key);
			List detailList = findCondicionesAsociadas(key);
			condicionMasterInstance .setCondiciones(detailList);			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}		
		return condicionMasterInstance;
	}
	
	public List selectAllCondicionesStructs() throws DataAccessException {	
		List master;
		try {
			master = sqlMap.queryForList("dbo_MatrizCondicionOtorgamiento.selectAllMaster", null);
			for (Iterator iterator = master.iterator(); iterator.hasNext();) {
				CondicionesStruct masterRecord = (CondicionesStruct) iterator.next();
				List detailList = findCondicionesAsociadas(masterRecord.getKey());
				masterRecord.setCondiciones(detailList);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);		
		}
		return master;
	}
	
	private List findCondicionesAsociadas(CondicionMasterKey key) throws DataAccessException {
		try {
			List masterList = sqlMap.queryForList("dbo_MatrizCondicionOtorgamiento.selectDetail", key);
			return masterList;			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void deleteCondicionesAsociadas(CondicionMasterKey key) throws DataAccessException {
		try {
			sqlMap.delete("dbo_MatrizCondicionOtorgamiento.deleteCondiciones", key);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}		
	}

	public void insertMatrizCondicionRecord(int tipoRiesgo, int tipoRenta, int perfilRiesgo, String idCondicion) throws DataAccessException {
		Map data = new Hashtable();
		try {
			data.put("idtiporiesgoexterno",new Integer(tipoRiesgo));
			data.put("idtiporentapermitida",new Integer(tipoRenta));
			data.put("idperfilriesgo",new Integer(perfilRiesgo));
			data.put("idcondicion",new Integer(idCondicion));
			sqlMap.insert("dbo_MatrizCondicionOtorgamiento.insertCondicion", data);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}		
	}
	
	
}
