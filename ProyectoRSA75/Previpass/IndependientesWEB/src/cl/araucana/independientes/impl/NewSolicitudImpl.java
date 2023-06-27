package cl.araucana.independientes.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.independientes.dao.ModAfiliadoDAO;
import cl.araucana.independientes.dao.NewSolicitudDAO;
import cl.araucana.independientes.vo.AfiliadoVO;
import cl.araucana.independientes.vo.AgrupacionVO;
import cl.araucana.independientes.vo.AnalistaVO;
import cl.araucana.independientes.vo.BaseComunIntercajaVO;
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

/* Implementación de la clase NewSolicitudImpl
 * Dicha clase contiene los datos que vienen del formulario (desde la capa vista) y que son traspasados
 * al DAO (NewSolicitudDAO)*/

public class NewSolicitudImpl {

    /*Función que setea los datos para realizar un insert de una nueva solicitud.
     * Recibe como entrada un request con la información ingresada desde la capa vista.( formDeSolicitud.jsp)
     * Retorna un objeto SolicitudNegocioVO con los datos requeridos, para ser insertados.
     * */

    public static RespuestaVO insertarNuevaSolicitud(HttpServletRequest request ){

        /*Declaracion de variables para setear fechas.*/
        String fecha = "";
        String horaCaptacion="";
        Date date = new Date();

        String DATE_FORMAT1 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);

        //Declaracion de objeto SolicitudNegocioVO que contendrá la información para ser insertada.
        SolicitudNegocioVO solNegocio = new SolicitudNegocioVO();

        //declaración de objetos 
        AfiliadoVO afiliadoVO =new AfiliadoVO();
        AnalistaVO analistaVO = new AnalistaVO();
        DireccionVO direccionParticularVO = new DireccionVO();
        DireccionVO direccionComercialVO = new DireccionVO();
        EmailVO emailVO = new EmailVO();
        EmailVO emailComerVO = new EmailVO();		
        GrupoFamiliarVO grupoFamiliarVO = new GrupoFamiliarVO();
        IngresoEconomicoVO ingresoEconomicoVO = new IngresoEconomicoVO();
        PersonaVO personaVO = new PersonaVO();
        SolicitudVO solicitudVO = new SolicitudVO();
        TelefonoVO telefonoCelularVO = new TelefonoVO();
        TelefonoVO telefonoContactoVO = new TelefonoVO();
        TelefonoVO telefonoComercialVO = new TelefonoVO();
        AnalistaVO analistaCaptadorVO = new AnalistaVO();
        AgrupacionVO agrupacionVO = new AgrupacionVO();
        //declaracion de variable de sesión.
        HttpSession session = request.getSession();

        /*Encabezado*/
        analistaVO.setOficina(Integer.parseInt(request.getParameter("dbx_Oficina")));
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

        fecha = request.getParameter("txt_FecNac");

        try{
            date = sdf1.parse(fecha);

        }catch (ParseException e) {
            date = new Date();
            e.printStackTrace();
        }

        personaVO.setFechaNacimientoDate(date);
        personaVO.setFechaNacimiento(fecha);

        personaVO.setTipoSexo(Integer.parseInt(request.getParameter("dbx_Sexo")));
        personaVO.setTipoNacionalidad(Integer.parseInt(request.getParameter("dbx_Nacionalidad")));

        //datos que seran insertados en la tabla TelefonoVO
        telefonoCelularVO.setCodArea(request.getParameter("txt_codAreaCelular"));
        telefonoCelularVO.setNroTelefono(request.getParameter("txt_TelCelular"));

        //Se comentan porque estos campos ahora son obligatorios.
        /*if(request.getParameter("txt_codAreaCelular").equals("") || request.getParameter("txt_codAreaCelular").trim().equals(null)){
			telefonoCelularVO.setCodArea("0");
		}else{
			telefonoCelularVO.setCodArea(request.getParameter("txt_codAreaCelular"));
		}

		if(request.getParameter("txt_TelCelular").equals("") || request.getParameter("txt_TelCelular").trim().equals(null)){
			telefonoCelularVO.setNroTelefono("0");
		}else{
			telefonoCelularVO.setNroTelefono(request.getParameter("txt_TelCelular"));
		}*/

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

