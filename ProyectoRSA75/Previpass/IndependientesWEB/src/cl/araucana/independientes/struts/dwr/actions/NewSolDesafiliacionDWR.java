package cl.araucana.independientes.struts.dwr.actions;

import cl.araucana.independientes.impl.NewSolDesafiliacionImpl;
import cl.araucana.independientes.impl.SolBeneficiosImpl;
import cl.araucana.independientes.vo.AfiliadoBeneficiosVO;
import cl.araucana.independientes.vo.SolicitudNegocioVO;

/* Implementación de la clase ModSolicitudDWR
 * Contiene las funciones que se comunicarán con las funciones de la clase Impl.*/
public class NewSolDesafiliacionDWR {


    /* Función que obtiene información de una solicitud por rut.
     * Recibe como parametro el rut de la persona de la cual se requiere obtener la información.
     * Retorna un objeto SolicitudNegocioVO con los datos requeridos.*/
    public SolicitudNegocioVO obtenerDatosPorRut(String rut){
        SolicitudNegocioVO res = NewSolDesafiliacionImpl.obtenerDatosPorRut(rut);
        //System.out.println("---------------------------------->"+res.getDireccionPartVO().getRegion());
        return res;
    }
    public String obtenerParametroList(){

        return NewSolDesafiliacionImpl.obtenerParametroList();	  

    }

    public String obtenerFechaSistema(){
        return NewSolDesafiliacionImpl.obtenerFechaSistema();
    }

    public String obtenerDatosAfiliado(String rut){

        String respuesta = null;
        AfiliadoBeneficiosVO afiliadoBeneficiosVO = null;
        afiliadoBeneficiosVO = SolBeneficiosImpl.obtenerDatosAfiliado(rut);
        respuesta = String.valueOf(afiliadoBeneficiosVO.getEstadoAporte());
        return respuesta;
    }

    public String obtenerDeudaAporte(String rut){ 		
        String montoDeuda = null;
        montoDeuda = NewSolDesafiliacionImpl.obtenerMontoTotalDeudaAporte(rut);
        System.out.println("montoDeuda: "+ montoDeuda);
        return montoDeuda;
    }
}
