package cl.araucana.aporte.orqOutput.logic;

import cl.araucana.aporte.orqOutput.bo.OrqOutputResultBO;
import cl.araucana.aporte.orqOutput.dao.OrqOutputDAO;
import cl.araucana.aporte.orqOutput.dao.OrqOutputDAOImpl;

public class OrqOutputMgrImpl implements OrqOutputMgr {

    private OrqOutputDAO orqOutputDAO;

    public OrqOutputMgrImpl(){
        orqOutputDAO = new OrqOutputDAOImpl();
    }

    public OrqOutputResultBO recuperacionPago  (int rut, int periodoAporte, int montoCredito, int montoLeasing, int montoAporte, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago) {
        OrqOutputResultBO orqOutputBO =  orqOutputDAO.recuperacionPago(rut, periodoAporte, montoCredito, montoLeasing, montoAporte, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
        return orqOutputBO;
    }

}
