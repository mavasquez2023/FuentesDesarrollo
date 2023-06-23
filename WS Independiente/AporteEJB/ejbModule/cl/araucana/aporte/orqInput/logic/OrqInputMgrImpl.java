package cl.araucana.aporte.orqInput.logic;

import cl.araucana.aporte.orqInput.bo.OrqInputResultBO;
import cl.araucana.aporte.orqInput.dao.OrqInputDAO;
import cl.araucana.aporte.orqInput.dao.OrqInputDAOImpl;

public class OrqInputMgrImpl implements OrqInputMgr {
    private OrqInputDAO orqInputDAO;

    public OrqInputMgrImpl(){
        orqInputDAO = new OrqInputDAOImpl();
    }

    public OrqInputResultBO obtenerInfoPago(int rut) {
        OrqInputResultBO orqInputBO = orqInputDAO.obtenerInfoPago(rut);
        return orqInputBO;
    }

}
