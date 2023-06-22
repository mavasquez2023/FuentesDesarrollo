package cl.araucana.sivegam.struts.dwr.actions;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.impl.GenerarListadoErroresImpl;
import cl.araucana.sivegam.vo.PeriodoProcesoVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif011VO;
import cl.araucana.sivegam.vo.Sif012VO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GenerarListadoErroresDWR {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public Sif011VO obtenerDataRetroSif011(String periodo, String flag) throws IOException, SQLException {

        return GenerarListadoErroresImpl.obtenerDataRetroSif011(periodo, flag);
    }

    public Sif012VO obtenerDataRetroSif012(String periodo, String flag) throws IOException, SQLException {

        return GenerarListadoErroresImpl.obtenerDataRetroSif012(periodo, flag);
    }

    /** Obtiene la glosa del mes. */
    public static PeriodoProcesoVO obtenerGlosaMes(int mesPeriodo) {

        PeriodoProcesoVO periodo = new PeriodoProcesoVO();
        ListadoParametros lp = ListadoParametros.getInstancia();
        PeriodoProcesoVO[] perPro = lp.getListPeriodoProcesos();
        for (int i = 0; i < perPro.length; i++) {
            if (perPro[i].getPeriodo_proceso() == mesPeriodo) {
                periodo.setDescripcion_periodo_proceso(perPro[i].getDescripcion_periodo_proceso());
                break;
            }
        }

 //       logger.debug("glosa periodo: " + periodo.getDescripcion_periodo_proceso());
        return periodo;
    }

    public static RespuestaVO eliminarDatoErroneoSif011(long correlativo) {

        return GenerarListadoErroresImpl.eliminarDatoErroneoSif011(correlativo);
    }

    public static RespuestaVO eliminarDatoErroneoSif012(long correlativo) {

        return GenerarListadoErroresImpl.eliminarDatoErroneoSif012(correlativo);
    }
}
