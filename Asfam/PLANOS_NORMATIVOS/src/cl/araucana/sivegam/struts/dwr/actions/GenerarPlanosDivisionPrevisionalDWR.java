package cl.araucana.sivegam.struts.dwr.actions;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.sivegam.impl.GenerarPlanosDivisionPrevisionalImpl;
import cl.araucana.sivegam.vo.RespuestaVO;

public class GenerarPlanosDivisionPrevisionalDWR {

    public RespuestaVO consultaRegistros(String flag, String periodoTxt, String idMaestroSiv, String mesPeriodo, String usser, String fechaReporte) throws IOException,
            SQLException {

        RespuestaVO vo = new RespuestaVO();

        switch (Integer.parseInt(flag)) {
        case 1:
            vo = GenerarPlanosDivisionPrevisionalImpl.consultaRegistrosSif011(flag, periodoTxt, idMaestroSiv, mesPeriodo, usser, fechaReporte);
            break;
        case 2:
            vo = GenerarPlanosDivisionPrevisionalImpl.consultaRegistrosSif012(flag, periodoTxt, idMaestroSiv, mesPeriodo, usser, fechaReporte);
            break;
        case 3:
            vo = GenerarPlanosDivisionPrevisionalImpl.consultaRegistrosSif014(flag, periodoTxt, idMaestroSiv, mesPeriodo, usser, fechaReporte);
            break;
        default:
            vo.setCodRespuesta(99);
            break;
        }

        return vo;

    }

}
