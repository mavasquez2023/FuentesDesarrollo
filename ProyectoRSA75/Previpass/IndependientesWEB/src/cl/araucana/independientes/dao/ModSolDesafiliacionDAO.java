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
import cl.araucana.independientes.struts.Forms.ModAfiliadoForm;
import cl.araucana.independientes.struts.Forms.ModSolDesafiliacionForm;
import cl.araucana.independientes.vo.AfiliadoVO;
import cl.araucana.independientes.vo.AnalistaVO;
import cl.araucana.independientes.vo.DireccionVO;
import cl.araucana.independientes.vo.DocumentoVO;
import cl.araucana.independientes.vo.EmailVO;
import cl.araucana.independientes.vo.EntidadRelacionVO;
import cl.araucana.independientes.vo.GrupoFamiliarVO;
import cl.araucana.independientes.vo.IngresoEconomicoVO;
import cl.araucana.independientes.vo.PersonaVO;
import cl.araucana.independientes.vo.SolicitudNegocioVO;
import cl.araucana.independientes.vo.SolicitudVO;
import cl.araucana.independientes.vo.TelefonoVO;
import cl.araucana.independientes.vo.param.Documento;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;

public class ModSolDesafiliacionDAO {

    /*Función que obtiene la informacion de una solicitud.
     * Recibe como entrada el folio y el rut por el que se desea buscar
     * retorna un objeto de tipo SolicitudNegocioVO.*/
    public static SolicitudNegocioVO obtenerSolicitud(String folio, String rut){

        /* Retornos 
			0 = Ok
			1 = No existe el par Folio-RUT
			2 = No existe el Folio --> No se Usa
			3 = No existe el RUT --> No se usa
			94 = Error al consultar por el rut  --> No se usa
			95 = Error al consultar por el folio  --> No se usa
			96 = Error al consultar por el par folio/rut
			97 = Los datos de folio y rut llegaron al DAO sin valores
			98 = Error en data. Existen mas de una solicitud asociada al Folio/RUT
			99 = Error Desconocido de BD
         */

        // Variables generales
        List datos = null;
        int resultado = 0;

        String fecha = "";
        String observaciones = "";
        String comentarios = "";
        String horaCaptacion = "";
        Date date = new Date();

        String DATE_FORMAT1 = "MM/dd/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista
        String DATE_FORMAT3 = "dd/MM/yy"; // El que se envia a la Vista		
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);
        SimpleDateFormat sdf3 = new SimpleDateFormat(DATE_FORMAT3);		

        SolicitudNegocioVO solicitud = new SolicitudNegocioVO();
        //ModSolicitudForm form = new ModSolicitudForm();
        ModSolDesafiliacionForm form = new ModSolDesafiliacionForm();

        //Seteo de parametros de entrada
        form.setTxt_Folio(folio);
        form.setTxt_Rut(rut);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            if(folio.equals("") || rut.equals("")){

                solicitud.setResultado("Ocurrió un error al consultar por el Folio/RUT");
                solicitud.setCodResultado(97);

                return solicitud;
            }

            if(!folio.equals("") && !rut.equals("")){

                HashMap parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerContSolicitudFR", parametros);

                if(datos != null && datos.size() >0 ){

                    resultado = Integer.parseInt((String)datos.get(0));

                }else{
                    solicitud.setResultado("Ocurrió un error al consultar por el Folio/RUT");
                    solicitud.setCodResultado(96);

                    return solicitud;
                }

            }	

