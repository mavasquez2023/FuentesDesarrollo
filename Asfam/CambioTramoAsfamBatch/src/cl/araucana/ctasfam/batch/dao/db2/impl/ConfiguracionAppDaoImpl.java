package cl.araucana.ctasfam.batch.dao.db2.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.ctasfam.batch.common.dto.PropiedadConfiguracionDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;
import cl.araucana.ctasfam.batch.dao.AbstractDb2Dao;
import cl.araucana.ctasfam.batch.dao.ConfiguracionAppDao;
import cl.araucana.ctasfam.batch.dao.extractor.PropiedadConfiguracionExtractor;

public class ConfiguracionAppDaoImpl extends AbstractDb2Dao implements ConfiguracionAppDao{
	
	final static Logger logger = Logger.getLogger(ConfiguracionAppDaoImpl.class);
	
	public ConfiguracionAppDaoImpl(){}
	
	public List<PropiedadConfiguracionDto> getAllPropiedadesDeConfiguracion() throws TechnicalException{
		logger.debug("Obteniendo parametros de propiedades desde base de datos");
		
		List<PropiedadConfiguracionDto> listResult = new ArrayList<PropiedadConfiguracionDto>();
		String querySql = "SELECT AFP80COD, AFP80VAL FROM AFDTA.AFP80F1";
		
		try{
			PreparedStatement psSql = getPreparedStatement(querySql);
			
			ResultSet rsSql = psSql.executeQuery(); 
			if(rsSql != null){
				while(rsSql.next()){
					PropiedadConfiguracionDto obj =  new PropiedadConfiguracionExtractor().extractor(rsSql);
					listResult.add(obj);
				}
			}
			
			if(rsSql != null) rsSql.close();
			if(psSql != null) psSql.close();
			closeSinglesConnection();
		}catch(SQLException ex){
			throw new TechnicalException("0101","Ocurrio un error al obtener los parametros de propiedades", ex);
		}
		
		return listResult;
		
	}
}
