package cl.laaraucana.simat.dao;

import cl.laaraucana.simat.entidades.SMF09_VO;

public interface SMF09_DAO {
	public void setEstadoPeriodoByProceso(SMF09_VO smf09) throws Exception;

	public void delEstadoPeriodoByProceso(SMF09_VO smf09) throws Exception;

	public SMF09_VO getEstadoPeriodoByProceso(SMF09_VO smf09) throws Exception;

	public void upEstadoPeriodoByProceso(SMF09_VO smf09) throws Exception;

}
