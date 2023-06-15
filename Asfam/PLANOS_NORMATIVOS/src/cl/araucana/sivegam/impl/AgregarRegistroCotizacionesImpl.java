package cl.araucana.sivegam.impl;

import cl.araucana.sivegam.dao.AgregarRegistroCotizacionesDAO;
import cl.araucana.sivegam.vo.LinSif016VO;
import cl.araucana.sivegam.vo.LinSif017VO;
import cl.araucana.sivegam.vo.LinSif018VO;
import cl.araucana.sivegam.vo.LinSif019VO;
import cl.araucana.sivegam.vo.RespuestaVO;

public class AgregarRegistroCotizacionesImpl {

    public static RespuestaVO insertSif018(LinSif018VO linsif018) {

        return AgregarRegistroCotizacionesDAO.insertSif018(linsif018);
    }

    public static RespuestaVO insertSif019(LinSif019VO linsif019) {

        return AgregarRegistroCotizacionesDAO.insertSif019(linsif019);
    }

    public static RespuestaVO insertSif017(LinSif017VO linsif017) {

        return AgregarRegistroCotizacionesDAO.insertSif017(linsif017);
    }

    public static RespuestaVO insertSif016(LinSif016VO linsif016) {

        return AgregarRegistroCotizacionesDAO.insertSif016(linsif016);
    }

    public static RespuestaVO cargarMesProcesamiento(String idTipoReporte) {

        return AgregarRegistroCotizacionesDAO.cargarMesProcesamiento(idTipoReporte);
    }
}
