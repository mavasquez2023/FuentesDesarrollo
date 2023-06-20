package cl.laaraucana.ventaremota.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lautaro.xi.CRM.WEB_Mobile.Oa_CargaFirmaWebFS_DT;


import cl.laaraucana.ventaremota.dao.GenericDao;
import cl.laaraucana.ventaremota.entities.BitacoraContratoCREntiti;
import cl.laaraucana.ventaremota.entities.BitacoraEntiti;
import cl.laaraucana.ventaremota.entities.CreditoEntiti;
import cl.laaraucana.ventaremota.model.WsCargaCrm;

@Service
public class BitacoraServiceImpl implements BitacoraService{
	private static final Logger logger = Logger.getLogger(BitacoraServiceImpl.class);
	
	@Autowired
	private GenericDao<BitacoraEntiti> dao;
	
	@Autowired
	private GenericDao<BitacoraContratoCREntiti> daoccr;
	
	@Override
	public void save(BitacoraEntiti entity) throws Exception {
		// TODO Auto-generated method stub
		dao.save(entity);
	}
	
	@Override
	public void save(BitacoraContratoCREntiti entity) throws Exception {
		List<BitacoraContratoCREntiti> list=  daoccr.findCCRbyRut(BitacoraContratoCREntiti.class, entity.getRutCliente());
		if(list==null || list.size()==0){
			daoccr.save(entity);
		}
	}
	
	@Override
	public void insertBitacora(CreditoEntiti credito, String idChallenge, String codigoRetorno, String resultadoValidacion, String ipRemota) throws Exception {
		BitacoraEntiti bita = new BitacoraEntiti();

		bita.setRutcliente(credito.getRutCliente());
		bita.setDvcliente(credito.getDvCliente());
		bita.setFechaAprobRech(new Date());
		bita.setFolioCredito(credito.getFolioCredito());
		bita.setNumeroOferta(credito.getNumeroOferta());
		bita.setIdAprobRech("");
		bita.setIdChallenge(idChallenge);
		bita.setNumeroOfertaCrm(String.valueOf(credito.getNumeroOferta()));
		bita.setEstadoOfertaCrm("");
		bita.setIpAcceso(ipRemota);
		bita.setCodigoRetorno(codigoRetorno);
		if(resultadoValidacion.trim().equals("0")){
			resultadoValidacion="OK";
		}
		if(resultadoValidacion.trim().equals("1")){
			resultadoValidacion="Error";
		}
		bita.setResultadoValidacion(resultadoValidacion);
		logger.info("guardando bitácora para Folio: " + bita.getFolioCredito() + ", from ip:" + bita.getIpAcceso());
		save(bita);
		
	}
	
	@Override
	public void insertBitacoraContratoCR(long rut, String dv, String idChallenge, String codigoRetorno, String resultadoValidacion, String ipRemota) throws Exception {
		BitacoraContratoCREntiti bita = new BitacoraContratoCREntiti();

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

}
