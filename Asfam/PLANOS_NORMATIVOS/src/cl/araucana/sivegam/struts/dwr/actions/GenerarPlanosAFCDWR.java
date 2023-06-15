package cl.araucana.sivegam.struts.dwr.actions;

import java.sql.SQLException;

import cl.araucana.sivegam.impl.GenerarPlanoAFCImpl;
import cl.araucana.sivegam.vo.RespuestaVO;

public class GenerarPlanosAFCDWR {

    public static RespuestaVO generarPlanosAfc(String tipoReporte, String flagReporte, String periodo, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte)
            throws SQLException, Exception {

        /** FlagReporte puede ser M o R */
        RespuestaVO respuesta = new RespuestaVO();

        respuesta = GenerarPlanoAFCImpl.generarPlanosAfc(tipoReporte, flagReporte, periodo, idMaestroSiv, mesPeriodo, usser, fechaReporte);

        return respuesta;
    }
}
