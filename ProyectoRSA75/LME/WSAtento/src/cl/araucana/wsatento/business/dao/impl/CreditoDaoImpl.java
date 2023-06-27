package cl.araucana.wsatento.business.dao.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cl.araucana.wsatento.business.dao.AbstractDao;
import cl.araucana.wsatento.business.dao.CreditoDao;
import cl.araucana.wsatento.business.to.CreditoTO;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

public class CreditoDaoImpl extends AbstractDao implements CreditoDao {
	
	
	public CreditoDaoImpl() throws WSAtentoException {
		openConnection();
	}
	public List getCreditosByRut(Integer rut) throws WSAtentoException{
		List listaCreditos = new ArrayList();
		String call = "{ call PSOBJ.GET_CREDITOS(?) }";
		try{
			CallableStatement cstmt = getConnection().prepareCall(call);
			cstmt.setInt(1, rut.intValue());
			 
			ResultSet rsCreditos = cstmt.executeQuery();
			
			SimpleDateFormat sdfyyyyMMdd = new SimpleDateFormat("yyyyMMdd"); 
			
			if(rsCreditos.next()){
				if(rsCreditos.getString(1) != null){
					CreditoTO objCred = new CreditoTO();
					objCred.setFechaPago(sdfyyyyMMdd.parse(rsCreditos.getString(1)));
					listaCreditos.add(objCred);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new WSAtentoException("0352","Error interno, comuniquese con el administrador.");
		}catch(ParseException e){
			e.printStackTrace();
			throw new WSAtentoException("0353","Error interno, comuniquese con el administrador.");
		}catch (Exception e){
			e.printStackTrace();
			throw new WSAtentoException("0351", "Error interno, comuniquese con el administrador.");
		}finally{
			closeConnection();
		}
		
		return listaCreditos;
	}

	public List getCreditosByRutQuery(Integer rut) throws WSAtentoException {
		List listaCreditos = new ArrayList();
		
		try{
			String sql = "SELECT	MAX((CASE TRIM(CHAR_LENGTH(CAST(A.CREOTOFEC AS VARCHAR(6)))) " +
						"					WHEN 3 THEN CAST(CONCAT('20000', CAST(A.CREOTOFEC AS CHAR(3))) AS DEC (10,0)) " +
						"					WHEN 4 THEN CAST(CONCAT('2000', CAST(A.CREOTOFEC AS CHAR(4))) AS DEC (10,0)) " +
						"					WHEN 5 THEN CAST(CONCAT('200' , CAST(A.CREOTOFEC AS CHAR(5))) AS DEC (10,0)) " +
						"					WHEN 6 THEN " +
						"						CASE  WHEN CAST(SUBSTRING(A.CREOTOFEC,1,2) AS DEC(2,0))<=60 " +
						"							THEN CAST(CONCAT('20'  , CAST(A.CREOTOFEC AS CHAR(6))) AS DEC (10,0)) " +
						"							ELSE CAST(CONCAT('19'  , CAST(A.CREOTOFEC AS CHAR(6))) AS DEC (10,0)) " +
						"						END " +
						"				END)) AS CREOTOFEC " + 
						"FROM CRDTA.CSF1000 A, " + 
						"	CRDTA.CRF1470 B " +
						"WHERE 	A.AFIRUT = ? " +
						"AND A.CREESTPTM IN (0) " + 
						"AND A.CRELIN IN (10, 11, 12, 13, 14, 16, 18) " +
						"AND A.OFIPRO=B.OFIPRO " +
						"AND A.CREFOL=B.CREFOL " +
						"AND EXISTS(SELECT '1' " +
						"			FROM TEDTA.TE07F1 C " + 
						"			WHERE C.TE3WA=B.TE3WA " +
						"			AND C.TE3YA IN('G','I')) ";
			
			
			
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setInt(1, rut.intValue());
			
			
			ResultSet rsCreditos = pstmt.executeQuery();
			
			SimpleDateFormat sdfyyyyMMdd = new SimpleDateFormat("yyyyMMdd"); 
			
			while(rsCreditos.next()){
				CreditoTO objCred = new CreditoTO();
//				objCred.setFechaPago(sdfyyyyMMdd.parse(rsCreditos.getString(1)));
				objCred.setFechaPago(sdfyyyyMMdd.parse("20130412"));
				listaCreditos.add(objCred);
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new WSAtentoException("0352","Error interno, comuniquese con el administrador.");
		}catch (ParseException e){
			e.printStackTrace();
			throw new WSAtentoException("0353","Error interno, comuniquese con el administrador.");
		}catch (Exception e){
			e.printStackTrace();
			throw new WSAtentoException("0351","Error interno, comuniquese con el administrador.");
		}finally{
			closeConnection();
		}
		return listaCreditos;
	}

}
