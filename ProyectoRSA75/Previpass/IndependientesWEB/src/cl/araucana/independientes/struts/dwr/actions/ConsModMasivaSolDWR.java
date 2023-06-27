package cl.araucana.independientes.struts.dwr.actions;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.independientes.impl.ConsModMasivaSolImpl;
import cl.araucana.independientes.vo.ConsModMasivaSolVO;
import cl.araucana.independientes.vo.SolicitudVO;
import cl.araucana.independientes.vo.param.Parametro;

public class ConsModMasivaSolDWR {

    public ConsModMasivaSolVO consultaMasivaSolicitudes(String fechaInicio, String fechaFin, String estado, String usuario, String fechaActual){

        ConsModMasivaSolImpl impl = new ConsModMasivaSolImpl();

        ConsModMasivaSolVO ret = new ConsModMasivaSolVO();

        try{

            ret = impl.consultaMasivaSolicitudes(fechaInicio, fechaFin, estado, usuario, fechaActual);

            return ret;

        }catch(IOException e){

        }catch(SQLException f){

        }

        return ret;	
    }

    /*funcion que trae idsolicitud*/
    public SolicitudVO retornaIdSolicitud( String folio){

        return ConsModMasivaSolImpl.retornaIdSolcitud( folio);
    }

    /*funcion update estado de la solicitud*/
    public int updateMasivaSolicitud(String folio, String tipoEstado, String rut){

        return ConsModMasivaSolImpl.updateMasivaSolcitud(folio, tipoEstado, rut);
    }

    /*Función que obtiene los estados.
     * Recibe el estado actual*/
    public Parametro[] getEstadosDestinoPosibles(String estadoActual){

        return ConsModMasivaSolImpl.getEstadosDestinoPosibles(estadoActual);
    }

    public String obtenerUltimaDiaMes(String fechaActual){

        return ConsModMasivaSolImpl.obtenerUltimaDiaMes(fechaActual);
    }
}
