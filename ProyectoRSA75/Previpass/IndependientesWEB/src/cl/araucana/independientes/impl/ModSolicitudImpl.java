package cl.araucana.independientes.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.araucana.independientes.dao.ModSolicitudDAO;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.vo.SolicitudNegocioVO;
import cl.araucana.independientes.vo.SolicitudVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

/*Implementación de la clase ModSolicitudImpl */
public class ModSolicitudImpl {

    /* Función que obtiene informacion de una solicitud.
     * Recibe como entrada el rut y el folio asociados a la solicitud de la persona que se desea consultar
     * Retorna un objeto de tipo SolicitudNegocioVO con la información requerida.*/
    public static SolicitudNegocioVO obtenerSolicitud(String folio, String rut )
    {
        SolicitudNegocioVO vo=new SolicitudNegocioVO();
        vo=ModSolicitudDAO.obtenerSolicitud(folio, rut);

//      TODO SToro #parche2		
        /*if(vo!=null && vo.getSolicitudVO()!=null && vo.getSolicitudVO().getFechaIngreso()!=null)
		{
			SolicitudVO voSol=new SolicitudVO();
			voSol=vo.getSolicitudVO();				

			try {
				voSol.setResolucionDirectorio(Helper.primerDiaMesSiguiente((vo.getSolicitudVO().getFechaIngreso())));
				vo.setSolicitudVO(voSol);
			} catch (Exception e) {
				System.out.println("obtenerSolicitud_error"+e.getMessage());
			}				
		}*/				
        return vo;				
    }

    /* Función que permite modificar el estado de una solicitud.
     * Recibe como entrada el rut, el folio y el estado al que se desea cambiar
     * Realiza un update a la tabla Solicitud con el cambio requerido.*/
    public static int updateEstadoSol(String folio, String estado, String rut, String fecVigencia)
    {
        int result=0,resultUpdate=0;
        result=ModSolicitudDAO.updateEstadoSol(folio, estado, rut);

        if(result==0 && "7".equals(estado))
        {
            try {
                String fechaResolucion = Helper.primerDiaMesSiguiente(fecVigencia);
                resultUpdate=ModSolicitudDAO.updateEstadoFechaResolucion(folio, fechaResolucion);
            } catch (Exception e) {
                System.out.println("Error updateEstadoSol_"+resultUpdate+":"+e.getMessage());
            }
        }
        return result;
    }

    /* Función que permite modificar el estado de una solicitud.
     * Recibe como entrada el rut, el folio y el estado al que se desea cambiar
     * Realiza un update a la tabla Solicitud con el cambio requerido.*/
    /*public static int updateFechaResolucion(String folio, String estado, String fechaResolucion, String fechaVigencia){		
		return ModSolicitudDAO.updateEstadoFechaResolucion(folio, fechaResolucion);
	}*/	

    /* Función modificar datos de una solicitud asociado a un rut y folio.
     * Recibe como entrada la cadena que contiene los datos que serán modificados
     * Realiza un update a las tablas con los datos modificados, por medio del traspaso de la cadena
     * 	al DAO (ModSolicitudDAO) que es donde se redistribuyen los campos a los onjetos correspondientes.*/
    public static int updateSolicitud(String cadenaForm){

        return ModSolicitudDAO.updateSolicitud(cadenaForm);
    }

    /* Función que obtiene los estados posibles a los que esta asociado un determinado estado actual.
     * Recibe como entrada en estado actual
     * Retorna un arreglo de tipo Parametro con los estados asociados al estado actual.*/
    public static Parametro[] getEstadosDestinoPosibles(String estadoActual){

        return ModSolicitudDAO.getEstadosDestinoPosibles(estadoActual);
    }

    /* Función que obtiene la información de una solicitud filtrada por un determinado folio.
     * Recibe como entrada un folio existente en el sistema.
     * Retorna un objeto de tipo SolicitudNegocioVO con la información requerida.*/
    public static SolicitudNegocioVO obtenerSolicitudPorFolio(String folio)
    {
        SolicitudNegocioVO vo=new SolicitudNegocioVO();
        vo=ModSolicitudDAO.obtenerSolicitudPorFolio(folio);

//      TODO SToro #parche2	
        /*if(vo!=null && vo.getSolicitudVO()!=null && vo.getSolicitudVO().getFechaIngreso()!=null)
		{
			SolicitudVO voSol=new SolicitudVO();
			voSol=vo.getSolicitudVO();				

			try {
				voSol.setResolucionDirectorio(Helper.primerDiaMesSiguiente((vo.getSolicitudVO().getFechaIngreso())));
				vo.setSolicitudVO(voSol);
			} catch (Exception e) {
				System.out.println("Error_obtenerSolicitudPorFolio_"+e.getMessage());
			}				
		}*/				
        return vo;
    }

