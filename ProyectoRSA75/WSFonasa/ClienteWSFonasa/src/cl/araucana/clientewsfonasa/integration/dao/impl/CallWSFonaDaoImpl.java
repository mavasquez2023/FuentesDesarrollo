package cl.araucana.clientewsfonasa.integration.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cl.araucana.clientewsfonasa.business.to.CallWSFonasaTO;
import cl.araucana.clientewsfonasa.business.to.RequestWSFonasaTO;
import cl.araucana.clientewsfonasa.business.to.ResponseWSFonasaTO;
import cl.araucana.clientewsfonasa.common.exception.DaoException;
import cl.araucana.clientewsfonasa.common.util.FechaUtil;
import cl.araucana.clientewsfonasa.integration.dao.AbstractDao;
import cl.araucana.clientewsfonasa.integration.dao.CallWSFonaDao;

public class CallWSFonaDaoImpl extends AbstractDao implements CallWSFonaDao{
	public CallWSFonaDaoImpl() {}

	public Integer saveCallWSFona(CallWSFonasaTO callTo) throws DaoException{
		Integer idCall = null;
		String call = "{ call LIEXP.SET_CALLWSFONA(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
		try{
			CallableStatement cstmt = getConnection().prepareCall(call);
			cstmt.setString(1, FechaUtil.formatearFecha("yyyyMMdd", callTo.getFechaHora()));
			cstmt.setString(2, FechaUtil.formatearFecha("HH:mm:ss", callTo.getFechaHora()));
			cstmt.setShort(3, callTo.getEstado().shortValue());
			cstmt.setShort(4, callTo.getTipo().shortValue());
			cstmt.setShort(5, (short)0);
			cstmt.setString(6, "");
			
			cstmt.setString(7, callTo.getRequest().getRutBeneficiario());
			cstmt.setString(8, "");
			cstmt.setString(9, "");
			cstmt.setString(10, "");
			cstmt.setString(11, "");
			
			cstmt.setShort(12, (short)0);
			cstmt.setString(13, "");
			cstmt.setString(14, "");
			cstmt.setString(15, "");
			cstmt.setString(16, "");
			cstmt.setString(17, "");
			cstmt.setString(18, "");
			cstmt.setString(19, "");
			cstmt.setString(20, "");
			
			cstmt.registerOutParameter(21, Types.INTEGER);
			cstmt.registerOutParameter(22, Types.INTEGER);
			
			cstmt.execute();
			
			if(cstmt.getInt(22) > 0){
				idCall = new Integer(cstmt.getInt(21));
			}else{
				throw new DaoException("003", "Ocurrio un problema en el SP LIEXP.SET_CALLWSFONA al insertar el registro");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException("001", "Ocurrio un problema al ejecutar el SP LIEXP.SET_CALLWSFONA");
		}catch (Exception e){
			e.printStackTrace();
			throw new DaoException("002", "Ocurrio un problema en la llamada al SP LIEXP.SET_CALLWSFONA");
		}finally{
			closeConnection();
		}
		
		return idCall;
	}
	
	public void updateCallWSFonaStep1(CallWSFonasaTO callTo) throws DaoException{
		String call = "{ call LIEXP.UPD_CALLWSFONA_STEP_1(?, ?, ?, ?, ?, ?, ?) }";
		try{
			CallableStatement cstmt = getConnection().prepareCall(call);
			cstmt.setInt(1, callTo.getIdCall().intValue());
			cstmt.setShort(2, callTo.getEstado().shortValue());
			
			cstmt.setString(3, FechaUtil.formatearFecha("yyyyMMdd", callTo.getRequest().getFecCertifica()));
			cstmt.setString(4, callTo.getRequest().getRutInstitucion());
			cstmt.setString(5, callTo.getRequest().getCodigoUsuario());
			cstmt.setString(6, callTo.getRequest().getClaveUsuario());
			
			cstmt.registerOutParameter(7, Types.INTEGER);
			
			cstmt.execute();
			
			if(cstmt.getInt(7) <= 0){
				throw new DaoException();
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}catch (Exception e){
			e.printStackTrace();
			throw new DaoException();
		}finally{
			closeConnection();
		}
	}
	
	public void updateCallWSFonaStep2(CallWSFonasaTO callTo) throws DaoException{
		String call = "{ call LIEXP.UPD_CALLWSFONA_STEP_2(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
		try{
			CallableStatement cstmt = getConnection().prepareCall(call);
			cstmt.setInt(1, callTo.getIdCall().intValue());
			cstmt.setShort(2, callTo.getEstado().shortValue());
			cstmt.setShort(3, callTo.getCodReturn().shortValue());
			cstmt.setString(4, callTo.getMsjReturn());
			
			if(callTo.getResponse() != null){
				cstmt.setShort(5, callTo.getResponse().getEstado().shortValue());
				cstmt.setString(6, callTo.getResponse().getGloEstado());
				cstmt.setString(7, callTo.getResponse().getExtApellidoPat());
				cstmt.setString(8, callTo.getResponse().getExtApellidoMat());
				cstmt.setString(9, callTo.getResponse().getExtNombres());
				cstmt.setString(10, callTo.getResponse().getExtSexo());
				cstmt.setString(11, FechaUtil.formatearFecha("yyyyMMdd", callTo.getResponse().getExtFechaNacimi()));
				cstmt.setString(12, callTo.getResponse().getExtCodEstBen());
				cstmt.setString(13, callTo.getResponse().getExtDescEstado());
			}else{
				cstmt.setNull(5, Types.SMALLINT);
				cstmt.setNull(6, Types.VARCHAR);
				cstmt.setNull(7, Types.VARCHAR);
				cstmt.setNull(8, Types.VARCHAR);
				cstmt.setNull(9, Types.VARCHAR);
				cstmt.setNull(10, Types.VARCHAR);
				cstmt.setNull(11, Types.VARCHAR);
				cstmt.setNull(12, Types.VARCHAR);
				cstmt.setNull(13, Types.VARCHAR);
			}
			cstmt.registerOutParameter(14, Types.INTEGER);
			
			cstmt.execute();
			
			if(cstmt.getInt(14) <= 0){
				throw new DaoException();
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}catch (Exception e){
			e.printStackTrace();
			throw new DaoException();
		}finally{
			closeConnection();
		}
	}
	
	public List getCallWSFona(CallWSFonasaTO callTo, Integer maxRows) throws DaoException{
		List listCalls = new ArrayList();
		String call = "{ call LIEXP.GET_CALLWSFONA(?, ?, ?) }";
		try{
			CallableStatement cstmt = getConnection().prepareCall(call);
			cstmt.setShort(1, callTo.getEstado().shortValue());
			cstmt.setShort(2, callTo.getTipo().shortValue());
			cstmt.setInt(3, maxRows.intValue());
			
			ResultSet rsCalls = cstmt.executeQuery();
			
			while(rsCalls.next()){
				CallWSFonasaTO objCall = new CallWSFonasaTO();
				objCall.setIdCall(new Integer(rsCalls.getInt(1)));
				String horaFecha = rsCalls.getString(2).trim() + " " + rsCalls.getString(3).trim();
				objCall.setFechaHora(FechaUtil.parsearFecha("yyyyMMdd HH:mm:ss", horaFecha));
				objCall.setEstado(new Short(rsCalls.getShort(4)));
				objCall.setTipo(new Short(rsCalls.getShort(5)));
				
				RequestWSFonasaTO reqTo = new RequestWSFonasaTO();
				reqTo.setRutBeneficiario(rsCalls.getString(8));
				objCall.setRequest(reqTo);
				
				listCalls.add(objCall);
			}
			
			rsCalls.close();
			cstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}catch (Exception e){
			e.printStackTrace();
			throw new DaoException();
		}finally{
			closeConnection();
		}
		
		return listCalls;
	}
	
	public CallWSFonasaTO getCallWSFonaById(Integer idCall) throws DaoException{
		List listCalls = new ArrayList();
		String call = "{ call LIEXP.GET_CALLWSFONABYID(?) }";
		try{
			CallableStatement cstmt = getConnection().prepareCall(call);
			cstmt.setInt(1, idCall.intValue());
			
			ResultSet rsCalls = cstmt.executeQuery();
			
			while(rsCalls.next()){
				CallWSFonasaTO objCall = new CallWSFonasaTO();
				objCall.setIdCall(new Integer(rsCalls.getInt(1)));
				String horaFecha = rsCalls.getString(2).trim() + " " + rsCalls.getString(3).trim();
				objCall.setFechaHora(FechaUtil.parsearFecha("yyyyMMdd HH:mm:ss", horaFecha));
				objCall.setEstado(new Short(rsCalls.getShort(4)));
				objCall.setTipo(new Short(rsCalls.getShort(5)));
				objCall.setCodReturn(new Short(rsCalls.getShort(6)));
				objCall.setMsjReturn(rsCalls.getString(7));
				
				RequestWSFonasaTO reqTo = new RequestWSFonasaTO();
				reqTo.setRutBeneficiario(rsCalls.getString(8));
				reqTo.setFecCertifica(FechaUtil.parsearFecha("yyyyMMdd", rsCalls.getString(9)));
				reqTo.setRutInstitucion(rsCalls.getString(10));
				reqTo.setCodigoUsuario(rsCalls.getString(11));
				reqTo.setClaveUsuario(rsCalls.getString(12));
				objCall.setRequest(reqTo);
				
				ResponseWSFonasaTO resTo = new ResponseWSFonasaTO();
				resTo.setEstado(new Short(rsCalls.getShort(13)));
				resTo.setGloEstado(rsCalls.getString(14));
				resTo.setExtApellidoPat(rsCalls.getString(15));
				resTo.setExtApellidoMat(rsCalls.getString(16));
				resTo.setExtNombres(rsCalls.getString(17));
				resTo.setExtSexo(rsCalls.getString(18));
				resTo.setExtFechaNacimi(FechaUtil.parsearFecha("yyyyMMdd", rsCalls.getString(19)));
				resTo.setExtCodEstBen(rsCalls.getString(20));
				resTo.setExtDescEstado(rsCalls.getString(21));
				objCall.setResponse(resTo);
				
				listCalls.add(objCall);
			}
			rsCalls.close();
			cstmt.close();
			
			if(listCalls.size() != 1)
				throw new DaoException();
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}catch (Exception e){
			e.printStackTrace();
			throw new DaoException();
		}finally{
			closeConnection();
		}
		
		return (CallWSFonasaTO)listCalls.get(0);
	}
	
	public int getCallExisteLicSIL(int numlicencia, int rutafi) throws DaoException{
		int existe=0;
		String call = "{ call LIEXP.EXISTLIC_F(?, ?) }";
		try{
			CallableStatement cstmt = getConnection().prepareCall(call);
			cstmt.setInt(1, numlicencia);
			cstmt.setInt(2, rutafi);
			
			ResultSet rsCalls = cstmt.executeQuery();
			
			if(rsCalls.next()){
				existe= rsCalls.getInt(1);
			}
			
			rsCalls.close();
			cstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}catch (Exception e){
			e.printStackTrace();
			throw new DaoException();
		}finally{
			closeConnection();
		}
		
		return existe;
	}
}
