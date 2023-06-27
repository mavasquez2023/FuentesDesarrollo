package cl.araucana.independientes.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.independientes.config.ConfiguracionSqlMap;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.vo.AfiliadoVO;
import cl.araucana.independientes.vo.AnalistaVO;
import cl.araucana.independientes.vo.BaseComunIntercajaVO;
import cl.araucana.independientes.vo.DireccionVO;
import cl.araucana.independientes.vo.EmailVO;
import cl.araucana.independientes.vo.EntidadRelacionVO;
import cl.araucana.independientes.vo.GrupoFamiliarVO;
import cl.araucana.independientes.vo.IngresoEconomicoVO;
import cl.araucana.independientes.vo.PersonaVO;
import cl.araucana.independientes.vo.RespuestaVO;
import cl.araucana.independientes.vo.SolicitudNegocioVO;
import cl.araucana.independientes.vo.SolicitudVO;
import cl.araucana.independientes.vo.TelefonoVO;

/*Implementación de la clase NewSolicitudDAO*/
public class NewSolicitudDAO {

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
        long idSolicitud = 0;
        long idPersona = 0;
        long idTelefono = 0;
        long idEmail = 0;
        long idDireccion = 0;
        long idGrupoFam = 0;
        long idIngEconom = 0;
        long idAfiliado = 0;
        //long idAnalista = 0;

        SolicitudVO solicitud = new SolicitudVO();
        PersonaVO persona = new PersonaVO();
        TelefonoVO telefonoCel = new TelefonoVO();
        TelefonoVO telefonoContacto = new TelefonoVO();
        TelefonoVO telefonoComercial = new TelefonoVO();
        EmailVO email = new EmailVO();
        EmailVO emailComer = new EmailVO();
        DireccionVO direccionParticular = new DireccionVO();
        DireccionVO direccionComercial = new DireccionVO();
        GrupoFamiliarVO grupoFamiliar = new  GrupoFamiliarVO();
        IngresoEconomicoVO ingresoEconomico = new IngresoEconomicoVO();
        AfiliadoVO afiliado = new AfiliadoVO();
        AnalistaVO analista = new AnalistaVO();
        EntidadRelacionVO entidadRelacion = new EntidadRelacionVO();
        AnalistaVO analistaCaptador = new AnalistaVO();

        //Obtencion de datos desde SolicitudNegocio
        solicitud = solNegocio.getSolicitudVO();
        persona = solNegocio.getPersonaVO();
        telefonoCel = solNegocio.getTelefonoCeluVO();
        telefonoContacto = solNegocio.getTelefonoPartVO();
        telefonoComercial = solNegocio.getTelefonoComerVO();
        email = solNegocio.getEmailVO();
        emailComer = solNegocio.getEmailComerVO();	
        direccionParticular = solNegocio.getDireccionPartVO();
        direccionComercial = solNegocio.getDireccionComerVO();
        grupoFamiliar = solNegocio.getGrupoFamiliarVO();
        ingresoEconomico = solNegocio.getIngresoEconomicoVO();
        afiliado = solNegocio.getAfiliadoVO();
        analista = solNegocio.getAnalistaVO();
        analistaCaptador = solNegocio.getAnalistaCaptadorVO();

