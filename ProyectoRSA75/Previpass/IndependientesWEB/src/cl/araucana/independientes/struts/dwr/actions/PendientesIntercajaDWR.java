package cl.araucana.independientes.struts.dwr.actions;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.independientes.impl.PendientesIntercajaImpl;
import cl.araucana.independientes.vo.PendientesIntercajaVO;
import cl.araucana.independientes.vo.param.ParametroLong;

public class PendientesIntercajaDWR {

    /*Obtiene todos los casos pendientes dentro de un rango determinado de fechas y diferenciados por el tipo de archivo*/
    public PendientesIntercajaVO obtenerCasosPendientes(String idMaestroArchivo, String tipoArchivo)
    {
        PendientesIntercajaVO resp = new PendientesIntercajaVO();

        try
        {
            resp = PendientesIntercajaImpl.obtenerCasosPendientes(idMaestroArchivo, tipoArchivo);

            return resp;

        }catch(IOException e){

        }catch(SQLException f){

        }

        return resp;
    }

    /*Consulta por data de detalle de registro, dado el idDetalleFile*/
    public PendientesIntercajaVO obtenerDetallePendiente(String idDetalleFile, String idFileType)
    {
        PendientesIntercajaVO resp = new PendientesIntercajaVO();

        try{
            resp = PendientesIntercajaImpl.obtenerDetallePendiente(idDetalleFile, idFileType);

        }catch(IOException e){

        }catch(SQLException f){

        }

        return resp;
    }

    public PendientesIntercajaVO obtenerDataGrillaColisiones(String numeroRegistro)
    {
        PendientesIntercajaVO resp = new PendientesIntercajaVO();

        try{

            resp = PendientesIntercajaImpl.obtenerDataGrillaColisiones(numeroRegistro);

            return resp;

        }catch(IOException e){

        }catch(SQLException f){

        }

        return resp;
    }

    public ParametroLong[] getFiltroArchivosPorFechayTipo(String fechaDesde, String fechaHasta, String tipoArchivo)
    {
        return PendientesIntercajaImpl.getFiltroArchivosPorFechayTipo(fechaDesde, fechaHasta, tipoArchivo);

    }
}
