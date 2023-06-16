package cl.ccaf.previpass.test;

import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.ccaf.previpass.dao.PrevipassDAO;
import cl.ccaf.previpass.util.SqlMapLocator;

public class TestLocal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlMapClient sql = SqlMapLocator.getInstance();
		HashMap data = new HashMap();
		data.put("nombre", "Luis&andresñasdf>");
		PrevipassDAO.ejecutarInsert(sql, "custom.insertDataTest", data);
		
		System.out.println("data : "+ PrevipassDAO.obtenerRegistros("custom.obtenerDataTest", null));
	}

}
