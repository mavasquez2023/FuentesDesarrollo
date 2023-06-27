package cl.araucana.independientes.struts.dwr.actions;

import cl.araucana.independientes.impl.ModSolicitudImpl;
import cl.araucana.independientes.vo.SolicitudNegocioVO;
import cl.araucana.independientes.vo.SolicitudVO;
import cl.araucana.independientes.vo.param.Parametro;

/* Implementaci�n de la clase ModSolicitudDWR
 * Contiene las funciones que se comunicar�n con las funciones de la clase Impl.*/
public class ModSolicitudDWR {

    /* Funci�n que obtiene informaci�n de una solicitud.
     * Recibe como parametro el rut de la persona y el folio, de la cual se requiere obtener la informaci�n.
     * Retorna un objeto SolicitudNegocioVO con los datos requeridos.*/
    public SolicitudNegocioVO obtenerSolicitud(String folio, String rut){
        return ModSolicitudImpl.obtenerSolicitud(folio, rut);
    }

    /* Funci�n que permite cambiar el estado de una solicitud
     * Recibe como par�metros el folio, el rut y el tipo de estado al que desea ser cambiado
     */
    public int updateEstadoSol(String folio, String estado, String rut, String fecVigencia){		
        return ModSolicitudImpl.updateEstadoSol(folio, estado, rut, fecVigencia);
    }

    /*Funci�n que permite cambiar la informaci�n de una solicitud de negocio.
     * Recibe como par�metros una cadena con todos los datos que ser�n cambiados.
     * La funci�n realiza un update de los datos modificados en el DAO. (ModSolicitudDAO).
     */
    public int updateSolicitud(String cadenaForm){

        return ModSolicitudImpl.updateSolicitud(cadenaForm);
    }

    /* Funci�n que obtiene los estados.
     * Recibe el estado actual*/
    public Parametro[] getEstadosDestinoPosibles(String estadoActual){

        return ModSolicitudImpl.getEstadosDestinoPosibles(estadoActual);
    }

    /* Funci�n que obtiene informaci�n de una solicitud por folio.
     * Recibe como parametro el folio de la cual se requiere obtener la informaci�n.
     * Retorna un objeto SolicitudNegocioVO con los datos requeridos.*/
    public SolicitudNegocioVO obtenerSolicitudPorFolio(String folio){
        SolicitudNegocioVO voOut=ModSolicitudImpl.obtenerSolicitudPorFolio(folio);
        System.out.println("Resultado_obtenerSolicitudPorFolio:"+voOut.getResultado());
        return voOut;
    }

    /* Funci�n que obtiene informaci�n de una solicitud por rut.
     * Recibe como parametro el rut de la persona de la cual se requiere obtener la informaci�n.
     * Retorna un objeto SolicitudNegocioVO con los datos requeridos.*/
    public SolicitudNegocioVO obtenerDatosPorRut(String rut){

        return ModSolicitudImpl.obtenerDatosPorRut(rut);
    }

    /* Funci�n que obtiene todos los folios asociados a un rut.
     * Recibe como parametro el rut de la persona de la cual se requiere obtener la informaci�n.
     * Retorna un arreglo de objetos del tipo SolicitudVO con los datos requeridos.*/
    public SolicitudVO[] obtenerFoliosPorRut(String rut){
        return ModSolicitudImpl.obtenerFoliosPorRut(rut);
    }

    /* Funci�n que obtiene los estados.
     * Recibe el estado actual*/
    public Parametro[] getEstadosDestinoPosiblesDoc(String estadoActual){

        return ModSolicitudImpl.getEstadosDestinoPosiblesDoc(estadoActual);
    }

    public int validaFechaVigencia(String fechaVigencia, String fechaIngreso){

        int respuesta = -1;

        respuesta = ModSolicitudImpl.validaFechaVigencia(fechaVigencia,fechaIngreso);

        return respuesta;
    }

    public String obtenerFechaVigencia(String fechaIngreso){

        return ModSolicitudImpl.obtenerFechaVigencia(fechaIngreso);

    }

    public String recuperarFecVigencia(String folio){

        return ModSolicitudImpl.recuperarFecVigencia(folio);
    }

    public String recuperarFecFirma(String folio){

        return ModSolicitudImpl.recuperarFecFirma(folio);
    }
}
