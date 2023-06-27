package cl.laaraucana.capaservicios.database.dao;

import org.apache.log4j.Logger;

import cl.laaraucana.capaservicios.database.vo.MigracionVO;
import cl.laaraucana.capaservicios.util.RutUtil;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MigracionDAO extends DaoParent {

  protected Logger logger = Logger.getLogger(this.getClass());

  /**
   * Consulta estado de migraci�n de BP a SAP
   * 
   * @param rut
   * @return true si el Bp est� migrado
   * @throws Exception
   */
  public boolean consultarEstMig(String rut) throws Exception {
    logger.info("Consultar Estado migrado de: " + rut);
    SqlMapClient sqlMap = getConn();
    int res = 0;
    boolean estMig = false;
    try {
      MigracionVO mig = new MigracionVO(RutUtil.getLongRut(rut), RutUtil.getDv(rut));
      res = (Integer) sqlMap.queryForObject("consultaEstMig", mig);
      estMig = (res > 0) ? true : false;
    } catch (Exception e) {
      logger.error("Excepcion al consultar estado migrado de:" + rut, e);
      throw new Exception("Fall� al realizar la consulta de migraci�n");
    }
    logger.info("Resultado consulta migracion rut:" + rut + ", estado:" + String.valueOf(res));
    return estMig;
  }
}
