package cl.araucana.independientes.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer; 

import cl.araucana.independientes.config.ConfiguracionSqlMap;
import cl.araucana.independientes.helper.ConsumidorCobol;
import cl.araucana.independientes.helper.GlobalProperties;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.intercaja.cobol.bo.ParametrosConexionBO;
import cl.araucana.independientes.intercaja.cobol.bo.ParametrosLlamadaBO;
import cl.araucana.independientes.vo.AfiliadoBeneficiosVO;
import cl.araucana.independientes.vo.BeneficioVO;
import cl.araucana.independientes.vo.DocBeneficioVO;
import cl.araucana.independientes.vo.PersonaVO;
import cl.araucana.independientes.vo.RetornoIngresoBeneficio;
import cl.araucana.independientes.vo.param.Retorno;

import com.ibatis.sqlmap.client.SqlMapClient;

public class SolBeneficiosDAO {

    public static AfiliadoBeneficiosVO obtenerDatosAfiliado(String rut){

        AfiliadoBeneficiosVO beneficio = new AfiliadoBeneficiosVO();

        HashMap parametros = new HashMap();
        List datos = null;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try
        {
            sqlMap.startTransaction(0);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", rut);
            datos = sqlMap.queryForList("solBeneficiosNS.obtenerAfiliadoBeneficios", parametros);

            if(datos != null && datos.size() >0 )
            {
                beneficio = (AfiliadoBeneficiosVO)datos.get(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }
        }

        return beneficio;
    }

    //JLGN 13-02-2012
    //public static int obtenerEstadoPagoAporte(String rut, String ultimoDiaMes){
    public static int obtenerEstadoPagoAporte(String rut, String ultimoDiaMes, String ultimoDiaMesAnterior){	

        Integer respuesta = new Integer(-1);

        HashMap parametros = new HashMap();
        List datos = null;

        Date fechaUDM = new Date();

        //JLGN 13-02-2012
        Date fechaUDMA = new Date();
        HashMap parametros2 = new HashMap();
        List datos2 = null;
        Integer respuesta2 = new Integer(-1);

        String DATE_FORMAT1 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);

        try{				
            fechaUDM = sdf1.parse(ultimoDiaMes);
            //JLGN 13-02-2012
            fechaUDMA = sdf1.parse(ultimoDiaMesAnterior);	
        }catch (ParseException e) {
            e.printStackTrace();
        }

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try
        {
            sqlMap.startTransaction(0);

            //Se obtiene estado del periodo del mes anterior
            parametros2 = new HashMap();
            parametros2.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros2.put("input", rut);
            parametros2.put("ultimoDMA", fechaUDMA);
            datos2 = sqlMap.queryForList("solBeneficiosNS.obtenerEstadoPagoAporteAnterior", parametros2);

            if(datos2 != null && datos2.size() >0 )
            {
                if (datos2.get(0) != null)
                {
                    respuesta2 = (Integer)datos2.get(0);
                }
                else
                {
                    respuesta2 = new Integer(-1);
                }
            }			

            System.out.println("obtenerEstadoPagoAporteAnterior: " + respuesta2.intValue() + " ultimoDM: "+ fechaUDM);

            if(respuesta2.intValue() != 2){
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", rut);
                parametros.put("ultimoDM", fechaUDM);
                datos = sqlMap.queryForList("solBeneficiosNS.obtenerEstadoPagoAporte", parametros);

                if(datos != null && datos.size() >0 )
                {
                    if (datos.get(0) != null)
                    {
                        respuesta = (Integer)datos.get(0);
                    }
                    else
                    {
                        respuesta = new Integer(-1);
                    }
                }
            }else{
                respuesta = respuesta2;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }
        }
        System.out.println("obtenerEstadoPagoAporte: " + respuesta.intValue());
        return respuesta.intValue();
    }

