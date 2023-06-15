package cl.laaraucana.compromisototal.dao.intercaja;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import cl.laaraucana.compromisototal.dao.intercaja.domain.SalidaListaIntercajaVO;
import cl.laaraucana.compromisototal.dao.intercaja.domain.Sinaf20h;
import cl.laaraucana.compromisototal.ibatis.MyClassSqlConfig;
import cl.laaraucana.compromisototal.utils.Codigo;

import com.ibatis.sqlmap.client.SqlMapClient;

public class IntercajasDAO {
	protected Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public SalidaListaIntercajaVO getCreditosIntercaja(String rut) {
		SalidaListaIntercajaVO salida = new SalidaListaIntercajaVO();

		try {

			SqlMapClient sqlMap = MyClassSqlConfig.getSqlMapInstance();
			ArrayList<Sinaf20h> listaIntercaja = new ArrayList<Sinaf20h>();
			listaIntercaja = (ArrayList<Sinaf20h>) sqlMap.queryForList("selectByRut", rut.trim());

			salida.setListaIntercaja(listaIntercaja);

			if (listaIntercaja.size() == 0) {
				salida.setCodError(Codigo.VACIO);
			} else {
				salida.setCodError(Codigo.OK);
			}

		} catch (Exception e) {
			salida.setCodError(Codigo.ERROR);
			logger.error("Error al consultar a la base de datos CRDTA/DEF1000 .cause: " + e.getMessage());
		}
		return salida;
	}
}