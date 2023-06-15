package cl.araucana.sivegam.struts.dwr.actions;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.sivegam.impl.GenerarPlanosCotizacionesImpl;
import cl.araucana.sivegam.vo.RespuestaVO;

public class GenerarPlanosCotizacionesDWR {

    public RespuestaVO consultaRegistros(String flag, String periodoTxt, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte) throws IOException,
            SQLException {

        RespuestaVO resp = new RespuestaVO();

        switch (Integer.parseInt(flag)) {
        case 4:
            resp = GenerarPlanosCotizacionesImpl.consultaRegistrosSif016(flag, periodoTxt, idMaestroSiv, mesPeriodo, usser, fechaReporte);
            break;
        case 5:
            resp = GenerarPlanosCotizacionesImpl.consultaRegistrosSif017(flag, periodoTxt, idMaestroSiv, mesPeriodo, usser, fechaReporte);
            break;
        case 6:
            resp = GenerarPlanosCotizacionesImpl.consultaRegistrosSif018(flag, periodoTxt, idMaestroSiv, mesPeriodo, usser, fechaReporte);
            break;
        case 7:
            resp = GenerarPlanosCotizacionesImpl.consultaRegistrosSif019(flag, periodoTxt, idMaestroSiv, mesPeriodo, usser, fechaReporte);
            break;
        default:
            resp.setCodRespuesta(99);
            break;
        }

        return resp;
    }
}
