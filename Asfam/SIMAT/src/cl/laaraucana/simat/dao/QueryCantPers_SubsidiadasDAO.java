package cl.laaraucana.simat.dao;

import cl.laaraucana.simat.entidades.CountVO;

public interface QueryCantPers_SubsidiadasDAO {

	public CountVO num_pers_subs_Pre_natal(CountVO qid) throws Exception;

	public CountVO num_pers_subs_parental_Madre(CountVO qid) throws Exception;

	public CountVO num_pers_subs_parental_Padre(CountVO qid) throws Exception;

	public CountVO num_pers_subs_Post_natal_Madre(CountVO qid) throws Exception;

	public CountVO num_pers_subs_Post_natal_Padre(CountVO qid) throws Exception;

	public CountVO num_pers_subs_Enf_menor_Madre(CountVO qid) throws Exception;

	public CountVO num_pers_subs_Enf_menor_Padre(CountVO qid) throws Exception;
}
