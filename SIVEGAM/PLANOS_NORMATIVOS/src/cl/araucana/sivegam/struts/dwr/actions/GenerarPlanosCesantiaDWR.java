package cl.araucana.sivegam.struts.dwr.actions;

import java.sql.SQLException;

import cl.araucana.sivegam.impl.GenerarPlanoCesantiaImpl;
import cl.araucana.sivegam.vo.RespuestaVO;

public class GenerarPlanosCesantiaDWR {

    public static RespuestaVO generarPlanosCesantia(int tipoReporte, String periodo, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte)
            throws SQLException, Exception {

        RespuestaVO resp = new RespuestaVO();
        switch (tipoReporte) {
        case 10:
            resp = GenerarPlanoCesantiaImpl.generarPlanoCesantia041(tipoReporte, periodo, idMaestroSiv, mesPeriodo, usser, fechaReporte);
            break;
        case 11:
            resp = GenerarPlanoCesantiaImpl.generarPlanoCesantia042(tipoReporte, periodo, idMaestroSiv, mesPeriodo, usser, fechaReporte);
            break;
        case 12:
            resp = GenerarPlanoCesantiaImpl.generarPlanoCesantia043(tipoReporte, periodo, idMaestroSiv, mesPeriodo, usser, fechaReporte);
            break;
        case 13:
            resp = GenerarPlanoCesantiaImpl.generarPlanoCesantia044(tipoReporte, periodo, idMaestroSiv, mesPeriodo, usser, fechaReporte);
            break;
        default:
            resp.setCodRespuesta(99);
            break;
        }

        return resp;
    }
}
