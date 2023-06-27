package cl.araucana.independientes.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.independientes.config.ConfiguracionSqlMap;
import cl.araucana.independientes.helper.GlobalProperties;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.struts.Forms.ModAfiliadoForm;
import cl.araucana.independientes.vo.AfiliadoVO;
import cl.araucana.independientes.vo.AnalistaVO;
import cl.araucana.independientes.vo.DireccionVO;
import cl.araucana.independientes.vo.EmailVO;
import cl.araucana.independientes.vo.EntidadRelacionVO;
import cl.araucana.independientes.vo.GrupoFamiliarVO;
import cl.araucana.independientes.vo.IngresoEconomicoVO;
import cl.araucana.independientes.vo.PersonaVO;
import cl.araucana.independientes.vo.SolicitudNegocioVO;
import cl.araucana.independientes.vo.SolicitudVO;
import cl.araucana.independientes.vo.TelefonoVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;
import cl.araucana.independientes.vo.param.Retorno;

public class ModAfiliadoDAO {

    /* Función que obtiene informacion de un afiliado
     * Recibe como entrada el rut del afiliado del que se desea consultar
     * Retorna un objeto de tipo SolicitudNegocioVO con la información requerida.*/
    public static SolicitudNegocioVO obtenerAfiliado(String rut){ 

        /* Retornos 
			0 = Ok
			1 = No existe RUT
			2 = No existe el Folio --> No se Usa
			3 = No existe el RUT --> No se usa
			94 = Error al consultar por el rut  --> No se usa
			95 = Error al consultar por el folio  --> No se usa
			96 = Error al consultar por el par folio/rut
			97 = Los datos de folio y rut llegaron al DAO sin valores
			98 = Error en data. Existen mas de una solicitud asociada al RUT
			99 = Error Desconocido de BD
         */

        // Variables generales
        List datos = null;
        int resultado = 0;

        SolicitudNegocioVO solicitudAfiliado = new SolicitudNegocioVO();

        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "MM/dd/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista
        String DATE_FORMAT3 = "dd/MM/yy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);
        SimpleDateFormat sdf3 = new SimpleDateFormat(DATE_FORMAT3);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try{
            sqlMap.startTransaction(0);
            if(rut.equals("")){

                solicitudAfiliado.setResultado("Ocurrió un error al consultar por el RUT");
                solicitudAfiliado.setCodResultado(94);
                System.out.println("1");
                return solicitudAfiliado;
            }

            if(!rut.equals("")){


                HashMap parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("modafiliadoNS.obtenerContAfiliadoR", parametros);
                //datos = sqlMap.queryForList("modafiliadoNS.obtenerCountRUT",rut);
                //datos = sqlMap.queryForList("modafiliadoNS.obtCountRUT",rut);
                if(datos != null && datos.size() > 0 ){

                    resultado = Integer.parseInt((String)datos.get(0));
                }else{

                    solicitudAfiliado.setResultado("Ocurrió un error al consultar por el RUT");
                    solicitudAfiliado.setCodResultado(94);
                    System.out.println("2");
                    return solicitudAfiliado;
                }

            }	

            switch(resultado){

            case 0:

                solicitudAfiliado.setResultado("No existe una solicitud asociada al RUT");
                solicitudAfiliado.setCodResultado(1);

                break;

            case 1:

                // Objetos internos de SolicitudNegocioVO (solicitud)
                AfiliadoVO afiliadoVO = new AfiliadoVO();
                DireccionVO direccionVO = new DireccionVO();
                EmailVO emailVO = new EmailVO();
                EmailVO emailComercialVO = new EmailVO();
                GrupoFamiliarVO grupoFamiliarVO = new GrupoFamiliarVO();
                IngresoEconomicoVO ingresoEconomicoVO = new IngresoEconomicoVO();
                PersonaVO personaVO = new PersonaVO();
                SolicitudVO solicitudVO = new SolicitudVO();
                SolicitudVO solicitudVOAUX = new SolicitudVO();
                TelefonoVO telefonoVO = new TelefonoVO();
                AnalistaVO analistaVO = new AnalistaVO();

                ListadoParametros listaParam = ListadoParametros.getInstancia();

                //Consultas
                // 01 - Consulta por Afiliado
                solicitudAfiliado.setResultado("Ocurrió un error al consultar Afiliado por el RUT");
                solicitudAfiliado.setCodResultado(94);

                HashMap parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoRAfiliado", parametros);

                if(datos != null && datos.size() >0 ){

                    afiliadoVO = (AfiliadoVO)datos.get(0);
                    afiliadoVO.setDesTipoEstadoAfiliado(Helper.obtenerDescripcion(listaParam.getListEstadoAfiliado(), afiliadoVO.getTipoEstadoAfiliado()));

                    solicitudAfiliado.setAfiliadoVO(afiliadoVO);

                }else{
                    System.out.println("3");
                    return solicitudAfiliado;
                }

                // 02 - Consulta por Persona
                solicitudAfiliado.setResultado("Ocurrió un error al consultar Persona por el RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoRPersona",parametros);

                if(datos != null && datos.size() >0 ){

                    personaVO = (PersonaVO)datos.get(0);

                    fecha = personaVO.getFechaNacimiento();
                    date = sdf1.parse(fecha);

                    personaVO.setFechaNacimiento(sdf2.format(date));

                    solicitudAfiliado.setPersonaVO(personaVO);

                }else{
                    System.out.println("4");
                    return solicitudAfiliado;
                }

                // 03 - Consulta por Solicitud
                solicitudAfiliado.setResultado("Ocurrió un error al consultar Solicitud por el RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoRSolicitud",parametros);

                if(datos != null && datos.size() >0 ){

                    solicitudVO = (SolicitudVO)datos.get(0);
                    fecha = solicitudVO.getFechaFirma();
                    date = sdf1.parse(fecha);

                    solicitudVO.setFechaFirma(sdf2.format(date));
                    if(solicitudVO.getFechaVigencia()!=null && !"".equals(solicitudVO.getFechaVigencia().trim())){
                        Date dateV=sdf3.parse(solicitudVO.getFechaVigencia());
                        solicitudVO.setFechaVigencia(sdf2.format(dateV));
                    }

                    solicitudAfiliado.setSolicitudVO(solicitudVO);

                }else{
                    System.out.println("5");
                    return solicitudAfiliado;
                }

                // 03 - Consulta por ESTADOS DE SOLICITUDES DEL AFILIADO
                solicitudAfiliado.setResultado("El afiliado tiene una solicitud de desafiliación en curso con vigencia a partir del XX/XX/XXXX");
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("modafiliadoNS.obtenerEstadoAfiliadoRSolicitud",parametros);

                if(datos != null && datos.size() >0 ){

                    solicitudVOAUX = (SolicitudVO)datos.get(0);
                    fecha = solicitudVOAUX.getFechaVigencia();
                    date = sdf1.parse(fecha);
                    solicitudVO.setFechaVigencia(sdf2.format(date));

                    solicitudAfiliado.setSolicitudVO(solicitudVO);


                }
                /*else{
					return solicitudAfiliado;
				}*/

                // 04 - Consulta por Grupo Familiar
                solicitudAfiliado.setResultado("Ocurrió un error al consultar Grupo Familiar por el RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoRGrupoFamiliar",parametros);

                if(datos != null && datos.size() >0 ){

                    grupoFamiliarVO = (GrupoFamiliarVO)datos.get(0);
                    solicitudAfiliado.setGrupoFamiliarVO(grupoFamiliarVO);


                }else{
                    System.out.println("6");
                    return solicitudAfiliado;
                }

                // 05 - Consulta por Ingreso Economico
                solicitudAfiliado.setResultado("Ocurrió un error al consultar Ingreso Economico por el RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoRIngresoEconomico",parametros);

                if(datos != null && datos.size() >0 ){

                    ingresoEconomicoVO = (IngresoEconomicoVO)datos.get(0);
                    if(ingresoEconomicoVO.getFecUltCotizacion()!=null){
                        Date fecUltCotizacion=sdf3.parse(ingresoEconomicoVO.getFecUltCotizacion());					
                        ingresoEconomicoVO.setFecUltCotizacion(sdf2.format(fecUltCotizacion));
                    }
                    solicitudAfiliado.setIngresoEconomicoVO(ingresoEconomicoVO);

                }else{
                    System.out.println("7");
                    return solicitudAfiliado;
                }

                // 06 - Consulta por Telefono Particular
                solicitudAfiliado.setResultado("Ocurrió un error al consultar Telefono Particular por el RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoRTelefonoParticular",parametros);

                if(datos != null && datos.size() >0 ){

                    telefonoVO = (TelefonoVO)datos.get(0);
                    solicitudAfiliado.setTelefonoPartVO(telefonoVO);

                }else{
                    System.out.println("8");
                    return solicitudAfiliado;
                }

                // 07 - Consulta por Celular Particular
                solicitudAfiliado.setResultado("Ocurrió un error al consultar Celular Particular por el RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoRCelularParticular",parametros);

                if(datos != null && datos.size() >0 ){

                    telefonoVO = (TelefonoVO)datos.get(0);
                    solicitudAfiliado.setTelefonoCeluVO(telefonoVO);

                }else{
                    System.out.println("9");
                    return solicitudAfiliado;
                }

                // 08 - Consulta por Telefono Comercial
                solicitudAfiliado.setResultado("Ocurrió un error al consultar Telefono Comercial por el RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoRTelefonoComercial",parametros);

                if(datos != null && datos.size() >0 ){

                    telefonoVO = (TelefonoVO)datos.get(0);
                    solicitudAfiliado.setTelefonoComerVO(telefonoVO);

                }else{
                    System.out.println("10");
                    return solicitudAfiliado;
                }

                // 09 - Consulta por EMail
                solicitudAfiliado.setResultado("Ocurrió un error al consultar Email Particular por el RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                parametros.put("input_localidad","1");				
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoREmail",parametros);

                if(datos != null && datos.size() >0 ){
                    Iterator iter=datos.iterator();
                    while(iter.hasNext()){
                        emailVO=new EmailVO();
                        emailVO = (EmailVO)iter.next();					
                        if(emailVO.getTipoLocalidad()==1){
                            solicitudAfiliado.setEmailVO(emailVO);
                            break;
                        }else if(emailVO.getTipoLocalidad()==0){
                            solicitudAfiliado.setEmailVO(emailVO);
                            break;
                        }						
                    }					
                }else if(datos == null){
                    emailVO=new EmailVO();
                    emailVO.setDireccMail("");
                    emailVO.setIdSecuenciaEmail(0);
                    emailVO.setTipoLocalidad(0);
                    emailVO.setTipoPrincipalidad(0);
                    solicitudAfiliado.setEmailVO(emailVO);
                    //return solicitudAfiliado;
                }
                // 09.1 - Consulta por EMail Comercial
                solicitudAfiliado.setResultado("Ocurrió un error al consultar Email Comercial por el RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                parametros.put("input_localidad","2");				
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoREmail",parametros);

                if(datos != null && datos.size() >0 ){

                    Iterator iter=datos.iterator();
                    while(iter.hasNext()){
                        emailComercialVO=new EmailVO();
                        emailComercialVO = (EmailVO)iter.next();					
                        if(emailComercialVO.getTipoLocalidad()==2){
                            solicitudAfiliado.setEmailComerVO(emailVO);
                            break;
                        }else if(emailComercialVO.getTipoLocalidad()==0){
                            solicitudAfiliado.setEmailComerVO(emailVO);
                            break;
                        }						
                    }					
                    /*					

					emailComercialVO = (EmailVO)datos.get(0);
					solicitudAfiliado.setEmailComerVO(emailComercialVO);	*/								
                }else if(datos == null){
                    System.out.println("11");
                    return solicitudAfiliado;
                }				
                // 10 - Consulta por Direccion Particular
                solicitudAfiliado.setResultado("Ocurrió un error al consultar Direccion Particular por el RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoRDireccionParticular",parametros);

                if(datos != null && datos.size() >0 ){

                    direccionVO = (DireccionVO)datos.get(0);
                    solicitudAfiliado.setDireccionPartVO(direccionVO);

                }else{
                    System.out.println("12");
                    return solicitudAfiliado;
                }

                // 11 - Consulta por Direccion Comercial
                solicitudAfiliado.setResultado("Ocurrió un error al consultar Direccion Comercial por el RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoRDireccionComercial",parametros);

                if(datos != null && datos.size() >0 ){

                    direccionVO = (DireccionVO)datos.get(0);
                    solicitudAfiliado.setDireccionComerVO(direccionVO);

                }else{
                    System.out.println("13");
                    return solicitudAfiliado;
                }

                //12 - consulta por analista.
                solicitudAfiliado.setResultado("Ocurrió un error al consultar por el analista.");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoRAnalista",parametros);

                if(datos != null && datos.size() > 0){

                    analistaVO = (AnalistaVO)datos.get(0);
                    analistaVO.setDesOficina(Helper.obtenerDescripcion(listaParam.getListOficina(), analistaVO.getOficina()));
                    solicitudAfiliado.setAnalistaVO(analistaVO);

                }else{
                    System.out.println("14");
                    return solicitudAfiliado;
                }
                //Fin consulta analista.

                //14 - Consulta por EMail Comercial
                solicitudAfiliado.setResultado("Ocurrió un error al consultar Email por el RUT");

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                parametros.put("input_localidad","2");
                datos = sqlMap.queryForList("modafiliadoNS.obtenerAfiliadoREmail",parametros);

                if(datos != null && datos.size() >0 ){

                    emailVO = (EmailVO)datos.get(0);
                    solicitudAfiliado.setEmailComerVO(emailVO);

                }else if(datos == null){
                    System.out.println("15");
                    return solicitudAfiliado;
                }		
                //Fin 14 - Consulta por EMail Comercial				
                //Fin REQ5348
                solicitudAfiliado.setResultado("");
                solicitudAfiliado.setCodResultado(0);

                break;

            default:

                solicitudAfiliado.setResultado("Error en data. Existe mas de una solicitud asociada al RUT");
            solicitudAfiliado.setCodResultado(98);

            }
            System.out.println("16");
            return solicitudAfiliado;

        } catch(SQLException e) {

            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 

                e.printStackTrace(); }
        }	
        System.out.println("17");	
        return solicitudAfiliado;

    }

    /*-------------------------------------------------------------------*/
    /* Función que modifica el estado de un afiliado 
     * Recibe como entrada el rut del afiliado y el tipo de estado al que se desea cambiar.*/

    public static int updateEstadoAfiliado(String rut, String estado){

        /* Retornos
			0 = Ok
			99 = Error Desconocido de BD
         */

        ModAfiliadoForm form = new ModAfiliadoForm();

        form.setTxt_Rut(rut);
        form.setDbx_EstSolicitud(estado);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", form);
            sqlMap.update("modafiliadoNS.updateEstadoAfiliado", parametros);

            sqlMap.commitTransaction();

            // Se llama al método que inserta usuarios en LDAP
            callInsertaUserLDAP(rut, estado);

            return 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace(); }

        }	

