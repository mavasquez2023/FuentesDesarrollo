package cl.araucana.aporte.orqOutput.dao;

import java.sql.SQLException;
import java.util.HashMap;

import cl.araucana.aporte.config.ConfiguracionSqlMap;
import cl.araucana.aporte.orqOutput.bo.ErrorResultBO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AporteDAOImpl {

    public static ErrorResultBO actualizarAporte(int rut, int periodoAporte, int montoCancelado, String fechaPago){

        SqlMapClient sqlMap =  null;
        HashMap paramMap = new HashMap();
        String CodErrorResult = new String();
        int codError = 0;
        ErrorResultBO error = new ErrorResultBO();

        try {
            paramMap.put("P_RUT", new Integer(rut));
            paramMap.put("P_FECHAPAGO", new String(fechaPago));
            paramMap.put("P_MONTO", new Integer(montoCancelado));
            paramMap.put("P_PERIODO", new Integer(periodoAporte));
            paramMap.put("P_ERROR", new String());
            try{
                sqlMap = ConfiguracionSqlMap.cargarSqlMap();	
                sqlMap.queryForObject("spOrqOutput.SP_ACLAPT",paramMap);
            }catch (Exception e){
                e.printStackTrace();
                error.setCodError(-1);
                if (e.toString().length() < 200){
                    error.setGlsError("Error conexión a Base de Datos: " + e.toString());
                }
                else{
                    error.setGlsError("Error conexión a Base de Datos: " + e.toString().substring(0,200));
                }
                return error;
            }			
            CodErrorResult = (String)paramMap.get("P_ERROR");
            codError = Integer.parseInt(CodErrorResult.trim());

            if (codError == 0){				
                error.setGlsError("Actualización exitosa para Aporte");
            }
            else if (codError == -2){
                error.setGlsError("Error en Transacción SQL");
            }
            else if (codError == -5){
                error.setGlsError("RUT no existe en el sistema");
            }
            else if (codError == -6){
                error.setGlsError("No existe aporte para pagar asociado al RUT");
            }
            else if (codError == -8){
                error.setGlsError("RUT de afiliado no se encuentra vigente en el sistema");
            }
            else {
                error.setGlsError("Error al actualizar información de aporte");
            }

            error.setCodError(codError);
            return error;
        }
        finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
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

    public static ErrorResultBO ingresarLogAporte(int flag, int rut, int periodoAporte, int montoCancelado, String fechaPago, String horaPago, String usuario){
        SqlMapClient sqlMap =  null;
        HashMap paramMap = new HashMap();
        String CodErrorResult = new String();
        int codError = 0;
        ErrorResultBO error = new ErrorResultBO();

        try {
            System.out.println("Ingreso de Parametros APORTELOG");
            System.out.println("rut:" + rut);
            paramMap.put("P_FLAG", new Integer(flag));
            paramMap.put("P_RUT", new Integer(rut));
            paramMap.put("P_FECHAPAGO", new String(fechaPago));
            paramMap.put("P_HORAPAGO", new String(horaPago));
            paramMap.put("P_MONTO", new Integer(montoCancelado));
            paramMap.put("P_PERIODO", new Integer(periodoAporte));
            paramMap.put("P_USUARIO", new String(usuario));
            paramMap.put("P_ERROR", new String());
            try{
                System.out.println("Ejecuta insert APORTELOG antes");
                sqlMap = ConfiguracionSqlMap.cargarSqlMap();	
                sqlMap.queryForObject("spOrqOutput.SP_INGAPTLOG",paramMap);
                System.out.println("Ejecuta insert APORTELOG despues");
            }catch (Exception e){
                e.printStackTrace();
                error.setCodError(-1);
                if (e.toString().length() < 200){
                    error.setGlsError("Error conexión a Base de Datos: " + e.toString());
                }
                else{
                    error.setGlsError("Error conexión a Base de Datos: " + e.toString().substring(0,200));
                }
                return error;
            }

            CodErrorResult = (String)paramMap.get("P_ERROR");
            codError = Integer.parseInt(CodErrorResult.trim());

            System.out.println("CodErrorResult: "+ CodErrorResult);
            System.out.println("codError: "+ codError);

            if (codError == 0){				
                error.setGlsError("Ingreso log exitoso");
            }
            else if (codError == -2){
                error.setGlsError("Error en Transacción SQL");
            }
            else {
                error.setGlsError("Error al ingresar log de información de aporte");
            }

            error.setCodError(codError);
            return error;
        }
        finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
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
}