        //datos que van en la tabla DireccionVO
        direccionParticularVO.setGlosCalle(request.getParameter("txt_Calle"));
        direccionParticularVO.setNumDireccion(request.getParameter("txt_Numero"));
        direccionParticularVO.setPoblacionVilla(request.getParameter("txt_PoblVilla"));
        direccionParticularVO.setDpto(request.getParameter("txt_Departamento"));

        direccionParticularVO.setRegion(Integer.parseInt((String)request.getParameter("dbx_Region")));
        direccionParticularVO.setCiudad(Integer.parseInt((String)request.getParameter("dbx_Provincia")));
        direccionParticularVO.setComuna(Integer.parseInt((String)request.getParameter("dbx_Comuna")));

        //Datos que van a la tabla AfiliadoVO.
        afiliadoVO.setTipoNivelEduc(Integer.parseInt(request.getParameter("dbx_NivEstudios")));
        afiliadoVO.setTipoProfesion(Integer.parseInt(request.getParameter("dbx_TitAcademico")));
        afiliadoVO.setTipoEstado(Integer.parseInt(request.getParameter("dbx_EstCivil")));

        afiliadoVO.setTipoAfp(Integer.parseInt(request.getParameter("dbx_RegPrevisional")));
        afiliadoVO.setTipoRegSalud(Integer.parseInt(request.getParameter("dbx_RegSalud")));

        if(request.getParameter("dbx_RazonSocialAgrup")!=null){
            String idAgrupacion="0";
            idAgrupacion+=(String)request.getParameter("dbx_RazonSocialAgrup");
            afiliadoVO.setIdSecuenciaAgrupacion(Long.parseLong(idAgrupacion));
        }

        //Datos que van en la tabla GrupoFamiliarVO ya no son obligatorios
        //grupoFamiliarVO.setConyugue(Integer.parseInt(request.getParameter("rbt_Conyugue")));
        if(request.getParameter("rbt_Conyugue")== null){
            grupoFamiliarVO.setConyugue(-1);
        }else{
            grupoFamiliarVO.setConyugue(Integer.parseInt(request.getParameter("rbt_Conyugue")));
        }

        //grupoFamiliarVO.setCantHijos(Integer.parseInt(request.getParameter("txt_Hijos")));
        if(request.getParameter("txt_Hijos").equals("") || request.getParameter("txt_Hijos").trim().equals(null)){
            grupoFamiliarVO.setCantHijos(0);
        }else{
            grupoFamiliarVO.setCantHijos(Integer.parseInt(request.getParameter("txt_Hijos")));
        }

        /*Parte 2 - Informacion actividad comercial.*/
        //Datos que van a la tabla IngresoEconomicoVO
        ingresoEconomicoVO.setActEconom(request.getParameter("txt_Actividad"));
        ingresoEconomicoVO.setHonorario(Integer.parseInt(request.getParameter("rbt_Honorarios")));

        //datos que van a la tabla DireccionVO (Direccion Comercial)
        direccionComercialVO.setGlosCalle(request.getParameter("txt_CalleComerc"));
        direccionComercialVO.setNumDireccion(request.getParameter("txt_NumeroComerc"));
        direccionComercialVO.setPoblacionVilla(request.getParameter("txt_PoblVillaComerc"));
        direccionComercialVO.setDpto(request.getParameter("txt_DptoComerc"));
        direccionComercialVO.setRegion(Integer.parseInt((String)request.getParameter("dbx_RegComerc")));
        direccionComercialVO.setCiudad(Integer.parseInt((String)request.getParameter("dbx_ProvinciaComerc")));
        direccionComercialVO.setComuna(Integer.parseInt((String)request.getParameter("dbx_ComunaComerc")));

