package cl.laaraucana.simat.dao;

import cl.laaraucana.simat.entidades.CountVO;

public interface CE4_DAO {

	public CountVO getCE4_NumSubs_JornadaCompleta_Iniciados_Parental(CountVO qid) throws Exception;

	public CountVO getCE4_NumSubs_JornadaParcial_Iniciados_Parental(CountVO qid) throws Exception;

	public CountVO getCE4_NumSubs_JornadaCompleta_Traspasados_Parental(CountVO qid) throws Exception;

	public CountVO getCE4_NumSubs_JornadaParcial_Traspasados_Parental(CountVO qid) throws Exception;

	public CountVO getCE4_NumSubs_JornadaCompleta_Pagados_Parental(CountVO qid) throws Exception;

	public CountVO getCE4_NumSubs_JornadaParcial_Pagados_Parental(CountVO qid) throws Exception;
}
