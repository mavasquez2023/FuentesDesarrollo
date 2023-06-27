package cl.araucana.wsatento.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cl.araucana.wsatento.business.service.LicenciaService;
import cl.araucana.wsatento.business.to.LicenciaTO;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

public class LicenciaServiceImplMock implements LicenciaService{
	
	public List getLicenicas(String rut) throws WSAtentoException{
		List listaLicencias = new ArrayList();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		try{
//			if(rut.trim().equals("23358823-7")){
//				listaLicencias.add(new LicenciaTO("01", sdf.parse("2013-04-29")));
//				listaLicencias.add(new LicenciaTO("02", sdf.parse("2013-05-15")));
//			}if(rut.trim().equals("13511818-4")){
//				listaLicencias.add(new LicenciaTO("02", sdf.parse("2013-05-12")));
//			}if(rut.trim().equals("14038741-k")){
//				listaLicencias.add(new LicenciaTO("01", sdf.parse("2013-04-29")));
//				listaLicencias.add(new LicenciaTO("02", sdf.parse("2013-05-15")));
//				listaLicencias.add(new LicenciaTO("02", sdf.parse("2013-05-11")));
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	
		return listaLicencias;
	}
}