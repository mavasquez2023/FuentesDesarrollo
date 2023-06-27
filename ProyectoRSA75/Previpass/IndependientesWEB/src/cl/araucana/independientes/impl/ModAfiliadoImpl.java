package cl.araucana.independientes.impl;

import cl.araucana.independientes.dao.ModAfiliadoDAO;
import cl.araucana.independientes.vo.SolicitudNegocioVO;
import cl.araucana.independientes.vo.param.Parametro;

/*Implementación de la clase ModAfiliadoImpl*/

public class ModAfiliadoImpl {

    /* Función que obtiene informacion de un afiliado
     * Recibe como entrada el rut del afiliado del que se desea consultar
     * Retorna un objeto de tipo SolicitudNegocioVO con la información requerida.*/
    public static SolicitudNegocioVO obtenerAfiliado(String rut){

        return ModAfiliadoDAO.obtenerAfiliado(rut);
    }

    /* Función que permite modificar el estado del afiliado
     * Recibe como entrada el rut del afiliado y el estado al que se desea cambiar
     * Realiza un update a la tabla Afiliado con el cambio requerido.*/
    public static int updateEstadoAfiliado(String rut, String estado){

        return ModAfiliadoDAO.updateEstadoAfiliado(rut, estado);
    }

    /* Función modificar datos del afiliado
     * Recibe como entrada la cadena que contiene los datos que serán modificados
     * Realiza un update a las tablas con los datos modificados, por medio del traspaso de la cadena
     * 	al DAO (modAfiliadoDAO) que es donde se redistribuyen los campos a los onjetos correspondientes.*/
    public static int updateAfiliado(String cadenaForm){

        return ModAfiliadoDAO.updateAfiliado(cadenaForm);
    }

    /* Función que obtiene los estados posibles a los que esta asociado un determinado estado actual.
     * Recibe como entrada en estado actual
     * Retorna un arreglo de tipo Parametro con los estados asociados al estado actual.*/
    public static Parametro[] getEstadosDestinoPosibles(String estadoActual){

        return ModAfiliadoDAO.getEstadosDestinoPosibles(estadoActual);
    }
}