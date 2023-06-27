package cl.araucana.aporte.orqOutput.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.aporte.cobol.bo.ParametrosConexionBO;
import cl.araucana.aporte.cobol.bo.ParametrosLlamadaBO;
import cl.araucana.aporte.config.ConfiguracionSqlMap;
import cl.araucana.aporte.config.ConsumidorCobol;
import cl.araucana.aporte.helper.GlobalProperties;
import cl.araucana.aporte.helper.Helper;
import cl.araucana.aporte.orqOutput.bo.DatosParametricosBO;
import cl.araucana.aporte.orqOutput.bo.ErrorResultBO;

public class CreditoDAOImpl {

    public static ErrorResultBO actualizarCredito(int rut, int montoCredito, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago){

        ParametrosConexionBO conexion = new ParametrosConexionBO();
        ParametrosLlamadaBO[] llamada = new ParametrosLlamadaBO[2];	
        DatosParametricosBO datosBO = new DatosParametricosBO();
        ErrorResultBO error = new ErrorResultBO();
        String entrada = "";
        String credito = new String();
        int codError;


        try {
            datosBO = obtenerTablasParametricas(38);

        }catch (Exception e){
            e.printStackTrace();
            error.setCodError(-14);
            if (e.toString().length() < 200){
                error.setGlsError("Error en carga datos de Tabla Parametrica: " + e.toString());
            }
            else{
                error.setGlsError("Error en carga datos de Tabla Parametrica: " + e.toString().substring(0,200));
            }
            return error;
        }

        if (datosBO.getError() == 0 ){		

            entrada = Helper.retornaString3(datosBO.getEntrada1(), String.valueOf(rut));
            entrada = entrada + Helper.retornaString3(datosBO.getEntrada2(), String.valueOf(montoCredito));
            entrada = entrada + Helper.retornaString3(datosBO.getEntrada3(), fechaRecaudacion);
            entrada = entrada + Helper.retornaString3(datosBO.getEntrada4(), (horaRecaudacion.substring(0, 2)+ horaRecaudacion.substring(3, 5)));
            entrada = entrada + Helper.retornaString2(datosBO.getEntrada5(), usuario);
            entrada = entrada + Helper.retornaString3(datosBO.getEntrada6(), String.valueOf(documentoPago));

            ParametrosLlamadaBO par1 = new ParametrosLlamadaBO();
            par1.setTipo("STRING");
            par1.setLargo(datosBO.getEntrada());
            par1.setValor(entrada);
            par1.setDireccion("IN");
            llamada[0] = par1;

            //System.out.println ("primer parametro: " + entrada);

            ParametrosLlamadaBO par2 = new ParametrosLlamadaBO();
            par2.setTipo("STRING");
            par2.setLargo(datosBO.getControl());
            par2.setValor(Helper.retornaString3(datosBO.getControl(), ""));
            par2.setDireccion("OUT");
            llamada[1] = par2;

            //System.out.println ("secundo parametro: " + Helper.retornaString3(datosBO.getControl(), ""));

            conexion.setIpServer(datosBO.getIpServer());
            conexion.setUsuarioConexion(datosBO.getUsuarioConexion());
            conexion.setClaveConexion(datosBO.getClaveConexion());
            conexion.setPrograma(datosBO.getPrograma());

            try{
                ParametrosLlamadaBO[] salida = ConsumidorCobol.call(conexion, llamada);
                credito = (String)salida[1].getValor();	

                if (credito.length() >= 2){
                    codError = Integer.parseInt(credito.substring(1, 2));
                }
                else{
                    codError = Integer.parseInt(credito);
                }

                if (codError == 1){
                    error.setCodError(-12);
                    error.setGlsError("Falta rescate deuda");
                }
                else if (codError == 2){
                    error.setCodError(-12);
                    error.setGlsError("Monto deuda no cuadra con monto a pago");
                }
                else if (codError == 4){
                    error.setCodError(-12);
                    error.setGlsError("Cliente no posee deuda");
                }
                else if (codError == 5){
                    error.setCodError(-12);
                    error.setGlsError("Proceso de pago ya fue solicitado");
                }
                else if (codError == 6){
                    error.setCodError(-12);
                    error.setGlsError("Proceso de pago ya fue realizado");
                }
                else if (codError == 3){ 
                    error.setCodError(0);
                    error.setGlsError("Proceso de pago exitoso");
                }		
                else{
                    error.setCodError(-2);
                    error.setGlsError("Error en Transacción COBOL: Servicio no puede resolver la solicitud");
                }
                return error;
            }catch (Exception e){
                error.setCodError(-1);
                if (e.toString().length() < 200){
                    error.setGlsError("Error en conexión a proceso COBOL" + e.toString());
                }
                else{
                    error.setGlsError("Error en conexión a proceso COBOL" + e.toString().substring(0,200));
                }
                return error;
            }
        }
        else{
            error.setCodError(datosBO.getError());
            error.setGlsError(datosBO.getGlserror());
            return error;
        }
    }

