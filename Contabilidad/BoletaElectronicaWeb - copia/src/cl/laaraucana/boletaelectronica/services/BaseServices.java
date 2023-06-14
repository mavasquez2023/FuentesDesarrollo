package cl.laaraucana.boletaelectronica.services;

import java.util.List;

import cl.laaraucana.boletaelectronica.entities.BoletaBase;

public interface BaseServices {

	public List<BoletaBase> getBoleta(int estado) throws Exception;

	public BoletaBase getBoletaById(long id) throws Exception;

	public void save(BoletaBase boleta) throws Exception;

	public List<BoletaBase> getBoletaByFolio(long numeroBoleta) throws Exception;

	public List<BoletaBase> getBoletaByNumberAndEstate(long numeroBoleta, int estado) throws Exception;

	public boolean existNumberAndFol(long numboleta) throws Exception;

	public void updateBase(BoletaBase boletaBase) throws Exception;

	public List<BoletaBase> findAllEmitidas() throws Exception;

	public List<BoletaBase> findByNumBolAndTipoDoc(long numboleta, int tipodoc) throws Exception;

	public List<BoletaBase> findByNumber(long folio) throws Exception;

	public List<BoletaBase> findByFecha(long fechaInicio, long fechaFin, long folio) throws Exception;

	
}