        //datos que van a la tabla EmailComercialVO (Direccion Comercial) -REQ5348
        if(request.getParameter("txt_EmailComercial")!=null) {
            emailComerVO.setDireccMail(request.getParameter("txt_EmailComercial"));
        }else{
            emailComerVO.setDireccMail("");
        }

        //datos que van a la tabla TelefonoVO

        if(request.getParameter("txt_codAreaComerc").equals("") || request.getParameter("txt_codAreaComerc").trim().equals(null)){
            telefonoComercialVO.setCodArea("0");
        }else{
            telefonoComercialVO.setCodArea(request.getParameter("txt_codAreaComerc"));
        }
        if(request.getParameter("txt_TelComerc").equals("") || request.getParameter("txt_TelComerc").trim().equals(null)){
            telefonoComercialVO.setNroTelefono("0");
        }else{
            telefonoComercialVO.setNroTelefono(request.getParameter("txt_TelComerc"));
        }

        /*parte 3 - Informacion de renta*/
        ingresoEconomicoVO.setRentaImponible(Long.parseLong(request.getParameter("txt_RentaImp")));

        if(request.getParameter("txt_RentaCot").equals("") || request.getParameter("txt_RentaCot").trim().equals(null)){
            ingresoEconomicoVO.setRentaCotizada(Long.parseLong("0"));
        }else{
            ingresoEconomicoVO.setRentaCotizada(Long.parseLong(request.getParameter("txt_RentaCot")));
        }
        //REQ5348
        if(request.getParameter("txt_MontoUltimaCotizacion").equals("") || request.getParameter("txt_MontoUltimaCotizacion").trim().equals(null)){
            ingresoEconomicoVO.setMontoUltimaCotizacion(Long.parseLong("0"));
        }else{
            ingresoEconomicoVO.setMontoUltimaCotizacion(Long.parseLong(request.getParameter("txt_MontoUltimaCotizacion")));
        }
        ingresoEconomicoVO.setFecUltCotizacion(request.getParameter("txt_FechaUltimaCotizacion"));

        //FIN REQ5348		

        //Datos que van a la tabla AfiliadoVO
        afiliadoVO.setMontoCotizar(Integer.parseInt(request.getParameter("txt_ValorACot")));

        /*parte 4 - Informacion de afiliacion*/
        solicitudVO.setTipoCajaOrigen(Long.parseLong(request.getParameter("dbx_CajaCompensacion")));

        fecha = request.getParameter("txt_FecVigAfil");

        try{
            date = sdf1.parse(fecha);

        }catch (ParseException e) {
            date = new Date();
            e.printStackTrace();
        }
        horaCaptacion=request.getParameter("txt_Hora");
        solicitudVO.setFechaVigenciaDate(date);
        solicitudVO.setFechaVigencia(fecha);

        solicitudVO.setHoraCaptacion(horaCaptacion);
        /* Parte 5 - Antecedentes proceso afiliacion*/
        //Datos que van a la tabla AnalistaVO
        analistaVO.setIdAnalista(Long.parseLong((String)session.getAttribute("IDAnalista")));
        analistaVO.setApellidoPaterno((String)session.getAttribute("ApePatAnalista"));
        analistaVO.setApellidoMaterno((String)session.getAttribute("ApeMatAnalista"));
        analistaVO.setNombres((String)session.getAttribute("NombreAnalista"));

        //Se agrega campo Fecha de Firma (2012-01-02), Se almacena en tabla Solicitud.
        fecha = request.getParameter("txt_FecFirma");

        if(fecha.trim().equals("")){
            fecha = "01/01/1900";
        }

        try{
            date = sdf1.parse(fecha);

        }catch(ParseException e) {
            date = new Date();
            e.printStackTrace();
        }
        solicitudVO.setFechaFirmaDate(date);
        solicitudVO.setFechaFirma(fecha);		