            switch(resultado){

            case 0:

                solicitud.setResultado("No existe una solicitud asociada al Folio/RUT");
                solicitud.setCodResultado(1);

                break;

            case 1:

                // Objetos internos de SolicitudNegocioVO (solicitud)
                AfiliadoVO afiliadoVO = new AfiliadoVO();
                AnalistaVO analistaVO = new AnalistaVO();
                DireccionVO direccionVO = new DireccionVO();
                EmailVO emailVO = new EmailVO();
                EmailVO emailComercialVO = new EmailVO();				
                GrupoFamiliarVO grupoFamiliarVO = new GrupoFamiliarVO();
                IngresoEconomicoVO ingresoEconomicoVO = new IngresoEconomicoVO();
                PersonaVO personaVO = new PersonaVO();
                SolicitudVO solicitudVO = new SolicitudVO();
                TelefonoVO telefonoVO = new TelefonoVO();

                ListadoParametros listaParam = ListadoParametros.getInstancia();

                //Consultas
                // 01 - Consulta por Afiliado
                solicitud.setResultado("Ocurrió un error al consultar Afiliado por el Folio/RUT");
                solicitud.setCodResultado(96);

                HashMap parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFRAfiliado", parametros);

                if(datos != null && datos.size() >0 ){

                    afiliadoVO = (AfiliadoVO)datos.get(0);
                    solicitud.setAfiliadoVO(afiliadoVO);

                }else{
                    return solicitud;
                }

                // 02 - Consulta por Persona
                solicitud.setResultado("Ocurrió un error al consultar Persona por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFRPersona", parametros);

                if(datos != null && datos.size() >0 ){

                    personaVO = (PersonaVO)datos.get(0);

                    fecha = personaVO.getFechaNacimiento();
                    date = sdf1.parse(fecha);

                    personaVO.setFechaNacimiento(sdf2.format(date));

                    solicitud.setPersonaVO(personaVO);

                }else{
                    return solicitud;
                }

                // 03 - Consulta por Solicitud
                solicitud.setResultado("Ocurrió un error al consultar Solicitud por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFRSolicitud", parametros);

                if(datos != null && datos.size() >0 ){

                    solicitudVO = (SolicitudVO)datos.get(0);

                    fecha = solicitudVO.getFechaVigencia();
                    date = sdf1.parse(fecha);
                    solicitudVO.setFechaVigencia(sdf2.format(date));

                    fecha = solicitudVO.getFechaIngreso();
                    date = sdf1.parse(fecha);
                    solicitudVO.setFechaIngreso(sdf2.format(date));

                    fecha = solicitudVO.getFechaUltAporte();
                    date = sdf1.parse(fecha);
                    solicitudVO.setFechaUltAporte(sdf2.format(date));

                    solicitudVO.setDesTipoEstadoSolicitud(Helper.obtenerDescripcion(listaParam.getListEstadoSolicitudDesafiliacion(), solicitudVO.getTipoEstadoSolicitud()));


                    fecha = solicitudVO.getFechaFirma();					
                    date = sdf1.parse(fecha);
                    solicitudVO.setFechaFirma(sdf2.format(date));

                    fecha = solicitudVO.getResolucionDirectorio();
                    date = sdf1.parse(fecha);
                    fecha = sdf2.format(date);

                    if(fecha.equals(IND_Constants.Fec_Comodin) || fecha.equals(IND_Constants.Fec_Comodin2))
                    {
                        solicitudVO.setResolucionDirectorio("");
                    }else{
                        solicitudVO.setResolucionDirectorio(fecha);
                    }

                    solicitudVO.setDesOficina(Helper.obtenerDescripcion(listaParam.getListOficina(), solicitudVO.getOficina()));

                    observaciones = solicitudVO.getObservaciones();
                    solicitudVO.setObservaciones(observaciones);

                    comentarios = solicitudVO.getComentarios();
                    solicitudVO.setComentarios(comentarios);

                    horaCaptacion = solicitudVO.getHoraCaptacion();
                    solicitudVO.setHoraCaptacion(horaCaptacion);

                    int motivo = 0;
                    int desMotivo = 0;

                    motivo = solicitudVO.getTipoMotivoDesafiliacion();
                    solicitudVO.setTipoMotivoDesafiliacion(motivo);

                    desMotivo = solicitudVO.getDescTipoMotivoDesafiliacion();
                    solicitudVO.setDescTipoMotivoDesafiliacion(desMotivo);

                    solicitud.setSolicitudVO(solicitudVO);

                }else{
                    return solicitud;
                }

                // 04 - Consulta por Grupo Familiar
                solicitud.setResultado("Ocurrió un error al consultar Grupo Familiar por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFRGrupoFamiliar", parametros);

                if(datos != null && datos.size() >0 ){

                    grupoFamiliarVO = (GrupoFamiliarVO)datos.get(0);
                    solicitud.setGrupoFamiliarVO(grupoFamiliarVO);


                }else{
                    return solicitud;
                }

                // 05 - Consulta por Ingreso Economico
                solicitud.setResultado("Ocurrió un error al consultar Ingreso Economico por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFRIngresoEconomico", parametros);

                if(datos != null && datos.size() >0 ){

                    ingresoEconomicoVO = (IngresoEconomicoVO)datos.get(0);
                    if(ingresoEconomicoVO.getFecUltCotizacion()!=null && !"".equals(ingresoEconomicoVO.getFecUltCotizacion().trim())){
                        Date fecUltCotizacion=sdf3.parse(ingresoEconomicoVO.getFecUltCotizacion());
                        ingresoEconomicoVO.setFecUltCotizacion(sdf2.format(fecUltCotizacion));
                    }else{
                        ingresoEconomicoVO.setFecUltCotizacion("");
                    }
                    solicitud.setIngresoEconomicoVO(ingresoEconomicoVO);

                }else{
                    return solicitud;
                }

                // 06 - Consulta por Telefono Particular
                solicitud.setResultado("Ocurrió un error al consultar Telefono Particular por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFRTelefonoParticular", parametros);

                if(datos != null && datos.size() >0 ){

                    telefonoVO = (TelefonoVO)datos.get(0);
                    solicitud.setTelefonoPartVO(telefonoVO);

                }else{
                    return solicitud;
                }

                // 07 - Consulta por Celular Particular
                solicitud.setResultado("Ocurrió un error al consultar Celular Particular por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFRCelularParticular", parametros);

                if(datos != null && datos.size() >0 ){

                    telefonoVO = (TelefonoVO)datos.get(0);
                    solicitud.setTelefonoCeluVO(telefonoVO);

                }else{
                    return solicitud;
                }

                // 08 - Consulta por Telefono Comercial
                solicitud.setResultado("Ocurrió un error al consultar Telefono Comercial por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFRTelefonoComercial", parametros);

                if(datos != null && datos.size() >0 ){

                    telefonoVO = (TelefonoVO)datos.get(0);
                    solicitud.setTelefonoComerVO(telefonoVO);

                }else{
                    return solicitud;
                }

                // 09 - Consulta por EMail
                solicitud.setResultado("Ocurrió un error al consultar Email por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                parametros.put("input_localidad", "1");				
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFREmail", parametros);

                if(datos != null && datos.size() >0 ){

                    emailVO = (EmailVO)datos.get(0);
                    solicitud.setEmailVO(emailVO);

                }else if (datos==null){//En caso de que SQL ejecute incorrectamente
                    return solicitud;
                }else if(datos.size()==0){//En caso de que SQL no devuelva datos
                    emailVO.setDireccMail("");
                    emailVO.setIdSecuenciaEmail(0);
                    solicitud.setEmailVO(emailVO);
                }

                // 09.1 - Consulta por EMail Comercial
                solicitud.setResultado("Ocurrió un error al consultar Email Comercial por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                parametros.put("input_localidad", "2");				
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFREmail", parametros);

                if(datos != null && datos.size() >0 ){					
                    emailComercialVO = (EmailVO)datos.get(0);
                    solicitud.setEmailComerVO(emailComercialVO);					
                }else if(datos==null){
                    return solicitud;
                }
                // 10 - Consulta por Direccion Particular
                solicitud.setResultado("Ocurrió un error al consultar Direccion Particular por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFRDireccionParticular", parametros);

                if(datos != null && datos.size() >0 ){

                    direccionVO = (DireccionVO)datos.get(0);
                    solicitud.setDireccionPartVO(direccionVO);

                }else{
                    return solicitud;
                }

                // 11 - Consulta por Direccion Comercial
                solicitud.setResultado("Ocurrió un error al consultar Direccion Comercial por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFRDireccionComercial", parametros);

                if(datos != null && datos.size() >0 ){

                    direccionVO = (DireccionVO)datos.get(0);
                    solicitud.setDireccionComerVO(direccionVO);

                }else{
                    return solicitud;
                }

                // 13 - Consulta por Analista (Promotor)
                solicitud.setResultado("Ocurrió un error al consultar Analista por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFRAnalista", parametros);

                if(datos != null && datos.size() >0 ){

                    analistaVO = (AnalistaVO)datos.get(0);
                    analistaVO.setDesOficina(Helper.obtenerDescripcion(listaParam.getListOficina(), analistaVO.getOficina()));

                    solicitud.setAnalistaCaptadorVO(analistaVO);

                }else{
                    return solicitud;
                }

                //13.1 - Consulta por Analista que se loguea en el sistema y que ingreso la solicitud
                solicitud.setResultado("Ocurrió un error al consultar por Analista por Folio/Rut");
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFRAnalist", parametros);
                if(datos != null && datos.size() >0 ){
                    analistaVO = (AnalistaVO)datos.get(0);
                    solicitud.setAnalistaVO(analistaVO);

                }else{
                    return solicitud;
                }

                // 14 - INI Consulta por Documentos
                solicitud.setResultado("Ocurrió un error al consultar Documentos por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFRDOcumentos", parametros); 

                DocumentoVO[] documentosSolicitud = (DocumentoVO[]) datos.toArray(new DocumentoVO[datos.size()]);

                Documento[] documentosParam = listaParam.getListTipoDocumentosDesafiliacion();

                DocumentoVO[] documentosFinal = Helper.matchDocumentos(documentosSolicitud, documentosParam);
                solicitud.setListaDocumentoVO(documentosFinal);

                // 14 - FIN Consulta por Documentos

                solicitud.setResultado("");
                solicitud.setCodResultado(0);

                break;

            default:

                solicitud.setResultado("Error en data. Existe mas de una solicitud asociada al Folio/RUT");
            solicitud.setCodResultado(98);

            }
            return solicitud;

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

        return solicitud;

    }

    /* Función que hace un update al estado de solicitud
     * Recibe como entrada el rut, folio y el estado al que se desea cambiar.
     */
    public static int updateEstadoSol(String folio, String estado, String rut){

        /* Retornos
			0 = Ok
			99 = Error Desconocido de BD
         */

        int estadoIni = 0;

        estadoIni = Integer.parseInt(estado);

        SolicitudVO solicitud = new SolicitudVO();
        solicitud.setFolio(Long.parseLong(folio));
        solicitud.setTipoEstadoSolicitud(estadoIni);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", solicitud);
            sqlMap.update("ModSolDesafiliaciondNS.updateEstado", parametros);

            sqlMap.commitTransaction();
            return 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }	

        return 99;
    }

