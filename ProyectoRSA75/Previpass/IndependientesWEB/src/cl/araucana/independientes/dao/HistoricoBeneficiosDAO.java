package cl.araucana.independientes.dao;

//import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import cl.araucana.independientes.config.ConfiguracionSqlMap;
import cl.araucana.independientes.helper.ConsumidorCobol;
import cl.araucana.independientes.helper.GlobalProperties;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.intercaja.cobol.bo.ParametrosConexionBO;
import cl.araucana.independientes.intercaja.cobol.bo.ParametrosLlamadaBO;
import cl.araucana.independientes.vo.BeneficioVO;
import cl.araucana.independientes.vo.PersonaVO;
import cl.araucana.independientes.vo.param.Retorno;

import com.ibatis.sqlmap.client.SqlMapClient;

public class HistoricoBeneficiosDAO {

    public static BeneficioVO[] consultaHistoricoBeneficios(String rut) 
    {
        List datos = null;
        BeneficioVO[] salida = new BeneficioVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener losbeneficios del afiliado.
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", rut);
            datos = sqlMap.queryForList("solBeneficiosNS.obtenerHistoricoBeneficios", parametros);
            return (BeneficioVO[]) datos.toArray(new BeneficioVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    public static Retorno anularBeneficioCobol(long folio)
    {
        Retorno ret = new Retorno();

        ParametrosConexionBO conexion = new ParametrosConexionBO();
        ParametrosLlamadaBO[] llamada = new ParametrosLlamadaBO[3];

        String cadenaInput = "";
        cadenaInput += Helper.paddingString(Long.toString(folio), 10, '0', true); // Monto

        GlobalProperties global = GlobalProperties.getInstance();

        String ipServer = global.getValorExterno("IND.conexion.cobol.beneficios.anular.IP");
        String usuarioConexion = global.getValorExterno("IND.conexion.cobol.beneficios.anular.user");
        String claveConexion = global.getValorExterno("IND.conexion.cobol.beneficios.anular.pass");
        String programa = global.getValorExterno("IND.conexion.cobol.beneficios.anular.prog");

        conexion.setIpServer(ipServer);
        conexion.setUsuarioConexion(usuarioConexion);
        conexion.setClaveConexion(claveConexion);
        conexion.setPrograma(programa);

        try{

            ParametrosLlamadaBO par = new ParametrosLlamadaBO();
            par.setTipo("STRING");
            par.setLargo(10);
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
            par.setLargo(1);
            par.setValor("0");
            par.setDireccion("OUT");
            llamada[2] = par;			

            ParametrosLlamadaBO[] salida = ConsumidorCobol.call(conexion, llamada);

            String flag = (String)salida[1].getValor();
            String flag1 = flag.substring(0, 1); 
            String flag2 = flag.substring(1, 2); 

            if(!flag1.equals("0"))
            {
                ret.setCodError(1);
                ret.setDesError("Los parámetros de entrada son Erroneos");
            }else if(!flag2.equals("0"))
            {
                ret.setCodError(2);
                ret.setDesError("Error al ejecutar Anulación");
            }else{
                ret.setCodError(0);
                ret.setDesError("Beneficio reversado con éxito");
            }
        }catch (Exception e){
            ret.setCodError(99);
            ret.setDesError("Error al llamar al servicio COBOl en anularBeneficioCobol()");
        }

        return ret;
    }

    public static Retorno anularBeneficio(long folio)
    {

        Retorno ret = new Retorno();

        /* Retornos
			0 = Ok
			98 = Error al cerrar conexión
			99 = Error Desconocido de BD
         */

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", Long.toString(folio));
            sqlMap.update("solBeneficiosNS.updateBeneficioAnula", parametros);

            sqlMap.commitTransaction();

        } catch (SQLException e) {

            ret.setCodError(99);
            ret.setDesError("Error desconocido de BD al ejecutar la anulación");

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                ret.setCodError(98);
                ret.setDesError("Error al cerrar conexión durante la ejecución de la Anulacion");

                e.printStackTrace(); }
        }

        return ret;
    }

