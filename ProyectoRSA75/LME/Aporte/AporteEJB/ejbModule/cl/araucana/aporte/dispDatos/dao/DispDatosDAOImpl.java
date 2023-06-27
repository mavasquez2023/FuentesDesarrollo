package cl.araucana.aporte.dispDatos.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.aporte.config.ConfiguracionSqlMap;
import cl.araucana.aporte.dispDatos.bo.AfiliadoResultBO;
import cl.araucana.aporte.dispDatos.bo.DispDatosResultBO;
import cl.araucana.aporte.dispDatos.bo.ErrorResultBO;
import cl.araucana.aporte.helper.Helper;

public class DispDatosDAOImpl implements DispDatosDAO {

    public DispDatosResultBO obtenerInfoDatos (int rut){
        /**
         * IMPLEMENTA LA BUSQUEDA DEL RUT PARA RETORNAR
         * DATOS DEL AFILIADO INDEPENDIENTE
         * POR EL MOMENTO EL FAX SE RETORNA NULO
         */
        //System.out.println("DispDatosDAOImpl");			
        DispDatosResultBO dispdatosResult = new DispDatosResultBO ();
        ErrorResultBO error = new ErrorResultBO();		
        AfiliadoResultBO afiliado = new AfiliadoResultBO();		

        SqlMapClient sqlMap = null;;		
        HashMap paramMap = new HashMap();
        List datos = null;
        AfiliadoResultBO[] result = null;

        try {

            paramMap.put("P_RUT", new Integer(rut));
            try{
                sqlMap = ConfiguracionSqlMap.cargarSqlMap();
                datos = sqlMap.queryForList("spDispDatos.SP_DISDATO",paramMap);		
            }catch (Exception e){
                error.setCodError(-1);
                if (e.toString().length() < 200){
                    error.setGlsError("Error conexión a Base de Datos: " + e.toString());
                }
                else{
                    error.setGlsError("Error conexión a Base de Datos: " + e.toString().substring(0,200));
                }
                afiliado = null;
                dispdatosResult.setError(error);
                dispdatosResult.setAfiliado(afiliado);
                return dispdatosResult;

            }
            int datosSize = datos.size();	

            if (datosSize == 0){
                error.setCodError(-5);
                error.setGlsError("RUT no existe en el sistema");
                afiliado = null;
                dispdatosResult.setError(error);
                dispdatosResult.setAfiliado(afiliado);
            }
            else{
                error.setCodError(0);
                error.setGlsError("Consulta Exitosa");
                result = (AfiliadoResultBO[])datos.toArray(new AfiliadoResultBO[datos.size()]);
                AfiliadoResultBO lineaTemp = result[0];   

                afiliado.setRut(lineaTemp.getRut());
                String dlgVer = Helper.digitoVerificadorRut(lineaTemp.getRut());
                afiliado.setDlgVerificador(dlgVer);
                afiliado.setApellidoPaterno(lineaTemp.getApellidoPaterno());
                afiliado.setApellidoMaterno(lineaTemp.getApellidoMaterno());
                afiliado.setNombres(lineaTemp.getNombres());
                afiliado.setGenero(lineaTemp.getGenero());
                afiliado.setActEconomica(lineaTemp.getActEconomica());
                afiliado.setTipoDireccion(lineaTemp.getTipoDireccion());
                afiliado.setDireccion(lineaTemp.getDireccion());
                afiliado.setNumero(lineaTemp.getNumero());
                afiliado.setDepartamento(lineaTemp.getDepartamento());
                afiliado.setComuna(lineaTemp.getComuna());
                afiliado.setMail(lineaTemp.getMail());
                afiliado.setTelefono(lineaTemp.getTelefono());
                afiliado.setFax("");
                afiliado.setCelular(lineaTemp.getCelular());

                dispdatosResult.setError(error);
                dispdatosResult.setAfiliado(afiliado);
            }	    
            return dispdatosResult;
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
                afiliado = null;
                dispdatosResult.setError(error);
                dispdatosResult.setAfiliado(afiliado);
                return dispdatosResult;
            } 
        }
    }
}