        /* Se agregan datos pertenecientes al analista comercial o Captador*/
        //datos van a la tabla Analista.		
        analistaCaptadorVO.setIdAnalista(Long.parseLong((String)request.getParameter("txt_RutEjec")));
        analistaCaptadorVO.setApellidoPaterno((String)request.getParameter("txt_ApePatEjec"));
        analistaCaptadorVO.setApellidoMaterno((String)request.getParameter("txt_ApeMatEjec"));
        analistaCaptadorVO.setNombres((String)request.getParameter("txt_NombreEjec"));
        analistaCaptadorVO.setOficina(Integer.parseInt(request.getParameter("dbx_Oficina")));
        //REQ5348
        /* Se agregan datos pertenecientes a la agrupacion*/
        //datos van a la tabla Afiliado_Agrupacion.	
//      TODO SToro #parche se comenta todo este bloque por innecesario
        /*String idAgrupacion="0";
		idAgrupacion+=(String)request.getParameter("dbx_RazonSocialAgrup");
		agrupacionVO.setIdSecuencia(Long.parseLong(idAgrupacion));
		agrupacionVO.setRazonSocial((String)request.getParameter("dbx_RazonSocialAgrup"));*/
        //FIN REQ5348
        /*insertar los objetos a solNegocio*/
        solNegocio.setSolicitudVO(solicitudVO);
        solNegocio.setPersonaVO(personaVO);
        solNegocio.setTelefonoCeluVO(telefonoCelularVO);
        solNegocio.setTelefonoPartVO(telefonoContactoVO);
        solNegocio.setEmailVO(emailVO);
        solNegocio.setEmailComerVO(emailComerVO);
        solNegocio.setDireccionPartVO(direccionParticularVO);
        solNegocio.setDireccionComerVO(direccionComercialVO);
        solNegocio.setTelefonoComerVO(telefonoComercialVO);
        solNegocio.setGrupoFamiliarVO(grupoFamiliarVO);
        solNegocio.setIngresoEconomicoVO(ingresoEconomicoVO);
        solNegocio.setAfiliadoVO(afiliadoVO);
        solNegocio.setAnalistaVO(analistaVO);
        solNegocio.setAnalistaCaptadorVO(analistaCaptadorVO);
        solNegocio.setAgrupacionVO(agrupacionVO);


