package cl.laaraucana.recepcionsil.persistencia.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.laaraucana.config.ibatis.DaoException;
import cl.laaraucana.recepcionsil.persistencia.dto.Ilfsering;
import cl.laaraucana.recepcionsil.service.vo.Ilf1100VO;
import cl.laaraucana.recepcionsil.service.vo.Ilf8600VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe031VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe033VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe034VO;
import cl.laaraucana.recepcionsil.service.vo.LicenciaNivel2VO;
import cl.laaraucana.recepcionsil.service.vo.LicenciaVO;

public interface ServIngDaoI {
	public abstract void ingresarCadena(Ilfsering ing) throws DaoException;
	public abstract void ingresarPre1000(LicenciaVO lic) throws DaoException;
	public abstract int getOficina(int oficina) throws DaoException;
	public abstract int getNumeroLicenciaCaja(int numeroLicenciaOperador)throws DaoException;
	public abstract void update8600(Ilf8600VO vo) throws DaoException;
	public abstract void update1010(HashMap<String, Object> param) throws DaoException;
	public abstract void ingresar1100(Ilf1100VO vo) throws DaoException;
	public abstract void ingresar031(Ilfe031VO vo31) throws DaoException;
	public abstract void ingresar033(Ilfe033VO vo33) throws DaoException;
	public abstract void ingresar034(Ilfe034VO vo34) throws DaoException;
	public abstract void update031(Ilfe031VO vo31) throws DaoException;
	public abstract List<HashMap<String, BigDecimal>> getLicAnteriores(HashMap<String, Integer> param) throws DaoException;
	public abstract int getTieneLicenciasAnteriores(HashMap<String, String> param) throws DaoException;
	public abstract Object getIlfeOpe(HashMap<String, String> param) throws DaoException;
	public abstract HashMap<String, BigDecimal> getIlfSering(HashMap<String, String> param) throws DaoException;
	public abstract String getNomEmpresa(int rutemp) throws DaoException;
	public abstract HashMap<String, String> getEmpleador(HashMap<String, Integer> param) throws DaoException;
	public abstract Map<String, String> getEndPoints() throws Exception; 
	public abstract Map<String, String> getParametrosBd() throws Exception;
	public abstract String getNombreEntidad(String codigo) throws DaoException;
	public abstract String getServicioSalud(int oficinaIngreso)throws DaoException;
	public abstract void insertIlfPersist(HashMap<String, Object> param) throws DaoException;
	public abstract List<HashMap<String, BigDecimal>> getIlfPersist(int estado) throws DaoException;
	public abstract void updateIlfPersist(HashMap<String, String> param) throws DaoException;
}