        return 99;
    }

    /* Función modificar datos del afiliado
     * Recibe como entrada la cadena que contiene los datos que serán modificados
     * Realiza un update a las tablas con los datos modificados, por medio del traspaso de la cadena
     * 	al DAO (modAfiliadoDAO) que es donde se redistribuyen los campos a los onjetos correspondientes.*/
    public static int updateAfiliado(String cadenaForm){

        /* Retornos
         * 0 OK.
         * 99 Error desconocido de BD.
         */
        String fecha = "";
        Date date = new Date();

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
            //formAfiliado = (ModAfiliadoForm)form;

            //01.- update a la tabla persona.
            PersonaVO personaVO = new PersonaVO();

            personaVO.setIdPersona(Long.parseLong(datos[53-1]));
            personaVO.setApellidoPaterno(datos[9-1]);
            personaVO.setApellidoMaterno(datos[10-1]);
            personaVO.setNombres(datos[11-1]);

            fecha = datos[13-1];
            date = sdf1.parse(fecha);

            personaVO.setFechaNacimientoDate(date);
            personaVO.setFechaNacimiento(fecha);

            personaVO.setTipoSexo(Integer.parseInt(datos[14-1]));
            personaVO.setTipoNacionalidad(Integer.parseInt(datos[12-1]));

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", personaVO);
            sqlMap.update("modafiliadoNS.updateAfiliadoPersona", parametros);

            //02.- update a la tabla solicitud
            /*sqlMap.update("modafiliadoNS.updateAfiliadoSolicitud", formAfiliado);*/

            //03.- update a la tabla Telefono para telefono celular
            TelefonoVO telefonoCelu = new TelefonoVO(); 

            telefonoCelu.setIdSecuenciaTelefono(Long.parseLong(datos[58-1]));
            telefonoCelu.setCodArea(datos[16-1]);
            telefonoCelu.setNroTelefono(datos[17-1]);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", telefonoCelu);
            sqlMap.update("modafiliadoNS.updateAfiliadoTelefonoCelular", parametros);

            //04.- update a la tabla Telefono para telefono de contacto
            TelefonoVO telefonoPart = new TelefonoVO(); 

            telefonoPart.setIdSecuenciaTelefono(Long.parseLong(datos[57-1]));
            telefonoPart.setCodArea(datos[18-1]);
            telefonoPart.setNroTelefono(datos[19-1]);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", telefonoPart);
            sqlMap.update("modafiliadoNS.updateAfiliadoTelefonoParticular", parametros);

            //05.- update a la tabla Direccion para direccion particular
            DireccionVO direccionPart = new DireccionVO();

            direccionPart.setIdSecuenciaDireccion(Long.parseLong(datos[60-1]));
            direccionPart.setGlosCalle(datos[21-1]);
            direccionPart.setNumDireccion(datos[22-1]);
            direccionPart.setPoblacionVilla(datos[23-1]);
            direccionPart.setDpto(datos[24-1]);
            direccionPart.setRegion(Integer.parseInt(datos[25-1]));
            direccionPart.setCiudad(Integer.parseInt(datos[26-1]));
            direccionPart.setComuna(Integer.parseInt(datos[27-1]));

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", direccionPart);
            sqlMap.update("modafiliadoNS.updateAfiliadoDireccionParticular", parametros);			

            //06.- update a la tabla Email
            EmailVO emailVO = new EmailVO();

            emailVO.setIdSecuenciaEmail(Long.parseLong(datos[59-1]));
            emailVO.setDireccMail(datos[20-1]);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", emailVO);
            parametros.put("input_localidad", "1");			
            sqlMap.update("modafiliadoNS.updateAfiliadoEmail", parametros);

            //06.1- update a la tabla Email Comercial
            EmailVO emailComercialVO = new EmailVO();	
            EntidadRelacionVO entidadRelacion = new EntidadRelacionVO();
            if(!"".equals(datos[64-1].trim()) && !"".equals(datos[63-1].trim())){
                emailComercialVO.setDireccMail(datos[63-1]);				
                emailComercialVO.setIdSecuenciaEmail(Long.parseLong(datos[64-1]));				
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", emailComercialVO);
                parametros.put("input_localidad", "2");				
                sqlMap.update("modafiliadoNS.updateAfiliadoEmail", parametros);		
            }else if("".equals(datos[64-1].trim()) && !"".equals(datos[63-1].trim())){//				
                /*REQ5348 Insertar datos a la tabla Email Comercial*/				
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                List datosMail = sqlMap.queryForList("newsolicitudNS.selectIdEmail", parametros);				
                if(datosMail != null && datosMail.size() >0 ){
                    //seteo de id`s
                    long idEmail = Long.parseLong((String)datosMail.get(0));
                    emailComercialVO.setIdSecuenciaEmail(idEmail);
                    emailComercialVO.setTipoLocalidad(2);//IDENTIFICADOR PARA CORREO COMERCIAL
                    emailComercialVO.setDireccMail(datos[63-1]);
                    entidadRelacion.setIdSecuencia(idEmail);
                    entidadRelacion.setIdEntidad(personaVO.getIdPersona());
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", entidadRelacion);
                    sqlMap.insert("newsolicitudNS.insertNewSolIdEmailEnt", parametros);						
                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", emailComercialVO);
                    sqlMap.insert("newsolicitudNS.insertNewSolEmail", parametros);		
                }						
            }
            //07.- update a la tabla Direccion para direccion comercial
            DireccionVO direccionComerVO = new DireccionVO();

            direccionComerVO.setIdSecuenciaDireccion(Long.parseLong(datos[61-1]));
            direccionComerVO.setGlosCalle(datos[41-1]);
            direccionComerVO.setNumDireccion(datos[42-1]);
            direccionComerVO.setPoblacionVilla(datos[43-1]);
            direccionComerVO.setDpto(datos[44-1]);
            direccionComerVO.setRegion(Integer.parseInt(datos[47-1]));
            direccionComerVO.setCiudad(Integer.parseInt(datos[48-1]));
            direccionComerVO.setComuna(Integer.parseInt(datos[49-1]));

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", direccionComerVO);
            sqlMap.update("modafiliadoNS.updateAfiliadoDireccionComercial", parametros);

            //08.- update a la tabla Telefono para telefono comercial
            TelefonoVO telefonoComerVO = new TelefonoVO(); 

            telefonoComerVO.setIdSecuenciaTelefono(Long.parseLong(datos[62-1]));
            telefonoComerVO.setCodArea(datos[45-1]);
            telefonoComerVO.setNroTelefono(datos[46-1]);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", telefonoComerVO);
            sqlMap.update("modafiliadoNS.updateAfiliadoTelefonoComercial", parametros);

            //09.- update a la tabla GrupoFamiliar
            GrupoFamiliarVO grupoFamiliarVO = new GrupoFamiliarVO();

            grupoFamiliarVO.setIdGrupoFam(Long.parseLong(datos[55-1]));
            grupoFamiliarVO.setConyugue(Integer.parseInt(datos[37-1]));
            grupoFamiliarVO.setCantHijos(Integer.parseInt(datos[38-1]));

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", grupoFamiliarVO);
            sqlMap.update("modafiliadoNS.updateAfiliadoGrupoFamiliar", parametros);

            //10.- update a la tabla IngresoEconomico
            IngresoEconomicoVO ingresoEconomicoVO = new IngresoEconomicoVO();

            ingresoEconomicoVO.setIdIngEconom(Long.parseLong(datos[56-1]));
            ingresoEconomicoVO.setActEconom(datos[39-1]);
            ingresoEconomicoVO.setRentaImponible(Long.parseLong(datos[50-1]));
            ingresoEconomicoVO.setRentaCotizada(Long.parseLong(datos[51-1]));
            ingresoEconomicoVO.setHonorario(Integer.parseInt(datos[40-1]));
            ingresoEconomicoVO.setMontoUltimaCotizacion(Long.parseLong(datos[65-1]));
            ingresoEconomicoVO.setFecUltCotizacion(datos[66-1]);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", ingresoEconomicoVO);
            sqlMap.update("modafiliadoNS.updateAfiliadoIngresoEconomico", parametros);

            //11.- update a la tabla Afiliado
            AfiliadoVO afiliadoVO = new AfiliadoVO();

            afiliadoVO.setIdPersonaAfiliado(Long.parseLong(datos[53-1]));
            afiliadoVO.setTipoProfesion(Integer.parseInt(datos[29-1]));
            afiliadoVO.setTipoNivelEduc(Integer.parseInt(datos[28-1]));
            afiliadoVO.setTipoRegSalud(Integer.parseInt(datos[36-1]));
            afiliadoVO.setTipoAfp(Integer.parseInt(datos[35-1]));
            afiliadoVO.setTipoEstado(Integer.parseInt(datos[15-1]));
            afiliadoVO.setMontoCotizar(Integer.parseInt(datos[52-1]));
            afiliadoVO.setIdSecuenciaAgrupacion(Long.parseLong(datos[67-1]));

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", afiliadoVO);
            sqlMap.update("modafiliadoNS.updateAfiliadoAfiliado", parametros);

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
            datos = sqlMap.queryForList("modafiliadoNS.getEstadosDestinoPosibles", parametros);

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

    public static Retorno callInsertaUserLDAP(String rut, String estado){

        List data = null;
        Retorno ret = new Retorno();
        int idLogInd = 0;
        int flag = -1;

        String codError = "0";
        String desError = "";

        GlobalProperties global = GlobalProperties.getInstance();
        String ambiente = global.getValorExterno("IND.properties.ambiente");

        if(ambiente.equals(IND_Constants.Ambiente_Desa) || !estado.equals(IND_Constants.str_est_vigente))
        {
            return ret;
        }

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Ejecutamos el Procedimiento Almacenado
            HashMap parametros = new HashMap();
            parametros.put("P_IDDOCUMENTO", new Integer(rut));
            parametros.put("P_ERROR", new String());
            parametros.put("P_GLOSAERROR", new String());
            sqlMap.queryForObject("modafiliadoNS.SP_LOGINDP",parametros);

            codError = ((String)parametros.get("P_ERROR")).trim();
            desError = (String)parametros.get("P_GLOSAERROR");

            //Obtenemos el nuevo ID para LOGIND
            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            data = sqlMap.queryForList("modafiliadoNS.selectIdLogInd", parametros);

            if(data != null && data.size() > 0){

                idLogInd = Integer.parseInt(((String)data.get(0)));
            }

            if(codError.equals("0")){
                flag = 0;
            }else{
                flag = 1;
            }

            //Inserta un registro en LOGIND
            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("idlogind", new Integer(idLogInd));
            parametros.put("flag", new Integer(flag));
            parametros.put("iddocumento", new Integer(rut));
            sqlMap.insert("modafiliadoNS.insertLogInd", parametros);

            ret.setCodError(Integer.parseInt(codError));
            ret.setDesError(desError);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }
        }
        return ret;
    }

}