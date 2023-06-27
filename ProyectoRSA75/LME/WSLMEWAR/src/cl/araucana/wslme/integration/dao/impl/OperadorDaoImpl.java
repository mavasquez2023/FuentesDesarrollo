package cl.araucana.wslme.integration.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.log4j.Logger;

import cl.araucana.wslme.business.to.Operador;
import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.common.util.ConfigUtil;
import cl.araucana.wslme.integration.dao.FileAbstractDao;
import cl.araucana.wslme.integration.dao.OperadorDao;

public class OperadorDaoImpl extends FileAbstractDao implements OperadorDao {
	private Logger log = Logger.getLogger(OperadorDaoImpl.class);
	
	public OperadorDaoImpl() throws WSLMEException {
		openFile(ConfigUtil.PATH_OPERADORES_REGISTRADOS);
	}

	public Operador getOperador(String codigoOperador) throws WSLMEException{
		Operador operador = new Operador();
		
		try{
			FileReader fileUsuarios = getFileReader();
			BufferedReader buffReader = new BufferedReader(fileUsuarios);
			String linea;
			while((linea=buffReader.readLine())!=null){
				String codOpeTemp = linea.split(":")[0];
				String claveOpeTemp = linea.split(":")[1];
				if(codigoOperador.equals(codOpeTemp)){
					operador.setCodigoOperador(codOpeTemp);
					operador.setClaveOperador(claveOpeTemp);
					log.debug("Se encontró el operador codigo [" + codigoOperador + "] en el archivo de operadores registrados");
					return operador;
				}
			}
			
		}catch(Exception e){
			log.error("Codigo 0021: Ocurrio un problema al buscar el operador codigo [" + codigoOperador + "] en el archivo de operadores registrados");
			throw new WSLMEException("0021","Error, Ocurrio un problema al buscar el operador codigo [" + codigoOperador + "] en el archivo de operadores registrados.");
		}
		
		return operador;
	}
}
