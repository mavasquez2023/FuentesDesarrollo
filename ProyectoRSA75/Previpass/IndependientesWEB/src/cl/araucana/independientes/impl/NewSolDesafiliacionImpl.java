package cl.araucana.independientes.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cl.araucana.independientes.dao.NewSolDesafiliacionDAO;
import cl.araucana.independientes.dao.NewSolicitudDAO;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.vo.AfiliadoVO;
import cl.araucana.independientes.vo.AgrupacionVO;
import cl.araucana.independientes.vo.AnalistaVO;
import cl.araucana.independientes.vo.DireccionVO;
import cl.araucana.independientes.vo.EmailVO;
import cl.araucana.independientes.vo.GrupoFamiliarVO;
import cl.araucana.independientes.vo.IngresoEconomicoVO;
import cl.araucana.independientes.vo.PersonaVO;
import cl.araucana.independientes.vo.RespuestaVO;
import cl.araucana.independientes.vo.SolicitudNegocioVO;
import cl.araucana.independientes.vo.SolicitudVO;
import cl.araucana.independientes.vo.TelefonoVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

/*Implementación de la clase ModSolicitudImpl */
public class NewSolDesafiliacionImpl {

    /* Función que obtiene la información de una solicitud filtrada por un determinado rut.
     * Recibe como entrada el rut de una persona ingresada en el sistema.
     * Retorna un objeto de tipo SolicitudNegocioVO con la información requerida.*/
    public static SolicitudNegocioVO obtenerDatosPorRut(String rut){

        return NewSolDesafiliacionDAO.obtenerDatosPorRut(rut);
    }

    /*Función que setea los datos para realizar un insert de una nueva solicitud.
     * Recibe como entrada un request con la información ingresada desde la capa vista.( formDeSolicitud.jsp)
     * Retorna un objeto SolicitudNegocioVO con los datos requeridos, para ser insertados.
     * */
    public static RespuestaVO insertarNuevaSolicitud(HttpServletRequest request ){

        /*Declaracion de variables para setear fechas.*/
        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);

        //Declaracion de objeto SolicitudNegocioVO que contendrá la información para ser insertada.
        SolicitudNegocioVO solNegocio = new SolicitudNegocioVO();

        //declaración de objetos 
        AfiliadoVO afiliadoVO =new AfiliadoVO();
        AnalistaVO analistaVO = new AnalistaVO();
        DireccionVO direccionParticularVO = new DireccionVO();
        EmailVO emailVO = new EmailVO();
        PersonaVO personaVO = new PersonaVO();
        SolicitudVO solicitudVO = new SolicitudVO();
        TelefonoVO telefonoCelularVO = new TelefonoVO();
        TelefonoVO telefonoContactoVO = new TelefonoVO();


        //declaracion de variable de sesión.
        HttpSession session = request.getSession();

        /*Encabezado*/


        solicitudVO.setOficina(Integer.parseInt(request.getParameter("dbx_Oficina")));

        fecha = (String)session.getAttribute("FechaSistema");

        try{
            date = sdf1.parse(fecha);

        }catch (ParseException e) {
            date = new Date();
            e.printStackTrace();
        }

        solicitudVO.setFechaIngreso(fecha);
        solicitudVO.setFechaIngresoDate(date);

        /*Parte 1.- Identificacion*/
        //datos que van a la tabla SolicitudVO
        solicitudVO.setFolio(Long.parseLong(request.getParameter("txt_Folio")));

        //Datos que serán insertados en la tabla PersonasVO
        personaVO.setIdDocumento(Long.parseLong(request.getParameter("txt_Rut")));
        personaVO.setDigVerificador(request.getParameter("txt_NumVerif"));
        personaVO.setApellidoPaterno(request.getParameter("txt_ApePat"));
        personaVO.setApellidoMaterno(request.getParameter("txt_ApeMat"));
        personaVO.setNombres(request.getParameter("txt_Nombres"));

        //datos que seran insertados en la tabla TelefonoVO
        telefonoCelularVO.setCodArea(request.getParameter("txt_codAreaCelular"));
        telefonoCelularVO.setNroTelefono(request.getParameter("txt_TelCelular"));

        telefonoCelularVO.setFlagTelefono(Integer.parseInt(request.getParameter("chk_flagCelu")));

        //Se comentan porque estos campos ahora son obligatorios.
        if(request.getParameter("txt_codAreaCelular").equals("") || request.getParameter("txt_codAreaCelular").trim().equals(null)){
            telefonoCelularVO.setCodArea("0");
        }else{
            telefonoCelularVO.setCodArea(request.getParameter("txt_codAreaCelular"));
        }

        if(request.getParameter("txt_TelCelular").equals("") || request.getParameter("txt_TelCelular").trim().equals(null)){
            telefonoCelularVO.setNroTelefono("0");
        }else{
            telefonoCelularVO.setNroTelefono(request.getParameter("txt_TelCelular"));
        }

