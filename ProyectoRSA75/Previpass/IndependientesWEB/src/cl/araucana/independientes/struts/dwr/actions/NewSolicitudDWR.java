package cl.araucana.independientes.struts.dwr.actions;

import cl.araucana.independientes.impl.NewSolicitudImpl;
import cl.araucana.independientes.vo.AnalistaVO;
import cl.araucana.independientes.vo.SolicitudNegocioVO;

/* Implementación de la clase NewSolicitudDWR
 * Contiene las funciones que se comunicarán con las funciones de la clase Impl.*/
public class NewSolicitudDWR{

    /* Función que obtiene el idCaptador.
     * Recibe como parametro el rut de analista
     * retorna un objeto AnalistaVO con los datos requeridos.*/

    public AnalistaVO obtenerCaptador(String rut){

        return NewSolicitudImpl.obtenerCaptador(rut);
    }

    /* Función que obtiene la información de una solicitud.
     * Recibe como entrada el rut de la persona de la cual se quieren obtener los datos.
     * Retorna un objeto SolicitudNegocioVO con la información requerida.*/
    public SolicitudNegocioVO obtenerSolicitud(String rut){

        return NewSolicitudImpl.obtenerAfiliado(rut);
    }
}
