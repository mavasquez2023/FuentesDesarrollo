package cl.araucana.independientes.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.independientes.config.ConfiguracionSqlMap;
import cl.araucana.independientes.vo.AfiliadoVO;
import cl.araucana.independientes.vo.AnalistaVO;
import cl.araucana.independientes.vo.DireccionVO;
import cl.araucana.independientes.vo.DocumentoVO;
import cl.araucana.independientes.vo.EmailVO;
import cl.araucana.independientes.vo.EntidadRelacionVO;
import cl.araucana.independientes.vo.PersonaVO;
import cl.araucana.independientes.vo.RespuestaVO;
import cl.araucana.independientes.vo.SolicitudNegocioVO;
import cl.araucana.independientes.vo.SolicitudVO;
import cl.araucana.independientes.vo.TelefonoVO;
import cl.araucana.independientes.vo.param.Documento;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;


public class NewSolDesafiliacionDAO {

    /*Función que obtiene la informacion de la solicitud filtrando por rut.
     * Recibe como entrada el rut por el cual se desea buscar.
     * Retorna un objeto de tipo SolicitudNegocioVO con la informacion requerida.*/
    public static SolicitudNegocioVO obtenerDatosPorRut(String rut){

        // Variables generales
        List datos = null;
        long idPersona = 0;

        SolicitudNegocioVO solicitudPorRut = new SolicitudNegocioVO();
        TelefonoVO telefonoVO = new TelefonoVO();
        DireccionVO direccionVO = new DireccionVO();
        AfiliadoVO afiliadoVO = new AfiliadoVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try{
            sqlMap.startTransaction(0);

            //Objetos internos de SolicitudNegocioVO (solicitudPorRut)
            PersonaVO personaVO = new PersonaVO();
            SolicitudVO solicitudVO = new SolicitudVO();
            EmailVO emailVO = new EmailVO();

            ListadoParametros listaParam = ListadoParametros.getInstancia();

            //	consulta ESTADO AFILIADO (vigente o bloqueado) y Solicitud Afiliación (aprobada)
            //solicitudPorRut.setResultado("El Afiliado debe estar vigente o bloqueado para generar una solicitud de Desafiliación");


            //consulta por persona.
            solicitudPorRut.setResultado("No existe el afiliado");

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", rut);
            datos = sqlMap.queryForList("NewSolDesafiliacionNS.obtenerSolicitudRutPers", parametros);

            if(datos != null && datos.size() >0 ){

                personaVO = (PersonaVO)datos.get(0);
                idPersona = personaVO.getIdPersona();
                solicitudPorRut.setPersonaVO(personaVO);

            }else{
                return solicitudPorRut;
            }


            //	01 - Consulta por ESTADO Y PROCESODESAFILIACION Afiliado
            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", Long.toString(idPersona));
            datos = sqlMap.queryForList("newsolicitudNS.SelectCountIdSolicitudValida", parametros);//Cuento cuantas Solicitudes de desafiliacion tiene el idPersona

            int contadorSolDesaf =0;
            contadorSolDesaf = Integer.parseInt((String)datos.get(0));

            if(datos != null && contadorSolDesaf == 0){

                solicitudPorRut.setResultado("Ocurrió un error al consultar Afiliado por el Folio/RUT");
                solicitudPorRut.setCodResultado(96);
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", Long.toString(idPersona));
                datos = sqlMap.queryForList("NewSolDesafiliacionNS.obtenerEstadoAfiliado", parametros);

                if(datos != null && datos.size() >0 ){

                    afiliadoVO = (AfiliadoVO)datos.get(0);
                    solicitudPorRut.setAfiliadoVO(afiliadoVO);

                }else{
                    return solicitudPorRut;
                }

                if(afiliadoVO.getTipoEstadoAfiliado()!= 2 && afiliadoVO.getTipoEstadoAfiliado()!= 3){
                    solicitudPorRut.setResultado("La persona no existe como Afiliado en la C.C.A.F. La Araucana");
                    solicitudPorRut.setCodResultado(96);
                    return solicitudPorRut;
                }

                //Consulta por Solicitud
                solicitudPorRut.setResultado("Ocurrió un error al consultar Solicitud por el RUT");
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("NewSolDesafiliacionNS.obtenerSolicitudRutSol", parametros);

                if(datos != null && datos.size() > 0 ){

                    solicitudVO = (SolicitudVO)datos.get(0);
                    solicitudVO.setDesTipoEstadoSolicitud(Helper.obtenerDescripcion(listaParam.getListEstadoSolicitudAfiliacion(), solicitudVO.getTipoEstadoSolicitud()));
                    solicitudVO.setDesOficina(Helper.obtenerDescripcion(listaParam.getListOficina(), solicitudVO.getOficina()));
                    System.out.println("solicitudVO.FechaVigencia: "+ solicitudVO.getFechaVigencia());
                    solicitudPorRut.setSolicitudVO(solicitudVO);

                }else{
                    return solicitudPorRut;

                }

                // 06 - Consulta por Telefono Particular
                solicitudPorRut.setResultado("Ocurrió un error al consultar Telefono Particular por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("NewSolDesafiliacionNS.obtenerSolicitudFRTelefonoParticular", parametros);

                if(datos != null && datos.size() >0 ){

                    telefonoVO = (TelefonoVO)datos.get(0);
                    solicitudPorRut.setTelefonoPartVO(telefonoVO);

                }else{
                    return solicitudPorRut;
                }

                // 07 - Consulta por Celular Particular
                solicitudPorRut.setResultado("Ocurrió un error al consultar Celular Particular por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("NewSolDesafiliacionNS.obtenerSolicitudFRCelularParticular", parametros);

                if(datos != null && datos.size() >0 ){

                    telefonoVO = (TelefonoVO)datos.get(0);
                    solicitudPorRut.setTelefonoCeluVO(telefonoVO);

                }else{
                    return solicitudPorRut;
                }

                // 09 - Consulta por EMail
                solicitudPorRut.setResultado("Ocurrió un error al consultar Email por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                parametros.put("input_localidad", "1");				
                datos = sqlMap.queryForList("NewSolDesafiliacionNS.obtenerSolicitudFREmail", parametros);

                if(datos != null && datos.size() >0 ){

                    emailVO = (EmailVO)datos.get(0);
                    solicitudPorRut.setEmailVO(emailVO);

                }else if (datos==null){//En caso de que SQL ejecute incorrectamente
                    return solicitudPorRut;
                }else if(datos.size()==0){//En caso de que SQL no devuelva datos
                    emailVO.setDireccMail("");
                    emailVO.setIdSecuenciaEmail(0);
                    solicitudPorRut.setEmailVO(emailVO);
                }

                // 10 - Consulta por Direccion Particular
                solicitudPorRut.setResultado("Ocurrió un error al consultar Direccion Particular por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("NewSolDesafiliacionNS.obtenerSolicitudFRDireccionParticular", parametros);

                if(datos != null && datos.size() >0 ){
                    direccionVO = (DireccionVO)datos.get(0);
                    solicitudPorRut.setDireccionPartVO(direccionVO);

                }else{
                    return solicitudPorRut;
                }



            }else{

                solicitudPorRut.setResultado("Persona ya tiene una solicitud de desafiliación");
                solicitudPorRut.setCodResultado(99);
                return solicitudPorRut;
            }

        }


        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        solicitudPorRut.setResultado("");
        solicitudPorRut.setCodResultado(0);
        return solicitudPorRut;
    }

    public static Parametro[] obtenerParametroList(String tipo){

        List datos = null;
        Parametro[] salida = new Parametro[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los paramétricos.
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tipo);
            datos = sqlMap.queryForList("NewSolDesafiliacionNS.obtenerParametricosDes", parametros);

            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) {
                e.printStackTrace(); }

        }
        return salida;
    }


    public static String obtenerFechaSistema(){

        List datos = null;
        String salida = "";
        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "dd/MM/yy";
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //realiza una consulta para obtener la fecha de sistema desde la base de datos.
            datos = sqlMap.queryForList("parametrosNS.obtenerFechaSistema");//No requiere parametrizacion.

            fecha = (String)datos.get(0);
            date = sdf1.parse(fecha);
            salida = sdf2.format(date);

            //retorna la fecha parseada.
            return salida;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }


    /*Función que realiza un insert de una solicitud.
     * .- Recibe como entrada un objeto de tipo SolicitudNegocioVO que contiene la información traspasada desde 
     * 		newSolicitudImpl y se setean los objetos pertenecientes al objeto de solicitudNegocio.
     * .- Antes de hacer los insert valida si existe el folio y el rut de la persona. Si el folio ingresado
     * 		existe retorna un mensaje de error y no realiza el insert. Luego hace la validación por el rut de la persona,
     * 		si dicho rut esta asociado a un estado de solicitud vigente envia un mensaje de error.
     * .- Si el rut de la persona no existe o no esta registrado en la base de datos, entonces realiza el insert a las tablas correspondientes, 
     * 		de lo contrario, si el rut no esta con un estado de solicitud vigente, entonces hace un update a las tablas correspondientes.
     * .- Si todo resulta de forma exitosa, se retorna un mensaje ok.
     * */
    public static RespuestaVO insertarNuevaSolicitud(SolicitudNegocioVO solNegocio){

        /* Retornos 
			0 = Ok
			1 = Existe el Folio
			99 = Error Desconocido de BD
         */

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        //declaración de variables 
        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);

        RespuestaVO resp = new RespuestaVO();
        resp.setCodRespuesta(99);
        resp.setMsgRespuesta("Error indefinido");

        List datos = null;
        List idAnterior = null;
        long idSolicitud = 0;
        long idPersona = 0;
        long idTelefono = 0;
        long idEmail = 0;
        long idDireccion = 0;
        long idAfiliado = 0;
        //long idAnalista = 0;

        SolicitudVO solicitud = new SolicitudVO();
        PersonaVO persona = new PersonaVO();
        TelefonoVO telefonoCel = new TelefonoVO();
        TelefonoVO telefonoContacto = new TelefonoVO();
        EmailVO email = new EmailVO();
        DireccionVO direccionParticular = new DireccionVO();
        AfiliadoVO afiliado = new AfiliadoVO();
        AnalistaVO analista = new AnalistaVO();
        EntidadRelacionVO entidadRelacion = new EntidadRelacionVO();
        AnalistaVO analistaCaptador = new AnalistaVO();


        //Obtencion de datos desde SolicitudNegocio
        solicitud = solNegocio.getSolicitudVO();
        persona = solNegocio.getPersonaVO();
        telefonoCel = solNegocio.getTelefonoCeluVO();
        telefonoContacto = solNegocio.getTelefonoPartVO();
        email = solNegocio.getEmailVO();
        direccionParticular = solNegocio.getDireccionPartVO();
        afiliado = solNegocio.getAfiliadoVO();
        analista = solNegocio.getAnalistaVO();
        analistaCaptador = solNegocio.getAnalistaCaptadorVO();

        try {

            sqlMap.startTransaction(0);

            // Contador para folio
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", Long.toString(solicitud.getFolio()));
            datos = sqlMap.queryForList("NewSolDesafiliacionNS.SelectCountFolio", parametros);

            if(datos != null && datos.size() > 0){

                long countFolio = Long.parseLong((String)datos.get(0));

                if(countFolio != 0){

                    resp.setMsgRespuesta("Error, el Folio de solicitud ya existe.");
                    resp.setCodRespuesta(1);
                    return resp;
                }
            }else{
                resp.setMsgRespuesta("Error al consultar si Folio de solicitud existe.");
                resp.setCodRespuesta(99);
                return resp;
            }
            //fin folio


            //contador para RUT de una persona. Valida que el Rut no se duplique en la tabla persona.
            String rut = Long.toString(persona.getIdDocumento());

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", rut);
            datos = sqlMap.queryForList("newsolicitudNS.SelectCountRutValida", parametros);//verifico que el rut exista

            if(datos != null && datos.size() > 0){
                int respuestaRut = Integer.parseInt((String)datos.get(0));
                //fin contador Rut.

                if(respuestaRut == 1){	

                    //traer id persona

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", rut);
                    datos = sqlMap.queryForList("newsolicitudNS.selectidPersonaDoc", parametros);//traigo el ID de la persona segun el RUT


                    if(datos != null && datos.size() > 0){

                        //seteo de id`s
                        idPersona = Long.parseLong((String)datos.get(0));

                    }else{

                        resp.setMsgRespuesta("Error al traer idPersona");
                        resp.setCodRespuesta(99);
                        return resp;
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", (String)datos.get(0));
                    datos = sqlMap.queryForList("newsolicitudNS.SelectCountIdSolicitudValida", parametros);//Cuento cuantas Solicitudes de desafiliacion tiene el idPersona

                    int contadorSolDesaf =0;
                    contadorSolDesaf = Integer.parseInt((String)datos.get(0));

                    if(datos != null && contadorSolDesaf == 0){

                        /*Insertar a la tabla Solicitud*/
                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        datos = sqlMap.queryForList("newsolicitudNS.selectIdSolicitud", parametros);//traigo el ID de la solicitud

                        if(datos != null && datos.size() > 0){

                            idSolicitud = Long.parseLong((String)datos.get(0));
                            //seteo de id`s
                            solicitud.setIdSolicitud(idSolicitud);
                            solicitud.setIdAfiliadoAgrupacion(idPersona);
                            solicitud.setIdAnalista(analista.getIdAnalista());
                            solicitud.setTipoEstadoSolicitud(1);
                            solicitud.setTipoSolicitud(2);

                        }else{

                            resp.setMsgRespuesta("Error al traer idSolicitud");
                            resp.setCodRespuesta(99);
                            return resp;
                        }

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", solicitud);
                        sqlMap.insert("newsolicitudNS.insertNewSolSolicitudDesaf", parametros);//Inserta la nueva solicitud

                    }else{

                        resp.setMsgRespuesta("Persona ya tiene una solicitud de desafiliación");
                        resp.setCodRespuesta(99);
                        return resp;
                    }

                    // update a la tabla telefono, con los datos telefono particular.
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", rut);
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaTelPart", parametros);

                    if(datos != null && datos.size() > 0){
                        idTelefono = Long.parseLong((String)(datos.get(0)));
                        telefonoContacto.setIdSecuenciaTelefono(idTelefono);
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", telefonoContacto);
                    sqlMap.update("modsolicitudNS.updateSolicitudTelefonoParticular", parametros);

                    //--------------UPDATE  O INSERT A LA TABLA TELEFONO
                    if(telefonoCel.getFlagTelefono() == 1){
                        //	update a la tabla telefono, con los datos telefono celular.
                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", rut);
                        datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaTelCel", parametros);//OBTENGO EL IDSECUENCIA DEL TEL CELULAR

                        if(datos != null && datos.size() > 0){
                            idTelefono = Long.parseLong((String)(datos.get(0)));
                            telefonoCel.setIdSecuenciaTelefono(idTelefono);
                            telefonoCel.setCodArea(telefonoCel.getCodArea());
                            telefonoCel.setTipoLocalidad(1);
                            telefonoCel.setNroTelefono(telefonoCel.getNroTelefono());
                        }
                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", telefonoCel);
                        sqlMap.update("modsolicitudNS.updateSolicitudTelefonoCelular", parametros);//ACTUALIZO LOS DATOS DEL TELEFONO CELULAR
                    }
                    if(telefonoCel.getFlagTelefono() == 2){
                        // Insert a la tabla telefono

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", rut);
                        idAnterior = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaTelCel", parametros);//OBTENGO EL IDSECUENCIA DEL TEL CELULAR

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", idAnterior.get(0));
                        sqlMap.update("modsolicitudNS.updatePrincipalidadTelefonoCelular", parametros);//ACTUALIZO LA PRINCIPALIDAD = 0 DEL TELEFONO CELULAR

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        datos = sqlMap.queryForList("newsolicitudNS.selectIdTelefono", parametros);

                        if(datos != null && datos.size() >0 ){

                            //Seteo de id`s
                            idTelefono = Long.parseLong((String)datos.get(0));
                            telefonoCel.setIdSecuenciaTelefono(idTelefono);
                            entidadRelacion.setIdSecuencia(idTelefono);
                            entidadRelacion.setIdEntidad(idPersona);
                            telefonoCel.setTipoLocalidad(1);
                            telefonoCel.setTipoPrincipalidad(1);

                        }else{

                            resp.setMsgRespuesta("Error al traer idTelefono");
                            resp.setCodRespuesta(99);
                            return resp;
                        }
                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", telefonoCel);
                        sqlMap.insert("newsolicitudNS.insertNewSolTelefono", parametros);

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", entidadRelacion);
                        sqlMap.insert("newsolicitudNS.insertNewSolIdTelefEnt", parametros);

                    }

                    //	UPDATE A LA TABLA PERSONA
                    persona.setIdPersona(idPersona);
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", persona);
                    sqlMap.update("ModSolDesafiliaciondNS.updateSolicitudPersona", parametros);

                    // UPDATE  O INSERT A LA TABLA EMAIL
                    if(email.getFlagEmail() == 1){
                        // UPDATE EMAIL

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", rut);
                        parametros.put("input_localidad", "1");
                        datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaMail", parametros);//TRAIGO EL IDSECUENCIA DE LA TABLA EMAIL

                        if(datos != null && datos.size() > 0){
                            idEmail = Long.parseLong((String)(datos.get(0)));
                            email.setIdSecuenciaEmail(idEmail);
                            email.setDireccMail(email.getDireccMail());
                        }

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", email);
                        sqlMap.update("modsolicitudNS.updateSolicitudEmail", parametros);
                    }

                    if(email.getFlagEmail() == 2){
                        // INSERT EMAIL

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", rut);
                        parametros.put("input_localidad", "1");
                        idAnterior = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaMail", parametros);//TRAIGO EL IDSECUENCIA DE LA TABLA EMAIL

                        if(idAnterior != null && idAnterior.size() >0 ){
                            parametros = new HashMap();
                            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                            parametros.put("input", idAnterior.get(0));
                            sqlMap.update("modsolicitudNS.updatePrincipalidadEmail", parametros);//ACTUALIZO LA PRINCIPALIDAD = 0 DEL TELEFONO CELULAR
                        }	
                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        datos = sqlMap.queryForList("newsolicitudNS.selectIdEmail", parametros);//OBTENGO IDSECUENCIAMAIL

                        if(datos != null && datos.size() >0 ){
                            //seteo de id`s
                            idEmail = Long.parseLong((String)datos.get(0));
                            email.setIdSecuenciaEmail(idEmail);
                            email.setTipoLocalidad(1);
                            entidadRelacion.setIdSecuencia(idEmail);
                            entidadRelacion.setIdEntidad(idPersona);
                            email.setTipoPrincipalidad(1);

                        }else{

                            resp.setMsgRespuesta("Error al traer idEmail");
                            resp.setCodRespuesta(99);
                            return resp;
                        }

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", entidadRelacion);
                        sqlMap.insert("newsolicitudNS.insertNewSolIdEmailEnt", parametros);//INSERTO RELACION EN TABLA EMAILENTIDAD

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", email);
                        sqlMap.insert("newsolicitudNS.insertNewSolEmail", parametros);//INSERTO A LA TABLA EMAIL
                    }

                    // UPDATE  O INSERT A LA TABLA DIRECCION
                    if(direccionParticular.getFlagCalle() == 1){
                        //UPDATE DIRECCION
                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", rut);
                        datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaDireccion", parametros);//TRAIGO EL IDSECUENCIA DE LA TABLA DIRECCION

                        if(datos != null && datos.size() > 0){

                            // seteo de parametros para direccion particular
                            idDireccion = Long.parseLong((String)datos.get(0));
                            direccionParticular.setIdSecuenciaDireccion(idDireccion);
                        }

                        //	update a la tabla direccion con datos de direccion particular.
                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", direccionParticular);
                        sqlMap.update("modsolicitudNS.updateSolicitudDireccionParticular", parametros);	


                    }
                    if(direccionParticular.getFlagCalle() == 2){
                        //INSERT DIRECCION

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", rut);
                        idAnterior = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaDireccion", parametros);//TRAIGO EL IDSECUENCIA DE LA TABLA DIRECCION

                        if (idAnterior != null && idAnterior.size() > 0)
                        {
                            parametros = new HashMap();
                            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                            parametros.put("input", idAnterior.get(0));
                            sqlMap.update("modsolicitudNS.updatePrincipalidadDireccion", parametros);//ACTUALIZO LA PRINCIPALIDAD = 0 DE LA DIRECCION
                        }
                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        datos = sqlMap.queryForList("newsolicitudNS.selectIdDireccion", parametros);// OBTENGO SECUENCIA DIRECCION

                        if(datos != null && datos.size() >0 ){

                            //seteo de id`s
                            idDireccion = Long.parseLong((String)datos.get(0));
                            direccionParticular.setIdSecuenciaDireccion(idDireccion);
                            direccionParticular.setTipoLocalidad(1);
                            direccionParticular.setTipoPrincipalidad(1);
                            entidadRelacion.setIdSecuencia(idDireccion);
                            entidadRelacion.setIdEntidad(idPersona);

                        }else{

                            resp.setMsgRespuesta("Error al traer idDireccion");
                            resp.setCodRespuesta(99);
                            return resp;
                        }

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", entidadRelacion);
                        sqlMap.insert("newsolicitudNS.insertNewSolIdDireccEnt", parametros);//INSERTO A TABLA INTERMEDIADIRECCION

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", direccionParticular);
                        sqlMap.insert("newsolicitudNS.insertNewSolDireccion",parametros);//INSERTO A TABLA DIRECCION

                    }

                    /*Ingresar a la tabla Afiliado*/

                    afiliado.setIdPersonaAfiliado(idPersona);
                    afiliado.setIdSolicitud(idSolicitud);
                    afiliado.setProcesoDesafiliacion(1);

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", afiliado);
                    sqlMap.update("modsolicitudNS.updateAfiliadoDesafiliacion", parametros);

                    //INI	update a la tabla ANALISTA

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", analista);
                    sqlMap.update("modsolicitudNS.updateOficinaAnalista", parametros);

                    //FIN	update a la tabla ANALISTA


                }else{

                    resp.setMsgRespuesta("Error Rut no encontrado");
                    resp.setCodRespuesta(99);
                    return resp;
                }
            }  

            /*Insertar a la tabla Analista*/
            //Verifica que este el id del analista.
            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", Long.toString(analista.getIdAnalista()));
            datos = sqlMap.queryForList("newsolicitudNS.selectIdAnalista", parametros);

            if(datos != null && datos.size() > 0){

                long countIdAnalista = Long.parseLong((String)datos.get(0));

                //si no existe el idanalista, entonces se inserta en la tabla.
                if(countIdAnalista == 0){

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", analista);
                    sqlMap.insert("newsolicitudNS.insertNewSolAnalista", parametros);
                }
            }else{
                resp.setMsgRespuesta("Error al consultar si el analista existe.");
                resp.setCodRespuesta(99);
                return resp;
            }


            if(solNegocio.getCodResultado() == 0)
            {
                sqlMap.commitTransaction();
                resp.setMsgRespuesta("");
                resp.setCodRespuesta(0);
            }	

            return resp;

        } catch (SQLException e) {
            resp.setMsgRespuesta("Error al insertar una nueva Solicitud.");
            resp.setCodRespuesta(99);
            e.printStackTrace();
        }
        finally {
            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }
        }	
        return resp;
    }

    //INICIO REQ 7002
    public static String obtenerMontoTotalDeudaAporte(String rut){

        List datos = null;
        String salida = "";
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {			
            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", rut);			
            datos = sqlMap.queryForList("NewSolDesafiliacionNS.SelectSumaDeudaAporte", parametros);//No requiere parametrizacion.
            //retorna Cantidad Deuda Aporte
            salida = (String)datos.get(0);
            System.out.println("obtenerMontoTotalDeudaAporte: "+salida);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }
    //FIN REQ 7002


}
