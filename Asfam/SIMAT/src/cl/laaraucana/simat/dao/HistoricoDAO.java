package cl.laaraucana.simat.dao;

import cl.laaraucana.simat.entidades.Control_Historico_VO;
import cl.laaraucana.simat.entidades.CountVO;

public interface HistoricoDAO {

	public CountVO getCountPeriodos(String tabla) throws Exception;

	public Control_Historico_VO getMenorPeriodo(String tabla) throws Exception;

	public void delPeriodo(String tabla, Control_Historico_VO chVO) throws Exception;

}
