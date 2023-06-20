package cl.laaraucana.ventaremota.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lautaro.xi.CRM.WEB_Mobile.Oa_CargaFirmaWebFS_DT;

import cl.laaraucana.ventaremota.controller.InitController;
import cl.laaraucana.ventaremota.dao.GenericDao;
import cl.laaraucana.ventaremota.entities.BitacoraEntiti;
import cl.laaraucana.ventaremota.entities.CreditoEntiti;
import cl.laaraucana.ventaremota.ibatis.dao.CodigosSinacofiDao;
import cl.laaraucana.ventaremota.ibatis.dao.CodigosSinacofiDaoImpl;
import cl.laaraucana.ventaremota.model.WsCargaCrm;
import cl.laaraucana.ventaremota.util.Configuraciones;

@Service
public class CreditoServiceImpl implements CreditoService {
	
	private static final Logger logger = Logger.getLogger(CreditoServiceImpl.class);
	
	@Autowired
	private GenericDao<CreditoEntiti> dao;
	
	@Autowired
	private GenericDao<BitacoraEntiti> daobita;
	
	@Autowired
	private BitacoraService bitaService;
	

	@Override
	public void save(CreditoEntiti Entity) throws Exception {
		// TODO Auto-generated method stub
		dao.save(Entity);
	}

	@Override
	public List<CreditoEntiti> getAllCreditByRut(long rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAllbyRut(CreditoEntiti.class, rut);
	}
	
	@Override
	public List<BitacoraEntiti> getAllRechazadoByRut(long rut, long numOferta) throws Exception {
		// TODO Auto-generated method stub
		return daobita.findAllRechazadoByRut(BitacoraEntiti.class, rut, numOferta);
	}

	@Override
	public CreditoEntiti getCreditById(long id) throws Exception{
		// TODO Auto-generated method stub
		return dao.findById(CreditoEntiti.class, id);
	}

	@Override
	public void caducar() throws Exception {
		// TODO Auto-generated method stub
		
		//Se busca todos los créditos Disponibles
		List<CreditoEntiti> listaDisponibles=  dao.findAllbyEstado(CreditoEntiti.class, Configuraciones.DISPONIBLE);
		
		//Por cada Crédito se informa a SAP y se guarda en bitácora
		for (Iterator iterator = listaDisponibles.iterator(); iterator
				.hasNext();) {
			CreditoEntiti credito = (CreditoEntiti) iterator.next();
			
			//Se informa a CRM
			WsCargaCrm crm = new WsCargaCrm();

			crm.setEstado(Configuraciones.CADUCADO);
			crm.setNumeroOferta(String.valueOf(credito.getNumeroOferta()));

			logger.info("invocando ws CargaCRM para Caducar crédito: " + credito.getNumeroOferta());
			Oa_CargaFirmaWebFS_DT res = crm.wsCargaCrm(crm);
			
			//Se graba en bitácora
			BitacoraEntiti bita = new BitacoraEntiti();

			bita.setRutcliente(credito.getRutCliente());
			bita.setDvcliente(credito.getDvCliente());
			bita.setFechaAprobRech(new Date());
			bita.setFolioCredito(credito.getFolioCredito());
			bita.setNumeroOferta(credito.getNumeroOferta());
			bita.setIdAprobRech(Configuraciones.CADUCADO);
			bita.setEstadoOfertaCrm("NOK");
			bita.setNumeroOfertaCrm(res.getNUM_OFERTA());
			//bita.setIpAcceso("server");
			logger.info("guardando bitácora");
			bitaService.save(bita);
			
		}
				
		dao.caducar(CreditoEntiti.class);
	}

	@Override
	public void updateCredit(long id, String estado) {
		// TODO Auto-generated method stub
		dao.updateById(CreditoEntiti.class, id, estado);
	}

}
