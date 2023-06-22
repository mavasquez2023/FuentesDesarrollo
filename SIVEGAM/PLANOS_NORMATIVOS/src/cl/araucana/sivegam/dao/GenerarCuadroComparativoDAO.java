package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.vo.CuadroComparativoVO;
import cl.araucana.sivegam.vo.RespuestaVO;

public class GenerarCuadroComparativoDAO {

    public static CuadroComparativoVO obtenerMontosResumenes(String periodoSelected) {

        List datos = null;
        String resultado = "";
        CuadroComparativoVO cuadro = new CuadroComparativoVO();
        RespuestaVO consulta = new RespuestaVO();

        consulta.setFechaProceso(Integer.parseInt(periodoSelected));
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            datos = sqlMap.queryForList("svCuadroComparativo.obtener11AT", consulta);
            if (datos != null && datos.size() > 0) {
                resultado = (String) datos.get(0);
                if ((String) datos.get(0) == null) {
                    cuadro.setMontoBeneficioSif011AT(0);
                } else {
                    cuadro.setMontoBeneficioSif011AT(Long.parseLong(resultado));
                }

            } else {
                cuadro.setMontoBeneficioSif011AT(0);
            }

            datos = sqlMap.queryForList("svCuadroComparativo.obtener11CES", consulta);
            if (datos != null && datos.size() > 0) {
                resultado = (String) datos.get(0);
                if ((String) datos.get(0) == null) {
                    cuadro.setMontoBeneficioSif011CES(0);
                } else {
                    cuadro.setMontoBeneficioSif011CES(Long.parseLong(resultado));
                }

            } else {
                cuadro.setMontoBeneficioSif011CES(0);
            }

            datos = sqlMap.queryForList("svCuadroComparativo.obtener12AT", consulta);
            if (datos != null && datos.size() > 0) {
                resultado = (String) datos.get(0);
                if ((String) datos.get(0) == null) {
                    cuadro.setMontoBeneficioSif012AT(0);
                } else {
                    cuadro.setMontoBeneficioSif012AT(Long.parseLong(resultado));
                }

            } else {
                cuadro.setMontoBeneficioSif012AT(0);
            }

            datos = sqlMap.queryForList("svCuadroComparativo.obtener12PD", consulta);
            if (datos != null && datos.size() > 0) {
                resultado = (String) datos.get(0);
                if ((String) datos.get(0) == null) {
                    cuadro.setMontoBeneficioSif012PD(0);
                } else {
                    cuadro.setMontoBeneficioSif012PD(Long.parseLong(resultado));
                }

            } else {
                cuadro.setMontoBeneficioSif012PD(0);
            }

            datos = sqlMap.queryForList("svCuadroComparativo.obtener12CES", consulta);
            if (datos != null && datos.size() > 0) {
                resultado = (String) datos.get(0);
                if ((String) datos.get(0) == null) {
                    cuadro.setMontoBeneficioSif012CES(0);
                } else {
                    cuadro.setMontoBeneficioSif012CES(Long.parseLong(resultado));
                }

            } else {
                cuadro.setMontoBeneficioSif012CES(0);
            }

            datos = sqlMap.queryForList("svCuadroComparativo.obtenerMtoDocSif016", consulta);
            if (datos != null && datos.size() > 0) {
                resultado = (String) datos.get(0);
                if ((String) datos.get(0) == null) {
                    cuadro.setMontoDocumentoSif016(0);
                } else {
                    cuadro.setMontoDocumentoSif016(Long.parseLong(resultado));
                }
            } else {
                cuadro.setMontoDocumentoSif016(0);
            }

            datos = sqlMap.queryForList("svCuadroComparativo.obtenerSif017MtoDoc", consulta);
            if (datos != null && datos.size() > 0) {
                resultado = (String) datos.get(0);
                if ((String) datos.get(0) == null) {
                    cuadro.setMontoDocumentoSif017(0);
                } else {
                    cuadro.setMontoDocumentoSif017(Long.parseLong(resultado));
                }

            } else {
                cuadro.setMontoDocumentoSif017(0);
            }

            datos = sqlMap.queryForList("svCuadroComparativo.e4", consulta);
            if (datos != null && datos.size() > 0) {
                resultado = (String) datos.get(0);
                if ((String) datos.get(0) == null) {
                    cuadro.setEee4(0);
                } else {
                    cuadro.setEee4(Long.parseLong(resultado));
                }

            } else {
                cuadro.setEee4(0);
            }

            datos = sqlMap.queryForList("svCuadroComparativo.obtener12CCqh1nch4n", consulta);
            if (datos != null && datos.size() > 0) {
                resultado = (String) datos.get(0);
                if ((String) datos.get(0) == null) {
                    cuadro.setCcu(0);
                } else {
                    cuadro.setCcu(Long.parseLong(resultado));
                }

            } else {
                cuadro.setCcu(0);
            }

            cuadro.setCodResultado(0);
            return cuadro;

        } catch (SQLException e) {
            e.printStackTrace();
            cuadro = null;
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
                cuadro = null;
            }
        }

        return cuadro;

    }
}
