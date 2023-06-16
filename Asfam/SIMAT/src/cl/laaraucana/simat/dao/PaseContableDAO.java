package cl.laaraucana.simat.dao;

import java.util.List;

import cl.laaraucana.simat.entidades.CuentaPaseContableVO;

public interface PaseContableDAO {
	public List<CuentaPaseContableVO> getCuentasPaseContable() throws Exception;
	public boolean ingresarPaseContable(List<CuentaPaseContableVO> cuenta, String periodo) throws Exception;
	public boolean crearPaseContableAs400(String periodo) throws Exception;
	public int borrarPaseContable(String periodo) throws Exception;
}
