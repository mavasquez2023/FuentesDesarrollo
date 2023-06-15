package cl.laaraucana.simat.dao;

import cl.laaraucana.simat.entidades.SumVO;

public interface Sum_Monto_Subs_PagadosDAO {

	public SumVO getSum_Monto_SubsPagado_Pre_natal(SumVO qid) throws Exception;

	public SumVO getSum_Monto_SubsPagado_Post_natal_Madre(SumVO qid) throws Exception;

	public SumVO getSum_Monto_SubsPagado_Post_natal_Padre(SumVO qid) throws Exception;

	public SumVO getSum_Monto_SubsPagado_Parental_Madre(SumVO qid) throws Exception;

	public SumVO getSum_Monto_SubsPagado_Parental_Padre(SumVO qid) throws Exception;

	public SumVO getSum_Monto_SubsPagado_Enf_menor_Madre(SumVO countSQL) throws Exception;

	public SumVO getSum_Monto_SubsPagado_Enf_menor_Padre(SumVO qid) throws Exception;
}