    public static Retorno reversarBeneficioCobol(long folio)
    {
        Retorno ret = new Retorno();

        /* Retornos
			0 = Ok
			98 = Error al cerrar conexión
			99 = Error Desconocido de BD
         */

        //System.out.println("Entro a reversarBeneficioCobol()");

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        List datos = null;
        BeneficioVO[] beneficios = new BeneficioVO[0];

        String cadenaInput = "";
        long sumaMontos = 0;
        String nuevoFolio = "";
        long idBeneficioAfiliado = 0;
        String oficina = "";

        // INI Obtencion de beneficios a reversar

        try 
        {
            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los beneficios asociado al folio.
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", Long.toString(folio));
            datos = sqlMap.queryForList("solBeneficiosNS.obtenerBeneficiosPorFolio", parametros);
            beneficios = (BeneficioVO[]) datos.toArray(new BeneficioVO[datos.size()]);

        } catch (SQLException e) 
        {
            ret.setCodError(99);
            ret.setDesError("Error desconocido de BD al ejecutar la reversa en cobol");
            e.printStackTrace();
            return ret;
        }
        finally 
        {
            try { sqlMap.endTransaction(); } catch (SQLException e) 
            {
                ret.setCodError(98);
                ret.setDesError("Error al cerrar conexión durante la ejecución de la reversa en cobol");
                e.printStackTrace();
                return ret;
            }
        }

        // FIN Obtencion de beneficios a reversar

        // INI Obtencion de oficina del Analista		
        try 
        {
            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", Long.toString(beneficios[0].getIdAnalista()));
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
            ret.setDesError("Error desconocido de BD al ejecutar la reversa en cobol");
            e.printStackTrace();
            return ret;
        }
        finally 
        {
            try { sqlMap.endTransaction(); } catch (SQLException e) 
            {
                ret.setCodError(98);
                ret.setDesError("Error al cerrar conexión durante la ejecución de la reversa en cobol");
                e.printStackTrace();
                return ret;
            }
        }		
        // FIN Obtencion de oficina del Analista
        // INI Generacion de cadena Input

        //ListadoParametros listaParam = ListadoParametros.getInstancia();

        String DATE_FORMAT1 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        String DATE_FORMAT2 = "yyyyMMdd";
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);
        String fechaSistema = ParametrosDAO.obtenerFechaSistema();
        try{

            cadenaInput += sdf2.format(sdf1.parse(fechaSistema)); //Fecha

        }catch (ParseException e) {

            ret.setCodError(97);
            ret.setDesError("Error de parseo al ejecutar la reversa en cobol");
            e.printStackTrace();
        }

        cadenaInput += ParametrosDAO.obtenerHoraSistema().replaceAll(":", ""); // Hora
        cadenaInput += IND_Constants.ben_str_formapago_caja; // Forma de Pago --> Caja
        cadenaInput += IND_Constants.ben_str_tipopago_efectivo; // Tipo de pago --> Efectivo
        try{

            cadenaInput += sdf2.format(sdf1.parse(fechaSistema)); // Fecha disponibilidad de pago

        }catch (ParseException e) {

            ret.setCodError(97);
            ret.setDesError("Error de parseo al ejecutar la reversa en cobol");
            e.printStackTrace();
        }

        cadenaInput += IND_Constants.ben_str_emitefactura_no; // Emite factura --> No

        cadenaInput += Helper.paddingString(oficina, 3, '0', true);//TODO 
        cadenaInput += Helper.paddingString(oficina, 3, '0', true); // TODO oficina usuario

        //Sumatoria de montos
        for(int i = 0; i < beneficios.length; i++)
        {
            sumaMontos = sumaMontos + beneficios[i].getMontoPagar();
        }

        cadenaInput += Helper.paddingString(Long.toString(sumaMontos), 12, '0', true); // Monto
        cadenaInput += IND_Constants.ben_str_codigorea_ind; // Codigo de Área de negocio

        //Obtenemos datos del Afiliado
        PersonaVO personaVO = SolBeneficiosDAO.obtenerDatosPersona(Long.toString(beneficios[0].getIdPersonaAfiliado()));

        cadenaInput += Helper.paddingString(Long.toString(personaVO.getIdDocumento()), 9, '0', true); //RUT Afiliado 
        cadenaInput += personaVO.getDigVerificador(); //DV Afiliado