    /* Función que obtiene la información de una solicitud filtrada por un determinado rut.
     * Recibe como entrada el rut de una persona ingresada en el sistema.
     * Retorna un objeto de tipo SolicitudNegocioVO con la información requerida.*/
    public static SolicitudNegocioVO obtenerDatosPorRut(String rut){

        return ModSolicitudDAO.obtenerDatosPorRut(rut);
    }

    /* Función que obtiene todos los folios asociados a un determinado rut de una persona.
     * Recibe como entrada el rut de una persona existente en el sistema, y que posee varios folios ingresados.
     * Retorna un arreglo de objetos de tipo Solicitud con la información requerida.
     * Se adiciona la glosa del tipoEstadoSolicitud al objeto, tomando en consideración el id de tipoEstadoSolicitud
     */
    public static SolicitudVO[] obtenerFoliosPorRut(String rut){

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        Parametro[] param = listaParam.getListEstadoSolicitudAfiliacion();

        SolicitudVO[] temp = ModSolicitudDAO.obtenerFoliosPorRut(rut);

        for(int i = 0; i < temp.length; i++){
            int estadoTemp = 0;
            estadoTemp = temp[i].getTipoEstadoSolicitud();

            temp[i].setDesTipoEstadoSolicitud(Helper.obtenerDescripcion(param, estadoTemp));

        }

        return temp;
    }

    /* Función que obtiene los estados posibles a los que esta asociado un determinado estado actual.
     * Recibe como entrada en estado actual
     * Retorna un arreglo de tipo Parametro con los estados asociados al estado actual.*/
    public static Parametro[] getEstadosDestinoPosiblesDoc(String estadoActual){

        return ModSolicitudDAO.getEstadosDestinoPosiblesDoc(estadoActual);
    }

    /*	Funcion que valida si la fechaVigencia entregada es correcta en base a la
     * 	fechaIngreso entregada. Amban se reciben como String en formato dd/MM/yyyy
     * 
     * 	Retorna 1 = true, 0 = false, -1 = error
     */
    public static int validaFechaVigencia(String fechaVigencia, String fechaIngreso){

        int respuesta = -1;

        System.out.println("Fecha Vigencia = " + fechaVigencia);
        System.out.println("Fecha Ingreso ) " + fechaIngreso);

        String fechaVigenciaCalculadaStr = "";
        Date fechaVigenciaCalculadaDat = new Date();
        Date fechaIngresoDat = new Date();

        long diferenciaDias;

        String DATE_FORMAT = "dd/MM/yyyy"; // Formato en el que se debe recibir la cadena
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        fechaVigenciaCalculadaStr = Helper.obtenerFechaVigencia(fechaIngreso);
        try
        {
            fechaVigenciaCalculadaDat = sdf.parse(fechaVigenciaCalculadaStr);
            fechaIngresoDat = sdf.parse(fechaVigencia);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }	

        diferenciaDias = Helper.diferenciaDeDias(fechaVigenciaCalculadaDat, fechaIngresoDat);

        System.out.println("Diferencia Dias = " + diferenciaDias);

        if(diferenciaDias >= 0){
            respuesta = 1;
        }else{
            respuesta = 0;
        }

        return respuesta;
    }

    public static String obtenerFechaVigencia(String fecha){

        return Helper.obtenerFechaVigencia(fecha);

    }

    public static String recuperarFecVigencia(String folio){

        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "MM/dd/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        fecha = ModSolicitudDAO.recuperarFecVigencia(folio);

        try
        {
            date = sdf1.parse(fecha);
            return sdf2.format(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return fecha;
    }

    public static String recuperarFecFirma(String folio){

        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "MM/dd/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        fecha = ModSolicitudDAO.recuperarFecFirma(folio);

        try
        {
            date = sdf1.parse(fecha);
            return sdf2.format(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return fecha;
    }
}
