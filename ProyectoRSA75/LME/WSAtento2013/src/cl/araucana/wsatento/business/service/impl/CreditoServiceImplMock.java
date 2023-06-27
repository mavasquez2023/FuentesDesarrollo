package cl.araucana.wsatento.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cl.araucana.wsatento.business.service.CreditoService;
import cl.araucana.wsatento.business.to.CreditoTO;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

public class CreditoServiceImplMock implements CreditoService{
	public List getCreditos(String rut) throws WSAtentoException{
		List listaCreditos = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			if(rut.trim().equals("23358823-7")){
				listaCreditos.add(new CreditoTO(sdf.parse("2013-04-29")));
				listaCreditos.add(new CreditoTO(sdf.parse("2013-05-02")));
			}if(rut.trim().equals("7579391-k")){
				listaCreditos.add(new CreditoTO(sdf.parse("2013-05-03")));
			}if(rut.trim().equals("14038741-k")){
				listaCreditos.add(new CreditoTO(sdf.parse("2013-04-26")));
				listaCreditos.add(new CreditoTO(sdf.parse("2013-05-02")));
				listaCreditos.add(new CreditoTO(sdf.parse("2013-05-04")));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listaCreditos;
	}
}