        try {

            sqlMap.startTransaction(0);

            //Contador para folio
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", Long.toString(solicitud.getFolio()));
            datos = sqlMap.queryForList("newsolicitudNS.SelectCountFolio", parametros);

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
            datos = sqlMap.queryForList("newsolicitudNS.SelectCountRutValida", parametros);

            if(datos != null && datos.size() > 0){
                int respuestaRut = Integer.parseInt((String)datos.get(0));

                //fin contador Rut.

                //if (persona.getIdPersona() == 0){
                if(respuestaRut == 0){	

                    //traer id persona
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdPersona", parametros);

                    if(datos != null && datos.size() > 0){

                        //seteo de id`s
                        idPersona = Long.parseLong((String)datos.get(0));
                        persona.setIdPersona(idPersona);
                        persona.setTipoPersona(1);
                        persona.setTipoDocumento(1);
                    }else{

                        resp.setMsgRespuesta("Error al traer idPersona");
                        resp.setCodRespuesta(99);
                        return resp;
                    }

                    /*Insertar a la tabla Solicitud*/
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdSolicitud", parametros);

                    if(datos != null && datos.size() > 0){

                        idSolicitud = Long.parseLong((String)datos.get(0));
                        //seteo de id`s
                        solicitud.setIdSolicitud(idSolicitud);
                        solicitud.setIdAfiliadoAgrupacion(idPersona);
                        solicitud.setIdAnalista(analista.getIdAnalista());
                        solicitud.setTipoEstadoSolicitud(1);
                        solicitud.setTipoSolicitud(1);
                        solicitud.setIdCaptador(analistaCaptador.getIdAnalista());

                    }else{

                        resp.setMsgRespuesta("Error al traer idSolicitud");
                        resp.setCodRespuesta(99);
                        return resp;
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", solicitud);
                    sqlMap.insert("newsolicitudNS.insertNewSolSolicitud", parametros);

                    /*Insertar a la tabla Persona*/
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", persona);
                    sqlMap.insert("newsolicitudNS.insertNewSolPersona", parametros);

                    /*Se insertan los datos en la tabla Telefono Particular*/
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

                    }else{

                        resp.setMsgRespuesta("Error al traer idTelefono");
                        resp.setCodRespuesta(99);
                        return resp;
                    }

                    //insert de telefonoentidad y telefono celular
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", entidadRelacion);
                    sqlMap.insert("newsolicitudNS.insertNewSolIdTelefEnt", parametros);

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", telefonoCel);
                    sqlMap.insert("newsolicitudNS.insertNewSolTelefono", parametros);//para telefono celular

                    //obtener id de secuencia para nuevo telefono
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdTelefono", parametros);

                    if(datos != null && datos.size() > 0){

                        //seteo de id`s
                        idTelefono = Long.parseLong((String)datos.get(0));
                        entidadRelacion.setIdSecuencia(idTelefono);
                        entidadRelacion.setIdEntidad(idPersona);
                        telefonoContacto.setIdSecuenciaTelefono(idTelefono);
                        telefonoContacto.setTipoLocalidad(2);

                    }else{

                        resp.setMsgRespuesta("Error al traer idTelefono");
                        resp.setCodRespuesta(99);
                        return resp;
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", entidadRelacion);
                    sqlMap.insert("newsolicitudNS.insertNewSolIdTelefEnt", parametros);

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", telefonoContacto);
                    sqlMap.insert("newsolicitudNS.insertNewSolTelefono", parametros);//para telefono de fijo

                    //obtener id de secuencia para insertar telefono comercial
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdTelefono", parametros);

                    if(datos != null && datos.size() > 0){

                        //seteo de id`s
                        idTelefono = Long.parseLong((String)datos.get(0));
                        entidadRelacion.setIdSecuencia(idTelefono);
                        entidadRelacion.setIdEntidad(idPersona);
                        telefonoComercial.setIdSecuenciaTelefono(idTelefono);
                        telefonoComercial.setTipoLocalidad(3);
                    }else{

                        resp.setMsgRespuesta("Error al traer idTelefono");
                        resp.setCodRespuesta(99);
                        return resp;
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", entidadRelacion);
                    sqlMap.insert("newsolicitudNS.insertNewSolIdTelefEnt", parametros);

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", telefonoComercial);
                    sqlMap.insert("newsolicitudNS.insertNewSolTelefono", parametros);//insert para telefono comercial

                    /*Insertar datos a la tabla Email*/
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdEmail", parametros);

                    if(datos != null && datos.size() >0 ){
                        //seteo de id`s
                        idEmail = Long.parseLong((String)datos.get(0));
                        email.setIdSecuenciaEmail(idEmail);
                        email.setTipoLocalidad(1);
                        entidadRelacion.setIdSecuencia(idEmail);
                        entidadRelacion.setIdEntidad(idPersona);

                    }else{

                        resp.setMsgRespuesta("Error al traer idEmail");
                        resp.setCodRespuesta(99);
                        return resp;
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", entidadRelacion);
                    sqlMap.insert("newsolicitudNS.insertNewSolIdEmailEnt", parametros);

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", email);
                    sqlMap.insert("newsolicitudNS.insertNewSolEmail", parametros);

                    /*REQ5348 Insertar datos a la tabla Email Comercial*/	
                    if(!"".equalsIgnoreCase(emailComer.getDireccMail().trim())){
                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        datos = sqlMap.queryForList("newsolicitudNS.selectIdEmail", parametros);

                        if(datos != null && datos.size() >0 ){
                            //seteo de id`s
                            idEmail = Long.parseLong((String)datos.get(0));
                            emailComer.setIdSecuenciaEmail(idEmail);
                            emailComer.setTipoLocalidad(2);//IDENTIFICADOR PARA CORREO COMERCIAL
                            entidadRelacion.setIdSecuencia(idEmail);
                            entidadRelacion.setIdEntidad(idPersona);

                        }else{

                            resp.setMsgRespuesta("Error al traer idEmailComercial");
                            resp.setCodRespuesta(99);
                            return resp;
                        }
                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", entidadRelacion);
                        sqlMap.insert("newsolicitudNS.insertNewSolIdEmailEnt", parametros);

                        parametros = new HashMap();
                        parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                        parametros.put("input", emailComer);
                        sqlMap.insert("newsolicitudNS.insertNewSolEmail", parametros);					
                    }
                    /*Insertar a la tabla de direccion particular*/

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdDireccion", parametros);

                    if(datos != null && datos.size() >0 ){

                        //seteo de id`s
                        idDireccion = Long.parseLong((String)datos.get(0));
                        direccionParticular.setIdSecuenciaDireccion(idDireccion);
                        direccionParticular.setTipoLocalidad(1);
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
                    sqlMap.insert("newsolicitudNS.insertNewSolIdDireccEnt", parametros);

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", direccionParticular);
                    sqlMap.insert("newsolicitudNS.insertNewSolDireccion",parametros);//para direccion particular

                    //insertar para direccion comercial, se ibtiene un nuevo id de direccion.
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdDireccion", parametros);

                    if(datos != null && datos.size() > 0){

                        //seteo de id`s
                        idDireccion = Long.parseLong((String)datos.get(0));
                        entidadRelacion.setIdSecuencia(idDireccion);
                        entidadRelacion.setIdEntidad(idPersona);
                        direccionComercial.setIdSecuenciaDireccion(idDireccion);
                        direccionComercial.setTipoLocalidad(2);
                    }else{

                        resp.setMsgRespuesta("Error al traer idDireccion");
                        resp.setCodRespuesta(99);
                        return resp;
                    }
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", entidadRelacion);
                    sqlMap.insert("newsolicitudNS.insertNewSolIdDireccEnt", parametros);

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", direccionComercial);
                    sqlMap.insert("newsolicitudNS.insertNewSolDireccion", parametros);//insert para direccion comercial

                    /*Insertar a la tabla GrupoFamiliar*/
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdGrupoFamiliar", parametros);

                    if(datos != null && datos.size() >0 ){

                        //seteo de id`s
                        idGrupoFam = Long.parseLong((String)datos.get(0));
                        grupoFamiliar.setIdGrupoFam(idGrupoFam);
                        grupoFamiliar.setIdPersonaAfiliado(idPersona);
                    }else{

                        resp.setMsgRespuesta("Error al traer idGrupoFamiliar");
                        resp.setCodRespuesta(99);
                        return resp;
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", grupoFamiliar);
                    sqlMap.insert("newsolicitudNS.insertNewSolGrupoFamiliar",parametros);

                    /*Insertar a la tabla IngresoEconomico*/
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdIngresoEconomico", parametros);

                    if(datos != null && datos.size() >0 ){

                        //seteo de id`s
                        idIngEconom = Long.parseLong((String)datos.get(0));
                        ingresoEconomico.setIdPersonaAfiliado(idPersona);
                        ingresoEconomico.setIdIngEconom(idIngEconom);
                        //ingresoEconomico.setHonorario(1); 28/O6/2012 - DAGG - SE RESUELVE PROBLEMA QUE REGISTRABA LOS HONORARIOS SIEMPRE EN TRUE 
                    }else{

                        resp.setMsgRespuesta("Error al traer idIngresoEconomico");
                        resp.setCodRespuesta(99);
                        return resp;
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", ingresoEconomico);
                    sqlMap.insert("newsolicitudNS.insertNewSolIngresoEconomico", parametros);

                    //seteo de id`s
                    afiliado.setIdPersonaAfiliado(idPersona);
                    afiliado.setTipoEstadoAfiliado(1);
                    afiliado.setIdSolicitud(idSolicitud);

                    /*Ingresar a la tabla Afiliado*/
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", afiliado);
                    sqlMap.insert("newsolicitudNS.insertNewSolAfiliado", parametros);

                }else{
                    //Si el rut de la persona no esta en estados vigentes, entonces se updatean los datos  
                    //se rescata el idPersona
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", rut);
                    datos = sqlMap.queryForList("newsolicitudNS.selectidPersonaDoc", parametros);

                    if(datos != null && datos.size() > 0){

                        //se almacena idpersona
                        idPersona = Long.parseLong((String)datos.get(0));
                    }

                    fecha = persona.getFechaNacimiento();//datos[14-1];
                    date = sdf1.parse(fecha);

                    persona.setIdPersona(idPersona);
                    persona.setFechaNacimientoDate(date);
                    persona.setApellidoPaterno(persona.getApellidoPaterno());
                    persona.setApellidoMaterno(persona.getApellidoMaterno());
                    persona.setIdDocumento(persona.getIdDocumento());
                    persona.setDigVerificador(persona.getDigVerificador());
                    persona.setTipoNacionalidad(persona.getTipoNacionalidad());
                    persona.setNombres(persona.getNombres());
                    persona.setTipoSexo(persona.getTipoSexo());

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", persona);
                    sqlMap.update("modsolicitudNS.updateSolicitudPersona", parametros);

                    //Se rescata idsecuenciadireccion 
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", rut);
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaDireccion", parametros);

                    if(datos != null && datos.size() > 0){

                        idDireccion = Long.parseLong((String)datos.get(0));

                    }

                    /*Insertar a la tabla Solicitud*/
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdSolicitud", parametros);

                    if(datos != null && datos.size() > 0){

                        idSolicitud = Long.parseLong((String)datos.get(0));
                        solicitud.setIdSolicitud(idSolicitud);
                        solicitud.setIdAfiliadoAgrupacion(idPersona);
                        solicitud.setIdAnalista(analista.getIdAnalista());
                        solicitud.setTipoEstadoSolicitud(1);
                        solicitud.setTipoSolicitud(1);
                        solicitud.setIdCaptador(analistaCaptador.getIdAnalista());

                    }else{

                        resp.setMsgRespuesta("Error al traer idSolicitud");
                        resp.setCodRespuesta(99);
                        return resp;
                    }

                    //update a la tabla solicitud
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", solicitud);
                    sqlMap.insert("newsolicitudNS.insertNewSolSolicitud", parametros);
                    //REQ5348
                    //parametros = new HashMap();
                    //parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    //afiliadoAgrupacion.setIdPersonaAfiliado(Long.toString(idPersona));
                    //afiliadoAgrupacion.setIdSecuenciaAgrupacion(Long.toString(solNegocio.getAgrupacionVO().getIdSecuencia()));					
                    //parametros = new HashMap();
                    //parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    //parametros.put("input", afiliadoAgrupacion);
                    //sqlMap.insert("newsolicitudNS.insertNewSolAfiliadoAgrupacion", parametros);
                    //FIN REQ5348
                    //update a la tabla telefono, con los datos telefono celular.
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", rut);
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaTelCel", parametros);

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
                    sqlMap.update("modsolicitudNS.updateSolicitudTelefonoCelular", parametros);

                    //update a la tabla telefono, con los datos telefono particular.
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

                    //seteo de parametros para direccion particular
                    direccionParticular.setIdSecuenciaDireccion(idDireccion);
                    direccionParticular.setRegion(direccionParticular.getRegion());
                    direccionParticular.setCiudad(direccionParticular.getCiudad());
                    direccionParticular.setComuna(direccionParticular.getComuna());

                    //update a la tabla direccion con datos de direccion particular.
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", direccionParticular);
                    sqlMap.update("modsolicitudNS.updateSolicitudDireccionParticular", parametros);			

                    //update a la tabla email.
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", rut);
                    parametros.put("input_localidad", "1");
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaMail", parametros);

                    if(datos != null && datos.size() > 0){
                        idEmail = Long.parseLong((String)(datos.get(0)));
                        email.setIdSecuenciaEmail(idEmail);
                        email.setDireccMail(email.getDireccMail());
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", email);
                    sqlMap.update("modsolicitudNS.updateSolicitudEmail", parametros);

                    //update a la tabla email comercial.
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", rut);
                    parametros.put("input_localidad", "2");					
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaMail", parametros);					
                    if(datos != null && datos.size() > 0){
                        idEmail = Long.parseLong((String)(datos.get(0)));
                        email.setIdSecuenciaEmail(idEmail);
                        email.setDireccMail(email.getDireccMail());
                    }else{
///////////////////////////////

                        /*REQ5348 Insertar datos a la tabla Email Comercial*/	
                        if(!"".equalsIgnoreCase(emailComer.getDireccMail().trim())){
                            parametros = new HashMap();
                            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                            datos = sqlMap.queryForList("newsolicitudNS.selectIdEmail", parametros);

                            if(datos != null && datos.size() >0 ){
                                //seteo de id`s
                                idEmail = Long.parseLong((String)datos.get(0));
                                emailComer.setIdSecuenciaEmail(idEmail);
                                emailComer.setTipoLocalidad(2);//IDENTIFICADOR PARA CORREO COMERCIAL
                                entidadRelacion.setIdSecuencia(idEmail);
                                entidadRelacion.setIdEntidad(idPersona);

                            }else{

                                resp.setMsgRespuesta("Error al traer idEmailComercial");
                                resp.setCodRespuesta(99);
                                return resp;
                            }
                            parametros = new HashMap();
                            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                            parametros.put("input", entidadRelacion);
                            sqlMap.insert("newsolicitudNS.insertNewSolIdEmailEnt", parametros);

                            parametros = new HashMap();
                            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                            parametros.put("input", emailComer);
                            sqlMap.insert("newsolicitudNS.insertNewSolEmail", parametros);					
                        }						

///////////////////////////////						
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", email);
                    sqlMap.update("modsolicitudNS.updateSolicitudEmail", parametros);

                    //update a la tabla direccion, con los datos de direccion comercial.
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", rut);
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaDireccCom", parametros);

                    if(datos != null && datos.size() > 0){
                        idDireccion = Long.parseLong((String)(datos.get(0)));
                        direccionComercial.setIdSecuenciaDireccion(idDireccion);
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", direccionComercial);
                    sqlMap.update("modsolicitudNS.updateSolicitudDireccionComercial", parametros);

                    //update a la tabla telefono, con los datos de telefono comercial
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", rut);
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaTelCom", parametros);

                    if(datos != null && datos.size() > 0){
                        idTelefono = Long.parseLong((String)(datos.get(0)));
                        telefonoComercial.setIdSecuenciaTelefono(idTelefono);
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", telefonoComercial);
                    sqlMap.update("modsolicitudNS.updateSolicitudTelefonoComercial", parametros);

                    //update a la tabla grupofamiliar
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", rut);
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaGrupFam", parametros);

                    if(datos != null && datos.size() > 0){
                        idGrupoFam = Long.parseLong((String)(datos.get(0)));
                        grupoFamiliar.setIdGrupoFam(idGrupoFam);
                        grupoFamiliar.setCantHijos(grupoFamiliar.getCantHijos());
                        grupoFamiliar.setConyugue(grupoFamiliar.getConyugue());
                        grupoFamiliar.setIdPersonaAfiliado(afiliado.getIdPersonaAfiliado());
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", grupoFamiliar);
                    sqlMap.update("modsolicitudNS.updateSolicitudGrupoFamiliar", parametros);

                    //update a la tabla ingresoeconomico
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", rut);
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaIngEco", parametros);

                    if (datos != null && datos.size() > 0) {
                        idIngEconom = Long.parseLong((String)(datos.get(0)));
                        ingresoEconomico.setIdIngEconom(idIngEconom);
                        ingresoEconomico.setActEconom(ingresoEconomico.getActEconom());
                        ingresoEconomico.setHonorario(ingresoEconomico.getHonorario());
                        ingresoEconomico.setIdPersonaAfiliado(afiliado.getIdPersonaAfiliado());
                        ingresoEconomico.setRentaImponible(ingresoEconomico.getRentaImponible());
                        ingresoEconomico.setRentaCotizada(ingresoEconomico.getRentaCotizada());
                        ingresoEconomico.setMontoUltimaCotizacion(ingresoEconomico.getMontoUltimaCotizacion());
                        ingresoEconomico.setFecUltCotizacion(ingresoEconomico.getFecUltCotizacion());						
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", ingresoEconomico);
                    sqlMap.update("modsolicitudNS.updateSolicitudIngresoEconomico", parametros);

                    //update a la tabla afiliado
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", rut);
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaAfiliado", parametros);

                    if(datos != null && datos.size() > 0){
                        idAfiliado = Long.parseLong((String)(datos.get(0)));
                        afiliado.setIdPersonaAfiliado(idAfiliado);
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", afiliado);
                    sqlMap.update("modsolicitudNS.updateSolicitudAfiliado", parametros);

                    //update a la tabla analista
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", rut);
                    datos = sqlMap.queryForList("newsolicitudNS.selectIdSecuenciaAnalist", parametros);

                    if(datos != null && datos.size() > 0){
                        //idAnalista = Long.parseLong((String)(datos.get(0)));
                        analistaCaptador.setOficina(analista.getOficina());
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", analistaCaptador);
                    sqlMap.update("modsolicitudNS.updateOficinaAnalista", parametros);
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

            /*Insertar a la tabla Analista... Se inserta Analista Comercial*/
            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", Long.toString(analistaCaptador.getIdAnalista()));
            datos = sqlMap.queryForList("newsolicitudNS.selectIdAnalista", parametros);

            if(datos != null && datos.size() > 0){

                long countIdCaptador = Long.parseLong((String)datos.get(0));

                if(countIdCaptador == 0){

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", analistaCaptador);
                    sqlMap.insert("newsolicitudNS.insertNewSolAnalista", parametros);

                }else{
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", analistaCaptador);
                    sqlMap.update("newsolicitudNS.updateNewSolOficinaAnalista", parametros);
                }
            }else{
                resp.setMsgRespuesta("Error al consultar si Analista Comercial existe.");
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
        catch (ParseException e) {
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

    /*Función que valida a un usuario por medio del rut.
     * Recibe como entrada el rut a validar.
     * La función verifica que los datos no lleguen vacíos al DAO de lo contrario retorna error.
     * Si el rut es distinto de vacio, verifica que el rut se encuentre en la base de datos:
     * 		.- si el rut no esta, retorna un 0 (cero)
     * 		.- si el rut existe, verifica que no posea algun estado de solicitud vigente, si posee, envia un mensaje, sino envia un mensaje de error.
     * */
    public static SolicitudNegocioVO validarAfiliadoReut(String rut){

        /* Retornos 
		0 = Ok
		10 = Existen solciitudes activas para este RUT
		11 = Error en data. Existe mas de una solicitud activa para el RUT
		94 = Error al consultar por el rut
		97 = El rut llegaron al DAO sin valores
		99 = Error Desconocido de BD
         */

        // Variables generales
        List datos = null;
        int resultado = 0;

        SolicitudNegocioVO solicitudAfiliado = new SolicitudNegocioVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try{
            sqlMap.startTransaction(0);

            //verifica que el rut llegue con valores al DAO
            if(rut.equals("")){

                solicitudAfiliado.setResultado("El rut llego al DAO sin valores");
                solicitudAfiliado.setCodResultado(97);

                return solicitudAfiliado;
            }

            //si el rut es distinto de vacio, se consulta por el rut, y el resultado de dicha consulta se guarda en la variable resultado, si algo sale mal
            //envía un mensaje de error.
            if(!rut.equals("")){

                HashMap parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("newsolicitudNS.selectCountRUT", parametros);

                if(datos != null && datos.size() > 0 ){

                    resultado = Integer.parseInt((String)datos.get(0));
                }else{

                    solicitudAfiliado.setResultado("Ocurrió un error al consultar por el RUT");
                    solicitudAfiliado.setCodResultado(94);

                    return solicitudAfiliado;
                }

            }	

            switch(resultado){

            case 0:

                //Si resultado es cero, retorna ok.
                solicitudAfiliado.setResultado("");
                solicitudAfiliado.setCodResultado(0);

                break;

            case 1:

                //si resultado es distinto de cero, realiza una consulta para saber los posibles estados de solicitud asociados al rut (pueden haber varios folios)
                SolicitudVO sol = new SolicitudVO();

                HashMap parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("newsolicitudNS.selectCountRUTDescripcion", parametros);

                if(datos != null && datos.size() > 0 ){

                    sol = (SolicitudVO)datos.get(0);

                    solicitudAfiliado.setResultado("El RUT que consultó se encuentra asociado a Solicitud folio " + sol.getFolio() + " que esta en estado " + sol.getDesTipoEstadoSolicitud() + ".");
                    solicitudAfiliado.setCodResultado(10);

                    return solicitudAfiliado;

                }else{

                    solicitudAfiliado.setResultado("Ocurrió un error al consultar por el RUT.");
                    solicitudAfiliado.setCodResultado(94);

                    return solicitudAfiliado;
                }

            default:

                solicitudAfiliado.setResultado("Error en data. Existe mas de una solicitud activa para el RUT");
            solicitudAfiliado.setCodResultado(11);

            }
            return solicitudAfiliado;

        } catch(SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 

                e.printStackTrace(); }
        }	

        return solicitudAfiliado;
    }

    public static AnalistaVO obtenerPromotor(String rutCompleto)
    {
        AnalistaVO promotor = new AnalistaVO();

        HashMap parametros = new HashMap();
        List datos = null;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        promotor.setIdAnalista(0);

        try{

            sqlMap.startTransaction(0);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", rutCompleto);
            datos = sqlMap.queryForList("newsolicitudNS.obtenerPromotor", parametros);

            if(datos != null && datos.size() >0 ){

                promotor = (AnalistaVO)datos.get(0);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }

        return promotor;
    }

    /*Creado para verificar rut en base comun intercaja*/
    public static BaseComunIntercajaVO verificaAfiliadoIntercaja(String rutAfiliado){

        List datos = null;
        int resultado;

        BaseComunIntercajaVO baseComun = new BaseComunIntercajaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        HashMap parametros = new HashMap();

        try{
            sqlMap.startTransaction(0);
            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", rutAfiliado);

            datos = sqlMap.queryForList("intercajaNS.selectCountRutBaseComun", parametros);

            if(datos != null && datos.size() > 0){

                resultado = Integer.parseInt((String)datos.get(0));

                if(resultado == 0){

                    baseComun.setCodResultado(0);
                    return baseComun;

                }else{

                    datos = null;
                    datos = sqlMap.queryForList("intercajaNS.selectFecIngrPorRutBaseC", parametros);
                    if(datos != null && datos.size() > 0){

                        baseComun = (BaseComunIntercajaVO)datos.get(0);

                        System.out.println("");
                        System.out.println("fecha consultada = " + baseComun.getFechaIngreso());
                        System.out.println("");

                        baseComun.setCodResultado(1);
                        return baseComun;

                    }else{
                        baseComun.setCodResultado(99);
                        return baseComun;
                    }
                }

            }else{

                baseComun.setCodResultado(99);
                return baseComun;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }

        return baseComun;
    }	
}
