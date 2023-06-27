package cl.araucana.wsatento.business.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.araucana.wsatento.business.dao.AbstractDao;
import cl.araucana.wsatento.business.dao.UsuarioDao;
import cl.araucana.wsatento.business.to.EntidadTO;
import cl.araucana.wsatento.business.to.UsuarioTO;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

public class UsuarioDaoImpl extends AbstractDao implements UsuarioDao {
	
	
	public UsuarioDaoImpl() throws WSAtentoException{
		openConnection();
	}

	public List getUsuarios() throws WSAtentoException{
		List listaUsuarios = new ArrayList();
		String call = "{ call PSOBJ.GET_USUARIOS() }";
		try{
			CallableStatement cstmt = getConnection().prepareCall(call);
			 
			ResultSet rsUsuarios = cstmt.executeQuery();
			 
			while(rsUsuarios.next()){
				UsuarioTO objUsu = new UsuarioTO();
				objUsu.setUId(new Integer(rsUsuarios.getInt(1)));
				objUsu.setNombre(rsUsuarios.getString(2));
				objUsu.setUsuario(rsUsuarios.getString(3));
				objUsu.setClave(rsUsuarios.getString(4));
				objUsu.setEstado(rsUsuarios.getString(5));
				
				EntidadTO objEnt = new EntidadTO();
				objEnt.setEId(new Integer(rsUsuarios.getInt(6)));
				objEnt.setNombre(rsUsuarios.getString(7));
				objEnt.setNombreLargo(rsUsuarios.getString(8));
				objEnt.setRut(rsUsuarios.getString(9));
				objEnt.setEstado(rsUsuarios.getString(10));
				
				objUsu.setEntidad(objEnt);
				
				listaUsuarios.add(objUsu);
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new WSAtentoException("0021","Error interno, comuniquese con el administrador.");
		}catch (Exception e){
			e.printStackTrace();
			throw new WSAtentoException("0022", "Error interno, comuniquese con el administrador.");
		}finally{
			closeConnection();
		}
		
		return listaUsuarios;
	}
}
