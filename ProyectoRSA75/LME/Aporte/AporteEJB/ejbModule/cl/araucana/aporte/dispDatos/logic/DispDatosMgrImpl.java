package cl.araucana.aporte.dispDatos.logic;

import cl.araucana.aporte.dispDatos.bo.DispDatosResultBO;
import cl.araucana.aporte.dispDatos.dao.DispDatosDAO;
import cl.araucana.aporte.dispDatos.dao.DispDatosDAOImpl;

public class DispDatosMgrImpl implements DispDatosMgr {
    private DispDatosDAO dispDatosDAO;

    public DispDatosMgrImpl() {
        dispDatosDAO = new DispDatosDAOImpl();
    }

    public DispDatosResultBO obtenerInfoDatos (int rut){
        DispDatosResultBO afiliado = dispDatosDAO.obtenerInfoDatos(rut);
        return afiliado;
    }

}
