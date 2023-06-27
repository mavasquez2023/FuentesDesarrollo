package cl.araucana.independientes.dao;

//import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cl.araucana.independientes.config.ConfiguracionSqlMap;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.vo.LinConsMasivaApoVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ConsMasivaApoDAO {

    public LinConsMasivaApoVO[] consultaMasivaApo(String fechaIni, String fechaFin, String estado, String oficina) 
    {
        //TODO Revisar formateo y variables no utilizadas
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        HashMap paramMap = new HashMap();
        List datos = null;

        LinConsMasivaApoVO[] result = null;

        String DATE_FORMAT1 = "yyyy-MM-dd";
        String DATE_FORMAT2 = "dd/MM/yyyy"; 
        String DATE_FORMAT3 = "yyyy/MM"; 
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);
        SimpleDateFormat sdf3 = new SimpleDateFormat(DATE_FORMAT3);
        Date date = new Date();
        int codigo = 0; 

        ListadoParametros listParametros = ListadoParametros.getInstancia();
        Parametro[] param1 = listParametros.getListEstadoAporte();
        Parametro[] param2 = listParametros.getListFormaPagoAporte();
        Parametro[] param3 = listParametros.getListOficina();
        Parametro[] param4 = listParametros.getListEstadoAfiliado();
        Parametro[] param5 = listParametros.getListTipoPagoAporte();
        try {

            paramMap.put("P_FECHADESDE", new String(fechaIni));
            paramMap.put("P_FECHAHASTA", new String(fechaFin));
            paramMap.put("P_ESTADOAPORTE", new Integer(estado));
            paramMap.put("P_OFICINA", new Integer(oficina));

            datos = sqlMap.queryForList("ConsMasivaApo.SP_MSVAPO",paramMap);

            result = (LinConsMasivaApoVO[])datos.toArray(new LinConsMasivaApoVO[datos.size()]);

            for(int i = 0; i < result.length ; i++){	    	

                LinConsMasivaApoVO lineaTemp = result[i];
                String rutTemp = lineaTemp.getRut();
                String montoAporteTemp = lineaTemp.getMontoAporte();
                String fechaPagoTemp = lineaTemp.getFechaPago();
                String fechaVigenciaTemp = lineaTemp.getFechaVigencia();
                String mesAporteTemp = lineaTemp.getMesAporte();
                String formaPagoTemp = lineaTemp.getFormaPago();
                String estadoAporteTemp = lineaTemp.getEstadoAporte();
                String oficinaTemp = lineaTemp.getOficinaAfiliacion();
                String estadoAfiliadoTemp = lineaTemp.getEstadoAfiliado();
                String rentaImponible = lineaTemp.getRentaImponible();
                String tipoPago = lineaTemp.getTipoPago();
                String valorPago = lineaTemp.getValorPago();

                rutTemp = Helper.separadorDeMiles(rutTemp) + "-" + Helper.digitoVerificadorRut(rutTemp);
                montoAporteTemp = Helper.separadorDeMiles(montoAporteTemp);

                lineaTemp.setRut(rutTemp);		
                lineaTemp.setMontoAporte(montoAporteTemp);

                if (fechaPagoTemp == null){
                    lineaTemp.setFechaPago("");
                }
                else{
                    date = sdf1.parse(fechaPagoTemp);
                    lineaTemp.setFechaPago(sdf2.format(date));		    		
                }

                if (formaPagoTemp == null){
                    lineaTemp.setFormaPago("");
                }		    	
                else{
                    codigo = Integer.parseInt(formaPagoTemp);		    	
                    formaPagoTemp = Helper.obtenerDescripcion(param2, codigo);
                    lineaTemp.setFormaPago(formaPagoTemp);
                }		 

                if (estadoAporteTemp == null){
                    estadoAporteTemp = Helper.obtenerDescripcion(param1, 0);
                    lineaTemp.setEstadoAporte(estadoAporteTemp);
                }
                else{			
                    codigo = Integer.parseInt(estadoAporteTemp);		    	
                    estadoAporteTemp = Helper.obtenerDescripcion(param1, codigo);
                    lineaTemp.setEstadoAporte(estadoAporteTemp);
                }

                date = sdf1.parse(fechaVigenciaTemp);
                lineaTemp.setFechaVigencia(sdf2.format(date));

                date = sdf1.parse(mesAporteTemp);
                lineaTemp.setMesAporte(sdf3.format(date));		    		    		

                codigo = Integer.parseInt(oficinaTemp);		    	
                oficinaTemp = Helper.obtenerDescripcion(param3, codigo);
                lineaTemp.setOficinaAfiliacion(oficinaTemp);

                codigo = Integer.parseInt(estadoAfiliadoTemp);		    	
                estadoAfiliadoTemp = Helper.obtenerDescripcion(param4, codigo);
                lineaTemp.setEstadoAfiliado(estadoAfiliadoTemp);

                rentaImponible = Helper.separadorDeMiles(rentaImponible);
                lineaTemp.setRentaImponible(rentaImponible);

                codigo = Integer.parseInt(tipoPago);		    	
                tipoPago = Helper.obtenerDescripcion(param5, codigo);
                lineaTemp.setTipoPago(tipoPago);

                valorPago = Helper.separadorDeMiles(valorPago);
                lineaTemp.setValorPago(valorPago);

                result[i] = lineaTemp;
            }

            return result;

        }  
        catch (ParseException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace(); }	 
        }	

        return result;
    }
}	
