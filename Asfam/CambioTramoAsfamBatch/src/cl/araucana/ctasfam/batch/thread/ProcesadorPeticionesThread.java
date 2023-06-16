package cl.araucana.ctasfam.batch.thread;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.ctasfam.batch.common.dto.ArchivoPropuestaDto;
import cl.araucana.ctasfam.batch.common.dto.BitacoraProcesamientoDto;
import cl.araucana.ctasfam.batch.common.dto.CantidadPropuestasAfiliadoDto;
import cl.araucana.ctasfam.batch.common.dto.PeticionProcesamientoDto;
import cl.araucana.ctasfam.batch.common.dto.PropuestaAfiliadoDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;
import cl.araucana.ctasfam.batch.common.util.ArchivoPropuestaUtil;
import cl.araucana.ctasfam.batch.dao.ArchivoPropuestaDao;
import cl.araucana.ctasfam.batch.dao.BitacoraProcesamientoDao;
import cl.araucana.ctasfam.batch.dao.PeticionProcesamientoDao;
import cl.araucana.ctasfam.batch.dao.PropuestaAfiliadoDao;
import cl.araucana.ctasfam.batch.dao.as400.impl.ArchivoPropuestaDaoImpl;
import cl.araucana.ctasfam.batch.dao.db2.impl.BitacoraProcesamientoDaoImpl;
import cl.araucana.ctasfam.batch.dao.db2.impl.PeticionProcesamientoDaoImpl;
import cl.araucana.ctasfam.batch.dao.db2.impl.PropuestaAfiliadoDaoImpl;

public class ProcesadorPeticionesThread extends Thread{
	
	final static Logger logger = Logger.getLogger(ProcesadorPeticionesThread.class);
	
	private Integer indexThread;
	private PeticionProcesamientoDto peticionProcesamiento;
	private BitacoraProcesamientoDto bitacoraProcesamiento;
	private Integer contadorPropuestas;
	private ArchivoPropuestaDao archivoPropDao;
	private PropuestaAfiliadoDao propAfilDao;
	private PeticionProcesamientoDao petProDao;
	private BitacoraProcesamientoDao bitProDao;
	
	public ProcesadorPeticionesThread(){}
	
	public ProcesadorPeticionesThread(Integer indexThread, PeticionProcesamientoDto peticionProcesamiento){
		logger.debug("Creando hebra procesador de peticiones");
		
		this.indexThread = indexThread;
		this.peticionProcesamiento = peticionProcesamiento;
		
		bitacoraProcesamiento = new BitacoraProcesamientoDto();
		bitacoraProcesamiento.setIdPeticionProcesamiento(peticionProcesamiento.getIdPeticionProcesamiento());
		bitacoraProcesamiento.setCodeHebraProcesadora(String.valueOf(indexThread));
		bitacoraProcesamiento.setNumeroIntento(peticionProcesamiento.getCantidadIntentos() + 1);
		
		contadorPropuestas = 0;
		
		archivoPropDao = new ArchivoPropuestaDaoImpl();
		propAfilDao = new PropuestaAfiliadoDaoImpl(indexThread);
		petProDao = new PeticionProcesamientoDaoImpl(indexThread);
		bitProDao = new BitacoraProcesamientoDaoImpl(indexThread);
	}
	
	public Integer getIndexThread() {
		return indexThread;
	}

	public void setIndexThread(Integer indexThread) {
		this.indexThread = indexThread;
	}

	public PeticionProcesamientoDto getPeticionProcesamiento() {
		return peticionProcesamiento;
	}

	public void setPeticionProcesamiento(
			PeticionProcesamientoDto peticionProcesamiento) {
		this.peticionProcesamiento = peticionProcesamiento;
	}

	public BitacoraProcesamientoDto getBitacoraProcesamiento() {
		return bitacoraProcesamiento;
	}

	public void setBitacoraProcesamiento(
			BitacoraProcesamientoDto bitacoraProcesamiento) {
		this.bitacoraProcesamiento = bitacoraProcesamiento;
	}

