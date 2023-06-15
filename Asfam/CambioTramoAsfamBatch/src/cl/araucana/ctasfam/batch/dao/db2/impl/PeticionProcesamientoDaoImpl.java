package cl.araucana.ctasfam.batch.dao.db2.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.ctasfam.batch.common.dto.PeticionProcesamientoDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;
import cl.araucana.ctasfam.batch.dao.AbstractDb2Dao;
import cl.araucana.ctasfam.batch.dao.PeticionProcesamientoDao;
import cl.araucana.ctasfam.batch.dao.extractor.PeticionProcesamientoExtractor;

public class PeticionProcesamientoDaoImpl extends AbstractDb2Dao implements PeticionProcesamientoDao {

	final static Logger logger = Logger.getLogger(PeticionProcesamientoDaoImpl.class);
	
	public PeticionProcesamientoDaoImpl(){}
	
	public PeticionProcesamientoDaoImpl(Integer indexConnection){
		super(indexConnection);
	}
	
	public List<PeticionProcesamientoDto> getPeticionProcesamientoPorEstado(String estado, int numeroFilas, 
			List<Integer> listRutsEmpEnCola, Integer maxIntentos) throws TechnicalException{
		
		logger.debug("Obteniendo peticiones de procesamiento pendientes");
		
		List<PeticionProcesamientoDto> listResult = new ArrayList<PeticionProcesamientoDto>();
		String querySql = "SELECT AFP66ID, AFP7A, AFOVA, AFOYA, AFOZA, AFP66ARC, AFP66EST, " +
				"AFP66TRI, AFP66FPA, AFP66TRP, AFP66ORI, AFP66CIN, OBF002, OBF003, OBF005, OBF006, " +
				"SAJKM94, SAJKM92 " +
				"FROM AFDTA.AFP66F1 " +
				"WHERE AFP66EST in (?,?) " + 
				"AND AFP66CIN < ? ";
		
		if(listRutsEmpEnCola != null && listRutsEmpEnCola.size() > 0){
			querySql += "AND AFOVA NOT IN (";
			for(int i = 0; i < listRutsEmpEnCola.size(); i++){
				querySql += "?,";
			}
			querySql = querySql.substring(0, querySql.length()-1) + ") ";
		}
		querySql += "ORDER BY AFP66ID ASC " +
				"FETCH FIRST " + numeroFilas + " ROWS ONLY";
		
		try{
			PreparedStatement psSql = getPreparedStatement(querySql);
			psSql.setString(1, estado);
			psSql.setString(2, "E");
			psSql.setInt(3, maxIntentos);
			
			if(listRutsEmpEnCola != null && listRutsEmpEnCola.size() > 0){
				int i = 4;
				for(Integer rutEmpEnCola : listRutsEmpEnCola){
					psSql.setInt(i, rutEmpEnCola);
					i++;
				}
			}
			
			ResultSet rsSql = psSql.executeQuery(); 
			if(rsSql != null){
				while(rsSql.next()){
					PeticionProcesamientoDto obj =  new PeticionProcesamientoExtractor().extractor(rsSql);
					listResult.add(obj);
				}
			}
			
			if(rsSql != null) rsSql.close();
			if(psSql != null) psSql.close();
			closeSinglesConnection();
		}catch(SQLException ex){
			throw new TechnicalException("0901","Ocurrio un error al obtener las peticiones de procesamiento pendientes", ex);
		}
		
		return listResult;
	}
	
	
	
	public Boolean updatePeticionProcesamiento(PeticionProcesamientoDto peticionProcesamiento) throws TechnicalException{
		
		logger.debug("Actualizando peticion de procesamiento");
		
		String querySql = "UPDATE AFDTA.AFP66F1 " +
				"SET AFP66EST = ?, " +
				"AFP66FPA = ?, " +
				"AFP66TRP = ?, " +
				"AFP66CIN = ? " +
				"WHERE AFP66ID = ?";
		
		try{
			PreparedStatement psSql = getPreparedStatement(querySql);
			psSql.setString(1, peticionProcesamiento.getEstado());
			psSql.setTimestamp(2, (peticionProcesamiento.getFechaProcesamiento()!=null)?
					new Timestamp(peticionProcesamiento.getFechaProcesamiento().getTime()):null);
			psSql.setInt(3, (peticionProcesamiento.getTotalRegProcesados()!=null)?
					peticionProcesamiento.getTotalRegProcesados():null);
			psSql.setInt(4, peticionProcesamiento.getCantidadIntentos());
			psSql.setInt(5, peticionProcesamiento.getIdPeticionProcesamiento());
			
			int resultUpdate = psSql.executeUpdate(); 

			if(psSql != null) psSql.close();
			closeSinglesConnection();
			return (resultUpdate>0)?true:false;
		}catch(SQLException ex){
			throw new TechnicalException("0902","Ocurrio un error al actualizar las peticiones de procesamiento pendientes", ex);
		}
	}
}
