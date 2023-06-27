package cl.araucana.wsatento.business.dao.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cl.araucana.wsatento.business.dao.AbstractDao;
import cl.araucana.wsatento.business.dao.LicenciaDao;
import cl.araucana.wsatento.business.to.LicenciaTO;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

import java.text.SimpleDateFormat;

public class LicenciaDaoImpl extends AbstractDao implements LicenciaDao {
	
	
	public LicenciaDaoImpl() throws WSAtentoException{
		openConnection();
	}

	public List getLicenciasByRut(Integer rut) throws WSAtentoException{
		List listaLicencias = new ArrayList();
		String call = "{ call PSOBJ.GET_LICENCIAS(?) }";
		try{
			CallableStatement cstmt = getConnection().prepareCall(call);
			cstmt.setInt(1, rut.intValue());
			 
			ResultSet rsLicencias = cstmt.executeQuery();
			 
			SimpleDateFormat sdfyyyyMMdd = new SimpleDateFormat("yyyyMMdd"); 
			while(rsLicencias.next()){
				LicenciaTO objLic = new LicenciaTO();
				objLic.setCompin(new Integer(rsLicencias.getInt(1)));
				objLic.setFechaDesde(sdfyyyyMMdd.parse(rsLicencias.getBigDecimal(4).toString()));
				objLic.setDias(new Integer(rsLicencias.getInt(5)));
				objLic.setCodSucPago(rsLicencias.getInt(6) + "");
				objLic.setFechaPago(sdfyyyyMMdd.parse(rsLicencias.getBigDecimal(7).toString()));
				objLic.setTipo(new Integer(rsLicencias.getInt(8)));
				listaLicencias.add(objLic);
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new WSAtentoException("0252","Error interno, comuniquese con el administrador.");
		}catch(ParseException e){
			e.printStackTrace();
			throw new WSAtentoException("0253","Error interno, comuniquese con el administrador.");
		}catch (Exception e){
			e.printStackTrace();
			throw new WSAtentoException("0251", "Error interno, comuniquese con el administrador.");
		}finally{
			closeConnection();
		}
		
		return listaLicencias;
	}
}
