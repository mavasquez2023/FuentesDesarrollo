package cl.laaraucana.contratocr.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lautaro.xi.CRM.WEB_Mobile.Oa_CargaFirmaWebFS_DT;


import cl.laaraucana.contratocr.dao.GenericDao;
import cl.laaraucana.contratocr.entities.BitacoraEntiti;
import cl.laaraucana.contratocr.model.WsCargaCrm;

@Service
public class BitacoraServiceImpl implements BitacoraService{
	private static final Logger logger = Logger.getLogger(BitacoraServiceImpl.class);
	
	@Autowired
	private GenericDao<BitacoraEntiti> dao;
	
	@Override
	public void save(BitacoraEntiti entity) throws Exception {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void insertBitacora(long rut, String dv, String idChallenge, String codigoRetorno, String resultadoValidacion, String ipRemota) throws Exception {
		BitacoraEntiti bita = new BitacoraEntiti();

		bita.setRutCliente(rut);
		bita.setDvCliente(dv);
		bita.setFechaAprobacion(new Date());
		bita.setIdChallenge(idChallenge);
		bita.setIpAcceso(ipRemota);
		bita.setCodigoRetorno(codigoRetorno);
		if(resultadoValidacion.trim().equals("0")){
			resultadoValidacion="OK";
		}
		if(resultadoValidacion.trim().equals("1")){
			resultadoValidacion="Error";
		}
		bita.setResultadoValidacion(resultadoValidacion);
		logger.info("guardando bitácora para Rut: " + rut + "-"+ dv);
		save(bita);
		
	}

	@Override
	public List<BitacoraEntiti> getCotratoByRut(long rutCliente) throws Exception {
		// TODO Auto-generated method stub
		return dao.findbyRut(BitacoraEntiti.class, rutCliente);
	}

}