        if(request.getParameter("txt_codAreaContacto").equals("") || request.getParameter("txt_codAreaContacto").trim().equals(null)){
            telefonoContactoVO.setCodArea("0");
        }else{
            telefonoContactoVO.setCodArea(request.getParameter("txt_codAreaContacto"));
        }
        if(request.getParameter("txt_TelContacto").equals("") || request.getParameter("txt_TelContacto").trim().equals(null)){
            telefonoContactoVO.setNroTelefono("0");
        }else{
            telefonoContactoVO.setNroTelefono(request.getParameter("txt_TelContacto"));
        }

        //datos que son insertados en la tabla EmailVO ahora es obligatorio
        emailVO.setDireccMail(request.getParameter("txt_Email"));
        emailVO.setFlagEmail(Integer.parseInt(request.getParameter("chk_flagEmail")));

        //datos que van en la tabla DireccionVO
        direccionParticularVO.setGlosCalle(request.getParameter("txt_Calle"));
        direccionParticularVO.setNumDireccion(request.getParameter("txt_Numero"));
        direccionParticularVO.setPoblacionVilla(request.getParameter("txt_PoblVilla"));
        direccionParticularVO.setDpto(request.getParameter("txt_Departamento"));
        direccionParticularVO.setRegion(Integer.parseInt(request.getParameter("dbx_Region")));
        direccionParticularVO.setCiudad(Integer.parseInt(request.getParameter("dbx_Provincia")));
        direccionParticularVO.setComuna(Integer.parseInt(request.getParameter("dbx_Comuna")));
        direccionParticularVO.setFlagCalle(Integer.parseInt(request.getParameter("chk_flagCalle")));

        /*parte 4 - Informacion de afiliacion*/
        solicitudVO.setTipoCajaOrigen(Long.parseLong(request.getParameter("dbx_CajaCompensacion")));
        solicitudVO.setTipoMotivoDesafiliacion(Integer.parseInt(request.getParameter("dbx_Motivo")));
        solicitudVO.setDescTipoMotivoDesafiliacion(Integer.parseInt(request.getParameter("dbx_DescMotivo")));
        solicitudVO.setObservaciones(request.getParameter("txt_Observaciones"));


        solicitudVO.setHoraCaptacion(request.getParameter("txt_Hora"));
        solicitudVO.setComentarios(request.getParameter("txt_Comentarios"));


        fecha = request.getParameter("txt_FecVigAfil");

        try{
            date = sdf1.parse(fecha);

        }catch (ParseException e) {
            date = new Date();
            e.printStackTrace();
        }
        solicitudVO.setFechaVigenciaDate(date);
        solicitudVO.setFechaVigencia(fecha);


        fecha = request.getParameter("txt_FecUltApo");

        try{
            date = sdf1.parse(fecha);

        }catch (ParseException e) {
            date = new Date();
            e.printStackTrace();
        }
        solicitudVO.setFechaUltAporteDate(date);
        solicitudVO.setFechaUltAporte(fecha);

        fecha = request.getParameter("txt_FecSolDesaf");

        try{
            date = sdf1.parse(fecha);

        }catch (ParseException e) {
            date = new Date();
            e.printStackTrace();
        }
        solicitudVO.setFechaFirmaDate(date);
        solicitudVO.setFechaFirma(fecha);

        /* Parte 5 - Antecedentes proceso afiliacion*/
        //Datos que van a la tabla AnalistaVO
        analistaVO.setIdAnalista(Long.parseLong((String)session.getAttribute("IDAnalista")));
        analistaVO.setApellidoPaterno((String)session.getAttribute("ApePatAnalista"));
        analistaVO.setApellidoMaterno((String)session.getAttribute("ApeMatAnalista"));
        analistaVO.setNombres((String)session.getAttribute("NombreAnalista"));		
        analistaVO.setOficina(Integer.parseInt(request.getParameter("dbx_Oficina")));

        /*insertar los objetos a solNegocio*/
        solNegocio.setSolicitudVO(solicitudVO);
        solNegocio.setPersonaVO(personaVO);
        solNegocio.setTelefonoCeluVO(telefonoCelularVO);
        solNegocio.setTelefonoPartVO(telefonoContactoVO);
        solNegocio.setEmailVO(emailVO);
        solNegocio.setDireccionPartVO(direccionParticularVO);
        solNegocio.setAfiliadoVO(afiliadoVO);
        solNegocio.setAnalistaVO(analistaVO);


        return NewSolDesafiliacionDAO.insertarNuevaSolicitud(solNegocio);
    }

    public static String obtenerParametroList(){
        Parametro[] parametros = null;
        String retornar;
        parametros = NewSolDesafiliacionDAO.obtenerParametroList("72");
        retornar = parametros[0].getGlosa();  
        System.out.println("retornarJLGN: "+ retornar);
        return retornar;
    }

    public static String obtenerFechaSistema(){
        return  NewSolDesafiliacionDAO.obtenerFechaSistema();

    }

    //INICIO REQ 7002
    public static String obtenerMontoTotalDeudaAporte(String rut){
        return  NewSolDesafiliacionDAO.obtenerMontoTotalDeudaAporte(rut);	
    }
    //FIN REQ 7002

}
