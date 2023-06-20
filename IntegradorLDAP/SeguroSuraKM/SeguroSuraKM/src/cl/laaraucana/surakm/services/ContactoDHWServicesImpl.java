/**
 * 
 */
package cl.laaraucana.surakm.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cl.laaraucana.surakm.controller.InitController;
import cl.laaraucana.surakm.ibatis.dao.ContactoDWHDao;
import cl.laaraucana.surakm.ibatis.dao.ContactoDWHDaoImpl;
import cl.laaraucana.surakm.ibatis.vo.ParamContacto;
import cl.laaraucana.surakm.vo.AfiliadoVo;

/**
 * @author J-Factory
 *
 */
@Service
public class ContactoDHWServicesImpl implements ContactoDWHServices {
	private static final Logger logger = Logger.getLogger(ContactoDHWServicesImpl.class);
	
	private ContactoDWHDao contactoDAO= new ContactoDWHDaoImpl();
	/* (non-Javadoc)
	 * @see cl.laaraucana.convenfsura.services.ContactoDWHServices#obtenerDatosContacto(cl.laaraucana.convenfsura.vo.AfiliadoVo)
	 */
	@Override
	public AfiliadoVo obtenerDatosContacto(AfiliadoVo afiliadoVO)
			throws Exception {
		String mail="";
		String celular="";
		logger.info("Obteniendo datos de contacto desde DWH para Rut:" + afiliadoVO.getRut());
		int rut= Integer.parseInt(afiliadoVO.getRut().split("-")[0]);
		Date fechaCorte= contactoDAO.getFechaCorte(rut);
		logger.info("Fecha de corte:" + fechaCorte);
		
		ParamContacto param= new ParamContacto();
		param.setRut(rut);
		param.setFechaCorte(fechaCorte);
		
		List<String> mails= contactoDAO.getMail(param);
		List<String> celulares= contactoDAO.getCelular(param);
		
		if(mails!=null && mails.size()>0){
			mail= mails.get(0);
			logger.info("mail:" + mail);
		}

		if(celulares!=null && celulares.size()>0){
			celular= celulares.get(0);
			logger.info("celular:" + celular);
		}
		afiliadoVO.setEmail(mail);
		afiliadoVO.setCelular(celular);
		
		return afiliadoVO;
	}


}