    public static ErrorResultBO ingresarLogCredito(int flag, int rut, int montoCancelado, String fechaPago, String horaPago, String usuario, int documentoPago){
        SqlMapClient sqlMap =  null;
        HashMap paramMap = new HashMap();
        String CodErrorResult = new String();
        int codError = 0;
        ErrorResultBO error = new ErrorResultBO();
        try {
            paramMap.put("P_FLAG", new Integer(flag));
            paramMap.put("P_RUT", new Integer(rut));
            paramMap.put("P_FECHAPAGO", new String(fechaPago));
            paramMap.put("P_HORAPAGO", new String(horaPago));
            paramMap.put("P_MONTO", new Integer(montoCancelado));
            paramMap.put("P_USUARIO", new String(usuario));
            paramMap.put("P_DOCPAGO", new Integer(documentoPago));
            paramMap.put("P_ERROR", new String());
            try{
                sqlMap = ConfiguracionSqlMap.cargarSqlMap();	
                sqlMap.queryForObject("spOrqOutput.SP_INGCRDLOG",paramMap);
            }catch (Exception e){
                e.printStackTrace();
                error.setCodError(-1);
                if (e.toString().length() < 200){
                    error.setGlsError("Error conexión a Base de Datos: " + e.toString());
                }
                else {
                    error.setGlsError("Error conexión a Base de Datos: " + e.toString().substring(0,200));
                }
                return error;
            }

            CodErrorResult = (String)paramMap.get("P_ERROR");
            codError = Integer.parseInt(CodErrorResult.trim());

            if (codError == 0){				
                error.setGlsError("Ingreso log credito exitoso");
            }
            else if (codError == -2){
                error.setGlsError("Error en Transacción SQL");
            }
            else {
                error.setGlsError("Error al ingresar log de información de credito");
            }

            error.setCodError(codError);
            return error;
        }
        finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
                error.setCodError(-3);	
                if (e.toString().length() < 200){
                    error.setGlsError("Error de finalización Transacción SQL:" + e.toString());		
                }
                else{
                    error.setGlsError("Error de finalización Transacción SQL:" + e.toString().substring(0,200));		
                }
                return error;
            } 
        }
    }

    public static DatosParametricosBO obtenerTablasParametricas(int entidad){
        SqlMapClient sqlMap =  null;
        HashMap paramMap = new HashMap();
        String CodErrorResult = new String();
        String entrada1 = new String();
        String entrada2 = new String();
        String entrada3 = new String();
        String entrada4 = new String();
        String entrada5 = new String();
        String entrada6 = new String();
        String control = new String();
        String ipServer = new String();
        String usuarioConexion = new String();
        String claveConexion = new String();
        String programa = new String();
        int entrada = 0;
        int codError = 0;
        DatosParametricosBO datosBO = new DatosParametricosBO();

        GlobalProperties global = GlobalProperties.getInstance();
        ipServer = global.getValorExterno("APO.output.ipServer");
        usuarioConexion = global.getValorExterno("APO.output.user");
        claveConexion = global.getValorExterno("APO.output.password");
        programa = global.getValorExterno("APO.output.programa");

        try {
            paramMap.put("P_ENTIDAD", new Integer(entidad));
            paramMap.put("P_ENTRADA1", new String());
            paramMap.put("P_ENTRADA2", new String());
            paramMap.put("P_ENTRADA3", new String());
            paramMap.put("P_ENTRADA4", new String());
            paramMap.put("P_ENTRADA5", new String());
            paramMap.put("P_ENTRADA6", new String());
            paramMap.put("P_CONTROL", new String());
            paramMap.put("P_SALIDA", new String());
            paramMap.put("P_ERROR", new String());
            try{
                sqlMap = ConfiguracionSqlMap.cargarSqlMap();	
                sqlMap.queryForObject("spOrqOutput.SP_DATORQ",paramMap);
            }catch (Exception e){
                datosBO.setError(-1);
                if (e.toString().length() < 200){
                    datosBO.setGlserror("Error conexión a Base de Datos: " + e.toString());
                }
                else {
                    datosBO.setGlserror("Error conexión a Base de Datos: " + e.toString().substring(0,200));
                }
                return datosBO;
            }

            CodErrorResult = (String)paramMap.get("P_ERROR");
            codError = Integer.parseInt(CodErrorResult.trim());

            if (codError == 0){				
                datosBO.setGlserror("Ingreso log exitoso");
                entrada1 = (String)paramMap.get("P_ENTRADA1");
                entrada = Integer.parseInt(entrada1.trim());
                datosBO.setEntrada1(Integer.parseInt(entrada1.trim()));

                entrada2 = (String)paramMap.get("P_ENTRADA2");
                entrada = entrada + Integer.parseInt(entrada2.trim());
                datosBO.setEntrada2(Integer.parseInt(entrada2.trim()));

                entrada3 = (String)paramMap.get("P_ENTRADA3");
                entrada = entrada + Integer.parseInt(entrada3.trim());
                datosBO.setEntrada3(Integer.parseInt(entrada3.trim()));

                entrada4 = (String)paramMap.get("P_ENTRADA4");
                entrada = entrada + Integer.parseInt(entrada4.trim());
                datosBO.setEntrada4(Integer.parseInt(entrada4.trim()));

                entrada5 = (String)paramMap.get("P_ENTRADA5");
                entrada = entrada + Integer.parseInt(entrada5.trim());
                datosBO.setEntrada5(Integer.parseInt(entrada5.trim()));

                entrada6 = (String)paramMap.get("P_ENTRADA6");
                entrada = entrada + Integer.parseInt(entrada6.trim());
                datosBO.setEntrada6(Integer.parseInt(entrada6.trim()));

                datosBO.setEntrada(entrada);

                control = (String)paramMap.get("P_CONTROL");
                datosBO.setControl(Integer.parseInt(control.trim()));

                datosBO.setIpServer(ipServer.trim());
                datosBO.setUsuarioConexion(usuarioConexion.trim());
                datosBO.setClaveConexion(claveConexion.trim());
                datosBO.setPrograma(programa.trim());

            }
            else if (codError == -2){
                datosBO.setGlserror("Error en Transacción SQL");
            }
            else {
                datosBO.setGlserror("Error al ingresar log de información de aporte");
            }			
            datosBO.setError(codError);
            return datosBO;

        }
        finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                datosBO.setError(-3);	
                if (e.toString().length() < 200){
                    datosBO.setGlserror("Error de finalización Transacción SQL:" + e.toString());		
                }
                else{
                    datosBO.setGlserror("Error de finalización Transacción SQL:" + e.toString().substring(0,200));		
                }
                return datosBO;
            } 
        }
    }

}
