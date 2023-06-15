package cl.araucana.sivegam.impl;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.sivegam.dao.GenerarListadoErroresDAO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif011VO;
import cl.araucana.sivegam.vo.Sif012VO;

public class GenerarListadoErroresImpl {

    public static Sif011VO obtenerDataRetroSif011(String periodo, String flag) throws IOException, SQLException {

        Sif011VO sif011 = new Sif011VO();
        sif011.setListSif011(GenerarListadoErroresDAO.obtenerDataRetroSif011(periodo, flag));
        return sif011;
    }

    public static Sif012VO obtenerDataRetroSif012(String periodo, String flag) throws IOException, SQLException {

        Sif012VO sif012 = new Sif012VO();
        sif012.setListSif012(GenerarListadoErroresDAO.obtenerDataRetroSif012(periodo, flag));
        return sif012;
    }

    public static RespuestaVO eliminarDatoErroneoSif011(long correlativo) {

        return GenerarListadoErroresDAO.eliminarDatoErroneoSif011(correlativo);
    }

    public static RespuestaVO eliminarDatoErroneoSif012(long correlativo) {

        return GenerarListadoErroresDAO.eliminarDatoErroneoSif012(correlativo);
    }
}
