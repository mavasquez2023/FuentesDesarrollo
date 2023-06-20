package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import cl.laaraucana.rendicionpagonomina.ibatis.vo.DetalleCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoManualVo;


public interface NominaManualDao {

	public void insertCabecera(ResumenCargaPagoManualVo cabecera) throws Exception;
	
	public void insertDetalle(DetalleCargaPagoManualVo detalle) throws Exception;
}
