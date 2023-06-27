package cl.araucana.independientes.struts.dwr.actions;

import cl.araucana.independientes.impl.ModAfiliadoImpl;
import cl.araucana.independientes.vo.SolicitudNegocioVO;
import cl.araucana.independientes.vo.param.Parametro;

/* Implementaci�n de la clase ModAfiliadoDWR
 * Contiene las funciones que se comunicar�n con las funciones de la clase Impl.*/
public class ModAfiliadoDWR {

    /*Funci�n que permite obtener la informaci�n completa de un determinado afiliado.
     * Recibe como entrada el rut del afiliado del que se desea consultar.
     * Retorna un objeto SolicitudNegocioVO con la informacion requerida*/
    public SolicitudNegocioVO obtenerAfiliado(String rut){
        SolicitudNegocioVO resp = ModAfiliadoImpl.obtenerAfiliado(rut);
        //System.out.println("folio -------------->"+resp.getSolicitudVO().getFolio());
        return resp; 

    }

    /*Funci�n que permite modificar el estado del afiliado en el sistema.
     * Recibe como entrada el rut del afiliado y el nuevo estado al que se desea cambiar.
     * La funci�n realiza un update en la tabla Afiliado*/
    public int updateEstadoAfiliado(String rut, String estado){

        return ModAfiliadoImpl.updateEstadoAfiliado(rut, estado);

    }

    /*	Funci�n que permite modificar la informaci�n de un afiliado
     *  Recibe como entrada una cadena que contiene todos los datos que ser�n cambiados en las tablas.
     *  Los datos de la cadena son sacados de la misma e insertados en los distintos objetos a los que pertenecen.
     *  Realiza un update en todas las tablas asociadas al proceso de modificacion del afiliado.*/
    public int updateAfiliado(String cadenaForm){

        return ModAfiliadoImpl.updateAfiliado(cadenaForm);

    }

    /*Funci�n que obtiene los distintos estados por los que puede pasar un estado actual.
     * Recibe como entrada el estado actual
     * Retorna un arreglo con los distintos estados que estan asociados a ese estado actual. */
    public Parametro[] getEstadosDestinoPosibles(String estadoActual){

        return ModAfiliadoImpl.getEstadosDestinoPosibles(estadoActual);
    }
}
