package cl.araucana.wsatento.business.dao.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.araucana.wsatento.business.dao.AbstractDao;
import cl.araucana.wsatento.business.dao.CalidadDao;
import cl.araucana.wsatento.business.to.CalidadTO;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

public class CalidadDaoImpl extends AbstractDao implements CalidadDao {

	public CalidadDaoImpl() throws WSAtentoException {
		openConnection();
	}

	public List getCalidadesByRut(Integer rut) throws WSAtentoException{
		List listaCalidad = new ArrayList();
		String call = "{ call PSOBJ.GET_CALIDADES(?) }";
		try{
			CallableStatement cstmt = getConnection().prepareCall(call);
			cstmt.setInt(1, rut.intValue());
			 
			ResultSet rsCalidad = cstmt.executeQuery();
			
			while(rsCalidad.next()){
				listaCalidad.add(new CalidadTO(new Integer(rsCalidad.getInt(1))));
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new WSAtentoException("0152","Error interno, comuniquese con el administrador.");
		}catch(Exception e){
			e.printStackTrace();
			throw new WSAtentoException("0151","Error interno, comuniquese con el administrador.");
		}finally{
			closeConnection();
		}
		
		return listaCalidad;
	}

	public List getCalidadByRut(Integer rut) throws WSAtentoException {
		List listaCalidad = new ArrayList();
		try{
			String sql = "SELECT C.TIPO FROM(	" +
					"SELECT 1 AS TIPO	" +
					"FROM 	AFDTA.AF03F1 A    " +
					"WHERE 	A.SE5FAR9='A'    " +
					"AND 	A.SE5FAJC=? " +
					"UNION	" +
					"SELECT 2 AS TIPO	" +
					"FROM BPAFDTA.AF03F1 A	" +
					"WHERE A.SE5FAR9='A'	" +
					"AND A.SE5FAJC=? " +
					"UNION	" +
					"SELECT 3 AS TIPO	" +
					"FROM PREXP.PEF1500 A	" +
					"WHERE A.AFIESTAFI=3	" +
					"AND A. AFIRUT=? " +
					"UNION	SELECT 4 AS TIPO	" +
					"FROM IIDTA.AFILIADO A, IIDTA.PERSONA B	" +
					"WHERE A.IDPERSONAAFILIADO = B.IDPERSONA	" +
					"AND A.TIPOESTADOAFILIADO=2	" +
					"AND B.IDDOCUMENTO=?) AS C";
			
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setInt(1, rut.intValue());
			pstmt.setInt(2, rut.intValue());
			pstmt.setInt(3, rut.intValue());
			pstmt.setInt(4, rut.intValue());
			ResultSet rsCalidad = pstmt.executeQuery();
			
			while(rsCalidad.next()){
				listaCalidad.add(new CalidadTO(new Integer(rsCalidad.getInt(1))));
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new WSAtentoException("0152","Error interno, comuniquese con el administrador.");
		}catch(Exception e){
			e.printStackTrace();
			throw new WSAtentoException("0151","Error interno, comuniquese con el administrador.");
		}finally{
			closeConnection();
		}
		return listaCalidad;
	}

}