        String nombreCompleto = personaVO.getNombres() + " " + personaVO.getApellidoPaterno() + " " + personaVO.getApellidoMaterno();

        if(nombreCompleto.length() > 50){
            nombreCompleto = nombreCompleto.substring(0, 49);
        }else{
            nombreCompleto = Helper.paddingString(nombreCompleto, 50, ' ', false);
        }

        cadenaInput += nombreCompleto; //Nombre afiliado
        cadenaInput += Helper.paddingString(Long.toString(beneficios[0].getIdAnalista()), 10, '0', true);; //USR
        cadenaInput += Helper.paddingString(Integer.toString(beneficios.length), 3, '0', true); // Cantidad de Beneficios
        cadenaInput += "               "; //Codigo de Barras

        for(int j = 0; j < beneficios.length; j++)
        {
            cadenaInput += Helper.paddingString(Helper.obtenerDatoBeneficio(beneficios[j].getIdbeneficio(), 3), 6, '0', true); //Codigo concepto
            cadenaInput += "0";
            cadenaInput += Helper.paddingString(Long.toString(beneficios[j].getMontoPagar()), 12, '0', true);
            cadenaInput += "                                                                           ";
        }


        for(int k = 0; k < 16 - beneficios.length; k++)
        {
            cadenaInput += "000000";
            cadenaInput += "0";
            cadenaInput += "000000000000";
            cadenaInput += "                                                                           ";
        }

        // FIN Generacion de cadena Input

        // INI Llamada Cobol

        ParametrosConexionBO conexion = new ParametrosConexionBO();
        ParametrosLlamadaBO[] llamada = new ParametrosLlamadaBO[3];

        GlobalProperties global = GlobalProperties.getInstance();

        String ipServer = global.getValorExterno("IND.conexion.cobol.beneficios.reversar.IP");
        String usuarioConexion = global.getValorExterno("IND.conexion.cobol.beneficios.reversar.user");
        String claveConexion = global.getValorExterno("IND.conexion.cobol.beneficios.reversar.pass");
        String programa = global.getValorExterno("IND.conexion.cobol.beneficios.reversar.prog");

        conexion.setIpServer(ipServer);
        conexion.setUsuarioConexion(usuarioConexion);
        conexion.setClaveConexion(claveConexion);
        conexion.setPrograma(programa);

        ParametrosLlamadaBO par = new ParametrosLlamadaBO();
        par.setTipo("STRING");
        par.setLargo(1638);
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

        ParametrosLlamadaBO[] salida = ConsumidorCobol.call(conexion, llamada);

        String flag = (String)salida[1].getValor();
        String flag1 = flag.substring(0, 1); 
        String flag2 = flag.substring(1, 2); 
        nuevoFolio = (String)salida[2].getValor();
        // FIN Llamada Cobol

