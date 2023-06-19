/**
 * 
 */
package cl.laaraucana.certificadodiferimiento.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.laaraucana.certificadodiferimiento.ibatis.dao.ContactoDWHDao;
import cl.laaraucana.certificadodiferimiento.ibatis.dao.ContactoDWHDaoImpl;
import cl.laaraucana.certificadodiferimiento.vo.AfiliadoVo;



/**
 * @author J-Factory
 *
 */
@Service
public class ContactoDHWServicesImpl implements ContactoDWHServices {
	
	private ContactoDWHDao contactoDAO= new ContactoDWHDaoImpl();
	/* (non-Javadoc)
	 * @see cl.laaraucana.convenfsura.services.ContactoDWHServices#obtenerDatosContacto(cl.laaraucana.convenfsura.vo.AfiliadoVo)
	 */
	@Override
	public AfiliadoVo obtenerDatosContacto(AfiliadoVo afiliadoVO)
			throws Exception {
		String mail="";
		String telefono="";
		String celular="";
		
		int rut= Integer.parseInt(afiliadoVO.getRut().split("-")[0]);
		List<String> mails= contactoDAO.getMail(rut);
		List<String> telefonos= contactoDAO.getTelefono(rut);
		List<String> celulares= contactoDAO.getCelular(rut);
		
		if(mails!=null && mails.size()>0){
			mail= mails.get(0);
			
		}
		if(telefonos!=null && telefonos.size()>0){
			telefono= telefonos.get(0);
			
		}
		if(celulares!=null && celulares.size()>0){
			celular= celulares.get(0);
			
		}
		afiliadoVO.setEmail(mail);
		afiliadoVO.setTelefono(telefono);
		afiliadoVO.setCelular(celular);
		
		return afiliadoVO;
	}

}