    /* Función que hace un update al estado de solicitud
     * Recibe como entrada el rut, folio y el estado al que se desea cambiar.
     */
    public static int updateEstadoFechaResolucion(String folio, String fechaResolucion){

        //TODO Revisar Fechas
        String fechaResolFin = "";
        StringTokenizer st=new StringTokenizer(fechaResolucion,"/");
        fechaResolFin = st.nextToken()+"."+st.nextToken()+"."+st.nextToken();

        SolicitudVO solicitud = new SolicitudVO();
        solicitud.setFolio(Long.parseLong(folio));
        solicitud.setResolucionDirectorio(fechaResolFin);
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", solicitud);
            sqlMap.update("ModSolDesafiliaciondNS.updateFechaResolucion", parametros);

            sqlMap.commitTransaction();
            return 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }	

        return 99;
    }

    /* Función modificar datos de una solicitud asociado a un rut y folio.
     * Recibe como entrada la cadena que contiene los datos que serán modificados
     * Realiza un update a las tablas con los datos modificados, por medio del traspaso de la cadena
     * 	al DAO que es donde se redistribuyen los campos a los onjetos correspondientes.*/
    public static int updateSolicitud(String cadenaForm){

        /* Retornos
         * 0 OK.
         * 98 Error al consultar el Analista Captador
         * 99 Error desconocido de BD.
         */

        String fecha = "";
        Date date = new Date();
        List data = null;
        long idDocumento = 0;
        String cadenaDocumentos = "";

        String DATE_FORMAT1 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            StringTokenizer tokens = new StringTokenizer(cadenaForm, "#");
            int nDatos = tokens.countTokens();
            String[] datos = new String[nDatos];
            int i = 0;

            while(tokens.hasMoreTokens()){
                String str = tokens.nextToken();
                datos[i]= str;// Double.valueOf(str).doubleValue();
                System.out.println("datos["+i+"] = "+datos[i]);
                i++;
            }

            sqlMap.startTransaction(0);

            //01.- update a la tabla persona.
            PersonaVO personaVO = new PersonaVO();

            personaVO.setIdPersona(Long.parseLong(datos[28]));
            personaVO.setApellidoPaterno(datos[9]);
            personaVO.setApellidoMaterno(datos[10]);
            personaVO.setNombres(datos[11]);			

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", personaVO);
            sqlMap.update("ModSolDesafiliaciondNS.updateSolicitudPersona", parametros);

            //02.- update a la tabla solicitud
            SolicitudVO solicitudVO = new SolicitudVO();

            solicitudVO.setIdSolicitud(Long.parseLong(datos[29]));

            fecha = datos[25];
            date = sdf1.parse(fecha);

            solicitudVO.setFechaVigenciaDate(date);
            solicitudVO.setFechaVigencia(fecha);

            solicitudVO.setTipoCajaOrigen(Integer.parseInt(datos[24]));
            //Se agrega fecha de firma.
            fecha = datos[27];

            if(fecha.trim().equals("")){

                fecha = "01/01/1900";
                date = sdf1.parse(fecha);			
                solicitudVO.setFechaFirmaDate(date);
                solicitudVO.setFechaFirma(fecha);

            }else{
                date = sdf1.parse(fecha);			
                solicitudVO.setFechaFirmaDate(date);
                solicitudVO.setFechaFirma(fecha);
            }
            //solicitudVO.setIdCaptador(Long.parseLong(datos[56-1]));
            solicitudVO.setHoraCaptacion(datos[36]);
            solicitudVO.setTipoMotivoDesafiliacion(Integer.parseInt(datos[38]));
            solicitudVO.setDescTipoMotivoDesafiliacion(Integer.parseInt(datos[39]));
            solicitudVO.setObservaciones(datos[40]);
            solicitudVO.setComentarios(datos[41]);
            solicitudVO.setTipoEstadoSolicitud(Integer.parseInt(datos[43]));
            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", solicitudVO);
            sqlMap.update("ModSolDesafiliaciondNS.updateSolicitudSolicitud", parametros);

            //03.- update a la tabla Telefono para telefono celular
            TelefonoVO telefonoCeluVO = new TelefonoVO(); 

            telefonoCeluVO.setIdSecuenciaTelefono(Long.parseLong(datos[32]));
            telefonoCeluVO.setCodArea(datos[12]);
            telefonoCeluVO.setNroTelefono(datos[13]);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", telefonoCeluVO);
            sqlMap.update("ModSolDesafiliaciondNS.updateSolicitudTelefonoCelular", parametros);

            //04.- update a la tabla Telefono para telefono de contacto
            TelefonoVO telefonoPartVO = new TelefonoVO(); 

            telefonoPartVO.setIdSecuenciaTelefono(Long.parseLong(datos[31]));
            telefonoPartVO.setCodArea(datos[14]);
            telefonoPartVO.setNroTelefono(datos[15]);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", telefonoPartVO);
            sqlMap.update("ModSolDesafiliaciondNS.updateSolicitudTelefonoParticular", parametros);

            //05.- update a la tabla Direccion para direccion particular
            DireccionVO direccionPartVO = new DireccionVO();

            direccionPartVO.setIdSecuenciaDireccion(Long.parseLong(datos[34]));
            direccionPartVO.setGlosCalle(datos[17]);
            direccionPartVO.setNumDireccion(datos[18]);
            direccionPartVO.setPoblacionVilla(datos[19]);
            direccionPartVO.setDpto(datos[20]);
            direccionPartVO.setRegion(Integer.parseInt(datos[21]));
            direccionPartVO.setCiudad(Integer.parseInt(datos[22]));
            direccionPartVO.setComuna(Integer.parseInt(datos[23]));

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", direccionPartVO);
            sqlMap.update("ModSolDesafiliaciondNS.updateSolicitudDireccionParticular", parametros);			

            //06.- update a la tabla Email
            EmailVO emailVO = new EmailVO();
            EntidadRelacionVO entidadRelacion=new EntidadRelacionVO();
            emailVO.setIdSecuenciaEmail(Long.parseLong(datos[33]));
            emailVO.setDireccMail(datos[16]);
            if(emailVO.getIdSecuenciaEmail()!=0){
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", emailVO);
                sqlMap.update("ModSolDesafiliaciondNS.updateSolicitudEmail", parametros);
            }else{

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", emailVO);				
                List datosMailC = sqlMap.queryForList("newsolicitudNS.selectIdEmail", parametros);

                if(datosMailC != null && datosMailC.size() >0 ){
                    //seteo de id`s
                    long idEmail = Long.parseLong((String)datosMailC.get(0));
                    emailVO.setIdSecuenciaEmail(idEmail);
                    emailVO.setTipoLocalidad(1);//IDENTIFICADOR PARA CORREO PARTICULAR
                    entidadRelacion.setIdSecuencia(idEmail);
                    entidadRelacion.setIdEntidad(personaVO.getIdPersona());
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", entidadRelacion);
                    sqlMap.insert("newsolicitudNS.insertNewSolIdEmailEnt", parametros);

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", emailVO);
                    sqlMap.insert("newsolicitudNS.insertNewSolEmail", parametros);						
                }				

            }
            //11.- update a la tabla Afiliado
            /*AfiliadoVO afiliadoVO = new AfiliadoVO();

			afiliadoVO.setIdPersonaAfiliado(Long.parseLong(datos[63-1]));
			afiliadoVO.setTipoEstado(Integer.parseInt(datos[16-1]));
			afiliadoVO.setIdSecuenciaAgrupacion(Long.parseLong(datos[75-1]));
			parametros = new HashMap();
			parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
			parametros.put("input", afiliadoVO);
			sqlMap.update("ModSolDesafiliaciondNS.updateSolicitudAfiliado", parametros);
             */

            //12.- update a la tabla analista
            AnalistaVO analistaCapVO = new AnalistaVO();
            analistaCapVO.setIdAnalista(Long.parseLong(datos[42]));
            analistaCapVO.setOficina(Integer.parseInt(datos[37]));

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", analistaCapVO);
            sqlMap.update("ModSolDesafiliaciondNS.updateOficinaAnalista", parametros);
            /*Insertar a la tabla Analista... Se inserta Analista Comercial*/
            /*
			parametros = new HashMap();
			parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
			parametros.put("input", Long.toString(analistaCapVO.getIdAnalista()));
			data = sqlMap.queryForList("newsolicitudNS.selectIdAnalista", parametros);

			if(datos != null && data.size() > 0){

				long countIdCaptador = Long.parseLong((String)data.get(0));

				if(countIdCaptador == 0){

					parametros = new HashMap();
					parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
					parametros.put("input", analistaCapVO);
					sqlMap.insert("newsolicitudNS.insertNewSolAnalista", parametros);

				}else{
					parametros = new HashMap();
					parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
					parametros.put("input", analistaCapVO);
					sqlMap.update("newsolicitudNS.updateNewSolOficinaAnalista", parametros);
				}
			}else{
				return 98;
			}
             */
            //13.- insert o update documentos
            cadenaDocumentos = datos[35];

            StringTokenizer tokensDoc = new StringTokenizer(cadenaDocumentos, "|");
            int nDatosDoc = tokensDoc.countTokens();
            String[] documentos = new String[nDatosDoc];
            int j = 0;

            while(tokensDoc.hasMoreTokens()){
                String str = tokensDoc.nextToken();
                documentos[j]= str;// Double.valueOf(str).doubleValue();
                System.out.println("documentos["+i+"] "+documentos[j]);
                j++;
            }

            int k = 0;

            do{
                DocumentoVO documentoVO = new DocumentoVO();
                documentoVO.setIdSecuenciaDocumento(Long.parseLong(documentos[k]));
                documentoVO.setIdSolicitud(solicitudVO.getIdSolicitud());
                documentoVO.setTipoDocumento(Integer.parseInt(documentos[k + 1]));
                documentoVO.setEstadoDocumento(Integer.parseInt(documentos[k + 2]));
                documentoVO.setObservacionesDocumento(documentos[k + 3]);

                if(documentoVO.getIdSecuenciaDocumento() == 0){
                    //Obtener ID
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    data = sqlMap.queryForList("ModSolDesafiliaciondNS.selectIdDocumento", parametros);

                    if(data != null && data.size() > 0){

                        idDocumento = Long.parseLong((String)data.get(0));
                        documentoVO.setIdSecuenciaDocumento(idDocumento);
                    }
                    //Insert
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", documentoVO);
                    sqlMap.insert("ModSolDesafiliaciondNS.insertSolicitudNewDocumento", parametros);
                }else{
                    //Update
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", documentoVO);
                    sqlMap.update("ModSolDesafiliaciondNS.updateSolicitudDocumento", parametros);
                }

                k = k + 4;

            }while(k < nDatosDoc);

            sqlMap.commitTransaction();
            return 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace(); }

        }	

        return 99;		
    }

    /* Función que obtiene los estados posibles a los que esta asociado un determinado estado actual.
     * Recibe como entrada en estado actual
     * Retorna un arreglo de tipo Parametro con los estados asociados al estado actual.*/
    public static Parametro[] getEstadosDestinoPosibles(String estadoActual){

        List datos = null;
        Parametro[] salida = new Parametro[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", estadoActual);
            datos = sqlMap.queryForList("ModSolDesafiliaciondNS.getEstadosDestinoPosibles", parametros);

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

    /*Función que obtiene la informacion de la solicitud filtrando por folio.
     * Recibe como entrada el folio por el cual se desea buscar.
     * Retorna un objeto de tipo SolicitudNegocioVO con la informacion requerida.*/
    public static SolicitudNegocioVO obtenerSolicitudPorFolio(String folio){
        /*  Retornos 
			0 = Ok
			1 = No existe Folio
			95 = Error al consultar por el folio
			97 = Los datos de folio llegaron al DAO sin valores
			98 = Error en data. Existen mas de una solicitud asociada al Folio.
			99 = Error Desconocido de BD
         */

        //variables generales
        List datos = null;
        int resultado = 0;

        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "MM/dd/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista
        String DATE_FORMAT3 = "dd/MM/yy"; // El que se envia a la Vista		
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);
        SimpleDateFormat sdf3 = new SimpleDateFormat(DATE_FORMAT3);

        SolicitudNegocioVO solicitud = new SolicitudNegocioVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try{
            sqlMap.startTransaction(0);

//          -------------------------------------------------------------------------------			

            if(folio.equals("")){

                solicitud.setResultado("Ocurrió un error al consultar por el Folio");
                solicitud.setCodResultado(97);

                return solicitud;
            }

            if(!folio.equals("")){

                HashMap parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerContSolicitudF", parametros);

                if(datos != null && datos.size() >0 ){

                    resultado = Integer.parseInt((String)datos.get(0));

                }else{
                    solicitud.setResultado("Ocurrió un error al consultar por el Folio");
                    solicitud.setCodResultado(95);

                    return solicitud;
                }
            }	

            switch(resultado){

            case 0:

                solicitud.setResultado("No existe una solicitud asociada al Folio");
                solicitud.setCodResultado(1);

                break;

            case 1:

//              -------------------------------------------------------------------------------

                // Objetos internos de SolicitudNegocioVO (solicitud)
                AfiliadoVO afiliadoVO = new AfiliadoVO();
                AnalistaVO analistaVO = new AnalistaVO();
                DireccionVO direccionVO = new DireccionVO();
                EmailVO emailVO = new EmailVO();
                EmailVO emailComercialVO = new EmailVO();			
                GrupoFamiliarVO grupoFamiliarVO = new GrupoFamiliarVO();
                IngresoEconomicoVO ingresoEconomicoVO = new IngresoEconomicoVO();
                PersonaVO personaVO = new PersonaVO();
                SolicitudVO solicitudVO = new SolicitudVO();
                TelefonoVO telefonoVO = new TelefonoVO();

                ListadoParametros listaParam = ListadoParametros.getInstancia();

                //Consultas
                // 01 - Consulta por Afiliado
                solicitud.setResultado("Ocurrió un error al consultar Afiliado por el Folio");
                solicitud.setCodResultado(96);

                HashMap parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolAfiliado", parametros);

                if(datos != null && datos.size() >0 ){

                    afiliadoVO = (AfiliadoVO)datos.get(0);
                    solicitud.setAfiliadoVO(afiliadoVO);

                }else{
                    return solicitud;
                }

                // 02 - Consulta por Persona
                solicitud.setResultado("Ocurrió un error al consultar Persona por el Folio");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolPersona", parametros);

                if(datos != null && datos.size() >0 ){

                    personaVO = (PersonaVO)datos.get(0);

                    fecha = personaVO.getFechaNacimiento();
                    date = sdf1.parse(fecha);

                    personaVO.setFechaNacimiento(sdf2.format(date));

                    solicitud.setPersonaVO(personaVO);

                }else{
                    return solicitud;
                }

                // 03 - Consulta por Solicitud
                solicitud.setResultado("Ocurrió un error al consultar Solicitud por el Folio");
                String observaciones = "";
                String comentarios = "";
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolSolicitud", parametros);

                if(datos != null && datos.size() >0 ){

                    solicitudVO = (SolicitudVO)datos.get(0);

                    fecha = solicitudVO.getFechaVigencia();
                    date = sdf1.parse(fecha);

                    solicitudVO.setFechaVigencia(sdf2.format(date));

                    fecha = solicitudVO.getFechaIngreso();
                    date = sdf1.parse(fecha);

                    solicitudVO.setFechaIngreso(sdf2.format(date));

                    solicitudVO.setDesTipoEstadoSolicitud(Helper.obtenerDescripcion(listaParam.getListEstadoSolicitudDesafiliacion(), solicitudVO.getTipoEstadoSolicitud()));

                    fecha = solicitudVO.getFechaFirma();
                    date = sdf1.parse(fecha);

                    solicitudVO.setFechaFirma(sdf2.format(date));

                    fecha = solicitudVO.getResolucionDirectorio();
                    date = sdf1.parse(fecha);
                    fecha = sdf2.format(date);

                    if(fecha.equals(IND_Constants.Fec_Comodin) || fecha.equals(IND_Constants.Fec_Comodin2))
                    {
                        solicitudVO.setResolucionDirectorio("");
                    }else{
                        solicitudVO.setResolucionDirectorio(fecha);
                    }

                    solicitudVO.setDesOficina(Helper.obtenerDescripcion(listaParam.getListOficina(), solicitudVO.getOficina()));

                    observaciones = solicitudVO.getObservaciones();
                    if (observaciones == null ){
                        observaciones = "";
                    }
                    solicitudVO.setObservaciones(observaciones);

                    comentarios = solicitudVO.getComentarios();
                    if (comentarios == null ){
                        comentarios = "";
                    }
                    solicitudVO.setComentarios(comentarios);

                    fecha = solicitudVO.getFechaUltAporte();
                    date = sdf1.parse(fecha);
                    solicitudVO.setFechaUltAporte(sdf2.format(date));

                    int motivo = 0;
                    int desMotivo = 0;

                    motivo = solicitudVO.getTipoMotivoDesafiliacion();
                    solicitudVO.setTipoMotivoDesafiliacion(motivo);

                    desMotivo = solicitudVO.getDescTipoMotivoDesafiliacion();
                    solicitudVO.setDescTipoMotivoDesafiliacion(desMotivo);

                    solicitud.setSolicitudVO(solicitudVO);

                }else{
                    return solicitud;
                }

                // 04 - Consulta por Grupo Familiar
                solicitud.setResultado("Ocurrió un error al consultar Grupo Familiar por el Folio");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolGrupoFamiliar", parametros);

                if(datos != null && datos.size() >0 ){

                    grupoFamiliarVO = (GrupoFamiliarVO)datos.get(0);
                    solicitud.setGrupoFamiliarVO(grupoFamiliarVO);


                }else{
                    return solicitud;
                }

                // 05 - Consulta por Ingreso Economico
                solicitud.setResultado("Ocurrió un error al consultar Ingreso Economico por el Folio");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolIngresoEconomico", parametros);

                if(datos != null && datos.size() >0 ){

                    ingresoEconomicoVO = (IngresoEconomicoVO)datos.get(0);
                    if(ingresoEconomicoVO.getFecUltCotizacion()!=null){
                        Date fecUltCotizacion=sdf3.parse(ingresoEconomicoVO.getFecUltCotizacion());
                        ingresoEconomicoVO.setFecUltCotizacion(sdf2.format(fecUltCotizacion));
                    }else{
                        ingresoEconomicoVO.setFecUltCotizacion("");
                    }
                    solicitud.setIngresoEconomicoVO(ingresoEconomicoVO);

                }else{
                    return solicitud;
                }

                // 06 - Consulta por Telefono Particular
                solicitud.setResultado("Ocurrió un error al consultar Telefono Particular por el Folio");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolTelefonoParticular", parametros);

                if(datos != null && datos.size() >0 ){

                    telefonoVO = (TelefonoVO)datos.get(0);
                    solicitud.setTelefonoPartVO(telefonoVO);

                }else{
                    return solicitud;
                }

                // 07 - Consulta por Celular Particular
                solicitud.setResultado("Ocurrió un error al consultar Celular Particular por el Folio");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolCelularParticular", parametros);

                if(datos != null && datos.size() >0 ){

                    telefonoVO = (TelefonoVO)datos.get(0);
                    solicitud.setTelefonoCeluVO(telefonoVO);

                }else{
                    return solicitud;
                }

                // 08 - Consulta por Telefono Comercial
                solicitud.setResultado("Ocurrió un error al consultar Telefono Comercial por el Folio");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolTelefonoComercial", parametros);

                if(datos != null && datos.size() >0 ){

                    telefonoVO = (TelefonoVO)datos.get(0);
                    solicitud.setTelefonoComerVO(telefonoVO);

                }else{
                    return solicitud;
                }

                // 09 - Consulta por EMail
                solicitud.setResultado("Ocurrió un error al consultar Email por el Folio");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                parametros.put("input_localidad", "1");				
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolEmail", parametros);

                if(datos != null && datos.size() >0 ){

                    emailVO = (EmailVO)datos.get(0);
                    solicitud.setEmailVO(emailVO);

                }else if(datos == null){
                    return solicitud;
                }else if(datos.size()==0){
                    emailVO.setDireccMail("");
                    emailVO.setIdSecuenciaEmail(0);
                    solicitud.setEmailVO(emailVO);
                }

                // 09.1 - Consulta por EMail Comercial
                solicitud.setResultado("Ocurrió un error al consultar Email Comercial por el Folio");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                parametros.put("input_localidad", "2");			
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolEmail", parametros);

                if(datos != null && datos.size() >0 ){

                    emailComercialVO = (EmailVO)datos.get(0);
                    solicitud.setEmailComerVO(emailComercialVO);

                }else if(datos == null){
                    return solicitud;
                }			

                // 10 - Consulta por Direccion Particular
                solicitud.setResultado("Ocurrió un error al consultar Direccion Particular por el Folio");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolDireccionParticular", parametros);

                if(datos != null && datos.size() >0 ){

                    direccionVO = (DireccionVO)datos.get(0);
                    solicitud.setDireccionPartVO(direccionVO);

                }else{
                    return solicitud;
                }

                // 11 - Consulta por Direccion Comercial
                solicitud.setResultado("Ocurrió un error al consultar Direccion Comercial por el Folio");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolDireccionComercial", parametros);

                if(datos != null && datos.size() >0 ){

                    direccionVO = (DireccionVO)datos.get(0);
                    solicitud.setDireccionComerVO(direccionVO);

                }else{
                    return solicitud;
                }

                // 12 - Consulta por Analista
                solicitud.setResultado("Ocurrió un error al consultar Analista por el Folio");
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolAnalista", parametros);

                if(datos != null && datos.size() >0 ){

                    analistaVO = (AnalistaVO)datos.get(0);
                    analistaVO.setDesOficina(Helper.obtenerDescripcion(listaParam.getListOficina(), analistaVO.getOficina()));

                    solicitud.setAnalistaVO(analistaVO);

                }else{
                    return solicitud;
                }

                //13.1 - Consulta por Captador (Promotor)
                solicitud.setResultado("Ocurrió un error al consultar Captador por el Folio");
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolFolCaptador", parametros);

                if(datos != null && datos.size() >0 ){

                    analistaVO = (AnalistaVO)datos.get(0);
                    analistaVO.setDesOficina(Helper.obtenerDescripcion(listaParam.getListOficina(), analistaVO.getOficina()));

                    solicitud.setAnalistaCaptadorVO(analistaVO);

                }else{
                    return solicitud;
                }

                // 14 - INI Consulta por Documentos
                solicitud.setResultado("Ocurrió un error al consultar Documentos por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudFolDOcumentos", parametros); 
                System.out.println("largo documentoooossssss------------------------>"+datos.size());
                DocumentoVO[] documentosSolicitud = (DocumentoVO[]) datos.toArray(new DocumentoVO[datos.size()]);

                Documento[] documentosParam = listaParam.getListTipoDocumentosDesafiliacion();

                DocumentoVO[] documentosFinal = Helper.matchDocumentos(documentosSolicitud, documentosParam);

                solicitud.setListaDocumentoVO(documentosFinal);

                // 13 - FIN Consulta por Documentos

                solicitud.setResultado("");
                solicitud.setCodResultado(0);

//              -----------------------------------------------------------------------------------------

                break;

            default:

                solicitud.setResultado("Error en data. Existe mas de una solicitud asociada al Folio");
            solicitud.setCodResultado(98);

            }
            return solicitud;

//          -----------------------------------------------------------------------------------------			

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

        return solicitud;
    }

    /*Función que obtiene la informacion de la solicitud filtrando por rut.
     * Recibe como entrada el rut por el cual se desea buscar.
     * Retorna un objeto de tipo SolicitudNegocioVO con la informacion requerida.*/
    public static SolicitudNegocioVO obtenerDatosPorRut(String rut){

        // Variables generales
        List datos = null;

        SolicitudNegocioVO solicitudPorRut = new SolicitudNegocioVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try{
            sqlMap.startTransaction(0);

            //Objetos internos de SolicitudNegocioVO (solicitudPorRut)
            PersonaVO personaVO = new PersonaVO();
            SolicitudVO solicitudVO = new SolicitudVO();

            ListadoParametros listaParam = ListadoParametros.getInstancia();

            //consulta por persona.
            solicitudPorRut.setResultado("Ocurrió un error al consultar Persona por el RUT");

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", rut);
            datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudRutPers", parametros);

            if(datos != null && datos.size() >0 ){

                personaVO = (PersonaVO)datos.get(0);
                solicitudPorRut.setPersonaVO(personaVO);

            }else{
                return solicitudPorRut;
            }

            //Consulta por Solicitud
            solicitudPorRut.setResultado("Ocurrió un error al consultar Solicitud por el RUT");

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", rut);
            datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudRutSol", parametros);

            if(datos != null && datos.size() > 0 ){

                solicitudVO = (SolicitudVO)datos.get(0);
                solicitudVO.setDesTipoEstadoSolicitud(Helper.obtenerDescripcion(listaParam.getListEstadoSolicitudDesafiliacion(), solicitudVO.getTipoEstadoSolicitud()));				
                solicitudPorRut.setSolicitudVO(solicitudVO);

            }else{
                return solicitudPorRut;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return solicitudPorRut;
    }

    /*Obtiene los datos de solicitudVO por RUT. Retorna un arreglo de objetos tipo SolicitudVO*/
    public static SolicitudVO[] obtenerFoliosPorRut(String rut){

        List datos = null;
        SolicitudVO[] solicitud = new SolicitudVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try{

            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", rut);
            datos = sqlMap.queryForList("ModSolDesafiliaciondNS.obtenerSolicitudRutSol", parametros);

            return (SolicitudVO[]) datos.toArray(new SolicitudVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return solicitud;
    }

    /* Función que obtiene los estados posibles a los que esta asociado un determinado estado actual.
     * Recibe como entrada en estado actual
     * Retorna un arreglo de tipo Parametro con los estados asociados al estado actual.*/
    public static Parametro[] getEstadosDestinoPosiblesDoc(String estadoActual){

        List datos = null;
        Parametro[] salida = new Parametro[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", estadoActual);
            datos = sqlMap.queryForList("ModSolDesafiliaciondNS.getEstadosDestinoPosiblesDoc", parametros); //TODO SToro Que se debe reparar aqui?

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

    public static String recuperarFecVigencia(String folio){

        List datos = null;
        String salida = IND_Constants.Fec_Comodin;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener la fecha
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", folio);
            datos = sqlMap.queryForList("ModSolDesafiliaciondNS.recuperarFecVigencia", parametros);

            //verifica que la consultra traiga los datos requeridos
            if(datos != null && datos.size() > 0){
                //Se guarda el resultado de la consulta en la variable salida
                salida = ((String)datos.get(0));
            }
            //retorna la fecha asociada al folio
            return salida;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }	

    public static String recuperarFecFirma(String folio){

        List datos = null;
        String salida = IND_Constants.Fec_Comodin;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener la fecha
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", folio);
            datos = sqlMap.queryForList("ModSolDesafiliaciondNS.recuperarFecFirma", parametros);

            //verifica que la consultra traiga los datos requeridos
            if(datos != null && datos.size() > 0){
                //Se guarda el resultado de la consulta en la variable salida
                salida = ((String)datos.get(0));
            }
            //retorna la fecha asociada al folio
            return salida;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }	

}
