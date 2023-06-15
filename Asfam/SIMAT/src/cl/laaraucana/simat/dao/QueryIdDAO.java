package cl.laaraucana.simat.dao;

import cl.laaraucana.simat.entidades.QueryIdVO;

public interface QueryIdDAO {
	public QueryIdVO ObtenerMinMaxId(QueryIdVO qid) throws Exception;
}
