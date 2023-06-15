package cl.araucana.sivegam.impl;

import cl.araucana.sivegam.dao.AgregarRegistroDivisionPrevisionalDAO;
import cl.araucana.sivegam.vo.LinSif011VO;
import cl.araucana.sivegam.vo.LinSif012VO;
import cl.araucana.sivegam.vo.LinSif014VO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.TipoOrigenVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class AgregarRegistroDivisionPrevisionalImpl {

    public static RespuestaVO insertSif011(LinSif011VO linsif011) {

        return AgregarRegistroDivisionPrevisionalDAO.insertSif011(linsif011);
    }

    public static RespuestaVO insertSif012(LinSif012VO linsif012) {

        return AgregarRegistroDivisionPrevisionalDAO.insertSif012(linsif012);
    }

    public static RespuestaVO insertSif014(LinSif014VO linsif014) {

        String glosa = "";

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        TipoOrigenVO[] tipoOrigen = listaParam.getListTipoOrigen();

        for (int i = 0; i < tipoOrigen.length; i++) {
            if (tipoOrigen[i].getId_tipo_origen() == linsif014.getFuente_origen()) {
                glosa = tipoOrigen[i].getDesc_tipo_origen();
                break;
            }
        }

        linsif014.setReferencia_origen("");

        return AgregarRegistroDivisionPrevisionalDAO.insertSif014(linsif014);
    }

    public static RespuestaVO cargarMesProcesamiento(String idTipoReporte) {

        return AgregarRegistroDivisionPrevisionalDAO.cargarMesProcesamiento(idTipoReporte);
    }

    /** Funcion que obtiene data de la tabla sif012 filtrada por correlativo */
    public static LinSif012VO obtenerDataSif012(long correlativo) {

        return AgregarRegistroDivisionPrevisionalDAO.obtenerDataSif012(correlativo);
    }

    /** Funcion que obtiene data de la tabla sif011 filtrada por correlativo */
    public static LinSif011VO obtenerDataSif011(long correlativo) {

        return AgregarRegistroDivisionPrevisionalDAO.obtenerDataSif011(correlativo);
    }
}
