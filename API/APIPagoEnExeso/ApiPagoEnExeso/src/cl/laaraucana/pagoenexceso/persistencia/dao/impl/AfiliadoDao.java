package cl.laaraucana.pagoenexceso.persistencia.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.config.ibatis.DaoException;
import cl.laaraucana.pagoenexceso.persistencia.dao.AfiliadoDaoI;
import cl.laaraucana.pagoenexceso.persistencia.vo.AfiliadoVO;

public class AfiliadoDao implements AfiliadoDaoI{
	
	private static SqlMapClient sqlMap;
	
	public AfiliadoDao(SqlMapClient sqlMapIn) {
		sqlMap = sqlMapIn;
	}
	
	
	@Override
	public String obtenerNombrePensionado(String rut) throws DaoException {
		String nombre = null;
		try {
			AfiliadoVO afiliado =(AfiliadoVO)sqlMap.queryForObject("obtenerNombrePensionado", rut);
			if(afiliado!=null)
				nombre = afiliado.getNombre() + " " + afiliado.getApellidos();
		} catch (Exception e) {
			throw new DaoException("Error al obtener nombre de pensionado",e);
		}
		return nombre;
	}

	@Override
	public String obtenerNombreAfiliado(String rut) throws DaoException {
		String nombre = null;
		try {
			AfiliadoVO afiliado =(AfiliadoVO)sqlMap.queryForObject("obtenerNombreAfiliado", rut);
			if(afiliado!=null)
				nombre = afiliado.getNombre() + " " +  afiliado.getApellidoPaterno() + " " + afiliado.getApellidoMaterno();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Error al obtener nombre de afiliado",e);
		}
		return nombre;
	}

	@Override
	public String obtenerNombreEmpresa(String rut) throws DaoException {
		String nombre = null;
		try {
			nombre = (String) sqlMap.queryForObject("obtenerNombreEmpresa", rut);
		} catch (Exception e) {
			throw new DaoException("Error al obtener nombre de empresa",e);
		}
		return nombre;
	}

	@Override
	public String obtenerNombreEmpresaCMDTA(String rut) throws DaoException {
		String nombre = null;
		try {
			nombre = (String) sqlMap.queryForObject("obtenerNombreEmpresaCMDTA", rut);
		} catch (Exception e) {
			throw new DaoException("Error al obtener nombre de empresa",e);
		}
		return nombre;
	}

	@Override
	public String obtenerNombreEntidadPagadora(String rut) throws DaoException {
		String nombre = null;
		try {
			nombre = (String) sqlMap.queryForObject("obtenerNombreEntidadPagadora", rut);
		} catch (Exception e) {
			throw new DaoException("Error al obtener nombre de entidad pagadora",e);
		}
		return nombre;
	}
}
