package cl.araucana.independientes.struts.dwr.actions;

import cl.araucana.independientes.impl.NewSolicitudImpl;
import cl.araucana.independientes.vo.AnalistaVO;
import cl.araucana.independientes.vo.SolicitudNegocioVO;

/* Implementaci�n de la clase NewSolicitudDWR
 * Contiene las funciones que se comunicar�n con las funciones de la clase Impl.*/
public class NewSolicitudDWR{

    /* Funci�n que obtiene el idCaptador.
     * Recibe como parametro el rut de analista
     * retorna un objeto AnalistaVO con los datos requeridos.*/

    public AnalistaVO obtenerCaptador(String rut){

        return NewSolicitudImpl.obtenerCaptador(rut);
    }

    /* Funci�n que obtiene la informaci�n de una solicitud.
     * Recibe como entrada el rut de la persona de la cual se quieren obtener los datos.
     * Retorna un objeto SolicitudNegocioVO con la informaci�n requerida.*/
    public SolicitudNegocioVO obtenerSolicitud(String rut){

        return NewSolicitudImpl.obtenerAfiliado(rut);
    }
}
