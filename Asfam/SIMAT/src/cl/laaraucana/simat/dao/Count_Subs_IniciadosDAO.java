package cl.laaraucana.simat.dao;

import cl.laaraucana.simat.entidades.CountVO;

public interface Count_Subs_IniciadosDAO {

	public CountVO getNum_subs_Iniciados_Pre_natal(CountVO qid) throws Exception;

	public CountVO getNum_subs_Iniciados_Post_natal_Madre(CountVO qid) throws Exception;

	public CountVO getNum_subs_Iniciados_Post_natal_Padre(CountVO qid) throws Exception;

	public CountVO getNum_subs_Iniciados_Parental_Madre(CountVO qid) throws Exception;

	public CountVO getNum_subs_Iniciados_Parental_Padre(CountVO qid) throws Exception;

	public CountVO getNum_subs_Iniciados_Enf_menor_Madre(CountVO qid) throws Exception;

	public CountVO getNum_subs_Iniciados_Enf_menor_Padre(CountVO qid) throws Exception;
}
