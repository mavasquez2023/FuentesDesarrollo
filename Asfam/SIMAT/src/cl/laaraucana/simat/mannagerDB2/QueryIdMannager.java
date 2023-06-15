package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.QueryIdDAO;
import cl.laaraucana.simat.entidades.QueryIdVO;

public class QueryIdMannager {

	public QueryIdVO getMinMaxId(QueryIdVO qid) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		QueryIdDAO queryIdDAO = df.getMinMaxId();
		return queryIdDAO.ObtenerMinMaxId(qid);
	}

}
