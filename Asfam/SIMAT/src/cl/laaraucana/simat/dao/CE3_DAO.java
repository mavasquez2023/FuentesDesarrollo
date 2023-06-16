package cl.laaraucana.simat.dao;

import cl.laaraucana.simat.entidades.CountVO;

public interface CE3_DAO {

	public CountVO getCE3_Sum_Dias_Pagados_Pre_natal(CountVO qid) throws Exception;

	public CountVO getCE3_Sum_Dias_Pagados_Post_natal(CountVO qid) throws Exception;

	public CountVO getCE3_Sum_Dias_Pagados_Parental(CountVO qid) throws Exception;

	public CountVO getCE3_Sum_Dias_Pagados_Enf_menor(CountVO qid) throws Exception;
}
