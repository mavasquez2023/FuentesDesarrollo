package cl.araucana.ctasfam.batch.dao.db2.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import cl.araucana.ctasfam.batch.common.dto.BitacoraProcesamientoDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;
import cl.araucana.ctasfam.batch.dao.AbstractDb2Dao;
import cl.araucana.ctasfam.batch.dao.BitacoraProcesamientoDao;

public class BitacoraProcesamientoDaoImpl extends AbstractDb2Dao implements BitacoraProcesamientoDao {

	final static Logger logger = Logger.getLogger(BitacoraProcesamientoDaoImpl.class);
	
	public BitacoraProcesamientoDaoImpl(){}
	
	public BitacoraProcesamientoDaoImpl(Integer indexConnection){
		super(indexConnection);
	}
	
	public Boolean insertBitacoraProcesamiento(BitacoraProcesamientoDto bitacoraProcesamiento) throws TechnicalException{
		logger.debug("Insertando bitacora de procesamiento");
		
		String querySql = "INSERT INTO AFDTA.AFP67F1 (AFP66ID, AFP67CH, AFP67FIP, AFP67NIN) VALUES (?, ?, ?, ?)";
		
		try{
			PreparedStatement psSql = getPreparedStatement(querySql);
			
			psSql.setInt(1, bitacoraProcesamiento.getIdPeticionProcesamiento());
			psSql.setString(2, bitacoraProcesamiento.getCodeHebraProcesadora());
			psSql.setTimestamp(3, new Timestamp(bitacoraProcesamiento.getFechaInicioProcesamiento().getTime()));
			psSql.setInt(4, bitacoraProcesamiento.getNumeroIntento());
			
			int resultInsert = psSql.executeUpdate();

			if(psSql != null) {
				psSql.close();
			}
			closeSinglesConnection();
			
			
			return (resultInsert>0)?true:false;
		}catch(SQLException ex){
			throw new TechnicalException("0401","Ocurrio un error al insertar bitacora de procesamiento", ex);
		}
	}
	
	public Boolean updateBitacoraProcesamiento(BitacoraProcesamientoDto bitacoraProcesamiento) throws TechnicalException{
		logger.debug("Actualizando bitacora de procesamiento");
		
		String querySql = "UPDATE AFDTA.AFP67F1 " + 
				"SET AFP67RES = ?, " +
				"AFP67DRE = ?, " +
				"AFP67FFP = ? " +
				"WHERE AFP66ID = ? " +
				"AND AFP67NIN = ?";
		
		try{
			PreparedStatement psSql = getPreparedStatement(querySql);
			psSql.setString(1, bitacoraProcesamiento.getResultadoProcesamiento());
			psSql.setString(2, bitacoraProcesamiento.getDetalleResultadoProcesamiento());
			psSql.setTimestamp(3, (bitacoraProcesamiento.getFechaFinProcesamiento()!=null)?
					new Timestamp(bitacoraProcesamiento.getFechaFinProcesamiento().getTime()):null);
			psSql.setInt(4, bitacoraProcesamiento.getIdPeticionProcesamiento());
			psSql.setInt(5, bitacoraProcesamiento.getNumeroIntento());
			
			int resultUpdate = psSql.executeUpdate(); 

			if(psSql != null){ 
				psSql.close();
			}
			closeSinglesConnection();
			return (resultUpdate>0)?true:false;
		}catch(SQLException ex){
			throw new TechnicalException("0403","Ocurrio un error al actualizar la bitacora de procesamiento", ex);
		}
	}
}
