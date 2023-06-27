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
import cl.araucana.independientes.struts.Forms.ModSolicitudForm;
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

public class ModSolicitudDAO {

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
        Date date = new Date();

        String DATE_FORMAT1 = "MM/dd/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista
        String DATE_FORMAT3 = "dd/MM/yy"; // El que se envia a la Vista		
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);
        SimpleDateFormat sdf3 = new SimpleDateFormat(DATE_FORMAT3);		

        SolicitudNegocioVO solicitud = new SolicitudNegocioVO();
        ModSolicitudForm form = new ModSolicitudForm();

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerContSolicitudFR", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFRAfiliado", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFRPersona", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFRSolicitud", parametros);

                if(datos != null && datos.size() >0 ){

                    solicitudVO = (SolicitudVO)datos.get(0);

                    fecha = solicitudVO.getFechaVigencia();
                    date = sdf1.parse(fecha);
                    solicitudVO.setFechaVigencia(sdf2.format(date));

                    fecha = solicitudVO.getFechaIngreso();
                    date = sdf1.parse(fecha);
                    solicitudVO.setFechaIngreso(sdf2.format(date));

                    solicitudVO.setDesTipoEstadoSolicitud(Helper.obtenerDescripcion(listaParam.getListEstadoSolicitudAfiliacion(), solicitudVO.getTipoEstadoSolicitud()));

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

                    solicitud.setSolicitudVO(solicitudVO);

                }else{
                    return solicitud;
                }

                // 04 - Consulta por Grupo Familiar
                solicitud.setResultado("Ocurrió un error al consultar Grupo Familiar por el Folio/RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", form);
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFRGrupoFamiliar", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFRIngresoEconomico", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFRTelefonoParticular", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFRCelularParticular", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFRTelefonoComercial", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFREmail", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFREmail", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFRDireccionParticular", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFRDireccionComercial", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFRAnalista", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFRAnalist", parametros);
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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFRDOcumentos", parametros); 
                System.out.println("datos.size de la lista de documentos-------------------------->"+datos.size());
                DocumentoVO[] documentosSolicitud = (DocumentoVO[]) datos.toArray(new DocumentoVO[datos.size()]);

                Documento[] documentosParam = listaParam.getListTipoDocumentosFull();

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
        String estadoFin = "0";

        estadoIni = Integer.parseInt(estado);

        SolicitudVO solicitud = new SolicitudVO();
        solicitud.setFolio(Long.parseLong(folio));
        solicitud.setTipoEstadoSolicitud(estadoIni);

        switch(estadoIni){

        case 1: estadoFin = "1";
        break;
        case 2: estadoFin = "1";
        break;
        case 3: estadoFin = "7";
        break;
        case 4: estadoFin = "1";
        break;
        case 5: estadoFin = "1";
        break;
        case 6:	estadoFin = "7";
        break;
        case 7: estadoFin = "2";
        break;
        case 8:	estadoFin = "5";
        break;
        }

        ModAfiliadoForm form = new ModAfiliadoForm();

        form.setTxt_Rut(rut);
        form.setDbx_EstSolicitud(estadoFin);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", solicitud);
            sqlMap.update("modsolicitudNS.updateEstado", parametros);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", form);
            sqlMap.update("modafiliadoNS.updateEstadoAfiliado", parametros);

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
            sqlMap.update("modsolicitudNS.updateFechaResolucion", parametros);

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
                i++;
            }

            sqlMap.startTransaction(0);

            //01.- update a la tabla persona.
            PersonaVO personaVO = new PersonaVO();

            personaVO.setIdPersona(Long.parseLong(datos[63-1]));
            personaVO.setApellidoPaterno(datos[10-1]);
            personaVO.setApellidoMaterno(datos[11-1]);
            personaVO.setNombres(datos[12-1]);

            fecha = datos[14-1];
            date = sdf1.parse(fecha);

            personaVO.setFechaNacimientoDate(date);
            personaVO.setFechaNacimiento(fecha);

            personaVO.setTipoSexo(Integer.parseInt(datos[15-1]));
            personaVO.setTipoNacionalidad(Integer.parseInt(datos[13-1]));

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", personaVO);
            sqlMap.update("modsolicitudNS.updateSolicitudPersona", parametros);

            //02.- update a la tabla solicitud
            SolicitudVO solicitudVO = new SolicitudVO();

            solicitudVO.setIdSolicitud(Long.parseLong(datos[64-1]));

            fecha = datos[55-1];
            date = sdf1.parse(fecha);

            solicitudVO.setFechaVigenciaDate(date);
            solicitudVO.setFechaVigencia(fecha);

            solicitudVO.setTipoCajaOrigen(Integer.parseInt(datos[54-1]));

            //Se agrega fecha de firma.
            fecha = datos[62-1];

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
            solicitudVO.setIdCaptador(Long.parseLong(datos[56-1]));
            solicitudVO.setHoraCaptacion(datos[80-1]);
            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", solicitudVO);
            sqlMap.update("modsolicitudNS.updateSolicitudSolicitud", parametros);

            //03.- update a la tabla Telefono para telefono celular
            TelefonoVO telefonoCeluVO = new TelefonoVO(); 

            telefonoCeluVO.setIdSecuenciaTelefono(Long.parseLong(datos[69-1]));
            telefonoCeluVO.setCodArea(datos[17-1]);
            telefonoCeluVO.setNroTelefono(datos[18-1]);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", telefonoCeluVO);
            sqlMap.update("modsolicitudNS.updateSolicitudTelefonoCelular", parametros);

            //04.- update a la tabla Telefono para telefono de contacto
            TelefonoVO telefonoPartVO = new TelefonoVO(); 

            telefonoPartVO.setIdSecuenciaTelefono(Long.parseLong(datos[68-1]));
            telefonoPartVO.setCodArea(datos[19-1]);
            telefonoPartVO.setNroTelefono(datos[20-1]);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", telefonoPartVO);
            sqlMap.update("modsolicitudNS.updateSolicitudTelefonoParticular", parametros);

            //05.- update a la tabla Direccion para direccion particular
            DireccionVO direccionPartVO = new DireccionVO();

            direccionPartVO.setIdSecuenciaDireccion(Long.parseLong(datos[71-1]));
            direccionPartVO.setGlosCalle(datos[22-1]);
            direccionPartVO.setNumDireccion(datos[23-1]);
            direccionPartVO.setPoblacionVilla(datos[24-1]);
            direccionPartVO.setDpto(datos[25-1]);
            direccionPartVO.setRegion(Integer.parseInt(datos[26-1]));
            direccionPartVO.setCiudad(Integer.parseInt(datos[27-1]));
            direccionPartVO.setComuna(Integer.parseInt(datos[28-1]));

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", direccionPartVO);
            sqlMap.update("modsolicitudNS.updateSolicitudDireccionParticular", parametros);			

            //06.- update a la tabla Email
            EmailVO emailVO = new EmailVO();
            EntidadRelacionVO entidadRelacion=new EntidadRelacionVO();
            emailVO.setIdSecuenciaEmail(Long.parseLong(datos[70-1]));
            emailVO.setDireccMail(datos[21-1]);
            if(emailVO.getIdSecuenciaEmail()!=0){
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", emailVO);
                sqlMap.update("modsolicitudNS.updateSolicitudEmail", parametros);
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
            //07.- update a la tabla Direccion para direccion comercial
            DireccionVO direccionComerVO = new DireccionVO();

            direccionComerVO.setIdSecuenciaDireccion(Long.parseLong(datos[72-1]));
            direccionComerVO.setGlosCalle(datos[42-1]);
            direccionComerVO.setNumDireccion(datos[43-1]);
            direccionComerVO.setPoblacionVilla(datos[44-1]);
            direccionComerVO.setDpto(datos[45-1]);
            direccionComerVO.setRegion(Integer.parseInt(datos[48-1]));
            direccionComerVO.setCiudad(Integer.parseInt(datos[49-1]));
            direccionComerVO.setComuna(Integer.parseInt(datos[50-1]));

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", direccionComerVO);
            sqlMap.update("modsolicitudNS.updateSolicitudDireccionComercial", parametros);

            //08.- update a la tabla Telefono para telefono comercial
            TelefonoVO telefonoComerVO = new TelefonoVO(); 

            telefonoComerVO.setIdSecuenciaTelefono(Long.parseLong(datos[73-1]));
            telefonoComerVO.setCodArea(datos[46-1]);
            telefonoComerVO.setNroTelefono(datos[47-1]);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", telefonoComerVO);
            sqlMap.update("modsolicitudNS.updateSolicitudTelefonoComercial", parametros);

            //09.- update a la tabla GrupoFamiliar
            GrupoFamiliarVO grupoFamiliarVO = new GrupoFamiliarVO();

            grupoFamiliarVO.setIdGrupoFam(Long.parseLong(datos[66-1]));
            grupoFamiliarVO.setConyugue(Integer.parseInt(datos[38-1]));
            grupoFamiliarVO.setCantHijos(Integer.parseInt(datos[39-1]));

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", grupoFamiliarVO);
            sqlMap.update("modsolicitudNS.updateSolicitudGrupoFamiliar", parametros);

            //10.- update a la tabla IngresoEconomico
            IngresoEconomicoVO ingresoEconomicoVO = new IngresoEconomicoVO();

            ingresoEconomicoVO.setIdIngEconom(Long.parseLong(datos[67-1]));
            ingresoEconomicoVO.setActEconom(datos[40-1]);
            ingresoEconomicoVO.setRentaImponible(Long.parseLong(datos[51-1]));
            ingresoEconomicoVO.setRentaCotizada(Long.parseLong(datos[52-1]));
            ingresoEconomicoVO.setHonorario(Integer.parseInt(datos[41-1]));
            ingresoEconomicoVO.setMontoUltimaCotizacion(Long.parseLong(datos[78-1]));
            ingresoEconomicoVO.setFecUltCotizacion(datos[79-1]);		
            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", ingresoEconomicoVO);
            sqlMap.update("modsolicitudNS.updateSolicitudIngresoEconomico", parametros);

            //11.- update a la tabla Afiliado
            AfiliadoVO afiliadoVO = new AfiliadoVO();

            afiliadoVO.setIdPersonaAfiliado(Long.parseLong(datos[63-1]));
            afiliadoVO.setTipoProfesion(Integer.parseInt(datos[30-1]));
            afiliadoVO.setTipoNivelEduc(Integer.parseInt(datos[29-1]));
            afiliadoVO.setTipoRegSalud(Integer.parseInt(datos[37-1]));
            afiliadoVO.setTipoAfp(Integer.parseInt(datos[36-1]));
            afiliadoVO.setTipoEstado(Integer.parseInt(datos[16-1]));
            afiliadoVO.setMontoCotizar(Integer.parseInt(datos[53-1]));
            afiliadoVO.setIdSecuenciaAgrupacion(Long.parseLong(datos[75-1]));
            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", afiliadoVO);
            sqlMap.update("modsolicitudNS.updateSolicitudAfiliado", parametros);

            //12.- update a la tabla analista
            AnalistaVO analistaCapVO = new AnalistaVO();
            analistaCapVO.setIdAnalista(Long.parseLong(datos[56-1]));
            analistaCapVO.setApellidoPaterno(datos[57-1]);
            analistaCapVO.setApellidoMaterno(datos[58-1]);
            analistaCapVO.setNombres(datos[59-1]);
            analistaCapVO.setOficina(Integer.parseInt(datos[81-1]));

            /*Insertar a la tabla Analista... Se inserta Analista Comercial*/
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

            //13.- insert o update documentos
            cadenaDocumentos = datos[74-1];

            StringTokenizer tokensDoc = new StringTokenizer(cadenaDocumentos, "|");
            int nDatosDoc = tokensDoc.countTokens();
            String[] documentos = new String[nDatosDoc];
            int j = 0;

            while(tokensDoc.hasMoreTokens()){
                String str = tokensDoc.nextToken();
                documentos[j]= str;// Double.valueOf(str).doubleValue();
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
                    data = sqlMap.queryForList("modsolicitudNS.selectIdDocumento", parametros);

                    if(data != null && data.size() > 0){

                        idDocumento = Long.parseLong((String)data.get(0));
                        documentoVO.setIdSecuenciaDocumento(idDocumento);
                    }
                    //Insert
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", documentoVO);
                    sqlMap.insert("modsolicitudNS.insertSolicitudNewDocumento", parametros);
                }else{
                    //Update
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", documentoVO);
                    sqlMap.update("modsolicitudNS.updateSolicitudDocumento", parametros);
                }

                k = k + 4;

            }while(k < nDatosDoc);

            //15.- update/insert al campo Email Comercial
            EmailVO emailComercialVO = new EmailVO();
            EntidadRelacionVO entidadRelacionC=new EntidadRelacionVO();
            emailComercialVO.setDireccMail(datos[76-1]);
            emailComercialVO.setIdSecuenciaEmail(Long.parseLong(datos[77-1]));

            if(emailComercialVO.getIdSecuenciaEmail()!=0){
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", emailComercialVO);
                sqlMap.update("modsolicitudNS.updateSolicitudEmail", parametros);	
            }else if(!"".equals(emailComercialVO.getDireccMail().trim())){
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", emailComercialVO);				
                List datosMailC = sqlMap.queryForList("newsolicitudNS.selectIdEmail", parametros);

                if(datosMailC != null && datosMailC.size() >0 ){
                    //seteo de id`s
                    long idEmail = Long.parseLong((String)datosMailC.get(0));
                    emailComercialVO.setIdSecuenciaEmail(idEmail);
                    emailComercialVO.setTipoLocalidad(2);//IDENTIFICADOR PARA CORREO COMERCIAL
                    entidadRelacionC.setIdSecuencia(idEmail);
                    entidadRelacionC.setIdEntidad(personaVO.getIdPersona());
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", entidadRelacionC);
                    sqlMap.insert("newsolicitudNS.insertNewSolIdEmailEnt", parametros);

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", emailComercialVO);
                    sqlMap.insert("newsolicitudNS.insertNewSolEmail", parametros);						
                }
            }
            //Fin 15 - update al campo Email Comercial - REQ5348.

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
            datos = sqlMap.queryForList("modsolicitudNS.getEstadosDestinoPosibles", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerContSolicitudF", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolAfiliado", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolPersona", parametros);

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

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolSolicitud", parametros);

                if(datos != null && datos.size() >0 ){

                    solicitudVO = (SolicitudVO)datos.get(0);

                    fecha = solicitudVO.getFechaVigencia();
                    date = sdf1.parse(fecha);

                    solicitudVO.setFechaVigencia(sdf2.format(date));

                    fecha = solicitudVO.getFechaIngreso();
                    date = sdf1.parse(fecha);

                    solicitudVO.setFechaIngreso(sdf2.format(date));

                    solicitudVO.setDesTipoEstadoSolicitud(Helper.obtenerDescripcion(listaParam.getListEstadoSolicitudAfiliacion(), solicitudVO.getTipoEstadoSolicitud()));

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

                    solicitud.setSolicitudVO(solicitudVO);

                }else{
                    return solicitud;
                }

                // 04 - Consulta por Grupo Familiar
                solicitud.setResultado("Ocurrió un error al consultar Grupo Familiar por el Folio");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", folio);
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolGrupoFamiliar", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolIngresoEconomico", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolTelefonoParticular", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolCelularParticular", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolTelefonoComercial", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolEmail", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolEmail", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolDireccionParticular", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolDireccionComercial", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolAnalista", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolFolCaptador", parametros);

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
                datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudFolDOcumentos", parametros); 

                DocumentoVO[] documentosSolicitud = (DocumentoVO[]) datos.toArray(new DocumentoVO[datos.size()]);

                Documento[] documentosParam = listaParam.getListTipoDocumentosFull();

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
            datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudRutPers", parametros);

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
            datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudRutSol", parametros);

            if(datos != null && datos.size() > 0 ){

                solicitudVO = (SolicitudVO)datos.get(0);
                solicitudVO.setDesTipoEstadoSolicitud(Helper.obtenerDescripcion(listaParam.getListEstadoSolicitudAfiliacion(), solicitudVO.getTipoEstadoSolicitud()));				
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
            datos = sqlMap.queryForList("modsolicitudNS.obtenerSolicitudRutSol", parametros);

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
            datos = sqlMap.queryForList("modsolicitudNS.getEstadosDestinoPosiblesDoc", parametros); //TODO SToro Que se debe reparar aqui?

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
            datos = sqlMap.queryForList("modsolicitudNS.recuperarFecVigencia", parametros);

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
            datos = sqlMap.queryForList("modsolicitudNS.recuperarFecFirma", parametros);

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
