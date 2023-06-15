package cl.araucana.ctasfam.batch.dao.db2.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import org.apache.log4j.Logger;

import cl.araucana.ctasfam.batch.common.dto.CantidadPropuestasAfiliadoDto;
import cl.araucana.ctasfam.batch.common.dto.PropuestaAfiliadoDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;
import cl.araucana.ctasfam.batch.dao.AbstractDb2Dao;
import cl.araucana.ctasfam.batch.dao.PropuestaAfiliadoDao;
import cl.araucana.ctasfam.batch.dao.extractor.CantidadPropuestasExtractor;

public class PropuestaAfiliadoDaoImpl extends AbstractDb2Dao implements PropuestaAfiliadoDao{
	
	final static Logger logger = Logger.getLogger(PropuestaAfiliadoDaoImpl.class);
	
	public PropuestaAfiliadoDaoImpl(){}
	
	public PropuestaAfiliadoDaoImpl(Integer indexConnection){
		super(indexConnection);
	}
	
	public CantidadPropuestasAfiliadoDto getCantidadPropuestaByAfiliado(Integer periodo, Integer rutEmpresa, Integer rutAfiliado) throws TechnicalException{
		
		logger.debug("Obteniendo cantidad de propuestas por afiliado");
		
		CantidadPropuestasAfiliadoDto obj = null;
		String querySql = "SELECT AFP6A, COUNT ( * ) AS COUNT, AFAMA FROM AFDTA.AFP64F1 " +
				"WHERE AFP7A = ? AND AFOVA = ? AND AFOWA = ? " +  
				"GROUP BY AFP6A, AFAMA";
		
		try{
			PreparedStatement psSql = getPreparedStatement(querySql);
			psSql.setInt(1, periodo);
			psSql.setInt(2, rutEmpresa);
			psSql.setInt(3, rutAfiliado);
			
			ResultSet rsSql = psSql.executeQuery(); 
			if(rsSql != null && rsSql.next()){
				obj =  new CantidadPropuestasExtractor().extractor(rsSql);
			}else{
				obj = new CantidadPropuestasAfiliadoDto(0);
			}
			
			if(rsSql != null) rsSql.close();
			if(psSql != null) psSql.close();
			return obj;
		}catch(SQLException ex){
			throw new TechnicalException("1001","Ocurrio un error al obtener la cantiadad de peticiones del afiliado", ex);
		}
	}
	
	public Boolean insertPropuestaAfiliado(PropuestaAfiliadoDto propAfil) throws TechnicalException{
		logger.debug("Insertando propuesta afiliado");
		
		String callSql = "{CALL AFDTA.PA_CAMBIOTRAMO_SET_AFIL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try{
			CallableStatement callSP = getCallableStatement(callSql);
			
			callSP.setInt(1, propAfil.getPeriodo());
			callSP.setInt(2, propAfil.getRutEmpresa());
			callSP.setInt(3, propAfil.getRutTrabajador());
			callSP.setInt(4, propAfil.getOficina());
			callSP.setInt(5, propAfil.getSucursal());
			callSP.setString(6, propAfil.getDvEmpresa());
			callSP.setString(7, propAfil.getDvTrabajador());
			callSP.setString(8, propAfil.getNombreCompleto());
			callSP.setInt(9, propAfil.getRemuneracionesMismoEmpleador());
			callSP.setInt(10, propAfil.getOtrasRemuneraciones());
			callSP.setInt(11, propAfil.getRentaTrabajadorIndependiente());
			callSP.setInt(12, propAfil.getSubsidio());
			callSP.setInt(13, propAfil.getPensiones());
			callSP.setInt(14, propAfil.getTotalIngresos());
			callSP.setInt(15, propAfil.getNumeroMeses());
			callSP.setInt(16, propAfil.getIngresoPromedio());
			callSP.setInt(17, propAfil.getTrabajadorConSinDeclaracion());
			callSP.setString(18, propAfil.getOrigen());
			callSP.setString(19, "I");
			callSP.setInt(20, 0);
			callSP.setDate(21, new java.sql.Date(new Date().getTime()));
			callSP.setTime(22, new java.sql.Time(new Date().getTime()));
			callSP.setDate(23, new java.sql.Date(new Date().getTime()));
			callSP.setTime(24, propAfil.getTiempo());
			callSP.setString(25, "ENCEMP-ENI"); // EmpNormal=ENCEMP-ENI
			callSP.setString(26, ""); // SAJKM92
			callSP.setInt(27, propAfil.getCodigoTramo());
			callSP.setInt(28, propAfil.getValorTramo());
			callSP.registerOutParameter(29, Types.INTEGER);

			callSP.execute();
			Integer paramResult = callSP.getInt(29);
			
			callSP.close();
			
			if (paramResult == 0){
				return false;
			}else{
				return true;
			}
		}catch(SQLException ex){
			throw new TechnicalException("1002","Ocurrio un error al insertar propuesta del afiliado", ex);
		}
	}
	
	public Boolean updatePropuestaAfiliado(PropuestaAfiliadoDto propAfil) throws TechnicalException{
		logger.debug("Actualizando propuesta afiliado");
		
		String callSql = "{CALL AFDTA.PA_CAMBIOTRAMO_UPDATE_AFIL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try{
			CallableStatement callSP = getCallableStatement(callSql);
			
			callSP.setInt(1, propAfil.getPeriodo());
			callSP.setInt(2, propAfil.getRutEmpresa());
			callSP.setInt(3, propAfil.getRutTrabajador());
			callSP.setInt(4, propAfil.getOficina());
			callSP.setInt(5, propAfil.getSucursal());
			callSP.setString(6, propAfil.getDvEmpresa());
			callSP.setString(7, propAfil.getDvTrabajador());
			callSP.setString(8, propAfil.getNombreCompleto());
			callSP.setInt(9, propAfil.getRemuneracionesMismoEmpleador());
			callSP.setInt(10, propAfil.getOtrasRemuneraciones());
			callSP.setInt(11, propAfil.getRentaTrabajadorIndependiente());
			callSP.setInt(12, propAfil.getSubsidio());
			callSP.setInt(13, propAfil.getPensiones());
			callSP.setInt(14, propAfil.getTotalIngresos());
			callSP.setInt(15, propAfil.getNumeroMeses());
			callSP.setInt(16, propAfil.getIngresoPromedio());
			callSP.setInt(17, propAfil.getTrabajadorConSinDeclaracion());
			callSP.setString(18, propAfil.getOrigen());
			callSP.setString(19, "I");
			callSP.setInt(20, 0);
			callSP.setDate(21, new java.sql.Date(new Date().getTime()));
			callSP.setTime(22, new java.sql.Time(new Date().getTime()));
			callSP.setDate(23, new java.sql.Date(new Date().getTime()));
			callSP.setTime(24, propAfil.getTiempo());
			callSP.setString(25, ""); // SAJKM92
			callSP.setString(26, "ENCEMP-ENI"); // EmpNormal=ENCEMP-ENI
			callSP.setInt(27, propAfil.getCodigoTramo());
			callSP.setInt(28, propAfil.getValorTramo());
			callSP.registerOutParameter(29, Types.INTEGER);

			callSP.execute();
			Integer paramResult = callSP.getInt(29);
			
			callSP.close();
			
			if (paramResult == 0){
				return false;
			}else{
				return true;
			}

		}catch(SQLException ex){
			throw new TechnicalException("1003","Ocurrio un error al actualizar propuesta del afiliado", ex);
		}
	}
}