	public Integer getContadorPropuestas() {
		return contadorPropuestas;
	}

	public void setContadorPropuestas(Integer contadorPropuestas) {
		this.contadorPropuestas = contadorPropuestas;
	}

	public ArchivoPropuestaDao getArchivoPropDao() {
		return archivoPropDao;
	}

	public void setArchivoPropDao(ArchivoPropuestaDao archivoPropDao) {
		this.archivoPropDao = archivoPropDao;
	}

	public PropuestaAfiliadoDao getPropAfilDao() {
		return propAfilDao;
	}

	public void setPropAfilDao(PropuestaAfiliadoDao propAfilDao) {
		this.propAfilDao = propAfilDao;
	}

	public PeticionProcesamientoDao getPetProDao() {
		return petProDao;
	}

	public void setPetProDao(PeticionProcesamientoDao petProDao) {
		this.petProDao = petProDao;
	}

	public BitacoraProcesamientoDao getBitProDao() {
		return bitProDao;
	}

	public void setBitProDao(BitacoraProcesamientoDao bitProDao) {
		this.bitProDao = bitProDao;
	}

	public void run(){
		logger.debug("Hebra-" + indexThread + ": Iniciando procesamiento de peticion " + peticionProcesamiento.getIdPeticionProcesamiento());

		try {
//			Crea una bitacra de procesamioento en base de datos
			bitacoraProcesamiento.setFechaInicioProcesamiento(new Date());
			if(!bitProDao.insertBitacoraProcesamiento(bitacoraProcesamiento)){
				throw new TechnicalException("0301", "No fue posible crear la bitacora de procesamiento");
			}
			
//			Actualiza estado de peticion de procesamiento
			peticionProcesamiento.setEstado("L");
			peticionProcesamiento.setFechaProcesamiento(bitacoraProcesamiento.getFechaInicioProcesamiento());
			peticionProcesamiento.setCantidadIntentos(bitacoraProcesamiento.getNumeroIntento());
			if(!petProDao.updatePeticionProcesamiento(peticionProcesamiento)){
				throw new TechnicalException("0302", "No fue posible actualizar la peticion de procesamiento");
			}
			
//			Obtiene el archivo de propuesta desde sistema de archivos AS400
			ArchivoPropuestaDto archivoProp = archivoPropDao.getArchivoPropuesta(peticionProcesamiento.getRutaArchivo());
			
//			Lee el archivo de propuesta
			List<PropuestaAfiliadoDto> listPropAfil = new ArrayList<PropuestaAfiliadoDto>();
			if("xls".equalsIgnoreCase(archivoProp.getTipo())){
				listPropAfil = ArchivoPropuestaUtil.leePropuestaExcel(archivoProp.getContenido());
			}else if("txt".equalsIgnoreCase(archivoProp.getTipo())){
				listPropAfil = ArchivoPropuestaUtil.leePropuestaTxt(archivoProp.getContenido());
			}else if("csv".equalsIgnoreCase(archivoProp.getTipo())){
				listPropAfil = ArchivoPropuestaUtil.leePropuestaCsv(archivoProp.getContenido());
			}else if("zip".equalsIgnoreCase(archivoProp.getTipo())){
				listPropAfil = ArchivoPropuestaUtil.leePropuestaCsv(archivoProp.getContenido());
			}else{
				throw new TechnicalException("0303","No fue posible leer un archivo de tipo desconocido");
			}
			
			
			for(PropuestaAfiliadoDto propAfil : listPropAfil){
				contadorPropuestas++;
				CantidadPropuestasAfiliadoDto cantPropAfil = propAfilDao.getCantidadPropuestaByAfiliado(
						propAfil.getPeriodo(), propAfil.getRutEmpresa(), propAfil.getRutTrabajador());
				
				propAfil.setOrigen("E");
				propAfil.setTiempo(new Time(peticionProcesamiento.getFechaProcesamiento().getTime()));
				propAfil.setAfama((cantPropAfil.getSecDigit() != null && cantPropAfil.getSecDigit()==0)?1:0);
				
				if(cantPropAfil.getCantidad() == 0){
					if(!propAfilDao.insertPropuestaAfiliado(propAfil)){
						throw new TechnicalException("0304","No fue posible insetar la propuesta del afiliado");
					}
				}else{
					if(!propAfilDao.updatePropuestaAfiliado(propAfil)){
						throw new TechnicalException("0305","No fue posible actualizar la propuesta del afiliado");
					}
				}
			}
			
			peticionProcesamiento.setEstado("F");
			peticionProcesamiento.setTotalRegProcesados(contadorPropuestas);
			
			if(!petProDao.updatePeticionProcesamiento(peticionProcesamiento)){
				throw new TechnicalException("0306","No fue posible actualizar la peticion de procesamiento");
			}
			
			bitacoraProcesamiento.setResultadoProcesamiento("Ok");
			bitacoraProcesamiento.setDetalleResultadoProcesamiento("Ok");
			bitacoraProcesamiento.setFechaFinProcesamiento(new Date());
			
			if(!bitProDao.updateBitacoraProcesamiento(bitacoraProcesamiento)){
				throw new TechnicalException("0307","No fue posible actualizar la bitacora de procesamiento");
			}
		} catch (TechnicalException te) {
			logger.error("Upss!! Ocurrio un error en la hebra " + indexThread + " al procesar la peticion " 
					+ peticionProcesamiento.getIdPeticionProcesamiento());
			logger.error(te.getCode() + ": " + te.getDescription(), te);
			
			peticionProcesamiento.setEstado("E");
			peticionProcesamiento.setTotalRegProcesados(contadorPropuestas);
			
			String detResult = te.getCode() + ": " + te.getDescription();
			detResult = (detResult.length() > 200)?detResult.substring(0, 200):detResult;
			bitacoraProcesamiento.setResultadoProcesamiento("Error");
			bitacoraProcesamiento.setDetalleResultadoProcesamiento(detResult);
			bitacoraProcesamiento.setFechaFinProcesamiento(new Date());
			try {
				petProDao.updatePeticionProcesamiento(peticionProcesamiento);
			} catch (TechnicalException e1) {
				logger.error("No fue posible actualizar la peticion de procesamiento para finalizar la ejecucion de la hebra");
			}
			try {
				bitProDao.updateBitacoraProcesamiento(bitacoraProcesamiento);
			} catch (TechnicalException e1) {
				logger.error("No fue posible actualizar la bitacora de procesamiento para finalizar la ejecucion de la hebra");
			}
		} catch(Exception e){
			logger.error("Upss!! Ocurrio un error en la hebra " + indexThread + " al procesar la peticion " 
					+ peticionProcesamiento.getIdPeticionProcesamiento(), e);
			
			peticionProcesamiento.setEstado("E");
			peticionProcesamiento.setTotalRegProcesados(contadorPropuestas);
			
			String detResult = "Ocurrio un error no identificado: " + e.getLocalizedMessage();
			detResult = (detResult.length() > 200)?detResult.substring(0, 200):detResult;
			bitacoraProcesamiento.setResultadoProcesamiento("Error");
			bitacoraProcesamiento.setDetalleResultadoProcesamiento(detResult);
			bitacoraProcesamiento.setFechaFinProcesamiento(new Date());
			try {
				petProDao.updatePeticionProcesamiento(peticionProcesamiento);
			} catch (TechnicalException e1) {
				logger.error("No fue posible actualizar la peticion de procesamiento para finalizar la ejecucion de la hebra");
			}
			try {
				bitProDao.updateBitacoraProcesamiento(bitacoraProcesamiento);
			} catch (TechnicalException e1) {
				logger.error("No fue posible actualizar la bitacora de procesamiento para finalizar la ejecucion de la hebra");
			}
		}
	}
}
