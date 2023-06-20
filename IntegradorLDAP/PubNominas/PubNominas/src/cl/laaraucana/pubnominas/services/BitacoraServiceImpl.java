/**
 * 
 */
package cl.laaraucana.pubnominas.services;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.pubnominas.persistence.DaoBitacora;
import cl.laaraucana.pubnominas.utils.Utils;
import cl.laaraucana.pubnominas.vo.BitacoraVo;


/**
 * @author J-Factory
 *
 */
@Service
public class BitacoraServiceImpl implements BitacoraService {
	private static final Logger logger = Logger.getLogger(BitacoraServiceImpl.class);
	
	@Autowired
	DaoBitacora daoBita;
	
	@Override
	public void insertBitacora(String tipoNomina, String rutusu, String rol, String periodo, String rutEmpresa, String oficina, String sucursal) throws Exception {
		
		logger.info("Descargando Nómina: " + tipoNomina + ", Periodo: " + periodo + ", RutEmpresa:" + rutEmpresa + ", Oficina:" + oficina + ", Sucursal:" + sucursal);
		BitacoraVo bitaVO= new BitacoraVo();
		bitaVO.setNomina(tipoNomina);
		bitaVO.setPeriodo(Integer.parseInt(Utils.getFechaPeriodo()));
		bitaVO.setRutusu(Integer.parseInt(rutusu.split("-")[0]));
		bitaVO.setDvrutusu(rutusu.split("-")[1]);
		bitaVO.setRol(rol);
		bitaVO.setPeriodo_consulta(Integer.parseInt(periodo));
		bitaVO.setRutemp(Integer.parseInt(rutEmpresa));
		bitaVO.setOficina(oficina.trim());
		bitaVO.setSucursal(sucursal.trim());
		
		daoBita.insertBitacora(bitaVO);
	}

}