    public static long obtenerNumeroRecurrencia(int id, long idAfi, String fechaSistema)
    {
        List datos = null;
        Integer salida = new Integer(0);
        String anioActual = fechaSistema.substring(6, 10);
        String iniAnio = "01/01/" + anioActual; 
        String finAnio = "12/31/" + anioActual; 

        Date dateIniAnio = new Date();
        Date dateFinAnio = new Date();

        String DATE_FORMAT1 = "MM/dd/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);

        try{				
            dateIniAnio = sdf1.parse(iniAnio);
            dateFinAnio = sdf1.parse(finAnio);

        }catch (ParseException e) {
            e.printStackTrace();
        }

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener el conteo
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("idBeneficio", new Integer(id));
            parametros.put("idAfi", new Long(idAfi));
            parametros.put("iniAnio", dateIniAnio);
            parametros.put("finAnio", dateFinAnio);
            datos = sqlMap.queryForList("solBeneficiosNS.obtenerContRecurrencia", parametros);

            //verifica que la consultra traiga los datos requeridos
            if(datos != null && datos.size() > 0){
                //Se guarda el resultado de la consulta en la variable salida
                salida = ((Integer)datos.get(0));
            }
            return salida.intValue();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }
        }
        return salida.intValue();
    }

    public static long obtenerNumeroCausanteUnico(int id, long idAfi, long rutCausante){

        List datos = null;
        Integer salida = new Integer(0);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener el conteo
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("idBeneficio", new Integer(id));
            parametros.put("idAfi", new Long(idAfi));
            parametros.put("rutCausante", new Long(rutCausante));
            datos = sqlMap.queryForList("solBeneficiosNS.obtenerContCausanteUnico", parametros);

            //verifica que la consultra traiga los datos requeridos
            if(datos != null && datos.size() > 0){
                //Se guarda el resultado de la consulta en la variable salida
                salida = ((Integer)datos.get(0));
            }
            return salida.intValue();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }
        }
        return salida.intValue();
    }

    public static Retorno registraBeneficios(String cadenaBeneficios, String folio)
    {
        Retorno ret = new Retorno();
        List datos = null;
        long idBeneficioAfiliado = 0;
        long idDocBenAfi = 0;
        String documentos = "";

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        String DATE_FORMAT1 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);

        StringTokenizer tokensLista = new StringTokenizer(cadenaBeneficios, "#");
        int nDatosLista = tokensLista.countTokens();
        String[] datosLista = new String[nDatosLista];
        int i = 0;

        String fechaSistema = ParametrosDAO.obtenerFechaSistema();

        while(tokensLista.hasMoreTokens()){
            String str = tokensLista.nextToken();
            datosLista[i]= str;
            i++;
        }

        System.out.println("Inicio registraBeneficios");

        for(int j = 0; j < datosLista.length; j ++){

            System.out.println("j = "+ j);

            StringTokenizer tokensBen = new StringTokenizer(datosLista[j], "|");
            int nDatosBen = tokensBen.countTokens();
            String[] datosBen = new String[nDatosBen];
            int k = 0;

            while(tokensBen.hasMoreTokens()){
                String str = tokensBen.nextToken();
                datosBen[k]= str;
                k++;
            }

            try {

                sqlMap.startTransaction(0);

                HashMap parametros = new HashMap();

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                datos = sqlMap.queryForList("solBeneficiosNS.selectIdBeneficioAfiliado", parametros); 

                if(datos != null && datos.size() > 0){

                    idBeneficioAfiliado = Long.parseLong((String)datos.get(0));
                }else{

                    ret.setCodError(99);
                    ret.setDesError("Error al obtener el Id para el nuevo Beneficio");
                }

                //Inicio REQ6988 JLGN 11-03-2013
                StringTokenizer tokensRutTer = new StringTokenizer(datosBen[8-1], "%");
                int nDatosRut = tokensRutTer.countTokens();
                String[] datosRut = new String[nDatosRut];

                int x = 0;		        
                while(tokensRutTer.hasMoreTokens()){
                    String str = tokensRutTer.nextToken();
                    datosRut[x]= str;
                    x++;
                }
                //Fin REQ6988 JLGN 11-03-2013

                BeneficioVO beneficio = new BeneficioVO();

                beneficio.setIdBeneficioAfiliado(idBeneficioAfiliado);
                beneficio.setIdPersonaAfiliado(Long.parseLong(datosBen[1-1]));
                beneficio.setIdbeneficio(Integer.parseInt(datosBen[2-1]));
                beneficio.setFechaSolicitud(sdf1.parse(fechaSistema));
                beneficio.setEstado(1);
                beneficio.setNombreCausante(datosBen[3-1]);
                System.out.println("NombreCausante = "+ datosBen[3-1]);
                beneficio.setRutCausante(Long.parseLong(datosBen[4-1]));
                beneficio.setFechaCausante(sdf1.parse(datosBen[5-1]));
                beneficio.setCopago(Long.parseLong(datosBen[6-1]));
                beneficio.setMontoPagar(Long.parseLong(datosBen[7-1]));
                beneficio.setFechaPago(sdf1.parse(IND_Constants.Fec_Comodin));
                //REQ 6988 JLGN 11-03-2013
                //beneficio.setRutTercero(Long.parseLong(datosBen[8-1]));
                beneficio.setRutTercero(Long.parseLong(datosRut[0]));
                beneficio.setNombreTercero(datosBen[9-1]);
                beneficio.setIdAnalista(Long.parseLong(datosBen[10-1]));
                beneficio.setTipoComprobante(IND_Constants.ben_int_tipocomprobante_egreso);//Tipo comprobante egreso
                beneficio.setFolio(Long.parseLong(folio));
                beneficio.setFolioReversado(0);

                if (beneficio.getRutTercero() == -1){
                    beneficio.setEstado(2);
                }

                documentos = datosBen[11-1];

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", beneficio);
                sqlMap.insert("solBeneficiosNS.insertNewBeneficioAfiliado", parametros);

                StringTokenizer tokensDocs = new StringTokenizer(documentos, "$");
                int nDatosDocs = tokensDocs.countTokens();
                String[] datosDocs = new String[nDatosDocs];
                int l = 0;

                while(tokensDocs.hasMoreTokens()){
                    String str = tokensDocs.nextToken();
                    datosDocs[l]= str;

                    l++;
                }

                for(int m = 0; m < datosDocs.length; m ++){

                    StringTokenizer tokensDoc = new StringTokenizer(datosDocs[m], "%");
                    int nDatosDoc = tokensDoc.countTokens();
                    String[] datosDoc = new String[nDatosDoc];
                    int n = 0;

                    while(tokensDoc.hasMoreTokens()){
                        String str = tokensDoc.nextToken();
                        datosDoc[n]= str;
                        n++;
                    }

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    datos = sqlMap.queryForList("solBeneficiosNS.selectIdDocumentoBenAfi", parametros); 

                    if(datos != null && datos.size() > 0){

                        idDocBenAfi = Long.parseLong((String)datos.get(0));
                    }else{

                        ret.setCodError(98);
                        ret.setDesError("Error al obtener el Id para el nuevo Beneficio");
                    }

                    DocBeneficioVO documento = new DocBeneficioVO();

                    documento.setIdDocumentoBenAfi(idDocBenAfi);
                    documento.setIdBeneficioAfiliado(beneficio.getIdBeneficioAfiliado());
                    documento.setIdDocBeneficio(Integer.parseInt(datosDoc[1-1]));
                    documento.setEstadoDocBeneficio(Integer.parseInt(datosDoc[2-1]));

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", documento);
                    sqlMap.insert("solBeneficiosNS.insertNewDocumentoBenAfi", parametros);
                }

            } catch (SQLException e) {

                ret.setCodError(97);
                ret.setDesError("Error no manejado al ejecutar registraBeneficios()");
                e.printStackTrace();
            }
            catch (ParseException e) {

                ret.setCodError(96);
                ret.setDesError("Error de parseo al ejecutar registraBeneficios()");
                e.printStackTrace();
            }
            finally {

                try { sqlMap.endTransaction(); 
                }catch (SQLException e) { 

                    e.printStackTrace(); }

            }	
        }

        if (ret.getCodError()== 0){
            ret.setDesError("Beneficio ingresado con éxito. Nº Folio generado: " + folio);
        }        
        return ret;
    }

    public static RetornoIngresoBeneficio registraBeneficiosCobol(String cadenaBeneficios)
    {
        RetornoIngresoBeneficio ret = new RetornoIngresoBeneficio();

        /* Retornos //TODO Reordenar
			0 = Ok
			98 = Error al cerrar conexión
			99 = Error Desconocido de BD
         */

        String cadenaInput = "";
        long sumaMontos = 0;
        String nuevoFolio = ""; //TODO Retornar
        String oficina = "";
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        // INI Generacion de cadena Input

        //ListadoParametros listaParam = ListadoParametros.getInstancia();

        String DATE_FORMAT1 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        String DATE_FORMAT2 = "yyyyMMdd";
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        System.out.println("CADENA DE ENTRADA registraBeneficiosCobol");
        System.out.println("cadenaBeneficios: "+ cadenaBeneficios);		

        StringTokenizer tokensLista = new StringTokenizer(cadenaBeneficios, "#");
        int nDatosLista = tokensLista.countTokens();
        String[] datosLista = new String[nDatosLista];
        int i = 0;

        while(tokensLista.hasMoreTokens()){
            String str = tokensLista.nextToken();
            datosLista[i]= str;
            i++;
        }

        for(int j = 0; j < datosLista.length; j ++){

            StringTokenizer tokensBen = new StringTokenizer(datosLista[j], "|");
            int nDatosBen = tokensBen.countTokens();
            String[] datosBen = new String[nDatosBen];
            int k = 0;

            while(tokensBen.hasMoreTokens()){
                String str = tokensBen.nextToken();
                datosBen[k]= str;
                k++;
            }

            //INI Obtencion de oficina del Analista		
            try 
            {
                sqlMap.startTransaction(0);

                HashMap parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", datosBen[10-1]);
                datos = sqlMap.queryForList("solBeneficiosNS.obtenerOficinaAnalista", parametros);
                if(datos != null && datos.size() >0 )
                {
                    oficina = (String)datos.get(0);
                }
                else{
                    oficina = "000";
                }


            } catch (SQLException e) 
            {
                ret.setCodError(99);
                ret.setDesError("Error desconocido de BD al ejecutar del ingreso de beneficio en cobol");
                e.printStackTrace();
                return ret;
            }
            finally 
            {
                try { sqlMap.endTransaction(); } catch (SQLException e) 
                {
                    ret.setCodError(98);
                    ret.setDesError("Error al cerrar conexión durante la ejecución del ingreso de beneficio en cobol");
                    e.printStackTrace();
                    return ret;
                }
            }		
            // FIN Obtencion de oficina del Analista

            if(j == 0){

                try{

                    cadenaInput += sdf2.format(sdf1.parse(ParametrosDAO.obtenerFechaSistema())); //Fecha --> FECEMI COBOL

                }catch (ParseException e) {

                    ret.setCodError(97);
                    ret.setDesError("Error de parseo al ejecutar del ingreso de beneficio en cobol");
                    e.printStackTrace();
                }

                cadenaInput += ParametrosDAO.obtenerHoraSistema().replaceAll(":", ""); // Hora --> HOREMI COBOL
                cadenaInput += IND_Constants.ben_str_formapago_caja;; // Forma de Pago -- Caja --> FORPAG COBOL
                cadenaInput += IND_Constants.ben_str_tipopago_efectivo; // Tipo de pago -- Efectivo --> TIPPAG COBOL
                try {
                    cadenaInput += sdf2.format(sdf1.parse(ParametrosDAO.obtenerFechaSistema())); // Fecha disponibilidad de pago --> FECDIS COBOL
                } catch (ParseException e) {
                    ret.setCodError(97);
                    ret.setDesError("Error de parseo al ejecutar del ingreso de beneficio en cobol");
                    e.printStackTrace();
                }
                cadenaInput += IND_Constants.ben_str_emitefactura_no; // Emite factura -- No --> EMIFAC COBOL


                cadenaInput += Helper.paddingString(oficina, 3, '0', true); // sucursal --> SUCEMP COBOL
                cadenaInput += Helper.paddingString(oficina, 3, '0', true); // oficina usuario --> CODOFI COBOL

                //cadenaInput += Helper.paddingString(Long.toString(sumaMontos), 12, '0', true); // Monto Total, corrido hasta el final
                cadenaInput += Helper.paddingString(IND_Constants.ben_str_codigorea_ind, 3, '0', true); // Codigo de Área de negocio --> ARENEG COBOL

                //Obtenemos datos del Afiliado
                PersonaVO personaVO = obtenerDatosPersona(datosBen[1-1]);

                cadenaInput += Helper.paddingString(Long.toString(personaVO.getIdDocumento()), 9, '0', true); //RUT Afiliado  "001234936"; --> RUTPRO COBOL
                cadenaInput += personaVO.getDigVerificador(); //DV Afiliado "K"; --> DIGPRO COBOL

                String nombreCompleto = personaVO.getNombres() + " " + personaVO.getApellidoPaterno() + " " + personaVO.getApellidoMaterno();

                if(nombreCompleto.length() > 50){
                    nombreCompleto = nombreCompleto.substring(0, 50);
                }else{
                    nombreCompleto = Helper.paddingString(nombreCompleto, 50, ' ', false);
                }

                cadenaInput += nombreCompleto; //Nombre afiliado --> NOMPRO COBOL

                //Inicio REQ6988 JLGN 11-03-2013
                StringTokenizer tokensRutTer = new StringTokenizer(datosBen[8-1], "%");
                int nDatosRut = tokensRutTer.countTokens();
                String[] datosRut = new String[nDatosRut];

                int x = 0;		        
                while(tokensRutTer.hasMoreTokens()){
                    String str = tokensRutTer.nextToken();
                    datosRut[x]= str;
                    x++;
                }			

                if (!datosRut[0].trim().equals("0") &&  !datosRut[0].trim().equals("-1")){
                    cadenaInput += Helper.paddingString(datosRut[0], 9, '0', true); //RUT TERCERO COBOL
                    cadenaInput += datosRut[1]; //DIG TERCERO COBOL

                    String nombreCompletoTercero = datosBen[9-1];

                    if(nombreCompletoTercero.length() > 50){
                        nombreCompletoTercero = nombreCompletoTercero.substring(0, 50);
                    }else{
                        nombreCompletoTercero = Helper.paddingString(nombreCompletoTercero, 50, ' ', false);
                    }

                    cadenaInput += nombreCompletoTercero; //NOMBRE TERCERO COBOL
                }else{
                    cadenaInput += "000000000"; //RUT TERCERO
                    cadenaInput += "0";//DIG TERCERO COBOL
                    cadenaInput += "                                                  ";//NOMBRE TERCERO COBOL
                }
                //Fin REQ6988 JLGN 11-03-2013

                cadenaInput += Helper.paddingString(datosBen[10-1], 10, '0', true); //Usuario creacion --> USUCRE COBOL
                cadenaInput += Helper.paddingString(Integer.toString(datosLista.length), 3, '0', true); // Cantidad de Beneficios --> CANLIN COBOL
                //cadenaInput += "               "; //Codigo de Barras
            }

            // Sumatoria de montos
            sumaMontos = sumaMontos + Long.parseLong(datosBen[7-1]);

            cadenaInput += Helper.paddingString(Helper.obtenerDatoBeneficio(Integer.parseInt(datosBen[2-1]), 3), 6, '0', true); //Codigo concepto --> CODCON COBOL
            cadenaInput += "0"; // SIGMON COBOL
            cadenaInput += Helper.paddingString(datosBen[7-1], 12, '0', true); // MONDET COBOL
            cadenaInput += "                                                                           "; // OBSDET COBOL
        }

        //Proceso COBOL recibe un maximo de 16 conceptos, si se ingresaron menos de 16 conceptos, se deben 
        //llenar los espacios de los restos para poder mantener el largo del string enviado como parametro
        //de entrada

        for(int m = 0; m < 16 - datosLista.length; m++)
        {
            cadenaInput += "000000"; //Codigo concepto --> CODCON COBOL
            cadenaInput += "0"; // SIGMON COBOL
            cadenaInput += "000000000000"; // MONDET COBOL
            cadenaInput += "                                                                           ";// OBSDET COBOL
        }

        //Inserta la Sumatoria
        cadenaInput = cadenaInput.substring(0, 31) + Helper.paddingString(Long.toString(sumaMontos), 12, '0', true) + cadenaInput.substring(32-1);// monto informado --> MTOINF COBOL

        //System.out.println (cadenaInput);
        // FIN Generacion de cadena Input

        // INI Llamada Cobol

        ParametrosConexionBO conexion = new ParametrosConexionBO();
        //ParametrosLlamadaBO[] llamada = new ParametrosLlamadaBO[3];
        ParametrosLlamadaBO[] llamada = new ParametrosLlamadaBO[4];

        GlobalProperties global = GlobalProperties.getInstance();

        String ipServer = global.getValorExterno("IND.conexion.cobol.beneficios.generar.IP");
        String usuarioConexion = global.getValorExterno("IND.conexion.cobol.beneficios.generar.user");
        String claveConexion = global.getValorExterno("IND.conexion.cobol.beneficios.generar.pass");
        String programa =  global.getValorExterno("IND.conexion.cobol.beneficios.generar.prog");

        System.out.println("-- Inicio Parametros de Conexion COBOL --");
        System.out.println("ipServer: "+ ipServer);
        System.out.println("usuarioConexion: "+ usuarioConexion);
        System.out.println("claveConexion: "+ claveConexion);
        System.out.println("programa: "+ programa);
        System.out.println("cadenaInput: "+ cadenaInput);
        System.out.println("-- Fin Parametros de Conexion COBOL --");

        conexion.setIpServer(ipServer);
        conexion.setUsuarioConexion(usuarioConexion);
        conexion.setClaveConexion(claveConexion);
        conexion.setPrograma(programa);

        ParametrosLlamadaBO par = new ParametrosLlamadaBO();
        par.setTipo("STRING");
        //REQ6988 JLGN 11-03-2013
        //par.setLargo(1623);
        par.setLargo(1683);
        par.setValor(cadenaInput);
        par.setDireccion("IN");
        llamada[0] = par;

        par = new ParametrosLlamadaBO();
        par.setTipo("STRING");
        par.setLargo(3);
        par.setValor("000");
        par.setDireccion("OUT");
        llamada[1] = par;

        par = new ParametrosLlamadaBO();
        par.setTipo("STRING");
        par.setLargo(10);
        par.setValor("0000000000");
        par.setDireccion("OUT");
        llamada[2] = par;

        //Inicio Indica en que parametro ocurre el error
        par = new ParametrosLlamadaBO();
        par.setTipo("STRING");
        par.setLargo(200);
        par.setValor("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        par.setDireccion("OUT");
        llamada[3] = par;
        //Fin 

        ParametrosLlamadaBO[] salida = ConsumidorCobol.call(conexion, llamada);

        String flag = (String)salida[1].getValor();
        String flag1 = flag.substring(0, 1);
        String flag2 = flag.substring(1, 2); 
        nuevoFolio = (String)salida[2].getValor();

        //Descripcion de error en la salida
        String mensajeError = (String)salida[3].getValor();

        System.out.println("-- Inicio Parametros de Salida COBOL --");
        System.out.println("flag: "+ flag);
        System.out.println("flag1: "+ flag1);
        System.out.println("flag2: "+ flag2);
        System.out.println("nuevoFolio: "+ nuevoFolio);
        System.out.println("mensajeError: "+ mensajeError);
        System.out.println("-- Fin Parametros de Salida COBOL --");

        // FIN Llamada Cobol

        if(!flag1.equals("0"))
        {
            ret.setCodError(1);
            ret.setDesError("Los parámetros de entrada son Erroneos");
        }else if(!flag2.equals("0"))
        {
            ret.setCodError(2);
            ret.setDesError("Error al ejecutar Ingreso de Beneficio");
        }else{
            ret.setFolio(nuevoFolio);
            ret.setCodError(0);
            ret.setDesError("Beneficio ingresado con éxito. Nº Folio generado: " + nuevoFolio);
        }

        return ret;
    }

    public static PersonaVO obtenerDatosPersona(String idPersona)
    {
        HashMap parametros = new HashMap();
        List datos = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        PersonaVO personaVO = new PersonaVO();

        try{

            sqlMap.startTransaction(0);

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", idPersona);
            datos = sqlMap.queryForList("solBeneficiosNS.obtenerDatosPersona",parametros);

        } catch(SQLException e) {

            e.printStackTrace();
        }finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 

                e.printStackTrace(); }
        }

        if(datos != null && datos.size() >0 ){

            return personaVO = (PersonaVO)datos.get(0);

        }else{

            return personaVO;
        }
    }
}	