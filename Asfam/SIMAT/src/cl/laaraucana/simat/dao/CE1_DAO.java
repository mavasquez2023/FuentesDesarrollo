package cl.laaraucana.simat.dao;

import cl.laaraucana.simat.entidades.CountVO;

public interface CE1_DAO {

	public CountVO getCE1_Num_Lic_Aut_SinModificar_Pre_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Lic_Aut_Modificadas_Pre_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Lic_Aut_Reconsideradas_Pre_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Lic_Rechazadas_Pre_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Dias_Lic_Autorizados_Pre_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Dias_Lic_Reconsiderados_Pre_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Dias_Lic_Rechazados_Pre_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Lic_Aut_SinModificar_Post_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Lic_Aut_Modificadas_Post_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Lic_Aut_Reconsideradas_Post_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Lic_Rechazadas_Post_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Dias_Lic_Autorizados_Post_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Dias_Lic_Reconsiderados_Post_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Dias_Lic_Rechazados_Post_natal(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Lic_Aut_SinModificar_Enf_menor(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Lic_Aut_Modificadas_Enf_menor(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Lic_Reconsideradas_Enf_menor(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Lic_Rechazadas_Enf_menor(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Dias_Lic_Autorizados_Enf_menor(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Dias_Lic_Reconsiderados_Enf_menor(CountVO qid) throws Exception;

	public CountVO getCE1_Num_Dias_Lic_Rechazados_Enf_menor(CountVO qid) throws Exception;
}
