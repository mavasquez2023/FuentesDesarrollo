/**
 * 
 */
package cl.laaraucana.convenfsura.services;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cl.laaraucana.convenfsura.ibatis.dao.BitacoraDao;
import cl.laaraucana.convenfsura.ibatis.dao.BitacoraDaoImpl;
import cl.laaraucana.convenfsura.ibatis.vo.BitacoraSuraVo;
import cl.laaraucana.convenfsura.ibatis.vo.DatosContactoVo;
import cl.laaraucana.convenfsura.vo.AfiliadoVo;

/**
 * @author J-Factory
 *
 */
@Service
public class BitacoraServiceImpl implements BitacoraService {
	private static final Logger logger = Logger.getLogger(BitacoraServiceImpl.class);
	BitacoraDao bitacoraDao= new BitacoraDaoImpl();
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.convenfsura.services.BitacoraService#insertBitacora(cl.laaraucana.convenfsura.vo.AfiliadoVo)
	 */
	@Override
	public void insertBitacora(AfiliadoVo data_afiliado) throws Exception {
		String rut= data_afiliado.getRut().split("-")[0];
		String dv= data_afiliado.getRut().split("-")[1];
		
		BitacoraSuraVo bitaVO= new BitacoraSuraVo();
		bitaVO.setRut(Long.parseLong(rut));
		bitaVO.setDv(dv);
		bitaVO.setNombre(data_afiliado.getNombre());
		bitaVO.setCelular(data_afiliado.getCelular());
		bitaVO.setTelefono(data_afiliado.getTelefono());
		bitaVO.setEmail(data_afiliado.getEmail());
		bitaVO.setFecha_creacion(new Date());
		
		bitacoraDao.insertBitacora(bitaVO);
	}

	@Override
	public void insertDatosContacto(AfiliadoVo data_afiliado, String tipoDato){
		String rut= data_afiliado.getRut().split("-")[0];
		String dv= data_afiliado.getRut().split("-")[1];
		
		DatosContactoVo datos= new DatosContactoVo();
		datos.setAccion("C");
		datos.setEstado("0");
		datos.setFechaCreacion(new Date());
		datos.setTipoBP("1");
		datos.setRut(Long.parseLong(rut));
		datos.setDv(dv);
		datos.setTipoDato(tipoDato);
		if(tipoDato.equals("MOB")){
			datos.setDato(data_afiliado.getCelular());
		}else if(tipoDato.equals("TEL")){
			datos.setDato(data_afiliado.getTelefono());
		}else if(tipoDato.equals("EMAIL")){
			datos.setDato(data_afiliado.getEmail());
		}
		try {
			bitacoraDao.insertDatosContacto(datos);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("RUT " + data_afiliado +  " ya regsitrado en tabla de Datos Contacto para tipoDato " + tipoDato);
		}
		
	}

}
