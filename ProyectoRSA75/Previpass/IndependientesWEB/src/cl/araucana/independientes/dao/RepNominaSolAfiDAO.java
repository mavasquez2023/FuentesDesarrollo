package cl.araucana.independientes.dao;

//import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.text.ParseException;

import cl.araucana.independientes.config.ConfiguracionSqlMap;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.vo.LinRepNominaSolAfiVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class RepNominaSolAfiDAO {

    public LinRepNominaSolAfiVO[] consultaRepNominaSolAfi(String fechaIni, String fechaFin, String oficina, String estado) 
    {
        //TODO Revisar formateo y variables no utilizadas
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        HashMap paramMap = new HashMap();
        List datos = null;

        String fecha = "";
        Date date = new Date();
        Date dateIni = new Date();
        Date dateFin = new Date();		

        String DATE_FORMAT1 = "MM/dd/yyyy";
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        LinRepNominaSolAfiVO[] result = null;

        try {
            fecha = fechaIni;
            dateIni = sdf2.parse(fecha);

            fecha = fechaFin;
            dateFin = sdf2.parse(fecha);

            paramMap.put("P_FECHADESDE", new String(sdf2.format(dateIni)));
            paramMap.put("P_FECHAHASTA", new String(sdf2.format(dateFin)));
            paramMap.put("P_OFICINA", new Integer(oficina));
            paramMap.put("P_ESTADOSOLICITUD", new Integer(estado));

            datos = sqlMap.queryForList("RepNominaSolAfi.SP_NOMSOLA",paramMap);

            result = (LinRepNominaSolAfiVO[])datos.toArray(new LinRepNominaSolAfiVO[datos.size()]);

            for(int i = 0; i < result.length ; i++){

                LinRepNominaSolAfiVO lineaTemp = result[i];

                //Formateo de fecha
                String fechaTemp = lineaTemp.getFechaIngreso();
                date = sdf1.parse(fechaTemp);
                lineaTemp.setFechaIngreso(sdf2.format(date));

                String rutTemp = lineaTemp.getRut();
                rutTemp = Helper.separadorDeMiles(rutTemp) + "-" + Helper.digitoVerificadorRut(rutTemp);
                lineaTemp.setRut(rutTemp);

                result[i] = lineaTemp;
            }

            return result;

        }   
        catch (SQLException e) {

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

        return result;
    }
}	