        if(!flag1.equals("0"))
        {
            ret.setCodError(1);
            ret.setDesError("Los parámetros de entrada son Erroneos");
        }else if(!flag2.equals("0"))
        {
            ret.setCodError(2);
            ret.setDesError("Error al ejecutar Anulación");
        }else{

            //INI Registro STI de Reversa
            for(int l = 0; l < beneficios.length; l++)
            {
                try{

                    sqlMap.startTransaction(0);

                    HashMap parametros = new HashMap();

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    datos = sqlMap.queryForList("solBeneficiosNS.selectIdBeneficioAfiliado", parametros); 

                    if(datos != null && datos.size() > 0){

                        idBeneficioAfiliado = Long.parseLong((String)datos.get(0));
                    }else
                    {
                        ret.setCodError(95);
                        ret.setDesError("Error al obtener el Id para el nuevo Beneficio");
                    }

                    BeneficioVO beneficio = new BeneficioVO();

                    beneficio.setIdBeneficioAfiliado(idBeneficioAfiliado);
                    beneficio.setIdPersonaAfiliado(beneficios[l].getIdPersonaAfiliado());
                    beneficio.setIdbeneficio(beneficios[l].getIdbeneficio());
                    try{

                        beneficio.setFechaSolicitud(sdf1.parse(fechaSistema)); //Fecha actual
                        beneficio.setFechaPago(sdf1.parse(IND_Constants.Fec_Comodin));

                    }catch (ParseException e) {

                        ret.setCodError(94);
                        ret.setDesError("Error de parseo al ejecutar reversarBeneficioCobol()");
                        e.printStackTrace();
                    }

                    beneficio.setEstado(1);
                    beneficio.setNombreCausante(beneficios[l].getNombreCausante());
                    beneficio.setRutCausante(beneficios[l].getRutCausante());
                    beneficio.setFechaCausante(beneficios[l].getFechaCausante());
                    beneficio.setCopago(beneficios[l].getCopago());
                    beneficio.setMontoPagar(beneficios[l].getMontoPagar());
                    beneficio.setRutTercero(0);
                    beneficio.setNombreTercero("");
                    beneficio.setIdAnalista(beneficios[l].getIdAnalista()); //Id Analista
                    beneficio.setTipoComprobante(IND_Constants.ben_int_tipocomprobante_ingreso);//Tipo comprobante 2
                    beneficio.setFolio(Long.parseLong(nuevoFolio));//Folio
                    beneficio.setFolioReversado(folio);//TODO que hay que hacer?

                    //documentos = datosBen[11-1];

                    parametros = new HashMap();
                    parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                    parametros.put("input", beneficio);

                    sqlMap.insert("solBeneficiosNS.insertNewBeneficioAfiliado", parametros);

                } catch (SQLException e) {

                    ret.setCodError(93);
                    ret.setDesError("Error no manejado al ejecutar registraBeneficios()");
                    e.printStackTrace();
                }
                finally 
                {
                    try { sqlMap.endTransaction(); } catch (SQLException e) 
                    {
                        ret.setCodError(98);
                        ret.setDesError("Error al cerrar conexión durante la ejecución de registraBeneficios()");
                        e.printStackTrace();
                        return ret;
                    }
                }
            }

            //FIN Registro STI de Reversa

            ret.setCodError(0);
            ret.setDesError("Beneficio reversado con éxito");
        } 
        return ret;
    }

    public static Retorno reversarBeneficio(long folio)
    {
        Retorno ret = new Retorno();

        /* Retornos
			0 = Ok
			98 = Error al cerrar conexión
			99 = Error Desconocido de BD
         */

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", Long.toString(folio));
            sqlMap.update("solBeneficiosNS.updateBeneficioReversa", parametros);

            sqlMap.commitTransaction();

        } catch (SQLException e) {

            ret.setCodError(99);
            ret.setDesError("Error desconocido de BD al ejecutar la reversa");

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                ret.setCodError(98);
                ret.setDesError("Error al cerrar conexión durante la ejecución de la reversa");

                e.printStackTrace(); }
        }

        return ret;
    }

    //Inicio REQ 6988 JLGN 11-02-2013
    public static BeneficioVO[] consultaBeneficiosTesoreria(String rut) 
    {
        List datos = null;
        BeneficioVO[] salida = new BeneficioVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {			
            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener losbeneficios del afiliado.
            System.out.println("rut: "+ rut);
            System.out.println("Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA): "+ Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            System.out.println("Helper.getVarPorAmbiente(IND_Constants.Libreria_TEDTA): "+ Helper.getVarPorAmbiente(IND_Constants.Libreria_TEDTA));
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("TEDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_TEDTA));
            parametros.put("input", rut);
            datos = sqlMap.queryForList("solBeneficiosNS.obtenerBeneficiosTesoreria", parametros);
            return (BeneficioVO[]) datos.toArray(new BeneficioVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    public static Retorno anularPagoBeneficio(long folio)
    {		
        Retorno ret = new Retorno();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", Long.toString(folio));
            sqlMap.update("solBeneficiosNS.updatePagoAnula", parametros);

            sqlMap.commitTransaction();

        } catch (SQLException e) {

            ret.setCodError(99);
            ret.setDesError("Error desconocido de BD al ejecutar la actualización de estado");

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                ret.setCodError(98);
                ret.setDesError("Error al cerrar conexión durante la ejecución de la Actualización");

                e.printStackTrace(); }
        }

        return ret;
    }

    //Fin REQ 6988 JLGN 11-02-2013
}	
