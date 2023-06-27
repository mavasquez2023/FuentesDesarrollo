package cl.araucana.aporte.orqInput.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.aporte.config.ConfiguracionSqlMap;
import cl.araucana.aporte.orqInput.bo.AporteCallBO;
import cl.araucana.aporte.orqInput.bo.AporteDetalleBO;
import cl.araucana.aporte.orqInput.bo.AporteResultBO;

public class AporteDAOImpl {

    public static AporteCallBO obtenerAporte(int rut){
        /**
         * IMPLEMENTA LA BUSQUEDA DEL RUT PARA RETORNAR
         * DATOS DEL APORTE DEL AFILIADO INDEPENDIENTE
         */
        //System.out.println("AporteDAOImpl");
        AporteCallBO aporteCallBO = new AporteCallBO();
        AporteResultBO aporteBO = new AporteResultBO();
        SqlMapClient sqlMap =  null;
        HashMap paramMap = new HashMap();
        List datos = null;
        AporteDetalleBO[] detalle = null;
        int monto = 0;

        try {
            paramMap.put("P_RUT", new Integer(rut));
            try{
                sqlMap = ConfiguracionSqlMap.cargarSqlMap();		
                datos = sqlMap.queryForList("spOrqInput.SP_CONSAPT",paramMap);		
            }catch (Exception e){
                aporteCallBO.setCodError(-1);
                if (e.toString().length() < 200){
                    aporteCallBO.setGlsError("Error conexión a Base de Datos: " + e.toString());
                }
                else{aporteCallBO.setGlsError("Error conexión a Base de Datos: " + e.toString().substring(0,200));
                }
                aporteBO = null;
                aporteCallBO.setAporte(aporteBO);
                return aporteCallBO;
            }

            int datosSize = datos.size();	   
            if (datosSize == 0){
                aporteCallBO.setCodError(-6);
                aporteCallBO.setGlsError("No existe aporte para pagar asociado al RUT");
                aporteBO = null;
                aporteCallBO.setAporte(aporteBO);
            }
            else{
                detalle = (AporteDetalleBO[])datos.toArray(new AporteDetalleBO[datos.size()]);
                AporteDetalleBO lineaTemp = detalle[0]; 
                if (datosSize == 1 && lineaTemp.getMonto() == 0)
                {
                    aporteCallBO.setCodError(-6);
                    aporteCallBO.setGlsError("No existe aporte para pagar asociado al RUT");	
                    aporteBO = null;
                    aporteCallBO.setAporte(aporteBO);
                }
                else{
                    for(int i = 0; i < datosSize; i++){
                        lineaTemp = detalle[i]; 
                        monto = monto + lineaTemp.getMonto() + lineaTemp.getInteresesReajuste();
                    }
                    aporteBO.setNumRegistros(datosSize);
                    aporteBO.setMonto(monto);
                    aporteBO.setAporteDetalle(detalle);
                    aporteCallBO.setCodError(0);
                    aporteCallBO.setGlsError("Consulta exitosa para Aporte");
                    aporteCallBO.setAporte(aporteBO);
                }
            }
            return aporteCallBO;
        }
        finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                aporteCallBO.setCodError(-3);	
                if (e.toString().length() < 200){
                    aporteCallBO.setGlsError("Error de finalización Transacción SQL:" + e.toString());		
                }
                else{
                    aporteCallBO.setGlsError("Error de finalización Transacción SQL:" + e.toString().substring(0,200));		
                }
                aporteBO = null;
                aporteCallBO.setAporte(aporteBO);
                return aporteCallBO;
            } 
        }	
    }
}
