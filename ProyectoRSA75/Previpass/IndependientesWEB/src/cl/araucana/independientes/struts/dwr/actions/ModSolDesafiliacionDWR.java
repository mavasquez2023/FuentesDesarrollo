package cl.araucana.independientes.struts.dwr.actions;

import cl.araucana.independientes.impl.ModSolDesafiliacionImpl;
import cl.araucana.independientes.vo.SolicitudNegocioVO;
import cl.araucana.independientes.vo.SolicitudVO;
import cl.araucana.independientes.vo.param.Parametro;

/* Implementaci�n de la clase ModSolicitudDWR
 * Contiene las funciones que se comunicar�n con las funciones de la clase Impl.*/
public class ModSolDesafiliacionDWR {

    /* Funci�n que obtiene informaci�n de una solicitud.
     * Recibe como parametro el rut de la persona y el folio, de la cual se requiere obtener la informaci�n.
     * Retorna un objeto SolicitudNegocioVO con los datos requeridos.*/
    public SolicitudNegocioVO obtenerSolicitud(String folio, String rut){
        SolicitudNegocioVO resp = ModSolDesafiliacionImpl.obtenerSolicitud(folio, rut);
        return resp;
    }

    /* Funci�n que permite cambiar el estado de una solicitud
     * Recibe como par�metros el folio, el rut y el tipo de estado al que desea ser cambiado
     */
    public int updateEstadoSol(String folio, String estado, String rut, String fecVigencia){		
        return ModSolDesafiliacionImpl.updateEstadoSol(folio, estado, rut, fecVigencia);
    }

    /*Funci�n que permite cambiar la informaci�n de una solicitud de negocio.
     * Recibe como par�metros una cadena con todos los datos que ser�n cambiados.
     * La funci�n realiza un update de los datos modificados en el DAO. (ModSolicitudDAO).
     */
    public int updateSolicitud(String cadenaForm){

        return ModSolDesafiliacionImpl.updateSolicitud(cadenaForm);
    }

    /* Funci�n que obtiene los estados.
     * Recibe el estado actual*/
    public Parametro[] getEstadosDestinoPosibles(String estadoActual){

        return ModSolDesafiliacionImpl.getEstadosDestinoPosibles(estadoActual);
    }

    /* Funci�n que obtiene informaci�n de una solicitud por folio.
     * Recibe como parametro el folio de la cual se requiere obtener la informaci�n.
     * Retorna un objeto SolicitudNegocioVO con los datos requeridos.*/
    public SolicitudNegocioVO obtenerSolicitudPorFolio(String folio){
        SolicitudNegocioVO voOut=ModSolDesafiliacionImpl.obtenerSolicitudPorFolio(folio);
        System.out.println("Resultado_obtenerSolicitudPorFolio:"+voOut.getResultado());
        return voOut;
    }

    /* Funci�n que obtiene informaci�n de una solicitud por rut.
     * Recibe como parametro el rut de la persona de la cual se requiere obtener la informaci�n.
     * Retorna un objeto SolicitudNegocioVO con los datos requeridos.*/
    public SolicitudNegocioVO obtenerDatosPorRut(String rut){

        return ModSolDesafiliacionImpl.obtenerDatosPorRut(rut);
    }

    /* Funci�n que obtiene todos los folios asociados a un rut.
     * Recibe como parametro el rut de la persona de la cual se requiere obtener la informaci�n.
     * Retorna un arreglo de objetos del tipo SolicitudVO con los datos requeridos.*/
    public SolicitudVO[] obtenerFoliosPorRut(String rut){
        return ModSolDesafiliacionImpl.obtenerFoliosPorRut(rut);
    }

    /* Funci�n que obtiene los estados.
     * Recibe el estado actual*/
    public Parametro[] getEstadosDestinoPosiblesDoc(String estadoActual){

        return ModSolDesafiliacionImpl.getEstadosDestinoPosiblesDoc(estadoActual);
    }

    public int validaFechaVigencia(String fechaVigencia, String fechaIngreso){

        int respuesta = -1;

        respuesta = ModSolDesafiliacionImpl.validaFechaVigencia(fechaVigencia,fechaIngreso);

        return respuesta;
    }

    public String obtenerFechaVigencia(String fechaIngreso){

        return ModSolDesafiliacionImpl.obtenerFechaVigencia(fechaIngreso);

    }

    public String recuperarFecVigencia(String folio){

        return ModSolDesafiliacionImpl.recuperarFecVigencia(folio);
    }

    public String recuperarFecFirma(String folio){

        return ModSolDesafiliacionImpl.recuperarFecFirma(folio);
    }
}