        return NewSolicitudDAO.insertarNuevaSolicitud(solNegocio);
    }

    /*Función que obtiene el analista Captador.
     * Recibe como entrada el rut del analista del que se desea consultar.
     * Retorna un objeto de tipo AnalistaVO con la información requerida.*/
    public static AnalistaVO obtenerCaptador(String rutCompleto){

        AnalistaVO analista = new AnalistaVO();

        User idCapt = null;
        analista.setIdAnalista(0);

        try{

            UserRegistryConnection conexion = new UserRegistryConnection();
            idCapt = conexion.getUser(rutCompleto);

        }catch(UserRegistryException e){

            return NewSolicitudDAO.obtenerPromotor(rutCompleto.substring(0, rutCompleto.length()-2));
        }

        analista.setIdAnalista(Long.parseLong(rutCompleto.substring(0, rutCompleto.length()-2)));
        analista.setApellidoPaterno(idCapt.getFirstName());
        analista.setApellidoMaterno(idCapt.getLastName());
        analista.setNombres(idCapt.getName());

        return analista;
    }

    /* Función que obtiene la informacion de un determinado afiliado.
     * Recibe como entrada el rut de la persona, de la que se desea obtenerla información.
     * Retorna en dos casos:
     * 	.- Primero valida que el rut ingresado no se esté usando, esto es si el estado de la solicitud
     * 		asociada a dicho rut se encuentra en cancelada, rechazada, anulada, etc, quiere decir que el rut es usable 
     * 		y por tanto es posible acceder a la informacion. Por tanto si el resultado de la función validarAfiliadoReut(rut) 
     *      es distinto de cero retorna un resp con el error.
     *  .- Si el resultado es igual a cero, llama a la función obtenerAfiliado(rut) y obtiene los datos requeridos.
     */
    public static SolicitudNegocioVO obtenerAfiliado(String rut){

        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        BaseComunIntercajaVO base = new BaseComunIntercajaVO();
        SolicitudNegocioVO resp = new SolicitudNegocioVO();

        /*Se añade validación de rut de afiliado, consultando si existe o no en la tabla BaseComunIntercaja.*/

        base = NewSolicitudDAO.verificaAfiliadoIntercaja(rut);

        int verificaBaseComun = base.getCodResultado();

        /* Si la verificacion del rut es 0, es decir no encontró el rut, sigue el proceso habitual.
         * de lo contrario debe verificar periodo vigencia minimo, si cumple dicho periodo puede
         * realizar una nueva solicitud, sino el sistema lo rechaza.
         */
        System.out.println("VERIFICA BASE COMUN----------------->"+verificaBaseComun);
        if(verificaBaseComun == 0){

            resp = NewSolicitudDAO.validarAfiliadoReut(rut);
            System.out.println("RESP ----------------->"+resp.getCodResultado());
            if(resp.getCodResultado() != 0)
            {
                return resp;
            }
            else{
                resp = ModAfiliadoDAO.obtenerAfiliado(rut);

                return resp;
            }	
        }
        else{

            if(verificaBaseComun == 1){
                //Aqui verificar antiguedad del afiliado.
                int mesSistema;
                int anioSistema;
                int counterMonth=0;
                int difYear = 0;
                int difMonth = 0;
                int cantMesxAnio = 12;
                int codigo = 1;
                int periodoMinimo = 0;
                String fechaSistema = "";
                String fechaIngreso = "";
                Date fecha = new Date();
                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();

                /*Consulta dada parametrizacion del periodo minimo de vigencia >= 5 meses.*/
                ListadoParametros Listaparam = ListadoParametros.getInstancia();
                Parametro[] param = Listaparam.getListVigenciaMinima();

                for(int i=0; i<param.length; i++){

                    if(param[i].getCodigo() == codigo){
                        periodoMinimo = Integer.parseInt(param[i].getGlosa());
                    }
                }

                fechaIngreso = base.getFechaIngreso();

                int dias = Integer.parseInt(fechaIngreso.substring(3,5));
                int mesIngreso = Integer.parseInt(fechaIngreso.substring(0,2));
                int anioIngreso = Integer.parseInt(fechaIngreso.substring(6,10));

                cal1.set(cal1.get(Calendar.YEAR),cal1.get(Calendar.MONTH), cal1.get(Calendar.DAY_OF_MONTH));
                cal2.set(anioIngreso, mesIngreso, dias);

                fecha = cal1.getTime();
                fechaSistema = sdf2.format(fecha);

                mesSistema = Integer.parseInt(fechaSistema.substring(3,5));
                anioSistema = Integer.parseInt(fechaSistema.substring(6,10));

                difYear = anioSistema - anioIngreso;
                difMonth = mesSistema - mesIngreso;

                if(difYear >= 1){

                    counterMonth = difYear * cantMesxAnio;

                }

                counterMonth = counterMonth + difMonth;


                if(counterMonth >= periodoMinimo){

                    resp = NewSolicitudDAO.validarAfiliadoReut(rut);

                    if(resp.getCodResultado() != 0)
                    {
                        return resp;
                    }
                    else{
                        resp = ModAfiliadoDAO.obtenerAfiliado(rut);

                        return resp;
                    }

                }
                else{
                    resp.setCodResultado(2);
                    resp.setResultado("No es posible generar una solicitud.\n El rut ingresado se encuentra afiliado en otra CCAF y no cumple la vigencia mínima.");
                    return resp;
                }

            }else{
                resp.setCodResultado(2);
                resp.setResultado("No es posible generar una solicitud.\n El rut ingresado se encuentra afiliado en otra CCAF y no cumple la vigencia mínima.");
                return resp;
            }
        }

    }
}