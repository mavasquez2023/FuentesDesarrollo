package cl.laaraucana.simat.dao;

import cl.laaraucana.simat.entidades.CountVO;

public interface CE2_DAO {

	public CountVO getCE2_Num_subs_Iniciados_Pre_natal(CountVO qid) throws Exception;

	public CountVO getCE2_Num_subs_Iniciados_Post_natal(CountVO qid) throws Exception;

	public CountVO getCE2_Num_subs_Iniciados_Parental(CountVO qid) throws Exception;

	public CountVO getCE2_Num_subs_Iniciados_Enf_menor(CountVO qid) throws Exception;
}
