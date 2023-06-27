package cl.araucana.independientes.impl;

import java.io.IOException;
import java.sql.SQLException;
import cl.araucana.independientes.dao.PendientesIntercajaDAO;
import cl.araucana.independientes.vo.PendientesIntercajaVO;
import cl.araucana.independientes.vo.param.ParametroLong;

public class PendientesIntercajaImpl 
{
    public static PendientesIntercajaVO obtenerCasosPendientes(String idMaestroArchivo, String tipoArchivo)throws IOException,SQLException
    {	
        PendientesIntercajaVO resp = new PendientesIntercajaVO();

        resp.setLisPendientesIntercaja(PendientesIntercajaDAO.obtenerCasosPendientes(idMaestroArchivo, tipoArchivo));

        return resp;
    }

    public static PendientesIntercajaVO obtenerDetallePendiente(String idDetalleFile, String idFileType)throws IOException,SQLException
    {
        PendientesIntercajaVO resp = new PendientesIntercajaVO();

        resp.setLisPendientesIntercaja(PendientesIntercajaDAO.obtenerDetallePendiente(idDetalleFile, idFileType));

        return resp;
    }

    public static PendientesIntercajaVO obtenerDataGrillaColisiones(String numeroRegistro)throws IOException,SQLException
    {
        PendientesIntercajaVO resp = new PendientesIntercajaVO();

        resp.setLisPendientesIntercaja(PendientesIntercajaDAO.obtenerDataGrillaColisiones(numeroRegistro));

        return resp;
    }

    public static ParametroLong[] getFiltroArchivosPorFechayTipo(String fechaDesde, String fechaHasta, String tipoArchivo)
    {
        return PendientesIntercajaDAO.getFiltroArchivosPorFechayTipo(fechaDesde, fechaHasta, tipoArchivo);
    }
}
