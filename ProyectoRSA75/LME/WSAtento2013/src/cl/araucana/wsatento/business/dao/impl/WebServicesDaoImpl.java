package cl.araucana.wsatento.business.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.araucana.wsatento.business.dao.AbstractDao;
import cl.araucana.wsatento.business.dao.WebServicesDao;
import cl.araucana.wsatento.business.to.WebServicesTO;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

public class WebServicesDaoImpl extends AbstractDao implements WebServicesDao {
	
	
	public WebServicesDaoImpl() throws WSAtentoException{
		openConnection();
	}

	public List getWebServicesByUsuario(Integer uId) throws WSAtentoException{
		List listaWebSrv = new ArrayList();
		String call = "{ call PSOBJ.GET_WEBSERVICES(?) }";
		try{
			CallableStatement cstmt = getConnection().prepareCall(call);
			cstmt.setInt(1, uId.intValue());
			
			ResultSet rsWebSrv = cstmt.executeQuery();
			 
			while(rsWebSrv.next()){
				WebServicesTO objWS = new WebServicesTO();
				objWS.setWsId(new Integer(rsWebSrv.getInt(1)));
				objWS.setNombre(rsWebSrv.getString(2));
				objWS.setDescripcion(rsWebSrv.getString(3));
				objWS.setEstado(rsWebSrv.getString(4));
								
				listaWebSrv.add(objWS);
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new WSAtentoException("0023","Error interno, comuniquese con el administrador.");
		}catch (Exception e){
			e.printStackTrace();
			throw new WSAtentoException("0024", "Error interno, comuniquese con el administrador.");
		}finally{
			closeConnection();
		}
		
		return listaWebSrv;
	}
}
