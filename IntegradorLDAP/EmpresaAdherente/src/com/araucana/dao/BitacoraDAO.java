/**
 * 
 */
package com.araucana.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.araucana.entity.BitacoraVo;

/**
 * @author 13247428-1
 *
 */
public class BitacoraDAO {
	

		public int insertBitacora(BitacoraVo bitacoraVO) {
			SqlSessionFactory sqlSession =null;
			SqlSession session = null;
			int count =0;
			try {
				sqlSession = SessionFactory.getSessionFactory();
				session = sqlSession.openSession();
				count = session.insert("Empresas.insertBitacora", bitacoraVO);
				
			} catch (Exception e) {
				e.printStackTrace();
				count=0;
			} finally {
				session.close();
			}

			return count;
		}
}
