package cl.laaraucana.simat.dao;

import cl.laaraucana.simat.entidades.CountVO;

public interface Sum_Dias_Subs_PagadosDAO {

	public CountVO getSum_Dias_Pagados_Pre_natal(CountVO qid) throws Exception;

	public CountVO getSum_Dias_Pagados_Post_natal_Madre(CountVO qid) throws Exception;

	public CountVO getSum_Dias_Pagados_Post_natal_Padre(CountVO qid) throws Exception;

	public CountVO getSum_Dias_Pagados_Parental_Madre(CountVO qid) throws Exception;

	public CountVO getSum_Dias_Pagados_Parental_Padre(CountVO qid) throws Exception;

	public CountVO getSum_Dias_Pagados_Enf_menor_Madre(CountVO qid) throws Exception;

	public CountVO getSum_Dias_Pagados_Enf_menor_Padre(CountVO qid) throws Exception;
}
